const TIME_WINDOW: number = 1000;
const POINTS_TO_SAVE: number = 10;
let previousTimestamp: number = Date.now();

type Vector2 = {
    x: number,
    y: number,
}

let points: Vector2[] = [];
let mainDiv: HTMLElement = document.querySelector("#div1") as HTMLElement;

((): void => {
    onmousemove = (event) => {
        if (!mainDiv.contains(event.target as Node)) {
            return;
        }

        let nowTimestamp = Date.now();
        let dt = nowTimestamp - previousTimestamp;
        if (dt > TIME_WINDOW) {
            console.log(dt, event.offsetX, event.offsetY);
            points.push({ x: event.offsetX, y: event.offsetY });
            previousTimestamp = nowTimestamp;
        }
        if (points.length >= POINTS_TO_SAVE) {
            console.log(points);
            points = [];
        }
    };
})();
