import StackedChart from "./StackedChart";

export default function StackChartContainer() {
    return (
        <div className = "border">
	    <p className = "title">Zone Temperature</p>
            <StackedChart type={'temperature'} />
	    <p style={{borderTop:"1px solid black"}} className = "title"><br/>Zone Airflow</p>
            <StackedChart type={'airflow'} />
        </div>
    );
}
