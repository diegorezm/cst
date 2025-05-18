#!/bin/bash

to_load=$1
case $to_load in
  "local")
    export $(cat .env.local | xargs)
    ;;
  "prod")
      export $(cat .env.prod | xargs)
  ;;
  *)
  echo -n "Either 'local' or 'prod'"
  ;;
esac