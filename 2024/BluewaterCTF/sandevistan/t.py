s = """98 119 99 116 102 123 89 111 85 95 107 78 111 87 95 121 79 117 95 100 49 100 110 116 95 108 48 115 51 95 85 114 95 53 51 76 102 45 99 111 78 116 82 48 76 46 95 76 69 116 39 53 95 115 116 97 114 116 95 97 116 95 116 104 101 95 114 52 105 110 66 48 119 125"""

for x in s.split(" "): 
    print(chr(int(x)), end='')