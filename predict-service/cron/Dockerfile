FROM python:3.8-alpine

COPY ./script/* ./
COPY root /var/spool/cron/crontabs/root
RUN pip3 install -r requirements.txt
CMD python3 /mysql_redis.py && crond -l 2 -f
