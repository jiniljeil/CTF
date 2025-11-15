var e = Object.create
    , t = Object.defineProperty
    , n = Object.getOwnPropertyDescriptor
    , r = Object.getOwnPropertyNames
    , i = Object.getPrototypeOf
    , a = Object.prototype.hasOwnProperty
    , o = (e, t) => () => (t || e((t = {
        exports: {}
    }).exports, t),
        t.exports)
    , s = (e, i, o, s) => {
        if (i && typeof i == `object` || typeof i == `function`)
            for (var c = r(i), l = 0, u = c.length, d; l < u; l++)
                d = c[l],
                    !a.call(e, d) && d !== o && t(e, d, {
                        get: (e => i[e]).bind(null, d),
                        enumerable: !(s = n(i, d)) || s.enumerable
                    });
        return e
    }
    , c = (n, r, a) => (a = n == null ? {} : e(i(n)),
        s(r || !n || !n.__esModule ? t(a, `default`, {
            value: n,
            enumerable: !0
        }) : a, n));
(function () {
    let e = document.createElement(`link`).relList;
    if (e && e.supports && e.supports(`modulepreload`))
        return;
    for (let e of document.querySelectorAll(`link[rel="modulepreload"]`))
        n(e);
    new MutationObserver(e => {
        for (let t of e)
            if (t.type === `childList`)
                for (let e of t.addedNodes)
                    e.tagName === `LINK` && e.rel === `modulepreload` && n(e)
    }
    ).observe(document, {
        childList: !0,
        subtree: !0
    });
    function t(e) {
        let t = {};
        return e.integrity && (t.integrity = e.integrity),
            e.referrerPolicy && (t.referrerPolicy = e.referrerPolicy),
            e.crossOrigin === `use-credentials` ? t.credentials = `include` : e.crossOrigin === `anonymous` ? t.credentials = `omit` : t.credentials = `same-origin`,
            t
    }
    function n(e) {
        if (e.ep)
            return;
        e.ep = !0;
        let n = t(e);
        fetch(e.href, n)
    }
}
)();
var l = o((e => {
    function t(e, t) {
        var n = e.length;
        e.push(t);
        a: for (; 0 < n;) {
            var r = n - 1 >>> 1
                , a = e[r];
            if (0 < i(a, t))
                e[r] = t,
                    e[n] = a,
                    n = r;
            else
                break a
        }
    }
    function n(e) {
        return e.length === 0 ? null : e[0]
    }
    function r(e) {
        if (e.length === 0)
            return null;
        var t = e[0]
            , n = e.pop();
        if (n !== t) {
            e[0] = n;
            a: for (var r = 0, a = e.length, o = a >>> 1; r < o;) {
                var s = 2 * (r + 1) - 1
                    , c = e[s]
                    , l = s + 1
                    , u = e[l];
                if (0 > i(c, n))
                    l < a && 0 > i(u, c) ? (e[r] = u,
                        e[l] = n,
                        r = l) : (e[r] = c,
                            e[s] = n,
                            r = s);
                else if (l < a && 0 > i(u, n))
                    e[r] = u,
                        e[l] = n,
                        r = l;
                else
                    break a
            }
        }
        return t
    }
    function i(e, t) {
        var n = e.sortIndex - t.sortIndex;
        return n === 0 ? e.id - t.id : n
    }
    if (e.unstable_now = void 0,
        typeof performance == `object` && typeof performance.now == `function`) {
        var a = performance;
        e.unstable_now = function () {
            return a.now()
        }
    } else {
        var o = Date
            , s = o.now();
        e.unstable_now = function () {
            return o.now() - s
        }
    }
    var c = []
        , l = []
        , u = 1
        , d = null
        , f = 3
        , p = !1
        , m = !1
        , h = !1
        , g = !1
        , _ = typeof setTimeout == `function` ? setTimeout : null
        , v = typeof clearTimeout == `function` ? clearTimeout : null
        , y = typeof setImmediate < `u` ? setImmediate : null;
    function b(e) {
        for (var i = n(l); i !== null;) {
            if (i.callback === null)
                r(l);
            else if (i.startTime <= e)
                r(l),
                    i.sortIndex = i.expirationTime,
                    t(c, i);
            else
                break;
            i = n(l)
        }
    }
    function x(e) {
        if (h = !1,
            b(e),
            !m)
            if (n(c) !== null)
                m = !0,
                    S || (S = !0,
                        O());
            else {
                var t = n(l);
                t !== null && ne(x, t.startTime - e)
            }
    }
    var S = !1
        , C = -1
        , w = 5
        , T = -1;
    function E() {
        return g ? !0 : !(e.unstable_now() - T < w)
    }
    function D() {
        if (g = !1,
            S) {
            var t = e.unstable_now();
            T = t;
            var i = !0;
            try {
                a: {
                    m = !1,
                        h && (h = !1,
                            v(C),
                            C = -1),
                        p = !0;
                    var a = f;
                    try {
                        b: {
                            for (b(t),
                                d = n(c); d !== null && !(d.expirationTime > t && E());) {
                                var o = d.callback;
                                if (typeof o == `function`) {
                                    d.callback = null,
                                        f = d.priorityLevel;
                                    var s = o(d.expirationTime <= t);
                                    if (t = e.unstable_now(),
                                        typeof s == `function`) {
                                        d.callback = s,
                                            b(t),
                                            i = !0;
                                        break b
                                    }
                                    d === n(c) && r(c),
                                        b(t)
                                } else
                                    r(c);
                                d = n(c)
                            }
                            if (d !== null)
                                i = !0;
                            else {
                                var u = n(l);
                                u !== null && ne(x, u.startTime - t),
                                    i = !1
                            }
                        }
                        break a
                    } finally {
                        d = null,
                            f = a,
                            p = !1
                    }
                    i = void 0
                }
            } finally {
                i ? O() : S = !1
            }
        }
    }
    var O;
    if (typeof y == `function`)
        O = function () {
            y(D)
        }
            ;
    else if (typeof MessageChannel < `u`) {
        var ee = new MessageChannel
            , te = ee.port2;
        ee.port1.onmessage = D,
            O = function () {
                te.postMessage(null)
            }
    } else
        O = function () {
            _(D, 0)
        }
            ;
    function ne(t, n) {
        C = _(function () {
            t(e.unstable_now())
        }, n)
    }
    e.unstable_IdlePriority = 5,
        e.unstable_ImmediatePriority = 1,
        e.unstable_LowPriority = 4,
        e.unstable_NormalPriority = 3,
        e.unstable_Profiling = null,
        e.unstable_UserBlockingPriority = 2,
        e.unstable_cancelCallback = function (e) {
            e.callback = null
        }
        ,
        e.unstable_forceFrameRate = function (e) {
            0 > e || 125 < e ? console.error(`forceFrameRate takes a positive int between 0 and 125, forcing frame rates higher than 125 fps is not supported`) : w = 0 < e ? Math.floor(1e3 / e) : 5
        }
        ,
        e.unstable_getCurrentPriorityLevel = function () {
            return f
        }
        ,
        e.unstable_next = function (e) {
            switch (f) {
                case 1:
                case 2:
                case 3:
                    var t = 3;
                    break;
                default:
                    t = f
            }
            var n = f;
            f = t;
            try {
                return e()
            } finally {
                f = n
            }
        }
        ,
        e.unstable_requestPaint = function () {
            g = !0
        }
        ,
        e.unstable_runWithPriority = function (e, t) {
            switch (e) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    break;
                default:
                    e = 3
            }
            var n = f;
            f = e;
            try {
                return t()
            } finally {
                f = n
            }
        }
        ,
        e.unstable_scheduleCallback = function (r, i, a) {
            var o = e.unstable_now();
            switch (typeof a == `object` && a ? (a = a.delay,
                a = typeof a == `number` && 0 < a ? o + a : o) : a = o,
            r) {
                case 1:
                    var s = -1;
                    break;
                case 2:
                    s = 250;
                    break;
                case 5:
                    s = 1073741823;
                    break;
                case 4:
                    s = 1e4;
                    break;
                default:
                    s = 5e3
            }
            return s = a + s,
                r = {
                    id: u++,
                    callback: i,
                    priorityLevel: r,
                    startTime: a,
                    expirationTime: s,
                    sortIndex: -1
                },
                a > o ? (r.sortIndex = a,
                    t(l, r),
                    n(c) === null && r === n(l) && (h ? (v(C),
                        C = -1) : h = !0,
                        ne(x, a - o))) : (r.sortIndex = s,
                            t(c, r),
                            m || p || (m = !0,
                                S || (S = !0,
                                    O()))),
                r
        }
        ,
        e.unstable_shouldYield = E,
        e.unstable_wrapCallback = function (e) {
            var t = f;
            return function () {
                var n = f;
                f = t;
                try {
                    return e.apply(this, arguments)
                } finally {
                    f = n
                }
            }
        }
}
))
    , u = o(((e, t) => {
        t.exports = l()
    }
    ))
    , d = o((e => {
        var t = Symbol.for(`react.transitional.element`)
            , n = Symbol.for(`react.portal`)
            , r = Symbol.for(`react.fragment`)
            , i = Symbol.for(`react.strict_mode`)
            , a = Symbol.for(`react.profiler`)
            , o = Symbol.for(`react.consumer`)
            , s = Symbol.for(`react.context`)
            , c = Symbol.for(`react.forward_ref`)
            , l = Symbol.for(`react.suspense`)
            , u = Symbol.for(`react.memo`)
            , d = Symbol.for(`react.lazy`)
            , f = Symbol.for(`react.activity`)
            , p = Symbol.iterator;
        function m(e) {
            return typeof e != `object` || !e ? null : (e = p && e[p] || e[`@@iterator`],
                typeof e == `function` ? e : null)
        }
        var h = {
            isMounted: function () {
                return !1
            },
            enqueueForceUpdate: function () { },
            enqueueReplaceState: function () { },
            enqueueSetState: function () { }
        }
            , g = Object.assign
            , _ = {};
        function v(e, t, n) {
            this.props = e,
                this.context = t,
                this.refs = _,
                this.updater = n || h
        }
        v.prototype.isReactComponent = {},
            v.prototype.setState = function (e, t) {
                if (typeof e != `object` && typeof e != `function` && e != null)
                    throw Error(`takes an object of state variables to update or a function which returns an object of state variables.`);
                this.updater.enqueueSetState(this, e, t, `setState`)
            }
            ,
            v.prototype.forceUpdate = function (e) {
                this.updater.enqueueForceUpdate(this, e, `forceUpdate`)
            }
            ;
        function y() { }
        y.prototype = v.prototype;
        function b(e, t, n) {
            this.props = e,
                this.context = t,
                this.refs = _,
                this.updater = n || h
        }
        var x = b.prototype = new y;
        x.constructor = b,
            g(x, v.prototype),
            x.isPureReactComponent = !0;
        var S = Array.isArray;
        function C() { }
        var w = {
            H: null,
            A: null,
            T: null,
            S: null
        }
            , T = Object.prototype.hasOwnProperty;
        function E(e, n, r) {
            var i = r.ref;
            return {
                $$typeof: t,
                type: e,
                key: n,
                ref: i === void 0 ? null : i,
                props: r
            }
        }
        function D(e, t) {
            return E(e.type, t, e.props)
        }
        function O(e) {
            return typeof e == `object` && !!e && e.$$typeof === t
        }
        function ee(e) {
            var t = {
                "=": `=0`,
                ":": `=2`
            };
            return `$` + e.replace(/[=:]/g, function (e) {
                return t[e]
            })
        }
        var te = /\/+/g;
        function ne(e, t) {
            return typeof e == `object` && e && e.key != null ? ee(`` + e.key) : t.toString(36)
        }
        function re(e) {
            switch (e.status) {
                case `fulfilled`:
                    return e.value;
                case `rejected`:
                    throw e.reason;
                default:
                    switch (typeof e.status == `string` ? e.then(C, C) : (e.status = `pending`,
                        e.then(function (t) {
                            e.status === `pending` && (e.status = `fulfilled`,
                                e.value = t)
                        }, function (t) {
                            e.status === `pending` && (e.status = `rejected`,
                                e.reason = t)
                        })),
                    e.status) {
                        case `fulfilled`:
                            return e.value;
                        case `rejected`:
                            throw e.reason
                    }
            }
            throw e
        }
        function ie(e, r, i, a, o) {
            var s = typeof e;
            (s === `undefined` || s === `boolean`) && (e = null);
            var c = !1;
            if (e === null)
                c = !0;
            else
                switch (s) {
                    case `bigint`:
                    case `string`:
                    case `number`:
                        c = !0;
                        break;
                    case `object`:
                        switch (e.$$typeof) {
                            case t:
                            case n:
                                c = !0;
                                break;
                            case d:
                                return c = e._init,
                                    ie(c(e._payload), r, i, a, o)
                        }
                }
            if (c)
                return o = o(e),
                    c = a === `` ? `.` + ne(e, 0) : a,
                    S(o) ? (i = ``,
                        c != null && (i = c.replace(te, `$&/`) + `/`),
                        ie(o, r, i, ``, function (e) {
                            return e
                        })) : o != null && (O(o) && (o = D(o, i + (o.key == null || e && e.key === o.key ? `` : (`` + o.key).replace(te, `$&/`) + `/`) + c)),
                            r.push(o)),
                    1;
            c = 0;
            var l = a === `` ? `.` : a + `:`;
            if (S(e))
                for (var u = 0; u < e.length; u++)
                    a = e[u],
                        s = l + ne(a, u),
                        c += ie(a, r, i, s, o);
            else if (u = m(e),
                typeof u == `function`)
                for (e = u.call(e),
                    u = 0; !(a = e.next()).done;)
                    a = a.value,
                        s = l + ne(a, u++),
                        c += ie(a, r, i, s, o);
            else if (s === `object`) {
                if (typeof e.then == `function`)
                    return ie(re(e), r, i, a, o);
                throw r = String(e),
                Error(`Objects are not valid as a React child (found: ` + (r === `[object Object]` ? `object with keys {` + Object.keys(e).join(`, `) + `}` : r) + `). If you meant to render a collection of children, use an array instead.`)
            }
            return c
        }
        function ae(e, t, n) {
            if (e == null)
                return e;
            var r = []
                , i = 0;
            return ie(e, r, ``, ``, function (e) {
                return t.call(n, e, i++)
            }),
                r
        }
        function oe(e) {
            if (e._status === -1) {
                var t = e._result;
                t = t(),
                    t.then(function (t) {
                        (e._status === 0 || e._status === -1) && (e._status = 1,
                            e._result = t)
                    }, function (t) {
                        (e._status === 0 || e._status === -1) && (e._status = 2,
                            e._result = t)
                    }),
                    e._status === -1 && (e._status = 0,
                        e._result = t)
            }
            if (e._status === 1)
                return e._result.default;
            throw e._result
        }
        var k = typeof reportError == `function` ? reportError : function (e) {
            if (typeof window == `object` && typeof window.ErrorEvent == `function`) {
                var t = new window.ErrorEvent(`error`, {
                    bubbles: !0,
                    cancelable: !0,
                    message: typeof e == `object` && e && typeof e.message == `string` ? String(e.message) : String(e),
                    error: e
                });
                if (!window.dispatchEvent(t))
                    return
            } else if (typeof process == `object` && typeof process.emit == `function`) {
                process.emit(`uncaughtException`, e);
                return
            }
            console.error(e)
        }
            , A = {
                map: ae,
                forEach: function (e, t, n) {
                    ae(e, function () {
                        t.apply(this, arguments)
                    }, n)
                },
                count: function (e) {
                    var t = 0;
                    return ae(e, function () {
                        t++
                    }),
                        t
                },
                toArray: function (e) {
                    return ae(e, function (e) {
                        return e
                    }) || []
                },
                only: function (e) {
                    if (!O(e))
                        throw Error(`React.Children.only expected to receive a single React element child.`);
                    return e
                }
            };
        e.Activity = f,
            e.Children = A,
            e.Component = v,
            e.Fragment = r,
            e.Profiler = a,
            e.PureComponent = b,
            e.StrictMode = i,
            e.Suspense = l,
            e.__CLIENT_INTERNALS_DO_NOT_USE_OR_WARN_USERS_THEY_CANNOT_UPGRADE = w,
            e.__COMPILER_RUNTIME = {
                __proto__: null,
                c: function (e) {
                    return w.H.useMemoCache(e)
                }
            },
            e.cache = function (e) {
                return function () {
                    return e.apply(null, arguments)
                }
            }
            ,
            e.cacheSignal = function () {
                return null
            }
            ,
            e.cloneElement = function (e, t, n) {
                if (e == null)
                    throw Error(`The argument must be a React element, but you passed ` + e + `.`);
                var r = g({}, e.props)
                    , i = e.key;
                if (t != null)
                    for (a in t.key !== void 0 && (i = `` + t.key),
                        t)
                        !T.call(t, a) || a === `key` || a === `__self` || a === `__source` || a === `ref` && t.ref === void 0 || (r[a] = t[a]);
                var a = arguments.length - 2;
                if (a === 1)
                    r.children = n;
                else if (1 < a) {
                    for (var o = Array(a), s = 0; s < a; s++)
                        o[s] = arguments[s + 2];
                    r.children = o
                }
                return E(e.type, i, r)
            }
            ,
            e.createContext = function (e) {
                return e = {
                    $$typeof: s,
                    _currentValue: e,
                    _currentValue2: e,
                    _threadCount: 0,
                    Provider: null,
                    Consumer: null
                },
                    e.Provider = e,
                    e.Consumer = {
                        $$typeof: o,
                        _context: e
                    },
                    e
            }
            ,
            e.createElement = function (e, t, n) {
                var r, i = {}, a = null;
                if (t != null)
                    for (r in t.key !== void 0 && (a = `` + t.key),
                        t)
                        T.call(t, r) && r !== `key` && r !== `__self` && r !== `__source` && (i[r] = t[r]);
                var o = arguments.length - 2;
                if (o === 1)
                    i.children = n;
                else if (1 < o) {
                    for (var s = Array(o), c = 0; c < o; c++)
                        s[c] = arguments[c + 2];
                    i.children = s
                }
                if (e && e.defaultProps)
                    for (r in o = e.defaultProps,
                        o)
                        i[r] === void 0 && (i[r] = o[r]);
                return E(e, a, i)
            }
            ,
            e.createRef = function () {
                return {
                    current: null
                }
            }
            ,
            e.forwardRef = function (e) {
                return {
                    $$typeof: c,
                    render: e
                }
            }
            ,
            e.isValidElement = O,
            e.lazy = function (e) {
                return {
                    $$typeof: d,
                    _payload: {
                        _status: -1,
                        _result: e
                    },
                    _init: oe
                }
            }
            ,
            e.memo = function (e, t) {
                return {
                    $$typeof: u,
                    type: e,
                    compare: t === void 0 ? null : t
                }
            }
            ,
            e.startTransition = function (e) {
                var t = w.T
                    , n = {};
                w.T = n;
                try {
                    var r = e()
                        , i = w.S;
                    i !== null && i(n, r),
                        typeof r == `object` && r && typeof r.then == `function` && r.then(C, k)
                } catch (e) {
                    k(e)
                } finally {
                    t !== null && n.types !== null && (t.types = n.types),
                        w.T = t
                }
            }
            ,
            e.unstable_useCacheRefresh = function () {
                return w.H.useCacheRefresh()
            }
            ,
            e.use = function (e) {
                return w.H.use(e)
            }
            ,
            e.useActionState = function (e, t, n) {
                return w.H.useActionState(e, t, n)
            }
            ,
            e.useCallback = function (e, t) {
                return w.H.useCallback(e, t)
            }
            ,
            e.useContext = function (e) {
                return w.H.useContext(e)
            }
            ,
            e.useDebugValue = function () { }
            ,
            e.useDeferredValue = function (e, t) {
                return w.H.useDeferredValue(e, t)
            }
            ,
            e.useEffect = function (e, t) {
                return w.H.useEffect(e, t)
            }
            ,
            e.useEffectEvent = function (e) {
                return w.H.useEffectEvent(e)
            }
            ,
            e.useId = function () {
                return w.H.useId()
            }
            ,
            e.useImperativeHandle = function (e, t, n) {
                return w.H.useImperativeHandle(e, t, n)
            }
            ,
            e.useInsertionEffect = function (e, t) {
                return w.H.useInsertionEffect(e, t)
            }
            ,
            e.useLayoutEffect = function (e, t) {
                return w.H.useLayoutEffect(e, t)
            }
            ,
            e.useMemo = function (e, t) {
                return w.H.useMemo(e, t)
            }
            ,
            e.useOptimistic = function (e, t) {
                return w.H.useOptimistic(e, t)
            }
            ,
            e.useReducer = function (e, t, n) {
                return w.H.useReducer(e, t, n)
            }
            ,
            e.useRef = function (e) {
                return w.H.useRef(e)
            }
            ,
            e.useState = function (e) {
                return w.H.useState(e)
            }
            ,
            e.useSyncExternalStore = function (e, t, n) {
                return w.H.useSyncExternalStore(e, t, n)
            }
            ,
            e.useTransition = function () {
                return w.H.useTransition()
            }
            ,
            e.version = `19.2.0`
    }
    ))
    , f = o(((e, t) => {
        t.exports = d()
    }
    ))
    , p = o((e => {
        var t = f();
        function n(e) {
            var t = `https://react.dev/errors/` + e;
            if (1 < arguments.length) {
                t += `?args[]=` + encodeURIComponent(arguments[1]);
                for (var n = 2; n < arguments.length; n++)
                    t += `&args[]=` + encodeURIComponent(arguments[n])
            }
            return `Minified React error #` + e + `; visit ` + t + ` for the full message or use the non-minified dev environment for full errors and additional helpful warnings.`
        }
        function r() { }
        var i = {
            d: {
                f: r,
                r: function () {
                    throw Error(n(522))
                },
                D: r,
                C: r,
                L: r,
                m: r,
                X: r,
                S: r,
                M: r
            },
            p: 0,
            findDOMNode: null
        }
            , a = Symbol.for(`react.portal`);
        function o(e, t, n) {
            var r = 3 < arguments.length && arguments[3] !== void 0 ? arguments[3] : null;
            return {
                $$typeof: a,
                key: r == null ? null : `` + r,
                children: e,
                containerInfo: t,
                implementation: n
            }
        }
        var s = t.__CLIENT_INTERNALS_DO_NOT_USE_OR_WARN_USERS_THEY_CANNOT_UPGRADE;
        function c(e, t) {
            if (e === `font`)
                return ``;
            if (typeof t == `string`)
                return t === `use-credentials` ? t : ``
        }
        e.__DOM_INTERNALS_DO_NOT_USE_OR_WARN_USERS_THEY_CANNOT_UPGRADE = i,
            e.createPortal = function (e, t) {
                var r = 2 < arguments.length && arguments[2] !== void 0 ? arguments[2] : null;
                if (!t || t.nodeType !== 1 && t.nodeType !== 9 && t.nodeType !== 11)
                    throw Error(n(299));
                return o(e, t, null, r)
            }
            ,
            e.flushSync = function (e) {
                var t = s.T
                    , n = i.p;
                try {
                    if (s.T = null,
                        i.p = 2,
                        e)
                        return e()
                } finally {
                    s.T = t,
                        i.p = n,
                        i.d.f()
                }
            }
            ,
            e.preconnect = function (e, t) {
                typeof e == `string` && (t ? (t = t.crossOrigin,
                    t = typeof t == `string` ? t === `use-credentials` ? t : `` : void 0) : t = null,
                    i.d.C(e, t))
            }
            ,
            e.prefetchDNS = function (e) {
                typeof e == `string` && i.d.D(e)
            }
            ,
            e.preinit = function (e, t) {
                if (typeof e == `string` && t && typeof t.as == `string`) {
                    var n = t.as
                        , r = c(n, t.crossOrigin)
                        , a = typeof t.integrity == `string` ? t.integrity : void 0
                        , o = typeof t.fetchPriority == `string` ? t.fetchPriority : void 0;
                    n === `style` ? i.d.S(e, typeof t.precedence == `string` ? t.precedence : void 0, {
                        crossOrigin: r,
                        integrity: a,
                        fetchPriority: o
                    }) : n === `script` && i.d.X(e, {
                        crossOrigin: r,
                        integrity: a,
                        fetchPriority: o,
                        nonce: typeof t.nonce == `string` ? t.nonce : void 0
                    })
                }
            }
            ,
            e.preinitModule = function (e, t) {
                if (typeof e == `string`)
                    if (typeof t == `object` && t) {
                        if (t.as == null || t.as === `script`) {
                            var n = c(t.as, t.crossOrigin);
                            i.d.M(e, {
                                crossOrigin: n,
                                integrity: typeof t.integrity == `string` ? t.integrity : void 0,
                                nonce: typeof t.nonce == `string` ? t.nonce : void 0
                            })
                        }
                    } else
                        t ?? i.d.M(e)
            }
            ,
            e.preload = function (e, t) {
                if (typeof e == `string` && typeof t == `object` && t && typeof t.as == `string`) {
                    var n = t.as
                        , r = c(n, t.crossOrigin);
                    i.d.L(e, n, {
                        crossOrigin: r,
                        integrity: typeof t.integrity == `string` ? t.integrity : void 0,
                        nonce: typeof t.nonce == `string` ? t.nonce : void 0,
                        type: typeof t.type == `string` ? t.type : void 0,
                        fetchPriority: typeof t.fetchPriority == `string` ? t.fetchPriority : void 0,
                        referrerPolicy: typeof t.referrerPolicy == `string` ? t.referrerPolicy : void 0,
                        imageSrcSet: typeof t.imageSrcSet == `string` ? t.imageSrcSet : void 0,
                        imageSizes: typeof t.imageSizes == `string` ? t.imageSizes : void 0,
                        media: typeof t.media == `string` ? t.media : void 0
                    })
                }
            }
            ,
            e.preloadModule = function (e, t) {
                if (typeof e == `string`)
                    if (t) {
                        var n = c(t.as, t.crossOrigin);
                        i.d.m(e, {
                            as: typeof t.as == `string` && t.as !== `script` ? t.as : void 0,
                            crossOrigin: n,
                            integrity: typeof t.integrity == `string` ? t.integrity : void 0
                        })
                    } else
                        i.d.m(e)
            }
            ,
            e.requestFormReset = function (e) {
                i.d.r(e)
            }
            ,
            e.unstable_batchedUpdates = function (e, t) {
                return e(t)
            }
            ,
            e.useFormState = function (e, t, n) {
                return s.H.useFormState(e, t, n)
            }
            ,
            e.useFormStatus = function () {
                return s.H.useHostTransitionStatus()
            }
            ,
            e.version = `19.2.0`
    }
    ))
    , m = o(((e, t) => {
        function n() {
            if (!(typeof __REACT_DEVTOOLS_GLOBAL_HOOK__ > `u` || typeof __REACT_DEVTOOLS_GLOBAL_HOOK__.checkDCE != `function`))
                try {
                    __REACT_DEVTOOLS_GLOBAL_HOOK__.checkDCE(n)
                } catch (e) {
                    console.error(e)
                }
        }
        n(),
            t.exports = p()
    }
    ))
    , h = o((e => {
        var t = u()
            , n = f()
            , r = m();
        function i(e) {
            var t = `https://react.dev/errors/` + e;
            if (1 < arguments.length) {
                t += `?args[]=` + encodeURIComponent(arguments[1]);
                for (var n = 2; n < arguments.length; n++)
                    t += `&args[]=` + encodeURIComponent(arguments[n])
            }
            return `Minified React error #` + e + `; visit ` + t + ` for the full message or use the non-minified dev environment for full errors and additional helpful warnings.`
        }
        function a(e) {
            return !(!e || e.nodeType !== 1 && e.nodeType !== 9 && e.nodeType !== 11)
        }
        function o(e) {
            var t = e
                , n = e;
            if (e.alternate)
                for (; t.return;)
                    t = t.return;
            else {
                e = t;
                do
                    t = e,
                        t.flags & 4098 && (n = t.return),
                        e = t.return;
                while (e)
            }
            return t.tag === 3 ? n : null
        }
        function s(e) {
            if (e.tag === 13) {
                var t = e.memoizedState;
                if (t === null && (e = e.alternate,
                    e !== null && (t = e.memoizedState)),
                    t !== null)
                    return t.dehydrated
            }
            return null
        }
        function c(e) {
            if (e.tag === 31) {
                var t = e.memoizedState;
                if (t === null && (e = e.alternate,
                    e !== null && (t = e.memoizedState)),
                    t !== null)
                    return t.dehydrated
            }
            return null
        }
        function l(e) {
            if (o(e) !== e)
                throw Error(i(188))
        }
        function d(e) {
            var t = e.alternate;
            if (!t) {
                if (t = o(e),
                    t === null)
                    throw Error(i(188));
                return t === e ? e : null
            }
            for (var n = e, r = t; ;) {
                var a = n.return;
                if (a === null)
                    break;
                var s = a.alternate;
                if (s === null) {
                    if (r = a.return,
                        r !== null) {
                        n = r;
                        continue
                    }
                    break
                }
                if (a.child === s.child) {
                    for (s = a.child; s;) {
                        if (s === n)
                            return l(a),
                                e;
                        if (s === r)
                            return l(a),
                                t;
                        s = s.sibling
                    }
                    throw Error(i(188))
                }
                if (n.return !== r.return)
                    n = a,
                        r = s;
                else {
                    for (var c = !1, u = a.child; u;) {
                        if (u === n) {
                            c = !0,
                                n = a,
                                r = s;
                            break
                        }
                        if (u === r) {
                            c = !0,
                                r = a,
                                n = s;
                            break
                        }
                        u = u.sibling
                    }
                    if (!c) {
                        for (u = s.child; u;) {
                            if (u === n) {
                                c = !0,
                                    n = s,
                                    r = a;
                                break
                            }
                            if (u === r) {
                                c = !0,
                                    r = s,
                                    n = a;
                                break
                            }
                            u = u.sibling
                        }
                        if (!c)
                            throw Error(i(189))
                    }
                }
                if (n.alternate !== r)
                    throw Error(i(190))
            }
            if (n.tag !== 3)
                throw Error(i(188));
            return n.stateNode.current === n ? e : t
        }
        function p(e) {
            var t = e.tag;
            if (t === 5 || t === 26 || t === 27 || t === 6)
                return e;
            for (e = e.child; e !== null;) {
                if (t = p(e),
                    t !== null)
                    return t;
                e = e.sibling
            }
            return null
        }
        var h = Object.assign
            , g = Symbol.for(`react.element`)
            , _ = Symbol.for(`react.transitional.element`)
            , v = Symbol.for(`react.portal`)
            , y = Symbol.for(`react.fragment`)
            , b = Symbol.for(`react.strict_mode`)
            , x = Symbol.for(`react.profiler`)
            , S = Symbol.for(`react.consumer`)
            , C = Symbol.for(`react.context`)
            , w = Symbol.for(`react.forward_ref`)
            , T = Symbol.for(`react.suspense`)
            , E = Symbol.for(`react.suspense_list`)
            , D = Symbol.for(`react.memo`)
            , O = Symbol.for(`react.lazy`)
            , ee = Symbol.for(`react.activity`)
            , te = Symbol.for(`react.memo_cache_sentinel`)
            , ne = Symbol.iterator;
        function re(e) {
            return typeof e != `object` || !e ? null : (e = ne && e[ne] || e[`@@iterator`],
                typeof e == `function` ? e : null)
        }
        var ie = Symbol.for(`react.client.reference`);
        function ae(e) {
            if (e == null)
                return null;
            if (typeof e == `function`)
                return e.$$typeof === ie ? null : e.displayName || e.name || null;
            if (typeof e == `string`)
                return e;
            switch (e) {
                case y:
                    return `Fragment`;
                case x:
                    return `Profiler`;
                case b:
                    return `StrictMode`;
                case T:
                    return `Suspense`;
                case E:
                    return `SuspenseList`;
                case ee:
                    return `Activity`
            }
            if (typeof e == `object`)
                switch (e.$$typeof) {
                    case v:
                        return `Portal`;
                    case C:
                        return e.displayName || `Context`;
                    case S:
                        return (e._context.displayName || `Context`) + `.Consumer`;
                    case w:
                        var t = e.render;
                        return e = e.displayName,
                            e ||= (e = t.displayName || t.name || ``,
                                e === `` ? `ForwardRef` : `ForwardRef(` + e + `)`),
                            e;
                    case D:
                        return t = e.displayName || null,
                            t === null ? ae(e.type) || `Memo` : t;
                    case O:
                        t = e._payload,
                            e = e._init;
                        try {
                            return ae(e(t))
                        } catch { }
                }
            return null
        }
        var oe = Array.isArray
            , k = n.__CLIENT_INTERNALS_DO_NOT_USE_OR_WARN_USERS_THEY_CANNOT_UPGRADE
            , A = r.__DOM_INTERNALS_DO_NOT_USE_OR_WARN_USERS_THEY_CANNOT_UPGRADE
            , se = {
                pending: !1,
                data: null,
                method: null,
                action: null
            }
            , ce = []
            , le = -1;
        function j(e) {
            return {
                current: e
            }
        }
        function M(e) {
            0 > le || (e.current = ce[le],
                ce[le] = null,
                le--)
        }
        function N(e, t) {
            le++,
                ce[le] = e.current,
                e.current = t
        }
        var P = j(null)
            , ue = j(null)
            , F = j(null)
            , de = j(null);
        function fe(e, t) {
            switch (N(F, t),
            N(ue, e),
            N(P, null),
            t.nodeType) {
                case 9:
                case 11:
                    e = (e = t.documentElement) && (e = e.namespaceURI) ? nf(e) : 0;
                    break;
                default:
                    if (e = t.tagName,
                        t = t.namespaceURI)
                        t = nf(t),
                            e = rf(t, e);
                    else
                        switch (e) {
                            case `svg`:
                                e = 1;
                                break;
                            case `math`:
                                e = 2;
                                break;
                            default:
                                e = 0
                        }
            }
            M(P),
                N(P, e)
        }
        function pe() {
            M(P),
                M(ue),
                M(F)
        }
        function me(e) {
            e.memoizedState !== null && N(de, e);
            var t = P.current
                , n = rf(t, e.type);
            t !== n && (N(ue, e),
                N(P, n))
        }
        function he(e) {
            ue.current === e && (M(P),
                M(ue)),
                de.current === e && (M(de),
                    mp._currentValue = se)
        }
        var ge, _e;
        function ve(e) {
            if (ge === void 0)
                try {
                    throw Error()
                } catch (e) {
                    var t = e.stack.trim().match(/\n( *(at )?)/);
                    ge = t && t[1] || ``,
                        _e = -1 < e.stack.indexOf(`
    at`) ? ` (<anonymous>)` : -1 < e.stack.indexOf(`@`) ? `@unknown:0:0` : ``
                }
            return `
` + ge + e + _e
        }
        var ye = !1;
        function be(e, t) {
            if (!e || ye)
                return ``;
            ye = !0;
            var n = Error.prepareStackTrace;
            Error.prepareStackTrace = void 0;
            try {
                var r = {
                    DetermineComponentFrameRoot: function () {
                        try {
                            if (t) {
                                var n = function () {
                                    throw Error()
                                };
                                if (Object.defineProperty(n.prototype, `props`, {
                                    set: function () {
                                        throw Error()
                                    }
                                }),
                                    typeof Reflect == `object` && Reflect.construct) {
                                    try {
                                        Reflect.construct(n, [])
                                    } catch (e) {
                                        var r = e
                                    }
                                    Reflect.construct(e, [], n)
                                } else {
                                    try {
                                        n.call()
                                    } catch (e) {
                                        r = e
                                    }
                                    e.call(n.prototype)
                                }
                            } else {
                                try {
                                    throw Error()
                                } catch (e) {
                                    r = e
                                }
                                (n = e()) && typeof n.catch == `function` && n.catch(function () { })
                            }
                        } catch (e) {
                            if (e && r && typeof e.stack == `string`)
                                return [e.stack, r.stack]
                        }
                        return [null, null]
                    }
                };
                r.DetermineComponentFrameRoot.displayName = `DetermineComponentFrameRoot`;
                var i = Object.getOwnPropertyDescriptor(r.DetermineComponentFrameRoot, `name`);
                i && i.configurable && Object.defineProperty(r.DetermineComponentFrameRoot, `name`, {
                    value: `DetermineComponentFrameRoot`
                });
                var a = r.DetermineComponentFrameRoot()
                    , o = a[0]
                    , s = a[1];
                if (o && s) {
                    var c = o.split(`
`)
                        , l = s.split(`
`);
                    for (i = r = 0; r < c.length && !c[r].includes(`DetermineComponentFrameRoot`);)
                        r++;
                    for (; i < l.length && !l[i].includes(`DetermineComponentFrameRoot`);)
                        i++;
                    if (r === c.length || i === l.length)
                        for (r = c.length - 1,
                            i = l.length - 1; 1 <= r && 0 <= i && c[r] !== l[i];)
                            i--;
                    for (; 1 <= r && 0 <= i; r--,
                        i--)
                        if (c[r] !== l[i]) {
                            if (r !== 1 || i !== 1)
                                do
                                    if (r--,
                                        i--,
                                        0 > i || c[r] !== l[i]) {
                                        var u = `
` + c[r].replace(` at new `, ` at `);
                                        return e.displayName && u.includes(`<anonymous>`) && (u = u.replace(`<anonymous>`, e.displayName)),
                                            u
                                    }
                                while (1 <= r && 0 <= i);
                            break
                        }
                }
            } finally {
                ye = !1,
                    Error.prepareStackTrace = n
            }
            return (n = e ? e.displayName || e.name : ``) ? ve(n) : ``
        }
        function xe(e, t) {
            switch (e.tag) {
                case 26:
                case 27:
                case 5:
                    return ve(e.type);
                case 16:
                    return ve(`Lazy`);
                case 13:
                    return e.child !== t && t !== null ? ve(`Suspense Fallback`) : ve(`Suspense`);
                case 19:
                    return ve(`SuspenseList`);
                case 0:
                case 15:
                    return be(e.type, !1);
                case 11:
                    return be(e.type.render, !1);
                case 1:
                    return be(e.type, !0);
                case 31:
                    return ve(`Activity`);
                default:
                    return ``
            }
        }
        function Se(e) {
            try {
                var t = ``
                    , n = null;
                do
                    t += xe(e, n),
                        n = e,
                        e = e.return;
                while (e);
                return t
            } catch (e) {
                return `
Error generating stack: ` + e.message + `
` + e.stack
            }
        }
        var Ce = Object.prototype.hasOwnProperty
            , we = t.unstable_scheduleCallback
            , Te = t.unstable_cancelCallback
            , Ee = t.unstable_shouldYield
            , De = t.unstable_requestPaint
            , Oe = t.unstable_now
            , ke = t.unstable_getCurrentPriorityLevel
            , Ae = t.unstable_ImmediatePriority
            , je = t.unstable_UserBlockingPriority
            , Me = t.unstable_NormalPriority
            , Ne = t.unstable_LowPriority
            , Pe = t.unstable_IdlePriority
            , Fe = t.log
            , Ie = t.unstable_setDisableYieldValue
            , Le = null
            , Re = null;
        function ze(e) {
            if (typeof Fe == `function` && Ie(e),
                Re && typeof Re.setStrictMode == `function`)
                try {
                    Re.setStrictMode(Le, e)
                } catch { }
        }
        var Be = Math.clz32 ? Math.clz32 : Ue
            , Ve = Math.log
            , He = Math.LN2;
        function Ue(e) {
            return e >>>= 0,
                e === 0 ? 32 : 31 - (Ve(e) / He | 0) | 0
        }
        var We = 256
            , Ge = 262144
            , Ke = 4194304;
        function qe(e) {
            var t = e & 42;
            if (t !== 0)
                return t;
            switch (e & -e) {
                case 1:
                    return 1;
                case 2:
                    return 2;
                case 4:
                    return 4;
                case 8:
                    return 8;
                case 16:
                    return 16;
                case 32:
                    return 32;
                case 64:
                    return 64;
                case 128:
                    return 128;
                case 256:
                case 512:
                case 1024:
                case 2048:
                case 4096:
                case 8192:
                case 16384:
                case 32768:
                case 65536:
                case 131072:
                    return e & 261888;
                case 262144:
                case 524288:
                case 1048576:
                case 2097152:
                    return e & 3932160;
                case 4194304:
                case 8388608:
                case 16777216:
                case 33554432:
                    return e & 62914560;
                case 67108864:
                    return 67108864;
                case 134217728:
                    return 134217728;
                case 268435456:
                    return 268435456;
                case 536870912:
                    return 536870912;
                case 1073741824:
                    return 0;
                default:
                    return e
            }
        }
        function Je(e, t, n) {
            var r = e.pendingLanes;
            if (r === 0)
                return 0;
            var i = 0
                , a = e.suspendedLanes
                , o = e.pingedLanes;
            e = e.warmLanes;
            var s = r & 134217727;
            return s === 0 ? (s = r & ~a,
                s === 0 ? o === 0 ? n || (n = r & ~e,
                    n !== 0 && (i = qe(n))) : i = qe(o) : i = qe(s)) : (r = s & ~a,
                        r === 0 ? (o &= s,
                            o === 0 ? n || (n = s & ~e,
                                n !== 0 && (i = qe(n))) : i = qe(o)) : i = qe(r)),
                i === 0 ? 0 : t !== 0 && t !== i && (t & a) === 0 && (a = i & -i,
                    n = t & -t,
                    a >= n || a === 32 && n & 4194048) ? t : i
        }
        function Ye(e, t) {
            return (e.pendingLanes & ~(e.suspendedLanes & ~e.pingedLanes) & t) === 0
        }
        function Xe(e, t) {
            switch (e) {
                case 1:
                case 2:
                case 4:
                case 8:
                case 64:
                    return t + 250;
                case 16:
                case 32:
                case 128:
                case 256:
                case 512:
                case 1024:
                case 2048:
                case 4096:
                case 8192:
                case 16384:
                case 32768:
                case 65536:
                case 131072:
                case 262144:
                case 524288:
                case 1048576:
                case 2097152:
                    return t + 5e3;
                case 4194304:
                case 8388608:
                case 16777216:
                case 33554432:
                    return -1;
                case 67108864:
                case 134217728:
                case 268435456:
                case 536870912:
                case 1073741824:
                    return -1;
                default:
                    return -1
            }
        }
        function Ze() {
            var e = Ke;
            return Ke <<= 1,
                !(Ke & 62914560) && (Ke = 4194304),
                e
        }
        function Qe(e) {
            for (var t = [], n = 0; 31 > n; n++)
                t.push(e);
            return t
        }
        function $e(e, t) {
            e.pendingLanes |= t,
                t !== 268435456 && (e.suspendedLanes = 0,
                    e.pingedLanes = 0,
                    e.warmLanes = 0)
        }
        function et(e, t, n, r, i, a) {
            var o = e.pendingLanes;
            e.pendingLanes = n,
                e.suspendedLanes = 0,
                e.pingedLanes = 0,
                e.warmLanes = 0,
                e.expiredLanes &= n,
                e.entangledLanes &= n,
                e.errorRecoveryDisabledLanes &= n,
                e.shellSuspendCounter = 0;
            var s = e.entanglements
                , c = e.expirationTimes
                , l = e.hiddenUpdates;
            for (n = o & ~n; 0 < n;) {
                var u = 31 - Be(n)
                    , d = 1 << u;
                s[u] = 0,
                    c[u] = -1;
                var f = l[u];
                if (f !== null)
                    for (l[u] = null,
                        u = 0; u < f.length; u++) {
                        var p = f[u];
                        p !== null && (p.lane &= -536870913)
                    }
                n &= ~d
            }
            r !== 0 && tt(e, r, 0),
                a !== 0 && i === 0 && e.tag !== 0 && (e.suspendedLanes |= a & ~(o & ~t))
        }
        function tt(e, t, n) {
            e.pendingLanes |= t,
                e.suspendedLanes &= ~t;
            var r = 31 - Be(t);
            e.entangledLanes |= t,
                e.entanglements[r] = e.entanglements[r] | 1073741824 | n & 261930
        }
        function nt(e, t) {
            var n = e.entangledLanes |= t;
            for (e = e.entanglements; n;) {
                var r = 31 - Be(n)
                    , i = 1 << r;
                i & t | e[r] & t && (e[r] |= t),
                    n &= ~i
            }
        }
        function rt(e, t) {
            var n = t & -t;
            return n = n & 42 ? 1 : it(n),
                (n & (e.suspendedLanes | t)) === 0 ? n : 0
        }
        function it(e) {
            switch (e) {
                case 2:
                    e = 1;
                    break;
                case 8:
                    e = 4;
                    break;
                case 32:
                    e = 16;
                    break;
                case 256:
                case 512:
                case 1024:
                case 2048:
                case 4096:
                case 8192:
                case 16384:
                case 32768:
                case 65536:
                case 131072:
                case 262144:
                case 524288:
                case 1048576:
                case 2097152:
                case 4194304:
                case 8388608:
                case 16777216:
                case 33554432:
                    e = 128;
                    break;
                case 268435456:
                    e = 134217728;
                    break;
                default:
                    e = 0
            }
            return e
        }
        function at(e) {
            return e &= -e,
                2 < e ? 8 < e ? e & 134217727 ? 32 : 268435456 : 8 : 2
        }
        function ot() {
            var e = A.p;
            return e === 0 ? (e = window.event,
                e === void 0 ? 32 : Ap(e.type)) : e
        }
        function st(e, t) {
            var n = A.p;
            try {
                return A.p = e,
                    t()
            } finally {
                A.p = n
            }
        }
        var ct = Math.random().toString(36).slice(2)
            , lt = `__reactFiber$` + ct
            , ut = `__reactProps$` + ct
            , dt = `__reactContainer$` + ct
            , ft = `__reactEvents$` + ct
            , pt = `__reactListeners$` + ct
            , mt = `__reactHandles$` + ct
            , ht = `__reactResources$` + ct
            , gt = `__reactMarker$` + ct;
        function _t(e) {
            delete e[lt],
                delete e[ut],
                delete e[ft],
                delete e[pt],
                delete e[mt]
        }
        function vt(e) {
            var t = e[lt];
            if (t)
                return t;
            for (var n = e.parentNode; n;) {
                if (t = n[dt] || n[lt]) {
                    if (n = t.alternate,
                        t.child !== null || n !== null && n.child !== null)
                        for (e = Df(e); e !== null;) {
                            if (n = e[lt])
                                return n;
                            e = Df(e)
                        }
                    return t
                }
                e = n,
                    n = e.parentNode
            }
            return null
        }
        function yt(e) {
            if (e = e[lt] || e[dt]) {
                var t = e.tag;
                if (t === 5 || t === 6 || t === 13 || t === 31 || t === 26 || t === 27 || t === 3)
                    return e
            }
            return null
        }
        function bt(e) {
            var t = e.tag;
            if (t === 5 || t === 26 || t === 27 || t === 6)
                return e.stateNode;
            throw Error(i(33))
        }
        function xt(e) {
            var t = e[ht];
            return t ||= e[ht] = {
                hoistableStyles: new Map,
                hoistableScripts: new Map
            },
                t
        }
        function St(e) {
            e[gt] = !0
        }
        var Ct = new Set
            , wt = {};
        function Tt(e, t) {
            Et(e, t),
                Et(e + `Capture`, t)
        }
        function Et(e, t) {
            for (wt[e] = t,
                e = 0; e < t.length; e++)
                Ct.add(t[e])
        }
        var Dt = RegExp(`^[:A-Z_a-z\\u00C0-\\u00D6\\u00D8-\\u00F6\\u00F8-\\u02FF\\u0370-\\u037D\\u037F-\\u1FFF\\u200C-\\u200D\\u2070-\\u218F\\u2C00-\\u2FEF\\u3001-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFFD][:A-Z_a-z\\u00C0-\\u00D6\\u00D8-\\u00F6\\u00F8-\\u02FF\\u0370-\\u037D\\u037F-\\u1FFF\\u200C-\\u200D\\u2070-\\u218F\\u2C00-\\u2FEF\\u3001-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFFD\\-.0-9\\u00B7\\u0300-\\u036F\\u203F-\\u2040]*$`)
            , Ot = {}
            , kt = {};
        function At(e) {
            return Ce.call(kt, e) ? !0 : Ce.call(Ot, e) ? !1 : Dt.test(e) ? kt[e] = !0 : (Ot[e] = !0,
                !1)
        }
        function jt(e, t, n) {
            if (At(t))
                if (n === null)
                    e.removeAttribute(t);
                else {
                    switch (typeof n) {
                        case `undefined`:
                        case `function`:
                        case `symbol`:
                            e.removeAttribute(t);
                            return;
                        case `boolean`:
                            var r = t.toLowerCase().slice(0, 5);
                            if (r !== `data-` && r !== `aria-`) {
                                e.removeAttribute(t);
                                return
                            }
                    }
                    e.setAttribute(t, `` + n)
                }
        }
        function Mt(e, t, n) {
            if (n === null)
                e.removeAttribute(t);
            else {
                switch (typeof n) {
                    case `undefined`:
                    case `function`:
                    case `symbol`:
                    case `boolean`:
                        e.removeAttribute(t);
                        return
                }
                e.setAttribute(t, `` + n)
            }
        }
        function Nt(e, t, n, r) {
            if (r === null)
                e.removeAttribute(n);
            else {
                switch (typeof r) {
                    case `undefined`:
                    case `function`:
                    case `symbol`:
                    case `boolean`:
                        e.removeAttribute(n);
                        return
                }
                e.setAttributeNS(t, n, `` + r)
            }
        }
        function Pt(e) {
            switch (typeof e) {
                case `bigint`:
                case `boolean`:
                case `number`:
                case `string`:
                case `undefined`:
                    return e;
                case `object`:
                    return e;
                default:
                    return ``
            }
        }
        function Ft(e) {
            var t = e.type;
            return (e = e.nodeName) && e.toLowerCase() === `input` && (t === `checkbox` || t === `radio`)
        }
        function It(e, t, n) {
            var r = Object.getOwnPropertyDescriptor(e.constructor.prototype, t);
            if (!e.hasOwnProperty(t) && r !== void 0 && typeof r.get == `function` && typeof r.set == `function`) {
                var i = r.get
                    , a = r.set;
                return Object.defineProperty(e, t, {
                    configurable: !0,
                    get: function () {
                        return i.call(this)
                    },
                    set: function (e) {
                        n = `` + e,
                            a.call(this, e)
                    }
                }),
                    Object.defineProperty(e, t, {
                        enumerable: r.enumerable
                    }),
                {
                    getValue: function () {
                        return n
                    },
                    setValue: function (e) {
                        n = `` + e
                    },
                    stopTracking: function () {
                        e._valueTracker = null,
                            delete e[t]
                    }
                }
            }
        }
        function Lt(e) {
            if (!e._valueTracker) {
                var t = Ft(e) ? `checked` : `value`;
                e._valueTracker = It(e, t, `` + e[t])
            }
        }
        function Rt(e) {
            if (!e)
                return !1;
            var t = e._valueTracker;
            if (!t)
                return !0;
            var n = t.getValue()
                , r = ``;
            return e && (r = Ft(e) ? e.checked ? `true` : `false` : e.value),
                e = r,
                e === n ? !1 : (t.setValue(e),
                    !0)
        }
        function zt(e) {
            if (e ||= typeof document < `u` ? document : void 0,
                e === void 0)
                return null;
            try {
                return e.activeElement || e.body
            } catch {
                return e.body
            }
        }
        var Bt = /[\n"\\]/g;
        function Vt(e) {
            return e.replace(Bt, function (e) {
                return `\\` + e.charCodeAt(0).toString(16) + ` `
            })
        }
        function Ht(e, t, n, r, i, a, o, s) {
            e.name = ``,
                o != null && typeof o != `function` && typeof o != `symbol` && typeof o != `boolean` ? e.type = o : e.removeAttribute(`type`),
                t == null ? o !== `submit` && o !== `reset` || e.removeAttribute(`value`) : o === `number` ? (t === 0 && e.value === `` || e.value != t) && (e.value = `` + Pt(t)) : e.value !== `` + Pt(t) && (e.value = `` + Pt(t)),
                t == null ? n == null ? r != null && e.removeAttribute(`value`) : Wt(e, o, Pt(n)) : Wt(e, o, Pt(t)),
                i == null && a != null && (e.defaultChecked = !!a),
                i != null && (e.checked = i && typeof i != `function` && typeof i != `symbol`),
                s != null && typeof s != `function` && typeof s != `symbol` && typeof s != `boolean` ? e.name = `` + Pt(s) : e.removeAttribute(`name`)
        }
        function Ut(e, t, n, r, i, a, o, s) {
            if (a != null && typeof a != `function` && typeof a != `symbol` && typeof a != `boolean` && (e.type = a),
                t != null || n != null) {
                if (!(a !== `submit` && a !== `reset` || t != null)) {
                    Lt(e);
                    return
                }
                n = n == null ? `` : `` + Pt(n),
                    t = t == null ? n : `` + Pt(t),
                    s || t === e.value || (e.value = t),
                    e.defaultValue = t
            }
            r ??= i,
                r = typeof r != `function` && typeof r != `symbol` && !!r,
                e.checked = s ? e.checked : !!r,
                e.defaultChecked = !!r,
                o != null && typeof o != `function` && typeof o != `symbol` && typeof o != `boolean` && (e.name = o),
                Lt(e)
        }
        function Wt(e, t, n) {
            t === `number` && zt(e.ownerDocument) === e || e.defaultValue === `` + n || (e.defaultValue = `` + n)
        }
        function Gt(e, t, n, r) {
            if (e = e.options,
                t) {
                t = {};
                for (var i = 0; i < n.length; i++)
                    t[`$` + n[i]] = !0;
                for (n = 0; n < e.length; n++)
                    i = t.hasOwnProperty(`$` + e[n].value),
                        e[n].selected !== i && (e[n].selected = i),
                        i && r && (e[n].defaultSelected = !0)
            } else {
                for (n = `` + Pt(n),
                    t = null,
                    i = 0; i < e.length; i++) {
                    if (e[i].value === n) {
                        e[i].selected = !0,
                            r && (e[i].defaultSelected = !0);
                        return
                    }
                    t !== null || e[i].disabled || (t = e[i])
                }
                t !== null && (t.selected = !0)
            }
        }
        function Kt(e, t, n) {
            if (t != null && (t = `` + Pt(t),
                t !== e.value && (e.value = t),
                n == null)) {
                e.defaultValue !== t && (e.defaultValue = t);
                return
            }
            e.defaultValue = n == null ? `` : `` + Pt(n)
        }
        function qt(e, t, n, r) {
            if (t == null) {
                if (r != null) {
                    if (n != null)
                        throw Error(i(92));
                    if (oe(r)) {
                        if (1 < r.length)
                            throw Error(i(93));
                        r = r[0]
                    }
                    n = r
                }
                n ??= ``,
                    t = n
            }
            n = Pt(t),
                e.defaultValue = n,
                r = e.textContent,
                r === n && r !== `` && r !== null && (e.value = r),
                Lt(e)
        }
        function Jt(e, t) {
            if (t) {
                var n = e.firstChild;
                if (n && n === e.lastChild && n.nodeType === 3) {
                    n.nodeValue = t;
                    return
                }
            }
            e.textContent = t
        }
        var Yt = new Set(`animationIterationCount aspectRatio borderImageOutset borderImageSlice borderImageWidth boxFlex boxFlexGroup boxOrdinalGroup columnCount columns flex flexGrow flexPositive flexShrink flexNegative flexOrder gridArea gridRow gridRowEnd gridRowSpan gridRowStart gridColumn gridColumnEnd gridColumnSpan gridColumnStart fontWeight lineClamp lineHeight opacity order orphans scale tabSize widows zIndex zoom fillOpacity floodOpacity stopOpacity strokeDasharray strokeDashoffset strokeMiterlimit strokeOpacity strokeWidth MozAnimationIterationCount MozBoxFlex MozBoxFlexGroup MozLineClamp msAnimationIterationCount msFlex msZoom msFlexGrow msFlexNegative msFlexOrder msFlexPositive msFlexShrink msGridColumn msGridColumnSpan msGridRow msGridRowSpan WebkitAnimationIterationCount WebkitBoxFlex WebKitBoxFlexGroup WebkitBoxOrdinalGroup WebkitColumnCount WebkitColumns WebkitFlex WebkitFlexGrow WebkitFlexPositive WebkitFlexShrink WebkitLineClamp`.split(` `));
        function Xt(e, t, n) {
            var r = t.indexOf(`--`) === 0;
            n == null || typeof n == `boolean` || n === `` ? r ? e.setProperty(t, ``) : t === `float` ? e.cssFloat = `` : e[t] = `` : r ? e.setProperty(t, n) : typeof n != `number` || n === 0 || Yt.has(t) ? t === `float` ? e.cssFloat = n : e[t] = (`` + n).trim() : e[t] = n + `px`
        }
        function Zt(e, t, n) {
            if (t != null && typeof t != `object`)
                throw Error(i(62));
            if (e = e.style,
                n != null) {
                for (var r in n)
                    !n.hasOwnProperty(r) || t != null && t.hasOwnProperty(r) || (r.indexOf(`--`) === 0 ? e.setProperty(r, ``) : r === `float` ? e.cssFloat = `` : e[r] = ``);
                for (var a in t)
                    r = t[a],
                        t.hasOwnProperty(a) && n[a] !== r && Xt(e, a, r)
            } else
                for (var o in t)
                    t.hasOwnProperty(o) && Xt(e, o, t[o])
        }
        function Qt(e) {
            if (e.indexOf(`-`) === -1)
                return !1;
            switch (e) {
                case `annotation-xml`:
                case `color-profile`:
                case `font-face`:
                case `font-face-src`:
                case `font-face-uri`:
                case `font-face-format`:
                case `font-face-name`:
                case `missing-glyph`:
                    return !1;
                default:
                    return !0
            }
        }
        var $t = new Map([[`acceptCharset`, `accept-charset`], [`htmlFor`, `for`], [`httpEquiv`, `http-equiv`], [`crossOrigin`, `crossorigin`], [`accentHeight`, `accent-height`], [`alignmentBaseline`, `alignment-baseline`], [`arabicForm`, `arabic-form`], [`baselineShift`, `baseline-shift`], [`capHeight`, `cap-height`], [`clipPath`, `clip-path`], [`clipRule`, `clip-rule`], [`colorInterpolation`, `color-interpolation`], [`colorInterpolationFilters`, `color-interpolation-filters`], [`colorProfile`, `color-profile`], [`colorRendering`, `color-rendering`], [`dominantBaseline`, `dominant-baseline`], [`enableBackground`, `enable-background`], [`fillOpacity`, `fill-opacity`], [`fillRule`, `fill-rule`], [`floodColor`, `flood-color`], [`floodOpacity`, `flood-opacity`], [`fontFamily`, `font-family`], [`fontSize`, `font-size`], [`fontSizeAdjust`, `font-size-adjust`], [`fontStretch`, `font-stretch`], [`fontStyle`, `font-style`], [`fontVariant`, `font-variant`], [`fontWeight`, `font-weight`], [`glyphName`, `glyph-name`], [`glyphOrientationHorizontal`, `glyph-orientation-horizontal`], [`glyphOrientationVertical`, `glyph-orientation-vertical`], [`horizAdvX`, `horiz-adv-x`], [`horizOriginX`, `horiz-origin-x`], [`imageRendering`, `image-rendering`], [`letterSpacing`, `letter-spacing`], [`lightingColor`, `lighting-color`], [`markerEnd`, `marker-end`], [`markerMid`, `marker-mid`], [`markerStart`, `marker-start`], [`overlinePosition`, `overline-position`], [`overlineThickness`, `overline-thickness`], [`paintOrder`, `paint-order`], [`panose-1`, `panose-1`], [`pointerEvents`, `pointer-events`], [`renderingIntent`, `rendering-intent`], [`shapeRendering`, `shape-rendering`], [`stopColor`, `stop-color`], [`stopOpacity`, `stop-opacity`], [`strikethroughPosition`, `strikethrough-position`], [`strikethroughThickness`, `strikethrough-thickness`], [`strokeDasharray`, `stroke-dasharray`], [`strokeDashoffset`, `stroke-dashoffset`], [`strokeLinecap`, `stroke-linecap`], [`strokeLinejoin`, `stroke-linejoin`], [`strokeMiterlimit`, `stroke-miterlimit`], [`strokeOpacity`, `stroke-opacity`], [`strokeWidth`, `stroke-width`], [`textAnchor`, `text-anchor`], [`textDecoration`, `text-decoration`], [`textRendering`, `text-rendering`], [`transformOrigin`, `transform-origin`], [`underlinePosition`, `underline-position`], [`underlineThickness`, `underline-thickness`], [`unicodeBidi`, `unicode-bidi`], [`unicodeRange`, `unicode-range`], [`unitsPerEm`, `units-per-em`], [`vAlphabetic`, `v-alphabetic`], [`vHanging`, `v-hanging`], [`vIdeographic`, `v-ideographic`], [`vMathematical`, `v-mathematical`], [`vectorEffect`, `vector-effect`], [`vertAdvY`, `vert-adv-y`], [`vertOriginX`, `vert-origin-x`], [`vertOriginY`, `vert-origin-y`], [`wordSpacing`, `word-spacing`], [`writingMode`, `writing-mode`], [`xmlnsXlink`, `xmlns:xlink`], [`xHeight`, `x-height`]])
            , en = /^[\u0000-\u001F ]*j[\r\n\t]*a[\r\n\t]*v[\r\n\t]*a[\r\n\t]*s[\r\n\t]*c[\r\n\t]*r[\r\n\t]*i[\r\n\t]*p[\r\n\t]*t[\r\n\t]*:/i;
        function tn(e) {
            return en.test(`` + e) ? `javascript:throw new Error('React has blocked a javascript: URL as a security precaution.')` : e
        }
        function nn() { }
        var rn = null;
        function an(e) {
            return e = e.target || e.srcElement || window,
                e.correspondingUseElement && (e = e.correspondingUseElement),
                e.nodeType === 3 ? e.parentNode : e
        }
        var on = null
            , sn = null;
        function I(e) {
            var t = yt(e);
            if (t && (e = t.stateNode)) {
                var n = e[ut] || null;
                a: switch (e = t.stateNode,
                t.type) {
                    case `input`:
                        if (Ht(e, n.value, n.defaultValue, n.defaultValue, n.checked, n.defaultChecked, n.type, n.name),
                            t = n.name,
                            n.type === `radio` && t != null) {
                            for (n = e; n.parentNode;)
                                n = n.parentNode;
                            for (n = n.querySelectorAll(`input[name="` + Vt(`` + t) + `"][type="radio"]`),
                                t = 0; t < n.length; t++) {
                                var r = n[t];
                                if (r !== e && r.form === e.form) {
                                    var a = r[ut] || null;
                                    if (!a)
                                        throw Error(i(90));
                                    Ht(r, a.value, a.defaultValue, a.defaultValue, a.checked, a.defaultChecked, a.type, a.name)
                                }
                            }
                            for (t = 0; t < n.length; t++)
                                r = n[t],
                                    r.form === e.form && Rt(r)
                        }
                        break a;
                    case `textarea`:
                        Kt(e, n.value, n.defaultValue);
                        break a;
                    case `select`:
                        t = n.value,
                            t != null && Gt(e, !!n.multiple, t, !1)
                }
            }
        }
        var L = !1;
        function cn(e, t, n) {
            if (L)
                return e(t, n);
            L = !0;
            try {
                return e(t)
            } finally {
                if (L = !1,
                    (on !== null || sn !== null) && (Nu(),
                        on && (t = on,
                            e = sn,
                            sn = on = null,
                            I(t),
                            e)))
                    for (t = 0; t < e.length; t++)
                        I(e[t])
            }
        }
        function ln(e, t) {
            var n = e.stateNode;
            if (n === null)
                return null;
            var r = n[ut] || null;
            if (r === null)
                return null;
            n = r[t];
            a: switch (t) {
                case `onClick`:
                case `onClickCapture`:
                case `onDoubleClick`:
                case `onDoubleClickCapture`:
                case `onMouseDown`:
                case `onMouseDownCapture`:
                case `onMouseMove`:
                case `onMouseMoveCapture`:
                case `onMouseUp`:
                case `onMouseUpCapture`:
                case `onMouseEnter`:
                    (r = !r.disabled) || (e = e.type,
                        r = !(e === `button` || e === `input` || e === `select` || e === `textarea`)),
                        e = !r;
                    break a;
                default:
                    e = !1
            }
            if (e)
                return null;
            if (n && typeof n != `function`)
                throw Error(i(231, t, typeof n));
            return n
        }
        var un = !(typeof window > `u` || window.document === void 0 || window.document.createElement === void 0)
            , dn = !1;
        if (un)
            try {
                var fn = {};
                Object.defineProperty(fn, `passive`, {
                    get: function () {
                        dn = !0
                    }
                }),
                    window.addEventListener(`test`, fn, fn),
                    window.removeEventListener(`test`, fn, fn)
            } catch {
                dn = !1
            }
        var pn = null
            , mn = null
            , hn = null;
        function gn() {
            if (hn)
                return hn;
            var e, t = mn, n = t.length, r, i = `value` in pn ? pn.value : pn.textContent, a = i.length;
            for (e = 0; e < n && t[e] === i[e]; e++)
                ;
            var o = n - e;
            for (r = 1; r <= o && t[n - r] === i[a - r]; r++)
                ;
            return hn = i.slice(e, 1 < r ? 1 - r : void 0)
        }
        function _n(e) {
            var t = e.keyCode;
            return `charCode` in e ? (e = e.charCode,
                e === 0 && t === 13 && (e = 13)) : e = t,
                e === 10 && (e = 13),
                32 <= e || e === 13 ? e : 0
        }
        function vn() {
            return !0
        }
        function yn() {
            return !1
        }
        function bn(e) {
            function t(t, n, r, i, a) {
                for (var o in this._reactName = t,
                    this._targetInst = r,
                    this.type = n,
                    this.nativeEvent = i,
                    this.target = a,
                    this.currentTarget = null,
                    e)
                    e.hasOwnProperty(o) && (t = e[o],
                        this[o] = t ? t(i) : i[o]);
                return this.isDefaultPrevented = (i.defaultPrevented == null ? !1 === i.returnValue : i.defaultPrevented) ? vn : yn,
                    this.isPropagationStopped = yn,
                    this
            }
            return h(t.prototype, {
                preventDefault: function () {
                    this.defaultPrevented = !0;
                    var e = this.nativeEvent;
                    e && (e.preventDefault ? e.preventDefault() : typeof e.returnValue != `unknown` && (e.returnValue = !1),
                        this.isDefaultPrevented = vn)
                },
                stopPropagation: function () {
                    var e = this.nativeEvent;
                    e && (e.stopPropagation ? e.stopPropagation() : typeof e.cancelBubble != `unknown` && (e.cancelBubble = !0),
                        this.isPropagationStopped = vn)
                },
                persist: function () { },
                isPersistent: vn
            }),
                t
        }
        var xn = {
            eventPhase: 0,
            bubbles: 0,
            cancelable: 0,
            timeStamp: function (e) {
                return e.timeStamp || Date.now()
            },
            defaultPrevented: 0,
            isTrusted: 0
        }, Sn = bn(xn), Cn = h({}, xn, {
            view: 0,
            detail: 0
        }), wn = bn(Cn), Tn, En, Dn, On = h({}, Cn, {
            screenX: 0,
            screenY: 0,
            clientX: 0,
            clientY: 0,
            pageX: 0,
            pageY: 0,
            ctrlKey: 0,
            shiftKey: 0,
            altKey: 0,
            metaKey: 0,
            getModifierState: Wn,
            button: 0,
            buttons: 0,
            relatedTarget: function (e) {
                return e.relatedTarget === void 0 ? e.fromElement === e.srcElement ? e.toElement : e.fromElement : e.relatedTarget
            },
            movementX: function (e) {
                return `movementX` in e ? e.movementX : (e !== Dn && (Dn && e.type === `mousemove` ? (Tn = e.screenX - Dn.screenX,
                    En = e.screenY - Dn.screenY) : En = Tn = 0,
                    Dn = e),
                    Tn)
            },
            movementY: function (e) {
                return `movementY` in e ? e.movementY : En
            }
        }), kn = bn(On), An = h({}, On, {
            dataTransfer: 0
        }), jn = bn(An), Mn = h({}, Cn, {
            relatedTarget: 0
        }), Nn = bn(Mn), Pn = h({}, xn, {
            animationName: 0,
            elapsedTime: 0,
            pseudoElement: 0
        }), Fn = bn(Pn), In = h({}, xn, {
            clipboardData: function (e) {
                return `clipboardData` in e ? e.clipboardData : window.clipboardData
            }
        }), Ln = bn(In), Rn = h({}, xn, {
            data: 0
        }), zn = bn(Rn), Bn = {
            Esc: `Escape`,
            Spacebar: ` `,
            Left: `ArrowLeft`,
            Up: `ArrowUp`,
            Right: `ArrowRight`,
            Down: `ArrowDown`,
            Del: `Delete`,
            Win: `OS`,
            Menu: `ContextMenu`,
            Apps: `ContextMenu`,
            Scroll: `ScrollLock`,
            MozPrintableKey: `Unidentified`
        }, Vn = {
            8: `Backspace`,
            9: `Tab`,
            12: `Clear`,
            13: `Enter`,
            16: `Shift`,
            17: `Control`,
            18: `Alt`,
            19: `Pause`,
            20: `CapsLock`,
            27: `Escape`,
            32: ` `,
            33: `PageUp`,
            34: `PageDown`,
            35: `End`,
            36: `Home`,
            37: `ArrowLeft`,
            38: `ArrowUp`,
            39: `ArrowRight`,
            40: `ArrowDown`,
            45: `Insert`,
            46: `Delete`,
            112: `F1`,
            113: `F2`,
            114: `F3`,
            115: `F4`,
            116: `F5`,
            117: `F6`,
            118: `F7`,
            119: `F8`,
            120: `F9`,
            121: `F10`,
            122: `F11`,
            123: `F12`,
            144: `NumLock`,
            145: `ScrollLock`,
            224: `Meta`
        }, Hn = {
            Alt: `altKey`,
            Control: `ctrlKey`,
            Meta: `metaKey`,
            Shift: `shiftKey`
        };
        function Un(e) {
            var t = this.nativeEvent;
            return t.getModifierState ? t.getModifierState(e) : (e = Hn[e]) ? !!t[e] : !1
        }
        function Wn() {
            return Un
        }
        var Gn = h({}, Cn, {
            key: function (e) {
                if (e.key) {
                    var t = Bn[e.key] || e.key;
                    if (t !== `Unidentified`)
                        return t
                }
                return e.type === `keypress` ? (e = _n(e),
                    e === 13 ? `Enter` : String.fromCharCode(e)) : e.type === `keydown` || e.type === `keyup` ? Vn[e.keyCode] || `Unidentified` : ``
            },
            code: 0,
            location: 0,
            ctrlKey: 0,
            shiftKey: 0,
            altKey: 0,
            metaKey: 0,
            repeat: 0,
            locale: 0,
            getModifierState: Wn,
            charCode: function (e) {
                return e.type === `keypress` ? _n(e) : 0
            },
            keyCode: function (e) {
                return e.type === `keydown` || e.type === `keyup` ? e.keyCode : 0
            },
            which: function (e) {
                return e.type === `keypress` ? _n(e) : e.type === `keydown` || e.type === `keyup` ? e.keyCode : 0
            }
        })
            , Kn = bn(Gn)
            , qn = h({}, On, {
                pointerId: 0,
                width: 0,
                height: 0,
                pressure: 0,
                tangentialPressure: 0,
                tiltX: 0,
                tiltY: 0,
                twist: 0,
                pointerType: 0,
                isPrimary: 0
            })
            , Jn = bn(qn)
            , Yn = h({}, Cn, {
                touches: 0,
                targetTouches: 0,
                changedTouches: 0,
                altKey: 0,
                metaKey: 0,
                ctrlKey: 0,
                shiftKey: 0,
                getModifierState: Wn
            })
            , Xn = bn(Yn)
            , Zn = h({}, xn, {
                propertyName: 0,
                elapsedTime: 0,
                pseudoElement: 0
            })
            , Qn = bn(Zn)
            , $n = h({}, On, {
                deltaX: function (e) {
                    return `deltaX` in e ? e.deltaX : `wheelDeltaX` in e ? -e.wheelDeltaX : 0
                },
                deltaY: function (e) {
                    return `deltaY` in e ? e.deltaY : `wheelDeltaY` in e ? -e.wheelDeltaY : `wheelDelta` in e ? -e.wheelDelta : 0
                },
                deltaZ: 0,
                deltaMode: 0
            })
            , er = bn($n)
            , tr = h({}, xn, {
                newState: 0,
                oldState: 0
            })
            , nr = bn(tr)
            , rr = [9, 13, 27, 32]
            , ir = un && `CompositionEvent` in window
            , ar = null;
        un && `documentMode` in document && (ar = document.documentMode);
        var or = un && `TextEvent` in window && !ar
            , sr = un && (!ir || ar && 8 < ar && 11 >= ar)
            , cr = ` `
            , lr = !1;
        function ur(e, t) {
            switch (e) {
                case `keyup`:
                    return rr.indexOf(t.keyCode) !== -1;
                case `keydown`:
                    return t.keyCode !== 229;
                case `keypress`:
                case `mousedown`:
                case `focusout`:
                    return !0;
                default:
                    return !1
            }
        }
        function dr(e) {
            return e = e.detail,
                typeof e == `object` && `data` in e ? e.data : null
        }
        var fr = !1;
        function pr(e, t) {
            switch (e) {
                case `compositionend`:
                    return dr(t);
                case `keypress`:
                    return t.which === 32 ? (lr = !0,
                        cr) : null;
                case `textInput`:
                    return e = t.data,
                        e === cr && lr ? null : e;
                default:
                    return null
            }
        }
        function mr(e, t) {
            if (fr)
                return e === `compositionend` || !ir && ur(e, t) ? (e = gn(),
                    hn = mn = pn = null,
                    fr = !1,
                    e) : null;
            switch (e) {
                case `paste`:
                    return null;
                case `keypress`:
                    if (!(t.ctrlKey || t.altKey || t.metaKey) || t.ctrlKey && t.altKey) {
                        if (t.char && 1 < t.char.length)
                            return t.char;
                        if (t.which)
                            return String.fromCharCode(t.which)
                    }
                    return null;
                case `compositionend`:
                    return sr && t.locale !== `ko` ? null : t.data;
                default:
                    return null
            }
        }
        var hr = {
            color: !0,
            date: !0,
            datetime: !0,
            "datetime-local": !0,
            email: !0,
            month: !0,
            number: !0,
            password: !0,
            range: !0,
            search: !0,
            tel: !0,
            text: !0,
            time: !0,
            url: !0,
            week: !0
        };
        function gr(e) {
            var t = e && e.nodeName && e.nodeName.toLowerCase();
            return t === `input` ? !!hr[e.type] : t === `textarea`
        }
        function _r(e, t, n, r) {
            on ? sn ? sn.push(r) : sn = [r] : on = r,
                t = Vd(t, `onChange`),
                0 < t.length && (n = new Sn(`onChange`, `change`, null, n, r),
                    e.push({
                        event: n,
                        listeners: t
                    }))
        }
        var vr = null
            , yr = null;
        function br(e) {
            Pd(e, 0)
        }
        function xr(e) {
            var t = bt(e);
            if (Rt(t))
                return e
        }
        function Sr(e, t) {
            if (e === `change`)
                return t
        }
        var Cr = !1;
        if (un) {
            var wr;
            if (un) {
                var Tr = `oninput` in document;
                if (!Tr) {
                    var Er = document.createElement(`div`);
                    Er.setAttribute(`oninput`, `return;`),
                        Tr = typeof Er.oninput == `function`
                }
                wr = Tr
            } else
                wr = !1;
            Cr = wr && (!document.documentMode || 9 < document.documentMode)
        }
        function Dr() {
            vr && (vr.detachEvent(`onpropertychange`, Or),
                yr = vr = null)
        }
        function Or(e) {
            if (e.propertyName === `value` && xr(yr)) {
                var t = [];
                _r(t, yr, e, an(e)),
                    cn(br, t)
            }
        }
        function kr(e, t, n) {
            e === `focusin` ? (Dr(),
                vr = t,
                yr = n,
                vr.attachEvent(`onpropertychange`, Or)) : e === `focusout` && Dr()
        }
        function Ar(e) {
            if (e === `selectionchange` || e === `keyup` || e === `keydown`)
                return xr(yr)
        }
        function jr(e, t) {
            if (e === `click`)
                return xr(t)
        }
        function Mr(e, t) {
            if (e === `input` || e === `change`)
                return xr(t)
        }
        function Nr(e, t) {
            return e === t && (e !== 0 || 1 / e == 1 / t) || e !== e && t !== t
        }
        var Pr = typeof Object.is == `function` ? Object.is : Nr;
        function Fr(e, t) {
            if (Pr(e, t))
                return !0;
            if (typeof e != `object` || !e || typeof t != `object` || !t)
                return !1;
            var n = Object.keys(e)
                , r = Object.keys(t);
            if (n.length !== r.length)
                return !1;
            for (r = 0; r < n.length; r++) {
                var i = n[r];
                if (!Ce.call(t, i) || !Pr(e[i], t[i]))
                    return !1
            }
            return !0
        }
        function Ir(e) {
            for (; e && e.firstChild;)
                e = e.firstChild;
            return e
        }
        function R(e, t) {
            var n = Ir(e);
            e = 0;
            for (var r; n;) {
                if (n.nodeType === 3) {
                    if (r = e + n.textContent.length,
                        e <= t && r >= t)
                        return {
                            node: n,
                            offset: t - e
                        };
                    e = r
                }
                a: {
                    for (; n;) {
                        if (n.nextSibling) {
                            n = n.nextSibling;
                            break a
                        }
                        n = n.parentNode
                    }
                    n = void 0
                }
                n = Ir(n)
            }
        }
        function Lr(e, t) {
            return e && t ? e === t ? !0 : e && e.nodeType === 3 ? !1 : t && t.nodeType === 3 ? Lr(e, t.parentNode) : `contains` in e ? e.contains(t) : e.compareDocumentPosition ? !!(e.compareDocumentPosition(t) & 16) : !1 : !1
        }
        function Rr(e) {
            e = e != null && e.ownerDocument != null && e.ownerDocument.defaultView != null ? e.ownerDocument.defaultView : window;
            for (var t = zt(e.document); t instanceof e.HTMLIFrameElement;) {
                try {
                    var n = typeof t.contentWindow.location.href == `string`
                } catch {
                    n = !1
                }
                if (n)
                    e = t.contentWindow;
                else
                    break;
                t = zt(e.document)
            }
            return t
        }
        function zr(e) {
            var t = e && e.nodeName && e.nodeName.toLowerCase();
            return t && (t === `input` && (e.type === `text` || e.type === `search` || e.type === `tel` || e.type === `url` || e.type === `password`) || t === `textarea` || e.contentEditable === `true`)
        }
        var Br = un && `documentMode` in document && 11 >= document.documentMode
            , Vr = null
            , Hr = null
            , Ur = null
            , Wr = !1;
        function Gr(e, t, n) {
            var r = n.window === n ? n.document : n.nodeType === 9 ? n : n.ownerDocument;
            Wr || Vr == null || Vr !== zt(r) || (r = Vr,
                `selectionStart` in r && zr(r) ? r = {
                    start: r.selectionStart,
                    end: r.selectionEnd
                } : (r = (r.ownerDocument && r.ownerDocument.defaultView || window).getSelection(),
                    r = {
                        anchorNode: r.anchorNode,
                        anchorOffset: r.anchorOffset,
                        focusNode: r.focusNode,
                        focusOffset: r.focusOffset
                    }),
                Ur && Fr(Ur, r) || (Ur = r,
                    r = Vd(Hr, `onSelect`),
                    0 < r.length && (t = new Sn(`onSelect`, `select`, null, t, n),
                        e.push({
                            event: t,
                            listeners: r
                        }),
                        t.target = Vr)))
        }
        function Kr(e, t) {
            var n = {};
            return n[e.toLowerCase()] = t.toLowerCase(),
                n[`Webkit` + e] = `webkit` + t,
                n[`Moz` + e] = `moz` + t,
                n
        }
        var qr = {
            animationend: Kr(`Animation`, `AnimationEnd`),
            animationiteration: Kr(`Animation`, `AnimationIteration`),
            animationstart: Kr(`Animation`, `AnimationStart`),
            transitionrun: Kr(`Transition`, `TransitionRun`),
            transitionstart: Kr(`Transition`, `TransitionStart`),
            transitioncancel: Kr(`Transition`, `TransitionCancel`),
            transitionend: Kr(`Transition`, `TransitionEnd`)
        }
            , Jr = {}
            , Yr = {};
        un && (Yr = document.createElement(`div`).style,
            `AnimationEvent` in window || (delete qr.animationend.animation,
                delete qr.animationiteration.animation,
                delete qr.animationstart.animation),
            `TransitionEvent` in window || delete qr.transitionend.transition);
        function Xr(e) {
            if (Jr[e])
                return Jr[e];
            if (!qr[e])
                return e;
            var t = qr[e], n;
            for (n in t)
                if (t.hasOwnProperty(n) && n in Yr)
                    return Jr[e] = t[n];
            return e
        }
        var Zr = Xr(`animationend`)
            , Qr = Xr(`animationiteration`)
            , $r = Xr(`animationstart`)
            , ei = Xr(`transitionrun`)
            , ti = Xr(`transitionstart`)
            , ni = Xr(`transitioncancel`)
            , ri = Xr(`transitionend`)
            , ii = new Map
            , ai = `abort auxClick beforeToggle cancel canPlay canPlayThrough click close contextMenu copy cut drag dragEnd dragEnter dragExit dragLeave dragOver dragStart drop durationChange emptied encrypted ended error gotPointerCapture input invalid keyDown keyPress keyUp load loadedData loadedMetadata loadStart lostPointerCapture mouseDown mouseMove mouseOut mouseOver mouseUp paste pause play playing pointerCancel pointerDown pointerMove pointerOut pointerOver pointerUp progress rateChange reset resize seeked seeking stalled submit suspend timeUpdate touchCancel touchEnd touchStart volumeChange scroll toggle touchMove waiting wheel`.split(` `);
        ai.push(`scrollEnd`);
        function oi(e, t) {
            ii.set(e, t),
                Tt(t, [e])
        }
        var si = typeof reportError == `function` ? reportError : function (e) {
            if (typeof window == `object` && typeof window.ErrorEvent == `function`) {
                var t = new window.ErrorEvent(`error`, {
                    bubbles: !0,
                    cancelable: !0,
                    message: typeof e == `object` && e && typeof e.message == `string` ? String(e.message) : String(e),
                    error: e
                });
                if (!window.dispatchEvent(t))
                    return
            } else if (typeof process == `object` && typeof process.emit == `function`) {
                process.emit(`uncaughtException`, e);
                return
            }
            console.error(e)
        }
            , ci = []
            , li = 0
            , ui = 0;
        function di() {
            for (var e = li, t = ui = li = 0; t < e;) {
                var n = ci[t];
                ci[t++] = null;
                var r = ci[t];
                ci[t++] = null;
                var i = ci[t];
                ci[t++] = null;
                var a = ci[t];
                if (ci[t++] = null,
                    r !== null && i !== null) {
                    var o = r.pending;
                    o === null ? i.next = i : (i.next = o.next,
                        o.next = i),
                        r.pending = i
                }
                a !== 0 && hi(n, i, a)
            }
        }
        function fi(e, t, n, r) {
            ci[li++] = e,
                ci[li++] = t,
                ci[li++] = n,
                ci[li++] = r,
                ui |= r,
                e.lanes |= r,
                e = e.alternate,
                e !== null && (e.lanes |= r)
        }
        function pi(e, t, n, r) {
            return fi(e, t, n, r),
                gi(e)
        }
        function mi(e, t) {
            return fi(e, null, null, t),
                gi(e)
        }
        function hi(e, t, n) {
            e.lanes |= n;
            var r = e.alternate;
            r !== null && (r.lanes |= n);
            for (var i = !1, a = e.return; a !== null;)
                a.childLanes |= n,
                    r = a.alternate,
                    r !== null && (r.childLanes |= n),
                    a.tag === 22 && (e = a.stateNode,
                        e === null || e._visibility & 1 || (i = !0)),
                    e = a,
                    a = a.return;
            return e.tag === 3 ? (a = e.stateNode,
                i && t !== null && (i = 31 - Be(n),
                    e = a.hiddenUpdates,
                    r = e[i],
                    r === null ? e[i] = [t] : r.push(t),
                    t.lane = n | 536870912),
                a) : null
        }
        function gi(e) {
            if (50 < wu)
                throw wu = 0,
                Tu = null,
                Error(i(185));
            for (var t = e.return; t !== null;)
                e = t,
                    t = e.return;
            return e.tag === 3 ? e.stateNode : null
        }
        var _i = {};
        function vi(e, t, n, r) {
            this.tag = e,
                this.key = n,
                this.sibling = this.child = this.return = this.stateNode = this.type = this.elementType = null,
                this.index = 0,
                this.refCleanup = this.ref = null,
                this.pendingProps = t,
                this.dependencies = this.memoizedState = this.updateQueue = this.memoizedProps = null,
                this.mode = r,
                this.subtreeFlags = this.flags = 0,
                this.deletions = null,
                this.childLanes = this.lanes = 0,
                this.alternate = null
        }
        function yi(e, t, n, r) {
            return new vi(e, t, n, r)
        }
        function bi(e) {
            return e = e.prototype,
                !(!e || !e.isReactComponent)
        }
        function xi(e, t) {
            var n = e.alternate;
            return n === null ? (n = yi(e.tag, t, e.key, e.mode),
                n.elementType = e.elementType,
                n.type = e.type,
                n.stateNode = e.stateNode,
                n.alternate = e,
                e.alternate = n) : (n.pendingProps = t,
                    n.type = e.type,
                    n.flags = 0,
                    n.subtreeFlags = 0,
                    n.deletions = null),
                n.flags = e.flags & 65011712,
                n.childLanes = e.childLanes,
                n.lanes = e.lanes,
                n.child = e.child,
                n.memoizedProps = e.memoizedProps,
                n.memoizedState = e.memoizedState,
                n.updateQueue = e.updateQueue,
                t = e.dependencies,
                n.dependencies = t === null ? null : {
                    lanes: t.lanes,
                    firstContext: t.firstContext
                },
                n.sibling = e.sibling,
                n.index = e.index,
                n.ref = e.ref,
                n.refCleanup = e.refCleanup,
                n
        }
        function Si(e, t) {
            e.flags &= 65011714;
            var n = e.alternate;
            return n === null ? (e.childLanes = 0,
                e.lanes = t,
                e.child = null,
                e.subtreeFlags = 0,
                e.memoizedProps = null,
                e.memoizedState = null,
                e.updateQueue = null,
                e.dependencies = null,
                e.stateNode = null) : (e.childLanes = n.childLanes,
                    e.lanes = n.lanes,
                    e.child = n.child,
                    e.subtreeFlags = 0,
                    e.deletions = null,
                    e.memoizedProps = n.memoizedProps,
                    e.memoizedState = n.memoizedState,
                    e.updateQueue = n.updateQueue,
                    e.type = n.type,
                    t = n.dependencies,
                    e.dependencies = t === null ? null : {
                        lanes: t.lanes,
                        firstContext: t.firstContext
                    }),
                e
        }
        function Ci(e, t, n, r, a, o) {
            var s = 0;
            if (r = e,
                typeof e == `function`)
                bi(e) && (s = 1);
            else if (typeof e == `string`)
                s = ap(e, n, P.current) ? 26 : e === `html` || e === `head` || e === `body` ? 27 : 5;
            else
                a: switch (e) {
                    case ee:
                        return e = yi(31, n, t, a),
                            e.elementType = ee,
                            e.lanes = o,
                            e;
                    case y:
                        return wi(n.children, a, o, t);
                    case b:
                        s = 8,
                            a |= 24;
                        break;
                    case x:
                        return e = yi(12, n, t, a | 2),
                            e.elementType = x,
                            e.lanes = o,
                            e;
                    case T:
                        return e = yi(13, n, t, a),
                            e.elementType = T,
                            e.lanes = o,
                            e;
                    case E:
                        return e = yi(19, n, t, a),
                            e.elementType = E,
                            e.lanes = o,
                            e;
                    default:
                        if (typeof e == `object` && e)
                            switch (e.$$typeof) {
                                case C:
                                    s = 10;
                                    break a;
                                case S:
                                    s = 9;
                                    break a;
                                case w:
                                    s = 11;
                                    break a;
                                case D:
                                    s = 14;
                                    break a;
                                case O:
                                    s = 16,
                                        r = null;
                                    break a
                            }
                        s = 29,
                            n = Error(i(130, e === null ? `null` : typeof e, ``)),
                            r = null
                }
            return t = yi(s, n, t, a),
                t.elementType = e,
                t.type = r,
                t.lanes = o,
                t
        }
        function wi(e, t, n, r) {
            return e = yi(7, e, r, t),
                e.lanes = n,
                e
        }
        function Ti(e, t, n) {
            return e = yi(6, e, null, t),
                e.lanes = n,
                e
        }
        function Ei(e) {
            var t = yi(18, null, null, 0);
            return t.stateNode = e,
                t
        }
        function Di(e, t, n) {
            return t = yi(4, e.children === null ? [] : e.children, e.key, t),
                t.lanes = n,
                t.stateNode = {
                    containerInfo: e.containerInfo,
                    pendingChildren: null,
                    implementation: e.implementation
                },
                t
        }
        var Oi = new WeakMap;
        function ki(e, t) {
            if (typeof e == `object` && e) {
                var n = Oi.get(e);
                return n === void 0 ? (t = {
                    value: e,
                    source: t,
                    stack: Se(t)
                },
                    Oi.set(e, t),
                    t) : n
            }
            return {
                value: e,
                source: t,
                stack: Se(t)
            }
        }
        var Ai = []
            , ji = 0
            , Mi = null
            , Ni = 0
            , Pi = []
            , Fi = 0
            , Ii = null
            , Li = 1
            , Ri = ``;
        function zi(e, t) {
            Ai[ji++] = Ni,
                Ai[ji++] = Mi,
                Mi = e,
                Ni = t
        }
        function Bi(e, t, n) {
            Pi[Fi++] = Li,
                Pi[Fi++] = Ri,
                Pi[Fi++] = Ii,
                Ii = e;
            var r = Li;
            e = Ri;
            var i = 32 - Be(r) - 1;
            r &= ~(1 << i),
                n += 1;
            var a = 32 - Be(t) + i;
            if (30 < a) {
                var o = i - i % 5;
                a = (r & (1 << o) - 1).toString(32),
                    r >>= o,
                    i -= o,
                    Li = 1 << 32 - Be(t) + i | n << i | r,
                    Ri = a + e
            } else
                Li = 1 << a | n << i | r,
                    Ri = e
        }
        function Vi(e) {
            e.return !== null && (zi(e, 1),
                Bi(e, 1, 0))
        }
        function Hi(e) {
            for (; e === Mi;)
                Mi = Ai[--ji],
                    Ai[ji] = null,
                    Ni = Ai[--ji],
                    Ai[ji] = null;
            for (; e === Ii;)
                Ii = Pi[--Fi],
                    Pi[Fi] = null,
                    Ri = Pi[--Fi],
                    Pi[Fi] = null,
                    Li = Pi[--Fi],
                    Pi[Fi] = null
        }
        function Ui(e, t) {
            Pi[Fi++] = Li,
                Pi[Fi++] = Ri,
                Pi[Fi++] = Ii,
                Li = t.id,
                Ri = t.overflow,
                Ii = e
        }
        var Wi = null
            , z = null
            , B = !1
            , Gi = null
            , Ki = !1
            , qi = Error(i(519));
        function Ji(e) {
            var t = Error(i(418, 1 < arguments.length && arguments[1] !== void 0 && arguments[1] ? `text` : `HTML`, ``));
            throw ea(ki(t, e)),
            qi
        }
        function Yi(e) {
            var t = e.stateNode
                , n = e.type
                , r = e.memoizedProps;
            switch (t[lt] = e,
            t[ut] = r,
            n) {
                case `dialog`:
                    Q(`cancel`, t),
                        Q(`close`, t);
                    break;
                case `iframe`:
                case `object`:
                case `embed`:
                    Q(`load`, t);
                    break;
                case `video`:
                case `audio`:
                    for (n = 0; n < Md.length; n++)
                        Q(Md[n], t);
                    break;
                case `source`:
                    Q(`error`, t);
                    break;
                case `img`:
                case `image`:
                case `link`:
                    Q(`error`, t),
                        Q(`load`, t);
                    break;
                case `details`:
                    Q(`toggle`, t);
                    break;
                case `input`:
                    Q(`invalid`, t),
                        Ut(t, r.value, r.defaultValue, r.checked, r.defaultChecked, r.type, r.name, !0);
                    break;
                case `select`:
                    Q(`invalid`, t);
                    break;
                case `textarea`:
                    Q(`invalid`, t),
                        qt(t, r.value, r.defaultValue, r.children)
            }
            n = r.children,
                typeof n != `string` && typeof n != `number` && typeof n != `bigint` || t.textContent === `` + n || !0 === r.suppressHydrationWarning || qd(t.textContent, n) ? (r.popover != null && (Q(`beforetoggle`, t),
                    Q(`toggle`, t)),
                    r.onScroll != null && Q(`scroll`, t),
                    r.onScrollEnd != null && Q(`scrollend`, t),
                    r.onClick != null && (t.onclick = nn),
                    t = !0) : t = !1,
                t || Ji(e, !0)
        }
        function Xi(e) {
            for (Wi = e.return; Wi;)
                switch (Wi.tag) {
                    case 5:
                    case 31:
                    case 13:
                        Ki = !1;
                        return;
                    case 27:
                    case 3:
                        Ki = !0;
                        return;
                    default:
                        Wi = Wi.return
                }
        }
        function Zi(e) {
            if (e !== Wi)
                return !1;
            if (!B)
                return Xi(e),
                    B = !0,
                    !1;
            var t = e.tag, n;
            if ((n = t !== 3 && t !== 27) && ((n = t === 5) && (n = e.type,
                n = !(n !== `form` && n !== `button`) || af(e.type, e.memoizedProps)),
                n = !n),
                n && z && Ji(e),
                Xi(e),
                t === 13) {
                if (e = e.memoizedState,
                    e = e === null ? null : e.dehydrated,
                    !e)
                    throw Error(i(317));
                z = Ef(e)
            } else if (t === 31) {
                if (e = e.memoizedState,
                    e = e === null ? null : e.dehydrated,
                    !e)
                    throw Error(i(317));
                z = Ef(e)
            } else
                t === 27 ? (t = z,
                    mf(e.type) ? (e = Tf,
                        Tf = null,
                        z = e) : z = t) : z = Wi ? wf(e.stateNode.nextSibling) : null;
            return !0
        }
        function Qi() {
            z = Wi = null,
                B = !1
        }
        function $i() {
            var e = Gi;
            return e !== null && (uu === null ? uu = e : uu.push.apply(uu, e),
                Gi = null),
                e
        }
        function ea(e) {
            Gi === null ? Gi = [e] : Gi.push(e)
        }
        var ta = j(null)
            , na = null
            , ra = null;
        function ia(e, t, n) {
            N(ta, t._currentValue),
                t._currentValue = n
        }
        function aa(e) {
            e._currentValue = ta.current,
                M(ta)
        }
        function oa(e, t, n) {
            for (; e !== null;) {
                var r = e.alternate;
                if ((e.childLanes & t) === t ? r !== null && (r.childLanes & t) !== t && (r.childLanes |= t) : (e.childLanes |= t,
                    r !== null && (r.childLanes |= t)),
                    e === n)
                    break;
                e = e.return
            }
        }
        function sa(e, t, n, r) {
            var a = e.child;
            for (a !== null && (a.return = e); a !== null;) {
                var o = a.dependencies;
                if (o !== null) {
                    var s = a.child;
                    o = o.firstContext;
                    a: for (; o !== null;) {
                        var c = o;
                        o = a;
                        for (var l = 0; l < t.length; l++)
                            if (c.context === t[l]) {
                                o.lanes |= n,
                                    c = o.alternate,
                                    c !== null && (c.lanes |= n),
                                    oa(o.return, n, e),
                                    r || (s = null);
                                break a
                            }
                        o = c.next
                    }
                } else if (a.tag === 18) {
                    if (s = a.return,
                        s === null)
                        throw Error(i(341));
                    s.lanes |= n,
                        o = s.alternate,
                        o !== null && (o.lanes |= n),
                        oa(s, n, e),
                        s = null
                } else
                    s = a.child;
                if (s !== null)
                    s.return = a;
                else
                    for (s = a; s !== null;) {
                        if (s === e) {
                            s = null;
                            break
                        }
                        if (a = s.sibling,
                            a !== null) {
                            a.return = s.return,
                                s = a;
                            break
                        }
                        s = s.return
                    }
                a = s
            }
        }
        function ca(e, t, n, r) {
            e = null;
            for (var a = t, o = !1; a !== null;) {
                if (!o) {
                    if (a.flags & 524288)
                        o = !0;
                    else if (a.flags & 262144)
                        break
                }
                if (a.tag === 10) {
                    var s = a.alternate;
                    if (s === null)
                        throw Error(i(387));
                    if (s = s.memoizedProps,
                        s !== null) {
                        var c = a.type;
                        Pr(a.pendingProps.value, s.value) || (e === null ? e = [c] : e.push(c))
                    }
                } else if (a === de.current) {
                    if (s = a.alternate,
                        s === null)
                        throw Error(i(387));
                    s.memoizedState.memoizedState !== a.memoizedState.memoizedState && (e === null ? e = [mp] : e.push(mp))
                }
                a = a.return
            }
            e !== null && sa(t, e, n, r),
                t.flags |= 262144
        }
        function la(e) {
            for (e = e.firstContext; e !== null;) {
                if (!Pr(e.context._currentValue, e.memoizedValue))
                    return !0;
                e = e.next
            }
            return !1
        }
        function ua(e) {
            na = e,
                ra = null,
                e = e.dependencies,
                e !== null && (e.firstContext = null)
        }
        function da(e) {
            return pa(na, e)
        }
        function fa(e, t) {
            return na === null && ua(e),
                pa(e, t)
        }
        function pa(e, t) {
            var n = t._currentValue;
            if (t = {
                context: t,
                memoizedValue: n,
                next: null
            },
                ra === null) {
                if (e === null)
                    throw Error(i(308));
                ra = t,
                    e.dependencies = {
                        lanes: 0,
                        firstContext: t
                    },
                    e.flags |= 524288
            } else
                ra = ra.next = t;
            return n
        }
        var ma = typeof AbortController < `u` ? AbortController : function () {
            var e = []
                , t = this.signal = {
                    aborted: !1,
                    addEventListener: function (t, n) {
                        e.push(n)
                    }
                };
            this.abort = function () {
                t.aborted = !0,
                    e.forEach(function (e) {
                        return e()
                    })
            }
        }
            , ha = t.unstable_scheduleCallback
            , ga = t.unstable_NormalPriority
            , _a = {
                $$typeof: C,
                Consumer: null,
                Provider: null,
                _currentValue: null,
                _currentValue2: null,
                _threadCount: 0
            };
        function va() {
            return {
                controller: new ma,
                data: new Map,
                refCount: 0
            }
        }
        function ya(e) {
            e.refCount--,
                e.refCount === 0 && ha(ga, function () {
                    e.controller.abort()
                })
        }
        var ba = null
            , xa = 0
            , Sa = 0
            , Ca = null;
        function wa(e, t) {
            if (ba === null) {
                var n = ba = [];
                xa = 0,
                    Sa = wd(),
                    Ca = {
                        status: `pending`,
                        value: void 0,
                        then: function (e) {
                            n.push(e)
                        }
                    }
            }
            return xa++,
                t.then(Ta, Ta),
                t
        }
        function Ta() {
            if (--xa === 0 && ba !== null) {
                Ca !== null && (Ca.status = `fulfilled`);
                var e = ba;
                ba = null,
                    Sa = 0,
                    Ca = null;
                for (var t = 0; t < e.length; t++)
                    (0,
                        e[t])()
            }
        }
        function Ea(e, t) {
            var n = []
                , r = {
                    status: `pending`,
                    value: null,
                    reason: null,
                    then: function (e) {
                        n.push(e)
                    }
                };
            return e.then(function () {
                r.status = `fulfilled`,
                    r.value = t;
                for (var e = 0; e < n.length; e++)
                    (0,
                        n[e])(t)
            }, function (e) {
                for (r.status = `rejected`,
                    r.reason = e,
                    e = 0; e < n.length; e++)
                    (0,
                        n[e])(void 0)
            }),
                r
        }
        var Da = k.S;
        k.S = function (e, t) {
            pu = Oe(),
                typeof t == `object` && t && typeof t.then == `function` && wa(e, t),
                Da !== null && Da(e, t)
        }
            ;
        var Oa = j(null);
        function ka() {
            var e = Oa.current;
            return e === null ? K.pooledCache : e
        }
        function Aa(e, t) {
            t === null ? N(Oa, Oa.current) : N(Oa, t.pool)
        }
        function ja() {
            var e = ka();
            return e === null ? null : {
                parent: _a._currentValue,
                pool: e
            }
        }
        var Ma = Error(i(460))
            , Na = Error(i(474))
            , Pa = Error(i(542))
            , Fa = {
                then: function () { }
            };
        function Ia(e) {
            return e = e.status,
                e === `fulfilled` || e === `rejected`
        }
        function La(e, t, n) {
            switch (n = e[n],
            n === void 0 ? e.push(t) : n !== t && (t.then(nn, nn),
                t = n),
            t.status) {
                case `fulfilled`:
                    return t.value;
                case `rejected`:
                    throw e = t.reason,
                    Va(e),
                    e;
                default:
                    if (typeof t.status == `string`)
                        t.then(nn, nn);
                    else {
                        if (e = K,
                            e !== null && 100 < e.shellSuspendCounter)
                            throw Error(i(482));
                        e = t,
                            e.status = `pending`,
                            e.then(function (e) {
                                if (t.status === `pending`) {
                                    var n = t;
                                    n.status = `fulfilled`,
                                        n.value = e
                                }
                            }, function (e) {
                                if (t.status === `pending`) {
                                    var n = t;
                                    n.status = `rejected`,
                                        n.reason = e
                                }
                            })
                    }
                    switch (t.status) {
                        case `fulfilled`:
                            return t.value;
                        case `rejected`:
                            throw e = t.reason,
                            Va(e),
                            e
                    }
                    throw za = t,
                    Ma
            }
        }
        function Ra(e) {
            try {
                var t = e._init;
                return t(e._payload)
            } catch (e) {
                throw typeof e == `object` && e && typeof e.then == `function` ? (za = e,
                    Ma) : e
            }
        }
        var za = null;
        function Ba() {
            if (za === null)
                throw Error(i(459));
            var e = za;
            return za = null,
                e
        }
        function Va(e) {
            if (e === Ma || e === Pa)
                throw Error(i(483))
        }
        var Ha = null
            , Ua = 0;
        function Wa(e) {
            var t = Ua;
            return Ua += 1,
                Ha === null && (Ha = []),
                La(Ha, e, t)
        }
        function Ga(e, t) {
            t = t.props.ref,
                e.ref = t === void 0 ? null : t
        }
        function Ka(e, t) {
            throw t.$$typeof === g ? Error(i(525)) : (e = Object.prototype.toString.call(t),
                Error(i(31, e === `[object Object]` ? `object with keys {` + Object.keys(t).join(`, `) + `}` : e)))
        }
        function qa(e) {
            function t(t, n) {
                if (e) {
                    var r = t.deletions;
                    r === null ? (t.deletions = [n],
                        t.flags |= 16) : r.push(n)
                }
            }
            function n(n, r) {
                if (!e)
                    return null;
                for (; r !== null;)
                    t(n, r),
                        r = r.sibling;
                return null
            }
            function r(e) {
                for (var t = new Map; e !== null;)
                    e.key === null ? t.set(e.index, e) : t.set(e.key, e),
                        e = e.sibling;
                return t
            }
            function a(e, t) {
                return e = xi(e, t),
                    e.index = 0,
                    e.sibling = null,
                    e
            }
            function o(t, n, r) {
                return t.index = r,
                    e ? (r = t.alternate,
                        r === null ? (t.flags |= 67108866,
                            n) : (r = r.index,
                                r < n ? (t.flags |= 67108866,
                                    n) : r)) : (t.flags |= 1048576,
                                        n)
            }
            function s(t) {
                return e && t.alternate === null && (t.flags |= 67108866),
                    t
            }
            function c(e, t, n, r) {
                return t === null || t.tag !== 6 ? (t = Ti(n, e.mode, r),
                    t.return = e,
                    t) : (t = a(t, n),
                        t.return = e,
                        t)
            }
            function l(e, t, n, r) {
                var i = n.type;
                return i === y ? d(e, t, n.props.children, r, n.key) : t !== null && (t.elementType === i || typeof i == `object` && i && i.$$typeof === O && Ra(i) === t.type) ? (t = a(t, n.props),
                    Ga(t, n),
                    t.return = e,
                    t) : (t = Ci(n.type, n.key, n.props, null, e.mode, r),
                        Ga(t, n),
                        t.return = e,
                        t)
            }
            function u(e, t, n, r) {
                return t === null || t.tag !== 4 || t.stateNode.containerInfo !== n.containerInfo || t.stateNode.implementation !== n.implementation ? (t = Di(n, e.mode, r),
                    t.return = e,
                    t) : (t = a(t, n.children || []),
                        t.return = e,
                        t)
            }
            function d(e, t, n, r, i) {
                return t === null || t.tag !== 7 ? (t = wi(n, e.mode, r, i),
                    t.return = e,
                    t) : (t = a(t, n),
                        t.return = e,
                        t)
            }
            function f(e, t, n) {
                if (typeof t == `string` && t !== `` || typeof t == `number` || typeof t == `bigint`)
                    return t = Ti(`` + t, e.mode, n),
                        t.return = e,
                        t;
                if (typeof t == `object` && t) {
                    switch (t.$$typeof) {
                        case _:
                            return n = Ci(t.type, t.key, t.props, null, e.mode, n),
                                Ga(n, t),
                                n.return = e,
                                n;
                        case v:
                            return t = Di(t, e.mode, n),
                                t.return = e,
                                t;
                        case O:
                            return t = Ra(t),
                                f(e, t, n)
                    }
                    if (oe(t) || re(t))
                        return t = wi(t, e.mode, n, null),
                            t.return = e,
                            t;
                    if (typeof t.then == `function`)
                        return f(e, Wa(t), n);
                    if (t.$$typeof === C)
                        return f(e, fa(e, t), n);
                    Ka(e, t)
                }
                return null
            }
            function p(e, t, n, r) {
                var i = t === null ? null : t.key;
                if (typeof n == `string` && n !== `` || typeof n == `number` || typeof n == `bigint`)
                    return i === null ? c(e, t, `` + n, r) : null;
                if (typeof n == `object` && n) {
                    switch (n.$$typeof) {
                        case _:
                            return n.key === i ? l(e, t, n, r) : null;
                        case v:
                            return n.key === i ? u(e, t, n, r) : null;
                        case O:
                            return n = Ra(n),
                                p(e, t, n, r)
                    }
                    if (oe(n) || re(n))
                        return i === null ? d(e, t, n, r, null) : null;
                    if (typeof n.then == `function`)
                        return p(e, t, Wa(n), r);
                    if (n.$$typeof === C)
                        return p(e, t, fa(e, n), r);
                    Ka(e, n)
                }
                return null
            }
            function m(e, t, n, r, i) {
                if (typeof r == `string` && r !== `` || typeof r == `number` || typeof r == `bigint`)
                    return e = e.get(n) || null,
                        c(t, e, `` + r, i);
                if (typeof r == `object` && r) {
                    switch (r.$$typeof) {
                        case _:
                            return e = e.get(r.key === null ? n : r.key) || null,
                                l(t, e, r, i);
                        case v:
                            return e = e.get(r.key === null ? n : r.key) || null,
                                u(t, e, r, i);
                        case O:
                            return r = Ra(r),
                                m(e, t, n, r, i)
                    }
                    if (oe(r) || re(r))
                        return e = e.get(n) || null,
                            d(t, e, r, i, null);
                    if (typeof r.then == `function`)
                        return m(e, t, n, Wa(r), i);
                    if (r.$$typeof === C)
                        return m(e, t, n, fa(t, r), i);
                    Ka(t, r)
                }
                return null
            }
            function h(i, a, s, c) {
                for (var l = null, u = null, d = a, h = a = 0, g = null; d !== null && h < s.length; h++) {
                    d.index > h ? (g = d,
                        d = null) : g = d.sibling;
                    var _ = p(i, d, s[h], c);
                    if (_ === null) {
                        d === null && (d = g);
                        break
                    }
                    e && d && _.alternate === null && t(i, d),
                        a = o(_, a, h),
                        u === null ? l = _ : u.sibling = _,
                        u = _,
                        d = g
                }
                if (h === s.length)
                    return n(i, d),
                        B && zi(i, h),
                        l;
                if (d === null) {
                    for (; h < s.length; h++)
                        d = f(i, s[h], c),
                            d !== null && (a = o(d, a, h),
                                u === null ? l = d : u.sibling = d,
                                u = d);
                    return B && zi(i, h),
                        l
                }
                for (d = r(d); h < s.length; h++)
                    g = m(d, i, h, s[h], c),
                        g !== null && (e && g.alternate !== null && d.delete(g.key === null ? h : g.key),
                            a = o(g, a, h),
                            u === null ? l = g : u.sibling = g,
                            u = g);
                return e && d.forEach(function (e) {
                    return t(i, e)
                }),
                    B && zi(i, h),
                    l
            }
            function g(a, s, c, l) {
                if (c == null)
                    throw Error(i(151));
                for (var u = null, d = null, h = s, g = s = 0, _ = null, v = c.next(); h !== null && !v.done; g++,
                    v = c.next()) {
                    h.index > g ? (_ = h,
                        h = null) : _ = h.sibling;
                    var y = p(a, h, v.value, l);
                    if (y === null) {
                        h === null && (h = _);
                        break
                    }
                    e && h && y.alternate === null && t(a, h),
                        s = o(y, s, g),
                        d === null ? u = y : d.sibling = y,
                        d = y,
                        h = _
                }
                if (v.done)
                    return n(a, h),
                        B && zi(a, g),
                        u;
                if (h === null) {
                    for (; !v.done; g++,
                        v = c.next())
                        v = f(a, v.value, l),
                            v !== null && (s = o(v, s, g),
                                d === null ? u = v : d.sibling = v,
                                d = v);
                    return B && zi(a, g),
                        u
                }
                for (h = r(h); !v.done; g++,
                    v = c.next())
                    v = m(h, a, g, v.value, l),
                        v !== null && (e && v.alternate !== null && h.delete(v.key === null ? g : v.key),
                            s = o(v, s, g),
                            d === null ? u = v : d.sibling = v,
                            d = v);
                return e && h.forEach(function (e) {
                    return t(a, e)
                }),
                    B && zi(a, g),
                    u
            }
            function b(e, r, o, c) {
                if (typeof o == `object` && o && o.type === y && o.key === null && (o = o.props.children),
                    typeof o == `object` && o) {
                    switch (o.$$typeof) {
                        case _:
                            a: {
                                for (var l = o.key; r !== null;) {
                                    if (r.key === l) {
                                        if (l = o.type,
                                            l === y) {
                                            if (r.tag === 7) {
                                                n(e, r.sibling),
                                                    c = a(r, o.props.children),
                                                    c.return = e,
                                                    e = c;
                                                break a
                                            }
                                        } else if (r.elementType === l || typeof l == `object` && l && l.$$typeof === O && Ra(l) === r.type) {
                                            n(e, r.sibling),
                                                c = a(r, o.props),
                                                Ga(c, o),
                                                c.return = e,
                                                e = c;
                                            break a
                                        }
                                        n(e, r);
                                        break
                                    } else
                                        t(e, r);
                                    r = r.sibling
                                }
                                o.type === y ? (c = wi(o.props.children, e.mode, c, o.key),
                                    c.return = e,
                                    e = c) : (c = Ci(o.type, o.key, o.props, null, e.mode, c),
                                        Ga(c, o),
                                        c.return = e,
                                        e = c)
                            }
                            return s(e);
                        case v:
                            a: {
                                for (l = o.key; r !== null;) {
                                    if (r.key === l)
                                        if (r.tag === 4 && r.stateNode.containerInfo === o.containerInfo && r.stateNode.implementation === o.implementation) {
                                            n(e, r.sibling),
                                                c = a(r, o.children || []),
                                                c.return = e,
                                                e = c;
                                            break a
                                        } else {
                                            n(e, r);
                                            break
                                        }
                                    else
                                        t(e, r);
                                    r = r.sibling
                                }
                                c = Di(o, e.mode, c),
                                    c.return = e,
                                    e = c
                            }
                            return s(e);
                        case O:
                            return o = Ra(o),
                                b(e, r, o, c)
                    }
                    if (oe(o))
                        return h(e, r, o, c);
                    if (re(o)) {
                        if (l = re(o),
                            typeof l != `function`)
                            throw Error(i(150));
                        return o = l.call(o),
                            g(e, r, o, c)
                    }
                    if (typeof o.then == `function`)
                        return b(e, r, Wa(o), c);
                    if (o.$$typeof === C)
                        return b(e, r, fa(e, o), c);
                    Ka(e, o)
                }
                return typeof o == `string` && o !== `` || typeof o == `number` || typeof o == `bigint` ? (o = `` + o,
                    r !== null && r.tag === 6 ? (n(e, r.sibling),
                        c = a(r, o),
                        c.return = e,
                        e = c) : (n(e, r),
                            c = Ti(o, e.mode, c),
                            c.return = e,
                            e = c),
                    s(e)) : n(e, r)
            }
            return function (e, t, n, r) {
                try {
                    Ua = 0;
                    var i = b(e, t, n, r);
                    return Ha = null,
                        i
                } catch (t) {
                    if (t === Ma || t === Pa)
                        throw t;
                    var a = yi(29, t, null, e.mode);
                    return a.lanes = r,
                        a.return = e,
                        a
                }
            }
        }
        var Ja = qa(!0)
            , Ya = qa(!1)
            , Xa = !1;
        function Za(e) {
            e.updateQueue = {
                baseState: e.memoizedState,
                firstBaseUpdate: null,
                lastBaseUpdate: null,
                shared: {
                    pending: null,
                    lanes: 0,
                    hiddenCallbacks: null
                },
                callbacks: null
            }
        }
        function Qa(e, t) {
            e = e.updateQueue,
                t.updateQueue === e && (t.updateQueue = {
                    baseState: e.baseState,
                    firstBaseUpdate: e.firstBaseUpdate,
                    lastBaseUpdate: e.lastBaseUpdate,
                    shared: e.shared,
                    callbacks: null
                })
        }
        function $a(e) {
            return {
                lane: e,
                tag: 0,
                payload: null,
                callback: null,
                next: null
            }
        }
        function eo(e, t, n) {
            var r = e.updateQueue;
            if (r === null)
                return null;
            if (r = r.shared,
                G & 2) {
                var i = r.pending;
                return i === null ? t.next = t : (t.next = i.next,
                    i.next = t),
                    r.pending = t,
                    t = gi(e),
                    hi(e, null, n),
                    t
            }
            return fi(e, r, t, n),
                gi(e)
        }
        function to(e, t, n) {
            if (t = t.updateQueue,
                t !== null && (t = t.shared,
                    n & 4194048)) {
                var r = t.lanes;
                r &= e.pendingLanes,
                    n |= r,
                    t.lanes = n,
                    nt(e, n)
            }
        }
        function no(e, t) {
            var n = e.updateQueue
                , r = e.alternate;
            if (r !== null && (r = r.updateQueue,
                n === r)) {
                var i = null
                    , a = null;
                if (n = n.firstBaseUpdate,
                    n !== null) {
                    do {
                        var o = {
                            lane: n.lane,
                            tag: n.tag,
                            payload: n.payload,
                            callback: null,
                            next: null
                        };
                        a === null ? i = a = o : a = a.next = o,
                            n = n.next
                    } while (n !== null);
                    a === null ? i = a = t : a = a.next = t
                } else
                    i = a = t;
                n = {
                    baseState: r.baseState,
                    firstBaseUpdate: i,
                    lastBaseUpdate: a,
                    shared: r.shared,
                    callbacks: r.callbacks
                },
                    e.updateQueue = n;
                return
            }
            e = n.lastBaseUpdate,
                e === null ? n.firstBaseUpdate = t : e.next = t,
                n.lastBaseUpdate = t
        }
        var ro = !1;
        function io() {
            if (ro) {
                var e = Ca;
                if (e !== null)
                    throw e
            }
        }
        function ao(e, t, n, r) {
            ro = !1;
            var i = e.updateQueue;
            Xa = !1;
            var a = i.firstBaseUpdate
                , o = i.lastBaseUpdate
                , s = i.shared.pending;
            if (s !== null) {
                i.shared.pending = null;
                var c = s
                    , l = c.next;
                c.next = null,
                    o === null ? a = l : o.next = l,
                    o = c;
                var u = e.alternate;
                u !== null && (u = u.updateQueue,
                    s = u.lastBaseUpdate,
                    s !== o && (s === null ? u.firstBaseUpdate = l : s.next = l,
                        u.lastBaseUpdate = c))
            }
            if (a !== null) {
                var d = i.baseState;
                o = 0,
                    u = l = c = null,
                    s = a;
                do {
                    var f = s.lane & -536870913
                        , p = f !== s.lane;
                    if (p ? (J & f) === f : (r & f) === f) {
                        f !== 0 && f === Sa && (ro = !0),
                            u !== null && (u = u.next = {
                                lane: 0,
                                tag: s.tag,
                                payload: s.payload,
                                callback: null,
                                next: null
                            });
                        a: {
                            var m = e
                                , g = s;
                            f = t;
                            var _ = n;
                            switch (g.tag) {
                                case 1:
                                    if (m = g.payload,
                                        typeof m == `function`) {
                                        d = m.call(_, d, f);
                                        break a
                                    }
                                    d = m;
                                    break a;
                                case 3:
                                    m.flags = m.flags & -65537 | 128;
                                case 0:
                                    if (m = g.payload,
                                        f = typeof m == `function` ? m.call(_, d, f) : m,
                                        f == null)
                                        break a;
                                    d = h({}, d, f);
                                    break a;
                                case 2:
                                    Xa = !0
                            }
                        }
                        f = s.callback,
                            f !== null && (e.flags |= 64,
                                p && (e.flags |= 8192),
                                p = i.callbacks,
                                p === null ? i.callbacks = [f] : p.push(f))
                    } else
                        p = {
                            lane: f,
                            tag: s.tag,
                            payload: s.payload,
                            callback: s.callback,
                            next: null
                        },
                            u === null ? (l = u = p,
                                c = d) : u = u.next = p,
                            o |= f;
                    if (s = s.next,
                        s === null) {
                        if (s = i.shared.pending,
                            s === null)
                            break;
                        p = s,
                            s = p.next,
                            p.next = null,
                            i.lastBaseUpdate = p,
                            i.shared.pending = null
                    }
                } while (1);
                u === null && (c = d),
                    i.baseState = c,
                    i.firstBaseUpdate = l,
                    i.lastBaseUpdate = u,
                    a === null && (i.shared.lanes = 0),
                    iu |= o,
                    e.lanes = o,
                    e.memoizedState = d
            }
        }
        function oo(e, t) {
            if (typeof e != `function`)
                throw Error(i(191, e));
            e.call(t)
        }
        function so(e, t) {
            var n = e.callbacks;
            if (n !== null)
                for (e.callbacks = null,
                    e = 0; e < n.length; e++)
                    oo(n[e], t)
        }
        var co = j(null)
            , lo = j(0);
        function uo(e, t) {
            e = ru,
                N(lo, e),
                N(co, t),
                ru = e | t.baseLanes
        }
        function fo() {
            N(lo, ru),
                N(co, co.current)
        }
        function po() {
            ru = lo.current,
                M(co),
                M(lo)
        }
        var mo = j(null)
            , ho = null;
        function go(e) {
            var t = e.alternate;
            N(xo, xo.current & 1),
                N(mo, e),
                ho === null && (t === null || co.current !== null || t.memoizedState !== null) && (ho = e)
        }
        function _o(e) {
            N(xo, xo.current),
                N(mo, e),
                ho === null && (ho = e)
        }
        function vo(e) {
            e.tag === 22 ? (N(xo, xo.current),
                N(mo, e),
                ho === null && (ho = e)) : yo(e)
        }
        function yo() {
            N(xo, xo.current),
                N(mo, mo.current)
        }
        function bo(e) {
            M(mo),
                ho === e && (ho = null),
                M(xo)
        }
        var xo = j(0);
        function So(e) {
            for (var t = e; t !== null;) {
                if (t.tag === 13) {
                    var n = t.memoizedState;
                    if (n !== null && (n = n.dehydrated,
                        n === null || xf(n) || Sf(n)))
                        return t
                } else if (t.tag === 19 && (t.memoizedProps.revealOrder === `forwards` || t.memoizedProps.revealOrder === `backwards` || t.memoizedProps.revealOrder === `unstable_legacy-backwards` || t.memoizedProps.revealOrder === `together`)) {
                    if (t.flags & 128)
                        return t
                } else if (t.child !== null) {
                    t.child.return = t,
                        t = t.child;
                    continue
                }
                if (t === e)
                    break;
                for (; t.sibling === null;) {
                    if (t.return === null || t.return === e)
                        return null;
                    t = t.return
                }
                t.sibling.return = t.return,
                    t = t.sibling
            }
            return null
        }
        var Co = 0
            , V = null
            , H = null
            , wo = null
            , To = !1
            , Eo = !1
            , Do = !1
            , Oo = 0
            , ko = 0
            , Ao = null
            , jo = 0;
        function Mo() {
            throw Error(i(321))
        }
        function No(e, t) {
            if (t === null)
                return !1;
            for (var n = 0; n < t.length && n < e.length; n++)
                if (!Pr(e[n], t[n]))
                    return !1;
            return !0
        }
        function Po(e, t, n, r, i, a) {
            return Co = a,
                V = t,
                t.memoizedState = null,
                t.updateQueue = null,
                t.lanes = 0,
                k.H = e === null || e.memoizedState === null ? $s : ec,
                Do = !1,
                a = n(r, i),
                Do = !1,
                Eo && (a = Io(t, n, r, i)),
                Fo(e),
                a
        }
        function Fo(e) {
            k.H = Qs;
            var t = H !== null && H.next !== null;
            if (Co = 0,
                wo = H = V = null,
                To = !1,
                ko = 0,
                Ao = null,
                t)
                throw Error(i(300));
            e === null || _c || (e = e.dependencies,
                e !== null && la(e) && (_c = !0))
        }
        function Io(e, t, n, r) {
            V = e;
            var a = 0;
            do {
                if (Eo && (Ao = null),
                    ko = 0,
                    Eo = !1,
                    25 <= a)
                    throw Error(i(301));
                if (a += 1,
                    wo = H = null,
                    e.updateQueue != null) {
                    var o = e.updateQueue;
                    o.lastEffect = null,
                        o.events = null,
                        o.stores = null,
                        o.memoCache != null && (o.memoCache.index = 0)
                }
                k.H = tc,
                    o = t(n, r)
            } while (Eo);
            return o
        }
        function Lo() {
            var e = k.H
                , t = e.useState()[0];
            return t = typeof t.then == `function` ? Wo(t) : t,
                e = e.useState()[0],
                (H === null ? null : H.memoizedState) !== e && (V.flags |= 1024),
                t
        }
        function Ro() {
            var e = Oo !== 0;
            return Oo = 0,
                e
        }
        function zo(e, t, n) {
            t.updateQueue = e.updateQueue,
                t.flags &= -2053,
                e.lanes &= ~n
        }
        function Bo(e) {
            if (To) {
                for (e = e.memoizedState; e !== null;) {
                    var t = e.queue;
                    t !== null && (t.pending = null),
                        e = e.next
                }
                To = !1
            }
            Co = 0,
                wo = H = V = null,
                Eo = !1,
                ko = Oo = 0,
                Ao = null
        }
        function Vo() {
            var e = {
                memoizedState: null,
                baseState: null,
                baseQueue: null,
                queue: null,
                next: null
            };
            return wo === null ? V.memoizedState = wo = e : wo = wo.next = e,
                wo
        }
        function Ho() {
            if (H === null) {
                var e = V.alternate;
                e = e === null ? null : e.memoizedState
            } else
                e = H.next;
            var t = wo === null ? V.memoizedState : wo.next;
            if (t !== null)
                wo = t,
                    H = e;
            else {
                if (e === null)
                    throw V.alternate === null ? Error(i(467)) : Error(i(310));
                H = e,
                    e = {
                        memoizedState: H.memoizedState,
                        baseState: H.baseState,
                        baseQueue: H.baseQueue,
                        queue: H.queue,
                        next: null
                    },
                    wo === null ? V.memoizedState = wo = e : wo = wo.next = e
            }
            return wo
        }
        function Uo() {
            return {
                lastEffect: null,
                events: null,
                stores: null,
                memoCache: null
            }
        }
        function Wo(e) {
            var t = ko;
            return ko += 1,
                Ao === null && (Ao = []),
                e = La(Ao, e, t),
                t = V,
                (wo === null ? t.memoizedState : wo.next) === null && (t = t.alternate,
                    k.H = t === null || t.memoizedState === null ? $s : ec),
                e
        }
        function Go(e) {
            if (typeof e == `object` && e) {
                if (typeof e.then == `function`)
                    return Wo(e);
                if (e.$$typeof === C)
                    return da(e)
            }
            throw Error(i(438, String(e)))
        }
        function Ko(e) {
            var t = null
                , n = V.updateQueue;
            if (n !== null && (t = n.memoCache),
                t == null) {
                var r = V.alternate;
                r !== null && (r = r.updateQueue,
                    r !== null && (r = r.memoCache,
                        r != null && (t = {
                            data: r.data.map(function (e) {
                                return e.slice()
                            }),
                            index: 0
                        })))
            }
            if (t ??= {
                data: [],
                index: 0
            },
                n === null && (n = Uo(),
                    V.updateQueue = n),
                n.memoCache = t,
                n = t.data[t.index],
                n === void 0)
                for (n = t.data[t.index] = Array(e),
                    r = 0; r < e; r++)
                    n[r] = te;
            return t.index++,
                n
        }
        function qo(e, t) {
            return typeof t == `function` ? t(e) : t
        }
        function Jo(e) {
            var t = Ho();
            return Yo(t, H, e)
        }
        function Yo(e, t, n) {
            var r = e.queue;
            if (r === null)
                throw Error(i(311));
            r.lastRenderedReducer = n;
            var a = e.baseQueue
                , o = r.pending;
            if (o !== null) {
                if (a !== null) {
                    var s = a.next;
                    a.next = o.next,
                        o.next = s
                }
                t.baseQueue = a = o,
                    r.pending = null
            }
            if (o = e.baseState,
                a === null)
                e.memoizedState = o;
            else {
                t = a.next;
                var c = s = null
                    , l = null
                    , u = t
                    , d = !1;
                do {
                    var f = u.lane & -536870913;
                    if (f === u.lane ? (Co & f) === f : (J & f) === f) {
                        var p = u.revertLane;
                        if (p === 0)
                            l !== null && (l = l.next = {
                                lane: 0,
                                revertLane: 0,
                                gesture: null,
                                action: u.action,
                                hasEagerState: u.hasEagerState,
                                eagerState: u.eagerState,
                                next: null
                            }),
                                f === Sa && (d = !0);
                        else if ((Co & p) === p) {
                            u = u.next,
                                p === Sa && (d = !0);
                            continue
                        } else
                            f = {
                                lane: 0,
                                revertLane: u.revertLane,
                                gesture: null,
                                action: u.action,
                                hasEagerState: u.hasEagerState,
                                eagerState: u.eagerState,
                                next: null
                            },
                                l === null ? (c = l = f,
                                    s = o) : l = l.next = f,
                                V.lanes |= p,
                                iu |= p;
                        f = u.action,
                            Do && n(o, f),
                            o = u.hasEagerState ? u.eagerState : n(o, f)
                    } else
                        p = {
                            lane: f,
                            revertLane: u.revertLane,
                            gesture: u.gesture,
                            action: u.action,
                            hasEagerState: u.hasEagerState,
                            eagerState: u.eagerState,
                            next: null
                        },
                            l === null ? (c = l = p,
                                s = o) : l = l.next = p,
                            V.lanes |= f,
                            iu |= f;
                    u = u.next
                } while (u !== null && u !== t);
                if (l === null ? s = o : l.next = c,
                    !Pr(o, e.memoizedState) && (_c = !0,
                        d && (n = Ca,
                            n !== null)))
                    throw n;
                e.memoizedState = o,
                    e.baseState = s,
                    e.baseQueue = l,
                    r.lastRenderedState = o
            }
            return a === null && (r.lanes = 0),
                [e.memoizedState, r.dispatch]
        }
        function Xo(e) {
            var t = Ho()
                , n = t.queue;
            if (n === null)
                throw Error(i(311));
            n.lastRenderedReducer = e;
            var r = n.dispatch
                , a = n.pending
                , o = t.memoizedState;
            if (a !== null) {
                n.pending = null;
                var s = a = a.next;
                do
                    o = e(o, s.action),
                        s = s.next;
                while (s !== a);
                Pr(o, t.memoizedState) || (_c = !0),
                    t.memoizedState = o,
                    t.baseQueue === null && (t.baseState = o),
                    n.lastRenderedState = o
            }
            return [o, r]
        }
        function Zo(e, t, n) {
            var r = V
                , a = Ho()
                , o = B;
            if (o) {
                if (n === void 0)
                    throw Error(i(407));
                n = n()
            } else
                n = t();
            var s = !Pr((H || a).memoizedState, n);
            if (s && (a.memoizedState = n,
                _c = !0),
                a = a.queue,
                ws(es.bind(null, r, a, e), [e]),
                a.getSnapshot !== t || s || wo !== null && wo.memoizedState.tag & 1) {
                if (r.flags |= 2048,
                    ys(9, {
                        destroy: void 0
                    }, $o.bind(null, r, a, n, t), null),
                    K === null)
                    throw Error(i(349));
                o || Co & 127 || Qo(r, t, n)
            }
            return n
        }
        function Qo(e, t, n) {
            e.flags |= 16384,
                e = {
                    getSnapshot: t,
                    value: n
                },
                t = V.updateQueue,
                t === null ? (t = Uo(),
                    V.updateQueue = t,
                    t.stores = [e]) : (n = t.stores,
                        n === null ? t.stores = [e] : n.push(e))
        }
        function $o(e, t, n, r) {
            t.value = n,
                t.getSnapshot = r,
                ts(t) && ns(e)
        }
        function es(e, t, n) {
            return n(function () {
                ts(t) && ns(e)
            })
        }
        function ts(e) {
            var t = e.getSnapshot;
            e = e.value;
            try {
                var n = t();
                return !Pr(e, n)
            } catch {
                return !0
            }
        }
        function ns(e) {
            var t = mi(e, 2);
            t !== null && Ou(t, e, 2)
        }
        function rs(e) {
            var t = Vo();
            if (typeof e == `function`) {
                var n = e;
                if (e = n(),
                    Do) {
                    ze(!0);
                    try {
                        n()
                    } finally {
                        ze(!1)
                    }
                }
            }
            return t.memoizedState = t.baseState = e,
                t.queue = {
                    pending: null,
                    lanes: 0,
                    dispatch: null,
                    lastRenderedReducer: qo,
                    lastRenderedState: e
                },
                t
        }
        function os(e, t, n, r) {
            return e.baseState = n,
                Yo(e, H, typeof r == `function` ? r : qo)
        }
        function ss(e, t, n, r, a) {
            if (Ys(e))
                throw Error(i(485));
            if (e = t.action,
                e !== null) {
                var o = {
                    payload: a,
                    action: e,
                    next: null,
                    isTransition: !0,
                    status: `pending`,
                    value: null,
                    reason: null,
                    listeners: [],
                    then: function (e) {
                        o.listeners.push(e)
                    }
                };
                k.T === null ? o.isTransition = !1 : n(!0),
                    r(o),
                    n = t.pending,
                    n === null ? (o.next = t.pending = o,
                        cs(t, o)) : (o.next = n.next,
                            t.pending = n.next = o)
            }
        }
        function cs(e, t) {
            var n = t.action
                , r = t.payload
                , i = e.state;
            if (t.isTransition) {
                var a = k.T
                    , o = {};
                k.T = o;
                try {
                    var s = n(i, r)
                        , c = k.S;
                    c !== null && c(o, s),
                        ls(e, t, s)
                } catch (n) {
                    ds(e, t, n)
                } finally {
                    a !== null && o.types !== null && (a.types = o.types),
                        k.T = a
                }
            } else
                try {
                    a = n(i, r),
                        ls(e, t, a)
                } catch (n) {
                    ds(e, t, n)
                }
        }
        function ls(e, t, n) {
            typeof n == `object` && n && typeof n.then == `function` ? n.then(function (n) {
                us(e, t, n)
            }, function (n) {
                return ds(e, t, n)
            }) : us(e, t, n)
        }
        function us(e, t, n) {
            t.status = `fulfilled`,
                t.value = n,
                fs(t),
                e.state = n,
                t = e.pending,
                t !== null && (n = t.next,
                    n === t ? e.pending = null : (n = n.next,
                        t.next = n,
                        cs(e, n)))
        }
        function ds(e, t, n) {
            var r = e.pending;
            if (e.pending = null,
                r !== null) {
                r = r.next;
                do
                    t.status = `rejected`,
                        t.reason = n,
                        fs(t),
                        t = t.next;
                while (t !== r)
            }
            e.action = null
        }
        function fs(e) {
            e = e.listeners;
            for (var t = 0; t < e.length; t++)
                (0,
                    e[t])()
        }
        function ps(e, t) {
            return t
        }
        function ms(e, t) {
            if (B) {
                var n = K.formState;
                if (n !== null) {
                    a: {
                        var r = V;
                        if (B) {
                            if (z) {
                                b: {
                                    for (var i = z, a = Ki; i.nodeType !== 8;) {
                                        if (!a) {
                                            i = null;
                                            break b
                                        }
                                        if (i = wf(i.nextSibling),
                                            i === null) {
                                            i = null;
                                            break b
                                        }
                                    }
                                    a = i.data,
                                        i = a === `F!` || a === `F` ? i : null
                                }
                                if (i) {
                                    z = wf(i.nextSibling),
                                        r = i.data === `F!`;
                                    break a
                                }
                            }
                            Ji(r)
                        }
                        r = !1
                    }
                    r && (t = n[0])
                }
            }
            return n = Vo(),
                n.memoizedState = n.baseState = t,
                r = {
                    pending: null,
                    lanes: 0,
                    dispatch: null,
                    lastRenderedReducer: ps,
                    lastRenderedState: t
                },
                n.queue = r,
                n = Ks.bind(null, V, r),
                r.dispatch = n,
                r = rs(!1),
                a = Js.bind(null, V, !1, r.queue),
                r = Vo(),
                i = {
                    state: t,
                    dispatch: null,
                    action: e,
                    pending: null
                },
                r.queue = i,
                n = ss.bind(null, V, i, a, n),
                i.dispatch = n,
                r.memoizedState = e,
                [t, n, !1]
        }
        function hs(e) {
            var t = Ho();
            return gs(t, H, e)
        }
        function gs(e, t, n) {
            if (t = Yo(e, t, ps)[0],
                e = Jo(qo)[0],
                typeof t == `object` && t && typeof t.then == `function`)
                try {
                    var r = Wo(t)
                } catch (e) {
                    throw e === Ma ? Pa : e
                }
            else
                r = t;
            t = Ho();
            var i = t.queue
                , a = i.dispatch;
            return n !== t.memoizedState && (V.flags |= 2048,
                ys(9, {
                    destroy: void 0
                }, _s.bind(null, i, n), null)),
                [r, a, e]
        }
        function _s(e, t) {
            e.action = t
        }
        function vs(e) {
            var t = Ho()
                , n = H;
            if (n !== null)
                return gs(t, n, e);
            Ho(),
                t = t.memoizedState,
                n = Ho();
            var r = n.queue.dispatch;
            return n.memoizedState = e,
                [t, r, !1]
        }
        function ys(e, t, n, r) {
            return e = {
                tag: e,
                create: n,
                deps: r,
                inst: t,
                next: null
            },
                t = V.updateQueue,
                t === null && (t = Uo(),
                    V.updateQueue = t),
                n = t.lastEffect,
                n === null ? t.lastEffect = e.next = e : (r = n.next,
                    n.next = e,
                    e.next = r,
                    t.lastEffect = e),
                e
        }
        function bs() {
            return Ho().memoizedState
        }
        function xs(e, t, n, r) {
            var i = Vo();
            V.flags |= e,
                i.memoizedState = ys(1 | t, {
                    destroy: void 0
                }, n, r === void 0 ? null : r)
        }
        function Ss(e, t, n, r) {
            var i = Ho();
            r = r === void 0 ? null : r;
            var a = i.memoizedState.inst;
            H !== null && r !== null && No(r, H.memoizedState.deps) ? i.memoizedState = ys(t, a, n, r) : (V.flags |= e,
                i.memoizedState = ys(1 | t, a, n, r))
        }
        function Cs(e, t) {
            xs(8390656, 8, e, t)
        }
        function ws(e, t) {
            Ss(2048, 8, e, t)
        }
        function Ts(e) {
            V.flags |= 4;
            var t = V.updateQueue;
            if (t === null)
                t = Uo(),
                    V.updateQueue = t,
                    t.events = [e];
            else {
                var n = t.events;
                n === null ? t.events = [e] : n.push(e)
            }
        }
        function Es(e) {
            var t = Ho().memoizedState;
            return Ts({
                ref: t,
                nextImpl: e
            }),
                function () {
                    if (G & 2)
                        throw Error(i(440));
                    return t.impl.apply(void 0, arguments)
                }
        }
        function Ds(e, t) {
            return Ss(4, 2, e, t)
        }
        function Os(e, t) {
            return Ss(4, 4, e, t)
        }
        function ks(e, t) {
            if (typeof t == `function`) {
                e = e();
                var n = t(e);
                return function () {
                    typeof n == `function` ? n() : t(null)
                }
            }
            if (t != null)
                return e = e(),
                    t.current = e,
                    function () {
                        t.current = null
                    }
        }
        function As(e, t, n) {
            n = n == null ? null : n.concat([e]),
                Ss(4, 4, ks.bind(null, t, e), n)
        }
        function js() { }
        function Ms(e, t) {
            var n = Ho();
            t = t === void 0 ? null : t;
            var r = n.memoizedState;
            return t !== null && No(t, r[1]) ? r[0] : (n.memoizedState = [e, t],
                e)
        }
        function Ns(e, t) {
            var n = Ho();
            t = t === void 0 ? null : t;
            var r = n.memoizedState;
            if (t !== null && No(t, r[1]))
                return r[0];
            if (r = e(),
                Do) {
                ze(!0);
                try {
                    e()
                } finally {
                    ze(!1)
                }
            }
            return n.memoizedState = [r, t],
                r
        }
        function Ps(e, t, n) {
            return n === void 0 || Co & 1073741824 && !(J & 261930) ? e.memoizedState = t : (e.memoizedState = n,
                e = Du(),
                V.lanes |= e,
                iu |= e,
                n)
        }
        function Fs(e, t, n, r) {
            return Pr(n, t) ? n : co.current === null ? !(Co & 42) || Co & 1073741824 && !(J & 261930) ? (_c = !0,
                e.memoizedState = n) : (e = Du(),
                    V.lanes |= e,
                    iu |= e,
                    t) : (e = Ps(e, n, r),
                        Pr(e, t) || (_c = !0),
                        e)
        }
        function Is(e, t, n, r, i) {
            var a = A.p;
            A.p = a !== 0 && 8 > a ? a : 8;
            var o = k.T
                , s = {};
            k.T = s,
                Js(e, !1, t, n);
            try {
                var c = i()
                    , l = k.S;
                if (l !== null && l(s, c),
                    typeof c == `object` && c && typeof c.then == `function`) {
                    var u = Ea(c, r);
                    qs(e, t, u, Eu(e))
                } else
                    qs(e, t, r, Eu(e))
            } catch (n) {
                qs(e, t, {
                    then: function () { },
                    status: `rejected`,
                    reason: n
                }, Eu())
            } finally {
                A.p = a,
                    o !== null && s.types !== null && (o.types = s.types),
                    k.T = o
            }
        }
        function Ls() { }
        function Rs(e, t, n, r) {
            if (e.tag !== 5)
                throw Error(i(476));
            var a = zs(e).queue;
            Is(e, a, t, se, n === null ? Ls : function () {
                return Bs(e),
                    n(r)
            }
            )
        }
        function zs(e) {
            var t = e.memoizedState;
            if (t !== null)
                return t;
            t = {
                memoizedState: se,
                baseState: se,
                baseQueue: null,
                queue: {
                    pending: null,
                    lanes: 0,
                    dispatch: null,
                    lastRenderedReducer: qo,
                    lastRenderedState: se
                },
                next: null
            };
            var n = {};
            return t.next = {
                memoizedState: n,
                baseState: n,
                baseQueue: null,
                queue: {
                    pending: null,
                    lanes: 0,
                    dispatch: null,
                    lastRenderedReducer: qo,
                    lastRenderedState: n
                },
                next: null
            },
                e.memoizedState = t,
                e = e.alternate,
                e !== null && (e.memoizedState = t),
                t
        }
        function Bs(e) {
            var t = zs(e);
            t.next === null && (t = e.alternate.memoizedState),
                qs(e, t.next.queue, {}, Eu())
        }
        function Vs() {
            return da(mp)
        }
        function Hs() {
            return Ho().memoizedState
        }
        function Us() {
            return Ho().memoizedState
        }
        function Ws(e) {
            for (var t = e.return; t !== null;) {
                switch (t.tag) {
                    case 24:
                    case 3:
                        var n = Eu();
                        e = $a(n);
                        var r = eo(t, e, n);
                        r !== null && (Ou(r, t, n),
                            to(r, t, n)),
                            t = {
                                cache: va()
                            },
                            e.payload = t;
                        return
                }
                t = t.return
            }
        }
        function Gs(e, t, n) {
            var r = Eu();
            n = {
                lane: r,
                revertLane: 0,
                gesture: null,
                action: n,
                hasEagerState: !1,
                eagerState: null,
                next: null
            },
                Ys(e) ? Xs(t, n) : (n = pi(e, t, n, r),
                    n !== null && (Ou(n, e, r),
                        Zs(n, t, r)))
        }
        function Ks(e, t, n) {
            var r = Eu();
            qs(e, t, n, r)
        }
        function qs(e, t, n, r) {
            var i = {
                lane: r,
                revertLane: 0,
                gesture: null,
                action: n,
                hasEagerState: !1,
                eagerState: null,
                next: null
            };
            if (Ys(e))
                Xs(t, i);
            else {
                var a = e.alternate;
                if (e.lanes === 0 && (a === null || a.lanes === 0) && (a = t.lastRenderedReducer,
                    a !== null))
                    try {
                        var o = t.lastRenderedState
                            , s = a(o, n);
                        if (i.hasEagerState = !0,
                            i.eagerState = s,
                            Pr(s, o))
                            return fi(e, t, i, 0),
                                K === null && di(),
                                !1
                    } catch { }
                if (n = pi(e, t, i, r),
                    n !== null)
                    return Ou(n, e, r),
                        Zs(n, t, r),
                        !0
            }
            return !1
        }
        function Js(e, t, n, r) {
            if (r = {
                lane: 2,
                revertLane: wd(),
                gesture: null,
                action: r,
                hasEagerState: !1,
                eagerState: null,
                next: null
            },
                Ys(e)) {
                if (t)
                    throw Error(i(479))
            } else
                t = pi(e, n, r, 2),
                    t !== null && Ou(t, e, 2)
        }
        function Ys(e) {
            var t = e.alternate;
            return e === V || t !== null && t === V
        }
        function Xs(e, t) {
            Eo = To = !0;
            var n = e.pending;
            n === null ? t.next = t : (t.next = n.next,
                n.next = t),
                e.pending = t
        }
        function Zs(e, t, n) {
            if (n & 4194048) {
                var r = t.lanes;
                r &= e.pendingLanes,
                    n |= r,
                    t.lanes = n,
                    nt(e, n)
            }
        }
        var Qs = {
            readContext: da,
            use: Go,
            useCallback: Mo,
            useContext: Mo,
            useEffect: Mo,
            useImperativeHandle: Mo,
            useLayoutEffect: Mo,
            useInsertionEffect: Mo,
            useMemo: Mo,
            useReducer: Mo,
            useRef: Mo,
            useState: Mo,
            useDebugValue: Mo,
            useDeferredValue: Mo,
            useTransition: Mo,
            useSyncExternalStore: Mo,
            useId: Mo,
            useHostTransitionStatus: Mo,
            useFormState: Mo,
            useActionState: Mo,
            useOptimistic: Mo,
            useMemoCache: Mo,
            useCacheRefresh: Mo
        };
        Qs.useEffectEvent = Mo;
        var $s = {
            readContext: da,
            use: Go,
            useCallback: function (e, t) {
                return Vo().memoizedState = [e, t === void 0 ? null : t],
                    e
            },
            useContext: da,
            useEffect: Cs,
            useImperativeHandle: function (e, t, n) {
                n = n == null ? null : n.concat([e]),
                    xs(4194308, 4, ks.bind(null, t, e), n)
            },
            useLayoutEffect: function (e, t) {
                return xs(4194308, 4, e, t)
            },
            useInsertionEffect: function (e, t) {
                xs(4, 2, e, t)
            },
            useMemo: function (e, t) {
                var n = Vo();
                t = t === void 0 ? null : t;
                var r = e();
                if (Do) {
                    ze(!0);
                    try {
                        e()
                    } finally {
                        ze(!1)
                    }
                }
                return n.memoizedState = [r, t],
                    r
            },
            useReducer: function (e, t, n) {
                var r = Vo();
                if (n !== void 0) {
                    var i = n(t);
                    if (Do) {
                        ze(!0);
                        try {
                            n(t)
                        } finally {
                            ze(!1)
                        }
                    }
                } else
                    i = t;
                return r.memoizedState = r.baseState = i,
                    e = {
                        pending: null,
                        lanes: 0,
                        dispatch: null,
                        lastRenderedReducer: e,
                        lastRenderedState: i
                    },
                    r.queue = e,
                    e = e.dispatch = Gs.bind(null, V, e),
                    [r.memoizedState, e]
            },
            useRef: function (e) {
                var t = Vo();
                return e = {
                    current: e
                },
                    t.memoizedState = e
            },
            useState: function (e) {
                e = rs(e);
                var t = e.queue
                    , n = Ks.bind(null, V, t);
                return t.dispatch = n,
                    [e.memoizedState, n]
            },
            useDebugValue: js,
            useDeferredValue: function (e, t) {
                var n = Vo();
                return Ps(n, e, t)
            },
            useTransition: function () {
                var e = rs(!1);
                return e = Is.bind(null, V, e.queue, !0, !1),
                    Vo().memoizedState = e,
                    [!1, e]
            },
            useSyncExternalStore: function (e, t, n) {
                var r = V
                    , a = Vo();
                if (B) {
                    if (n === void 0)
                        throw Error(i(407));
                    n = n()
                } else {
                    if (n = t(),
                        K === null)
                        throw Error(i(349));
                    J & 127 || Qo(r, t, n)
                }
                a.memoizedState = n;
                var o = {
                    value: n,
                    getSnapshot: t
                };
                return a.queue = o,
                    Cs(es.bind(null, r, o, e), [e]),
                    r.flags |= 2048,
                    ys(9, {
                        destroy: void 0
                    }, $o.bind(null, r, o, n, t), null),
                    n
            },
            useId: function () {
                var e = Vo()
                    , t = K.identifierPrefix;
                if (B) {
                    var n = Ri
                        , r = Li;
                    n = (r & ~(1 << 32 - Be(r) - 1)).toString(32) + n,
                        t = `_` + t + `R_` + n,
                        n = Oo++,
                        0 < n && (t += `H` + n.toString(32)),
                        t += `_`
                } else
                    n = jo++,
                        t = `_` + t + `r_` + n.toString(32) + `_`;
                return e.memoizedState = t
            },
            useHostTransitionStatus: Vs,
            useFormState: ms,
            useActionState: ms,
            useOptimistic: function (e) {
                var t = Vo();
                t.memoizedState = t.baseState = e;
                var n = {
                    pending: null,
                    lanes: 0,
                    dispatch: null,
                    lastRenderedReducer: null,
                    lastRenderedState: null
                };
                return t.queue = n,
                    t = Js.bind(null, V, !0, n),
                    n.dispatch = t,
                    [e, t]
            },
            useMemoCache: Ko,
            useCacheRefresh: function () {
                return Vo().memoizedState = Ws.bind(null, V)
            },
            useEffectEvent: function (e) {
                var t = Vo()
                    , n = {
                        impl: e
                    };
                return t.memoizedState = n,
                    function () {
                        if (G & 2)
                            throw Error(i(440));
                        return n.impl.apply(void 0, arguments)
                    }
            }
        }
            , ec = {
                readContext: da,
                use: Go,
                useCallback: Ms,
                useContext: da,
                useEffect: ws,
                useImperativeHandle: As,
                useInsertionEffect: Ds,
                useLayoutEffect: Os,
                useMemo: Ns,
                useReducer: Jo,
                useRef: bs,
                useState: function () {
                    return Jo(qo)
                },
                useDebugValue: js,
                useDeferredValue: function (e, t) {
                    var n = Ho();
                    return Fs(n, H.memoizedState, e, t)
                },
                useTransition: function () {
                    var e = Jo(qo)[0]
                        , t = Ho().memoizedState;
                    return [typeof e == `boolean` ? e : Wo(e), t]
                },
                useSyncExternalStore: Zo,
                useId: Hs,
                useHostTransitionStatus: Vs,
                useFormState: hs,
                useActionState: hs,
                useOptimistic: function (e, t) {
                    var n = Ho();
                    return os(n, H, e, t)
                },
                useMemoCache: Ko,
                useCacheRefresh: Us
            };
        ec.useEffectEvent = Es;
        var tc = {
            readContext: da,
            use: Go,
            useCallback: Ms,
            useContext: da,
            useEffect: ws,
            useImperativeHandle: As,
            useInsertionEffect: Ds,
            useLayoutEffect: Os,
            useMemo: Ns,
            useReducer: Xo,
            useRef: bs,
            useState: function () {
                return Xo(qo)
            },
            useDebugValue: js,
            useDeferredValue: function (e, t) {
                var n = Ho();
                return H === null ? Ps(n, e, t) : Fs(n, H.memoizedState, e, t)
            },
            useTransition: function () {
                var e = Xo(qo)[0]
                    , t = Ho().memoizedState;
                return [typeof e == `boolean` ? e : Wo(e), t]
            },
            useSyncExternalStore: Zo,
            useId: Hs,
            useHostTransitionStatus: Vs,
            useFormState: vs,
            useActionState: vs,
            useOptimistic: function (e, t) {
                var n = Ho();
                return H === null ? (n.baseState = e,
                    [e, n.queue.dispatch]) : os(n, H, e, t)
            },
            useMemoCache: Ko,
            useCacheRefresh: Us
        };
        tc.useEffectEvent = Es;
        function nc(e, t, n, r) {
            t = e.memoizedState,
                n = n(r, t),
                n = n == null ? t : h({}, t, n),
                e.memoizedState = n,
                e.lanes === 0 && (e.updateQueue.baseState = n)
        }
        var rc = {
            enqueueSetState: function (e, t, n) {
                e = e._reactInternals;
                var r = Eu()
                    , i = $a(r);
                i.payload = t,
                    n != null && (i.callback = n),
                    t = eo(e, i, r),
                    t !== null && (Ou(t, e, r),
                        to(t, e, r))
            },
            enqueueReplaceState: function (e, t, n) {
                e = e._reactInternals;
                var r = Eu()
                    , i = $a(r);
                i.tag = 1,
                    i.payload = t,
                    n != null && (i.callback = n),
                    t = eo(e, i, r),
                    t !== null && (Ou(t, e, r),
                        to(t, e, r))
            },
            enqueueForceUpdate: function (e, t) {
                e = e._reactInternals;
                var n = Eu()
                    , r = $a(n);
                r.tag = 2,
                    t != null && (r.callback = t),
                    t = eo(e, r, n),
                    t !== null && (Ou(t, e, n),
                        to(t, e, n))
            }
        };
        function ic(e, t, n, r, i, a, o) {
            return e = e.stateNode,
                typeof e.shouldComponentUpdate == `function` ? e.shouldComponentUpdate(r, a, o) : t.prototype && t.prototype.isPureReactComponent ? !Fr(n, r) || !Fr(i, a) : !0
        }
        function ac(e, t, n, r) {
            e = t.state,
                typeof t.componentWillReceiveProps == `function` && t.componentWillReceiveProps(n, r),
                typeof t.UNSAFE_componentWillReceiveProps == `function` && t.UNSAFE_componentWillReceiveProps(n, r),
                t.state !== e && rc.enqueueReplaceState(t, t.state, null)
        }
        function oc(e, t) {
            var n = t;
            if (`ref` in t)
                for (var r in n = {},
                    t)
                    r !== `ref` && (n[r] = t[r]);
            if (e = e.defaultProps)
                for (var i in n === t && (n = h({}, n)),
                    e)
                    n[i] === void 0 && (n[i] = e[i]);
            return n
        }
        function sc(e) {
            si(e)
        }
        function cc(e) {
            console.error(e)
        }
        function lc(e) {
            si(e)
        }
        function uc(e, t) {
            try {
                var n = e.onUncaughtError;
                n(t.value, {
                    componentStack: t.stack
                })
            } catch (e) {
                setTimeout(function () {
                    throw e
                })
            }
        }
        function dc(e, t, n) {
            try {
                var r = e.onCaughtError;
                r(n.value, {
                    componentStack: n.stack,
                    errorBoundary: t.tag === 1 ? t.stateNode : null
                })
            } catch (e) {
                setTimeout(function () {
                    throw e
                })
            }
        }
        function fc(e, t, n) {
            return n = $a(n),
                n.tag = 3,
                n.payload = {
                    element: null
                },
                n.callback = function () {
                    uc(e, t)
                }
                ,
                n
        }
        function pc(e) {
            return e = $a(e),
                e.tag = 3,
                e
        }
        function mc(e, t, n, r) {
            var i = n.type.getDerivedStateFromError;
            if (typeof i == `function`) {
                var a = r.value;
                e.payload = function () {
                    return i(a)
                }
                    ,
                    e.callback = function () {
                        dc(t, n, r)
                    }
            }
            var o = n.stateNode;
            o !== null && typeof o.componentDidCatch == `function` && (e.callback = function () {
                dc(t, n, r),
                    typeof i != `function` && (gu === null ? gu = new Set([this]) : gu.add(this));
                var e = r.stack;
                this.componentDidCatch(r.value, {
                    componentStack: e === null ? `` : e
                })
            }
            )
        }
        function hc(e, t, n, r, a) {
            if (n.flags |= 32768,
                typeof r == `object` && r && typeof r.then == `function`) {
                if (t = n.alternate,
                    t !== null && ca(t, n, a, !0),
                    n = mo.current,
                    n !== null) {
                    switch (n.tag) {
                        case 31:
                        case 13:
                            return ho === null ? Bu() : n.alternate === null && X === 0 && (X = 3),
                                n.flags &= -257,
                                n.flags |= 65536,
                                n.lanes = a,
                                r === Fa ? n.flags |= 16384 : (t = n.updateQueue,
                                    t === null ? n.updateQueue = new Set([r]) : t.add(r),
                                    id(e, r, a)),
                                !1;
                        case 22:
                            return n.flags |= 65536,
                                r === Fa ? n.flags |= 16384 : (t = n.updateQueue,
                                    t === null ? (t = {
                                        transitions: null,
                                        markerInstances: null,
                                        retryQueue: new Set([r])
                                    },
                                        n.updateQueue = t) : (n = t.retryQueue,
                                            n === null ? t.retryQueue = new Set([r]) : n.add(r)),
                                    id(e, r, a)),
                                !1
                    }
                    throw Error(i(435, n.tag))
                }
                return id(e, r, a),
                    Bu(),
                    !1
            }
            if (B)
                return t = mo.current,
                    t === null ? (r !== qi && (t = Error(i(423), {
                        cause: r
                    }),
                        ea(ki(t, n))),
                        e = e.current.alternate,
                        e.flags |= 65536,
                        a &= -a,
                        e.lanes |= a,
                        r = ki(r, n),
                        a = fc(e.stateNode, r, a),
                        no(e, a),
                        X !== 4 && (X = 2)) : (!(t.flags & 65536) && (t.flags |= 256),
                            t.flags |= 65536,
                            t.lanes = a,
                            r !== qi && (e = Error(i(422), {
                                cause: r
                            }),
                                ea(ki(e, n)))),
                    !1;
            var o = Error(i(520), {
                cause: r
            });
            if (o = ki(o, n),
                lu === null ? lu = [o] : lu.push(o),
                X !== 4 && (X = 2),
                t === null)
                return !0;
            r = ki(r, n),
                n = t;
            do {
                switch (n.tag) {
                    case 3:
                        return n.flags |= 65536,
                            e = a & -a,
                            n.lanes |= e,
                            e = fc(n.stateNode, r, e),
                            no(n, e),
                            !1;
                    case 1:
                        if (t = n.type,
                            o = n.stateNode,
                            !(n.flags & 128) && (typeof t.getDerivedStateFromError == `function` || o !== null && typeof o.componentDidCatch == `function` && (gu === null || !gu.has(o))))
                            return n.flags |= 65536,
                                a &= -a,
                                n.lanes |= a,
                                a = pc(a),
                                mc(a, e, n, r),
                                no(n, a),
                                !1
                }
                n = n.return
            } while (n !== null);
            return !1
        }
        var gc = Error(i(461))
            , _c = !1;
        function vc(e, t, n, r) {
            t.child = e === null ? Ya(t, null, n, r) : Ja(t, e.child, n, r)
        }
        function yc(e, t, n, r, i) {
            n = n.render;
            var a = t.ref;
            if (`ref` in r) {
                var o = {};
                for (var s in r)
                    s !== `ref` && (o[s] = r[s])
            } else
                o = r;
            return ua(t),
                r = Po(e, t, n, o, a, i),
                s = Ro(),
                e !== null && !_c ? (zo(e, t, i),
                    Uc(e, t, i)) : (B && s && Vi(t),
                        t.flags |= 1,
                        vc(e, t, r, i),
                        t.child)
        }
        function bc(e, t, n, r, i) {
            if (e === null) {
                var a = n.type;
                return typeof a == `function` && !bi(a) && a.defaultProps === void 0 && n.compare === null ? (t.tag = 15,
                    t.type = a,
                    xc(e, t, a, r, i)) : (e = Ci(n.type, null, r, t, t.mode, i),
                        e.ref = t.ref,
                        e.return = t,
                        t.child = e)
            }
            if (a = e.child,
                !Wc(e, i)) {
                var o = a.memoizedProps;
                if (n = n.compare,
                    n = n === null ? Fr : n,
                    n(o, r) && e.ref === t.ref)
                    return Uc(e, t, i)
            }
            return t.flags |= 1,
                e = xi(a, r),
                e.ref = t.ref,
                e.return = t,
                t.child = e
        }
        function xc(e, t, n, r, i) {
            if (e !== null) {
                var a = e.memoizedProps;
                if (Fr(a, r) && e.ref === t.ref)
                    if (_c = !1,
                        t.pendingProps = r = a,
                        Wc(e, i))
                        e.flags & 131072 && (_c = !0);
                    else
                        return t.lanes = e.lanes,
                            Uc(e, t, i)
            }
            return kc(e, t, n, r, i)
        }
        function Sc(e, t, n, r) {
            var i = r.children
                , a = e === null ? null : e.memoizedState;
            if (e === null && t.stateNode === null && (t.stateNode = {
                _visibility: 1,
                _pendingMarkers: null,
                _retryCache: null,
                _transitions: null
            }),
                r.mode === `hidden`) {
                if (t.flags & 128) {
                    if (a = a === null ? n : a.baseLanes | n,
                        e !== null) {
                        for (r = t.child = e.child,
                            i = 0; r !== null;)
                            i = i | r.lanes | r.childLanes,
                                r = r.sibling;
                        r = i & ~a
                    } else
                        r = 0,
                            t.child = null;
                    return wc(e, t, a, n, r)
                }
                if (n & 536870912)
                    t.memoizedState = {
                        baseLanes: 0,
                        cachePool: null
                    },
                        e !== null && Aa(t, a === null ? null : a.cachePool),
                        a === null ? fo() : uo(t, a),
                        vo(t);
                else
                    return r = t.lanes = 536870912,
                        wc(e, t, a === null ? n : a.baseLanes | n, n, r)
            } else
                a === null ? (e !== null && Aa(t, null),
                    fo(),
                    yo(t)) : (Aa(t, a.cachePool),
                        uo(t, a),
                        yo(t),
                        t.memoizedState = null);
            return vc(e, t, i, n),
                t.child
        }
        function Cc(e, t) {
            return e !== null && e.tag === 22 || t.stateNode !== null || (t.stateNode = {
                _visibility: 1,
                _pendingMarkers: null,
                _retryCache: null,
                _transitions: null
            }),
                t.sibling
        }
        function wc(e, t, n, r, i) {
            var a = ka();
            return a = a === null ? null : {
                parent: _a._currentValue,
                pool: a
            },
                t.memoizedState = {
                    baseLanes: n,
                    cachePool: a
                },
                e !== null && Aa(t, null),
                fo(),
                vo(t),
                e !== null && ca(e, t, r, !0),
                t.childLanes = i,
                null
        }
        function Tc(e, t) {
            return t = Rc({
                mode: t.mode,
                children: t.children
            }, e.mode),
                t.ref = e.ref,
                e.child = t,
                t.return = e,
                t
        }
        function Ec(e, t, n) {
            return Ja(t, e.child, null, n),
                e = Tc(t, t.pendingProps),
                e.flags |= 2,
                bo(t),
                t.memoizedState = null,
                e
        }
        function Dc(e, t, n) {
            var r = t.pendingProps
                , a = (t.flags & 128) != 0;
            if (t.flags &= -129,
                e === null) {
                if (B) {
                    if (r.mode === `hidden`)
                        return e = Tc(t, r),
                            t.lanes = 536870912,
                            Cc(null, e);
                    if (_o(t),
                        (e = z) ? (e = bf(e, Ki),
                            e = e !== null && e.data === `&` ? e : null,
                            e !== null && (t.memoizedState = {
                                dehydrated: e,
                                treeContext: Ii === null ? null : {
                                    id: Li,
                                    overflow: Ri
                                },
                                retryLane: 536870912,
                                hydrationErrors: null
                            },
                                n = Ei(e),
                                n.return = t,
                                t.child = n,
                                Wi = t,
                                z = null)) : e = null,
                        e === null)
                        throw Ji(t);
                    return t.lanes = 536870912,
                        null
                }
                return Tc(t, r)
            }
            var o = e.memoizedState;
            if (o !== null) {
                var s = o.dehydrated;
                if (_o(t),
                    a)
                    if (t.flags & 256)
                        t.flags &= -257,
                            t = Ec(e, t, n);
                    else if (t.memoizedState !== null)
                        t.child = e.child,
                            t.flags |= 128,
                            t = null;
                    else
                        throw Error(i(558));
                else if (_c || ca(e, t, n, !1),
                    a = (n & e.childLanes) !== 0,
                    _c || a) {
                    if (r = K,
                        r !== null && (s = rt(r, n),
                            s !== 0 && s !== o.retryLane))
                        throw o.retryLane = s,
                        mi(e, s),
                        Ou(r, e, s),
                        gc;
                    Bu(),
                        t = Ec(e, t, n)
                } else
                    e = o.treeContext,
                        z = wf(s.nextSibling),
                        Wi = t,
                        B = !0,
                        Gi = null,
                        Ki = !1,
                        e !== null && Ui(t, e),
                        t = Tc(t, r),
                        t.flags |= 4096;
                return t
            }
            return e = xi(e.child, {
                mode: r.mode,
                children: r.children
            }),
                e.ref = t.ref,
                t.child = e,
                e.return = t,
                e
        }
        function Oc(e, t) {
            var n = t.ref;
            if (n === null)
                e !== null && e.ref !== null && (t.flags |= 4194816);
            else {
                if (typeof n != `function` && typeof n != `object`)
                    throw Error(i(284));
                (e === null || e.ref !== n) && (t.flags |= 4194816)
            }
        }
        function kc(e, t, n, r, i) {
            return ua(t),
                n = Po(e, t, n, r, void 0, i),
                r = Ro(),
                e !== null && !_c ? (zo(e, t, i),
                    Uc(e, t, i)) : (B && r && Vi(t),
                        t.flags |= 1,
                        vc(e, t, n, i),
                        t.child)
        }
        function Ac(e, t, n, r, i, a) {
            return ua(t),
                t.updateQueue = null,
                n = Io(t, r, n, i),
                Fo(e),
                r = Ro(),
                e !== null && !_c ? (zo(e, t, a),
                    Uc(e, t, a)) : (B && r && Vi(t),
                        t.flags |= 1,
                        vc(e, t, n, a),
                        t.child)
        }
        function jc(e, t, n, r, i) {
            if (ua(t),
                t.stateNode === null) {
                var a = _i
                    , o = n.contextType;
                typeof o == `object` && o && (a = da(o)),
                    a = new n(r, a),
                    t.memoizedState = a.state !== null && a.state !== void 0 ? a.state : null,
                    a.updater = rc,
                    t.stateNode = a,
                    a._reactInternals = t,
                    a = t.stateNode,
                    a.props = r,
                    a.state = t.memoizedState,
                    a.refs = {},
                    Za(t),
                    o = n.contextType,
                    a.context = typeof o == `object` && o ? da(o) : _i,
                    a.state = t.memoizedState,
                    o = n.getDerivedStateFromProps,
                    typeof o == `function` && (nc(t, n, o, r),
                        a.state = t.memoizedState),
                    typeof n.getDerivedStateFromProps == `function` || typeof a.getSnapshotBeforeUpdate == `function` || typeof a.UNSAFE_componentWillMount != `function` && typeof a.componentWillMount != `function` || (o = a.state,
                        typeof a.componentWillMount == `function` && a.componentWillMount(),
                        typeof a.UNSAFE_componentWillMount == `function` && a.UNSAFE_componentWillMount(),
                        o !== a.state && rc.enqueueReplaceState(a, a.state, null),
                        ao(t, r, a, i),
                        io(),
                        a.state = t.memoizedState),
                    typeof a.componentDidMount == `function` && (t.flags |= 4194308),
                    r = !0
            } else if (e === null) {
                a = t.stateNode;
                var s = t.memoizedProps
                    , c = oc(n, s);
                a.props = c;
                var l = a.context
                    , u = n.contextType;
                o = _i,
                    typeof u == `object` && u && (o = da(u));
                var d = n.getDerivedStateFromProps;
                u = typeof d == `function` || typeof a.getSnapshotBeforeUpdate == `function`,
                    s = t.pendingProps !== s,
                    u || typeof a.UNSAFE_componentWillReceiveProps != `function` && typeof a.componentWillReceiveProps != `function` || (s || l !== o) && ac(t, a, r, o),
                    Xa = !1;
                var f = t.memoizedState;
                a.state = f,
                    ao(t, r, a, i),
                    io(),
                    l = t.memoizedState,
                    s || f !== l || Xa ? (typeof d == `function` && (nc(t, n, d, r),
                        l = t.memoizedState),
                        (c = Xa || ic(t, n, c, r, f, l, o)) ? (u || typeof a.UNSAFE_componentWillMount != `function` && typeof a.componentWillMount != `function` || (typeof a.componentWillMount == `function` && a.componentWillMount(),
                            typeof a.UNSAFE_componentWillMount == `function` && a.UNSAFE_componentWillMount()),
                            typeof a.componentDidMount == `function` && (t.flags |= 4194308)) : (typeof a.componentDidMount == `function` && (t.flags |= 4194308),
                                t.memoizedProps = r,
                                t.memoizedState = l),
                        a.props = r,
                        a.state = l,
                        a.context = o,
                        r = c) : (typeof a.componentDidMount == `function` && (t.flags |= 4194308),
                            r = !1)
            } else {
                a = t.stateNode,
                    Qa(e, t),
                    o = t.memoizedProps,
                    u = oc(n, o),
                    a.props = u,
                    d = t.pendingProps,
                    f = a.context,
                    l = n.contextType,
                    c = _i,
                    typeof l == `object` && l && (c = da(l)),
                    s = n.getDerivedStateFromProps,
                    (l = typeof s == `function` || typeof a.getSnapshotBeforeUpdate == `function`) || typeof a.UNSAFE_componentWillReceiveProps != `function` && typeof a.componentWillReceiveProps != `function` || (o !== d || f !== c) && ac(t, a, r, c),
                    Xa = !1,
                    f = t.memoizedState,
                    a.state = f,
                    ao(t, r, a, i),
                    io();
                var p = t.memoizedState;
                o !== d || f !== p || Xa || e !== null && e.dependencies !== null && la(e.dependencies) ? (typeof s == `function` && (nc(t, n, s, r),
                    p = t.memoizedState),
                    (u = Xa || ic(t, n, u, r, f, p, c) || e !== null && e.dependencies !== null && la(e.dependencies)) ? (l || typeof a.UNSAFE_componentWillUpdate != `function` && typeof a.componentWillUpdate != `function` || (typeof a.componentWillUpdate == `function` && a.componentWillUpdate(r, p, c),
                        typeof a.UNSAFE_componentWillUpdate == `function` && a.UNSAFE_componentWillUpdate(r, p, c)),
                        typeof a.componentDidUpdate == `function` && (t.flags |= 4),
                        typeof a.getSnapshotBeforeUpdate == `function` && (t.flags |= 1024)) : (typeof a.componentDidUpdate != `function` || o === e.memoizedProps && f === e.memoizedState || (t.flags |= 4),
                            typeof a.getSnapshotBeforeUpdate != `function` || o === e.memoizedProps && f === e.memoizedState || (t.flags |= 1024),
                            t.memoizedProps = r,
                            t.memoizedState = p),
                    a.props = r,
                    a.state = p,
                    a.context = c,
                    r = u) : (typeof a.componentDidUpdate != `function` || o === e.memoizedProps && f === e.memoizedState || (t.flags |= 4),
                        typeof a.getSnapshotBeforeUpdate != `function` || o === e.memoizedProps && f === e.memoizedState || (t.flags |= 1024),
                        r = !1)
            }
            return a = r,
                Oc(e, t),
                r = (t.flags & 128) != 0,
                a || r ? (a = t.stateNode,
                    n = r && typeof n.getDerivedStateFromError != `function` ? null : a.render(),
                    t.flags |= 1,
                    e !== null && r ? (t.child = Ja(t, e.child, null, i),
                        t.child = Ja(t, null, n, i)) : vc(e, t, n, i),
                    t.memoizedState = a.state,
                    e = t.child) : e = Uc(e, t, i),
                e
        }
        function Mc(e, t, n, r) {
            return Qi(),
                t.flags |= 256,
                vc(e, t, n, r),
                t.child
        }
        var Nc = {
            dehydrated: null,
            treeContext: null,
            retryLane: 0,
            hydrationErrors: null
        };
        function Pc(e) {
            return {
                baseLanes: e,
                cachePool: ja()
            }
        }
        function Fc(e, t, n) {
            return e = e === null ? 0 : e.childLanes & ~n,
                t && (e |= su),
                e
        }
        function Ic(e, t, n) {
            var r = t.pendingProps, a = !1, o = (t.flags & 128) != 0, s;
            if ((s = o) || (s = e !== null && e.memoizedState === null ? !1 : (xo.current & 2) != 0),
                s && (a = !0,
                    t.flags &= -129),
                s = (t.flags & 32) != 0,
                t.flags &= -33,
                e === null) {
                if (B) {
                    if (a ? go(t) : yo(t),
                        (e = z) ? (e = bf(e, Ki),
                            e = e !== null && e.data !== `&` ? e : null,
                            e !== null && (t.memoizedState = {
                                dehydrated: e,
                                treeContext: Ii === null ? null : {
                                    id: Li,
                                    overflow: Ri
                                },
                                retryLane: 536870912,
                                hydrationErrors: null
                            },
                                n = Ei(e),
                                n.return = t,
                                t.child = n,
                                Wi = t,
                                z = null)) : e = null,
                        e === null)
                        throw Ji(t);
                    return Sf(e) ? t.lanes = 32 : t.lanes = 536870912,
                        null
                }
                var c = r.children;
                return r = r.fallback,
                    a ? (yo(t),
                        a = t.mode,
                        c = Rc({
                            mode: `hidden`,
                            children: c
                        }, a),
                        r = wi(r, a, n, null),
                        c.return = t,
                        r.return = t,
                        c.sibling = r,
                        t.child = c,
                        r = t.child,
                        r.memoizedState = Pc(n),
                        r.childLanes = Fc(e, s, n),
                        t.memoizedState = Nc,
                        Cc(null, r)) : (go(t),
                            Lc(t, c))
            }
            var l = e.memoizedState;
            if (l !== null && (c = l.dehydrated,
                c !== null)) {
                if (o)
                    t.flags & 256 ? (go(t),
                        t.flags &= -257,
                        t = zc(e, t, n)) : t.memoizedState === null ? (yo(t),
                            c = r.fallback,
                            a = t.mode,
                            r = Rc({
                                mode: `visible`,
                                children: r.children
                            }, a),
                            c = wi(c, a, n, null),
                            c.flags |= 2,
                            r.return = t,
                            c.return = t,
                            r.sibling = c,
                            t.child = r,
                            Ja(t, e.child, null, n),
                            r = t.child,
                            r.memoizedState = Pc(n),
                            r.childLanes = Fc(e, s, n),
                            t.memoizedState = Nc,
                            t = Cc(null, r)) : (yo(t),
                                t.child = e.child,
                                t.flags |= 128,
                                t = null);
                else if (go(t),
                    Sf(c)) {
                    if (s = c.nextSibling && c.nextSibling.dataset,
                        s)
                        var u = s.dgst;
                    s = u,
                        r = Error(i(419)),
                        r.stack = ``,
                        r.digest = s,
                        ea({
                            value: r,
                            source: null,
                            stack: null
                        }),
                        t = zc(e, t, n)
                } else if (_c || ca(e, t, n, !1),
                    s = (n & e.childLanes) !== 0,
                    _c || s) {
                    if (s = K,
                        s !== null && (r = rt(s, n),
                            r !== 0 && r !== l.retryLane))
                        throw l.retryLane = r,
                        mi(e, r),
                        Ou(s, e, r),
                        gc;
                    xf(c) || Bu(),
                        t = zc(e, t, n)
                } else
                    xf(c) ? (t.flags |= 192,
                        t.child = e.child,
                        t = null) : (e = l.treeContext,
                            z = wf(c.nextSibling),
                            Wi = t,
                            B = !0,
                            Gi = null,
                            Ki = !1,
                            e !== null && Ui(t, e),
                            t = Lc(t, r.children),
                            t.flags |= 4096);
                return t
            }
            return a ? (yo(t),
                c = r.fallback,
                a = t.mode,
                l = e.child,
                u = l.sibling,
                r = xi(l, {
                    mode: `hidden`,
                    children: r.children
                }),
                r.subtreeFlags = l.subtreeFlags & 65011712,
                u === null ? (c = wi(c, a, n, null),
                    c.flags |= 2) : c = xi(u, c),
                c.return = t,
                r.return = t,
                r.sibling = c,
                t.child = r,
                Cc(null, r),
                r = t.child,
                c = e.child.memoizedState,
                c === null ? c = Pc(n) : (a = c.cachePool,
                    a === null ? a = ja() : (l = _a._currentValue,
                        a = a.parent === l ? a : {
                            parent: l,
                            pool: l
                        }),
                    c = {
                        baseLanes: c.baseLanes | n,
                        cachePool: a
                    }),
                r.memoizedState = c,
                r.childLanes = Fc(e, s, n),
                t.memoizedState = Nc,
                Cc(e.child, r)) : (go(t),
                    n = e.child,
                    e = n.sibling,
                    n = xi(n, {
                        mode: `visible`,
                        children: r.children
                    }),
                    n.return = t,
                    n.sibling = null,
                    e !== null && (s = t.deletions,
                        s === null ? (t.deletions = [e],
                            t.flags |= 16) : s.push(e)),
                    t.child = n,
                    t.memoizedState = null,
                    n)
        }
        function Lc(e, t) {
            return t = Rc({
                mode: `visible`,
                children: t
            }, e.mode),
                t.return = e,
                e.child = t
        }
        function Rc(e, t) {
            return e = yi(22, e, null, t),
                e.lanes = 0,
                e
        }
        function zc(e, t, n) {
            return Ja(t, e.child, null, n),
                e = Lc(t, t.pendingProps.children),
                e.flags |= 2,
                t.memoizedState = null,
                e
        }
        function Bc(e, t, n) {
            e.lanes |= t;
            var r = e.alternate;
            r !== null && (r.lanes |= t),
                oa(e.return, t, n)
        }
        function Vc(e, t, n, r, i, a) {
            var o = e.memoizedState;
            o === null ? e.memoizedState = {
                isBackwards: t,
                rendering: null,
                renderingStartTime: 0,
                last: r,
                tail: n,
                tailMode: i,
                treeForkCount: a
            } : (o.isBackwards = t,
                o.rendering = null,
                o.renderingStartTime = 0,
                o.last = r,
                o.tail = n,
                o.tailMode = i,
                o.treeForkCount = a)
        }
        function Hc(e, t, n) {
            var r = t.pendingProps
                , i = r.revealOrder
                , a = r.tail;
            r = r.children;
            var o = xo.current
                , s = (o & 2) != 0;
            if (s ? (o = o & 1 | 2,
                t.flags |= 128) : o &= 1,
                N(xo, o),
                vc(e, t, r, n),
                r = B ? Ni : 0,
                !s && e !== null && e.flags & 128)
                a: for (e = t.child; e !== null;) {
                    if (e.tag === 13)
                        e.memoizedState !== null && Bc(e, n, t);
                    else if (e.tag === 19)
                        Bc(e, n, t);
                    else if (e.child !== null) {
                        e.child.return = e,
                            e = e.child;
                        continue
                    }
                    if (e === t)
                        break a;
                    for (; e.sibling === null;) {
                        if (e.return === null || e.return === t)
                            break a;
                        e = e.return
                    }
                    e.sibling.return = e.return,
                        e = e.sibling
                }
            switch (i) {
                case `forwards`:
                    for (n = t.child,
                        i = null; n !== null;)
                        e = n.alternate,
                            e !== null && So(e) === null && (i = n),
                            n = n.sibling;
                    n = i,
                        n === null ? (i = t.child,
                            t.child = null) : (i = n.sibling,
                                n.sibling = null),
                        Vc(t, !1, i, n, a, r);
                    break;
                case `backwards`:
                case `unstable_legacy-backwards`:
                    for (n = null,
                        i = t.child,
                        t.child = null; i !== null;) {
                        if (e = i.alternate,
                            e !== null && So(e) === null) {
                            t.child = i;
                            break
                        }
                        e = i.sibling,
                            i.sibling = n,
                            n = i,
                            i = e
                    }
                    Vc(t, !0, n, null, a, r);
                    break;
                case `together`:
                    Vc(t, !1, null, null, void 0, r);
                    break;
                default:
                    t.memoizedState = null
            }
            return t.child
        }
        function Uc(e, t, n) {
            if (e !== null && (t.dependencies = e.dependencies),
                iu |= t.lanes,
                (n & t.childLanes) === 0)
                if (e !== null) {
                    if (ca(e, t, n, !1),
                        (n & t.childLanes) === 0)
                        return null
                } else
                    return null;
            if (e !== null && t.child !== e.child)
                throw Error(i(153));
            if (t.child !== null) {
                for (e = t.child,
                    n = xi(e, e.pendingProps),
                    t.child = n,
                    n.return = t; e.sibling !== null;)
                    e = e.sibling,
                        n = n.sibling = xi(e, e.pendingProps),
                        n.return = t;
                n.sibling = null
            }
            return t.child
        }
        function Wc(e, t) {
            return (e.lanes & t) === 0 ? (e = e.dependencies,
                !!(e !== null && la(e))) : !0
        }
        function Gc(e, t, n) {
            switch (t.tag) {
                case 3:
                    fe(t, t.stateNode.containerInfo),
                        ia(t, _a, e.memoizedState.cache),
                        Qi();
                    break;
                case 27:
                case 5:
                    me(t);
                    break;
                case 4:
                    fe(t, t.stateNode.containerInfo);
                    break;
                case 10:
                    ia(t, t.type, t.memoizedProps.value);
                    break;
                case 31:
                    if (t.memoizedState !== null)
                        return t.flags |= 128,
                            _o(t),
                            null;
                    break;
                case 13:
                    var r = t.memoizedState;
                    if (r !== null)
                        return r.dehydrated === null ? (n & t.child.childLanes) === 0 ? (go(t),
                            e = Uc(e, t, n),
                            e === null ? null : e.sibling) : Ic(e, t, n) : (go(t),
                                t.flags |= 128,
                                null);
                    go(t);
                    break;
                case 19:
                    var i = (e.flags & 128) != 0;
                    if (r = (n & t.childLanes) !== 0,
                        r ||= (ca(e, t, n, !1),
                            (n & t.childLanes) !== 0),
                        i) {
                        if (r)
                            return Hc(e, t, n);
                        t.flags |= 128
                    }
                    if (i = t.memoizedState,
                        i !== null && (i.rendering = null,
                            i.tail = null,
                            i.lastEffect = null),
                        N(xo, xo.current),
                        r)
                        break;
                    return null;
                case 22:
                    return t.lanes = 0,
                        Sc(e, t, n, t.pendingProps);
                case 24:
                    ia(t, _a, e.memoizedState.cache)
            }
            return Uc(e, t, n)
        }
        function Kc(e, t, n) {
            if (e !== null)
                if (e.memoizedProps !== t.pendingProps)
                    _c = !0;
                else {
                    if (!Wc(e, n) && !(t.flags & 128))
                        return _c = !1,
                            Gc(e, t, n);
                    _c = !!(e.flags & 131072)
                }
            else
                _c = !1,
                    B && t.flags & 1048576 && Bi(t, Ni, t.index);
            switch (t.lanes = 0,
            t.tag) {
                case 16:
                    a: {
                        var r = t.pendingProps;
                        if (e = Ra(t.elementType),
                            t.type = e,
                            typeof e == `function`)
                            bi(e) ? (r = oc(e, r),
                                t.tag = 1,
                                t = jc(null, t, e, r, n)) : (t.tag = 0,
                                    t = kc(null, t, e, r, n));
                        else {
                            if (e != null) {
                                var a = e.$$typeof;
                                if (a === w) {
                                    t.tag = 11,
                                        t = yc(null, t, e, r, n);
                                    break a
                                } else if (a === D) {
                                    t.tag = 14,
                                        t = bc(null, t, e, r, n);
                                    break a
                                }
                            }
                            throw t = ae(e) || e,
                            Error(i(306, t, ``))
                        }
                    }
                    return t;
                case 0:
                    return kc(e, t, t.type, t.pendingProps, n);
                case 1:
                    return r = t.type,
                        a = oc(r, t.pendingProps),
                        jc(e, t, r, a, n);
                case 3:
                    a: {
                        if (fe(t, t.stateNode.containerInfo),
                            e === null)
                            throw Error(i(387));
                        r = t.pendingProps;
                        var o = t.memoizedState;
                        a = o.element,
                            Qa(e, t),
                            ao(t, r, null, n);
                        var s = t.memoizedState;
                        if (r = s.cache,
                            ia(t, _a, r),
                            r !== o.cache && sa(t, [_a], n, !0),
                            io(),
                            r = s.element,
                            o.isDehydrated)
                            if (o = {
                                element: r,
                                isDehydrated: !1,
                                cache: s.cache
                            },
                                t.updateQueue.baseState = o,
                                t.memoizedState = o,
                                t.flags & 256) {
                                t = Mc(e, t, r, n);
                                break a
                            } else if (r !== a) {
                                a = ki(Error(i(424)), t),
                                    ea(a),
                                    t = Mc(e, t, r, n);
                                break a
                            } else {
                                switch (e = t.stateNode.containerInfo,
                                e.nodeType) {
                                    case 9:
                                        e = e.body;
                                        break;
                                    default:
                                        e = e.nodeName === `HTML` ? e.ownerDocument.body : e
                                }
                                for (z = wf(e.firstChild),
                                    Wi = t,
                                    B = !0,
                                    Gi = null,
                                    Ki = !0,
                                    n = Ya(t, null, r, n),
                                    t.child = n; n;)
                                    n.flags = n.flags & -3 | 4096,
                                        n = n.sibling
                            }
                        else {
                            if (Qi(),
                                r === a) {
                                t = Uc(e, t, n);
                                break a
                            }
                            vc(e, t, r, n)
                        }
                        t = t.child
                    }
                    return t;
                case 26:
                    return Oc(e, t),
                        e === null ? (n = Gf(t.type, null, t.pendingProps, null)) ? t.memoizedState = n : B || (n = t.type,
                            e = t.pendingProps,
                            r = tf(F.current).createElement(n),
                            r[lt] = t,
                            r[ut] = e,
                            Yd(r, n, e),
                            St(r),
                            t.stateNode = r) : t.memoizedState = Gf(t.type, e.memoizedProps, t.pendingProps, e.memoizedState),
                        null;
                case 27:
                    return me(t),
                        e === null && B && (r = t.stateNode = Of(t.type, t.pendingProps, F.current),
                            Wi = t,
                            Ki = !0,
                            a = z,
                            mf(t.type) ? (Tf = a,
                                z = wf(r.firstChild)) : z = a),
                        vc(e, t, t.pendingProps.children, n),
                        Oc(e, t),
                        e === null && (t.flags |= 4194304),
                        t.child;
                case 5:
                    return e === null && B && ((a = r = z) && (r = vf(r, t.type, t.pendingProps, Ki),
                        r === null ? a = !1 : (t.stateNode = r,
                            Wi = t,
                            z = wf(r.firstChild),
                            Ki = !1,
                            a = !0)),
                        a || Ji(t)),
                        me(t),
                        a = t.type,
                        o = t.pendingProps,
                        s = e === null ? null : e.memoizedProps,
                        r = o.children,
                        af(a, o) ? r = null : s !== null && af(a, s) && (t.flags |= 32),
                        t.memoizedState !== null && (a = Po(e, t, Lo, null, null, n),
                            mp._currentValue = a),
                        Oc(e, t),
                        vc(e, t, r, n),
                        t.child;
                case 6:
                    return e === null && B && ((e = n = z) && (n = yf(n, t.pendingProps, Ki),
                        n === null ? e = !1 : (t.stateNode = n,
                            Wi = t,
                            z = null,
                            e = !0)),
                        e || Ji(t)),
                        null;
                case 13:
                    return Ic(e, t, n);
                case 4:
                    return fe(t, t.stateNode.containerInfo),
                        r = t.pendingProps,
                        e === null ? t.child = Ja(t, null, r, n) : vc(e, t, r, n),
                        t.child;
                case 11:
                    return yc(e, t, t.type, t.pendingProps, n);
                case 7:
                    return vc(e, t, t.pendingProps, n),
                        t.child;
                case 8:
                    return vc(e, t, t.pendingProps.children, n),
                        t.child;
                case 12:
                    return vc(e, t, t.pendingProps.children, n),
                        t.child;
                case 10:
                    return r = t.pendingProps,
                        ia(t, t.type, r.value),
                        vc(e, t, r.children, n),
                        t.child;
                case 9:
                    return a = t.type._context,
                        r = t.pendingProps.children,
                        ua(t),
                        a = da(a),
                        r = r(a),
                        t.flags |= 1,
                        vc(e, t, r, n),
                        t.child;
                case 14:
                    return bc(e, t, t.type, t.pendingProps, n);
                case 15:
                    return xc(e, t, t.type, t.pendingProps, n);
                case 19:
                    return Hc(e, t, n);
                case 31:
                    return Dc(e, t, n);
                case 22:
                    return Sc(e, t, n, t.pendingProps);
                case 24:
                    return ua(t),
                        r = da(_a),
                        e === null ? (a = ka(),
                            a === null && (a = K,
                                o = va(),
                                a.pooledCache = o,
                                o.refCount++,
                                o !== null && (a.pooledCacheLanes |= n),
                                a = o),
                            t.memoizedState = {
                                parent: r,
                                cache: a
                            },
                            Za(t),
                            ia(t, _a, a)) : ((e.lanes & n) !== 0 && (Qa(e, t),
                                ao(t, null, null, n),
                                io()),
                                a = e.memoizedState,
                                o = t.memoizedState,
                                a.parent === r ? (r = o.cache,
                                    ia(t, _a, r),
                                    r !== a.cache && sa(t, [_a], n, !0)) : (a = {
                                        parent: r,
                                        cache: r
                                    },
                                        t.memoizedState = a,
                                        t.lanes === 0 && (t.memoizedState = t.updateQueue.baseState = a),
                                        ia(t, _a, r))),
                        vc(e, t, t.pendingProps.children, n),
                        t.child;
                case 29:
                    throw t.pendingProps
            }
            throw Error(i(156, t.tag))
        }
        function qc(e) {
            e.flags |= 4
        }
        function Jc(e, t, n, r, i) {
            if ((t = (e.mode & 32) != 0) && (t = !1),
                t) {
                if (e.flags |= 16777216,
                    (i & 335544128) === i)
                    if (e.stateNode.complete)
                        e.flags |= 8192;
                    else if (Lu())
                        e.flags |= 8192;
                    else
                        throw za = Fa,
                        Na
            } else
                e.flags &= -16777217
        }
        function Yc(e, t) {
            if (t.type !== `stylesheet` || t.state.loading & 4)
                e.flags &= -16777217;
            else if (e.flags |= 16777216,
                !op(t))
                if (Lu())
                    e.flags |= 8192;
                else
                    throw za = Fa,
                    Na
        }
        function Xc(e, t) {
            t !== null && (e.flags |= 4),
                e.flags & 16384 && (t = e.tag === 22 ? 536870912 : Ze(),
                    e.lanes |= t,
                    cu |= t)
        }
        function Zc(e, t) {
            if (!B)
                switch (e.tailMode) {
                    case `hidden`:
                        t = e.tail;
                        for (var n = null; t !== null;)
                            t.alternate !== null && (n = t),
                                t = t.sibling;
                        n === null ? e.tail = null : n.sibling = null;
                        break;
                    case `collapsed`:
                        n = e.tail;
                        for (var r = null; n !== null;)
                            n.alternate !== null && (r = n),
                                n = n.sibling;
                        r === null ? t || e.tail === null ? e.tail = null : e.tail.sibling = null : r.sibling = null
                }
        }
        function U(e) {
            var t = e.alternate !== null && e.alternate.child === e.child
                , n = 0
                , r = 0;
            if (t)
                for (var i = e.child; i !== null;)
                    n |= i.lanes | i.childLanes,
                        r |= i.subtreeFlags & 65011712,
                        r |= i.flags & 65011712,
                        i.return = e,
                        i = i.sibling;
            else
                for (i = e.child; i !== null;)
                    n |= i.lanes | i.childLanes,
                        r |= i.subtreeFlags,
                        r |= i.flags,
                        i.return = e,
                        i = i.sibling;
            return e.subtreeFlags |= r,
                e.childLanes = n,
                t
        }
        function Qc(e, t, n) {
            var r = t.pendingProps;
            switch (Hi(t),
            t.tag) {
                case 16:
                case 15:
                case 0:
                case 11:
                case 7:
                case 8:
                case 12:
                case 9:
                case 14:
                    return U(t),
                        null;
                case 1:
                    return U(t),
                        null;
                case 3:
                    return n = t.stateNode,
                        r = null,
                        e !== null && (r = e.memoizedState.cache),
                        t.memoizedState.cache !== r && (t.flags |= 2048),
                        aa(_a),
                        pe(),
                        n.pendingContext &&= (n.context = n.pendingContext,
                            null),
                        (e === null || e.child === null) && (Zi(t) ? qc(t) : e === null || e.memoizedState.isDehydrated && !(t.flags & 256) || (t.flags |= 1024,
                            $i())),
                        U(t),
                        null;
                case 26:
                    var a = t.type
                        , o = t.memoizedState;
                    return e === null ? (qc(t),
                        o === null ? (U(t),
                            Jc(t, a, null, r, n)) : (U(t),
                                Yc(t, o))) : o ? o === e.memoizedState ? (U(t),
                                    t.flags &= -16777217) : (qc(t),
                                        U(t),
                                        Yc(t, o)) : (e = e.memoizedProps,
                                            e !== r && qc(t),
                                            U(t),
                                            Jc(t, a, e, r, n)),
                        null;
                case 27:
                    if (he(t),
                        n = F.current,
                        a = t.type,
                        e !== null && t.stateNode != null)
                        e.memoizedProps !== r && qc(t);
                    else {
                        if (!r) {
                            if (t.stateNode === null)
                                throw Error(i(166));
                            return U(t),
                                null
                        }
                        e = P.current,
                            Zi(t) ? Yi(t, e) : (e = Of(a, r, n),
                                t.stateNode = e,
                                qc(t))
                    }
                    return U(t),
                        null;
                case 5:
                    if (he(t),
                        a = t.type,
                        e !== null && t.stateNode != null)
                        e.memoizedProps !== r && qc(t);
                    else {
                        if (!r) {
                            if (t.stateNode === null)
                                throw Error(i(166));
                            return U(t),
                                null
                        }
                        if (o = P.current,
                            Zi(t))
                            Yi(t, o);
                        else {
                            var s = tf(F.current);
                            switch (o) {
                                case 1:
                                    o = s.createElementNS(`http://www.w3.org/2000/svg`, a);
                                    break;
                                case 2:
                                    o = s.createElementNS(`http://www.w3.org/1998/Math/MathML`, a);
                                    break;
                                default:
                                    switch (a) {
                                        case `svg`:
                                            o = s.createElementNS(`http://www.w3.org/2000/svg`, a);
                                            break;
                                        case `math`:
                                            o = s.createElementNS(`http://www.w3.org/1998/Math/MathML`, a);
                                            break;
                                        case `script`:
                                            o = s.createElement(`div`),
                                                o.innerHTML = `<script><\/script>`,
                                                o = o.removeChild(o.firstChild);
                                            break;
                                        case `select`:
                                            o = typeof r.is == `string` ? s.createElement(`select`, {
                                                is: r.is
                                            }) : s.createElement(`select`),
                                                r.multiple ? o.multiple = !0 : r.size && (o.size = r.size);
                                            break;
                                        default:
                                            o = typeof r.is == `string` ? s.createElement(a, {
                                                is: r.is
                                            }) : s.createElement(a)
                                    }
                            }
                            o[lt] = t,
                                o[ut] = r;
                            a: for (s = t.child; s !== null;) {
                                if (s.tag === 5 || s.tag === 6)
                                    o.appendChild(s.stateNode);
                                else if (s.tag !== 4 && s.tag !== 27 && s.child !== null) {
                                    s.child.return = s,
                                        s = s.child;
                                    continue
                                }
                                if (s === t)
                                    break a;
                                for (; s.sibling === null;) {
                                    if (s.return === null || s.return === t)
                                        break a;
                                    s = s.return
                                }
                                s.sibling.return = s.return,
                                    s = s.sibling
                            }
                            t.stateNode = o;
                            a: switch (Yd(o, a, r),
                            a) {
                                case `button`:
                                case `input`:
                                case `select`:
                                case `textarea`:
                                    r = !!r.autoFocus;
                                    break a;
                                case `img`:
                                    r = !0;
                                    break a;
                                default:
                                    r = !1
                            }
                            r && qc(t)
                        }
                    }
                    return U(t),
                        Jc(t, t.type, e === null ? null : e.memoizedProps, t.pendingProps, n),
                        null;
                case 6:
                    if (e && t.stateNode != null)
                        e.memoizedProps !== r && qc(t);
                    else {
                        if (typeof r != `string` && t.stateNode === null)
                            throw Error(i(166));
                        if (e = F.current,
                            Zi(t)) {
                            if (e = t.stateNode,
                                n = t.memoizedProps,
                                r = null,
                                a = Wi,
                                a !== null)
                                switch (a.tag) {
                                    case 27:
                                    case 5:
                                        r = a.memoizedProps
                                }
                            e[lt] = t,
                                e = !!(e.nodeValue === n || r !== null && !0 === r.suppressHydrationWarning || qd(e.nodeValue, n)),
                                e || Ji(t, !0)
                        } else
                            e = tf(e).createTextNode(r),
                                e[lt] = t,
                                t.stateNode = e
                    }
                    return U(t),
                        null;
                case 31:
                    if (n = t.memoizedState,
                        e === null || e.memoizedState !== null) {
                        if (r = Zi(t),
                            n !== null) {
                            if (e === null) {
                                if (!r)
                                    throw Error(i(318));
                                if (e = t.memoizedState,
                                    e = e === null ? null : e.dehydrated,
                                    !e)
                                    throw Error(i(557));
                                e[lt] = t
                            } else
                                Qi(),
                                    !(t.flags & 128) && (t.memoizedState = null),
                                    t.flags |= 4;
                            U(t),
                                e = !1
                        } else
                            n = $i(),
                                e !== null && e.memoizedState !== null && (e.memoizedState.hydrationErrors = n),
                                e = !0;
                        if (!e)
                            return t.flags & 256 ? (bo(t),
                                t) : (bo(t),
                                    null);
                        if (t.flags & 128)
                            throw Error(i(558))
                    }
                    return U(t),
                        null;
                case 13:
                    if (r = t.memoizedState,
                        e === null || e.memoizedState !== null && e.memoizedState.dehydrated !== null) {
                        if (a = Zi(t),
                            r !== null && r.dehydrated !== null) {
                            if (e === null) {
                                if (!a)
                                    throw Error(i(318));
                                if (a = t.memoizedState,
                                    a = a === null ? null : a.dehydrated,
                                    !a)
                                    throw Error(i(317));
                                a[lt] = t
                            } else
                                Qi(),
                                    !(t.flags & 128) && (t.memoizedState = null),
                                    t.flags |= 4;
                            U(t),
                                a = !1
                        } else
                            a = $i(),
                                e !== null && e.memoizedState !== null && (e.memoizedState.hydrationErrors = a),
                                a = !0;
                        if (!a)
                            return t.flags & 256 ? (bo(t),
                                t) : (bo(t),
                                    null)
                    }
                    return bo(t),
                        t.flags & 128 ? (t.lanes = n,
                            t) : (n = r !== null,
                                e = e !== null && e.memoizedState !== null,
                                n && (r = t.child,
                                    a = null,
                                    r.alternate !== null && r.alternate.memoizedState !== null && r.alternate.memoizedState.cachePool !== null && (a = r.alternate.memoizedState.cachePool.pool),
                                    o = null,
                                    r.memoizedState !== null && r.memoizedState.cachePool !== null && (o = r.memoizedState.cachePool.pool),
                                    o !== a && (r.flags |= 2048)),
                                n !== e && n && (t.child.flags |= 8192),
                                Xc(t, t.updateQueue),
                                U(t),
                                null);
                case 4:
                    return pe(),
                        e === null && Ld(t.stateNode.containerInfo),
                        U(t),
                        null;
                case 10:
                    return aa(t.type),
                        U(t),
                        null;
                case 19:
                    if (M(xo),
                        r = t.memoizedState,
                        r === null)
                        return U(t),
                            null;
                    if (a = (t.flags & 128) != 0,
                        o = r.rendering,
                        o === null)
                        if (a)
                            Zc(r, !1);
                        else {
                            if (X !== 0 || e !== null && e.flags & 128)
                                for (e = t.child; e !== null;) {
                                    if (o = So(e),
                                        o !== null) {
                                        for (t.flags |= 128,
                                            Zc(r, !1),
                                            e = o.updateQueue,
                                            t.updateQueue = e,
                                            Xc(t, e),
                                            t.subtreeFlags = 0,
                                            e = n,
                                            n = t.child; n !== null;)
                                            Si(n, e),
                                                n = n.sibling;
                                        return N(xo, xo.current & 1 | 2),
                                            B && zi(t, r.treeForkCount),
                                            t.child
                                    }
                                    e = e.sibling
                                }
                            r.tail !== null && Oe() > mu && (t.flags |= 128,
                                a = !0,
                                Zc(r, !1),
                                t.lanes = 4194304)
                        }
                    else {
                        if (!a)
                            if (e = So(o),
                                e !== null) {
                                if (t.flags |= 128,
                                    a = !0,
                                    e = e.updateQueue,
                                    t.updateQueue = e,
                                    Xc(t, e),
                                    Zc(r, !0),
                                    r.tail === null && r.tailMode === `hidden` && !o.alternate && !B)
                                    return U(t),
                                        null
                            } else
                                2 * Oe() - r.renderingStartTime > mu && n !== 536870912 && (t.flags |= 128,
                                    a = !0,
                                    Zc(r, !1),
                                    t.lanes = 4194304);
                        r.isBackwards ? (o.sibling = t.child,
                            t.child = o) : (e = r.last,
                                e === null ? t.child = o : e.sibling = o,
                                r.last = o)
                    }
                    return r.tail === null ? (U(t),
                        null) : (e = r.tail,
                            r.rendering = e,
                            r.tail = e.sibling,
                            r.renderingStartTime = Oe(),
                            e.sibling = null,
                            n = xo.current,
                            N(xo, a ? n & 1 | 2 : n & 1),
                            B && zi(t, r.treeForkCount),
                            e);
                case 22:
                case 23:
                    return bo(t),
                        po(),
                        r = t.memoizedState !== null,
                        e === null ? r && (t.flags |= 8192) : e.memoizedState !== null !== r && (t.flags |= 8192),
                        r ? n & 536870912 && !(t.flags & 128) && (U(t),
                            t.subtreeFlags & 6 && (t.flags |= 8192)) : U(t),
                        n = t.updateQueue,
                        n !== null && Xc(t, n.retryQueue),
                        n = null,
                        e !== null && e.memoizedState !== null && e.memoizedState.cachePool !== null && (n = e.memoizedState.cachePool.pool),
                        r = null,
                        t.memoizedState !== null && t.memoizedState.cachePool !== null && (r = t.memoizedState.cachePool.pool),
                        r !== n && (t.flags |= 2048),
                        e !== null && M(Oa),
                        null;
                case 24:
                    return n = null,
                        e !== null && (n = e.memoizedState.cache),
                        t.memoizedState.cache !== n && (t.flags |= 2048),
                        aa(_a),
                        U(t),
                        null;
                case 25:
                    return null;
                case 30:
                    return null
            }
            throw Error(i(156, t.tag))
        }
        function $c(e, t) {
            switch (Hi(t),
            t.tag) {
                case 1:
                    return e = t.flags,
                        e & 65536 ? (t.flags = e & -65537 | 128,
                            t) : null;
                case 3:
                    return aa(_a),
                        pe(),
                        e = t.flags,
                        e & 65536 && !(e & 128) ? (t.flags = e & -65537 | 128,
                            t) : null;
                case 26:
                case 27:
                case 5:
                    return he(t),
                        null;
                case 31:
                    if (t.memoizedState !== null) {
                        if (bo(t),
                            t.alternate === null)
                            throw Error(i(340));
                        Qi()
                    }
                    return e = t.flags,
                        e & 65536 ? (t.flags = e & -65537 | 128,
                            t) : null;
                case 13:
                    if (bo(t),
                        e = t.memoizedState,
                        e !== null && e.dehydrated !== null) {
                        if (t.alternate === null)
                            throw Error(i(340));
                        Qi()
                    }
                    return e = t.flags,
                        e & 65536 ? (t.flags = e & -65537 | 128,
                            t) : null;
                case 19:
                    return M(xo),
                        null;
                case 4:
                    return pe(),
                        null;
                case 10:
                    return aa(t.type),
                        null;
                case 22:
                case 23:
                    return bo(t),
                        po(),
                        e !== null && M(Oa),
                        e = t.flags,
                        e & 65536 ? (t.flags = e & -65537 | 128,
                            t) : null;
                case 24:
                    return aa(_a),
                        null;
                case 25:
                    return null;
                default:
                    return null
            }
        }
        function el(e, t) {
            switch (Hi(t),
            t.tag) {
                case 3:
                    aa(_a),
                        pe();
                    break;
                case 26:
                case 27:
                case 5:
                    he(t);
                    break;
                case 4:
                    pe();
                    break;
                case 31:
                    t.memoizedState !== null && bo(t);
                    break;
                case 13:
                    bo(t);
                    break;
                case 19:
                    M(xo);
                    break;
                case 10:
                    aa(t.type);
                    break;
                case 22:
                case 23:
                    bo(t),
                        po(),
                        e !== null && M(Oa);
                    break;
                case 24:
                    aa(_a)
            }
        }
        function tl(e, t) {
            try {
                var n = t.updateQueue
                    , r = n === null ? null : n.lastEffect;
                if (r !== null) {
                    var i = r.next;
                    n = i;
                    do {
                        if ((n.tag & e) === e) {
                            r = void 0;
                            var a = n.create
                                , o = n.inst;
                            r = a(),
                                o.destroy = r
                        }
                        n = n.next
                    } while (n !== i)
                }
            } catch (e) {
                Z(t, t.return, e)
            }
        }
        function nl(e, t, n) {
            try {
                var r = t.updateQueue
                    , i = r === null ? null : r.lastEffect;
                if (i !== null) {
                    var a = i.next;
                    r = a;
                    do {
                        if ((r.tag & e) === e) {
                            var o = r.inst
                                , s = o.destroy;
                            if (s !== void 0) {
                                o.destroy = void 0,
                                    i = t;
                                var c = n
                                    , l = s;
                                try {
                                    l()
                                } catch (e) {
                                    Z(i, c, e)
                                }
                            }
                        }
                        r = r.next
                    } while (r !== a)
                }
            } catch (e) {
                Z(t, t.return, e)
            }
        }
        function rl(e) {
            var t = e.updateQueue;
            if (t !== null) {
                var n = e.stateNode;
                try {
                    so(t, n)
                } catch (t) {
                    Z(e, e.return, t)
                }
            }
        }
        function il(e, t, n) {
            n.props = oc(e.type, e.memoizedProps),
                n.state = e.memoizedState;
            try {
                n.componentWillUnmount()
            } catch (n) {
                Z(e, t, n)
            }
        }
        function al(e, t) {
            try {
                var n = e.ref;
                if (n !== null) {
                    switch (e.tag) {
                        case 26:
                        case 27:
                        case 5:
                            var r = e.stateNode;
                            break;
                        case 30:
                            r = e.stateNode;
                            break;
                        default:
                            r = e.stateNode
                    }
                    typeof n == `function` ? e.refCleanup = n(r) : n.current = r
                }
            } catch (n) {
                Z(e, t, n)
            }
        }
        function ol(e, t) {
            var n = e.ref
                , r = e.refCleanup;
            if (n !== null)
                if (typeof r == `function`)
                    try {
                        r()
                    } catch (n) {
                        Z(e, t, n)
                    } finally {
                        e.refCleanup = null,
                            e = e.alternate,
                            e != null && (e.refCleanup = null)
                    }
                else if (typeof n == `function`)
                    try {
                        n(null)
                    } catch (n) {
                        Z(e, t, n)
                    }
                else
                    n.current = null
        }
        function sl(e) {
            var t = e.type
                , n = e.memoizedProps
                , r = e.stateNode;
            try {
                a: switch (t) {
                    case `button`:
                    case `input`:
                    case `select`:
                    case `textarea`:
                        n.autoFocus && r.focus();
                        break a;
                    case `img`:
                        n.src ? r.src = n.src : n.srcSet && (r.srcset = n.srcSet)
                }
            } catch (t) {
                Z(e, e.return, t)
            }
        }
        function cl(e, t, n) {
            try {
                var r = e.stateNode;
                Xd(r, e.type, n, t),
                    r[ut] = t
            } catch (t) {
                Z(e, e.return, t)
            }
        }
        function ll(e) {
            return e.tag === 5 || e.tag === 3 || e.tag === 26 || e.tag === 27 && mf(e.type) || e.tag === 4
        }
        function ul(e) {
            a: for (; ;) {
                for (; e.sibling === null;) {
                    if (e.return === null || ll(e.return))
                        return null;
                    e = e.return
                }
                for (e.sibling.return = e.return,
                    e = e.sibling; e.tag !== 5 && e.tag !== 6 && e.tag !== 18;) {
                    if (e.tag === 27 && mf(e.type) || e.flags & 2 || e.child === null || e.tag === 4)
                        continue a;
                    e.child.return = e,
                        e = e.child
                }
                if (!(e.flags & 2))
                    return e.stateNode
            }
        }
        function dl(e, t, n) {
            var r = e.tag;
            if (r === 5 || r === 6)
                e = e.stateNode,
                    t ? (n.nodeType === 9 ? n.body : n.nodeName === `HTML` ? n.ownerDocument.body : n).insertBefore(e, t) : (t = n.nodeType === 9 ? n.body : n.nodeName === `HTML` ? n.ownerDocument.body : n,
                        t.appendChild(e),
                        n = n._reactRootContainer,
                        n != null || t.onclick !== null || (t.onclick = nn));
            else if (r !== 4 && (r === 27 && mf(e.type) && (n = e.stateNode,
                t = null),
                e = e.child,
                e !== null))
                for (dl(e, t, n),
                    e = e.sibling; e !== null;)
                    dl(e, t, n),
                        e = e.sibling
        }
        function fl(e, t, n) {
            var r = e.tag;
            if (r === 5 || r === 6)
                e = e.stateNode,
                    t ? n.insertBefore(e, t) : n.appendChild(e);
            else if (r !== 4 && (r === 27 && mf(e.type) && (n = e.stateNode),
                e = e.child,
                e !== null))
                for (fl(e, t, n),
                    e = e.sibling; e !== null;)
                    fl(e, t, n),
                        e = e.sibling
        }
        function pl(e) {
            var t = e.stateNode
                , n = e.memoizedProps;
            try {
                for (var r = e.type, i = t.attributes; i.length;)
                    t.removeAttributeNode(i[0]);
                Yd(t, r, n),
                    t[lt] = e,
                    t[ut] = n
            } catch (t) {
                Z(e, e.return, t)
            }
        }
        var ml = !1
            , hl = !1
            , gl = !1
            , _l = typeof WeakSet == `function` ? WeakSet : Set
            , vl = null;
        function yl(e, t) {
            if (e = e.containerInfo,
                $d = Cp,
                e = Rr(e),
                zr(e)) {
                if (`selectionStart` in e)
                    var n = {
                        start: e.selectionStart,
                        end: e.selectionEnd
                    };
                else
                    a: {
                        n = (n = e.ownerDocument) && n.defaultView || window;
                        var r = n.getSelection && n.getSelection();
                        if (r && r.rangeCount !== 0) {
                            n = r.anchorNode;
                            var a = r.anchorOffset
                                , o = r.focusNode;
                            r = r.focusOffset;
                            try {
                                n.nodeType,
                                    o.nodeType
                            } catch {
                                n = null;
                                break a
                            }
                            var s = 0
                                , c = -1
                                , l = -1
                                , u = 0
                                , d = 0
                                , f = e
                                , p = null;
                            b: for (; ;) {
                                for (var m; f !== n || a !== 0 && f.nodeType !== 3 || (c = s + a),
                                    f !== o || r !== 0 && f.nodeType !== 3 || (l = s + r),
                                    f.nodeType === 3 && (s += f.nodeValue.length),
                                    (m = f.firstChild) !== null;)
                                    p = f,
                                        f = m;
                                for (; ;) {
                                    if (f === e)
                                        break b;
                                    if (p === n && ++u === a && (c = s),
                                        p === o && ++d === r && (l = s),
                                        (m = f.nextSibling) !== null)
                                        break;
                                    f = p,
                                        p = f.parentNode
                                }
                                f = m
                            }
                            n = c === -1 || l === -1 ? null : {
                                start: c,
                                end: l
                            }
                        } else
                            n = null
                    }
                n ||= {
                    start: 0,
                    end: 0
                }
            } else
                n = null;
            for (ef = {
                focusedElem: e,
                selectionRange: n
            },
                Cp = !1,
                vl = t; vl !== null;)
                if (t = vl,
                    e = t.child,
                    t.subtreeFlags & 1028 && e !== null)
                    e.return = t,
                        vl = e;
                else
                    for (; vl !== null;) {
                        switch (t = vl,
                        o = t.alternate,
                        e = t.flags,
                        t.tag) {
                            case 0:
                                if (e & 4 && (e = t.updateQueue,
                                    e = e === null ? null : e.events,
                                    e !== null))
                                    for (n = 0; n < e.length; n++)
                                        a = e[n],
                                            a.ref.impl = a.nextImpl;
                                break;
                            case 11:
                            case 15:
                                break;
                            case 1:
                                if (e & 1024 && o !== null) {
                                    e = void 0,
                                        n = t,
                                        a = o.memoizedProps,
                                        o = o.memoizedState,
                                        r = n.stateNode;
                                    try {
                                        var h = oc(n.type, a);
                                        e = r.getSnapshotBeforeUpdate(h, o),
                                            r.__reactInternalSnapshotBeforeUpdate = e
                                    } catch (e) {
                                        Z(n, n.return, e)
                                    }
                                }
                                break;
                            case 3:
                                if (e & 1024) {
                                    if (e = t.stateNode.containerInfo,
                                        n = e.nodeType,
                                        n === 9)
                                        _f(e);
                                    else if (n === 1)
                                        switch (e.nodeName) {
                                            case `HEAD`:
                                            case `HTML`:
                                            case `BODY`:
                                                _f(e);
                                                break;
                                            default:
                                                e.textContent = ``
                                        }
                                }
                                break;
                            case 5:
                            case 26:
                            case 27:
                            case 6:
                            case 4:
                            case 17:
                                break;
                            default:
                                if (e & 1024)
                                    throw Error(i(163))
                        }
                        if (e = t.sibling,
                            e !== null) {
                            e.return = t.return,
                                vl = e;
                            break
                        }
                        vl = t.return
                    }
        }
        function bl(e, t, n) {
            var r = n.flags;
            switch (n.tag) {
                case 0:
                case 11:
                case 15:
                    Pl(e, n),
                        r & 4 && tl(5, n);
                    break;
                case 1:
                    if (Pl(e, n),
                        r & 4)
                        if (e = n.stateNode,
                            t === null)
                            try {
                                e.componentDidMount()
                            } catch (e) {
                                Z(n, n.return, e)
                            }
                        else {
                            var i = oc(n.type, t.memoizedProps);
                            t = t.memoizedState;
                            try {
                                e.componentDidUpdate(i, t, e.__reactInternalSnapshotBeforeUpdate)
                            } catch (e) {
                                Z(n, n.return, e)
                            }
                        }
                    r & 64 && rl(n),
                        r & 512 && al(n, n.return);
                    break;
                case 3:
                    if (Pl(e, n),
                        r & 64 && (e = n.updateQueue,
                            e !== null)) {
                        if (t = null,
                            n.child !== null)
                            switch (n.child.tag) {
                                case 27:
                                case 5:
                                    t = n.child.stateNode;
                                    break;
                                case 1:
                                    t = n.child.stateNode
                            }
                        try {
                            so(e, t)
                        } catch (e) {
                            Z(n, n.return, e)
                        }
                    }
                    break;
                case 27:
                    t === null && r & 4 && pl(n);
                case 26:
                case 5:
                    Pl(e, n),
                        t === null && r & 4 && sl(n),
                        r & 512 && al(n, n.return);
                    break;
                case 12:
                    Pl(e, n);
                    break;
                case 31:
                    Pl(e, n),
                        r & 4 && Tl(e, n);
                    break;
                case 13:
                    Pl(e, n),
                        r & 4 && El(e, n),
                        r & 64 && (e = n.memoizedState,
                            e !== null && (e = e.dehydrated,
                                e !== null && (n = sd.bind(null, n),
                                    Cf(e, n))));
                    break;
                case 22:
                    if (r = n.memoizedState !== null || ml,
                        !r) {
                        t = t !== null && t.memoizedState !== null || hl,
                            i = ml;
                        var a = hl;
                        ml = r,
                            (hl = t) && !a ? Il(e, n, (n.subtreeFlags & 8772) != 0) : Pl(e, n),
                            ml = i,
                            hl = a
                    }
                    break;
                case 30:
                    break;
                default:
                    Pl(e, n)
            }
        }
        function xl(e) {
            var t = e.alternate;
            t !== null && (e.alternate = null,
                xl(t)),
                e.child = null,
                e.deletions = null,
                e.sibling = null,
                e.tag === 5 && (t = e.stateNode,
                    t !== null && _t(t)),
                e.stateNode = null,
                e.return = null,
                e.dependencies = null,
                e.memoizedProps = null,
                e.memoizedState = null,
                e.pendingProps = null,
                e.stateNode = null,
                e.updateQueue = null
        }
        var W = null
            , Sl = !1;
        function Cl(e, t, n) {
            for (n = n.child; n !== null;)
                wl(e, t, n),
                    n = n.sibling
        }
        function wl(e, t, n) {
            if (Re && typeof Re.onCommitFiberUnmount == `function`)
                try {
                    Re.onCommitFiberUnmount(Le, n)
                } catch { }
            switch (n.tag) {
                case 26:
                    hl || ol(n, t),
                        Cl(e, t, n),
                        n.memoizedState ? n.memoizedState.count-- : n.stateNode && (n = n.stateNode,
                            n.parentNode.removeChild(n));
                    break;
                case 27:
                    hl || ol(n, t);
                    var r = W
                        , i = Sl;
                    mf(n.type) && (W = n.stateNode,
                        Sl = !1),
                        Cl(e, t, n),
                        kf(n.stateNode),
                        W = r,
                        Sl = i;
                    break;
                case 5:
                    hl || ol(n, t);
                case 6:
                    if (r = W,
                        i = Sl,
                        W = null,
                        Cl(e, t, n),
                        W = r,
                        Sl = i,
                        W !== null)
                        if (Sl)
                            try {
                                (W.nodeType === 9 ? W.body : W.nodeName === `HTML` ? W.ownerDocument.body : W).removeChild(n.stateNode)
                            } catch (e) {
                                Z(n, t, e)
                            }
                        else
                            try {
                                W.removeChild(n.stateNode)
                            } catch (e) {
                                Z(n, t, e)
                            }
                    break;
                case 18:
                    W !== null && (Sl ? (e = W,
                        hf(e.nodeType === 9 ? e.body : e.nodeName === `HTML` ? e.ownerDocument.body : e, n.stateNode),
                        Yp(e)) : hf(W, n.stateNode));
                    break;
                case 4:
                    r = W,
                        i = Sl,
                        W = n.stateNode.containerInfo,
                        Sl = !0,
                        Cl(e, t, n),
                        W = r,
                        Sl = i;
                    break;
                case 0:
                case 11:
                case 14:
                case 15:
                    nl(2, n, t),
                        hl || nl(4, n, t),
                        Cl(e, t, n);
                    break;
                case 1:
                    hl || (ol(n, t),
                        r = n.stateNode,
                        typeof r.componentWillUnmount == `function` && il(n, t, r)),
                        Cl(e, t, n);
                    break;
                case 21:
                    Cl(e, t, n);
                    break;
                case 22:
                    hl = (r = hl) || n.memoizedState !== null,
                        Cl(e, t, n),
                        hl = r;
                    break;
                default:
                    Cl(e, t, n)
            }
        }
        function Tl(e, t) {
            if (t.memoizedState === null && (e = t.alternate,
                e !== null && (e = e.memoizedState,
                    e !== null))) {
                e = e.dehydrated;
                try {
                    Yp(e)
                } catch (e) {
                    Z(t, t.return, e)
                }
            }
        }
        function El(e, t) {
            if (t.memoizedState === null && (e = t.alternate,
                e !== null && (e = e.memoizedState,
                    e !== null && (e = e.dehydrated,
                        e !== null))))
                try {
                    Yp(e)
                } catch (e) {
                    Z(t, t.return, e)
                }
        }
        function Dl(e) {
            switch (e.tag) {
                case 31:
                case 13:
                case 19:
                    var t = e.stateNode;
                    return t === null && (t = e.stateNode = new _l),
                        t;
                case 22:
                    return e = e.stateNode,
                        t = e._retryCache,
                        t === null && (t = e._retryCache = new _l),
                        t;
                default:
                    throw Error(i(435, e.tag))
            }
        }
        function Ol(e, t) {
            var n = Dl(e);
            t.forEach(function (t) {
                if (!n.has(t)) {
                    n.add(t);
                    var r = cd.bind(null, e, t);
                    t.then(r, r)
                }
            })
        }
        function kl(e, t) {
            var n = t.deletions;
            if (n !== null)
                for (var r = 0; r < n.length; r++) {
                    var a = n[r]
                        , o = e
                        , s = t
                        , c = s;
                    a: for (; c !== null;) {
                        switch (c.tag) {
                            case 27:
                                if (mf(c.type)) {
                                    W = c.stateNode,
                                        Sl = !1;
                                    break a
                                }
                                break;
                            case 5:
                                W = c.stateNode,
                                    Sl = !1;
                                break a;
                            case 3:
                            case 4:
                                W = c.stateNode.containerInfo,
                                    Sl = !0;
                                break a
                        }
                        c = c.return
                    }
                    if (W === null)
                        throw Error(i(160));
                    wl(o, s, a),
                        W = null,
                        Sl = !1,
                        o = a.alternate,
                        o !== null && (o.return = null),
                        a.return = null
                }
            if (t.subtreeFlags & 13886)
                for (t = t.child; t !== null;)
                    jl(t, e),
                        t = t.sibling
        }
        var Al = null;
        function jl(e, t) {
            var n = e.alternate
                , r = e.flags;
            switch (e.tag) {
                case 0:
                case 11:
                case 14:
                case 15:
                    kl(t, e),
                        Ml(e),
                        r & 4 && (nl(3, e, e.return),
                            tl(3, e),
                            nl(5, e, e.return));
                    break;
                case 1:
                    kl(t, e),
                        Ml(e),
                        r & 512 && (hl || n === null || ol(n, n.return)),
                        r & 64 && ml && (e = e.updateQueue,
                            e !== null && (r = e.callbacks,
                                r !== null && (n = e.shared.hiddenCallbacks,
                                    e.shared.hiddenCallbacks = n === null ? r : n.concat(r))));
                    break;
                case 26:
                    var a = Al;
                    if (kl(t, e),
                        Ml(e),
                        r & 512 && (hl || n === null || ol(n, n.return)),
                        r & 4) {
                        var o = n === null ? null : n.memoizedState;
                        if (r = e.memoizedState,
                            n === null)
                            if (r === null)
                                if (e.stateNode === null) {
                                    a: {
                                        r = e.type,
                                            n = e.memoizedProps,
                                            a = a.ownerDocument || a;
                                        b: switch (r) {
                                            case `title`:
                                                o = a.getElementsByTagName(`title`)[0],
                                                    (!o || o[gt] || o[lt] || o.namespaceURI === `http://www.w3.org/2000/svg` || o.hasAttribute(`itemprop`)) && (o = a.createElement(r),
                                                        a.head.insertBefore(o, a.querySelector(`head > title`))),
                                                    Yd(o, r, n),
                                                    o[lt] = e,
                                                    St(o),
                                                    r = o;
                                                break a;
                                            case `link`:
                                                var s = rp(`link`, `href`, a).get(r + (n.href || ``));
                                                if (s) {
                                                    for (var c = 0; c < s.length; c++)
                                                        if (o = s[c],
                                                            o.getAttribute(`href`) === (n.href == null || n.href === `` ? null : n.href) && o.getAttribute(`rel`) === (n.rel == null ? null : n.rel) && o.getAttribute(`title`) === (n.title == null ? null : n.title) && o.getAttribute(`crossorigin`) === (n.crossOrigin == null ? null : n.crossOrigin)) {
                                                            s.splice(c, 1);
                                                            break b
                                                        }
                                                }
                                                o = a.createElement(r),
                                                    Yd(o, r, n),
                                                    a.head.appendChild(o);
                                                break;
                                            case `meta`:
                                                if (s = rp(`meta`, `content`, a).get(r + (n.content || ``))) {
                                                    for (c = 0; c < s.length; c++)
                                                        if (o = s[c],
                                                            o.getAttribute(`content`) === (n.content == null ? null : `` + n.content) && o.getAttribute(`name`) === (n.name == null ? null : n.name) && o.getAttribute(`property`) === (n.property == null ? null : n.property) && o.getAttribute(`http-equiv`) === (n.httpEquiv == null ? null : n.httpEquiv) && o.getAttribute(`charset`) === (n.charSet == null ? null : n.charSet)) {
                                                            s.splice(c, 1);
                                                            break b
                                                        }
                                                }
                                                o = a.createElement(r),
                                                    Yd(o, r, n),
                                                    a.head.appendChild(o);
                                                break;
                                            default:
                                                throw Error(i(468, r))
                                        }
                                        o[lt] = e,
                                            St(o),
                                            r = o
                                    }
                                    e.stateNode = r
                                } else
                                    ip(a, e.type, e.stateNode);
                            else
                                e.stateNode = Qf(a, r, e.memoizedProps);
                        else
                            o === r ? r === null && e.stateNode !== null && cl(e, e.memoizedProps, n.memoizedProps) : (o === null ? n.stateNode !== null && (n = n.stateNode,
                                n.parentNode.removeChild(n)) : o.count--,
                                r === null ? ip(a, e.type, e.stateNode) : Qf(a, r, e.memoizedProps))
                    }
                    break;
                case 27:
                    kl(t, e),
                        Ml(e),
                        r & 512 && (hl || n === null || ol(n, n.return)),
                        n !== null && r & 4 && cl(e, e.memoizedProps, n.memoizedProps);
                    break;
                case 5:
                    if (kl(t, e),
                        Ml(e),
                        r & 512 && (hl || n === null || ol(n, n.return)),
                        e.flags & 32) {
                        a = e.stateNode;
                        try {
                            Jt(a, ``)
                        } catch (t) {
                            Z(e, e.return, t)
                        }
                    }
                    r & 4 && e.stateNode != null && (a = e.memoizedProps,
                        cl(e, a, n === null ? a : n.memoizedProps)),
                        r & 1024 && (gl = !0);
                    break;
                case 6:
                    if (kl(t, e),
                        Ml(e),
                        r & 4) {
                        if (e.stateNode === null)
                            throw Error(i(162));
                        r = e.memoizedProps,
                            n = e.stateNode;
                        try {
                            n.nodeValue = r
                        } catch (t) {
                            Z(e, e.return, t)
                        }
                    }
                    break;
                case 3:
                    if (np = null,
                        a = Al,
                        Al = Mf(t.containerInfo),
                        kl(t, e),
                        Al = a,
                        Ml(e),
                        r & 4 && n !== null && n.memoizedState.isDehydrated)
                        try {
                            Yp(t.containerInfo)
                        } catch (t) {
                            Z(e, e.return, t)
                        }
                    gl && (gl = !1,
                        Nl(e));
                    break;
                case 4:
                    r = Al,
                        Al = Mf(e.stateNode.containerInfo),
                        kl(t, e),
                        Ml(e),
                        Al = r;
                    break;
                case 12:
                    kl(t, e),
                        Ml(e);
                    break;
                case 31:
                    kl(t, e),
                        Ml(e),
                        r & 4 && (r = e.updateQueue,
                            r !== null && (e.updateQueue = null,
                                Ol(e, r)));
                    break;
                case 13:
                    kl(t, e),
                        Ml(e),
                        e.child.flags & 8192 && e.memoizedState !== null != (n !== null && n.memoizedState !== null) && (fu = Oe()),
                        r & 4 && (r = e.updateQueue,
                            r !== null && (e.updateQueue = null,
                                Ol(e, r)));
                    break;
                case 22:
                    a = e.memoizedState !== null;
                    var l = n !== null && n.memoizedState !== null
                        , u = ml
                        , d = hl;
                    if (ml = u || a,
                        hl = d || l,
                        kl(t, e),
                        hl = d,
                        ml = u,
                        Ml(e),
                        r & 8192)
                        a: for (t = e.stateNode,
                            t._visibility = a ? t._visibility & -2 : t._visibility | 1,
                            a && (n === null || l || ml || hl || Fl(e)),
                            n = null,
                            t = e; ;) {
                            if (t.tag === 5 || t.tag === 26) {
                                if (n === null) {
                                    l = n = t;
                                    try {
                                        if (o = l.stateNode,
                                            a)
                                            s = o.style,
                                                typeof s.setProperty == `function` ? s.setProperty(`display`, `none`, `important`) : s.display = `none`;
                                        else {
                                            c = l.stateNode;
                                            var f = l.memoizedProps.style
                                                , p = f != null && f.hasOwnProperty(`display`) ? f.display : null;
                                            c.style.display = p == null || typeof p == `boolean` ? `` : (`` + p).trim()
                                        }
                                    } catch (e) {
                                        Z(l, l.return, e)
                                    }
                                }
                            } else if (t.tag === 6) {
                                if (n === null) {
                                    l = t;
                                    try {
                                        l.stateNode.nodeValue = a ? `` : l.memoizedProps
                                    } catch (e) {
                                        Z(l, l.return, e)
                                    }
                                }
                            } else if (t.tag === 18) {
                                if (n === null) {
                                    l = t;
                                    try {
                                        var m = l.stateNode;
                                        a ? gf(m, !0) : gf(l.stateNode, !1)
                                    } catch (e) {
                                        Z(l, l.return, e)
                                    }
                                }
                            } else if ((t.tag !== 22 && t.tag !== 23 || t.memoizedState === null || t === e) && t.child !== null) {
                                t.child.return = t,
                                    t = t.child;
                                continue
                            }
                            if (t === e)
                                break a;
                            for (; t.sibling === null;) {
                                if (t.return === null || t.return === e)
                                    break a;
                                n === t && (n = null),
                                    t = t.return
                            }
                            n === t && (n = null),
                                t.sibling.return = t.return,
                                t = t.sibling
                        }
                    r & 4 && (r = e.updateQueue,
                        r !== null && (n = r.retryQueue,
                            n !== null && (r.retryQueue = null,
                                Ol(e, n))));
                    break;
                case 19:
                    kl(t, e),
                        Ml(e),
                        r & 4 && (r = e.updateQueue,
                            r !== null && (e.updateQueue = null,
                                Ol(e, r)));
                    break;
                case 30:
                    break;
                case 21:
                    break;
                default:
                    kl(t, e),
                        Ml(e)
            }
        }
        function Ml(e) {
            var t = e.flags;
            if (t & 2) {
                try {
                    for (var n, r = e.return; r !== null;) {
                        if (ll(r)) {
                            n = r;
                            break
                        }
                        r = r.return
                    }
                    if (n == null)
                        throw Error(i(160));
                    switch (n.tag) {
                        case 27:
                            var a = n.stateNode
                                , o = ul(e);
                            fl(e, o, a);
                            break;
                        case 5:
                            var s = n.stateNode;
                            n.flags & 32 && (Jt(s, ``),
                                n.flags &= -33);
                            var c = ul(e);
                            fl(e, c, s);
                            break;
                        case 3:
                        case 4:
                            var l = n.stateNode.containerInfo
                                , u = ul(e);
                            dl(e, u, l);
                            break;
                        default:
                            throw Error(i(161))
                    }
                } catch (t) {
                    Z(e, e.return, t)
                }
                e.flags &= -3
            }
            t & 4096 && (e.flags &= -4097)
        }
        function Nl(e) {
            if (e.subtreeFlags & 1024)
                for (e = e.child; e !== null;) {
                    var t = e;
                    Nl(t),
                        t.tag === 5 && t.flags & 1024 && t.stateNode.reset(),
                        e = e.sibling
                }
        }
        function Pl(e, t) {
            if (t.subtreeFlags & 8772)
                for (t = t.child; t !== null;)
                    bl(e, t.alternate, t),
                        t = t.sibling
        }
        function Fl(e) {
            for (e = e.child; e !== null;) {
                var t = e;
                switch (t.tag) {
                    case 0:
                    case 11:
                    case 14:
                    case 15:
                        nl(4, t, t.return),
                            Fl(t);
                        break;
                    case 1:
                        ol(t, t.return);
                        var n = t.stateNode;
                        typeof n.componentWillUnmount == `function` && il(t, t.return, n),
                            Fl(t);
                        break;
                    case 27:
                        kf(t.stateNode);
                    case 26:
                    case 5:
                        ol(t, t.return),
                            Fl(t);
                        break;
                    case 22:
                        t.memoizedState === null && Fl(t);
                        break;
                    case 30:
                        Fl(t);
                        break;
                    default:
                        Fl(t)
                }
                e = e.sibling
            }
        }
        function Il(e, t, n) {
            for (n &&= (t.subtreeFlags & 8772) != 0,
                t = t.child; t !== null;) {
                var r = t.alternate
                    , i = e
                    , a = t
                    , o = a.flags;
                switch (a.tag) {
                    case 0:
                    case 11:
                    case 15:
                        Il(i, a, n),
                            tl(4, a);
                        break;
                    case 1:
                        if (Il(i, a, n),
                            r = a,
                            i = r.stateNode,
                            typeof i.componentDidMount == `function`)
                            try {
                                i.componentDidMount()
                            } catch (e) {
                                Z(r, r.return, e)
                            }
                        if (r = a,
                            i = r.updateQueue,
                            i !== null) {
                            var s = r.stateNode;
                            try {
                                var c = i.shared.hiddenCallbacks;
                                if (c !== null)
                                    for (i.shared.hiddenCallbacks = null,
                                        i = 0; i < c.length; i++)
                                        oo(c[i], s)
                            } catch (e) {
                                Z(r, r.return, e)
                            }
                        }
                        n && o & 64 && rl(a),
                            al(a, a.return);
                        break;
                    case 27:
                        pl(a);
                    case 26:
                    case 5:
                        Il(i, a, n),
                            n && r === null && o & 4 && sl(a),
                            al(a, a.return);
                        break;
                    case 12:
                        Il(i, a, n);
                        break;
                    case 31:
                        Il(i, a, n),
                            n && o & 4 && Tl(i, a);
                        break;
                    case 13:
                        Il(i, a, n),
                            n && o & 4 && El(i, a);
                        break;
                    case 22:
                        a.memoizedState === null && Il(i, a, n),
                            al(a, a.return);
                        break;
                    case 30:
                        break;
                    default:
                        Il(i, a, n)
                }
                t = t.sibling
            }
        }
        function Ll(e, t) {
            var n = null;
            e !== null && e.memoizedState !== null && e.memoizedState.cachePool !== null && (n = e.memoizedState.cachePool.pool),
                e = null,
                t.memoizedState !== null && t.memoizedState.cachePool !== null && (e = t.memoizedState.cachePool.pool),
                e !== n && (e != null && e.refCount++,
                    n != null && ya(n))
        }
        function Rl(e, t) {
            e = null,
                t.alternate !== null && (e = t.alternate.memoizedState.cache),
                t = t.memoizedState.cache,
                t !== e && (t.refCount++,
                    e != null && ya(e))
        }
        function zl(e, t, n, r) {
            if (t.subtreeFlags & 10256)
                for (t = t.child; t !== null;)
                    Bl(e, t, n, r),
                        t = t.sibling
        }
        function Bl(e, t, n, r) {
            var i = t.flags;
            switch (t.tag) {
                case 0:
                case 11:
                case 15:
                    zl(e, t, n, r),
                        i & 2048 && tl(9, t);
                    break;
                case 1:
                    zl(e, t, n, r);
                    break;
                case 3:
                    zl(e, t, n, r),
                        i & 2048 && (e = null,
                            t.alternate !== null && (e = t.alternate.memoizedState.cache),
                            t = t.memoizedState.cache,
                            t !== e && (t.refCount++,
                                e != null && ya(e)));
                    break;
                case 12:
                    if (i & 2048) {
                        zl(e, t, n, r),
                            e = t.stateNode;
                        try {
                            var a = t.memoizedProps
                                , o = a.id
                                , s = a.onPostCommit;
                            typeof s == `function` && s(o, t.alternate === null ? `mount` : `update`, e.passiveEffectDuration, -0)
                        } catch (e) {
                            Z(t, t.return, e)
                        }
                    } else
                        zl(e, t, n, r);
                    break;
                case 31:
                    zl(e, t, n, r);
                    break;
                case 13:
                    zl(e, t, n, r);
                    break;
                case 23:
                    break;
                case 22:
                    a = t.stateNode,
                        o = t.alternate,
                        t.memoizedState === null ? a._visibility & 2 ? zl(e, t, n, r) : (a._visibility |= 2,
                            Vl(e, t, n, r, (t.subtreeFlags & 10256) != 0 || !1)) : a._visibility & 2 ? zl(e, t, n, r) : Hl(e, t),
                        i & 2048 && Ll(o, t);
                    break;
                case 24:
                    zl(e, t, n, r),
                        i & 2048 && Rl(t.alternate, t);
                    break;
                default:
                    zl(e, t, n, r)
            }
        }
        function Vl(e, t, n, r, i) {
            for (i &&= (t.subtreeFlags & 10256) != 0 || !1,
                t = t.child; t !== null;) {
                var a = e
                    , o = t
                    , s = n
                    , c = r
                    , l = o.flags;
                switch (o.tag) {
                    case 0:
                    case 11:
                    case 15:
                        Vl(a, o, s, c, i),
                            tl(8, o);
                        break;
                    case 23:
                        break;
                    case 22:
                        var u = o.stateNode;
                        o.memoizedState === null ? (u._visibility |= 2,
                            Vl(a, o, s, c, i)) : u._visibility & 2 ? Vl(a, o, s, c, i) : Hl(a, o),
                            i && l & 2048 && Ll(o.alternate, o);
                        break;
                    case 24:
                        Vl(a, o, s, c, i),
                            i && l & 2048 && Rl(o.alternate, o);
                        break;
                    default:
                        Vl(a, o, s, c, i)
                }
                t = t.sibling
            }
        }
        function Hl(e, t) {
            if (t.subtreeFlags & 10256)
                for (t = t.child; t !== null;) {
                    var n = e
                        , r = t
                        , i = r.flags;
                    switch (r.tag) {
                        case 22:
                            Hl(n, r),
                                i & 2048 && Ll(r.alternate, r);
                            break;
                        case 24:
                            Hl(n, r),
                                i & 2048 && Rl(r.alternate, r);
                            break;
                        default:
                            Hl(n, r)
                    }
                    t = t.sibling
                }
        }
        var Ul = 8192;
        function Wl(e, t, n) {
            if (e.subtreeFlags & Ul)
                for (e = e.child; e !== null;)
                    Gl(e, t, n),
                        e = e.sibling
        }
        function Gl(e, t, n) {
            switch (e.tag) {
                case 26:
                    Wl(e, t, n),
                        e.flags & Ul && e.memoizedState !== null && sp(n, Al, e.memoizedState, e.memoizedProps);
                    break;
                case 5:
                    Wl(e, t, n);
                    break;
                case 3:
                case 4:
                    var r = Al;
                    Al = Mf(e.stateNode.containerInfo),
                        Wl(e, t, n),
                        Al = r;
                    break;
                case 22:
                    e.memoizedState === null && (r = e.alternate,
                        r !== null && r.memoizedState !== null ? (r = Ul,
                            Ul = 16777216,
                            Wl(e, t, n),
                            Ul = r) : Wl(e, t, n));
                    break;
                default:
                    Wl(e, t, n)
            }
        }
        function Kl(e) {
            var t = e.alternate;
            if (t !== null && (e = t.child,
                e !== null)) {
                t.child = null;
                do
                    t = e.sibling,
                        e.sibling = null,
                        e = t;
                while (e !== null)
            }
        }
        function ql(e) {
            var t = e.deletions;
            if (e.flags & 16) {
                if (t !== null)
                    for (var n = 0; n < t.length; n++) {
                        var r = t[n];
                        vl = r,
                            Xl(r, e)
                    }
                Kl(e)
            }
            if (e.subtreeFlags & 10256)
                for (e = e.child; e !== null;)
                    Jl(e),
                        e = e.sibling
        }
        function Jl(e) {
            switch (e.tag) {
                case 0:
                case 11:
                case 15:
                    ql(e),
                        e.flags & 2048 && nl(9, e, e.return);
                    break;
                case 3:
                    ql(e);
                    break;
                case 12:
                    ql(e);
                    break;
                case 22:
                    var t = e.stateNode;
                    e.memoizedState !== null && t._visibility & 2 && (e.return === null || e.return.tag !== 13) ? (t._visibility &= -3,
                        Yl(e)) : ql(e);
                    break;
                default:
                    ql(e)
            }
        }
        function Yl(e) {
            var t = e.deletions;
            if (e.flags & 16) {
                if (t !== null)
                    for (var n = 0; n < t.length; n++) {
                        var r = t[n];
                        vl = r,
                            Xl(r, e)
                    }
                Kl(e)
            }
            for (e = e.child; e !== null;) {
                switch (t = e,
                t.tag) {
                    case 0:
                    case 11:
                    case 15:
                        nl(8, t, t.return),
                            Yl(t);
                        break;
                    case 22:
                        n = t.stateNode,
                            n._visibility & 2 && (n._visibility &= -3,
                                Yl(t));
                        break;
                    default:
                        Yl(t)
                }
                e = e.sibling
            }
        }
        function Xl(e, t) {
            for (; vl !== null;) {
                var n = vl;
                switch (n.tag) {
                    case 0:
                    case 11:
                    case 15:
                        nl(8, n, t);
                        break;
                    case 23:
                    case 22:
                        if (n.memoizedState !== null && n.memoizedState.cachePool !== null) {
                            var r = n.memoizedState.cachePool.pool;
                            r != null && r.refCount++
                        }
                        break;
                    case 24:
                        ya(n.memoizedState.cache)
                }
                if (r = n.child,
                    r !== null)
                    r.return = n,
                        vl = r;
                else
                    a: for (n = e; vl !== null;) {
                        r = vl;
                        var i = r.sibling
                            , a = r.return;
                        if (xl(r),
                            r === n) {
                            vl = null;
                            break a
                        }
                        if (i !== null) {
                            i.return = a,
                                vl = i;
                            break a
                        }
                        vl = a
                    }
            }
        }
        var Zl = {
            getCacheForType: function (e) {
                var t = da(_a)
                    , n = t.data.get(e);
                return n === void 0 && (n = e(),
                    t.data.set(e, n)),
                    n
            },
            cacheSignal: function () {
                return da(_a).controller.signal
            }
        }
            , Ql = typeof WeakMap == `function` ? WeakMap : Map
            , G = 0
            , K = null
            , q = null
            , J = 0
            , Y = 0
            , $l = null
            , eu = !1
            , tu = !1
            , nu = !1
            , ru = 0
            , X = 0
            , iu = 0
            , au = 0
            , ou = 0
            , su = 0
            , cu = 0
            , lu = null
            , uu = null
            , du = !1
            , fu = 0
            , pu = 0
            , mu = 1 / 0
            , hu = null
            , gu = null
            , _u = 0
            , vu = null
            , yu = null
            , bu = 0
            , xu = 0
            , Su = null
            , Cu = null
            , wu = 0
            , Tu = null;
        function Eu() {
            return G & 2 && J !== 0 ? J & -J : k.T === null ? ot() : wd()
        }
        function Du() {
            if (su === 0)
                if (!(J & 536870912) || B) {
                    var e = Ge;
                    Ge <<= 1,
                        !(Ge & 3932160) && (Ge = 262144),
                        su = e
                } else
                    su = 536870912;
            return e = mo.current,
                e !== null && (e.flags |= 32),
                su
        }
        function Ou(e, t, n) {
            (e === K && (Y === 2 || Y === 9) || e.cancelPendingCommit !== null) && (Fu(e, 0),
                Mu(e, J, su, !1)),
                $e(e, n),
                (!(G & 2) || e !== K) && (e === K && (!(G & 2) && (au |= n),
                    X === 4 && Mu(e, J, su, !1)),
                    gd(e))
        }
        function ku(e, t, n) {
            if (G & 6)
                throw Error(i(327));
            var r = !n && (t & 127) == 0 && (t & e.expiredLanes) === 0 || Ye(e, t)
                , a = r ? Uu(e, t) : Vu(e, t, !0)
                , o = r;
            do {
                if (a === 0) {
                    tu && !r && Mu(e, t, 0, !1);
                    break
                } else {
                    if (n = e.current.alternate,
                        o && !ju(n)) {
                        a = Vu(e, t, !1),
                            o = !1;
                        continue
                    }
                    if (a === 2) {
                        if (o = t,
                            e.errorRecoveryDisabledLanes & o)
                            var s = 0;
                        else
                            s = e.pendingLanes & -536870913,
                                s = s === 0 ? s & 536870912 ? 536870912 : 0 : s;
                        if (s !== 0) {
                            t = s;
                            a: {
                                var c = e;
                                a = lu;
                                var l = c.current.memoizedState.isDehydrated;
                                if (l && (Fu(c, s).flags |= 256),
                                    s = Vu(c, s, !1),
                                    s !== 2) {
                                    if (nu && !l) {
                                        c.errorRecoveryDisabledLanes |= o,
                                            au |= o,
                                            a = 4;
                                        break a
                                    }
                                    o = uu,
                                        uu = a,
                                        o !== null && (uu === null ? uu = o : uu.push.apply(uu, o))
                                }
                                a = s
                            }
                            if (o = !1,
                                a !== 2)
                                continue
                        }
                    }
                    if (a === 1) {
                        Fu(e, 0),
                            Mu(e, t, 0, !0);
                        break
                    }
                    a: {
                        switch (r = e,
                        o = a,
                        o) {
                            case 0:
                            case 1:
                                throw Error(i(345));
                            case 4:
                                if ((t & 4194048) !== t)
                                    break;
                            case 6:
                                Mu(r, t, su, !eu);
                                break a;
                            case 2:
                                uu = null;
                                break;
                            case 3:
                            case 5:
                                break;
                            default:
                                throw Error(i(329))
                        }
                        if ((t & 62914560) === t && (a = fu + 300 - Oe(),
                            10 < a)) {
                            if (Mu(r, t, su, !eu),
                                Je(r, 0, !0) !== 0)
                                break a;
                            bu = t,
                                r.timeoutHandle = lf(Au.bind(null, r, n, uu, hu, du, t, su, au, cu, eu, o, `Throttled`, -0, 0), a);
                            break a
                        }
                        Au(r, n, uu, hu, du, t, su, au, cu, eu, o, null, -0, 0)
                    }
                }
                break
            } while (1);
            gd(e)
        }
        function Au(e, t, n, r, i, a, o, s, c, l, u, d, f, p) {
            if (e.timeoutHandle = -1,
                d = t.subtreeFlags,
                d & 8192 || (d & 16785408) == 16785408) {
                d = {
                    stylesheets: null,
                    count: 0,
                    imgCount: 0,
                    imgBytes: 0,
                    suspenseyImages: [],
                    waitingForImages: !0,
                    waitingForViewTransition: !1,
                    unsuspend: nn
                },
                    Gl(t, a, d);
                var m = (a & 62914560) === a ? fu - Oe() : (a & 4194048) === a ? pu - Oe() : 0;
                if (m = lp(d, m),
                    m !== null) {
                    bu = a,
                        e.cancelPendingCommit = m(Xu.bind(null, e, t, a, n, r, i, o, s, c, u, d, null, f, p)),
                        Mu(e, a, o, !l);
                    return
                }
            }
            Xu(e, t, a, n, r, i, o, s, c)
        }
        function ju(e) {
            for (var t = e; ;) {
                var n = t.tag;
                if ((n === 0 || n === 11 || n === 15) && t.flags & 16384 && (n = t.updateQueue,
                    n !== null && (n = n.stores,
                        n !== null)))
                    for (var r = 0; r < n.length; r++) {
                        var i = n[r]
                            , a = i.getSnapshot;
                        i = i.value;
                        try {
                            if (!Pr(a(), i))
                                return !1
                        } catch {
                            return !1
                        }
                    }
                if (n = t.child,
                    t.subtreeFlags & 16384 && n !== null)
                    n.return = t,
                        t = n;
                else {
                    if (t === e)
                        break;
                    for (; t.sibling === null;) {
                        if (t.return === null || t.return === e)
                            return !0;
                        t = t.return
                    }
                    t.sibling.return = t.return,
                        t = t.sibling
                }
            }
            return !0
        }
        function Mu(e, t, n, r) {
            t &= ~ou,
                t &= ~au,
                e.suspendedLanes |= t,
                e.pingedLanes &= ~t,
                r && (e.warmLanes |= t),
                r = e.expirationTimes;
            for (var i = t; 0 < i;) {
                var a = 31 - Be(i)
                    , o = 1 << a;
                r[a] = -1,
                    i &= ~o
            }
            n !== 0 && tt(e, n, t)
        }
        function Nu() {
            return G & 6 ? !0 : (_d(0, !1),
                !1)
        }
        function Pu() {
            if (q !== null) {
                if (Y === 0)
                    var e = q.return;
                else
                    e = q,
                        ra = na = null,
                        Bo(e),
                        Ha = null,
                        Ua = 0,
                        e = q;
                for (; e !== null;)
                    el(e.alternate, e),
                        e = e.return;
                q = null
            }
        }
        function Fu(e, t) {
            var n = e.timeoutHandle;
            n !== -1 && (e.timeoutHandle = -1,
                uf(n)),
                n = e.cancelPendingCommit,
                n !== null && (e.cancelPendingCommit = null,
                    n()),
                bu = 0,
                Pu(),
                K = e,
                q = n = xi(e.current, null),
                J = t,
                Y = 0,
                $l = null,
                eu = !1,
                tu = Ye(e, t),
                nu = !1,
                cu = su = ou = au = iu = X = 0,
                uu = lu = null,
                du = !1,
                t & 8 && (t |= t & 32);
            var r = e.entangledLanes;
            if (r !== 0)
                for (e = e.entanglements,
                    r &= t; 0 < r;) {
                    var i = 31 - Be(r)
                        , a = 1 << i;
                    t |= e[i],
                        r &= ~a
                }
            return ru = t,
                di(),
                n
        }
        function Iu(e, t) {
            V = null,
                k.H = Qs,
                t === Ma || t === Pa ? (t = Ba(),
                    Y = 3) : t === Na ? (t = Ba(),
                        Y = 4) : Y = t === gc ? 8 : typeof t == `object` && t && typeof t.then == `function` ? 6 : 1,
                $l = t,
                q === null && (X = 1,
                    uc(e, ki(t, e.current)))
        }
        function Lu() {
            var e = mo.current;
            return e === null ? !0 : (J & 4194048) === J ? ho === null : (J & 62914560) === J || J & 536870912 ? e === ho : !1
        }
        function Ru() {
            var e = k.H;
            return k.H = Qs,
                e === null ? Qs : e
        }
        function zu() {
            var e = k.A;
            return k.A = Zl,
                e
        }
        function Bu() {
            X = 4,
                eu || (J & 4194048) !== J && mo.current !== null || (tu = !0),
                !(iu & 134217727) && !(au & 134217727) || K === null || Mu(K, J, su, !1)
        }
        function Vu(e, t, n) {
            var r = G;
            G |= 2;
            var i = Ru()
                , a = zu();
            (K !== e || J !== t) && (hu = null,
                Fu(e, t)),
                t = !1;
            var o = X;
            a: do
                try {
                    if (Y !== 0 && q !== null) {
                        var s = q
                            , c = $l;
                        switch (Y) {
                            case 8:
                                Pu(),
                                    o = 6;
                                break a;
                            case 3:
                            case 2:
                            case 9:
                            case 6:
                                mo.current === null && (t = !0);
                                var l = Y;
                                if (Y = 0,
                                    $l = null,
                                    qu(e, s, c, l),
                                    n && tu) {
                                    o = 0;
                                    break a
                                }
                                break;
                            default:
                                l = Y,
                                    Y = 0,
                                    $l = null,
                                    qu(e, s, c, l)
                        }
                    }
                    Hu(),
                        o = X;
                    break
                } catch (t) {
                    Iu(e, t)
                }
            while (1);
            return t && e.shellSuspendCounter++,
                ra = na = null,
                G = r,
                k.H = i,
                k.A = a,
                q === null && (K = null,
                    J = 0,
                    di()),
                o
        }
        function Hu() {
            for (; q !== null;)
                Gu(q)
        }
        function Uu(e, t) {
            var n = G;
            G |= 2;
            var r = Ru()
                , a = zu();
            K !== e || J !== t ? (hu = null,
                mu = Oe() + 500,
                Fu(e, t)) : tu = Ye(e, t);
            a: do
                try {
                    if (Y !== 0 && q !== null) {
                        t = q;
                        var o = $l;
                        b: switch (Y) {
                            case 1:
                                Y = 0,
                                    $l = null,
                                    qu(e, t, o, 1);
                                break;
                            case 2:
                            case 9:
                                if (Ia(o)) {
                                    Y = 0,
                                        $l = null,
                                        Ku(t);
                                    break
                                }
                                t = function () {
                                    Y !== 2 && Y !== 9 || K !== e || (Y = 7),
                                        gd(e)
                                }
                                    ,
                                    o.then(t, t);
                                break a;
                            case 3:
                                Y = 7;
                                break a;
                            case 4:
                                Y = 5;
                                break a;
                            case 7:
                                Ia(o) ? (Y = 0,
                                    $l = null,
                                    Ku(t)) : (Y = 0,
                                        $l = null,
                                        qu(e, t, o, 7));
                                break;
                            case 5:
                                var s = null;
                                switch (q.tag) {
                                    case 26:
                                        s = q.memoizedState;
                                    case 5:
                                    case 27:
                                        var c = q;
                                        if (s ? op(s) : c.stateNode.complete) {
                                            Y = 0,
                                                $l = null;
                                            var l = c.sibling;
                                            if (l !== null)
                                                q = l;
                                            else {
                                                var u = c.return;
                                                u === null ? q = null : (q = u,
                                                    Ju(u))
                                            }
                                            break b
                                        }
                                }
                                Y = 0,
                                    $l = null,
                                    qu(e, t, o, 5);
                                break;
                            case 6:
                                Y = 0,
                                    $l = null,
                                    qu(e, t, o, 6);
                                break;
                            case 8:
                                Pu(),
                                    X = 6;
                                break a;
                            default:
                                throw Error(i(462))
                        }
                    }
                    Wu();
                    break
                } catch (t) {
                    Iu(e, t)
                }
            while (1);
            return ra = na = null,
                k.H = r,
                k.A = a,
                G = n,
                q === null ? (K = null,
                    J = 0,
                    di(),
                    X) : 0
        }
        function Wu() {
            for (; q !== null && !Ee();)
                Gu(q)
        }
        function Gu(e) {
            var t = Kc(e.alternate, e, ru);
            e.memoizedProps = e.pendingProps,
                t === null ? Ju(e) : q = t
        }
        function Ku(e) {
            var t = e
                , n = t.alternate;
            switch (t.tag) {
                case 15:
                case 0:
                    t = Ac(n, t, t.pendingProps, t.type, void 0, J);
                    break;
                case 11:
                    t = Ac(n, t, t.pendingProps, t.type.render, t.ref, J);
                    break;
                case 5:
                    Bo(t);
                default:
                    el(n, t),
                        t = q = Si(t, ru),
                        t = Kc(n, t, ru)
            }
            e.memoizedProps = e.pendingProps,
                t === null ? Ju(e) : q = t
        }
        function qu(e, t, n, r) {
            ra = na = null,
                Bo(t),
                Ha = null,
                Ua = 0;
            var i = t.return;
            try {
                if (hc(e, i, t, n, J)) {
                    X = 1,
                        uc(e, ki(n, e.current)),
                        q = null;
                    return
                }
            } catch (t) {
                if (i !== null)
                    throw q = i,
                    t;
                X = 1,
                    uc(e, ki(n, e.current)),
                    q = null;
                return
            }
            t.flags & 32768 ? (B || r === 1 ? e = !0 : tu || J & 536870912 ? e = !1 : (eu = e = !0,
                (r === 2 || r === 9 || r === 3 || r === 6) && (r = mo.current,
                    r !== null && r.tag === 13 && (r.flags |= 16384))),
                Yu(t, e)) : Ju(t)
        }
        function Ju(e) {
            var t = e;
            do {
                if (t.flags & 32768) {
                    Yu(t, eu);
                    return
                }
                e = t.return;
                var n = Qc(t.alternate, t, ru);
                if (n !== null) {
                    q = n;
                    return
                }
                if (t = t.sibling,
                    t !== null) {
                    q = t;
                    return
                }
                q = t = e
            } while (t !== null);
            X === 0 && (X = 5)
        }
        function Yu(e, t) {
            do {
                var n = $c(e.alternate, e);
                if (n !== null) {
                    n.flags &= 32767,
                        q = n;
                    return
                }
                if (n = e.return,
                    n !== null && (n.flags |= 32768,
                        n.subtreeFlags = 0,
                        n.deletions = null),
                    !t && (e = e.sibling,
                        e !== null)) {
                    q = e;
                    return
                }
                q = e = n
            } while (e !== null);
            X = 6,
                q = null
        }
        function Xu(e, t, n, r, a, o, s, c, l) {
            e.cancelPendingCommit = null;
            do
                td();
            while (_u !== 0);
            if (G & 6)
                throw Error(i(327));
            if (t !== null) {
                if (t === e.current)
                    throw Error(i(177));
                if (o = t.lanes | t.childLanes,
                    o |= ui,
                    et(e, n, o, s, c, l),
                    e === K && (q = K = null,
                        J = 0),
                    yu = t,
                    vu = e,
                    bu = n,
                    xu = o,
                    Su = a,
                    Cu = r,
                    t.subtreeFlags & 10256 || t.flags & 10256 ? (e.callbackNode = null,
                        e.callbackPriority = 0,
                        ld(Me, function () {
                            return nd(),
                                null
                        })) : (e.callbackNode = null,
                            e.callbackPriority = 0),
                    r = (t.flags & 13878) != 0,
                    t.subtreeFlags & 13878 || r) {
                    r = k.T,
                        k.T = null,
                        a = A.p,
                        A.p = 2,
                        s = G,
                        G |= 4;
                    try {
                        yl(e, t, n)
                    } finally {
                        G = s,
                            A.p = a,
                            k.T = r
                    }
                }
                _u = 1,
                    Zu(),
                    Qu(),
                    $u()
            }
        }
        function Zu() {
            if (_u === 1) {
                _u = 0;
                var e = vu
                    , t = yu
                    , n = (t.flags & 13878) != 0;
                if (t.subtreeFlags & 13878 || n) {
                    n = k.T,
                        k.T = null;
                    var r = A.p;
                    A.p = 2;
                    var i = G;
                    G |= 4;
                    try {
                        jl(t, e);
                        var a = ef
                            , o = Rr(e.containerInfo)
                            , s = a.focusedElem
                            , c = a.selectionRange;
                        if (o !== s && s && s.ownerDocument && Lr(s.ownerDocument.documentElement, s)) {
                            if (c !== null && zr(s)) {
                                var l = c.start
                                    , u = c.end;
                                if (u === void 0 && (u = l),
                                    `selectionStart` in s)
                                    s.selectionStart = l,
                                        s.selectionEnd = Math.min(u, s.value.length);
                                else {
                                    var d = s.ownerDocument || document
                                        , f = d && d.defaultView || window;
                                    if (f.getSelection) {
                                        var p = f.getSelection()
                                            , m = s.textContent.length
                                            , h = Math.min(c.start, m)
                                            , g = c.end === void 0 ? h : Math.min(c.end, m);
                                        !p.extend && h > g && (o = g,
                                            g = h,
                                            h = o);
                                        var _ = R(s, h)
                                            , v = R(s, g);
                                        if (_ && v && (p.rangeCount !== 1 || p.anchorNode !== _.node || p.anchorOffset !== _.offset || p.focusNode !== v.node || p.focusOffset !== v.offset)) {
                                            var y = d.createRange();
                                            y.setStart(_.node, _.offset),
                                                p.removeAllRanges(),
                                                h > g ? (p.addRange(y),
                                                    p.extend(v.node, v.offset)) : (y.setEnd(v.node, v.offset),
                                                        p.addRange(y))
                                        }
                                    }
                                }
                            }
                            for (d = [],
                                p = s; p = p.parentNode;)
                                p.nodeType === 1 && d.push({
                                    element: p,
                                    left: p.scrollLeft,
                                    top: p.scrollTop
                                });
                            for (typeof s.focus == `function` && s.focus(),
                                s = 0; s < d.length; s++) {
                                var b = d[s];
                                b.element.scrollLeft = b.left,
                                    b.element.scrollTop = b.top
                            }
                        }
                        Cp = !!$d,
                            ef = $d = null
                    } finally {
                        G = i,
                            A.p = r,
                            k.T = n
                    }
                }
                e.current = t,
                    _u = 2
            }
        }
        function Qu() {
            if (_u === 2) {
                _u = 0;
                var e = vu
                    , t = yu
                    , n = (t.flags & 8772) != 0;
                if (t.subtreeFlags & 8772 || n) {
                    n = k.T,
                        k.T = null;
                    var r = A.p;
                    A.p = 2;
                    var i = G;
                    G |= 4;
                    try {
                        bl(e, t.alternate, t)
                    } finally {
                        G = i,
                            A.p = r,
                            k.T = n
                    }
                }
                _u = 3
            }
        }
        function $u() {
            if (_u === 4 || _u === 3) {
                _u = 0,
                    De();
                var e = vu
                    , t = yu
                    , n = bu
                    , r = Cu;
                t.subtreeFlags & 10256 || t.flags & 10256 ? _u = 5 : (_u = 0,
                    yu = vu = null,
                    ed(e, e.pendingLanes));
                var i = e.pendingLanes;
                if (i === 0 && (gu = null),
                    at(n),
                    t = t.stateNode,
                    Re && typeof Re.onCommitFiberRoot == `function`)
                    try {
                        Re.onCommitFiberRoot(Le, t, void 0, (t.current.flags & 128) == 128)
                    } catch { }
                if (r !== null) {
                    t = k.T,
                        i = A.p,
                        A.p = 2,
                        k.T = null;
                    try {
                        for (var a = e.onRecoverableError, o = 0; o < r.length; o++) {
                            var s = r[o];
                            a(s.value, {
                                componentStack: s.stack
                            })
                        }
                    } finally {
                        k.T = t,
                            A.p = i
                    }
                }
                bu & 3 && td(),
                    gd(e),
                    i = e.pendingLanes,
                    n & 261930 && i & 42 ? e === Tu ? wu++ : (wu = 0,
                        Tu = e) : wu = 0,
                    _d(0, !1)
            }
        }
        function ed(e, t) {
            (e.pooledCacheLanes &= t) === 0 && (t = e.pooledCache,
                t != null && (e.pooledCache = null,
                    ya(t)))
        }
        function td() {
            return Zu(),
                Qu(),
                $u(),
                nd()
        }
        function nd() {
            if (_u !== 5)
                return !1;
            var e = vu
                , t = xu;
            xu = 0;
            var n = at(bu)
                , r = k.T
                , a = A.p;
            try {
                A.p = 32 > n ? 32 : n,
                    k.T = null,
                    n = Su,
                    Su = null;
                var o = vu
                    , s = bu;
                if (_u = 0,
                    yu = vu = null,
                    bu = 0,
                    G & 6)
                    throw Error(i(331));
                var c = G;
                if (G |= 4,
                    Jl(o.current),
                    Bl(o, o.current, s, n),
                    G = c,
                    _d(0, !1),
                    Re && typeof Re.onPostCommitFiberRoot == `function`)
                    try {
                        Re.onPostCommitFiberRoot(Le, o)
                    } catch { }
                return !0
            } finally {
                A.p = a,
                    k.T = r,
                    ed(e, t)
            }
        }
        function rd(e, t, n) {
            t = ki(n, t),
                t = fc(e.stateNode, t, 2),
                e = eo(e, t, 2),
                e !== null && ($e(e, 2),
                    gd(e))
        }
        function Z(e, t, n) {
            if (e.tag === 3)
                rd(e, e, n);
            else
                for (; t !== null;) {
                    if (t.tag === 3) {
                        rd(t, e, n);
                        break
                    } else if (t.tag === 1) {
                        var r = t.stateNode;
                        if (typeof t.type.getDerivedStateFromError == `function` || typeof r.componentDidCatch == `function` && (gu === null || !gu.has(r))) {
                            e = ki(n, e),
                                n = pc(2),
                                r = eo(t, n, 2),
                                r !== null && (mc(n, r, t, e),
                                    $e(r, 2),
                                    gd(r));
                            break
                        }
                    }
                    t = t.return
                }
        }
        function id(e, t, n) {
            var r = e.pingCache;
            if (r === null) {
                r = e.pingCache = new Ql;
                var i = new Set;
                r.set(t, i)
            } else
                i = r.get(t),
                    i === void 0 && (i = new Set,
                        r.set(t, i));
            i.has(n) || (nu = !0,
                i.add(n),
                e = ad.bind(null, e, t, n),
                t.then(e, e))
        }
        function ad(e, t, n) {
            var r = e.pingCache;
            r !== null && r.delete(t),
                e.pingedLanes |= e.suspendedLanes & n,
                e.warmLanes &= ~n,
                K === e && (J & n) === n && (X === 4 || X === 3 && (J & 62914560) === J && 300 > Oe() - fu ? !(G & 2) && Fu(e, 0) : ou |= n,
                    cu === J && (cu = 0)),
                gd(e)
        }
        function od(e, t) {
            t === 0 && (t = Ze()),
                e = mi(e, t),
                e !== null && ($e(e, t),
                    gd(e))
        }
        function sd(e) {
            var t = e.memoizedState
                , n = 0;
            t !== null && (n = t.retryLane),
                od(e, n)
        }
        function cd(e, t) {
            var n = 0;
            switch (e.tag) {
                case 31:
                case 13:
                    var r = e.stateNode
                        , a = e.memoizedState;
                    a !== null && (n = a.retryLane);
                    break;
                case 19:
                    r = e.stateNode;
                    break;
                case 22:
                    r = e.stateNode._retryCache;
                    break;
                default:
                    throw Error(i(314))
            }
            r !== null && r.delete(t),
                od(e, n)
        }
        function ld(e, t) {
            return we(e, t)
        }
        var ud = null
            , dd = null
            , fd = !1
            , pd = !1
            , md = !1
            , hd = 0;
        function gd(e) {
            e !== dd && e.next === null && (dd === null ? ud = dd = e : dd = dd.next = e),
                pd = !0,
                fd || (fd = !0,
                    Cd())
        }
        function _d(e, t) {
            if (!md && pd) {
                md = !0;
                do
                    for (var n = !1, r = ud; r !== null;) {
                        if (!t)
                            if (e !== 0) {
                                var i = r.pendingLanes;
                                if (i === 0)
                                    var a = 0;
                                else {
                                    var o = r.suspendedLanes
                                        , s = r.pingedLanes;
                                    a = (1 << 31 - Be(42 | e) + 1) - 1,
                                        a &= i & ~(o & ~s),
                                        a = a & 201326741 ? a & 201326741 | 1 : a ? a | 2 : 0
                                }
                                a !== 0 && (n = !0,
                                    Sd(r, a))
                            } else
                                a = J,
                                    a = Je(r, r === K ? a : 0, r.cancelPendingCommit !== null || r.timeoutHandle !== -1),
                                    !(a & 3) || Ye(r, a) || (n = !0,
                                        Sd(r, a));
                        r = r.next
                    }
                while (n);
                md = !1
            }
        }
        function vd() {
            yd()
        }
        function yd() {
            pd = fd = !1;
            var e = 0;
            hd !== 0 && cf() && (e = hd);
            for (var t = Oe(), n = null, r = ud; r !== null;) {
                var i = r.next
                    , a = bd(r, t);
                a === 0 ? (r.next = null,
                    n === null ? ud = i : n.next = i,
                    i === null && (dd = n)) : (n = r,
                        (e !== 0 || a & 3) && (pd = !0)),
                    r = i
            }
            _u !== 0 && _u !== 5 || _d(e, !1),
                hd !== 0 && (hd = 0)
        }
        function bd(e, t) {
            for (var n = e.suspendedLanes, r = e.pingedLanes, i = e.expirationTimes, a = e.pendingLanes & -62914561; 0 < a;) {
                var o = 31 - Be(a)
                    , s = 1 << o
                    , c = i[o];
                c === -1 ? ((s & n) === 0 || (s & r) !== 0) && (i[o] = Xe(s, t)) : c <= t && (e.expiredLanes |= s),
                    a &= ~s
            }
            if (t = K,
                n = J,
                n = Je(e, e === t ? n : 0, e.cancelPendingCommit !== null || e.timeoutHandle !== -1),
                r = e.callbackNode,
                n === 0 || e === t && (Y === 2 || Y === 9) || e.cancelPendingCommit !== null)
                return r !== null && r !== null && Te(r),
                    e.callbackNode = null,
                    e.callbackPriority = 0;
            if (!(n & 3) || Ye(e, n)) {
                if (t = n & -n,
                    t === e.callbackPriority)
                    return t;
                switch (r !== null && Te(r),
                at(n)) {
                    case 2:
                    case 8:
                        n = je;
                        break;
                    case 32:
                        n = Me;
                        break;
                    case 268435456:
                        n = Pe;
                        break;
                    default:
                        n = Me
                }
                return r = xd.bind(null, e),
                    n = we(n, r),
                    e.callbackPriority = t,
                    e.callbackNode = n,
                    t
            }
            return r !== null && r !== null && Te(r),
                e.callbackPriority = 2,
                e.callbackNode = null,
                2
        }
        function xd(e, t) {
            if (_u !== 0 && _u !== 5)
                return e.callbackNode = null,
                    e.callbackPriority = 0,
                    null;
            var n = e.callbackNode;
            if (td() && e.callbackNode !== n)
                return null;
            var r = J;
            return r = Je(e, e === K ? r : 0, e.cancelPendingCommit !== null || e.timeoutHandle !== -1),
                r === 0 ? null : (ku(e, r, t),
                    bd(e, Oe()),
                    e.callbackNode != null && e.callbackNode === n ? xd.bind(null, e) : null)
        }
        function Sd(e, t) {
            if (td())
                return null;
            ku(e, t, !0)
        }
        function Cd() {
            ff(function () {
                G & 6 ? we(Ae, vd) : yd()
            })
        }
        function wd() {
            if (hd === 0) {
                var e = Sa;
                e === 0 && (e = We,
                    We <<= 1,
                    !(We & 261888) && (We = 256)),
                    hd = e
            }
            return hd
        }
        function Td(e) {
            return e == null || typeof e == `symbol` || typeof e == `boolean` ? null : typeof e == `function` ? e : tn(`` + e)
        }
        function Ed(e, t) {
            var n = t.ownerDocument.createElement(`input`);
            return n.name = t.name,
                n.value = t.value,
                e.id && n.setAttribute(`form`, e.id),
                t.parentNode.insertBefore(n, t),
                e = new FormData(e),
                n.parentNode.removeChild(n),
                e
        }
        function Dd(e, t, n, r, i) {
            if (t === `submit` && n && n.stateNode === i) {
                var a = Td((i[ut] || null).action)
                    , o = r.submitter;
                o && (t = (t = o[ut] || null) ? Td(t.formAction) : o.getAttribute(`formAction`),
                    t !== null && (a = t,
                        o = null));
                var s = new Sn(`action`, `action`, null, r, i);
                e.push({
                    event: s,
                    listeners: [{
                        instance: null,
                        listener: function () {
                            if (r.defaultPrevented) {
                                if (hd !== 0) {
                                    var e = o ? Ed(i, o) : new FormData(i);
                                    Rs(n, {
                                        pending: !0,
                                        data: e,
                                        method: i.method,
                                        action: a
                                    }, null, e)
                                }
                            } else
                                typeof a == `function` && (s.preventDefault(),
                                    e = o ? Ed(i, o) : new FormData(i),
                                    Rs(n, {
                                        pending: !0,
                                        data: e,
                                        method: i.method,
                                        action: a
                                    }, a, e))
                        },
                        currentTarget: i
                    }]
                })
            }
        }
        for (var Od = 0; Od < ai.length; Od++) {
            var kd = ai[Od]
                , Ad = kd.toLowerCase()
                , jd = kd[0].toUpperCase() + kd.slice(1);
            oi(Ad, `on` + jd)
        }
        oi(Zr, `onAnimationEnd`),
            oi(Qr, `onAnimationIteration`),
            oi($r, `onAnimationStart`),
            oi(`dblclick`, `onDoubleClick`),
            oi(`focusin`, `onFocus`),
            oi(`focusout`, `onBlur`),
            oi(ei, `onTransitionRun`),
            oi(ti, `onTransitionStart`),
            oi(ni, `onTransitionCancel`),
            oi(ri, `onTransitionEnd`),
            Et(`onMouseEnter`, [`mouseout`, `mouseover`]),
            Et(`onMouseLeave`, [`mouseout`, `mouseover`]),
            Et(`onPointerEnter`, [`pointerout`, `pointerover`]),
            Et(`onPointerLeave`, [`pointerout`, `pointerover`]),
            Tt(`onChange`, `change click focusin focusout input keydown keyup selectionchange`.split(` `)),
            Tt(`onSelect`, `focusout contextmenu dragend focusin keydown keyup mousedown mouseup selectionchange`.split(` `)),
            Tt(`onBeforeInput`, [`compositionend`, `keypress`, `textInput`, `paste`]),
            Tt(`onCompositionEnd`, `compositionend focusout keydown keypress keyup mousedown`.split(` `)),
            Tt(`onCompositionStart`, `compositionstart focusout keydown keypress keyup mousedown`.split(` `)),
            Tt(`onCompositionUpdate`, `compositionupdate focusout keydown keypress keyup mousedown`.split(` `));
        var Md = `abort canplay canplaythrough durationchange emptied encrypted ended error loadeddata loadedmetadata loadstart pause play playing progress ratechange resize seeked seeking stalled suspend timeupdate volumechange waiting`.split(` `)
            , Nd = new Set(`beforetoggle cancel close invalid load scroll scrollend toggle`.split(` `).concat(Md));
        function Pd(e, t) {
            t = (t & 4) != 0;
            for (var n = 0; n < e.length; n++) {
                var r = e[n]
                    , i = r.event;
                r = r.listeners;
                a: {
                    var a = void 0;
                    if (t)
                        for (var o = r.length - 1; 0 <= o; o--) {
                            var s = r[o]
                                , c = s.instance
                                , l = s.currentTarget;
                            if (s = s.listener,
                                c !== a && i.isPropagationStopped())
                                break a;
                            a = s,
                                i.currentTarget = l;
                            try {
                                a(i)
                            } catch (e) {
                                si(e)
                            }
                            i.currentTarget = null,
                                a = c
                        }
                    else
                        for (o = 0; o < r.length; o++) {
                            if (s = r[o],
                                c = s.instance,
                                l = s.currentTarget,
                                s = s.listener,
                                c !== a && i.isPropagationStopped())
                                break a;
                            a = s,
                                i.currentTarget = l;
                            try {
                                a(i)
                            } catch (e) {
                                si(e)
                            }
                            i.currentTarget = null,
                                a = c
                        }
                }
            }
        }
        function Q(e, t) {
            var n = t[ft];
            n === void 0 && (n = t[ft] = new Set);
            var r = e + `__bubble`;
            n.has(r) || (Rd(t, e, 2, !1),
                n.add(r))
        }
        function Fd(e, t, n) {
            var r = 0;
            t && (r |= 4),
                Rd(n, e, r, t)
        }
        var Id = `_reactListening` + Math.random().toString(36).slice(2);
        function Ld(e) {
            if (!e[Id]) {
                e[Id] = !0,
                    Ct.forEach(function (t) {
                        t !== `selectionchange` && (Nd.has(t) || Fd(t, !1, e),
                            Fd(t, !0, e))
                    });
                var t = e.nodeType === 9 ? e : e.ownerDocument;
                t === null || t[Id] || (t[Id] = !0,
                    Fd(`selectionchange`, !1, t))
            }
        }
        function Rd(e, t, n, r) {
            switch (Ap(t)) {
                case 2:
                    var i = wp;
                    break;
                case 8:
                    i = Tp;
                    break;
                default:
                    i = Ep
            }
            n = i.bind(null, t, n, e),
                i = void 0,
                !dn || t !== `touchstart` && t !== `touchmove` && t !== `wheel` || (i = !0),
                r ? i === void 0 ? e.addEventListener(t, n, !0) : e.addEventListener(t, n, {
                    capture: !0,
                    passive: i
                }) : i === void 0 ? e.addEventListener(t, n, !1) : e.addEventListener(t, n, {
                    passive: i
                })
        }
        function zd(e, t, n, r, i) {
            var a = r;
            if (!(t & 1) && !(t & 2) && r !== null)
                a: for (; ;) {
                    if (r === null)
                        return;
                    var s = r.tag;
                    if (s === 3 || s === 4) {
                        var c = r.stateNode.containerInfo;
                        if (c === i)
                            break;
                        if (s === 4)
                            for (s = r.return; s !== null;) {
                                var l = s.tag;
                                if ((l === 3 || l === 4) && s.stateNode.containerInfo === i)
                                    return;
                                s = s.return
                            }
                        for (; c !== null;) {
                            if (s = vt(c),
                                s === null)
                                return;
                            if (l = s.tag,
                                l === 5 || l === 6 || l === 26 || l === 27) {
                                r = a = s;
                                continue a
                            }
                            c = c.parentNode
                        }
                    }
                    r = r.return
                }
            cn(function () {
                var r = a
                    , i = an(n)
                    , s = [];
                a: {
                    var c = ii.get(e);
                    if (c !== void 0) {
                        var l = Sn
                            , u = e;
                        switch (e) {
                            case `keypress`:
                                if (_n(n) === 0)
                                    break a;
                            case `keydown`:
                            case `keyup`:
                                l = Kn;
                                break;
                            case `focusin`:
                                u = `focus`,
                                    l = Nn;
                                break;
                            case `focusout`:
                                u = `blur`,
                                    l = Nn;
                                break;
                            case `beforeblur`:
                            case `afterblur`:
                                l = Nn;
                                break;
                            case `click`:
                                if (n.button === 2)
                                    break a;
                            case `auxclick`:
                            case `dblclick`:
                            case `mousedown`:
                            case `mousemove`:
                            case `mouseup`:
                            case `mouseout`:
                            case `mouseover`:
                            case `contextmenu`:
                                l = kn;
                                break;
                            case `drag`:
                            case `dragend`:
                            case `dragenter`:
                            case `dragexit`:
                            case `dragleave`:
                            case `dragover`:
                            case `dragstart`:
                            case `drop`:
                                l = jn;
                                break;
                            case `touchcancel`:
                            case `touchend`:
                            case `touchmove`:
                            case `touchstart`:
                                l = Xn;
                                break;
                            case Zr:
                            case Qr:
                            case $r:
                                l = Fn;
                                break;
                            case ri:
                                l = Qn;
                                break;
                            case `scroll`:
                            case `scrollend`:
                                l = wn;
                                break;
                            case `wheel`:
                                l = er;
                                break;
                            case `copy`:
                            case `cut`:
                            case `paste`:
                                l = Ln;
                                break;
                            case `gotpointercapture`:
                            case `lostpointercapture`:
                            case `pointercancel`:
                            case `pointerdown`:
                            case `pointermove`:
                            case `pointerout`:
                            case `pointerover`:
                            case `pointerup`:
                                l = Jn;
                                break;
                            case `toggle`:
                            case `beforetoggle`:
                                l = nr
                        }
                        var d = (t & 4) != 0
                            , f = !d && (e === `scroll` || e === `scrollend`)
                            , p = d ? c === null ? null : c + `Capture` : c;
                        d = [];
                        for (var m = r, h; m !== null;) {
                            var g = m;
                            if (h = g.stateNode,
                                g = g.tag,
                                g !== 5 && g !== 26 && g !== 27 || h === null || p === null || (g = ln(m, p),
                                    g != null && d.push(Bd(m, g, h))),
                                f)
                                break;
                            m = m.return
                        }
                        0 < d.length && (c = new l(c, u, null, n, i),
                            s.push({
                                event: c,
                                listeners: d
                            }))
                    }
                }
                if (!(t & 7)) {
                    a: {
                        if (c = e === `mouseover` || e === `pointerover`,
                            l = e === `mouseout` || e === `pointerout`,
                            c && n !== rn && (u = n.relatedTarget || n.fromElement) && (vt(u) || u[dt]))
                            break a;
                        if ((l || c) && (c = i.window === i ? i : (c = i.ownerDocument) ? c.defaultView || c.parentWindow : window,
                            l ? (u = n.relatedTarget || n.toElement,
                                l = r,
                                u = u ? vt(u) : null,
                                u !== null && (f = o(u),
                                    d = u.tag,
                                    u !== f || d !== 5 && d !== 27 && d !== 6) && (u = null)) : (l = null,
                                        u = r),
                            l !== u)) {
                            if (d = kn,
                                g = `onMouseLeave`,
                                p = `onMouseEnter`,
                                m = `mouse`,
                                (e === `pointerout` || e === `pointerover`) && (d = Jn,
                                    g = `onPointerLeave`,
                                    p = `onPointerEnter`,
                                    m = `pointer`),
                                f = l == null ? c : bt(l),
                                h = u == null ? c : bt(u),
                                c = new d(g, m + `leave`, l, n, i),
                                c.target = f,
                                c.relatedTarget = h,
                                g = null,
                                vt(i) === r && (d = new d(p, m + `enter`, u, n, i),
                                    d.target = h,
                                    d.relatedTarget = f,
                                    g = d),
                                f = g,
                                l && u)
                                b: {
                                    for (d = Hd,
                                        p = l,
                                        m = u,
                                        h = 0,
                                        g = p; g; g = d(g))
                                        h++;
                                    g = 0;
                                    for (var _ = m; _; _ = d(_))
                                        g++;
                                    for (; 0 < h - g;)
                                        p = d(p),
                                            h--;
                                    for (; 0 < g - h;)
                                        m = d(m),
                                            g--;
                                    for (; h--;) {
                                        if (p === m || m !== null && p === m.alternate) {
                                            d = p;
                                            break b
                                        }
                                        p = d(p),
                                            m = d(m)
                                    }
                                    d = null
                                }
                            else
                                d = null;
                            l !== null && Ud(s, c, l, d, !1),
                                u !== null && f !== null && Ud(s, f, u, d, !0)
                        }
                    }
                    a: {
                        if (c = r ? bt(r) : window,
                            l = c.nodeName && c.nodeName.toLowerCase(),
                            l === `select` || l === `input` && c.type === `file`)
                            var v = Sr;
                        else if (gr(c))
                            if (Cr)
                                v = Mr;
                            else {
                                v = Ar;
                                var y = kr
                            }
                        else
                            l = c.nodeName,
                                !l || l.toLowerCase() !== `input` || c.type !== `checkbox` && c.type !== `radio` ? r && Qt(r.elementType) && (v = Sr) : v = jr;
                        if (v &&= v(e, r)) {
                            _r(s, v, n, i);
                            break a
                        }
                        y && y(e, c, r),
                            e === `focusout` && r && c.type === `number` && r.memoizedProps.value != null && Wt(c, `number`, c.value)
                    }
                    switch (y = r ? bt(r) : window,
                    e) {
                        case `focusin`:
                            (gr(y) || y.contentEditable === `true`) && (Vr = y,
                                Hr = r,
                                Ur = null);
                            break;
                        case `focusout`:
                            Ur = Hr = Vr = null;
                            break;
                        case `mousedown`:
                            Wr = !0;
                            break;
                        case `contextmenu`:
                        case `mouseup`:
                        case `dragend`:
                            Wr = !1,
                                Gr(s, n, i);
                            break;
                        case `selectionchange`:
                            if (Br)
                                break;
                        case `keydown`:
                        case `keyup`:
                            Gr(s, n, i)
                    }
                    var b;
                    if (ir)
                        b: {
                            switch (e) {
                                case `compositionstart`:
                                    var x = `onCompositionStart`;
                                    break b;
                                case `compositionend`:
                                    x = `onCompositionEnd`;
                                    break b;
                                case `compositionupdate`:
                                    x = `onCompositionUpdate`;
                                    break b
                            }
                            x = void 0
                        }
                    else
                        fr ? ur(e, n) && (x = `onCompositionEnd`) : e === `keydown` && n.keyCode === 229 && (x = `onCompositionStart`);
                    x && (sr && n.locale !== `ko` && (fr || x !== `onCompositionStart` ? x === `onCompositionEnd` && fr && (b = gn()) : (pn = i,
                        mn = `value` in pn ? pn.value : pn.textContent,
                        fr = !0)),
                        y = Vd(r, x),
                        0 < y.length && (x = new zn(x, e, null, n, i),
                            s.push({
                                event: x,
                                listeners: y
                            }),
                            b ? x.data = b : (b = dr(n),
                                b !== null && (x.data = b)))),
                        (b = or ? pr(e, n) : mr(e, n)) && (x = Vd(r, `onBeforeInput`),
                            0 < x.length && (y = new zn(`onBeforeInput`, `beforeinput`, null, n, i),
                                s.push({
                                    event: y,
                                    listeners: x
                                }),
                                y.data = b)),
                        Dd(s, e, r, n, i)
                }
                Pd(s, t)
            })
        }
        function Bd(e, t, n) {
            return {
                instance: e,
                listener: t,
                currentTarget: n
            }
        }
        function Vd(e, t) {
            for (var n = t + `Capture`, r = []; e !== null;) {
                var i = e
                    , a = i.stateNode;
                if (i = i.tag,
                    i !== 5 && i !== 26 && i !== 27 || a === null || (i = ln(e, n),
                        i != null && r.unshift(Bd(e, i, a)),
                        i = ln(e, t),
                        i != null && r.push(Bd(e, i, a))),
                    e.tag === 3)
                    return r;
                e = e.return
            }
            return []
        }
        function Hd(e) {
            if (e === null)
                return null;
            do
                e = e.return;
            while (e && e.tag !== 5 && e.tag !== 27);
            return e || null
        }
        function Ud(e, t, n, r, i) {
            for (var a = t._reactName, o = []; n !== null && n !== r;) {
                var s = n
                    , c = s.alternate
                    , l = s.stateNode;
                if (s = s.tag,
                    c !== null && c === r)
                    break;
                s !== 5 && s !== 26 && s !== 27 || l === null || (c = l,
                    i ? (l = ln(n, a),
                        l != null && o.unshift(Bd(n, l, c))) : i || (l = ln(n, a),
                            l != null && o.push(Bd(n, l, c)))),
                    n = n.return
            }
            o.length !== 0 && e.push({
                event: t,
                listeners: o
            })
        }
        var Wd = /\r\n?/g
            , Gd = /\u0000|\uFFFD/g;
        function Kd(e) {
            return (typeof e == `string` ? e : `` + e).replace(Wd, `
`).replace(Gd, ``)
        }
        function qd(e, t) {
            return t = Kd(t),
                Kd(e) === t
        }
        function $(e, t, n, r, a, o) {
            switch (n) {
                case `children`:
                    typeof r == `string` ? t === `body` || t === `textarea` && r === `` || Jt(e, r) : (typeof r == `number` || typeof r == `bigint`) && t !== `body` && Jt(e, `` + r);
                    break;
                case `className`:
                    Mt(e, `class`, r);
                    break;
                case `tabIndex`:
                    Mt(e, `tabindex`, r);
                    break;
                case `dir`:
                case `role`:
                case `viewBox`:
                case `width`:
                case `height`:
                    Mt(e, n, r);
                    break;
                case `style`:
                    Zt(e, r, o);
                    break;
                case `data`:
                    if (t !== `object`) {
                        Mt(e, `data`, r);
                        break
                    }
                case `src`:
                case `href`:
                    if (r === `` && (t !== `a` || n !== `href`)) {
                        e.removeAttribute(n);
                        break
                    }
                    if (r == null || typeof r == `function` || typeof r == `symbol` || typeof r == `boolean`) {
                        e.removeAttribute(n);
                        break
                    }
                    r = tn(`` + r),
                        e.setAttribute(n, r);
                    break;
                case `action`:
                case `formAction`:
                    if (typeof r == `function`) {
                        e.setAttribute(n, `javascript:throw new Error('A React form was unexpectedly submitted. If you called form.submit() manually, consider using form.requestSubmit() instead. If you\\'re trying to use event.stopPropagation() in a submit event handler, consider also calling event.preventDefault().')`);
                        break
                    } else
                        typeof o == `function` && (n === `formAction` ? (t !== `input` && $(e, t, `name`, a.name, a, null),
                            $(e, t, `formEncType`, a.formEncType, a, null),
                            $(e, t, `formMethod`, a.formMethod, a, null),
                            $(e, t, `formTarget`, a.formTarget, a, null)) : ($(e, t, `encType`, a.encType, a, null),
                                $(e, t, `method`, a.method, a, null),
                                $(e, t, `target`, a.target, a, null)));
                    if (r == null || typeof r == `symbol` || typeof r == `boolean`) {
                        e.removeAttribute(n);
                        break
                    }
                    r = tn(`` + r),
                        e.setAttribute(n, r);
                    break;
                case `onClick`:
                    r != null && (e.onclick = nn);
                    break;
                case `onScroll`:
                    r != null && Q(`scroll`, e);
                    break;
                case `onScrollEnd`:
                    r != null && Q(`scrollend`, e);
                    break;
                case `dangerouslySetInnerHTML`:
                    if (r != null) {
                        if (typeof r != `object` || !(`__html` in r))
                            throw Error(i(61));
                        if (n = r.__html,
                            n != null) {
                            if (a.children != null)
                                throw Error(i(60));
                            e.innerHTML = n
                        }
                    }
                    break;
                case `multiple`:
                    e.multiple = r && typeof r != `function` && typeof r != `symbol`;
                    break;
                case `muted`:
                    e.muted = r && typeof r != `function` && typeof r != `symbol`;
                    break;
                case `suppressContentEditableWarning`:
                case `suppressHydrationWarning`:
                case `defaultValue`:
                case `defaultChecked`:
                case `innerHTML`:
                case `ref`:
                    break;
                case `autoFocus`:
                    break;
                case `xlinkHref`:
                    if (r == null || typeof r == `function` || typeof r == `boolean` || typeof r == `symbol`) {
                        e.removeAttribute(`xlink:href`);
                        break
                    }
                    n = tn(`` + r),
                        e.setAttributeNS(`http://www.w3.org/1999/xlink`, `xlink:href`, n);
                    break;
                case `contentEditable`:
                case `spellCheck`:
                case `draggable`:
                case `value`:
                case `autoReverse`:
                case `externalResourcesRequired`:
                case `focusable`:
                case `preserveAlpha`:
                    r != null && typeof r != `function` && typeof r != `symbol` ? e.setAttribute(n, `` + r) : e.removeAttribute(n);
                    break;
                case `inert`:
                case `allowFullScreen`:
                case `async`:
                case `autoPlay`:
                case `controls`:
                case `default`:
                case `defer`:
                case `disabled`:
                case `disablePictureInPicture`:
                case `disableRemotePlayback`:
                case `formNoValidate`:
                case `hidden`:
                case `loop`:
                case `noModule`:
                case `noValidate`:
                case `open`:
                case `playsInline`:
                case `readOnly`:
                case `required`:
                case `reversed`:
                case `scoped`:
                case `seamless`:
                case `itemScope`:
                    r && typeof r != `function` && typeof r != `symbol` ? e.setAttribute(n, ``) : e.removeAttribute(n);
                    break;
                case `capture`:
                case `download`:
                    !0 === r ? e.setAttribute(n, ``) : !1 !== r && r != null && typeof r != `function` && typeof r != `symbol` ? e.setAttribute(n, r) : e.removeAttribute(n);
                    break;
                case `cols`:
                case `rows`:
                case `size`:
                case `span`:
                    r != null && typeof r != `function` && typeof r != `symbol` && !isNaN(r) && 1 <= r ? e.setAttribute(n, r) : e.removeAttribute(n);
                    break;
                case `rowSpan`:
                case `start`:
                    r == null || typeof r == `function` || typeof r == `symbol` || isNaN(r) ? e.removeAttribute(n) : e.setAttribute(n, r);
                    break;
                case `popover`:
                    Q(`beforetoggle`, e),
                        Q(`toggle`, e),
                        jt(e, `popover`, r);
                    break;
                case `xlinkActuate`:
                    Nt(e, `http://www.w3.org/1999/xlink`, `xlink:actuate`, r);
                    break;
                case `xlinkArcrole`:
                    Nt(e, `http://www.w3.org/1999/xlink`, `xlink:arcrole`, r);
                    break;
                case `xlinkRole`:
                    Nt(e, `http://www.w3.org/1999/xlink`, `xlink:role`, r);
                    break;
                case `xlinkShow`:
                    Nt(e, `http://www.w3.org/1999/xlink`, `xlink:show`, r);
                    break;
                case `xlinkTitle`:
                    Nt(e, `http://www.w3.org/1999/xlink`, `xlink:title`, r);
                    break;
                case `xlinkType`:
                    Nt(e, `http://www.w3.org/1999/xlink`, `xlink:type`, r);
                    break;
                case `xmlBase`:
                    Nt(e, `http://www.w3.org/XML/1998/namespace`, `xml:base`, r);
                    break;
                case `xmlLang`:
                    Nt(e, `http://www.w3.org/XML/1998/namespace`, `xml:lang`, r);
                    break;
                case `xmlSpace`:
                    Nt(e, `http://www.w3.org/XML/1998/namespace`, `xml:space`, r);
                    break;
                case `is`:
                    jt(e, `is`, r);
                    break;
                case `innerText`:
                case `textContent`:
                    break;
                default:
                    (!(2 < n.length) || n[0] !== `o` && n[0] !== `O` || n[1] !== `n` && n[1] !== `N`) && (n = $t.get(n) || n,
                        jt(e, n, r))
            }
        }
        function Jd(e, t, n, r, a, o) {
            switch (n) {
                case `style`:
                    Zt(e, r, o);
                    break;
                case `dangerouslySetInnerHTML`:
                    if (r != null) {
                        if (typeof r != `object` || !(`__html` in r))
                            throw Error(i(61));
                        if (n = r.__html,
                            n != null) {
                            if (a.children != null)
                                throw Error(i(60));
                            e.innerHTML = n
                        }
                    }
                    break;
                case `children`:
                    typeof r == `string` ? Jt(e, r) : (typeof r == `number` || typeof r == `bigint`) && Jt(e, `` + r);
                    break;
                case `onScroll`:
                    r != null && Q(`scroll`, e);
                    break;
                case `onScrollEnd`:
                    r != null && Q(`scrollend`, e);
                    break;
                case `onClick`:
                    r != null && (e.onclick = nn);
                    break;
                case `suppressContentEditableWarning`:
                case `suppressHydrationWarning`:
                case `innerHTML`:
                case `ref`:
                    break;
                case `innerText`:
                case `textContent`:
                    break;
                default:
                    if (!wt.hasOwnProperty(n))
                        a: {
                            if (n[0] === `o` && n[1] === `n` && (a = n.endsWith(`Capture`),
                                t = n.slice(2, a ? n.length - 7 : void 0),
                                o = e[ut] || null,
                                o = o == null ? null : o[n],
                                typeof o == `function` && e.removeEventListener(t, o, a),
                                typeof r == `function`)) {
                                typeof o != `function` && o !== null && (n in e ? e[n] = null : e.hasAttribute(n) && e.removeAttribute(n)),
                                    e.addEventListener(t, r, a);
                                break a
                            }
                            n in e ? e[n] = r : !0 === r ? e.setAttribute(n, ``) : jt(e, n, r)
                        }
            }
        }
        function Yd(e, t, n) {
            switch (t) {
                case `div`:
                case `span`:
                case `svg`:
                case `path`:
                case `a`:
                case `g`:
                case `p`:
                case `li`:
                    break;
                case `img`:
                    Q(`error`, e),
                        Q(`load`, e);
                    var r = !1, a = !1, o;
                    for (o in n)
                        if (n.hasOwnProperty(o)) {
                            var s = n[o];
                            if (s != null)
                                switch (o) {
                                    case `src`:
                                        r = !0;
                                        break;
                                    case `srcSet`:
                                        a = !0;
                                        break;
                                    case `children`:
                                    case `dangerouslySetInnerHTML`:
                                        throw Error(i(137, t));
                                    default:
                                        $(e, t, o, s, n, null)
                                }
                        }
                    a && $(e, t, `srcSet`, n.srcSet, n, null),
                        r && $(e, t, `src`, n.src, n, null);
                    return;
                case `input`:
                    Q(`invalid`, e);
                    var c = o = s = a = null
                        , l = null
                        , u = null;
                    for (r in n)
                        if (n.hasOwnProperty(r)) {
                            var d = n[r];
                            if (d != null)
                                switch (r) {
                                    case `name`:
                                        a = d;
                                        break;
                                    case `type`:
                                        s = d;
                                        break;
                                    case `checked`:
                                        l = d;
                                        break;
                                    case `defaultChecked`:
                                        u = d;
                                        break;
                                    case `value`:
                                        o = d;
                                        break;
                                    case `defaultValue`:
                                        c = d;
                                        break;
                                    case `children`:
                                    case `dangerouslySetInnerHTML`:
                                        if (d != null)
                                            throw Error(i(137, t));
                                        break;
                                    default:
                                        $(e, t, r, d, n, null)
                                }
                        }
                    Ut(e, o, c, l, u, s, a, !1);
                    return;
                case `select`:
                    for (a in Q(`invalid`, e),
                        r = s = o = null,
                        n)
                        if (n.hasOwnProperty(a) && (c = n[a],
                            c != null))
                            switch (a) {
                                case `value`:
                                    o = c;
                                    break;
                                case `defaultValue`:
                                    s = c;
                                    break;
                                case `multiple`:
                                    r = c;
                                default:
                                    $(e, t, a, c, n, null)
                            }
                    t = o,
                        n = s,
                        e.multiple = !!r,
                        t == null ? n != null && Gt(e, !!r, n, !0) : Gt(e, !!r, t, !1);
                    return;
                case `textarea`:
                    for (s in Q(`invalid`, e),
                        o = a = r = null,
                        n)
                        if (n.hasOwnProperty(s) && (c = n[s],
                            c != null))
                            switch (s) {
                                case `value`:
                                    r = c;
                                    break;
                                case `defaultValue`:
                                    a = c;
                                    break;
                                case `children`:
                                    o = c;
                                    break;
                                case `dangerouslySetInnerHTML`:
                                    if (c != null)
                                        throw Error(i(91));
                                    break;
                                default:
                                    $(e, t, s, c, n, null)
                            }
                    qt(e, r, a, o);
                    return;
                case `option`:
                    for (l in n)
                        if (n.hasOwnProperty(l) && (r = n[l],
                            r != null))
                            switch (l) {
                                case `selected`:
                                    e.selected = r && typeof r != `function` && typeof r != `symbol`;
                                    break;
                                default:
                                    $(e, t, l, r, n, null)
                            }
                    return;
                case `dialog`:
                    Q(`beforetoggle`, e),
                        Q(`toggle`, e),
                        Q(`cancel`, e),
                        Q(`close`, e);
                    break;
                case `iframe`:
                case `object`:
                    Q(`load`, e);
                    break;
                case `video`:
                case `audio`:
                    for (r = 0; r < Md.length; r++)
                        Q(Md[r], e);
                    break;
                case `image`:
                    Q(`error`, e),
                        Q(`load`, e);
                    break;
                case `details`:
                    Q(`toggle`, e);
                    break;
                case `embed`:
                case `source`:
                case `link`:
                    Q(`error`, e),
                        Q(`load`, e);
                case `area`:
                case `base`:
                case `br`:
                case `col`:
                case `hr`:
                case `keygen`:
                case `meta`:
                case `param`:
                case `track`:
                case `wbr`:
                case `menuitem`:
                    for (u in n)
                        if (n.hasOwnProperty(u) && (r = n[u],
                            r != null))
                            switch (u) {
                                case `children`:
                                case `dangerouslySetInnerHTML`:
                                    throw Error(i(137, t));
                                default:
                                    $(e, t, u, r, n, null)
                            }
                    return;
                default:
                    if (Qt(t)) {
                        for (d in n)
                            n.hasOwnProperty(d) && (r = n[d],
                                r !== void 0 && Jd(e, t, d, r, n, void 0));
                        return
                    }
            }
            for (c in n)
                n.hasOwnProperty(c) && (r = n[c],
                    r != null && $(e, t, c, r, n, null))
        }
        function Xd(e, t, n, r) {
            switch (t) {
                case `div`:
                case `span`:
                case `svg`:
                case `path`:
                case `a`:
                case `g`:
                case `p`:
                case `li`:
                    break;
                case `input`:
                    var a = null
                        , o = null
                        , s = null
                        , c = null
                        , l = null
                        , u = null
                        , d = null;
                    for (m in n) {
                        var f = n[m];
                        if (n.hasOwnProperty(m) && f != null)
                            switch (m) {
                                case `checked`:
                                    break;
                                case `value`:
                                    break;
                                case `defaultValue`:
                                    l = f;
                                default:
                                    r.hasOwnProperty(m) || $(e, t, m, null, r, f)
                            }
                    }
                    for (var p in r) {
                        var m = r[p];
                        if (f = n[p],
                            r.hasOwnProperty(p) && (m != null || f != null))
                            switch (p) {
                                case `type`:
                                    o = m;
                                    break;
                                case `name`:
                                    a = m;
                                    break;
                                case `checked`:
                                    u = m;
                                    break;
                                case `defaultChecked`:
                                    d = m;
                                    break;
                                case `value`:
                                    s = m;
                                    break;
                                case `defaultValue`:
                                    c = m;
                                    break;
                                case `children`:
                                case `dangerouslySetInnerHTML`:
                                    if (m != null)
                                        throw Error(i(137, t));
                                    break;
                                default:
                                    m !== f && $(e, t, p, m, r, f)
                            }
                    }
                    Ht(e, s, c, l, u, d, o, a);
                    return;
                case `select`:
                    for (o in m = s = c = p = null,
                        n)
                        if (l = n[o],
                            n.hasOwnProperty(o) && l != null)
                            switch (o) {
                                case `value`:
                                    break;
                                case `multiple`:
                                    m = l;
                                default:
                                    r.hasOwnProperty(o) || $(e, t, o, null, r, l)
                            }
                    for (a in r)
                        if (o = r[a],
                            l = n[a],
                            r.hasOwnProperty(a) && (o != null || l != null))
                            switch (a) {
                                case `value`:
                                    p = o;
                                    break;
                                case `defaultValue`:
                                    c = o;
                                    break;
                                case `multiple`:
                                    s = o;
                                default:
                                    o !== l && $(e, t, a, o, r, l)
                            }
                    t = c,
                        n = s,
                        r = m,
                        p == null ? !!r != !!n && (t == null ? Gt(e, !!n, n ? [] : ``, !1) : Gt(e, !!n, t, !0)) : Gt(e, !!n, p, !1);
                    return;
                case `textarea`:
                    for (c in m = p = null,
                        n)
                        if (a = n[c],
                            n.hasOwnProperty(c) && a != null && !r.hasOwnProperty(c))
                            switch (c) {
                                case `value`:
                                    break;
                                case `children`:
                                    break;
                                default:
                                    $(e, t, c, null, r, a)
                            }
                    for (s in r)
                        if (a = r[s],
                            o = n[s],
                            r.hasOwnProperty(s) && (a != null || o != null))
                            switch (s) {
                                case `value`:
                                    p = a;
                                    break;
                                case `defaultValue`:
                                    m = a;
                                    break;
                                case `children`:
                                    break;
                                case `dangerouslySetInnerHTML`:
                                    if (a != null)
                                        throw Error(i(91));
                                    break;
                                default:
                                    a !== o && $(e, t, s, a, r, o)
                            }
                    Kt(e, p, m);
                    return;
                case `option`:
                    for (var h in n)
                        if (p = n[h],
                            n.hasOwnProperty(h) && p != null && !r.hasOwnProperty(h))
                            switch (h) {
                                case `selected`:
                                    e.selected = !1;
                                    break;
                                default:
                                    $(e, t, h, null, r, p)
                            }
                    for (l in r)
                        if (p = r[l],
                            m = n[l],
                            r.hasOwnProperty(l) && p !== m && (p != null || m != null))
                            switch (l) {
                                case `selected`:
                                    e.selected = p && typeof p != `function` && typeof p != `symbol`;
                                    break;
                                default:
                                    $(e, t, l, p, r, m)
                            }
                    return;
                case `img`:
                case `link`:
                case `area`:
                case `base`:
                case `br`:
                case `col`:
                case `embed`:
                case `hr`:
                case `keygen`:
                case `meta`:
                case `param`:
                case `source`:
                case `track`:
                case `wbr`:
                case `menuitem`:
                    for (var g in n)
                        p = n[g],
                            n.hasOwnProperty(g) && p != null && !r.hasOwnProperty(g) && $(e, t, g, null, r, p);
                    for (u in r)
                        if (p = r[u],
                            m = n[u],
                            r.hasOwnProperty(u) && p !== m && (p != null || m != null))
                            switch (u) {
                                case `children`:
                                case `dangerouslySetInnerHTML`:
                                    if (p != null)
                                        throw Error(i(137, t));
                                    break;
                                default:
                                    $(e, t, u, p, r, m)
                            }
                    return;
                default:
                    if (Qt(t)) {
                        for (var _ in n)
                            p = n[_],
                                n.hasOwnProperty(_) && p !== void 0 && !r.hasOwnProperty(_) && Jd(e, t, _, void 0, r, p);
                        for (d in r)
                            p = r[d],
                                m = n[d],
                                !r.hasOwnProperty(d) || p === m || p === void 0 && m === void 0 || Jd(e, t, d, p, r, m);
                        return
                    }
            }
            for (var v in n)
                p = n[v],
                    n.hasOwnProperty(v) && p != null && !r.hasOwnProperty(v) && $(e, t, v, null, r, p);
            for (f in r)
                p = r[f],
                    m = n[f],
                    !r.hasOwnProperty(f) || p === m || p == null && m == null || $(e, t, f, p, r, m)
        }
        function Zd(e) {
            switch (e) {
                case `css`:
                case `script`:
                case `font`:
                case `img`:
                case `image`:
                case `input`:
                case `link`:
                    return !0;
                default:
                    return !1
            }
        }
        function Qd() {
            if (typeof performance.getEntriesByType == `function`) {
                for (var e = 0, t = 0, n = performance.getEntriesByType(`resource`), r = 0; r < n.length; r++) {
                    var i = n[r]
                        , a = i.transferSize
                        , o = i.initiatorType
                        , s = i.duration;
                    if (a && s && Zd(o)) {
                        for (o = 0,
                            s = i.responseEnd,
                            r += 1; r < n.length; r++) {
                            var c = n[r]
                                , l = c.startTime;
                            if (l > s)
                                break;
                            var u = c.transferSize
                                , d = c.initiatorType;
                            u && Zd(d) && (c = c.responseEnd,
                                o += u * (c < s ? 1 : (s - l) / (c - l)))
                        }
                        if (--r,
                            t += 8 * (a + o) / (i.duration / 1e3),
                            e++,
                            10 < e)
                            break
                    }
                }
                if (0 < e)
                    return t / e / 1e6
            }
            return navigator.connection && (e = navigator.connection.downlink,
                typeof e == `number`) ? e : 5
        }
        var $d = null
            , ef = null;
        function tf(e) {
            return e.nodeType === 9 ? e : e.ownerDocument
        }
        function nf(e) {
            switch (e) {
                case `http://www.w3.org/2000/svg`:
                    return 1;
                case `http://www.w3.org/1998/Math/MathML`:
                    return 2;
                default:
                    return 0
            }
        }
        function rf(e, t) {
            if (e === 0)
                switch (t) {
                    case `svg`:
                        return 1;
                    case `math`:
                        return 2;
                    default:
                        return 0
                }
            return e === 1 && t === `foreignObject` ? 0 : e
        }
        function af(e, t) {
            return e === `textarea` || e === `noscript` || typeof t.children == `string` || typeof t.children == `number` || typeof t.children == `bigint` || typeof t.dangerouslySetInnerHTML == `object` && t.dangerouslySetInnerHTML !== null && t.dangerouslySetInnerHTML.__html != null
        }
        var sf = null;
        function cf() {
            var e = window.event;
            return e && e.type === `popstate` ? e === sf ? !1 : (sf = e,
                !0) : (sf = null,
                    !1)
        }
        var lf = typeof setTimeout == `function` ? setTimeout : void 0
            , uf = typeof clearTimeout == `function` ? clearTimeout : void 0
            , df = typeof Promise == `function` ? Promise : void 0
            , ff = typeof queueMicrotask == `function` ? queueMicrotask : df === void 0 ? lf : function (e) {
                return df.resolve(null).then(e).catch(pf)
            }
            ;
        function pf(e) {
            setTimeout(function () {
                throw e
            })
        }
        function mf(e) {
            return e === `head`
        }
        function hf(e, t) {
            var n = t
                , r = 0;
            do {
                var i = n.nextSibling;
                if (e.removeChild(n),
                    i && i.nodeType === 8)
                    if (n = i.data,
                        n === `/$` || n === `/&`) {
                        if (r === 0) {
                            e.removeChild(i),
                                Yp(t);
                            return
                        }
                        r--
                    } else if (n === `$` || n === `$?` || n === `$~` || n === `$!` || n === `&`)
                        r++;
                    else if (n === `html`)
                        kf(e.ownerDocument.documentElement);
                    else if (n === `head`) {
                        n = e.ownerDocument.head,
                            kf(n);
                        for (var a = n.firstChild; a;) {
                            var o = a.nextSibling
                                , s = a.nodeName;
                            a[gt] || s === `SCRIPT` || s === `STYLE` || s === `LINK` && a.rel.toLowerCase() === `stylesheet` || n.removeChild(a),
                                a = o
                        }
                    } else
                        n === `body` && kf(e.ownerDocument.body);
                n = i
            } while (n);
            Yp(t)
        }
        function gf(e, t) {
            var n = e;
            e = 0;
            do {
                var r = n.nextSibling;
                if (n.nodeType === 1 ? t ? (n._stashedDisplay = n.style.display,
                    n.style.display = `none`) : (n.style.display = n._stashedDisplay || ``,
                        n.getAttribute(`style`) === `` && n.removeAttribute(`style`)) : n.nodeType === 3 && (t ? (n._stashedText = n.nodeValue,
                            n.nodeValue = ``) : n.nodeValue = n._stashedText || ``),
                    r && r.nodeType === 8)
                    if (n = r.data,
                        n === `/$`) {
                        if (e === 0)
                            break;
                        e--
                    } else
                        n !== `$` && n !== `$?` && n !== `$~` && n !== `$!` || e++;
                n = r
            } while (n)
        }
        function _f(e) {
            var t = e.firstChild;
            for (t && t.nodeType === 10 && (t = t.nextSibling); t;) {
                var n = t;
                switch (t = t.nextSibling,
                n.nodeName) {
                    case `HTML`:
                    case `HEAD`:
                    case `BODY`:
                        _f(n),
                            _t(n);
                        continue;
                    case `SCRIPT`:
                    case `STYLE`:
                        continue;
                    case `LINK`:
                        if (n.rel.toLowerCase() === `stylesheet`)
                            continue
                }
                e.removeChild(n)
            }
        }
        function vf(e, t, n, r) {
            for (; e.nodeType === 1;) {
                var i = n;
                if (e.nodeName.toLowerCase() !== t.toLowerCase()) {
                    if (!r && (e.nodeName !== `INPUT` || e.type !== `hidden`))
                        break
                } else if (r) {
                    if (!e[gt])
                        switch (t) {
                            case `meta`:
                                if (!e.hasAttribute(`itemprop`))
                                    break;
                                return e;
                            case `link`:
                                if (a = e.getAttribute(`rel`),
                                    a === `stylesheet` && e.hasAttribute(`data-precedence`) || a !== i.rel || e.getAttribute(`href`) !== (i.href == null || i.href === `` ? null : i.href) || e.getAttribute(`crossorigin`) !== (i.crossOrigin == null ? null : i.crossOrigin) || e.getAttribute(`title`) !== (i.title == null ? null : i.title))
                                    break;
                                return e;
                            case `style`:
                                if (e.hasAttribute(`data-precedence`))
                                    break;
                                return e;
                            case `script`:
                                if (a = e.getAttribute(`src`),
                                    (a !== (i.src == null ? null : i.src) || e.getAttribute(`type`) !== (i.type == null ? null : i.type) || e.getAttribute(`crossorigin`) !== (i.crossOrigin == null ? null : i.crossOrigin)) && a && e.hasAttribute(`async`) && !e.hasAttribute(`itemprop`))
                                    break;
                                return e;
                            default:
                                return e
                        }
                } else if (t === `input` && e.type === `hidden`) {
                    var a = i.name == null ? null : `` + i.name;
                    if (i.type === `hidden` && e.getAttribute(`name`) === a)
                        return e
                } else
                    return e;
                if (e = wf(e.nextSibling),
                    e === null)
                    break
            }
            return null
        }
        function yf(e, t, n) {
            if (t === ``)
                return null;
            for (; e.nodeType !== 3;)
                if ((e.nodeType !== 1 || e.nodeName !== `INPUT` || e.type !== `hidden`) && !n || (e = wf(e.nextSibling),
                    e === null))
                    return null;
            return e
        }
        function bf(e, t) {
            for (; e.nodeType !== 8;)
                if ((e.nodeType !== 1 || e.nodeName !== `INPUT` || e.type !== `hidden`) && !t || (e = wf(e.nextSibling),
                    e === null))
                    return null;
            return e
        }
        function xf(e) {
            return e.data === `$?` || e.data === `$~`
        }
        function Sf(e) {
            return e.data === `$!` || e.data === `$?` && e.ownerDocument.readyState !== `loading`
        }
        function Cf(e, t) {
            var n = e.ownerDocument;
            if (e.data === `$~`)
                e._reactRetry = t;
            else if (e.data !== `$?` || n.readyState !== `loading`)
                t();
            else {
                var r = function () {
                    t(),
                        n.removeEventListener(`DOMContentLoaded`, r)
                };
                n.addEventListener(`DOMContentLoaded`, r),
                    e._reactRetry = r
            }
        }
        function wf(e) {
            for (; e != null; e = e.nextSibling) {
                var t = e.nodeType;
                if (t === 1 || t === 3)
                    break;
                if (t === 8) {
                    if (t = e.data,
                        t === `$` || t === `$!` || t === `$?` || t === `$~` || t === `&` || t === `F!` || t === `F`)
                        break;
                    if (t === `/$` || t === `/&`)
                        return null
                }
            }
            return e
        }
        var Tf = null;
        function Ef(e) {
            e = e.nextSibling;
            for (var t = 0; e;) {
                if (e.nodeType === 8) {
                    var n = e.data;
                    if (n === `/$` || n === `/&`) {
                        if (t === 0)
                            return wf(e.nextSibling);
                        t--
                    } else
                        n !== `$` && n !== `$!` && n !== `$?` && n !== `$~` && n !== `&` || t++
                }
                e = e.nextSibling
            }
            return null
        }
        function Df(e) {
            e = e.previousSibling;
            for (var t = 0; e;) {
                if (e.nodeType === 8) {
                    var n = e.data;
                    if (n === `$` || n === `$!` || n === `$?` || n === `$~` || n === `&`) {
                        if (t === 0)
                            return e;
                        t--
                    } else
                        n !== `/$` && n !== `/&` || t++
                }
                e = e.previousSibling
            }
            return null
        }
        function Of(e, t, n) {
            switch (t = tf(n),
            e) {
                case `html`:
                    if (e = t.documentElement,
                        !e)
                        throw Error(i(452));
                    return e;
                case `head`:
                    if (e = t.head,
                        !e)
                        throw Error(i(453));
                    return e;
                case `body`:
                    if (e = t.body,
                        !e)
                        throw Error(i(454));
                    return e;
                default:
                    throw Error(i(451))
            }
        }
        function kf(e) {
            for (var t = e.attributes; t.length;)
                e.removeAttributeNode(t[0]);
            _t(e)
        }
        var Af = new Map
            , jf = new Set;
        function Mf(e) {
            return typeof e.getRootNode == `function` ? e.getRootNode() : e.nodeType === 9 ? e : e.ownerDocument
        }
        var Nf = A.d;
        A.d = {
            f: Pf,
            r: Ff,
            D: Rf,
            C: zf,
            L: Bf,
            m: Vf,
            X: Uf,
            S: Hf,
            M: Wf
        };
        function Pf() {
            var e = Nf.f()
                , t = Nu();
            return e || t
        }
        function Ff(e) {
            var t = yt(e);
            t !== null && t.tag === 5 && t.type === `form` ? Bs(t) : Nf.r(e)
        }
        var If = typeof document > `u` ? null : document;
        function Lf(e, t, n) {
            var r = If;
            if (r && typeof t == `string` && t) {
                var i = Vt(t);
                i = `link[rel="` + e + `"][href="` + i + `"]`,
                    typeof n == `string` && (i += `[crossorigin="` + n + `"]`),
                    jf.has(i) || (jf.add(i),
                        e = {
                            rel: e,
                            crossOrigin: n,
                            href: t
                        },
                        r.querySelector(i) === null && (t = r.createElement(`link`),
                            Yd(t, `link`, e),
                            St(t),
                            r.head.appendChild(t)))
            }
        }
        function Rf(e) {
            Nf.D(e),
                Lf(`dns-prefetch`, e, null)
        }
        function zf(e, t) {
            Nf.C(e, t),
                Lf(`preconnect`, e, t)
        }
        function Bf(e, t, n) {
            Nf.L(e, t, n);
            var r = If;
            if (r && e && t) {
                var i = `link[rel="preload"][as="` + Vt(t) + `"]`;
                t === `image` && n && n.imageSrcSet ? (i += `[imagesrcset="` + Vt(n.imageSrcSet) + `"]`,
                    typeof n.imageSizes == `string` && (i += `[imagesizes="` + Vt(n.imageSizes) + `"]`)) : i += `[href="` + Vt(e) + `"]`;
                var a = i;
                switch (t) {
                    case `style`:
                        a = Kf(e);
                        break;
                    case `script`:
                        a = Xf(e)
                }
                Af.has(a) || (e = h({
                    rel: `preload`,
                    href: t === `image` && n && n.imageSrcSet ? void 0 : e,
                    as: t
                }, n),
                    Af.set(a, e),
                    r.querySelector(i) !== null || t === `style` && r.querySelector(qf(a)) || t === `script` && r.querySelector(Zf(a)) || (t = r.createElement(`link`),
                        Yd(t, `link`, e),
                        St(t),
                        r.head.appendChild(t)))
            }
        }
        function Vf(e, t) {
            Nf.m(e, t);
            var n = If;
            if (n && e) {
                var r = t && typeof t.as == `string` ? t.as : `script`
                    , i = `link[rel="modulepreload"][as="` + Vt(r) + `"][href="` + Vt(e) + `"]`
                    , a = i;
                switch (r) {
                    case `audioworklet`:
                    case `paintworklet`:
                    case `serviceworker`:
                    case `sharedworker`:
                    case `worker`:
                    case `script`:
                        a = Xf(e)
                }
                if (!Af.has(a) && (e = h({
                    rel: `modulepreload`,
                    href: e
                }, t),
                    Af.set(a, e),
                    n.querySelector(i) === null)) {
                    switch (r) {
                        case `audioworklet`:
                        case `paintworklet`:
                        case `serviceworker`:
                        case `sharedworker`:
                        case `worker`:
                        case `script`:
                            if (n.querySelector(Zf(a)))
                                return
                    }
                    r = n.createElement(`link`),
                        Yd(r, `link`, e),
                        St(r),
                        n.head.appendChild(r)
                }
            }
        }
        function Hf(e, t, n) {
            Nf.S(e, t, n);
            var r = If;
            if (r && e) {
                var i = xt(r).hoistableStyles
                    , a = Kf(e);
                t ||= `default`;
                var o = i.get(a);
                if (!o) {
                    var s = {
                        loading: 0,
                        preload: null
                    };
                    if (o = r.querySelector(qf(a)))
                        s.loading = 5;
                    else {
                        e = h({
                            rel: `stylesheet`,
                            href: e,
                            "data-precedence": t
                        }, n),
                            (n = Af.get(a)) && ep(e, n);
                        var c = o = r.createElement(`link`);
                        St(c),
                            Yd(c, `link`, e),
                            c._p = new Promise(function (e, t) {
                                c.onload = e,
                                    c.onerror = t
                            }
                            ),
                            c.addEventListener(`load`, function () {
                                s.loading |= 1
                            }),
                            c.addEventListener(`error`, function () {
                                s.loading |= 2
                            }),
                            s.loading |= 4,
                            $f(o, t, r)
                    }
                    o = {
                        type: `stylesheet`,
                        instance: o,
                        count: 1,
                        state: s
                    },
                        i.set(a, o)
                }
            }
        }
        function Uf(e, t) {
            Nf.X(e, t);
            var n = If;
            if (n && e) {
                var r = xt(n).hoistableScripts
                    , i = Xf(e)
                    , a = r.get(i);
                a || (a = n.querySelector(Zf(i)),
                    a || (e = h({
                        src: e,
                        async: !0
                    }, t),
                        (t = Af.get(i)) && tp(e, t),
                        a = n.createElement(`script`),
                        St(a),
                        Yd(a, `link`, e),
                        n.head.appendChild(a)),
                    a = {
                        type: `script`,
                        instance: a,
                        count: 1,
                        state: null
                    },
                    r.set(i, a))
            }
        }
        function Wf(e, t) {
            Nf.M(e, t);
            var n = If;
            if (n && e) {
                var r = xt(n).hoistableScripts
                    , i = Xf(e)
                    , a = r.get(i);
                a || (a = n.querySelector(Zf(i)),
                    a || (e = h({
                        src: e,
                        async: !0,
                        type: `module`
                    }, t),
                        (t = Af.get(i)) && tp(e, t),
                        a = n.createElement(`script`),
                        St(a),
                        Yd(a, `link`, e),
                        n.head.appendChild(a)),
                    a = {
                        type: `script`,
                        instance: a,
                        count: 1,
                        state: null
                    },
                    r.set(i, a))
            }
        }
        function Gf(e, t, n, r) {
            var a = (a = F.current) ? Mf(a) : null;
            if (!a)
                throw Error(i(446));
            switch (e) {
                case `meta`:
                case `title`:
                    return null;
                case `style`:
                    return typeof n.precedence == `string` && typeof n.href == `string` ? (t = Kf(n.href),
                        n = xt(a).hoistableStyles,
                        r = n.get(t),
                        r || (r = {
                            type: `style`,
                            instance: null,
                            count: 0,
                            state: null
                        },
                            n.set(t, r)),
                        r) : {
                        type: `void`,
                        instance: null,
                        count: 0,
                        state: null
                    };
                case `link`:
                    if (n.rel === `stylesheet` && typeof n.href == `string` && typeof n.precedence == `string`) {
                        e = Kf(n.href);
                        var o = xt(a).hoistableStyles
                            , s = o.get(e);
                        if (s || (a = a.ownerDocument || a,
                            s = {
                                type: `stylesheet`,
                                instance: null,
                                count: 0,
                                state: {
                                    loading: 0,
                                    preload: null
                                }
                            },
                            o.set(e, s),
                            (o = a.querySelector(qf(e))) && !o._p && (s.instance = o,
                                s.state.loading = 5),
                            Af.has(e) || (n = {
                                rel: `preload`,
                                as: `style`,
                                href: n.href,
                                crossOrigin: n.crossOrigin,
                                integrity: n.integrity,
                                media: n.media,
                                hrefLang: n.hrefLang,
                                referrerPolicy: n.referrerPolicy
                            },
                                Af.set(e, n),
                                o || Yf(a, e, n, s.state))),
                            t && r === null)
                            throw Error(i(528, ``));
                        return s
                    }
                    if (t && r !== null)
                        throw Error(i(529, ``));
                    return null;
                case `script`:
                    return t = n.async,
                        n = n.src,
                        typeof n == `string` && t && typeof t != `function` && typeof t != `symbol` ? (t = Xf(n),
                            n = xt(a).hoistableScripts,
                            r = n.get(t),
                            r || (r = {
                                type: `script`,
                                instance: null,
                                count: 0,
                                state: null
                            },
                                n.set(t, r)),
                            r) : {
                            type: `void`,
                            instance: null,
                            count: 0,
                            state: null
                        };
                default:
                    throw Error(i(444, e))
            }
        }
        function Kf(e) {
            return `href="` + Vt(e) + `"`
        }
        function qf(e) {
            return `link[rel="stylesheet"][` + e + `]`
        }
        function Jf(e) {
            return h({}, e, {
                "data-precedence": e.precedence,
                precedence: null
            })
        }
        function Yf(e, t, n, r) {
            e.querySelector(`link[rel="preload"][as="style"][` + t + `]`) ? r.loading = 1 : (t = e.createElement(`link`),
                r.preload = t,
                t.addEventListener(`load`, function () {
                    return r.loading |= 1
                }),
                t.addEventListener(`error`, function () {
                    return r.loading |= 2
                }),
                Yd(t, `link`, n),
                St(t),
                e.head.appendChild(t))
        }
        function Xf(e) {
            return `[src="` + Vt(e) + `"]`
        }
        function Zf(e) {
            return `script[async]` + e
        }
        function Qf(e, t, n) {
            if (t.count++,
                t.instance === null)
                switch (t.type) {
                    case `style`:
                        var r = e.querySelector(`style[data-href~="` + Vt(n.href) + `"]`);
                        if (r)
                            return t.instance = r,
                                St(r),
                                r;
                        var a = h({}, n, {
                            "data-href": n.href,
                            "data-precedence": n.precedence,
                            href: null,
                            precedence: null
                        });
                        return r = (e.ownerDocument || e).createElement(`style`),
                            St(r),
                            Yd(r, `style`, a),
                            $f(r, n.precedence, e),
                            t.instance = r;
                    case `stylesheet`:
                        a = Kf(n.href);
                        var o = e.querySelector(qf(a));
                        if (o)
                            return t.state.loading |= 4,
                                t.instance = o,
                                St(o),
                                o;
                        r = Jf(n),
                            (a = Af.get(a)) && ep(r, a),
                            o = (e.ownerDocument || e).createElement(`link`),
                            St(o);
                        var s = o;
                        return s._p = new Promise(function (e, t) {
                            s.onload = e,
                                s.onerror = t
                        }
                        ),
                            Yd(o, `link`, r),
                            t.state.loading |= 4,
                            $f(o, n.precedence, e),
                            t.instance = o;
                    case `script`:
                        return o = Xf(n.src),
                            (a = e.querySelector(Zf(o))) ? (t.instance = a,
                                St(a),
                                a) : (r = n,
                                    (a = Af.get(o)) && (r = h({}, n),
                                        tp(r, a)),
                                    e = e.ownerDocument || e,
                                    a = e.createElement(`script`),
                                    St(a),
                                    Yd(a, `link`, r),
                                    e.head.appendChild(a),
                                    t.instance = a);
                    case `void`:
                        return null;
                    default:
                        throw Error(i(443, t.type))
                }
            else
                t.type === `stylesheet` && !(t.state.loading & 4) && (r = t.instance,
                    t.state.loading |= 4,
                    $f(r, n.precedence, e));
            return t.instance
        }
        function $f(e, t, n) {
            for (var r = n.querySelectorAll(`link[rel="stylesheet"][data-precedence],style[data-precedence]`), i = r.length ? r[r.length - 1] : null, a = i, o = 0; o < r.length; o++) {
                var s = r[o];
                if (s.dataset.precedence === t)
                    a = s;
                else if (a !== i)
                    break
            }
            a ? a.parentNode.insertBefore(e, a.nextSibling) : (t = n.nodeType === 9 ? n.head : n,
                t.insertBefore(e, t.firstChild))
        }
        function ep(e, t) {
            e.crossOrigin ??= t.crossOrigin,
                e.referrerPolicy ??= t.referrerPolicy,
                e.title ??= t.title
        }
        function tp(e, t) {
            e.crossOrigin ??= t.crossOrigin,
                e.referrerPolicy ??= t.referrerPolicy,
                e.integrity ??= t.integrity
        }
        var np = null;
        function rp(e, t, n) {
            if (np === null) {
                var r = new Map
                    , i = np = new Map;
                i.set(n, r)
            } else
                i = np,
                    r = i.get(n),
                    r || (r = new Map,
                        i.set(n, r));
            if (r.has(e))
                return r;
            for (r.set(e, null),
                n = n.getElementsByTagName(e),
                i = 0; i < n.length; i++) {
                var a = n[i];
                if (!(a[gt] || a[lt] || e === `link` && a.getAttribute(`rel`) === `stylesheet`) && a.namespaceURI !== `http://www.w3.org/2000/svg`) {
                    var o = a.getAttribute(t) || ``;
                    o = e + o;
                    var s = r.get(o);
                    s ? s.push(a) : r.set(o, [a])
                }
            }
            return r
        }
        function ip(e, t, n) {
            e = e.ownerDocument || e,
                e.head.insertBefore(n, t === `title` ? e.querySelector(`head > title`) : null)
        }
        function ap(e, t, n) {
            if (n === 1 || t.itemProp != null)
                return !1;
            switch (e) {
                case `meta`:
                case `title`:
                    return !0;
                case `style`:
                    if (typeof t.precedence != `string` || typeof t.href != `string` || t.href === ``)
                        break;
                    return !0;
                case `link`:
                    if (typeof t.rel != `string` || typeof t.href != `string` || t.href === `` || t.onLoad || t.onError)
                        break;
                    switch (t.rel) {
                        case `stylesheet`:
                            return e = t.disabled,
                                typeof t.precedence == `string` && e == null;
                        default:
                            return !0
                    }
                case `script`:
                    if (t.async && typeof t.async != `function` && typeof t.async != `symbol` && !t.onLoad && !t.onError && t.src && typeof t.src == `string`)
                        return !0
            }
            return !1
        }
        function op(e) {
            return !(e.type === `stylesheet` && !(e.state.loading & 3))
        }
        function sp(e, t, n, r) {
            if (n.type === `stylesheet` && (typeof r.media != `string` || !1 !== matchMedia(r.media).matches) && !(n.state.loading & 4)) {
                if (n.instance === null) {
                    var i = Kf(r.href)
                        , a = t.querySelector(qf(i));
                    if (a) {
                        t = a._p,
                            typeof t == `object` && t && typeof t.then == `function` && (e.count++,
                                e = up.bind(e),
                                t.then(e, e)),
                            n.state.loading |= 4,
                            n.instance = a,
                            St(a);
                        return
                    }
                    a = t.ownerDocument || t,
                        r = Jf(r),
                        (i = Af.get(i)) && ep(r, i),
                        a = a.createElement(`link`),
                        St(a);
                    var o = a;
                    o._p = new Promise(function (e, t) {
                        o.onload = e,
                            o.onerror = t
                    }
                    ),
                        Yd(a, `link`, r),
                        n.instance = a
                }
                e.stylesheets === null && (e.stylesheets = new Map),
                    e.stylesheets.set(n, t),
                    (t = n.state.preload) && !(n.state.loading & 3) && (e.count++,
                        n = up.bind(e),
                        t.addEventListener(`load`, n),
                        t.addEventListener(`error`, n))
            }
        }
        var cp = 0;
        function lp(e, t) {
            return e.stylesheets && e.count === 0 && fp(e, e.stylesheets),
                0 < e.count || 0 < e.imgCount ? function (n) {
                    var r = setTimeout(function () {
                        if (e.stylesheets && fp(e, e.stylesheets),
                            e.unsuspend) {
                            var t = e.unsuspend;
                            e.unsuspend = null,
                                t()
                        }
                    }, 6e4 + t);
                    0 < e.imgBytes && cp === 0 && (cp = 62500 * Qd());
                    var i = setTimeout(function () {
                        if (e.waitingForImages = !1,
                            e.count === 0 && (e.stylesheets && fp(e, e.stylesheets),
                                e.unsuspend)) {
                            var t = e.unsuspend;
                            e.unsuspend = null,
                                t()
                        }
                    }, (e.imgBytes > cp ? 50 : 800) + t);
                    return e.unsuspend = n,
                        function () {
                            e.unsuspend = null,
                                clearTimeout(r),
                                clearTimeout(i)
                        }
                }
                    : null
        }
        function up() {
            if (this.count--,
                this.count === 0 && (this.imgCount === 0 || !this.waitingForImages)) {
                if (this.stylesheets)
                    fp(this, this.stylesheets);
                else if (this.unsuspend) {
                    var e = this.unsuspend;
                    this.unsuspend = null,
                        e()
                }
            }
        }
        var dp = null;
        function fp(e, t) {
            e.stylesheets = null,
                e.unsuspend !== null && (e.count++,
                    dp = new Map,
                    t.forEach(pp, e),
                    dp = null,
                    up.call(e))
        }
        function pp(e, t) {
            if (!(t.state.loading & 4)) {
                var n = dp.get(e);
                if (n)
                    var r = n.get(null);
                else {
                    n = new Map,
                        dp.set(e, n);
                    for (var i = e.querySelectorAll(`link[data-precedence],style[data-precedence]`), a = 0; a < i.length; a++) {
                        var o = i[a];
                        (o.nodeName === `LINK` || o.getAttribute(`media`) !== `not all`) && (n.set(o.dataset.precedence, o),
                            r = o)
                    }
                    r && n.set(null, r)
                }
                i = t.instance,
                    o = i.getAttribute(`data-precedence`),
                    a = n.get(o) || r,
                    a === r && n.set(null, i),
                    n.set(o, i),
                    this.count++,
                    r = up.bind(this),
                    i.addEventListener(`load`, r),
                    i.addEventListener(`error`, r),
                    a ? a.parentNode.insertBefore(i, a.nextSibling) : (e = e.nodeType === 9 ? e.head : e,
                        e.insertBefore(i, e.firstChild)),
                    t.state.loading |= 4
            }
        }
        var mp = {
            $$typeof: C,
            Provider: null,
            Consumer: null,
            _currentValue: se,
            _currentValue2: se,
            _threadCount: 0
        };
        function hp(e, t, n, r, i, a, o, s, c) {
            this.tag = 1,
                this.containerInfo = e,
                this.pingCache = this.current = this.pendingChildren = null,
                this.timeoutHandle = -1,
                this.callbackNode = this.next = this.pendingContext = this.context = this.cancelPendingCommit = null,
                this.callbackPriority = 0,
                this.expirationTimes = Qe(-1),
                this.entangledLanes = this.shellSuspendCounter = this.errorRecoveryDisabledLanes = this.expiredLanes = this.warmLanes = this.pingedLanes = this.suspendedLanes = this.pendingLanes = 0,
                this.entanglements = Qe(0),
                this.hiddenUpdates = Qe(null),
                this.identifierPrefix = r,
                this.onUncaughtError = i,
                this.onCaughtError = a,
                this.onRecoverableError = o,
                this.pooledCache = null,
                this.pooledCacheLanes = 0,
                this.formState = c,
                this.incompleteTransitions = new Map
        }
        function gp(e, t, n, r, i, a, o, s, c, l, u, d) {
            return e = new hp(e, t, n, o, c, l, u, d, s),
                t = 1,
                !0 === a && (t |= 24),
                a = yi(3, null, null, t),
                e.current = a,
                a.stateNode = e,
                t = va(),
                t.refCount++,
                e.pooledCache = t,
                t.refCount++,
                a.memoizedState = {
                    element: r,
                    isDehydrated: n,
                    cache: t
                },
                Za(a),
                e
        }
        function _p(e) {
            return e ? (e = _i,
                e) : _i
        }
        function vp(e, t, n, r, i, a) {
            i = _p(i),
                r.context === null ? r.context = i : r.pendingContext = i,
                r = $a(t),
                r.payload = {
                    element: n
                },
                a = a === void 0 ? null : a,
                a !== null && (r.callback = a),
                n = eo(e, r, t),
                n !== null && (Ou(n, e, t),
                    to(n, e, t))
        }
        function yp(e, t) {
            if (e = e.memoizedState,
                e !== null && e.dehydrated !== null) {
                var n = e.retryLane;
                e.retryLane = n !== 0 && n < t ? n : t
            }
        }
        function bp(e, t) {
            yp(e, t),
                (e = e.alternate) && yp(e, t)
        }
        function xp(e) {
            if (e.tag === 13 || e.tag === 31) {
                var t = mi(e, 67108864);
                t !== null && Ou(t, e, 67108864),
                    bp(e, 67108864)
            }
        }
        function Sp(e) {
            if (e.tag === 13 || e.tag === 31) {
                var t = Eu();
                t = it(t);
                var n = mi(e, t);
                n !== null && Ou(n, e, t),
                    bp(e, t)
            }
        }
        var Cp = !0;
        function wp(e, t, n, r) {
            var i = k.T;
            k.T = null;
            var a = A.p;
            try {
                A.p = 2,
                    Ep(e, t, n, r)
            } finally {
                A.p = a,
                    k.T = i
            }
        }
        function Tp(e, t, n, r) {
            var i = k.T;
            k.T = null;
            var a = A.p;
            try {
                A.p = 8,
                    Ep(e, t, n, r)
            } finally {
                A.p = a,
                    k.T = i
            }
        }
        function Ep(e, t, n, r) {
            if (Cp) {
                var i = Dp(r);
                if (i === null)
                    zd(e, t, r, Op, n),
                        zp(e, r);
                else if (Vp(i, e, t, n, r))
                    r.stopPropagation();
                else if (zp(e, r),
                    t & 4 && -1 < Rp.indexOf(e)) {
                    for (; i !== null;) {
                        var a = yt(i);
                        if (a !== null)
                            switch (a.tag) {
                                case 3:
                                    if (a = a.stateNode,
                                        a.current.memoizedState.isDehydrated) {
                                        var o = qe(a.pendingLanes);
                                        if (o !== 0) {
                                            var s = a;
                                            for (s.pendingLanes |= 2,
                                                s.entangledLanes |= 2; o;) {
                                                var c = 1 << 31 - Be(o);
                                                s.entanglements[1] |= c,
                                                    o &= ~c
                                            }
                                            gd(a),
                                                !(G & 6) && (mu = Oe() + 500,
                                                    _d(0, !1))
                                        }
                                    }
                                    break;
                                case 31:
                                case 13:
                                    s = mi(a, 2),
                                        s !== null && Ou(s, a, 2),
                                        Nu(),
                                        bp(a, 2)
                            }
                        if (a = Dp(r),
                            a === null && zd(e, t, r, Op, n),
                            a === i)
                            break;
                        i = a
                    }
                    i !== null && r.stopPropagation()
                } else
                    zd(e, t, r, null, n)
            }
        }
        function Dp(e) {
            return e = an(e),
                kp(e)
        }
        var Op = null;
        function kp(e) {
            if (Op = null,
                e = vt(e),
                e !== null) {
                var t = o(e);
                if (t === null)
                    e = null;
                else {
                    var n = t.tag;
                    if (n === 13) {
                        if (e = s(t),
                            e !== null)
                            return e;
                        e = null
                    } else if (n === 31) {
                        if (e = c(t),
                            e !== null)
                            return e;
                        e = null
                    } else if (n === 3) {
                        if (t.stateNode.current.memoizedState.isDehydrated)
                            return t.tag === 3 ? t.stateNode.containerInfo : null;
                        e = null
                    } else
                        t !== e && (e = null)
                }
            }
            return Op = e,
                null
        }
        function Ap(e) {
            switch (e) {
                case `beforetoggle`:
                case `cancel`:
                case `click`:
                case `close`:
                case `contextmenu`:
                case `copy`:
                case `cut`:
                case `auxclick`:
                case `dblclick`:
                case `dragend`:
                case `dragstart`:
                case `drop`:
                case `focusin`:
                case `focusout`:
                case `input`:
                case `invalid`:
                case `keydown`:
                case `keypress`:
                case `keyup`:
                case `mousedown`:
                case `mouseup`:
                case `paste`:
                case `pause`:
                case `play`:
                case `pointercancel`:
                case `pointerdown`:
                case `pointerup`:
                case `ratechange`:
                case `reset`:
                case `resize`:
                case `seeked`:
                case `submit`:
                case `toggle`:
                case `touchcancel`:
                case `touchend`:
                case `touchstart`:
                case `volumechange`:
                case `change`:
                case `selectionchange`:
                case `textInput`:
                case `compositionstart`:
                case `compositionend`:
                case `compositionupdate`:
                case `beforeblur`:
                case `afterblur`:
                case `beforeinput`:
                case `blur`:
                case `fullscreenchange`:
                case `focus`:
                case `hashchange`:
                case `popstate`:
                case `select`:
                case `selectstart`:
                    return 2;
                case `drag`:
                case `dragenter`:
                case `dragexit`:
                case `dragleave`:
                case `dragover`:
                case `mousemove`:
                case `mouseout`:
                case `mouseover`:
                case `pointermove`:
                case `pointerout`:
                case `pointerover`:
                case `scroll`:
                case `touchmove`:
                case `wheel`:
                case `mouseenter`:
                case `mouseleave`:
                case `pointerenter`:
                case `pointerleave`:
                    return 8;
                case `message`:
                    switch (ke()) {
                        case Ae:
                            return 2;
                        case je:
                            return 8;
                        case Me:
                        case Ne:
                            return 32;
                        case Pe:
                            return 268435456;
                        default:
                            return 32
                    }
                default:
                    return 32
            }
        }
        var jp = !1
            , Mp = null
            , Np = null
            , Pp = null
            , Fp = new Map
            , Ip = new Map
            , Lp = []
            , Rp = `mousedown mouseup touchcancel touchend touchstart auxclick dblclick pointercancel pointerdown pointerup dragend dragstart drop compositionend compositionstart keydown keypress keyup input textInput copy cut paste click change contextmenu reset`.split(` `);
        function zp(e, t) {
            switch (e) {
                case `focusin`:
                case `focusout`:
                    Mp = null;
                    break;
                case `dragenter`:
                case `dragleave`:
                    Np = null;
                    break;
                case `mouseover`:
                case `mouseout`:
                    Pp = null;
                    break;
                case `pointerover`:
                case `pointerout`:
                    Fp.delete(t.pointerId);
                    break;
                case `gotpointercapture`:
                case `lostpointercapture`:
                    Ip.delete(t.pointerId)
            }
        }
        function Bp(e, t, n, r, i, a) {
            return e === null || e.nativeEvent !== a ? (e = {
                blockedOn: t,
                domEventName: n,
                eventSystemFlags: r,
                nativeEvent: a,
                targetContainers: [i]
            },
                t !== null && (t = yt(t),
                    t !== null && xp(t)),
                e) : (e.eventSystemFlags |= r,
                    t = e.targetContainers,
                    i !== null && t.indexOf(i) === -1 && t.push(i),
                    e)
        }
        function Vp(e, t, n, r, i) {
            switch (t) {
                case `focusin`:
                    return Mp = Bp(Mp, e, t, n, r, i),
                        !0;
                case `dragenter`:
                    return Np = Bp(Np, e, t, n, r, i),
                        !0;
                case `mouseover`:
                    return Pp = Bp(Pp, e, t, n, r, i),
                        !0;
                case `pointerover`:
                    var a = i.pointerId;
                    return Fp.set(a, Bp(Fp.get(a) || null, e, t, n, r, i)),
                        !0;
                case `gotpointercapture`:
                    return a = i.pointerId,
                        Ip.set(a, Bp(Ip.get(a) || null, e, t, n, r, i)),
                        !0
            }
            return !1
        }
        function Hp(e) {
            var t = vt(e.target);
            if (t !== null) {
                var n = o(t);
                if (n !== null) {
                    if (t = n.tag,
                        t === 13) {
                        if (t = s(n),
                            t !== null) {
                            e.blockedOn = t,
                                st(e.priority, function () {
                                    Sp(n)
                                });
                            return
                        }
                    } else if (t === 31) {
                        if (t = c(n),
                            t !== null) {
                            e.blockedOn = t,
                                st(e.priority, function () {
                                    Sp(n)
                                });
                            return
                        }
                    } else if (t === 3 && n.stateNode.current.memoizedState.isDehydrated) {
                        e.blockedOn = n.tag === 3 ? n.stateNode.containerInfo : null;
                        return
                    }
                }
            }
            e.blockedOn = null
        }
        function Up(e) {
            if (e.blockedOn !== null)
                return !1;
            for (var t = e.targetContainers; 0 < t.length;) {
                var n = Dp(e.nativeEvent);
                if (n === null) {
                    n = e.nativeEvent;
                    var r = new n.constructor(n.type, n);
                    rn = r,
                        n.target.dispatchEvent(r),
                        rn = null
                } else
                    return t = yt(n),
                        t !== null && xp(t),
                        e.blockedOn = n,
                        !1;
                t.shift()
            }
            return !0
        }
        function Wp(e, t, n) {
            Up(e) && n.delete(t)
        }
        function Gp() {
            jp = !1,
                Mp !== null && Up(Mp) && (Mp = null),
                Np !== null && Up(Np) && (Np = null),
                Pp !== null && Up(Pp) && (Pp = null),
                Fp.forEach(Wp),
                Ip.forEach(Wp)
        }
        function Kp(e, n) {
            e.blockedOn === n && (e.blockedOn = null,
                jp || (jp = !0,
                    t.unstable_scheduleCallback(t.unstable_NormalPriority, Gp)))
        }
        var qp = null;
        function Jp(e) {
            qp !== e && (qp = e,
                t.unstable_scheduleCallback(t.unstable_NormalPriority, function () {
                    qp === e && (qp = null);
                    for (var t = 0; t < e.length; t += 3) {
                        var n = e[t]
                            , r = e[t + 1]
                            , i = e[t + 2];
                        if (typeof r != `function`) {
                            if (kp(r || n) === null)
                                continue;
                            break
                        }
                        var a = yt(n);
                        a !== null && (e.splice(t, 3),
                            t -= 3,
                            Rs(a, {
                                pending: !0,
                                data: i,
                                method: n.method,
                                action: r
                            }, r, i))
                    }
                }))
        }
        function Yp(e) {
            function t(t) {
                return Kp(t, e)
            }
            Mp !== null && Kp(Mp, e),
                Np !== null && Kp(Np, e),
                Pp !== null && Kp(Pp, e),
                Fp.forEach(t),
                Ip.forEach(t);
            for (var n = 0; n < Lp.length; n++) {
                var r = Lp[n];
                r.blockedOn === e && (r.blockedOn = null)
            }
            for (; 0 < Lp.length && (n = Lp[0],
                n.blockedOn === null);)
                Hp(n),
                    n.blockedOn === null && Lp.shift();
            if (n = (e.ownerDocument || e).$$reactFormReplay,
                n != null)
                for (r = 0; r < n.length; r += 3) {
                    var i = n[r]
                        , a = n[r + 1]
                        , o = i[ut] || null;
                    if (typeof a == `function`)
                        o || Jp(n);
                    else if (o) {
                        var s = null;
                        if (a && a.hasAttribute(`formAction`)) {
                            if (i = a,
                                o = a[ut] || null)
                                s = o.formAction;
                            else if (kp(i) !== null)
                                continue
                        } else
                            s = o.action;
                        typeof s == `function` ? n[r + 1] = s : (n.splice(r, 3),
                            r -= 3),
                            Jp(n)
                    }
                }
        }
        function Xp() {
            function e(e) {
                e.canIntercept && e.info === `react-transition` && e.intercept({
                    handler: function () {
                        return new Promise(function (e) {
                            return i = e
                        }
                        )
                    },
                    focusReset: `manual`,
                    scroll: `manual`
                })
            }
            function t() {
                i !== null && (i(),
                    i = null),
                    r || setTimeout(n, 20)
            }
            function n() {
                if (!r && !navigation.transition) {
                    var e = navigation.currentEntry;
                    e && e.url != null && navigation.navigate(e.url, {
                        state: e.getState(),
                        info: `react-transition`,
                        history: `replace`
                    })
                }
            }
            if (typeof navigation == `object`) {
                var r = !1
                    , i = null;
                return navigation.addEventListener(`navigate`, e),
                    navigation.addEventListener(`navigatesuccess`, t),
                    navigation.addEventListener(`navigateerror`, t),
                    setTimeout(n, 100),
                    function () {
                        r = !0,
                            navigation.removeEventListener(`navigate`, e),
                            navigation.removeEventListener(`navigatesuccess`, t),
                            navigation.removeEventListener(`navigateerror`, t),
                            i !== null && (i(),
                                i = null)
                    }
            }
        }
        function Zp(e) {
            this._internalRoot = e
        }
        Qp.prototype.render = Zp.prototype.render = function (e) {
            var t = this._internalRoot;
            if (t === null)
                throw Error(i(409));
            var n = t.current
                , r = Eu();
            vp(n, r, e, t, null, null)
        }
            ,
            Qp.prototype.unmount = Zp.prototype.unmount = function () {
                var e = this._internalRoot;
                if (e !== null) {
                    this._internalRoot = null;
                    var t = e.containerInfo;
                    vp(e.current, 2, null, e, null, null),
                        Nu(),
                        t[dt] = null
                }
            }
            ;
        function Qp(e) {
            this._internalRoot = e
        }
        Qp.prototype.unstable_scheduleHydration = function (e) {
            if (e) {
                var t = ot();
                e = {
                    blockedOn: null,
                    target: e,
                    priority: t
                };
                for (var n = 0; n < Lp.length && t !== 0 && t < Lp[n].priority; n++)
                    ;
                Lp.splice(n, 0, e),
                    n === 0 && Hp(e)
            }
        }
            ;
        var $p = n.version;
        if ($p !== `19.2.0`)
            throw Error(i(527, $p, `19.2.0`));
        A.findDOMNode = function (e) {
            var t = e._reactInternals;
            if (t === void 0)
                throw typeof e.render == `function` ? Error(i(188)) : (e = Object.keys(e).join(`,`),
                    Error(i(268, e)));
            return e = d(t),
                e = e === null ? null : p(e),
                e = e === null ? null : e.stateNode,
                e
        }
            ;
        var em = {
            bundleType: 0,
            version: `19.2.0`,
            rendererPackageName: `react-dom`,
            currentDispatcherRef: k,
            reconcilerVersion: `19.2.0`
        };
        if (typeof __REACT_DEVTOOLS_GLOBAL_HOOK__ < `u`) {
            var tm = __REACT_DEVTOOLS_GLOBAL_HOOK__;
            if (!tm.isDisabled && tm.supportsFiber)
                try {
                    Le = tm.inject(em),
                        Re = tm
                } catch { }
        }
        e.createRoot = function (e, t) {
            if (!a(e))
                throw Error(i(299));
            var n = !1
                , r = ``
                , o = sc
                , s = cc
                , c = lc;
            return t != null && (!0 === t.unstable_strictMode && (n = !0),
                t.identifierPrefix !== void 0 && (r = t.identifierPrefix),
                t.onUncaughtError !== void 0 && (o = t.onUncaughtError),
                t.onCaughtError !== void 0 && (s = t.onCaughtError),
                t.onRecoverableError !== void 0 && (c = t.onRecoverableError)),
                t = gp(e, 1, !1, null, null, n, r, null, o, s, c, Xp),
                e[dt] = t.current,
                Ld(e),
                new Zp(t)
        }
    }
    ))
    , g = c(o(((e, t) => {
        function n() {
            if (!(typeof __REACT_DEVTOOLS_GLOBAL_HOOK__ > `u` || typeof __REACT_DEVTOOLS_GLOBAL_HOOK__.checkDCE != `function`))
                try {
                    __REACT_DEVTOOLS_GLOBAL_HOOK__.checkDCE(n)
                } catch (e) {
                    console.error(e)
                }
        }
        n(),
            t.exports = h()
    }
    ))())
    , _ = new WeakMap
    , v = new WeakMap
    , y = {
        current: []
    }
    , b = !1
    , x = 0
    , S = new Set
    , C = new Map;
function w(e) {
    let t = Array.from(e).sort((e, t) => e instanceof ne && e.options.deps.includes(t) ? 1 : t instanceof ne && t.options.deps.includes(e) ? -1 : 0);
    for (let e of t) {
        if (y.current.includes(e))
            continue;
        y.current.push(e),
            e.recompute();
        let t = v.get(e);
        if (t)
            for (let e of t) {
                let t = _.get(e);
                t && w(t)
            }
    }
}
function T(e) {
    let t = {
        prevVal: e.prevState,
        currentVal: e.state
    };
    for (let n of e.listeners)
        n(t)
}
function E(e) {
    let t = {
        prevVal: e.prevState,
        currentVal: e.state
    };
    for (let n of e.listeners)
        n(t)
}
function D(e) {
    if (x > 0 && !C.has(e) && C.set(e, e.prevState),
        S.add(e),
        !(x > 0) && !b)
        try {
            for (b = !0; S.size > 0;) {
                let e = Array.from(S);
                S.clear();
                for (let t of e)
                    t.prevState = C.get(t) ?? t.prevState,
                        T(t);
                for (let t of e) {
                    let e = _.get(t);
                    e && (y.current.push(t),
                        w(e))
                }
                for (let t of e) {
                    let e = _.get(t);
                    if (e)
                        for (let t of e)
                            E(t)
                }
            }
        } finally {
            b = !1,
                y.current = [],
                C.clear()
        }
}
function O(e) {
    x++;
    try {
        e()
    } finally {
        if (x--,
            x === 0) {
            let e = S.values().next().value;
            e && D(e)
        }
    }
}
function ee(e) {
    return typeof e == `function`
}
var te = class {
    constructor(e, t) {
        this.listeners = new Set,
            this.subscribe = e => {
                var t;
                this.listeners.add(e);
                let n = ((t = this.options)?.onSubscribe)?.call(t, e, this);
                return () => {
                    this.listeners.delete(e),
                        n?.()
                }
            }
            ,
            this.prevState = e,
            this.state = e,
            this.options = t
    }
    setState(e) {
        var t, n;
        this.prevState = this.state,
            this.options?.updateFn ? this.state = this.options.updateFn(this.prevState)(e) : ee(e) ? this.state = e(this.prevState) : this.state = e,
            (n = (t = this.options)?.onUpdate) == null || n.call(t),
            D(this)
    }
}
    , ne = class e {
        constructor(t) {
            this.listeners = new Set,
                this._subscriptions = [],
                this.lastSeenDepValues = [],
                this.getDepVals = () => {
                    let e = this.options.deps.length
                        , t = Array(e)
                        , n = Array(e);
                    for (let r = 0; r < e; r++) {
                        let e = this.options.deps[r];
                        t[r] = e.prevState,
                            n[r] = e.state
                    }
                    return this.lastSeenDepValues = n,
                    {
                        prevDepVals: t,
                        currDepVals: n,
                        prevVal: this.prevState ?? void 0
                    }
                }
                ,
                this.recompute = () => {
                    var e, t;
                    this.prevState = this.state;
                    let n = this.getDepVals();
                    this.state = this.options.fn(n),
                        (t = (e = this.options).onUpdate) == null || t.call(e)
                }
                ,
                this.checkIfRecalculationNeededDeeply = () => {
                    for (let t of this.options.deps)
                        t instanceof e && t.checkIfRecalculationNeededDeeply();
                    let t = !1
                        , n = this.lastSeenDepValues
                        , { currDepVals: r } = this.getDepVals();
                    for (let e = 0; e < r.length; e++)
                        if (r[e] !== n[e]) {
                            t = !0;
                            break
                        }
                    t && this.recompute()
                }
                ,
                this.mount = () => (this.registerOnGraph(),
                    this.checkIfRecalculationNeededDeeply(),
                    () => {
                        this.unregisterFromGraph();
                        for (let e of this._subscriptions)
                            e()
                    }
                ),
                this.subscribe = e => {
                    var t;
                    this.listeners.add(e);
                    let n = (t = this.options).onSubscribe?.call(t, e, this);
                    return () => {
                        this.listeners.delete(e),
                            n?.()
                    }
                }
                ,
                this.options = t,
                this.state = t.fn({
                    prevDepVals: void 0,
                    prevVal: void 0,
                    currDepVals: this.getDepVals().currDepVals
                })
        }
        registerOnGraph(t = this.options.deps) {
            for (let n of t)
                if (n instanceof e)
                    n.registerOnGraph(),
                        this.registerOnGraph(n.options.deps);
                else if (n instanceof te) {
                    let e = _.get(n);
                    e || (e = new Set,
                        _.set(n, e)),
                        e.add(this);
                    let t = v.get(this);
                    t || (t = new Set,
                        v.set(this, t)),
                        t.add(n)
                }
        }
        unregisterFromGraph(t = this.options.deps) {
            for (let n of t)
                if (n instanceof e)
                    this.unregisterFromGraph(n.options.deps);
                else if (n instanceof te) {
                    let e = _.get(n);
                    e && e.delete(this);
                    let t = v.get(this);
                    t && t.delete(n)
                }
        }
    }
    , re = `__TSR_index`
    , ie = `popstate`
    , ae = `beforeunload`;
function oe(e) {
    let t = e.getLocation()
        , n = new Set
        , r = r => {
            t = e.getLocation(),
                n.forEach(e => e({
                    location: t,
                    action: r
                }))
        }
        , i = n => {
            e.notifyOnIndexChange ?? !0 ? r(n) : t = e.getLocation()
        }
        , a = async ({ task: n, navigateOpts: r, ...i }) => {
            if (r?.ignoreBlocker ?? !1) {
                n();
                return
            }
            let a = e.getBlockers?.() ?? []
                , o = i.type === `PUSH` || i.type === `REPLACE`;
            if (typeof document < `u` && a.length && o)
                for (let n of a) {
                    let r = se(i.path, i.state);
                    if (await n.blockerFn({
                        currentLocation: t,
                        nextLocation: r,
                        action: i.type
                    })) {
                        e.onBlocked?.();
                        return
                    }
                }
            n()
        }
        ;
    return {
        get location() {
            return t
        },
        get length() {
            return e.getLength()
        },
        subscribers: n,
        subscribe: e => (n.add(e),
            () => {
                n.delete(e)
            }
        ),
        push: (n, i, o) => {
            let s = t.state[re];
            i = k(s + 1, i),
                a({
                    task: () => {
                        e.pushState(n, i),
                            r({
                                type: `PUSH`
                            })
                    }
                    ,
                    navigateOpts: o,
                    type: `PUSH`,
                    path: n,
                    state: i
                })
        }
        ,
        replace: (n, i, o) => {
            let s = t.state[re];
            i = k(s, i),
                a({
                    task: () => {
                        e.replaceState(n, i),
                            r({
                                type: `REPLACE`
                            })
                    }
                    ,
                    navigateOpts: o,
                    type: `REPLACE`,
                    path: n,
                    state: i
                })
        }
        ,
        go: (t, n) => {
            a({
                task: () => {
                    e.go(t),
                        i({
                            type: `GO`,
                            index: t
                        })
                }
                ,
                navigateOpts: n,
                type: `GO`
            })
        }
        ,
        back: t => {
            a({
                task: () => {
                    e.back(t?.ignoreBlocker ?? !1),
                        i({
                            type: `BACK`
                        })
                }
                ,
                navigateOpts: t,
                type: `BACK`
            })
        }
        ,
        forward: t => {
            a({
                task: () => {
                    e.forward(t?.ignoreBlocker ?? !1),
                        i({
                            type: `FORWARD`
                        })
                }
                ,
                navigateOpts: t,
                type: `FORWARD`
            })
        }
        ,
        canGoBack: () => t.state[re] !== 0,
        createHref: t => e.createHref(t),
        block: t => {
            if (!e.setBlockers)
                return () => { }
                    ;
            let n = e.getBlockers?.() ?? [];
            return e.setBlockers([...n, t]),
                () => {
                    let n = e.getBlockers?.() ?? [];
                    e.setBlockers?.(n.filter(e => e !== t))
                }
        }
        ,
        flush: () => e.flush?.(),
        destroy: () => e.destroy?.(),
        notify: r
    }
}
function k(e, t) {
    t ||= {};
    let n = ce();
    return {
        ...t,
        key: n,
        __TSR_key: n,
        [re]: e
    }
}
function A(e) {
    let t = e?.window ?? (typeof document < `u` ? window : void 0)
        , n = t.history.pushState
        , r = t.history.replaceState
        , i = []
        , a = () => i
        , o = e => i = e
        , s = e?.createHref ?? (e => e)
        , c = e?.parseLocation ?? (() => se(`${t.location.pathname}${t.location.search}${t.location.hash}`, t.history.state));
    if (!t.history.state?.__TSR_key && !t.history.state?.key) {
        let e = ce();
        t.history.replaceState({
            [re]: 0,
            key: e,
            __TSR_key: e
        }, ``)
    }
    let l = c(), u, d = !1, f = !1, p = !1, m = !1, h = () => l, g, _, v = () => {
        g && (C._ignoreSubscribers = !0,
            (g.isPush ? t.history.pushState : t.history.replaceState)(g.state, ``, g.href),
            C._ignoreSubscribers = !1,
            g = void 0,
            _ = void 0,
            u = void 0)
    }
        , y = (e, t, n) => {
            let r = s(t);
            _ || (u = l),
                l = se(t, n),
                g = {
                    href: r,
                    state: n,
                    isPush: g?.isPush || e === `push`
                },
                _ ||= Promise.resolve().then(() => v())
        }
        , b = e => {
            l = c(),
                C.notify({
                    type: e
                })
        }
        , x = async () => {
            if (f) {
                f = !1;
                return
            }
            let e = c()
                , n = e.state[re] - l.state[re]
                , r = n === 1
                , i = n === -1
                , o = !r && !i || d;
            d = !1;
            let s = o ? `GO` : i ? `BACK` : `FORWARD`
                , u = o ? {
                    type: `GO`,
                    index: n
                } : {
                    type: i ? `BACK` : `FORWARD`
                };
            if (p)
                p = !1;
            else {
                let n = a();
                if (typeof document < `u` && n.length) {
                    for (let r of n)
                        if (await r.blockerFn({
                            currentLocation: l,
                            nextLocation: e,
                            action: s
                        })) {
                            f = !0,
                                t.history.go(1),
                                C.notify(u);
                            return
                        }
                }
            }
            l = c(),
                C.notify(u)
        }
        , S = e => {
            if (m) {
                m = !1;
                return
            }
            let t = !1
                , n = a();
            if (typeof document < `u` && n.length)
                for (let e of n) {
                    let n = e.enableBeforeUnload ?? !0;
                    if (n === !0) {
                        t = !0;
                        break
                    }
                    if (typeof n == `function` && n() === !0) {
                        t = !0;
                        break
                    }
                }
            if (t)
                return e.preventDefault(),
                    e.returnValue = ``
        }
        , C = oe({
            getLocation: h,
            getLength: () => t.history.length,
            pushState: (e, t) => y(`push`, e, t),
            replaceState: (e, t) => y(`replace`, e, t),
            back: e => (e && (p = !0),
                m = !0,
                t.history.back()),
            forward: e => {
                e && (p = !0),
                    m = !0,
                    t.history.forward()
            }
            ,
            go: e => {
                d = !0,
                    t.history.go(e)
            }
            ,
            createHref: e => s(e),
            flush: v,
            destroy: () => {
                t.history.pushState = n,
                    t.history.replaceState = r,
                    t.removeEventListener(ae, S, {
                        capture: !0
                    }),
                    t.removeEventListener(ie, x)
            }
            ,
            onBlocked: () => {
                u && l !== u && (l = u)
            }
            ,
            getBlockers: a,
            setBlockers: o,
            notifyOnIndexChange: !1
        });
    return t.addEventListener(ae, S, {
        capture: !0
    }),
        t.addEventListener(ie, x),
        t.history.pushState = function (...e) {
            let r = n.apply(t.history, e);
            return C._ignoreSubscribers || b(`PUSH`),
                r
        }
        ,
        t.history.replaceState = function (...e) {
            let n = r.apply(t.history, e);
            return C._ignoreSubscribers || b(`REPLACE`),
                n
        }
        ,
        C
}
function se(e, t) {
    let n = e.indexOf(`#`)
        , r = e.indexOf(`?`)
        , i = ce();
    return {
        href: e,
        pathname: e.substring(0, n > 0 ? r > 0 ? Math.min(n, r) : n : r > 0 ? r : e.length),
        hash: n > -1 ? e.substring(n) : ``,
        search: r > -1 ? e.slice(r, n === -1 ? void 0 : n) : ``,
        state: t || {
            [re]: 0,
            key: i,
            __TSR_key: i
        }
    }
}
function ce() {
    return (Math.random() + 1).toString(36).substring(7)
}
function le(e) {
    return e[e.length - 1]
}
function j(e) {
    return typeof e == `function`
}
function M(e, t) {
    return j(e) ? e(t) : e
}
var N = Object.prototype.hasOwnProperty;
function P(e, t) {
    if (e === t)
        return e;
    let n = t
        , r = fe(e) && fe(n);
    if (!r && !(F(e) && F(n)))
        return n;
    let i = r ? e : ue(e);
    if (!i)
        return n;
    let a = r ? n : ue(n);
    if (!a)
        return n;
    let o = i.length
        , s = a.length
        , c = r ? Array(s) : {}
        , l = 0;
    for (let t = 0; t < s; t++) {
        let i = r ? t : a[t]
            , s = e[i]
            , u = n[i];
        if (s === u) {
            c[i] = s,
                (r ? t < o : N.call(e, i)) && l++;
            continue
        }
        if (s === null || u === null || typeof s != `object` || typeof u != `object`) {
            c[i] = u;
            continue
        }
        let d = P(s, u);
        c[i] = d,
            d === s && l++
    }
    return o === s && l === o ? e : c
}
function ue(e) {
    let t = []
        , n = Object.getOwnPropertyNames(e);
    for (let r of n) {
        if (!Object.prototype.propertyIsEnumerable.call(e, r))
            return !1;
        t.push(r)
    }
    let r = Object.getOwnPropertySymbols(e);
    for (let n of r) {
        if (!Object.prototype.propertyIsEnumerable.call(e, n))
            return !1;
        t.push(n)
    }
    return t
}
function F(e) {
    if (!de(e))
        return !1;
    let t = e.constructor;
    if (t === void 0)
        return !0;
    let n = t.prototype;
    return !(!de(n) || !n.hasOwnProperty(`isPrototypeOf`))
}
function de(e) {
    return Object.prototype.toString.call(e) === `[object Object]`
}
function fe(e) {
    return Array.isArray(e) && e.length === Object.keys(e).length
}
function pe(e, t, n) {
    if (e === t)
        return !0;
    if (typeof e != typeof t)
        return !1;
    if (Array.isArray(e) && Array.isArray(t)) {
        if (e.length !== t.length)
            return !1;
        for (let r = 0, i = e.length; r < i; r++)
            if (!pe(e[r], t[r], n))
                return !1;
        return !0
    }
    if (F(e) && F(t)) {
        let r = n?.ignoreUndefined ?? !0;
        if (n?.partial) {
            for (let i in t)
                if ((!r || t[i] !== void 0) && !pe(e[i], t[i], n))
                    return !1;
            return !0
        }
        let i = 0;
        if (!r)
            i = Object.keys(e).length;
        else
            for (let t in e)
                e[t] !== void 0 && i++;
        let a = 0;
        for (let o in t)
            if ((!r || t[o] !== void 0) && (a++,
                a > i || !pe(e[o], t[o], n)))
                return !1;
        return i === a
    }
    return !1
}
function me(e) {
    let t, n, r = new Promise((e, r) => {
        t = e,
            n = r
    }
    );
    return r.status = `pending`,
        r.resolve = n => {
            r.status = `resolved`,
                r.value = n,
                t(n),
                e?.(n)
        }
        ,
        r.reject = e => {
            r.status = `rejected`,
                n(e)
        }
        ,
        r
}
function he(e) {
    return !!(e && typeof e == `object` && typeof e.then == `function`)
}
var ge = !0
    , _e = `Invariant failed`;
function ve(e, t) {
    if (!e) {
        if (ge)
            throw Error(_e);
        var n = typeof t == `function` ? t() : t
            , r = n ? `${_e}: ${n}` : _e;
        throw Error(r)
    }
}
var ye = `__root__`;
function be(e) {
    return xe(e.filter(e => e !== void 0).join(`/`))
}
function xe(e) {
    return e.replace(/\/{2,}/g, `/`)
}
function Se(e) {
    return e === `/` ? e : e.replace(/^\/{1,}/, ``)
}
function Ce(e) {
    return e === `/` ? e : e.replace(/\/{1,}$/, ``)
}
function we(e) {
    return Ce(Se(e))
}
function Te(e, t) {
    return e?.endsWith(`/`) && e !== `/` && e !== `${t}/` ? e.slice(0, -1) : e
}
function Ee(e, t, n) {
    return Te(e, n) === Te(t, n)
}
function De(e) {
    let { type: t, value: n } = e;
    if (t === 0)
        return n;
    let { prefixSegment: r, suffixSegment: i } = e;
    if (t === 1) {
        let e = n.substring(1);
        if (r && i)
            return `${r}{$${e}}${i}`;
        if (r)
            return `${r}{$${e}}`;
        if (i)
            return `{$${e}}${i}`
    }
    if (t === 3) {
        let e = n.substring(1);
        return r && i ? `${r}{-$${e}}${i}` : r ? `${r}{-$${e}}` : i ? `{-$${e}}${i}` : `{-$${e}}`
    }
    if (t === 2) {
        if (r && i)
            return `${r}{$}${i}`;
        if (r)
            return `${r}{$}`;
        if (i)
            return `{$}${i}`
    }
    return n
}
function Oe({ base: e, to: t, trailingSlash: n = `never`, parseCache: r }) {
    let i = ke(e, r).slice()
        , a = Ae(t, r);
    i.length > 1 && le(i)?.value === `/` && i.pop();
    for (let e = 0, t = a.length; e < t; e++) {
        let n = a[e]
            , r = n.value;
        r === `/` ? e ? e === t - 1 && i.push(n) : i = [n] : r === `..` ? i.pop() : r === `.` || i.push(n)
    }
    i.length > 1 && (le(i).value === `/` ? n === `never` && i.pop() : n === `always` && i.push({
        type: 0,
        value: `/`
    }));
    let o = i.map(De);
    return be(o)
}
var ke = (e, t) => je(e, t, !0)
    , Ae = (e, t) => je(e, t, !1)
    , je = (e, t, n) => {
        if (!e)
            return [];
        let r = t?.get(e);
        if (r)
            return r;
        let i = Le(e, n);
        return t?.set(e, i),
            i
    }
    , Me = /^\$.{1,}$/
    , Ne = /^(.*?)\{(\$[a-zA-Z_$][a-zA-Z0-9_$]*)\}(.*)$/
    , Pe = /^(.*?)\{-(\$[a-zA-Z_$][a-zA-Z0-9_$]*)\}(.*)$/
    , Fe = /^\$$/
    , Ie = /^(.*?)\{\$\}(.*)$/;
function Le(e, t) {
    e = xe(e);
    let n = [];
    if (e.slice(0, 1) === `/` && (e = e.substring(1),
        n.push({
            type: 0,
            value: `/`
        })),
        !e)
        return n;
    let r = e.split(`/`).filter(Boolean);
    return n.push(...r.map(e => {
        let n = !t && e !== `__root__` && e.slice(-1) === `_` ? e.slice(0, -1) : e
            , r = n.match(Ie);
        if (r) {
            let e = r[1]
                , t = r[2];
            return {
                type: 2,
                value: `$`,
                prefixSegment: e || void 0,
                suffixSegment: t || void 0
            }
        }
        let i = n.match(Pe);
        if (i) {
            let e = i[1]
                , t = i[2]
                , n = i[3];
            return {
                type: 3,
                value: t,
                prefixSegment: e || void 0,
                suffixSegment: n || void 0
            }
        }
        let a = n.match(Ne);
        if (a) {
            let e = a[1]
                , t = a[2]
                , n = a[3];
            return {
                type: 1,
                value: `` + t,
                prefixSegment: e || void 0,
                suffixSegment: n || void 0
            }
        }
        return Me.test(n) ? {
            type: 1,
            value: `$` + n.substring(1),
            prefixSegment: void 0,
            suffixSegment: void 0
        } : Fe.test(n) ? {
            type: 2,
            value: `$`,
            prefixSegment: void 0,
            suffixSegment: void 0
        } : {
            type: 0,
            value: n.includes(`%25`) ? n.split(`%25`).map(e => decodeURI(e)).join(`%25`) : decodeURI(n)
        }
    }
    )),
        e.slice(-1) === `/` && (e = e.substring(1),
            n.push({
                type: 0,
                value: `/`
            })),
        n
}
function Re({ path: e, params: t, leaveWildcards: n, leaveParams: r, decodeCharMap: i, parseCache: a }) {
    let o = Ae(e, a);
    function s(e) {
        let n = t[e]
            , r = typeof n == `string`;
        return e === `*` || e === `_splat` ? r ? encodeURI(n) : n : r ? ze(n, i) : n
    }
    let c = !1
        , l = {}
        , u = be(o.map(e => {
            if (e.type === 0)
                return e.value;
            if (e.type === 2) {
                l._splat = t._splat,
                    l[`*`] = t._splat;
                let r = e.prefixSegment || ``
                    , i = e.suffixSegment || ``;
                if (!(`_splat` in t))
                    return c = !0,
                        n ? `${r}${e.value}${i}` : r || i ? `${r}${i}` : void 0;
                let a = s(`_splat`);
                return n ? `${r}${e.value}${a ?? ``}${i}` : `${r}${a}${i}`
            }
            if (e.type === 1) {
                let n = e.value.substring(1);
                !c && !(n in t) && (c = !0),
                    l[n] = t[n];
                let i = e.prefixSegment || ``
                    , a = e.suffixSegment || ``;
                if (r) {
                    let t = s(e.value);
                    return `${i}${e.value}${t ?? ``}${a}`
                }
                return `${i}${s(n) ?? `undefined`}${a}`
            }
            if (e.type === 3) {
                let i = e.value.substring(1)
                    , a = e.prefixSegment || ``
                    , o = e.suffixSegment || ``;
                if (!(i in t) || t[i] == null)
                    return n ? `${a}${i}${o}` : a || o ? `${a}${o}` : void 0;
                if (l[i] = t[i],
                    r) {
                    let t = s(e.value);
                    return `${a}${e.value}${t ?? ``}${o}`
                }
                return n ? `${a}${i}${s(i) ?? ``}${o}` : `${a}${s(i) ?? ``}${o}`
            }
            return e.value
        }
        ));
    return {
        usedParams: l,
        interpolatedPath: u,
        isMissingParams: c
    }
}
function ze(e, t) {
    let n = encodeURIComponent(e);
    if (t)
        for (let [e, r] of t)
            n = n.replaceAll(e, r);
    return n
}
function Be(e, t, n) {
    let r = Ve(e, t, n);
    if (!(t.to && !r))
        return r ?? {}
}
function Ve(e, { to: t, fuzzy: n, caseSensitive: r }, i) {
    let a = t
        , o = ke(e.startsWith(`/`) ? e : `/${e}`, i)
        , s = Ae(a.startsWith(`/`) ? a : `/${a}`, i)
        , c = {};
    return He(o, s, c, n, r) ? c : void 0
}
function He(e, t, n, r, i) {
    let a = 0
        , o = 0;
    for (; a < e.length || o < t.length;) {
        let s = e[a]
            , c = t[o];
        if (c) {
            if (c.type === 2) {
                let t = e.slice(a), r;
                if (c.prefixSegment || c.suffixSegment) {
                    if (!s)
                        return !1;
                    let n = c.prefixSegment || ``
                        , i = c.suffixSegment || ``
                        , a = s.value;
                    if (`prefixSegment` in c && !a.startsWith(n) || `suffixSegment` in c && !e[e.length - 1]?.value.endsWith(i))
                        return !1;
                    let o = decodeURI(be(t.map(e => e.value)));
                    n && o.startsWith(n) && (o = o.slice(n.length)),
                        i && o.endsWith(i) && (o = o.slice(0, o.length - i.length)),
                        r = o
                } else
                    r = decodeURI(be(t.map(e => e.value)));
                return n[`*`] = r,
                    n._splat = r,
                    !0
            }
            if (c.type === 0) {
                if (c.value === `/` && !s?.value) {
                    o++;
                    continue
                }
                if (s) {
                    if (i) {
                        if (c.value !== s.value)
                            return !1
                    } else if (c.value.toLowerCase() !== s.value.toLowerCase())
                        return !1;
                    a++,
                        o++;
                    continue
                } else
                    return !1
            }
            if (c.type === 1) {
                if (!s || s.value === `/`)
                    return !1;
                let e = ``
                    , t = !1;
                if (c.prefixSegment || c.suffixSegment) {
                    let n = c.prefixSegment || ``
                        , r = c.suffixSegment || ``
                        , i = s.value;
                    if (n && !i.startsWith(n) || r && !i.endsWith(r))
                        return !1;
                    let a = i;
                    n && a.startsWith(n) && (a = a.slice(n.length)),
                        r && a.endsWith(r) && (a = a.slice(0, a.length - r.length)),
                        e = decodeURIComponent(a),
                        t = !0
                } else
                    e = decodeURIComponent(s.value),
                        t = !0;
                t && (n[c.value.substring(1)] = e,
                    a++),
                    o++;
                continue
            }
            if (c.type === 3) {
                if (!s) {
                    o++;
                    continue
                }
                if (s.value === `/`) {
                    o++;
                    continue
                }
                let r = ``
                    , i = !1;
                if (c.prefixSegment || c.suffixSegment) {
                    let e = c.prefixSegment || ``
                        , t = c.suffixSegment || ``
                        , n = s.value;
                    if ((!e || n.startsWith(e)) && (!t || n.endsWith(t))) {
                        let a = n;
                        e && a.startsWith(e) && (a = a.slice(e.length)),
                            t && a.endsWith(t) && (a = a.slice(0, a.length - t.length)),
                            r = decodeURIComponent(a),
                            i = !0
                    }
                } else {
                    let n = !0;
                    for (let r = o + 1; r < t.length; r++) {
                        let i = t[r];
                        if (i?.type === 0 && i.value === s.value) {
                            n = !1;
                            break
                        }
                        if (i?.type === 1 || i?.type === 2) {
                            e.length < t.length && (n = !1);
                            break
                        }
                    }
                    n && (r = decodeURIComponent(s.value),
                        i = !0)
                }
                i && (n[c.value.substring(1)] = r,
                    a++),
                    o++;
                continue
            }
        }
        if (a < e.length && o >= t.length)
            return n[`**`] = be(e.slice(a).map(e => e.value)),
                !!r && t[t.length - 1]?.value !== `/`;
        if (o < t.length && a >= e.length) {
            for (let e = o; e < t.length; e++)
                if (t[e]?.type !== 3)
                    return !1;
            break
        }
        break
    }
    return !0
}
var Ue = .75
    , We = 1
    , Ge = .5
    , Ke = .4
    , qe = .25
    , Je = .2
    , Ye = .05
    , Xe = .02
    , Ze = .01
    , Qe = 2e-4
    , $e = 1e-4;
function et(e, t) {
    return e.prefixSegment && e.suffixSegment ? t + Ye + Qe * e.prefixSegment.length + $e * e.suffixSegment.length : e.prefixSegment ? t + Xe + Qe * e.prefixSegment.length : e.suffixSegment ? t + Ze + $e * e.suffixSegment.length : t
}
function tt(e) {
    let t = [];
    return e.forEach((e, n) => {
        if (e.isRoot || !e.path)
            return;
        let r = Se(e.fullPath)
            , i = Ae(r)
            , a = 0;
        for (; i.length > a + 1 && i[a]?.value === `/`;)
            a++;
        a > 0 && (i = i.slice(a));
        let o = 0
            , s = !1
            , c = i.map((e, t) => {
                if (e.value === `/`)
                    return Ue;
                if (e.type === 0)
                    return We;
                let n;
                e.type === 1 ? n = Ge : e.type === 3 ? (n = Ke,
                    o++) : n = qe;
                for (let r = t + 1; r < i.length; r++) {
                    let t = i[r];
                    if (t.type === 0 && t.value !== `/`)
                        return s = !0,
                            et(e, n + Je)
                }
                return et(e, n)
            }
            );
        t.push({
            child: e,
            trimmed: r,
            parsed: i,
            index: n,
            scores: c,
            optionalParamCount: o,
            hasStaticAfter: s
        })
    }
    ),
        t.sort((e, t) => {
            let n = Math.min(e.scores.length, t.scores.length);
            for (let r = 0; r < n; r++)
                if (e.scores[r] !== t.scores[r])
                    return t.scores[r] - e.scores[r];
            if (e.scores.length !== t.scores.length) {
                if (e.optionalParamCount !== t.optionalParamCount) {
                    if (e.hasStaticAfter === t.hasStaticAfter)
                        return e.optionalParamCount - t.optionalParamCount;
                    if (e.hasStaticAfter && !t.hasStaticAfter)
                        return -1;
                    if (!e.hasStaticAfter && t.hasStaticAfter)
                        return 1
                }
                return t.scores.length - e.scores.length
            }
            for (let r = 0; r < n; r++)
                if (e.parsed[r].value !== t.parsed[r].value)
                    return e.parsed[r].value > t.parsed[r].value ? 1 : -1;
            return e.index - t.index
        }
        ).map((e, t) => (e.child.rank = t,
            e.child))
}
function nt({ routeTree: e, initRoute: t }) {
    let n = {}
        , r = {}
        , i = e => {
            e.forEach((e, a) => {
                t?.(e, a);
                let o = n[e.id];
                if (ve(!o, `Duplicate routes found with id: ${String(e.id)}`),
                    n[e.id] = e,
                    !e.isRoot && e.path) {
                    let t = Ce(e.fullPath);
                    (!r[t] || e.fullPath.endsWith(`/`)) && (r[t] = e)
                }
                let s = e.children;
                s?.length && i(s)
            }
            )
        }
        ;
    i([e]);
    let a = tt(Object.values(n));
    return {
        routesById: n,
        routesByPath: r,
        flatRoutes: a
    }
}
function rt(e) {
    return !!e?.isNotFound
}
function it() {
    try {
        if (typeof window < `u` && typeof window.sessionStorage == `object`)
            return window.sessionStorage
    } catch { }
}
var at = `tsr-scroll-restoration-v1_3`
    , ot = (e, t) => {
        let n;
        return (...r) => {
            n ||= setTimeout(() => {
                e(...r),
                    n = null
            }
                , t)
        }
    }
    ;
function st() {
    let e = it();
    if (!e)
        return null;
    let t = e.getItem(at)
        , n = t ? JSON.parse(t) : {};
    return {
        state: n,
        set: t => (n = M(t, n) || n,
            e.setItem(at, JSON.stringify(n)))
    }
}
var ct = st()
    , lt = e => e.state.__TSR_key || e.href;
function ut(e) {
    let t = [], n;
    for (; n = e.parentNode;)
        t.push(`${e.tagName}:nth-child(${Array.prototype.indexOf.call(n.children, e) + 1})`),
            e = n;
    return `${t.reverse().join(` > `)}`.toLowerCase()
}
var dt = !1;
function ft({ storageKey: e, key: t, behavior: n, shouldScrollRestoration: r, scrollToTopSelectors: i, location: a }) {
    let o;
    try {
        o = JSON.parse(sessionStorage.getItem(e) || `{}`)
    } catch (e) {
        console.error(e);
        return
    }
    let s = t || window.history.state?.__TSR_key
        , c = o[s];
    dt = !0;
    scroll: {
        if (r && c && Object.keys(c).length > 0) {
            for (let e in c) {
                let t = c[e];
                if (e === `window`)
                    window.scrollTo({
                        top: t.scrollY,
                        left: t.scrollX,
                        behavior: n
                    });
                else if (e) {
                    let n = document.querySelector(e);
                    n && (n.scrollLeft = t.scrollX,
                        n.scrollTop = t.scrollY)
                }
            }
            break scroll
        }
        let e = (a ?? window.location).hash.split(`#`, 2)[1];
        if (e) {
            let t = window.history.state?.__hashScrollIntoViewOptions ?? !0;
            if (t) {
                let n = document.getElementById(e);
                n && n.scrollIntoView(t)
            }
            break scroll
        }
        let t = {
            top: 0,
            left: 0,
            behavior: n
        };
        if (window.scrollTo(t),
            i)
            for (let e of i) {
                if (e === `window`)
                    continue;
                let n = typeof e == `function` ? e() : document.querySelector(e);
                n && n.scrollTo(t)
            }
    }
    dt = !1
}
function pt(e, t) {
    if (!ct && !e.isServer || ((t ?? e.options.scrollRestoration ?? !1) && (e.isScrollRestoring = !0),
        e.isServer || e.isScrollRestorationSetup || !ct))
        return;
    e.isScrollRestorationSetup = !0,
        dt = !1;
    let n = e.options.getScrollRestorationKey || lt;
    window.history.scrollRestoration = `manual`,
        typeof document < `u` && document.addEventListener(`scroll`, ot(t => {
            if (dt || !e.isScrollRestoring)
                return;
            let r = ``;
            if (t.target === document || t.target === window)
                r = `window`;
            else {
                let e = t.target.getAttribute(`data-scroll-restoration-id`);
                r = e ? `[data-scroll-restoration-id="${e}"]` : ut(t.target)
            }
            let i = n(e.state.location);
            ct.set(e => {
                let t = e[i] ||= {}
                    , n = t[r] ||= {};
                if (r === `window`)
                    n.scrollX = window.scrollX || 0,
                        n.scrollY = window.scrollY || 0;
                else if (r) {
                    let e = document.querySelector(r);
                    e && (n.scrollX = e.scrollLeft || 0,
                        n.scrollY = e.scrollTop || 0)
                }
                return e
            }
            )
        }
            , 100), !0),
        e.subscribe(`onRendered`, t => {
            let r = n(t.toLocation);
            if (!e.resetNextScroll) {
                e.resetNextScroll = !0;
                return
            }
            typeof e.options.scrollRestoration == `function` && !e.options.scrollRestoration({
                location: e.latestLocation
            }) || (ft({
                storageKey: at,
                key: r,
                behavior: e.options.scrollRestorationBehavior,
                shouldScrollRestoration: e.isScrollRestoring,
                scrollToTopSelectors: e.options.scrollToTopSelectors,
                location: e.history.location
            }),
                e.isScrollRestoring && ct.set(e => (e[r] ||= {},
                    e)))
        }
        )
}
function mt(e) {
    if (typeof document < `u` && document.querySelector) {
        let t = e.state.location.state.__hashScrollIntoViewOptions ?? !0;
        if (t && e.state.location.hash !== ``) {
            let n = document.getElementById(e.state.location.hash);
            n && n.scrollIntoView(t)
        }
    }
}
function ht(e, t = String) {
    let n = new URLSearchParams;
    for (let r in e) {
        let i = e[r];
        i !== void 0 && n.set(r, t(i))
    }
    return n.toString()
}
function gt(e) {
    return e ? e === `false` ? !1 : e === `true` ? !0 : e * 0 == 0 && +e + `` === e ? +e : e : ``
}
function _t(e) {
    let t = new URLSearchParams(e)
        , n = {};
    for (let [e, r] of t.entries()) {
        let t = n[e];
        t == null ? n[e] = gt(r) : Array.isArray(t) ? t.push(gt(r)) : n[e] = [t, gt(r)]
    }
    return n
}
var vt = bt(JSON.parse)
    , yt = xt(JSON.stringify, JSON.parse);
function bt(e) {
    return t => {
        t[0] === `?` && (t = t.substring(1));
        let n = _t(t);
        for (let t in n) {
            let r = n[t];
            if (typeof r == `string`)
                try {
                    n[t] = e(r)
                } catch { }
        }
        return n
    }
}
function xt(e, t) {
    let n = typeof t == `function`;
    function r(r) {
        if (typeof r == `object` && r)
            try {
                return e(r)
            } catch { }
        else if (n && typeof r == `string`)
            try {
                return t(r),
                    e(r)
            } catch { }
        return r
    }
    return e => {
        let t = ht(e, r);
        return t ? `?${t}` : ``
    }
}
function St(e) {
    if (e.statusCode = e.statusCode || e.code || 307,
        !e.reloadDocument && typeof e.href == `string`)
        try {
            new URL(e.href),
                e.reloadDocument = !0
        } catch { }
    let t = new Headers(e.headers);
    e.href && t.get(`Location`) === null && t.set(`Location`, e.href);
    let n = new Response(null, {
        status: e.statusCode,
        headers: t
    });
    if (n.options = e,
        e.throw)
        throw n;
    return n
}
function Ct(e) {
    return e instanceof Response && !!e.options
}
function wt(e) {
    let t = new Map, n, r, i = e => {
        e.next && (e.prev ? (e.prev.next = e.next,
            e.next.prev = e.prev,
            e.next = void 0,
            r && (r.next = e,
                e.prev = r)) : (e.next.prev = void 0,
                    n = e.next,
                    e.next = void 0,
                    r && (e.prev = r,
                        r.next = e)),
            r = e)
    }
        ;
    return {
        get(e) {
            let n = t.get(e);
            if (n)
                return i(n),
                    n.value
        },
        set(a, o) {
            if (t.size >= e && n) {
                let e = n;
                t.delete(e.key),
                    e.next && (n = e.next,
                        e.next.prev = void 0),
                    e === r && (r = void 0)
            }
            let s = t.get(a);
            if (s)
                s.value = o,
                    i(s);
            else {
                let e = {
                    key: a,
                    value: o,
                    prev: r
                };
                r && (r.next = e),
                    r = e,
                    n ||= e,
                    t.set(a, e)
            }
        }
    }
}
var Tt = e => {
    if (!e.rendered)
        return e.rendered = !0,
            e.onReady?.()
}
    , Et = (e, t) => !!(e.preload && !e.router.state.matches.some(e => e.id === t))
    , Dt = (e, t) => {
        let n = e.router.routesById[t.routeId ?? ``] ?? e.router.routeTree;
        !n.options.notFoundComponent && e.router.options?.defaultNotFoundComponent && (n.options.notFoundComponent = e.router.options.defaultNotFoundComponent),
            ve(n.options.notFoundComponent, `No notFoundComponent found. Please set a notFoundComponent on your route or provide a defaultNotFoundComponent to the router.`);
        let r = e.matches.find(e => e.routeId === n.id);
        ve(r, `Could not find match for route: ` + n.id),
            e.updateMatch(r.id, e => ({
                ...e,
                status: `notFound`,
                error: t,
                isFetching: !1
            })),
            t.routerCode === `BEFORE_LOAD` && n.parentRoute && (t.routeId = n.parentRoute.id,
                Dt(e, t))
    }
    , Ot = (e, t, n) => {
        if (!(!Ct(n) && !rt(n))) {
            if (Ct(n) && n.redirectHandled && !n.options.reloadDocument)
                throw n;
            if (t) {
                t._nonReactive.beforeLoadPromise?.resolve(),
                    t._nonReactive.loaderPromise?.resolve(),
                    t._nonReactive.beforeLoadPromise = void 0,
                    t._nonReactive.loaderPromise = void 0;
                let r = Ct(n) ? `redirected` : `notFound`;
                e.updateMatch(t.id, e => ({
                    ...e,
                    status: r,
                    isFetching: !1,
                    error: n
                })),
                    rt(n) && !n.routeId && (n.routeId = t.routeId),
                    t._nonReactive.loadPromise?.resolve()
            }
            throw Ct(n) ? (e.rendered = !0,
                n.options._fromLocation = e.location,
                n.redirectHandled = !0,
                n = e.router.resolveRedirect(n),
                n) : (Dt(e, n),
                    n)
        }
    }
    , kt = (e, t) => {
        let n = e.router.getMatch(t);
        return !!(!e.router.isServer && n._nonReactive.dehydrated || e.router.isServer && n.ssr === !1)
    }
    , At = (e, t, n, r) => {
        let { id: i, routeId: a } = e.matches[t]
            , o = e.router.looseRoutesById[a];
        if (n instanceof Promise)
            throw n;
        n.routerCode = r,
            e.firstBadMatchIndex ??= t,
            Ot(e, e.router.getMatch(i), n);
        try {
            o.options.onError?.(n)
        } catch (t) {
            n = t,
                Ot(e, e.router.getMatch(i), n)
        }
        e.updateMatch(i, e => (e._nonReactive.beforeLoadPromise?.resolve(),
            e._nonReactive.beforeLoadPromise = void 0,
            e._nonReactive.loadPromise?.resolve(),
        {
            ...e,
            error: n,
            status: `error`,
            isFetching: !1,
            updatedAt: Date.now(),
            abortController: new AbortController
        }))
    }
    , jt = (e, t, n, r) => {
        let i = e.router.getMatch(t)
            , a = e.matches[n - 1]?.id
            , o = a ? e.router.getMatch(a) : void 0;
        if (e.router.isShell()) {
            i.ssr = t === ye;
            return
        }
        if (o?.ssr === !1) {
            i.ssr = !1;
            return
        }
        let s = e => e === !0 && o?.ssr === `data-only` ? `data-only` : e
            , c = e.router.options.defaultSsr ?? !0;
        if (r.options.ssr === void 0) {
            i.ssr = s(c);
            return
        }
        if (typeof r.options.ssr != `function`) {
            i.ssr = s(r.options.ssr);
            return
        }
        let { search: l, params: u } = i
            , d = {
                search: Ht(l, i.searchError),
                params: Ht(u, i.paramsError),
                location: e.location,
                matches: e.matches.map(e => ({
                    index: e.index,
                    pathname: e.pathname,
                    fullPath: e.fullPath,
                    staticData: e.staticData,
                    id: e.id,
                    routeId: e.routeId,
                    search: Ht(e.search, e.searchError),
                    params: Ht(e.params, e.paramsError),
                    ssr: e.ssr
                }))
            }
            , f = r.options.ssr(d);
        if (he(f))
            return f.then(e => {
                i.ssr = s(e ?? c)
            }
            );
        i.ssr = s(f ?? c)
    }
    , Mt = (e, t, n, r) => {
        if (r._nonReactive.pendingTimeout !== void 0)
            return;
        let i = n.options.pendingMs ?? e.router.options.defaultPendingMs;
        if (e.onReady && !e.router.isServer && !Et(e, t) && (n.options.loader || n.options.beforeLoad || Ut(n)) && typeof i == `number` && i !== 1 / 0 && (n.options.pendingComponent ?? e.router.options?.defaultPendingComponent)) {
            let t = setTimeout(() => {
                Tt(e)
            }
                , i);
            r._nonReactive.pendingTimeout = t
        }
    }
    , Nt = (e, t, n) => {
        let r = e.router.getMatch(t);
        if (!r._nonReactive.beforeLoadPromise && !r._nonReactive.loaderPromise)
            return;
        Mt(e, t, n, r);
        let i = () => {
            let n = e.router.getMatch(t);
            n.preload && (n.status === `redirected` || n.status === `notFound`) && Ot(e, n, n.error)
        }
            ;
        return r._nonReactive.beforeLoadPromise ? r._nonReactive.beforeLoadPromise.then(i) : i()
    }
    , Pt = (e, t, n, r) => {
        let i = e.router.getMatch(t)
            , a = i._nonReactive.loadPromise;
        i._nonReactive.loadPromise = me(() => {
            a?.resolve()
        }
        );
        let { paramsError: o, searchError: s } = i;
        o && At(e, n, o, `PARSE_PARAMS`),
            s && At(e, n, s, `VALIDATE_SEARCH`),
            Mt(e, t, r, i);
        let c = new AbortController
            , l = e.matches[n - 1]?.id
            , u = {
                ...(l ? e.router.getMatch(l) : void 0)?.context ?? e.router.options.context ?? void 0,
                ...i.__routeContext
            }
            , d = !1
            , f = () => {
                d || (d = !0,
                    e.updateMatch(t, e => ({
                        ...e,
                        isFetching: `beforeLoad`,
                        fetchCount: e.fetchCount + 1,
                        abortController: c,
                        context: u
                    })))
            }
            , p = () => {
                i._nonReactive.beforeLoadPromise?.resolve(),
                    i._nonReactive.beforeLoadPromise = void 0,
                    e.updateMatch(t, e => ({
                        ...e,
                        isFetching: !1
                    }))
            }
            ;
        if (!r.options.beforeLoad) {
            O(() => {
                f(),
                    p()
            }
            );
            return
        }
        i._nonReactive.beforeLoadPromise = me();
        let { search: m, params: h, cause: g } = i, _ = Et(e, t), v = {
            search: m,
            abortController: c,
            params: h,
            preload: _,
            context: u,
            location: e.location,
            navigate: t => e.router.navigate({
                ...t,
                _fromLocation: e.location
            }),
            buildLocation: e.router.buildLocation,
            cause: _ ? `preload` : g,
            matches: e.matches,
            ...e.router.options.additionalContext
        }, y = r => {
            if (r === void 0) {
                O(() => {
                    f(),
                        p()
                }
                );
                return
            }
            (Ct(r) || rt(r)) && (f(),
                At(e, n, r, `BEFORE_LOAD`)),
                O(() => {
                    f(),
                        e.updateMatch(t, e => ({
                            ...e,
                            __beforeLoadContext: r,
                            context: {
                                ...e.context,
                                ...r
                            }
                        })),
                        p()
                }
                )
        }
            , b;
        try {
            if (b = r.options.beforeLoad(v),
                he(b))
                return f(),
                    b.catch(t => {
                        At(e, n, t, `BEFORE_LOAD`)
                    }
                    ).then(y)
        } catch (t) {
            f(),
                At(e, n, t, `BEFORE_LOAD`)
        }
        y(b)
    }
    , Ft = (e, t) => {
        let { id: n, routeId: r } = e.matches[t]
            , i = e.router.looseRoutesById[r]
            , a = () => {
                if (e.router.isServer) {
                    let r = jt(e, n, t, i);
                    if (he(r))
                        return r.then(s)
                }
                return s()
            }
            , o = () => Pt(e, n, t, i)
            , s = () => {
                if (kt(e, n))
                    return;
                let t = Nt(e, n, i);
                return he(t) ? t.then(o) : o()
            }
            ;
        return a()
    }
    , It = (e, t, n) => {
        let r = e.router.getMatch(t);
        if (!r || !n.options.head && !n.options.scripts && !n.options.headers)
            return;
        let i = {
            matches: e.matches,
            match: r,
            params: r.params,
            loaderData: r.loaderData
        };
        return Promise.all([n.options.head?.(i), n.options.scripts?.(i), n.options.headers?.(i)]).then(([e, t, n]) => {
            let r = e?.meta
                , i = e?.links
                , a = e?.scripts
                , o = e?.styles;
            return {
                meta: r,
                links: i,
                headScripts: a,
                headers: n,
                scripts: t,
                styles: o
            }
        }
        )
    }
    , Lt = (e, t, n, r) => {
        let i = e.matchPromises[n - 1]
            , { params: a, loaderDeps: o, abortController: s, context: c, cause: l } = e.router.getMatch(t)
            , u = Et(e, t);
        return {
            params: a,
            deps: o,
            preload: !!u,
            parentMatchPromise: i,
            abortController: s,
            context: c,
            location: e.location,
            navigate: t => e.router.navigate({
                ...t,
                _fromLocation: e.location
            }),
            cause: u ? `preload` : l,
            route: r,
            ...e.router.options.additionalContext
        }
    }
    , Rt = async (e, t, n, r) => {
        try {
            let i = e.router.getMatch(t);
            try {
                (!e.router.isServer || i.ssr === !0) && Vt(r);
                let a = r.options.loader?.(Lt(e, t, n, r))
                    , o = r.options.loader && he(a);
                if ((o || r._lazyPromise || r._componentsPromise || r.options.head || r.options.scripts || r.options.headers || i._nonReactive.minPendingPromise) && e.updateMatch(t, e => ({
                    ...e,
                    isFetching: `loader`
                })),
                    r.options.loader) {
                    let n = o ? await a : a;
                    Ot(e, e.router.getMatch(t), n),
                        n !== void 0 && e.updateMatch(t, e => ({
                            ...e,
                            loaderData: n
                        }))
                }
                r._lazyPromise && await r._lazyPromise;
                let s = It(e, t, r)
                    , c = s ? await s : void 0
                    , l = i._nonReactive.minPendingPromise;
                l && await l,
                    r._componentsPromise && await r._componentsPromise,
                    e.updateMatch(t, e => ({
                        ...e,
                        error: void 0,
                        status: `success`,
                        isFetching: !1,
                        updatedAt: Date.now(),
                        ...c
                    }))
            } catch (n) {
                let a = n
                    , o = i._nonReactive.minPendingPromise;
                o && await o,
                    Ot(e, e.router.getMatch(t), n);
                try {
                    r.options.onError?.(n)
                } catch (n) {
                    a = n,
                        Ot(e, e.router.getMatch(t), n)
                }
                let s = It(e, t, r)
                    , c = s ? await s : void 0;
                e.updateMatch(t, e => ({
                    ...e,
                    error: a,
                    status: `error`,
                    isFetching: !1,
                    ...c
                }))
            }
        } catch (n) {
            let i = e.router.getMatch(t);
            if (i) {
                let n = It(e, t, r);
                if (n) {
                    let r = await n;
                    e.updateMatch(t, e => ({
                        ...e,
                        ...r
                    }))
                }
                i._nonReactive.loaderPromise = void 0
            }
            Ot(e, i, n)
        }
    }
    , zt = async (e, t) => {
        let { id: n, routeId: r } = e.matches[t]
            , i = !1
            , a = !1
            , o = e.router.looseRoutesById[r];
        if (kt(e, n)) {
            if (e.router.isServer) {
                let t = It(e, n, o);
                if (t) {
                    let r = await t;
                    e.updateMatch(n, e => ({
                        ...e,
                        ...r
                    }))
                }
                return e.router.getMatch(n)
            }
        } else {
            let r = e.router.getMatch(n);
            if (r._nonReactive.loaderPromise) {
                if (r.status === `success` && !e.sync && !r.preload)
                    return r;
                await r._nonReactive.loaderPromise;
                let t = e.router.getMatch(n);
                t.error && Ot(e, t, t.error)
            } else {
                let s = Date.now() - r.updatedAt
                    , c = Et(e, n)
                    , l = c ? o.options.preloadStaleTime ?? e.router.options.defaultPreloadStaleTime ?? 3e4 : o.options.staleTime ?? e.router.options.defaultStaleTime ?? 0
                    , u = o.options.shouldReload
                    , d = typeof u == `function` ? u(Lt(e, n, t, o)) : u
                    , f = !!c && !e.router.state.matches.some(e => e.id === n)
                    , p = e.router.getMatch(n);
                p._nonReactive.loaderPromise = me(),
                    f !== p.preload && e.updateMatch(n, e => ({
                        ...e,
                        preload: f
                    }));
                let { status: m, invalid: h } = p;
                if (i = m === `success` && (h || (d ?? s > l)),
                    !(c && o.options.preload === !1))
                    if (i && !e.sync)
                        a = !0,
                            (async () => {
                                try {
                                    await Rt(e, n, t, o);
                                    let r = e.router.getMatch(n);
                                    r._nonReactive.loaderPromise?.resolve(),
                                        r._nonReactive.loadPromise?.resolve(),
                                        r._nonReactive.loaderPromise = void 0
                                } catch (t) {
                                    Ct(t) && await e.router.navigate(t.options)
                                }
                            }
                            )();
                    else if (m !== `success` || i && e.sync)
                        await Rt(e, n, t, o);
                    else {
                        let t = It(e, n, o);
                        if (t) {
                            let r = await t;
                            e.updateMatch(n, e => ({
                                ...e,
                                ...r
                            }))
                        }
                    }
            }
        }
        let s = e.router.getMatch(n);
        a || (s._nonReactive.loaderPromise?.resolve(),
            s._nonReactive.loadPromise?.resolve()),
            clearTimeout(s._nonReactive.pendingTimeout),
            s._nonReactive.pendingTimeout = void 0,
            a || (s._nonReactive.loaderPromise = void 0),
            s._nonReactive.dehydrated = void 0;
        let c = a ? s.isFetching : !1;
        return c !== s.isFetching || s.invalid !== !1 ? (e.updateMatch(n, e => ({
            ...e,
            isFetching: c,
            invalid: !1
        })),
            e.router.getMatch(n)) : s
    }
    ;
async function Bt(e) {
    let t = Object.assign(e, {
        matchPromises: []
    });
    !t.router.isServer && t.router.state.matches.some(e => e._forcePending) && Tt(t);
    try {
        for (let e = 0; e < t.matches.length; e++) {
            let n = Ft(t, e);
            he(n) && await n
        }
        let e = t.firstBadMatchIndex ?? t.matches.length;
        for (let n = 0; n < e; n++)
            t.matchPromises.push(zt(t, n));
        await Promise.all(t.matchPromises);
        let n = Tt(t);
        he(n) && await n
    } catch (e) {
        if (rt(e) && !t.preload) {
            let n = Tt(t);
            throw he(n) && await n,
            e
        }
        if (Ct(e))
            throw e
    }
    return t.matches
}
async function Vt(e) {
    if (!e._lazyLoaded && e._lazyPromise === void 0 && (e.lazyFn ? e._lazyPromise = e.lazyFn().then(t => {
        let { id: n, ...r } = t.options;
        Object.assign(e.options, r),
            e._lazyLoaded = !0,
            e._lazyPromise = void 0
    }
    ) : e._lazyLoaded = !0),
        !e._componentsLoaded && e._componentsPromise === void 0) {
        let t = () => {
            let t = [];
            for (let n of Wt) {
                let r = e.options[n]?.preload;
                r && t.push(r())
            }
            if (t.length)
                return Promise.all(t).then(() => {
                    e._componentsLoaded = !0,
                        e._componentsPromise = void 0
                }
                );
            e._componentsLoaded = !0,
                e._componentsPromise = void 0
        }
            ;
        e._componentsPromise = e._lazyPromise ? e._lazyPromise.then(t) : t()
    }
    return e._componentsPromise
}
function Ht(e, t) {
    return t ? {
        status: `error`,
        error: t
    } : {
        status: `success`,
        value: e
    }
}
function Ut(e) {
    for (let t of Wt)
        if (e.options[t]?.preload)
            return !0;
    return !1
}
var Wt = [`component`, `errorComponent`, `pendingComponent`, `notFoundComponent`];
function Gt(e) {
    return {
        input: ({ url: t }) => {
            for (let n of e)
                t = qt(n, t);
            return t
        }
        ,
        output: ({ url: t }) => {
            for (let n = e.length - 1; n >= 0; n--)
                t = Jt(e[n], t);
            return t
        }
    }
}
function Kt(e) {
    let t = we(e.basepath)
        , n = `/${t}`
        , r = `${n}/`
        , i = e.caseSensitive ? n : n.toLowerCase()
        , a = e.caseSensitive ? r : r.toLowerCase();
    return {
        input: ({ url: t }) => {
            let r = e.caseSensitive ? t.pathname : t.pathname.toLowerCase();
            return r === i ? t.pathname = `/` : r.startsWith(a) && (t.pathname = t.pathname.slice(n.length)),
                t
        }
        ,
        output: ({ url: e }) => (e.pathname = be([`/`, t, e.pathname]),
            e)
    }
}
function qt(e, t) {
    let n = e?.input?.({
        url: t
    });
    if (n) {
        if (typeof n == `string`)
            return new URL(n);
        if (n instanceof URL)
            return n
    }
    return t
}
function Jt(e, t) {
    let n = e?.output?.({
        url: t
    });
    if (n) {
        if (typeof n == `string`)
            return new URL(n);
        if (n instanceof URL)
            return n
    }
    return t
}
function Yt(e) {
    let t = e.resolvedLocation
        , n = e.location
        , r = t?.pathname !== n.pathname
        , i = t?.href !== n.href
        , a = t?.hash !== n.hash;
    return {
        fromLocation: t,
        toLocation: n,
        pathChanged: r,
        hrefChanged: i,
        hashChanged: a
    }
}
var Xt = class {
    constructor(e) {
        this.tempLocationKey = `${Math.round(Math.random() * 1e7)}`,
            this.resetNextScroll = !0,
            this.shouldViewTransition = void 0,
            this.isViewTransitionTypesSupported = void 0,
            this.subscribers = new Set,
            this.isScrollRestoring = !1,
            this.isScrollRestorationSetup = !1,
            this.startTransition = e => e(),
            this.update = e => {
                e.notFoundRoute && console.warn(`The notFoundRoute API is deprecated and will be removed in the next major version. See https://tanstack.com/router/v1/docs/framework/react/guide/not-found-errors#migrating-from-notfoundroute for more info.`);
                let t = this.options
                    , n = this.basepath ?? t?.basepath ?? `/`
                    , r = this.basepath === void 0
                    , i = t?.rewrite;
                this.options = {
                    ...t,
                    ...e
                },
                    this.isServer = this.options.isServer ?? typeof document > `u`,
                    this.pathParamsDecodeCharMap = this.options.pathParamsAllowedCharacters ? new Map(this.options.pathParamsAllowedCharacters.map(e => [encodeURIComponent(e), e])) : void 0,
                    (!this.history || this.options.history && this.options.history !== this.history) && (this.options.history ? this.history = this.options.history : this.isServer || (this.history = A())),
                    this.origin = this.options.origin,
                    this.origin || (!this.isServer && window?.origin && window.origin !== `null` ? this.origin = window.origin : this.origin = `http://localhost`),
                    this.history && this.updateLatestLocation(),
                    this.options.routeTree !== this.routeTree && (this.routeTree = this.options.routeTree,
                        this.buildRouteTree()),
                    !this.__store && this.latestLocation && (this.__store = new te($t(this.latestLocation), {
                        onUpdate: () => {
                            this.__store.state = {
                                ...this.state,
                                cachedMatches: this.state.cachedMatches.filter(e => ![`redirected`].includes(e.status))
                            }
                        }
                    }),
                        pt(this));
                let a = !1
                    , o = this.options.basepath ?? `/`
                    , s = this.options.rewrite;
                if (r || n !== o || i !== s) {
                    this.basepath = o;
                    let e = [];
                    we(o) !== `` && e.push(Kt({
                        basepath: o
                    })),
                        s && e.push(s),
                        this.rewrite = e.length === 0 ? void 0 : e.length === 1 ? e[0] : Gt(e),
                        this.history && this.updateLatestLocation(),
                        a = !0
                }
                a && this.__store && (this.__store.state = {
                    ...this.state,
                    location: this.latestLocation
                }),
                    typeof window < `u` && `CSS` in window && typeof window.CSS?.supports == `function` && (this.isViewTransitionTypesSupported = window.CSS.supports(`selector(:active-view-transition-type(a)`))
            }
            ,
            this.updateLatestLocation = () => {
                this.latestLocation = this.parseLocation(this.history.location, this.latestLocation)
            }
            ,
            this.buildRouteTree = () => {
                let { routesById: e, routesByPath: t, flatRoutes: n } = nt({
                    routeTree: this.routeTree,
                    initRoute: (e, t) => {
                        e.init({
                            originalIndex: t
                        })
                    }
                });
                this.routesById = e,
                    this.routesByPath = t,
                    this.flatRoutes = n;
                let r = this.options.notFoundRoute;
                r && (r.init({
                    originalIndex: 99999999999
                }),
                    this.routesById[r.id] = r)
            }
            ,
            this.subscribe = (e, t) => {
                let n = {
                    eventType: e,
                    fn: t
                };
                return this.subscribers.add(n),
                    () => {
                        this.subscribers.delete(n)
                    }
            }
            ,
            this.emit = e => {
                this.subscribers.forEach(t => {
                    t.eventType === e.type && t.fn(e)
                }
                )
            }
            ,
            this.parseLocation = (e, t) => {
                let n = ({ href: e, state: n }) => {
                    let r = new URL(e, this.origin)
                        , i = qt(this.rewrite, r)
                        , a = this.options.parseSearch(i.search)
                        , o = this.options.stringifySearch(a);
                    i.search = o;
                    let s = i.href.replace(i.origin, ``)
                        , { pathname: c, hash: l } = i;
                    return {
                        href: s,
                        publicHref: e,
                        url: i.href,
                        pathname: c,
                        searchStr: o,
                        search: P(t?.search, a),
                        hash: l.split(`#`).reverse()[0] ?? ``,
                        state: P(t?.state, n)
                    }
                }
                    , r = n(e)
                    , { __tempLocation: i, __tempKey: a } = r.state;
                if (i && (!a || a === this.tempLocationKey)) {
                    let e = n(i);
                    return e.state.key = r.state.key,
                        e.state.__TSR_key = r.state.__TSR_key,
                        delete e.state.__tempLocation,
                    {
                        ...e,
                        maskedLocation: r
                    }
                }
                return r
            }
            ,
            this.resolvePathWithBase = (e, t) => Oe({
                base: e,
                to: xe(t),
                trailingSlash: this.options.trailingSlash,
                parseCache: this.parsePathnameCache
            }),
            this.matchRoutes = (e, t, n) => typeof e == `string` ? this.matchRoutesInternal({
                pathname: e,
                search: t
            }, n) : this.matchRoutesInternal(e, t),
            this.parsePathnameCache = wt(1e3),
            this.getMatchedRoutes = (e, t) => tn({
                pathname: e,
                routePathname: t,
                caseSensitive: this.options.caseSensitive,
                routesByPath: this.routesByPath,
                routesById: this.routesById,
                flatRoutes: this.flatRoutes,
                parseCache: this.parsePathnameCache
            }),
            this.cancelMatch = e => {
                let t = this.getMatch(e);
                t && (t.abortController.abort(),
                    clearTimeout(t._nonReactive.pendingTimeout),
                    t._nonReactive.pendingTimeout = void 0)
            }
            ,
            this.cancelMatches = () => {
                this.state.pendingMatches?.forEach(e => {
                    this.cancelMatch(e.id)
                }
                )
            }
            ,
            this.buildLocation = e => {
                let t = (t = {}) => {
                    let n = t._fromLocation || this.latestLocation
                        , r = this.matchRoutes(n, {
                            _buildLocation: !0
                        })
                        , i = le(r);
                    t.from;
                    let a = t.unsafeRelative === `path` ? n.pathname : t.from ?? i.fullPath
                        , o = this.resolvePathWithBase(a, `.`)
                        , s = i.search
                        , c = {
                            ...i.params
                        }
                        , l = t.to ? this.resolvePathWithBase(o, `${t.to}`) : this.resolvePathWithBase(o, `.`)
                        , u = t.params === !1 || t.params === null ? {} : (t.params ?? !0) === !0 ? c : Object.assign(c, M(t.params, c))
                        , d = Re({
                            path: l,
                            params: u,
                            parseCache: this.parsePathnameCache
                        }).interpolatedPath
                        , f = this.matchRoutes(d, void 0, {
                            _buildLocation: !0
                        }).map(e => this.looseRoutesById[e.routeId]);
                    if (Object.keys(u).length > 0)
                        for (let e of f) {
                            let t = e.options.params?.stringify ?? e.options.stringifyParams;
                            t && Object.assign(u, t(u))
                        }
                    let p = Re({
                        path: l,
                        params: u,
                        leaveWildcards: !1,
                        leaveParams: e.leaveParams,
                        decodeCharMap: this.pathParamsDecodeCharMap,
                        parseCache: this.parsePathnameCache
                    }).interpolatedPath
                        , m = s;
                    if (e._includeValidateSearch && this.options.search?.strict) {
                        let e = {};
                        f.forEach(t => {
                            if (t.options.validateSearch)
                                try {
                                    Object.assign(e, en(t.options.validateSearch, {
                                        ...e,
                                        ...m
                                    }))
                                } catch { }
                        }
                        ),
                            m = e
                    }
                    m = nn({
                        search: m,
                        dest: t,
                        destRoutes: f,
                        _includeValidateSearch: e._includeValidateSearch
                    }),
                        m = P(s, m);
                    let h = this.options.stringifySearch(m)
                        , g = t.hash === !0 ? n.hash : t.hash ? M(t.hash, n.hash) : void 0
                        , _ = g ? `#${g}` : ``
                        , v = t.state === !0 ? n.state : t.state ? M(t.state, n.state) : {};
                    v = P(n.state, v);
                    let y = `${p}${h}${_}`
                        , b = new URL(y, this.origin)
                        , x = Jt(this.rewrite, b);
                    return {
                        publicHref: x.pathname + x.search + x.hash,
                        href: y,
                        url: x.href,
                        pathname: p,
                        search: m,
                        searchStr: h,
                        state: v,
                        hash: g ?? ``,
                        unmaskOnReload: t.unmaskOnReload
                    }
                }
                    , n = (n = {}, r) => {
                        let i = t(n)
                            , a = r ? t(r) : void 0;
                        if (!a) {
                            let n = {}
                                , o = this.options.routeMasks?.find(e => {
                                    let t = Be(i.pathname, {
                                        to: e.from,
                                        caseSensitive: !1,
                                        fuzzy: !1
                                    }, this.parsePathnameCache);
                                    return t ? (n = t,
                                        !0) : !1
                                }
                                );
                            if (o) {
                                let { from: i, ...s } = o;
                                r = {
                                    from: e.from,
                                    ...s,
                                    params: n
                                },
                                    a = t(r)
                            }
                        }
                        return a && (i.maskedLocation = a),
                            i
                    }
                    ;
                return e.mask ? n(e, {
                    from: e.from,
                    ...e.mask
                }) : n(e)
            }
            ,
            this.commitLocation = ({ viewTransition: e, ignoreBlocker: t, ...n }) => {
                let r = () => {
                    let e = [`key`, `__TSR_key`, `__TSR_index`, `__hashScrollIntoViewOptions`];
                    e.forEach(e => {
                        n.state[e] = this.latestLocation.state[e]
                    }
                    );
                    let t = pe(n.state, this.latestLocation.state);
                    return e.forEach(e => {
                        delete n.state[e]
                    }
                    ),
                        t
                }
                    , i = Ce(this.latestLocation.href) === Ce(n.href)
                    , a = this.commitLocationPromise;
                if (this.commitLocationPromise = me(() => {
                    a?.resolve()
                }
                ),
                    i && r())
                    this.load();
                else {
                    let { maskedLocation: r, hashScrollIntoView: i, ...a } = n;
                    r && (a = {
                        ...r,
                        state: {
                            ...r.state,
                            __tempKey: void 0,
                            __tempLocation: {
                                ...a,
                                search: a.searchStr,
                                state: {
                                    ...a.state,
                                    __tempKey: void 0,
                                    __tempLocation: void 0,
                                    __TSR_key: void 0,
                                    key: void 0
                                }
                            }
                        }
                    },
                        (a.unmaskOnReload ?? this.options.unmaskOnReload ?? !1) && (a.state.__tempKey = this.tempLocationKey)),
                        a.state.__hashScrollIntoViewOptions = i ?? this.options.defaultHashScrollIntoView ?? !0,
                        this.shouldViewTransition = e,
                        this.history[n.replace ? `replace` : `push`](a.publicHref, a.state, {
                            ignoreBlocker: t
                        })
                }
                return this.resetNextScroll = n.resetScroll ?? !0,
                    this.history.subscribers.size || this.load(),
                    this.commitLocationPromise
            }
            ,
            this.buildAndCommitLocation = ({ replace: e, resetScroll: t, hashScrollIntoView: n, viewTransition: r, ignoreBlocker: i, href: a, ...o } = {}) => {
                if (a) {
                    let t = this.history.location.state.__TSR_index
                        , n = se(a, {
                            __TSR_index: e ? t : t + 1
                        });
                    o.to = n.pathname,
                        o.search = this.options.parseSearch(n.search),
                        o.hash = n.hash.slice(1)
                }
                let s = this.buildLocation({
                    ...o,
                    _includeValidateSearch: !0
                });
                return this.commitLocation({
                    ...s,
                    viewTransition: r,
                    replace: e,
                    resetScroll: t,
                    hashScrollIntoView: n,
                    ignoreBlocker: i
                })
            }
            ,
            this.navigate = ({ to: e, reloadDocument: t, href: n, ...r }) => {
                if (!t && n)
                    try {
                        new URL(`${n}`),
                            t = !0
                    } catch { }
                return t ? (n ||= this.buildLocation({
                    to: e,
                    ...r
                }).url,
                    r.replace ? window.location.replace(n) : window.location.href = n,
                    Promise.resolve()) : this.buildAndCommitLocation({
                        ...r,
                        href: n,
                        to: e,
                        _isNavigate: !0
                    })
            }
            ,
            this.beforeLoad = () => {
                if (this.cancelMatches(),
                    this.updateLatestLocation(),
                    this.isServer) {
                    let e = this.buildLocation({
                        to: this.latestLocation.pathname,
                        search: !0,
                        params: !0,
                        hash: !0,
                        state: !0,
                        _includeValidateSearch: !0
                    })
                        , t = e => {
                            try {
                                return encodeURI(decodeURI(e))
                            } catch {
                                return e
                            }
                        }
                        ;
                    if (we(t(this.latestLocation.href)) !== we(t(e.href))) {
                        let t = e.url;
                        throw this.origin && t.startsWith(this.origin) && (t = t.replace(this.origin, ``) || `/`),
                        St({
                            href: t
                        })
                    }
                }
                let e = this.matchRoutes(this.latestLocation);
                this.__store.setState(t => ({
                    ...t,
                    status: `pending`,
                    statusCode: 200,
                    isLoading: !0,
                    location: this.latestLocation,
                    pendingMatches: e,
                    cachedMatches: t.cachedMatches.filter(t => !e.some(e => e.id === t.id))
                }))
            }
            ,
            this.load = async e => {
                let t, n, r;
                for (r = new Promise(i => {
                    this.startTransition(async () => {
                        try {
                            this.beforeLoad();
                            let t = this.latestLocation
                                , n = this.state.resolvedLocation;
                            this.state.redirect || this.emit({
                                type: `onBeforeNavigate`,
                                ...Yt({
                                    resolvedLocation: n,
                                    location: t
                                })
                            }),
                                this.emit({
                                    type: `onBeforeLoad`,
                                    ...Yt({
                                        resolvedLocation: n,
                                        location: t
                                    })
                                }),
                                await Bt({
                                    router: this,
                                    sync: e?.sync,
                                    matches: this.state.pendingMatches,
                                    location: t,
                                    updateMatch: this.updateMatch,
                                    onReady: async () => {
                                        this.startViewTransition(async () => {
                                            let e, t, n;
                                            O(() => {
                                                this.__store.setState(r => {
                                                    let i = r.matches
                                                        , a = r.pendingMatches || r.matches;
                                                    return e = i.filter(e => !a.some(t => t.id === e.id)),
                                                        t = a.filter(e => !i.some(t => t.id === e.id)),
                                                        n = i.filter(e => a.some(t => t.id === e.id)),
                                                    {
                                                        ...r,
                                                        isLoading: !1,
                                                        loadedAt: Date.now(),
                                                        matches: a,
                                                        pendingMatches: void 0,
                                                        cachedMatches: [...r.cachedMatches, ...e.filter(e => e.status !== `error`)]
                                                    }
                                                }
                                                ),
                                                    this.clearExpiredCache()
                                            }
                                            ),
                                                [[e, `onLeave`], [t, `onEnter`], [n, `onStay`]].forEach(([e, t]) => {
                                                    e.forEach(e => {
                                                        this.looseRoutesById[e.routeId].options[t]?.(e)
                                                    }
                                                    )
                                                }
                                                )
                                        }
                                        )
                                    }
                                })
                        } catch (e) {
                            Ct(e) ? (t = e,
                                this.isServer || this.navigate({
                                    ...t.options,
                                    replace: !0,
                                    ignoreBlocker: !0
                                })) : rt(e) && (n = e),
                                this.__store.setState(e => ({
                                    ...e,
                                    statusCode: t ? t.status : n ? 404 : e.matches.some(e => e.status === `error`) ? 500 : 200,
                                    redirect: t
                                }))
                        }
                        this.latestLoadPromise === r && (this.commitLocationPromise?.resolve(),
                            this.latestLoadPromise = void 0,
                            this.commitLocationPromise = void 0),
                            i()
                    }
                    )
                }
                ),
                    this.latestLoadPromise = r,
                    await r; this.latestLoadPromise && r !== this.latestLoadPromise;)
                    await this.latestLoadPromise;
                this.hasNotFoundMatch() && this.__store.setState(e => ({
                    ...e,
                    statusCode: 404
                }))
            }
            ,
            this.startViewTransition = e => {
                let t = this.shouldViewTransition ?? this.options.defaultViewTransition;
                if (delete this.shouldViewTransition,
                    t && typeof document < `u` && `startViewTransition` in document && typeof document.startViewTransition == `function`) {
                    let n;
                    if (typeof t == `object` && this.isViewTransitionTypesSupported) {
                        let r = this.latestLocation
                            , i = this.state.resolvedLocation
                            , a = typeof t.types == `function` ? t.types(Yt({
                                resolvedLocation: i,
                                location: r
                            })) : t.types;
                        if (a === !1) {
                            e();
                            return
                        }
                        n = {
                            update: e,
                            types: a
                        }
                    } else
                        n = e;
                    document.startViewTransition(n)
                } else
                    e()
            }
            ,
            this.updateMatch = (e, t) => {
                let n = this.state.pendingMatches?.some(t => t.id === e) ? `pendingMatches` : this.state.matches.some(t => t.id === e) ? `matches` : this.state.cachedMatches.some(t => t.id === e) ? `cachedMatches` : ``;
                n && this.__store.setState(r => ({
                    ...r,
                    [n]: r[n]?.map(n => n.id === e ? t(n) : n)
                }))
            }
            ,
            this.getMatch = e => {
                let t = t => t.id === e;
                return this.state.cachedMatches.find(t) ?? this.state.pendingMatches?.find(t) ?? this.state.matches.find(t)
            }
            ,
            this.invalidate = e => {
                let t = t => e?.filter?.(t) ?? !0 ? {
                    ...t,
                    invalid: !0,
                    ...e?.forcePending || t.status === `error` ? {
                        status: `pending`,
                        error: void 0
                    } : void 0
                } : t;
                return this.__store.setState(e => ({
                    ...e,
                    matches: e.matches.map(t),
                    cachedMatches: e.cachedMatches.map(t),
                    pendingMatches: e.pendingMatches?.map(t)
                })),
                    this.shouldViewTransition = !1,
                    this.load({
                        sync: e?.sync
                    })
            }
            ,
            this.resolveRedirect = e => {
                if (!e.options.href) {
                    let t = this.buildLocation(e.options)
                        , n = t.url;
                    this.origin && n.startsWith(this.origin) && (n = n.replace(this.origin, ``) || `/`),
                        e.options.href = t.href,
                        e.headers.set(`Location`, n)
                }
                return e.headers.get(`Location`) || e.headers.set(`Location`, e.options.href),
                    e
            }
            ,
            this.clearCache = e => {
                let t = e?.filter;
                t === void 0 ? this.__store.setState(e => ({
                    ...e,
                    cachedMatches: []
                })) : this.__store.setState(e => ({
                    ...e,
                    cachedMatches: e.cachedMatches.filter(e => !t(e))
                }))
            }
            ,
            this.clearExpiredCache = () => {
                this.clearCache({
                    filter: e => {
                        let t = this.looseRoutesById[e.routeId];
                        if (!t.options.loader)
                            return !0;
                        let n = (e.preload ? t.options.preloadGcTime ?? this.options.defaultPreloadGcTime : t.options.gcTime ?? this.options.defaultGcTime) ?? 300 * 1e3;
                        return e.status === `error` ? !0 : Date.now() - e.updatedAt >= n
                    }
                })
            }
            ,
            this.loadRouteChunk = Vt,
            this.preloadRoute = async e => {
                let t = this.buildLocation(e)
                    , n = this.matchRoutes(t, {
                        throwOnError: !0,
                        preload: !0,
                        dest: e
                    })
                    , r = new Set([...this.state.matches, ...this.state.pendingMatches ?? []].map(e => e.id))
                    , i = new Set([...r, ...this.state.cachedMatches.map(e => e.id)]);
                O(() => {
                    n.forEach(e => {
                        i.has(e.id) || this.__store.setState(t => ({
                            ...t,
                            cachedMatches: [...t.cachedMatches, e]
                        }))
                    }
                    )
                }
                );
                try {
                    return n = await Bt({
                        router: this,
                        matches: n,
                        location: t,
                        preload: !0,
                        updateMatch: (e, t) => {
                            r.has(e) ? n = n.map(n => n.id === e ? t(n) : n) : this.updateMatch(e, t)
                        }
                    }),
                        n
                } catch (e) {
                    if (Ct(e))
                        return e.options.reloadDocument ? void 0 : await this.preloadRoute({
                            ...e.options,
                            _fromLocation: t
                        });
                    rt(e) || console.error(e);
                    return
                }
            }
            ,
            this.matchRoute = (e, t) => {
                let n = {
                    ...e,
                    to: e.to ? this.resolvePathWithBase(e.from || ``, e.to) : void 0,
                    params: e.params || {},
                    leaveParams: !0
                }
                    , r = this.buildLocation(n);
                if (t?.pending && this.state.status !== `pending`)
                    return !1;
                let i = (t?.pending === void 0 ? !this.state.isLoading : t.pending) ? this.latestLocation : this.state.resolvedLocation || this.state.location
                    , a = Be(i.pathname, {
                        ...t,
                        to: r.pathname
                    }, this.parsePathnameCache);
                return !a || e.params && !pe(a, e.params, {
                    partial: !0
                }) ? !1 : a && (t?.includeSearch ?? !0) ? pe(i.search, r.search, {
                    partial: !0
                }) ? a : !1 : a
            }
            ,
            this.hasNotFoundMatch = () => this.__store.state.matches.some(e => e.status === `notFound` || e.globalNotFound),
            this.update({
                defaultPreloadDelay: 50,
                defaultPendingMs: 1e3,
                defaultPendingMinMs: 500,
                context: void 0,
                ...e,
                caseSensitive: e.caseSensitive ?? !1,
                notFoundMode: e.notFoundMode ?? `fuzzy`,
                stringifySearch: e.stringifySearch ?? yt,
                parseSearch: e.parseSearch ?? vt
            }),
            typeof document < `u` && (self.__TSR_ROUTER__ = this)
    }
    isShell() {
        return !!this.options.isShell
    }
    isPrerendering() {
        return !!this.options.isPrerendering
    }
    get state() {
        return this.__store.state
    }
    get looseRoutesById() {
        return this.routesById
    }
    matchRoutesInternal(e, t) {
        let { foundRoute: n, matchedRoutes: r, routeParams: i } = this.getMatchedRoutes(e.pathname, t?.dest?.to)
            , a = !1;
        (n ? n.path !== `/` && i[`**`] : Ce(e.pathname)) && (this.options.notFoundRoute ? r.push(this.options.notFoundRoute) : a = !0);
        let o = (() => {
            if (a) {
                if (this.options.notFoundMode !== `root`)
                    for (let e = r.length - 1; e >= 0; e--) {
                        let t = r[e];
                        if (t.children)
                            return t.id
                    }
                return ye
            }
        }
        )()
            , s = []
            , c = e => e?.id ? e.context ?? this.options.context ?? void 0 : this.options.context ?? void 0;
        return r.forEach((n, r) => {
            let a = s[r - 1], [l, u, d] = (() => {
                let r = a?.search ?? e.search
                    , i = a?._strictSearch ?? void 0;
                try {
                    let e = en(n.options.validateSearch, {
                        ...r
                    }) ?? void 0;
                    return [{
                        ...r,
                        ...e
                    }, {
                        ...i,
                        ...e
                    }, void 0]
                } catch (e) {
                    let n = e;
                    if (e instanceof Zt || (n = new Zt(e.message, {
                        cause: e
                    })),
                        t?.throwOnError)
                        throw n;
                    return [r, {}, n]
                }
            }
            )(), f = n.options.loaderDeps?.({
                search: l
            }) ?? ``, p = f ? JSON.stringify(f) : ``, { interpolatedPath: m, usedParams: h } = Re({
                path: n.fullPath,
                params: i,
                decodeCharMap: this.pathParamsDecodeCharMap
            }), g = Re({
                path: n.id,
                params: i,
                leaveWildcards: !0,
                decodeCharMap: this.pathParamsDecodeCharMap,
                parseCache: this.parsePathnameCache
            }).interpolatedPath + p, _ = this.getMatch(g), v = this.state.matches.find(e => e.routeId === n.id), y = _?._strictParams ?? h, b;
            if (!_) {
                let e = n.options.params?.parse ?? n.options.parseParams;
                if (e)
                    try {
                        Object.assign(y, e(y))
                    } catch (e) {
                        if (b = new Qt(e.message, {
                            cause: e
                        }),
                            t?.throwOnError)
                            throw b
                    }
            }
            Object.assign(i, y);
            let x = v ? `stay` : `enter`, S;
            if (_)
                S = {
                    ..._,
                    cause: x,
                    params: v ? P(v.params, i) : i,
                    _strictParams: y,
                    search: P(v ? v.search : _.search, l),
                    _strictSearch: u
                };
            else {
                let e = n.options.loader || n.options.beforeLoad || n.lazyFn || Ut(n) ? `pending` : `success`;
                S = {
                    id: g,
                    index: r,
                    routeId: n.id,
                    params: v ? P(v.params, i) : i,
                    _strictParams: y,
                    pathname: m,
                    updatedAt: Date.now(),
                    search: v ? P(v.search, l) : l,
                    _strictSearch: u,
                    searchError: void 0,
                    status: e,
                    isFetching: !1,
                    error: void 0,
                    paramsError: b,
                    __routeContext: void 0,
                    _nonReactive: {
                        loadPromise: me()
                    },
                    __beforeLoadContext: void 0,
                    context: {},
                    abortController: new AbortController,
                    fetchCount: 0,
                    cause: x,
                    loaderDeps: v ? P(v.loaderDeps, f) : f,
                    invalid: !1,
                    preload: !1,
                    links: void 0,
                    scripts: void 0,
                    headScripts: void 0,
                    meta: void 0,
                    staticData: n.options.staticData || {},
                    fullPath: n.fullPath
                }
            }
            t?.preload || (S.globalNotFound = o === n.id),
                S.searchError = d,
                S.context = {
                    ...c(a),
                    ...S.__routeContext,
                    ...S.__beforeLoadContext
                },
                s.push(S)
        }
        ),
            s.forEach((n, r) => {
                let i = this.looseRoutesById[n.routeId];
                if (!this.getMatch(n.id) && t?._buildLocation !== !0) {
                    let t = s[r - 1]
                        , a = c(t);
                    if (i.options.context) {
                        let t = {
                            deps: n.loaderDeps,
                            params: n.params,
                            context: a ?? {},
                            location: e,
                            navigate: t => this.navigate({
                                ...t,
                                _fromLocation: e
                            }),
                            buildLocation: this.buildLocation,
                            cause: n.cause,
                            abortController: n.abortController,
                            preload: !!n.preload,
                            matches: s
                        };
                        n.__routeContext = i.options.context(t) ?? void 0
                    }
                    n.context = {
                        ...a,
                        ...n.__routeContext,
                        ...n.__beforeLoadContext
                    }
                }
            }
            ),
            s
    }
}
    , Zt = class extends Error {
    }
    , Qt = class extends Error {
    }
    ;
function $t(e) {
    return {
        loadedAt: 0,
        isLoading: !1,
        isTransitioning: !1,
        status: `idle`,
        resolvedLocation: void 0,
        location: e,
        matches: [],
        pendingMatches: [],
        cachedMatches: [],
        statusCode: 200
    }
}
function en(e, t) {
    if (e == null)
        return {};
    if (`~standard` in e) {
        let n = e[`~standard`].validate(t);
        if (n instanceof Promise)
            throw new Zt(`Async validation not supported`);
        if (n.issues)
            throw new Zt(JSON.stringify(n.issues, void 0, 2), {
                cause: n
            });
        return n.value
    }
    return `parse` in e ? e.parse(t) : typeof e == `function` ? e(t) : {}
}
function tn({ pathname: e, routePathname: t, caseSensitive: n, routesByPath: r, routesById: i, flatRoutes: a, parseCache: o }) {
    let s = {}
        , c = Ce(e)
        , l = e => Be(c, {
            to: e.fullPath,
            caseSensitive: e.options?.caseSensitive ?? n,
            fuzzy: !0
        }, o)
        , u = t === void 0 ? void 0 : r[t];
    if (u)
        s = l(u);
    else {
        let e;
        for (let t of a) {
            let n = l(t);
            if (n)
                if (t.path !== `/` && n[`**`])
                    e ||= {
                        foundRoute: t,
                        routeParams: n
                    };
                else {
                    u = t,
                        s = n;
                    break
                }
        }
        !u && e && (u = e.foundRoute,
            s = e.routeParams)
    }
    let d = u || i.__root__
        , f = [d];
    for (; d.parentRoute;)
        d = d.parentRoute,
            f.push(d);
    return f.reverse(),
    {
        matchedRoutes: f,
        routeParams: s,
        foundRoute: u
    }
}
function nn({ search: e, dest: t, destRoutes: n, _includeValidateSearch: r }) {
    let i = n.reduce((e, t) => {
        let n = [];
        return `search` in t.options ? t.options.search?.middlewares && n.push(...t.options.search.middlewares) : (t.options.preSearchFilters || t.options.postSearchFilters) && n.push(({ search: e, next: n }) => {
            let r = e;
            `preSearchFilters` in t.options && t.options.preSearchFilters && (r = t.options.preSearchFilters.reduce((e, t) => t(e), e));
            let i = n(r);
            return `postSearchFilters` in t.options && t.options.postSearchFilters ? t.options.postSearchFilters.reduce((e, t) => t(e), i) : i
        }
        ),
            r && t.options.validateSearch && n.push(({ search: e, next: n }) => {
                let r = n(e);
                try {
                    return {
                        ...r,
                        ...en(t.options.validateSearch, r) ?? void 0
                    }
                } catch {
                    return r
                }
            }
            ),
            e.concat(n)
    }
        , []) ?? [];
    i.push(({ search: e }) => t.search ? t.search === !0 ? e : M(t.search, e) : {});
    let a = (e, t) => {
        if (e >= i.length)
            return t;
        let n = i[e];
        return n({
            search: t,
            next: t => a(e + 1, t)
        })
    }
        ;
    return a(0, e)
}
var rn = `Error preloading route! ☝️`
    , an = class {
        constructor(e) {
            if (this.init = e => {
                this.originalIndex = e.originalIndex;
                let t = this.options
                    , n = !t?.path && !t?.id;
                this.parentRoute = this.options.getParentRoute?.(),
                    n ? this._path = ye : this.parentRoute || ve(!1, `Child Route instances must pass a 'getParentRoute: () => ParentRoute' option that returns a Route instance.`);
                let r = n ? ye : t?.path;
                r && r !== `/` && (r = Se(r));
                let i = t?.id || r
                    , a = n ? ye : be([this.parentRoute.id === `__root__` ? `` : this.parentRoute.id, i]);
                r === `__root__` && (r = `/`),
                    a !== `__root__` && (a = be([`/`, a]));
                let o = a === `__root__` ? `/` : be([this.parentRoute.fullPath, r]);
                this._path = r,
                    this._id = a,
                    this._fullPath = o,
                    this._to = o
            }
                ,
                this.clone = e => {
                    this._path = e._path,
                        this._id = e._id,
                        this._fullPath = e._fullPath,
                        this._to = e._to,
                        this.options.getParentRoute = e.options.getParentRoute,
                        this.children = e.children
                }
                ,
                this.addChildren = e => this._addFileChildren(e),
                this._addFileChildren = e => (Array.isArray(e) && (this.children = e),
                    typeof e == `object` && e && (this.children = Object.values(e)),
                    this),
                this._addFileTypes = () => this,
                this.updateLoader = e => (Object.assign(this.options, e),
                    this),
                this.update = e => (Object.assign(this.options, e),
                    this),
                this.lazy = e => (this.lazyFn = e,
                    this),
                this.options = e || {},
                this.isRoot = !e?.getParentRoute,
                e?.id && e?.path)
                throw Error(`Route cannot have both an 'id' and a 'path' option.`)
        }
        get to() {
            return this._to
        }
        get id() {
            return this._id
        }
        get path() {
            return this._path
        }
        get fullPath() {
            return this._fullPath
        }
    }
    , on = class extends an {
        constructor(e) {
            super(e)
        }
    }
    , sn = o((e => {
        var t = Symbol.for(`react.transitional.element`)
            , n = Symbol.for(`react.fragment`);
        function r(e, n, r) {
            var i = null;
            if (r !== void 0 && (i = `` + r),
                n.key !== void 0 && (i = `` + n.key),
                `key` in n)
                for (var a in r = {},
                    n)
                    a !== `key` && (r[a] = n[a]);
            else
                r = n;
            return n = r.ref,
            {
                $$typeof: t,
                type: e,
                key: i,
                ref: n === void 0 ? null : n,
                props: r
            }
        }
        e.Fragment = n,
            e.jsx = r,
            e.jsxs = r
    }
    ))
    , I = c(o(((e, t) => {
        t.exports = sn()
    }
    ))())
    , L = c(f());
function cn(e) {
    let t = e.errorComponent ?? un;
    return (0,
        I.jsx)(ln, {
            getResetKey: e.getResetKey,
            onCatch: e.onCatch,
            children: ({ error: n, reset: r }) => n ? L.createElement(t, {
                error: n,
                reset: r
            }) : e.children
        })
}
var ln = class extends L.Component {
    constructor() {
        super(...arguments),
            this.state = {
                error: null
            }
    }
    static getDerivedStateFromProps(e) {
        return {
            resetKey: e.getResetKey()
        }
    }
    static getDerivedStateFromError(e) {
        return {
            error: e
        }
    }
    reset() {
        this.setState({
            error: null
        })
    }
    componentDidUpdate(e, t) {
        t.error && t.resetKey !== this.state.resetKey && this.reset()
    }
    componentDidCatch(e, t) {
        this.props.onCatch && this.props.onCatch(e, t)
    }
    render() {
        return this.props.children({
            error: this.state.resetKey === this.props.getResetKey() ? this.state.error : null,
            reset: () => {
                this.reset()
            }
        })
    }
}
    ;
function un({ error: e }) {
    let [t, n] = L.useState(!1);
    return (0,
        I.jsxs)(`div`, {
            style: {
                padding: `.5rem`,
                maxWidth: `100%`
            },
            children: [(0,
                I.jsxs)(`div`, {
                    style: {
                        display: `flex`,
                        alignItems: `center`,
                        gap: `.5rem`
                    },
                    children: [(0,
                        I.jsx)(`strong`, {
                            style: {
                                fontSize: `1rem`
                            },
                            children: `Something went wrong!`
                        }), (0,
                            I.jsx)(`button`, {
                                style: {
                                    appearance: `none`,
                                    fontSize: `.6em`,
                                    border: `1px solid currentColor`,
                                    padding: `.1rem .2rem`,
                                    fontWeight: `bold`,
                                    borderRadius: `.25rem`
                                },
                                onClick: () => n(e => !e),
                                children: t ? `Hide Error` : `Show Error`
                            })]
                }), (0,
                    I.jsx)(`div`, {
                        style: {
                            height: `.25rem`
                        }
                    }), t ? (0,
                        I.jsx)(`div`, {
                            children: (0,
                                I.jsx)(`pre`, {
                                    style: {
                                        fontSize: `.7em`,
                                        border: `1px solid red`,
                                        borderRadius: `.25rem`,
                                        padding: `.3rem`,
                                        color: `red`,
                                        overflow: `auto`
                                    },
                                    children: e.message ? (0,
                                        I.jsx)(`code`, {
                                            children: e.message
                                        }) : null
                                })
                        }) : null]
        })
}
function dn({ children: e, fallback: t = null }) {
    return fn() ? (0,
        I.jsx)(L.Fragment, {
            children: e
        }) : (0,
            I.jsx)(L.Fragment, {
                children: t
            })
}
function fn() {
    return L.useSyncExternalStore(pn, () => !0, () => !1)
}
function pn() {
    return () => { }
}
var mn = !0;
function hn(e, t) {
    if (!mn) {
        if (e)
            return;
        var n = `Warning: ` + t;
        typeof console < `u` && console.warn(n);
        try {
            throw Error(n)
        } catch { }
    }
}
var gn = hn
    , _n = o((e => {
        var t = f();
        function n(e, t) {
            return e === t && (e !== 0 || 1 / e == 1 / t) || e !== e && t !== t
        }
        var r = typeof Object.is == `function` ? Object.is : n
            , i = t.useState
            , a = t.useEffect
            , o = t.useLayoutEffect
            , s = t.useDebugValue;
        function c(e, t) {
            var n = t()
                , r = i({
                    inst: {
                        value: n,
                        getSnapshot: t
                    }
                })
                , c = r[0].inst
                , u = r[1];
            return o(function () {
                c.value = n,
                    c.getSnapshot = t,
                    l(c) && u({
                        inst: c
                    })
            }, [e, n, t]),
                a(function () {
                    return l(c) && u({
                        inst: c
                    }),
                        e(function () {
                            l(c) && u({
                                inst: c
                            })
                        })
                }, [e]),
                s(n),
                n
        }
        function l(e) {
            var t = e.getSnapshot;
            e = e.value;
            try {
                var n = t();
                return !r(e, n)
            } catch {
                return !0
            }
        }
        function u(e, t) {
            return t()
        }
        var d = typeof window > `u` || window.document === void 0 || window.document.createElement === void 0 ? u : c;
        e.useSyncExternalStore = t.useSyncExternalStore === void 0 ? d : t.useSyncExternalStore
    }
    ))
    , vn = o(((e, t) => {
        t.exports = _n()
    }
    ))
    , yn = o((e => {
        var t = f()
            , n = vn();
        function r(e, t) {
            return e === t && (e !== 0 || 1 / e == 1 / t) || e !== e && t !== t
        }
        var i = typeof Object.is == `function` ? Object.is : r
            , a = n.useSyncExternalStore
            , o = t.useRef
            , s = t.useEffect
            , c = t.useMemo
            , l = t.useDebugValue;
        e.useSyncExternalStoreWithSelector = function (e, t, n, r, u) {
            var d = o(null);
            if (d.current === null) {
                var f = {
                    hasValue: !1,
                    value: null
                };
                d.current = f
            } else
                f = d.current;
            d = c(function () {
                function e(e) {
                    if (!a) {
                        if (a = !0,
                            o = e,
                            e = r(e),
                            u !== void 0 && f.hasValue) {
                            var t = f.value;
                            if (u(t, e))
                                return s = t
                        }
                        return s = e
                    }
                    if (t = s,
                        i(o, e))
                        return t;
                    var n = r(e);
                    return u !== void 0 && u(t, n) ? (o = e,
                        t) : (o = e,
                            s = n)
                }
                var a = !1, o, s, c = n === void 0 ? null : n;
                return [function () {
                    return e(t())
                }
                    , c === null ? void 0 : function () {
                        return e(c())
                    }
                ]
            }, [t, n, r, u]);
            var p = a(e, d[0], d[1]);
            return s(function () {
                f.hasValue = !0,
                    f.value = p
            }, [p]),
                l(p),
                p
        }
    }
    ))
    , bn = c(o(((e, t) => {
        t.exports = yn()
    }
    ))());
function xn(e, t = e => e) {
    return (0,
        bn.useSyncExternalStoreWithSelector)(e.subscribe, () => e.state, () => e.state, t, Sn)
}
function Sn(e, t) {
    if (Object.is(e, t))
        return !0;
    if (typeof e != `object` || !e || typeof t != `object` || !t)
        return !1;
    if (e instanceof Map && t instanceof Map) {
        if (e.size !== t.size)
            return !1;
        for (let [n, r] of e)
            if (!t.has(n) || !Object.is(r, t.get(n)))
                return !1;
        return !0
    }
    if (e instanceof Set && t instanceof Set) {
        if (e.size !== t.size)
            return !1;
        for (let n of e)
            if (!t.has(n))
                return !1;
        return !0
    }
    if (e instanceof Date && t instanceof Date)
        return e.getTime() === t.getTime();
    let n = Cn(e);
    if (n.length !== Cn(t).length)
        return !1;
    for (let r = 0; r < n.length; r++)
        if (!Object.prototype.hasOwnProperty.call(t, n[r]) || !Object.is(e[n[r]], t[n[r]]))
            return !1;
    return !0
}
function Cn(e) {
    return Object.keys(e).concat(Object.getOwnPropertySymbols(e))
}
var wn = L.createContext(null);
function Tn() {
    return typeof document > `u` ? wn : window.__TSR_ROUTER_CONTEXT__ ? window.__TSR_ROUTER_CONTEXT__ : (window.__TSR_ROUTER_CONTEXT__ = wn,
        wn)
}
function En(e) {
    let t = L.useContext(Tn());
    return gn(!((e?.warn ?? !0) && !t), `useRouter must be used inside a <RouterProvider> component!`),
        t
}
function Dn(e) {
    let t = En({
        warn: e?.router === void 0
    })
        , n = e?.router || t
        , r = (0,
            L.useRef)(void 0);
    return xn(n.__store, t => {
        if (e?.select) {
            if (e.structuralSharing ?? n.options.defaultStructuralSharing) {
                let n = P(r.current, e.select(t));
                return r.current = n,
                    n
            }
            return e.select(t)
        }
        return t
    }
    )
}
var On = L.createContext(void 0)
    , kn = L.createContext(void 0);
function An(e) {
    let t = L.useContext(e.from ? kn : On);
    return Dn({
        select: n => {
            let r = n.matches.find(n => e.from ? e.from === n.routeId : n.id === t);
            if (ve(!((e.shouldThrow ?? !0) && !r), `Could not find ${e.from ? `an active match from "${e.from}"` : `a nearest match!`}`),
                r !== void 0)
                return e.select ? e.select(r) : r
        }
        ,
        structuralSharing: e.structuralSharing
    })
}
function jn(e) {
    return An({
        from: e.from,
        strict: e.strict,
        structuralSharing: e.structuralSharing,
        select: t => e.select ? e.select(t.loaderData) : t.loaderData
    })
}
function Mn(e) {
    let { select: t, ...n } = e;
    return An({
        ...n,
        select: e => t ? t(e.loaderDeps) : e.loaderDeps
    })
}
function Nn(e) {
    return An({
        from: e.from,
        shouldThrow: e.shouldThrow,
        structuralSharing: e.structuralSharing,
        strict: e.strict,
        select: t => {
            let n = e.strict === !1 ? t.params : t._strictParams;
            return e.select ? e.select(n) : n
        }
    })
}
function Pn(e) {
    return An({
        from: e.from,
        strict: e.strict,
        shouldThrow: e.shouldThrow,
        structuralSharing: e.structuralSharing,
        select: t => e.select ? e.select(t.search) : t.search
    })
}
function Fn(e) {
    let t = En();
    return L.useCallback(n => t.navigate({
        ...n,
        from: n.from ?? e?.from
    }), [e?.from, t])
}
var In = typeof window < `u` ? L.useLayoutEffect : L.useEffect;
function Ln(e) {
    let t = L.useRef({
        value: e,
        prev: null
    })
        , n = t.current.value;
    return e !== n && (t.current = {
        value: e,
        prev: n
    }),
        t.current.prev
}
function Rn(e, t, n = {}, r = {}) {
    L.useEffect(() => {
        if (!e.current || r.disabled || typeof IntersectionObserver != `function`)
            return;
        let i = new IntersectionObserver(([e]) => {
            t(e)
        }
            , n);
        return i.observe(e.current),
            () => {
                i.disconnect()
            }
    }
        , [t, n, r.disabled, e])
}
function zn(e) {
    let t = L.useRef(null);
    return L.useImperativeHandle(e, () => t.current, []),
        t
}
var Bn = c(m());
function Vn(e, t) {
    let n = En()
        , [r, i] = L.useState(!1)
        , a = L.useRef(!1)
        , o = zn(t)
        , { activeProps: s, inactiveProps: c, activeOptions: l, to: u, preload: d, preloadDelay: f, hashScrollIntoView: p, replace: m, startTransition: h, resetScroll: g, viewTransition: _, children: v, target: y, disabled: b, style: x, className: S, onClick: C, onFocus: w, onMouseEnter: T, onMouseLeave: E, onTouchStart: D, ignoreBlocker: O, params: ee, search: te, hash: ne, state: re, mask: ie, reloadDocument: ae, unsafeRelative: oe, from: k, _fromLocation: A, ...se } = e
        , ce = Dn({
            select: e => e.location.search,
            structuralSharing: !0
        })
        , le = e.from
        , j = L.useMemo(() => ({
            ...e,
            from: le
        }), [n, ce, le, e._fromLocation, e.hash, e.to, e.search, e.params, e.state, e.mask, e.unsafeRelative])
        , N = L.useMemo(() => n.buildLocation({
            ...j
        }), [n, j])
        , P = L.useMemo(() => {
            if (b)
                return;
            let e = N.maskedLocation ? N.maskedLocation.url : N.url
                , t = !1;
            return n.origin && (e.startsWith(n.origin) ? e = n.history.createHref(e.replace(n.origin, ``)) || `/` : t = !0),
            {
                href: e,
                external: t
            }
        }
            , [b, N.maskedLocation, N.url, n.origin, n.history])
        , ue = L.useMemo(() => {
            if (P?.external)
                return P.href;
            try {
                return new URL(u),
                    u
            } catch { }
        }
            , [u, P])
        , F = e.reloadDocument || ue ? !1 : d ?? n.options.defaultPreload
        , de = f ?? n.options.defaultPreloadDelay ?? 0
        , fe = Dn({
            select: e => {
                if (ue)
                    return !1;
                if (l?.exact) {
                    if (!Ee(e.location.pathname, N.pathname, n.basepath))
                        return !1
                } else {
                    let t = Te(e.location.pathname, n.basepath)
                        , r = Te(N.pathname, n.basepath);
                    if (!(t.startsWith(r) && (t.length === r.length || t[r.length] === `/`)))
                        return !1
                }
                return (l?.includeSearch ?? !0) && !pe(e.location.search, N.search, {
                    partial: !l?.exact,
                    ignoreUndefined: !l?.explicitUndefined
                }) ? !1 : l?.includeHash ? e.location.hash === N.hash : !0
            }
        })
        , me = L.useCallback(() => {
            n.preloadRoute({
                ...j
            }).catch(e => {
                console.warn(e),
                    console.warn(rn)
            }
            )
        }
            , [n, j])
        , he = L.useCallback(e => {
            e?.isIntersecting && me()
        }
            , [me]);
    Rn(o, he, Jn, {
        disabled: !!b || F !== `viewport`
    }),
        L.useEffect(() => {
            a.current || !b && F === `render` && (me(),
                a.current = !0)
        }
            , [b, me, F]);
    let ge = e => {
        let t = e.currentTarget.target
            , r = y === void 0 ? t : y;
        if (!b && !Zn(e) && !e.defaultPrevented && (!r || r === `_self`) && e.button === 0) {
            e.preventDefault(),
                (0,
                    Bn.flushSync)(() => {
                        i(!0)
                    }
                    );
            let t = n.subscribe(`onResolved`, () => {
                t(),
                    i(!1)
            }
            );
            n.navigate({
                ...j,
                replace: m,
                resetScroll: g,
                hashScrollIntoView: p,
                startTransition: h,
                viewTransition: _,
                ignoreBlocker: O
            })
        }
    }
        ;
    if (ue)
        return {
            ...se,
            ref: o,
            href: ue,
            ...v && {
                children: v
            },
            ...y && {
                target: y
            },
            ...b && {
                disabled: b
            },
            ...x && {
                style: x
            },
            ...S && {
                className: S
            },
            ...C && {
                onClick: C
            },
            ...w && {
                onFocus: w
            },
            ...T && {
                onMouseEnter: T
            },
            ...E && {
                onMouseLeave: E
            },
            ...D && {
                onTouchStart: D
            }
        };
    let _e = e => {
        b || F && me()
    }
        , ve = _e
        , ye = e => {
            if (!(b || !F))
                if (!de)
                    me();
                else {
                    let t = e.target;
                    if (qn.has(t))
                        return;
                    let n = setTimeout(() => {
                        qn.delete(t),
                            me()
                    }
                        , de);
                    qn.set(t, n)
                }
        }
        , be = e => {
            if (b || !F || !de)
                return;
            let t = e.target
                , n = qn.get(t);
            n && (clearTimeout(n),
                qn.delete(t))
        }
        , xe = fe ? M(s, {}) ?? Un : Hn
        , Se = fe ? Hn : M(c, {}) ?? Hn
        , Ce = [S, xe.className, Se.className].filter(Boolean).join(` `)
        , we = (x || xe.style || Se.style) && {
            ...x,
            ...xe.style,
            ...Se.style
        };
    return {
        ...se,
        ...xe,
        ...Se,
        href: P?.href,
        ref: o,
        onClick: Yn([C, ge]),
        onFocus: Yn([w, _e]),
        onMouseEnter: Yn([T, ye]),
        onMouseLeave: Yn([E, be]),
        onTouchStart: Yn([D, ve]),
        disabled: !!b,
        target: y,
        ...we && {
            style: we
        },
        ...Ce && {
            className: Ce
        },
        ...b && Wn,
        ...fe && Gn,
        ...r && Kn
    }
}
var Hn = {}
    , Un = {
        className: `active`
    }
    , Wn = {
        role: `link`,
        "aria-disabled": !0
    }
    , Gn = {
        "data-status": `active`,
        "aria-current": `page`
    }
    , Kn = {
        "data-transitioning": `transitioning`
    }
    , qn = new WeakMap
    , Jn = {
        rootMargin: `100px`
    }
    , Yn = e => t => {
        for (let n of e)
            if (n) {
                if (t.defaultPrevented)
                    return;
                n(t)
            }
    }
    , Xn = L.forwardRef((e, t) => {
        let { _asChild: n, ...r } = e
            , { type: i, ref: a, ...o } = Vn(r, t)
            , s = typeof r.children == `function` ? r.children({
                isActive: o[`data-status`] === `active`
            }) : r.children;
        return n === void 0 && delete o.disabled,
            L.createElement(n || `a`, {
                ...o,
                ref: a
            }, s)
    }
    );
function Zn(e) {
    return !!(e.metaKey || e.altKey || e.ctrlKey || e.shiftKey)
}
var Qn = class extends an {
    constructor(e) {
        super(e),
            this.useMatch = e => An({
                select: e?.select,
                from: this.id,
                structuralSharing: e?.structuralSharing
            }),
            this.useRouteContext = e => An({
                ...e,
                from: this.id,
                select: t => e?.select ? e.select(t.context) : t.context
            }),
            this.useSearch = e => Pn({
                select: e?.select,
                structuralSharing: e?.structuralSharing,
                from: this.id
            }),
            this.useParams = e => Nn({
                select: e?.select,
                structuralSharing: e?.structuralSharing,
                from: this.id
            }),
            this.useLoaderDeps = e => Mn({
                ...e,
                from: this.id
            }),
            this.useLoaderData = e => jn({
                ...e,
                from: this.id
            }),
            this.useNavigate = () => Fn({
                from: this.fullPath
            }),
            this.Link = L.forwardRef((e, t) => (0,
                I.jsx)(Xn, {
                    ref: t,
                    from: this.fullPath,
                    ...e
                })),
            this.$$typeof = Symbol.for(`react.memo`)
    }
}
    ;
function $n(e) {
    return new Qn(e)
}
var er = class extends on {
    constructor(e) {
        super(e),
            this.useMatch = e => An({
                select: e?.select,
                from: this.id,
                structuralSharing: e?.structuralSharing
            }),
            this.useRouteContext = e => An({
                ...e,
                from: this.id,
                select: t => e?.select ? e.select(t.context) : t.context
            }),
            this.useSearch = e => Pn({
                select: e?.select,
                structuralSharing: e?.structuralSharing,
                from: this.id
            }),
            this.useParams = e => Nn({
                select: e?.select,
                structuralSharing: e?.structuralSharing,
                from: this.id
            }),
            this.useLoaderDeps = e => Mn({
                ...e,
                from: this.id
            }),
            this.useLoaderData = e => jn({
                ...e,
                from: this.id
            }),
            this.useNavigate = () => Fn({
                from: this.fullPath
            }),
            this.Link = L.forwardRef((e, t) => (0,
                I.jsx)(Xn, {
                    ref: t,
                    from: this.fullPath,
                    ...e
                })),
            this.$$typeof = Symbol.for(`react.memo`)
    }
}
    ;
function tr(e) {
    return new er(e)
}
function nr(e) {
    return typeof e == `object` ? new rr(e, {
        silent: !0
    }).createRoute(e) : new rr(e, {
        silent: !0
    }).createRoute
}
var rr = class {
    constructor(e, t) {
        this.path = e,
            this.createRoute = e => {
                gn(this.silent, `FileRoute is deprecated and will be removed in the next major version. Use the createFileRoute(path)(options) function instead.`);
                let t = $n(e);
                return t.isRoot = !1,
                    t
            }
            ,
            this.silent = t?.silent
    }
}
    , ir = class {
        constructor(e) {
            this.useMatch = e => An({
                select: e?.select,
                from: this.options.id,
                structuralSharing: e?.structuralSharing
            }),
                this.useRouteContext = e => An({
                    from: this.options.id,
                    select: t => e?.select ? e.select(t.context) : t.context
                }),
                this.useSearch = e => Pn({
                    select: e?.select,
                    structuralSharing: e?.structuralSharing,
                    from: this.options.id
                }),
                this.useParams = e => Nn({
                    select: e?.select,
                    structuralSharing: e?.structuralSharing,
                    from: this.options.id
                }),
                this.useLoaderDeps = e => Mn({
                    ...e,
                    from: this.options.id
                }),
                this.useLoaderData = e => jn({
                    ...e,
                    from: this.options.id
                }),
                this.useNavigate = () => {
                    let e = En();
                    return Fn({
                        from: e.routesById[this.options.id].fullPath
                    })
                }
                ,
                this.options = e,
                this.$$typeof = Symbol.for(`react.memo`)
        }
    }
    ;
function ar(e) {
    return typeof e == `object` ? new ir(e) : t => new ir({
        id: e,
        ...t
    })
}
function or() {
    let e = En()
        , t = L.useRef({
            router: e,
            mounted: !1
        })
        , [n, r] = L.useState(!1)
        , { hasPendingMatches: i, isLoading: a } = Dn({
            select: e => ({
                isLoading: e.isLoading,
                hasPendingMatches: e.matches.some(e => e.status === `pending`)
            }),
            structuralSharing: !0
        })
        , o = Ln(a)
        , s = a || n || i
        , c = Ln(s)
        , l = a || i
        , u = Ln(l);
    return e.startTransition = e => {
        r(!0),
            L.startTransition(() => {
                e(),
                    r(!1)
            }
            )
    }
        ,
        L.useEffect(() => {
            let t = e.history.subscribe(e.load)
                , n = e.buildLocation({
                    to: e.latestLocation.pathname,
                    search: !0,
                    params: !0,
                    hash: !0,
                    state: !0,
                    _includeValidateSearch: !0
                });
            return Ce(e.latestLocation.href) !== Ce(n.href) && e.commitLocation({
                ...n,
                replace: !0
            }),
                () => {
                    t()
                }
        }
            , [e, e.history]),
        In(() => {
            typeof window < `u` && e.ssr || t.current.router === e && t.current.mounted || (t.current = {
                router: e,
                mounted: !0
            },
                (async () => {
                    try {
                        await e.load()
                    } catch (e) {
                        console.error(e)
                    }
                }
                )())
        }
            , [e]),
        In(() => {
            o && !a && e.emit({
                type: `onLoad`,
                ...Yt(e.state)
            })
        }
            , [o, e, a]),
        In(() => {
            u && !l && e.emit({
                type: `onBeforeRouteMount`,
                ...Yt(e.state)
            })
        }
            , [l, u, e]),
        In(() => {
            c && !s && (e.emit({
                type: `onResolved`,
                ...Yt(e.state)
            }),
                e.__store.setState(e => ({
                    ...e,
                    status: `idle`,
                    resolvedLocation: e.location
                })),
                mt(e))
        }
            , [s, c, e]),
        null
}
function sr(e) {
    let t = Dn({
        select: e => `not-found-${e.location.pathname}-${e.status}`
    });
    return (0,
        I.jsx)(cn, {
            getResetKey: () => t,
            onCatch: (t, n) => {
                if (rt(t))
                    e.onCatch?.(t, n);
                else
                    throw t
            }
            ,
            errorComponent: ({ error: t }) => {
                if (rt(t))
                    return e.fallback?.(t);
                throw t
            }
            ,
            children: e.children
        })
}
function cr() {
    return (0,
        I.jsx)(`p`, {
            children: `Not Found`
        })
}
function lr(e) {
    return (0,
        I.jsx)(I.Fragment, {
            children: e.children
        })
}
function ur(e, t, n) {
    return t.options.notFoundComponent ? (0,
        I.jsx)(t.options.notFoundComponent, {
            data: n
        }) : e.options.defaultNotFoundComponent ? (0,
            I.jsx)(e.options.defaultNotFoundComponent, {
                data: n
            }) : (0,
                I.jsx)(cr, {})
}
function dr({ children: e }) {
    let t = En();
    return t.isServer ? (0,
        I.jsx)(`script`, {
            nonce: t.options.ssr?.nonce,
            className: `$tsr`,
            dangerouslySetInnerHTML: {
                __html: [e].filter(Boolean).join(`
`) + `;$_TSR.c()`
            }
        }) : null
}
function fr() {
    let e = En();
    if (!e.isScrollRestoring || !e.isServer || typeof e.options.scrollRestoration == `function` && !e.options.scrollRestoration({
        location: e.latestLocation
    }))
        return null;
    let t = (e.options.getScrollRestorationKey || lt)(e.latestLocation)
        , n = t === lt(e.latestLocation) ? void 0 : t
        , r = {
            storageKey: at,
            shouldScrollRestoration: !0
        };
    return n && (r.key = n),
        (0,
            I.jsx)(dr, {
                children: `(${ft.toString()})(${JSON.stringify(r)})`
            })
}
var pr = L.memo(function ({ matchId: e }) {
    let t = En()
        , n = Dn({
            select: t => {
                let n = t.matches.find(t => t.id === e);
                return ve(n, `Could not find match for matchId "${e}". Please file an issue!`),
                {
                    routeId: n.routeId,
                    ssr: n.ssr,
                    _displayPending: n._displayPending
                }
            }
            ,
            structuralSharing: !0
        })
        , r = t.routesById[n.routeId]
        , i = r.options.pendingComponent ?? t.options.defaultPendingComponent
        , a = i ? (0,
            I.jsx)(i, {}) : null
        , o = r.options.errorComponent ?? t.options.defaultErrorComponent
        , s = r.options.onCatch ?? t.options.defaultOnCatch
        , c = r.isRoot ? r.options.notFoundComponent ?? t.options.notFoundRoute?.options.component : r.options.notFoundComponent
        , l = n.ssr === !1 || n.ssr === `data-only`
        , u = (!r.isRoot || r.options.wrapInSuspense || l) && (r.options.wrapInSuspense ?? i ?? (r.options.errorComponent?.preload || l)) ? L.Suspense : lr
        , d = o ? cn : lr
        , f = c ? sr : lr
        , p = Dn({
            select: e => e.loadedAt
        })
        , m = Dn({
            select: t => {
                let n = t.matches.findIndex(t => t.id === e);
                return t.matches[n - 1]?.routeId
            }
        })
        , h = r.isRoot ? r.options.shellComponent ?? lr : lr;
    return (0,
        I.jsxs)(h, {
            children: [(0,
                I.jsx)(On.Provider, {
                    value: e,
                    children: (0,
                        I.jsx)(u, {
                            fallback: a,
                            children: (0,
                                I.jsx)(d, {
                                    getResetKey: () => p,
                                    errorComponent: o || un,
                                    onCatch: (t, n) => {
                                        if (rt(t))
                                            throw t;
                                        gn(!1, `Error in route match: ${e}`),
                                            s?.(t, n)
                                    }
                                    ,
                                    children: (0,
                                        I.jsx)(f, {
                                            fallback: e => {
                                                if (!c || e.routeId && e.routeId !== n.routeId || !e.routeId && !r.isRoot)
                                                    throw e;
                                                return L.createElement(c, e)
                                            }
                                            ,
                                            children: l || n._displayPending ? (0,
                                                I.jsx)(dn, {
                                                    fallback: a,
                                                    children: (0,
                                                        I.jsx)(hr, {
                                                            matchId: e
                                                        })
                                                }) : (0,
                                                    I.jsx)(hr, {
                                                        matchId: e
                                                    })
                                        })
                                })
                        })
                }), m === `__root__` && t.options.scrollRestoration ? (0,
                    I.jsxs)(I.Fragment, {
                        children: [(0,
                            I.jsx)(mr, {}), (0,
                                I.jsx)(fr, {})]
                    }) : null]
        })
});
function mr() {
    let e = En()
        , t = L.useRef(void 0);
    return (0,
        I.jsx)(`script`, {
            suppressHydrationWarning: !0,
            ref: n => {
                n && (t.current === void 0 || t.current.href !== e.latestLocation.href) && (e.emit({
                    type: `onRendered`,
                    ...Yt(e.state)
                }),
                    t.current = e.latestLocation)
            }
        }, e.latestLocation.state.__TSR_key)
}
var hr = L.memo(function ({ matchId: e }) {
    let t = En()
        , { match: n, key: r, routeId: i } = Dn({
            select: n => {
                let r = n.matches.find(t => t.id === e)
                    , i = r.routeId
                    , a = (t.routesById[i].options.remountDeps ?? t.options.defaultRemountDeps)?.({
                        routeId: i,
                        loaderDeps: r.loaderDeps,
                        params: r._strictParams,
                        search: r._strictSearch
                    });
                return {
                    key: a ? JSON.stringify(a) : void 0,
                    routeId: i,
                    match: {
                        id: r.id,
                        status: r.status,
                        error: r.error,
                        _forcePending: r._forcePending,
                        _displayPending: r._displayPending
                    }
                }
            }
            ,
            structuralSharing: !0
        })
        , a = t.routesById[i]
        , o = L.useMemo(() => {
            let e = a.options.component ?? t.options.defaultComponent;
            return e ? (0,
                I.jsx)(e, {}, r) : (0,
                    I.jsx)(gr, {})
        }
            , [r, a.options.component, t.options.defaultComponent]);
    if (n._displayPending)
        throw t.getMatch(n.id)?._nonReactive.displayPendingPromise;
    if (n._forcePending)
        throw t.getMatch(n.id)?._nonReactive.minPendingPromise;
    if (n.status === `pending`) {
        let e = a.options.pendingMinMs ?? t.options.defaultPendingMinMs;
        if (e) {
            let r = t.getMatch(n.id);
            if (r && !r._nonReactive.minPendingPromise && !t.isServer) {
                let t = me();
                r._nonReactive.minPendingPromise = t,
                    setTimeout(() => {
                        t.resolve(),
                            r._nonReactive.minPendingPromise = void 0
                    }
                        , e)
            }
        }
        throw t.getMatch(n.id)?._nonReactive.loadPromise
    }
    if (n.status === `notFound`)
        return ve(rt(n.error), `Expected a notFound error`),
            ur(t, a, n.error);
    if (n.status === `redirected`)
        throw ve(Ct(n.error), `Expected a redirect error`),
        t.getMatch(n.id)?._nonReactive.loadPromise;
    if (n.status === `error`) {
        if (t.isServer) {
            let e = (a.options.errorComponent ?? t.options.defaultErrorComponent) || un;
            return (0,
                I.jsx)(e, {
                    error: n.error,
                    reset: void 0,
                    info: {
                        componentStack: ``
                    }
                })
        }
        throw n.error
    }
    return o
})
    , gr = L.memo(function () {
        let e = En()
            , t = L.useContext(On)
            , n = Dn({
                select: e => e.matches.find(e => e.id === t)?.routeId
            })
            , r = e.routesById[n]
            , i = Dn({
                select: e => {
                    let n = e.matches.find(e => e.id === t);
                    return ve(n, `Could not find parent match for matchId "${t}"`),
                        n.globalNotFound
                }
            })
            , a = Dn({
                select: e => {
                    let n = e.matches
                        , r = n.findIndex(e => e.id === t);
                    return n[r + 1]?.id
                }
            })
            , o = e.options.defaultPendingComponent ? (0,
                I.jsx)(e.options.defaultPendingComponent, {}) : null;
        if (i)
            return ur(e, r, void 0);
        if (!a)
            return null;
        let s = (0,
            I.jsx)(pr, {
                matchId: a
            });
        return t === `__root__` ? (0,
            I.jsx)(L.Suspense, {
                fallback: o,
                children: s
            }) : s
    });
function _r() {
    let e = En()
        , t = e.routesById.__root__.options.pendingComponent ?? e.options.defaultPendingComponent
        , n = t ? (0,
            I.jsx)(t, {}) : null
        , r = e.isServer || typeof document < `u` && e.ssr ? lr : L.Suspense
        , i = (0,
            I.jsxs)(r, {
                fallback: n,
                children: [!e.isServer && (0,
                    I.jsx)(or, {}), (0,
                        I.jsx)(vr, {})]
            });
    return e.options.InnerWrap ? (0,
        I.jsx)(e.options.InnerWrap, {
            children: i
        }) : i
}
function vr() {
    let e = En()
        , t = Dn({
            select: e => e.matches[0]?.id
        })
        , n = Dn({
            select: e => e.loadedAt
        })
        , r = t ? (0,
            I.jsx)(pr, {
                matchId: t
            }) : null;
    return (0,
        I.jsx)(On.Provider, {
            value: t,
            children: e.options.disableGlobalCatchBoundary ? r : (0,
                I.jsx)(cn, {
                    getResetKey: () => n,
                    errorComponent: un,
                    onCatch: e => {
                        gn(!1, `The following error wasn't caught by any route! At the very least, consider setting an 'errorComponent' in your RootRoute!`),
                            gn(!1, e.message || e.toString())
                    }
                    ,
                    children: r
                })
        })
}
var yr = e => new br(e)
    , br = class extends Xt {
        constructor(e) {
            super(e)
        }
    }
    ;
typeof globalThis < `u` ? (globalThis.createFileRoute = nr,
    globalThis.createLazyFileRoute = ar) : typeof window < `u` && (window.createFileRoute = nr,
        window.createLazyFileRoute = ar);
function xr({ router: e, children: t, ...n }) {
    Object.keys(n).length > 0 && e.update({
        ...e.options,
        ...n,
        context: {
            ...e.options.context,
            ...n.context
        }
    });
    let r = Tn()
        , i = (0,
            I.jsx)(r.Provider, {
                value: e,
                children: t
            });
    return e.options.Wrap ? (0,
        I.jsx)(e.options.Wrap, {
            children: i
        }) : i
}
function Sr({ router: e, ...t }) {
    return (0,
        I.jsx)(xr, {
            router: e,
            ...t,
            children: (0,
                I.jsx)(_r, {})
        })
}
var Cr = o((e => {
    var t = f().__CLIENT_INTERNALS_DO_NOT_USE_OR_WARN_USERS_THEY_CANNOT_UPGRADE;
    e.c = function (e) {
        return t.H.useMemoCache(e)
    }
}
))
    , wr = o(((e, t) => {
        t.exports = Cr()
    }
    ))
    , Tr = c(wr());
function Er() {
    let e = (0,
        Tr.c)(1), t;
    return e[0] === Symbol.for(`react.memo_cache_sentinel`) ? (t = (0,
        I.jsxs)(`div`, {
            className: `min-h-screen bg-gray-50`,
            children: [(0,
                I.jsx)(`nav`, {
                    className: `bg-white shadow-sm border-b border-gray-200`,
                    children: (0,
                        I.jsx)(`div`, {
                            className: `max-w-7xl mx-auto px-4 sm:px-6 lg:px-8`,
                            children: (0,
                                I.jsx)(`div`, {
                                    className: `flex justify-between h-16 items-center`,
                                    children: (0,
                                        I.jsx)(Xn, {
                                            to: `/`,
                                            className: `text-2xl font-bold text-primary-600`,
                                            children: `Human Benchmark`
                                        })
                                })
                        })
                }), (0,
                    I.jsx)(`main`, {
                        children: (0,
                            I.jsx)(gr, {})
                    })]
        }),
        e[0] = t) : t = e[0],
        t
}
var Dr = `human-benchmark-user-id`
    , Or = `human-benchmark-user-name`;
function kr() {
    let e = localStorage.getItem(Dr);
    return e || (e = crypto.randomUUID(),
        localStorage.setItem(Dr, e)),
        e
}
function Ar() {
    return localStorage.getItem(Or) || `Anonymous`
}
function jr(e) {
    localStorage.setItem(Or, e)
}
var Mr = [{
    id: `reaction-time`,
    name: `Reaction Time`,
    description: `Test how quickly you can react to a color change`,
    icon: `⚡`,
    color: `bg-yellow-500`
}, {
    id: `chimp-test`,
    name: `Chimp Test`,
    description: `Test your working memory with numbers`,
    icon: `🧠`,
    color: `bg-purple-500`
}, {
    id: `aim-trainer`,
    name: `Aim Trainer`,
    description: `Test your precision and reflexes`,
    icon: `🎯`,
    color: `bg-red-500`
}, {
    id: `typing`,
    name: `Typing`,
    description: `Test how fast you can type`,
    icon: `⌨️`,
    color: `bg-green-500`
}];
function Nr() {
    let [e, t] = (0,
        L.useState)({})
        , [n, r] = (0,
            L.useState)(!1)
        , [i, a] = (0,
            L.useState)(!1)
        , [o, s] = (0,
            L.useState)(null)
        , c = kr();
    (0,
        L.useEffect)(() => {
            l()
        }
            , []);
    async function l() {
        let e = {}
            , n = null;
        for (let t of Mr)
            try {
                let r = await (await fetch(`/api/leaderboard/${t.id}?userId=${c}`)).json()
                    , i = r.entries || r
                    , a = r.flag
                    , o = i.find(e => e.userId === c);
                o && (e[t.id] = o),
                    a && (n = a)
            } catch (e) {
                console.error(`Failed to fetch ${t.id} score:`, e)
            }
        t(e),
            n ? (s(n),
                a(!0)) : (s(null),
                    a(!1))
    }
    function u() {
        let t = window.location.origin
            , n = `🎮 My Human Benchmark Scores 🎮

`;
        return [{
            id: `reaction-time`,
            name: `Reaction Time`,
            unit: `ms`
        }, {
            id: `chimp-test`,
            name: `Chimp Test`,
            unit: `Level`
        }, {
            id: `aim-trainer`,
            name: `Aim Trainer`,
            unit: `hits`
        }, {
            id: `typing`,
            name: `Typing`,
            unit: `WPM`
        }].forEach(t => {
            let r = e[t.id];
            if (r) {
                let e = r.score;
                if (t.id === `chimp-test` && r.time) {
                    let i = Math.round(r.time / 1e3);
                    n += `${t.name}: Level ${e} (${i}s)\n`
                } else
                    n += `${t.name}: ${e} ${t.unit}\n`
            }
        }
        ),
            n += `\nCan you beat my scores? ${t}`,
            n
    }
    async function d() {
        let e = u();
        try {
            if (navigator.clipboard && navigator.clipboard.writeText)
                await navigator.clipboard.writeText(e);
            else {
                let t = document.createElement(`textarea`);
                t.value = e,
                    t.style.position = `fixed`,
                    t.style.left = `-999999px`,
                    document.body.appendChild(t),
                    t.select(),
                    document.execCommand(`copy`),
                    document.body.removeChild(t)
            }
            r(!0),
                setTimeout(() => r(!1), 2e3)
        } catch (e) {
            console.error(`Failed to copy:`, e),
                alert(`Failed to copy to clipboard`)
        }
    }
    let f = Object.keys(e).length > 0;
    return (0,
        I.jsxs)(`div`, {
            className: `game-container py-12`,
            children: [(0,
                I.jsxs)(`div`, {
                    className: `text-center mb-12`,
                    children: [(0,
                        I.jsx)(`h1`, {
                            className: `text-5xl font-bold text-gray-900 mb-4`,
                            children: `Human Benchmark`
                        }), (0,
                            I.jsx)(`p`, {
                                className: `text-xl text-gray-600 mb-6`,
                                children: `Test your cognitive abilities with these interactive games`
                            }), (0,
                                I.jsx)(Xn, {
                                    to: `/leaderboard`,
                                    className: `inline-block game-button`,
                                    children: `🏆 View Leaderboards`
                                })]
                }), i && o && (0,
                    I.jsx)(`div`, {
                        className: `card mb-8 bg-gradient-to-r from-yellow-400 to-yellow-300 border-4 border-yellow-500`,
                        children: (0,
                            I.jsxs)(`div`, {
                                className: `text-center`,
                                children: [(0,
                                    I.jsx)(`h2`, {
                                        className: `text-3xl font-bold text-gray-900 mb-4`,
                                        children: `🏆 You win! 🏆`
                                    }), (0,
                                        I.jsx)(`div`, {
                                            className: `bg-black text-green-400 font-mono text-lg p-4 rounded-lg inline-block mt-4`,
                                            children: o
                                        })]
                            })
                    }), f && (0,
                        I.jsxs)(`div`, {
                            className: `card mb-8 bg-gradient-to-r from-primary-50 to-blue-50`,
                            children: [(0,
                                I.jsx)(`h2`, {
                                    className: `text-2xl font-bold text-gray-900 mb-4`,
                                    children: `📊 Your Scores`
                                }), (0,
                                    I.jsx)(`div`, {
                                        className: `grid grid-cols-2 md:grid-cols-4 gap-4 mb-4`,
                                        children: Mr.map(t => {
                                            let n = e[t.id];
                                            if (!n)
                                                return null;
                                            let r = n.score;
                                            return (0,
                                                I.jsxs)(`div`, {
                                                    className: `text-center p-3 bg-white rounded-lg shadow-sm`,
                                                    children: [(0,
                                                        I.jsx)(`div`, {
                                                            className: `text-2xl mb-1`,
                                                            children: t.icon
                                                        }), (0,
                                                            I.jsx)(`div`, {
                                                                className: `text-sm text-gray-600 mb-1`,
                                                                children: t.name
                                                            }), (0,
                                                                I.jsx)(`div`, {
                                                                    className: `text-xl font-bold text-primary-600`,
                                                                    children: t.id === `chimp-test` ? `Level ${r}` : `${r} ${{
                                                                        "reaction-time": `ms`,
                                                                        "chimp-test": `Level`,
                                                                        "aim-trainer": `hits`,
                                                                        typing: `WPM`
                                                                    }[t.id]}`
                                                                }), t.id === `chimp-test` && n.time && (0,
                                                                    I.jsxs)(`div`, {
                                                                        className: `text-xs text-gray-500 mt-1`,
                                                                        children: [`(`, Math.round(n.time / 1e3), `s)`]
                                                                    })]
                                                }, t.id)
                                        }
                                        )
                                    }), (0,
                                        I.jsx)(`button`, {
                                            onClick: d,
                                            className: `game-button w-full md:w-auto`,
                                            children: n ? `✓ Copied!` : `🔗 Share Your Scores`
                                        })]
                        }), (0,
                            I.jsx)(`div`, {
                                className: `grid grid-cols-1 md:grid-cols-2 gap-6`,
                                children: Mr.map(e => (0,
                                    I.jsx)(Xn, {
                                        to: `/${e.id}`,
                                        className: `card hover:shadow-xl transition-shadow duration-200 cursor-pointer group`,
                                        children: (0,
                                            I.jsxs)(`div`, {
                                                className: `flex items-start gap-4`,
                                                children: [(0,
                                                    I.jsx)(`div`, {
                                                        className: `${e.color} text-white rounded-lg p-4 text-3xl`,
                                                        children: e.icon
                                                    }), (0,
                                                        I.jsxs)(`div`, {
                                                            className: `flex-1`,
                                                            children: [(0,
                                                                I.jsx)(`h2`, {
                                                                    className: `text-2xl font-bold text-gray-900 mb-2 group-hover:text-primary-600 transition-colors`,
                                                                    children: e.name
                                                                }), (0,
                                                                    I.jsx)(`p`, {
                                                                        className: `text-gray-600`,
                                                                        children: e.description
                                                                    })]
                                                        }), (0,
                                                            I.jsx)(`div`, {
                                                                className: `text-primary-600 text-2xl`,
                                                                children: `→`
                                                            })]
                                            })
                                    }, e.id))
                            })]
        })
}
function Pr({ gameType: e, personalBest: t, formatScore: n, onRefresh: r }) {
    let [i, a] = (0,
        L.useState)([])
        , [o, s] = (0,
            L.useState)(!1)
        , [c, l] = (0,
            L.useState)(Ar())
        , u = kr();
    (0,
        L.useEffect)(() => {
            d();
            let e = setInterval(d, 2e3);
            return () => clearInterval(e)
        }
            , [e, t]);
    async function d() {
        try {
            let t = await (await fetch(`/api/leaderboard/${e}?userId=${u}`)).json();
            a(t),
                r && r()
        } catch (e) {
            console.error(`Failed to fetch leaderboard:`, e)
        }
    }
    async function f() {
        if (c.trim()) {
            jr(c.trim()),
                s(!1);
            try {
                await fetch(`/api/update-name`, {
                    method: `POST`,
                    headers: {
                        "Content-Type": `application/json`
                    },
                    body: JSON.stringify({
                        userId: u,
                        name: c.trim()
                    })
                }),
                    d()
            } catch (e) {
                console.error(`Failed to update name:`, e)
            }
        }
    }
    return (0,
        I.jsxs)(`div`, {
            className: `card mt-6`,
            children: [(0,
                I.jsxs)(`div`, {
                    className: `flex justify-between items-center mb-4`,
                    children: [(0,
                        I.jsx)(`h2`, {
                            className: `text-2xl font-bold text-gray-900`,
                            children: `Leaderboard`
                        }), t && (0,
                            I.jsxs)(`div`, {
                                className: `text-right`,
                                children: [(0,
                                    I.jsx)(`div`, {
                                        className: `text-sm text-gray-600`,
                                        children: `Your Best`
                                    }), (0,
                                        I.jsx)(`div`, {
                                            className: `text-xl font-bold text-primary-600`,
                                            children: n(t)
                                        })]
                            })]
                }), o ? (0,
                    I.jsxs)(`div`, {
                        className: `mb-4 flex gap-2`,
                        children: [(0,
                            I.jsx)(`input`, {
                                type: `text`,
                                value: c,
                                onChange: e => l(e.target.value),
                                onKeyDown: e => e.key === `Enter` && f(),
                                className: `flex-1 px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-primary-500`,
                                placeholder: `Enter your name`,
                                maxLength: 20
                            }), (0,
                                I.jsx)(`button`, {
                                    onClick: f,
                                    className: `game-button`,
                                    children: `Save`
                                }), (0,
                                    I.jsx)(`button`, {
                                        onClick: () => {
                                            s(!1),
                                                l(Ar())
                                        }
                                        ,
                                        className: `px-4 py-2 bg-gray-200 rounded-lg hover:bg-gray-300`,
                                        children: `Cancel`
                                    })]
                    }) : (0,
                        I.jsxs)(`div`, {
                            className: `mb-4 flex justify-between items-center`,
                            children: [(0,
                                I.jsxs)(`span`, {
                                    className: `text-gray-600`,
                                    children: [`Playing as: `, (0,
                                        I.jsx)(`strong`, {
                                            children: Ar()
                                        })]
                                }), (0,
                                    I.jsx)(`button`, {
                                        onClick: () => s(!0),
                                        className: `text-primary-600 hover:text-primary-700 text-sm`,
                                        children: `Change Name`
                                    })]
                        }), (0,
                            I.jsx)(`div`, {
                                className: `overflow-x-auto`,
                                children: (0,
                                    I.jsxs)(`table`, {
                                        className: `w-full`,
                                        children: [(0,
                                            I.jsx)(`thead`, {
                                                children: (0,
                                                    I.jsxs)(`tr`, {
                                                        className: `border-b border-gray-200`,
                                                        children: [(0,
                                                            I.jsx)(`th`, {
                                                                className: `text-left py-2 px-4 text-gray-600 font-semibold`,
                                                                children: `Rank`
                                                            }), (0,
                                                                I.jsx)(`th`, {
                                                                    className: `text-left py-2 px-4 text-gray-600 font-semibold`,
                                                                    children: `Player`
                                                                }), (0,
                                                                    I.jsx)(`th`, {
                                                                        className: `text-right py-2 px-4 text-gray-600 font-semibold`,
                                                                        children: `Score`
                                                                    }), e === `chimp-test` && (0,
                                                                        I.jsx)(`th`, {
                                                                            className: `text-right py-2 px-4 text-gray-600 font-semibold`,
                                                                            children: `Time`
                                                                        })]
                                                    })
                                            }), (0,
                                                I.jsx)(`tbody`, {
                                                    children: i.entries.length === 0 ? (0,
                                                        I.jsx)(`tr`, {
                                                            children: (0,
                                                                I.jsx)(`td`, {
                                                                    colSpan: e === `chimp-test` ? 4 : 3,
                                                                    className: `text-center py-8 text-gray-500`,
                                                                    children: `No scores yet. Be the first!`
                                                                })
                                                        }) : i.entries.map((t, r) => (0,
                                                            I.jsxs)(`tr`, {
                                                                className: `border-b border-gray-100 ${t.userId === u ? `bg-primary-50` : ``}`,
                                                                children: [(0,
                                                                    I.jsx)(`td`, {
                                                                        className: `py-3 px-4`,
                                                                        children: (0,
                                                                            I.jsx)(`span`, {
                                                                                className: `font-bold ${r === 0 ? `text-yellow-500` : r === 1 ? `text-gray-400` : r === 2 ? `text-orange-600` : `text-gray-600`}`,
                                                                                children: r === 0 ? `🥇` : r === 1 ? `🥈` : r === 2 ? `🥉` : `#${r + 1}`
                                                                            })
                                                                    }), (0,
                                                                        I.jsx)(`td`, {
                                                                            className: `py-3 px-4`,
                                                                            children: (0,
                                                                                I.jsxs)(`span`, {
                                                                                    className: t.userId === u ? `font-bold` : ``,
                                                                                    children: [t.name, t.userId === u && ` (You)`]
                                                                                })
                                                                        }), (0,
                                                                            I.jsx)(`td`, {
                                                                                className: `py-3 px-4 text-right font-semibold`,
                                                                                children: n(t.score)
                                                                            }), e === `chimp-test` && (0,
                                                                                I.jsx)(`td`, {
                                                                                    className: `py-3 px-4 text-right text-sm text-gray-600`,
                                                                                    children: t.time ? `${(t.time / 1e3).toFixed(1)}s` : `-`
                                                                                })]
                                                            }, t.userId))
                                                })]
                                    })
                            })]
        })
}
var Fr = class {
    constructor(e) {
        this.gameType = e,
            this.ws = null,
            this.messageHandlers = []
    }
    connect() {
        return new Promise((e, t) => {
            let n = window.location.protocol === `https:` ? `wss:` : `ws:`
                , r = window.location.host
                , i = kr();
            this.ws = new WebSocket(`${n}//${r}/game/${this.gameType}?userId=${i}`),
                this.ws.onopen = () => {
                    console.log(`WebSocket connected for`, this.gameType),
                        e()
                }
                ,
                this.ws.onerror = e => {
                    console.error(`WebSocket error:`, e),
                        t(e)
                }
                ,
                this.ws.onmessage = e => {
                    let t = JSON.parse(e.data);
                    this.messageHandlers.forEach(e => e(t))
                }
        }
        )
    }
    send(e) {
        this.ws && this.ws.readyState === WebSocket.OPEN && this.ws.send(JSON.stringify(e))
    }
    onMessage(e) {
        this.messageHandlers.push(e)
    }
    close() {
        this.ws && this.ws.close()
    }
}
    , Ir = c(wr())
    , R = {
        WAITING: `waiting`,
        READY: `ready`,
        GO: `go`,
        RESULT: `result`,
        TOO_EARLY: `too_early`
    };
function Lr() {
    let e = (0,
        Ir.c)(31), [t, n] = (0,
            L.useState)(R.WAITING), [r, i] = (0,
                L.useState)(null), [a, o] = (0,
                    L.useState)(null), s;
    e[0] === Symbol.for(`react.memo_cache_sentinel`) ? (s = [],
        e[0] = s) : s = e[0];
    let [c, l] = (0,
        L.useState)(s), u = (0,
            L.useRef)(null), d = (0,
                L.useRef)(null), f, p;
    e[1] === Symbol.for(`react.memo_cache_sentinel`) ? (f = () => () => {
        d.current && d.current.close()
    }
        ,
        p = [],
        e[1] = f,
        e[2] = p) : (f = e[1],
            p = e[2]),
        (0,
            L.useEffect)(f, p);
    let m;
    e[3] === Symbol.for(`react.memo_cache_sentinel`) ? (m = async function () {
        n(R.READY),
            d.current = new Fr(`reaction-time`),
            await d.current.connect(),
            d.current.onMessage(e => {
                if (e.type === `go`) {
                    let e = Date.now();
                    u.current = e,
                        n(R.GO),
                        d.current.send({
                            type: `goReceived`,
                            timestamp: e
                        })
                } else if (e.type === `score` && e.success) {
                    let t = e.score;
                    o(t),
                        l(e => [...e, t])
                }
            }
            ),
            d.current.send({
                type: `ready`
            })
    }
        ,
        e[3] = m) : m = e[3];
    let h = m, g;
    e[4] === t ? g = e[5] : (g = async function () {
        if (t === R.WAITING)
            h();
        else if (t === R.READY)
            n(R.TOO_EARLY),
                d.current &&= (d.current.send({
                    type: `tooEarly`
                }),
                    d.current.close(),
                    null);
        else if (t === R.GO) {
            let e = Date.now()
                , t = e - u.current;
            i(t),
                o(null),
                n(R.RESULT),
                d.current && d.current.send({
                    type: `click`,
                    timestamp: e
                })
        } else
            (t === R.RESULT || t === R.TOO_EARLY) && h()
    }
        ,
        e[4] = t,
        e[5] = g);
    let _ = g, v = function () {
        switch (t) {
            case R.WAITING:
                return `bg-primary-600`;
            case R.READY:
                return `bg-red-600`;
            case R.GO:
                return `bg-green-500`;
            case R.RESULT:
                return `bg-primary-600`;
            case R.TOO_EARLY:
                return `bg-red-600`;
            default:
                return `bg-primary-600`
        }
    }, y;
    e[6] !== t || e[7] !== a ? (y = function () {
        switch (t) {
            case R.WAITING:
                return {
                    title: `Reaction Time Test`,
                    subtitle: `Click to start`
                };
            case R.READY:
                return {
                    title: `Wait for green...`,
                    subtitle: ``
                };
            case R.GO:
                return {
                    title: `Click!`,
                    subtitle: ``
                };
            case R.RESULT:
                return {
                    title: a ? `${a} ms` : `Validating...`,
                    subtitle: `Click to try again`
                };
            case R.TOO_EARLY:
                return {
                    title: `Too early!`,
                    subtitle: `Click to try again`
                };
            default:
                return {
                    title: ``,
                    subtitle: ``
                }
        }
    }(),
        e[6] = t,
        e[7] = a,
        e[8] = y) : y = e[8];
    let b = y, x;
    e[9] === c ? x = e[10] : (x = c.length > 0 ? Math.round(c.reduce(Br, 0) / c.length) : null,
        e[9] = c,
        e[10] = x);
    let S = x, C;
    e[11] === Symbol.for(`react.memo_cache_sentinel`) ? (C = (0,
        I.jsx)(`div`, {
            className: `mb-4`,
            children: (0,
                I.jsx)(Xn, {
                    to: `/`,
                    className: `text-primary-600 hover:text-primary-700`,
                    children: `← Back to Home`
                })
        }),
        e[11] = C) : C = e[11];
    let w = `${v()} rounded-xl cursor-pointer transition-colors duration-200 flex items-center justify-center min-h-[400px]`, T;
    e[12] === b.title ? T = e[13] : (T = (0,
        I.jsx)(`h1`, {
            className: `text-5xl font-bold mb-4`,
            children: b.title
        }),
        e[12] = b.title,
        e[13] = T);
    let E;
    e[14] === b.subtitle ? E = e[15] : (E = (0,
        I.jsx)(`p`, {
            className: `text-xl`,
            children: b.subtitle
        }),
        e[14] = b.subtitle,
        e[15] = E);
    let D;
    e[16] !== E || e[17] !== T ? (D = (0,
        I.jsxs)(`div`, {
            className: `text-center text-white`,
            children: [T, E]
        }),
        e[16] = E,
        e[17] = T,
        e[18] = D) : D = e[18];
    let O;
    e[19] !== _ || e[20] !== D || e[21] !== w ? (O = (0,
        I.jsx)(`div`, {
            className: w,
            onClick: _,
            children: D
        }),
        e[19] = _,
        e[20] = D,
        e[21] = w,
        e[22] = O) : O = e[22];
    let ee;
    e[23] !== c || e[24] !== S || e[25] !== r ? (ee = c.length > 0 && (0,
        I.jsxs)(`div`, {
            className: `card mt-6`,
            children: [(0,
                I.jsx)(`h2`, {
                    className: `text-2xl font-bold mb-4`,
                    children: `Your Attempts`
                }), (0,
                    I.jsxs)(`div`, {
                        className: `grid grid-cols-2 gap-4 mb-4`,
                        children: [(0,
                            I.jsxs)(`div`, {
                                children: [(0,
                                    I.jsx)(`div`, {
                                        className: `text-gray-600`,
                                        children: `Last Attempt`
                                    }), (0,
                                        I.jsxs)(`div`, {
                                            className: `text-3xl font-bold text-primary-600`,
                                            children: [r, ` ms`]
                                        })]
                            }), (0,
                                I.jsxs)(`div`, {
                                    children: [(0,
                                        I.jsx)(`div`, {
                                            className: `text-gray-600`,
                                            children: `Average`
                                        }), (0,
                                            I.jsxs)(`div`, {
                                                className: `text-3xl font-bold text-gray-900`,
                                                children: [S, ` ms`]
                                            })]
                                })]
                    }), (0,
                        I.jsx)(`div`, {
                            className: `flex flex-wrap gap-2`,
                            children: c.map(zr)
                        })]
        }),
        e[23] = c,
        e[24] = S,
        e[25] = r,
        e[26] = ee) : ee = e[26];
    let te;
    e[27] === Symbol.for(`react.memo_cache_sentinel`) ? (te = (0,
        I.jsx)(Pr, {
            gameType: `reaction-time`,
            formatScore: Rr
        }),
        e[27] = te) : te = e[27];
    let ne;
    return e[28] !== O || e[29] !== ee ? (ne = (0,
        I.jsxs)(`div`, {
            className: `game-container`,
            children: [C, O, ee, te]
        }),
        e[28] = O,
        e[29] = ee,
        e[30] = ne) : ne = e[30],
        ne
}
function Rr(e) {
    return `${e} ms`
}
function zr(e, t) {
    return (0,
        I.jsxs)(`div`, {
            className: `px-3 py-2 bg-gray-100 rounded-lg text-sm font-semibold`,
            children: [e, ` ms`]
        }, t)
}
function Br(e, t) {
    return e + t
}
var Vr = c(wr())
    , Hr = 8
    , Ur = {
        START: `start`,
        MEMORIZE: `memorize`,
        RECALL: `recall`,
        RESULT: `result`
    };
function Wr() {
    let e = (0,
        Vr.c)(35), [t, n] = (0,
            L.useState)(Ur.START), [r, i] = (0,
                L.useState)(4), a;
    e[0] === Symbol.for(`react.memo_cache_sentinel`) ? (a = [],
        e[0] = a) : a = e[0];
    let [o, s] = (0,
        L.useState)(a), [c, l] = (0,
            L.useState)(!1), [u, d] = (0,
                L.useState)(1), [, f] = (0,
                    L.useState)(!1), [, p] = (0,
                        L.useState)(0), [m, h] = (0,
                            L.useState)(null), g = (0,
                                L.useRef)(null), _, v;
    e[1] === Symbol.for(`react.memo_cache_sentinel`) ? (_ = () => () => {
        g.current && g.current.close()
    }
        ,
        v = [],
        e[1] = _,
        e[2] = v) : (_ = e[1],
            v = e[2]),
        (0,
            L.useEffect)(_, v);
    let y, b, x, S, C, w, T;
    if (e[3] !== o || e[4] !== u || e[5] !== c || e[6] !== r || e[7] !== t) {
        y = async function () {
            d(1),
                f(!1),
                p(0),
                g.current = new Fr(`chimp-test`),
                await g.current.connect(),
                g.current.onMessage(e => {
                    if (e.type === `levelData`)
                        i(e.level),
                            a(e.level, e.positions);
                    else if (e.type === `levelComplete`)
                        p(t => Math.max(t, e.level));
                    else if (e.type === `gameOver`)
                        _(e.level);
                    else if (e.type === `score` && e.success) {
                        let t = e.score;
                        h(t)
                    }
                }
                ),
                g.current.send({
                    type: `start`
                })
        }
            ;
        function a(e, t) {
            n(Ur.MEMORIZE),
                d(1),
                l(!1),
                s(t),
                n(Ur.RECALL)
        }
        let m = function (e) {
            t === Ur.RECALL && (c || l(!0),
                g.current && g.current.send({
                    type: `click`,
                    number: e.number,
                    row: e.row,
                    col: e.col,
                    timestamp: Date.now()
                }),
                e.number === u && d(u + 1))
        };
        async function _(e) {
            f(!0),
                n(Ur.RESULT),
                p(e),
                h(null),
                g.current && g.current.send({
                    type: `end`
                })
        }
        w = `game-container`,
            e[15] === Symbol.for(`react.memo_cache_sentinel`) ? (T = (0,
                I.jsx)(`div`, {
                    className: `mb-4`,
                    children: (0,
                        I.jsx)(Xn, {
                            to: `/`,
                            className: `text-primary-600 hover:text-primary-700`,
                            children: `← Back to Home`
                        })
                }),
                e[15] = T) : T = e[15],
            b = `card`,
            e[16] === Symbol.for(`react.memo_cache_sentinel`) ? (x = (0,
                I.jsx)(`h1`, {
                    className: `text-4xl font-bold text-center mb-6`,
                    children: `Chimp Test`
                }),
                e[16] = x) : x = e[16],
            e[17] !== r || e[18] !== t ? (S = t === Ur.START && (0,
                I.jsxs)(`div`, {
                    className: `text-center`,
                    children: [(0,
                        I.jsxs)(`p`, {
                            className: `text-lg text-gray-600 mb-6`,
                            children: [`Click the numbers in order from 1 to `, r, `. After clicking the first number, all numbers will hide. Each level adds one more number.`]
                        }), (0,
                            I.jsx)(`button`, {
                                onClick: y,
                                className: `game-button`,
                                children: `Start Test`
                            })]
                }),
                e[17] = r,
                e[18] = t,
                e[19] = S) : S = e[19],
            C = (t === Ur.MEMORIZE || t === Ur.RECALL) && (0,
                I.jsxs)(`div`, {
                    children: [(0,
                        I.jsx)(`div`, {
                            className: `text-center mb-6`,
                            children: (0,
                                I.jsxs)(`div`, {
                                    className: `text-2xl font-bold`,
                                    children: [`Level `, r]
                                })
                        }), (0,
                            I.jsx)(`div`, {
                                className: `flex justify-center`,
                                children: function () {
                                    return (0,
                                        I.jsx)(`div`, {
                                            className: `inline-grid gap-2`,
                                            style: {
                                                gridTemplateColumns: `repeat(${Hr}, minmax(0, 1fr))`
                                            },
                                            children: Array.from({
                                                length: Hr * Hr
                                            }).map((e, t) => {
                                                let n = Math.floor(t / Hr)
                                                    , r = t % Hr
                                                    , i = o.find(e => e.row === n && e.col === r);
                                                return (0,
                                                    I.jsx)(`div`, {
                                                        onClick: () => i && m(i),
                                                        className: `w-16 h-16 rounded-lg flex items-center justify-center text-2xl font-bold cursor-pointer transition-colors ${i ? `bg-white border-2 border-primary-600 hover:bg-primary-50` : `bg-gray-200`}`,
                                                        children: i && (!c || i.number < u) && i.number
                                                    }, t)
                                            }
                                            )
                                        })
                                }()
                            })]
                }),
            e[3] = o,
            e[4] = u,
            e[5] = c,
            e[6] = r,
            e[7] = t,
            e[8] = y,
            e[9] = b,
            e[10] = x,
            e[11] = S,
            e[12] = C,
            e[13] = w,
            e[14] = T
    } else
        y = e[8],
            b = e[9],
            x = e[10],
            S = e[11],
            C = e[12],
            w = e[13],
            T = e[14];
    let E;
    e[20] !== y || e[21] !== t || e[22] !== m ? (E = t === Ur.RESULT && (0,
        I.jsxs)(`div`, {
            className: `text-center`,
            children: [(0,
                I.jsx)(`h2`, {
                    className: `text-3xl font-bold mb-4`,
                    children: `Game Over!`
                }), (0,
                    I.jsx)(`p`, {
                        className: `text-xl text-gray-600 mb-2`,
                        children: `Highest Level Reached`
                    }), (0,
                        I.jsx)(`p`, {
                            className: `text-5xl font-bold text-primary-600 mb-6`,
                            children: m === null ? `Validating...` : m
                        }), (0,
                            I.jsx)(`button`, {
                                onClick: y,
                                className: `game-button`,
                                children: `Try Again`
                            })]
        }),
        e[20] = y,
        e[21] = t,
        e[22] = m,
        e[23] = E) : E = e[23];
    let D;
    e[24] !== b || e[25] !== x || e[26] !== S || e[27] !== C || e[28] !== E ? (D = (0,
        I.jsxs)(`div`, {
            className: b,
            children: [x, S, C, E]
        }),
        e[24] = b,
        e[25] = x,
        e[26] = S,
        e[27] = C,
        e[28] = E,
        e[29] = D) : D = e[29];
    let O;
    e[30] === Symbol.for(`react.memo_cache_sentinel`) ? (O = (0,
        I.jsx)(Pr, {
            gameType: `chimp-test`,
            formatScore: Gr
        }),
        e[30] = O) : O = e[30];
    let ee;
    return e[31] !== D || e[32] !== w || e[33] !== T ? (ee = (0,
        I.jsxs)(`div`, {
            className: w,
            children: [T, D, O]
        }),
        e[31] = D,
        e[32] = w,
        e[33] = T,
        e[34] = ee) : ee = e[34],
        ee
}
function Gr(e) {
    return `Level ${e}`
}
var Kr = 2e3
    , qr = 50
    , Jr = 10
    , Yr = {
        START: `start`,
        PLAYING: `playing`,
        RESULT: `result`
    };
function Xr() {
    let [e, t] = (0,
        L.useState)(Yr.START)
        , [n, r] = (0,
            L.useState)([])
        , [i, a] = (0,
            L.useState)(0)
        , [o, s] = (0,
            L.useState)(null)
        , [c, l] = (0,
            L.useState)(0)
        , u = (0,
            L.useRef)(null)
        , d = (0,
            L.useRef)(null)
        , f = (0,
            L.useRef)([])
        , p = (0,
            L.useRef)(new Map)
        , m = (0,
            L.useRef)(null)
        , h = (0,
            L.useRef)(null)
        , g = (0,
            L.useRef)(null)
        , _ = (0,
            L.useRef)(null)
        , [, v] = (0,
            L.useState)({});
    (0,
        L.useEffect)(() => () => {
            u.current && u.current.close(),
                _.current && clearInterval(_.current),
                g.current && cancelAnimationFrame(g.current),
                p.current.forEach(e => clearTimeout(e)),
                p.current.clear()
        }
            , []),
        (0,
            L.useEffect)(() => {
                if (e !== Yr.PLAYING)
                    return;
                function t(e) {
                    if ((e.key === `z` || e.key === `x` || e.key === ` `) && (e.preventDefault(),
                        h.current && d.current)) {
                        let e = h.current
                            , t = d.current.getBoundingClientRect()
                            , n = {
                                stopPropagation: () => { }
                                ,
                                clientX: t.left + e.x,
                                clientY: t.top + e.y,
                                currentTarget: d.current
                            };
                        x(e, n)
                    }
                }
                return window.addEventListener(`keydown`, t),
                    () => window.removeEventListener(`keydown`, t)
            }
                , [e]);
    async function y() {
        a(0),
            l(0),
            r([]),
            t(Yr.PLAYING),
            u.current = new Fr(`aim-trainer`),
            await u.current.connect(),
            u.current.onMessage(e => {
                e.type === `gameStart` ? (f.current = e.circles,
                    m.current = Date.now(),
                    u.current.send({
                        type: `gameStartAck`,
                        timestamp: m.current
                    }),
                    b()) : e.type === `gameOver` && e.success && (s(e.score),
                        C())
            }
            ),
            u.current.send({
                type: `start`
            });
        let e = () => {
            v({}),
                g.current = requestAnimationFrame(e)
        }
            ;
        e()
    }
    function b() {
        _.current = setInterval(() => {
            let e = Date.now()
                , n = e - m.current
                , i = [];
            f.current = f.current.filter(e => n >= e.spawnOffset ? (i.push(e),
                !1) : !0),
                i.forEach(n => {
                    let i = {
                        id: n.circleId,
                        x: n.x,
                        y: n.y,
                        spawnTime: e
                    };
                    r(e => [...e, i]);
                    let a = setTimeout(() => {
                        t(e => (e === Yr.PLAYING && r(e => e.some(e => e.id === n.circleId) ? (S(n.circleId),
                            u.current && u.current.send({
                                type: `timeout`,
                                circleId: n.circleId,
                                timestamp: Date.now()
                            }),
                            e.filter(e => e.id !== n.circleId)) : e),
                            e)),
                            p.current.delete(n.circleId)
                    }
                        , Kr);
                    p.current.set(n.circleId, a)
                }
                )
        }
            , 50)
    }
    function x(e, t) {
        t.stopPropagation();
        let n = d.current.getBoundingClientRect()
            , i = t.clientX - n.left
            , o = t.clientY - n.top
            , s = p.current.get(e.id);
        s && (clearTimeout(s),
            p.current.delete(e.id)),
            r(t => t.filter(t => t.id !== e.id)),
            a(e => e + 1),
            u.current && u.current.send({
                type: `click`,
                circleId: e.id,
                x: i,
                y: o,
                timestamp: Date.now()
            })
    }
    function S(e) {
        t(e => (e === Yr.PLAYING && l(e => e + 1),
            e))
    }
    function C() {
        t(Yr.RESULT),
            _.current && clearInterval(_.current),
            g.current && cancelAnimationFrame(g.current),
            p.current.forEach(e => clearTimeout(e)),
            p.current.clear(),
            r([])
    }
    function w(e) {
        let t = (Date.now() - e.spawnTime) / Kr;
        return qr - (qr - Jr) * t
    }
    return (0,
        I.jsxs)(`div`, {
            className: `game-container`,
            children: [(0,
                I.jsx)(`div`, {
                    className: `mb-4`,
                    children: (0,
                        I.jsx)(Xn, {
                            to: `/`,
                            className: `text-primary-600 hover:text-primary-700`,
                            children: `← Back to Home`
                        })
                }), (0,
                    I.jsxs)(`div`, {
                        className: `card`,
                        children: [(0,
                            I.jsx)(`h1`, {
                                className: `text-4xl font-bold text-center mb-6`,
                                children: `Aim Trainer`
                            }), e === Yr.START && (0,
                                I.jsxs)(`div`, {
                                    className: `text-center`,
                                    children: [(0,
                                        I.jsx)(`p`, {
                                            className: `text-lg text-gray-600 mb-4`,
                                            children: `Click the circles before they disappear! Each circle shrinks over time. Circles spawn faster as you survive longer. Survive as long as you can! If you miss 3 circles, game over.`
                                        }), (0,
                                            I.jsxs)(`p`, {
                                                className: `text-sm text-gray-500 mb-6`,
                                                children: [`💡 Tip: You can also press `, (0,
                                                    I.jsx)(`kbd`, {
                                                        className: `px-2 py-1 bg-gray-200 rounded`,
                                                        children: `Z`
                                                    }), `, `, (0,
                                                        I.jsx)(`kbd`, {
                                                            className: `px-2 py-1 bg-gray-200 rounded`,
                                                            children: `X`
                                                        }), `, or `, (0,
                                                            I.jsx)(`kbd`, {
                                                                className: `px-2 py-1 bg-gray-200 rounded`,
                                                                children: `Space`
                                                            }), ` to click the circles!`]
                                            }), (0,
                                                I.jsx)(`button`, {
                                                    onClick: y,
                                                    className: `game-button`,
                                                    children: `Start Training`
                                                })]
                                }), e === Yr.PLAYING && (0,
                                    I.jsxs)(`div`, {
                                        children: [(0,
                                            I.jsxs)(`div`, {
                                                className: `flex justify-between mb-4`,
                                                children: [(0,
                                                    I.jsxs)(`div`, {
                                                        children: [(0,
                                                            I.jsx)(`span`, {
                                                                className: `text-gray-600`,
                                                                children: `Score: `
                                                            }), (0,
                                                                I.jsx)(`span`, {
                                                                    className: `text-2xl font-bold text-primary-600`,
                                                                    children: i
                                                                })]
                                                    }), (0,
                                                        I.jsxs)(`div`, {
                                                            children: [(0,
                                                                I.jsx)(`span`, {
                                                                    className: `text-gray-600`,
                                                                    children: `Misses: `
                                                                }), (0,
                                                                    I.jsxs)(`span`, {
                                                                        className: `text-2xl font-bold text-red-600`,
                                                                        children: [c, `/3`]
                                                                    })]
                                                        })]
                                            }), (0,
                                                I.jsx)(`div`, {
                                                    ref: d,
                                                    className: `relative bg-gray-100 rounded-lg overflow-hidden cursor-crosshair`,
                                                    style: {
                                                        width: `800px`,
                                                        height: `600px`,
                                                        margin: `0 auto`
                                                    },
                                                    children: n.map(e => {
                                                        let t = w(e);
                                                        return (0,
                                                            I.jsx)(`div`, {
                                                                onClick: t => x(e, t),
                                                                onMouseEnter: () => h.current = e,
                                                                onMouseLeave: () => {
                                                                    h.current?.id === e.id && (h.current = null)
                                                                }
                                                                ,
                                                                className: `absolute bg-primary-600 rounded-full cursor-pointer hover:bg-primary-700`,
                                                                style: {
                                                                    left: `${e.x}px`,
                                                                    top: `${e.y}px`,
                                                                    width: `${t * 2}px`,
                                                                    height: `${t * 2}px`,
                                                                    transform: `translate(-50%, -50%)`
                                                                }
                                                            }, e.id)
                                                    }
                                                    )
                                                })]
                                    }), e === Yr.RESULT && (0,
                                        I.jsxs)(`div`, {
                                            className: `text-center`,
                                            children: [(0,
                                                I.jsx)(`h2`, {
                                                    className: `text-3xl font-bold mb-4`,
                                                    children: `Game Over!`
                                                }), (0,
                                                    I.jsx)(`p`, {
                                                        className: `text-xl text-gray-600 mb-2`,
                                                        children: `Final Score`
                                                    }), (0,
                                                        I.jsx)(`p`, {
                                                            className: `text-5xl font-bold text-primary-600 mb-6`,
                                                            children: o === null ? `Validating...` : o
                                                        }), (0,
                                                            I.jsx)(`button`, {
                                                                onClick: y,
                                                                className: `game-button`,
                                                                children: `Try Again`
                                                            })]
                                        })]
                    }), (0,
                        I.jsx)(Pr, {
                            gameType: `aim-trainer`,
                            formatScore: e => `${e} hits`
                        })]
        })
}
var Zr = c(wr())
    , Qr = {
        START: `start`,
        TYPING: `typing`,
        CAPTCHA_EXPLAIN: `captcha_explain`,
        CAPTCHA: `captcha`,
        RESULT: `result`
    };
function $r() {
    let e = (0,
        Zr.c)(76), [t, n] = (0,
            L.useState)(Qr.START), r;
    e[0] === Symbol.for(`react.memo_cache_sentinel`) ? (r = [],
        e[0] = r) : r = e[0];
    let [i, a] = (0,
        L.useState)(r), [o, s] = (0,
            L.useState)(0), [c, l] = (0,
                L.useState)(``), [u, d] = (0,
                    L.useState)(null), f;
    e[1] === Symbol.for(`react.memo_cache_sentinel`) ? (f = [],
        e[1] = f) : f = e[1];
    let [p, m] = (0,
        L.useState)(f), [, h] = (0,
            L.useState)(``), [, g] = (0,
                L.useState)(0), [, _] = (0,
                    L.useState)(0), [, v] = (0,
                        L.useState)(100), [y, b] = (0,
                            L.useState)(null), [x, S] = (0,
                                L.useState)(null), [C, w] = (0,
                                    L.useState)(``), [, T] = (0,
                                        L.useState)(null), [E, D] = (0,
                                            L.useState)(!1), O = (0,
                                                L.useRef)(null), ee = (0,
                                                    L.useRef)(null), [te, ne] = (0,
                                                        L.useState)(!1), re, ie;
    e[2] === Symbol.for(`react.memo_cache_sentinel`) ? (re = () => () => {
        O.current && O.current.close()
    }
        ,
        ie = [],
        e[2] = re,
        e[3] = ie) : (re = e[2],
            ie = e[3]),
        (0,
            L.useEffect)(re, ie);
    let ae;
    e[4] === Symbol.for(`react.memo_cache_sentinel`) ? (ae = async function () {
        ne(!0);
        try {
            l(``),
                s(0),
                m([]),
                h(``),
                O.current = new Fr(`typing`),
                await O.current.connect(),
                O.current.onMessage(e => {
                    e.type === `session` && e.words ? (a(e.words),
                        n(Qr.TYPING),
                        O.current.send({
                            type: `start`
                        }),
                        setTimeout(() => {
                            ee.current && ee.current.focus()
                        }
                            , 100),
                        d(null),
                        ne(!1)) : e.type === `captcha` ? (S(e.image),
                            n(Qr.CAPTCHA_EXPLAIN),
                            ne(!1)) : e.type === `score` && (e.success ? b({
                                wpm: e.score,
                                rawWpm: e.rawWpm,
                                accuracy: e.accuracy
                            }) : e.error && (alert(e.error),
                                n(Qr.START)))
                }
                )
        } catch (e) {
            let t = e;
            console.error(`Failed to start game:`, t),
                alert(`Failed to load typing test. Please try again.`),
                ne(!1)
        }
    }
        ,
        e[4] = ae) : ae = e[4];
    let oe = ae, k, A, se;
    if (e[5] !== C || e[6] !== p || e[7] !== c || e[8] !== o || e[9] !== u || e[10] !== t || e[11] !== i) {
        k = function (e) {
            if (t !== Qr.TYPING)
                return;
            !u && e.key.length === 1 && d(Date.now());
            let n = i[o]
                , r = o === i.length - 1;
            if (e.key === ` `) {
                e.preventDefault();
                let t = c;
                O.current && O.current.send({
                    type: `word`,
                    word: t,
                    wordIndex: o,
                    timestamp: Date.now()
                }),
                    m(e => [...e, {
                        typed: t,
                        expected: n,
                        correct: t === n
                    }]),
                    h(e => e + t + ` `);
                let r = o + 1;
                s(r),
                    l(``),
                    r >= i.length && f()
            } else if (e.key === `Backspace`) {
                if (e.preventDefault(),
                    c.length > 0)
                    l(ci);
                else if (o > 0) {
                    let e = o - 1
                        , t = p[e];
                    s(e),
                        l(t.typed),
                        m(si),
                        h(oi)
                }
            } else if (e.key.length === 1 && !e.ctrlKey && !e.metaKey && !e.altKey) {
                e.preventDefault();
                let t = c + e.key;
                l(t),
                    r && t === n && (O.current && O.current.send({
                        type: `word`,
                        word: t,
                        wordIndex: o,
                        timestamp: Date.now()
                    }),
                        m(e => [...e, {
                            typed: t,
                            expected: n,
                            correct: !0
                        }]),
                        f())
            }
        }
            ;
        let r;
        e[15] === Symbol.for(`react.memo_cache_sentinel`) ? (r = function () {
            D(!0),
                T(Date.now()),
                n(Qr.CAPTCHA),
                O.current && O.current.send({
                    type: `captchaStart`
                })
        }
            ,
            e[15] = r) : r = e[15],
            A = r;
        let a;
        e[16] === C ? a = e[17] : (a = function () {
            !C.trim() || !O.current || (O.current.send({
                type: `captcha`,
                text: C
            }),
                n(Qr.RESULT),
                b(null))
        }
            ,
            e[16] = C,
            e[17] = a),
            se = a;
        async function f() {
            let e = (Date.now() - u) / 6e4
                , t = 0
                , r = 0;
            p.forEach(e => {
                t += e.typed.length,
                    e.correct && (r += e.typed.length)
            }
            );
            let i = t / 5
                , a = Math.floor(i / e)
                , o = t > 0 ? Math.round(r / t * 100) : 0
                , s = Math.floor(a * (o / 100));
            _(a),
                g(s),
                v(o),
                b(null),
                n(Qr.RESULT),
                O.current && O.current.send({
                    type: `end`
                })
        }
        e[5] = C,
            e[6] = p,
            e[7] = c,
            e[8] = o,
            e[9] = u,
            e[10] = t,
            e[11] = i,
            e[12] = k,
            e[13] = A,
            e[14] = se
    } else
        k = e[12],
            A = e[13],
            se = e[14];
    let ce;
    if (e[18] === Symbol.for(`react.memo_cache_sentinel`)) {
        let t = function (e, t) {
            let n = []
                , r = Math.max(e.length, t.length);
            for (let i = 0; i < r; i++) {
                let r = e[i]
                    , a = t[i];
                r && a ? r === a ? n.push({
                    type: `correct`,
                    char: r
                }) : n.push({
                    type: `wrong`,
                    char: r
                }) : r && !a ? n.push({
                    type: `extra`,
                    char: r
                }) : !r && a && n.push({
                    type: `missing`,
                    char: a
                })
            }
            return n
        };
        ce = function (e) {
            if (!e)
                return null;
            if (e.correct)
                return (0,
                    I.jsx)(`span`, {
                        className: `text-green-600`,
                        children: e.typed
                    });
            let n = t(e.typed, e.expected);
            return (0,
                I.jsx)(`span`, {
                    children: n.map(ai)
                })
        }
            ,
            e[18] = ce
    } else
        ce = e[18];
    let le = ce, j, M, N, P, ue, F, de, fe;
    if (e[19] !== x || e[20] !== C || e[21] !== p || e[22] !== c || e[23] !== o || e[24] !== k || e[25] !== te || e[26] !== E || e[27] !== A || e[28] !== u || e[29] !== t || e[30] !== se || e[31] !== i) {
        let n = function () {
            return (0,
                I.jsx)(`div`, {
                    className: `text-2xl leading-relaxed font-mono mb-6 p-4 bg-gray-50 rounded-lg min-h-[150px] flex flex-wrap cursor-text relative`,
                    tabIndex: 0,
                    onKeyDown: k,
                    ref: ee,
                    children: i.map((e, t) => {
                        let n = t < o, r = t === o, i;
                        if (n)
                            i = le(p[t]);
                        else if (r) {
                            let t = e
                                , n = c;
                            i = (0,
                                I.jsxs)(`span`, {
                                    children: [n.split(``).map((e, n) => {
                                        let r = t[n];
                                        return (0,
                                            I.jsx)(`span`, {
                                                className: e === r ? `text-gray-900` : `text-red-600 bg-red-100`,
                                                children: r || e
                                            }, n)
                                    }
                                    ), (0,
                                        I.jsx)(`span`, {
                                            className: `inline-block w-0.5 h-6 bg-primary-600 animate-pulse align-middle`
                                        }), t.slice(n.length).split(``).map(ii)]
                                })
                        } else
                            i = (0,
                                I.jsx)(`span`, {
                                    className: `text-gray-400`,
                                    children: e
                                });
                        return (0,
                            I.jsx)(`span`, {
                                className: `mr-3 mb-2`,
                                children: i
                            }, t)
                    }
                    )
                })
        }, r;
        e[40] !== p || e[41] !== c || e[42] !== u ? (r = function () {
            if (!u || p.length === 0)
                return 0;
            let e = (Date.now() - u) / 6e4
                , t = p.reduce(ri, 0);
            t += c.length;
            let n = t / 5;
            return Math.round(n / e)
        }
            ,
            e[40] = p,
            e[41] = c,
            e[42] = u,
            e[43] = r) : r = e[43];
        let a = r, s;
        e[44] === p ? s = e[45] : (s = function () {
            if (p.length === 0)
                return 100;
            let e = p.filter(ni).length;
            return Math.round(e / p.length * 100)
        }
            ,
            e[44] = p,
            e[45] = s);
        let l = s;
        N = `game-container`,
            e[46] === Symbol.for(`react.memo_cache_sentinel`) ? (P = (0,
                I.jsx)(`div`, {
                    className: `mb-4`,
                    children: (0,
                        I.jsx)(Xn, {
                            to: `/`,
                            className: `text-primary-600 hover:text-primary-700`,
                            children: `← Back to Home`
                        })
                }),
                e[46] = P) : P = e[46],
            ue = `card`,
            e[47] === Symbol.for(`react.memo_cache_sentinel`) ? (F = (0,
                I.jsx)(`h1`, {
                    className: `text-4xl font-bold text-center mb-6`,
                    children: `Typing Test`
                }),
                e[47] = F) : F = e[47],
            e[48] !== te || e[49] !== t ? (de = t === Qr.START && (0,
                I.jsxs)(`div`, {
                    className: `text-center`,
                    children: [(0,
                        I.jsx)(`p`, {
                            className: `text-lg text-gray-600 mb-6`,
                            children: `Type the given words as quickly and accurately as possible.`
                        }), (0,
                            I.jsx)(`button`, {
                                onClick: oe,
                                className: `game-button`,
                                disabled: te,
                                children: te ? `Loading...` : `Start Test`
                            })]
                }),
                e[48] = te,
                e[49] = t,
                e[50] = de) : de = e[50],
            e[51] !== A || e[52] !== t ? (fe = t === Qr.CAPTCHA_EXPLAIN && (0,
                I.jsxs)(`div`, {
                    className: `text-center max-w-2xl mx-auto`,
                    children: [(0,
                        I.jsx)(`h2`, {
                            className: `text-2xl font-bold mb-4`,
                            children: `⚠️ Verification Required`
                        }), (0,
                            I.jsxs)(`div`, {
                                className: `bg-yellow-50 border-2 border-yellow-200 rounded-lg p-6 mb-6 text-left`,
                                children: [(0,
                                    I.jsx)(`p`, {
                                        className: `text-gray-800 mb-3`,
                                        children: (0,
                                            I.jsx)(`strong`, {
                                                children: `Your typing speed was very high!`
                                            })
                                    }), (0,
                                        I.jsx)(`p`, {
                                            className: `text-gray-700 mb-3`,
                                            children: `To prevent cheating, we need to verify your speed. You'll be shown an image with 25 words displayed in a grid.`
                                        }), (0,
                                            I.jsx)(`p`, {
                                                className: `text-gray-700 mb-3`,
                                                children: (0,
                                                    I.jsx)(`strong`, {
                                                        children: `Instructions:`
                                                    })
                                            }), (0,
                                                I.jsxs)(`ul`, {
                                                    className: `list-disc list-inside text-gray-700 space-y-1 mb-3`,
                                                    children: [(0,
                                                        I.jsx)(`li`, {
                                                            children: `Type all 25 words separated by spaces`
                                                        }), (0,
                                                            I.jsx)(`li`, {
                                                                children: `The image is intentionally distorted to prevent automation`
                                                            }), (0,
                                                                I.jsx)(`li`, {
                                                                    children: `You need at least 70% accuracy to pass`
                                                                }), (0,
                                                                    I.jsx)(`li`, {
                                                                        children: `Your captcha typing speed should be similar to your test speed`
                                                                    })]
                                                }), (0,
                                                    I.jsx)(`p`, {
                                                        className: `text-gray-600 text-sm`,
                                                        children: `This helps ensure fair competition on the leaderboard.`
                                                    })]
                            }), (0,
                                I.jsx)(`button`, {
                                    onClick: A,
                                    className: `game-button`,
                                    children: `Start Verification`
                                })]
                }),
                e[51] = A,
                e[52] = t,
                e[53] = fe) : fe = e[53],
            e[54] !== x || e[55] !== C || e[56] !== E || e[57] !== t || e[58] !== se ? (j = t === Qr.CAPTCHA && (0,
                I.jsxs)(`div`, {
                    className: `text-center`,
                    children: [(0,
                        I.jsx)(`h2`, {
                            className: `text-2xl font-bold mb-4`,
                            children: `Type the words from the image`
                        }), x && E && (0,
                            I.jsx)(`div`, {
                                className: `mb-4`,
                                children: (0,
                                    I.jsx)(`img`, {
                                        src: x,
                                        alt: `Captcha`,
                                        className: `mx-auto border-2 border-gray-300 rounded shadow-lg`,
                                        style: {
                                            maxWidth: `600px`,
                                            width: `100%`
                                        }
                                    })
                            }), (0,
                                I.jsx)(`p`, {
                                    className: `text-gray-600 mb-3 text-sm`,
                                    children: `Type all 25 words separated by spaces`
                                }), (0,
                                    I.jsx)(`textarea`, {
                                        value: C,
                                        onChange: e => w(e.target.value.toLowerCase()),
                                        className: `w-full max-w-2xl px-4 py-3 border-2 border-gray-300 rounded-lg focus:outline-none focus:border-primary-500 mb-4 font-mono`,
                                        placeholder: `Type the words here, separated by spaces...`,
                                        rows: 4,
                                        autoFocus: !0
                                    }), (0,
                                        I.jsxs)(`div`, {
                                            className: `text-gray-600 text-sm mb-4`,
                                            children: [`Words typed: `, C.trim().split(/\s+/).filter(ti).length, ` / 25`]
                                        }), (0,
                                            I.jsx)(`div`, {
                                                children: (0,
                                                    I.jsx)(`button`, {
                                                        onClick: se,
                                                        className: `game-button`,
                                                        children: `Submit`
                                                    })
                                            })]
                }),
                e[54] = x,
                e[55] = C,
                e[56] = E,
                e[57] = t,
                e[58] = se,
                e[59] = j) : j = e[59],
            M = t === Qr.TYPING && (0,
                I.jsxs)(`div`, {
                    children: [(0,
                        I.jsxs)(`div`, {
                            className: `flex justify-around mb-6`,
                            children: [(0,
                                I.jsxs)(`div`, {
                                    className: `text-center`,
                                    children: [(0,
                                        I.jsx)(`div`, {
                                            className: `text-gray-600`,
                                            children: `WPM`
                                        }), (0,
                                            I.jsx)(`div`, {
                                                className: `text-3xl font-bold text-primary-600`,
                                                children: a()
                                            })]
                                }), (0,
                                    I.jsxs)(`div`, {
                                        className: `text-center`,
                                        children: [(0,
                                            I.jsx)(`div`, {
                                                className: `text-gray-600`,
                                                children: `Accuracy`
                                            }), (0,
                                                I.jsxs)(`div`, {
                                                    className: `text-3xl font-bold text-gray-900`,
                                                    children: [l(), `%`]
                                                })]
                                    }), (0,
                                        I.jsxs)(`div`, {
                                            className: `text-center`,
                                            children: [(0,
                                                I.jsx)(`div`, {
                                                    className: `text-gray-600`,
                                                    children: `Progress`
                                                }), (0,
                                                    I.jsxs)(`div`, {
                                                        className: `text-3xl font-bold text-gray-900`,
                                                        children: [o, `/`, i.length]
                                                    })]
                                        })]
                        }), n(), (0,
                            I.jsxs)(`div`, {
                                className: `text-sm text-gray-600 text-center`,
                                children: [`Click the text area above and start typing. Press `, (0,
                                    I.jsx)(`kbd`, {
                                        className: `px-2 py-1 bg-gray-200 rounded`,
                                        children: `Space`
                                    }), ` to move to the next word.`]
                            })]
                }),
            e[19] = x,
            e[20] = C,
            e[21] = p,
            e[22] = c,
            e[23] = o,
            e[24] = k,
            e[25] = te,
            e[26] = E,
            e[27] = A,
            e[28] = u,
            e[29] = t,
            e[30] = se,
            e[31] = i,
            e[32] = j,
            e[33] = M,
            e[34] = N,
            e[35] = P,
            e[36] = ue,
            e[37] = F,
            e[38] = de,
            e[39] = fe
    } else
        j = e[32],
            M = e[33],
            N = e[34],
            P = e[35],
            ue = e[36],
            F = e[37],
            de = e[38],
            fe = e[39];
    let pe;
    e[60] !== t || e[61] !== y ? (pe = t === Qr.RESULT && (0,
        I.jsxs)(`div`, {
            className: `text-center`,
            children: [(0,
                I.jsx)(`h2`, {
                    className: `text-3xl font-bold mb-6`,
                    children: `Test Complete!`
                }), y ? (0,
                    I.jsxs)(`div`, {
                        className: `grid grid-cols-3 gap-4 max-w-2xl mx-auto mb-6`,
                        children: [(0,
                            I.jsxs)(`div`, {
                                className: `card bg-primary-50`,
                                children: [(0,
                                    I.jsx)(`div`, {
                                        className: `text-gray-600 mb-2 text-sm`,
                                        children: `Adjusted WPM`
                                    }), (0,
                                        I.jsx)(`div`, {
                                            className: `text-3xl font-bold text-primary-600`,
                                            children: y.wpm
                                        })]
                            }), (0,
                                I.jsxs)(`div`, {
                                    className: `card bg-gray-50`,
                                    children: [(0,
                                        I.jsx)(`div`, {
                                            className: `text-gray-600 mb-2 text-sm`,
                                            children: `Raw WPM`
                                        }), (0,
                                            I.jsx)(`div`, {
                                                className: `text-3xl font-bold text-gray-900`,
                                                children: y.rawWpm
                                            })]
                                }), (0,
                                    I.jsxs)(`div`, {
                                        className: `card bg-gray-50`,
                                        children: [(0,
                                            I.jsx)(`div`, {
                                                className: `text-gray-600 mb-2 text-sm`,
                                                children: `Accuracy`
                                            }), (0,
                                                I.jsxs)(`div`, {
                                                    className: `text-3xl font-bold text-gray-900`,
                                                    children: [y.accuracy, `%`]
                                                })]
                                    })]
                    }) : (0,
                        I.jsx)(`p`, {
                            className: `text-2xl text-gray-600 mb-6`,
                            children: `Validating...`
                        }), (0,
                            I.jsx)(`button`, {
                                onClick: oe,
                                className: `game-button`,
                                children: `Try Again`
                            })]
        }),
        e[60] = t,
        e[61] = y,
        e[62] = pe) : pe = e[62];
    let me;
    e[63] !== j || e[64] !== M || e[65] !== pe || e[66] !== ue || e[67] !== F || e[68] !== de || e[69] !== fe ? (me = (0,
        I.jsxs)(`div`, {
            className: ue,
            children: [F, de, fe, j, M, pe]
        }),
        e[63] = j,
        e[64] = M,
        e[65] = pe,
        e[66] = ue,
        e[67] = F,
        e[68] = de,
        e[69] = fe,
        e[70] = me) : me = e[70];
    let he;
    e[71] === Symbol.for(`react.memo_cache_sentinel`) ? (he = (0,
        I.jsx)(Pr, {
            gameType: `typing`,
            formatScore: ei
        }),
        e[71] = he) : he = e[71];
    let ge;
    return e[72] !== N || e[73] !== P || e[74] !== me ? (ge = (0,
        I.jsxs)(`div`, {
            className: N,
            children: [P, me, he]
        }),
        e[72] = N,
        e[73] = P,
        e[74] = me,
        e[75] = ge) : ge = e[75],
        ge
}
function ei(e) {
    return `${e} WPM`
}
function ti(e) {
    return e
}
function ni(e) {
    return e.correct
}
function ri(e, t) {
    return e + t.typed.length
}
function ii(e, t) {
    return (0,
        I.jsx)(`span`, {
            className: `text-gray-400`,
            children: e
        }, `remaining-${t}`)
}
function ai(e, t) {
    return e.type === `correct` ? (0,
        I.jsx)(`span`, {
            className: `text-green-600`,
            children: e.char
        }, t) : e.type === `wrong` ? (0,
            I.jsx)(`span`, {
                className: `text-red-600 line-through`,
                children: e.char
            }, t) : e.type === `extra` ? (0,
                I.jsx)(`span`, {
                    className: `text-orange-600 line-through font-bold`,
                    children: e.char
                }, t) : e.type === `missing` ? (0,
                    I.jsx)(`span`, {
                        className: `text-gray-500`,
                        children: e.char
                    }, t) : null
}
function oi(e) {
    let t = e.lastIndexOf(` `);
    return t >= 0 ? e.slice(0, t + 1) : ``
}
function si(e) {
    return e.slice(0, -1)
}
function ci(e) {
    return e.slice(0, -1)
}
var li = [{
    id: `reaction-time`,
    name: `Reaction Time`,
    formatScore: e => `${e.score} ms`
}, {
    id: `chimp-test`,
    name: `Chimp Test`,
    formatScore: e => `Level ${e.score} (${(e.time / 1e3).toFixed(2)}s)`
}, {
    id: `aim-trainer`,
    name: `Aim Trainer`,
    formatScore: e => `${e.score} hits`
}, {
    id: `typing`,
    name: `Typing Test`,
    formatScore: e => `${e.score} WPM`
}];
function ui() {
    let [e, t] = (0,
        L.useState)({})
        , [n, r] = (0,
            L.useState)(!1)
        , [i, a] = (0,
            L.useState)(Ar())
        , [o, s] = (0,
            L.useState)(!1)
        , [c, l] = (0,
            L.useState)(null)
        , u = kr();
    (0,
        L.useEffect)(() => {
            d();
            let e = setInterval(() => {
                d()
            }
                , 2e3);
            return () => clearInterval(e)
        }
            , []);
    async function d() {
        let e = {}
            , n = null;
        for (let t of li)
            try {
                let r = await (await fetch(`/api/leaderboard/${t.id}?userId=${u}`)).json()
                    , i = r.entries || r
                    , a = r.flag;
                e[t.id] = i,
                    a && (n = a)
            } catch (e) {
                console.error(`Failed to fetch ${t.id} leaderboard:`, e)
            }
        t(e),
            n ? (l(n),
                s(!0)) : (l(null),
                    s(!1))
    }
    async function f() {
        if (i.trim()) {
            jr(i.trim()),
                r(!1);
            try {
                await fetch(`/api/update-name`, {
                    method: `POST`,
                    headers: {
                        "Content-Type": `application/json`
                    },
                    body: JSON.stringify({
                        userId: u,
                        name: i.trim()
                    })
                }),
                    d()
            } catch (e) {
                console.error(`Failed to update name:`, e)
            }
        }
    }
    function p(t) {
        let n = li.find(e => e.id === t)
            , r = e[t] || [];
        return (0,
            I.jsxs)(`div`, {
                className: `card mb-6`,
                children: [(0,
                    I.jsx)(`h2`, {
                        className: `text-2xl font-bold mb-4`,
                        children: n.name
                    }), (0,
                        I.jsx)(`div`, {
                            className: `overflow-x-auto`,
                            children: (0,
                                I.jsxs)(`table`, {
                                    className: `w-full`,
                                    children: [(0,
                                        I.jsx)(`thead`, {
                                            children: (0,
                                                I.jsxs)(`tr`, {
                                                    className: `border-b border-gray-200`,
                                                    children: [(0,
                                                        I.jsx)(`th`, {
                                                            className: `text-left py-2 px-4 text-gray-600 font-semibold`,
                                                            children: `Rank`
                                                        }), (0,
                                                            I.jsx)(`th`, {
                                                                className: `text-left py-2 px-4 text-gray-600 font-semibold`,
                                                                children: `Player`
                                                            }), (0,
                                                                I.jsx)(`th`, {
                                                                    className: `text-right py-2 px-4 text-gray-600 font-semibold`,
                                                                    children: `Score`
                                                                })]
                                                })
                                        }), (0,
                                            I.jsx)(`tbody`, {
                                                children: r.length === 0 ? (0,
                                                    I.jsx)(`tr`, {
                                                        children: (0,
                                                            I.jsx)(`td`, {
                                                                className: `text-center py-8 text-gray-500`,
                                                                children: `No scores yet`
                                                            })
                                                    }) : r.map((e, t) => (0,
                                                        I.jsxs)(`tr`, {
                                                            className: `border-b border-gray-100 ${e.userId === u ? `bg-primary-50` : ``}`,
                                                            children: [(0,
                                                                I.jsx)(`td`, {
                                                                    className: `py-3 px-4`,
                                                                    children: (0,
                                                                        I.jsx)(`span`, {
                                                                            className: `font-bold ${t === 0 ? `text-yellow-500` : t === 1 ? `text-gray-400` : t === 2 ? `text-orange-600` : `text-gray-600`}`,
                                                                            children: t === 0 ? `🥇` : t === 1 ? `🥈` : t === 2 ? `🥉` : `#${t + 1}`
                                                                        })
                                                                }), (0,
                                                                    I.jsx)(`td`, {
                                                                        className: `py-3 px-4`,
                                                                        children: (0,
                                                                            I.jsxs)(`span`, {
                                                                                className: e.userId === u ? `font-bold` : ``,
                                                                                children: [e.name, e.userId === u && ` (You)`]
                                                                            })
                                                                    }), (0,
                                                                        I.jsx)(`td`, {
                                                                            className: `py-3 px-4 text-right font-semibold`,
                                                                            children: n.formatScore(e)
                                                                        })]
                                                        }, e.userId))
                                            })]
                                })
                        })]
            }, t)
    }
    return (0,
        I.jsxs)(`div`, {
            className: `game-container`,
            children: [(0,
                I.jsx)(`div`, {
                    className: `mb-4`,
                    children: (0,
                        I.jsx)(Xn, {
                            to: `/`,
                            className: `text-primary-600 hover:text-primary-700`,
                            children: `← Back to Home`
                        })
                }), o && c && (0,
                    I.jsx)(`div`, {
                        className: `card mb-6 bg-gradient-to-r from-yellow-400 to-yellow-300 border-4 border-yellow-500`,
                        children: (0,
                            I.jsxs)(`div`, {
                                className: `text-center`,
                                children: [(0,
                                    I.jsx)(`h2`, {
                                        className: `text-3xl font-bold text-gray-900 mb-4`,
                                        children: `🏆 You win! 🏆`
                                    }), (0,
                                        I.jsx)(`div`, {
                                            className: `bg-black text-green-400 font-mono text-lg p-4 rounded-lg inline-block mt-4`,
                                            children: c
                                        })]
                            })
                    }), (0,
                        I.jsxs)(`div`, {
                            className: `card mb-6`,
                            children: [(0,
                                I.jsx)(`h1`, {
                                    className: `text-4xl font-bold text-center mb-4`,
                                    children: `Leaderboards`
                                }), n ? (0,
                                    I.jsxs)(`div`, {
                                        className: `flex gap-2 justify-center`,
                                        children: [(0,
                                            I.jsx)(`input`, {
                                                type: `text`,
                                                value: i,
                                                onChange: e => a(e.target.value),
                                                onKeyDown: e => e.key === `Enter` && f(),
                                                className: `flex-1 max-w-xs px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-primary-500`,
                                                placeholder: `Enter your name`,
                                                maxLength: 20
                                            }), (0,
                                                I.jsx)(`button`, {
                                                    onClick: f,
                                                    className: `game-button`,
                                                    children: `Save`
                                                }), (0,
                                                    I.jsx)(`button`, {
                                                        onClick: () => {
                                                            r(!1),
                                                                a(Ar())
                                                        }
                                                        ,
                                                        className: `px-4 py-2 bg-gray-200 rounded-lg hover:bg-gray-300`,
                                                        children: `Cancel`
                                                    })]
                                    }) : (0,
                                        I.jsxs)(`div`, {
                                            className: `flex justify-center items-center gap-4`,
                                            children: [(0,
                                                I.jsxs)(`span`, {
                                                    className: `text-gray-600`,
                                                    children: [`Playing as: `, (0,
                                                        I.jsx)(`strong`, {
                                                            children: Ar()
                                                        })]
                                                }), (0,
                                                    I.jsx)(`button`, {
                                                        onClick: () => r(!0),
                                                        className: `text-primary-600 hover:text-primary-700 text-sm`,
                                                        children: `Change Name`
                                                    })]
                                        })]
                        }), li.map(e => p(e.id))]
        })
}
var di = tr({
    component: Er
})
    , fi = $n({
        getParentRoute: () => di,
        path: `/`,
        component: Nr
    })
    , pi = $n({
        getParentRoute: () => di,
        path: `/reaction-time`,
        component: Lr
    })
    , mi = $n({
        getParentRoute: () => di,
        path: `/chimp-test`,
        component: Wr
    })
    , hi = $n({
        getParentRoute: () => di,
        path: `/aim-trainer`,
        component: Xr
    })
    , gi = $n({
        getParentRoute: () => di,
        path: `/typing`,
        component: $r
    })
    , _i = $n({
        getParentRoute: () => di,
        path: `/leaderboard`,
        component: ui
    })
    , vi = di.addChildren([fi, pi, mi, hi, gi, _i]);
const yi = yr({
    routeTree: vi
});
(0,
    g.createRoot)(document.getElementById(`root`)).render((0,
        I.jsx)(Sr, {
            router: yi
        }));
