import HeatMap from './HeatMap'

export default function HeatMapContainer() {
    return (
        <div className = "border">
            <p className = "title">Zone Temperature</p>
            <HeatMap type="temperature" />
            <p style={{borderTop:"1px solid black"}} className = "title"><br/>Zone Airflow</p>
            <HeatMap type="airflow" />
        </div>
    )
}
