(function(w, d, $) {
	if (w.myjs) {
		alert('myjs is inited');
		return;
	}
	w.myjs = {
		str : {
			concat : _concat,	//连接字符串
			copy : _copy	//复制字符串
		},
		date : {
			getNowMontLong : _getNowMontLong, 	//获取当月总毫秒数
			format : _format,		//格式化日期
			parsestr : _parsestr,	//字符串转日期对应的毫秒数
			getnow : _getnow,		//获取当前时间
			getnl : _getnl		//获取一个月的农历
			,getlrmonth:_getlrmonth 	//获取时间的左右月份 例如 2013-01-05 13:33:00 返回 ['2013-01-01 00:00:00','2013-02-01 00:00:00']
		},
		jsonkeys : _jsonkeys,	//返回json对象中的key
		json : _json,		//ajax请求返回json对象
		ajax : _ajax,		//ajax请求
		getxhr : _getxhr,		//获取 XMLHttpRequest 对象
		isstrjson : _isstrjson,		//是否json格式的字符串
		isarr : _isarr,		//是否数组
		isbool : _isbool,	//是否布尔
		isstr : _isstr,		//是否字符串
		isfun : _isfun,		//是否为函数
		isundef : _isundef,		//是否未定义
		isobj : _isobj,		//是否为对象
		istype : _istype,	//判断类型
		isnum : _isnum,		//是否是数字
		type : _type,		//返回类型
		strjson : _strjson,		//字符串 转化为 json
		jsonstr : _jsonstr,		//json 转化为 字符串
		showobj : _showobj,		//显示属性
		nvl : _nvl,		//判断是否为空
		jq : _jq,	//生成juqery对象
		multiTask : _multiTask,	//监听任务
		otherTask : _otherTask	//多任务
		,setCookie:_setCookie	//设置Cookie
		,getCookie:_getCookie	//获取Cookie
		,stopevent:_stopevent	//停止事件传播
		,ismobile:_ismobile		//判断是否为手机系统
		,bindswipe:_bindswipe	//绑定滑动事件
		,poppane:_poppane
	};
	function _stopevent(e){
		e.preventDefault();
		e.stopPropagation();
	}
	/**
	 * n 执行时时间 单位秒 o 超时时间 单位秒 s 每次执行间隔 单位毫秒 a 判断是否执行的函数 返回 true or false b
	 * 如果a函数返回true 则执行 b  c 超时时执行的函数
	 * 
	 */
	function _multiTask(n, o, s, a, b, c) {
		var second = 1 * 1000;
		var nowtime = new Date();
		nowtime = nowtime.getTime() / 1000;
		if (s) {
			second = s;
		}
		setTimeout(function() {
			if (_isfun(a)) {
				if (a()) {
					if (_isfun(b)) {
						b();
					}
				} else {
					if ((nowtime - n) > o) {
						if (_isfun(c)) {
							c();
						}
					} else {
						_multiTask(n, o, s, a, b, c);
					}
				}
			}
		}, second);
	}
	function _otherTask(callback, ms) {
		var second = 10;
		var nowtime = new Date();
		nowtime = nowtime.getTime() / 1000;
		if (ms) {
			second = ms;
		}
		_multiTask(nowtime, 10, second, function() {
			return true;
		}, callback, function() {
		});
	}
	function _docid(a) {
		return document.getElementById(a);
	}
	function _doctag(a) {
		return document.getElementsByTagName(a);
	}
	// 创建jquery 页面元素对象
	function _jq(a, b) {
		var obj = $(document.createElement(a));
		if (b) {
			for ( var lstr1 in b) {
				var mmstr1 = lstr1;
				var mmobj1 = b[lstr1];
				if (mmobj1) {
					if (mmstr1 == 'html') {
						obj.html(mmobj1);
					} else if (mmstr1 == 'append') {
						obj.append(mmobj1);
					} else {
						obj.attr(mmstr1, mmobj1);
					}
				}
			}
		}
		return obj;
	}
	function _log(a) {
		if (console && _isfun(console.log)) {
			console.log(a);
		}
	}
	function _nvl(a, b) {
		if (a) {
			return a;
		} else {
			return b;
		}
	}
	function _poppane(a, b) {
		var nw = 400;
		var nh = 300;

		var nwid = $(window).width();
		var nhei = $(window).height();

		var ntop = nhei > nh ? ((nhei - nh) / 2) : 0;
		var nleft = nwid > nw ? ((nwid - nw) / 2) : 0;

		var jdiv = _jq(
				'div',
				{
					style : 'border:1px solid gray;border-radius:5px;box-shadow: 0 1px 3px rgba(0,0,0,0.8);padding:10px;line-height:20px;text-align:left;position:fixed;width:'
							+ nw
							+ 'px;height:'
							+ nh
							+ 'px;overflow:auto;background-color:white;color:black;'
							+ 'top:' + ntop + 'px;left:' + nleft + 'px;'
				});
		if (b) {
			jdiv.addClass(_nvl(b.cls, ''));
		}

		if (_doctag('body').length == 0) {
			$(document).ready(function() {
				$('body').append(jdiv);
			});
		} else {
			$('body').append(jdiv);
		}
		jdiv.click(function() {
			$(this).remove();
		});
		jdiv.html(a);
	}
	// 查看对象中存在的属性
	function _showobj(a) {
		if (a) {
			var mstr1 = "";
			for ( var lstr1 in a) {
				var val = a[lstr1];
				_log('key :: ' + lstr1);
				_log('value type:: ' + _type(val));

				mstr1 += 'key :: ' + lstr1 + '<br />';
				mstr1 += 'key :: ' + 'value type:: ' + _type(val) + '<br />';

				if (_isstr(val) || _isnum(val)) {
					_log('value :: ' + val);
					mstr1 += 'value :: ' + val + '<br />';
				}
			}
			_poppane(mstr1);
		}
	}
	// json 和 str 的互相转换
	function _jsonstr(a) {
		if (JSON && _isobj(a)) {
			return JSON.stringify(a);
		} else {
			return a;
		}
	}
	// json 和 str 的互相转换
	function _strjson(a) {
		if (JSON && _isstrjson(a)) {
			try {
				return JSON.parse(a);
			} catch (e) {
				var ret = eval(a);
				return ret;
			}
		} else {
			return a;
		}
	}
	// 类型判断
	function _type(a) {
		return (typeof a).toLowerCase();
	}
	function _istype(a, b) {
		return (_type(a) == b.toLowerCase());
	}
	function _isobj(a) {
		return _istype(a, 'object');
	}
	function _isundef(a) {
		return _istype(a, 'undefined');
	}
	function _isfun(a) {
		return _istype(a, 'function');
	}
	function _isstr(a) {
		return _istype(a, 'string');
	}
	function _isbool(a) {
		return _istype(a, 'boolean');
	}
	function _isnum(a) {
		return _istype(a, 'number');
	}
	function _isarr(a) {
		if (_istype(a, 'object') && (a.length || a.length == 0)
				&& a.length >= 0) {
			return true;
		} else {
			return false;
		}
	}
	function _isstrjson(a) {
		if (_isstr(a)) {
			var regexp1 = /^[ \t\n\r]*[\[]/;
			var regexp11 = /^[ \t\n\r]*[\{]/;
			var regexp2 = /[\]][ \t\n\r]*$/;
			var regexp21 = /[\}][ \t\n\r]*$/;
			if ((regexp1.test(a) && regexp2.test(a))
					|| (regexp11.test(a) && regexp21.test(a)))
				return true;
		}
		return false;
	}
	// 获取XMLHttpRequest对象
	function _getxhr(o) {
		/*
		 * 0 Uninitialized 初始化状态。XMLHttpRequest 对象已创建或已被 abort() 方法重置。 1 Open
		 * open() 方法已调用，但是 send() 方法未调用。请求还没有被发送。 2 Sent Send() 方法已调用，HTTP
		 * 请求已发送到 Web 服务器。未接收到响应。 3 Receiving 所有响应头部都已经接收到。响应体开始接收但未完成。 4 Loaded
		 * HTTP 响应已经完全接收。
		 * 
		 * 属性: readyState responseText responseXML status statusText 方法: abort()
		 * getAllResponseHeaders() getResponseHeader() open(method, url, async,
		 * username, password) send(body) setRequestHeader(name, value) 事件:
		 * onreadystatechange
		 * 
		 */
		var xhr;
		try {
			// Firefox, Opera 8.0+, Safari
			xhr = new XMLHttpRequest();
		} catch (e) {
			// Internet Explorer
			try {
				xhr = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					xhr = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {
					return null;
				}
			}
		}
		return xhr;
	}
	function _ajax(o) {
		if (!_isobj(o) || _isundef(o.url))
			return false;
		var xhr = _getxhr();
		if (!xhr)
			return false;
		var mo = {
			method : o.method ? o.method : 'get',
			url : o.url,
			async : o.async ? true : false,
			callback : o.callback,
			paras : o.paras ? o.paras : null
		};
		var isfun = _isfun(mo.callback);
		xhr.onreadystatechange = function() {
			if (isfun) {
				mo.callback(xhr, mo);
			}
		};
		xhr.open(mo.method, mo.url, mo.async);
		xhr.send(o.paras);
		if (!mo.async) {
			return xhr;
		}
	}
	function _json(o) {
		var mo = {
			method : o.method ? o.method : 'get',
			url : o.url,
			async : false
		};
		var xhr = _ajax(o);
		return _strjson(xhr.responseText);
	}
	function _jsonkeys(a) {
		var arr1 = [];
		for ( var name in a) {
			if (a[name]) {
				arr1.push(name);
			}
		}
		return arr1;
	}
	function _format(a, b) {
		if (!a) {
			return '00-00';
		}
		var type = _type(a);
		var date;
		if (type === 'string') {
			date = _parsestr(a);
			var ma = new Date();
			ma.setTime(date);
			date = ma;
		} else if (type === 'object' && a.getFullYear) {
			date = a;
		} else if (type === 'number') {
			var d1 = new Date();
			d1.setTime(a);
			date = d1;
		} else {
			return a;
		}
		if (b == 'int') {
			return date.getTime();
		}
		var myear = date.getFullYear();
		var mmoth = (date.getMonth() + 1);
		var mday = date.getDate();
		var mhours = date.getHours();
		var mminutes = date.getMinutes();
		var mseconds = date.getSeconds();
		var mweek = (date.getDay() + 1);

		var mfmoth = mmoth > 9 ? mmoth : '0' + mmoth;
		var mfday = mday > 9 ? mday : '0' + mday;
		var mfhours = mhours > 9 ? mhours : '0' + mhours;
		var mfminutes = mminutes > 9 ? mminutes : '0' + mminutes;
		var mfseconds = mseconds > 9 ? mseconds : '0' + mseconds;
		if (b == 'short') {
			return mfhours + ':' + mfminutes;
		} else if (b == 'normal') {
			return mfmoth + '-' + mfday + ' ' + mfhours + ':' + mfminutes;
		} else if (b == 'long') {
			return myear + '-' + mfmoth + '-' + mfday + ' ' + mfhours + ':'
					+ mfminutes + ':' + mfseconds + '.0';
		} else if (b == 'longno0') {
			return myear + '-' + mfmoth + '-' + mfday + ' ' + mfhours + ':'
					+ mfminutes + ':' + mfseconds;
		} else if (b == 'long2') {
			return myear + '-' + mfmoth + '-' + mfday + ' ' + mfhours + ':'
					+ mfminutes;
		} else if (b == 'datelong') {
			return myear + '-' + mfmoth + '-' + mfday + ' 00:00:00.0';
		} else if (b == 'datelongnotime' || b == 'date') {
			return myear + '-' + mfmoth + '-' + mfday;
		} else if (b == 'datelongnotimeweek') {
			return myear + '-' + mfmoth + '-' + mfday + ' 星期'
					+ _getweekzh(mweek);
		} else if (b == 'datenormal') {
			return mfmoth + '-' + mfday;
		} else if (b == 'yearmonth') {
			return myear + '-' + mfmoth;
		} else if (b == 'yearmonthzh') {
			return myear + ' ' + mfmoth + '月';
		} else {
			return a;
		}

	}
	function _parsestr(a) {
		var ma = Date.parse(a);
		if (!ma) {
			ma = Date.parse(a.replace(/[-]/g, '/').replace('.0', ''));
		}
		return ma;
	}
	function _getnow(a) {
		var date = new Date();
		return _format(date, a);
	}
	function _getNowMontLong(a) {
		var d1 = new Date();
		d1.setTime(a);
		var year = d1.getFullYear();
		var month = (d1.getMonth() + 1);
		var days = {
			'1' : 31,
			'2' : year % 4 == 0 ? 29 : 28,
			'3' : 31,
			'4' : 30,
			'5' : 31,
			'6' : 30,
			'7' : 31,
			'8' : 31,
			'9' : 30,
			'10' : 31,
			'11' : 30,
			'12' : 31
		};
		return days[month] * 24 * 60 * 60 * 1000;
	}
	function _concat() {
		var paras = arguments ? arguments : [];
		var length = paras.length;
		var ret = '';
		for ( var i = 0; i < length; i++) {
			ret += paras[i];
		}
		return ret;
	}
	function _copy(a, b) {
		var ret = '';
		for ( var i = 0; i < b; i++) {
			ret += a;
		}
		return ret;
	}
	function _getweekzh(n) {
		var strs = [ '日', '一', '二', '三', '四', '五', '六' ];
		return strs[n - 1];
	}
	function _getnl(thedate) {
		var lunarInfo=new Array(
				0x04bd8,0x04ae0,0x0a570,0x054d5,0x0d260,0x0d950,0x16554,0x056a0,0x09ad0,0x055d2,
				0x04ae0,0x0a5b6,0x0a4d0,0x0d250,0x1d255,0x0b540,0x0d6a0,0x0ada2,0x095b0,0x14977,
				0x04970,0x0a4b0,0x0b4b5,0x06a50,0x06d40,0x1ab54,0x02b60,0x09570,0x052f2,0x04970,
				0x06566,0x0d4a0,0x0ea50,0x06e95,0x05ad0,0x02b60,0x186e3,0x092e0,0x1c8d7,0x0c950,
				0x0d4a0,0x1d8a6,0x0b550,0x056a0,0x1a5b4,0x025d0,0x092d0,0x0d2b2,0x0a950,0x0b557,
				0x06ca0,0x0b550,0x15355,0x04da0,0x0a5d0,0x14573,0x052d0,0x0a9a8,0x0e950,0x06aa0,
				0x0aea6,0x0ab50,0x04b60,0x0aae4,0x0a570,0x05260,0x0f263,0x0d950,0x05b57,0x056a0,
				0x096d0,0x04dd5,0x04ad0,0x0a4d0,0x0d4d4,0x0d250,0x0d558,0x0b540,0x0b5a0,0x195a6,
				0x095b0,0x049b0,0x0a974,0x0a4b0,0x0b27a,0x06a50,0x06d40,0x0af46,0x0ab60,0x09570,
				0x04af5,0x04970,0x064b0,0x074a3,0x0ea50,0x06b58,0x055c0,0x0ab60,0x096d5,0x092e0,
				0x0c960,0x0d954,0x0d4a0,0x0da50,0x07552,0x056a0,0x0abb7,0x025d0,0x092d0,0x0cab5,
				0x0a950,0x0b4a0,0x0baa4,0x0ad50,0x055d9,0x04ba0,0x0a5b0,0x15176,0x052b0,0x0a930,
				0x07954,0x06aa0,0x0ad50,0x05b52,0x04b60,0x0a6e6,0x0a4e0,0x0d260,0x0ea65,0x0d530,
				0x05aa0,0x076a3,0x096d0,0x04bd7,0x04ad0,0x0a4d0,0x1d0b6,0x0d250,0x0d520,0x0dd45,
				0x0b5a0,0x056d0,0x055b2,0x049b0,0x0a577,0x0a4b0,0x0aa50,0x1b255,0x06d20,0x0ada0);
		
		var solarMonth=new Array(31,28,31,30,31,30,31,31,30,31,30,31);

		var Gan=new Array("甲","乙","丙","丁","戊","己","庚","辛","壬","癸");

		var Zhi=new Array("子","丑","寅","卯","辰","巳","午","未","申","酉","戌","亥");

		var Animals=new Array("鼠","牛","虎","兔","龙","蛇","马","羊","猴","鸡","狗","猪");

		var solarTerm = new Array("小寒","大寒","立春","雨水","惊蛰","春分","清明","谷雨","立夏"
				,"小满","芒种","夏至","小暑","大暑","立秋","处暑","白露","秋分"
				,"寒露","霜降","立冬","小雪","大雪","冬至");

		var sTermInfo = new Array(0,21208,42467,63836,85337,107014,128867,150921,173149,195551
				,218072,240693,263343,285989,308563,331033,353350,375494
				,397447,419210,440795,462224,483532,504758);
		
		var nStr1 = new Array('日','一','二','三','四','五','六','七','八','九','十');

		var nStr2 = new Array('初','十','廿','卅','　');
		
		var nStr3 = new Array('一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','冬月','腊月');

		var monthName = new Array("JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC");
		
		// 国历节日 *表示放假日

		var sFtv = new Array("0101*元旦","0214 情人节","0308 妇女节","0312 植树节","0315 消费者权益日"
				,"0401 愚人节","0501 劳动节","0504 青年节","0512 护士节","0601 儿童节"
				,"0701 建党节","0801 建军节","0910 教师节","1001*国庆节","1225 圣诞节");
		// 农历节日 *表示放假日

		var lFtv = new Array("0101*春节","0115 元宵节","0505 端午节","0707 七夕情人节"
				,"0715 中元节","0815 中秋节","0909 重阳节","1208 腊八节","1224 小年","0100*除夕");
		// 某月的第几个星期几
		
		var wFtv = new Array("0520 母亲节","0636 Armed Forces Day");
	
		// ====================================== 传回农历 y年的总天数
		function lYearDays(y) {
		   var i, sum = 348;
		   for(i=0x8000; i>0x8; i>>=1) sum += (lunarInfo[y-1900] & i)? 1: 0;
		   return(sum+leapDays(y));
		}
		// ====================================== 传回农历 y年闰月的天数
		function leapDays(y) {
		   if(leapMonth(y))  return((lunarInfo[y-1900] & 0x10000)? 30: 29);
		   else return(0);
		}
		// ====================================== 传回农历 y年闰哪个月 1-12 , 没闰传回 0
		function leapMonth(y) {
		   return(lunarInfo[y-1900] & 0xf);
		}
		// ====================================== 传回农历 y年m月的总天数
		function monthDays(y,m) {
		   return( (lunarInfo[y-1900] & (0x10000>>m))? 30: 29 );
		}
		// ====================================== 算出农历, 传入日期对象, 传回农历日期对象
		// 该物件属性有 .year .month .day .isLeap .yearCyl .dayCyl .monCyl
	
		function Lunar(objDate) {
			var i, leap=0, temp=0;
			var baseDate = new Date(1900,0,31);
			var offset   = (objDate - baseDate)/86400000;
			this.dayCyl = offset + 40;
			this.monCyl = 14;
			for(i=1900; i<2050 && offset>0; i++) {
				temp = lYearDays(i);
				offset -= temp;
				this.monCyl += 12;
			}

			if(offset<0) {
				offset += temp;
				i--;
				this.monCyl -= 12;
			}
			this.year = i;
			this.yearCyl = i-1864;
			leap = leapMonth(i); // 闰哪个月
			this.isLeap = false;
			for(i=1; i<13 && offset>0; i++) {
				// 闰月
				if(leap>0 && i==(leap+1) && this.isLeap==false){ 
					--i; 
					this.isLeap = true; 
					temp = leapDays(this.year); 
				}else { temp = monthDays(this.year, i); }
				// 解除闰月
				if(this.isLeap==true && i==(leap+1)) this.isLeap = false;
				offset -= temp;
				if(this.isLeap == false) this.monCyl ++;
			}
			if(offset==0 && leap>0 && i==leap+1){
				if(this.isLeap) { 
					this.isLeap = false; 
				} else { 
					this.isLeap = true; 
					--i; 
					--this.monCyl;
				}
			}
			if(offset<0){ 
				offset += temp; 
				--i; 
				--this.monCyl; 
			}
			this.month = i;
			this.day = offset + 1;
		}
		// ==============================传回国历 y年某m+1月的天数
		function solarDays(y,m) {
		   if(m==1)
		      return(((y%4 == 0) && (y%100 != 0) || (y%400 == 0))? 29: 28);
		   else
		      return(solarMonth[m]);
		}
		// ============================== 传入 offset 传回干支, 0=甲子
		function cyclical(num) {
		   return(Gan[num%10]+Zhi[num%12]);
		}
		// ============================== 月历属性
		function calElement(sYear,sMonth,sDay,week,lYear,lMonth,lDay,lDayzh,isLeap,cYear,cMonth,cDay) {
		      this.isToday    = false;
		      // 国历
		      this.sYear      = sYear;
		      this.sMonth     = sMonth;
		      this.sDay       = sDay;
		      this.week       = week;
		      // 农历
		      this.lYear      = lYear;
		      this.lMonth     = lMonth;
		      this.lDay       = lDay;
		      this.lDayzh	  = lDayzh;
		      this.isLeap     = isLeap;
		      // 干支
		      this.cYear      = cYear;
		      this.cMonth     = cMonth;
		      this.cDay       = cDay;
		      this.color      = '';
		      this.lunarFestival = ''; // 农历节日
		      this.solarFestival = ''; // 国历节日
		      this.solarTerms    = ''; // 节气
		}
		// ===== 某年的第n个节气为几日(从0小寒起算)
		function sTerm(y,n) {
		   var offDate = new Date( ( 31556925974.7*(y-1900) + sTermInfo[n]*60000  ) + Date.UTC(1900,0,6,2,5) );
		   return(offDate.getUTCDate());
		}
		// ============================== 传回月历物件 (y年,m+1月)

		function calendar(y,m) {
		   var sDObj, lDObj, lY, lM, lD=1, lL, lX=0, tmp1, tmp2;
		   var lDPOS = new Array(3);
		   var n = 0;
		   var firstLM = 0;
		   sDObj = new Date(y,m,1);            // 当月一日日期
		   this.length    = solarDays(y,m);    // 国历当月天数
		   this.firstWeek = sDObj.getDay();    // 国历当月1日星期几
		   for(var i=0;i<this.length;i++) {
		      if(lD>lX) {
		         sDObj = new Date(y,m,i+1);    // 当月一日日期
		         lDObj = new Lunar(sDObj);     // 农历
		         lY    = lDObj.year;           // 农历年
		         lM    = lDObj.month;          // 农历月
		         lD    = lDObj.day;            // 农历日
		         lL    = lDObj.isLeap;         // 农历是否闰月
		         lX    = lL? leapDays(lY): monthDays(lY,lM); // 农历当月最後一天
		         if(n==0) firstLM = lM;
		         lDPOS[n++] = i-lD+1;
		      }
		      // sYear,sMonth,sDay,week,
		      // lYear,lMonth,lDay,lDayzh,isLeap,
		      // cYear,cMonth,cDay
		      
		      this[i] = new calElement(y, m+1, i+1, nStr1[(i+this.firstWeek)%7],
		                               lY, lM, lD,cDay(lD,lM), lL,
		                               cyclical(lDObj.yearCyl) ,cyclical(lDObj.monCyl), cyclical(lDObj.dayCyl++) );
		      lD++;
		      if((i+this.firstWeek)%7==0)   this[i].color = 'red';  // 周日颜色
		      if((i+this.firstWeek)%14==13) this[i].color = 'red';  // 周休二日颜色
		   }
		   // 节气
		   tmp1=sTerm(y,m*2  )-1;
		   tmp2=sTerm(y,m*2+1)-1;
		   this[tmp1].solarTerms = solarTerm[m*2];
		   this[tmp2].solarTerms = solarTerm[m*2+1];
		   if(m==3) this[tmp1].color = 'red'; // 清明颜色
		   // 国历节日
		   for(i in sFtv)
		      if(sFtv[i].match(/^(\d{2})(\d{2})([\s\*])(.+)$/))
		         if(Number(RegExp.$1)==(m+1)) {
		            this[Number(RegExp.$2)-1].solarFestival += RegExp.$4 + ' ';
		            if(RegExp.$3=='*') this[Number(RegExp.$2)-1].color = 'red';
		         }
		   // 月周节日
		   for(i in wFtv)
		      if(wFtv[i].match(/^(\d{2})(\d)(\d)([\s\*])(.+)$/))
		         if(Number(RegExp.$1)==(m+1)) {
		            tmp1=Number(RegExp.$2);
		            tmp2=Number(RegExp.$3);
		            this[((this.firstWeek>tmp2)?7:0) + 7*(tmp1-1) + tmp2 - this.firstWeek].solarFestival += RegExp.$5 + ' ';
		         }
		   // 农历节日
		   for(i in lFtv)
		      if(lFtv[i].match(/^(\d{2})(.{2})([\s\*])(.+)$/)) {
		         tmp1=Number(RegExp.$1)-firstLM;
		         if(tmp1==-11) tmp1=1;
		         if(tmp1 >=0 && tmp1<n) {
		            tmp2 = lDPOS[tmp1] + Number(RegExp.$2) -1;
		            if( tmp2 >= 0 && tmp2<this.length) {
		               this[tmp2].lunarFestival += RegExp.$4 + ' ';
		               if(RegExp.$3=='*') this[tmp2].color = 'red';
		            }
		         }
		      }
		   // 黑色星期五
			if((this.firstWeek+12)%7==5)
				this[12].solarFestival += '黑色星期五 ';
		   // 今日
			var Today = new Date();
			var tY = Today.getFullYear();
			var tM = Today.getMonth();
			var tD = Today.getDate();
		   if(y==tY && m==tM) this[tD-1].isToday = true;
		}
		// ====================== 中文日期

		function cDay(d,m){
		   var s;
		   switch (d) {
		      case 10:
		         s = '初十'; break;
		      case 20:
		         s = '二十'; break;
		         break;
		      case 30:
		         s = '三十'; break;
		      case 1:
		    	  s = nStr3[m-1]; break;
		      default :
		         s = nStr2[Math.floor(d/10)];
		         s += nStr1[d%10];
		   }
		   return(s);
		}
		return new calendar(thedate.getFullYear(),thedate.getMonth());
	}

	function _setCookie(name, value) {
		var today = new Date();
		var expires = new Date();
		expires.setTime(today.getTime() + 1000 * 60 * 60 * 24 * 365);
		document.cookie = name + "=" + escape(value) + "; expires="
				+ expires.toGMTString();
	}

	function _getCookie(Name) {
		var search = Name + "=";
		if (document.cookie.length > 0) {
			offset = document.cookie.indexOf(search);
			if (offset != -1) {
				offset += search.length;
				end = document.cookie.indexOf(";", offset);
				if (end == -1)
					end = document.cookie.length;
				return unescape(document.cookie.substring(offset, end));
			}
			else
				return "";
		}
	}
	function _getlrmonth(date) {
		var aRet = [];
		var leftdate = myjs.date.format(date,'yearmonth') + "-01 00:00:00.0";
		var rightdate = myjs.date.parsestr(leftdate);
		rightdate = rightdate + myjs.date.getNowMontLong(rightdate);
		var date1 = new Date();
		date1.setTime(rightdate);
		rightdate = myjs.date.format(date1,'yearmonth') + "-01 00:00:00.0";
		aRet.push(leftdate);
		aRet.push(rightdate);
		return aRet;
	}
	function _ismobile(){
		var thisOS=navigator.platform;
		//定义匹配的移动终端操作系统类型列表，随时间推移准确性有待矫正
		var os=new Array("iPhone","iPod","iPad","android","Nokia"
				,"SymbianOS","Symbian","Windows Phone","Phone"
				,"Linux armv71","MAUI","UNTRUSTED/1.0"
				,"Windows CE","BlackBerry","IEMobile");
		 
		for(var i=0;i<os.length;i++){//循环匹配到列表中的手机系统
			if(thisOS.match(os[i])){
				return true;
			}
		}
		//因为相当部分的手机系统不知道信息,这里是做临时性特殊辨认
		if(navigator.platform.indexOf('iPad') != -1){
			return true;
		}
		//做这一部分是因为Android手机的内核也是Linux 
		//但是navigator.platform显示信息不尽相同情况繁多,因此从浏览器下手，即用navigator.appVersion信息做判断
		var check = navigator.appVersion;
		if( check.match(/linux/i) ) {
			//X11是UC浏览器的平台 ，如果有其他特殊浏览器也可以附加上条件
			if(check.match(/mobile/i) || check.match(/X11/i)) { 
				return true;
			}   
		}
	}
	function _bindswipe(jq){
        var swipparas={};
        var swipevent1='touchstart';
        var swipevent2='touchmove';
        var swipevent3='touchend';
        
        if(!_ismobile()){
        	swipevent1='mousedown';
        	swipevent2='mousemove';
        	swipevent3='mouseup';
        }
        var istouched = false;
        var ismoved = false;
        function tapevent1(e){
        	ismoved = true;
        }
        function tapevent2(){
        	istouched = false;
        	jq.unbind(swipevent3,tapevent2);
        	jq.unbind(swipevent2,tapevent1);
        }
        function multitask(time,e){
        	if(time){
        		_multiTask(time/1000, 0.6, 100, function() {
        			console.log('tap event :: listen');
        			if(istouched){
        				if(ismoved){
        					console.log('tap event :: ismoved retime');
        					ismoved = false;
        					multitask((new Date()).getTime(),e);
        					return true;
        				}
        				return false;
        			}else{
        				console.log('tap event :: touched end');
        				return true;
        			}
        		}, function(){
        			console.log('tap event :: over');
        			return;
        		}, function() {
        			console.log('tap event happen');
        			$(e.target).trigger('tap');
        		});
        	}
        }
    	jq.bind(swipevent1,function(e){
    		jq.stop();
    		console.log('tap event ::istouched');
    		istouched = true;
        	swipparas={};
        	var touch;
        	if(typeof e.clientX !='undefined') {
        		touch=e;
        	} else {
        		touch= e.originalEvent.touches[0];
        	}
        	swipparas.startx = touch.clientX;
        	swipparas.starty = touch.clientY;
        	swipparas.starttime=(new Date()).getTime();
        	swipparas.target=e.target;
        	multitask((new Date()).getTime(),e);
        	jq.bind(swipevent2,tapevent1);
        	jq.bind(swipevent3,tapevent2);
        });
        jq.bind(swipevent2,function(e){
        	var touch;
        	if(typeof e.clientX !='undefined') {
        		touch=e
        	} else {
        		touch= e.originalEvent.touches[0];
        	}
        	swipparas.endx = touch.clientX;
        	swipparas.endy = touch.clientY;
        });
        jq.bind(swipevent3,function(e){
        	var startx=swipparas.startx;
        	var starty=swipparas.starty;
        	var starttime=swipparas.starttime;
        	var endx = swipparas.endx;
        	var endy = swipparas.endy;
        	var endtime=(new Date()).getTime();
        	var mt=endtime-starttime;
        	var target=swipparas.target;
        	if(mt>1000){
        		return;
        	}else{
        		var hspeed=Math.abs(endx-startx)/mt;
        		var vspeed=Math.abs(endy-starty)/mt;
        		var type='swipeleft';
        		var speed=hspeed;
        		if(hspeed>=vspeed) {
        			if(endx>=startx)
        			{
        				type='swiperight';
        			}else{
        				type='swipeleft';
        			}
        		}else{
        			speed=vspeed;
           			if(endy>=starty){
        				type='swipedown';
        			}else{
        				type='swipeup';
        			}
        		}
        		if(speed>0.3){
        			console.log('swipe event happen::' + type);
        			$(target).trigger(type,[speed]);
        		}
        	}
        });
    }
})(window, document, jQuery);