import React, { useState, useEffect } from 'react';
import DataAdaptor from './DataAdaptor';
import ReactApexChart from "react-apexcharts";
import { Date, Promise } from "globalthis/implementation";
import moment from 'moment';
import GetStartDate from './GetStartDate';
import GetHeatMapWeek from './GetHeatMapWeek';
import GetPredictionLabel from './GetPredictionLabel';

export default function HeatMap(props) {

    const END_DATE = moment().subtract(1, 'days').toDate();
    let START_DATE = new Date();
    let LABELS_IDX = {};
    const COLORS = ['#FFCF9C', '#A4D4B4', '#3B1C32', '#CA054D', '#173F5F', '#20639B', '#3CAEA3', '#F6D55C', '#ED553B', '#89045D']
    const NUM_WEEKS = 4;

    // Beginning state for data and options for heatmap 
    const [state, setState] = useState({series: [
        {
            name: '',
            data: []
        },
        {
            name: '',
            data: []
        },
        {
            name: '',
            data: []
        },
        {
            name: '',
            data: []
        },
        ],
        options: {
            chart: {
                height: 350,
                type: 'heatmap',
                toolbar: {
                    show: false
                  }
            },
            plotOptions: {
                heatmap: {
                    shadeIntensity: 0.5,
                    radius: 0,
                    useFillColorAsStroke: true,
                    colorScale: {
                        ranges: []
                    }
                }
            },
            dataLabels: {
                enabled: false
            },
            stroke: {
                width: 1
            },
        },
    })

    // changes to heatmap start here
    useEffect (() => {
        
        getData();
    }, []);
  
   // Get data by getting Sunday 4 weeks ago, retrieve data, get and set labels,
   // store data in series format, update state
    const getData = () => {
        const p = new Promise((resolve, reject)=>{
            resolve(START_DATE.setTime(GetStartDate()));
        })
        .then(() => { return DataAdaptor(props.type, START_DATE, END_DATE); })
        .then((res) => {
            const newLabels = GetPredictionLabel(res);
            createLabelIdx(newLabels); // map labels to index
            return res
        }).then((res) =>{
            addColors(setColorRanges);
            return res
        }).then((res) => { 
            // creates 4 weeks of data based on identifying the label with largest frequency
            // data not available is returned as a string '-1'
            return getHeatMapMonth(res, LABELS_IDX);
        }).then((newSeries) => {
            setState((prevState) => {
                return { ...prevState, series: newSeries }
            })
        })  
    }

    // Get 4 weeks of data
    const getHeatMapMonth = (data, LABELS_IDX) => {
        
        // represents data for one week
        let series = [] 

        let dateIdx = 0;
        // read all data, create computations for each week, return a "series" (month of data)
        for (let i=0; i<NUM_WEEKS; i++) {
            const data_keys = Object.keys(data);
            const data_values = Object.values(data);
            let weekResult = GetHeatMapWeek(data_keys, data_values, dateIdx, LABELS_IDX)
            dateIdx = weekResult.updatedDateIdx;
            let weekData = weekResult.weekData;
            series.push(
               weekData
            );
    }
    return series.reverse(); 
    }

    // map prediction labels to a number
    const createLabelIdx = (labels) => {
        // A label with "-1" means that there is no data for that day
        LABELS_IDX['-1'] = -1;
        for (let i=0; i<labels.length; i++) {
            LABELS_IDX[labels[i]] = i;
        }
    }

    const setColorRanges =  (range) => {

        setState((prevState) => {
        return { ...prevState, options: {...prevState.options.plotOptions, plotOptions: {...prevState.options.plotOptions.heatmap, heatmap: {...prevState.options.plotOptions.heatmap.colorScale, colorScale:{...prevState.options.plotOptions.heatmap.colorScale.ranges, ranges: range}}}}  }
        })
    }

    const addColors = (callback) => {

        let range = [];
        let i = 0;
        let newName = '';
        let newColor = '';    

        for (const [key, value] of Object.entries(LABELS_IDX)) {
            if (value == -1) {
                newName = 'Unavailable'
                newColor = '#DEDEDE';
            } else {
                newName = key;
                newColor = COLORS[value%10]; // if # of labels > 10, colors are reused
            }
            range.push(
                {
                    from: value,
                    to: value,
                    name: newName,
                    color: newColor
                }
            );
            i++;
          }

          callback(range);
    }

    return (
        <div id="chart">
		<ReactApexChart options={state.options} series={state.series} type="heatmap" height={350} />
	  	</div>
    )
}
