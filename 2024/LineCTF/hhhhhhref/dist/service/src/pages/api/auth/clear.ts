import { getServerSession } from 'next-auth';
import { nextAuthOptions } from './[...nextauth]';
import Redis from 'ioredis';

export default async function handler(req: any, res: any) {
    if (req.method === 'POST') {
        await clear(req, res);
    } else {
        return res.status(405).json({ message: 'Method Not allowed' });
    }
}

async function clear(req: any, res: any) {
    const session = await getServerSession(req, res, nextAuthOptions);

    if (!session) {
        return res.status(200).end();
    }

    if (!session.user) {
        return res.status(500).end();
    }

    const redis = new Redis(6379, 'redis');
    await redis.del(session.user.userId);
    redis.disconnect();
    return res.status(200).end();
}
