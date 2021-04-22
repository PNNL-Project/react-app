# React Application

## Summary
![]()
On the top of the main page has three links: Home, Alert, and Grafana.
The Grafana link will redirect users to Grafana channel which is mentioned above.
The Home link will redirect users to visualization components which is also default when users open the application.
The Alerts link will redirect users to the hunting service page.
### Visualization Components

There are three visualization components: pie chart, stacked bar, and heat map.They are using the same datasource. Users can use the dropdown menu to switch components. All of the labels are dynamic.
Datasource
All of the data is from a Redis URL.
### Pie Chart
![](images/react-fe-pie-charts.png)

The pie chart shows the data from the last 4 weeks(start from Sunday). The airflow and the temperature are two separated channels. If the data is missing or -1(invalid data), the pie chart for that day will show “No Data”.
### Stacked Bar
![](images/react-fe-stackedbar-1.png)
![](images/react-fe-stackedbar-2.png)

The stacked bar shows the data from the latest week. The airflow and the temperature are two separated channels. If the data is missing or -1(invalid data), there will be no bar on that day.
### Heat Map
![](images/react-fe-heatmap.png)

The heat map shows the data from the last 4 weeks(start from Sunday). The airflow and the temperature are two separated channels. The label with highest frequency is presented each day.
Alerts(Hunting Service)
### Alerts
![](images/react-fe-alerts.png)
The page presents the device, timestamp of the hunting alerts. Users can use### the button at the top left to filter.
Datasource
All of the data is from a URL.
### Details

By clicking the DETAILS button on each row, users can get the detail of the abnormal moments.

### Run React App

`npm start` to run the app in the development mode.\
Open [http://localhost:3000](http://localhost:3000) to view it in the browser.

The page will reload if you make edits.\
You will also see any lint errors in the console.

## Learn More

You can learn more in the [Create React App documentation](https://facebook.github.io/create-react-app/docs/getting-started).

To learn React, check out the [React documentation](https://reactjs.org/).
