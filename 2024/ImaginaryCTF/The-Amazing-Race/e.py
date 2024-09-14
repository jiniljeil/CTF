import requests
import copy 
from collections import deque

MAZE_SIZE = 35
visited = [[0] * MAZE_SIZE for _ in range(MAZE_SIZE)]

dx = [-1, 0, 1, 0]
dy = [ 0, 1, 0,-1]
dir = [ 'L', 'D', 'R', 'U']
curr_y, curr_x = 0, 1 

id = "fbb96d75-3d61-471f-9233-c63548c1b743"
url = f"http://the-amazing-race.chal.imaginaryctf.org"
# url = f"http://localhost:7000"

def move(path): 
    # path.append("D")
    # path.append("R")
    dr = {"L": "left", "D": "down", "R": "right", "U": "up"}
    for x in path:
        r = requests.post(
            f"{url}/move?id={id}&move={dr[x]}", 
        )
        # print(r.status_code)
        # print(r.text) 
        assert r.status_code == 200
    print("Done")
    r = requests.get(
        f"{url}/{id}"
    )
    print(r.text)

r = requests.get(
    f"{url}/{id}"
)

maze = r.text[ r.text.find("<code>")+7 : r.text.find("</code>")-1 ]
mat = [[] for _ in range(MAZE_SIZE)]
i = 0
for j in range(len(maze)):
    if maze[j] == "\n": 
        i = i + 1
        continue
    mat[i].append(maze[j])
maze = mat 

for i in range(len(mat)): 
    for j in range(len(mat[i])): 
        print(mat[i][j], end='')
    print()
    

d = deque([[0, 0, []]])
visited[0][0] = 1

while len(d) != 0: 
    curr = d.popleft() 
    curr_y, curr_x, path = curr[0], curr[1], curr[2]

    if curr_y == MAZE_SIZE - 2 and curr_x == MAZE_SIZE - 2: 
        print(path)
        move(path) 
        break 

    for k in range(4): 
        next_y = curr_y + dy[k]
        next_x = curr_x + dx[k] 

        if next_x < 0 or next_y < 0 or next_y >= MAZE_SIZE - 1 or next_x >= MAZE_SIZE - 1: continue 
        if visited[next_y][next_x] or maze[next_y][next_x] == '#': continue 
        visited[next_y][next_x] = 1
        new_path = copy.deepcopy(path) 
        new_path.append(dir[k])
        d.append([next_y, next_x, new_path])

for i in range(len(mat)): 
    for j in range(len(mat[i])): 
        print(visited[i][j], end='')
    print()
