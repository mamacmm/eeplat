/*
 * combox是对于传统select控件的简单封装，改善了原来的select控件不支持手动输入内容，以及内容过多定位麻烦的问题
 * 支持输入内容自动查找，全键盘操作（上下，翻页，回车，Esc），支持鼠标操作（选择，滚动，点击）
 *
 * 使用方法:
 * 首先页面上一定要一个select或者input text作为基本组件，对于combox最终提交的值也是存放在这个select/input text里面的
 *
 * 标准模式
 *
 * 假如页面有一个id为a的select
 * 例如: var a = new combox(document.getElementById('a'))
 * 将该select自动转换为combox，默认的搜索模式为模糊搜索
 *
 * 高级模式:
 *
 * 高级模式就是在标准模式的基础上，加入配置参数对象
 * 例如:  var bbb = new combox(document.getElementById('bbb'), {debug: false,searchMode: 1});
 * 这里传入的参数2为一个对象，在这个对象里面，可以设置combox的各种配置信息
 * debug: true/false，是否显示原始的select元素可见
 * searchMode: 0为模糊不区分大小写，1为前缀搜索不区分大小写
 * remoteSourceUrl:远程数据源url，支持json格式为下
 * [
 *   {"text":"a","value":"b"},
 *   {"text":"a1","value":"b1"}
 * ]
 * remoteFilterUrl:筛选时候读取的远程url，参数为xxxx?asdasd=~!value!~，~!value!~为宏，在触发时会被替换为当前输入框的值
 * allowAnyValue:默认为false，不允许combox输入非options内的数据
 * displayValue:combox的显示值，当displayValue在combox的options里面能够找到与之对应的value时候，它显示该option的text，否则他显示displayValue
 * pleaseSelect:当combox的txtBox值为pleaseSelect的文本时，下拉时展开所有options
 * mouseDblClick:0为单击，1为双击
 *
 */
function combox(el, conf){
    // 保存select引用指针
    this.originEl = el;
    el.combox = this;
    // 得到select的高度等
    this.originElPos = _getPos(el);
    this.source = {};
    this.length = 0;
    this.selectedValue = el.value;
    if (el.options && el.options.length) {
        this.selectedText = this._previousText = el.options[el.selectedIndex].text;
    }
    else {
        this.selectedText = this._previousText = '';
    }
    this.flagFiltering = false;
    this.dropDownDivMaxHeight = 300;
    this.rows = [];
    this.hoverIdx = -1;
    this.selectedIdx = -1;
    this.timeout_filter = null;//自动筛选定时器
    this.dropDownBtnWidth = 20;
    this.dynamicDelay = 200;//动态延时时间，为了解决由于选项数据过大，动态调整延时触发时间，解决浏览器过卡问题
    //配置选项
    this.debug = false;
    this.searchMode = 0;//0:模糊不区分大小写,1:前缀不区分大小写
    this.sourceMode = 0;//0:初始数据源在本地select，1:初始数据源来自远程的url
    this.filterMode = 0;//0:本地，1:远程
    this.displayValue = this.selectedValue;//文本框显示值
    this.pleaseSelect = '请选择';
    this.remoteFilterUrl = "";
    this.allowAnyValue = false;
    this.mouseDblClick = false;
    if (conf) {
        if (conf.debug == true) {
            this.debug = true;
        }
        if (conf.searchMode) {
            this.searchMode = conf.searchMode;
        }
        if (conf.remoteSourceUrl) {
            this.remoteSourceUrl = conf.remoteSourceUrl;
            this.sourceMode = 1;
        }
        if (conf.remoteFilterUrl) {
            this.remoteFilterUrl = conf.remoteFilterUrl;
            this.filterMode = 1;
        }
        if (conf.allowAnyValue) {
            this.allowAnyValue = conf.allowAnyValue;
        }
        if (conf.displayValue) {
            this.displayValue = conf.displayValue;
        }
        if (conf.pleaseSelect) {
            this.pleaseSelect = conf.pleaseSelect;
        }
        if (conf.mouseDblClick == true) {
            this.mouseDblClick = true;
        }
    }
    //
    if (!window._combox) {
        window._combox = [];
    }
    window._combox.push(this);
    _addEv(document, 'click', function(){
        var curEl = document.all ? window.event.srcElement : arguments[0].target;
        if (!curEl.comboxType && (curEl.parentNode && !curEl.parentNode.comboxType)) {
            for (var i = 0; i < window._combox.length; i++) {
                window._combox[i].fold();
            }
        }
    })
    this.initialize();
}

combox.prototype.initialize = function(){
    //本地数据源模式
    if (this.sourceMode == 0) {
        this.getOriginSelectOptionsSource();
    }
    //远程数据源模式
    else 
        if (this.sourceMode == 1) {
            this.getRemoteOptionSourceFromUrl();
        }
    this.buildBase();
}
combox.prototype.buildBase = function(){
    var _combox = this;
    if (_combox.debug == true) {
        this.originEl.style.display = '';
    }
    else {
        this.originEl.style.display = 'none';
    }
    if (_combox.allowAnyValue == true) {
        var select = _combox.originEl;
        if (select.tagName && select.tagName.toLowerCase() == 'select') {
            var input = document.createElement('input');
            input.type = 'text';
            input.id = _combox.originEl.id;
            input.name = _combox.originEl.name;
            input.value = _combox.displayValue;
            if (typeof(select.onchange) == 'function') {
                input.onchange = select.onchange;
            }
            if (_combox.debug == true) {
                input.style.display = '';
            }
            else {
                input.style.display = 'none';
            }
            select.combox = null;
            select.parentNode.replaceChild(input, select);
            input.combox = _combox;
            _combox.originEl = input;
        }
    }
    //创建输入框
    var _span1 = document.createElement('span');
    //
    var _txtbox1 = document.createElement('input');
    _txtbox1.type = 'text';
    _txtbox1.comboxType = 'input';
    _txtbox1.setAttribute('autoComplete', 'off');
    _txtbox1.style.height = _addUnit(this.originElPos.height);
    _txtbox1.style.width = _addUnit(this.originElPos.width - this.dropDownBtnWidth);
    var _displayValueFlag = true;
    for (var text in _combox.source) {
        if (_combox.source[text] == this.displayValue) {
            _txtbox1.value = text;
            _displayValueFlag = false;
            break;
        }
    }
    if (_displayValueFlag) {
        _txtbox1.value = this.displayValue;
    }
    _span1.appendChild(_txtbox1);
    var _btn1 = document.createElement('input');
    _btn1.type = 'button';
    _btn1.style.height = _addUnit(this.originElPos.height);
    _btn1.style.width = _addUnit(this.dropDownBtnWidth);
    _btn1.comboxType = 'btn';
    _btn1.className = 'combox_dropDownBtn';
    _btn1.value = '▼';
    _span1.appendChild(_btn1);
    this.originEl.parentNode.insertBefore(_span1, this.originEl);
    //
    var _div1 = document.createElement("div");
    _div1.style.visibility = 'hidden';
    _div1.style.position = 'absolute';
    _div1.style.zIndex = 9999;
    _div1.style.width = _addUnit(this.originElPos.width);
    _div1.style.top = _addUnit(this.originElPos.height + this.originElPos.top - -  $(document).scrollTop() );
    _div1.style.left = _addUnit(this.originElPos.left);
    //
    var _div2 = document.createElement("div");
    _div2.style.overflow = 'auto';
    _div2.className = 'combox_dropDownDiv';
    //
    _div1.innerHTML = '<iframe style="position:absolute;z-index:-999;width:100%;background-color:#fff;height:100%;top:0;left:0;right:0;scrolling:no;" frameborder="0" src="about:blank"></iframe>';
    _div1.appendChild(_div2);
    document.body.appendChild(_div1);
    //
    _txtbox1._dropDownDiv = _div2;
    _txtbox1._dropDownContainer = _div1;
    _txtbox1._combox = this;
    _btn1._combox = this;
    _div1._combox = this;
    _div2._combox = this;
    //
    this.txtBox = _txtbox1;
    this._dropDownDiv = _div2;
    this._dropDownContainer = _div1;
    //
    _txtbox1.onclick = this._inputAreaClickEv;
    if (this.mouseDblClick) {
        _div1.ondblclick = this._dropDownAreaClickEv;
    }
    else {
        _div1.onclick = this._dropDownAreaClickEv;
    }
    _btn1.onclick = this._dropDownBtnClickEv;
    _div1.onmouseover = this._dropDownAreaMouseOverEv;
    _txtbox1.onkeydown = this._dropDownAreaKeyDownEv;
    _txtbox1.onkeyup = this._dropDownAreaKeyUpEv;
    _txtbox1.onblur = function(){
        if (this.value.length > 0 && typeof(this._combox.source[this.value.toString()]) != 'undefined') {
            this._combox.selectedValue = this._combox.originEl.value = this._combox.source[this.value.toString()];
            this._combox.selectedText = this.value;
        }
        else 
            if (this._combox.allowAnyValue == true) {
                this._combox.selectedText = this._combox.selectedValue = this._combox.originEl.value = this.value;
            }
    }
    /*
     _div1.onkeydown = function() {
     _combox._dropDownAreaKeyDownEv;
     var curEv = document.all ? window.event : arguments[0];
     _stopDefEv(curEv);
     }
     _div1.onkeyup = function() {
     _combox._dropDownAreaKeyUpEv;
     var curEv = document.all ? window.event : arguments[0];
     _stopDefEv(curEv);
     }*/
}
//获取select的options的数据源
combox.prototype.getOriginSelectOptionsSource = function(){
    if (this.originEl.options && this.originEl.options.length) {
        var s = {};
        for (var i = 0; i < this.originEl.options.length; i++) {
            var o = this.originEl.options[i];
            s[o.text] = o.value;
        }
        this.source = s;
        //增加动态延时时间
        /*if (this.originEl.options.length <= 500) {
         this.dynamicDelay = 200;
         }
         else if (this.originEl.options.length > 500 && this.originEl.options.length < 2000) {
         this.dynamicDelay = 200;
         }
         else {
         this.dynamicDelay = 200;
         }*/
    }
}
//获取远程数据源
combox.prototype.getRemoteOptionSourceFromUrl = function(){
    var _combox = this;
    _createAjax(this.remoteSourceUrl, function(res){
        var r = eval(res);
        if (r.length) {
            var s = {};
            for (var i = 0; i < r.length; i++) {
                var o = r[i].text;
                s[o] = r[i].value;
                if (r[i].selected) {
                    _combox.selectedValue = _combox.originEl.value = r[i].value;
                    _combox.selectedText = _combox.txtBox.value = r[i].text;
                }
            }
            _combox.source = s;
        }
    });
}
combox.prototype.getRemoteOptionSourceReCall = function(res){
    var r = eval(res);
    if (r.length) {
        var s = {};
        for (var i = 0; i < r.length; i++) {
            var o = r[i].text;
            s[o] = r[i].value;
        }
        this.source = s;
    }
}
//showType:0为所有,1为筛选模式
combox.prototype.fillDropDownData = function(showType){
    var _combox = this;
    //GC
    if (this.rows && this.rows.length) {
        this.rows.splice(0, this.rows.length);
    }
    this._dropDownDiv.innerHTML = '';
    //
    if (this.filterMode == 0) {
        var filterSource = {};
        if (this.txtBox.value == '' || (this.txtBox.value == this.pleaseSelect && this.selectedText == this.pleaseSelect) || showType == 0) {
            filterSource = this.source;
        }
        else {
            for (o in this.source) {
                switch (this.searchMode) {
                    case 0://模糊不区分大小写
                        if (o.toLowerCase().indexOf(this.txtBox.value.toLowerCase()) >= 0) {
                            filterSource[o] = this.source[o];
                        }
                        break;
                    case 1://前缀不区分大小写
                        if (o.toLowerCase().indexOf(this.txtBox.value.toLowerCase()) == 0) {
                            filterSource[o] = this.source[o];
                        }
                        break;
                }
                
            }
        }
        this.fillDropDownDataDetail(filterSource);
    }
    else 
        if (this.filterMode == 1) {
            var url = this.remoteFilterUrl.replace(/~!value!~/g, this.txtBox.value);
            if (this.debug) {
                alert(url);
            }
            _createAjax(url, function(res){
                var r = eval(res);
                var filterSource = {};
                if (r.length) {
                    var s = {};
                    for (var i = 0; i < r.length; i++) {
                        var o = r[i].text;
                        s[o] = r[i].value;
                    }
                    filterSource = s;
                    _combox.fillDropDownDataDetail(filterSource);
                }
            });
        }
}
combox.prototype.fillDropDownDataDetail = function(filterSource){
    var rowList = [];
    var rowIdx = 0;
    //使用documentFragment替代频繁的dom操作，减少浏览器延时
    var dfg = document.createDocumentFragment();
    for (o in filterSource) {
        var _div1 = document.createElement('div');
        if (this.txtBox.value && this.txtBox.value.length) {
            //由于正则表达式在遇到/,(这种情况有问题，所以
            //var regx = new RegExp(this.txtBox.value, 'ig');
            //_div1.innerHTML = o.replace(regx, '<span class="combox_filter">$&</span>');
            var s1 = this.txtBox.value;
            var s2 = s1.toLowerCase();
            var s3 = o.toLowerCase();
            var i1 = s3.indexOf(s2);
            var i2 = s1.length;
            var i3 = o.length;
            if (i1 > -1) {
                _div1.innerHTML = o.substr(0, i1) + '<span class="combox_filter">' + o.substr(i1, i2) + '</span>' + o.substr(i1 + i2);
            }
            else {
                _div1.innerHTML = o;
            }
            //_div1.innerHTML = _div1.innerHTML = o.replace(this.txtBox.value, '<span class="combox_filter">' + this.txtBox.value + '</span>');
        }
        else {
            _div1.innerHTML = o;
        }
        _div1._text = o;
        _div1._val = filterSource[o];
        _div1.rowIdx = rowIdx;
        _div1.comboxType = 'row';
        _div1.style.whiteSpace = 'nowrap';
        _addClass(_div1, 'combox_normal');
        if (o == this.selectedText) {
            _addClass(_div1, 'combox_selected_row');
            this.selectedIdx = rowIdx;
        }
        dfg.appendChild(_div1);
        rowList.push(_div1);
        rowIdx++;
    }
    this._dropDownDiv.appendChild(dfg);
    var contentHeight = 0;
    var rowMaxWidth = 0;
    for (var i = 0; i < rowList.length; i++) {
        contentHeight += rowList[i].offsetHeight;
        rowMaxWidth = rowList[i].offsetWidth > rowMaxWidth ? rowList[i].offsetWidth : rowMaxWidth;
    }
    this.rows = rowList;
    this.hoverIdx = -1;
    //计算内部高度，如果超过固定搞定，设置滚动条
    if (contentHeight > this.dropDownDivMaxHeight) {
        this._dropDownDiv.style.height = _addUnit(this.dropDownDivMaxHeight);
        if (this._dropDownContainer.childNodes[0] && this._dropDownContainer.childNodes[0].tagName && this._dropDownContainer.childNodes[0].tagName.toLowerCase() == 'iframe') {
            this._dropDownContainer.childNodes[0].style.height = _addUnit(this.dropDownDivMaxHeight);
        }
    }
    else {
        this._dropDownDiv.style.height = 'auto';
        if (this._dropDownContainer.childNodes[0] && this._dropDownContainer.childNodes[0].tagName && this._dropDownContainer.childNodes[0].tagName.toLowerCase() == 'iframe') {
            this._dropDownContainer.childNodes[0].style.height = _addUnit(this._dropDownDiv.clientHeight);
        }
    }
    if (this._dropDownDiv.clientWidth < this._dropDownDiv.scrollWidth) {
        this._dropDownDiv.style.width = _addUnit(this._dropDownDiv.scrollWidth + 20);
        this._dropDownContainer.childNodes[0].style.width = this._dropDownDiv.offsetWidth;
    }
    this._dropDownDiv.style.overflowX = 'hidden';
    //打开下拉选择区
    if (rowList.length) {
        this.expand();
    }
    else {
        this.fold();
    }
}
combox.prototype._dropDownAreaKeyDownEv = function(){
    var _combox = this._combox;
    clearTimeout(_combox.timeout_filter);
    var curEl = document.all ? window.event.srcElement : arguments[0].target;
    var curEv = document.all ? window.event : arguments[0];
    if (curEl.comboxType && curEl.comboxType == 'input') {
        var pressKey = document.all ? window.event.keyCode : arguments[0].keyCode;
        switch (pressKey) {
            case 38://up
                if (_combox.rows.length) {
                    _combox.expand();
                    if (_combox.hoverIdx > 0) {
                        _addOrReplaceClass(_combox.rows[_combox.hoverIdx], 'combox_hover', 'combox_normal');
                        _addOrReplaceClass(_combox.rows[--_combox.hoverIdx], 'combox_normal', 'combox_hover');
                        if (_combox.rows[_combox.hoverIdx].offsetTop < _combox._dropDownDiv.scrollTop) {
                            _combox._dropDownDiv.scrollTop = _combox.rows[_combox.hoverIdx].offsetTop;
                        }
                    }
                    else {
                        _addOrReplaceClass(_combox.rows[0], 'combox_normal', 'combox_hover');
                        _combox.hoverIdx = 0;
                    }
                }
                break;
            case 40://down
                if (_combox.rows.length) {
                    _combox.expand();
                    if (_combox.hoverIdx >= -1 && _combox.hoverIdx < _combox.rows.length - 1) {
                        if (_combox.hoverIdx > -1) {
                            _addOrReplaceClass(_combox.rows[_combox.hoverIdx], 'combox_hover', 'combox_normal');
                        }
                        _addOrReplaceClass(_combox.rows[++_combox.hoverIdx], 'combox_normal', 'combox_hover');
                        if (_combox.rows[_combox.hoverIdx].offsetTop + _combox.rows[_combox.hoverIdx].offsetHeight > _combox._dropDownDiv.scrollTop + _combox.dropDownDivMaxHeight) {
                            _combox._dropDownDiv.scrollTop = _combox.rows[_combox.hoverIdx].offsetTop + _combox.rows[_combox.hoverIdx].offsetHeight - _combox.dropDownDivMaxHeight;
                        }
                    }
                    else {
                        _addOrReplaceClass(_combox.rows[_combox.rows.length - 1], 'combox_normal', 'combox_hover');
                        _combox.hoverIdx = _combox.rows.length - 1;
                    }
                }
                break;
            case 33://page up
                if (_combox.rows.length) {
                    _combox.expand();
                    if (_combox.hoverIdx > 0) {
                        _addOrReplaceClass(_combox.rows[_combox.hoverIdx], 'combox_hover', 'combox_normal');
                        var h = 0;
                        for (var i = _combox.hoverIdx; i > 0; i--) {
                            h += _combox.rows[i].offsetHeight;
                            if (h > _combox.dropDownDivMaxHeight) {
                                _addOrReplaceClass(_combox.rows[i], 'combox_normal', 'combox_hover');
                                if ((_combox._dropDownDiv.scrollTop <= _combox.rows[_combox.hoverIdx].offsetTop) && (_combox._dropDownDiv.scrollTop >= _combox.rows[_combox.hoverIdx].offsetTop + _combox.rows[_combox.hoverIdx].offsetHeight - _combox.dropDownDivMaxHeight)) {
                                    _combox._dropDownDiv.scrollTop -= h - _combox.rows[i].offsetHeight;
                                }
                                else {
                                    _combox._dropDownDiv.scrollTop = _combox.rows[i].offsetTop;
                                }
                                _combox.hoverIdx = i;
                                break;
                            }
                            if (i == 1) {
                                _addOrReplaceClass(_combox.rows[0], 'combox_normal', 'combox_hover');
                                _combox.hoverIdx = 0;
                                _combox._dropDownDiv.scrollTop = 0;
                            }
                        }
                    }
                    else {
                        _addOrReplaceClass(_combox.rows[0], 'combox_normal', 'combox_hover');
                        _combox.hoverIdx = 0;
                    }
                }
                break;
            case 34://page down
                if (_combox.rows.length) {
                    _combox.expand();
                    if (_combox.hoverIdx == -1) {
                        _addOrReplaceClass(_combox.rows[0], 'combox_normal', 'combox_hover');
                        _combox.hoverIdx = 0;
                    }
                    else 
                        if (_combox.hoverIdx > -1 && _combox.hoverIdx < _combox.rows.length - 1) {
                            _addOrReplaceClass(_combox.rows[_combox.hoverIdx], 'combox_hover', 'combox_normal');
                            var h = 0;
                            for (var i = _combox.hoverIdx; i < _combox.rows.length - 1; i++) {
                                h += _combox.rows[i + 1].offsetHeight;
                                if (h > _combox.dropDownDivMaxHeight) {
                                    _addOrReplaceClass(_combox.rows[i], 'combox_normal', 'combox_hover');
                                    if ((_combox._dropDownDiv.scrollTop <= _combox.rows[_combox.hoverIdx].offsetTop) && (_combox._dropDownDiv.scrollTop >= _combox.rows[_combox.hoverIdx].offsetTop + _combox.rows[_combox.hoverIdx].offsetHeight - _combox.dropDownDivMaxHeight)) {
                                        _combox._dropDownDiv.scrollTop += h - _combox.rows[i].offsetHeight;
                                    }
                                    else {
                                        _combox._dropDownDiv.scrollTop = _combox.rows[i].offsetTop + _combox.rows[i].offsetHeight - _combox.dropDownDivMaxHeight;
                                    }
                                    _combox.hoverIdx = i;
                                    break;
                                }
                                if (i == _combox.rows.length - 2) {
                                    _combox.hoverIdx = _combox.rows.length - 1;
                                    _addOrReplaceClass(_combox.rows[_combox.rows.length - 1], 'combox_normal', 'combox_hover');
                                    _combox._dropDownDiv.scrollTop = _combox._dropDownDiv.scrollHeight;
                                }
                            }
                        }
                        else {
                            _addOrReplaceClass(_combox.rows[_combox.rows.length - 1], 'combox_normal', 'combox_hover');
                            _combox.hoverIdx = _combox.rows.length - 1;
                        }
                }
                break;
            case 13://enter
                _preventDefaultEv(curEv);
                break;
            default:
        }
    }
}
combox.prototype._dropDownAreaKeyUpEv = function(){
    var _combox = this._combox;
    clearTimeout(_combox.timeout_filter);
    var curEl = document.all ? window.event.srcElement : arguments[0].target;
    var curEv = document.all ? window.event : arguments[0];
    if (curEl.comboxType && curEl.comboxType == 'input') {
        var pressKey = document.all ? window.event.keyCode : arguments[0].keyCode;
        switch (pressKey) {
            case 37://left
                break;
            case 38://up
                break;
            case 38://right
                break;
            case 40://down
                break;
            case 13://enter
                if (_combox.hoverIdx > -1) {
                    //fix快速敲打键盘，造成选中的值与输入的值不一致情况
                    if (!_combox.flagFiltering) {
                        _combox.selectedText = _combox.txtBox.value = _combox.rows[_combox.hoverIdx]._text;
                        _combox.selectedValue = _combox.rows[_combox.hoverIdx]._val;
                        //
                        _combox.changeSelectedRowClass();
                        //
                        if (_combox.originEl.value != _combox.rows[_combox.hoverIdx]._val) {
                            _combox.originEl.value = _combox.rows[_combox.hoverIdx]._val;
                            if (typeof(_combox.originEl.onchange) == 'function') {
                                _combox.originEl.onchange();
                            }
                        }
                    }
                }
                _combox.fold();
                _stopDefEv(curEv);
                break;
            case 27://esc
                if (_combox.isFolded()) {
                    _combox.expand();
                }
                else {
                    _combox.fold();
                }
                break;
            case 33://page up
                break;
            case 34://page down
                break;
            /*case 16://shift 好烦躁的shift，有的人会用shift+字母输入。。。
             break;*/
            case 17://ctrl
                break;
            case 18://alt
                break;
            case 45://insert
                break;
            case 20://cap lock
                break;
            case 144://num lock
                break;
            case 32://space
                if (curEv.ctrlKey) {
                    break;
                }
            default:
                if (_combox._previousText != curEl.value || _combox.flagFiltering) {
                    _combox.flagFiltering = true;
                    _combox.timeout_filter = setTimeout(function(){
                        curEl._combox.fillDropDownData(1);
                        _combox.flagFiltering = false;
                    }, _combox.dynamicDelay);
                    _combox._previousText = curEl.value;
                }
                else 
                    if (!_combox.txtBox.value.length) {
                        _combox.flagFiltering = true;
                        _combox.timeout_filter = setTimeout(function(){
                            curEl._combox.fillDropDownData(0);
                            _combox.flagFiltering = false;
                        }, _combox.dynamicDelay);
                        _combox._previousText = curEl.value;
                    }
        }
    }
}
combox.prototype.changeSelectedRowClass = function(){
    var _combox = this;
    if (_combox.selectedIdx > -1 && _combox.selectedIdx != _combox.hoverIdx) {
        _removeClass(_combox.rows[_combox.selectedIdx], 'combox_selected_row');
        _addClass(_combox.rows[_combox.hoverIdx], 'combox_selected_row');
        _combox.selectedIdx = _combox.hoverIdx;
    }
}
combox.prototype._dropDownAreaMouseOverEv = function(){
    var _combox = this._combox;
    var curEl = document.all ? window.event.srcElement : arguments[0].target;
    if (curEl.comboxType && curEl.comboxType == 'row') {
        if (_combox.hoverIdx > -1 && _combox.hoverIdx != curEl.rowIdx) {
            _addOrReplaceClass(_combox.rows[_combox.hoverIdx], 'combox_hover', 'combox_normal');
            _combox.hoverIdx = curEl.rowIdx;
        }
        else {
            _combox.hoverIdx = curEl.rowIdx;
        }
        _addOrReplaceClass(curEl, 'combox_normal', 'combox_hover');
    }
    else 
        if (curEl.tagName && curEl.tagName.toLowerCase() == 'span' &&
        curEl.parentNode.comboxType &&
        curEl.parentNode.comboxType == 'row') {
            if (_combox.hoverIdx > -1 && _combox.hoverIdx != curEl.parentNode.rowIdx) {
                _addOrReplaceClass(_combox.rows[_combox.hoverIdx], 'combox_hover', 'combox_normal');
                _combox.hoverIdx = curEl.parentNode.rowIdx;
            }
            else {
                _combox.hoverIdx = curEl.parentNode.rowIdx;
            }
            _addOrReplaceClass(curEl.parentNode, 'combox_normal', 'combox_hover');
        }
    var curEv = document.all ? window.event : arguments[0];
    _stopDefEv(curEv);
}
combox.prototype._dropDownAreaClickEv = function(){
    var _combox = this._combox;
    var curEl = document.all ? window.event.srcElement : arguments[0].target;
    if (curEl.comboxType && curEl.comboxType == 'row') {
        if (_combox.originEl.value != curEl._val) {
            _combox.selectedValue = _combox.originEl.value = curEl._val;
            if (typeof(_combox.originEl.onchange) == 'function') {
                _combox.originEl.onchange();
            }
            _combox.selectedText = _combox.txtBox.value = curEl._text;
            _combox.changeSelectedRowClass();
        }
        _combox.fold();
    }
    else 
        if (curEl.tagName && curEl.tagName.toLowerCase() == 'span' &&
        curEl.parentNode.comboxType &&
        curEl.parentNode.comboxType == 'row') {
            if (_combox.originEl.value != curEl.parentNode._val) {
                _combox.selectedValue = _combox.originEl.value = curEl.parentNode._val;
                if (typeof(_combox.originEl.onchange) == 'function') {
                    _combox.originEl.onchange();
                }
                _combox.selectedText = _combox.txtBox.value = curEl.parentNode._text;
                _combox.changeSelectedRowClass();
            }
            _combox.fold();
        }
    var curEv = document.all ? window.event : arguments[0];
    _stopDefEv(curEv);
}
combox.prototype._dropDownBtnClickEv = function(){
    var _combox = this._combox;
    var curEl = document.all ? window.event.srcElement : arguments[0].target;
    if (curEl.comboxType && curEl.comboxType == 'btn') {
        if (_combox.isFolded()) {
            _combox.fillDropDownData(0);
        }
        else {
            _combox.fold();
        }
    }
    var curEv = document.all ? window.event : arguments[0];
    _stopDefEv(curEv);
    curEl.blur();
}
combox.prototype._inputAreaClickEv = function(){
    var _combox = this._combox;
    var curEl = document.all ? window.event.srcElement : arguments[0].target;
    if (curEl.comboxType && curEl.comboxType == 'input') {
        if (_combox.isFolded()) {
            _combox.fillDropDownData(0);
        }
    }
    var curEv = document.all ? window.event : arguments[0];
    _stopDefEv(curEv);
}
combox.prototype.expand = function(){
    this._dropDownContainer.style.visibility = 'visible';
}
combox.prototype.fold = function(){
    this._dropDownContainer.style.visibility = 'hidden';
    /*if (this.txtBox.value.length > 0 && typeof(this.source[this.txtBox.value.toString()]) != 'undefined') {
     this.selectedValue = this.originEl.value = this.source[this.txtBox.value.toString()];
     this.selectedText = this.txtBox.value;
     }
     else
     if (this.allowAnyValue == true) {
     this.selectedText = this.selectedValue = this.originEl.value = this.txtBox.value;
     }
     else {
     this.txtBox.value = this.selectedText;
     }*/
}
combox.prototype.isFolded = function(){
    return this._dropDownContainer.style.visibility == 'hidden';
}
combox.prototype.setValue = function(value){
    if (value.length > 0 && typeof(this.source[value.toString()]) != 'undefined') {
        this.selectedValue = this.originEl.value = this.source[value];
        this.txtBox.value = this.selectedText = value;
    }
    else 
        if (this.allowAnyValue == true) {
            this.txtBox.value = this.selectedText = this.selectedValue = this.originEl.value = value;
        }
}
function _stopDefEv(ev){
    if (document.all) {
        ev.cancelBubble = true;
        ev.returnValue = false;
    }
    else {
        ev.stopPropagation();
        ev.preventDefault();
    }
}

function _preventDefaultEv(ev){
    if (document.all) {
        ev.returnValue = false;
    }
    else {
        ev.preventDefault();
    }
}

function _addEv(el, ev, fn){
    if (document.all) {
        el.attachEvent('on' + ev, fn);
    }
    else {
        el.addEventListener(ev, fn, false);
    }
}

function _hasCss(el, className){
    var cssAry = el.className.split(/\s/);
    for (var i = 0, css; css = cssAry[i]; i++) {
        if (className.toLowerCase() == css.toLowerCase()) {
            return true;
        }
    }
    return false;
}

function _addClass(el, className){
    var a1 = el.className.split(/\s/);
    var a2 = [];
    for (var i = 0; i < a1.length; i++) {
        if (a1[i] != '' && a1[i].length) {
            a2.push(a1[i]);
        }
    }
    a2.push(className);
    el.className = a2.join(' ');
}

function _removeClass(el, className){
    var a1 = el.className.split(/\s/);
    var a2 = [];
    for (var i = 0; i < a1.length; i++) {
        if (a1[i] != '' && a1[i].length && a1[i] != className) {
            a2.push(a1[i]);
        }
    }
    el.className = a2.join(' ');
}

function _addOrReplaceClass(el, sourceClass, replaceClass){
    var a1 = el.className.split(/\s/);
    var a2 = [];
    for (var i = 0; i < a1.length; i++) {
        if (a1[i] != '' && a1[i].length && a1[i] != sourceClass) {
            a2.push(a1[i]);
        }
    }
    a2.push(replaceClass);
    el.className = a2.join(' ');
}

/*
 * 格式化<>等html标记，暂时不存在该问题
 */
function _formatHTML(str){
    return str.replace(/</g, '&lt;').replace(/>/g, '&gt;');
}

function _createAjax(url, reCallFn){
    var request;
    if (window.XMLHttpRequest) {
        request = new XMLHttpRequest();
        if (request.overrideMimeType) {
            request.overrideMimeType('text/xml');
        }
    }
    else 
        if (window.ActiveXObject) {
            try {
                request = new ActiveXObject('Msxml2.XMLHTTP');
            } 
            catch (e) {
                try {
                    request = new ActiveXObject('Microsoft.XMLHTTP');
                } 
                catch (e) {
                    return false;
                }
            }
        }
    if (!request) {
        window.alert(".创建对象实例失败");
        return false;
    }
    request.onreadystatechange = function(){
        if (request.readyState == 4) {
            if (request.status == 200) {
                reCallFn(request.responseText);
            }
        }
    }
    request.open('get', url, true);
    request.send(null);
}

//获取元素的位置
function _getPos(el){
    var _el = el;
    var t = _el.offsetTop;
    var l = _el.offsetLeft;
    var w = _el.offsetWidth;
    var h = _el.offsetHeight;
    while (_el = _el.offsetParent) {
        t += _el.offsetTop;
        l += _el.offsetLeft;
    }
    return {
        top: t,
        left: l,
        width: w,
        height: h
    };
}

//悲剧，只能修复window.onresize时候，动态调整combox的绝对定位
_addEv(window, 'resize', function(){
    if (window._combox && window._combox.length) {
        for (var i = 0; i < window._combox.length; i++) {
            var c = window._combox[i];
            var pos = _getPos(c.txtBox);
            c._dropDownContainer.style.top = _addUnit(pos.top + pos.height);
            c._dropDownContainer.style.left = _addUnit(pos.left);
        }
    }
});

//实现与通过css加载combox，兼容以前做的那个combox
_addEv(window, 'load', function(){
    var sel_ary = document.getElementsByTagName('select');
    var _a = [];
    for (var i = 0; i < sel_ary.length; i++) {
        if (!sel_ary[i].multiple && sel_ary[i].style.display != 'none') {
            if (_hasCss(sel_ary[i], 'combox')) {
                _a.push(sel_ary[i]);
            }
        }
    }
    for (var i = 0; i < _a.length; i++) {
        var conf = {};
        var el = _a[i];
        if (el.getAttribute('debug') == 'true') {
            conf.debug = true;
        }
        if (!isNaN(el.getAttribute('searchMode')) && el.getAttribute('searchMode') != '') {
            conf.searchMode = parseInt(el.getAttribute('searchMode'));
        }
        if (el.getAttribute('remoteFilterUrl')) {
            conf.remoteFilterUrl = el.getAttribute('remoteFilterUrl');
        }
        if (el.getAttribute('allowAnyValue') == 'true') {
            conf.allowAnyValue = true;
        }
        if (el.getAttribute('displayValue')) {
            conf.displayValue = el.getAttribute('displayValue');
        }
        if (el.getAttribute('pleaseSelect')) {
            conf.pleaseSelect = el.getAttribute('pleaseSelect');
        }
        if (el.getAttribute('mouseDblClick') == 'true') {
            conf.mouseDblClick = true;
        }
        var o = new combox(el, conf);
    }
    if (_a.length) {
        _a.splice(0, _a.length);
    }
})
function _addUnit(num){
    return num + 'px';
}
