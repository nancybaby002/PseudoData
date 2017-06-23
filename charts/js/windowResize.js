window.global = window;
(function () {
    // main config for this page
    //var appConfig = {
    //    base: '/static/',
    //    middleware: true,
    //    debug: true
    //};
    // semantic 需要
    Cube.register('jquery', $);
    window.share = {
        //datacenter: 'https://dc.datav.aliyun.com/',
        ratioX: null,
        ratioY: null,
        event: new EventEmitter(),
        screen: {}
    }

    //Cube.init({
    //    base: appConfig.base,
    //    debug: appConfig.debug,
    //    version: "0_1_8_3",
    //    enableCss: true,
    //    strict: false,
    //    remoteBase: {
    //        'datav': '//resource.datav.aliyun.com/cube/',
    //        'static': '//cdn-service.datav.aliyun.com/static/'
    //    },
    //    timeout: 60000
    //});
    //Cube.use('/screens/83b1633bfced939b45d9456b19edb12f/config.js', function (cfg) {
    //    Cube.use('/screens/83b1633bfced939b45d9456b19edb12f/hook.js', function (hook) {
    //        Cube.use('static:/screen.js', function (Screen) {
    //            window.screenAlias = '83b1633bfced939b45d9456b19edb12f'
    //            window.screen = cfg.config || {};
    //            window.screen.width && $('body').css('width', window.screen.width);
    //            window.screen.height && $('body').css('height', window.screen.height);
    //            window.share.screen.comList = cfg.config.comList
    //            if (!window.share.screen.comList) {
    //                window.share.screen.comList = getComList(cfg.scenes[0].layers[0].coms);
    //            }
    //            $('head').append('<meta name="viewport" content="width=' + window.screen.width + '"/>');
    //            Screen.run('83b1633bfced939b45d9456b19edb12f', cfg, hook);
    //        });
    //    });
    //});
    $(function(){
        resize();
    })
    $(window, document).resize(function () {
        resize();
    }).load(function () {
        $('.datavLayout').css('visibility', 'visible');
        resize();
        $('#datavLoading').fadeOut();
    });
    function resize() {
        if (window.screen.display == 2) {
            resizeCenter();
        } else if (window.screen.display == 3) {
            resizeFull();
        } else {
            resizeWidth();
        }
        window.share.event.emit('ratio-change', {
            ratioX: window.share.ratioX,
            ratioY: window.share.ratioY
        })
    }

    function resizeWidth() {
        var ratio1 = window.share.ratioX = window.share.ratioY = $(window).height() / (1080 || $('body').height());
        var ratio2 = window.share.ratioX = window.share.ratioY = $(window).width() / (1920|| $('body').width());
        $('.main-content').css({
            transform: "scale(" + ratio2 + ")",
            transformOrigin: "left top",
            backgroundSize: "100%"
        });
        //if(ratio1>ratio2){
        //    $('body').css({
        //        transform: "scale(" + ratio2 + ")",
        //        transformOrigin: "left top",
        //        backgroundSize: "100%"
        //    });
        //}else{
        //    $('body').css({
        //        transform: "scale(" + ratio1 + ")",
        //        transformOrigin: "left top",
        //        backgroundSize: "100%"
        //    });
        //}

    }

    function resizeCenter() {
        if (!window.screen.height || !window.screen.width) return resizeCenterBak();
        var ratio = window.share.ratioX = window.share.ratioY = $(window).height() / window.screen.height;
        $('.main-content').css({
            transform: "scale(" + ratio + ")",
            transformOrigin: "left top",
            backgroundSize: 100 * (window.screen.width / $(window).width() * ratio) + "%" + ' 100%',
            backgroundPosition: ($(window).width() - $('body').width() * ratio) / 2 + "px top",
            marginLeft: ($(window).width() - $('body').width() * ratio) / 2
        });
    }

    function resizeFull() {
        if (!window.screen.height || !window.screen.width) return resizeFullBak();
        var ratioX = window.share.ratioX = $(window).width() / window.screen.width;
        var ratioY = window.share.ratioY = $(window).height() / window.screen.height;
        $('.main-content').css({
            transform: "scale(" + ratioX + ", " + ratioY + ")",
            transformOrigin: "left top",
            backgroundSize: "100% 100%",
        });
    }

    function resizeCenterBak() {
        var ratioX = $(window).width() / $('body').width();
        var ratioY = $(window).height() / $('body').height();
        var ratio = window.share.ratioX = window.share.ratioY = Math.min(ratioX, ratioY);
        $('.main-content').css({
            transform: "scale(" + ratio + ")",
            transformOrigin: "left top",
            backgroundSize: (1 / ratioX) * 100 * ratio + "%",
            backgroundPosition: ($(window).width() - $('body').width() * ratio) / 2 + "px top",
            marginLeft: ($(window).width() - $('body').width() * ratio) / 2
        });
    }

    function resizeFullBak() {
        var ratioX = window.share.ratioX = $(window).width() / $('body').width();
        var ratioY = window.share.ratioY = $(window).height() / $('body').height();
        $('.main-content').css({
            transform: "scale(" + ratioX + ", " + ratioY + ")",
            transformOrigin: "left top",
            backgroundSize: "100% " + ratioY * 100 + "%",
        });
    }

    function getComList(coms) {
        var result = [];
        var comList = {};
        coms.forEach(function (com, index) {
            if (com.parent) return;
            var idx = com.attr.zIndex || 1000;
            if (!comList[idx]) comList[idx] = [];
            comList[idx].push(com);
        });
        Object.keys(comList).sort(function (a, b) {
            a = a * 1;
            b = b * 1;
            if (a > b) return 1;
            if (a < b)return -1;
            return 0
        }).forEach(function (idx) {
            comList[idx].forEach(function (com) {
                result.push(com.com_id);
            });
        });
        return result;
    }
})();