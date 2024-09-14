import type { DefaultSession } from 'next-auth';

declare module 'next-auth' {
    interface Session {
        user?: {
            userId: string;
            role: string;
        } & DefaultSession['user'];
    }
}

declare module 'next-auth/jwt' {
    interface JWT {
        accessToken?: string;
    }
}
