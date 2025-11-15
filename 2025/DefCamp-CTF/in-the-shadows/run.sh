#!/bin/sh

SOURCE_PATH="/mnt/flags/flags.txt"
DEST_PATH="/app/flag.txt"

# Copy flag from mounted volume or use default
if [ -f "$SOURCE_PATH" ]; then
    cp "$SOURCE_PATH" "$DEST_PATH"
    rm "$SOURCE_PATH"
    echo "Flag placed from $SOURCE_PATH to $DEST_PATH"
else
    echo "DCTF{real_flag_on_remote}" > "$DEST_PATH"
    echo "Default flag created at $DEST_PATH"
fi

# Start the Flask application
python3 app.py