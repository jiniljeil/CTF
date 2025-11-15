Beamer.appendHtml = function (a, b) { 
    var c = document.createElement("div"); 
    for (c.innerHTML = b; 0 < c.children.length;)
        a.appendChild(c.children[0]) 
    };

Beamer.appendPushScript = function (a) {
    if (!(Beamer.isSafari() || Beamer.isIE() || Beamer.isFacebookApp() || Beamer.isInstagramApp())) 
        if ("undefined" !== typeof Beamer.pushDomain) 
            (Beamer.pushDomain == window.location.host || "undefined" !== typeof Beamer.extendedPushDomain && Beamer.extendedPushDomain && window.location.host.endsWith("." + Beamer.pushDomain)) 
            && Beamer.appendPushPermissionScript(a); 
        else if ("undefined" !== typeof _BEAMER_PUSH_PROMPT_TYPE && ("popup" == _BEAMER_PUSH_PROMPT_TYPE || "sidebar" == _BEAMER_PUSH_PROMPT_TYPE)) {
            var b = _BEAMER_PUSH_URL + "embeddedPush?product=" + beamer_config.product_id; 
            if (beamer_config.language) b += "&language=" + beamer_config.language; 
            else { var c = window.navigator.userLanguage || window.navigator.language; c && 1 < c.length && (c = c.substring(0, 2).toUpperCase(), b += "&language=" + c) } 
            "undefined" !== typeof a && !0 === a && (Beamer.pushRefused = !0); 
            "undefined" !== typeof Beamer.escapeHtml && (b = Beamer.escapeHtml(b)); 
            Beamer.appendHtml(document.body, "<iframe id='beamerPush' src='" + b + "' width='0' height='0' frameborder='0' scrolling='no'></iframe>")
        }
};    

Beamer.update = function (a) {
    if ("undefined" !== typeof a) {
        var b = !1;
        "undefined" !== typeof a.onopen && Beamer.isFunction(a.onopen) && beamer_config.onopen !== a.onopen && (beamer_config.onopen = a.onopen, b = !0);
        "undefined" !== typeof a.onclose && Beamer.isFunction(a.onclose) && beamer_config.onclose !== a.onclose && (beamer_config.onclose = a.onclose, b = !0);
        "undefined" !== typeof a.onclick && Beamer.isFunction(a.onclick) && beamer_config.onclick !== a.onclick && (beamer_config.onclick = a.onclick, b = !0);
        "undefined" !== typeof a.onerror && Beamer.isFunction(a.onerror) &&
            beamer_config.onerror !== a.onerror && (beamer_config.onerror = a.onerror, b = !0);
        "undefined" !== typeof a.onOpen && Beamer.isFunction(a.onOpen) && beamer_config.onOpen !== a.onOpen && (beamer_config.onOpen = a.onOpen, b = !0);
        "undefined" !== typeof a.onClose && Beamer.isFunction(a.onClose) && beamer_config.onClose !== a.onClose && (beamer_config.onClose = a.onClose, b = !0);
        "undefined" !== typeof a.onClick && Beamer.isFunction(a.onClick) && beamer_config.onClick !== a.onClick && (beamer_config.onClick = a.onClick, b = !0);
        "undefined" !== typeof a.onError &&
            Beamer.isFunction(a.onError) && beamer_config.onError !== a.onError && (beamer_config.onError = a.onError, b = !0);
        "undefined" !== typeof a.onNpsShow && Beamer.isFunction(a.onNpsShow) && beamer_config.onNpsShow !== a.onNpsShow && (beamer_config.onNpsShow = a.onNpsShow, b = !0);
        "undefined" !== typeof a.onNpsHide && Beamer.isFunction(a.onNpsHide) && beamer_config.onNpsHide !== a.onNpsHide && (beamer_config.onNpsHide = a.onNpsHide, b = !0);
        "undefined" !== typeof a.onNpsScore && Beamer.isFunction(a.onNpsScore) && beamer_config.onNpsScore !== a.onNpsScore &&
            (beamer_config.onNpsScore = a.onNpsScore, b = !0);
        "undefined" !== typeof a.onNpsFeedback && Beamer.isFunction(a.onNpsFeedback) && beamer_config.onNpsFeedback !== a.onNpsFeedback && (beamer_config.onNpsFeedback = a.onNpsFeedback, b = !0);
        "undefined" !== typeof a.onInputFocus && Beamer.isFunction(a.onInputFocus) && beamer_config.onInputFocus !== a.onInputFocus && (beamer_config.onInputFocus = a.onInputFocus, b = !0);
        "undefined" !== typeof a.onInputBlur && Beamer.isFunction(a.onInputBlur) && beamer_config.onInputBlur !== a.onInputBlur && (beamer_config.onInputBlur =
            a.onInputBlur, b = !0);
        "undefined" !== typeof a.filter_by_url && beamer_config.filter_by_url !== a.filter_by_url && (beamer_config.filter_by_url = a.filter_by_url, b = !0);
        "undefined" !== typeof a.filter && beamer_config.filter !== a.filter && (beamer_config.filter = a.filter, b = !0);
        "undefined" !== typeof a.force_filter && beamer_config.force_filter !== a.force_filter && (beamer_config.force_filter = a.force_filter, b = !0);
        "undefined" !== typeof a.language && beamer_config.language !== a.language && (beamer_config.language = a.language, b = !0);
        "undefined" !==
            typeof a.user_id && beamer_config.user_id !== a.user_id && (beamer_config.user_id = a.user_id, b = !0);
        "undefined" !== typeof a.user_token && beamer_config.user_token !== a.user_token && (beamer_config.user_token = a.user_token, b = !0);
        "undefined" !== typeof a.user_lastname && beamer_config.user_lastname !== a.user_lastname && (beamer_config.user_lastname = a.user_lastname, b = !0);
        "undefined" !== typeof a.user_firstname && beamer_config.user_firstname !== a.user_firstname && (beamer_config.user_firstname = a.user_firstname, b = !0);
        "undefined" !==
            typeof a.user_email && beamer_config.user_email !== a.user_email && (beamer_config.user_email = a.user_email, b = !0);
        "undefined" !== typeof a.alert && beamer_config.alert !== a.alert && (beamer_config.alert = a.alert, b = !0);
        "undefined" !== typeof a.counter && beamer_config.counter !== a.counter && (beamer_config.counter = a.counter, b = !0);
        "undefined" !== typeof a.standalone && beamer_config.standalone !== a.standalone && (beamer_config.standalone = a.standalone, b = !0);
        "undefined" !== typeof a.multi_user && beamer_config.multi_user !== a.multi_user &&
            (beamer_config.multi_user = a.multi_user, b = !0);
        "undefined" !== typeof a.first_visit_unread && beamer_config.first_visit_unread !== a.first_visit_unread && (beamer_config.first_visit_unread = a.first_visit_unread, b = !0);
        "undefined" !== typeof a.force_button && beamer_config.force_button !== a.force_button && (beamer_config.force_button = a.force_button, b = !0);
        "undefined" !== typeof a.post_request && beamer_config.post_request !== a.post_request && (beamer_config.post_request = a.post_request, b = !0);
        "undefined" !== typeof a.callback &&
            Beamer.isFunction(a.callback) && beamer_config.callback !== a.callback && (beamer_config.callback = a.callback, b = !0);
        "undefined" !== typeof a.ignore_auto_open && beamer_config.ignore_auto_open !== a.ignore_auto_open && (beamer_config.ignore_auto_open = a.ignore_auto_open, b = !0);
        "undefined" !== typeof a.ignore_auto_open_mobile && beamer_config.ignore_auto_open_mobile !== a.ignore_auto_open_mobile && (beamer_config.ignore_auto_open_mobile = a.ignore_auto_open_mobile, b = !0);
        "undefined" !== typeof a.ignore_boosted_announcements && beamer_config.ignore_boosted_announcements !==
            a.ignore_boosted_announcements && (beamer_config.ignore_boosted_announcements = a.ignore_boosted_announcements, b = !0);
        "undefined" !== typeof a.hide_feedback && beamer_config.hide_feedback !== a.hide_feedback && (beamer_config.hide_feedback = a.hide_feedback, b = !0);
        "undefined" !== typeof a.bounce && beamer_config.bounce !== a.bounce && (beamer_config.bounce = a.bounce, b = !0);
        "undefined" !== typeof a.right && beamer_config.right !== a.right && (beamer_config.right = a.right, b = !0);
        "undefined" !== typeof a.top && beamer_config.top !== a.top &&
            (beamer_config.top = a.top, b = !0);
        "undefined" !== typeof a.bottom && beamer_config.bottom !== a.bottom && (beamer_config.bottom = a.bottom, b = !0);
        "undefined" !== typeof a.left && beamer_config.left !== a.left && (beamer_config.left = a.left, b = !0);
        "undefined" !== typeof a.header_color && beamer_config.header_color !== a.header_color && (beamer_config.header_color = a.header_color, b = !0);
        "undefined" !== typeof a.standalone_logo && beamer_config.standalone_logo !== a.standalone_logo && (beamer_config.standalone_logo = a.standalone_logo, b = !0);
        "undefined" !==
            typeof a.theme && beamer_config.theme !== a.theme && (beamer_config.theme = a.theme, b = !0);
        for (var c in a)
            if (a.hasOwnProperty(c) && !(-1 < Beamer.reservedParameters.indexOf(c))) {
                var d = a[c];
                "undefined" === typeof d || "object" === typeof d || Beamer.isFunction(d) || beamer_config[c] === d || (beamer_config[c] = d, b = !0)
            } b && (Beamer.started ? Beamer.appendAlert(!0, !0) : Beamer.init())
    }
};

Beamer.init = function () {
    if (!Beamer.started) {
        Beamer.started = !0;
        var a = function () {
            try {
                console.warn("Seems your Beamer feed can't be accessed! Please contact our Support chat on https://getbeamer.com/")
            } catch (g) { }
            var e = "undefined" !== typeof beamer_config.onerror ? beamer_config.onerror : beamer_config.onError;
            e && Beamer.isFunction(e) && e()
        },
            b = function (e, g) {
                Beamer.config = e;
                Beamer.setParameters(e);
                "undefined" !== e.clearAllCookies && e.clearAllCookies && Beamer.clearAllCookies();
                "undefined" !== typeof e.topDomain && (Beamer.topDomain =
                    e.topDomain);
                if ("undefined" !== typeof e.enabled && !e.enabled || "undefined" !== typeof e.limited && typeof e.limited) "undefined" !== typeof e.limited && typeof e.limited ? a() : Beamer.enabled = !1;
                else {
                    if ("undefined" !== typeof e.blocked && typeof e.blocked) return a();
                    "undefined" !== typeof e.massive && !0 === e.massive && (_BEAMER_MASSIVE = !0);
                    "undefined" !== typeof Beamer.binded && Beamer.binded || (Beamer.bindWindowEvents(), Beamer.bindEscape(), Beamer.binded = !0);
                    if (!beamer_config.selector || 0 > beamer_config.selector.indexOf(".beamerTrigger")) beamer_config.selector &&
                        "" !== beamer_config.selector.trim() && "element-id" !== beamer_config.selector.trim() ? beamer_config.selector += ",.beamerTrigger" : (beamer_config.selector = ".beamerTrigger", Beamer.noSelector = !0);
                    if (!beamer_config.selector || 0 > beamer_config.selector.indexOf('a[href="#beamerTrigger"]')) beamer_config.selector && "" !== beamer_config.selector.trim() && "element-id" !== beamer_config.selector.trim() ? beamer_config.selector += ',a[href="#beamerTrigger"]' : (beamer_config.selector = 'a[href="#beamerTrigger"]', Beamer.noSelector = !0);
                    Beamer.appendStyles();
                    Beamer.appendAlert();
                    Beamer.appendFeedbackButtons();
                    Beamer.isInApp() && Beamer.appendPopperScript();
                    try {
                        var f = Beamer.getCookie(_BEAMER_FIRST_VISIT + "_" + beamer_config.product_id);
                        if ("undefined" === typeof f || null === f || "" === f) f = (new Date).toISOString();
                        Beamer.setCookie(_BEAMER_FIRST_VISIT + "_" + beamer_config.product_id, f, 300)
                    } catch (h) { }
                }
                if ("undefined" !== typeof e.enableNPS && e.enableNPS) {
                    g = Beamer.getCookie(_BEAMER_USER_ID + "_" + beamer_config.product_id);
                    if (null === g || "" === g) g = Beamer.uuidv4();
                    Beamer.setCookie(_BEAMER_USER_ID + "_" + beamer_config.product_id, g, 300);
                    "undefined" !== typeof e.showNPSDelay && (beamer_config.nps_delay = e.showNPSDelay);
                    Beamer.shouldShowNPS(e.npsShowForUrls, e.npsHideForUrls) && Beamer.appendNPSScript()
                }
                "undefined" !== typeof e.enableHiddenTooltipObserver && !0 === e.enableHiddenTooltipObserver && (Beamer.enableHiddenTooltipObserver = !0);
                "undefined" !== typeof e.enableTooltipAutoFlipControl && !0 === e.enableTooltipAutoFlipControl && (Beamer.enableTooltipAutoFlipControl = !0)
            },
            c = encodeURIComponent(window.location.host);
        c = _BEAMER_URL_BACK + "initialize?product=" + beamer_config.product_id + "&domain=" + c;
        if (beamer_config.language) c += "&language=" + encodeURIComponent(beamer_config.language);
        else {
            var d = window.navigator.userLanguage || window.navigator.language;
            d && 1 < d.length && (d = d.substring(0, 2).toUpperCase(), c += "&language=" + encodeURIComponent(d))
        }
        if ("undefined" !== typeof beamer_config.user_id && "" !== beamer_config.user_id || "undefined" !== typeof beamer_config.user_email && "" !== beamer_config.user_email) c += "&uid=true";
        Beamer.ajax(c,
            function (e) {
                try {
                    e = JSON.parse(e);
                    "undefined" !== typeof e.logs && (beamer_config.logs = e.logs);
                    if ("undefined" === typeof beamer_config.logs || beamer_config.logs)
                        if ("undefined" === typeof Beamer.initializationLogged || !Beamer.initializationLogged) {
                            try {
                                console.log("Initializing Beamer. [Update and engage users effortlessly - https://getbeamer.com]")
                            } catch (g) { }
                            Beamer.initializationLogged = !0
                        }
                    "undefined" !== typeof e.blockedUrls || "undefined" !== typeof e.allowedUrls ? ("undefined" !== typeof Beamer.config && Beamer.config ||
                        (Beamer.config = {}), "undefined" !== typeof e.blockedUrls && (Beamer.config.blockedUrls = e.blockedUrls), "undefined" !== typeof e.allowedUrls && (Beamer.config.allowedUrls = e.allowedUrls), Beamer.checkUrlAllowed(e).then(function () {
                            Beamer.enabled = !0;
                            b(e)
                        }, function () {
                            Beamer.enabled = !1;
                            "undefined" !== typeof e.listenUrlChanges && e.listenUrlChanges && (Beamer.started = !1)
                        }).catch(function (g) {
                            Beamer.enabled = !1;
                            "undefined" !== typeof e.listenUrlChanges && e.listenUrlChanges && (Beamer.started = !1);
                            Beamer.logError(g)
                        })) : (Beamer.enabled = !0, b(e));
                    "undefined" !== typeof e.listenUrlChanges && e.listenUrlChanges && Beamer.initUrlObserver()
                } catch (g) {
                    Beamer.logError(g)
                }
            }, a)
    }
};

Beamer.appendFeedbackButtons = function () {
    if ("undefined" !== typeof Beamer.config.ideasEnabled && Beamer.config.ideasEnabled && "undefined" !== typeof beamer_config.feedback_button && beamer_config.feedback_button) {
        var a = function () {
            _BEAMER_CSS_LOADED ? Beamer.appendDefaultIdeasButton() : setTimeout(a, 200)
        };
        a()
    }
    Beamer.bindFeedbackButtons()
};
Beamer.appendDefaultIdeasButton = function () {
    if (!(0 < Beamer.findElements(".beamer_ideasFormButton").length)) {
        var a = "undefined" !== typeof beamer_config.feedback_button_position ? beamer_config.feedback_button_position : "right",
            b = "undefined" !== typeof Beamer.config.feedbackButtonText ? Beamer.config.feedbackButtonText : "Feedback",
            c = "";
        if ("undefined" !== typeof Beamer.config.feedbackButtonColor || "undefined" !== typeof Beamer.config.feedbackButtonTextColor) c = ' style="', "undefined" !== typeof Beamer.config.feedbackButtonColor &&
            (c += "background-color: " + Beamer.escapeHtml(Beamer.config.feedbackButtonColor) + ";"), "undefined" !== typeof Beamer.config.feedbackButtonTextColor && (c += "color: " + Beamer.escapeHtml(Beamer.config.feedbackButtonTextColor) + ";"), c += '"';
        a = '<div class="beamer_ideasFormButton"' + c + ' data-position="' + Beamer.escapeHtml(a) + '" tabindex="0" role="button"><span>' + Beamer.escapeHtml(b) + "</span></div>";
        Beamer.appendHtml(document.body, a);
        Beamer.addClick(".beamer_ideasFormButton", function (d) {
            Beamer.showIdeas(!0, d)
        })
    }
};