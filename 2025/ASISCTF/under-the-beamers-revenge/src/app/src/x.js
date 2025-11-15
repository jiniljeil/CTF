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