import { console, Number, Object } from 'globalthis/implementation';
import React from 'react';
import ReactApexChart from "react-apexcharts";
import DataAdaptor from './DataAdaptor';


export default function PieChart(props) {
    
    const options = 
        {chart: {
                    with: 348,
                    type: 'pie',
                },
                labels: Object.keys(props.data),
                responsive: [{
                    breakpoint: 480,
                    options: {
                        chart: {
                            width: 200
                        },
                        legend: {
                            position: 'bottom'
                        },
                    }
                }]
        };

    return (
        <ReactApexChart options={options} series={Object.values(props.data).map(val => Number(val))} type="pie" width={350} />
    );
}