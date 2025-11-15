import random
import string
import os


def copy(src, dst):
    for k, v in src.items():
        if hasattr(dst, "__getitem__"):
            if dst.get(k) and type(v) == dict:
                copy(v, dst.get(k))
            else:
                dst[k] = v
        elif hasattr(dst, k) and type(v) == dict:
            copy(v, getattr(dst, k))
        else:
            setattr(dst, k, v)


def rand_name(length):
    characters = string.ascii_letters + string.digits
    return "".join(random.choice(characters) for _ in range(length))


GAME_URL = os.environ.get("GAME_URL") or "http://localhost:5000/"


def game_url(path="game"):
    return GAME_URL + path
