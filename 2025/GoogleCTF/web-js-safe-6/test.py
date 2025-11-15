encrypted = [100,42,40,48,23,8,20,30,19,32,45,58,88,57,16,28,85,40,23,49,77,33,4,42,21,58,58,3,45,21,51,84,70]
flag = "0BACHagAgHHe0Ptx0FHW!@cuzHepHvA12?ceHFF_cWb_aHe"

password = [ord(c) for c in flag]
decrypted = ''.join(chr(c ^ password[i % len(password)]) for i, c in enumerate(encrypted))

print(decrypted)
