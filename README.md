# react-app

This repo contains three components of our reac app:

- alert-service
- pnnl-frontend-react
- react-app-redis-backend

Both _alert-service_ and _react-app-redis-backend_ serve as the backend of _pnnl-frontend-react_.

## react-app-redis-backend (docker compose version)

### What is it?

This service fetches the data from a local redis data-store supposed to be updated daily. The purpose of this service is to increase the responsiveness of our frontend.

### What are the components?

Docker Images:

**cron**: Read data from mysql to redis every 3 hours

**nodejs**: Http server that serve GET requests at port 5000

**redis**: Just redis :)

Sample requests (data available from the passed one month to present day):

GET `http://localhost:5000/range/temperature/2021-03-16/2021-03-17`

GET `http://localhost:5000/day/temperature/2021-03-16`

### How can I launch it?

EZ! Make sure the docker app is up and running on your machine. Then `cd ./react-app-redis-backend` and `docker-compose up -d` (running a set of containers at background)
