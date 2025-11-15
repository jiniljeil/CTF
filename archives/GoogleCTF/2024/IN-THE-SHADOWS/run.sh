#!/bin/bash
set -eou

kctf_setup
kctf_drop_privs nsjail --config /nsjail.cfg -- /nsjail_entrypoint.sh ${PORT:-1337} ${XSSBOT_HOST:-} ${XSSBOT_PORT:-}
