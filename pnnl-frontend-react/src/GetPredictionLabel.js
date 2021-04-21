export default function GetPredictionLabel(data) {
    let labels = {};
    for (const date in data) {
        for (const label in data[date]) {
            labels[label] = true;
        }
    }
    return Object.keys(labels);
}