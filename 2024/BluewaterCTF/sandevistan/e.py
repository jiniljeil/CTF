from hashlib import sha256
from time import perf_counter

def main(name, leadingzero):
    attempts = 1
    prefix = '0' * leadingzero
    while not (hash_ := sha256(f'{name}{attempts}'.encode()).hexdigest()).startswith(prefix):
        attempts += 1
    return name, hash_, attempts

gottem = input("Hash > ")

start = perf_counter()
name, hash_, attempts = main(gottem, 6)
end = perf_counter()
print(f'Found hash: {hash_}')
print(f'Number of attempts: {attempts}')
print(f'Execution time: {end-start:.15f} seconds')
print(f'Final pre-image: {name}{attempts}\n')