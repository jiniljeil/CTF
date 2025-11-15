import crypto from 'crypto';

const CSRF_SECRET = process.env.SECRET || 'SECRET';

export function requireLogin(req, res, next) {
    if (req.session.user !== undefined) {
        if (req.session.user['id'])
            return next();
    }
    res.status(401).send('Login required');
    return
}

export function generateCsrfToken(userId) {
    const nonce = crypto.randomBytes(16).toString('hex');
    const ts = Math.floor(Date.now() / 1000);
    const data = `${userId}:${nonce}:${ts}`;
    const sig = crypto.createHmac('sha256', CSRF_SECRET)
        .update(data)
        .digest('hex');
    return `${nonce}.${ts}.${sig}`;
}

export function verifyCsrfToken(userId, token) {
    if (!token) return false;
    const parts = token.split('.');
    if (parts.length !== 3) return false;

    const [nonce, tsStr, sig] = parts;
    const ts = parseInt(tsStr, 10);

    const now = Math.floor(Date.now() / 1000);
    if (now - ts > 120) {
        return false;
    }

    const data = `${userId}:${nonce}:${ts}`;
    const expectedSig = crypto.createHmac('sha256', CSRF_SECRET)
        .update(data)
        .digest('hex');

    return crypto.timingSafeEqual(
        Buffer.from(sig),
        Buffer.from(expectedSig)
    );
}

export function csrfPrepare(req, res, next) {
    const token = generateCsrfToken(req.session.user ? req.session.user.id : 'guest')
    res.locals._csrf = token
    next()
}

export function csrfProtect(req, res, next) {
    const token = req.body._csrf

    if (!token) {
        return res.status(403).send('No CSRF token submited.')
    }

    if (!verifyCsrfToken(req.session.user ? req.session.user.id : 'guest', token)) {
        return res.status(403).send('Wrong or expired CSRF token.')
    }
    next()
}

export function cacheFiles(cache) {
    return function (req, res, next) {
        const key = req.originalUrl
        const cached = cache.get(key);
        if (cached) {
            res.set('X-Cache-Status', 'HIT');
            return res.send(cached);
        }

        const originalSend = res.send.bind(res)
        res.send = (body) => {
            cache.set(key, body)
            res.set('X-Cache-Status', 'MISS')
            return originalSend(body)
        }
        next();
    }
}

export function requireEsotericKnowledge(req, res, next) {
    const user = req.session.user;
    if (user && user.role === 'guide') {
        return next();
    }
    res.status(403).send('You are not yet prepared');
}
