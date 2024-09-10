const TIME_WINDOW: number = 500;

type Vector2 = {
    x: number,
    y: number,
    dt: number
}

type Replay = {
    domain: string,
    customer: string,
    datenano: number,
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
const onemilion: number = 1000000;

((): void => {
    let previousTimestamp: number | undefined = undefined;
    let startingDatenano: number | undefined = undefined;
    let dt: number = 0;

    onmousemove = (event) => {
        if (!isRecording) {
            previousTimestamp = undefined;
            return;
        }
        if (!mainDiv.contains(event.target as Node)) {
            previousTimestamp = undefined;
            return;
        }
        let nowTimestamp = Date.now();
        dt = previousTimestamp === undefined ? 0 : (dt + (nowTimestamp - previousTimestamp));
        if (startingDatenano === undefined) {
            startingDatenano = Date.now() * onemilion;
        }

        if (dt > TIME_WINDOW) {
            console.log(dt, event.offsetX, event.offsetY);
            pointsRecorded.push({ x: event.offsetX, y: event.offsetY, dt: dt });
        }
        previousTimestamp = nowTimestamp;
    };

    replayButton.onclick = async () => {
        const now: Date = new Date();
        const lastweek: Date = new Date(Date.now() - 7 * 24 * 60 * 60 * 1000);
        const url: string = `http://localhost:12345/tracker/${exampleDomain}/${exampleCustomer}/${lastweek.getTime()*onemilion}/${now.getTime()*onemilion}`;
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
                        customer: exampleCustomer,
                        datenano: startingDatenano,
                    }),
                headers: {
                    'Content-Type': 'application/json; charset=UTF-8',
                    'Access-Control-Allow-Origin': '*'
                }
            }).then(
                response => {
                    console.log(response.json());
                    pointsRecorded = [];
                    startingDatenano = undefined;
                }
            ).catch(error => console.log(error))
                .finally(() => {
                    replayButton.disabled = false
                });
        }
    };
})();
