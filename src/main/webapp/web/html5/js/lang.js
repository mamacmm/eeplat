(function($,w,doc){
	var _sLang = 'zh';
	var _oContent={
			error:{zh:'错误',en:'Error'}
			,applications:{zh:'全部应用',en:'Applications'}
			,loading:{zh:'努力加载中...',en:'Loading...'}
		};
	w.lang = {
		get:_get
		,setLang:_setLang
		,getLang:_getLang
	};
	function _get(a){
		if(_oContent[a] && _oContent[a][_sLang])return _oContent[a][_sLang];
		else return a;
	}
	function _setLang(a){
		if(a)_sLang = a;
		else _sLang = 'zh';
	}
	function _getLang(a){
		return _sLang;
	}
})(jQuery,window,document);