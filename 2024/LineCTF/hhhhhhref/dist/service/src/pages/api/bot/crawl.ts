import Redis from 'ioredis';

// const redis = new Redis(Number(process.env.REDISBOT_PORT) || 6379, 'redisbot');
const redis = new Redis(
    Number(process.env.REDISBOT_PORT) || 6379,
    process.env.REDISBOT_HOST || 'redisbot'
);

export default async function handler(req: any, res: any) {
    if (req.method === 'POST') {
        await queue(req, res);
    } else {
        return res.status(405).json({ message: 'Method Not allowed' });
    }
}

async function queue(req: any, res: any) {
    const { name, password, errorCode } = req.body;
    const request = 'REQ-' + name + '-' + password + '-' + errorCode;
    try {
        await redis.rpush('crawl_url', request);
        return res.status(200).end();
    } catch (error) {
        console.error(error);
        return res.status(500).json({ message: 'Something went wrong' });
    }
}
