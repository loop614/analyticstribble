const TIME_WINDOW: number = 500;
let previousTimestamp: number = Date.now();

type Vector2 = {
    x: number,
    y: number,
    dt: number
}

type Replay = {
    domain: string,
    customer: string,
    trails: Vector2[]
}

let pointsRecorded: Vector2[] = [];
let mainDiv: HTMLElement = document.querySelector("#div1") as HTMLElement;
let recordButton: HTMLButtonElement = document.querySelector("#record") as HTMLButtonElement;
let replayButton: HTMLButtonElement = document.querySelector("#replay") as HTMLButtonElement;
let cursorDiv: HTMLElement = document.querySelector("#cursorDiv") as HTMLElement;
let isRecording: boolean = false;
const exampleDomain: string = "example.com";
const exampleCustomer: string = "customer1@otherexample.com";

((): void => {
    onmousemove = (event) => {
        if (!isRecording) {
            return;
        }
        if (!mainDiv.contains(event.target as Node)) {
            return;
        }

        let nowTimestamp = Date.now();
        let dt = nowTimestamp - previousTimestamp;
        if (dt > TIME_WINDOW) {
            console.log(dt, event.offsetX, event.offsetY);
            pointsRecorded.push({ x: event.offsetX, y: event.offsetY, dt: dt });
            previousTimestamp = nowTimestamp;
        }
    };

    replayButton.onclick = async () => {
        const now: Date = new Date();
        const url: string = `http://localhost:12345/tracker/${exampleDomain}/${exampleCustomer}/${now.toISOString().split('T')[0]}`;
        const response = await fetch(url);
        if (!response.ok) {
            throw new Error(`Response status: ${response.status}`);
        }

        const replay: Replay = await response.json();
        let dt: number = 0;
        let dtSum: number = 0;
        let pivot: number = 0;
        let previousTimestamp: number | undefined = undefined;
        function step(nowTimestamp: number): void {
            if (previousTimestamp === undefined) {
                previousTimestamp = nowTimestamp;
            }

            dt = nowTimestamp - previousTimestamp;
            dtSum += dt;
            if (pivot < replay.trails.length && dtSum > replay.trails[pivot].dt) {
                cursorDiv.style.left = replay.trails[pivot].x + "px";
                cursorDiv.style.top = replay.trails[pivot].y + "px";
                pivot++;
                dtSum = 0;
            }

            previousTimestamp = nowTimestamp;
            if (pivot < replay.trails.length) {
                window.requestAnimationFrame(step);
            }
        }
        window.requestAnimationFrame(step);
    };

    recordButton.onclick = () => {
        isRecording = !isRecording;
        recordButton.innerText = isRecording ? "Stop" : "Record";
        if (!isRecording && pointsRecorded.length > 0) {
            fetch("http://localhost:12345/tracker/new", {
                method: 'POST',
                body: JSON.stringify(
                    {
                        trails: pointsRecorded,
                        domain: exampleDomain,
                        customer: exampleCustomer
                    }),
                headers: {
                    'Content-Type': 'application/json; charset=UTF-8',
                    'Access-Control-Allow-Origin': '*'
                }
            }).then(
                response => {
                    console.log(response.json());
                    pointsRecorded = [];
                }
            ).catch(error => console.log(error))
                .finally(() => {
                    replayButton.disabled = false
                });
        }
    };
})();
