#!/bin/bash
./load_env.sh prod


./mvnw clean install -Dmaven.test.skip=true
