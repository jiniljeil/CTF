import NextAuth from 'next-auth/next';
import CredentialsProvider from 'next-auth/providers/credentials';
import { type NextAuthOptions } from 'next-auth';
import Redis from 'ioredis';
import { prisma } from '../../../../lib/prisma';
import crypto from 'crypto';

export const nextAuthOptions: NextAuthOptions = {
    providers: [
        CredentialsProvider({
            name: 'Credentials',
            credentials: {
                name: {
                    label: 'Username',
                    type: 'text',
                    placeholder: 'neko1337',
                },
                password: { label: 'Password', type: 'password' },
            },
            // All you can register as 'USER' role without any requirements!
            async authorize(credentials, req) {
                const redis = new Redis(6379, 'redis');

                if (!credentials) {
                    return null;
                }

                if (!credentials.name || !credentials.password) {
                    return null;
                }
            
                if (/[^0-9a-zA-Z].*/.test(credentials.name)) {
                    return null;
                }

                const loginUser = await prisma.user.findUnique({
                    where: {
                        name: credentials.name,
                    },
                });

                if (!loginUser) {
                    return null;
                }

                const attemptPassHash = crypto
                    .createHash('sha512')
                    .update(credentials.password)
                    .digest('hex');
                if (attemptPassHash !== loginUser.passwordHash) {
                    return null;
                }

                if (credentials?.name === 'admin') {
                    const userId = 'ADMIN_' + loginUser.id;
                    const userName = loginUser.name;
                    const userRole = 'ADMIN';

                    redis.hset(userId, 'userName', userName);
                    redis.hset(userId, 'userRole', userRole);
                    redis.hset(
                        userId,
                        'adminSecretToken',
                        process.env.ADMIN_SECRET_TOKEN as string
                    );

                    return {
                        userId,
                        name: userName,
                        role: userRole,
                    };
                }

                // TODO: check malicious chars
                const userId = 'USER_' + loginUser.id;
                const userName = loginUser.name;
                const userRole = 'USER';
                const normalUser = {
                    userId,
                    name: userName,
                    role: userRole,
                };

                // NOTE: the following will ONLY be executed on first login after registration
                if (!(await redis.exists(userId))) {
                    if (!req.headers) {
                        return null;
                    }

                    // prevent overwriting of default keys
                    if (req.headers['x-user-token-key']) {
                        if (
                            ['username', 'userrole'].includes(
                                req.headers['x-user-token-key'].toLowerCase()
                            )
                        ) {
                            return null;
                        }
                    }

                    // set default values
                    const defaultKey = 'userSecretToken';
                    req.headers['x-user-token-key'] =
                        req.headers['x-user-token-key'] || defaultKey;
                    req.headers['x-user-token-value'] =
                        req.headers['x-user-token-value'] ||
                        crypto.randomUUID().toString();

                    // save registered user info in redis
                    redis.hset(userId, 'userName', userName);
                    redis.hset(userId, 'userRole', userRole);
                    redis.hset(
                        userId,
                        req.headers['x-user-token-key'].toString(),
                        req.headers['x-user-token-value'].toString()
                    );
                }

                redis.disconnect();
                return normalUser as any;
            },
        }),
    ],
    secret: process.env.JWT_SECRET,
    callbacks: {
        jwt({ token, user }) {
            return { ...token, ...user };
        },
        session({ session, token }) {
            return { expires: session.expires, user: { ...token } };
        },
    },
    session: {
        strategy: 'jwt',
        maxAge: 24 * 60 * 60,
    },
};

// https://github.com/nextauthjs/next-auth/discussions/4325
export default NextAuth(nextAuthOptions);
