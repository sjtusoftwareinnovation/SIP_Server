#!/bin/bash

echo "=== Starting to deploy ==="
scp -r $TRAVIS_BUILD_DIR dbc@47.101.58.195:~/SIP_Server_Deploy/
echo "=== End deploy ==="
