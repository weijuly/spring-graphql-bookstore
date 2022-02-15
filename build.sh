#!/bin/bash

WORKDIR=`pwd`
echo ${WORKDIR}
cd ${WORKDIR}/bookstore-database
/bin/bash build.sh
cd ${WORKDIR}/bookstore-gateway
/bin/bash build.sh
cd ${WORKDIR}/bookstore-service
mvn clean install -Prelease
