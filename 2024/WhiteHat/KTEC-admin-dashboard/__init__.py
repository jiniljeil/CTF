from flask import Flask
import secrets
import logging
import click

class RemoveColorFilter(logging.Filter):
    def filter(self, record):
        if record and record.msg and isinstance(record.msg, str):
            record.msg = click.unstyle(record.msg)
            return True

class DebugPinFilter(logging.Filter):
    def filter(self, record):
        return 'Debugger PIN' not in record.getMessage() and '/console' not in record.getMessage()

def create_app():
    app = Flask(__name__)

    app.secret_key = secrets.token_hex()

    logger = app.logger
    logger.setLevel(logging.DEBUG)

    file_handler = logging.FileHandler("logs/server.log", encoding='utf-8')
    logger = logging.getLogger('werkzeug')
    logger.setLevel(logging.INFO)
    logger.propagate = False
    logger.addHandler(file_handler)
    logger.addFilter(DebugPinFilter())
    logger.addFilter(RemoveColorFilter())

    from views import index, admin
    app.register_blueprint(index.bp)
    app.register_blueprint(admin.bp)

    return app


if __name__ == "__main__":
    app = create_app()
    app.run(host="0.0.0.0", threaded=True, debug=True, port=5000)