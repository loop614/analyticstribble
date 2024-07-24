const TIME_WINDOW: number = 500;
const POINTS_TO_SAVE: number = 6;
let previousTimestamp: number = Date.now();

type Vector2 = {
    x: number,
    y: number,
    dt: number
}

let points: Vector2[] = [];
let mainDiv: HTMLElement = document.querySelector("#div1") as HTMLElement;
let playButton: HTMLElement = document.querySelector("#play") as HTMLButtonElement;
const exampleDomain: string = "example.com";
const exampleCustomer: string = "customer1@otherexample.com";

((): void => {
    onmousemove = (event) => {
        if (!mainDiv.contains(event.target as Node)) {
            return;
        }

        let nowTimestamp = Date.now();
        let dt = nowTimestamp - previousTimestamp;
        if (dt > TIME_WINDOW) {
            console.log(dt, event.offsetX, event.offsetY);
            points.push({ x: event.offsetX, y: event.offsetY, dt: dt });
            previousTimestamp = nowTimestamp;
        }
        if (points.length >= POINTS_TO_SAVE) {
            fetch("http://localhost:12345/tracker/new", {
                method: 'POST',
                body: JSON.stringify(
                    {
                        trails: points,
                        domain: exampleDomain,
                        customer: exampleCustomer
                    }),
                headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }
            }).then(
                response => {
                    console.log(response.json());
                    points = [];
                }
            ).catch(error => console.log(error));
        }
    };

    playButton.onclick = async () => {
        const response = await fetch("http://localhost:12345/tracker", {
            method: 'POST',
            body: JSON.stringify(
                {
                    trails: points,
                    domain: exampleDomain,
                    customer: exampleCustomer
                }),
            headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }
        });
    };
})();
