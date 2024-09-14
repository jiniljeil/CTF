import { prisma } from '../../../../lib/prisma';
import crypto from 'crypto';

export default async function handler(req: any, res: any) {
    if (req.method === 'POST') {
        await register(req, res);
    } else {
        return res.status(405).json({ message: 'Method Not allowed' });
    }
}

async function register(req: any, res: any) {
    const { name, password } = req.body;

    if (name.length < 1 || password.length < 1) {
        return res.status(400).end();
    }

    if (/[^0-9a-zA-Z].*/.test(name)) {
        return res.status(400).end();
    }

    if (name.toLowerCase() === 'admin') {
        return res.status(400).end();
    }

    try {
        await prisma.user.create({
            data: {
                name: name.toLowerCase(),
                passwordHash: crypto
                    .createHash('sha512')
                    .update(password)
                    .digest('hex'),
            },
        });
    } catch (error) {
        return res.status(400).end();
    }
    return res.status(200).end();
}
