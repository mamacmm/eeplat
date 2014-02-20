(function($,w,doc,his){
	/*****************************全局变量*****************************************/
	var _bIsDebug = true;	//控制_log，是否输出内容
	var _dialogs=[];	//存储所有代开的Page页,pushdialog 和 popdialog 函数 使用
	var _nPageMinWidth=320;	//Page页最小宽度
	var _nPageMinHeight=460;	//Page页最小高度
	var _isloading=false;
	var _isReady = true;
	
	/***************************功能函数**************************************************/
	/**
	 * 弹出一个Page
	 * @param sid 弹出Page的Id
	 * @param b 为弹出Page传递的参数
	 * */
	function _pushdialog(sid,b){
		var mjq = $('#' + sid);
		if(mjq.get(0)){
			mjq.dialogshow();
			mjq.trigger('dialogshow',[b]);
			_dialogs.push(sid);
			history.pushState({id:sid},"","#"+sid);
		}else{
			loadPml({
				pml:sid
			});
		}
	}
	/**
	 * 
	 * */
	function _popevent(){
		history.back();
	}
	/**
	 * 关闭最新打开的Page页
	 * */
	function _popdialog(isevent){
		if(_dialogs.length>0){
			console.log('_dialogs :: length :: ' + _dialogs.length);
			var sid=_dialogs.pop();
			$('#'+sid).dialoghide();
			console.log('_dialogs :: pop :: ' + sid);
		}
	}
	/**************************监控函数************************************/
	/**
	 * 监控函数-浏览器窗口改变宽高
	 * */
	function _docResize(e){
		var iwid=$(w).width();
		var ihei=$(w).height();
		_log('重新调整页面大小!','width::'+iwid,'height::'+ihei);
		$('.html5-page').each(function(){
			$(this).height(ihei > _nPageMinHeight? ihei : _nPageMinHeight);
			$(this).width(iwid > _nPageMinWidth? iwid : _nPageMinWidth);
		});
		$('.html5-content').each(function(){

		});

		$('.html5-middle').each(function(){
			var iThisHeight = $(this).height();
			$(this).children().each(function(){
				var iThisHeight2 = $(this).height();
				var iMiddleLen = (iThisHeight - iThisHeight2)/2;
				var strPx = iMiddleLen + 'px';
				$(this).css({marginTop:strPx,marginBottom:strPx});
			});
		});
	}
	/**
	 * 监控函数-页面加载完成
	 * */
	function _docReady(e){
		_docResize(e);
		if(location.hash){
			var sid = location.hash.replace('#',"");
			_pushdialog(sid);
		}
	}
	/**
	 * 监控函数-浏览器回退
	 * */
	function _popstatehandle(e,a){
		if(!_isReady){
			_popdialog(true);
			if(_dialogs.length>0){
				var strId = _dialogs[_dialogs.length-1];
				location.hash = strId;
			}else{
				location.hash = "";
			}
		}
		_isReady = false;
		myjs.stopevent(e);
	}
	/**
	 * 监控函数-windows 错误
	 * */
	function _addWinErrorHandle(){
		var sErr="";
		function __winerrorhandle(a,b,c){
			sErr = lang.get('error')
				+ ':\n'
				+ a
				+ '\n\n'
				+ ':\n'
				+ b
				+ '\n\n'
				+':\n'
				+ c;
			_log(sErr);
			_loading("<a href='#' >发生错误,点击返回</a>");
		}
		w.onerror = __winerrorhandle;
	}
	/**
	 * 监控函数-监控poppanehide
	 * */
	function _poppanehidehandle(){
		$('.html5-poppane').hide();
	}
	/**
	 * 监控函数-监控滚动事件，模拟手机应用的滚动效果
	 * */
	function _scrollevt(e){
		var self = this;
		var scrolltop = self.scrollTop;
		var scrollheight = self.scrollHeight;
		var clientheight = self.clientHeight;
		var scrolllength = (clientheight/scrollheight)*clientheight;
		var toplen = (scrolltop/scrollheight) * clientheight + scrolltop;
		var jdivscrolbar;
		jdivscrolbar = $(self).find('.html5-scrollbar');
		if(jdivscrolbar.size() == 0){
			var divsrollbar = _scrollbar({height:scrolllength});
			$(self).append(divsrollbar);
			jdivscrolbar = divsrollbar;
		}
		jdivscrolbar.height(scrolllength);
		jdivscrolbar.stop(true,true);
		jdivscrolbar.show();
		if((toplen + scrolllength) > scrollheight){
			toplen = scrollheight - toplen;
		}
		jdivscrolbar.css('top',toplen + 'px');
		jdivscrolbar.fadeOut("slow");
	}
	/*************************Jquery 插件*************************/
	function _addJqueryPlugin(){
		$.fn.extend({
			dialogshow:_dialogshow	//显示页面,根据不同的class实现不同的效果
			,dialoghide:_dialoghide	//关闭页面,根据不同的class实现不同的效果
			,scrollhandle:_scrollhandle	//绑定滚动事件，用于模拟手机应用的滚动效果
			,toStrHtml:_toStrHtml	//返回jqeruy对象的html字符串
			,loadDefault:_loadDefault	//封装load函数
			,chartResize:_chartResize
		});
		$.extend({
			menulist:_menulist //UI-菜单列表
		});
		function _chartResize(a){
			if(a &&　a.resize){
				
			}
		}
		/**
		 * Jquery插件-封装load函数
		 * */
		function _loadDefault(a,b,c,resize){
			_loading();
			this.load(a,b,function(ret){
				if(resize){
					_docResize();
				}
				if(myjs.isfun(c)){
					c(ret);
				}
				_close();
			});
		}
		/**
		 * Jquery插件-输出html字符串
		 * */
		function _toStrHtml(){
			return myjs.jq('div').html(this).html();
		}
		/**
		 * Jquery插件-UI-菜单列表
		 * */
		function _menulist(o,p){
			var op = {
				size:3
			};
			$.extend(op,p);
			var isdown = false;
			function _menuclick(e){
				var self = $(this);
				var menudata = self.data('menudata');
				var pml = menudata.linkpage
				loadPml({
					pml:pml
				});
			}
			function _menumousedown(e){
				var self = $(this);
				var pt = $(this).parent();
				self.stop();
				self.animate({width:'50px',height:'50px'},100);
				isdown =true;
			}
			function _menumouseup(e){
				var self = $(this);
				var pt = $(this).parent();
				self.stop();
				self.animate({width:'60px',height:'60px'},100);
				isdown=false;
			}
			function _menumouseout(e){
				if(isdown){
					var self = $(this);
					var pt = $(this).parent();
					self.stop();
					self.animate({width:'60px',height:'60px'},100);
					isdown=false;
				}
			}
			var jq1 = myjs.jq('div',{'class':'html5-menulist'});
			for(var i=0,len=o.length;i<len;i++){
				var onemenu = o[i];
				var jqitem1 = myjs.jq('div',{'class':'html5-menulist-item'});
				var jqitem2 = myjs.jq('div',{'class':'html5-menulist-item-icon html5-menulist-item-icon-' + onemenu.icon});
				var jqitem3 = myjs.jq('div',{'class':'html5-menulist-item-name',html:onemenu.name});
				jqitem2.data('menudata',onemenu);
				jqitem2.bind('click',_menuclick);
				jqitem2.bind('mousedown',_menumousedown);
				jqitem2.bind('mouseup',_menumouseup);
				jqitem2.bind('mouseout',_menumouseout);
				jqitem2.bind('touchstart',_menumousedown);
				jqitem2.bind('touchend',_menumouseup);
				jqitem1.append(jqitem2).append(jqitem3);
				jq1.append(jqitem1);
			}
			return jq1;
		}
		/**
		 * Jquery插件-页面显示
		 * */
		function _dialogshow(){
			if(this.hasClass('pane')){
				
			}else if(this.hasClass('popup')){
				
			}else if(this.hasClass('html5-page')){
				this.fadeOut(0,function(){
					$(this).fadeIn('slow');
				});
			}else{
				this.show();
			}
		}
		/**
		 * Jquery插件-页面关闭
		 * */
		function _dialoghide(){
			if(this.hasClass('pane')){
				this.panehide();
			}else if(this.hasClass('popup')){
				
			}else if(this.hasClass('html5-page')){
				this.fadeOut('slow',function(){
					$(this).remove();
				});
			}else{
				this.hide();
			}
		}
		
		/**
		 * Jquery插件-绑定滚动事件，用于模拟手机应用的滚动效果
		 * */
		function _scrollhandle(){
			if(_ismobile()){
				this.on('touchmove',_scrollevt);
			}else{
				this.scroll(_scrollevt);
			}
		}
	}
	
	/******************************************************************************************/
	/**
	 * 输出日志,输出全部参数
	 * */
	function _log(){
		if(_bIsDebug && !myjs.isundef(console)){
			for(var i = 0,len = arguments.length;i<len;i++){
				console.log(arguments[i]);
			}
		}else{
			alert(arguments);
		}
	}
	/**
	 * 生成一个滚动条div标签
	 * @return jquery对象
	 * */
	function _scrollbar(o){
		var jdiv1 = myjs.jq('div',{'class':'html5-scrollbar',style:'height:' + _nvl(o.height,50) + 'px;'});
		return jdiv1;
	}
	/**
	 * 删除Less添加的Style，重新加载Less文件
	 * */
	function _changeTheme(a){
		var jlinkless = $('#application_theme');
		var strcsshref = jlinkless.attr('href');
		strcsshref = strcsshref.replace(/html5theme\_[a-z]*\.css$/,'html5theme_' + a + '.css');
		jlinkless.attr('href',strcsshref);
		_removeAllLessStyleAndRefresh();
	}
	/**
	 * 改变Page页的风格
	 * @param a 风格名称 例如：green、gray
	 * */
	function _removeAllLessStyleAndRefresh(){
		$("style[id^='less:']").remove();
		less.refresh();
	}
	/**
	 * 打开加载窗口
	 * @param a 显示的提示文字
	 * */
	function _loading(a,o){
		_isloading =true;
		var _loadingid='html5loadingdiv';
		
		if($('#'+_loadingid).get(0)){
			if(a){
				$('#'+_loadingid).find('.txtct:eq(0)').html(a);
			}
			$('#'+_loadingid).dialogshow();
		}else{
			var jdiv = myjs.jq('div',{id:_loadingid,'class':'html5-loader'});
			var jtb = _getTable(1,1);
			jtb.addClass('vermiddle txtcenter wh100');
			var jloader = myjs.jq('img',{'src':'web/html5/img/default/loading.png','class':'html5-imgload wh40px'});
			var jdiv2 = myjs.jq('div',{'class':'txtcenter colorwhite txtct'});
			if(a){
				jdiv2.html(a);
			}else{
				jdiv2.html("努力加载...");
			}
			jtb.find('td:eq(0)').append(jloader).append(jdiv2);
			jdiv.append(jtb);
			$('body').append(jdiv);
			jdiv.bind('click',function(e){
				myjs.stopevent(e);
				if(o && o.clicknoclose){
					
				}else{
					_close();
				}
			});
		}
	}
	/**
	 * 关闭加载窗口
	 * */
	function _close(){
		_isloading = false;
		var _loadingid='html5loadingdiv';
		$('#'+_loadingid).hide();
	}
	/**
	 * 获得一个表格
	 * @param a 行数
	 * @param b 列数
	 * @return jquery 对象
	 * */
	function _getTable(a,b){
		var table = myjs.jq('table');
		for(var i=0;i<a;i++){
			var tr = myjs.jq('tr');
			table.append(tr);
			for(var j=0;j<b;j++){
				var td = myjs.jq('td');
				tr.append(td);
			}
		}
		return table;
	}
	function _bindEvent(){
		$(document).bind('poppanehide',_poppanehidehandle);
	}
	/************************绑定、执行部分*************************************************/
	w.html5 = {
			resize:_docResize	//调整页面元素的宽高
			,pushdialog:_pushdialog	//弹出一个Page
			,popdialog:_popevent	//关闭最新打开的Page页
			,changeTheme:_changeTheme	//改变Page页的风格
			,popindex:99	//用于设置打开Page页的z-index 使其保持在最上层
			,loading:_loading	//加载页面
			,close:_close	//关闭加载页面
	};
	_bindEvent();
	myjs.bindswipe($(doc));
	_addWinErrorHandle();//添加错误绑定函数
	_addJqueryPlugin();//执行添加Jquery插件函数
	w.onpopstate = _popstatehandle;	//监控浏览器回退事件,html5 新特性，用于模拟手机应用的返回功能
	$(w).resize(_docResize);	//监控浏览器改变窗口大小事件
	$(doc).ready(_docReady);	//监控文档加载成功事件
	
})(jQuery,window,document,history);