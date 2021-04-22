import { HUNTING_SERVICE_URL } from './config';

export default async function HuntingServiceDataAdaptor() {

    let url = HUNTING_SERVICE_URL + '/hunting/hunting/today';

    async function getData() {
        let result = await (await fetch(url)).json();
        return result;
    }
    let data = await getData();
    console.log(data);
    return data;
}