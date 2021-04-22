import React, {useEffect, useState} from 'react';
import {Bar} from 'react-chartjs-2';
import DataAdaptor from "./DataAdaptor";

const options = {
    scales: {
        yAxes: [
            {
                stacked: true,
                ticks: {
                    beginAtZero: true,
                },
            },
        ],
        xAxes: [
            {
                stacked: true,
            },
        ],
    },
}

export default function StackedChart(props) {
    const [tempDataObject, setTempDataObject] = useState({});
    const currentDate = new Date();
    const pastDate = new Date(currentDate.getTime() - (6 * 24 * 60 * 60 * 1000));
    const COLORS = ['#FFCF9C', '#A4D4B4', '#3B1C32', '#CA054D', '#173F5F', '#20639B', '#3CAEA3', '#F6D55C', '#ED553B', '#89045D'];
    const tempDateArray = [];
    let dataObject = {};
    useEffect(() => {
        getData();
    }, []);

    const getData = async () => {
        let res = await DataAdaptor(props.type, pastDate, currentDate);
        setTempDataObject(res);
    }

    for (const key in tempDataObject) {
        tempDateArray.push(key);
        for (const key2 in tempDataObject[key]) {
            dataObject[key2] = [0, 0, 0, 0, 0, 0, 0];
        }
    }

    let j = 0;
    for (const key in tempDataObject) {
        for (const key2 in tempDataObject[key]) {
            dataObject[key2][j] = (tempDataObject[key][key2] < 0 ? 0 : tempDataObject[key][key2]);
        }
        j++;
    }

    const dataset = [];
    let i = 0;
    for (const [key, value] of Object.entries(dataObject)) {
        dataset.push({
            label: key,
            data: value,
            backgroundColor: COLORS[i],
        });
        i++;
    }

    const Data = {
        labels: tempDateArray,
        datasets: dataset,
    }

    return (
        <>
            <div style={{margin: '10px', display: 'flex', justifyContent: 'start', maxWidth: 800}}>
                <Bar data={Data} options={options}/>
            </div>
        </>
    );
}
;


