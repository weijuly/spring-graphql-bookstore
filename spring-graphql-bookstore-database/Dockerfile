FROM mysql:8

MAINTAINER gopikrishnan.ganesan@gmail.com

ENV MYSQL_ROOT_PASSWORD=rootpass

COPY init.sql /docker-entrypoint-initdb.d
