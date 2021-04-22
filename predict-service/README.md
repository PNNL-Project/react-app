# react-app-redis-backend (docker compose version)

Docker Images:

**cron**: Read data from mysql to redis every 3 hours

**nodejs**: Http server that serve GET requests at port 5000

**redis**: Just redis :)

This service fetches the data from a local redis data-store supposed to be updated daily. The purpose of this service is to increase the responsiveness of our frontend.

Sample requests (data available from the passed one month to present day):

GET `http://localhost:5000/range/temperature/2021-03-16/2021-03-17`

GET `http://localhost:5000/day/temperature/2021-03-16`

# How can I use it?

EZ! Make sure the docker app is up and running on your machine. Then `cd ./react-app-redis-backend` and `docker-compose up -d` (running a set of containers at background)
