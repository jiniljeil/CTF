#!/bin/sh
docker build . -t scorepost-generator
docker container rm -f scorepost-generator
docker run --rm --name scorepost-generator -p 1337:1337 scorepost-generator
