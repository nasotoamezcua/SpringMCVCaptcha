(function($) {
  'use strict';
  
  $.fn.captcha = function(settings) {
    
    var element = this;
    
    if (element.length === 0) {
      throw new Error('Captcha html element cound not be found.');
    }

    if (!settings || !settings.captchaEndpoint) {
      throw new Error('The captchaEndpoint setting is required.');
    }
    
    // normalize captcha endpoint path
    settings.captchaEndpoint = settings.captchaEndpoint.replace(/\/+$/g, '');
    
    var styleName = element.data('stylename') ? element.data('stylename') : 'defaultCaptcha';
    
    // get captcha html markup
    function _getHtml() {
      //console.log("_getHtml " + settings.captchaEndpoint);
      return $.ajax({
        method: 'GET',
        url: settings.captchaEndpoint,
        data: {
          get: 'html',
          c: styleName
        }
      });
    };
    
    // ajax validate captcha on blur event and trigging the 
    // custom 'validatecaptcha' event to fire the validation result
    function _registerUserInputBlurValidation() {
      var instance = _getInstance();
      if (!instance) { return; }
      
      $('#' + instance.options.userInputID).on('blur', function() {
        var captchaCode = $.trim($(this).val());
        if (captchaCode.length === 0) { return; }

        var self = this;
        //console.log("_registerUserInputBlurValidation  " + instance.validationUrl);
        $.ajax({
          method: 'GET',
          url: instance.validationUrl,
          data: {
            i: captchaCode
          },
          success: function (isCorrect) {
            if (!isCorrect) {
              instance.reloadImage();
            }
            $(self).trigger('validatecaptcha', [isCorrect]);
          }
        });
      });
    };
    
    // a custom of $.getScript(), which lets changing the options
    function _getScript(url, options) {
      //console.log("_getScript  " + url);
      options = $.extend({
        dataType: 'script',
        cache: false,
        url: url
      }, options || {});
      return $.ajax(options);
    };
    
    // load botdetect scripts and execute them
    function _loadScriptIncludes() {
      var scriptIncludeUrl = settings.captchaEndpoint + '?get=script-include';
      _getScript(scriptIncludeUrl, { cache: true }).done(function() {
        var captchaId = $('#BDC_VCID_' + styleName).val();
        //console.log(settings.captchaEndpoint + '?get=init-script-include&c=' + styleName + '&t=' + captchaId + '&cs=2');
        var initScriptIncludeUrl = settings.captchaEndpoint + '?get=init-script-include&c=' + styleName + '&t=' + captchaId + '&cs=2';
        
        _getScript(initScriptIncludeUrl).done(_onLoadScriptsSuccess);
      });
    };
    
    // use user input blur validation if the input element has data-correct-captcha attribute
    function _useUserInputBlurValidation() {
      var instance = _getInstance();
      if (!instance) { return; }
      return ($('#' + instance.options.userInputID).attr('data-correct-captcha') !== undefined);
    };
    
    // fire the custom event when botdetect scripts are loaded
    function _onLoadScriptsSuccess() {
      if (_useUserInputBlurValidation()) {
        _registerUserInputBlurValidation();
      }
    }
    
    // get botdetect captcha client-side instance
    function _getInstance() {
      return (window.botdetect !== undefined) 
        ? window.botdetect.getInstanceByStyleName(styleName)
        : null;
    };
    
    // display captcha html markup in view
    function _displayHtml() {
      _getHtml(settings.captchaEndpoint, styleName).done(function(captchaHtml) {
        //console.log("...");
        //console.log(captchaHtml);
        //console.log("-------------");
        captchaHtml = captchaHtml.replace(/href="/g, 'href="' + __captcha());
        captchaHtml = captchaHtml.replace(/src="/g, 'src="' + __captcha());
        captchaHtml = captchaHtml.replace(/<a\s.*<\/a>/, '');
        //console.log(captchaHtml);
        element.html(captchaHtml.replace(/<script.*<\/script>/g, ''));
        _loadScriptIncludes();
      });
    }
    
    // plugin initialization - we display the captcha html markup in view
    element.init = function() {
      _displayHtml();
      return element;
    };
    
    // captcha id for validating captcha at server-side
    element.getCaptchaId = function() {
      var instance, id = null;
      if ((instance = _getInstance())) {
        return instance.captchaId;
      }
      return id;
    };
    
    // reload new captcha image
    element.reloadImage = function() {
      var instance = _getInstance();
      if (instance) {
        instance.reloadImage();
      }
    };

    return element.init();
  };
  
}(jQuery));
