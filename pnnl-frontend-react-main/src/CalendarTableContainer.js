import CalendarTable from './CalendarTable'

export default function CalendarTableContainer() {
    return (
        <div className = "border">
            <p className = "title">Zone Temperature</p>
            <CalendarTable type = 'temperature' />
            <p style={{borderTop:"1px solid black"}} className = "title"><br/>Zone Airflow</p>
            <CalendarTable type = 'airflow' />
        </div>
    )
}
