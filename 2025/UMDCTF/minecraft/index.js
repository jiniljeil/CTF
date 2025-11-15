(function() {
    const o = document.createElement("link").relList;
    if (o && o.supports && o.supports("modulepreload"))
        return;
    for (const d of document.querySelectorAll('link[rel="modulepreload"]'))
        f(d);
    new MutationObserver(d => {
        for (const v of d)
            if (v.type === "childList")
                for (const x of v.addedNodes)
                    x.tagName === "LINK" && x.rel === "modulepreload" && f(x)
    }
    ).observe(document, {
        childList: !0,
        subtree: !0
    });
    function s(d) {
        const v = {};
        return d.integrity && (v.integrity = d.integrity),
        d.referrerPolicy && (v.referrerPolicy = d.referrerPolicy),
        d.crossOrigin === "use-credentials" ? v.credentials = "include" : d.crossOrigin === "anonymous" ? v.credentials = "omit" : v.credentials = "same-origin",
        v
    }
    function f(d) {
        if (d.ep)
            return;
        d.ep = !0;
        const v = s(d);
        fetch(d.href, v)
    }
}
)();
var sr = {
    exports: {}
}
  , Tu = {};
/**
 * @license React
 * react-jsx-runtime.production.js
 *
 * Copyright (c) Meta Platforms, Inc. and affiliates.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
var hm;
function gv() {
    if (hm)
        return Tu;
    hm = 1;
    var i = Symbol.for("react.transitional.element")
      , o = Symbol.for("react.fragment");
    function s(f, d, v) {
        var x = null;
        if (v !== void 0 && (x = "" + v),
        d.key !== void 0 && (x = "" + d.key),
        "key"in d) {
            v = {};
            for (var D in d)
                D !== "key" && (v[D] = d[D])
        } else
            v = d;
        return d = v.ref,
        {
            $$typeof: i,
            type: f,
            key: x,
            ref: d !== void 0 ? d : null,
            props: v
        }
    }
    return Tu.Fragment = o,
    Tu.jsx = s,
    Tu.jsxs = s,
    Tu
}
var ym;
function pv() {
    return ym || (ym = 1,
    sr.exports = gv()),
    sr.exports
}
var w = pv()
  , dr = {
    exports: {}
}
  , ct = {};
/**
 * @license React
 * react.production.js
 *
 * Copyright (c) Meta Platforms, Inc. and affiliates.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
var vm;
function Sv() {
    if (vm)
        return ct;
    vm = 1;
    var i = Symbol.for("react.transitional.element")
      , o = Symbol.for("react.portal")
      , s = Symbol.for("react.fragment")
      , f = Symbol.for("react.strict_mode")
      , d = Symbol.for("react.profiler")
      , v = Symbol.for("react.consumer")
      , x = Symbol.for("react.context")
      , D = Symbol.for("react.forward_ref")
      , S = Symbol.for("react.suspense")
      , h = Symbol.for("react.memo")
      , R = Symbol.for("react.lazy")
      , Y = Symbol.iterator;
    function C(y) {
        return y === null || typeof y != "object" ? null : (y = Y && y[Y] || y["@@iterator"],
        typeof y == "function" ? y : null)
    }
    var G = {
        isMounted: function() {
            return !1
        },
        enqueueForceUpdate: function() {},
        enqueueReplaceState: function() {},
        enqueueSetState: function() {}
    }
      , H = Object.assign
      , k = {};
    function Z(y, z, V) {
        this.props = y,
        this.context = z,
        this.refs = k,
        this.updater = V || G
    }
    Z.prototype.isReactComponent = {},
    Z.prototype.setState = function(y, z) {
        if (typeof y != "object" && typeof y != "function" && y != null)
            throw Error("takes an object of state variables to update or a function which returns an object of state variables.");
        this.updater.enqueueSetState(this, y, z, "setState")
    }
    ,
    Z.prototype.forceUpdate = function(y) {
        this.updater.enqueueForceUpdate(this, y, "forceUpdate")
    }
    ;
    function q() {}
    q.prototype = Z.prototype;
    function et(y, z, V) {
        this.props = y,
        this.context = z,
        this.refs = k,
        this.updater = V || G
    }
    var Q = et.prototype = new q;
    Q.constructor = et,
    H(Q, Z.prototype),
    Q.isPureReactComponent = !0;
    var ut = Array.isArray
      , P = {
        H: null,
        A: null,
        T: null,
        S: null,
        V: null
    }
      , Mt = Object.prototype.hasOwnProperty;
    function Nt(y, z, V, j, F, I) {
        return V = I.ref,
        {
            $$typeof: i,
            type: y,
            key: z,
            ref: V !== void 0 ? V : null,
            props: I
        }
    }
    function Ut(y, z) {
        return Nt(y.type, z, void 0, void 0, void 0, y.props)
    }
    function xt(y) {
        return typeof y == "object" && y !== null && y.$$typeof === i
    }
    function Pt(y) {
        var z = {
            "=": "=0",
            ":": "=2"
        };
        return "$" + y.replace(/[=:]/g, function(V) {
            return z[V]
        })
    }
    var St = /\/+/g;
    function qt(y, z) {
        return typeof y == "object" && y !== null && y.key != null ? Pt("" + y.key) : z.toString(36)
    }
    function Qe() {}
    function Ze(y) {
        switch (y.status) {
        case "fulfilled":
            return y.value;
        case "rejected":
            throw y.reason;
        default:
            switch (typeof y.status == "string" ? y.then(Qe, Qe) : (y.status = "pending",
            y.then(function(z) {
                y.status === "pending" && (y.status = "fulfilled",
                y.value = z)
            }, function(z) {
                y.status === "pending" && (y.status = "rejected",
                y.reason = z)
            })),
            y.status) {
            case "fulfilled":
                return y.value;
            case "rejected":
                throw y.reason
            }
        }
        throw y
    }
    function $t(y, z, V, j, F) {
        var I = typeof y;
        (I === "undefined" || I === "boolean") && (y = null);
        var at = !1;
        if (y === null)
            at = !0;
        else
            switch (I) {
            case "bigint":
            case "string":
            case "number":
                at = !0;
                break;
            case "object":
                switch (y.$$typeof) {
                case i:
                case o:
                    at = !0;
                    break;
                case R:
                    return at = y._init,
                    $t(at(y._payload), z, V, j, F)
                }
            }
        if (at)
            return F = F(y),
            at = j === "" ? "." + qt(y, 0) : j,
            ut(F) ? (V = "",
            at != null && (V = at.replace(St, "$&/") + "/"),
            $t(F, z, V, "", function(ze) {
                return ze
            })) : F != null && (xt(F) && (F = Ut(F, V + (F.key == null || y && y.key === F.key ? "" : ("" + F.key).replace(St, "$&/") + "/") + at)),
            z.push(F)),
            1;
        at = 0;
        var te = j === "" ? "." : j + ":";
        if (ut(y))
            for (var At = 0; At < y.length; At++)
                j = y[At],
                I = te + qt(j, At),
                at += $t(j, z, V, I, F);
        else if (At = C(y),
        typeof At == "function")
            for (y = At.call(y),
            At = 0; !(j = y.next()).done; )
                j = j.value,
                I = te + qt(j, At++),
                at += $t(j, z, V, I, F);
        else if (I === "object") {
            if (typeof y.then == "function")
                return $t(Ze(y), z, V, j, F);
            throw z = String(y),
            Error("Objects are not valid as a React child (found: " + (z === "[object Object]" ? "object with keys {" + Object.keys(y).join(", ") + "}" : z) + "). If you meant to render a collection of children, use an array instead.")
        }
        return at
    }
    function M(y, z, V) {
        if (y == null)
            return y;
        var j = []
          , F = 0;
        return $t(y, j, "", "", function(I) {
            return z.call(V, I, F++)
        }),
        j
    }
    function X(y) {
        if (y._status === -1) {
            var z = y._result;
            z = z(),
            z.then(function(V) {
                (y._status === 0 || y._status === -1) && (y._status = 1,
                y._result = V)
            }, function(V) {
                (y._status === 0 || y._status === -1) && (y._status = 2,
                y._result = V)
            }),
            y._status === -1 && (y._status = 0,
            y._result = z)
        }
        if (y._status === 1)
            return y._result.default;
        throw y._result
    }
    var tt = typeof reportError == "function" ? reportError : function(y) {
        if (typeof window == "object" && typeof window.ErrorEvent == "function") {
            var z = new window.ErrorEvent("error",{
                bubbles: !0,
                cancelable: !0,
                message: typeof y == "object" && y !== null && typeof y.message == "string" ? String(y.message) : String(y),
                error: y
            });
            if (!window.dispatchEvent(z))
                return
        } else if (typeof process == "object" && typeof process.emit == "function") {
            process.emit("uncaughtException", y);
            return
        }
        console.error(y)
    }
    ;
    function vt() {}
    return ct.Children = {
        map: M,
        forEach: function(y, z, V) {
            M(y, function() {
                z.apply(this, arguments)
            }, V)
        },
        count: function(y) {
            var z = 0;
            return M(y, function() {
                z++
            }),
            z
        },
        toArray: function(y) {
            return M(y, function(z) {
                return z
            }) || []
        },
        only: function(y) {
            if (!xt(y))
                throw Error("React.Children.only expected to receive a single React element child.");
            return y
        }
    },
    ct.Component = Z,
    ct.Fragment = s,
    ct.Profiler = d,
    ct.PureComponent = et,
    ct.StrictMode = f,
    ct.Suspense = S,
    ct.__CLIENT_INTERNALS_DO_NOT_USE_OR_WARN_USERS_THEY_CANNOT_UPGRADE = P,
    ct.__COMPILER_RUNTIME = {
        __proto__: null,
        c: function(y) {
            return P.H.useMemoCache(y)
        }
    },
    ct.cache = function(y) {
        return function() {
            return y.apply(null, arguments)
        }
    }
    ,
    ct.cloneElement = function(y, z, V) {
        if (y == null)
            throw Error("The argument must be a React element, but you passed " + y + ".");
        var j = H({}, y.props)
          , F = y.key
          , I = void 0;
        if (z != null)
            for (at in z.ref !== void 0 && (I = void 0),
            z.key !== void 0 && (F = "" + z.key),
            z)
                !Mt.call(z, at) || at === "key" || at === "__self" || at === "__source" || at === "ref" && z.ref === void 0 || (j[at] = z[at]);
        var at = arguments.length - 2;
        if (at === 1)
            j.children = V;
        else if (1 < at) {
            for (var te = Array(at), At = 0; At < at; At++)
                te[At] = arguments[At + 2];
            j.children = te
        }
        return Nt(y.type, F, void 0, void 0, I, j)
    }
    ,
    ct.createContext = function(y) {
        return y = {
            $$typeof: x,
            _currentValue: y,
            _currentValue2: y,
            _threadCount: 0,
            Provider: null,
            Consumer: null
        },
        y.Provider = y,
        y.Consumer = {
            $$typeof: v,
            _context: y
        },
        y
    }
    ,
    ct.createElement = function(y, z, V) {
        var j, F = {}, I = null;
        if (z != null)
            for (j in z.key !== void 0 && (I = "" + z.key),
            z)
                Mt.call(z, j) && j !== "key" && j !== "__self" && j !== "__source" && (F[j] = z[j]);
        var at = arguments.length - 2;
        if (at === 1)
            F.children = V;
        else if (1 < at) {
            for (var te = Array(at), At = 0; At < at; At++)
                te[At] = arguments[At + 2];
            F.children = te
        }
        if (y && y.defaultProps)
            for (j in at = y.defaultProps,
            at)
                F[j] === void 0 && (F[j] = at[j]);
        return Nt(y, I, void 0, void 0, null, F)
    }
    ,
    ct.createRef = function() {
        return {
            current: null
        }
    }
    ,
    ct.forwardRef = function(y) {
        return {
            $$typeof: D,
            render: y
        }
    }
    ,
    ct.isValidElement = xt,
    ct.lazy = function(y) {
        return {
            $$typeof: R,
            _payload: {
                _status: -1,
                _result: y
            },
            _init: X
        }
    }
    ,
    ct.memo = function(y, z) {
        return {
            $$typeof: h,
            type: y,
            compare: z === void 0 ? null : z
        }
    }
    ,
    ct.startTransition = function(y) {
        var z = P.T
          , V = {};
        P.T = V;
        try {
            var j = y()
              , F = P.S;
            F !== null && F(V, j),
            typeof j == "object" && j !== null && typeof j.then == "function" && j.then(vt, tt)
        } catch (I) {
            tt(I)
        } finally {
            P.T = z
        }
    }
    ,
    ct.unstable_useCacheRefresh = function() {
        return P.H.useCacheRefresh()
    }
    ,
    ct.use = function(y) {
        return P.H.use(y)
    }
    ,
    ct.useActionState = function(y, z, V) {
        return P.H.useActionState(y, z, V)
    }
    ,
    ct.useCallback = function(y, z) {
        return P.H.useCallback(y, z)
    }
    ,
    ct.useContext = function(y) {
        return P.H.useContext(y)
    }
    ,
    ct.useDebugValue = function() {}
    ,
    ct.useDeferredValue = function(y, z) {
        return P.H.useDeferredValue(y, z)
    }
    ,
    ct.useEffect = function(y, z, V) {
        var j = P.H;
        if (typeof V == "function")
            throw Error("useEffect CRUD overload is not enabled in this build of React.");
        return j.useEffect(y, z)
    }
    ,
    ct.useId = function() {
        return P.H.useId()
    }
    ,
    ct.useImperativeHandle = function(y, z, V) {
        return P.H.useImperativeHandle(y, z, V)
    }
    ,
    ct.useInsertionEffect = function(y, z) {
        return P.H.useInsertionEffect(y, z)
    }
    ,
    ct.useLayoutEffect = function(y, z) {
        return P.H.useLayoutEffect(y, z)
    }
    ,
    ct.useMemo = function(y, z) {
        return P.H.useMemo(y, z)
    }
    ,
    ct.useOptimistic = function(y, z) {
        return P.H.useOptimistic(y, z)
    }
    ,
    ct.useReducer = function(y, z, V) {
        return P.H.useReducer(y, z, V)
    }
    ,
    ct.useRef = function(y) {
        return P.H.useRef(y)
    }
    ,
    ct.useState = function(y) {
        return P.H.useState(y)
    }
    ,
    ct.useSyncExternalStore = function(y, z, V) {
        return P.H.useSyncExternalStore(y, z, V)
    }
    ,
    ct.useTransition = function() {
        return P.H.useTransition()
    }
    ,
    ct.version = "19.1.0",
    ct
}
var gm;
function Dr() {
    return gm || (gm = 1,
    dr.exports = Sv()),
    dr.exports
}
var O = Dr()
  , mr = {
    exports: {}
}
  , Au = {}
  , hr = {
    exports: {}
}
  , yr = {};
/**
 * @license React
 * scheduler.production.js
 *
 * Copyright (c) Meta Platforms, Inc. and affiliates.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
var pm;
function bv() {
    return pm || (pm = 1,
    function(i) {
        function o(M, X) {
            var tt = M.length;
            M.push(X);
            t: for (; 0 < tt; ) {
                var vt = tt - 1 >>> 1
                  , y = M[vt];
                if (0 < d(y, X))
                    M[vt] = X,
                    M[tt] = y,
                    tt = vt;
                else
                    break t
            }
        }
        function s(M) {
            return M.length === 0 ? null : M[0]
        }
        function f(M) {
            if (M.length === 0)
                return null;
            var X = M[0]
              , tt = M.pop();
            if (tt !== X) {
                M[0] = tt;
                t: for (var vt = 0, y = M.length, z = y >>> 1; vt < z; ) {
                    var V = 2 * (vt + 1) - 1
                      , j = M[V]
                      , F = V + 1
                      , I = M[F];
                    if (0 > d(j, tt))
                        F < y && 0 > d(I, j) ? (M[vt] = I,
                        M[F] = tt,
                        vt = F) : (M[vt] = j,
                        M[V] = tt,
                        vt = V);
                    else if (F < y && 0 > d(I, tt))
                        M[vt] = I,
                        M[F] = tt,
                        vt = F;
                    else
                        break t
                }
            }
            return X
        }
        function d(M, X) {
            var tt = M.sortIndex - X.sortIndex;
            return tt !== 0 ? tt : M.id - X.id
        }
        if (i.unstable_now = void 0,
        typeof performance == "object" && typeof performance.now == "function") {
            var v = performance;
            i.unstable_now = function() {
                return v.now()
            }
        } else {
            var x = Date
              , D = x.now();
            i.unstable_now = function() {
                return x.now() - D
            }
        }
        var S = []
          , h = []
          , R = 1
          , Y = null
          , C = 3
          , G = !1
          , H = !1
          , k = !1
          , Z = !1
          , q = typeof setTimeout == "function" ? setTimeout : null
          , et = typeof clearTimeout == "function" ? clearTimeout : null
          , Q = typeof setImmediate < "u" ? setImmediate : null;
        function ut(M) {
            for (var X = s(h); X !== null; ) {
                if (X.callback === null)
                    f(h);
                else if (X.startTime <= M)
                    f(h),
                    X.sortIndex = X.expirationTime,
                    o(S, X);
                else
                    break;
                X = s(h)
            }
        }
        function P(M) {
            if (k = !1,
            ut(M),
            !H)
                if (s(S) !== null)
                    H = !0,
                    Mt || (Mt = !0,
                    qt());
                else {
                    var X = s(h);
                    X !== null && $t(P, X.startTime - M)
                }
        }
        var Mt = !1
          , Nt = -1
          , Ut = 5
          , xt = -1;
        function Pt() {
            return Z ? !0 : !(i.unstable_now() - xt < Ut)
        }
        function St() {
            if (Z = !1,
            Mt) {
                var M = i.unstable_now();
                xt = M;
                var X = !0;
                try {
                    t: {
                        H = !1,
                        k && (k = !1,
                        et(Nt),
                        Nt = -1),
                        G = !0;
                        var tt = C;
                        try {
                            e: {
                                for (ut(M),
                                Y = s(S); Y !== null && !(Y.expirationTime > M && Pt()); ) {
                                    var vt = Y.callback;
                                    if (typeof vt == "function") {
                                        Y.callback = null,
                                        C = Y.priorityLevel;
                                        var y = vt(Y.expirationTime <= M);
                                        if (M = i.unstable_now(),
                                        typeof y == "function") {
                                            Y.callback = y,
                                            ut(M),
                                            X = !0;
                                            break e
                                        }
                                        Y === s(S) && f(S),
                                        ut(M)
                                    } else
                                        f(S);
                                    Y = s(S)
                                }
                                if (Y !== null)
                                    X = !0;
                                else {
                                    var z = s(h);
                                    z !== null && $t(P, z.startTime - M),
                                    X = !1
                                }
                            }
                            break t
                        } finally {
                            Y = null,
                            C = tt,
                            G = !1
                        }
                        X = void 0
                    }
                } finally {
                    X ? qt() : Mt = !1
                }
            }
        }
        var qt;
        if (typeof Q == "function")
            qt = function() {
                Q(St)
            }
            ;
        else if (typeof MessageChannel < "u") {
            var Qe = new MessageChannel
              , Ze = Qe.port2;
            Qe.port1.onmessage = St,
            qt = function() {
                Ze.postMessage(null)
            }
        } else
            qt = function() {
                q(St, 0)
            }
            ;
        function $t(M, X) {
            Nt = q(function() {
                M(i.unstable_now())
            }, X)
        }
        i.unstable_IdlePriority = 5,
        i.unstable_ImmediatePriority = 1,
        i.unstable_LowPriority = 4,
        i.unstable_NormalPriority = 3,
        i.unstable_Profiling = null,
        i.unstable_UserBlockingPriority = 2,
        i.unstable_cancelCallback = function(M) {
            M.callback = null
        }
        ,
        i.unstable_forceFrameRate = function(M) {
            0 > M || 125 < M ? console.error("forceFrameRate takes a positive int between 0 and 125, forcing frame rates higher than 125 fps is not supported") : Ut = 0 < M ? Math.floor(1e3 / M) : 5
        }
        ,
        i.unstable_getCurrentPriorityLevel = function() {
            return C
        }
        ,
        i.unstable_next = function(M) {
            switch (C) {
            case 1:
            case 2:
            case 3:
                var X = 3;
                break;
            default:
                X = C
            }
            var tt = C;
            C = X;
            try {
                return M()
            } finally {
                C = tt
            }
        }
        ,
        i.unstable_requestPaint = function() {
            Z = !0
        }
        ,
        i.unstable_runWithPriority = function(M, X) {
            switch (M) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                break;
            default:
                M = 3
            }
            var tt = C;
            C = M;
            try {
                return X()
            } finally {
                C = tt
            }
        }
        ,
        i.unstable_scheduleCallback = function(M, X, tt) {
            var vt = i.unstable_now();
            switch (typeof tt == "object" && tt !== null ? (tt = tt.delay,
            tt = typeof tt == "number" && 0 < tt ? vt + tt : vt) : tt = vt,
            M) {
            case 1:
                var y = -1;
                break;
            case 2:
                y = 250;
                break;
            case 5:
                y = 1073741823;
                break;
            case 4:
                y = 1e4;
                break;
            default:
                y = 5e3
            }
            return y = tt + y,
            M = {
                id: R++,
                callback: X,
                priorityLevel: M,
                startTime: tt,
                expirationTime: y,
                sortIndex: -1
            },
            tt > vt ? (M.sortIndex = tt,
            o(h, M),
            s(S) === null && M === s(h) && (k ? (et(Nt),
            Nt = -1) : k = !0,
            $t(P, tt - vt))) : (M.sortIndex = y,
            o(S, M),
            H || G || (H = !0,
            Mt || (Mt = !0,
            qt()))),
            M
        }
        ,
        i.unstable_shouldYield = Pt,
        i.unstable_wrapCallback = function(M) {
            var X = C;
            return function() {
                var tt = C;
                C = X;
                try {
                    return M.apply(this, arguments)
                } finally {
                    C = tt
                }
            }
        }
    }(yr)),
    yr
}
var Sm;
function Ev() {
    return Sm || (Sm = 1,
    hr.exports = bv()),
    hr.exports
}
var vr = {
    exports: {}
}
  , se = {};
/**
 * @license React
 * react-dom.production.js
 *
 * Copyright (c) Meta Platforms, Inc. and affiliates.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
var bm;
function Tv() {
    if (bm)
        return se;
    bm = 1;
    var i = Dr();
    function o(S) {
        var h = "https://react.dev/errors/" + S;
        if (1 < arguments.length) {
            h += "?args[]=" + encodeURIComponent(arguments[1]);
            for (var R = 2; R < arguments.length; R++)
                h += "&args[]=" + encodeURIComponent(arguments[R])
        }
        return "Minified React error #" + S + "; visit " + h + " for the full message or use the non-minified dev environment for full errors and additional helpful warnings."
    }
    function s() {}
    var f = {
        d: {
            f: s,
            r: function() {
                throw Error(o(522))
            },
            D: s,
            C: s,
            L: s,
            m: s,
            X: s,
            S: s,
            M: s
        },
        p: 0,
        findDOMNode: null
    }
      , d = Symbol.for("react.portal");
    function v(S, h, R) {
        var Y = 3 < arguments.length && arguments[3] !== void 0 ? arguments[3] : null;
        return {
            $$typeof: d,
            key: Y == null ? null : "" + Y,
            children: S,
            containerInfo: h,
            implementation: R
        }
    }
    var x = i.__CLIENT_INTERNALS_DO_NOT_USE_OR_WARN_USERS_THEY_CANNOT_UPGRADE;
    function D(S, h) {
        if (S === "font")
            return "";
        if (typeof h == "string")
            return h === "use-credentials" ? h : ""
    }
    return se.__DOM_INTERNALS_DO_NOT_USE_OR_WARN_USERS_THEY_CANNOT_UPGRADE = f,
    se.createPortal = function(S, h) {
        var R = 2 < arguments.length && arguments[2] !== void 0 ? arguments[2] : null;
        if (!h || h.nodeType !== 1 && h.nodeType !== 9 && h.nodeType !== 11)
            throw Error(o(299));
        return v(S, h, null, R)
    }
    ,
    se.flushSync = function(S) {
        var h = x.T
          , R = f.p;
        try {
            if (x.T = null,
            f.p = 2,
            S)
                return S()
        } finally {
            x.T = h,
            f.p = R,
            f.d.f()
        }
    }
    ,
    se.preconnect = function(S, h) {
        typeof S == "string" && (h ? (h = h.crossOrigin,
        h = typeof h == "string" ? h === "use-credentials" ? h : "" : void 0) : h = null,
        f.d.C(S, h))
    }
    ,
    se.prefetchDNS = function(S) {
        typeof S == "string" && f.d.D(S)
    }
    ,
    se.preinit = function(S, h) {
        if (typeof S == "string" && h && typeof h.as == "string") {
            var R = h.as
              , Y = D(R, h.crossOrigin)
              , C = typeof h.integrity == "string" ? h.integrity : void 0
              , G = typeof h.fetchPriority == "string" ? h.fetchPriority : void 0;
            R === "style" ? f.d.S(S, typeof h.precedence == "string" ? h.precedence : void 0, {
                crossOrigin: Y,
                integrity: C,
                fetchPriority: G
            }) : R === "script" && f.d.X(S, {
                crossOrigin: Y,
                integrity: C,
                fetchPriority: G,
                nonce: typeof h.nonce == "string" ? h.nonce : void 0
            })
        }
    }
    ,
    se.preinitModule = function(S, h) {
        if (typeof S == "string")
            if (typeof h == "object" && h !== null) {
                if (h.as == null || h.as === "script") {
                    var R = D(h.as, h.crossOrigin);
                    f.d.M(S, {
                        crossOrigin: R,
                        integrity: typeof h.integrity == "string" ? h.integrity : void 0,
                        nonce: typeof h.nonce == "string" ? h.nonce : void 0
                    })
                }
            } else
                h == null && f.d.M(S)
    }
    ,
    se.preload = function(S, h) {
        if (typeof S == "string" && typeof h == "object" && h !== null && typeof h.as == "string") {
            var R = h.as
              , Y = D(R, h.crossOrigin);
            f.d.L(S, R, {
                crossOrigin: Y,
                integrity: typeof h.integrity == "string" ? h.integrity : void 0,
                nonce: typeof h.nonce == "string" ? h.nonce : void 0,
                type: typeof h.type == "string" ? h.type : void 0,
                fetchPriority: typeof h.fetchPriority == "string" ? h.fetchPriority : void 0,
                referrerPolicy: typeof h.referrerPolicy == "string" ? h.referrerPolicy : void 0,
                imageSrcSet: typeof h.imageSrcSet == "string" ? h.imageSrcSet : void 0,
                imageSizes: typeof h.imageSizes == "string" ? h.imageSizes : void 0,
                media: typeof h.media == "string" ? h.media : void 0
            })
        }
    }
    ,
    se.preloadModule = function(S, h) {
        if (typeof S == "string")
            if (h) {
                var R = D(h.as, h.crossOrigin);
                f.d.m(S, {
                    as: typeof h.as == "string" && h.as !== "script" ? h.as : void 0,
                    crossOrigin: R,
                    integrity: typeof h.integrity == "string" ? h.integrity : void 0
                })
            } else
                f.d.m(S)
    }
    ,
    se.requestFormReset = function(S) {
        f.d.r(S)
    }
    ,
    se.unstable_batchedUpdates = function(S, h) {
        return S(h)
    }
    ,
    se.useFormState = function(S, h, R) {
        return x.H.useFormState(S, h, R)
    }
    ,
    se.useFormStatus = function() {
        return x.H.useHostTransitionStatus()
    }
    ,
    se.version = "19.1.0",
    se
}
var Em;
function Av() {
    if (Em)
        return vr.exports;
    Em = 1;
    function i() {
        if (!(typeof __REACT_DEVTOOLS_GLOBAL_HOOK__ > "u" || typeof __REACT_DEVTOOLS_GLOBAL_HOOK__.checkDCE != "function"))
            try {
                __REACT_DEVTOOLS_GLOBAL_HOOK__.checkDCE(i)
            } catch (o) {
                console.error(o)
            }
    }
    return i(),
    vr.exports = Tv(),
    vr.exports
}
/**
 * @license React
 * react-dom-client.production.js
 *
 * Copyright (c) Meta Platforms, Inc. and affiliates.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
var Tm;
function _v() {
    if (Tm)
        return Au;
    Tm = 1;
    var i = Ev()
      , o = Dr()
      , s = Av();
    function f(t) {
        var e = "https://react.dev/errors/" + t;
        if (1 < arguments.length) {
            e += "?args[]=" + encodeURIComponent(arguments[1]);
            for (var l = 2; l < arguments.length; l++)
                e += "&args[]=" + encodeURIComponent(arguments[l])
        }
        return "Minified React error #" + t + "; visit " + e + " for the full message or use the non-minified dev environment for full errors and additional helpful warnings."
    }
    function d(t) {
        return !(!t || t.nodeType !== 1 && t.nodeType !== 9 && t.nodeType !== 11)
    }
    function v(t) {
        var e = t
          , l = t;
        if (t.alternate)
            for (; e.return; )
                e = e.return;
        else {
            t = e;
            do
                e = t,
                (e.flags & 4098) !== 0 && (l = e.return),
                t = e.return;
            while (t)
        }
        return e.tag === 3 ? l : null
    }
    function x(t) {
        if (t.tag === 13) {
            var e = t.memoizedState;
            if (e === null && (t = t.alternate,
            t !== null && (e = t.memoizedState)),
            e !== null)
                return e.dehydrated
        }
        return null
    }
    function D(t) {
        if (v(t) !== t)
            throw Error(f(188))
    }
    function S(t) {
        var e = t.alternate;
        if (!e) {
            if (e = v(t),
            e === null)
                throw Error(f(188));
            return e !== t ? null : t
        }
        for (var l = t, a = e; ; ) {
            var n = l.return;
            if (n === null)
                break;
            var u = n.alternate;
            if (u === null) {
                if (a = n.return,
                a !== null) {
                    l = a;
                    continue
                }
                break
            }
            if (n.child === u.child) {
                for (u = n.child; u; ) {
                    if (u === l)
                        return D(n),
                        t;
                    if (u === a)
                        return D(n),
                        e;
                    u = u.sibling
                }
                throw Error(f(188))
            }
            if (l.return !== a.return)
                l = n,
                a = u;
            else {
                for (var c = !1, r = n.child; r; ) {
                    if (r === l) {
                        c = !0,
                        l = n,
                        a = u;
                        break
                    }
                    if (r === a) {
                        c = !0,
                        a = n,
                        l = u;
                        break
                    }
                    r = r.sibling
                }
                if (!c) {
                    for (r = u.child; r; ) {
                        if (r === l) {
                            c = !0,
                            l = u,
                            a = n;
                            break
                        }
                        if (r === a) {
                            c = !0,
                            a = u,
                            l = n;
                            break
                        }
                        r = r.sibling
                    }
                    if (!c)
                        throw Error(f(189))
                }
            }
            if (l.alternate !== a)
                throw Error(f(190))
        }
        if (l.tag !== 3)
            throw Error(f(188));
        return l.stateNode.current === l ? t : e
    }
    function h(t) {
        var e = t.tag;
        if (e === 5 || e === 26 || e === 27 || e === 6)
            return t;
        for (t = t.child; t !== null; ) {
            if (e = h(t),
            e !== null)
                return e;
            t = t.sibling
        }
        return null
    }
    var R = Object.assign
      , Y = Symbol.for("react.element")
      , C = Symbol.for("react.transitional.element")
      , G = Symbol.for("react.portal")
      , H = Symbol.for("react.fragment")
      , k = Symbol.for("react.strict_mode")
      , Z = Symbol.for("react.profiler")
      , q = Symbol.for("react.provider")
      , et = Symbol.for("react.consumer")
      , Q = Symbol.for("react.context")
      , ut = Symbol.for("react.forward_ref")
      , P = Symbol.for("react.suspense")
      , Mt = Symbol.for("react.suspense_list")
      , Nt = Symbol.for("react.memo")
      , Ut = Symbol.for("react.lazy")
      , xt = Symbol.for("react.activity")
      , Pt = Symbol.for("react.memo_cache_sentinel")
      , St = Symbol.iterator;
    function qt(t) {
        return t === null || typeof t != "object" ? null : (t = St && t[St] || t["@@iterator"],
        typeof t == "function" ? t : null)
    }
    var Qe = Symbol.for("react.client.reference");
    function Ze(t) {
        if (t == null)
            return null;
        if (typeof t == "function")
            return t.$$typeof === Qe ? null : t.displayName || t.name || null;
        if (typeof t == "string")
            return t;
        switch (t) {
        case H:
            return "Fragment";
        case Z:
            return "Profiler";
        case k:
            return "StrictMode";
        case P:
            return "Suspense";
        case Mt:
            return "SuspenseList";
        case xt:
            return "Activity"
        }
        if (typeof t == "object")
            switch (t.$$typeof) {
            case G:
                return "Portal";
            case Q:
                return (t.displayName || "Context") + ".Provider";
            case et:
                return (t._context.displayName || "Context") + ".Consumer";
            case ut:
                var e = t.render;
                return t = t.displayName,
                t || (t = e.displayName || e.name || "",
                t = t !== "" ? "ForwardRef(" + t + ")" : "ForwardRef"),
                t;
            case Nt:
                return e = t.displayName || null,
                e !== null ? e : Ze(t.type) || "Memo";
            case Ut:
                e = t._payload,
                t = t._init;
                try {
                    return Ze(t(e))
                } catch {}
            }
        return null
    }
    var $t = Array.isArray
      , M = o.__CLIENT_INTERNALS_DO_NOT_USE_OR_WARN_USERS_THEY_CANNOT_UPGRADE
      , X = s.__DOM_INTERNALS_DO_NOT_USE_OR_WARN_USERS_THEY_CANNOT_UPGRADE
      , tt = {
        pending: !1,
        data: null,
        method: null,
        action: null
    }
      , vt = []
      , y = -1;
    function z(t) {
        return {
            current: t
        }
    }
    function V(t) {
        0 > y || (t.current = vt[y],
        vt[y] = null,
        y--)
    }
    function j(t, e) {
        y++,
        vt[y] = t.current,
        t.current = e
    }
    var F = z(null)
      , I = z(null)
      , at = z(null)
      , te = z(null);
    function At(t, e) {
        switch (j(at, e),
        j(I, t),
        j(F, null),
        e.nodeType) {
        case 9:
        case 11:
            t = (t = e.documentElement) && (t = t.namespaceURI) ? Xd(t) : 0;
            break;
        default:
            if (t = e.tagName,
            e = e.namespaceURI)
                e = Xd(e),
                t = Qd(e, t);
            else
                switch (t) {
                case "svg":
                    t = 1;
                    break;
                case "math":
                    t = 2;
                    break;
                default:
                    t = 0
                }
        }
        V(F),
        j(F, t)
    }
    function ze() {
        V(F),
        V(I),
        V(at)
    }
    function za(t) {
        t.memoizedState !== null && j(te, t);
        var e = F.current
          , l = Qd(e, t.type);
        e !== l && (j(I, t),
        j(F, l))
    }
    function ia(t) {
        I.current === t && (V(F),
        V(I)),
        te.current === t && (V(te),
        gu._currentValue = tt)
    }
    var Pe = Object.prototype.hasOwnProperty
      , ca = i.unstable_scheduleCallback
      , Ve = i.unstable_cancelCallback
      , En = i.unstable_shouldYield
      , Tn = i.unstable_requestPaint
      , ee = i.unstable_now
      , Ua = i.unstable_getCurrentPriorityLevel
      , fa = i.unstable_ImmediatePriority
      , An = i.unstable_UserBlockingPriority
      , ra = i.unstable_NormalPriority
      , lc = i.unstable_LowPriority
      , Ca = i.unstable_IdlePriority
      , oa = i.log
      , zl = i.unstable_setDisableYieldValue
      , Ue = null
      , ie = null;
    function Ke(t) {
        if (typeof oa == "function" && zl(t),
        ie && typeof ie.setStrictMode == "function")
            try {
                ie.setStrictMode(Ue, t)
            } catch {}
    }
    var ce = Math.clz32 ? Math.clz32 : La
      , _n = Math.log
      , Hu = Math.LN2;
    function La(t) {
        return t >>>= 0,
        t === 0 ? 32 : 31 - (_n(t) / Hu | 0) | 0
    }
    var rl = 256
      , ge = 4194304;
    function pe(t) {
        var e = t & 42;
        if (e !== 0)
            return e;
        switch (t & -t) {
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
        case 262144:
        case 524288:
        case 1048576:
        case 2097152:
            return t & 4194048;
        case 4194304:
        case 8388608:
        case 16777216:
        case 33554432:
            return t & 62914560;
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
            return t
        }
    }
    function Ul(t, e, l) {
        var a = t.pendingLanes;
        if (a === 0)
            return 0;
        var n = 0
          , u = t.suspendedLanes
          , c = t.pingedLanes;
        t = t.warmLanes;
        var r = a & 134217727;
        return r !== 0 ? (a = r & ~u,
        a !== 0 ? n = pe(a) : (c &= r,
        c !== 0 ? n = pe(c) : l || (l = r & ~t,
        l !== 0 && (n = pe(l))))) : (r = a & ~u,
        r !== 0 ? n = pe(r) : c !== 0 ? n = pe(c) : l || (l = a & ~t,
        l !== 0 && (n = pe(l)))),
        n === 0 ? 0 : e !== 0 && e !== n && (e & u) === 0 && (u = n & -n,
        l = e & -e,
        u >= l || u === 32 && (l & 4194048) !== 0) ? e : n
    }
    function ol(t, e) {
        return (t.pendingLanes & ~(t.suspendedLanes & ~t.pingedLanes) & e) === 0
    }
    function ac(t, e) {
        switch (t) {
        case 1:
        case 2:
        case 4:
        case 8:
        case 64:
            return e + 250;
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
            return e + 5e3;
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
    function sa() {
        var t = rl;
        return rl <<= 1,
        (rl & 4194048) === 0 && (rl = 256),
        t
    }
    function da() {
        var t = ge;
        return ge <<= 1,
        (ge & 62914560) === 0 && (ge = 4194304),
        t
    }
    function xn(t) {
        for (var e = [], l = 0; 31 > l; l++)
            e.push(t);
        return e
    }
    function Je(t, e) {
        t.pendingLanes |= e,
        e !== 268435456 && (t.suspendedLanes = 0,
        t.pingedLanes = 0,
        t.warmLanes = 0)
    }
    function nc(t, e, l, a, n, u) {
        var c = t.pendingLanes;
        t.pendingLanes = l,
        t.suspendedLanes = 0,
        t.pingedLanes = 0,
        t.warmLanes = 0,
        t.expiredLanes &= l,
        t.entangledLanes &= l,
        t.errorRecoveryDisabledLanes &= l,
        t.shellSuspendCounter = 0;
        var r = t.entanglements
          , m = t.expirationTimes
          , T = t.hiddenUpdates;
        for (l = c & ~l; 0 < l; ) {
            var N = 31 - ce(l)
              , L = 1 << N;
            r[N] = 0,
            m[N] = -1;
            var A = T[N];
            if (A !== null)
                for (T[N] = null,
                N = 0; N < A.length; N++) {
                    var _ = A[N];
                    _ !== null && (_.lane &= -536870913)
                }
            l &= ~L
        }
        a !== 0 && wu(t, a, 0),
        u !== 0 && n === 0 && t.tag !== 0 && (t.suspendedLanes |= u & ~(c & ~e))
    }
    function wu(t, e, l) {
        t.pendingLanes |= e,
        t.suspendedLanes &= ~e;
        var a = 31 - ce(e);
        t.entangledLanes |= e,
        t.entanglements[a] = t.entanglements[a] | 1073741824 | l & 4194090
    }
    function Bt(t, e) {
        var l = t.entangledLanes |= e;
        for (t = t.entanglements; l; ) {
            var a = 31 - ce(l)
              , n = 1 << a;
            n & e | t[a] & e && (t[a] |= e),
            l &= ~n
        }
    }
    function tl(t) {
        switch (t) {
        case 2:
            t = 1;
            break;
        case 8:
            t = 4;
            break;
        case 32:
            t = 16;
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
            t = 128;
            break;
        case 268435456:
            t = 134217728;
            break;
        default:
            t = 0
        }
        return t
    }
    function Rn(t) {
        return t &= -t,
        2 < t ? 8 < t ? (t & 134217727) !== 0 ? 32 : 268435456 : 8 : 2
    }
    function On() {
        var t = X.p;
        return t !== 0 ? t : (t = window.event,
        t === void 0 ? 32 : fm(t.type))
    }
    function Dn(t, e) {
        var l = X.p;
        try {
            return X.p = t,
            e()
        } finally {
            X.p = l
        }
    }
    var ke = Math.random().toString(36).slice(2)
      , Vt = "__reactFiber$" + ke
      , fe = "__reactProps$" + ke
      , le = "__reactContainer$" + ke
      , Cl = "__reactEvents$" + ke
      , Bu = "__reactListeners$" + ke
      , ju = "__reactHandles$" + ke
      , Ha = "__reactResources$" + ke
      , Ll = "__reactMarker$" + ke;
    function Ae(t) {
        delete t[Vt],
        delete t[fe],
        delete t[Cl],
        delete t[Bu],
        delete t[ju]
    }
    function sl(t) {
        var e = t[Vt];
        if (e)
            return e;
        for (var l = t.parentNode; l; ) {
            if (e = l[le] || l[Vt]) {
                if (l = e.alternate,
                e.child !== null || l !== null && l.child !== null)
                    for (t = Jd(t); t !== null; ) {
                        if (l = t[Vt])
                            return l;
                        t = Jd(t)
                    }
                return e
            }
            t = l,
            l = t.parentNode
        }
        return null
    }
    function dl(t) {
        if (t = t[Vt] || t[le]) {
            var e = t.tag;
            if (e === 5 || e === 6 || e === 13 || e === 26 || e === 27 || e === 3)
                return t
        }
        return null
    }
    function Hl(t) {
        var e = t.tag;
        if (e === 5 || e === 26 || e === 27 || e === 6)
            return t.stateNode;
        throw Error(f(33))
    }
    function ml(t) {
        var e = t[Ha];
        return e || (e = t[Ha] = {
            hoistableStyles: new Map,
            hoistableScripts: new Map
        }),
        e
    }
    function Gt(t) {
        t[Ll] = !0
    }
    var $ = new Set
      , p = {};
    function B(t, e) {
        K(t, e),
        K(t + "Capture", e)
    }
    function K(t, e) {
        for (p[t] = e,
        t = 0; t < e.length; t++)
            $.add(e[t])
    }
    var Rt = RegExp("^[:A-Z_a-z\\u00C0-\\u00D6\\u00D8-\\u00F6\\u00F8-\\u02FF\\u0370-\\u037D\\u037F-\\u1FFF\\u200C-\\u200D\\u2070-\\u218F\\u2C00-\\u2FEF\\u3001-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFFD][:A-Z_a-z\\u00C0-\\u00D6\\u00D8-\\u00F6\\u00F8-\\u02FF\\u0370-\\u037D\\u037F-\\u1FFF\\u200C-\\u200D\\u2070-\\u218F\\u2C00-\\u2FEF\\u3001-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFFD\\-.0-9\\u00B7\\u0300-\\u036F\\u203F-\\u2040]*$")
      , Kt = {}
      , Xt = {};
    function re(t) {
        return Pe.call(Xt, t) ? !0 : Pe.call(Kt, t) ? !1 : Rt.test(t) ? Xt[t] = !0 : (Kt[t] = !0,
        !1)
    }
    function de(t, e, l) {
        if (re(e))
            if (l === null)
                t.removeAttribute(e);
            else {
                switch (typeof l) {
                case "undefined":
                case "function":
                case "symbol":
                    t.removeAttribute(e);
                    return;
                case "boolean":
                    var a = e.toLowerCase().slice(0, 5);
                    if (a !== "data-" && a !== "aria-") {
                        t.removeAttribute(e);
                        return
                    }
                }
                t.setAttribute(e, "" + l)
            }
    }
    function el(t, e, l) {
        if (l === null)
            t.removeAttribute(e);
        else {
            switch (typeof l) {
            case "undefined":
            case "function":
            case "symbol":
            case "boolean":
                t.removeAttribute(e);
                return
            }
            t.setAttribute(e, "" + l)
        }
    }
    function Dt(t, e, l, a) {
        if (a === null)
            t.removeAttribute(l);
        else {
            switch (typeof a) {
            case "undefined":
            case "function":
            case "symbol":
            case "boolean":
                t.removeAttribute(l);
                return
            }
            t.setAttributeNS(e, l, "" + a)
        }
    }
    var wa, Mn;
    function Ba(t) {
        if (wa === void 0)
            try {
                throw Error()
            } catch (l) {
                var e = l.stack.trim().match(/\n( *(at )?)/);
                wa = e && e[1] || "",
                Mn = -1 < l.stack.indexOf(`
    at`) ? " (<anonymous>)" : -1 < l.stack.indexOf("@") ? "@unknown:0:0" : ""
            }
        return `
` + wa + t + Mn
    }
    var uc = !1;
    function ic(t, e) {
        if (!t || uc)
            return "";
        uc = !0;
        var l = Error.prepareStackTrace;
        Error.prepareStackTrace = void 0;
        try {
            var a = {
                DetermineComponentFrameRoot: function() {
                    try {
                        if (e) {
                            var L = function() {
                                throw Error()
                            };
                            if (Object.defineProperty(L.prototype, "props", {
                                set: function() {
                                    throw Error()
                                }
                            }),
                            typeof Reflect == "object" && Reflect.construct) {
                                try {
                                    Reflect.construct(L, [])
                                } catch (_) {
                                    var A = _
                                }
                                Reflect.construct(t, [], L)
                            } else {
                                try {
                                    L.call()
                                } catch (_) {
                                    A = _
                                }
                                t.call(L.prototype)
                            }
                        } else {
                            try {
                                throw Error()
                            } catch (_) {
                                A = _
                            }
                            (L = t()) && typeof L.catch == "function" && L.catch(function() {})
                        }
                    } catch (_) {
                        if (_ && A && typeof _.stack == "string")
                            return [_.stack, A.stack]
                    }
                    return [null, null]
                }
            };
            a.DetermineComponentFrameRoot.displayName = "DetermineComponentFrameRoot";
            var n = Object.getOwnPropertyDescriptor(a.DetermineComponentFrameRoot, "name");
            n && n.configurable && Object.defineProperty(a.DetermineComponentFrameRoot, "name", {
                value: "DetermineComponentFrameRoot"
            });
            var u = a.DetermineComponentFrameRoot()
              , c = u[0]
              , r = u[1];
            if (c && r) {
                var m = c.split(`
`)
                  , T = r.split(`
`);
                for (n = a = 0; a < m.length && !m[a].includes("DetermineComponentFrameRoot"); )
                    a++;
                for (; n < T.length && !T[n].includes("DetermineComponentFrameRoot"); )
                    n++;
                if (a === m.length || n === T.length)
                    for (a = m.length - 1,
                    n = T.length - 1; 1 <= a && 0 <= n && m[a] !== T[n]; )
                        n--;
                for (; 1 <= a && 0 <= n; a--,
                n--)
                    if (m[a] !== T[n]) {
                        if (a !== 1 || n !== 1)
                            do
                                if (a--,
                                n--,
                                0 > n || m[a] !== T[n]) {
                                    var N = `
` + m[a].replace(" at new ", " at ");
                                    return t.displayName && N.includes("<anonymous>") && (N = N.replace("<anonymous>", t.displayName)),
                                    N
                                }
                            while (1 <= a && 0 <= n);
                        break
                    }
            }
        } finally {
            uc = !1,
            Error.prepareStackTrace = l
        }
        return (l = t ? t.displayName || t.name : "") ? Ba(l) : ""
    }
    function sh(t) {
        switch (t.tag) {
        case 26:
        case 27:
        case 5:
            return Ba(t.type);
        case 16:
            return Ba("Lazy");
        case 13:
            return Ba("Suspense");
        case 19:
            return Ba("SuspenseList");
        case 0:
        case 15:
            return ic(t.type, !1);
        case 11:
            return ic(t.type.render, !1);
        case 1:
            return ic(t.type, !0);
        case 31:
            return Ba("Activity");
        default:
            return ""
        }
    }
    function Hr(t) {
        try {
            var e = "";
            do
                e += sh(t),
                t = t.return;
            while (t);
            return e
        } catch (l) {
            return `
Error generating stack: ` + l.message + `
` + l.stack
        }
    }
    function Ce(t) {
        switch (typeof t) {
        case "bigint":
        case "boolean":
        case "number":
        case "string":
        case "undefined":
            return t;
        case "object":
            return t;
        default:
            return ""
        }
    }
    function wr(t) {
        var e = t.type;
        return (t = t.nodeName) && t.toLowerCase() === "input" && (e === "checkbox" || e === "radio")
    }
    function dh(t) {
        var e = wr(t) ? "checked" : "value"
          , l = Object.getOwnPropertyDescriptor(t.constructor.prototype, e)
          , a = "" + t[e];
        if (!t.hasOwnProperty(e) && typeof l < "u" && typeof l.get == "function" && typeof l.set == "function") {
            var n = l.get
              , u = l.set;
            return Object.defineProperty(t, e, {
                configurable: !0,
                get: function() {
                    return n.call(this)
                },
                set: function(c) {
                    a = "" + c,
                    u.call(this, c)
                }
            }),
            Object.defineProperty(t, e, {
                enumerable: l.enumerable
            }),
            {
                getValue: function() {
                    return a
                },
                setValue: function(c) {
                    a = "" + c
                },
                stopTracking: function() {
                    t._valueTracker = null,
                    delete t[e]
                }
            }
        }
    }
    function Yu(t) {
        t._valueTracker || (t._valueTracker = dh(t))
    }
    function Br(t) {
        if (!t)
            return !1;
        var e = t._valueTracker;
        if (!e)
            return !0;
        var l = e.getValue()
          , a = "";
        return t && (a = wr(t) ? t.checked ? "true" : "false" : t.value),
        t = a,
        t !== l ? (e.setValue(t),
        !0) : !1
    }
    function qu(t) {
        if (t = t || (typeof document < "u" ? document : void 0),
        typeof t > "u")
            return null;
        try {
            return t.activeElement || t.body
        } catch {
            return t.body
        }
    }
    var mh = /[\n"\\]/g;
    function Le(t) {
        return t.replace(mh, function(e) {
            return "\\" + e.charCodeAt(0).toString(16) + " "
        })
    }
    function cc(t, e, l, a, n, u, c, r) {
        t.name = "",
        c != null && typeof c != "function" && typeof c != "symbol" && typeof c != "boolean" ? t.type = c : t.removeAttribute("type"),
        e != null ? c === "number" ? (e === 0 && t.value === "" || t.value != e) && (t.value = "" + Ce(e)) : t.value !== "" + Ce(e) && (t.value = "" + Ce(e)) : c !== "submit" && c !== "reset" || t.removeAttribute("value"),
        e != null ? fc(t, c, Ce(e)) : l != null ? fc(t, c, Ce(l)) : a != null && t.removeAttribute("value"),
        n == null && u != null && (t.defaultChecked = !!u),
        n != null && (t.checked = n && typeof n != "function" && typeof n != "symbol"),
        r != null && typeof r != "function" && typeof r != "symbol" && typeof r != "boolean" ? t.name = "" + Ce(r) : t.removeAttribute("name")
    }
    function jr(t, e, l, a, n, u, c, r) {
        if (u != null && typeof u != "function" && typeof u != "symbol" && typeof u != "boolean" && (t.type = u),
        e != null || l != null) {
            if (!(u !== "submit" && u !== "reset" || e != null))
                return;
            l = l != null ? "" + Ce(l) : "",
            e = e != null ? "" + Ce(e) : l,
            r || e === t.value || (t.value = e),
            t.defaultValue = e
        }
        a = a ?? n,
        a = typeof a != "function" && typeof a != "symbol" && !!a,
        t.checked = r ? t.checked : !!a,
        t.defaultChecked = !!a,
        c != null && typeof c != "function" && typeof c != "symbol" && typeof c != "boolean" && (t.name = c)
    }
    function fc(t, e, l) {
        e === "number" && qu(t.ownerDocument) === t || t.defaultValue === "" + l || (t.defaultValue = "" + l)
    }
    function ja(t, e, l, a) {
        if (t = t.options,
        e) {
            e = {};
            for (var n = 0; n < l.length; n++)
                e["$" + l[n]] = !0;
            for (l = 0; l < t.length; l++)
                n = e.hasOwnProperty("$" + t[l].value),
                t[l].selected !== n && (t[l].selected = n),
                n && a && (t[l].defaultSelected = !0)
        } else {
            for (l = "" + Ce(l),
            e = null,
            n = 0; n < t.length; n++) {
                if (t[n].value === l) {
                    t[n].selected = !0,
                    a && (t[n].defaultSelected = !0);
                    return
                }
                e !== null || t[n].disabled || (e = t[n])
            }
            e !== null && (e.selected = !0)
        }
    }
    function Yr(t, e, l) {
        if (e != null && (e = "" + Ce(e),
        e !== t.value && (t.value = e),
        l == null)) {
            t.defaultValue !== e && (t.defaultValue = e);
            return
        }
        t.defaultValue = l != null ? "" + Ce(l) : ""
    }
    function qr(t, e, l, a) {
        if (e == null) {
            if (a != null) {
                if (l != null)
                    throw Error(f(92));
                if ($t(a)) {
                    if (1 < a.length)
                        throw Error(f(93));
                    a = a[0]
                }
                l = a
            }
            l == null && (l = ""),
            e = l
        }
        l = Ce(e),
        t.defaultValue = l,
        a = t.textContent,
        a === l && a !== "" && a !== null && (t.value = a)
    }
    function Ya(t, e) {
        if (e) {
            var l = t.firstChild;
            if (l && l === t.lastChild && l.nodeType === 3) {
                l.nodeValue = e;
                return
            }
        }
        t.textContent = e
    }
    var hh = new Set("animationIterationCount aspectRatio borderImageOutset borderImageSlice borderImageWidth boxFlex boxFlexGroup boxOrdinalGroup columnCount columns flex flexGrow flexPositive flexShrink flexNegative flexOrder gridArea gridRow gridRowEnd gridRowSpan gridRowStart gridColumn gridColumnEnd gridColumnSpan gridColumnStart fontWeight lineClamp lineHeight opacity order orphans scale tabSize widows zIndex zoom fillOpacity floodOpacity stopOpacity strokeDasharray strokeDashoffset strokeMiterlimit strokeOpacity strokeWidth MozAnimationIterationCount MozBoxFlex MozBoxFlexGroup MozLineClamp msAnimationIterationCount msFlex msZoom msFlexGrow msFlexNegative msFlexOrder msFlexPositive msFlexShrink msGridColumn msGridColumnSpan msGridRow msGridRowSpan WebkitAnimationIterationCount WebkitBoxFlex WebKitBoxFlexGroup WebkitBoxOrdinalGroup WebkitColumnCount WebkitColumns WebkitFlex WebkitFlexGrow WebkitFlexPositive WebkitFlexShrink WebkitLineClamp".split(" "));
    function Gr(t, e, l) {
        var a = e.indexOf("--") === 0;
        l == null || typeof l == "boolean" || l === "" ? a ? t.setProperty(e, "") : e === "float" ? t.cssFloat = "" : t[e] = "" : a ? t.setProperty(e, l) : typeof l != "number" || l === 0 || hh.has(e) ? e === "float" ? t.cssFloat = l : t[e] = ("" + l).trim() : t[e] = l + "px"
    }
    function Xr(t, e, l) {
        if (e != null && typeof e != "object")
            throw Error(f(62));
        if (t = t.style,
        l != null) {
            for (var a in l)
                !l.hasOwnProperty(a) || e != null && e.hasOwnProperty(a) || (a.indexOf("--") === 0 ? t.setProperty(a, "") : a === "float" ? t.cssFloat = "" : t[a] = "");
            for (var n in e)
                a = e[n],
                e.hasOwnProperty(n) && l[n] !== a && Gr(t, n, a)
        } else
            for (var u in e)
                e.hasOwnProperty(u) && Gr(t, u, e[u])
    }
    function rc(t) {
        if (t.indexOf("-") === -1)
            return !1;
        switch (t) {
        case "annotation-xml":
        case "color-profile":
        case "font-face":
        case "font-face-src":
        case "font-face-uri":
        case "font-face-format":
        case "font-face-name":
        case "missing-glyph":
            return !1;
        default:
            return !0
        }
    }
    var yh = new Map([["acceptCharset", "accept-charset"], ["htmlFor", "for"], ["httpEquiv", "http-equiv"], ["crossOrigin", "crossorigin"], ["accentHeight", "accent-height"], ["alignmentBaseline", "alignment-baseline"], ["arabicForm", "arabic-form"], ["baselineShift", "baseline-shift"], ["capHeight", "cap-height"], ["clipPath", "clip-path"], ["clipRule", "clip-rule"], ["colorInterpolation", "color-interpolation"], ["colorInterpolationFilters", "color-interpolation-filters"], ["colorProfile", "color-profile"], ["colorRendering", "color-rendering"], ["dominantBaseline", "dominant-baseline"], ["enableBackground", "enable-background"], ["fillOpacity", "fill-opacity"], ["fillRule", "fill-rule"], ["floodColor", "flood-color"], ["floodOpacity", "flood-opacity"], ["fontFamily", "font-family"], ["fontSize", "font-size"], ["fontSizeAdjust", "font-size-adjust"], ["fontStretch", "font-stretch"], ["fontStyle", "font-style"], ["fontVariant", "font-variant"], ["fontWeight", "font-weight"], ["glyphName", "glyph-name"], ["glyphOrientationHorizontal", "glyph-orientation-horizontal"], ["glyphOrientationVertical", "glyph-orientation-vertical"], ["horizAdvX", "horiz-adv-x"], ["horizOriginX", "horiz-origin-x"], ["imageRendering", "image-rendering"], ["letterSpacing", "letter-spacing"], ["lightingColor", "lighting-color"], ["markerEnd", "marker-end"], ["markerMid", "marker-mid"], ["markerStart", "marker-start"], ["overlinePosition", "overline-position"], ["overlineThickness", "overline-thickness"], ["paintOrder", "paint-order"], ["panose-1", "panose-1"], ["pointerEvents", "pointer-events"], ["renderingIntent", "rendering-intent"], ["shapeRendering", "shape-rendering"], ["stopColor", "stop-color"], ["stopOpacity", "stop-opacity"], ["strikethroughPosition", "strikethrough-position"], ["strikethroughThickness", "strikethrough-thickness"], ["strokeDasharray", "stroke-dasharray"], ["strokeDashoffset", "stroke-dashoffset"], ["strokeLinecap", "stroke-linecap"], ["strokeLinejoin", "stroke-linejoin"], ["strokeMiterlimit", "stroke-miterlimit"], ["strokeOpacity", "stroke-opacity"], ["strokeWidth", "stroke-width"], ["textAnchor", "text-anchor"], ["textDecoration", "text-decoration"], ["textRendering", "text-rendering"], ["transformOrigin", "transform-origin"], ["underlinePosition", "underline-position"], ["underlineThickness", "underline-thickness"], ["unicodeBidi", "unicode-bidi"], ["unicodeRange", "unicode-range"], ["unitsPerEm", "units-per-em"], ["vAlphabetic", "v-alphabetic"], ["vHanging", "v-hanging"], ["vIdeographic", "v-ideographic"], ["vMathematical", "v-mathematical"], ["vectorEffect", "vector-effect"], ["vertAdvY", "vert-adv-y"], ["vertOriginX", "vert-origin-x"], ["vertOriginY", "vert-origin-y"], ["wordSpacing", "word-spacing"], ["writingMode", "writing-mode"], ["xmlnsXlink", "xmlns:xlink"], ["xHeight", "x-height"]])
      , vh = /^[\u0000-\u001F ]*j[\r\n\t]*a[\r\n\t]*v[\r\n\t]*a[\r\n\t]*s[\r\n\t]*c[\r\n\t]*r[\r\n\t]*i[\r\n\t]*p[\r\n\t]*t[\r\n\t]*:/i;
    function Gu(t) {
        return vh.test("" + t) ? "javascript:throw new Error('React has blocked a javascript: URL as a security precaution.')" : t
    }
    var oc = null;
    function sc(t) {
        return t = t.target || t.srcElement || window,
        t.correspondingUseElement && (t = t.correspondingUseElement),
        t.nodeType === 3 ? t.parentNode : t
    }
    var qa = null
      , Ga = null;
    function Qr(t) {
        var e = dl(t);
        if (e && (t = e.stateNode)) {
            var l = t[fe] || null;
            t: switch (t = e.stateNode,
            e.type) {
            case "input":
                if (cc(t, l.value, l.defaultValue, l.defaultValue, l.checked, l.defaultChecked, l.type, l.name),
                e = l.name,
                l.type === "radio" && e != null) {
                    for (l = t; l.parentNode; )
                        l = l.parentNode;
                    for (l = l.querySelectorAll('input[name="' + Le("" + e) + '"][type="radio"]'),
                    e = 0; e < l.length; e++) {
                        var a = l[e];
                        if (a !== t && a.form === t.form) {
                            var n = a[fe] || null;
                            if (!n)
                                throw Error(f(90));
                            cc(a, n.value, n.defaultValue, n.defaultValue, n.checked, n.defaultChecked, n.type, n.name)
                        }
                    }
                    for (e = 0; e < l.length; e++)
                        a = l[e],
                        a.form === t.form && Br(a)
                }
                break t;
            case "textarea":
                Yr(t, l.value, l.defaultValue);
                break t;
            case "select":
                e = l.value,
                e != null && ja(t, !!l.multiple, e, !1)
            }
        }
    }
    var dc = !1;
    function Zr(t, e, l) {
        if (dc)
            return t(e, l);
        dc = !0;
        try {
            var a = t(e);
            return a
        } finally {
            if (dc = !1,
            (qa !== null || Ga !== null) && (Ri(),
            qa && (e = qa,
            t = Ga,
            Ga = qa = null,
            Qr(e),
            t)))
                for (e = 0; e < t.length; e++)
                    Qr(t[e])
        }
    }
    function Nn(t, e) {
        var l = t.stateNode;
        if (l === null)
            return null;
        var a = l[fe] || null;
        if (a === null)
            return null;
        l = a[e];
        t: switch (e) {
        case "onClick":
        case "onClickCapture":
        case "onDoubleClick":
        case "onDoubleClickCapture":
        case "onMouseDown":
        case "onMouseDownCapture":
        case "onMouseMove":
        case "onMouseMoveCapture":
        case "onMouseUp":
        case "onMouseUpCapture":
        case "onMouseEnter":
            (a = !a.disabled) || (t = t.type,
            a = !(t === "button" || t === "input" || t === "select" || t === "textarea")),
            t = !a;
            break t;
        default:
            t = !1
        }
        if (t)
            return null;
        if (l && typeof l != "function")
            throw Error(f(231, e, typeof l));
        return l
    }
    var hl = !(typeof window > "u" || typeof window.document > "u" || typeof window.document.createElement > "u")
      , mc = !1;
    if (hl)
        try {
            var zn = {};
            Object.defineProperty(zn, "passive", {
                get: function() {
                    mc = !0
                }
            }),
            window.addEventListener("test", zn, zn),
            window.removeEventListener("test", zn, zn)
        } catch {
            mc = !1
        }
    var wl = null
      , hc = null
      , Xu = null;
    function Vr() {
        if (Xu)
            return Xu;
        var t, e = hc, l = e.length, a, n = "value"in wl ? wl.value : wl.textContent, u = n.length;
        for (t = 0; t < l && e[t] === n[t]; t++)
            ;
        var c = l - t;
        for (a = 1; a <= c && e[l - a] === n[u - a]; a++)
            ;
        return Xu = n.slice(t, 1 < a ? 1 - a : void 0)
    }
    function Qu(t) {
        var e = t.keyCode;
        return "charCode"in t ? (t = t.charCode,
        t === 0 && e === 13 && (t = 13)) : t = e,
        t === 10 && (t = 13),
        32 <= t || t === 13 ? t : 0
    }
    function Zu() {
        return !0
    }
    function Kr() {
        return !1
    }
    function Se(t) {
        function e(l, a, n, u, c) {
            this._reactName = l,
            this._targetInst = n,
            this.type = a,
            this.nativeEvent = u,
            this.target = c,
            this.currentTarget = null;
            for (var r in t)
                t.hasOwnProperty(r) && (l = t[r],
                this[r] = l ? l(u) : u[r]);
            return this.isDefaultPrevented = (u.defaultPrevented != null ? u.defaultPrevented : u.returnValue === !1) ? Zu : Kr,
            this.isPropagationStopped = Kr,
            this
        }
        return R(e.prototype, {
            preventDefault: function() {
                this.defaultPrevented = !0;
                var l = this.nativeEvent;
                l && (l.preventDefault ? l.preventDefault() : typeof l.returnValue != "unknown" && (l.returnValue = !1),
                this.isDefaultPrevented = Zu)
            },
            stopPropagation: function() {
                var l = this.nativeEvent;
                l && (l.stopPropagation ? l.stopPropagation() : typeof l.cancelBubble != "unknown" && (l.cancelBubble = !0),
                this.isPropagationStopped = Zu)
            },
            persist: function() {},
            isPersistent: Zu
        }),
        e
    }
    var ma = {
        eventPhase: 0,
        bubbles: 0,
        cancelable: 0,
        timeStamp: function(t) {
            return t.timeStamp || Date.now()
        },
        defaultPrevented: 0,
        isTrusted: 0
    }, Vu = Se(ma), Un = R({}, ma, {
        view: 0,
        detail: 0
    }), gh = Se(Un), yc, vc, Cn, Ku = R({}, Un, {
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
        getModifierState: pc,
        button: 0,
        buttons: 0,
        relatedTarget: function(t) {
            return t.relatedTarget === void 0 ? t.fromElement === t.srcElement ? t.toElement : t.fromElement : t.relatedTarget
        },
        movementX: function(t) {
            return "movementX"in t ? t.movementX : (t !== Cn && (Cn && t.type === "mousemove" ? (yc = t.screenX - Cn.screenX,
            vc = t.screenY - Cn.screenY) : vc = yc = 0,
            Cn = t),
            yc)
        },
        movementY: function(t) {
            return "movementY"in t ? t.movementY : vc
        }
    }), Jr = Se(Ku), ph = R({}, Ku, {
        dataTransfer: 0
    }), Sh = Se(ph), bh = R({}, Un, {
        relatedTarget: 0
    }), gc = Se(bh), Eh = R({}, ma, {
        animationName: 0,
        elapsedTime: 0,
        pseudoElement: 0
    }), Th = Se(Eh), Ah = R({}, ma, {
        clipboardData: function(t) {
            return "clipboardData"in t ? t.clipboardData : window.clipboardData
        }
    }), _h = Se(Ah), xh = R({}, ma, {
        data: 0
    }), kr = Se(xh), Rh = {
        Esc: "Escape",
        Spacebar: " ",
        Left: "ArrowLeft",
        Up: "ArrowUp",
        Right: "ArrowRight",
        Down: "ArrowDown",
        Del: "Delete",
        Win: "OS",
        Menu: "ContextMenu",
        Apps: "ContextMenu",
        Scroll: "ScrollLock",
        MozPrintableKey: "Unidentified"
    }, Oh = {
        8: "Backspace",
        9: "Tab",
        12: "Clear",
        13: "Enter",
        16: "Shift",
        17: "Control",
        18: "Alt",
        19: "Pause",
        20: "CapsLock",
        27: "Escape",
        32: " ",
        33: "PageUp",
        34: "PageDown",
        35: "End",
        36: "Home",
        37: "ArrowLeft",
        38: "ArrowUp",
        39: "ArrowRight",
        40: "ArrowDown",
        45: "Insert",
        46: "Delete",
        112: "F1",
        113: "F2",
        114: "F3",
        115: "F4",
        116: "F5",
        117: "F6",
        118: "F7",
        119: "F8",
        120: "F9",
        121: "F10",
        122: "F11",
        123: "F12",
        144: "NumLock",
        145: "ScrollLock",
        224: "Meta"
    }, Dh = {
        Alt: "altKey",
        Control: "ctrlKey",
        Meta: "metaKey",
        Shift: "shiftKey"
    };
    function Mh(t) {
        var e = this.nativeEvent;
        return e.getModifierState ? e.getModifierState(t) : (t = Dh[t]) ? !!e[t] : !1
    }
    function pc() {
        return Mh
    }
    var Nh = R({}, Un, {
        key: function(t) {
            if (t.key) {
                var e = Rh[t.key] || t.key;
                if (e !== "Unidentified")
                    return e
            }
            return t.type === "keypress" ? (t = Qu(t),
            t === 13 ? "Enter" : String.fromCharCode(t)) : t.type === "keydown" || t.type === "keyup" ? Oh[t.keyCode] || "Unidentified" : ""
        },
        code: 0,
        location: 0,
        ctrlKey: 0,
        shiftKey: 0,
        altKey: 0,
        metaKey: 0,
        repeat: 0,
        locale: 0,
        getModifierState: pc,
        charCode: function(t) {
            return t.type === "keypress" ? Qu(t) : 0
        },
        keyCode: function(t) {
            return t.type === "keydown" || t.type === "keyup" ? t.keyCode : 0
        },
        which: function(t) {
            return t.type === "keypress" ? Qu(t) : t.type === "keydown" || t.type === "keyup" ? t.keyCode : 0
        }
    })
      , zh = Se(Nh)
      , Uh = R({}, Ku, {
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
      , $r = Se(Uh)
      , Ch = R({}, Un, {
        touches: 0,
        targetTouches: 0,
        changedTouches: 0,
        altKey: 0,
        metaKey: 0,
        ctrlKey: 0,
        shiftKey: 0,
        getModifierState: pc
    })
      , Lh = Se(Ch)
      , Hh = R({}, ma, {
        propertyName: 0,
        elapsedTime: 0,
        pseudoElement: 0
    })
      , wh = Se(Hh)
      , Bh = R({}, Ku, {
        deltaX: function(t) {
            return "deltaX"in t ? t.deltaX : "wheelDeltaX"in t ? -t.wheelDeltaX : 0
        },
        deltaY: function(t) {
            return "deltaY"in t ? t.deltaY : "wheelDeltaY"in t ? -t.wheelDeltaY : "wheelDelta"in t ? -t.wheelDelta : 0
        },
        deltaZ: 0,
        deltaMode: 0
    })
      , jh = Se(Bh)
      , Yh = R({}, ma, {
        newState: 0,
        oldState: 0
    })
      , qh = Se(Yh)
      , Gh = [9, 13, 27, 32]
      , Sc = hl && "CompositionEvent"in window
      , Ln = null;
    hl && "documentMode"in document && (Ln = document.documentMode);
    var Xh = hl && "TextEvent"in window && !Ln
      , Wr = hl && (!Sc || Ln && 8 < Ln && 11 >= Ln)
      , Fr = " "
      , Ir = !1;
    function Pr(t, e) {
        switch (t) {
        case "keyup":
            return Gh.indexOf(e.keyCode) !== -1;
        case "keydown":
            return e.keyCode !== 229;
        case "keypress":
        case "mousedown":
        case "focusout":
            return !0;
        default:
            return !1
        }
    }
    function to(t) {
        return t = t.detail,
        typeof t == "object" && "data"in t ? t.data : null
    }
    var Xa = !1;
    function Qh(t, e) {
        switch (t) {
        case "compositionend":
            return to(e);
        case "keypress":
            return e.which !== 32 ? null : (Ir = !0,
            Fr);
        case "textInput":
            return t = e.data,
            t === Fr && Ir ? null : t;
        default:
            return null
        }
    }
    function Zh(t, e) {
        if (Xa)
            return t === "compositionend" || !Sc && Pr(t, e) ? (t = Vr(),
            Xu = hc = wl = null,
            Xa = !1,
            t) : null;
        switch (t) {
        case "paste":
            return null;
        case "keypress":
            if (!(e.ctrlKey || e.altKey || e.metaKey) || e.ctrlKey && e.altKey) {
                if (e.char && 1 < e.char.length)
                    return e.char;
                if (e.which)
                    return String.fromCharCode(e.which)
            }
            return null;
        case "compositionend":
            return Wr && e.locale !== "ko" ? null : e.data;
        default:
            return null
        }
    }
    var Vh = {
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
    function eo(t) {
        var e = t && t.nodeName && t.nodeName.toLowerCase();
        return e === "input" ? !!Vh[t.type] : e === "textarea"
    }
    function lo(t, e, l, a) {
        qa ? Ga ? Ga.push(a) : Ga = [a] : qa = a,
        e = Ui(e, "onChange"),
        0 < e.length && (l = new Vu("onChange","change",null,l,a),
        t.push({
            event: l,
            listeners: e
        }))
    }
    var Hn = null
      , wn = null;
    function Kh(t) {
        Bd(t, 0)
    }
    function Ju(t) {
        var e = Hl(t);
        if (Br(e))
            return t
    }
    function ao(t, e) {
        if (t === "change")
            return e
    }
    var no = !1;
    if (hl) {
        var bc;
        if (hl) {
            var Ec = "oninput"in document;
            if (!Ec) {
                var uo = document.createElement("div");
                uo.setAttribute("oninput", "return;"),
                Ec = typeof uo.oninput == "function"
            }
            bc = Ec
        } else
            bc = !1;
        no = bc && (!document.documentMode || 9 < document.documentMode)
    }
    function io() {
        Hn && (Hn.detachEvent("onpropertychange", co),
        wn = Hn = null)
    }
    function co(t) {
        if (t.propertyName === "value" && Ju(wn)) {
            var e = [];
            lo(e, wn, t, sc(t)),
            Zr(Kh, e)
        }
    }
    function Jh(t, e, l) {
        t === "focusin" ? (io(),
        Hn = e,
        wn = l,
        Hn.attachEvent("onpropertychange", co)) : t === "focusout" && io()
    }
    function kh(t) {
        if (t === "selectionchange" || t === "keyup" || t === "keydown")
            return Ju(wn)
    }
    function $h(t, e) {
        if (t === "click")
            return Ju(e)
    }
    function Wh(t, e) {
        if (t === "input" || t === "change")
            return Ju(e)
    }
    function Fh(t, e) {
        return t === e && (t !== 0 || 1 / t === 1 / e) || t !== t && e !== e
    }
    var _e = typeof Object.is == "function" ? Object.is : Fh;
    function Bn(t, e) {
        if (_e(t, e))
            return !0;
        if (typeof t != "object" || t === null || typeof e != "object" || e === null)
            return !1;
        var l = Object.keys(t)
          , a = Object.keys(e);
        if (l.length !== a.length)
            return !1;
        for (a = 0; a < l.length; a++) {
            var n = l[a];
            if (!Pe.call(e, n) || !_e(t[n], e[n]))
                return !1
        }
        return !0
    }
    function fo(t) {
        for (; t && t.firstChild; )
            t = t.firstChild;
        return t
    }
    function ro(t, e) {
        var l = fo(t);
        t = 0;
        for (var a; l; ) {
            if (l.nodeType === 3) {
                if (a = t + l.textContent.length,
                t <= e && a >= e)
                    return {
                        node: l,
                        offset: e - t
                    };
                t = a
            }
            t: {
                for (; l; ) {
                    if (l.nextSibling) {
                        l = l.nextSibling;
                        break t
                    }
                    l = l.parentNode
                }
                l = void 0
            }
            l = fo(l)
        }
    }
    function oo(t, e) {
        return t && e ? t === e ? !0 : t && t.nodeType === 3 ? !1 : e && e.nodeType === 3 ? oo(t, e.parentNode) : "contains"in t ? t.contains(e) : t.compareDocumentPosition ? !!(t.compareDocumentPosition(e) & 16) : !1 : !1
    }
    function so(t) {
        t = t != null && t.ownerDocument != null && t.ownerDocument.defaultView != null ? t.ownerDocument.defaultView : window;
        for (var e = qu(t.document); e instanceof t.HTMLIFrameElement; ) {
            try {
                var l = typeof e.contentWindow.location.href == "string"
            } catch {
                l = !1
            }
            if (l)
                t = e.contentWindow;
            else
                break;
            e = qu(t.document)
        }
        return e
    }
    function Tc(t) {
        var e = t && t.nodeName && t.nodeName.toLowerCase();
        return e && (e === "input" && (t.type === "text" || t.type === "search" || t.type === "tel" || t.type === "url" || t.type === "password") || e === "textarea" || t.contentEditable === "true")
    }
    var Ih = hl && "documentMode"in document && 11 >= document.documentMode
      , Qa = null
      , Ac = null
      , jn = null
      , _c = !1;
    function mo(t, e, l) {
        var a = l.window === l ? l.document : l.nodeType === 9 ? l : l.ownerDocument;
        _c || Qa == null || Qa !== qu(a) || (a = Qa,
        "selectionStart"in a && Tc(a) ? a = {
            start: a.selectionStart,
            end: a.selectionEnd
        } : (a = (a.ownerDocument && a.ownerDocument.defaultView || window).getSelection(),
        a = {
            anchorNode: a.anchorNode,
            anchorOffset: a.anchorOffset,
            focusNode: a.focusNode,
            focusOffset: a.focusOffset
        }),
        jn && Bn(jn, a) || (jn = a,
        a = Ui(Ac, "onSelect"),
        0 < a.length && (e = new Vu("onSelect","select",null,e,l),
        t.push({
            event: e,
            listeners: a
        }),
        e.target = Qa)))
    }
    function ha(t, e) {
        var l = {};
        return l[t.toLowerCase()] = e.toLowerCase(),
        l["Webkit" + t] = "webkit" + e,
        l["Moz" + t] = "moz" + e,
        l
    }
    var Za = {
        animationend: ha("Animation", "AnimationEnd"),
        animationiteration: ha("Animation", "AnimationIteration"),
        animationstart: ha("Animation", "AnimationStart"),
        transitionrun: ha("Transition", "TransitionRun"),
        transitionstart: ha("Transition", "TransitionStart"),
        transitioncancel: ha("Transition", "TransitionCancel"),
        transitionend: ha("Transition", "TransitionEnd")
    }
      , xc = {}
      , ho = {};
    hl && (ho = document.createElement("div").style,
    "AnimationEvent"in window || (delete Za.animationend.animation,
    delete Za.animationiteration.animation,
    delete Za.animationstart.animation),
    "TransitionEvent"in window || delete Za.transitionend.transition);
    function ya(t) {
        if (xc[t])
            return xc[t];
        if (!Za[t])
            return t;
        var e = Za[t], l;
        for (l in e)
            if (e.hasOwnProperty(l) && l in ho)
                return xc[t] = e[l];
        return t
    }
    var yo = ya("animationend")
      , vo = ya("animationiteration")
      , go = ya("animationstart")
      , Ph = ya("transitionrun")
      , ty = ya("transitionstart")
      , ey = ya("transitioncancel")
      , po = ya("transitionend")
      , So = new Map
      , Rc = "abort auxClick beforeToggle cancel canPlay canPlayThrough click close contextMenu copy cut drag dragEnd dragEnter dragExit dragLeave dragOver dragStart drop durationChange emptied encrypted ended error gotPointerCapture input invalid keyDown keyPress keyUp load loadedData loadedMetadata loadStart lostPointerCapture mouseDown mouseMove mouseOut mouseOver mouseUp paste pause play playing pointerCancel pointerDown pointerMove pointerOut pointerOver pointerUp progress rateChange reset resize seeked seeking stalled submit suspend timeUpdate touchCancel touchEnd touchStart volumeChange scroll toggle touchMove waiting wheel".split(" ");
    Rc.push("scrollEnd");
    function $e(t, e) {
        So.set(t, e),
        B(e, [t])
    }
    var bo = new WeakMap;
    function He(t, e) {
        if (typeof t == "object" && t !== null) {
            var l = bo.get(t);
            return l !== void 0 ? l : (e = {
                value: t,
                source: e,
                stack: Hr(e)
            },
            bo.set(t, e),
            e)
        }
        return {
            value: t,
            source: e,
            stack: Hr(e)
        }
    }
    var we = []
      , Va = 0
      , Oc = 0;
    function ku() {
        for (var t = Va, e = Oc = Va = 0; e < t; ) {
            var l = we[e];
            we[e++] = null;
            var a = we[e];
            we[e++] = null;
            var n = we[e];
            we[e++] = null;
            var u = we[e];
            if (we[e++] = null,
            a !== null && n !== null) {
                var c = a.pending;
                c === null ? n.next = n : (n.next = c.next,
                c.next = n),
                a.pending = n
            }
            u !== 0 && Eo(l, n, u)
        }
    }
    function $u(t, e, l, a) {
        we[Va++] = t,
        we[Va++] = e,
        we[Va++] = l,
        we[Va++] = a,
        Oc |= a,
        t.lanes |= a,
        t = t.alternate,
        t !== null && (t.lanes |= a)
    }
    function Dc(t, e, l, a) {
        return $u(t, e, l, a),
        Wu(t)
    }
    function Ka(t, e) {
        return $u(t, null, null, e),
        Wu(t)
    }
    function Eo(t, e, l) {
        t.lanes |= l;
        var a = t.alternate;
        a !== null && (a.lanes |= l);
        for (var n = !1, u = t.return; u !== null; )
            u.childLanes |= l,
            a = u.alternate,
            a !== null && (a.childLanes |= l),
            u.tag === 22 && (t = u.stateNode,
            t === null || t._visibility & 1 || (n = !0)),
            t = u,
            u = u.return;
        return t.tag === 3 ? (u = t.stateNode,
        n && e !== null && (n = 31 - ce(l),
        t = u.hiddenUpdates,
        a = t[n],
        a === null ? t[n] = [e] : a.push(e),
        e.lane = l | 536870912),
        u) : null
    }
    function Wu(t) {
        if (50 < ru)
            throw ru = 0,
            Hf = null,
            Error(f(185));
        for (var e = t.return; e !== null; )
            t = e,
            e = t.return;
        return t.tag === 3 ? t.stateNode : null
    }
    var Ja = {};
    function ly(t, e, l, a) {
        this.tag = t,
        this.key = l,
        this.sibling = this.child = this.return = this.stateNode = this.type = this.elementType = null,
        this.index = 0,
        this.refCleanup = this.ref = null,
        this.pendingProps = e,
        this.dependencies = this.memoizedState = this.updateQueue = this.memoizedProps = null,
        this.mode = a,
        this.subtreeFlags = this.flags = 0,
        this.deletions = null,
        this.childLanes = this.lanes = 0,
        this.alternate = null
    }
    function xe(t, e, l, a) {
        return new ly(t,e,l,a)
    }
    function Mc(t) {
        return t = t.prototype,
        !(!t || !t.isReactComponent)
    }
    function yl(t, e) {
        var l = t.alternate;
        return l === null ? (l = xe(t.tag, e, t.key, t.mode),
        l.elementType = t.elementType,
        l.type = t.type,
        l.stateNode = t.stateNode,
        l.alternate = t,
        t.alternate = l) : (l.pendingProps = e,
        l.type = t.type,
        l.flags = 0,
        l.subtreeFlags = 0,
        l.deletions = null),
        l.flags = t.flags & 65011712,
        l.childLanes = t.childLanes,
        l.lanes = t.lanes,
        l.child = t.child,
        l.memoizedProps = t.memoizedProps,
        l.memoizedState = t.memoizedState,
        l.updateQueue = t.updateQueue,
        e = t.dependencies,
        l.dependencies = e === null ? null : {
            lanes: e.lanes,
            firstContext: e.firstContext
        },
        l.sibling = t.sibling,
        l.index = t.index,
        l.ref = t.ref,
        l.refCleanup = t.refCleanup,
        l
    }
    function To(t, e) {
        t.flags &= 65011714;
        var l = t.alternate;
        return l === null ? (t.childLanes = 0,
        t.lanes = e,
        t.child = null,
        t.subtreeFlags = 0,
        t.memoizedProps = null,
        t.memoizedState = null,
        t.updateQueue = null,
        t.dependencies = null,
        t.stateNode = null) : (t.childLanes = l.childLanes,
        t.lanes = l.lanes,
        t.child = l.child,
        t.subtreeFlags = 0,
        t.deletions = null,
        t.memoizedProps = l.memoizedProps,
        t.memoizedState = l.memoizedState,
        t.updateQueue = l.updateQueue,
        t.type = l.type,
        e = l.dependencies,
        t.dependencies = e === null ? null : {
            lanes: e.lanes,
            firstContext: e.firstContext
        }),
        t
    }
    function Fu(t, e, l, a, n, u) {
        var c = 0;
        if (a = t,
        typeof t == "function")
            Mc(t) && (c = 1);
        else if (typeof t == "string")
            c = nv(t, l, F.current) ? 26 : t === "html" || t === "head" || t === "body" ? 27 : 5;
        else
            t: switch (t) {
            case xt:
                return t = xe(31, l, e, n),
                t.elementType = xt,
                t.lanes = u,
                t;
            case H:
                return va(l.children, n, u, e);
            case k:
                c = 8,
                n |= 24;
                break;
            case Z:
                return t = xe(12, l, e, n | 2),
                t.elementType = Z,
                t.lanes = u,
                t;
            case P:
                return t = xe(13, l, e, n),
                t.elementType = P,
                t.lanes = u,
                t;
            case Mt:
                return t = xe(19, l, e, n),
                t.elementType = Mt,
                t.lanes = u,
                t;
            default:
                if (typeof t == "object" && t !== null)
                    switch (t.$$typeof) {
                    case q:
                    case Q:
                        c = 10;
                        break t;
                    case et:
                        c = 9;
                        break t;
                    case ut:
                        c = 11;
                        break t;
                    case Nt:
                        c = 14;
                        break t;
                    case Ut:
                        c = 16,
                        a = null;
                        break t
                    }
                c = 29,
                l = Error(f(130, t === null ? "null" : typeof t, "")),
                a = null
            }
        return e = xe(c, l, e, n),
        e.elementType = t,
        e.type = a,
        e.lanes = u,
        e
    }
    function va(t, e, l, a) {
        return t = xe(7, t, a, e),
        t.lanes = l,
        t
    }
    function Nc(t, e, l) {
        return t = xe(6, t, null, e),
        t.lanes = l,
        t
    }
    function zc(t, e, l) {
        return e = xe(4, t.children !== null ? t.children : [], t.key, e),
        e.lanes = l,
        e.stateNode = {
            containerInfo: t.containerInfo,
            pendingChildren: null,
            implementation: t.implementation
        },
        e
    }
    var ka = []
      , $a = 0
      , Iu = null
      , Pu = 0
      , Be = []
      , je = 0
      , ga = null
      , vl = 1
      , gl = "";
    function pa(t, e) {
        ka[$a++] = Pu,
        ka[$a++] = Iu,
        Iu = t,
        Pu = e
    }
    function Ao(t, e, l) {
        Be[je++] = vl,
        Be[je++] = gl,
        Be[je++] = ga,
        ga = t;
        var a = vl;
        t = gl;
        var n = 32 - ce(a) - 1;
        a &= ~(1 << n),
        l += 1;
        var u = 32 - ce(e) + n;
        if (30 < u) {
            var c = n - n % 5;
            u = (a & (1 << c) - 1).toString(32),
            a >>= c,
            n -= c,
            vl = 1 << 32 - ce(e) + n | l << n | a,
            gl = u + t
        } else
            vl = 1 << u | l << n | a,
            gl = t
    }
    function Uc(t) {
        t.return !== null && (pa(t, 1),
        Ao(t, 1, 0))
    }
    function Cc(t) {
        for (; t === Iu; )
            Iu = ka[--$a],
            ka[$a] = null,
            Pu = ka[--$a],
            ka[$a] = null;
        for (; t === ga; )
            ga = Be[--je],
            Be[je] = null,
            gl = Be[--je],
            Be[je] = null,
            vl = Be[--je],
            Be[je] = null
    }
    var me = null
      , Ht = null
      , yt = !1
      , Sa = null
      , ll = !1
      , Lc = Error(f(519));
    function ba(t) {
        var e = Error(f(418, ""));
        throw Gn(He(e, t)),
        Lc
    }
    function _o(t) {
        var e = t.stateNode
          , l = t.type
          , a = t.memoizedProps;
        switch (e[Vt] = t,
        e[fe] = a,
        l) {
        case "dialog":
            st("cancel", e),
            st("close", e);
            break;
        case "iframe":
        case "object":
        case "embed":
            st("load", e);
            break;
        case "video":
        case "audio":
            for (l = 0; l < su.length; l++)
                st(su[l], e);
            break;
        case "source":
            st("error", e);
            break;
        case "img":
        case "image":
        case "link":
            st("error", e),
            st("load", e);
            break;
        case "details":
            st("toggle", e);
            break;
        case "input":
            st("invalid", e),
            jr(e, a.value, a.defaultValue, a.checked, a.defaultChecked, a.type, a.name, !0),
            Yu(e);
            break;
        case "select":
            st("invalid", e);
            break;
        case "textarea":
            st("invalid", e),
            qr(e, a.value, a.defaultValue, a.children),
            Yu(e)
        }
        l = a.children,
        typeof l != "string" && typeof l != "number" && typeof l != "bigint" || e.textContent === "" + l || a.suppressHydrationWarning === !0 || Gd(e.textContent, l) ? (a.popover != null && (st("beforetoggle", e),
        st("toggle", e)),
        a.onScroll != null && st("scroll", e),
        a.onScrollEnd != null && st("scrollend", e),
        a.onClick != null && (e.onclick = Ci),
        e = !0) : e = !1,
        e || ba(t)
    }
    function xo(t) {
        for (me = t.return; me; )
            switch (me.tag) {
            case 5:
            case 13:
                ll = !1;
                return;
            case 27:
            case 3:
                ll = !0;
                return;
            default:
                me = me.return
            }
    }
    function Yn(t) {
        if (t !== me)
            return !1;
        if (!yt)
            return xo(t),
            yt = !0,
            !1;
        var e = t.tag, l;
        if ((l = e !== 3 && e !== 27) && ((l = e === 5) && (l = t.type,
        l = !(l !== "form" && l !== "button") || Ff(t.type, t.memoizedProps)),
        l = !l),
        l && Ht && ba(t),
        xo(t),
        e === 13) {
            if (t = t.memoizedState,
            t = t !== null ? t.dehydrated : null,
            !t)
                throw Error(f(317));
            t: {
                for (t = t.nextSibling,
                e = 0; t; ) {
                    if (t.nodeType === 8)
                        if (l = t.data,
                        l === "/$") {
                            if (e === 0) {
                                Ht = Fe(t.nextSibling);
                                break t
                            }
                            e--
                        } else
                            l !== "$" && l !== "$!" && l !== "$?" || e++;
                    t = t.nextSibling
                }
                Ht = null
            }
        } else
            e === 27 ? (e = Ht,
            Il(t.type) ? (t = er,
            er = null,
            Ht = t) : Ht = e) : Ht = me ? Fe(t.stateNode.nextSibling) : null;
        return !0
    }
    function qn() {
        Ht = me = null,
        yt = !1
    }
    function Ro() {
        var t = Sa;
        return t !== null && (Te === null ? Te = t : Te.push.apply(Te, t),
        Sa = null),
        t
    }
    function Gn(t) {
        Sa === null ? Sa = [t] : Sa.push(t)
    }
    var Hc = z(null)
      , Ea = null
      , pl = null;
    function Bl(t, e, l) {
        j(Hc, e._currentValue),
        e._currentValue = l
    }
    function Sl(t) {
        t._currentValue = Hc.current,
        V(Hc)
    }
    function wc(t, e, l) {
        for (; t !== null; ) {
            var a = t.alternate;
            if ((t.childLanes & e) !== e ? (t.childLanes |= e,
            a !== null && (a.childLanes |= e)) : a !== null && (a.childLanes & e) !== e && (a.childLanes |= e),
            t === l)
                break;
            t = t.return
        }
    }
    function Bc(t, e, l, a) {
        var n = t.child;
        for (n !== null && (n.return = t); n !== null; ) {
            var u = n.dependencies;
            if (u !== null) {
                var c = n.child;
                u = u.firstContext;
                t: for (; u !== null; ) {
                    var r = u;
                    u = n;
                    for (var m = 0; m < e.length; m++)
                        if (r.context === e[m]) {
                            u.lanes |= l,
                            r = u.alternate,
                            r !== null && (r.lanes |= l),
                            wc(u.return, l, t),
                            a || (c = null);
                            break t
                        }
                    u = r.next
                }
            } else if (n.tag === 18) {
                if (c = n.return,
                c === null)
                    throw Error(f(341));
                c.lanes |= l,
                u = c.alternate,
                u !== null && (u.lanes |= l),
                wc(c, l, t),
                c = null
            } else
                c = n.child;
            if (c !== null)
                c.return = n;
            else
                for (c = n; c !== null; ) {
                    if (c === t) {
                        c = null;
                        break
                    }
                    if (n = c.sibling,
                    n !== null) {
                        n.return = c.return,
                        c = n;
                        break
                    }
                    c = c.return
                }
            n = c
        }
    }
    function Xn(t, e, l, a) {
        t = null;
        for (var n = e, u = !1; n !== null; ) {
            if (!u) {
                if ((n.flags & 524288) !== 0)
                    u = !0;
                else if ((n.flags & 262144) !== 0)
                    break
            }
            if (n.tag === 10) {
                var c = n.alternate;
                if (c === null)
                    throw Error(f(387));
                if (c = c.memoizedProps,
                c !== null) {
                    var r = n.type;
                    _e(n.pendingProps.value, c.value) || (t !== null ? t.push(r) : t = [r])
                }
            } else if (n === te.current) {
                if (c = n.alternate,
                c === null)
                    throw Error(f(387));
                c.memoizedState.memoizedState !== n.memoizedState.memoizedState && (t !== null ? t.push(gu) : t = [gu])
            }
            n = n.return
        }
        t !== null && Bc(e, t, l, a),
        e.flags |= 262144
    }
    function ti(t) {
        for (t = t.firstContext; t !== null; ) {
            if (!_e(t.context._currentValue, t.memoizedValue))
                return !0;
            t = t.next
        }
        return !1
    }
    function Ta(t) {
        Ea = t,
        pl = null,
        t = t.dependencies,
        t !== null && (t.firstContext = null)
    }
    function oe(t) {
        return Oo(Ea, t)
    }
    function ei(t, e) {
        return Ea === null && Ta(t),
        Oo(t, e)
    }
    function Oo(t, e) {
        var l = e._currentValue;
        if (e = {
            context: e,
            memoizedValue: l,
            next: null
        },
        pl === null) {
            if (t === null)
                throw Error(f(308));
            pl = e,
            t.dependencies = {
                lanes: 0,
                firstContext: e
            },
            t.flags |= 524288
        } else
            pl = pl.next = e;
        return l
    }
    var ay = typeof AbortController < "u" ? AbortController : function() {
        var t = []
          , e = this.signal = {
            aborted: !1,
            addEventListener: function(l, a) {
                t.push(a)
            }
        };
        this.abort = function() {
            e.aborted = !0,
            t.forEach(function(l) {
                return l()
            })
        }
    }
      , ny = i.unstable_scheduleCallback
      , uy = i.unstable_NormalPriority
      , Jt = {
        $$typeof: Q,
        Consumer: null,
        Provider: null,
        _currentValue: null,
        _currentValue2: null,
        _threadCount: 0
    };
    function jc() {
        return {
            controller: new ay,
            data: new Map,
            refCount: 0
        }
    }
    function Qn(t) {
        t.refCount--,
        t.refCount === 0 && ny(uy, function() {
            t.controller.abort()
        })
    }
    var Zn = null
      , Yc = 0
      , Wa = 0
      , Fa = null;
    function iy(t, e) {
        if (Zn === null) {
            var l = Zn = [];
            Yc = 0,
            Wa = Xf(),
            Fa = {
                status: "pending",
                value: void 0,
                then: function(a) {
                    l.push(a)
                }
            }
        }
        return Yc++,
        e.then(Do, Do),
        e
    }
    function Do() {
        if (--Yc === 0 && Zn !== null) {
            Fa !== null && (Fa.status = "fulfilled");
            var t = Zn;
            Zn = null,
            Wa = 0,
            Fa = null;
            for (var e = 0; e < t.length; e++)
                (0,
                t[e])()
        }
    }
    function cy(t, e) {
        var l = []
          , a = {
            status: "pending",
            value: null,
            reason: null,
            then: function(n) {
                l.push(n)
            }
        };
        return t.then(function() {
            a.status = "fulfilled",
            a.value = e;
            for (var n = 0; n < l.length; n++)
                (0,
                l[n])(e)
        }, function(n) {
            for (a.status = "rejected",
            a.reason = n,
            n = 0; n < l.length; n++)
                (0,
                l[n])(void 0)
        }),
        a
    }
    var Mo = M.S;
    M.S = function(t, e) {
        typeof e == "object" && e !== null && typeof e.then == "function" && iy(t, e),
        Mo !== null && Mo(t, e)
    }
    ;
    var Aa = z(null);
    function qc() {
        var t = Aa.current;
        return t !== null ? t : Ot.pooledCache
    }
    function li(t, e) {
        e === null ? j(Aa, Aa.current) : j(Aa, e.pool)
    }
    function No() {
        var t = qc();
        return t === null ? null : {
            parent: Jt._currentValue,
            pool: t
        }
    }
    var Vn = Error(f(460))
      , zo = Error(f(474))
      , ai = Error(f(542))
      , Gc = {
        then: function() {}
    };
    function Uo(t) {
        return t = t.status,
        t === "fulfilled" || t === "rejected"
    }
    function ni() {}
    function Co(t, e, l) {
        switch (l = t[l],
        l === void 0 ? t.push(e) : l !== e && (e.then(ni, ni),
        e = l),
        e.status) {
        case "fulfilled":
            return e.value;
        case "rejected":
            throw t = e.reason,
            Ho(t),
            t;
        default:
            if (typeof e.status == "string")
                e.then(ni, ni);
            else {
                if (t = Ot,
                t !== null && 100 < t.shellSuspendCounter)
                    throw Error(f(482));
                t = e,
                t.status = "pending",
                t.then(function(a) {
                    if (e.status === "pending") {
                        var n = e;
                        n.status = "fulfilled",
                        n.value = a
                    }
                }, function(a) {
                    if (e.status === "pending") {
                        var n = e;
                        n.status = "rejected",
                        n.reason = a
                    }
                })
            }
            switch (e.status) {
            case "fulfilled":
                return e.value;
            case "rejected":
                throw t = e.reason,
                Ho(t),
                t
            }
            throw Kn = e,
            Vn
        }
    }
    var Kn = null;
    function Lo() {
        if (Kn === null)
            throw Error(f(459));
        var t = Kn;
        return Kn = null,
        t
    }
    function Ho(t) {
        if (t === Vn || t === ai)
            throw Error(f(483))
    }
    var jl = !1;
    function Xc(t) {
        t.updateQueue = {
            baseState: t.memoizedState,
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
    function Qc(t, e) {
        t = t.updateQueue,
        e.updateQueue === t && (e.updateQueue = {
            baseState: t.baseState,
            firstBaseUpdate: t.firstBaseUpdate,
            lastBaseUpdate: t.lastBaseUpdate,
            shared: t.shared,
            callbacks: null
        })
    }
    function Yl(t) {
        return {
            lane: t,
            tag: 0,
            payload: null,
            callback: null,
            next: null
        }
    }
    function ql(t, e, l) {
        var a = t.updateQueue;
        if (a === null)
            return null;
        if (a = a.shared,
        (gt & 2) !== 0) {
            var n = a.pending;
            return n === null ? e.next = e : (e.next = n.next,
            n.next = e),
            a.pending = e,
            e = Wu(t),
            Eo(t, null, l),
            e
        }
        return $u(t, a, e, l),
        Wu(t)
    }
    function Jn(t, e, l) {
        if (e = e.updateQueue,
        e !== null && (e = e.shared,
        (l & 4194048) !== 0)) {
            var a = e.lanes;
            a &= t.pendingLanes,
            l |= a,
            e.lanes = l,
            Bt(t, l)
        }
    }
    function Zc(t, e) {
        var l = t.updateQueue
          , a = t.alternate;
        if (a !== null && (a = a.updateQueue,
        l === a)) {
            var n = null
              , u = null;
            if (l = l.firstBaseUpdate,
            l !== null) {
                do {
                    var c = {
                        lane: l.lane,
                        tag: l.tag,
                        payload: l.payload,
                        callback: null,
                        next: null
                    };
                    u === null ? n = u = c : u = u.next = c,
                    l = l.next
                } while (l !== null);
                u === null ? n = u = e : u = u.next = e
            } else
                n = u = e;
            l = {
                baseState: a.baseState,
                firstBaseUpdate: n,
                lastBaseUpdate: u,
                shared: a.shared,
                callbacks: a.callbacks
            },
            t.updateQueue = l;
            return
        }
        t = l.lastBaseUpdate,
        t === null ? l.firstBaseUpdate = e : t.next = e,
        l.lastBaseUpdate = e
    }
    var Vc = !1;
    function kn() {
        if (Vc) {
            var t = Fa;
            if (t !== null)
                throw t
        }
    }
    function $n(t, e, l, a) {
        Vc = !1;
        var n = t.updateQueue;
        jl = !1;
        var u = n.firstBaseUpdate
          , c = n.lastBaseUpdate
          , r = n.shared.pending;
        if (r !== null) {
            n.shared.pending = null;
            var m = r
              , T = m.next;
            m.next = null,
            c === null ? u = T : c.next = T,
            c = m;
            var N = t.alternate;
            N !== null && (N = N.updateQueue,
            r = N.lastBaseUpdate,
            r !== c && (r === null ? N.firstBaseUpdate = T : r.next = T,
            N.lastBaseUpdate = m))
        }
        if (u !== null) {
            var L = n.baseState;
            c = 0,
            N = T = m = null,
            r = u;
            do {
                var A = r.lane & -536870913
                  , _ = A !== r.lane;
                if (_ ? (mt & A) === A : (a & A) === A) {
                    A !== 0 && A === Wa && (Vc = !0),
                    N !== null && (N = N.next = {
                        lane: 0,
                        tag: r.tag,
                        payload: r.payload,
                        callback: null,
                        next: null
                    });
                    t: {
                        var it = t
                          , lt = r;
                        A = e;
                        var Tt = l;
                        switch (lt.tag) {
                        case 1:
                            if (it = lt.payload,
                            typeof it == "function") {
                                L = it.call(Tt, L, A);
                                break t
                            }
                            L = it;
                            break t;
                        case 3:
                            it.flags = it.flags & -65537 | 128;
                        case 0:
                            if (it = lt.payload,
                            A = typeof it == "function" ? it.call(Tt, L, A) : it,
                            A == null)
                                break t;
                            L = R({}, L, A);
                            break t;
                        case 2:
                            jl = !0
                        }
                    }
                    A = r.callback,
                    A !== null && (t.flags |= 64,
                    _ && (t.flags |= 8192),
                    _ = n.callbacks,
                    _ === null ? n.callbacks = [A] : _.push(A))
                } else
                    _ = {
                        lane: A,
                        tag: r.tag,
                        payload: r.payload,
                        callback: r.callback,
                        next: null
                    },
                    N === null ? (T = N = _,
                    m = L) : N = N.next = _,
                    c |= A;
                if (r = r.next,
                r === null) {
                    if (r = n.shared.pending,
                    r === null)
                        break;
                    _ = r,
                    r = _.next,
                    _.next = null,
                    n.lastBaseUpdate = _,
                    n.shared.pending = null
                }
            } while (!0);
            N === null && (m = L),
            n.baseState = m,
            n.firstBaseUpdate = T,
            n.lastBaseUpdate = N,
            u === null && (n.shared.lanes = 0),
            kl |= c,
            t.lanes = c,
            t.memoizedState = L
        }
    }
    function wo(t, e) {
        if (typeof t != "function")
            throw Error(f(191, t));
        t.call(e)
    }
    function Bo(t, e) {
        var l = t.callbacks;
        if (l !== null)
            for (t.callbacks = null,
            t = 0; t < l.length; t++)
                wo(l[t], e)
    }
    var Ia = z(null)
      , ui = z(0);
    function jo(t, e) {
        t = Rl,
        j(ui, t),
        j(Ia, e),
        Rl = t | e.baseLanes
    }
    function Kc() {
        j(ui, Rl),
        j(Ia, Ia.current)
    }
    function Jc() {
        Rl = ui.current,
        V(Ia),
        V(ui)
    }
    var Gl = 0
      , ft = null
      , bt = null
      , Qt = null
      , ii = !1
      , Pa = !1
      , _a = !1
      , ci = 0
      , Wn = 0
      , tn = null
      , fy = 0;
    function jt() {
        throw Error(f(321))
    }
    function kc(t, e) {
        if (e === null)
            return !1;
        for (var l = 0; l < e.length && l < t.length; l++)
            if (!_e(t[l], e[l]))
                return !1;
        return !0
    }
    function $c(t, e, l, a, n, u) {
        return Gl = u,
        ft = e,
        e.memoizedState = null,
        e.updateQueue = null,
        e.lanes = 0,
        M.H = t === null || t.memoizedState === null ? Es : Ts,
        _a = !1,
        u = l(a, n),
        _a = !1,
        Pa && (u = qo(e, l, a, n)),
        Yo(t),
        u
    }
    function Yo(t) {
        M.H = mi;
        var e = bt !== null && bt.next !== null;
        if (Gl = 0,
        Qt = bt = ft = null,
        ii = !1,
        Wn = 0,
        tn = null,
        e)
            throw Error(f(300));
        t === null || Wt || (t = t.dependencies,
        t !== null && ti(t) && (Wt = !0))
    }
    function qo(t, e, l, a) {
        ft = t;
        var n = 0;
        do {
            if (Pa && (tn = null),
            Wn = 0,
            Pa = !1,
            25 <= n)
                throw Error(f(301));
            if (n += 1,
            Qt = bt = null,
            t.updateQueue != null) {
                var u = t.updateQueue;
                u.lastEffect = null,
                u.events = null,
                u.stores = null,
                u.memoCache != null && (u.memoCache.index = 0)
            }
            M.H = yy,
            u = e(l, a)
        } while (Pa);
        return u
    }
    function ry() {
        var t = M.H
          , e = t.useState()[0];
        return e = typeof e.then == "function" ? Fn(e) : e,
        t = t.useState()[0],
        (bt !== null ? bt.memoizedState : null) !== t && (ft.flags |= 1024),
        e
    }
    function Wc() {
        var t = ci !== 0;
        return ci = 0,
        t
    }
    function Fc(t, e, l) {
        e.updateQueue = t.updateQueue,
        e.flags &= -2053,
        t.lanes &= ~l
    }
    function Ic(t) {
        if (ii) {
            for (t = t.memoizedState; t !== null; ) {
                var e = t.queue;
                e !== null && (e.pending = null),
                t = t.next
            }
            ii = !1
        }
        Gl = 0,
        Qt = bt = ft = null,
        Pa = !1,
        Wn = ci = 0,
        tn = null
    }
    function be() {
        var t = {
            memoizedState: null,
            baseState: null,
            baseQueue: null,
            queue: null,
            next: null
        };
        return Qt === null ? ft.memoizedState = Qt = t : Qt = Qt.next = t,
        Qt
    }
    function Zt() {
        if (bt === null) {
            var t = ft.alternate;
            t = t !== null ? t.memoizedState : null
        } else
            t = bt.next;
        var e = Qt === null ? ft.memoizedState : Qt.next;
        if (e !== null)
            Qt = e,
            bt = t;
        else {
            if (t === null)
                throw ft.alternate === null ? Error(f(467)) : Error(f(310));
            bt = t,
            t = {
                memoizedState: bt.memoizedState,
                baseState: bt.baseState,
                baseQueue: bt.baseQueue,
                queue: bt.queue,
                next: null
            },
            Qt === null ? ft.memoizedState = Qt = t : Qt = Qt.next = t
        }
        return Qt
    }
    function Pc() {
        return {
            lastEffect: null,
            events: null,
            stores: null,
            memoCache: null
        }
    }
    function Fn(t) {
        var e = Wn;
        return Wn += 1,
        tn === null && (tn = []),
        t = Co(tn, t, e),
        e = ft,
        (Qt === null ? e.memoizedState : Qt.next) === null && (e = e.alternate,
        M.H = e === null || e.memoizedState === null ? Es : Ts),
        t
    }
    function fi(t) {
        if (t !== null && typeof t == "object") {
            if (typeof t.then == "function")
                return Fn(t);
            if (t.$$typeof === Q)
                return oe(t)
        }
        throw Error(f(438, String(t)))
    }
    function tf(t) {
        var e = null
          , l = ft.updateQueue;
        if (l !== null && (e = l.memoCache),
        e == null) {
            var a = ft.alternate;
            a !== null && (a = a.updateQueue,
            a !== null && (a = a.memoCache,
            a != null && (e = {
                data: a.data.map(function(n) {
                    return n.slice()
                }),
                index: 0
            })))
        }
        if (e == null && (e = {
            data: [],
            index: 0
        }),
        l === null && (l = Pc(),
        ft.updateQueue = l),
        l.memoCache = e,
        l = e.data[e.index],
        l === void 0)
            for (l = e.data[e.index] = Array(t),
            a = 0; a < t; a++)
                l[a] = Pt;
        return e.index++,
        l
    }
    function bl(t, e) {
        return typeof e == "function" ? e(t) : e
    }
    function ri(t) {
        var e = Zt();
        return ef(e, bt, t)
    }
    function ef(t, e, l) {
        var a = t.queue;
        if (a === null)
            throw Error(f(311));
        a.lastRenderedReducer = l;
        var n = t.baseQueue
          , u = a.pending;
        if (u !== null) {
            if (n !== null) {
                var c = n.next;
                n.next = u.next,
                u.next = c
            }
            e.baseQueue = n = u,
            a.pending = null
        }
        if (u = t.baseState,
        n === null)
            t.memoizedState = u;
        else {
            e = n.next;
            var r = c = null
              , m = null
              , T = e
              , N = !1;
            do {
                var L = T.lane & -536870913;
                if (L !== T.lane ? (mt & L) === L : (Gl & L) === L) {
                    var A = T.revertLane;
                    if (A === 0)
                        m !== null && (m = m.next = {
                            lane: 0,
                            revertLane: 0,
                            action: T.action,
                            hasEagerState: T.hasEagerState,
                            eagerState: T.eagerState,
                            next: null
                        }),
                        L === Wa && (N = !0);
                    else if ((Gl & A) === A) {
                        T = T.next,
                        A === Wa && (N = !0);
                        continue
                    } else
                        L = {
                            lane: 0,
                            revertLane: T.revertLane,
                            action: T.action,
                            hasEagerState: T.hasEagerState,
                            eagerState: T.eagerState,
                            next: null
                        },
                        m === null ? (r = m = L,
                        c = u) : m = m.next = L,
                        ft.lanes |= A,
                        kl |= A;
                    L = T.action,
                    _a && l(u, L),
                    u = T.hasEagerState ? T.eagerState : l(u, L)
                } else
                    A = {
                        lane: L,
                        revertLane: T.revertLane,
                        action: T.action,
                        hasEagerState: T.hasEagerState,
                        eagerState: T.eagerState,
                        next: null
                    },
                    m === null ? (r = m = A,
                    c = u) : m = m.next = A,
                    ft.lanes |= L,
                    kl |= L;
                T = T.next
            } while (T !== null && T !== e);
            if (m === null ? c = u : m.next = r,
            !_e(u, t.memoizedState) && (Wt = !0,
            N && (l = Fa,
            l !== null)))
                throw l;
            t.memoizedState = u,
            t.baseState = c,
            t.baseQueue = m,
            a.lastRenderedState = u
        }
        return n === null && (a.lanes = 0),
        [t.memoizedState, a.dispatch]
    }
    function lf(t) {
        var e = Zt()
          , l = e.queue;
        if (l === null)
            throw Error(f(311));
        l.lastRenderedReducer = t;
        var a = l.dispatch
          , n = l.pending
          , u = e.memoizedState;
        if (n !== null) {
            l.pending = null;
            var c = n = n.next;
            do
                u = t(u, c.action),
                c = c.next;
            while (c !== n);
            _e(u, e.memoizedState) || (Wt = !0),
            e.memoizedState = u,
            e.baseQueue === null && (e.baseState = u),
            l.lastRenderedState = u
        }
        return [u, a]
    }
    function Go(t, e, l) {
        var a = ft
          , n = Zt()
          , u = yt;
        if (u) {
            if (l === void 0)
                throw Error(f(407));
            l = l()
        } else
            l = e();
        var c = !_e((bt || n).memoizedState, l);
        c && (n.memoizedState = l,
        Wt = !0),
        n = n.queue;
        var r = Zo.bind(null, a, n, t);
        if (In(2048, 8, r, [t]),
        n.getSnapshot !== e || c || Qt !== null && Qt.memoizedState.tag & 1) {
            if (a.flags |= 2048,
            en(9, oi(), Qo.bind(null, a, n, l, e), null),
            Ot === null)
                throw Error(f(349));
            u || (Gl & 124) !== 0 || Xo(a, e, l)
        }
        return l
    }
    function Xo(t, e, l) {
        t.flags |= 16384,
        t = {
            getSnapshot: e,
            value: l
        },
        e = ft.updateQueue,
        e === null ? (e = Pc(),
        ft.updateQueue = e,
        e.stores = [t]) : (l = e.stores,
        l === null ? e.stores = [t] : l.push(t))
    }
    function Qo(t, e, l, a) {
        e.value = l,
        e.getSnapshot = a,
        Vo(e) && Ko(t)
    }
    function Zo(t, e, l) {
        return l(function() {
            Vo(e) && Ko(t)
        })
    }
    function Vo(t) {
        var e = t.getSnapshot;
        t = t.value;
        try {
            var l = e();
            return !_e(t, l)
        } catch {
            return !0
        }
    }
    function Ko(t) {
        var e = Ka(t, 2);
        e !== null && Ne(e, t, 2)
    }
    function af(t) {
        var e = be();
        if (typeof t == "function") {
            var l = t;
            if (t = l(),
            _a) {
                Ke(!0);
                try {
                    l()
                } finally {
                    Ke(!1)
                }
            }
        }
        return e.memoizedState = e.baseState = t,
        e.queue = {
            pending: null,
            lanes: 0,
            dispatch: null,
            lastRenderedReducer: bl,
            lastRenderedState: t
        },
        e
    }
    function Jo(t, e, l, a) {
        return t.baseState = l,
        ef(t, bt, typeof a == "function" ? a : bl)
    }
    function oy(t, e, l, a, n) {
        if (di(t))
            throw Error(f(485));
        if (t = e.action,
        t !== null) {
            var u = {
                payload: n,
                action: t,
                next: null,
                isTransition: !0,
                status: "pending",
                value: null,
                reason: null,
                listeners: [],
                then: function(c) {
                    u.listeners.push(c)
                }
            };
            M.T !== null ? l(!0) : u.isTransition = !1,
            a(u),
            l = e.pending,
            l === null ? (u.next = e.pending = u,
            ko(e, u)) : (u.next = l.next,
            e.pending = l.next = u)
        }
    }
    function ko(t, e) {
        var l = e.action
          , a = e.payload
          , n = t.state;
        if (e.isTransition) {
            var u = M.T
              , c = {};
            M.T = c;
            try {
                var r = l(n, a)
                  , m = M.S;
                m !== null && m(c, r),
                $o(t, e, r)
            } catch (T) {
                nf(t, e, T)
            } finally {
                M.T = u
            }
        } else
            try {
                u = l(n, a),
                $o(t, e, u)
            } catch (T) {
                nf(t, e, T)
            }
    }
    function $o(t, e, l) {
        l !== null && typeof l == "object" && typeof l.then == "function" ? l.then(function(a) {
            Wo(t, e, a)
        }, function(a) {
            return nf(t, e, a)
        }) : Wo(t, e, l)
    }
    function Wo(t, e, l) {
        e.status = "fulfilled",
        e.value = l,
        Fo(e),
        t.state = l,
        e = t.pending,
        e !== null && (l = e.next,
        l === e ? t.pending = null : (l = l.next,
        e.next = l,
        ko(t, l)))
    }
    function nf(t, e, l) {
        var a = t.pending;
        if (t.pending = null,
        a !== null) {
            a = a.next;
            do
                e.status = "rejected",
                e.reason = l,
                Fo(e),
                e = e.next;
            while (e !== a)
        }
        t.action = null
    }
    function Fo(t) {
        t = t.listeners;
        for (var e = 0; e < t.length; e++)
            (0,
            t[e])()
    }
    function Io(t, e) {
        return e
    }
    function Po(t, e) {
        if (yt) {
            var l = Ot.formState;
            if (l !== null) {
                t: {
                    var a = ft;
                    if (yt) {
                        if (Ht) {
                            e: {
                                for (var n = Ht, u = ll; n.nodeType !== 8; ) {
                                    if (!u) {
                                        n = null;
                                        break e
                                    }
                                    if (n = Fe(n.nextSibling),
                                    n === null) {
                                        n = null;
                                        break e
                                    }
                                }
                                u = n.data,
                                n = u === "F!" || u === "F" ? n : null
                            }
                            if (n) {
                                Ht = Fe(n.nextSibling),
                                a = n.data === "F!";
                                break t
                            }
                        }
                        ba(a)
                    }
                    a = !1
                }
                a && (e = l[0])
            }
        }
        return l = be(),
        l.memoizedState = l.baseState = e,
        a = {
            pending: null,
            lanes: 0,
            dispatch: null,
            lastRenderedReducer: Io,
            lastRenderedState: e
        },
        l.queue = a,
        l = ps.bind(null, ft, a),
        a.dispatch = l,
        a = af(!1),
        u = of.bind(null, ft, !1, a.queue),
        a = be(),
        n = {
            state: e,
            dispatch: null,
            action: t,
            pending: null
        },
        a.queue = n,
        l = oy.bind(null, ft, n, u, l),
        n.dispatch = l,
        a.memoizedState = t,
        [e, l, !1]
    }
    function ts(t) {
        var e = Zt();
        return es(e, bt, t)
    }
    function es(t, e, l) {
        if (e = ef(t, e, Io)[0],
        t = ri(bl)[0],
        typeof e == "object" && e !== null && typeof e.then == "function")
            try {
                var a = Fn(e)
            } catch (c) {
                throw c === Vn ? ai : c
            }
        else
            a = e;
        e = Zt();
        var n = e.queue
          , u = n.dispatch;
        return l !== e.memoizedState && (ft.flags |= 2048,
        en(9, oi(), sy.bind(null, n, l), null)),
        [a, u, t]
    }
    function sy(t, e) {
        t.action = e
    }
    function ls(t) {
        var e = Zt()
          , l = bt;
        if (l !== null)
            return es(e, l, t);
        Zt(),
        e = e.memoizedState,
        l = Zt();
        var a = l.queue.dispatch;
        return l.memoizedState = t,
        [e, a, !1]
    }
    function en(t, e, l, a) {
        return t = {
            tag: t,
            create: l,
            deps: a,
            inst: e,
            next: null
        },
        e = ft.updateQueue,
        e === null && (e = Pc(),
        ft.updateQueue = e),
        l = e.lastEffect,
        l === null ? e.lastEffect = t.next = t : (a = l.next,
        l.next = t,
        t.next = a,
        e.lastEffect = t),
        t
    }
    function oi() {
        return {
            destroy: void 0,
            resource: void 0
        }
    }
    function as() {
        return Zt().memoizedState
    }
    function si(t, e, l, a) {
        var n = be();
        a = a === void 0 ? null : a,
        ft.flags |= t,
        n.memoizedState = en(1 | e, oi(), l, a)
    }
    function In(t, e, l, a) {
        var n = Zt();
        a = a === void 0 ? null : a;
        var u = n.memoizedState.inst;
        bt !== null && a !== null && kc(a, bt.memoizedState.deps) ? n.memoizedState = en(e, u, l, a) : (ft.flags |= t,
        n.memoizedState = en(1 | e, u, l, a))
    }
    function ns(t, e) {
        si(8390656, 8, t, e)
    }
    function us(t, e) {
        In(2048, 8, t, e)
    }
    function is(t, e) {
        return In(4, 2, t, e)
    }
    function cs(t, e) {
        return In(4, 4, t, e)
    }
    function fs(t, e) {
        if (typeof e == "function") {
            t = t();
            var l = e(t);
            return function() {
                typeof l == "function" ? l() : e(null)
            }
        }
        if (e != null)
            return t = t(),
            e.current = t,
            function() {
                e.current = null
            }
    }
    function rs(t, e, l) {
        l = l != null ? l.concat([t]) : null,
        In(4, 4, fs.bind(null, e, t), l)
    }
    function uf() {}
    function os(t, e) {
        var l = Zt();
        e = e === void 0 ? null : e;
        var a = l.memoizedState;
        return e !== null && kc(e, a[1]) ? a[0] : (l.memoizedState = [t, e],
        t)
    }
    function ss(t, e) {
        var l = Zt();
        e = e === void 0 ? null : e;
        var a = l.memoizedState;
        if (e !== null && kc(e, a[1]))
            return a[0];
        if (a = t(),
        _a) {
            Ke(!0);
            try {
                t()
            } finally {
                Ke(!1)
            }
        }
        return l.memoizedState = [a, e],
        a
    }
    function cf(t, e, l) {
        return l === void 0 || (Gl & 1073741824) !== 0 ? t.memoizedState = e : (t.memoizedState = l,
        t = hd(),
        ft.lanes |= t,
        kl |= t,
        l)
    }
    function ds(t, e, l, a) {
        return _e(l, e) ? l : Ia.current !== null ? (t = cf(t, l, a),
        _e(t, e) || (Wt = !0),
        t) : (Gl & 42) === 0 ? (Wt = !0,
        t.memoizedState = l) : (t = hd(),
        ft.lanes |= t,
        kl |= t,
        e)
    }
    function ms(t, e, l, a, n) {
        var u = X.p;
        X.p = u !== 0 && 8 > u ? u : 8;
        var c = M.T
          , r = {};
        M.T = r,
        of(t, !1, e, l);
        try {
            var m = n()
              , T = M.S;
            if (T !== null && T(r, m),
            m !== null && typeof m == "object" && typeof m.then == "function") {
                var N = cy(m, a);
                Pn(t, e, N, Me(t))
            } else
                Pn(t, e, a, Me(t))
        } catch (L) {
            Pn(t, e, {
                then: function() {},
                status: "rejected",
                reason: L
            }, Me())
        } finally {
            X.p = u,
            M.T = c
        }
    }
    function dy() {}
    function ff(t, e, l, a) {
        if (t.tag !== 5)
            throw Error(f(476));
        var n = hs(t).queue;
        ms(t, n, e, tt, l === null ? dy : function() {
            return ys(t),
            l(a)
        }
        )
    }
    function hs(t) {
        var e = t.memoizedState;
        if (e !== null)
            return e;
        e = {
            memoizedState: tt,
            baseState: tt,
            baseQueue: null,
            queue: {
                pending: null,
                lanes: 0,
                dispatch: null,
                lastRenderedReducer: bl,
                lastRenderedState: tt
            },
            next: null
        };
        var l = {};
        return e.next = {
            memoizedState: l,
            baseState: l,
            baseQueue: null,
            queue: {
                pending: null,
                lanes: 0,
                dispatch: null,
                lastRenderedReducer: bl,
                lastRenderedState: l
            },
            next: null
        },
        t.memoizedState = e,
        t = t.alternate,
        t !== null && (t.memoizedState = e),
        e
    }
    function ys(t) {
        var e = hs(t).next.queue;
        Pn(t, e, {}, Me())
    }
    function rf() {
        return oe(gu)
    }
    function vs() {
        return Zt().memoizedState
    }
    function gs() {
        return Zt().memoizedState
    }
    function my(t) {
        for (var e = t.return; e !== null; ) {
            switch (e.tag) {
            case 24:
            case 3:
                var l = Me();
                t = Yl(l);
                var a = ql(e, t, l);
                a !== null && (Ne(a, e, l),
                Jn(a, e, l)),
                e = {
                    cache: jc()
                },
                t.payload = e;
                return
            }
            e = e.return
        }
    }
    function hy(t, e, l) {
        var a = Me();
        l = {
            lane: a,
            revertLane: 0,
            action: l,
            hasEagerState: !1,
            eagerState: null,
            next: null
        },
        di(t) ? Ss(e, l) : (l = Dc(t, e, l, a),
        l !== null && (Ne(l, t, a),
        bs(l, e, a)))
    }
    function ps(t, e, l) {
        var a = Me();
        Pn(t, e, l, a)
    }
    function Pn(t, e, l, a) {
        var n = {
            lane: a,
            revertLane: 0,
            action: l,
            hasEagerState: !1,
            eagerState: null,
            next: null
        };
        if (di(t))
            Ss(e, n);
        else {
            var u = t.alternate;
            if (t.lanes === 0 && (u === null || u.lanes === 0) && (u = e.lastRenderedReducer,
            u !== null))
                try {
                    var c = e.lastRenderedState
                      , r = u(c, l);
                    if (n.hasEagerState = !0,
                    n.eagerState = r,
                    _e(r, c))
                        return $u(t, e, n, 0),
                        Ot === null && ku(),
                        !1
                } catch {} finally {}
            if (l = Dc(t, e, n, a),
            l !== null)
                return Ne(l, t, a),
                bs(l, e, a),
                !0
        }
        return !1
    }
    function of(t, e, l, a) {
        if (a = {
            lane: 2,
            revertLane: Xf(),
            action: a,
            hasEagerState: !1,
            eagerState: null,
            next: null
        },
        di(t)) {
            if (e)
                throw Error(f(479))
        } else
            e = Dc(t, l, a, 2),
            e !== null && Ne(e, t, 2)
    }
    function di(t) {
        var e = t.alternate;
        return t === ft || e !== null && e === ft
    }
    function Ss(t, e) {
        Pa = ii = !0;
        var l = t.pending;
        l === null ? e.next = e : (e.next = l.next,
        l.next = e),
        t.pending = e
    }
    function bs(t, e, l) {
        if ((l & 4194048) !== 0) {
            var a = e.lanes;
            a &= t.pendingLanes,
            l |= a,
            e.lanes = l,
            Bt(t, l)
        }
    }
    var mi = {
        readContext: oe,
        use: fi,
        useCallback: jt,
        useContext: jt,
        useEffect: jt,
        useImperativeHandle: jt,
        useLayoutEffect: jt,
        useInsertionEffect: jt,
        useMemo: jt,
        useReducer: jt,
        useRef: jt,
        useState: jt,
        useDebugValue: jt,
        useDeferredValue: jt,
        useTransition: jt,
        useSyncExternalStore: jt,
        useId: jt,
        useHostTransitionStatus: jt,
        useFormState: jt,
        useActionState: jt,
        useOptimistic: jt,
        useMemoCache: jt,
        useCacheRefresh: jt
    }
      , Es = {
        readContext: oe,
        use: fi,
        useCallback: function(t, e) {
            return be().memoizedState = [t, e === void 0 ? null : e],
            t
        },
        useContext: oe,
        useEffect: ns,
        useImperativeHandle: function(t, e, l) {
            l = l != null ? l.concat([t]) : null,
            si(4194308, 4, fs.bind(null, e, t), l)
        },
        useLayoutEffect: function(t, e) {
            return si(4194308, 4, t, e)
        },
        useInsertionEffect: function(t, e) {
            si(4, 2, t, e)
        },
        useMemo: function(t, e) {
            var l = be();
            e = e === void 0 ? null : e;
            var a = t();
            if (_a) {
                Ke(!0);
                try {
                    t()
                } finally {
                    Ke(!1)
                }
            }
            return l.memoizedState = [a, e],
            a
        },
        useReducer: function(t, e, l) {
            var a = be();
            if (l !== void 0) {
                var n = l(e);
                if (_a) {
                    Ke(!0);
                    try {
                        l(e)
                    } finally {
                        Ke(!1)
                    }
                }
            } else
                n = e;
            return a.memoizedState = a.baseState = n,
            t = {
                pending: null,
                lanes: 0,
                dispatch: null,
                lastRenderedReducer: t,
                lastRenderedState: n
            },
            a.queue = t,
            t = t.dispatch = hy.bind(null, ft, t),
            [a.memoizedState, t]
        },
        useRef: function(t) {
            var e = be();
            return t = {
                current: t
            },
            e.memoizedState = t
        },
        useState: function(t) {
            t = af(t);
            var e = t.queue
              , l = ps.bind(null, ft, e);
            return e.dispatch = l,
            [t.memoizedState, l]
        },
        useDebugValue: uf,
        useDeferredValue: function(t, e) {
            var l = be();
            return cf(l, t, e)
        },
        useTransition: function() {
            var t = af(!1);
            return t = ms.bind(null, ft, t.queue, !0, !1),
            be().memoizedState = t,
            [!1, t]
        },
        useSyncExternalStore: function(t, e, l) {
            var a = ft
              , n = be();
            if (yt) {
                if (l === void 0)
                    throw Error(f(407));
                l = l()
            } else {
                if (l = e(),
                Ot === null)
                    throw Error(f(349));
                (mt & 124) !== 0 || Xo(a, e, l)
            }
            n.memoizedState = l;
            var u = {
                value: l,
                getSnapshot: e
            };
            return n.queue = u,
            ns(Zo.bind(null, a, u, t), [t]),
            a.flags |= 2048,
            en(9, oi(), Qo.bind(null, a, u, l, e), null),
            l
        },
        useId: function() {
            var t = be()
              , e = Ot.identifierPrefix;
            if (yt) {
                var l = gl
                  , a = vl;
                l = (a & ~(1 << 32 - ce(a) - 1)).toString(32) + l,
                e = "«" + e + "R" + l,
                l = ci++,
                0 < l && (e += "H" + l.toString(32)),
                e += "»"
            } else
                l = fy++,
                e = "«" + e + "r" + l.toString(32) + "»";
            return t.memoizedState = e
        },
        useHostTransitionStatus: rf,
        useFormState: Po,
        useActionState: Po,
        useOptimistic: function(t) {
            var e = be();
            e.memoizedState = e.baseState = t;
            var l = {
                pending: null,
                lanes: 0,
                dispatch: null,
                lastRenderedReducer: null,
                lastRenderedState: null
            };
            return e.queue = l,
            e = of.bind(null, ft, !0, l),
            l.dispatch = e,
            [t, e]
        },
        useMemoCache: tf,
        useCacheRefresh: function() {
            return be().memoizedState = my.bind(null, ft)
        }
    }
      , Ts = {
        readContext: oe,
        use: fi,
        useCallback: os,
        useContext: oe,
        useEffect: us,
        useImperativeHandle: rs,
        useInsertionEffect: is,
        useLayoutEffect: cs,
        useMemo: ss,
        useReducer: ri,
        useRef: as,
        useState: function() {
            return ri(bl)
        },
        useDebugValue: uf,
        useDeferredValue: function(t, e) {
            var l = Zt();
            return ds(l, bt.memoizedState, t, e)
        },
        useTransition: function() {
            var t = ri(bl)[0]
              , e = Zt().memoizedState;
            return [typeof t == "boolean" ? t : Fn(t), e]
        },
        useSyncExternalStore: Go,
        useId: vs,
        useHostTransitionStatus: rf,
        useFormState: ts,
        useActionState: ts,
        useOptimistic: function(t, e) {
            var l = Zt();
            return Jo(l, bt, t, e)
        },
        useMemoCache: tf,
        useCacheRefresh: gs
    }
      , yy = {
        readContext: oe,
        use: fi,
        useCallback: os,
        useContext: oe,
        useEffect: us,
        useImperativeHandle: rs,
        useInsertionEffect: is,
        useLayoutEffect: cs,
        useMemo: ss,
        useReducer: lf,
        useRef: as,
        useState: function() {
            return lf(bl)
        },
        useDebugValue: uf,
        useDeferredValue: function(t, e) {
            var l = Zt();
            return bt === null ? cf(l, t, e) : ds(l, bt.memoizedState, t, e)
        },
        useTransition: function() {
            var t = lf(bl)[0]
              , e = Zt().memoizedState;
            return [typeof t == "boolean" ? t : Fn(t), e]
        },
        useSyncExternalStore: Go,
        useId: vs,
        useHostTransitionStatus: rf,
        useFormState: ls,
        useActionState: ls,
        useOptimistic: function(t, e) {
            var l = Zt();
            return bt !== null ? Jo(l, bt, t, e) : (l.baseState = t,
            [t, l.queue.dispatch])
        },
        useMemoCache: tf,
        useCacheRefresh: gs
    }
      , ln = null
      , tu = 0;
    function hi(t) {
        var e = tu;
        return tu += 1,
        ln === null && (ln = []),
        Co(ln, t, e)
    }
    function eu(t, e) {
        e = e.props.ref,
        t.ref = e !== void 0 ? e : null
    }
    function yi(t, e) {
        throw e.$$typeof === Y ? Error(f(525)) : (t = Object.prototype.toString.call(e),
        Error(f(31, t === "[object Object]" ? "object with keys {" + Object.keys(e).join(", ") + "}" : t)))
    }
    function As(t) {
        var e = t._init;
        return e(t._payload)
    }
    function _s(t) {
        function e(b, g) {
            if (t) {
                var E = b.deletions;
                E === null ? (b.deletions = [g],
                b.flags |= 16) : E.push(g)
            }
        }
        function l(b, g) {
            if (!t)
                return null;
            for (; g !== null; )
                e(b, g),
                g = g.sibling;
            return null
        }
        function a(b) {
            for (var g = new Map; b !== null; )
                b.key !== null ? g.set(b.key, b) : g.set(b.index, b),
                b = b.sibling;
            return g
        }
        function n(b, g) {
            return b = yl(b, g),
            b.index = 0,
            b.sibling = null,
            b
        }
        function u(b, g, E) {
            return b.index = E,
            t ? (E = b.alternate,
            E !== null ? (E = E.index,
            E < g ? (b.flags |= 67108866,
            g) : E) : (b.flags |= 67108866,
            g)) : (b.flags |= 1048576,
            g)
        }
        function c(b) {
            return t && b.alternate === null && (b.flags |= 67108866),
            b
        }
        function r(b, g, E, U) {
            return g === null || g.tag !== 6 ? (g = Nc(E, b.mode, U),
            g.return = b,
            g) : (g = n(g, E),
            g.return = b,
            g)
        }
        function m(b, g, E, U) {
            var J = E.type;
            return J === H ? N(b, g, E.props.children, U, E.key) : g !== null && (g.elementType === J || typeof J == "object" && J !== null && J.$$typeof === Ut && As(J) === g.type) ? (g = n(g, E.props),
            eu(g, E),
            g.return = b,
            g) : (g = Fu(E.type, E.key, E.props, null, b.mode, U),
            eu(g, E),
            g.return = b,
            g)
        }
        function T(b, g, E, U) {
            return g === null || g.tag !== 4 || g.stateNode.containerInfo !== E.containerInfo || g.stateNode.implementation !== E.implementation ? (g = zc(E, b.mode, U),
            g.return = b,
            g) : (g = n(g, E.children || []),
            g.return = b,
            g)
        }
        function N(b, g, E, U, J) {
            return g === null || g.tag !== 7 ? (g = va(E, b.mode, U, J),
            g.return = b,
            g) : (g = n(g, E),
            g.return = b,
            g)
        }
        function L(b, g, E) {
            if (typeof g == "string" && g !== "" || typeof g == "number" || typeof g == "bigint")
                return g = Nc("" + g, b.mode, E),
                g.return = b,
                g;
            if (typeof g == "object" && g !== null) {
                switch (g.$$typeof) {
                case C:
                    return E = Fu(g.type, g.key, g.props, null, b.mode, E),
                    eu(E, g),
                    E.return = b,
                    E;
                case G:
                    return g = zc(g, b.mode, E),
                    g.return = b,
                    g;
                case Ut:
                    var U = g._init;
                    return g = U(g._payload),
                    L(b, g, E)
                }
                if ($t(g) || qt(g))
                    return g = va(g, b.mode, E, null),
                    g.return = b,
                    g;
                if (typeof g.then == "function")
                    return L(b, hi(g), E);
                if (g.$$typeof === Q)
                    return L(b, ei(b, g), E);
                yi(b, g)
            }
            return null
        }
        function A(b, g, E, U) {
            var J = g !== null ? g.key : null;
            if (typeof E == "string" && E !== "" || typeof E == "number" || typeof E == "bigint")
                return J !== null ? null : r(b, g, "" + E, U);
            if (typeof E == "object" && E !== null) {
                switch (E.$$typeof) {
                case C:
                    return E.key === J ? m(b, g, E, U) : null;
                case G:
                    return E.key === J ? T(b, g, E, U) : null;
                case Ut:
                    return J = E._init,
                    E = J(E._payload),
                    A(b, g, E, U)
                }
                if ($t(E) || qt(E))
                    return J !== null ? null : N(b, g, E, U, null);
                if (typeof E.then == "function")
                    return A(b, g, hi(E), U);
                if (E.$$typeof === Q)
                    return A(b, g, ei(b, E), U);
                yi(b, E)
            }
            return null
        }
        function _(b, g, E, U, J) {
            if (typeof U == "string" && U !== "" || typeof U == "number" || typeof U == "bigint")
                return b = b.get(E) || null,
                r(g, b, "" + U, J);
            if (typeof U == "object" && U !== null) {
                switch (U.$$typeof) {
                case C:
                    return b = b.get(U.key === null ? E : U.key) || null,
                    m(g, b, U, J);
                case G:
                    return b = b.get(U.key === null ? E : U.key) || null,
                    T(g, b, U, J);
                case Ut:
                    var rt = U._init;
                    return U = rt(U._payload),
                    _(b, g, E, U, J)
                }
                if ($t(U) || qt(U))
                    return b = b.get(E) || null,
                    N(g, b, U, J, null);
                if (typeof U.then == "function")
                    return _(b, g, E, hi(U), J);
                if (U.$$typeof === Q)
                    return _(b, g, E, ei(g, U), J);
                yi(g, U)
            }
            return null
        }
        function it(b, g, E, U) {
            for (var J = null, rt = null, W = g, nt = g = 0, It = null; W !== null && nt < E.length; nt++) {
                W.index > nt ? (It = W,
                W = null) : It = W.sibling;
                var ht = A(b, W, E[nt], U);
                if (ht === null) {
                    W === null && (W = It);
                    break
                }
                t && W && ht.alternate === null && e(b, W),
                g = u(ht, g, nt),
                rt === null ? J = ht : rt.sibling = ht,
                rt = ht,
                W = It
            }
            if (nt === E.length)
                return l(b, W),
                yt && pa(b, nt),
                J;
            if (W === null) {
                for (; nt < E.length; nt++)
                    W = L(b, E[nt], U),
                    W !== null && (g = u(W, g, nt),
                    rt === null ? J = W : rt.sibling = W,
                    rt = W);
                return yt && pa(b, nt),
                J
            }
            for (W = a(W); nt < E.length; nt++)
                It = _(W, b, nt, E[nt], U),
                It !== null && (t && It.alternate !== null && W.delete(It.key === null ? nt : It.key),
                g = u(It, g, nt),
                rt === null ? J = It : rt.sibling = It,
                rt = It);
            return t && W.forEach(function(aa) {
                return e(b, aa)
            }),
            yt && pa(b, nt),
            J
        }
        function lt(b, g, E, U) {
            if (E == null)
                throw Error(f(151));
            for (var J = null, rt = null, W = g, nt = g = 0, It = null, ht = E.next(); W !== null && !ht.done; nt++,
            ht = E.next()) {
                W.index > nt ? (It = W,
                W = null) : It = W.sibling;
                var aa = A(b, W, ht.value, U);
                if (aa === null) {
                    W === null && (W = It);
                    break
                }
                t && W && aa.alternate === null && e(b, W),
                g = u(aa, g, nt),
                rt === null ? J = aa : rt.sibling = aa,
                rt = aa,
                W = It
            }
            if (ht.done)
                return l(b, W),
                yt && pa(b, nt),
                J;
            if (W === null) {
                for (; !ht.done; nt++,
                ht = E.next())
                    ht = L(b, ht.value, U),
                    ht !== null && (g = u(ht, g, nt),
                    rt === null ? J = ht : rt.sibling = ht,
                    rt = ht);
                return yt && pa(b, nt),
                J
            }
            for (W = a(W); !ht.done; nt++,
            ht = E.next())
                ht = _(W, b, nt, ht.value, U),
                ht !== null && (t && ht.alternate !== null && W.delete(ht.key === null ? nt : ht.key),
                g = u(ht, g, nt),
                rt === null ? J = ht : rt.sibling = ht,
                rt = ht);
            return t && W.forEach(function(vv) {
                return e(b, vv)
            }),
            yt && pa(b, nt),
            J
        }
        function Tt(b, g, E, U) {
            if (typeof E == "object" && E !== null && E.type === H && E.key === null && (E = E.props.children),
            typeof E == "object" && E !== null) {
                switch (E.$$typeof) {
                case C:
                    t: {
                        for (var J = E.key; g !== null; ) {
                            if (g.key === J) {
                                if (J = E.type,
                                J === H) {
                                    if (g.tag === 7) {
                                        l(b, g.sibling),
                                        U = n(g, E.props.children),
                                        U.return = b,
                                        b = U;
                                        break t
                                    }
                                } else if (g.elementType === J || typeof J == "object" && J !== null && J.$$typeof === Ut && As(J) === g.type) {
                                    l(b, g.sibling),
                                    U = n(g, E.props),
                                    eu(U, E),
                                    U.return = b,
                                    b = U;
                                    break t
                                }
                                l(b, g);
                                break
                            } else
                                e(b, g);
                            g = g.sibling
                        }
                        E.type === H ? (U = va(E.props.children, b.mode, U, E.key),
                        U.return = b,
                        b = U) : (U = Fu(E.type, E.key, E.props, null, b.mode, U),
                        eu(U, E),
                        U.return = b,
                        b = U)
                    }
                    return c(b);
                case G:
                    t: {
                        for (J = E.key; g !== null; ) {
                            if (g.key === J)
                                if (g.tag === 4 && g.stateNode.containerInfo === E.containerInfo && g.stateNode.implementation === E.implementation) {
                                    l(b, g.sibling),
                                    U = n(g, E.children || []),
                                    U.return = b,
                                    b = U;
                                    break t
                                } else {
                                    l(b, g);
                                    break
                                }
                            else
                                e(b, g);
                            g = g.sibling
                        }
                        U = zc(E, b.mode, U),
                        U.return = b,
                        b = U
                    }
                    return c(b);
                case Ut:
                    return J = E._init,
                    E = J(E._payload),
                    Tt(b, g, E, U)
                }
                if ($t(E))
                    return it(b, g, E, U);
                if (qt(E)) {
                    if (J = qt(E),
                    typeof J != "function")
                        throw Error(f(150));
                    return E = J.call(E),
                    lt(b, g, E, U)
                }
                if (typeof E.then == "function")
                    return Tt(b, g, hi(E), U);
                if (E.$$typeof === Q)
                    return Tt(b, g, ei(b, E), U);
                yi(b, E)
            }
            return typeof E == "string" && E !== "" || typeof E == "number" || typeof E == "bigint" ? (E = "" + E,
            g !== null && g.tag === 6 ? (l(b, g.sibling),
            U = n(g, E),
            U.return = b,
            b = U) : (l(b, g),
            U = Nc(E, b.mode, U),
            U.return = b,
            b = U),
            c(b)) : l(b, g)
        }
        return function(b, g, E, U) {
            try {
                tu = 0;
                var J = Tt(b, g, E, U);
                return ln = null,
                J
            } catch (W) {
                if (W === Vn || W === ai)
                    throw W;
                var rt = xe(29, W, null, b.mode);
                return rt.lanes = U,
                rt.return = b,
                rt
            } finally {}
        }
    }
    var an = _s(!0)
      , xs = _s(!1)
      , Ye = z(null)
      , al = null;
    function Xl(t) {
        var e = t.alternate;
        j(kt, kt.current & 1),
        j(Ye, t),
        al === null && (e === null || Ia.current !== null || e.memoizedState !== null) && (al = t)
    }
    function Rs(t) {
        if (t.tag === 22) {
            if (j(kt, kt.current),
            j(Ye, t),
            al === null) {
                var e = t.alternate;
                e !== null && e.memoizedState !== null && (al = t)
            }
        } else
            Ql()
    }
    function Ql() {
        j(kt, kt.current),
        j(Ye, Ye.current)
    }
    function El(t) {
        V(Ye),
        al === t && (al = null),
        V(kt)
    }
    var kt = z(0);
    function vi(t) {
        for (var e = t; e !== null; ) {
            if (e.tag === 13) {
                var l = e.memoizedState;
                if (l !== null && (l = l.dehydrated,
                l === null || l.data === "$?" || tr(l)))
                    return e
            } else if (e.tag === 19 && e.memoizedProps.revealOrder !== void 0) {
                if ((e.flags & 128) !== 0)
                    return e
            } else if (e.child !== null) {
                e.child.return = e,
                e = e.child;
                continue
            }
            if (e === t)
                break;
            for (; e.sibling === null; ) {
                if (e.return === null || e.return === t)
                    return null;
                e = e.return
            }
            e.sibling.return = e.return,
            e = e.sibling
        }
        return null
    }
    function sf(t, e, l, a) {
        e = t.memoizedState,
        l = l(a, e),
        l = l == null ? e : R({}, e, l),
        t.memoizedState = l,
        t.lanes === 0 && (t.updateQueue.baseState = l)
    }
    var df = {
        enqueueSetState: function(t, e, l) {
            t = t._reactInternals;
            var a = Me()
              , n = Yl(a);
            n.payload = e,
            l != null && (n.callback = l),
            e = ql(t, n, a),
            e !== null && (Ne(e, t, a),
            Jn(e, t, a))
        },
        enqueueReplaceState: function(t, e, l) {
            t = t._reactInternals;
            var a = Me()
              , n = Yl(a);
            n.tag = 1,
            n.payload = e,
            l != null && (n.callback = l),
            e = ql(t, n, a),
            e !== null && (Ne(e, t, a),
            Jn(e, t, a))
        },
        enqueueForceUpdate: function(t, e) {
            t = t._reactInternals;
            var l = Me()
              , a = Yl(l);
            a.tag = 2,
            e != null && (a.callback = e),
            e = ql(t, a, l),
            e !== null && (Ne(e, t, l),
            Jn(e, t, l))
        }
    };
    function Os(t, e, l, a, n, u, c) {
        return t = t.stateNode,
        typeof t.shouldComponentUpdate == "function" ? t.shouldComponentUpdate(a, u, c) : e.prototype && e.prototype.isPureReactComponent ? !Bn(l, a) || !Bn(n, u) : !0
    }
    function Ds(t, e, l, a) {
        t = e.state,
        typeof e.componentWillReceiveProps == "function" && e.componentWillReceiveProps(l, a),
        typeof e.UNSAFE_componentWillReceiveProps == "function" && e.UNSAFE_componentWillReceiveProps(l, a),
        e.state !== t && df.enqueueReplaceState(e, e.state, null)
    }
    function xa(t, e) {
        var l = e;
        if ("ref"in e) {
            l = {};
            for (var a in e)
                a !== "ref" && (l[a] = e[a])
        }
        if (t = t.defaultProps) {
            l === e && (l = R({}, l));
            for (var n in t)
                l[n] === void 0 && (l[n] = t[n])
        }
        return l
    }
    var gi = typeof reportError == "function" ? reportError : function(t) {
        if (typeof window == "object" && typeof window.ErrorEvent == "function") {
            var e = new window.ErrorEvent("error",{
                bubbles: !0,
                cancelable: !0,
                message: typeof t == "object" && t !== null && typeof t.message == "string" ? String(t.message) : String(t),
                error: t
            });
            if (!window.dispatchEvent(e))
                return
        } else if (typeof process == "object" && typeof process.emit == "function") {
            process.emit("uncaughtException", t);
            return
        }
        console.error(t)
    }
    ;
    function Ms(t) {
        gi(t)
    }
    function Ns(t) {
        console.error(t)
    }
    function zs(t) {
        gi(t)
    }
    function pi(t, e) {
        try {
            var l = t.onUncaughtError;
            l(e.value, {
                componentStack: e.stack
            })
        } catch (a) {
            setTimeout(function() {
                throw a
            })
        }
    }
    function Us(t, e, l) {
        try {
            var a = t.onCaughtError;
            a(l.value, {
                componentStack: l.stack,
                errorBoundary: e.tag === 1 ? e.stateNode : null
            })
        } catch (n) {
            setTimeout(function() {
                throw n
            })
        }
    }
    function mf(t, e, l) {
        return l = Yl(l),
        l.tag = 3,
        l.payload = {
            element: null
        },
        l.callback = function() {
            pi(t, e)
        }
        ,
        l
    }
    function Cs(t) {
        return t = Yl(t),
        t.tag = 3,
        t
    }
    function Ls(t, e, l, a) {
        var n = l.type.getDerivedStateFromError;
        if (typeof n == "function") {
            var u = a.value;
            t.payload = function() {
                return n(u)
            }
            ,
            t.callback = function() {
                Us(e, l, a)
            }
        }
        var c = l.stateNode;
        c !== null && typeof c.componentDidCatch == "function" && (t.callback = function() {
            Us(e, l, a),
            typeof n != "function" && ($l === null ? $l = new Set([this]) : $l.add(this));
            var r = a.stack;
            this.componentDidCatch(a.value, {
                componentStack: r !== null ? r : ""
            })
        }
        )
    }
    function vy(t, e, l, a, n) {
        if (l.flags |= 32768,
        a !== null && typeof a == "object" && typeof a.then == "function") {
            if (e = l.alternate,
            e !== null && Xn(e, l, n, !0),
            l = Ye.current,
            l !== null) {
                switch (l.tag) {
                case 13:
                    return al === null ? Bf() : l.alternate === null && wt === 0 && (wt = 3),
                    l.flags &= -257,
                    l.flags |= 65536,
                    l.lanes = n,
                    a === Gc ? l.flags |= 16384 : (e = l.updateQueue,
                    e === null ? l.updateQueue = new Set([a]) : e.add(a),
                    Yf(t, a, n)),
                    !1;
                case 22:
                    return l.flags |= 65536,
                    a === Gc ? l.flags |= 16384 : (e = l.updateQueue,
                    e === null ? (e = {
                        transitions: null,
                        markerInstances: null,
                        retryQueue: new Set([a])
                    },
                    l.updateQueue = e) : (l = e.retryQueue,
                    l === null ? e.retryQueue = new Set([a]) : l.add(a)),
                    Yf(t, a, n)),
                    !1
                }
                throw Error(f(435, l.tag))
            }
            return Yf(t, a, n),
            Bf(),
            !1
        }
        if (yt)
            return e = Ye.current,
            e !== null ? ((e.flags & 65536) === 0 && (e.flags |= 256),
            e.flags |= 65536,
            e.lanes = n,
            a !== Lc && (t = Error(f(422), {
                cause: a
            }),
            Gn(He(t, l)))) : (a !== Lc && (e = Error(f(423), {
                cause: a
            }),
            Gn(He(e, l))),
            t = t.current.alternate,
            t.flags |= 65536,
            n &= -n,
            t.lanes |= n,
            a = He(a, l),
            n = mf(t.stateNode, a, n),
            Zc(t, n),
            wt !== 4 && (wt = 2)),
            !1;
        var u = Error(f(520), {
            cause: a
        });
        if (u = He(u, l),
        fu === null ? fu = [u] : fu.push(u),
        wt !== 4 && (wt = 2),
        e === null)
            return !0;
        a = He(a, l),
        l = e;
        do {
            switch (l.tag) {
            case 3:
                return l.flags |= 65536,
                t = n & -n,
                l.lanes |= t,
                t = mf(l.stateNode, a, t),
                Zc(l, t),
                !1;
            case 1:
                if (e = l.type,
                u = l.stateNode,
                (l.flags & 128) === 0 && (typeof e.getDerivedStateFromError == "function" || u !== null && typeof u.componentDidCatch == "function" && ($l === null || !$l.has(u))))
                    return l.flags |= 65536,
                    n &= -n,
                    l.lanes |= n,
                    n = Cs(n),
                    Ls(n, t, l, a),
                    Zc(l, n),
                    !1
            }
            l = l.return
        } while (l !== null);
        return !1
    }
    var Hs = Error(f(461))
      , Wt = !1;
    function ae(t, e, l, a) {
        e.child = t === null ? xs(e, null, l, a) : an(e, t.child, l, a)
    }
    function ws(t, e, l, a, n) {
        l = l.render;
        var u = e.ref;
        if ("ref"in a) {
            var c = {};
            for (var r in a)
                r !== "ref" && (c[r] = a[r])
        } else
            c = a;
        return Ta(e),
        a = $c(t, e, l, c, u, n),
        r = Wc(),
        t !== null && !Wt ? (Fc(t, e, n),
        Tl(t, e, n)) : (yt && r && Uc(e),
        e.flags |= 1,
        ae(t, e, a, n),
        e.child)
    }
    function Bs(t, e, l, a, n) {
        if (t === null) {
            var u = l.type;
            return typeof u == "function" && !Mc(u) && u.defaultProps === void 0 && l.compare === null ? (e.tag = 15,
            e.type = u,
            js(t, e, u, a, n)) : (t = Fu(l.type, null, a, e, e.mode, n),
            t.ref = e.ref,
            t.return = e,
            e.child = t)
        }
        if (u = t.child,
        !Ef(t, n)) {
            var c = u.memoizedProps;
            if (l = l.compare,
            l = l !== null ? l : Bn,
            l(c, a) && t.ref === e.ref)
                return Tl(t, e, n)
        }
        return e.flags |= 1,
        t = yl(u, a),
        t.ref = e.ref,
        t.return = e,
        e.child = t
    }
    function js(t, e, l, a, n) {
        if (t !== null) {
            var u = t.memoizedProps;
            if (Bn(u, a) && t.ref === e.ref)
                if (Wt = !1,
                e.pendingProps = a = u,
                Ef(t, n))
                    (t.flags & 131072) !== 0 && (Wt = !0);
                else
                    return e.lanes = t.lanes,
                    Tl(t, e, n)
        }
        return hf(t, e, l, a, n)
    }
    function Ys(t, e, l) {
        var a = e.pendingProps
          , n = a.children
          , u = t !== null ? t.memoizedState : null;
        if (a.mode === "hidden") {
            if ((e.flags & 128) !== 0) {
                if (a = u !== null ? u.baseLanes | l : l,
                t !== null) {
                    for (n = e.child = t.child,
                    u = 0; n !== null; )
                        u = u | n.lanes | n.childLanes,
                        n = n.sibling;
                    e.childLanes = u & ~a
                } else
                    e.childLanes = 0,
                    e.child = null;
                return qs(t, e, a, l)
            }
            if ((l & 536870912) !== 0)
                e.memoizedState = {
                    baseLanes: 0,
                    cachePool: null
                },
                t !== null && li(e, u !== null ? u.cachePool : null),
                u !== null ? jo(e, u) : Kc(),
                Rs(e);
            else
                return e.lanes = e.childLanes = 536870912,
                qs(t, e, u !== null ? u.baseLanes | l : l, l)
        } else
            u !== null ? (li(e, u.cachePool),
            jo(e, u),
            Ql(),
            e.memoizedState = null) : (t !== null && li(e, null),
            Kc(),
            Ql());
        return ae(t, e, n, l),
        e.child
    }
    function qs(t, e, l, a) {
        var n = qc();
        return n = n === null ? null : {
            parent: Jt._currentValue,
            pool: n
        },
        e.memoizedState = {
            baseLanes: l,
            cachePool: n
        },
        t !== null && li(e, null),
        Kc(),
        Rs(e),
        t !== null && Xn(t, e, a, !0),
        null
    }
    function Si(t, e) {
        var l = e.ref;
        if (l === null)
            t !== null && t.ref !== null && (e.flags |= 4194816);
        else {
            if (typeof l != "function" && typeof l != "object")
                throw Error(f(284));
            (t === null || t.ref !== l) && (e.flags |= 4194816)
        }
    }
    function hf(t, e, l, a, n) {
        return Ta(e),
        l = $c(t, e, l, a, void 0, n),
        a = Wc(),
        t !== null && !Wt ? (Fc(t, e, n),
        Tl(t, e, n)) : (yt && a && Uc(e),
        e.flags |= 1,
        ae(t, e, l, n),
        e.child)
    }
    function Gs(t, e, l, a, n, u) {
        return Ta(e),
        e.updateQueue = null,
        l = qo(e, a, l, n),
        Yo(t),
        a = Wc(),
        t !== null && !Wt ? (Fc(t, e, u),
        Tl(t, e, u)) : (yt && a && Uc(e),
        e.flags |= 1,
        ae(t, e, l, u),
        e.child)
    }
    function Xs(t, e, l, a, n) {
        if (Ta(e),
        e.stateNode === null) {
            var u = Ja
              , c = l.contextType;
            typeof c == "object" && c !== null && (u = oe(c)),
            u = new l(a,u),
            e.memoizedState = u.state !== null && u.state !== void 0 ? u.state : null,
            u.updater = df,
            e.stateNode = u,
            u._reactInternals = e,
            u = e.stateNode,
            u.props = a,
            u.state = e.memoizedState,
            u.refs = {},
            Xc(e),
            c = l.contextType,
            u.context = typeof c == "object" && c !== null ? oe(c) : Ja,
            u.state = e.memoizedState,
            c = l.getDerivedStateFromProps,
            typeof c == "function" && (sf(e, l, c, a),
            u.state = e.memoizedState),
            typeof l.getDerivedStateFromProps == "function" || typeof u.getSnapshotBeforeUpdate == "function" || typeof u.UNSAFE_componentWillMount != "function" && typeof u.componentWillMount != "function" || (c = u.state,
            typeof u.componentWillMount == "function" && u.componentWillMount(),
            typeof u.UNSAFE_componentWillMount == "function" && u.UNSAFE_componentWillMount(),
            c !== u.state && df.enqueueReplaceState(u, u.state, null),
            $n(e, a, u, n),
            kn(),
            u.state = e.memoizedState),
            typeof u.componentDidMount == "function" && (e.flags |= 4194308),
            a = !0
        } else if (t === null) {
            u = e.stateNode;
            var r = e.memoizedProps
              , m = xa(l, r);
            u.props = m;
            var T = u.context
              , N = l.contextType;
            c = Ja,
            typeof N == "object" && N !== null && (c = oe(N));
            var L = l.getDerivedStateFromProps;
            N = typeof L == "function" || typeof u.getSnapshotBeforeUpdate == "function",
            r = e.pendingProps !== r,
            N || typeof u.UNSAFE_componentWillReceiveProps != "function" && typeof u.componentWillReceiveProps != "function" || (r || T !== c) && Ds(e, u, a, c),
            jl = !1;
            var A = e.memoizedState;
            u.state = A,
            $n(e, a, u, n),
            kn(),
            T = e.memoizedState,
            r || A !== T || jl ? (typeof L == "function" && (sf(e, l, L, a),
            T = e.memoizedState),
            (m = jl || Os(e, l, m, a, A, T, c)) ? (N || typeof u.UNSAFE_componentWillMount != "function" && typeof u.componentWillMount != "function" || (typeof u.componentWillMount == "function" && u.componentWillMount(),
            typeof u.UNSAFE_componentWillMount == "function" && u.UNSAFE_componentWillMount()),
            typeof u.componentDidMount == "function" && (e.flags |= 4194308)) : (typeof u.componentDidMount == "function" && (e.flags |= 4194308),
            e.memoizedProps = a,
            e.memoizedState = T),
            u.props = a,
            u.state = T,
            u.context = c,
            a = m) : (typeof u.componentDidMount == "function" && (e.flags |= 4194308),
            a = !1)
        } else {
            u = e.stateNode,
            Qc(t, e),
            c = e.memoizedProps,
            N = xa(l, c),
            u.props = N,
            L = e.pendingProps,
            A = u.context,
            T = l.contextType,
            m = Ja,
            typeof T == "object" && T !== null && (m = oe(T)),
            r = l.getDerivedStateFromProps,
            (T = typeof r == "function" || typeof u.getSnapshotBeforeUpdate == "function") || typeof u.UNSAFE_componentWillReceiveProps != "function" && typeof u.componentWillReceiveProps != "function" || (c !== L || A !== m) && Ds(e, u, a, m),
            jl = !1,
            A = e.memoizedState,
            u.state = A,
            $n(e, a, u, n),
            kn();
            var _ = e.memoizedState;
            c !== L || A !== _ || jl || t !== null && t.dependencies !== null && ti(t.dependencies) ? (typeof r == "function" && (sf(e, l, r, a),
            _ = e.memoizedState),
            (N = jl || Os(e, l, N, a, A, _, m) || t !== null && t.dependencies !== null && ti(t.dependencies)) ? (T || typeof u.UNSAFE_componentWillUpdate != "function" && typeof u.componentWillUpdate != "function" || (typeof u.componentWillUpdate == "function" && u.componentWillUpdate(a, _, m),
            typeof u.UNSAFE_componentWillUpdate == "function" && u.UNSAFE_componentWillUpdate(a, _, m)),
            typeof u.componentDidUpdate == "function" && (e.flags |= 4),
            typeof u.getSnapshotBeforeUpdate == "function" && (e.flags |= 1024)) : (typeof u.componentDidUpdate != "function" || c === t.memoizedProps && A === t.memoizedState || (e.flags |= 4),
            typeof u.getSnapshotBeforeUpdate != "function" || c === t.memoizedProps && A === t.memoizedState || (e.flags |= 1024),
            e.memoizedProps = a,
            e.memoizedState = _),
            u.props = a,
            u.state = _,
            u.context = m,
            a = N) : (typeof u.componentDidUpdate != "function" || c === t.memoizedProps && A === t.memoizedState || (e.flags |= 4),
            typeof u.getSnapshotBeforeUpdate != "function" || c === t.memoizedProps && A === t.memoizedState || (e.flags |= 1024),
            a = !1)
        }
        return u = a,
        Si(t, e),
        a = (e.flags & 128) !== 0,
        u || a ? (u = e.stateNode,
        l = a && typeof l.getDerivedStateFromError != "function" ? null : u.render(),
        e.flags |= 1,
        t !== null && a ? (e.child = an(e, t.child, null, n),
        e.child = an(e, null, l, n)) : ae(t, e, l, n),
        e.memoizedState = u.state,
        t = e.child) : t = Tl(t, e, n),
        t
    }
    function Qs(t, e, l, a) {
        return qn(),
        e.flags |= 256,
        ae(t, e, l, a),
        e.child
    }
    var yf = {
        dehydrated: null,
        treeContext: null,
        retryLane: 0,
        hydrationErrors: null
    };
    function vf(t) {
        return {
            baseLanes: t,
            cachePool: No()
        }
    }
    function gf(t, e, l) {
        return t = t !== null ? t.childLanes & ~l : 0,
        e && (t |= qe),
        t
    }
    function Zs(t, e, l) {
        var a = e.pendingProps, n = !1, u = (e.flags & 128) !== 0, c;
        if ((c = u) || (c = t !== null && t.memoizedState === null ? !1 : (kt.current & 2) !== 0),
        c && (n = !0,
        e.flags &= -129),
        c = (e.flags & 32) !== 0,
        e.flags &= -33,
        t === null) {
            if (yt) {
                if (n ? Xl(e) : Ql(),
                yt) {
                    var r = Ht, m;
                    if (m = r) {
                        t: {
                            for (m = r,
                            r = ll; m.nodeType !== 8; ) {
                                if (!r) {
                                    r = null;
                                    break t
                                }
                                if (m = Fe(m.nextSibling),
                                m === null) {
                                    r = null;
                                    break t
                                }
                            }
                            r = m
                        }
                        r !== null ? (e.memoizedState = {
                            dehydrated: r,
                            treeContext: ga !== null ? {
                                id: vl,
                                overflow: gl
                            } : null,
                            retryLane: 536870912,
                            hydrationErrors: null
                        },
                        m = xe(18, null, null, 0),
                        m.stateNode = r,
                        m.return = e,
                        e.child = m,
                        me = e,
                        Ht = null,
                        m = !0) : m = !1
                    }
                    m || ba(e)
                }
                if (r = e.memoizedState,
                r !== null && (r = r.dehydrated,
                r !== null))
                    return tr(r) ? e.lanes = 32 : e.lanes = 536870912,
                    null;
                El(e)
            }
            return r = a.children,
            a = a.fallback,
            n ? (Ql(),
            n = e.mode,
            r = bi({
                mode: "hidden",
                children: r
            }, n),
            a = va(a, n, l, null),
            r.return = e,
            a.return = e,
            r.sibling = a,
            e.child = r,
            n = e.child,
            n.memoizedState = vf(l),
            n.childLanes = gf(t, c, l),
            e.memoizedState = yf,
            a) : (Xl(e),
            pf(e, r))
        }
        if (m = t.memoizedState,
        m !== null && (r = m.dehydrated,
        r !== null)) {
            if (u)
                e.flags & 256 ? (Xl(e),
                e.flags &= -257,
                e = Sf(t, e, l)) : e.memoizedState !== null ? (Ql(),
                e.child = t.child,
                e.flags |= 128,
                e = null) : (Ql(),
                n = a.fallback,
                r = e.mode,
                a = bi({
                    mode: "visible",
                    children: a.children
                }, r),
                n = va(n, r, l, null),
                n.flags |= 2,
                a.return = e,
                n.return = e,
                a.sibling = n,
                e.child = a,
                an(e, t.child, null, l),
                a = e.child,
                a.memoizedState = vf(l),
                a.childLanes = gf(t, c, l),
                e.memoizedState = yf,
                e = n);
            else if (Xl(e),
            tr(r)) {
                if (c = r.nextSibling && r.nextSibling.dataset,
                c)
                    var T = c.dgst;
                c = T,
                a = Error(f(419)),
                a.stack = "",
                a.digest = c,
                Gn({
                    value: a,
                    source: null,
                    stack: null
                }),
                e = Sf(t, e, l)
            } else if (Wt || Xn(t, e, l, !1),
            c = (l & t.childLanes) !== 0,
            Wt || c) {
                if (c = Ot,
                c !== null && (a = l & -l,
                a = (a & 42) !== 0 ? 1 : tl(a),
                a = (a & (c.suspendedLanes | l)) !== 0 ? 0 : a,
                a !== 0 && a !== m.retryLane))
                    throw m.retryLane = a,
                    Ka(t, a),
                    Ne(c, t, a),
                    Hs;
                r.data === "$?" || Bf(),
                e = Sf(t, e, l)
            } else
                r.data === "$?" ? (e.flags |= 192,
                e.child = t.child,
                e = null) : (t = m.treeContext,
                Ht = Fe(r.nextSibling),
                me = e,
                yt = !0,
                Sa = null,
                ll = !1,
                t !== null && (Be[je++] = vl,
                Be[je++] = gl,
                Be[je++] = ga,
                vl = t.id,
                gl = t.overflow,
                ga = e),
                e = pf(e, a.children),
                e.flags |= 4096);
            return e
        }
        return n ? (Ql(),
        n = a.fallback,
        r = e.mode,
        m = t.child,
        T = m.sibling,
        a = yl(m, {
            mode: "hidden",
            children: a.children
        }),
        a.subtreeFlags = m.subtreeFlags & 65011712,
        T !== null ? n = yl(T, n) : (n = va(n, r, l, null),
        n.flags |= 2),
        n.return = e,
        a.return = e,
        a.sibling = n,
        e.child = a,
        a = n,
        n = e.child,
        r = t.child.memoizedState,
        r === null ? r = vf(l) : (m = r.cachePool,
        m !== null ? (T = Jt._currentValue,
        m = m.parent !== T ? {
            parent: T,
            pool: T
        } : m) : m = No(),
        r = {
            baseLanes: r.baseLanes | l,
            cachePool: m
        }),
        n.memoizedState = r,
        n.childLanes = gf(t, c, l),
        e.memoizedState = yf,
        a) : (Xl(e),
        l = t.child,
        t = l.sibling,
        l = yl(l, {
            mode: "visible",
            children: a.children
        }),
        l.return = e,
        l.sibling = null,
        t !== null && (c = e.deletions,
        c === null ? (e.deletions = [t],
        e.flags |= 16) : c.push(t)),
        e.child = l,
        e.memoizedState = null,
        l)
    }
    function pf(t, e) {
        return e = bi({
            mode: "visible",
            children: e
        }, t.mode),
        e.return = t,
        t.child = e
    }
    function bi(t, e) {
        return t = xe(22, t, null, e),
        t.lanes = 0,
        t.stateNode = {
            _visibility: 1,
            _pendingMarkers: null,
            _retryCache: null,
            _transitions: null
        },
        t
    }
    function Sf(t, e, l) {
        return an(e, t.child, null, l),
        t = pf(e, e.pendingProps.children),
        t.flags |= 2,
        e.memoizedState = null,
        t
    }
    function Vs(t, e, l) {
        t.lanes |= e;
        var a = t.alternate;
        a !== null && (a.lanes |= e),
        wc(t.return, e, l)
    }
    function bf(t, e, l, a, n) {
        var u = t.memoizedState;
        u === null ? t.memoizedState = {
            isBackwards: e,
            rendering: null,
            renderingStartTime: 0,
            last: a,
            tail: l,
            tailMode: n
        } : (u.isBackwards = e,
        u.rendering = null,
        u.renderingStartTime = 0,
        u.last = a,
        u.tail = l,
        u.tailMode = n)
    }
    function Ks(t, e, l) {
        var a = e.pendingProps
          , n = a.revealOrder
          , u = a.tail;
        if (ae(t, e, a.children, l),
        a = kt.current,
        (a & 2) !== 0)
            a = a & 1 | 2,
            e.flags |= 128;
        else {
            if (t !== null && (t.flags & 128) !== 0)
                t: for (t = e.child; t !== null; ) {
                    if (t.tag === 13)
                        t.memoizedState !== null && Vs(t, l, e);
                    else if (t.tag === 19)
                        Vs(t, l, e);
                    else if (t.child !== null) {
                        t.child.return = t,
                        t = t.child;
                        continue
                    }
                    if (t === e)
                        break t;
                    for (; t.sibling === null; ) {
                        if (t.return === null || t.return === e)
                            break t;
                        t = t.return
                    }
                    t.sibling.return = t.return,
                    t = t.sibling
                }
            a &= 1
        }
        switch (j(kt, a),
        n) {
        case "forwards":
            for (l = e.child,
            n = null; l !== null; )
                t = l.alternate,
                t !== null && vi(t) === null && (n = l),
                l = l.sibling;
            l = n,
            l === null ? (n = e.child,
            e.child = null) : (n = l.sibling,
            l.sibling = null),
            bf(e, !1, n, l, u);
            break;
        case "backwards":
            for (l = null,
            n = e.child,
            e.child = null; n !== null; ) {
                if (t = n.alternate,
                t !== null && vi(t) === null) {
                    e.child = n;
                    break
                }
                t = n.sibling,
                n.sibling = l,
                l = n,
                n = t
            }
            bf(e, !0, l, null, u);
            break;
        case "together":
            bf(e, !1, null, null, void 0);
            break;
        default:
            e.memoizedState = null
        }
        return e.child
    }
    function Tl(t, e, l) {
        if (t !== null && (e.dependencies = t.dependencies),
        kl |= e.lanes,
        (l & e.childLanes) === 0)
            if (t !== null) {
                if (Xn(t, e, l, !1),
                (l & e.childLanes) === 0)
                    return null
            } else
                return null;
        if (t !== null && e.child !== t.child)
            throw Error(f(153));
        if (e.child !== null) {
            for (t = e.child,
            l = yl(t, t.pendingProps),
            e.child = l,
            l.return = e; t.sibling !== null; )
                t = t.sibling,
                l = l.sibling = yl(t, t.pendingProps),
                l.return = e;
            l.sibling = null
        }
        return e.child
    }
    function Ef(t, e) {
        return (t.lanes & e) !== 0 ? !0 : (t = t.dependencies,
        !!(t !== null && ti(t)))
    }
    function gy(t, e, l) {
        switch (e.tag) {
        case 3:
            At(e, e.stateNode.containerInfo),
            Bl(e, Jt, t.memoizedState.cache),
            qn();
            break;
        case 27:
        case 5:
            za(e);
            break;
        case 4:
            At(e, e.stateNode.containerInfo);
            break;
        case 10:
            Bl(e, e.type, e.memoizedProps.value);
            break;
        case 13:
            var a = e.memoizedState;
            if (a !== null)
                return a.dehydrated !== null ? (Xl(e),
                e.flags |= 128,
                null) : (l & e.child.childLanes) !== 0 ? Zs(t, e, l) : (Xl(e),
                t = Tl(t, e, l),
                t !== null ? t.sibling : null);
            Xl(e);
            break;
        case 19:
            var n = (t.flags & 128) !== 0;
            if (a = (l & e.childLanes) !== 0,
            a || (Xn(t, e, l, !1),
            a = (l & e.childLanes) !== 0),
            n) {
                if (a)
                    return Ks(t, e, l);
                e.flags |= 128
            }
            if (n = e.memoizedState,
            n !== null && (n.rendering = null,
            n.tail = null,
            n.lastEffect = null),
            j(kt, kt.current),
            a)
                break;
            return null;
        case 22:
        case 23:
            return e.lanes = 0,
            Ys(t, e, l);
        case 24:
            Bl(e, Jt, t.memoizedState.cache)
        }
        return Tl(t, e, l)
    }
    function Js(t, e, l) {
        if (t !== null)
            if (t.memoizedProps !== e.pendingProps)
                Wt = !0;
            else {
                if (!Ef(t, l) && (e.flags & 128) === 0)
                    return Wt = !1,
                    gy(t, e, l);
                Wt = (t.flags & 131072) !== 0
            }
        else
            Wt = !1,
            yt && (e.flags & 1048576) !== 0 && Ao(e, Pu, e.index);
        switch (e.lanes = 0,
        e.tag) {
        case 16:
            t: {
                t = e.pendingProps;
                var a = e.elementType
                  , n = a._init;
                if (a = n(a._payload),
                e.type = a,
                typeof a == "function")
                    Mc(a) ? (t = xa(a, t),
                    e.tag = 1,
                    e = Xs(null, e, a, t, l)) : (e.tag = 0,
                    e = hf(null, e, a, t, l));
                else {
                    if (a != null) {
                        if (n = a.$$typeof,
                        n === ut) {
                            e.tag = 11,
                            e = ws(null, e, a, t, l);
                            break t
                        } else if (n === Nt) {
                            e.tag = 14,
                            e = Bs(null, e, a, t, l);
                            break t
                        }
                    }
                    throw e = Ze(a) || a,
                    Error(f(306, e, ""))
                }
            }
            return e;
        case 0:
            return hf(t, e, e.type, e.pendingProps, l);
        case 1:
            return a = e.type,
            n = xa(a, e.pendingProps),
            Xs(t, e, a, n, l);
        case 3:
            t: {
                if (At(e, e.stateNode.containerInfo),
                t === null)
                    throw Error(f(387));
                a = e.pendingProps;
                var u = e.memoizedState;
                n = u.element,
                Qc(t, e),
                $n(e, a, null, l);
                var c = e.memoizedState;
                if (a = c.cache,
                Bl(e, Jt, a),
                a !== u.cache && Bc(e, [Jt], l, !0),
                kn(),
                a = c.element,
                u.isDehydrated)
                    if (u = {
                        element: a,
                        isDehydrated: !1,
                        cache: c.cache
                    },
                    e.updateQueue.baseState = u,
                    e.memoizedState = u,
                    e.flags & 256) {
                        e = Qs(t, e, a, l);
                        break t
                    } else if (a !== n) {
                        n = He(Error(f(424)), e),
                        Gn(n),
                        e = Qs(t, e, a, l);
                        break t
                    } else {
                        switch (t = e.stateNode.containerInfo,
                        t.nodeType) {
                        case 9:
                            t = t.body;
                            break;
                        default:
                            t = t.nodeName === "HTML" ? t.ownerDocument.body : t
                        }
                        for (Ht = Fe(t.firstChild),
                        me = e,
                        yt = !0,
                        Sa = null,
                        ll = !0,
                        l = xs(e, null, a, l),
                        e.child = l; l; )
                            l.flags = l.flags & -3 | 4096,
                            l = l.sibling
                    }
                else {
                    if (qn(),
                    a === n) {
                        e = Tl(t, e, l);
                        break t
                    }
                    ae(t, e, a, l)
                }
                e = e.child
            }
            return e;
        case 26:
            return Si(t, e),
            t === null ? (l = Fd(e.type, null, e.pendingProps, null)) ? e.memoizedState = l : yt || (l = e.type,
            t = e.pendingProps,
            a = Li(at.current).createElement(l),
            a[Vt] = e,
            a[fe] = t,
            ue(a, l, t),
            Gt(a),
            e.stateNode = a) : e.memoizedState = Fd(e.type, t.memoizedProps, e.pendingProps, t.memoizedState),
            null;
        case 27:
            return za(e),
            t === null && yt && (a = e.stateNode = kd(e.type, e.pendingProps, at.current),
            me = e,
            ll = !0,
            n = Ht,
            Il(e.type) ? (er = n,
            Ht = Fe(a.firstChild)) : Ht = n),
            ae(t, e, e.pendingProps.children, l),
            Si(t, e),
            t === null && (e.flags |= 4194304),
            e.child;
        case 5:
            return t === null && yt && ((n = a = Ht) && (a = Vy(a, e.type, e.pendingProps, ll),
            a !== null ? (e.stateNode = a,
            me = e,
            Ht = Fe(a.firstChild),
            ll = !1,
            n = !0) : n = !1),
            n || ba(e)),
            za(e),
            n = e.type,
            u = e.pendingProps,
            c = t !== null ? t.memoizedProps : null,
            a = u.children,
            Ff(n, u) ? a = null : c !== null && Ff(n, c) && (e.flags |= 32),
            e.memoizedState !== null && (n = $c(t, e, ry, null, null, l),
            gu._currentValue = n),
            Si(t, e),
            ae(t, e, a, l),
            e.child;
        case 6:
            return t === null && yt && ((t = l = Ht) && (l = Ky(l, e.pendingProps, ll),
            l !== null ? (e.stateNode = l,
            me = e,
            Ht = null,
            t = !0) : t = !1),
            t || ba(e)),
            null;
        case 13:
            return Zs(t, e, l);
        case 4:
            return At(e, e.stateNode.containerInfo),
            a = e.pendingProps,
            t === null ? e.child = an(e, null, a, l) : ae(t, e, a, l),
            e.child;
        case 11:
            return ws(t, e, e.type, e.pendingProps, l);
        case 7:
            return ae(t, e, e.pendingProps, l),
            e.child;
        case 8:
            return ae(t, e, e.pendingProps.children, l),
            e.child;
        case 12:
            return ae(t, e, e.pendingProps.children, l),
            e.child;
        case 10:
            return a = e.pendingProps,
            Bl(e, e.type, a.value),
            ae(t, e, a.children, l),
            e.child;
        case 9:
            return n = e.type._context,
            a = e.pendingProps.children,
            Ta(e),
            n = oe(n),
            a = a(n),
            e.flags |= 1,
            ae(t, e, a, l),
            e.child;
        case 14:
            return Bs(t, e, e.type, e.pendingProps, l);
        case 15:
            return js(t, e, e.type, e.pendingProps, l);
        case 19:
            return Ks(t, e, l);
        case 31:
            return a = e.pendingProps,
            l = e.mode,
            a = {
                mode: a.mode,
                children: a.children
            },
            t === null ? (l = bi(a, l),
            l.ref = e.ref,
            e.child = l,
            l.return = e,
            e = l) : (l = yl(t.child, a),
            l.ref = e.ref,
            e.child = l,
            l.return = e,
            e = l),
            e;
        case 22:
            return Ys(t, e, l);
        case 24:
            return Ta(e),
            a = oe(Jt),
            t === null ? (n = qc(),
            n === null && (n = Ot,
            u = jc(),
            n.pooledCache = u,
            u.refCount++,
            u !== null && (n.pooledCacheLanes |= l),
            n = u),
            e.memoizedState = {
                parent: a,
                cache: n
            },
            Xc(e),
            Bl(e, Jt, n)) : ((t.lanes & l) !== 0 && (Qc(t, e),
            $n(e, null, null, l),
            kn()),
            n = t.memoizedState,
            u = e.memoizedState,
            n.parent !== a ? (n = {
                parent: a,
                cache: a
            },
            e.memoizedState = n,
            e.lanes === 0 && (e.memoizedState = e.updateQueue.baseState = n),
            Bl(e, Jt, a)) : (a = u.cache,
            Bl(e, Jt, a),
            a !== n.cache && Bc(e, [Jt], l, !0))),
            ae(t, e, e.pendingProps.children, l),
            e.child;
        case 29:
            throw e.pendingProps
        }
        throw Error(f(156, e.tag))
    }
    function Al(t) {
        t.flags |= 4
    }
    function ks(t, e) {
        if (e.type !== "stylesheet" || (e.state.loading & 4) !== 0)
            t.flags &= -16777217;
        else if (t.flags |= 16777216,
        !lm(e)) {
            if (e = Ye.current,
            e !== null && ((mt & 4194048) === mt ? al !== null : (mt & 62914560) !== mt && (mt & 536870912) === 0 || e !== al))
                throw Kn = Gc,
                zo;
            t.flags |= 8192
        }
    }
    function Ei(t, e) {
        e !== null && (t.flags |= 4),
        t.flags & 16384 && (e = t.tag !== 22 ? da() : 536870912,
        t.lanes |= e,
        fn |= e)
    }
    function lu(t, e) {
        if (!yt)
            switch (t.tailMode) {
            case "hidden":
                e = t.tail;
                for (var l = null; e !== null; )
                    e.alternate !== null && (l = e),
                    e = e.sibling;
                l === null ? t.tail = null : l.sibling = null;
                break;
            case "collapsed":
                l = t.tail;
                for (var a = null; l !== null; )
                    l.alternate !== null && (a = l),
                    l = l.sibling;
                a === null ? e || t.tail === null ? t.tail = null : t.tail.sibling = null : a.sibling = null
            }
    }
    function Ct(t) {
        var e = t.alternate !== null && t.alternate.child === t.child
          , l = 0
          , a = 0;
        if (e)
            for (var n = t.child; n !== null; )
                l |= n.lanes | n.childLanes,
                a |= n.subtreeFlags & 65011712,
                a |= n.flags & 65011712,
                n.return = t,
                n = n.sibling;
        else
            for (n = t.child; n !== null; )
                l |= n.lanes | n.childLanes,
                a |= n.subtreeFlags,
                a |= n.flags,
                n.return = t,
                n = n.sibling;
        return t.subtreeFlags |= a,
        t.childLanes = l,
        e
    }
    function py(t, e, l) {
        var a = e.pendingProps;
        switch (Cc(e),
        e.tag) {
        case 31:
        case 16:
        case 15:
        case 0:
        case 11:
        case 7:
        case 8:
        case 12:
        case 9:
        case 14:
            return Ct(e),
            null;
        case 1:
            return Ct(e),
            null;
        case 3:
            return l = e.stateNode,
            a = null,
            t !== null && (a = t.memoizedState.cache),
            e.memoizedState.cache !== a && (e.flags |= 2048),
            Sl(Jt),
            ze(),
            l.pendingContext && (l.context = l.pendingContext,
            l.pendingContext = null),
            (t === null || t.child === null) && (Yn(e) ? Al(e) : t === null || t.memoizedState.isDehydrated && (e.flags & 256) === 0 || (e.flags |= 1024,
            Ro())),
            Ct(e),
            null;
        case 26:
            return l = e.memoizedState,
            t === null ? (Al(e),
            l !== null ? (Ct(e),
            ks(e, l)) : (Ct(e),
            e.flags &= -16777217)) : l ? l !== t.memoizedState ? (Al(e),
            Ct(e),
            ks(e, l)) : (Ct(e),
            e.flags &= -16777217) : (t.memoizedProps !== a && Al(e),
            Ct(e),
            e.flags &= -16777217),
            null;
        case 27:
            ia(e),
            l = at.current;
            var n = e.type;
            if (t !== null && e.stateNode != null)
                t.memoizedProps !== a && Al(e);
            else {
                if (!a) {
                    if (e.stateNode === null)
                        throw Error(f(166));
                    return Ct(e),
                    null
                }
                t = F.current,
                Yn(e) ? _o(e) : (t = kd(n, a, l),
                e.stateNode = t,
                Al(e))
            }
            return Ct(e),
            null;
        case 5:
            if (ia(e),
            l = e.type,
            t !== null && e.stateNode != null)
                t.memoizedProps !== a && Al(e);
            else {
                if (!a) {
                    if (e.stateNode === null)
                        throw Error(f(166));
                    return Ct(e),
                    null
                }
                if (t = F.current,
                Yn(e))
                    _o(e);
                else {
                    switch (n = Li(at.current),
                    t) {
                    case 1:
                        t = n.createElementNS("http://www.w3.org/2000/svg", l);
                        break;
                    case 2:
                        t = n.createElementNS("http://www.w3.org/1998/Math/MathML", l);
                        break;
                    default:
                        switch (l) {
                        case "svg":
                            t = n.createElementNS("http://www.w3.org/2000/svg", l);
                            break;
                        case "math":
                            t = n.createElementNS("http://www.w3.org/1998/Math/MathML", l);
                            break;
                        case "script":
                            t = n.createElement("div"),
                            t.innerHTML = "<script><\/script>",
                            t = t.removeChild(t.firstChild);
                            break;
                        case "select":
                            t = typeof a.is == "string" ? n.createElement("select", {
                                is: a.is
                            }) : n.createElement("select"),
                            a.multiple ? t.multiple = !0 : a.size && (t.size = a.size);
                            break;
                        default:
                            t = typeof a.is == "string" ? n.createElement(l, {
                                is: a.is
                            }) : n.createElement(l)
                        }
                    }
                    t[Vt] = e,
                    t[fe] = a;
                    t: for (n = e.child; n !== null; ) {
                        if (n.tag === 5 || n.tag === 6)
                            t.appendChild(n.stateNode);
                        else if (n.tag !== 4 && n.tag !== 27 && n.child !== null) {
                            n.child.return = n,
                            n = n.child;
                            continue
                        }
                        if (n === e)
                            break t;
                        for (; n.sibling === null; ) {
                            if (n.return === null || n.return === e)
                                break t;
                            n = n.return
                        }
                        n.sibling.return = n.return,
                        n = n.sibling
                    }
                    e.stateNode = t;
                    t: switch (ue(t, l, a),
                    l) {
                    case "button":
                    case "input":
                    case "select":
                    case "textarea":
                        t = !!a.autoFocus;
                        break t;
                    case "img":
                        t = !0;
                        break t;
                    default:
                        t = !1
                    }
                    t && Al(e)
                }
            }
            return Ct(e),
            e.flags &= -16777217,
            null;
        case 6:
            if (t && e.stateNode != null)
                t.memoizedProps !== a && Al(e);
            else {
                if (typeof a != "string" && e.stateNode === null)
                    throw Error(f(166));
                if (t = at.current,
                Yn(e)) {
                    if (t = e.stateNode,
                    l = e.memoizedProps,
                    a = null,
                    n = me,
                    n !== null)
                        switch (n.tag) {
                        case 27:
                        case 5:
                            a = n.memoizedProps
                        }
                    t[Vt] = e,
                    t = !!(t.nodeValue === l || a !== null && a.suppressHydrationWarning === !0 || Gd(t.nodeValue, l)),
                    t || ba(e)
                } else
                    t = Li(t).createTextNode(a),
                    t[Vt] = e,
                    e.stateNode = t
            }
            return Ct(e),
            null;
        case 13:
            if (a = e.memoizedState,
            t === null || t.memoizedState !== null && t.memoizedState.dehydrated !== null) {
                if (n = Yn(e),
                a !== null && a.dehydrated !== null) {
                    if (t === null) {
                        if (!n)
                            throw Error(f(318));
                        if (n = e.memoizedState,
                        n = n !== null ? n.dehydrated : null,
                        !n)
                            throw Error(f(317));
                        n[Vt] = e
                    } else
                        qn(),
                        (e.flags & 128) === 0 && (e.memoizedState = null),
                        e.flags |= 4;
                    Ct(e),
                    n = !1
                } else
                    n = Ro(),
                    t !== null && t.memoizedState !== null && (t.memoizedState.hydrationErrors = n),
                    n = !0;
                if (!n)
                    return e.flags & 256 ? (El(e),
                    e) : (El(e),
                    null)
            }
            if (El(e),
            (e.flags & 128) !== 0)
                return e.lanes = l,
                e;
            if (l = a !== null,
            t = t !== null && t.memoizedState !== null,
            l) {
                a = e.child,
                n = null,
                a.alternate !== null && a.alternate.memoizedState !== null && a.alternate.memoizedState.cachePool !== null && (n = a.alternate.memoizedState.cachePool.pool);
                var u = null;
                a.memoizedState !== null && a.memoizedState.cachePool !== null && (u = a.memoizedState.cachePool.pool),
                u !== n && (a.flags |= 2048)
            }
            return l !== t && l && (e.child.flags |= 8192),
            Ei(e, e.updateQueue),
            Ct(e),
            null;
        case 4:
            return ze(),
            t === null && Kf(e.stateNode.containerInfo),
            Ct(e),
            null;
        case 10:
            return Sl(e.type),
            Ct(e),
            null;
        case 19:
            if (V(kt),
            n = e.memoizedState,
            n === null)
                return Ct(e),
                null;
            if (a = (e.flags & 128) !== 0,
            u = n.rendering,
            u === null)
                if (a)
                    lu(n, !1);
                else {
                    if (wt !== 0 || t !== null && (t.flags & 128) !== 0)
                        for (t = e.child; t !== null; ) {
                            if (u = vi(t),
                            u !== null) {
                                for (e.flags |= 128,
                                lu(n, !1),
                                t = u.updateQueue,
                                e.updateQueue = t,
                                Ei(e, t),
                                e.subtreeFlags = 0,
                                t = l,
                                l = e.child; l !== null; )
                                    To(l, t),
                                    l = l.sibling;
                                return j(kt, kt.current & 1 | 2),
                                e.child
                            }
                            t = t.sibling
                        }
                    n.tail !== null && ee() > _i && (e.flags |= 128,
                    a = !0,
                    lu(n, !1),
                    e.lanes = 4194304)
                }
            else {
                if (!a)
                    if (t = vi(u),
                    t !== null) {
                        if (e.flags |= 128,
                        a = !0,
                        t = t.updateQueue,
                        e.updateQueue = t,
                        Ei(e, t),
                        lu(n, !0),
                        n.tail === null && n.tailMode === "hidden" && !u.alternate && !yt)
                            return Ct(e),
                            null
                    } else
                        2 * ee() - n.renderingStartTime > _i && l !== 536870912 && (e.flags |= 128,
                        a = !0,
                        lu(n, !1),
                        e.lanes = 4194304);
                n.isBackwards ? (u.sibling = e.child,
                e.child = u) : (t = n.last,
                t !== null ? t.sibling = u : e.child = u,
                n.last = u)
            }
            return n.tail !== null ? (e = n.tail,
            n.rendering = e,
            n.tail = e.sibling,
            n.renderingStartTime = ee(),
            e.sibling = null,
            t = kt.current,
            j(kt, a ? t & 1 | 2 : t & 1),
            e) : (Ct(e),
            null);
        case 22:
        case 23:
            return El(e),
            Jc(),
            a = e.memoizedState !== null,
            t !== null ? t.memoizedState !== null !== a && (e.flags |= 8192) : a && (e.flags |= 8192),
            a ? (l & 536870912) !== 0 && (e.flags & 128) === 0 && (Ct(e),
            e.subtreeFlags & 6 && (e.flags |= 8192)) : Ct(e),
            l = e.updateQueue,
            l !== null && Ei(e, l.retryQueue),
            l = null,
            t !== null && t.memoizedState !== null && t.memoizedState.cachePool !== null && (l = t.memoizedState.cachePool.pool),
            a = null,
            e.memoizedState !== null && e.memoizedState.cachePool !== null && (a = e.memoizedState.cachePool.pool),
            a !== l && (e.flags |= 2048),
            t !== null && V(Aa),
            null;
        case 24:
            return l = null,
            t !== null && (l = t.memoizedState.cache),
            e.memoizedState.cache !== l && (e.flags |= 2048),
            Sl(Jt),
            Ct(e),
            null;
        case 25:
            return null;
        case 30:
            return null
        }
        throw Error(f(156, e.tag))
    }
    function Sy(t, e) {
        switch (Cc(e),
        e.tag) {
        case 1:
            return t = e.flags,
            t & 65536 ? (e.flags = t & -65537 | 128,
            e) : null;
        case 3:
            return Sl(Jt),
            ze(),
            t = e.flags,
            (t & 65536) !== 0 && (t & 128) === 0 ? (e.flags = t & -65537 | 128,
            e) : null;
        case 26:
        case 27:
        case 5:
            return ia(e),
            null;
        case 13:
            if (El(e),
            t = e.memoizedState,
            t !== null && t.dehydrated !== null) {
                if (e.alternate === null)
                    throw Error(f(340));
                qn()
            }
            return t = e.flags,
            t & 65536 ? (e.flags = t & -65537 | 128,
            e) : null;
        case 19:
            return V(kt),
            null;
        case 4:
            return ze(),
            null;
        case 10:
            return Sl(e.type),
            null;
        case 22:
        case 23:
            return El(e),
            Jc(),
            t !== null && V(Aa),
            t = e.flags,
            t & 65536 ? (e.flags = t & -65537 | 128,
            e) : null;
        case 24:
            return Sl(Jt),
            null;
        case 25:
            return null;
        default:
            return null
        }
    }
    function $s(t, e) {
        switch (Cc(e),
        e.tag) {
        case 3:
            Sl(Jt),
            ze();
            break;
        case 26:
        case 27:
        case 5:
            ia(e);
            break;
        case 4:
            ze();
            break;
        case 13:
            El(e);
            break;
        case 19:
            V(kt);
            break;
        case 10:
            Sl(e.type);
            break;
        case 22:
        case 23:
            El(e),
            Jc(),
            t !== null && V(Aa);
            break;
        case 24:
            Sl(Jt)
        }
    }
    function au(t, e) {
        try {
            var l = e.updateQueue
              , a = l !== null ? l.lastEffect : null;
            if (a !== null) {
                var n = a.next;
                l = n;
                do {
                    if ((l.tag & t) === t) {
                        a = void 0;
                        var u = l.create
                          , c = l.inst;
                        a = u(),
                        c.destroy = a
                    }
                    l = l.next
                } while (l !== n)
            }
        } catch (r) {
            _t(e, e.return, r)
        }
    }
    function Zl(t, e, l) {
        try {
            var a = e.updateQueue
              , n = a !== null ? a.lastEffect : null;
            if (n !== null) {
                var u = n.next;
                a = u;
                do {
                    if ((a.tag & t) === t) {
                        var c = a.inst
                          , r = c.destroy;
                        if (r !== void 0) {
                            c.destroy = void 0,
                            n = e;
                            var m = l
                              , T = r;
                            try {
                                T()
                            } catch (N) {
                                _t(n, m, N)
                            }
                        }
                    }
                    a = a.next
                } while (a !== u)
            }
        } catch (N) {
            _t(e, e.return, N)
        }
    }
    function Ws(t) {
        var e = t.updateQueue;
        if (e !== null) {
            var l = t.stateNode;
            try {
                Bo(e, l)
            } catch (a) {
                _t(t, t.return, a)
            }
        }
    }
    function Fs(t, e, l) {
        l.props = xa(t.type, t.memoizedProps),
        l.state = t.memoizedState;
        try {
            l.componentWillUnmount()
        } catch (a) {
            _t(t, e, a)
        }
    }
    function nu(t, e) {
        try {
            var l = t.ref;
            if (l !== null) {
                switch (t.tag) {
                case 26:
                case 27:
                case 5:
                    var a = t.stateNode;
                    break;
                case 30:
                    a = t.stateNode;
                    break;
                default:
                    a = t.stateNode
                }
                typeof l == "function" ? t.refCleanup = l(a) : l.current = a
            }
        } catch (n) {
            _t(t, e, n)
        }
    }
    function nl(t, e) {
        var l = t.ref
          , a = t.refCleanup;
        if (l !== null)
            if (typeof a == "function")
                try {
                    a()
                } catch (n) {
                    _t(t, e, n)
                } finally {
                    t.refCleanup = null,
                    t = t.alternate,
                    t != null && (t.refCleanup = null)
                }
            else if (typeof l == "function")
                try {
                    l(null)
                } catch (n) {
                    _t(t, e, n)
                }
            else
                l.current = null
    }
    function Is(t) {
        var e = t.type
          , l = t.memoizedProps
          , a = t.stateNode;
        try {
            t: switch (e) {
            case "button":
            case "input":
            case "select":
            case "textarea":
                l.autoFocus && a.focus();
                break t;
            case "img":
                l.src ? a.src = l.src : l.srcSet && (a.srcset = l.srcSet)
            }
        } catch (n) {
            _t(t, t.return, n)
        }
    }
    function Tf(t, e, l) {
        try {
            var a = t.stateNode;
            qy(a, t.type, l, e),
            a[fe] = e
        } catch (n) {
            _t(t, t.return, n)
        }
    }
    function Ps(t) {
        return t.tag === 5 || t.tag === 3 || t.tag === 26 || t.tag === 27 && Il(t.type) || t.tag === 4
    }
    function Af(t) {
        t: for (; ; ) {
            for (; t.sibling === null; ) {
                if (t.return === null || Ps(t.return))
                    return null;
                t = t.return
            }
            for (t.sibling.return = t.return,
            t = t.sibling; t.tag !== 5 && t.tag !== 6 && t.tag !== 18; ) {
                if (t.tag === 27 && Il(t.type) || t.flags & 2 || t.child === null || t.tag === 4)
                    continue t;
                t.child.return = t,
                t = t.child
            }
            if (!(t.flags & 2))
                return t.stateNode
        }
    }
    function _f(t, e, l) {
        var a = t.tag;
        if (a === 5 || a === 6)
            t = t.stateNode,
            e ? (l.nodeType === 9 ? l.body : l.nodeName === "HTML" ? l.ownerDocument.body : l).insertBefore(t, e) : (e = l.nodeType === 9 ? l.body : l.nodeName === "HTML" ? l.ownerDocument.body : l,
            e.appendChild(t),
            l = l._reactRootContainer,
            l != null || e.onclick !== null || (e.onclick = Ci));
        else if (a !== 4 && (a === 27 && Il(t.type) && (l = t.stateNode,
        e = null),
        t = t.child,
        t !== null))
            for (_f(t, e, l),
            t = t.sibling; t !== null; )
                _f(t, e, l),
                t = t.sibling
    }
    function Ti(t, e, l) {
        var a = t.tag;
        if (a === 5 || a === 6)
            t = t.stateNode,
            e ? l.insertBefore(t, e) : l.appendChild(t);
        else if (a !== 4 && (a === 27 && Il(t.type) && (l = t.stateNode),
        t = t.child,
        t !== null))
            for (Ti(t, e, l),
            t = t.sibling; t !== null; )
                Ti(t, e, l),
                t = t.sibling
    }
    function td(t) {
        var e = t.stateNode
          , l = t.memoizedProps;
        try {
            for (var a = t.type, n = e.attributes; n.length; )
                e.removeAttributeNode(n[0]);
            ue(e, a, l),
            e[Vt] = t,
            e[fe] = l
        } catch (u) {
            _t(t, t.return, u)
        }
    }
    var _l = !1
      , Yt = !1
      , xf = !1
      , ed = typeof WeakSet == "function" ? WeakSet : Set
      , Ft = null;
    function by(t, e) {
        if (t = t.containerInfo,
        $f = qi,
        t = so(t),
        Tc(t)) {
            if ("selectionStart"in t)
                var l = {
                    start: t.selectionStart,
                    end: t.selectionEnd
                };
            else
                t: {
                    l = (l = t.ownerDocument) && l.defaultView || window;
                    var a = l.getSelection && l.getSelection();
                    if (a && a.rangeCount !== 0) {
                        l = a.anchorNode;
                        var n = a.anchorOffset
                          , u = a.focusNode;
                        a = a.focusOffset;
                        try {
                            l.nodeType,
                            u.nodeType
                        } catch {
                            l = null;
                            break t
                        }
                        var c = 0
                          , r = -1
                          , m = -1
                          , T = 0
                          , N = 0
                          , L = t
                          , A = null;
                        e: for (; ; ) {
                            for (var _; L !== l || n !== 0 && L.nodeType !== 3 || (r = c + n),
                            L !== u || a !== 0 && L.nodeType !== 3 || (m = c + a),
                            L.nodeType === 3 && (c += L.nodeValue.length),
                            (_ = L.firstChild) !== null; )
                                A = L,
                                L = _;
                            for (; ; ) {
                                if (L === t)
                                    break e;
                                if (A === l && ++T === n && (r = c),
                                A === u && ++N === a && (m = c),
                                (_ = L.nextSibling) !== null)
                                    break;
                                L = A,
                                A = L.parentNode
                            }
                            L = _
                        }
                        l = r === -1 || m === -1 ? null : {
                            start: r,
                            end: m
                        }
                    } else
                        l = null
                }
            l = l || {
                start: 0,
                end: 0
            }
        } else
            l = null;
        for (Wf = {
            focusedElem: t,
            selectionRange: l
        },
        qi = !1,
        Ft = e; Ft !== null; )
            if (e = Ft,
            t = e.child,
            (e.subtreeFlags & 1024) !== 0 && t !== null)
                t.return = e,
                Ft = t;
            else
                for (; Ft !== null; ) {
                    switch (e = Ft,
                    u = e.alternate,
                    t = e.flags,
                    e.tag) {
                    case 0:
                        break;
                    case 11:
                    case 15:
                        break;
                    case 1:
                        if ((t & 1024) !== 0 && u !== null) {
                            t = void 0,
                            l = e,
                            n = u.memoizedProps,
                            u = u.memoizedState,
                            a = l.stateNode;
                            try {
                                var it = xa(l.type, n, l.elementType === l.type);
                                t = a.getSnapshotBeforeUpdate(it, u),
                                a.__reactInternalSnapshotBeforeUpdate = t
                            } catch (lt) {
                                _t(l, l.return, lt)
                            }
                        }
                        break;
                    case 3:
                        if ((t & 1024) !== 0) {
                            if (t = e.stateNode.containerInfo,
                            l = t.nodeType,
                            l === 9)
                                Pf(t);
                            else if (l === 1)
                                switch (t.nodeName) {
                                case "HEAD":
                                case "HTML":
                                case "BODY":
                                    Pf(t);
                                    break;
                                default:
                                    t.textContent = ""
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
                        if ((t & 1024) !== 0)
                            throw Error(f(163))
                    }
                    if (t = e.sibling,
                    t !== null) {
                        t.return = e.return,
                        Ft = t;
                        break
                    }
                    Ft = e.return
                }
    }
    function ld(t, e, l) {
        var a = l.flags;
        switch (l.tag) {
        case 0:
        case 11:
        case 15:
            Vl(t, l),
            a & 4 && au(5, l);
            break;
        case 1:
            if (Vl(t, l),
            a & 4)
                if (t = l.stateNode,
                e === null)
                    try {
                        t.componentDidMount()
                    } catch (c) {
                        _t(l, l.return, c)
                    }
                else {
                    var n = xa(l.type, e.memoizedProps);
                    e = e.memoizedState;
                    try {
                        t.componentDidUpdate(n, e, t.__reactInternalSnapshotBeforeUpdate)
                    } catch (c) {
                        _t(l, l.return, c)
                    }
                }
            a & 64 && Ws(l),
            a & 512 && nu(l, l.return);
            break;
        case 3:
            if (Vl(t, l),
            a & 64 && (t = l.updateQueue,
            t !== null)) {
                if (e = null,
                l.child !== null)
                    switch (l.child.tag) {
                    case 27:
                    case 5:
                        e = l.child.stateNode;
                        break;
                    case 1:
                        e = l.child.stateNode
                    }
                try {
                    Bo(t, e)
                } catch (c) {
                    _t(l, l.return, c)
                }
            }
            break;
        case 27:
            e === null && a & 4 && td(l);
        case 26:
        case 5:
            Vl(t, l),
            e === null && a & 4 && Is(l),
            a & 512 && nu(l, l.return);
            break;
        case 12:
            Vl(t, l);
            break;
        case 13:
            Vl(t, l),
            a & 4 && ud(t, l),
            a & 64 && (t = l.memoizedState,
            t !== null && (t = t.dehydrated,
            t !== null && (l = My.bind(null, l),
            Jy(t, l))));
            break;
        case 22:
            if (a = l.memoizedState !== null || _l,
            !a) {
                e = e !== null && e.memoizedState !== null || Yt,
                n = _l;
                var u = Yt;
                _l = a,
                (Yt = e) && !u ? Kl(t, l, (l.subtreeFlags & 8772) !== 0) : Vl(t, l),
                _l = n,
                Yt = u
            }
            break;
        case 30:
            break;
        default:
            Vl(t, l)
        }
    }
    function ad(t) {
        var e = t.alternate;
        e !== null && (t.alternate = null,
        ad(e)),
        t.child = null,
        t.deletions = null,
        t.sibling = null,
        t.tag === 5 && (e = t.stateNode,
        e !== null && Ae(e)),
        t.stateNode = null,
        t.return = null,
        t.dependencies = null,
        t.memoizedProps = null,
        t.memoizedState = null,
        t.pendingProps = null,
        t.stateNode = null,
        t.updateQueue = null
    }
    var zt = null
      , Ee = !1;
    function xl(t, e, l) {
        for (l = l.child; l !== null; )
            nd(t, e, l),
            l = l.sibling
    }
    function nd(t, e, l) {
        if (ie && typeof ie.onCommitFiberUnmount == "function")
            try {
                ie.onCommitFiberUnmount(Ue, l)
            } catch {}
        switch (l.tag) {
        case 26:
            Yt || nl(l, e),
            xl(t, e, l),
            l.memoizedState ? l.memoizedState.count-- : l.stateNode && (l = l.stateNode,
            l.parentNode.removeChild(l));
            break;
        case 27:
            Yt || nl(l, e);
            var a = zt
              , n = Ee;
            Il(l.type) && (zt = l.stateNode,
            Ee = !1),
            xl(t, e, l),
            mu(l.stateNode),
            zt = a,
            Ee = n;
            break;
        case 5:
            Yt || nl(l, e);
        case 6:
            if (a = zt,
            n = Ee,
            zt = null,
            xl(t, e, l),
            zt = a,
            Ee = n,
            zt !== null)
                if (Ee)
                    try {
                        (zt.nodeType === 9 ? zt.body : zt.nodeName === "HTML" ? zt.ownerDocument.body : zt).removeChild(l.stateNode)
                    } catch (u) {
                        _t(l, e, u)
                    }
                else
                    try {
                        zt.removeChild(l.stateNode)
                    } catch (u) {
                        _t(l, e, u)
                    }
            break;
        case 18:
            zt !== null && (Ee ? (t = zt,
            Kd(t.nodeType === 9 ? t.body : t.nodeName === "HTML" ? t.ownerDocument.body : t, l.stateNode),
            Eu(t)) : Kd(zt, l.stateNode));
            break;
        case 4:
            a = zt,
            n = Ee,
            zt = l.stateNode.containerInfo,
            Ee = !0,
            xl(t, e, l),
            zt = a,
            Ee = n;
            break;
        case 0:
        case 11:
        case 14:
        case 15:
            Yt || Zl(2, l, e),
            Yt || Zl(4, l, e),
            xl(t, e, l);
            break;
        case 1:
            Yt || (nl(l, e),
            a = l.stateNode,
            typeof a.componentWillUnmount == "function" && Fs(l, e, a)),
            xl(t, e, l);
            break;
        case 21:
            xl(t, e, l);
            break;
        case 22:
            Yt = (a = Yt) || l.memoizedState !== null,
            xl(t, e, l),
            Yt = a;
            break;
        default:
            xl(t, e, l)
        }
    }
    function ud(t, e) {
        if (e.memoizedState === null && (t = e.alternate,
        t !== null && (t = t.memoizedState,
        t !== null && (t = t.dehydrated,
        t !== null))))
            try {
                Eu(t)
            } catch (l) {
                _t(e, e.return, l)
            }
    }
    function Ey(t) {
        switch (t.tag) {
        case 13:
        case 19:
            var e = t.stateNode;
            return e === null && (e = t.stateNode = new ed),
            e;
        case 22:
            return t = t.stateNode,
            e = t._retryCache,
            e === null && (e = t._retryCache = new ed),
            e;
        default:
            throw Error(f(435, t.tag))
        }
    }
    function Rf(t, e) {
        var l = Ey(t);
        e.forEach(function(a) {
            var n = Ny.bind(null, t, a);
            l.has(a) || (l.add(a),
            a.then(n, n))
        })
    }
    function Re(t, e) {
        var l = e.deletions;
        if (l !== null)
            for (var a = 0; a < l.length; a++) {
                var n = l[a]
                  , u = t
                  , c = e
                  , r = c;
                t: for (; r !== null; ) {
                    switch (r.tag) {
                    case 27:
                        if (Il(r.type)) {
                            zt = r.stateNode,
                            Ee = !1;
                            break t
                        }
                        break;
                    case 5:
                        zt = r.stateNode,
                        Ee = !1;
                        break t;
                    case 3:
                    case 4:
                        zt = r.stateNode.containerInfo,
                        Ee = !0;
                        break t
                    }
                    r = r.return
                }
                if (zt === null)
                    throw Error(f(160));
                nd(u, c, n),
                zt = null,
                Ee = !1,
                u = n.alternate,
                u !== null && (u.return = null),
                n.return = null
            }
        if (e.subtreeFlags & 13878)
            for (e = e.child; e !== null; )
                id(e, t),
                e = e.sibling
    }
    var We = null;
    function id(t, e) {
        var l = t.alternate
          , a = t.flags;
        switch (t.tag) {
        case 0:
        case 11:
        case 14:
        case 15:
            Re(e, t),
            Oe(t),
            a & 4 && (Zl(3, t, t.return),
            au(3, t),
            Zl(5, t, t.return));
            break;
        case 1:
            Re(e, t),
            Oe(t),
            a & 512 && (Yt || l === null || nl(l, l.return)),
            a & 64 && _l && (t = t.updateQueue,
            t !== null && (a = t.callbacks,
            a !== null && (l = t.shared.hiddenCallbacks,
            t.shared.hiddenCallbacks = l === null ? a : l.concat(a))));
            break;
        case 26:
            var n = We;
            if (Re(e, t),
            Oe(t),
            a & 512 && (Yt || l === null || nl(l, l.return)),
            a & 4) {
                var u = l !== null ? l.memoizedState : null;
                if (a = t.memoizedState,
                l === null)
                    if (a === null)
                        if (t.stateNode === null) {
                            t: {
                                a = t.type,
                                l = t.memoizedProps,
                                n = n.ownerDocument || n;
                                e: switch (a) {
                                case "title":
                                    u = n.getElementsByTagName("title")[0],
                                    (!u || u[Ll] || u[Vt] || u.namespaceURI === "http://www.w3.org/2000/svg" || u.hasAttribute("itemprop")) && (u = n.createElement(a),
                                    n.head.insertBefore(u, n.querySelector("head > title"))),
                                    ue(u, a, l),
                                    u[Vt] = t,
                                    Gt(u),
                                    a = u;
                                    break t;
                                case "link":
                                    var c = tm("link", "href", n).get(a + (l.href || ""));
                                    if (c) {
                                        for (var r = 0; r < c.length; r++)
                                            if (u = c[r],
                                            u.getAttribute("href") === (l.href == null || l.href === "" ? null : l.href) && u.getAttribute("rel") === (l.rel == null ? null : l.rel) && u.getAttribute("title") === (l.title == null ? null : l.title) && u.getAttribute("crossorigin") === (l.crossOrigin == null ? null : l.crossOrigin)) {
                                                c.splice(r, 1);
                                                break e
                                            }
                                    }
                                    u = n.createElement(a),
                                    ue(u, a, l),
                                    n.head.appendChild(u);
                                    break;
                                case "meta":
                                    if (c = tm("meta", "content", n).get(a + (l.content || ""))) {
                                        for (r = 0; r < c.length; r++)
                                            if (u = c[r],
                                            u.getAttribute("content") === (l.content == null ? null : "" + l.content) && u.getAttribute("name") === (l.name == null ? null : l.name) && u.getAttribute("property") === (l.property == null ? null : l.property) && u.getAttribute("http-equiv") === (l.httpEquiv == null ? null : l.httpEquiv) && u.getAttribute("charset") === (l.charSet == null ? null : l.charSet)) {
                                                c.splice(r, 1);
                                                break e
                                            }
                                    }
                                    u = n.createElement(a),
                                    ue(u, a, l),
                                    n.head.appendChild(u);
                                    break;
                                default:
                                    throw Error(f(468, a))
                                }
                                u[Vt] = t,
                                Gt(u),
                                a = u
                            }
                            t.stateNode = a
                        } else
                            em(n, t.type, t.stateNode);
                    else
                        t.stateNode = Pd(n, a, t.memoizedProps);
                else
                    u !== a ? (u === null ? l.stateNode !== null && (l = l.stateNode,
                    l.parentNode.removeChild(l)) : u.count--,
                    a === null ? em(n, t.type, t.stateNode) : Pd(n, a, t.memoizedProps)) : a === null && t.stateNode !== null && Tf(t, t.memoizedProps, l.memoizedProps)
            }
            break;
        case 27:
            Re(e, t),
            Oe(t),
            a & 512 && (Yt || l === null || nl(l, l.return)),
            l !== null && a & 4 && Tf(t, t.memoizedProps, l.memoizedProps);
            break;
        case 5:
            if (Re(e, t),
            Oe(t),
            a & 512 && (Yt || l === null || nl(l, l.return)),
            t.flags & 32) {
                n = t.stateNode;
                try {
                    Ya(n, "")
                } catch (_) {
                    _t(t, t.return, _)
                }
            }
            a & 4 && t.stateNode != null && (n = t.memoizedProps,
            Tf(t, n, l !== null ? l.memoizedProps : n)),
            a & 1024 && (xf = !0);
            break;
        case 6:
            if (Re(e, t),
            Oe(t),
            a & 4) {
                if (t.stateNode === null)
                    throw Error(f(162));
                a = t.memoizedProps,
                l = t.stateNode;
                try {
                    l.nodeValue = a
                } catch (_) {
                    _t(t, t.return, _)
                }
            }
            break;
        case 3:
            if (Bi = null,
            n = We,
            We = Hi(e.containerInfo),
            Re(e, t),
            We = n,
            Oe(t),
            a & 4 && l !== null && l.memoizedState.isDehydrated)
                try {
                    Eu(e.containerInfo)
                } catch (_) {
                    _t(t, t.return, _)
                }
            xf && (xf = !1,
            cd(t));
            break;
        case 4:
            a = We,
            We = Hi(t.stateNode.containerInfo),
            Re(e, t),
            Oe(t),
            We = a;
            break;
        case 12:
            Re(e, t),
            Oe(t);
            break;
        case 13:
            Re(e, t),
            Oe(t),
            t.child.flags & 8192 && t.memoizedState !== null != (l !== null && l.memoizedState !== null) && (Uf = ee()),
            a & 4 && (a = t.updateQueue,
            a !== null && (t.updateQueue = null,
            Rf(t, a)));
            break;
        case 22:
            n = t.memoizedState !== null;
            var m = l !== null && l.memoizedState !== null
              , T = _l
              , N = Yt;
            if (_l = T || n,
            Yt = N || m,
            Re(e, t),
            Yt = N,
            _l = T,
            Oe(t),
            a & 8192)
                t: for (e = t.stateNode,
                e._visibility = n ? e._visibility & -2 : e._visibility | 1,
                n && (l === null || m || _l || Yt || Ra(t)),
                l = null,
                e = t; ; ) {
                    if (e.tag === 5 || e.tag === 26) {
                        if (l === null) {
                            m = l = e;
                            try {
                                if (u = m.stateNode,
                                n)
                                    c = u.style,
                                    typeof c.setProperty == "function" ? c.setProperty("display", "none", "important") : c.display = "none";
                                else {
                                    r = m.stateNode;
                                    var L = m.memoizedProps.style
                                      , A = L != null && L.hasOwnProperty("display") ? L.display : null;
                                    r.style.display = A == null || typeof A == "boolean" ? "" : ("" + A).trim()
                                }
                            } catch (_) {
                                _t(m, m.return, _)
                            }
                        }
                    } else if (e.tag === 6) {
                        if (l === null) {
                            m = e;
                            try {
                                m.stateNode.nodeValue = n ? "" : m.memoizedProps
                            } catch (_) {
                                _t(m, m.return, _)
                            }
                        }
                    } else if ((e.tag !== 22 && e.tag !== 23 || e.memoizedState === null || e === t) && e.child !== null) {
                        e.child.return = e,
                        e = e.child;
                        continue
                    }
                    if (e === t)
                        break t;
                    for (; e.sibling === null; ) {
                        if (e.return === null || e.return === t)
                            break t;
                        l === e && (l = null),
                        e = e.return
                    }
                    l === e && (l = null),
                    e.sibling.return = e.return,
                    e = e.sibling
                }
            a & 4 && (a = t.updateQueue,
            a !== null && (l = a.retryQueue,
            l !== null && (a.retryQueue = null,
            Rf(t, l))));
            break;
        case 19:
            Re(e, t),
            Oe(t),
            a & 4 && (a = t.updateQueue,
            a !== null && (t.updateQueue = null,
            Rf(t, a)));
            break;
        case 30:
            break;
        case 21:
            break;
        default:
            Re(e, t),
            Oe(t)
        }
    }
    function Oe(t) {
        var e = t.flags;
        if (e & 2) {
            try {
                for (var l, a = t.return; a !== null; ) {
                    if (Ps(a)) {
                        l = a;
                        break
                    }
                    a = a.return
                }
                if (l == null)
                    throw Error(f(160));
                switch (l.tag) {
                case 27:
                    var n = l.stateNode
                      , u = Af(t);
                    Ti(t, u, n);
                    break;
                case 5:
                    var c = l.stateNode;
                    l.flags & 32 && (Ya(c, ""),
                    l.flags &= -33);
                    var r = Af(t);
                    Ti(t, r, c);
                    break;
                case 3:
                case 4:
                    var m = l.stateNode.containerInfo
                      , T = Af(t);
                    _f(t, T, m);
                    break;
                default:
                    throw Error(f(161))
                }
            } catch (N) {
                _t(t, t.return, N)
            }
            t.flags &= -3
        }
        e & 4096 && (t.flags &= -4097)
    }
    function cd(t) {
        if (t.subtreeFlags & 1024)
            for (t = t.child; t !== null; ) {
                var e = t;
                cd(e),
                e.tag === 5 && e.flags & 1024 && e.stateNode.reset(),
                t = t.sibling
            }
    }
    function Vl(t, e) {
        if (e.subtreeFlags & 8772)
            for (e = e.child; e !== null; )
                ld(t, e.alternate, e),
                e = e.sibling
    }
    function Ra(t) {
        for (t = t.child; t !== null; ) {
            var e = t;
            switch (e.tag) {
            case 0:
            case 11:
            case 14:
            case 15:
                Zl(4, e, e.return),
                Ra(e);
                break;
            case 1:
                nl(e, e.return);
                var l = e.stateNode;
                typeof l.componentWillUnmount == "function" && Fs(e, e.return, l),
                Ra(e);
                break;
            case 27:
                mu(e.stateNode);
            case 26:
            case 5:
                nl(e, e.return),
                Ra(e);
                break;
            case 22:
                e.memoizedState === null && Ra(e);
                break;
            case 30:
                Ra(e);
                break;
            default:
                Ra(e)
            }
            t = t.sibling
        }
    }
    function Kl(t, e, l) {
        for (l = l && (e.subtreeFlags & 8772) !== 0,
        e = e.child; e !== null; ) {
            var a = e.alternate
              , n = t
              , u = e
              , c = u.flags;
            switch (u.tag) {
            case 0:
            case 11:
            case 15:
                Kl(n, u, l),
                au(4, u);
                break;
            case 1:
                if (Kl(n, u, l),
                a = u,
                n = a.stateNode,
                typeof n.componentDidMount == "function")
                    try {
                        n.componentDidMount()
                    } catch (T) {
                        _t(a, a.return, T)
                    }
                if (a = u,
                n = a.updateQueue,
                n !== null) {
                    var r = a.stateNode;
                    try {
                        var m = n.shared.hiddenCallbacks;
                        if (m !== null)
                            for (n.shared.hiddenCallbacks = null,
                            n = 0; n < m.length; n++)
                                wo(m[n], r)
                    } catch (T) {
                        _t(a, a.return, T)
                    }
                }
                l && c & 64 && Ws(u),
                nu(u, u.return);
                break;
            case 27:
                td(u);
            case 26:
            case 5:
                Kl(n, u, l),
                l && a === null && c & 4 && Is(u),
                nu(u, u.return);
                break;
            case 12:
                Kl(n, u, l);
                break;
            case 13:
                Kl(n, u, l),
                l && c & 4 && ud(n, u);
                break;
            case 22:
                u.memoizedState === null && Kl(n, u, l),
                nu(u, u.return);
                break;
            case 30:
                break;
            default:
                Kl(n, u, l)
            }
            e = e.sibling
        }
    }
    function Of(t, e) {
        var l = null;
        t !== null && t.memoizedState !== null && t.memoizedState.cachePool !== null && (l = t.memoizedState.cachePool.pool),
        t = null,
        e.memoizedState !== null && e.memoizedState.cachePool !== null && (t = e.memoizedState.cachePool.pool),
        t !== l && (t != null && t.refCount++,
        l != null && Qn(l))
    }
    function Df(t, e) {
        t = null,
        e.alternate !== null && (t = e.alternate.memoizedState.cache),
        e = e.memoizedState.cache,
        e !== t && (e.refCount++,
        t != null && Qn(t))
    }
    function ul(t, e, l, a) {
        if (e.subtreeFlags & 10256)
            for (e = e.child; e !== null; )
                fd(t, e, l, a),
                e = e.sibling
    }
    function fd(t, e, l, a) {
        var n = e.flags;
        switch (e.tag) {
        case 0:
        case 11:
        case 15:
            ul(t, e, l, a),
            n & 2048 && au(9, e);
            break;
        case 1:
            ul(t, e, l, a);
            break;
        case 3:
            ul(t, e, l, a),
            n & 2048 && (t = null,
            e.alternate !== null && (t = e.alternate.memoizedState.cache),
            e = e.memoizedState.cache,
            e !== t && (e.refCount++,
            t != null && Qn(t)));
            break;
        case 12:
            if (n & 2048) {
                ul(t, e, l, a),
                t = e.stateNode;
                try {
                    var u = e.memoizedProps
                      , c = u.id
                      , r = u.onPostCommit;
                    typeof r == "function" && r(c, e.alternate === null ? "mount" : "update", t.passiveEffectDuration, -0)
                } catch (m) {
                    _t(e, e.return, m)
                }
            } else
                ul(t, e, l, a);
            break;
        case 13:
            ul(t, e, l, a);
            break;
        case 23:
            break;
        case 22:
            u = e.stateNode,
            c = e.alternate,
            e.memoizedState !== null ? u._visibility & 2 ? ul(t, e, l, a) : uu(t, e) : u._visibility & 2 ? ul(t, e, l, a) : (u._visibility |= 2,
            nn(t, e, l, a, (e.subtreeFlags & 10256) !== 0)),
            n & 2048 && Of(c, e);
            break;
        case 24:
            ul(t, e, l, a),
            n & 2048 && Df(e.alternate, e);
            break;
        default:
            ul(t, e, l, a)
        }
    }
    function nn(t, e, l, a, n) {
        for (n = n && (e.subtreeFlags & 10256) !== 0,
        e = e.child; e !== null; ) {
            var u = t
              , c = e
              , r = l
              , m = a
              , T = c.flags;
            switch (c.tag) {
            case 0:
            case 11:
            case 15:
                nn(u, c, r, m, n),
                au(8, c);
                break;
            case 23:
                break;
            case 22:
                var N = c.stateNode;
                c.memoizedState !== null ? N._visibility & 2 ? nn(u, c, r, m, n) : uu(u, c) : (N._visibility |= 2,
                nn(u, c, r, m, n)),
                n && T & 2048 && Of(c.alternate, c);
                break;
            case 24:
                nn(u, c, r, m, n),
                n && T & 2048 && Df(c.alternate, c);
                break;
            default:
                nn(u, c, r, m, n)
            }
            e = e.sibling
        }
    }
    function uu(t, e) {
        if (e.subtreeFlags & 10256)
            for (e = e.child; e !== null; ) {
                var l = t
                  , a = e
                  , n = a.flags;
                switch (a.tag) {
                case 22:
                    uu(l, a),
                    n & 2048 && Of(a.alternate, a);
                    break;
                case 24:
                    uu(l, a),
                    n & 2048 && Df(a.alternate, a);
                    break;
                default:
                    uu(l, a)
                }
                e = e.sibling
            }
    }
    var iu = 8192;
    function un(t) {
        if (t.subtreeFlags & iu)
            for (t = t.child; t !== null; )
                rd(t),
                t = t.sibling
    }
    function rd(t) {
        switch (t.tag) {
        case 26:
            un(t),
            t.flags & iu && t.memoizedState !== null && iv(We, t.memoizedState, t.memoizedProps);
            break;
        case 5:
            un(t);
            break;
        case 3:
        case 4:
            var e = We;
            We = Hi(t.stateNode.containerInfo),
            un(t),
            We = e;
            break;
        case 22:
            t.memoizedState === null && (e = t.alternate,
            e !== null && e.memoizedState !== null ? (e = iu,
            iu = 16777216,
            un(t),
            iu = e) : un(t));
            break;
        default:
            un(t)
        }
    }
    function od(t) {
        var e = t.alternate;
        if (e !== null && (t = e.child,
        t !== null)) {
            e.child = null;
            do
                e = t.sibling,
                t.sibling = null,
                t = e;
            while (t !== null)
        }
    }
    function cu(t) {
        var e = t.deletions;
        if ((t.flags & 16) !== 0) {
            if (e !== null)
                for (var l = 0; l < e.length; l++) {
                    var a = e[l];
                    Ft = a,
                    dd(a, t)
                }
            od(t)
        }
        if (t.subtreeFlags & 10256)
            for (t = t.child; t !== null; )
                sd(t),
                t = t.sibling
    }
    function sd(t) {
        switch (t.tag) {
        case 0:
        case 11:
        case 15:
            cu(t),
            t.flags & 2048 && Zl(9, t, t.return);
            break;
        case 3:
            cu(t);
            break;
        case 12:
            cu(t);
            break;
        case 22:
            var e = t.stateNode;
            t.memoizedState !== null && e._visibility & 2 && (t.return === null || t.return.tag !== 13) ? (e._visibility &= -3,
            Ai(t)) : cu(t);
            break;
        default:
            cu(t)
        }
    }
    function Ai(t) {
        var e = t.deletions;
        if ((t.flags & 16) !== 0) {
            if (e !== null)
                for (var l = 0; l < e.length; l++) {
                    var a = e[l];
                    Ft = a,
                    dd(a, t)
                }
            od(t)
        }
        for (t = t.child; t !== null; ) {
            switch (e = t,
            e.tag) {
            case 0:
            case 11:
            case 15:
                Zl(8, e, e.return),
                Ai(e);
                break;
            case 22:
                l = e.stateNode,
                l._visibility & 2 && (l._visibility &= -3,
                Ai(e));
                break;
            default:
                Ai(e)
            }
            t = t.sibling
        }
    }
    function dd(t, e) {
        for (; Ft !== null; ) {
            var l = Ft;
            switch (l.tag) {
            case 0:
            case 11:
            case 15:
                Zl(8, l, e);
                break;
            case 23:
            case 22:
                if (l.memoizedState !== null && l.memoizedState.cachePool !== null) {
                    var a = l.memoizedState.cachePool.pool;
                    a != null && a.refCount++
                }
                break;
            case 24:
                Qn(l.memoizedState.cache)
            }
            if (a = l.child,
            a !== null)
                a.return = l,
                Ft = a;
            else
                t: for (l = t; Ft !== null; ) {
                    a = Ft;
                    var n = a.sibling
                      , u = a.return;
                    if (ad(a),
                    a === l) {
                        Ft = null;
                        break t
                    }
                    if (n !== null) {
                        n.return = u,
                        Ft = n;
                        break t
                    }
                    Ft = u
                }
        }
    }
    var Ty = {
        getCacheForType: function(t) {
            var e = oe(Jt)
              , l = e.data.get(t);
            return l === void 0 && (l = t(),
            e.data.set(t, l)),
            l
        }
    }
      , Ay = typeof WeakMap == "function" ? WeakMap : Map
      , gt = 0
      , Ot = null
      , ot = null
      , mt = 0
      , pt = 0
      , De = null
      , Jl = !1
      , cn = !1
      , Mf = !1
      , Rl = 0
      , wt = 0
      , kl = 0
      , Oa = 0
      , Nf = 0
      , qe = 0
      , fn = 0
      , fu = null
      , Te = null
      , zf = !1
      , Uf = 0
      , _i = 1 / 0
      , xi = null
      , $l = null
      , ne = 0
      , Wl = null
      , rn = null
      , on = 0
      , Cf = 0
      , Lf = null
      , md = null
      , ru = 0
      , Hf = null;
    function Me() {
        if ((gt & 2) !== 0 && mt !== 0)
            return mt & -mt;
        if (M.T !== null) {
            var t = Wa;
            return t !== 0 ? t : Xf()
        }
        return On()
    }
    function hd() {
        qe === 0 && (qe = (mt & 536870912) === 0 || yt ? sa() : 536870912);
        var t = Ye.current;
        return t !== null && (t.flags |= 32),
        qe
    }
    function Ne(t, e, l) {
        (t === Ot && (pt === 2 || pt === 9) || t.cancelPendingCommit !== null) && (sn(t, 0),
        Fl(t, mt, qe, !1)),
        Je(t, l),
        ((gt & 2) === 0 || t !== Ot) && (t === Ot && ((gt & 2) === 0 && (Oa |= l),
        wt === 4 && Fl(t, mt, qe, !1)),
        il(t))
    }
    function yd(t, e, l) {
        if ((gt & 6) !== 0)
            throw Error(f(327));
        var a = !l && (e & 124) === 0 && (e & t.expiredLanes) === 0 || ol(t, e)
          , n = a ? Ry(t, e) : jf(t, e, !0)
          , u = a;
        do {
            if (n === 0) {
                cn && !a && Fl(t, e, 0, !1);
                break
            } else {
                if (l = t.current.alternate,
                u && !_y(l)) {
                    n = jf(t, e, !1),
                    u = !1;
                    continue
                }
                if (n === 2) {
                    if (u = e,
                    t.errorRecoveryDisabledLanes & u)
                        var c = 0;
                    else
                        c = t.pendingLanes & -536870913,
                        c = c !== 0 ? c : c & 536870912 ? 536870912 : 0;
                    if (c !== 0) {
                        e = c;
                        t: {
                            var r = t;
                            n = fu;
                            var m = r.current.memoizedState.isDehydrated;
                            if (m && (sn(r, c).flags |= 256),
                            c = jf(r, c, !1),
                            c !== 2) {
                                if (Mf && !m) {
                                    r.errorRecoveryDisabledLanes |= u,
                                    Oa |= u,
                                    n = 4;
                                    break t
                                }
                                u = Te,
                                Te = n,
                                u !== null && (Te === null ? Te = u : Te.push.apply(Te, u))
                            }
                            n = c
                        }
                        if (u = !1,
                        n !== 2)
                            continue
                    }
                }
                if (n === 1) {
                    sn(t, 0),
                    Fl(t, e, 0, !0);
                    break
                }
                t: {
                    switch (a = t,
                    u = n,
                    u) {
                    case 0:
                    case 1:
                        throw Error(f(345));
                    case 4:
                        if ((e & 4194048) !== e)
                            break;
                    case 6:
                        Fl(a, e, qe, !Jl);
                        break t;
                    case 2:
                        Te = null;
                        break;
                    case 3:
                    case 5:
                        break;
                    default:
                        throw Error(f(329))
                    }
                    if ((e & 62914560) === e && (n = Uf + 300 - ee(),
                    10 < n)) {
                        if (Fl(a, e, qe, !Jl),
                        Ul(a, 0, !0) !== 0)
                            break t;
                        a.timeoutHandle = Zd(vd.bind(null, a, l, Te, xi, zf, e, qe, Oa, fn, Jl, u, 2, -0, 0), n);
                        break t
                    }
                    vd(a, l, Te, xi, zf, e, qe, Oa, fn, Jl, u, 0, -0, 0)
                }
            }
            break
        } while (!0);
        il(t)
    }
    function vd(t, e, l, a, n, u, c, r, m, T, N, L, A, _) {
        if (t.timeoutHandle = -1,
        L = e.subtreeFlags,
        (L & 8192 || (L & 16785408) === 16785408) && (vu = {
            stylesheets: null,
            count: 0,
            unsuspend: uv
        },
        rd(e),
        L = cv(),
        L !== null)) {
            t.cancelPendingCommit = L(Ad.bind(null, t, e, u, l, a, n, c, r, m, N, 1, A, _)),
            Fl(t, u, c, !T);
            return
        }
        Ad(t, e, u, l, a, n, c, r, m)
    }
    function _y(t) {
        for (var e = t; ; ) {
            var l = e.tag;
            if ((l === 0 || l === 11 || l === 15) && e.flags & 16384 && (l = e.updateQueue,
            l !== null && (l = l.stores,
            l !== null)))
                for (var a = 0; a < l.length; a++) {
                    var n = l[a]
                      , u = n.getSnapshot;
                    n = n.value;
                    try {
                        if (!_e(u(), n))
                            return !1
                    } catch {
                        return !1
                    }
                }
            if (l = e.child,
            e.subtreeFlags & 16384 && l !== null)
                l.return = e,
                e = l;
            else {
                if (e === t)
                    break;
                for (; e.sibling === null; ) {
                    if (e.return === null || e.return === t)
                        return !0;
                    e = e.return
                }
                e.sibling.return = e.return,
                e = e.sibling
            }
        }
        return !0
    }
    function Fl(t, e, l, a) {
        e &= ~Nf,
        e &= ~Oa,
        t.suspendedLanes |= e,
        t.pingedLanes &= ~e,
        a && (t.warmLanes |= e),
        a = t.expirationTimes;
        for (var n = e; 0 < n; ) {
            var u = 31 - ce(n)
              , c = 1 << u;
            a[u] = -1,
            n &= ~c
        }
        l !== 0 && wu(t, l, e)
    }
    function Ri() {
        return (gt & 6) === 0 ? (ou(0),
        !1) : !0
    }
    function wf() {
        if (ot !== null) {
            if (pt === 0)
                var t = ot.return;
            else
                t = ot,
                pl = Ea = null,
                Ic(t),
                ln = null,
                tu = 0,
                t = ot;
            for (; t !== null; )
                $s(t.alternate, t),
                t = t.return;
            ot = null
        }
    }
    function sn(t, e) {
        var l = t.timeoutHandle;
        l !== -1 && (t.timeoutHandle = -1,
        Xy(l)),
        l = t.cancelPendingCommit,
        l !== null && (t.cancelPendingCommit = null,
        l()),
        wf(),
        Ot = t,
        ot = l = yl(t.current, null),
        mt = e,
        pt = 0,
        De = null,
        Jl = !1,
        cn = ol(t, e),
        Mf = !1,
        fn = qe = Nf = Oa = kl = wt = 0,
        Te = fu = null,
        zf = !1,
        (e & 8) !== 0 && (e |= e & 32);
        var a = t.entangledLanes;
        if (a !== 0)
            for (t = t.entanglements,
            a &= e; 0 < a; ) {
                var n = 31 - ce(a)
                  , u = 1 << n;
                e |= t[n],
                a &= ~u
            }
        return Rl = e,
        ku(),
        l
    }
    function gd(t, e) {
        ft = null,
        M.H = mi,
        e === Vn || e === ai ? (e = Lo(),
        pt = 3) : e === zo ? (e = Lo(),
        pt = 4) : pt = e === Hs ? 8 : e !== null && typeof e == "object" && typeof e.then == "function" ? 6 : 1,
        De = e,
        ot === null && (wt = 1,
        pi(t, He(e, t.current)))
    }
    function pd() {
        var t = M.H;
        return M.H = mi,
        t === null ? mi : t
    }
    function Sd() {
        var t = M.A;
        return M.A = Ty,
        t
    }
    function Bf() {
        wt = 4,
        Jl || (mt & 4194048) !== mt && Ye.current !== null || (cn = !0),
        (kl & 134217727) === 0 && (Oa & 134217727) === 0 || Ot === null || Fl(Ot, mt, qe, !1)
    }
    function jf(t, e, l) {
        var a = gt;
        gt |= 2;
        var n = pd()
          , u = Sd();
        (Ot !== t || mt !== e) && (xi = null,
        sn(t, e)),
        e = !1;
        var c = wt;
        t: do
            try {
                if (pt !== 0 && ot !== null) {
                    var r = ot
                      , m = De;
                    switch (pt) {
                    case 8:
                        wf(),
                        c = 6;
                        break t;
                    case 3:
                    case 2:
                    case 9:
                    case 6:
                        Ye.current === null && (e = !0);
                        var T = pt;
                        if (pt = 0,
                        De = null,
                        dn(t, r, m, T),
                        l && cn) {
                            c = 0;
                            break t
                        }
                        break;
                    default:
                        T = pt,
                        pt = 0,
                        De = null,
                        dn(t, r, m, T)
                    }
                }
                xy(),
                c = wt;
                break
            } catch (N) {
                gd(t, N)
            }
        while (!0);
        return e && t.shellSuspendCounter++,
        pl = Ea = null,
        gt = a,
        M.H = n,
        M.A = u,
        ot === null && (Ot = null,
        mt = 0,
        ku()),
        c
    }
    function xy() {
        for (; ot !== null; )
            bd(ot)
    }
    function Ry(t, e) {
        var l = gt;
        gt |= 2;
        var a = pd()
          , n = Sd();
        Ot !== t || mt !== e ? (xi = null,
        _i = ee() + 500,
        sn(t, e)) : cn = ol(t, e);
        t: do
            try {
                if (pt !== 0 && ot !== null) {
                    e = ot;
                    var u = De;
                    e: switch (pt) {
                    case 1:
                        pt = 0,
                        De = null,
                        dn(t, e, u, 1);
                        break;
                    case 2:
                    case 9:
                        if (Uo(u)) {
                            pt = 0,
                            De = null,
                            Ed(e);
                            break
                        }
                        e = function() {
                            pt !== 2 && pt !== 9 || Ot !== t || (pt = 7),
                            il(t)
                        }
                        ,
                        u.then(e, e);
                        break t;
                    case 3:
                        pt = 7;
                        break t;
                    case 4:
                        pt = 5;
                        break t;
                    case 7:
                        Uo(u) ? (pt = 0,
                        De = null,
                        Ed(e)) : (pt = 0,
                        De = null,
                        dn(t, e, u, 7));
                        break;
                    case 5:
                        var c = null;
                        switch (ot.tag) {
                        case 26:
                            c = ot.memoizedState;
                        case 5:
                        case 27:
                            var r = ot;
                            if (!c || lm(c)) {
                                pt = 0,
                                De = null;
                                var m = r.sibling;
                                if (m !== null)
                                    ot = m;
                                else {
                                    var T = r.return;
                                    T !== null ? (ot = T,
                                    Oi(T)) : ot = null
                                }
                                break e
                            }
                        }
                        pt = 0,
                        De = null,
                        dn(t, e, u, 5);
                        break;
                    case 6:
                        pt = 0,
                        De = null,
                        dn(t, e, u, 6);
                        break;
                    case 8:
                        wf(),
                        wt = 6;
                        break t;
                    default:
                        throw Error(f(462))
                    }
                }
                Oy();
                break
            } catch (N) {
                gd(t, N)
            }
        while (!0);
        return pl = Ea = null,
        M.H = a,
        M.A = n,
        gt = l,
        ot !== null ? 0 : (Ot = null,
        mt = 0,
        ku(),
        wt)
    }
    function Oy() {
        for (; ot !== null && !En(); )
            bd(ot)
    }
    function bd(t) {
        var e = Js(t.alternate, t, Rl);
        t.memoizedProps = t.pendingProps,
        e === null ? Oi(t) : ot = e
    }
    function Ed(t) {
        var e = t
          , l = e.alternate;
        switch (e.tag) {
        case 15:
        case 0:
            e = Gs(l, e, e.pendingProps, e.type, void 0, mt);
            break;
        case 11:
            e = Gs(l, e, e.pendingProps, e.type.render, e.ref, mt);
            break;
        case 5:
            Ic(e);
        default:
            $s(l, e),
            e = ot = To(e, Rl),
            e = Js(l, e, Rl)
        }
        t.memoizedProps = t.pendingProps,
        e === null ? Oi(t) : ot = e
    }
    function dn(t, e, l, a) {
        pl = Ea = null,
        Ic(e),
        ln = null,
        tu = 0;
        var n = e.return;
        try {
            if (vy(t, n, e, l, mt)) {
                wt = 1,
                pi(t, He(l, t.current)),
                ot = null;
                return
            }
        } catch (u) {
            if (n !== null)
                throw ot = n,
                u;
            wt = 1,
            pi(t, He(l, t.current)),
            ot = null;
            return
        }
        e.flags & 32768 ? (yt || a === 1 ? t = !0 : cn || (mt & 536870912) !== 0 ? t = !1 : (Jl = t = !0,
        (a === 2 || a === 9 || a === 3 || a === 6) && (a = Ye.current,
        a !== null && a.tag === 13 && (a.flags |= 16384))),
        Td(e, t)) : Oi(e)
    }
    function Oi(t) {
        var e = t;
        do {
            if ((e.flags & 32768) !== 0) {
                Td(e, Jl);
                return
            }
            t = e.return;
            var l = py(e.alternate, e, Rl);
            if (l !== null) {
                ot = l;
                return
            }
            if (e = e.sibling,
            e !== null) {
                ot = e;
                return
            }
            ot = e = t
        } while (e !== null);
        wt === 0 && (wt = 5)
    }
    function Td(t, e) {
        do {
            var l = Sy(t.alternate, t);
            if (l !== null) {
                l.flags &= 32767,
                ot = l;
                return
            }
            if (l = t.return,
            l !== null && (l.flags |= 32768,
            l.subtreeFlags = 0,
            l.deletions = null),
            !e && (t = t.sibling,
            t !== null)) {
                ot = t;
                return
            }
            ot = t = l
        } while (t !== null);
        wt = 6,
        ot = null
    }
    function Ad(t, e, l, a, n, u, c, r, m) {
        t.cancelPendingCommit = null;
        do
            Di();
        while (ne !== 0);
        if ((gt & 6) !== 0)
            throw Error(f(327));
        if (e !== null) {
            if (e === t.current)
                throw Error(f(177));
            if (u = e.lanes | e.childLanes,
            u |= Oc,
            nc(t, l, u, c, r, m),
            t === Ot && (ot = Ot = null,
            mt = 0),
            rn = e,
            Wl = t,
            on = l,
            Cf = u,
            Lf = n,
            md = a,
            (e.subtreeFlags & 10256) !== 0 || (e.flags & 10256) !== 0 ? (t.callbackNode = null,
            t.callbackPriority = 0,
            zy(ra, function() {
                return Dd(),
                null
            })) : (t.callbackNode = null,
            t.callbackPriority = 0),
            a = (e.flags & 13878) !== 0,
            (e.subtreeFlags & 13878) !== 0 || a) {
                a = M.T,
                M.T = null,
                n = X.p,
                X.p = 2,
                c = gt,
                gt |= 4;
                try {
                    by(t, e, l)
                } finally {
                    gt = c,
                    X.p = n,
                    M.T = a
                }
            }
            ne = 1,
            _d(),
            xd(),
            Rd()
        }
    }
    function _d() {
        if (ne === 1) {
            ne = 0;
            var t = Wl
              , e = rn
              , l = (e.flags & 13878) !== 0;
            if ((e.subtreeFlags & 13878) !== 0 || l) {
                l = M.T,
                M.T = null;
                var a = X.p;
                X.p = 2;
                var n = gt;
                gt |= 4;
                try {
                    id(e, t);
                    var u = Wf
                      , c = so(t.containerInfo)
                      , r = u.focusedElem
                      , m = u.selectionRange;
                    if (c !== r && r && r.ownerDocument && oo(r.ownerDocument.documentElement, r)) {
                        if (m !== null && Tc(r)) {
                            var T = m.start
                              , N = m.end;
                            if (N === void 0 && (N = T),
                            "selectionStart"in r)
                                r.selectionStart = T,
                                r.selectionEnd = Math.min(N, r.value.length);
                            else {
                                var L = r.ownerDocument || document
                                  , A = L && L.defaultView || window;
                                if (A.getSelection) {
                                    var _ = A.getSelection()
                                      , it = r.textContent.length
                                      , lt = Math.min(m.start, it)
                                      , Tt = m.end === void 0 ? lt : Math.min(m.end, it);
                                    !_.extend && lt > Tt && (c = Tt,
                                    Tt = lt,
                                    lt = c);
                                    var b = ro(r, lt)
                                      , g = ro(r, Tt);
                                    if (b && g && (_.rangeCount !== 1 || _.anchorNode !== b.node || _.anchorOffset !== b.offset || _.focusNode !== g.node || _.focusOffset !== g.offset)) {
                                        var E = L.createRange();
                                        E.setStart(b.node, b.offset),
                                        _.removeAllRanges(),
                                        lt > Tt ? (_.addRange(E),
                                        _.extend(g.node, g.offset)) : (E.setEnd(g.node, g.offset),
                                        _.addRange(E))
                                    }
                                }
                            }
                        }
                        for (L = [],
                        _ = r; _ = _.parentNode; )
                            _.nodeType === 1 && L.push({
                                element: _,
                                left: _.scrollLeft,
                                top: _.scrollTop
                            });
                        for (typeof r.focus == "function" && r.focus(),
                        r = 0; r < L.length; r++) {
                            var U = L[r];
                            U.element.scrollLeft = U.left,
                            U.element.scrollTop = U.top
                        }
                    }
                    qi = !!$f,
                    Wf = $f = null
                } finally {
                    gt = n,
                    X.p = a,
                    M.T = l
                }
            }
            t.current = e,
            ne = 2
        }
    }
    function xd() {
        if (ne === 2) {
            ne = 0;
            var t = Wl
              , e = rn
              , l = (e.flags & 8772) !== 0;
            if ((e.subtreeFlags & 8772) !== 0 || l) {
                l = M.T,
                M.T = null;
                var a = X.p;
                X.p = 2;
                var n = gt;
                gt |= 4;
                try {
                    ld(t, e.alternate, e)
                } finally {
                    gt = n,
                    X.p = a,
                    M.T = l
                }
            }
            ne = 3
        }
    }
    function Rd() {
        if (ne === 4 || ne === 3) {
            ne = 0,
            Tn();
            var t = Wl
              , e = rn
              , l = on
              , a = md;
            (e.subtreeFlags & 10256) !== 0 || (e.flags & 10256) !== 0 ? ne = 5 : (ne = 0,
            rn = Wl = null,
            Od(t, t.pendingLanes));
            var n = t.pendingLanes;
            if (n === 0 && ($l = null),
            Rn(l),
            e = e.stateNode,
            ie && typeof ie.onCommitFiberRoot == "function")
                try {
                    ie.onCommitFiberRoot(Ue, e, void 0, (e.current.flags & 128) === 128)
                } catch {}
            if (a !== null) {
                e = M.T,
                n = X.p,
                X.p = 2,
                M.T = null;
                try {
                    for (var u = t.onRecoverableError, c = 0; c < a.length; c++) {
                        var r = a[c];
                        u(r.value, {
                            componentStack: r.stack
                        })
                    }
                } finally {
                    M.T = e,
                    X.p = n
                }
            }
            (on & 3) !== 0 && Di(),
            il(t),
            n = t.pendingLanes,
            (l & 4194090) !== 0 && (n & 42) !== 0 ? t === Hf ? ru++ : (ru = 0,
            Hf = t) : ru = 0,
            ou(0)
        }
    }
    function Od(t, e) {
        (t.pooledCacheLanes &= e) === 0 && (e = t.pooledCache,
        e != null && (t.pooledCache = null,
        Qn(e)))
    }
    function Di(t) {
        return _d(),
        xd(),
        Rd(),
        Dd()
    }
    function Dd() {
        if (ne !== 5)
            return !1;
        var t = Wl
          , e = Cf;
        Cf = 0;
        var l = Rn(on)
          , a = M.T
          , n = X.p;
        try {
            X.p = 32 > l ? 32 : l,
            M.T = null,
            l = Lf,
            Lf = null;
            var u = Wl
              , c = on;
            if (ne = 0,
            rn = Wl = null,
            on = 0,
            (gt & 6) !== 0)
                throw Error(f(331));
            var r = gt;
            if (gt |= 4,
            sd(u.current),
            fd(u, u.current, c, l),
            gt = r,
            ou(0, !1),
            ie && typeof ie.onPostCommitFiberRoot == "function")
                try {
                    ie.onPostCommitFiberRoot(Ue, u)
                } catch {}
            return !0
        } finally {
            X.p = n,
            M.T = a,
            Od(t, e)
        }
    }
    function Md(t, e, l) {
        e = He(l, e),
        e = mf(t.stateNode, e, 2),
        t = ql(t, e, 2),
        t !== null && (Je(t, 2),
        il(t))
    }
    function _t(t, e, l) {
        if (t.tag === 3)
            Md(t, t, l);
        else
            for (; e !== null; ) {
                if (e.tag === 3) {
                    Md(e, t, l);
                    break
                } else if (e.tag === 1) {
                    var a = e.stateNode;
                    if (typeof e.type.getDerivedStateFromError == "function" || typeof a.componentDidCatch == "function" && ($l === null || !$l.has(a))) {
                        t = He(l, t),
                        l = Cs(2),
                        a = ql(e, l, 2),
                        a !== null && (Ls(l, a, e, t),
                        Je(a, 2),
                        il(a));
                        break
                    }
                }
                e = e.return
            }
    }
    function Yf(t, e, l) {
        var a = t.pingCache;
        if (a === null) {
            a = t.pingCache = new Ay;
            var n = new Set;
            a.set(e, n)
        } else
            n = a.get(e),
            n === void 0 && (n = new Set,
            a.set(e, n));
        n.has(l) || (Mf = !0,
        n.add(l),
        t = Dy.bind(null, t, e, l),
        e.then(t, t))
    }
    function Dy(t, e, l) {
        var a = t.pingCache;
        a !== null && a.delete(e),
        t.pingedLanes |= t.suspendedLanes & l,
        t.warmLanes &= ~l,
        Ot === t && (mt & l) === l && (wt === 4 || wt === 3 && (mt & 62914560) === mt && 300 > ee() - Uf ? (gt & 2) === 0 && sn(t, 0) : Nf |= l,
        fn === mt && (fn = 0)),
        il(t)
    }
    function Nd(t, e) {
        e === 0 && (e = da()),
        t = Ka(t, e),
        t !== null && (Je(t, e),
        il(t))
    }
    function My(t) {
        var e = t.memoizedState
          , l = 0;
        e !== null && (l = e.retryLane),
        Nd(t, l)
    }
    function Ny(t, e) {
        var l = 0;
        switch (t.tag) {
        case 13:
            var a = t.stateNode
              , n = t.memoizedState;
            n !== null && (l = n.retryLane);
            break;
        case 19:
            a = t.stateNode;
            break;
        case 22:
            a = t.stateNode._retryCache;
            break;
        default:
            throw Error(f(314))
        }
        a !== null && a.delete(e),
        Nd(t, l)
    }
    function zy(t, e) {
        return ca(t, e)
    }
    var Mi = null
      , mn = null
      , qf = !1
      , Ni = !1
      , Gf = !1
      , Da = 0;
    function il(t) {
        t !== mn && t.next === null && (mn === null ? Mi = mn = t : mn = mn.next = t),
        Ni = !0,
        qf || (qf = !0,
        Cy())
    }
    function ou(t, e) {
        if (!Gf && Ni) {
            Gf = !0;
            do
                for (var l = !1, a = Mi; a !== null; ) {
                    if (t !== 0) {
                        var n = a.pendingLanes;
                        if (n === 0)
                            var u = 0;
                        else {
                            var c = a.suspendedLanes
                              , r = a.pingedLanes;
                            u = (1 << 31 - ce(42 | t) + 1) - 1,
                            u &= n & ~(c & ~r),
                            u = u & 201326741 ? u & 201326741 | 1 : u ? u | 2 : 0
                        }
                        u !== 0 && (l = !0,
                        Ld(a, u))
                    } else
                        u = mt,
                        u = Ul(a, a === Ot ? u : 0, a.cancelPendingCommit !== null || a.timeoutHandle !== -1),
                        (u & 3) === 0 || ol(a, u) || (l = !0,
                        Ld(a, u));
                    a = a.next
                }
            while (l);
            Gf = !1
        }
    }
    function Uy() {
        zd()
    }
    function zd() {
        Ni = qf = !1;
        var t = 0;
        Da !== 0 && (Gy() && (t = Da),
        Da = 0);
        for (var e = ee(), l = null, a = Mi; a !== null; ) {
            var n = a.next
              , u = Ud(a, e);
            u === 0 ? (a.next = null,
            l === null ? Mi = n : l.next = n,
            n === null && (mn = l)) : (l = a,
            (t !== 0 || (u & 3) !== 0) && (Ni = !0)),
            a = n
        }
        ou(t)
    }
    function Ud(t, e) {
        for (var l = t.suspendedLanes, a = t.pingedLanes, n = t.expirationTimes, u = t.pendingLanes & -62914561; 0 < u; ) {
            var c = 31 - ce(u)
              , r = 1 << c
              , m = n[c];
            m === -1 ? ((r & l) === 0 || (r & a) !== 0) && (n[c] = ac(r, e)) : m <= e && (t.expiredLanes |= r),
            u &= ~r
        }
        if (e = Ot,
        l = mt,
        l = Ul(t, t === e ? l : 0, t.cancelPendingCommit !== null || t.timeoutHandle !== -1),
        a = t.callbackNode,
        l === 0 || t === e && (pt === 2 || pt === 9) || t.cancelPendingCommit !== null)
            return a !== null && a !== null && Ve(a),
            t.callbackNode = null,
            t.callbackPriority = 0;
        if ((l & 3) === 0 || ol(t, l)) {
            if (e = l & -l,
            e === t.callbackPriority)
                return e;
            switch (a !== null && Ve(a),
            Rn(l)) {
            case 2:
            case 8:
                l = An;
                break;
            case 32:
                l = ra;
                break;
            case 268435456:
                l = Ca;
                break;
            default:
                l = ra
            }
            return a = Cd.bind(null, t),
            l = ca(l, a),
            t.callbackPriority = e,
            t.callbackNode = l,
            e
        }
        return a !== null && a !== null && Ve(a),
        t.callbackPriority = 2,
        t.callbackNode = null,
        2
    }
    function Cd(t, e) {
        if (ne !== 0 && ne !== 5)
            return t.callbackNode = null,
            t.callbackPriority = 0,
            null;
        var l = t.callbackNode;
        if (Di() && t.callbackNode !== l)
            return null;
        var a = mt;
        return a = Ul(t, t === Ot ? a : 0, t.cancelPendingCommit !== null || t.timeoutHandle !== -1),
        a === 0 ? null : (yd(t, a, e),
        Ud(t, ee()),
        t.callbackNode != null && t.callbackNode === l ? Cd.bind(null, t) : null)
    }
    function Ld(t, e) {
        if (Di())
            return null;
        yd(t, e, !0)
    }
    function Cy() {
        Qy(function() {
            (gt & 6) !== 0 ? ca(fa, Uy) : zd()
        })
    }
    function Xf() {
        return Da === 0 && (Da = sa()),
        Da
    }
    function Hd(t) {
        return t == null || typeof t == "symbol" || typeof t == "boolean" ? null : typeof t == "function" ? t : Gu("" + t)
    }
    function wd(t, e) {
        var l = e.ownerDocument.createElement("input");
        return l.name = e.name,
        l.value = e.value,
        t.id && l.setAttribute("form", t.id),
        e.parentNode.insertBefore(l, e),
        t = new FormData(t),
        l.parentNode.removeChild(l),
        t
    }
    function Ly(t, e, l, a, n) {
        if (e === "submit" && l && l.stateNode === n) {
            var u = Hd((n[fe] || null).action)
              , c = a.submitter;
            c && (e = (e = c[fe] || null) ? Hd(e.formAction) : c.getAttribute("formAction"),
            e !== null && (u = e,
            c = null));
            var r = new Vu("action","action",null,a,n);
            t.push({
                event: r,
                listeners: [{
                    instance: null,
                    listener: function() {
                        if (a.defaultPrevented) {
                            if (Da !== 0) {
                                var m = c ? wd(n, c) : new FormData(n);
                                ff(l, {
                                    pending: !0,
                                    data: m,
                                    method: n.method,
                                    action: u
                                }, null, m)
                            }
                        } else
                            typeof u == "function" && (r.preventDefault(),
                            m = c ? wd(n, c) : new FormData(n),
                            ff(l, {
                                pending: !0,
                                data: m,
                                method: n.method,
                                action: u
                            }, u, m))
                    },
                    currentTarget: n
                }]
            })
        }
    }
    for (var Qf = 0; Qf < Rc.length; Qf++) {
        var Zf = Rc[Qf]
          , Hy = Zf.toLowerCase()
          , wy = Zf[0].toUpperCase() + Zf.slice(1);
        $e(Hy, "on" + wy)
    }
    $e(yo, "onAnimationEnd"),
    $e(vo, "onAnimationIteration"),
    $e(go, "onAnimationStart"),
    $e("dblclick", "onDoubleClick"),
    $e("focusin", "onFocus"),
    $e("focusout", "onBlur"),
    $e(Ph, "onTransitionRun"),
    $e(ty, "onTransitionStart"),
    $e(ey, "onTransitionCancel"),
    $e(po, "onTransitionEnd"),
    K("onMouseEnter", ["mouseout", "mouseover"]),
    K("onMouseLeave", ["mouseout", "mouseover"]),
    K("onPointerEnter", ["pointerout", "pointerover"]),
    K("onPointerLeave", ["pointerout", "pointerover"]),
    B("onChange", "change click focusin focusout input keydown keyup selectionchange".split(" ")),
    B("onSelect", "focusout contextmenu dragend focusin keydown keyup mousedown mouseup selectionchange".split(" ")),
    B("onBeforeInput", ["compositionend", "keypress", "textInput", "paste"]),
    B("onCompositionEnd", "compositionend focusout keydown keypress keyup mousedown".split(" ")),
    B("onCompositionStart", "compositionstart focusout keydown keypress keyup mousedown".split(" ")),
    B("onCompositionUpdate", "compositionupdate focusout keydown keypress keyup mousedown".split(" "));
    var su = "abort canplay canplaythrough durationchange emptied encrypted ended error loadeddata loadedmetadata loadstart pause play playing progress ratechange resize seeked seeking stalled suspend timeupdate volumechange waiting".split(" ")
      , By = new Set("beforetoggle cancel close invalid load scroll scrollend toggle".split(" ").concat(su));
    function Bd(t, e) {
        e = (e & 4) !== 0;
        for (var l = 0; l < t.length; l++) {
            var a = t[l]
              , n = a.event;
            a = a.listeners;
            t: {
                var u = void 0;
                if (e)
                    for (var c = a.length - 1; 0 <= c; c--) {
                        var r = a[c]
                          , m = r.instance
                          , T = r.currentTarget;
                        if (r = r.listener,
                        m !== u && n.isPropagationStopped())
                            break t;
                        u = r,
                        n.currentTarget = T;
                        try {
                            u(n)
                        } catch (N) {
                            gi(N)
                        }
                        n.currentTarget = null,
                        u = m
                    }
                else
                    for (c = 0; c < a.length; c++) {
                        if (r = a[c],
                        m = r.instance,
                        T = r.currentTarget,
                        r = r.listener,
                        m !== u && n.isPropagationStopped())
                            break t;
                        u = r,
                        n.currentTarget = T;
                        try {
                            u(n)
                        } catch (N) {
                            gi(N)
                        }
                        n.currentTarget = null,
                        u = m
                    }
            }
        }
    }
    function st(t, e) {
        var l = e[Cl];
        l === void 0 && (l = e[Cl] = new Set);
        var a = t + "__bubble";
        l.has(a) || (jd(e, t, 2, !1),
        l.add(a))
    }
    function Vf(t, e, l) {
        var a = 0;
        e && (a |= 4),
        jd(l, t, a, e)
    }
    var zi = "_reactListening" + Math.random().toString(36).slice(2);
    function Kf(t) {
        if (!t[zi]) {
            t[zi] = !0,
            $.forEach(function(l) {
                l !== "selectionchange" && (By.has(l) || Vf(l, !1, t),
                Vf(l, !0, t))
            });
            var e = t.nodeType === 9 ? t : t.ownerDocument;
            e === null || e[zi] || (e[zi] = !0,
            Vf("selectionchange", !1, e))
        }
    }
    function jd(t, e, l, a) {
        switch (fm(e)) {
        case 2:
            var n = ov;
            break;
        case 8:
            n = sv;
            break;
        default:
            n = ir
        }
        l = n.bind(null, e, l, t),
        n = void 0,
        !mc || e !== "touchstart" && e !== "touchmove" && e !== "wheel" || (n = !0),
        a ? n !== void 0 ? t.addEventListener(e, l, {
            capture: !0,
            passive: n
        }) : t.addEventListener(e, l, !0) : n !== void 0 ? t.addEventListener(e, l, {
            passive: n
        }) : t.addEventListener(e, l, !1)
    }
    function Jf(t, e, l, a, n) {
        var u = a;
        if ((e & 1) === 0 && (e & 2) === 0 && a !== null)
            t: for (; ; ) {
                if (a === null)
                    return;
                var c = a.tag;
                if (c === 3 || c === 4) {
                    var r = a.stateNode.containerInfo;
                    if (r === n)
                        break;
                    if (c === 4)
                        for (c = a.return; c !== null; ) {
                            var m = c.tag;
                            if ((m === 3 || m === 4) && c.stateNode.containerInfo === n)
                                return;
                            c = c.return
                        }
                    for (; r !== null; ) {
                        if (c = sl(r),
                        c === null)
                            return;
                        if (m = c.tag,
                        m === 5 || m === 6 || m === 26 || m === 27) {
                            a = u = c;
                            continue t
                        }
                        r = r.parentNode
                    }
                }
                a = a.return
            }
        Zr(function() {
            var T = u
              , N = sc(l)
              , L = [];
            t: {
                var A = So.get(t);
                if (A !== void 0) {
                    var _ = Vu
                      , it = t;
                    switch (t) {
                    case "keypress":
                        if (Qu(l) === 0)
                            break t;
                    case "keydown":
                    case "keyup":
                        _ = zh;
                        break;
                    case "focusin":
                        it = "focus",
                        _ = gc;
                        break;
                    case "focusout":
                        it = "blur",
                        _ = gc;
                        break;
                    case "beforeblur":
                    case "afterblur":
                        _ = gc;
                        break;
                    case "click":
                        if (l.button === 2)
                            break t;
                    case "auxclick":
                    case "dblclick":
                    case "mousedown":
                    case "mousemove":
                    case "mouseup":
                    case "mouseout":
                    case "mouseover":
                    case "contextmenu":
                        _ = Jr;
                        break;
                    case "drag":
                    case "dragend":
                    case "dragenter":
                    case "dragexit":
                    case "dragleave":
                    case "dragover":
                    case "dragstart":
                    case "drop":
                        _ = Sh;
                        break;
                    case "touchcancel":
                    case "touchend":
                    case "touchmove":
                    case "touchstart":
                        _ = Lh;
                        break;
                    case yo:
                    case vo:
                    case go:
                        _ = Th;
                        break;
                    case po:
                        _ = wh;
                        break;
                    case "scroll":
                    case "scrollend":
                        _ = gh;
                        break;
                    case "wheel":
                        _ = jh;
                        break;
                    case "copy":
                    case "cut":
                    case "paste":
                        _ = _h;
                        break;
                    case "gotpointercapture":
                    case "lostpointercapture":
                    case "pointercancel":
                    case "pointerdown":
                    case "pointermove":
                    case "pointerout":
                    case "pointerover":
                    case "pointerup":
                        _ = $r;
                        break;
                    case "toggle":
                    case "beforetoggle":
                        _ = qh
                    }
                    var lt = (e & 4) !== 0
                      , Tt = !lt && (t === "scroll" || t === "scrollend")
                      , b = lt ? A !== null ? A + "Capture" : null : A;
                    lt = [];
                    for (var g = T, E; g !== null; ) {
                        var U = g;
                        if (E = U.stateNode,
                        U = U.tag,
                        U !== 5 && U !== 26 && U !== 27 || E === null || b === null || (U = Nn(g, b),
                        U != null && lt.push(du(g, U, E))),
                        Tt)
                            break;
                        g = g.return
                    }
                    0 < lt.length && (A = new _(A,it,null,l,N),
                    L.push({
                        event: A,
                        listeners: lt
                    }))
                }
            }
            if ((e & 7) === 0) {
                t: {
                    if (A = t === "mouseover" || t === "pointerover",
                    _ = t === "mouseout" || t === "pointerout",
                    A && l !== oc && (it = l.relatedTarget || l.fromElement) && (sl(it) || it[le]))
                        break t;
                    if ((_ || A) && (A = N.window === N ? N : (A = N.ownerDocument) ? A.defaultView || A.parentWindow : window,
                    _ ? (it = l.relatedTarget || l.toElement,
                    _ = T,
                    it = it ? sl(it) : null,
                    it !== null && (Tt = v(it),
                    lt = it.tag,
                    it !== Tt || lt !== 5 && lt !== 27 && lt !== 6) && (it = null)) : (_ = null,
                    it = T),
                    _ !== it)) {
                        if (lt = Jr,
                        U = "onMouseLeave",
                        b = "onMouseEnter",
                        g = "mouse",
                        (t === "pointerout" || t === "pointerover") && (lt = $r,
                        U = "onPointerLeave",
                        b = "onPointerEnter",
                        g = "pointer"),
                        Tt = _ == null ? A : Hl(_),
                        E = it == null ? A : Hl(it),
                        A = new lt(U,g + "leave",_,l,N),
                        A.target = Tt,
                        A.relatedTarget = E,
                        U = null,
                        sl(N) === T && (lt = new lt(b,g + "enter",it,l,N),
                        lt.target = E,
                        lt.relatedTarget = Tt,
                        U = lt),
                        Tt = U,
                        _ && it)
                            e: {
                                for (lt = _,
                                b = it,
                                g = 0,
                                E = lt; E; E = hn(E))
                                    g++;
                                for (E = 0,
                                U = b; U; U = hn(U))
                                    E++;
                                for (; 0 < g - E; )
                                    lt = hn(lt),
                                    g--;
                                for (; 0 < E - g; )
                                    b = hn(b),
                                    E--;
                                for (; g--; ) {
                                    if (lt === b || b !== null && lt === b.alternate)
                                        break e;
                                    lt = hn(lt),
                                    b = hn(b)
                                }
                                lt = null
                            }
                        else
                            lt = null;
                        _ !== null && Yd(L, A, _, lt, !1),
                        it !== null && Tt !== null && Yd(L, Tt, it, lt, !0)
                    }
                }
                t: {
                    if (A = T ? Hl(T) : window,
                    _ = A.nodeName && A.nodeName.toLowerCase(),
                    _ === "select" || _ === "input" && A.type === "file")
                        var J = ao;
                    else if (eo(A))
                        if (no)
                            J = Wh;
                        else {
                            J = kh;
                            var rt = Jh
                        }
                    else
                        _ = A.nodeName,
                        !_ || _.toLowerCase() !== "input" || A.type !== "checkbox" && A.type !== "radio" ? T && rc(T.elementType) && (J = ao) : J = $h;
                    if (J && (J = J(t, T))) {
                        lo(L, J, l, N);
                        break t
                    }
                    rt && rt(t, A, T),
                    t === "focusout" && T && A.type === "number" && T.memoizedProps.value != null && fc(A, "number", A.value)
                }
                switch (rt = T ? Hl(T) : window,
                t) {
                case "focusin":
                    (eo(rt) || rt.contentEditable === "true") && (Qa = rt,
                    Ac = T,
                    jn = null);
                    break;
                case "focusout":
                    jn = Ac = Qa = null;
                    break;
                case "mousedown":
                    _c = !0;
                    break;
                case "contextmenu":
                case "mouseup":
                case "dragend":
                    _c = !1,
                    mo(L, l, N);
                    break;
                case "selectionchange":
                    if (Ih)
                        break;
                case "keydown":
                case "keyup":
                    mo(L, l, N)
                }
                var W;
                if (Sc)
                    t: {
                        switch (t) {
                        case "compositionstart":
                            var nt = "onCompositionStart";
                            break t;
                        case "compositionend":
                            nt = "onCompositionEnd";
                            break t;
                        case "compositionupdate":
                            nt = "onCompositionUpdate";
                            break t
                        }
                        nt = void 0
                    }
                else
                    Xa ? Pr(t, l) && (nt = "onCompositionEnd") : t === "keydown" && l.keyCode === 229 && (nt = "onCompositionStart");
                nt && (Wr && l.locale !== "ko" && (Xa || nt !== "onCompositionStart" ? nt === "onCompositionEnd" && Xa && (W = Vr()) : (wl = N,
                hc = "value"in wl ? wl.value : wl.textContent,
                Xa = !0)),
                rt = Ui(T, nt),
                0 < rt.length && (nt = new kr(nt,t,null,l,N),
                L.push({
                    event: nt,
                    listeners: rt
                }),
                W ? nt.data = W : (W = to(l),
                W !== null && (nt.data = W)))),
                (W = Xh ? Qh(t, l) : Zh(t, l)) && (nt = Ui(T, "onBeforeInput"),
                0 < nt.length && (rt = new kr("onBeforeInput","beforeinput",null,l,N),
                L.push({
                    event: rt,
                    listeners: nt
                }),
                rt.data = W)),
                Ly(L, t, T, l, N)
            }
            Bd(L, e)
        })
    }
    function du(t, e, l) {
        return {
            instance: t,
            listener: e,
            currentTarget: l
        }
    }
    function Ui(t, e) {
        for (var l = e + "Capture", a = []; t !== null; ) {
            var n = t
              , u = n.stateNode;
            if (n = n.tag,
            n !== 5 && n !== 26 && n !== 27 || u === null || (n = Nn(t, l),
            n != null && a.unshift(du(t, n, u)),
            n = Nn(t, e),
            n != null && a.push(du(t, n, u))),
            t.tag === 3)
                return a;
            t = t.return
        }
        return []
    }
    function hn(t) {
        if (t === null)
            return null;
        do
            t = t.return;
        while (t && t.tag !== 5 && t.tag !== 27);
        return t || null
    }
    function Yd(t, e, l, a, n) {
        for (var u = e._reactName, c = []; l !== null && l !== a; ) {
            var r = l
              , m = r.alternate
              , T = r.stateNode;
            if (r = r.tag,
            m !== null && m === a)
                break;
            r !== 5 && r !== 26 && r !== 27 || T === null || (m = T,
            n ? (T = Nn(l, u),
            T != null && c.unshift(du(l, T, m))) : n || (T = Nn(l, u),
            T != null && c.push(du(l, T, m)))),
            l = l.return
        }
        c.length !== 0 && t.push({
            event: e,
            listeners: c
        })
    }
    var jy = /\r\n?/g
      , Yy = /\u0000|\uFFFD/g;
    function qd(t) {
        return (typeof t == "string" ? t : "" + t).replace(jy, `
`).replace(Yy, "")
    }
    function Gd(t, e) {
        return e = qd(e),
        qd(t) === e
    }
    function Ci() {}
    function Et(t, e, l, a, n, u) {
        switch (l) {
        case "children":
            typeof a == "string" ? e === "body" || e === "textarea" && a === "" || Ya(t, a) : (typeof a == "number" || typeof a == "bigint") && e !== "body" && Ya(t, "" + a);
            break;
        case "className":
            el(t, "class", a);
            break;
        case "tabIndex":
            el(t, "tabindex", a);
            break;
        case "dir":
        case "role":
        case "viewBox":
        case "width":
        case "height":
            el(t, l, a);
            break;
        case "style":
            Xr(t, a, u);
            break;
        case "data":
            if (e !== "object") {
                el(t, "data", a);
                break
            }
        case "src":
        case "href":
            if (a === "" && (e !== "a" || l !== "href")) {
                t.removeAttribute(l);
                break
            }
            if (a == null || typeof a == "function" || typeof a == "symbol" || typeof a == "boolean") {
                t.removeAttribute(l);
                break
            }
            a = Gu("" + a),
            t.setAttribute(l, a);
            break;
        case "action":
        case "formAction":
            if (typeof a == "function") {
                t.setAttribute(l, "javascript:throw new Error('A React form was unexpectedly submitted. If you called form.submit() manually, consider using form.requestSubmit() instead. If you\\'re trying to use event.stopPropagation() in a submit event handler, consider also calling event.preventDefault().')");
                break
            } else
                typeof u == "function" && (l === "formAction" ? (e !== "input" && Et(t, e, "name", n.name, n, null),
                Et(t, e, "formEncType", n.formEncType, n, null),
                Et(t, e, "formMethod", n.formMethod, n, null),
                Et(t, e, "formTarget", n.formTarget, n, null)) : (Et(t, e, "encType", n.encType, n, null),
                Et(t, e, "method", n.method, n, null),
                Et(t, e, "target", n.target, n, null)));
            if (a == null || typeof a == "symbol" || typeof a == "boolean") {
                t.removeAttribute(l);
                break
            }
            a = Gu("" + a),
            t.setAttribute(l, a);
            break;
        case "onClick":
            a != null && (t.onclick = Ci);
            break;
        case "onScroll":
            a != null && st("scroll", t);
            break;
        case "onScrollEnd":
            a != null && st("scrollend", t);
            break;
        case "dangerouslySetInnerHTML":
            if (a != null) {
                if (typeof a != "object" || !("__html"in a))
                    throw Error(f(61));
                if (l = a.__html,
                l != null) {
                    if (n.children != null)
                        throw Error(f(60));
                    t.innerHTML = l
                }
            }
            break;
        case "multiple":
            t.multiple = a && typeof a != "function" && typeof a != "symbol";
            break;
        case "muted":
            t.muted = a && typeof a != "function" && typeof a != "symbol";
            break;
        case "suppressContentEditableWarning":
        case "suppressHydrationWarning":
        case "defaultValue":
        case "defaultChecked":
        case "innerHTML":
        case "ref":
            break;
        case "autoFocus":
            break;
        case "xlinkHref":
            if (a == null || typeof a == "function" || typeof a == "boolean" || typeof a == "symbol") {
                t.removeAttribute("xlink:href");
                break
            }
            l = Gu("" + a),
            t.setAttributeNS("http://www.w3.org/1999/xlink", "xlink:href", l);
            break;
        case "contentEditable":
        case "spellCheck":
        case "draggable":
        case "value":
        case "autoReverse":
        case "externalResourcesRequired":
        case "focusable":
        case "preserveAlpha":
            a != null && typeof a != "function" && typeof a != "symbol" ? t.setAttribute(l, "" + a) : t.removeAttribute(l);
            break;
        case "inert":
        case "allowFullScreen":
        case "async":
        case "autoPlay":
        case "controls":
        case "default":
        case "defer":
        case "disabled":
        case "disablePictureInPicture":
        case "disableRemotePlayback":
        case "formNoValidate":
        case "hidden":
        case "loop":
        case "noModule":
        case "noValidate":
        case "open":
        case "playsInline":
        case "readOnly":
        case "required":
        case "reversed":
        case "scoped":
        case "seamless":
        case "itemScope":
            a && typeof a != "function" && typeof a != "symbol" ? t.setAttribute(l, "") : t.removeAttribute(l);
            break;
        case "capture":
        case "download":
            a === !0 ? t.setAttribute(l, "") : a !== !1 && a != null && typeof a != "function" && typeof a != "symbol" ? t.setAttribute(l, a) : t.removeAttribute(l);
            break;
        case "cols":
        case "rows":
        case "size":
        case "span":
            a != null && typeof a != "function" && typeof a != "symbol" && !isNaN(a) && 1 <= a ? t.setAttribute(l, a) : t.removeAttribute(l);
            break;
        case "rowSpan":
        case "start":
            a == null || typeof a == "function" || typeof a == "symbol" || isNaN(a) ? t.removeAttribute(l) : t.setAttribute(l, a);
            break;
        case "popover":
            st("beforetoggle", t),
            st("toggle", t),
            de(t, "popover", a);
            break;
        case "xlinkActuate":
            Dt(t, "http://www.w3.org/1999/xlink", "xlink:actuate", a);
            break;
        case "xlinkArcrole":
            Dt(t, "http://www.w3.org/1999/xlink", "xlink:arcrole", a);
            break;
        case "xlinkRole":
            Dt(t, "http://www.w3.org/1999/xlink", "xlink:role", a);
            break;
        case "xlinkShow":
            Dt(t, "http://www.w3.org/1999/xlink", "xlink:show", a);
            break;
        case "xlinkTitle":
            Dt(t, "http://www.w3.org/1999/xlink", "xlink:title", a);
            break;
        case "xlinkType":
            Dt(t, "http://www.w3.org/1999/xlink", "xlink:type", a);
            break;
        case "xmlBase":
            Dt(t, "http://www.w3.org/XML/1998/namespace", "xml:base", a);
            break;
        case "xmlLang":
            Dt(t, "http://www.w3.org/XML/1998/namespace", "xml:lang", a);
            break;
        case "xmlSpace":
            Dt(t, "http://www.w3.org/XML/1998/namespace", "xml:space", a);
            break;
        case "is":
            de(t, "is", a);
            break;
        case "innerText":
        case "textContent":
            break;
        default:
            (!(2 < l.length) || l[0] !== "o" && l[0] !== "O" || l[1] !== "n" && l[1] !== "N") && (l = yh.get(l) || l,
            de(t, l, a))
        }
    }
    function kf(t, e, l, a, n, u) {
        switch (l) {
        case "style":
            Xr(t, a, u);
            break;
        case "dangerouslySetInnerHTML":
            if (a != null) {
                if (typeof a != "object" || !("__html"in a))
                    throw Error(f(61));
                if (l = a.__html,
                l != null) {
                    if (n.children != null)
                        throw Error(f(60));
                    t.innerHTML = l
                }
            }
            break;
        case "children":
            typeof a == "string" ? Ya(t, a) : (typeof a == "number" || typeof a == "bigint") && Ya(t, "" + a);
            break;
        case "onScroll":
            a != null && st("scroll", t);
            break;
        case "onScrollEnd":
            a != null && st("scrollend", t);
            break;
        case "onClick":
            a != null && (t.onclick = Ci);
            break;
        case "suppressContentEditableWarning":
        case "suppressHydrationWarning":
        case "innerHTML":
        case "ref":
            break;
        case "innerText":
        case "textContent":
            break;
        default:
            if (!p.hasOwnProperty(l))
                t: {
                    if (l[0] === "o" && l[1] === "n" && (n = l.endsWith("Capture"),
                    e = l.slice(2, n ? l.length - 7 : void 0),
                    u = t[fe] || null,
                    u = u != null ? u[l] : null,
                    typeof u == "function" && t.removeEventListener(e, u, n),
                    typeof a == "function")) {
                        typeof u != "function" && u !== null && (l in t ? t[l] = null : t.hasAttribute(l) && t.removeAttribute(l)),
                        t.addEventListener(e, a, n);
                        break t
                    }
                    l in t ? t[l] = a : a === !0 ? t.setAttribute(l, "") : de(t, l, a)
                }
        }
    }
    function ue(t, e, l) {
        switch (e) {
        case "div":
        case "span":
        case "svg":
        case "path":
        case "a":
        case "g":
        case "p":
        case "li":
            break;
        case "img":
            st("error", t),
            st("load", t);
            var a = !1, n = !1, u;
            for (u in l)
                if (l.hasOwnProperty(u)) {
                    var c = l[u];
                    if (c != null)
                        switch (u) {
                        case "src":
                            a = !0;
                            break;
                        case "srcSet":
                            n = !0;
                            break;
                        case "children":
                        case "dangerouslySetInnerHTML":
                            throw Error(f(137, e));
                        default:
                            Et(t, e, u, c, l, null)
                        }
                }
            n && Et(t, e, "srcSet", l.srcSet, l, null),
            a && Et(t, e, "src", l.src, l, null);
            return;
        case "input":
            st("invalid", t);
            var r = u = c = n = null
              , m = null
              , T = null;
            for (a in l)
                if (l.hasOwnProperty(a)) {
                    var N = l[a];
                    if (N != null)
                        switch (a) {
                        case "name":
                            n = N;
                            break;
                        case "type":
                            c = N;
                            break;
                        case "checked":
                            m = N;
                            break;
                        case "defaultChecked":
                            T = N;
                            break;
                        case "value":
                            u = N;
                            break;
                        case "defaultValue":
                            r = N;
                            break;
                        case "children":
                        case "dangerouslySetInnerHTML":
                            if (N != null)
                                throw Error(f(137, e));
                            break;
                        default:
                            Et(t, e, a, N, l, null)
                        }
                }
            jr(t, u, r, m, T, c, n, !1),
            Yu(t);
            return;
        case "select":
            st("invalid", t),
            a = c = u = null;
            for (n in l)
                if (l.hasOwnProperty(n) && (r = l[n],
                r != null))
                    switch (n) {
                    case "value":
                        u = r;
                        break;
                    case "defaultValue":
                        c = r;
                        break;
                    case "multiple":
                        a = r;
                    default:
                        Et(t, e, n, r, l, null)
                    }
            e = u,
            l = c,
            t.multiple = !!a,
            e != null ? ja(t, !!a, e, !1) : l != null && ja(t, !!a, l, !0);
            return;
        case "textarea":
            st("invalid", t),
            u = n = a = null;
            for (c in l)
                if (l.hasOwnProperty(c) && (r = l[c],
                r != null))
                    switch (c) {
                    case "value":
                        a = r;
                        break;
                    case "defaultValue":
                        n = r;
                        break;
                    case "children":
                        u = r;
                        break;
                    case "dangerouslySetInnerHTML":
                        if (r != null)
                            throw Error(f(91));
                        break;
                    default:
                        Et(t, e, c, r, l, null)
                    }
            qr(t, a, n, u),
            Yu(t);
            return;
        case "option":
            for (m in l)
                if (l.hasOwnProperty(m) && (a = l[m],
                a != null))
                    switch (m) {
                    case "selected":
                        t.selected = a && typeof a != "function" && typeof a != "symbol";
                        break;
                    default:
                        Et(t, e, m, a, l, null)
                    }
            return;
        case "dialog":
            st("beforetoggle", t),
            st("toggle", t),
            st("cancel", t),
            st("close", t);
            break;
        case "iframe":
        case "object":
            st("load", t);
            break;
        case "video":
        case "audio":
            for (a = 0; a < su.length; a++)
                st(su[a], t);
            break;
        case "image":
            st("error", t),
            st("load", t);
            break;
        case "details":
            st("toggle", t);
            break;
        case "embed":
        case "source":
        case "link":
            st("error", t),
            st("load", t);
        case "area":
        case "base":
        case "br":
        case "col":
        case "hr":
        case "keygen":
        case "meta":
        case "param":
        case "track":
        case "wbr":
        case "menuitem":
            for (T in l)
                if (l.hasOwnProperty(T) && (a = l[T],
                a != null))
                    switch (T) {
                    case "children":
                    case "dangerouslySetInnerHTML":
                        throw Error(f(137, e));
                    default:
                        Et(t, e, T, a, l, null)
                    }
            return;
        default:
            if (rc(e)) {
                for (N in l)
                    l.hasOwnProperty(N) && (a = l[N],
                    a !== void 0 && kf(t, e, N, a, l, void 0));
                return
            }
        }
        for (r in l)
            l.hasOwnProperty(r) && (a = l[r],
            a != null && Et(t, e, r, a, l, null))
    }
    function qy(t, e, l, a) {
        switch (e) {
        case "div":
        case "span":
        case "svg":
        case "path":
        case "a":
        case "g":
        case "p":
        case "li":
            break;
        case "input":
            var n = null
              , u = null
              , c = null
              , r = null
              , m = null
              , T = null
              , N = null;
            for (_ in l) {
                var L = l[_];
                if (l.hasOwnProperty(_) && L != null)
                    switch (_) {
                    case "checked":
                        break;
                    case "value":
                        break;
                    case "defaultValue":
                        m = L;
                    default:
                        a.hasOwnProperty(_) || Et(t, e, _, null, a, L)
                    }
            }
            for (var A in a) {
                var _ = a[A];
                if (L = l[A],
                a.hasOwnProperty(A) && (_ != null || L != null))
                    switch (A) {
                    case "type":
                        u = _;
                        break;
                    case "name":
                        n = _;
                        break;
                    case "checked":
                        T = _;
                        break;
                    case "defaultChecked":
                        N = _;
                        break;
                    case "value":
                        c = _;
                        break;
                    case "defaultValue":
                        r = _;
                        break;
                    case "children":
                    case "dangerouslySetInnerHTML":
                        if (_ != null)
                            throw Error(f(137, e));
                        break;
                    default:
                        _ !== L && Et(t, e, A, _, a, L)
                    }
            }
            cc(t, c, r, m, T, N, u, n);
            return;
        case "select":
            _ = c = r = A = null;
            for (u in l)
                if (m = l[u],
                l.hasOwnProperty(u) && m != null)
                    switch (u) {
                    case "value":
                        break;
                    case "multiple":
                        _ = m;
                    default:
                        a.hasOwnProperty(u) || Et(t, e, u, null, a, m)
                    }
            for (n in a)
                if (u = a[n],
                m = l[n],
                a.hasOwnProperty(n) && (u != null || m != null))
                    switch (n) {
                    case "value":
                        A = u;
                        break;
                    case "defaultValue":
                        r = u;
                        break;
                    case "multiple":
                        c = u;
                    default:
                        u !== m && Et(t, e, n, u, a, m)
                    }
            e = r,
            l = c,
            a = _,
            A != null ? ja(t, !!l, A, !1) : !!a != !!l && (e != null ? ja(t, !!l, e, !0) : ja(t, !!l, l ? [] : "", !1));
            return;
        case "textarea":
            _ = A = null;
            for (r in l)
                if (n = l[r],
                l.hasOwnProperty(r) && n != null && !a.hasOwnProperty(r))
                    switch (r) {
                    case "value":
                        break;
                    case "children":
                        break;
                    default:
                        Et(t, e, r, null, a, n)
                    }
            for (c in a)
                if (n = a[c],
                u = l[c],
                a.hasOwnProperty(c) && (n != null || u != null))
                    switch (c) {
                    case "value":
                        A = n;
                        break;
                    case "defaultValue":
                        _ = n;
                        break;
                    case "children":
                        break;
                    case "dangerouslySetInnerHTML":
                        if (n != null)
                            throw Error(f(91));
                        break;
                    default:
                        n !== u && Et(t, e, c, n, a, u)
                    }
            Yr(t, A, _);
            return;
        case "option":
            for (var it in l)
                if (A = l[it],
                l.hasOwnProperty(it) && A != null && !a.hasOwnProperty(it))
                    switch (it) {
                    case "selected":
                        t.selected = !1;
                        break;
                    default:
                        Et(t, e, it, null, a, A)
                    }
            for (m in a)
                if (A = a[m],
                _ = l[m],
                a.hasOwnProperty(m) && A !== _ && (A != null || _ != null))
                    switch (m) {
                    case "selected":
                        t.selected = A && typeof A != "function" && typeof A != "symbol";
                        break;
                    default:
                        Et(t, e, m, A, a, _)
                    }
            return;
        case "img":
        case "link":
        case "area":
        case "base":
        case "br":
        case "col":
        case "embed":
        case "hr":
        case "keygen":
        case "meta":
        case "param":
        case "source":
        case "track":
        case "wbr":
        case "menuitem":
            for (var lt in l)
                A = l[lt],
                l.hasOwnProperty(lt) && A != null && !a.hasOwnProperty(lt) && Et(t, e, lt, null, a, A);
            for (T in a)
                if (A = a[T],
                _ = l[T],
                a.hasOwnProperty(T) && A !== _ && (A != null || _ != null))
                    switch (T) {
                    case "children":
                    case "dangerouslySetInnerHTML":
                        if (A != null)
                            throw Error(f(137, e));
                        break;
                    default:
                        Et(t, e, T, A, a, _)
                    }
            return;
        default:
            if (rc(e)) {
                for (var Tt in l)
                    A = l[Tt],
                    l.hasOwnProperty(Tt) && A !== void 0 && !a.hasOwnProperty(Tt) && kf(t, e, Tt, void 0, a, A);
                for (N in a)
                    A = a[N],
                    _ = l[N],
                    !a.hasOwnProperty(N) || A === _ || A === void 0 && _ === void 0 || kf(t, e, N, A, a, _);
                return
            }
        }
        for (var b in l)
            A = l[b],
            l.hasOwnProperty(b) && A != null && !a.hasOwnProperty(b) && Et(t, e, b, null, a, A);
        for (L in a)
            A = a[L],
            _ = l[L],
            !a.hasOwnProperty(L) || A === _ || A == null && _ == null || Et(t, e, L, A, a, _)
    }
    var $f = null
      , Wf = null;
    function Li(t) {
        return t.nodeType === 9 ? t : t.ownerDocument
    }
    function Xd(t) {
        switch (t) {
        case "http://www.w3.org/2000/svg":
            return 1;
        case "http://www.w3.org/1998/Math/MathML":
            return 2;
        default:
            return 0
        }
    }
    function Qd(t, e) {
        if (t === 0)
            switch (e) {
            case "svg":
                return 1;
            case "math":
                return 2;
            default:
                return 0
            }
        return t === 1 && e === "foreignObject" ? 0 : t
    }
    function Ff(t, e) {
        return t === "textarea" || t === "noscript" || typeof e.children == "string" || typeof e.children == "number" || typeof e.children == "bigint" || typeof e.dangerouslySetInnerHTML == "object" && e.dangerouslySetInnerHTML !== null && e.dangerouslySetInnerHTML.__html != null
    }
    var If = null;
    function Gy() {
        var t = window.event;
        return t && t.type === "popstate" ? t === If ? !1 : (If = t,
        !0) : (If = null,
        !1)
    }
    var Zd = typeof setTimeout == "function" ? setTimeout : void 0
      , Xy = typeof clearTimeout == "function" ? clearTimeout : void 0
      , Vd = typeof Promise == "function" ? Promise : void 0
      , Qy = typeof queueMicrotask == "function" ? queueMicrotask : typeof Vd < "u" ? function(t) {
        return Vd.resolve(null).then(t).catch(Zy)
    }
    : Zd;
    function Zy(t) {
        setTimeout(function() {
            throw t
        })
    }
    function Il(t) {
        return t === "head"
    }
    function Kd(t, e) {
        var l = e
          , a = 0
          , n = 0;
        do {
            var u = l.nextSibling;
            if (t.removeChild(l),
            u && u.nodeType === 8)
                if (l = u.data,
                l === "/$") {
                    if (0 < a && 8 > a) {
                        l = a;
                        var c = t.ownerDocument;
                        if (l & 1 && mu(c.documentElement),
                        l & 2 && mu(c.body),
                        l & 4)
                            for (l = c.head,
                            mu(l),
                            c = l.firstChild; c; ) {
                                var r = c.nextSibling
                                  , m = c.nodeName;
                                c[Ll] || m === "SCRIPT" || m === "STYLE" || m === "LINK" && c.rel.toLowerCase() === "stylesheet" || l.removeChild(c),
                                c = r
                            }
                    }
                    if (n === 0) {
                        t.removeChild(u),
                        Eu(e);
                        return
                    }
                    n--
                } else
                    l === "$" || l === "$?" || l === "$!" ? n++ : a = l.charCodeAt(0) - 48;
            else
                a = 0;
            l = u
        } while (l);
        Eu(e)
    }
    function Pf(t) {
        var e = t.firstChild;
        for (e && e.nodeType === 10 && (e = e.nextSibling); e; ) {
            var l = e;
            switch (e = e.nextSibling,
            l.nodeName) {
            case "HTML":
            case "HEAD":
            case "BODY":
                Pf(l),
                Ae(l);
                continue;
            case "SCRIPT":
            case "STYLE":
                continue;
            case "LINK":
                if (l.rel.toLowerCase() === "stylesheet")
                    continue
            }
            t.removeChild(l)
        }
    }
    function Vy(t, e, l, a) {
        for (; t.nodeType === 1; ) {
            var n = l;
            if (t.nodeName.toLowerCase() !== e.toLowerCase()) {
                if (!a && (t.nodeName !== "INPUT" || t.type !== "hidden"))
                    break
            } else if (a) {
                if (!t[Ll])
                    switch (e) {
                    case "meta":
                        if (!t.hasAttribute("itemprop"))
                            break;
                        return t;
                    case "link":
                        if (u = t.getAttribute("rel"),
                        u === "stylesheet" && t.hasAttribute("data-precedence"))
                            break;
                        if (u !== n.rel || t.getAttribute("href") !== (n.href == null || n.href === "" ? null : n.href) || t.getAttribute("crossorigin") !== (n.crossOrigin == null ? null : n.crossOrigin) || t.getAttribute("title") !== (n.title == null ? null : n.title))
                            break;
                        return t;
                    case "style":
                        if (t.hasAttribute("data-precedence"))
                            break;
                        return t;
                    case "script":
                        if (u = t.getAttribute("src"),
                        (u !== (n.src == null ? null : n.src) || t.getAttribute("type") !== (n.type == null ? null : n.type) || t.getAttribute("crossorigin") !== (n.crossOrigin == null ? null : n.crossOrigin)) && u && t.hasAttribute("async") && !t.hasAttribute("itemprop"))
                            break;
                        return t;
                    default:
                        return t
                    }
            } else if (e === "input" && t.type === "hidden") {
                var u = n.name == null ? null : "" + n.name;
                if (n.type === "hidden" && t.getAttribute("name") === u)
                    return t
            } else
                return t;
            if (t = Fe(t.nextSibling),
            t === null)
                break
        }
        return null
    }
    function Ky(t, e, l) {
        if (e === "")
            return null;
        for (; t.nodeType !== 3; )
            if ((t.nodeType !== 1 || t.nodeName !== "INPUT" || t.type !== "hidden") && !l || (t = Fe(t.nextSibling),
            t === null))
                return null;
        return t
    }
    function tr(t) {
        return t.data === "$!" || t.data === "$?" && t.ownerDocument.readyState === "complete"
    }
    function Jy(t, e) {
        var l = t.ownerDocument;
        if (t.data !== "$?" || l.readyState === "complete")
            e();
        else {
            var a = function() {
                e(),
                l.removeEventListener("DOMContentLoaded", a)
            };
            l.addEventListener("DOMContentLoaded", a),
            t._reactRetry = a
        }
    }
    function Fe(t) {
        for (; t != null; t = t.nextSibling) {
            var e = t.nodeType;
            if (e === 1 || e === 3)
                break;
            if (e === 8) {
                if (e = t.data,
                e === "$" || e === "$!" || e === "$?" || e === "F!" || e === "F")
                    break;
                if (e === "/$")
                    return null
            }
        }
        return t
    }
    var er = null;
    function Jd(t) {
        t = t.previousSibling;
        for (var e = 0; t; ) {
            if (t.nodeType === 8) {
                var l = t.data;
                if (l === "$" || l === "$!" || l === "$?") {
                    if (e === 0)
                        return t;
                    e--
                } else
                    l === "/$" && e++
            }
            t = t.previousSibling
        }
        return null
    }
    function kd(t, e, l) {
        switch (e = Li(l),
        t) {
        case "html":
            if (t = e.documentElement,
            !t)
                throw Error(f(452));
            return t;
        case "head":
            if (t = e.head,
            !t)
                throw Error(f(453));
            return t;
        case "body":
            if (t = e.body,
            !t)
                throw Error(f(454));
            return t;
        default:
            throw Error(f(451))
        }
    }
    function mu(t) {
        for (var e = t.attributes; e.length; )
            t.removeAttributeNode(e[0]);
        Ae(t)
    }
    var Ge = new Map
      , $d = new Set;
    function Hi(t) {
        return typeof t.getRootNode == "function" ? t.getRootNode() : t.nodeType === 9 ? t : t.ownerDocument
    }
    var Ol = X.d;
    X.d = {
        f: ky,
        r: $y,
        D: Wy,
        C: Fy,
        L: Iy,
        m: Py,
        X: ev,
        S: tv,
        M: lv
    };
    function ky() {
        var t = Ol.f()
          , e = Ri();
        return t || e
    }
    function $y(t) {
        var e = dl(t);
        e !== null && e.tag === 5 && e.type === "form" ? ys(e) : Ol.r(t)
    }
    var yn = typeof document > "u" ? null : document;
    function Wd(t, e, l) {
        var a = yn;
        if (a && typeof e == "string" && e) {
            var n = Le(e);
            n = 'link[rel="' + t + '"][href="' + n + '"]',
            typeof l == "string" && (n += '[crossorigin="' + l + '"]'),
            $d.has(n) || ($d.add(n),
            t = {
                rel: t,
                crossOrigin: l,
                href: e
            },
            a.querySelector(n) === null && (e = a.createElement("link"),
            ue(e, "link", t),
            Gt(e),
            a.head.appendChild(e)))
        }
    }
    function Wy(t) {
        Ol.D(t),
        Wd("dns-prefetch", t, null)
    }
    function Fy(t, e) {
        Ol.C(t, e),
        Wd("preconnect", t, e)
    }
    function Iy(t, e, l) {
        Ol.L(t, e, l);
        var a = yn;
        if (a && t && e) {
            var n = 'link[rel="preload"][as="' + Le(e) + '"]';
            e === "image" && l && l.imageSrcSet ? (n += '[imagesrcset="' + Le(l.imageSrcSet) + '"]',
            typeof l.imageSizes == "string" && (n += '[imagesizes="' + Le(l.imageSizes) + '"]')) : n += '[href="' + Le(t) + '"]';
            var u = n;
            switch (e) {
            case "style":
                u = vn(t);
                break;
            case "script":
                u = gn(t)
            }
            Ge.has(u) || (t = R({
                rel: "preload",
                href: e === "image" && l && l.imageSrcSet ? void 0 : t,
                as: e
            }, l),
            Ge.set(u, t),
            a.querySelector(n) !== null || e === "style" && a.querySelector(hu(u)) || e === "script" && a.querySelector(yu(u)) || (e = a.createElement("link"),
            ue(e, "link", t),
            Gt(e),
            a.head.appendChild(e)))
        }
    }
    function Py(t, e) {
        Ol.m(t, e);
        var l = yn;
        if (l && t) {
            var a = e && typeof e.as == "string" ? e.as : "script"
              , n = 'link[rel="modulepreload"][as="' + Le(a) + '"][href="' + Le(t) + '"]'
              , u = n;
            switch (a) {
            case "audioworklet":
            case "paintworklet":
            case "serviceworker":
            case "sharedworker":
            case "worker":
            case "script":
                u = gn(t)
            }
            if (!Ge.has(u) && (t = R({
                rel: "modulepreload",
                href: t
            }, e),
            Ge.set(u, t),
            l.querySelector(n) === null)) {
                switch (a) {
                case "audioworklet":
                case "paintworklet":
                case "serviceworker":
                case "sharedworker":
                case "worker":
                case "script":
                    if (l.querySelector(yu(u)))
                        return
                }
                a = l.createElement("link"),
                ue(a, "link", t),
                Gt(a),
                l.head.appendChild(a)
            }
        }
    }
    function tv(t, e, l) {
        Ol.S(t, e, l);
        var a = yn;
        if (a && t) {
            var n = ml(a).hoistableStyles
              , u = vn(t);
            e = e || "default";
            var c = n.get(u);
            if (!c) {
                var r = {
                    loading: 0,
                    preload: null
                };
                if (c = a.querySelector(hu(u)))
                    r.loading = 5;
                else {
                    t = R({
                        rel: "stylesheet",
                        href: t,
                        "data-precedence": e
                    }, l),
                    (l = Ge.get(u)) && lr(t, l);
                    var m = c = a.createElement("link");
                    Gt(m),
                    ue(m, "link", t),
                    m._p = new Promise(function(T, N) {
                        m.onload = T,
                        m.onerror = N
                    }
                    ),
                    m.addEventListener("load", function() {
                        r.loading |= 1
                    }),
                    m.addEventListener("error", function() {
                        r.loading |= 2
                    }),
                    r.loading |= 4,
                    wi(c, e, a)
                }
                c = {
                    type: "stylesheet",
                    instance: c,
                    count: 1,
                    state: r
                },
                n.set(u, c)
            }
        }
    }
    function ev(t, e) {
        Ol.X(t, e);
        var l = yn;
        if (l && t) {
            var a = ml(l).hoistableScripts
              , n = gn(t)
              , u = a.get(n);
            u || (u = l.querySelector(yu(n)),
            u || (t = R({
                src: t,
                async: !0
            }, e),
            (e = Ge.get(n)) && ar(t, e),
            u = l.createElement("script"),
            Gt(u),
            ue(u, "link", t),
            l.head.appendChild(u)),
            u = {
                type: "script",
                instance: u,
                count: 1,
                state: null
            },
            a.set(n, u))
        }
    }
    function lv(t, e) {
        Ol.M(t, e);
        var l = yn;
        if (l && t) {
            var a = ml(l).hoistableScripts
              , n = gn(t)
              , u = a.get(n);
            u || (u = l.querySelector(yu(n)),
            u || (t = R({
                src: t,
                async: !0,
                type: "module"
            }, e),
            (e = Ge.get(n)) && ar(t, e),
            u = l.createElement("script"),
            Gt(u),
            ue(u, "link", t),
            l.head.appendChild(u)),
            u = {
                type: "script",
                instance: u,
                count: 1,
                state: null
            },
            a.set(n, u))
        }
    }
    function Fd(t, e, l, a) {
        var n = (n = at.current) ? Hi(n) : null;
        if (!n)
            throw Error(f(446));
        switch (t) {
        case "meta":
        case "title":
            return null;
        case "style":
            return typeof l.precedence == "string" && typeof l.href == "string" ? (e = vn(l.href),
            l = ml(n).hoistableStyles,
            a = l.get(e),
            a || (a = {
                type: "style",
                instance: null,
                count: 0,
                state: null
            },
            l.set(e, a)),
            a) : {
                type: "void",
                instance: null,
                count: 0,
                state: null
            };
        case "link":
            if (l.rel === "stylesheet" && typeof l.href == "string" && typeof l.precedence == "string") {
                t = vn(l.href);
                var u = ml(n).hoistableStyles
                  , c = u.get(t);
                if (c || (n = n.ownerDocument || n,
                c = {
                    type: "stylesheet",
                    instance: null,
                    count: 0,
                    state: {
                        loading: 0,
                        preload: null
                    }
                },
                u.set(t, c),
                (u = n.querySelector(hu(t))) && !u._p && (c.instance = u,
                c.state.loading = 5),
                Ge.has(t) || (l = {
                    rel: "preload",
                    as: "style",
                    href: l.href,
                    crossOrigin: l.crossOrigin,
                    integrity: l.integrity,
                    media: l.media,
                    hrefLang: l.hrefLang,
                    referrerPolicy: l.referrerPolicy
                },
                Ge.set(t, l),
                u || av(n, t, l, c.state))),
                e && a === null)
                    throw Error(f(528, ""));
                return c
            }
            if (e && a !== null)
                throw Error(f(529, ""));
            return null;
        case "script":
            return e = l.async,
            l = l.src,
            typeof l == "string" && e && typeof e != "function" && typeof e != "symbol" ? (e = gn(l),
            l = ml(n).hoistableScripts,
            a = l.get(e),
            a || (a = {
                type: "script",
                instance: null,
                count: 0,
                state: null
            },
            l.set(e, a)),
            a) : {
                type: "void",
                instance: null,
                count: 0,
                state: null
            };
        default:
            throw Error(f(444, t))
        }
    }
    function vn(t) {
        return 'href="' + Le(t) + '"'
    }
    function hu(t) {
        return 'link[rel="stylesheet"][' + t + "]"
    }
    function Id(t) {
        return R({}, t, {
            "data-precedence": t.precedence,
            precedence: null
        })
    }
    function av(t, e, l, a) {
        t.querySelector('link[rel="preload"][as="style"][' + e + "]") ? a.loading = 1 : (e = t.createElement("link"),
        a.preload = e,
        e.addEventListener("load", function() {
            return a.loading |= 1
        }),
        e.addEventListener("error", function() {
            return a.loading |= 2
        }),
        ue(e, "link", l),
        Gt(e),
        t.head.appendChild(e))
    }
    function gn(t) {
        return '[src="' + Le(t) + '"]'
    }
    function yu(t) {
        return "script[async]" + t
    }
    function Pd(t, e, l) {
        if (e.count++,
        e.instance === null)
            switch (e.type) {
            case "style":
                var a = t.querySelector('style[data-href~="' + Le(l.href) + '"]');
                if (a)
                    return e.instance = a,
                    Gt(a),
                    a;
                var n = R({}, l, {
                    "data-href": l.href,
                    "data-precedence": l.precedence,
                    href: null,
                    precedence: null
                });
                return a = (t.ownerDocument || t).createElement("style"),
                Gt(a),
                ue(a, "style", n),
                wi(a, l.precedence, t),
                e.instance = a;
            case "stylesheet":
                n = vn(l.href);
                var u = t.querySelector(hu(n));
                if (u)
                    return e.state.loading |= 4,
                    e.instance = u,
                    Gt(u),
                    u;
                a = Id(l),
                (n = Ge.get(n)) && lr(a, n),
                u = (t.ownerDocument || t).createElement("link"),
                Gt(u);
                var c = u;
                return c._p = new Promise(function(r, m) {
                    c.onload = r,
                    c.onerror = m
                }
                ),
                ue(u, "link", a),
                e.state.loading |= 4,
                wi(u, l.precedence, t),
                e.instance = u;
            case "script":
                return u = gn(l.src),
                (n = t.querySelector(yu(u))) ? (e.instance = n,
                Gt(n),
                n) : (a = l,
                (n = Ge.get(u)) && (a = R({}, l),
                ar(a, n)),
                t = t.ownerDocument || t,
                n = t.createElement("script"),
                Gt(n),
                ue(n, "link", a),
                t.head.appendChild(n),
                e.instance = n);
            case "void":
                return null;
            default:
                throw Error(f(443, e.type))
            }
        else
            e.type === "stylesheet" && (e.state.loading & 4) === 0 && (a = e.instance,
            e.state.loading |= 4,
            wi(a, l.precedence, t));
        return e.instance
    }
    function wi(t, e, l) {
        for (var a = l.querySelectorAll('link[rel="stylesheet"][data-precedence],style[data-precedence]'), n = a.length ? a[a.length - 1] : null, u = n, c = 0; c < a.length; c++) {
            var r = a[c];
            if (r.dataset.precedence === e)
                u = r;
            else if (u !== n)
                break
        }
        u ? u.parentNode.insertBefore(t, u.nextSibling) : (e = l.nodeType === 9 ? l.head : l,
        e.insertBefore(t, e.firstChild))
    }
    function lr(t, e) {
        t.crossOrigin == null && (t.crossOrigin = e.crossOrigin),
        t.referrerPolicy == null && (t.referrerPolicy = e.referrerPolicy),
        t.title == null && (t.title = e.title)
    }
    function ar(t, e) {
        t.crossOrigin == null && (t.crossOrigin = e.crossOrigin),
        t.referrerPolicy == null && (t.referrerPolicy = e.referrerPolicy),
        t.integrity == null && (t.integrity = e.integrity)
    }
    var Bi = null;
    function tm(t, e, l) {
        if (Bi === null) {
            var a = new Map
              , n = Bi = new Map;
            n.set(l, a)
        } else
            n = Bi,
            a = n.get(l),
            a || (a = new Map,
            n.set(l, a));
        if (a.has(t))
            return a;
        for (a.set(t, null),
        l = l.getElementsByTagName(t),
        n = 0; n < l.length; n++) {
            var u = l[n];
            if (!(u[Ll] || u[Vt] || t === "link" && u.getAttribute("rel") === "stylesheet") && u.namespaceURI !== "http://www.w3.org/2000/svg") {
                var c = u.getAttribute(e) || "";
                c = t + c;
                var r = a.get(c);
                r ? r.push(u) : a.set(c, [u])
            }
        }
        return a
    }
    function em(t, e, l) {
        t = t.ownerDocument || t,
        t.head.insertBefore(l, e === "title" ? t.querySelector("head > title") : null)
    }
    function nv(t, e, l) {
        if (l === 1 || e.itemProp != null)
            return !1;
        switch (t) {
        case "meta":
        case "title":
            return !0;
        case "style":
            if (typeof e.precedence != "string" || typeof e.href != "string" || e.href === "")
                break;
            return !0;
        case "link":
            if (typeof e.rel != "string" || typeof e.href != "string" || e.href === "" || e.onLoad || e.onError)
                break;
            switch (e.rel) {
            case "stylesheet":
                return t = e.disabled,
                typeof e.precedence == "string" && t == null;
            default:
                return !0
            }
        case "script":
            if (e.async && typeof e.async != "function" && typeof e.async != "symbol" && !e.onLoad && !e.onError && e.src && typeof e.src == "string")
                return !0
        }
        return !1
    }
    function lm(t) {
        return !(t.type === "stylesheet" && (t.state.loading & 3) === 0)
    }
    var vu = null;
    function uv() {}
    function iv(t, e, l) {
        if (vu === null)
            throw Error(f(475));
        var a = vu;
        if (e.type === "stylesheet" && (typeof l.media != "string" || matchMedia(l.media).matches !== !1) && (e.state.loading & 4) === 0) {
            if (e.instance === null) {
                var n = vn(l.href)
                  , u = t.querySelector(hu(n));
                if (u) {
                    t = u._p,
                    t !== null && typeof t == "object" && typeof t.then == "function" && (a.count++,
                    a = ji.bind(a),
                    t.then(a, a)),
                    e.state.loading |= 4,
                    e.instance = u,
                    Gt(u);
                    return
                }
                u = t.ownerDocument || t,
                l = Id(l),
                (n = Ge.get(n)) && lr(l, n),
                u = u.createElement("link"),
                Gt(u);
                var c = u;
                c._p = new Promise(function(r, m) {
                    c.onload = r,
                    c.onerror = m
                }
                ),
                ue(u, "link", l),
                e.instance = u
            }
            a.stylesheets === null && (a.stylesheets = new Map),
            a.stylesheets.set(e, t),
            (t = e.state.preload) && (e.state.loading & 3) === 0 && (a.count++,
            e = ji.bind(a),
            t.addEventListener("load", e),
            t.addEventListener("error", e))
        }
    }
    function cv() {
        if (vu === null)
            throw Error(f(475));
        var t = vu;
        return t.stylesheets && t.count === 0 && nr(t, t.stylesheets),
        0 < t.count ? function(e) {
            var l = setTimeout(function() {
                if (t.stylesheets && nr(t, t.stylesheets),
                t.unsuspend) {
                    var a = t.unsuspend;
                    t.unsuspend = null,
                    a()
                }
            }, 6e4);
            return t.unsuspend = e,
            function() {
                t.unsuspend = null,
                clearTimeout(l)
            }
        }
        : null
    }
    function ji() {
        if (this.count--,
        this.count === 0) {
            if (this.stylesheets)
                nr(this, this.stylesheets);
            else if (this.unsuspend) {
                var t = this.unsuspend;
                this.unsuspend = null,
                t()
            }
        }
    }
    var Yi = null;
    function nr(t, e) {
        t.stylesheets = null,
        t.unsuspend !== null && (t.count++,
        Yi = new Map,
        e.forEach(fv, t),
        Yi = null,
        ji.call(t))
    }
    function fv(t, e) {
        if (!(e.state.loading & 4)) {
            var l = Yi.get(t);
            if (l)
                var a = l.get(null);
            else {
                l = new Map,
                Yi.set(t, l);
                for (var n = t.querySelectorAll("link[data-precedence],style[data-precedence]"), u = 0; u < n.length; u++) {
                    var c = n[u];
                    (c.nodeName === "LINK" || c.getAttribute("media") !== "not all") && (l.set(c.dataset.precedence, c),
                    a = c)
                }
                a && l.set(null, a)
            }
            n = e.instance,
            c = n.getAttribute("data-precedence"),
            u = l.get(c) || a,
            u === a && l.set(null, n),
            l.set(c, n),
            this.count++,
            a = ji.bind(this),
            n.addEventListener("load", a),
            n.addEventListener("error", a),
            u ? u.parentNode.insertBefore(n, u.nextSibling) : (t = t.nodeType === 9 ? t.head : t,
            t.insertBefore(n, t.firstChild)),
            e.state.loading |= 4
        }
    }
    var gu = {
        $$typeof: Q,
        Provider: null,
        Consumer: null,
        _currentValue: tt,
        _currentValue2: tt,
        _threadCount: 0
    };
    function rv(t, e, l, a, n, u, c, r) {
        this.tag = 1,
        this.containerInfo = t,
        this.pingCache = this.current = this.pendingChildren = null,
        this.timeoutHandle = -1,
        this.callbackNode = this.next = this.pendingContext = this.context = this.cancelPendingCommit = null,
        this.callbackPriority = 0,
        this.expirationTimes = xn(-1),
        this.entangledLanes = this.shellSuspendCounter = this.errorRecoveryDisabledLanes = this.expiredLanes = this.warmLanes = this.pingedLanes = this.suspendedLanes = this.pendingLanes = 0,
        this.entanglements = xn(0),
        this.hiddenUpdates = xn(null),
        this.identifierPrefix = a,
        this.onUncaughtError = n,
        this.onCaughtError = u,
        this.onRecoverableError = c,
        this.pooledCache = null,
        this.pooledCacheLanes = 0,
        this.formState = r,
        this.incompleteTransitions = new Map
    }
    function am(t, e, l, a, n, u, c, r, m, T, N, L) {
        return t = new rv(t,e,l,c,r,m,T,L),
        e = 1,
        u === !0 && (e |= 24),
        u = xe(3, null, null, e),
        t.current = u,
        u.stateNode = t,
        e = jc(),
        e.refCount++,
        t.pooledCache = e,
        e.refCount++,
        u.memoizedState = {
            element: a,
            isDehydrated: l,
            cache: e
        },
        Xc(u),
        t
    }
    function nm(t) {
        return t ? (t = Ja,
        t) : Ja
    }
    function um(t, e, l, a, n, u) {
        n = nm(n),
        a.context === null ? a.context = n : a.pendingContext = n,
        a = Yl(e),
        a.payload = {
            element: l
        },
        u = u === void 0 ? null : u,
        u !== null && (a.callback = u),
        l = ql(t, a, e),
        l !== null && (Ne(l, t, e),
        Jn(l, t, e))
    }
    function im(t, e) {
        if (t = t.memoizedState,
        t !== null && t.dehydrated !== null) {
            var l = t.retryLane;
            t.retryLane = l !== 0 && l < e ? l : e
        }
    }
    function ur(t, e) {
        im(t, e),
        (t = t.alternate) && im(t, e)
    }
    function cm(t) {
        if (t.tag === 13) {
            var e = Ka(t, 67108864);
            e !== null && Ne(e, t, 67108864),
            ur(t, 67108864)
        }
    }
    var qi = !0;
    function ov(t, e, l, a) {
        var n = M.T;
        M.T = null;
        var u = X.p;
        try {
            X.p = 2,
            ir(t, e, l, a)
        } finally {
            X.p = u,
            M.T = n
        }
    }
    function sv(t, e, l, a) {
        var n = M.T;
        M.T = null;
        var u = X.p;
        try {
            X.p = 8,
            ir(t, e, l, a)
        } finally {
            X.p = u,
            M.T = n
        }
    }
    function ir(t, e, l, a) {
        if (qi) {
            var n = cr(a);
            if (n === null)
                Jf(t, e, a, Gi, l),
                rm(t, a);
            else if (mv(n, t, e, l, a))
                a.stopPropagation();
            else if (rm(t, a),
            e & 4 && -1 < dv.indexOf(t)) {
                for (; n !== null; ) {
                    var u = dl(n);
                    if (u !== null)
                        switch (u.tag) {
                        case 3:
                            if (u = u.stateNode,
                            u.current.memoizedState.isDehydrated) {
                                var c = pe(u.pendingLanes);
                                if (c !== 0) {
                                    var r = u;
                                    for (r.pendingLanes |= 2,
                                    r.entangledLanes |= 2; c; ) {
                                        var m = 1 << 31 - ce(c);
                                        r.entanglements[1] |= m,
                                        c &= ~m
                                    }
                                    il(u),
                                    (gt & 6) === 0 && (_i = ee() + 500,
                                    ou(0))
                                }
                            }
                            break;
                        case 13:
                            r = Ka(u, 2),
                            r !== null && Ne(r, u, 2),
                            Ri(),
                            ur(u, 2)
                        }
                    if (u = cr(a),
                    u === null && Jf(t, e, a, Gi, l),
                    u === n)
                        break;
                    n = u
                }
                n !== null && a.stopPropagation()
            } else
                Jf(t, e, a, null, l)
        }
    }
    function cr(t) {
        return t = sc(t),
        fr(t)
    }
    var Gi = null;
    function fr(t) {
        if (Gi = null,
        t = sl(t),
        t !== null) {
            var e = v(t);
            if (e === null)
                t = null;
            else {
                var l = e.tag;
                if (l === 13) {
                    if (t = x(e),
                    t !== null)
                        return t;
                    t = null
                } else if (l === 3) {
                    if (e.stateNode.current.memoizedState.isDehydrated)
                        return e.tag === 3 ? e.stateNode.containerInfo : null;
                    t = null
                } else
                    e !== t && (t = null)
            }
        }
        return Gi = t,
        null
    }
    function fm(t) {
        switch (t) {
        case "beforetoggle":
        case "cancel":
        case "click":
        case "close":
        case "contextmenu":
        case "copy":
        case "cut":
        case "auxclick":
        case "dblclick":
        case "dragend":
        case "dragstart":
        case "drop":
        case "focusin":
        case "focusout":
        case "input":
        case "invalid":
        case "keydown":
        case "keypress":
        case "keyup":
        case "mousedown":
        case "mouseup":
        case "paste":
        case "pause":
        case "play":
        case "pointercancel":
        case "pointerdown":
        case "pointerup":
        case "ratechange":
        case "reset":
        case "resize":
        case "seeked":
        case "submit":
        case "toggle":
        case "touchcancel":
        case "touchend":
        case "touchstart":
        case "volumechange":
        case "change":
        case "selectionchange":
        case "textInput":
        case "compositionstart":
        case "compositionend":
        case "compositionupdate":
        case "beforeblur":
        case "afterblur":
        case "beforeinput":
        case "blur":
        case "fullscreenchange":
        case "focus":
        case "hashchange":
        case "popstate":
        case "select":
        case "selectstart":
            return 2;
        case "drag":
        case "dragenter":
        case "dragexit":
        case "dragleave":
        case "dragover":
        case "mousemove":
        case "mouseout":
        case "mouseover":
        case "pointermove":
        case "pointerout":
        case "pointerover":
        case "scroll":
        case "touchmove":
        case "wheel":
        case "mouseenter":
        case "mouseleave":
        case "pointerenter":
        case "pointerleave":
            return 8;
        case "message":
            switch (Ua()) {
            case fa:
                return 2;
            case An:
                return 8;
            case ra:
            case lc:
                return 32;
            case Ca:
                return 268435456;
            default:
                return 32
            }
        default:
            return 32
        }
    }
    var rr = !1
      , Pl = null
      , ta = null
      , ea = null
      , pu = new Map
      , Su = new Map
      , la = []
      , dv = "mousedown mouseup touchcancel touchend touchstart auxclick dblclick pointercancel pointerdown pointerup dragend dragstart drop compositionend compositionstart keydown keypress keyup input textInput copy cut paste click change contextmenu reset".split(" ");
    function rm(t, e) {
        switch (t) {
        case "focusin":
        case "focusout":
            Pl = null;
            break;
        case "dragenter":
        case "dragleave":
            ta = null;
            break;
        case "mouseover":
        case "mouseout":
            ea = null;
            break;
        case "pointerover":
        case "pointerout":
            pu.delete(e.pointerId);
            break;
        case "gotpointercapture":
        case "lostpointercapture":
            Su.delete(e.pointerId)
        }
    }
    function bu(t, e, l, a, n, u) {
        return t === null || t.nativeEvent !== u ? (t = {
            blockedOn: e,
            domEventName: l,
            eventSystemFlags: a,
            nativeEvent: u,
            targetContainers: [n]
        },
        e !== null && (e = dl(e),
        e !== null && cm(e)),
        t) : (t.eventSystemFlags |= a,
        e = t.targetContainers,
        n !== null && e.indexOf(n) === -1 && e.push(n),
        t)
    }
    function mv(t, e, l, a, n) {
        switch (e) {
        case "focusin":
            return Pl = bu(Pl, t, e, l, a, n),
            !0;
        case "dragenter":
            return ta = bu(ta, t, e, l, a, n),
            !0;
        case "mouseover":
            return ea = bu(ea, t, e, l, a, n),
            !0;
        case "pointerover":
            var u = n.pointerId;
            return pu.set(u, bu(pu.get(u) || null, t, e, l, a, n)),
            !0;
        case "gotpointercapture":
            return u = n.pointerId,
            Su.set(u, bu(Su.get(u) || null, t, e, l, a, n)),
            !0
        }
        return !1
    }
    function om(t) {
        var e = sl(t.target);
        if (e !== null) {
            var l = v(e);
            if (l !== null) {
                if (e = l.tag,
                e === 13) {
                    if (e = x(l),
                    e !== null) {
                        t.blockedOn = e,
                        Dn(t.priority, function() {
                            if (l.tag === 13) {
                                var a = Me();
                                a = tl(a);
                                var n = Ka(l, a);
                                n !== null && Ne(n, l, a),
                                ur(l, a)
                            }
                        });
                        return
                    }
                } else if (e === 3 && l.stateNode.current.memoizedState.isDehydrated) {
                    t.blockedOn = l.tag === 3 ? l.stateNode.containerInfo : null;
                    return
                }
            }
        }
        t.blockedOn = null
    }
    function Xi(t) {
        if (t.blockedOn !== null)
            return !1;
        for (var e = t.targetContainers; 0 < e.length; ) {
            var l = cr(t.nativeEvent);
            if (l === null) {
                l = t.nativeEvent;
                var a = new l.constructor(l.type,l);
                oc = a,
                l.target.dispatchEvent(a),
                oc = null
            } else
                return e = dl(l),
                e !== null && cm(e),
                t.blockedOn = l,
                !1;
            e.shift()
        }
        return !0
    }
    function sm(t, e, l) {
        Xi(t) && l.delete(e)
    }
    function hv() {
        rr = !1,
        Pl !== null && Xi(Pl) && (Pl = null),
        ta !== null && Xi(ta) && (ta = null),
        ea !== null && Xi(ea) && (ea = null),
        pu.forEach(sm),
        Su.forEach(sm)
    }
    function Qi(t, e) {
        t.blockedOn === e && (t.blockedOn = null,
        rr || (rr = !0,
        i.unstable_scheduleCallback(i.unstable_NormalPriority, hv)))
    }
    var Zi = null;
    function dm(t) {
        Zi !== t && (Zi = t,
        i.unstable_scheduleCallback(i.unstable_NormalPriority, function() {
            Zi === t && (Zi = null);
            for (var e = 0; e < t.length; e += 3) {
                var l = t[e]
                  , a = t[e + 1]
                  , n = t[e + 2];
                if (typeof a != "function") {
                    if (fr(a || l) === null)
                        continue;
                    break
                }
                var u = dl(l);
                u !== null && (t.splice(e, 3),
                e -= 3,
                ff(u, {
                    pending: !0,
                    data: n,
                    method: l.method,
                    action: a
                }, a, n))
            }
        }))
    }
    function Eu(t) {
        function e(m) {
            return Qi(m, t)
        }
        Pl !== null && Qi(Pl, t),
        ta !== null && Qi(ta, t),
        ea !== null && Qi(ea, t),
        pu.forEach(e),
        Su.forEach(e);
        for (var l = 0; l < la.length; l++) {
            var a = la[l];
            a.blockedOn === t && (a.blockedOn = null)
        }
        for (; 0 < la.length && (l = la[0],
        l.blockedOn === null); )
            om(l),
            l.blockedOn === null && la.shift();
        if (l = (t.ownerDocument || t).$$reactFormReplay,
        l != null)
            for (a = 0; a < l.length; a += 3) {
                var n = l[a]
                  , u = l[a + 1]
                  , c = n[fe] || null;
                if (typeof u == "function")
                    c || dm(l);
                else if (c) {
                    var r = null;
                    if (u && u.hasAttribute("formAction")) {
                        if (n = u,
                        c = u[fe] || null)
                            r = c.formAction;
                        else if (fr(n) !== null)
                            continue
                    } else
                        r = c.action;
                    typeof r == "function" ? l[a + 1] = r : (l.splice(a, 3),
                    a -= 3),
                    dm(l)
                }
            }
    }
    function or(t) {
        this._internalRoot = t
    }
    Vi.prototype.render = or.prototype.render = function(t) {
        var e = this._internalRoot;
        if (e === null)
            throw Error(f(409));
        var l = e.current
          , a = Me();
        um(l, a, t, e, null, null)
    }
    ,
    Vi.prototype.unmount = or.prototype.unmount = function() {
        var t = this._internalRoot;
        if (t !== null) {
            this._internalRoot = null;
            var e = t.containerInfo;
            um(t.current, 2, null, t, null, null),
            Ri(),
            e[le] = null
        }
    }
    ;
    function Vi(t) {
        this._internalRoot = t
    }
    Vi.prototype.unstable_scheduleHydration = function(t) {
        if (t) {
            var e = On();
            t = {
                blockedOn: null,
                target: t,
                priority: e
            };
            for (var l = 0; l < la.length && e !== 0 && e < la[l].priority; l++)
                ;
            la.splice(l, 0, t),
            l === 0 && om(t)
        }
    }
    ;
    var mm = o.version;
    if (mm !== "19.1.0")
        throw Error(f(527, mm, "19.1.0"));
    X.findDOMNode = function(t) {
        var e = t._reactInternals;
        if (e === void 0)
            throw typeof t.render == "function" ? Error(f(188)) : (t = Object.keys(t).join(","),
            Error(f(268, t)));
        return t = S(e),
        t = t !== null ? h(t) : null,
        t = t === null ? null : t.stateNode,
        t
    }
    ;
    var yv = {
        bundleType: 0,
        version: "19.1.0",
        rendererPackageName: "react-dom",
        currentDispatcherRef: M,
        reconcilerVersion: "19.1.0"
    };
    if (typeof __REACT_DEVTOOLS_GLOBAL_HOOK__ < "u") {
        var Ki = __REACT_DEVTOOLS_GLOBAL_HOOK__;
        if (!Ki.isDisabled && Ki.supportsFiber)
            try {
                Ue = Ki.inject(yv),
                ie = Ki
            } catch {}
    }
    return Au.createRoot = function(t, e) {
        if (!d(t))
            throw Error(f(299));
        var l = !1
          , a = ""
          , n = Ms
          , u = Ns
          , c = zs
          , r = null;
        return e != null && (e.unstable_strictMode === !0 && (l = !0),
        e.identifierPrefix !== void 0 && (a = e.identifierPrefix),
        e.onUncaughtError !== void 0 && (n = e.onUncaughtError),
        e.onCaughtError !== void 0 && (u = e.onCaughtError),
        e.onRecoverableError !== void 0 && (c = e.onRecoverableError),
        e.unstable_transitionCallbacks !== void 0 && (r = e.unstable_transitionCallbacks)),
        e = am(t, 1, !1, null, null, l, a, n, u, c, r, null),
        t[le] = e.current,
        Kf(t),
        new or(e)
    }
    ,
    Au.hydrateRoot = function(t, e, l) {
        if (!d(t))
            throw Error(f(299));
        var a = !1
          , n = ""
          , u = Ms
          , c = Ns
          , r = zs
          , m = null
          , T = null;
        return l != null && (l.unstable_strictMode === !0 && (a = !0),
        l.identifierPrefix !== void 0 && (n = l.identifierPrefix),
        l.onUncaughtError !== void 0 && (u = l.onUncaughtError),
        l.onCaughtError !== void 0 && (c = l.onCaughtError),
        l.onRecoverableError !== void 0 && (r = l.onRecoverableError),
        l.unstable_transitionCallbacks !== void 0 && (m = l.unstable_transitionCallbacks),
        l.formState !== void 0 && (T = l.formState)),
        e = am(t, 1, !0, e, l ?? null, a, n, u, c, r, m, T),
        e.context = nm(null),
        l = e.current,
        a = Me(),
        a = tl(a),
        n = Yl(a),
        n.callback = null,
        ql(l, n, a),
        l = a,
        e.current.lanes = l,
        Je(e, l),
        il(e),
        t[le] = e.current,
        Kf(t),
        new Vi(e)
    }
    ,
    Au.version = "19.1.0",
    Au
}
var Am;
function xv() {
    if (Am)
        return mr.exports;
    Am = 1;
    function i() {
        if (!(typeof __REACT_DEVTOOLS_GLOBAL_HOOK__ > "u" || typeof __REACT_DEVTOOLS_GLOBAL_HOOK__.checkDCE != "function"))
            try {
                __REACT_DEVTOOLS_GLOBAL_HOOK__.checkDCE(i)
            } catch (o) {
                console.error(o)
            }
    }
    return i(),
    mr.exports = _v(),
    mr.exports
}
var Rv = xv(), _u = {}, _m;
function Ov() {
    if (_m)
        return _u;
    _m = 1,
    Object.defineProperty(_u, "__esModule", {
        value: !0
    }),
    _u.parse = x,
    _u.serialize = h;
    const i = /^[\u0021-\u003A\u003C\u003E-\u007E]+$/
      , o = /^[\u0021-\u003A\u003C-\u007E]*$/
      , s = /^([.]?[a-z0-9]([a-z0-9-]{0,61}[a-z0-9])?)([.][a-z0-9]([a-z0-9-]{0,61}[a-z0-9])?)*$/i
      , f = /^[\u0020-\u003A\u003D-\u007E]*$/
      , d = Object.prototype.toString
      , v = ( () => {
        const C = function() {};
        return C.prototype = Object.create(null),
        C
    }
    )();
    function x(C, G) {
        const H = new v
          , k = C.length;
        if (k < 2)
            return H;
        const Z = (G == null ? void 0 : G.decode) || R;
        let q = 0;
        do {
            const et = C.indexOf("=", q);
            if (et === -1)
                break;
            const Q = C.indexOf(";", q)
              , ut = Q === -1 ? k : Q;
            if (et > ut) {
                q = C.lastIndexOf(";", et - 1) + 1;
                continue
            }
            const P = D(C, q, et)
              , Mt = S(C, et, P)
              , Nt = C.slice(P, Mt);
            if (H[Nt] === void 0) {
                let Ut = D(C, et + 1, ut)
                  , xt = S(C, ut, Ut);
                const Pt = Z(C.slice(Ut, xt));
                H[Nt] = Pt
            }
            q = ut + 1
        } while (q < k);
        return H
    }
    function D(C, G, H) {
        do {
            const k = C.charCodeAt(G);
            if (k !== 32 && k !== 9)
                return G
        } while (++G < H);
        return H
    }
    function S(C, G, H) {
        for (; G > H; ) {
            const k = C.charCodeAt(--G);
            if (k !== 32 && k !== 9)
                return G + 1
        }
        return H
    }
    function h(C, G, H) {
        const k = (H == null ? void 0 : H.encode) || encodeURIComponent;
        if (!i.test(C))
            throw new TypeError(`argument name is invalid: ${C}`);
        const Z = k(G);
        if (!o.test(Z))
            throw new TypeError(`argument val is invalid: ${G}`);
        let q = C + "=" + Z;
        if (!H)
            return q;
        if (H.maxAge !== void 0) {
            if (!Number.isInteger(H.maxAge))
                throw new TypeError(`option maxAge is invalid: ${H.maxAge}`);
            q += "; Max-Age=" + H.maxAge
        }
        if (H.domain) {
            if (!s.test(H.domain))
                throw new TypeError(`option domain is invalid: ${H.domain}`);
            q += "; Domain=" + H.domain
        }
        if (H.path) {
            if (!f.test(H.path))
                throw new TypeError(`option path is invalid: ${H.path}`);
            q += "; Path=" + H.path
        }
        if (H.expires) {
            if (!Y(H.expires) || !Number.isFinite(H.expires.valueOf()))
                throw new TypeError(`option expires is invalid: ${H.expires}`);
            q += "; Expires=" + H.expires.toUTCString()
        }
        if (H.httpOnly && (q += "; HttpOnly"),
        H.secure && (q += "; Secure"),
        H.partitioned && (q += "; Partitioned"),
        H.priority)
            switch (typeof H.priority == "string" ? H.priority.toLowerCase() : void 0) {
            case "low":
                q += "; Priority=Low";
                break;
            case "medium":
                q += "; Priority=Medium";
                break;
            case "high":
                q += "; Priority=High";
                break;
            default:
                throw new TypeError(`option priority is invalid: ${H.priority}`)
            }
        if (H.sameSite)
            switch (typeof H.sameSite == "string" ? H.sameSite.toLowerCase() : H.sameSite) {
            case !0:
            case "strict":
                q += "; SameSite=Strict";
                break;
            case "lax":
                q += "; SameSite=Lax";
                break;
            case "none":
                q += "; SameSite=None";
                break;
            default:
                throw new TypeError(`option sameSite is invalid: ${H.sameSite}`)
            }
        return q
    }
    function R(C) {
        if (C.indexOf("%") === -1)
            return C;
        try {
            return decodeURIComponent(C)
        } catch {
            return C
        }
    }
    function Y(C) {
        return d.call(C) === "[object Date]"
    }
    return _u
}
Ov();
/**
 * react-router v7.5.1
 *
 * Copyright (c) Remix Software Inc.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE.md file in the root directory of this source tree.
 *
 * @license MIT
 */
var xm = "popstate";
function Dv(i={}) {
    function o(f, d) {
        let {pathname: v, search: x, hash: D} = f.location;
        return _r("", {
            pathname: v,
            search: x,
            hash: D
        }, d.state && d.state.usr || null, d.state && d.state.key || "default")
    }
    function s(f, d) {
        return typeof d == "string" ? d : zu(d)
    }
    return Nv(o, s, null, i)
}
function Lt(i, o) {
    if (i === !1 || i === null || typeof i > "u")
        throw new Error(o)
}
function cl(i, o) {
    if (!i) {
        typeof console < "u" && console.warn(o);
        try {
            throw new Error(o)
        } catch {}
    }
}
function Mv() {
    return Math.random().toString(36).substring(2, 10)
}
function Rm(i, o) {
    return {
        usr: i.state,
        key: i.key,
        idx: o
    }
}
function _r(i, o, s=null, f) {
    return {
        pathname: typeof i == "string" ? i : i.pathname,
        search: "",
        hash: "",
        ...typeof o == "string" ? Sn(o) : o,
        state: s,
        key: o && o.key || f || Mv()
    }
}
function zu({pathname: i="/", search: o="", hash: s=""}) {
    return o && o !== "?" && (i += o.charAt(0) === "?" ? o : "?" + o),
    s && s !== "#" && (i += s.charAt(0) === "#" ? s : "#" + s),
    i
}
function Sn(i) {
    let o = {};
    if (i) {
        let s = i.indexOf("#");
        s >= 0 && (o.hash = i.substring(s),
        i = i.substring(0, s));
        let f = i.indexOf("?");
        f >= 0 && (o.search = i.substring(f),
        i = i.substring(0, f)),
        i && (o.pathname = i)
    }
    return o
}
function Nv(i, o, s, f={}) {
    let {window: d=document.defaultView, v5Compat: v=!1} = f
      , x = d.history
      , D = "POP"
      , S = null
      , h = R();
    h == null && (h = 0,
    x.replaceState({
        ...x.state,
        idx: h
    }, ""));
    function R() {
        return (x.state || {
            idx: null
        }).idx
    }
    function Y() {
        D = "POP";
        let Z = R()
          , q = Z == null ? null : Z - h;
        h = Z,
        S && S({
            action: D,
            location: k.location,
            delta: q
        })
    }
    function C(Z, q) {
        D = "PUSH";
        let et = _r(k.location, Z, q);
        h = R() + 1;
        let Q = Rm(et, h)
          , ut = k.createHref(et);
        try {
            x.pushState(Q, "", ut)
        } catch (P) {
            if (P instanceof DOMException && P.name === "DataCloneError")
                throw P;
            d.location.assign(ut)
        }
        v && S && S({
            action: D,
            location: k.location,
            delta: 1
        })
    }
    function G(Z, q) {
        D = "REPLACE";
        let et = _r(k.location, Z, q);
        h = R();
        let Q = Rm(et, h)
          , ut = k.createHref(et);
        x.replaceState(Q, "", ut),
        v && S && S({
            action: D,
            location: k.location,
            delta: 0
        })
    }
    function H(Z) {
        let q = d.location.origin !== "null" ? d.location.origin : d.location.href
          , et = typeof Z == "string" ? Z : zu(Z);
        return et = et.replace(/ $/, "%20"),
        Lt(q, `No window.location.(origin|href) available to create URL for href: ${et}`),
        new URL(et,q)
    }
    let k = {
        get action() {
            return D
        },
        get location() {
            return i(d, x)
        },
        listen(Z) {
            if (S)
                throw new Error("A history only accepts one active listener");
            return d.addEventListener(xm, Y),
            S = Z,
            () => {
                d.removeEventListener(xm, Y),
                S = null
            }
        },
        createHref(Z) {
            return o(d, Z)
        },
        createURL: H,
        encodeLocation(Z) {
            let q = H(Z);
            return {
                pathname: q.pathname,
                search: q.search,
                hash: q.hash
            }
        },
        push: C,
        replace: G,
        go(Z) {
            return x.go(Z)
        }
    };
    return k
}
function qm(i, o, s="/") {
    return zv(i, o, s, !1)
}
function zv(i, o, s, f) {
    let d = typeof o == "string" ? Sn(o) : o
      , v = Ml(d.pathname || "/", s);
    if (v == null)
        return null;
    let x = Gm(i);
    Uv(x);
    let D = null;
    for (let S = 0; D == null && S < x.length; ++S) {
        let h = Qv(v);
        D = Gv(x[S], h, f)
    }
    return D
}
function Gm(i, o=[], s=[], f="") {
    let d = (v, x, D) => {
        let S = {
            relativePath: D === void 0 ? v.path || "" : D,
            caseSensitive: v.caseSensitive === !0,
            childrenIndex: x,
            route: v
        };
        S.relativePath.startsWith("/") && (Lt(S.relativePath.startsWith(f), `Absolute route path "${S.relativePath}" nested under path "${f}" is not valid. An absolute child route path must start with the combined path of all its parent routes.`),
        S.relativePath = S.relativePath.slice(f.length));
        let h = Dl([f, S.relativePath])
          , R = s.concat(S);
        v.children && v.children.length > 0 && (Lt(v.index !== !0, `Index routes must not have child routes. Please remove all child routes from route path "${h}".`),
        Gm(v.children, o, R, h)),
        !(v.path == null && !v.index) && o.push({
            path: h,
            score: Yv(h, v.index),
            routesMeta: R
        })
    }
    ;
    return i.forEach( (v, x) => {
        var D;
        if (v.path === "" || !((D = v.path) != null && D.includes("?")))
            d(v, x);
        else
            for (let S of Xm(v.path))
                d(v, x, S)
    }
    ),
    o
}
function Xm(i) {
    let o = i.split("/");
    if (o.length === 0)
        return [];
    let[s,...f] = o
      , d = s.endsWith("?")
      , v = s.replace(/\?$/, "");
    if (f.length === 0)
        return d ? [v, ""] : [v];
    let x = Xm(f.join("/"))
      , D = [];
    return D.push(...x.map(S => S === "" ? v : [v, S].join("/"))),
    d && D.push(...x),
    D.map(S => i.startsWith("/") && S === "" ? "/" : S)
}
function Uv(i) {
    i.sort( (o, s) => o.score !== s.score ? s.score - o.score : qv(o.routesMeta.map(f => f.childrenIndex), s.routesMeta.map(f => f.childrenIndex)))
}
var Cv = /^:[\w-]+$/
  , Lv = 3
  , Hv = 2
  , wv = 1
  , Bv = 10
  , jv = -2
  , Om = i => i === "*";
function Yv(i, o) {
    let s = i.split("/")
      , f = s.length;
    return s.some(Om) && (f += jv),
    o && (f += Hv),
    s.filter(d => !Om(d)).reduce( (d, v) => d + (Cv.test(v) ? Lv : v === "" ? wv : Bv), f)
}
function qv(i, o) {
    return i.length === o.length && i.slice(0, -1).every( (f, d) => f === o[d]) ? i[i.length - 1] - o[o.length - 1] : 0
}
function Gv(i, o, s=!1) {
    let {routesMeta: f} = i
      , d = {}
      , v = "/"
      , x = [];
    for (let D = 0; D < f.length; ++D) {
        let S = f[D]
          , h = D === f.length - 1
          , R = v === "/" ? o : o.slice(v.length) || "/"
          , Y = Pi({
            path: S.relativePath,
            caseSensitive: S.caseSensitive,
            end: h
        }, R)
          , C = S.route;
        if (!Y && h && s && !f[f.length - 1].route.index && (Y = Pi({
            path: S.relativePath,
            caseSensitive: S.caseSensitive,
            end: !1
        }, R)),
        !Y)
            return null;
        Object.assign(d, Y.params),
        x.push({
            params: d,
            pathname: Dl([v, Y.pathname]),
            pathnameBase: Jv(Dl([v, Y.pathnameBase])),
            route: C
        }),
        Y.pathnameBase !== "/" && (v = Dl([v, Y.pathnameBase]))
    }
    return x
}
function Pi(i, o) {
    typeof i == "string" && (i = {
        path: i,
        caseSensitive: !1,
        end: !0
    });
    let[s,f] = Xv(i.path, i.caseSensitive, i.end)
      , d = o.match(s);
    if (!d)
        return null;
    let v = d[0]
      , x = v.replace(/(.)\/+$/, "$1")
      , D = d.slice(1);
    return {
        params: f.reduce( (h, {paramName: R, isOptional: Y}, C) => {
            if (R === "*") {
                let H = D[C] || "";
                x = v.slice(0, v.length - H.length).replace(/(.)\/+$/, "$1")
            }
            const G = D[C];
            return Y && !G ? h[R] = void 0 : h[R] = (G || "").replace(/%2F/g, "/"),
            h
        }
        , {}),
        pathname: v,
        pathnameBase: x,
        pattern: i
    }
}
function Xv(i, o=!1, s=!0) {
    cl(i === "*" || !i.endsWith("*") || i.endsWith("/*"), `Route path "${i}" will be treated as if it were "${i.replace(/\*$/, "/*")}" because the \`*\` character must always follow a \`/\` in the pattern. To get rid of this warning, please change the route path to "${i.replace(/\*$/, "/*")}".`);
    let f = []
      , d = "^" + i.replace(/\/*\*?$/, "").replace(/^\/*/, "/").replace(/[\\.*+^${}|()[\]]/g, "\\$&").replace(/\/:([\w-]+)(\?)?/g, (x, D, S) => (f.push({
        paramName: D,
        isOptional: S != null
    }),
    S ? "/?([^\\/]+)?" : "/([^\\/]+)"));
    return i.endsWith("*") ? (f.push({
        paramName: "*"
    }),
    d += i === "*" || i === "/*" ? "(.*)$" : "(?:\\/(.+)|\\/*)$") : s ? d += "\\/*$" : i !== "" && i !== "/" && (d += "(?:(?=\\/|$))"),
    [new RegExp(d,o ? void 0 : "i"), f]
}
function Qv(i) {
    try {
        return i.split("/").map(o => decodeURIComponent(o).replace(/\//g, "%2F")).join("/")
    } catch (o) {
        return cl(!1, `The URL path "${i}" could not be decoded because it is a malformed URL segment. This is probably due to a bad percent encoding (${o}).`),
        i
    }
}
function Ml(i, o) {
    if (o === "/")
        return i;
    if (!i.toLowerCase().startsWith(o.toLowerCase()))
        return null;
    let s = o.endsWith("/") ? o.length - 1 : o.length
      , f = i.charAt(s);
    return f && f !== "/" ? null : i.slice(s) || "/"
}
function Zv(i, o="/") {
    let {pathname: s, search: f="", hash: d=""} = typeof i == "string" ? Sn(i) : i;
    return {
        pathname: s ? s.startsWith("/") ? s : Vv(s, o) : o,
        search: kv(f),
        hash: $v(d)
    }
}
function Vv(i, o) {
    let s = o.replace(/\/+$/, "").split("/");
    return i.split("/").forEach(d => {
        d === ".." ? s.length > 1 && s.pop() : d !== "." && s.push(d)
    }
    ),
    s.length > 1 ? s.join("/") : "/"
}
function gr(i, o, s, f) {
    return `Cannot include a '${i}' character in a manually specified \`to.${o}\` field [${JSON.stringify(f)}].  Please separate it out to the \`to.${s}\` field. Alternatively you may provide the full path as a string in <Link to="..."> and the router will parse it for you.`
}
function Kv(i) {
    return i.filter( (o, s) => s === 0 || o.route.path && o.route.path.length > 0)
}
function Qm(i) {
    let o = Kv(i);
    return o.map( (s, f) => f === o.length - 1 ? s.pathname : s.pathnameBase)
}
function Zm(i, o, s, f=!1) {
    let d;
    typeof i == "string" ? d = Sn(i) : (d = {
        ...i
    },
    Lt(!d.pathname || !d.pathname.includes("?"), gr("?", "pathname", "search", d)),
    Lt(!d.pathname || !d.pathname.includes("#"), gr("#", "pathname", "hash", d)),
    Lt(!d.search || !d.search.includes("#"), gr("#", "search", "hash", d)));
    let v = i === "" || d.pathname === "", x = v ? "/" : d.pathname, D;
    if (x == null)
        D = s;
    else {
        let Y = o.length - 1;
        if (!f && x.startsWith("..")) {
            let C = x.split("/");
            for (; C[0] === ".."; )
                C.shift(),
                Y -= 1;
            d.pathname = C.join("/")
        }
        D = Y >= 0 ? o[Y] : "/"
    }
    let S = Zv(d, D)
      , h = x && x !== "/" && x.endsWith("/")
      , R = (v || x === ".") && s.endsWith("/");
    return !S.pathname.endsWith("/") && (h || R) && (S.pathname += "/"),
    S
}
var Dl = i => i.join("/").replace(/\/\/+/g, "/")
  , Jv = i => i.replace(/\/+$/, "").replace(/^\/*/, "/")
  , kv = i => !i || i === "?" ? "" : i.startsWith("?") ? i : "?" + i
  , $v = i => !i || i === "#" ? "" : i.startsWith("#") ? i : "#" + i;
function Wv(i) {
    return i != null && typeof i.status == "number" && typeof i.statusText == "string" && typeof i.internal == "boolean" && "data"in i
}
var Vm = ["POST", "PUT", "PATCH", "DELETE"];
new Set(Vm);
var Fv = ["GET", ...Vm];
new Set(Fv);
var bn = O.createContext(null);
bn.displayName = "DataRouter";
var tc = O.createContext(null);
tc.displayName = "DataRouterState";
var Km = O.createContext({
    isTransitioning: !1
});
Km.displayName = "ViewTransition";
var Iv = O.createContext(new Map);
Iv.displayName = "Fetchers";
var Pv = O.createContext(null);
Pv.displayName = "Await";
var fl = O.createContext(null);
fl.displayName = "Navigation";
var Uu = O.createContext(null);
Uu.displayName = "Location";
var Nl = O.createContext({
    outlet: null,
    matches: [],
    isDataRoute: !1
});
Nl.displayName = "Route";
var Mr = O.createContext(null);
Mr.displayName = "RouteError";
function t0(i, {relative: o}={}) {
    Lt(Cu(), "useHref() may be used only in the context of a <Router> component.");
    let {basename: s, navigator: f} = O.useContext(fl)
      , {hash: d, pathname: v, search: x} = Lu(i, {
        relative: o
    })
      , D = v;
    return s !== "/" && (D = v === "/" ? s : Dl([s, v])),
    f.createHref({
        pathname: D,
        search: x,
        hash: d
    })
}
function Cu() {
    return O.useContext(Uu) != null
}
function ua() {
    return Lt(Cu(), "useLocation() may be used only in the context of a <Router> component."),
    O.useContext(Uu).location
}
var Jm = "You should call navigate() in a React.useEffect(), not when your component is first rendered.";
function km(i) {
    O.useContext(fl).static || O.useLayoutEffect(i)
}
function Nr() {
    let {isDataRoute: i} = O.useContext(Nl);
    return i ? m0() : e0()
}
function e0() {
    Lt(Cu(), "useNavigate() may be used only in the context of a <Router> component.");
    let i = O.useContext(bn)
      , {basename: o, navigator: s} = O.useContext(fl)
      , {matches: f} = O.useContext(Nl)
      , {pathname: d} = ua()
      , v = JSON.stringify(Qm(f))
      , x = O.useRef(!1);
    return km( () => {
        x.current = !0
    }
    ),
    O.useCallback( (S, h={}) => {
        if (cl(x.current, Jm),
        !x.current)
            return;
        if (typeof S == "number") {
            s.go(S);
            return
        }
        let R = Zm(S, JSON.parse(v), d, h.relative === "path");
        i == null && o !== "/" && (R.pathname = R.pathname === "/" ? o : Dl([o, R.pathname])),
        (h.replace ? s.replace : s.push)(R, h.state, h)
    }
    , [o, s, v, d, i])
}
O.createContext(null);
function Lu(i, {relative: o}={}) {
    let {matches: s} = O.useContext(Nl)
      , {pathname: f} = ua()
      , d = JSON.stringify(Qm(s));
    return O.useMemo( () => Zm(i, JSON.parse(d), f, o === "path"), [i, d, f, o])
}
function l0(i, o) {
    return $m(i, o)
}
function $m(i, o, s, f) {
    var et;
    Lt(Cu(), "useRoutes() may be used only in the context of a <Router> component.");
    let {navigator: d, static: v} = O.useContext(fl)
      , {matches: x} = O.useContext(Nl)
      , D = x[x.length - 1]
      , S = D ? D.params : {}
      , h = D ? D.pathname : "/"
      , R = D ? D.pathnameBase : "/"
      , Y = D && D.route;
    {
        let Q = Y && Y.path || "";
        Wm(h, !Y || Q.endsWith("*") || Q.endsWith("*?"), `You rendered descendant <Routes> (or called \`useRoutes()\`) at "${h}" (under <Route path="${Q}">) but the parent route path has no trailing "*". This means if you navigate deeper, the parent won't match anymore and therefore the child routes will never render.

Please change the parent <Route path="${Q}"> to <Route path="${Q === "/" ? "*" : `${Q}/*`}">.`)
    }
    let C = ua(), G;
    if (o) {
        let Q = typeof o == "string" ? Sn(o) : o;
        Lt(R === "/" || ((et = Q.pathname) == null ? void 0 : et.startsWith(R)), `When overriding the location using \`<Routes location>\` or \`useRoutes(routes, location)\`, the location pathname must begin with the portion of the URL pathname that was matched by all parent routes. The current pathname base is "${R}" but pathname "${Q.pathname}" was given in the \`location\` prop.`),
        G = Q
    } else
        G = C;
    let H = G.pathname || "/"
      , k = H;
    if (R !== "/") {
        let Q = R.replace(/^\//, "").split("/");
        k = "/" + H.replace(/^\//, "").split("/").slice(Q.length).join("/")
    }
    let Z = !v && s && s.matches && s.matches.length > 0 ? s.matches : qm(i, {
        pathname: k
    });
    cl(Y || Z != null, `No routes matched location "${G.pathname}${G.search}${G.hash}" `),
    cl(Z == null || Z[Z.length - 1].route.element !== void 0 || Z[Z.length - 1].route.Component !== void 0 || Z[Z.length - 1].route.lazy !== void 0, `Matched leaf route at location "${G.pathname}${G.search}${G.hash}" does not have an element or Component. This means it will render an <Outlet /> with a null value by default resulting in an "empty" page.`);
    let q = c0(Z && Z.map(Q => Object.assign({}, Q, {
        params: Object.assign({}, S, Q.params),
        pathname: Dl([R, d.encodeLocation ? d.encodeLocation(Q.pathname).pathname : Q.pathname]),
        pathnameBase: Q.pathnameBase === "/" ? R : Dl([R, d.encodeLocation ? d.encodeLocation(Q.pathnameBase).pathname : Q.pathnameBase])
    })), x, s, f);
    return o && q ? O.createElement(Uu.Provider, {
        value: {
            location: {
                pathname: "/",
                search: "",
                hash: "",
                state: null,
                key: "default",
                ...G
            },
            navigationType: "POP"
        }
    }, q) : q
}
function a0() {
    let i = d0()
      , o = Wv(i) ? `${i.status} ${i.statusText}` : i instanceof Error ? i.message : JSON.stringify(i)
      , s = i instanceof Error ? i.stack : null
      , f = "rgba(200,200,200, 0.5)"
      , d = {
        padding: "0.5rem",
        backgroundColor: f
    }
      , v = {
        padding: "2px 4px",
        backgroundColor: f
    }
      , x = null;
    return console.error("Error handled by React Router default ErrorBoundary:", i),
    x = O.createElement(O.Fragment, null, O.createElement("p", null, "💿 Hey developer 👋"), O.createElement("p", null, "You can provide a way better UX than this when your app throws errors by providing your own ", O.createElement("code", {
        style: v
    }, "ErrorBoundary"), " or", " ", O.createElement("code", {
        style: v
    }, "errorElement"), " prop on your route.")),
    O.createElement(O.Fragment, null, O.createElement("h2", null, "Unexpected Application Error!"), O.createElement("h3", {
        style: {
            fontStyle: "italic"
        }
    }, o), s ? O.createElement("pre", {
        style: d
    }, s) : null, x)
}
var n0 = O.createElement(a0, null)
  , u0 = class extends O.Component {
    constructor(i) {
        super(i),
        this.state = {
            location: i.location,
            revalidation: i.revalidation,
            error: i.error
        }
    }
    static getDerivedStateFromError(i) {
        return {
            error: i
        }
    }
    static getDerivedStateFromProps(i, o) {
        return o.location !== i.location || o.revalidation !== "idle" && i.revalidation === "idle" ? {
            error: i.error,
            location: i.location,
            revalidation: i.revalidation
        } : {
            error: i.error !== void 0 ? i.error : o.error,
            location: o.location,
            revalidation: i.revalidation || o.revalidation
        }
    }
    componentDidCatch(i, o) {
        console.error("React Router caught the following error during render", i, o)
    }
    render() {
        return this.state.error !== void 0 ? O.createElement(Nl.Provider, {
            value: this.props.routeContext
        }, O.createElement(Mr.Provider, {
            value: this.state.error,
            children: this.props.component
        })) : this.props.children
    }
}
;
function i0({routeContext: i, match: o, children: s}) {
    let f = O.useContext(bn);
    return f && f.static && f.staticContext && (o.route.errorElement || o.route.ErrorBoundary) && (f.staticContext._deepestRenderedBoundaryId = o.route.id),
    O.createElement(Nl.Provider, {
        value: i
    }, s)
}
function c0(i, o=[], s=null, f=null) {
    if (i == null) {
        if (!s)
            return null;
        if (s.errors)
            i = s.matches;
        else if (o.length === 0 && !s.initialized && s.matches.length > 0)
            i = s.matches;
        else
            return null
    }
    let d = i
      , v = s == null ? void 0 : s.errors;
    if (v != null) {
        let S = d.findIndex(h => h.route.id && (v == null ? void 0 : v[h.route.id]) !== void 0);
        Lt(S >= 0, `Could not find a matching route for errors on route IDs: ${Object.keys(v).join(",")}`),
        d = d.slice(0, Math.min(d.length, S + 1))
    }
    let x = !1
      , D = -1;
    if (s)
        for (let S = 0; S < d.length; S++) {
            let h = d[S];
            if ((h.route.HydrateFallback || h.route.hydrateFallbackElement) && (D = S),
            h.route.id) {
                let {loaderData: R, errors: Y} = s
                  , C = h.route.loader && !R.hasOwnProperty(h.route.id) && (!Y || Y[h.route.id] === void 0);
                if (h.route.lazy || C) {
                    x = !0,
                    D >= 0 ? d = d.slice(0, D + 1) : d = [d[0]];
                    break
                }
            }
        }
    return d.reduceRight( (S, h, R) => {
        let Y, C = !1, G = null, H = null;
        s && (Y = v && h.route.id ? v[h.route.id] : void 0,
        G = h.route.errorElement || n0,
        x && (D < 0 && R === 0 ? (Wm("route-fallback", !1, "No `HydrateFallback` element provided to render during initial hydration"),
        C = !0,
        H = null) : D === R && (C = !0,
        H = h.route.hydrateFallbackElement || null)));
        let k = o.concat(d.slice(0, R + 1))
          , Z = () => {
            let q;
            return Y ? q = G : C ? q = H : h.route.Component ? q = O.createElement(h.route.Component, null) : h.route.element ? q = h.route.element : q = S,
            O.createElement(i0, {
                match: h,
                routeContext: {
                    outlet: S,
                    matches: k,
                    isDataRoute: s != null
                },
                children: q
            })
        }
        ;
        return s && (h.route.ErrorBoundary || h.route.errorElement || R === 0) ? O.createElement(u0, {
            location: s.location,
            revalidation: s.revalidation,
            component: G,
            error: Y,
            children: Z(),
            routeContext: {
                outlet: null,
                matches: k,
                isDataRoute: !0
            }
        }) : Z()
    }
    , null)
}
function zr(i) {
    return `${i} must be used within a data router.  See https://reactrouter.com/en/main/routers/picking-a-router.`
}
function f0(i) {
    let o = O.useContext(bn);
    return Lt(o, zr(i)),
    o
}
function r0(i) {
    let o = O.useContext(tc);
    return Lt(o, zr(i)),
    o
}
function o0(i) {
    let o = O.useContext(Nl);
    return Lt(o, zr(i)),
    o
}
function Ur(i) {
    let o = o0(i)
      , s = o.matches[o.matches.length - 1];
    return Lt(s.route.id, `${i} can only be used on routes that contain a unique "id"`),
    s.route.id
}
function s0() {
    return Ur("useRouteId")
}
function d0() {
    var f;
    let i = O.useContext(Mr)
      , o = r0("useRouteError")
      , s = Ur("useRouteError");
    return i !== void 0 ? i : (f = o.errors) == null ? void 0 : f[s]
}
function m0() {
    let {router: i} = f0("useNavigate")
      , o = Ur("useNavigate")
      , s = O.useRef(!1);
    return km( () => {
        s.current = !0
    }
    ),
    O.useCallback(async (d, v={}) => {
        cl(s.current, Jm),
        s.current && (typeof d == "number" ? i.navigate(d) : await i.navigate(d, {
            fromRouteId: o,
            ...v
        }))
    }
    , [i, o])
}
var Dm = {};
function Wm(i, o, s) {
    !o && !Dm[i] && (Dm[i] = !0,
    cl(!1, s))
}
O.memo(h0);
function h0({routes: i, future: o, state: s}) {
    return $m(i, void 0, s, o)
}
function pn(i) {
    Lt(!1, "A <Route> is only ever to be used as the child of <Routes> element, never rendered directly. Please wrap your <Route> in a <Routes>.")
}
function y0({basename: i="/", children: o=null, location: s, navigationType: f="POP", navigator: d, static: v=!1}) {
    Lt(!Cu(), "You cannot render a <Router> inside another <Router>. You should never have more than one in your app.");
    let x = i.replace(/^\/*/, "/")
      , D = O.useMemo( () => ({
        basename: x,
        navigator: d,
        static: v,
        future: {}
    }), [x, d, v]);
    typeof s == "string" && (s = Sn(s));
    let {pathname: S="/", search: h="", hash: R="", state: Y=null, key: C="default"} = s
      , G = O.useMemo( () => {
        let H = Ml(S, x);
        return H == null ? null : {
            location: {
                pathname: H,
                search: h,
                hash: R,
                state: Y,
                key: C
            },
            navigationType: f
        }
    }
    , [x, S, h, R, Y, C, f]);
    return cl(G != null, `<Router basename="${x}"> is not able to match the URL "${S}${h}${R}" because it does not start with the basename, so the <Router> won't render anything.`),
    G == null ? null : O.createElement(fl.Provider, {
        value: D
    }, O.createElement(Uu.Provider, {
        children: o,
        value: G
    }))
}
function v0({children: i, location: o}) {
    return l0(xr(i), o)
}
function xr(i, o=[]) {
    let s = [];
    return O.Children.forEach(i, (f, d) => {
        if (!O.isValidElement(f))
            return;
        let v = [...o, d];
        if (f.type === O.Fragment) {
            s.push.apply(s, xr(f.props.children, v));
            return
        }
        Lt(f.type === pn, `[${typeof f.type == "string" ? f.type : f.type.name}] is not a <Route> component. All component children of <Routes> must be a <Route> or <React.Fragment>`),
        Lt(!f.props.index || !f.props.children, "An index route cannot have child routes.");
        let x = {
            id: f.props.id || v.join("-"),
            caseSensitive: f.props.caseSensitive,
            element: f.props.element,
            Component: f.props.Component,
            index: f.props.index,
            path: f.props.path,
            loader: f.props.loader,
            action: f.props.action,
            hydrateFallbackElement: f.props.hydrateFallbackElement,
            HydrateFallback: f.props.HydrateFallback,
            errorElement: f.props.errorElement,
            ErrorBoundary: f.props.ErrorBoundary,
            hasErrorBoundary: f.props.hasErrorBoundary === !0 || f.props.ErrorBoundary != null || f.props.errorElement != null,
            shouldRevalidate: f.props.shouldRevalidate,
            handle: f.props.handle,
            lazy: f.props.lazy
        };
        f.props.children && (x.children = xr(f.props.children, v)),
        s.push(x)
    }
    ),
    s
}
var Wi = "get"
  , Fi = "application/x-www-form-urlencoded";
function ec(i) {
    return i != null && typeof i.tagName == "string"
}
function g0(i) {
    return ec(i) && i.tagName.toLowerCase() === "button"
}
function p0(i) {
    return ec(i) && i.tagName.toLowerCase() === "form"
}
function S0(i) {
    return ec(i) && i.tagName.toLowerCase() === "input"
}
function b0(i) {
    return !!(i.metaKey || i.altKey || i.ctrlKey || i.shiftKey)
}
function E0(i, o) {
    return i.button === 0 && (!o || o === "_self") && !b0(i)
}
var Ji = null;
function T0() {
    if (Ji === null)
        try {
            new FormData(document.createElement("form"),0),
            Ji = !1
        } catch {
            Ji = !0
        }
    return Ji
}
var A0 = new Set(["application/x-www-form-urlencoded", "multipart/form-data", "text/plain"]);
function pr(i) {
    return i != null && !A0.has(i) ? (cl(!1, `"${i}" is not a valid \`encType\` for \`<Form>\`/\`<fetcher.Form>\` and will default to "${Fi}"`),
    null) : i
}
function _0(i, o) {
    let s, f, d, v, x;
    if (p0(i)) {
        let D = i.getAttribute("action");
        f = D ? Ml(D, o) : null,
        s = i.getAttribute("method") || Wi,
        d = pr(i.getAttribute("enctype")) || Fi,
        v = new FormData(i)
    } else if (g0(i) || S0(i) && (i.type === "submit" || i.type === "image")) {
        let D = i.form;
        if (D == null)
            throw new Error('Cannot submit a <button> or <input type="submit"> without a <form>');
        let S = i.getAttribute("formaction") || D.getAttribute("action");
        if (f = S ? Ml(S, o) : null,
        s = i.getAttribute("formmethod") || D.getAttribute("method") || Wi,
        d = pr(i.getAttribute("formenctype")) || pr(D.getAttribute("enctype")) || Fi,
        v = new FormData(D,i),
        !T0()) {
            let {name: h, type: R, value: Y} = i;
            if (R === "image") {
                let C = h ? `${h}.` : "";
                v.append(`${C}x`, "0"),
                v.append(`${C}y`, "0")
            } else
                h && v.append(h, Y)
        }
    } else {
        if (ec(i))
            throw new Error('Cannot submit element that is not <form>, <button>, or <input type="submit|image">');
        s = Wi,
        f = null,
        d = Fi,
        x = i
    }
    return v && d === "text/plain" && (x = v,
    v = void 0),
    {
        action: f,
        method: s.toLowerCase(),
        encType: d,
        formData: v,
        body: x
    }
}
function Cr(i, o) {
    if (i === !1 || i === null || typeof i > "u")
        throw new Error(o)
}
async function x0(i, o) {
    if (i.id in o)
        return o[i.id];
    try {
        let s = await import(i.module);
        return o[i.id] = s,
        s
    } catch (s) {
        return console.error(`Error loading route module \`${i.module}\`, reloading page...`),
        console.error(s),
        window.__reactRouterContext && window.__reactRouterContext.isSpaMode,
        window.location.reload(),
        new Promise( () => {}
        )
    }
}
function R0(i) {
    return i == null ? !1 : i.href == null ? i.rel === "preload" && typeof i.imageSrcSet == "string" && typeof i.imageSizes == "string" : typeof i.rel == "string" && typeof i.href == "string"
}
async function O0(i, o, s) {
    let f = await Promise.all(i.map(async d => {
        let v = o.routes[d.route.id];
        if (v) {
            let x = await x0(v, s);
            return x.links ? x.links() : []
        }
        return []
    }
    ));
    return z0(f.flat(1).filter(R0).filter(d => d.rel === "stylesheet" || d.rel === "preload").map(d => d.rel === "stylesheet" ? {
        ...d,
        rel: "prefetch",
        as: "style"
    } : {
        ...d,
        rel: "prefetch"
    }))
}
function Mm(i, o, s, f, d, v) {
    let x = (S, h) => s[h] ? S.route.id !== s[h].route.id : !0
      , D = (S, h) => {
        var R;
        return s[h].pathname !== S.pathname || ((R = s[h].route.path) == null ? void 0 : R.endsWith("*")) && s[h].params["*"] !== S.params["*"]
    }
    ;
    return v === "assets" ? o.filter( (S, h) => x(S, h) || D(S, h)) : v === "data" ? o.filter( (S, h) => {
        var Y;
        let R = f.routes[S.route.id];
        if (!R || !R.hasLoader)
            return !1;
        if (x(S, h) || D(S, h))
            return !0;
        if (S.route.shouldRevalidate) {
            let C = S.route.shouldRevalidate({
                currentUrl: new URL(d.pathname + d.search + d.hash,window.origin),
                currentParams: ((Y = s[0]) == null ? void 0 : Y.params) || {},
                nextUrl: new URL(i,window.origin),
                nextParams: S.params,
                defaultShouldRevalidate: !0
            });
            if (typeof C == "boolean")
                return C
        }
        return !0
    }
    ) : []
}
function D0(i, o, {includeHydrateFallback: s}={}) {
    return M0(i.map(f => {
        let d = o.routes[f.route.id];
        if (!d)
            return [];
        let v = [d.module];
        return d.clientActionModule && (v = v.concat(d.clientActionModule)),
        d.clientLoaderModule && (v = v.concat(d.clientLoaderModule)),
        s && d.hydrateFallbackModule && (v = v.concat(d.hydrateFallbackModule)),
        d.imports && (v = v.concat(d.imports)),
        v
    }
    ).flat(1))
}
function M0(i) {
    return [...new Set(i)]
}
function N0(i) {
    let o = {}
      , s = Object.keys(i).sort();
    for (let f of s)
        o[f] = i[f];
    return o
}
function z0(i, o) {
    let s = new Set;
    return new Set(o),
    i.reduce( (f, d) => {
        let v = JSON.stringify(N0(d));
        return s.has(v) || (s.add(v),
        f.push({
            key: v,
            link: d
        })),
        f
    }
    , [])
}
var U0 = new Set([100, 101, 204, 205]);
function C0(i, o) {
    let s = typeof i == "string" ? new URL(i,typeof window > "u" ? "server://singlefetch/" : window.location.origin) : i;
    return s.pathname === "/" ? s.pathname = "_root.data" : o && Ml(s.pathname, o) === "/" ? s.pathname = `${o.replace(/\/$/, "")}/_root.data` : s.pathname = `${s.pathname.replace(/\/$/, "")}.data`,
    s
}
function Fm() {
    let i = O.useContext(bn);
    return Cr(i, "You must render this element inside a <DataRouterContext.Provider> element"),
    i
}
function L0() {
    let i = O.useContext(tc);
    return Cr(i, "You must render this element inside a <DataRouterStateContext.Provider> element"),
    i
}
var Lr = O.createContext(void 0);
Lr.displayName = "FrameworkContext";
function Im() {
    let i = O.useContext(Lr);
    return Cr(i, "You must render this element inside a <HydratedRouter> element"),
    i
}
function H0(i, o) {
    let s = O.useContext(Lr)
      , [f,d] = O.useState(!1)
      , [v,x] = O.useState(!1)
      , {onFocus: D, onBlur: S, onMouseEnter: h, onMouseLeave: R, onTouchStart: Y} = o
      , C = O.useRef(null);
    O.useEffect( () => {
        if (i === "render" && x(!0),
        i === "viewport") {
            let k = q => {
                q.forEach(et => {
                    x(et.isIntersecting)
                }
                )
            }
              , Z = new IntersectionObserver(k,{
                threshold: .5
            });
            return C.current && Z.observe(C.current),
            () => {
                Z.disconnect()
            }
        }
    }
    , [i]),
    O.useEffect( () => {
        if (f) {
            let k = setTimeout( () => {
                x(!0)
            }
            , 100);
            return () => {
                clearTimeout(k)
            }
        }
    }
    , [f]);
    let G = () => {
        d(!0)
    }
      , H = () => {
        d(!1),
        x(!1)
    }
    ;
    return s ? i !== "intent" ? [v, C, {}] : [v, C, {
        onFocus: xu(D, G),
        onBlur: xu(S, H),
        onMouseEnter: xu(h, G),
        onMouseLeave: xu(R, H),
        onTouchStart: xu(Y, G)
    }] : [!1, C, {}]
}
function xu(i, o) {
    return s => {
        i && i(s),
        s.defaultPrevented || o(s)
    }
}
function w0({page: i, ...o}) {
    let {router: s} = Fm()
      , f = O.useMemo( () => qm(s.routes, i, s.basename), [s.routes, i, s.basename]);
    return f ? O.createElement(j0, {
        page: i,
        matches: f,
        ...o
    }) : null
}
function B0(i) {
    let {manifest: o, routeModules: s} = Im()
      , [f,d] = O.useState([]);
    return O.useEffect( () => {
        let v = !1;
        return O0(i, o, s).then(x => {
            v || d(x)
        }
        ),
        () => {
            v = !0
        }
    }
    , [i, o, s]),
    f
}
function j0({page: i, matches: o, ...s}) {
    let f = ua()
      , {manifest: d, routeModules: v} = Im()
      , {basename: x} = Fm()
      , {loaderData: D, matches: S} = L0()
      , h = O.useMemo( () => Mm(i, o, S, d, f, "data"), [i, o, S, d, f])
      , R = O.useMemo( () => Mm(i, o, S, d, f, "assets"), [i, o, S, d, f])
      , Y = O.useMemo( () => {
        if (i === f.pathname + f.search + f.hash)
            return [];
        let H = new Set
          , k = !1;
        if (o.forEach(q => {
            var Q;
            let et = d.routes[q.route.id];
            !et || !et.hasLoader || (!h.some(ut => ut.route.id === q.route.id) && q.route.id in D && ((Q = v[q.route.id]) != null && Q.shouldRevalidate) || et.hasClientLoader ? k = !0 : H.add(q.route.id))
        }
        ),
        H.size === 0)
            return [];
        let Z = C0(i, x);
        return k && H.size > 0 && Z.searchParams.set("_routes", o.filter(q => H.has(q.route.id)).map(q => q.route.id).join(",")),
        [Z.pathname + Z.search]
    }
    , [x, D, f, d, h, o, i, v])
      , C = O.useMemo( () => D0(R, d), [R, d])
      , G = B0(R);
    return O.createElement(O.Fragment, null, Y.map(H => O.createElement("link", {
        key: H,
        rel: "prefetch",
        as: "fetch",
        href: H,
        ...s
    })), C.map(H => O.createElement("link", {
        key: H,
        rel: "modulepreload",
        href: H,
        ...s
    })), G.map( ({key: H, link: k}) => O.createElement("link", {
        key: H,
        ...k
    })))
}
function Y0(...i) {
    return o => {
        i.forEach(s => {
            typeof s == "function" ? s(o) : s != null && (s.current = o)
        }
        )
    }
}
var Pm = typeof window < "u" && typeof window.document < "u" && typeof window.document.createElement < "u";
try {
    Pm && (window.__reactRouterVersion = "7.5.1")
} catch {}
function q0({basename: i, children: o, window: s}) {
    let f = O.useRef();
    f.current == null && (f.current = Dv({
        window: s,
        v5Compat: !0
    }));
    let d = f.current
      , [v,x] = O.useState({
        action: d.action,
        location: d.location
    })
      , D = O.useCallback(S => {
        O.startTransition( () => x(S))
    }
    , [x]);
    return O.useLayoutEffect( () => d.listen(D), [d, D]),
    O.createElement(y0, {
        basename: i,
        children: o,
        location: v.location,
        navigationType: v.action,
        navigator: d
    })
}
var th = /^(?:[a-z][a-z0-9+.-]*:|\/\/)/i
  , Na = O.forwardRef(function({onClick: o, discover: s="render", prefetch: f="none", relative: d, reloadDocument: v, replace: x, state: D, target: S, to: h, preventScrollReset: R, viewTransition: Y, ...C}, G) {
    let {basename: H} = O.useContext(fl), k = typeof h == "string" && th.test(h), Z, q = !1;
    if (typeof h == "string" && k && (Z = h,
    Pm))
        try {
            let xt = new URL(window.location.href)
              , Pt = h.startsWith("//") ? new URL(xt.protocol + h) : new URL(h)
              , St = Ml(Pt.pathname, H);
            Pt.origin === xt.origin && St != null ? h = St + Pt.search + Pt.hash : q = !0
        } catch {
            cl(!1, `<Link to="${h}"> contains an invalid URL which will probably break when clicked - please update to a valid URL path.`)
        }
    let et = t0(h, {
        relative: d
    })
      , [Q,ut,P] = H0(f, C)
      , Mt = Z0(h, {
        replace: x,
        state: D,
        target: S,
        preventScrollReset: R,
        relative: d,
        viewTransition: Y
    });
    function Nt(xt) {
        o && o(xt),
        xt.defaultPrevented || Mt(xt)
    }
    let Ut = O.createElement("a", {
        ...C,
        ...P,
        href: Z || et,
        onClick: q || v ? o : Nt,
        ref: Y0(G, ut),
        target: S,
        "data-discover": !k && s === "render" ? "true" : void 0
    });
    return Q && !k ? O.createElement(O.Fragment, null, Ut, O.createElement(w0, {
        page: et
    })) : Ut
});
Na.displayName = "Link";
var G0 = O.forwardRef(function({"aria-current": o="page", caseSensitive: s=!1, className: f="", end: d=!1, style: v, to: x, viewTransition: D, children: S, ...h}, R) {
    let Y = Lu(x, {
        relative: h.relative
    })
      , C = ua()
      , G = O.useContext(tc)
      , {navigator: H, basename: k} = O.useContext(fl)
      , Z = G != null && $0(Y) && D === !0
      , q = H.encodeLocation ? H.encodeLocation(Y).pathname : Y.pathname
      , et = C.pathname
      , Q = G && G.navigation && G.navigation.location ? G.navigation.location.pathname : null;
    s || (et = et.toLowerCase(),
    Q = Q ? Q.toLowerCase() : null,
    q = q.toLowerCase()),
    Q && k && (Q = Ml(Q, k) || Q);
    const ut = q !== "/" && q.endsWith("/") ? q.length - 1 : q.length;
    let P = et === q || !d && et.startsWith(q) && et.charAt(ut) === "/", Mt = Q != null && (Q === q || !d && Q.startsWith(q) && Q.charAt(q.length) === "/"), Nt = {
        isActive: P,
        isPending: Mt,
        isTransitioning: Z
    }, Ut = P ? o : void 0, xt;
    typeof f == "function" ? xt = f(Nt) : xt = [f, P ? "active" : null, Mt ? "pending" : null, Z ? "transitioning" : null].filter(Boolean).join(" ");
    let Pt = typeof v == "function" ? v(Nt) : v;
    return O.createElement(Na, {
        ...h,
        "aria-current": Ut,
        className: xt,
        ref: R,
        style: Pt,
        to: x,
        viewTransition: D
    }, typeof S == "function" ? S(Nt) : S)
});
G0.displayName = "NavLink";
var X0 = O.forwardRef( ({discover: i="render", fetcherKey: o, navigate: s, reloadDocument: f, replace: d, state: v, method: x=Wi, action: D, onSubmit: S, relative: h, preventScrollReset: R, viewTransition: Y, ...C}, G) => {
    let H = J0()
      , k = k0(D, {
        relative: h
    })
      , Z = x.toLowerCase() === "get" ? "get" : "post"
      , q = typeof D == "string" && th.test(D)
      , et = Q => {
        if (S && S(Q),
        Q.defaultPrevented)
            return;
        Q.preventDefault();
        let ut = Q.nativeEvent.submitter
          , P = (ut == null ? void 0 : ut.getAttribute("formmethod")) || x;
        H(ut || Q.currentTarget, {
            fetcherKey: o,
            method: P,
            navigate: s,
            replace: d,
            state: v,
            relative: h,
            preventScrollReset: R,
            viewTransition: Y
        })
    }
    ;
    return O.createElement("form", {
        ref: G,
        method: Z,
        action: k,
        onSubmit: f ? S : et,
        ...C,
        "data-discover": !q && i === "render" ? "true" : void 0
    })
}
);
X0.displayName = "Form";
function Q0(i) {
    return `${i} must be used within a data router.  See https://reactrouter.com/en/main/routers/picking-a-router.`
}
function eh(i) {
    let o = O.useContext(bn);
    return Lt(o, Q0(i)),
    o
}
function Z0(i, {target: o, replace: s, state: f, preventScrollReset: d, relative: v, viewTransition: x}={}) {
    let D = Nr()
      , S = ua()
      , h = Lu(i, {
        relative: v
    });
    return O.useCallback(R => {
        if (E0(R, o)) {
            R.preventDefault();
            let Y = s !== void 0 ? s : zu(S) === zu(h);
            D(i, {
                replace: Y,
                state: f,
                preventScrollReset: d,
                relative: v,
                viewTransition: x
            })
        }
    }
    , [S, D, h, s, f, o, i, d, v, x])
}
var V0 = 0
  , K0 = () => `__${String(++V0)}__`;
function J0() {
    let {router: i} = eh("useSubmit")
      , {basename: o} = O.useContext(fl)
      , s = s0();
    return O.useCallback(async (f, d={}) => {
        let {action: v, method: x, encType: D, formData: S, body: h} = _0(f, o);
        if (d.navigate === !1) {
            let R = d.fetcherKey || K0();
            await i.fetch(R, s, d.action || v, {
                preventScrollReset: d.preventScrollReset,
                formData: S,
                body: h,
                formMethod: d.method || x,
                formEncType: d.encType || D,
                flushSync: d.flushSync
            })
        } else
            await i.navigate(d.action || v, {
                preventScrollReset: d.preventScrollReset,
                formData: S,
                body: h,
                formMethod: d.method || x,
                formEncType: d.encType || D,
                replace: d.replace,
                state: d.state,
                fromRouteId: s,
                flushSync: d.flushSync,
                viewTransition: d.viewTransition
            })
    }
    , [i, o, s])
}
function k0(i, {relative: o}={}) {
    let {basename: s} = O.useContext(fl)
      , f = O.useContext(Nl);
    Lt(f, "useFormAction must be used inside a RouteContext");
    let[d] = f.matches.slice(-1)
      , v = {
        ...Lu(i || ".", {
            relative: o
        })
    }
      , x = ua();
    if (i == null) {
        v.search = x.search;
        let D = new URLSearchParams(v.search)
          , S = D.getAll("index");
        if (S.some(R => R === "")) {
            D.delete("index"),
            S.filter(Y => Y).forEach(Y => D.append("index", Y));
            let R = D.toString();
            v.search = R ? `?${R}` : ""
        }
    }
    return (!i || i === ".") && d.route.index && (v.search = v.search ? v.search.replace(/^\?/, "?index&") : "?index"),
    s !== "/" && (v.pathname = v.pathname === "/" ? s : Dl([s, v.pathname])),
    zu(v)
}
function $0(i, o={}) {
    let s = O.useContext(Km);
    Lt(s != null, "`useViewTransitionState` must be used within `react-router-dom`'s `RouterProvider`.  Did you accidentally import `RouterProvider` from `react-router`?");
    let {basename: f} = eh("useViewTransitionState")
      , d = Lu(i, {
        relative: o.relative
    });
    if (!s.isTransitioning)
        return !1;
    let v = Ml(s.currentLocation.pathname, f) || s.currentLocation.pathname
      , x = Ml(s.nextLocation.pathname, f) || s.nextLocation.pathname;
    return Pi(d.pathname, x) != null || Pi(d.pathname, v) != null
}
new TextEncoder;
[...U0];
const na = "https://a-minecraft-movie-api.challs.umdctf.io";
async function W0() {
    if (window.sessionNumber !== void 0)
        return;
    const i = await fetch(`${na}/start-session`, {
        method: "POST",
        credentials: "include"
    });
    if (!i.ok)
        return;
    const {sessionNumber: o} = await i.json();
    window.sessionNumber = o
}
const lh = O.createContext(void 0);
function ah() {
    const i = O.useContext(lh);
    if (!i)
        throw new Error("Missing AccountContext.Provider");
    return i
}
function F0() {
    const [i,o] = O.useState("login")
      , [s,f] = O.useState("")
      , [d,v] = O.useState("")
      , [x,D] = O.useState()
      , [S,h] = ah()
      , R = Nr()
      , Y = O.useCallback(async C => {
        C.preventDefault();
        const H = await fetch(`${na}${i === "login" ? "/login" : "/register"}`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            credentials: "include",
            body: JSON.stringify({
                username: s,
                password: d
            })
        });
        if (!H.ok) {
            D(await H.text());
            return
        }
        h(!0),
        R("/")
    }
    , [s, d, i, R]);
    return w.jsx("div", {
        className: "flex justify-center items-center",
        children: w.jsxs("div", {
            className: "w-full max-w-md p-8 bg-white rounded-xl shadow-md",
            children: [w.jsx("h2", {
                className: "text-2xl font-bold text-center text-[#52a535] mb-6",
                children: i === "login" ? "Login" : "Register"
            }), w.jsxs("form", {
                className: "space-y-4",
                onSubmit: Y,
                children: [w.jsxs("div", {
                    children: [w.jsx("label", {
                        className: "block text-sm font-medium text-gray-700",
                        children: "Username"
                    }), w.jsx("input", {
                        id: "username",
                        type: "text",
                        value: s,
                        onChange: C => f(C.target.value),
                        className: "mt-1 block w-full rounded-md border border-gray-300 shadow-sm px-3 py-2 focus:outline-none focus:ring-[#52a535] focus:border-[#52a535]",
                        required: !0
                    })]
                }), w.jsxs("div", {
                    children: [w.jsx("label", {
                        className: "block text-sm font-medium text-gray-700",
                        children: "Password"
                    }), w.jsx("input", {
                        id: "password",
                        type: "password",
                        value: d,
                        onChange: C => v(C.target.value),
                        className: "mt-1 block w-full rounded-md border border-gray-300 shadow-sm px-3 py-2 focus:outline-none focus:ring-[#52a535] focus:border-[#52a535]",
                        required: !0
                    })]
                }), x && w.jsx("p", {
                    className: "text-red-600 text-sm font-medium",
                    children: x
                }), w.jsx("button", {
                    type: "submit",
                    className: "cursor-pointer w-full py-2 px-4 bg-[#52a535] text-white rounded-md font-semibold hover:bg-green-600 transition",
                    children: i === "login" ? "Log In" : "Sign Up"
                })]
            }), w.jsx("div", {
                className: "mt-4 text-center",
                children: w.jsxs("p", {
                    className: "text-sm text-gray-600",
                    children: [i === "login" ? "Don't have an account?" : "Already registered?", w.jsx("button", {
                        type: "button",
                        onClick: () => o(i === "login" ? "register" : "login"),
                        className: "cursor-pointer ml-1 text-[#52a535] font-medium hover:underline",
                        children: i === "login" ? "Register" : "Login"
                    })]
                })
            })]
        })
    })
}
function I0() {
    const [i,o] = ah();
    return O.useEffect( () => {
        (async () => (await fetch(`${na}/me`, {
            credentials: "include"
        })).ok && o(!0))()
    }
    ),
    w.jsx("nav", {
        className: "bg-[#52a535] shadow-lg p-4 mb-8",
        children: w.jsxs("div", {
            className: "flex items-center justify-between",
            children: [w.jsx(Na, {
                to: "/",
                className: "text-xl font-semibold tracking-wide text-white cursor-pointer",
                children: "Minecraft Movie Fanclub"
            }), w.jsxs("div", {
                className: "space-x-6",
                children: [w.jsx(Na, {
                    to: "/create-post",
                    className: "text-white hover:underline cursor-pointer",
                    children: "Create a Post"
                }), i ? w.jsx(Na, {
                    to: "/account",
                    className: "text-white hover:underline cursor-pointer",
                    children: "Account"
                }) : w.jsx(Na, {
                    to: "/login",
                    className: "text-white hover:underline cursor-pointer",
                    children: "Login / Register"
                })]
            })]
        })
    })
}
/*! @license DOMPurify 3.2.5 | (c) Cure53 and other contributors | Released under the Apache license 2.0 and Mozilla Public License 2.0 | github.com/cure53/DOMPurify/blob/3.2.5/LICENSE */
const {entries: nh, setPrototypeOf: Nm, isFrozen: P0, getPrototypeOf: tg, getOwnPropertyDescriptor: eg} = Object;
let {freeze: ye, seal: Xe, create: uh} = Object
  , {apply: Rr, construct: Or} = typeof Reflect < "u" && Reflect;
ye || (ye = function(o) {
    return o
}
);
Xe || (Xe = function(o) {
    return o
}
);
Rr || (Rr = function(o, s, f) {
    return o.apply(s, f)
}
);
Or || (Or = function(o, s) {
    return new o(...s)
}
);
const ki = ve(Array.prototype.forEach)
  , lg = ve(Array.prototype.lastIndexOf)
  , zm = ve(Array.prototype.pop)
  , Ru = ve(Array.prototype.push)
  , ag = ve(Array.prototype.splice)
  , Ii = ve(String.prototype.toLowerCase)
  , Sr = ve(String.prototype.toString)
  , Um = ve(String.prototype.match)
  , Ou = ve(String.prototype.replace)
  , ng = ve(String.prototype.indexOf)
  , ug = ve(String.prototype.trim)
  , Ie = ve(Object.prototype.hasOwnProperty)
  , he = ve(RegExp.prototype.test)
  , Du = ig(TypeError);
function ve(i) {
    return function(o) {
        o instanceof RegExp && (o.lastIndex = 0);
        for (var s = arguments.length, f = new Array(s > 1 ? s - 1 : 0), d = 1; d < s; d++)
            f[d - 1] = arguments[d];
        return Rr(i, o, f)
    }
}
function ig(i) {
    return function() {
        for (var o = arguments.length, s = new Array(o), f = 0; f < o; f++)
            s[f] = arguments[f];
        return Or(i, s)
    }
}
function dt(i, o) {
    let s = arguments.length > 2 && arguments[2] !== void 0 ? arguments[2] : Ii;
    Nm && Nm(i, null);
    let f = o.length;
    for (; f--; ) {
        let d = o[f];
        if (typeof d == "string") {
            const v = s(d);
            v !== d && (P0(o) || (o[f] = v),
            d = v)
        }
        i[d] = !0
    }
    return i
}
function cg(i) {
    for (let o = 0; o < i.length; o++)
        Ie(i, o) || (i[o] = null);
    return i
}
function Ma(i) {
    const o = uh(null);
    for (const [s,f] of nh(i))
        Ie(i, s) && (Array.isArray(f) ? o[s] = cg(f) : f && typeof f == "object" && f.constructor === Object ? o[s] = Ma(f) : o[s] = f);
    return o
}
function Mu(i, o) {
    for (; i !== null; ) {
        const f = eg(i, o);
        if (f) {
            if (f.get)
                return ve(f.get);
            if (typeof f.value == "function")
                return ve(f.value)
        }
        i = tg(i)
    }
    function s() {
        return null
    }
    return s
}
const Cm = ye(["a", "abbr", "acronym", "address", "area", "article", "aside", "audio", "b", "bdi", "bdo", "big", "blink", "blockquote", "body", "br", "button", "canvas", "caption", "center", "cite", "code", "col", "colgroup", "content", "data", "datalist", "dd", "decorator", "del", "details", "dfn", "dialog", "dir", "div", "dl", "dt", "element", "em", "fieldset", "figcaption", "figure", "font", "footer", "form", "h1", "h2", "h3", "h4", "h5", "h6", "head", "header", "hgroup", "hr", "html", "i", "img", "input", "ins", "kbd", "label", "legend", "li", "main", "map", "mark", "marquee", "menu", "menuitem", "meter", "nav", "nobr", "ol", "optgroup", "option", "output", "p", "picture", "pre", "progress", "q", "rp", "rt", "ruby", "s", "samp", "section", "select", "shadow", "small", "source", "spacer", "span", "strike", "strong", "style", "sub", "summary", "sup", "table", "tbody", "td", "template", "textarea", "tfoot", "th", "thead", "time", "tr", "track", "tt", "u", "ul", "var", "video", "wbr"])
  , br = ye(["svg", "a", "altglyph", "altglyphdef", "altglyphitem", "animatecolor", "animatemotion", "animatetransform", "circle", "clippath", "defs", "desc", "ellipse", "filter", "font", "g", "glyph", "glyphref", "hkern", "image", "line", "lineargradient", "marker", "mask", "metadata", "mpath", "path", "pattern", "polygon", "polyline", "radialgradient", "rect", "stop", "style", "switch", "symbol", "text", "textpath", "title", "tref", "tspan", "view", "vkern"])
  , Er = ye(["feBlend", "feColorMatrix", "feComponentTransfer", "feComposite", "feConvolveMatrix", "feDiffuseLighting", "feDisplacementMap", "feDistantLight", "feDropShadow", "feFlood", "feFuncA", "feFuncB", "feFuncG", "feFuncR", "feGaussianBlur", "feImage", "feMerge", "feMergeNode", "feMorphology", "feOffset", "fePointLight", "feSpecularLighting", "feSpotLight", "feTile", "feTurbulence"])
  , fg = ye(["animate", "color-profile", "cursor", "discard", "font-face", "font-face-format", "font-face-name", "font-face-src", "font-face-uri", "foreignobject", "hatch", "hatchpath", "mesh", "meshgradient", "meshpatch", "meshrow", "missing-glyph", "script", "set", "solidcolor", "unknown", "use"])
  , Tr = ye(["math", "menclose", "merror", "mfenced", "mfrac", "mglyph", "mi", "mlabeledtr", "mmultiscripts", "mn", "mo", "mover", "mpadded", "mphantom", "mroot", "mrow", "ms", "mspace", "msqrt", "mstyle", "msub", "msup", "msubsup", "mtable", "mtd", "mtext", "mtr", "munder", "munderover", "mprescripts"])
  , rg = ye(["maction", "maligngroup", "malignmark", "mlongdiv", "mscarries", "mscarry", "msgroup", "mstack", "msline", "msrow", "semantics", "annotation", "annotation-xml", "mprescripts", "none"])
  , Lm = ye(["#text"])
  , Hm = ye(["accept", "action", "align", "alt", "autocapitalize", "autocomplete", "autopictureinpicture", "autoplay", "background", "bgcolor", "border", "capture", "cellpadding", "cellspacing", "checked", "cite", "class", "clear", "color", "cols", "colspan", "controls", "controlslist", "coords", "crossorigin", "datetime", "decoding", "default", "dir", "disabled", "disablepictureinpicture", "disableremoteplayback", "download", "draggable", "enctype", "enterkeyhint", "face", "for", "headers", "height", "hidden", "high", "href", "hreflang", "id", "inputmode", "integrity", "ismap", "kind", "label", "lang", "list", "loading", "loop", "low", "max", "maxlength", "media", "method", "min", "minlength", "multiple", "muted", "name", "nonce", "noshade", "novalidate", "nowrap", "open", "optimum", "pattern", "placeholder", "playsinline", "popover", "popovertarget", "popovertargetaction", "poster", "preload", "pubdate", "radiogroup", "readonly", "rel", "required", "rev", "reversed", "role", "rows", "rowspan", "spellcheck", "scope", "selected", "shape", "size", "sizes", "span", "srclang", "start", "src", "srcset", "step", "style", "summary", "tabindex", "title", "translate", "type", "usemap", "valign", "value", "width", "wrap", "xmlns", "slot"])
  , Ar = ye(["accent-height", "accumulate", "additive", "alignment-baseline", "amplitude", "ascent", "attributename", "attributetype", "azimuth", "basefrequency", "baseline-shift", "begin", "bias", "by", "class", "clip", "clippathunits", "clip-path", "clip-rule", "color", "color-interpolation", "color-interpolation-filters", "color-profile", "color-rendering", "cx", "cy", "d", "dx", "dy", "diffuseconstant", "direction", "display", "divisor", "dur", "edgemode", "elevation", "end", "exponent", "fill", "fill-opacity", "fill-rule", "filter", "filterunits", "flood-color", "flood-opacity", "font-family", "font-size", "font-size-adjust", "font-stretch", "font-style", "font-variant", "font-weight", "fx", "fy", "g1", "g2", "glyph-name", "glyphref", "gradientunits", "gradienttransform", "height", "href", "id", "image-rendering", "in", "in2", "intercept", "k", "k1", "k2", "k3", "k4", "kerning", "keypoints", "keysplines", "keytimes", "lang", "lengthadjust", "letter-spacing", "kernelmatrix", "kernelunitlength", "lighting-color", "local", "marker-end", "marker-mid", "marker-start", "markerheight", "markerunits", "markerwidth", "maskcontentunits", "maskunits", "max", "mask", "media", "method", "mode", "min", "name", "numoctaves", "offset", "operator", "opacity", "order", "orient", "orientation", "origin", "overflow", "paint-order", "path", "pathlength", "patterncontentunits", "patterntransform", "patternunits", "points", "preservealpha", "preserveaspectratio", "primitiveunits", "r", "rx", "ry", "radius", "refx", "refy", "repeatcount", "repeatdur", "restart", "result", "rotate", "scale", "seed", "shape-rendering", "slope", "specularconstant", "specularexponent", "spreadmethod", "startoffset", "stddeviation", "stitchtiles", "stop-color", "stop-opacity", "stroke-dasharray", "stroke-dashoffset", "stroke-linecap", "stroke-linejoin", "stroke-miterlimit", "stroke-opacity", "stroke", "stroke-width", "style", "surfacescale", "systemlanguage", "tabindex", "tablevalues", "targetx", "targety", "transform", "transform-origin", "text-anchor", "text-decoration", "text-rendering", "textlength", "type", "u1", "u2", "unicode", "values", "viewbox", "visibility", "version", "vert-adv-y", "vert-origin-x", "vert-origin-y", "width", "word-spacing", "wrap", "writing-mode", "xchannelselector", "ychannelselector", "x", "x1", "x2", "xmlns", "y", "y1", "y2", "z", "zoomandpan"])
  , wm = ye(["accent", "accentunder", "align", "bevelled", "close", "columnsalign", "columnlines", "columnspan", "denomalign", "depth", "dir", "display", "displaystyle", "encoding", "fence", "frame", "height", "href", "id", "largeop", "length", "linethickness", "lspace", "lquote", "mathbackground", "mathcolor", "mathsize", "mathvariant", "maxsize", "minsize", "movablelimits", "notation", "numalign", "open", "rowalign", "rowlines", "rowspacing", "rowspan", "rspace", "rquote", "scriptlevel", "scriptminsize", "scriptsizemultiplier", "selection", "separator", "separators", "stretchy", "subscriptshift", "supscriptshift", "symmetric", "voffset", "width", "xmlns"])
  , $i = ye(["xlink:href", "xml:id", "xlink:title", "xml:space", "xmlns:xlink"])
  , og = Xe(/\{\{[\w\W]*|[\w\W]*\}\}/gm)
  , sg = Xe(/<%[\w\W]*|[\w\W]*%>/gm)
  , dg = Xe(/\$\{[\w\W]*/gm)
  , mg = Xe(/^data-[\-\w.\u00B7-\uFFFF]+$/)
  , hg = Xe(/^aria-[\-\w]+$/)
  , ih = Xe(/^(?:(?:(?:f|ht)tps?|mailto|tel|callto|sms|cid|xmpp):|[^a-z]|[a-z+.\-]+(?:[^a-z+.\-:]|$))/i)
  , yg = Xe(/^(?:\w+script|data):/i)
  , vg = Xe(/[\u0000-\u0020\u00A0\u1680\u180E\u2000-\u2029\u205F\u3000]/g)
  , ch = Xe(/^html$/i)
  , gg = Xe(/^[a-z][.\w]*(-[.\w]+)+$/i);
var Bm = Object.freeze({
    __proto__: null,
    ARIA_ATTR: hg,
    ATTR_WHITESPACE: vg,
    CUSTOM_ELEMENT: gg,
    DATA_ATTR: mg,
    DOCTYPE_NAME: ch,
    ERB_EXPR: sg,
    IS_ALLOWED_URI: ih,
    IS_SCRIPT_OR_DATA: yg,
    MUSTACHE_EXPR: og,
    TMPLIT_EXPR: dg
});
const Nu = {
    element: 1,
    text: 3,
    progressingInstruction: 7,
    comment: 8,
    document: 9
}
  , pg = function() {
    return typeof window > "u" ? null : window
}
  , Sg = function(o, s) {
    if (typeof o != "object" || typeof o.createPolicy != "function")
        return null;
    let f = null;
    const d = "data-tt-policy-suffix";
    s && s.hasAttribute(d) && (f = s.getAttribute(d));
    const v = "dompurify" + (f ? "#" + f : "");
    try {
        return o.createPolicy(v, {
            createHTML(x) {
                return x
            },
            createScriptURL(x) {
                return x
            }
        })
    } catch {
        return console.warn("TrustedTypes policy " + v + " could not be created."),
        null
    }
}
  , jm = function() {
    return {
        afterSanitizeAttributes: [],
        afterSanitizeElements: [],
        afterSanitizeShadowDOM: [],
        beforeSanitizeAttributes: [],
        beforeSanitizeElements: [],
        beforeSanitizeShadowDOM: [],
        uponSanitizeAttribute: [],
        uponSanitizeElement: [],
        uponSanitizeShadowNode: []
    }
};
function fh() {
    let i = arguments.length > 0 && arguments[0] !== void 0 ? arguments[0] : pg();
    const o = $ => fh($);
    if (o.version = "3.2.5",
    o.removed = [],
    !i || !i.document || i.document.nodeType !== Nu.document || !i.Element)
        return o.isSupported = !1,
        o;
    let {document: s} = i;
    const f = s
      , d = f.currentScript
      , {DocumentFragment: v, HTMLTemplateElement: x, Node: D, Element: S, NodeFilter: h, NamedNodeMap: R=i.NamedNodeMap || i.MozNamedAttrMap, HTMLFormElement: Y, DOMParser: C, trustedTypes: G} = i
      , H = S.prototype
      , k = Mu(H, "cloneNode")
      , Z = Mu(H, "remove")
      , q = Mu(H, "nextSibling")
      , et = Mu(H, "childNodes")
      , Q = Mu(H, "parentNode");
    if (typeof x == "function") {
        const $ = s.createElement("template");
        $.content && $.content.ownerDocument && (s = $.content.ownerDocument)
    }
    let ut, P = "";
    const {implementation: Mt, createNodeIterator: Nt, createDocumentFragment: Ut, getElementsByTagName: xt} = s
      , {importNode: Pt} = f;
    let St = jm();
    o.isSupported = typeof nh == "function" && typeof Q == "function" && Mt && Mt.createHTMLDocument !== void 0;
    const {MUSTACHE_EXPR: qt, ERB_EXPR: Qe, TMPLIT_EXPR: Ze, DATA_ATTR: $t, ARIA_ATTR: M, IS_SCRIPT_OR_DATA: X, ATTR_WHITESPACE: tt, CUSTOM_ELEMENT: vt} = Bm;
    let {IS_ALLOWED_URI: y} = Bm
      , z = null;
    const V = dt({}, [...Cm, ...br, ...Er, ...Tr, ...Lm]);
    let j = null;
    const F = dt({}, [...Hm, ...Ar, ...wm, ...$i]);
    let I = Object.seal(uh(null, {
        tagNameCheck: {
            writable: !0,
            configurable: !1,
            enumerable: !0,
            value: null
        },
        attributeNameCheck: {
            writable: !0,
            configurable: !1,
            enumerable: !0,
            value: null
        },
        allowCustomizedBuiltInElements: {
            writable: !0,
            configurable: !1,
            enumerable: !0,
            value: !1
        }
    }))
      , at = null
      , te = null
      , At = !0
      , ze = !0
      , za = !1
      , ia = !0
      , Pe = !1
      , ca = !0
      , Ve = !1
      , En = !1
      , Tn = !1
      , ee = !1
      , Ua = !1
      , fa = !1
      , An = !0
      , ra = !1;
    const lc = "user-content-";
    let Ca = !0
      , oa = !1
      , zl = {}
      , Ue = null;
    const ie = dt({}, ["annotation-xml", "audio", "colgroup", "desc", "foreignobject", "head", "iframe", "math", "mi", "mn", "mo", "ms", "mtext", "noembed", "noframes", "noscript", "plaintext", "script", "style", "svg", "template", "thead", "title", "video", "xmp"]);
    let Ke = null;
    const ce = dt({}, ["audio", "video", "img", "source", "image", "track"]);
    let _n = null;
    const Hu = dt({}, ["alt", "class", "for", "id", "label", "name", "pattern", "placeholder", "role", "summary", "title", "value", "style", "xmlns"])
      , La = "http://www.w3.org/1998/Math/MathML"
      , rl = "http://www.w3.org/2000/svg"
      , ge = "http://www.w3.org/1999/xhtml";
    let pe = ge
      , Ul = !1
      , ol = null;
    const ac = dt({}, [La, rl, ge], Sr);
    let sa = dt({}, ["mi", "mo", "mn", "ms", "mtext"])
      , da = dt({}, ["annotation-xml"]);
    const xn = dt({}, ["title", "style", "font", "a", "script"]);
    let Je = null;
    const nc = ["application/xhtml+xml", "text/html"]
      , wu = "text/html";
    let Bt = null
      , tl = null;
    const Rn = s.createElement("form")
      , On = function(p) {
        return p instanceof RegExp || p instanceof Function
    }
      , Dn = function() {
        let p = arguments.length > 0 && arguments[0] !== void 0 ? arguments[0] : {};
        if (!(tl && tl === p)) {
            if ((!p || typeof p != "object") && (p = {}),
            p = Ma(p),
            Je = nc.indexOf(p.PARSER_MEDIA_TYPE) === -1 ? wu : p.PARSER_MEDIA_TYPE,
            Bt = Je === "application/xhtml+xml" ? Sr : Ii,
            z = Ie(p, "ALLOWED_TAGS") ? dt({}, p.ALLOWED_TAGS, Bt) : V,
            j = Ie(p, "ALLOWED_ATTR") ? dt({}, p.ALLOWED_ATTR, Bt) : F,
            ol = Ie(p, "ALLOWED_NAMESPACES") ? dt({}, p.ALLOWED_NAMESPACES, Sr) : ac,
            _n = Ie(p, "ADD_URI_SAFE_ATTR") ? dt(Ma(Hu), p.ADD_URI_SAFE_ATTR, Bt) : Hu,
            Ke = Ie(p, "ADD_DATA_URI_TAGS") ? dt(Ma(ce), p.ADD_DATA_URI_TAGS, Bt) : ce,
            Ue = Ie(p, "FORBID_CONTENTS") ? dt({}, p.FORBID_CONTENTS, Bt) : ie,
            at = Ie(p, "FORBID_TAGS") ? dt({}, p.FORBID_TAGS, Bt) : {},
            te = Ie(p, "FORBID_ATTR") ? dt({}, p.FORBID_ATTR, Bt) : {},
            zl = Ie(p, "USE_PROFILES") ? p.USE_PROFILES : !1,
            At = p.ALLOW_ARIA_ATTR !== !1,
            ze = p.ALLOW_DATA_ATTR !== !1,
            za = p.ALLOW_UNKNOWN_PROTOCOLS || !1,
            ia = p.ALLOW_SELF_CLOSE_IN_ATTR !== !1,
            Pe = p.SAFE_FOR_TEMPLATES || !1,
            ca = p.SAFE_FOR_XML !== !1,
            Ve = p.WHOLE_DOCUMENT || !1,
            ee = p.RETURN_DOM || !1,
            Ua = p.RETURN_DOM_FRAGMENT || !1,
            fa = p.RETURN_TRUSTED_TYPE || !1,
            Tn = p.FORCE_BODY || !1,
            An = p.SANITIZE_DOM !== !1,
            ra = p.SANITIZE_NAMED_PROPS || !1,
            Ca = p.KEEP_CONTENT !== !1,
            oa = p.IN_PLACE || !1,
            y = p.ALLOWED_URI_REGEXP || ih,
            pe = p.NAMESPACE || ge,
            sa = p.MATHML_TEXT_INTEGRATION_POINTS || sa,
            da = p.HTML_INTEGRATION_POINTS || da,
            I = p.CUSTOM_ELEMENT_HANDLING || {},
            p.CUSTOM_ELEMENT_HANDLING && On(p.CUSTOM_ELEMENT_HANDLING.tagNameCheck) && (I.tagNameCheck = p.CUSTOM_ELEMENT_HANDLING.tagNameCheck),
            p.CUSTOM_ELEMENT_HANDLING && On(p.CUSTOM_ELEMENT_HANDLING.attributeNameCheck) && (I.attributeNameCheck = p.CUSTOM_ELEMENT_HANDLING.attributeNameCheck),
            p.CUSTOM_ELEMENT_HANDLING && typeof p.CUSTOM_ELEMENT_HANDLING.allowCustomizedBuiltInElements == "boolean" && (I.allowCustomizedBuiltInElements = p.CUSTOM_ELEMENT_HANDLING.allowCustomizedBuiltInElements),
            Pe && (ze = !1),
            Ua && (ee = !0),
            zl && (z = dt({}, Lm),
            j = [],
            zl.html === !0 && (dt(z, Cm),
            dt(j, Hm)),
            zl.svg === !0 && (dt(z, br),
            dt(j, Ar),
            dt(j, $i)),
            zl.svgFilters === !0 && (dt(z, Er),
            dt(j, Ar),
            dt(j, $i)),
            zl.mathMl === !0 && (dt(z, Tr),
            dt(j, wm),
            dt(j, $i))),
            p.ADD_TAGS && (z === V && (z = Ma(z)),
            dt(z, p.ADD_TAGS, Bt)),
            p.ADD_ATTR && (j === F && (j = Ma(j)),
            dt(j, p.ADD_ATTR, Bt)),
            p.ADD_URI_SAFE_ATTR && dt(_n, p.ADD_URI_SAFE_ATTR, Bt),
            p.FORBID_CONTENTS && (Ue === ie && (Ue = Ma(Ue)),
            dt(Ue, p.FORBID_CONTENTS, Bt)),
            Ca && (z["#text"] = !0),
            Ve && dt(z, ["html", "head", "body"]),
            z.table && (dt(z, ["tbody"]),
            delete at.tbody),
            p.TRUSTED_TYPES_POLICY) {
                if (typeof p.TRUSTED_TYPES_POLICY.createHTML != "function")
                    throw Du('TRUSTED_TYPES_POLICY configuration option must provide a "createHTML" hook.');
                if (typeof p.TRUSTED_TYPES_POLICY.createScriptURL != "function")
                    throw Du('TRUSTED_TYPES_POLICY configuration option must provide a "createScriptURL" hook.');
                ut = p.TRUSTED_TYPES_POLICY,
                P = ut.createHTML("")
            } else
                ut === void 0 && (ut = Sg(G, d)),
                ut !== null && typeof P == "string" && (P = ut.createHTML(""));
            ye && ye(p),
            tl = p
        }
    }
      , ke = dt({}, [...br, ...Er, ...fg])
      , Vt = dt({}, [...Tr, ...rg])
      , fe = function(p) {
        let B = Q(p);
        (!B || !B.tagName) && (B = {
            namespaceURI: pe,
            tagName: "template"
        });
        const K = Ii(p.tagName)
          , Rt = Ii(B.tagName);
        return ol[p.namespaceURI] ? p.namespaceURI === rl ? B.namespaceURI === ge ? K === "svg" : B.namespaceURI === La ? K === "svg" && (Rt === "annotation-xml" || sa[Rt]) : !!ke[K] : p.namespaceURI === La ? B.namespaceURI === ge ? K === "math" : B.namespaceURI === rl ? K === "math" && da[Rt] : !!Vt[K] : p.namespaceURI === ge ? B.namespaceURI === rl && !da[Rt] || B.namespaceURI === La && !sa[Rt] ? !1 : !Vt[K] && (xn[K] || !ke[K]) : !!(Je === "application/xhtml+xml" && ol[p.namespaceURI]) : !1
    }
      , le = function(p) {
        Ru(o.removed, {
            element: p
        });
        try {
            Q(p).removeChild(p)
        } catch {
            Z(p)
        }
    }
      , Cl = function(p, B) {
        try {
            Ru(o.removed, {
                attribute: B.getAttributeNode(p),
                from: B
            })
        } catch {
            Ru(o.removed, {
                attribute: null,
                from: B
            })
        }
        if (B.removeAttribute(p),
        p === "is")
            if (ee || Ua)
                try {
                    le(B)
                } catch {}
            else
                try {
                    B.setAttribute(p, "")
                } catch {}
    }
      , Bu = function(p) {
        let B = null
          , K = null;
        if (Tn)
            p = "<remove></remove>" + p;
        else {
            const Xt = Um(p, /^[\r\n\t ]+/);
            K = Xt && Xt[0]
        }
        Je === "application/xhtml+xml" && pe === ge && (p = '<html xmlns="http://www.w3.org/1999/xhtml"><head></head><body>' + p + "</body></html>");
        const Rt = ut ? ut.createHTML(p) : p;
        if (pe === ge)
            try {
                B = new C().parseFromString(Rt, Je)
            } catch {}
        if (!B || !B.documentElement) {
            B = Mt.createDocument(pe, "template", null);
            try {
                B.documentElement.innerHTML = Ul ? P : Rt
            } catch {}
        }
        const Kt = B.body || B.documentElement;
        return p && K && Kt.insertBefore(s.createTextNode(K), Kt.childNodes[0] || null),
        pe === ge ? xt.call(B, Ve ? "html" : "body")[0] : Ve ? B.documentElement : Kt
    }
      , ju = function(p) {
        return Nt.call(p.ownerDocument || p, p, h.SHOW_ELEMENT | h.SHOW_COMMENT | h.SHOW_TEXT | h.SHOW_PROCESSING_INSTRUCTION | h.SHOW_CDATA_SECTION, null)
    }
      , Ha = function(p) {
        return p instanceof Y && (typeof p.nodeName != "string" || typeof p.textContent != "string" || typeof p.removeChild != "function" || !(p.attributes instanceof R) || typeof p.removeAttribute != "function" || typeof p.setAttribute != "function" || typeof p.namespaceURI != "string" || typeof p.insertBefore != "function" || typeof p.hasChildNodes != "function")
    }
      , Ll = function(p) {
        return typeof D == "function" && p instanceof D
    };
    function Ae($, p, B) {
        ki($, K => {
            K.call(o, p, B, tl)
        }
        )
    }
    const sl = function(p) {
        let B = null;
        if (Ae(St.beforeSanitizeElements, p, null),
        Ha(p))
            return le(p),
            !0;
        const K = Bt(p.nodeName);
        if (Ae(St.uponSanitizeElement, p, {
            tagName: K,
            allowedTags: z
        }),
        p.hasChildNodes() && !Ll(p.firstElementChild) && he(/<[/\w!]/g, p.innerHTML) && he(/<[/\w!]/g, p.textContent) || p.nodeType === Nu.progressingInstruction || ca && p.nodeType === Nu.comment && he(/<[/\w]/g, p.data))
            return le(p),
            !0;
        if (!z[K] || at[K]) {
            if (!at[K] && Hl(K) && (I.tagNameCheck instanceof RegExp && he(I.tagNameCheck, K) || I.tagNameCheck instanceof Function && I.tagNameCheck(K)))
                return !1;
            if (Ca && !Ue[K]) {
                const Rt = Q(p) || p.parentNode
                  , Kt = et(p) || p.childNodes;
                if (Kt && Rt) {
                    const Xt = Kt.length;
                    for (let re = Xt - 1; re >= 0; --re) {
                        const de = k(Kt[re], !0);
                        de.__removalCount = (p.__removalCount || 0) + 1,
                        Rt.insertBefore(de, q(p))
                    }
                }
            }
            return le(p),
            !0
        }
        return p instanceof S && !fe(p) || (K === "noscript" || K === "noembed" || K === "noframes") && he(/<\/no(script|embed|frames)/i, p.innerHTML) ? (le(p),
        !0) : (Pe && p.nodeType === Nu.text && (B = p.textContent,
        ki([qt, Qe, Ze], Rt => {
            B = Ou(B, Rt, " ")
        }
        ),
        p.textContent !== B && (Ru(o.removed, {
            element: p.cloneNode()
        }),
        p.textContent = B)),
        Ae(St.afterSanitizeElements, p, null),
        !1)
    }
      , dl = function(p, B, K) {
        if (An && (B === "id" || B === "name") && (K in s || K in Rn))
            return !1;
        if (!(ze && !te[B] && he($t, B))) {
            if (!(At && he(M, B))) {
                if (!j[B] || te[B]) {
                    if (!(Hl(p) && (I.tagNameCheck instanceof RegExp && he(I.tagNameCheck, p) || I.tagNameCheck instanceof Function && I.tagNameCheck(p)) && (I.attributeNameCheck instanceof RegExp && he(I.attributeNameCheck, B) || I.attributeNameCheck instanceof Function && I.attributeNameCheck(B)) || B === "is" && I.allowCustomizedBuiltInElements && (I.tagNameCheck instanceof RegExp && he(I.tagNameCheck, K) || I.tagNameCheck instanceof Function && I.tagNameCheck(K))))
                        return !1
                } else if (!_n[B]) {
                    if (!he(y, Ou(K, tt, ""))) {
                        if (!((B === "src" || B === "xlink:href" || B === "href") && p !== "script" && ng(K, "data:") === 0 && Ke[p])) {
                            if (!(za && !he(X, Ou(K, tt, "")))) {
                                if (K)
                                    return !1
                            }
                        }
                    }
                }
            }
        }
        return !0
    }
      , Hl = function(p) {
        return p !== "annotation-xml" && Um(p, vt)
    }
      , ml = function(p) {
        Ae(St.beforeSanitizeAttributes, p, null);
        const {attributes: B} = p;
        if (!B || Ha(p))
            return;
        const K = {
            attrName: "",
            attrValue: "",
            keepAttr: !0,
            allowedAttributes: j,
            forceKeepAttr: void 0
        };
        let Rt = B.length;
        for (; Rt--; ) {
            const Kt = B[Rt]
              , {name: Xt, namespaceURI: re, value: de} = Kt
              , el = Bt(Xt);
            let Dt = Xt === "value" ? de : ug(de);
            if (K.attrName = el,
            K.attrValue = Dt,
            K.keepAttr = !0,
            K.forceKeepAttr = void 0,
            Ae(St.uponSanitizeAttribute, p, K),
            Dt = K.attrValue,
            ra && (el === "id" || el === "name") && (Cl(Xt, p),
            Dt = lc + Dt),
            ca && he(/((--!?|])>)|<\/(style|title)/i, Dt)) {
                Cl(Xt, p);
                continue
            }
            if (K.forceKeepAttr || (Cl(Xt, p),
            !K.keepAttr))
                continue;
            if (!ia && he(/\/>/i, Dt)) {
                Cl(Xt, p);
                continue
            }
            Pe && ki([qt, Qe, Ze], Mn => {
                Dt = Ou(Dt, Mn, " ")
            }
            );
            const wa = Bt(p.nodeName);
            if (dl(wa, el, Dt)) {
                if (ut && typeof G == "object" && typeof G.getAttributeType == "function" && !re)
                    switch (G.getAttributeType(wa, el)) {
                    case "TrustedHTML":
                        {
                            Dt = ut.createHTML(Dt);
                            break
                        }
                    case "TrustedScriptURL":
                        {
                            Dt = ut.createScriptURL(Dt);
                            break
                        }
                    }
                try {
                    re ? p.setAttributeNS(re, Xt, Dt) : p.setAttribute(Xt, Dt),
                    Ha(p) ? le(p) : zm(o.removed)
                } catch {}
            }
        }
        Ae(St.afterSanitizeAttributes, p, null)
    }
      , Gt = function $(p) {
        let B = null;
        const K = ju(p);
        for (Ae(St.beforeSanitizeShadowDOM, p, null); B = K.nextNode(); )
            Ae(St.uponSanitizeShadowNode, B, null),
            sl(B),
            ml(B),
            B.content instanceof v && $(B.content);
        Ae(St.afterSanitizeShadowDOM, p, null)
    };
    return o.sanitize = function($) {
        let p = arguments.length > 1 && arguments[1] !== void 0 ? arguments[1] : {}
          , B = null
          , K = null
          , Rt = null
          , Kt = null;
        if (Ul = !$,
        Ul && ($ = "<!-->"),
        typeof $ != "string" && !Ll($))
            if (typeof $.toString == "function") {
                if ($ = $.toString(),
                typeof $ != "string")
                    throw Du("dirty is not a string, aborting")
            } else
                throw Du("toString is not a function");
        if (!o.isSupported)
            return $;
        if (En || Dn(p),
        o.removed = [],
        typeof $ == "string" && (oa = !1),
        oa) {
            if ($.nodeName) {
                const de = Bt($.nodeName);
                if (!z[de] || at[de])
                    throw Du("root node is forbidden and cannot be sanitized in-place")
            }
        } else if ($ instanceof D)
            B = Bu("<!---->"),
            K = B.ownerDocument.importNode($, !0),
            K.nodeType === Nu.element && K.nodeName === "BODY" || K.nodeName === "HTML" ? B = K : B.appendChild(K);
        else {
            if (!ee && !Pe && !Ve && $.indexOf("<") === -1)
                return ut && fa ? ut.createHTML($) : $;
            if (B = Bu($),
            !B)
                return ee ? null : fa ? P : ""
        }
        B && Tn && le(B.firstChild);
        const Xt = ju(oa ? $ : B);
        for (; Rt = Xt.nextNode(); )
            sl(Rt),
            ml(Rt),
            Rt.content instanceof v && Gt(Rt.content);
        if (oa)
            return $;
        if (ee) {
            if (Ua)
                for (Kt = Ut.call(B.ownerDocument); B.firstChild; )
                    Kt.appendChild(B.firstChild);
            else
                Kt = B;
            return (j.shadowroot || j.shadowrootmode) && (Kt = Pt.call(f, Kt, !0)),
            Kt
        }
        let re = Ve ? B.outerHTML : B.innerHTML;
        return Ve && z["!doctype"] && B.ownerDocument && B.ownerDocument.doctype && B.ownerDocument.doctype.name && he(ch, B.ownerDocument.doctype.name) && (re = "<!DOCTYPE " + B.ownerDocument.doctype.name + `>
` + re),
        Pe && ki([qt, Qe, Ze], de => {
            re = Ou(re, de, " ")
        }
        ),
        ut && fa ? ut.createHTML(re) : re
    }
    ,
    o.setConfig = function() {
        let $ = arguments.length > 0 && arguments[0] !== void 0 ? arguments[0] : {};
        Dn($),
        En = !0
    }
    ,
    o.clearConfig = function() {
        tl = null,
        En = !1
    }
    ,
    o.isValidAttribute = function($, p, B) {
        tl || Dn({});
        const K = Bt($)
          , Rt = Bt(p);
        return dl(K, Rt, B)
    }
    ,
    o.addHook = function($, p) {
        typeof p == "function" && Ru(St[$], p)
    }
    ,
    o.removeHook = function($, p) {
        if (p !== void 0) {
            const B = lg(St[$], p);
            return B === -1 ? void 0 : ag(St[$], B, 1)[0]
        }
        return zm(St[$])
    }
    ,
    o.removeHooks = function($) {
        St[$] = []
    }
    ,
    o.removeAllHooks = function() {
        St = jm()
    }
    ,
    o
}
var rh = fh();
const bg = /^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$/i;
function Ym(i) {
    return bg.test(i)
}
function Eg() {
    const i = ua()
      , s = new URLSearchParams(i.search).get("postId")
      , [f,d] = O.useState()
      , [v,x] = O.useState()
      , [D,S] = O.useState()
      , h = O.useCallback(async () => {
        try {
            const C = await fetch(`${na}/post?postId=${s}`);
            if (!C.ok) {
                x(await C.text());
                return
            }
            d(await C.json())
        } catch {
            x("Failed to fetch post.")
        }
    }
    , [s, d, x])
      , R = O.useCallback(async C => {
        await W0();
        const G = await fetch(`${na}/legacy-social`, {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: `sessionNumber=${window.sessionNumber}&postId=${s}&likes=${C}`,
            credentials: "include"
        });
        if (!G.ok) {
            S(await G.text());
            return
        }
        await h()
    }
    , [s, h, S]);
    if (O.useEffect( () => {
        s != null && Ym(s) && h()
    }
    , [s]),
    s != null && !Ym(s))
        return w.jsx("div", {
            className: "flex justify-center items-center",
            children: w.jsx("p", {
                className: "text-lg font-semibold text-red-600",
                children: "Invalid post id!"
            })
        });
    if (v)
        return w.jsx("div", {
            className: "flex justify-center items-center",
            children: w.jsxs("p", {
                className: "text-lg font-semibold text-red-600",
                children: ["Error: ", v]
            })
        });
    if (!f)
        return w.jsx("div", {
            className: "flex justify-center items-center",
            children: w.jsx("p", {
                className: "text-lg font-semibold text-gray-600",
                children: "Loading..."
            })
        });
    const Y = rh.sanitize(f.content, {
        ADD_TAGS: ["iframe"],
        ADD_ATTR: ["allow", "allowfullscreen", "frameborder", "scrolling", "src", "width", "height"]
    });
    return w.jsx("div", {
        className: "flex justify-center items-center",
        children: w.jsxs("div", {
            className: "w-xl rounded-xl shadow-md overflow-hidden",
            children: [w.jsxs("div", {
                className: "bg-[#52a535] p-4",
                children: [w.jsx("h2", {
                    className: "text-white text-xl font-bold",
                    children: f.title
                }), w.jsxs("p", {
                    className: "text-white text-sm",
                    children: ["Posted by ", w.jsxs("span", {
                        className: "font-semibold",
                        children: ["@", f.username]
                    })]
                })]
            }), w.jsxs("div", {
                className: "p-6 bg-white space-y-4",
                children: [w.jsx("div", {
                    className: "text-gray-700",
                    dangerouslySetInnerHTML: {
                        __html: Y
                    }
                }), f.likedByAdmin && w.jsx("div", {
                    className: "bg-yellow-100 text-yellow-800 text-sm font-medium px-4 py-2 rounded-md shadow",
                    children: "🌟 This post was liked by an admin!"
                }), D && w.jsx("p", {
                    className: "text-sm text-red-600 font-medium",
                    children: D
                }), w.jsx("div", {
                    dangerouslySetInnerHTML: {
                        __html: "<!-- TODO: Migrate social endpoint, switch to useState/useContext -->"
                    }
                }), w.jsxs("div", {
                    className: "flex items-center justify-between",
                    children: [w.jsxs("span", {
                        className: "text-sm text-gray-600",
                        children: [f.likes, " Likes"]
                    }), w.jsxs("div", {
                        className: "flex items-center space-x-2",
                        children: [w.jsx("button", {
                            className: "cursor-pointer px-3 py-1 text-sm font-medium text-white bg-[#52a535] rounded-md hover:bg-green-600 transition",
                            onClick: () => R(1),
                            children: "👍 Like"
                        }), w.jsx("button", {
                            id: "dislike-button",
                            className: "cursor-pointer px-3 py-1 text-sm font-medium text-white bg-red-500 rounded-md hover:bg-red-600 transition",
                            onClick: () => R(-1),
                            children: "👎 Dislike"
                        })]
                    })]
                })]
            })]
        })
    })
}
function Tg() {
    const [i,o] = O.useState("")
      , [s,f] = O.useState("")
      , [d,v] = O.useState()
      , x = Nr()
      , D = O.useCallback(async S => {
        S.preventDefault();
        const h = await fetch(`${na}/create-post`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            credentials: "include",
            body: JSON.stringify({
                title: i,
                content: s
            })
        });
        if (!h.ok) {
            v(await h.text());
            return
        }
        const R = await h.text();
        x(`/post?postId=${R}`)
    }
    , [i, s, x]);
    return w.jsx("div", {
        className: "flex justify-center items-center",
        children: w.jsxs("form", {
            className: "w-full max-w-md rounded-xl shadow-md overflow-hidden bg-white",
            onSubmit: D,
            children: [w.jsx("div", {
                className: "bg-[#52a535] p-4",
                children: w.jsx("h2", {
                    className: "text-white text-xl font-bold",
                    children: "Create a Post"
                })
            }), w.jsxs("div", {
                className: "p-6 space-y-4",
                children: [d && w.jsx("p", {
                    className: "text-sm text-red-600 font-medium",
                    children: d
                }), w.jsxs("div", {
                    children: [w.jsx("label", {
                        className: "block text-sm font-medium text-gray-700 mb-1",
                        children: "Title"
                    }), w.jsx("input", {
                        type: "text",
                        className: "w-full px-3 py-2 border rounded-md shadow-sm focus:outline-none focus:ring-[#52a535] focus:border-[#52a535]",
                        value: i,
                        onChange: S => o(S.target.value),
                        required: !0
                    })]
                }), w.jsxs("div", {
                    children: [w.jsx("label", {
                        className: "block text-sm font-medium text-gray-700 mb-1",
                        children: "Content"
                    }), w.jsx("textarea", {
                        className: "w-full h-32 px-3 py-2 border rounded-md shadow-sm resize-none focus:outline-none focus:ring-[#52a535] focus:border-[#52a535]",
                        value: s,
                        onChange: S => f(S.target.value),
                        required: !0
                    })]
                }), w.jsx("div", {
                    className: "text-right",
                    children: w.jsx("button", {
                        type: "submit",
                        className: "cursor-pointer px-4 py-2 bg-[#52a535] text-white text-sm font-medium rounded-md hover:bg-green-600 transition",
                        children: "📤 Submit Post"
                    })
                })]
            })]
        })
    })
}
function oh({postId: i, title: o, username: s, likes: f}) {
    return w.jsxs(Na, {
        to: `/post?postId=${i}`,
        className: "w-72 bg-white rounded-xl shadow-md border border-gray-200 hover:shadow-lg transition transform hover:scale-[1.01]",
        children: [w.jsx("div", {
            className: "bg-[#52a535] text-white px-4 py-2 rounded-t-xl font-bold",
            children: o
        }), w.jsxs("div", {
            className: "p-4 space-y-2 text-sm text-gray-700",
            children: [w.jsxs("p", {
                children: [w.jsx("strong", {
                    children: "By:"
                }), " ", s]
            }), w.jsxs("p", {
                children: [w.jsx("strong", {
                    children: "Likes:"
                }), " ", f]
            })]
        })]
    }, i)
}
function Ag() {
    const [i,o] = O.useState([])
      , [s,f] = O.useState();
    return O.useEffect( () => {
        fetch(`${na}/top-posts`).then(d => {
            if (!d.ok)
                throw new Error("Failed to load posts");
            return d.json()
        }
        ).then(d => {
            o(d.sort( (v, x) => x.likes - v.likes))
        }
        ).catch(d => f(d.message))
    }
    , []),
    w.jsxs("div", {
        className: "p-6 bg-white min-h-screen",
        children: [w.jsx("h1", {
            className: "text-3xl font-extrabold text-[#52a535] mb-6 text-center",
            children: "Top Minecraft Movie Posts!!!!!!"
        }), s && w.jsx("p", {
            className: "text-red-600 text-center mb-4",
            children: s
        }), w.jsx("div", {
            className: "flex flex-wrap justify-start gap-6 pl-4",
            children: i.map(d => w.jsx(oh, {
                postId: d.postId,
                title: d.title,
                username: d.username,
                likes: d.likes
            }, d.postId))
        })]
    })
}
function _g() {
    const [i,o] = O.useState()
      , [s,f] = O.useState();
    return O.useEffect( () => {
        fetch(`${na}/me`, {
            credentials: "include"
        }).then(d => {
            if (!d.ok)
                throw new Error("Failed to load account info");
            return d.json()
        }
        ).then(o).catch(d => f(d.message))
    }
    , []),
    w.jsxs("div", {
        className: "p-6 bg-white min-h-screen",
        children: [w.jsx("h1", {
            className: "text-3xl font-extrabold text-[#52a535] mb-6 text-center",
            children: "Account Summary"
        }), s && w.jsx("p", {
            className: "text-red-600 text-center mb-4",
            children: s
        }), i && w.jsxs(w.Fragment, {
            children: [w.jsxs("div", {
                className: "text-center text-gray-800 mb-8 space-y-2",
                children: [w.jsxs("p", {
                    className: "text-xl font-semibold",
                    children: ["Username: ", i.username]
                }), w.jsxs("p", {
                    className: "text-lg",
                    children: ["Current Session Number: ", window.sessionNumber ? window.sessionNumber : "undefined"]
                }), "flag"in i && i.flag && w.jsxs("div", {
                    className: "mt-4 text-green-700 font-medium",
                    children: [w.jsx("p", {
                        children: "Wow, an admin liked your post! ⛏️"
                    }), w.jsxs("p", {
                        children: ["Your flag is: ", i.flag]
                    })]
                })]
            }), w.jsx("h2", {
                className: "text-2xl font-bold text-[#52a535] mb-4 pl-4",
                children: "Your Posts"
            }), i.posts.length === 0 ? w.jsx("p", {
                className: "pl-4 text-gray-600 italic",
                children: "You haven't posted anything yet."
            }) : w.jsx("div", {
                className: "flex flex-wrap justify-start gap-6 pl-4",
                children: i.posts.map(d => w.jsx(oh, {
                    postId: d.postId,
                    title: d.title,
                    username: i.username,
                    likes: d.likes
                }, d.postId))
            })]
        })]
    })
}
function xg() {
    const [i,o] = O.useState(!1);
    return O.useEffect( () => {
        rh.addHook("uponSanitizeElement", (s, f) => {
            var d;
            f.tagName === "iframe" && s instanceof Element && ((s.getAttribute("src") || "").startsWith("https://www.youtube.com/embed/") || (d = s.parentNode) == null || d.removeChild(s))
        }
        )
    }
    ),
    w.jsx("div", {
        className: "min-h-screen bg-gray-50 text-gray-900",
        children: w.jsxs(lh.Provider, {
            value: [i, o],
            children: [w.jsx(I0, {}), w.jsxs(v0, {
                children: [w.jsx(pn, {
                    path: "/",
                    element: w.jsx(Ag, {})
                }), w.jsx(pn, {
                    path: "/login",
                    element: w.jsx(F0, {})
                }), w.jsx(pn, {
                    path: "/post",
                    element: w.jsx(Eg, {})
                }), w.jsx(pn, {
                    path: "/create-post",
                    element: w.jsx(Tg, {})
                }), w.jsx(pn, {
                    path: "/account",
                    element: w.jsx(_g, {})
                })]
            })]
        })
    })
}
Rv.createRoot(document.getElementById("root")).render(w.jsx(O.StrictMode, {
    children: w.jsx(q0, {
        children: w.jsx(xg, {})
    })
}));
