import './App.css';
import React from 'react';
import {DataGrid, GridRowsProp, GridColDef} from '@material-ui/data-grid';


export default function HuntingServiceModal(props): React.Component {

    let i = 1;
    const dataset = [];

    for(let x in props.data){
        const upperBound = props.data[x]['zoneCoolingTemperatureSetPoint'];
        const LowerBound = props.data[x]['zoneHeatingTemperatureSetPoint'];
        const currentTemp = props.data[x]['zoneTemperature'];
        const status = currentTemp < LowerBound? "Below zone temperature heating setpoint" : currentTemp > upperBound?
            "Above zone temperature cooling setpoint" : "";

        dataset.push({
            id: i,
            col1 : props.data[x]['vavName'],
            col2 : props.data[x]['time'],
            col3 : props.data[x]['zoneTemperature'],
            col4 : status
        });
        i++;
    }

    const rows: GridRowsProp = dataset;

    const columns: GridColDef[] = [
        {field: 'col1', headerName: 'Device Name', width: 300},
        {field: 'col2', headerName: 'Timestamp', width: 300},
        {field: 'col3', headerName: 'Value', width: 300},
        {field: 'col4', headerName: 'Zone Temperature Status', width: 300},
    ];

    return (
        <div style={{height: 800, width: '100%', fontFamily: 'serif'}}>
            <DataGrid rows={rows} columns={columns}/>
        </div>
    );
}
