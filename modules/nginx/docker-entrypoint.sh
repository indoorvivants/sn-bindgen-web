#!/usr/bin/env bash

set -xeuo pipefail

/app/web --host localhost --port 8081 &
nginx -g "daemon off;"

wait -n

exit $?
