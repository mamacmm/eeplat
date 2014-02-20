/*****************************************主页面框架代码******************************************/
var isMobi = isMobile();///是否为手机环境
var position = "first"; //tab页显示顺序，first是显示在前面，last是显示在后面
var isHome = 1; //是否有首页   有是1   没有是0
var globalService = globalURL + 'servicecontroller';
var globalPml= globalURL + 'mvccontroller';

//得到浏览器可用高度，赋给菜单  以及右边区域总div
function resscrEvt(height,width){
	if(height==undefined||width==undefined){
		height = $(window).height();
		width = $(window).width();
	}
	
	try{
		var top = $(".main-container").offset().top;
		var left = $(".sidebar").width() + 1;
	

		$(".resizeTd").css("height",height-top);
	///树	 
		$(".mytree").css("height",height-top);
		
	//tab-pane
	    $(".page-content:visible .ui-tabs-panel").css("height",height-top-40);  ///原来的是-25
	    $(".page-content:visible .ui-tabs-panel").css("width",width-left-$(".lrschidren").width()-10);   //原来没有-10
	  
	    
	    if($(".page-content:visible .lrschidren").size() == 2){
	      	 $(".page-content:visible .lrschidren").css("height",height-top);  
	      	 $(".page-content:visible .lrschidren").eq(1).css("width",width-left-$(".lrschidren").eq(0).width()-10);   //原来没有-10
	      }
	}catch(e){
		
		
	}
  
  //  $(".mRight:visible").css("overflow","hidden");
}

//让菜单能伸展   如果这个方法放到类里执行 就会非常慢  所以没有放到类里，在这里判断如果有outlook菜单 则执行
$(function(){
	if($(".mHi").length>0){
		//所有菜单ul标记隐藏
		$(".mHi:gt(0)").hide(); 
		
		$(".mTitle").bind("click",function(){
	  		$(".mHi").hide(); 
			$(".mTitle-hover").removeClass("mTitle-hover");
			$(this).addClass("mTitle-hover");
			$(this).next('.mHi').fadeIn("normal");
	 	});
	}
});

//鼠标在菜单上时   更换背景
function bindMenuHoverCss(){
  $(".mMenu").bind("mouseover",function(){
		$(this).addClass("mMenu-hover");
  }).bind("mouseout",function(){
		$(this).removeClass("mMenu-hover");
  })
};

//点击菜单
function bindClickMenu() {
 $(".mMenu").bind("click",function(event){
 		//设置center总区域有滚动条
		//$(".mRight:eq(0)").css("overflow-y","auto");
		//$(".mRight:eq(0)").css("overflow-x","auto");
		
		$(".mMenu").removeClass("mMenu-hover2");
		$(".mMenu").removeClass("mMenu-hover");
		$(this).addClass("mMenu-hover2");
		
		
		//菜单id和tab  id有关联的
		var menuId = $(this).attr("id");
		//菜单title 等于 tab的 title
		var menuName = $(this).attr("name");
		//属性选择器   选择table 属性 tabId  值为 menuId的
		var paneId = $(this).attr("paneid");
		
		////面板的名称
		var paneName = $(this).attr("paneName");
		////目标面板的名称
		var target = $(this).attr("target");
		
		if(target!=null && target!="" && target!="_opener_tab" && paneName!=null){

			loadPml({
				'pml':paneName,
				'target':target
			});
			
		}
		else if(paneId==null || paneId.indexOf('mvccontroller')!=-1){
			createTab(menuId,menuName,paneId,'isMenu');
		}else{
			window.open(paneId);
		}	
		event.stopPropagation();

  })
};



function createNewTab(menuId,menuName,paneUrl,isMenu){
	
	var tabSelector = "#dvTab .oneTab[tabId='"+menuId+"']";
	if($(tabSelector).length==1){
		$(tabSelector).remove();
		$('#tab_' + menuId).remove();
	}
	createTab(menuId,menuName,paneUrl,isMenu);
}

function createTab(menuId,menuName,paneUrl,isMenu){

	
	var tabSelector = "#dvTab .oneTab[tabId='"+menuId+"']";
	
	if($(tabSelector).length==1){
		//如果这个tab已经存在，则设置成选中的css
		selectTabCss(tabSelector);
		return;
	}
 
	//如果tab页到7个  则关闭最后一个
	////关闭问题也挺复杂
	if($(".gTab .oneTab").length ==6){
		if(position=="first"){
			var tabId = $(".gTab .oneTab:last").attr("tabId");
			$(".gTab .oneTab:last").remove();
			$('#tab_' + tabId).remove();

		}else if(position=="last"){
			var tabId = $(".gTab .oneTab:eq("+isHome+")").attr("tabId");
			$(".gTab .oneTab:eq("+isHome+")").remove();
			$('#tab_' + tabId).remove();
			//$("#dvTab table:eq("+isHome+")").remove();

		}	
	}

	if(isMenu==null){
		isMenu = "";
	}

	//添加tab页
	if($("#dvTab .oneTab").length>0){
		$("#dvTab .oneTab:" + position).after("<div class=\"oneTab\" tabId=\""
				+menuId+"\" paneId = \""
				+paneUrl+"\" isMenu=\""
				+isMenu+"\" title=\""
				+menuName+"\" style=\"WIDTH: 130px; ZOOM: 1\"><div class=\"tLe\">&nbsp;</div><div class=\"bdy\"><nobr>"
				+menuName+"</nobr></div><div class=\"tRi\">&nbsp;</div><div class=\"btnTab\"><a class=\"TabCls\">&nbsp;&nbsp;&nbsp;</a></div></div>");
	}else {
		$("#dvTab").append("<div class=\"oneTab\" tabId=\""
				+menuId+"\" paneId = \""
				+paneUrl+"\" isMenu=\""
				+isMenu+"\" title=\""
				+menuName+"\" style=\"WIDTH: 130px; ZOOM: 1\"><div class=\"tLe\">&nbsp;</div><div class=\"bdy\"><nobr>"
				+menuName+"</nobr></div><div class=\"tRi\">&nbsp;</div><div class=\"btnTab\"><a class=\"TabCls\">&nbsp;&nbsp;&nbsp;</a></div></div>");
	}
	//设置新添加的tab页为选中的css
	var tabBtnSelector = tabSelector+" .btnTab";
	selectTabCss(tabSelector);
	//重新绑定事件
	bindTabClickCss(tabSelector);
	bindTabCloseCss(tabBtnSelector);
	bindTabCloseWindow(tabBtnSelector);
	
	
}


//右侧tab页事件   鼠标点击时更换css
function bindTabClickCss(tabSelector){
  if(tabSelector==undefined){
  		  $(".gTab .oneTab").bind("click",function(){
				tabClickCss(this);
		  })
  }else{
  		$(tabSelector).bind("click",function(){
				tabClickCss(tabSelector);
		})
  }
}
//鼠标点击tab处理  menuid attr("tabId")一一对应
function tabClickCss(tabSelector){
	selectTabCss(tabSelector);
	$(".mMenu").removeClass("mMenu-hover2");
	$(".mMenu").removeClass("mMenu-hover");
	var menuId = "#"+$(tabSelector).attr("tabId");
	///只有和菜单有对应关系的tab才更新css
	var isMenu = "#"+$(tabSelector).attr("isMenu");
	if(isMenu!=null &&  isMenu=='isMenu'){
		$(menuId).addClass("mMenu-hover2");
	}	
}
function selectTabCss(tabSelector){
	$(".gTab .oneTab").removeClass("on");
	$(tabSelector).addClass("on");
	
	//加载内容
	var paneUrl = $(tabSelector).attr("paneId");
//	showMainMsg("#mRight",32,32,"center","loadingImg","","n");
	var tabId = $(tabSelector).attr("tabId");
	
	$(".mRight").hide();
	if($('#tab_' + tabId).size()>0){
		resscrEvt();
	}else{
	  	$("#mRight").clone().attr("id",'tab_' + tabId).insertAfter("#mRight");
	  	
	 
		if(paneUrl.indexOf(".htm")!=-1 ||  paneUrl.indexOf('.jsp')!=-1){
			$('#tab_' + tabId).append( "<iframe  id='if" + tabId + "'  frameborder='0'  />" );
			
			$('#if' + tabId).height( $('#tab_' + tabId).height() )
		              .width( $('#tab_' + tabId).width() )
		              .attr('src',paneUrl);
			resscrEvt();          
		}else{
			loading( EELang.loading );
			$('#tab_' + tabId).load(paneUrl,function(){
				resscrEvt();
				closeWin();
			});
		}
	}
	$('#tab_' + tabId).show();
	//closeWin();
}
//控制tab也上的差号显示
function bindTabCloseCss(tabBtnSelector){
	  if(tabBtnSelector==undefined){
	  		  $(".btnTab").bind("mouseover",function(){
					$(this).children("a").removeClass("TabCls");
					$(this).children("a").addClass("TabClsOver");
			  }).bind("mouseout",function(){
				    $(this).children("a").removeClass("TabClsOver");
					$(this).children("a").addClass("TabCls");
			  })
	  }else{
	  		  $(tabBtnSelector).bind("mouseover",function(){
					$(this).children("a").removeClass("TabCls");
					$(this).children("a").addClass("TabClsOver");
			  }).bind("mouseout",function(){
				    $(this).children("a").removeClass("TabClsOver");
					$(this).children("a").addClass("TabCls");
			  })
	  }
	}

//给差号绑定关闭事件
function bindTabCloseWindow(tabBtnSelector){
  if(tabBtnSelector==undefined){
	  $(".btnTab").bind("click",function(){
			tabCloseWindow(this);
	  })
  }else{
	  $(tabBtnSelector).bind("click",function(){
			tabCloseWindow(tabBtnSelector);
	  })
  }
}
//关闭tab
function tabCloseWindow(tabBtnSelector){
	
	var tabId = $(tabBtnSelector).parents(".oneTab").attr("tabId");
	$(tabBtnSelector).parents(".oneTab").remove();
	$('#tab_' + tabId).remove();
	
	if($(".on").length==0){
				//如果没有被选中的tab页，则选中最后一个
				$(".mMenu").removeClass("mMenu-hover");
				//如果只有一个tab页   选中首页
				if($(".gTab .oneTab:eq(1)").length>0){
					selectTabCss(".gTab .oneTab:eq(1)");
				}else{
					selectTabCss(".gTab .oneTab:eq(0)");
				}
				//菜单跟tab同步    最后一个tab选中后  对应的菜单也要选中
				var menuId = "#"+$(".gTab .oneTab:eq(1)").attr("tabId");
				var isMenu = "#"+$(".gTab .oneTab:eq(1)").attr("isMenu");
				if(isMenu!=null &&  isMenu=='isMenu'){
					$(menuId).addClass("mMenu-hover");
				}	
			}
}

var workbenchPath = "";
function loadWorkbench(path){
	

	if($("#tab_workbench_container").size()>0){
		$(".mRight").hide();
		$("#tab_workbench_container").show();
	}else{
		if(path==null || path==undefined || path==""){
			return;
		}
		workbenchPath = path;
	  	$("#mRight").clone().attr("id",'tab_workbench_container').insertAfter("#mRight");
		$("#tab_workbench_container").load(globalURL + workbenchPath);
		$("#mRight").hide();
		$("#tab_workbench_container").show();

//		$("#mRight").load(globalURL + "web/default/workbench/workbench.jsp");
	}
	
	$(".gTab .oneTab").removeClass("on");
	$(".oneTab[tabid='workbench_container']").addClass("on");
}


function closeWin(){
//	$("#fullBg").css("display","none");
//	$(".alertClose").parent("#msg").css("display","none");
	try{
		$("#main_msg").remove();
		$("#fullBg").remove();
	}catch(e){
		
	}	
}
/*****************************************遮罩层***************************************************/
/*
position:遮罩层显示在哪个区域，例如显示在aa  div里面：#aa 就可以
msgW:弹出框的宽
msgH:弹出框的高
align:显示位置  left,right,center  目前有三个值
type:类型，loading和loadingImg,登录现在用的是loading,loadingImg是一个图片，代表加载内容.如果自定义则给空值.
content:弹出框里面得内容，自定义
isClose:是否有关闭按钮
*/
//开启遮罩

function loading(aMsg,position){

    var aPos = "body";
    if(position){
    	aPos = position;
    }
    var msgLen = 150;
    if(aMsg){
    	 msgLen = aMsg.length * 15 ;
    }
	if(aMsg!=null){
		showMainMsg(aPos,msgLen,16,"center","sef_defined","<div>&nbsp;&nbsp;" +aMsg + "</div>","n");
	}else{
		showMainMsg(aPos,msgLen,16,"center","loading","","n");
	}	

}

function showMainMsg(position,msgW,msgH,align,type,content,isClose){
	
	if($("#main_msg").size() == 0){
		$("body").append("<DIV id=fullBg style='z-index: 199999;position:absolute'></DIV><DIV style='position: fixed;z-index: 299999;margin:0;padding:2px;' id=main_msg></DIV>");
	}else{
		$("#main_msg").empty();
	}
	if(type=="loading"){
		content="<div >&nbsp;&nbsp;" + EELang.loading + "</div>";
	}else if(type=="loadingImg"){
		content= "<div class=index-loading></div>";
	}
	
	
	if(align=="left"){
		$("#main_msg").css({top:$(position).offset().top,left:$(position).offset().left,width:msgW,height:msgH});
	}else if(align=="center"){
		$("#main_msg").css({top:$(position).offset().top+($(position).height()-msgH)/2,left:$(position).offset().left+($(position).width()-msgW)/2,width:msgW,height:msgH});
	}else if(align=="right"){
		$("#main_msg").css({top:$(position).offset().top,left:$(position).offset().left+$(position).width()-msgW-5,width:msgW,height:msgH});
	}
	
	
	$("#fullBg").css({top:$(position).offset().top,left:$(position).offset().left,width:$(position).width(),height:$(position).height()});
	//显示关闭按钮
	if(isClose=="y"||isClose=="Y"){
		var closeImg="<div class='alertClose'></div>";
		$("#main_msg").append(closeImg);
	}
	
	$("#main_msg").append(content);
	
	//关闭按钮事件
	if(isClose=="y"||isClose=="Y"){
		$(".alertClose").bind("click",function(){
			closeWin();
		})
	}
}



/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
/*****************************编码处理***************************************************************/
//UrlCode 处理代码
function urlCodeDeal(str){
	if(str.length==0||null==str){
		return "";
	}
	var paras = new Array();
	paras = str.split('&');
	var result ="";
	for(var i = 0; i < paras.length; i++){
	    var name_V =   new Array();
	    name_V = paras[i].split('=');
	    if(name_V[0]==''){
	    	continue;
	    }
	    if(i==0){
	    	result += name_V[0]+"=";
	    }else{
	    	result +="&" + name_V[0]+"=";
	    }
	    if(name_V.length>1){
	    	//之前空格被用+替换了, 参数中加号用空格替换回来 
	    	result += encodeURIComponent(escape(decodeURIComponent(name_V[1].split("+").join("%20"))));  
	    }
	}
	return result;
}


function pageSplitDeal(str,pageNo,pageSize){
	if(pageNo==null || pageSize==null){
		return "";
	}
	if(str == null || str==undefined){
		str = "";
	}
	if(/pageNo=[0-9]*/gi.test(str)){
		str = str.replace(/pageNo=[0-9]*/gi,"pageNo=" + pageNo);
	}else{
		str = str + "&pageNo=" + pageNo;
	}
	if(/pageSize=[0-9]*/gi.test(str)){
		str = str.replace(/pageSize=[0-9]*/gi,"pageSize=" + pageSize);
	}else{
		str = str + "&pageSize=" + pageSize;
	}
	return str;

}


/*****************************************弹出层代码******************************************/
function popupDialog(id,title,href,width,height){

	    if(width==null || width==""){
	    	width = 800;
	    }
	    if(height==null || height==""){
	    	height = 450;
	    }
	    
	    if($('#F' + id).size()==0){
			createFloatDiv(id);
	    }
		$('#F' + id).dialog({
			autoOpen: false,
			title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='icon-info'></i>" + title + "</h4></div>",
			title_html: true,
			height: height,
			width: width,
			modal: true,
			resizable:true,
			close: function(event, ui) {
				 $("#dmLayer").hide();
				 $('#F' + id).empty();
			}
		});

		$('#F' + id).load(href +"&eeplatdialog=true" );
		$( '#F' + id ).dialog( "open" );
//		
//		$(  '#F' + id ).dialog({
//			open: function( event, ui ) {
//				$('#F' + id).children("widget-main").unwrap().unwrap();
//			}
//		});
		
}

function createFloatDiv(id,title) {

    var htmlStr = "<div id='F" + id  + "'  class='hide'></div> \n";
	$(document.body).append(htmlStr); 
}


/*****************************************弹出层代码OLD******************************************/
function popupDialog2(id,title,href,width,height){


		createFloatDiv(id,title);
		var t = $('#jqmC' + id);
		$('#F' + id).jqm({
			ajax: href,
			target: t,
			modal: true, 
			onLoad: function(){closeWin();},
			onHide: function(h) { 
						t.html('Please Wait...');  // Clear Content HTML on Hide.
						h.o.remove(); // remove overlay
						h.w.fadeOut(0); // hide window
					    if($("#F" + id).length > 0){
						       $("#F" + id).remove();
						}
					},
			overlay: 0}).jqDrag('.jqmdTC').jqResize('.jqHandle'); 
		
	     if(width!=null && width>0){
	    	 $('#F' + id).width(parseInt(width));
	     }
	     if(height!=null && height>0){
	    	 $('#F' + id).height(parseInt(height));
	     }
		
	     $('#F' + id).jqmShow();
		 if($(".jqmDialog").length > 1){
			 var oldTop = $(".jqmDialog").offset().top;
			 if(oldTop < 0){
				 oldTop = 20;
			 }
	         $('#F' + id).css("top",oldTop + 20 );
			 $('#F' + id).css("left",$(".jqmDialog").offset().left + 20 +$(".jqmDialog").width()/2);
		 }
		 loading(null,'#F' + id);
}
function createFloatDiv2(id,title) {
	     var htmlStr = "<div id='F" + id  + "' class='jqmDialog'> \n"
 		+" <div class='jqmdTL'><div class='jqmdTR'><div class='jqmdTC'>" + title + "</div></div></div> \n"
 		+" <input type='image' src='" +globalURL +"web/default/js/jquery-plugin/dialog/close.gif' class='jqmdX jqmClose' /> \n"
		+" <div class='jqmContent' id='jqmC" + id + "'> \n"
		+" <p>Please wait... <img src='" +globalURL +"web/default/js/jquery-plugin/dialog/busy.gif' alt='loading' /></p>  \n </div> \n"
		+"	<div class='jqHandle'></div>  \n  </div> \n";
		$(document.body).append(htmlStr); 
}



/**
 * 刷新树 
 * @param type =1 刷新 选中的节点，否则刷新 选中节点的上层
 * @return
 */
function reloadTree(type){
	  try{
		if(webFXTreeHandler==null || webFXTreeHandler.selected==null){
			return;
		}
		oldSelectName = webFXTreeHandler.selected.text;
	    if(type == 1){  
	    	if(webFXTreeHandler.selected.src!=null && webFXTreeHandler.selected.src!=''){
	    			webFXTreeHandler.selected.reload();
	    	}
	    }else{
	    	if(webFXTreeHandler.selected.parentNode.src!=null && webFXTreeHandler.selected.parentNode.src!=''){
	    		webFXTreeHandler.selected.parentNode.reload();
	    	}else{
	    		if(webFXTreeHandler.selected.parentNode.parentNode.src!=null
	    				&& webFXTreeHandler.selected.parentNode.parentNode.src!=''){
	    			webFXTreeHandler.selected.parentNode.parentNode.reload();
	    		}	
	    	}	
	    }
	  }catch(e){
	  
	  }
}


/**
 * is mobile
 */

function isMobile(){
	

	// If the screen orientation is defined we are in a modern mobile OS
	var mobileOS = typeof orientation != 'undefined' ? true : false;
	
	if(mobileOS){
		return true;
	}
	
	var android = (navigator.platform.indexOf("android")>=0);
	
	var iPhone = (navigator.platform.indexOf("iPhone")>=0);
	
	var iPod = (navigator.platform.indexOf("iPod")>=0);
	
	var iPad = (navigator.platform.indexOf("iPad")>=0);
	
	var symbian = (navigator.platform.indexOf("symbian")>=0);
	
	var series60 = (navigator.platform.indexOf("series60")>=0);	
	
	var BlackBerry = (navigator.platform.indexOf("BlackBerry")>=0);	


	return android || iPhone || iPod || iPad || symbian || series60 || BlackBerry;
}



/**
 * 工作流节点权限相关
 */
function insertAuthPt(){
	var selectedNode = window.opener.selectedNodeBak;
	var whatuid = selectedNode.getAttribute('id');
	var ouuid = $("#gm_do_authorization_insert_ptnode_role_ouuid").val();
	callService({'serviceName':'u_role_ptnode',paras:"whatuid=" + whatuid + "&ouuid="+ouuid,'pml':'PM_do_org_role_of_ptnode','target':'PM_do_org_role_of_ptnode'}  );
	
	$('#FPM_do_authorization_insert_ptnode_role').jqmHide();
	
}

/**
 * 代码编辑相关
 */
function insertAceCode(){
	
	var name = $("input[name=propertyuid]").val();
	
	if(name==null || name==''){
		alert(EELang.nameRequired);
		return;
	}
	var isValid = 1;
	var validChecks = $("input[name=icon]:checked");
	if(validChecks.length > 0){
		isValid = $("input[name=icon]:checked").val();
	}
	
	var theCode  = '';
	var hidden_type = $("input[name=mVersion]").val();
	
	if(hidden_type==null){
		hidden_type = 'js';
	}
	
	if($.browser.msie){
		
	    if(mirrorEditor){
		    mirrorEditor.save();
		    theCode = mirrorEditor.getValue();
	    }else   if(mirrorEditor2){
	    	mirrorEditor2.save();
	    	theCode = mirrorEditor2.getValue();
	    }
	}else{

		if(hidden_type=='js'){
			theCode = jsEditor.getSession().getValue();
		}else if(hidden_type=='css'){
			theCode = cssEditor.getSession().getValue();
		}else if(hidden_type=='rhino'){
			theCode = rhinoEditor.getSession().getValue();
		}else if(hidden_type=='html'){
			theCode = htmlEditor.getSession().getValue();
		}
	}
	
	
	var objuid = $("input[name=objuid]").val();
	

	var serviceName = "DO_BO_Icon_Insert";
	if(objuid){
		serviceName = "DO_BO_Icon_Update";
	}
	callService({'serviceName':serviceName,'callType':'uf', 'callback':alert(EELang.saveSuccess), 
		paras:"propertyuid=" + name + "&formulascript=" + encodeURIComponent(theCode) + "&icon=" + isValid + "&mVersion="+hidden_type}  );
}


function  confirmDelete(){
	  
	return confirm(EELang.confirmDelete);
}

function confirmShare(){
	
	return confirm(EELang.confirmShare);
	
}

function confirmSetup(){
	
	return confirm(EELang.confirmSetup);
}

function confirmCopy(){
	
	return confirm(EELang.confirmCopy);
}


function confirmInit(){
	
	return confirm(EELang.confirmInit);
}

function confirmGenerate(){
	
	return confirm(EELang.confirmGenerate);
}

function confirmServiceToRule(){
	
	return  confirm(EELang.confirmServiceToRule);
}

function confirmImport(){
	return confirm(EELang.confirmImport);
}

function confirmRemove(){
	
	return confirm(EELang.confirmRemove);
}

function toDecimal2(x) {    
    var f = parseFloat(x);    
    if (isNaN(f)) {    
        return false;    
    }    
    var f = Math.round(x*100)/100;    
    var s = f.toString();    
    var rs = s.indexOf('.');    
    if (rs < 0) {    
        rs = s.length;    
        s += '.';    
    }    
    while (s.length <= rs + 2) {    
        s += '0';    
    }    
    return s;    
}  

function labelFormatter(label, series) {
	return "<div style='font-size:8pt; text-align:center; padding:2px; color:white;'>" + label + "<br/>" + Math.round(series.percent) + "%</div>";
}


function activeNavLi(obj,isChild){
	
	$("#sidebar .nav >li").removeClass("active");
	if(isChild){
		$("#sidebar .submenu >li").removeClass("active");
		$(obj).parent().parent().parent().addClass("active");
	}
	$(obj).parent().addClass("active");
	
}

function eeplatTabs(){

//	$(".nav-tabs > i").on('click', function(ev) {
//		console.log("Click LI===========================");
//		$(this).parents("li").remove();
//		$("#pc" + $(this).parent().attr("pmlName")).remove();
//		ev.stopPropagation();
//	});
	
	$(".nav-tabs").delegate('i' , 'click', function(ev) {
		////处理Home页面
		if("_opener_tab" == $(this).parent().attr("pmlName")){
			return;
		}
		$(this).parents("li").remove();
		$("#pc" + $(this).parent().attr("pmlName")).remove();
		$(".nav-tabs>li:last>a").trigger("click");
		
	});
	$(".nav-tabs").delegate('a' , 'click', function(ev) {
		if($("#pc" + $(this).attr("pmlName")).length > 0){
			$(".page-content").hide();
			$("#pc" + $(this).attr("pmlName")).show();
	    }
		////处理Home页面
		if("_opener_tab" == $(this).attr("pmlName")){
			$(".page-content").hide();
			$("#_opener_tab").show();
		}
	});
}


function  createNewTab(pmlName,title,thisPml){
	

	$(".nav-tabs > li").removeClass();
	
	
	///home
	if("_opener_tab" == pmlName){
		$(".page-content").hide();
		$("#_opener_tab").show();
		return;
	}
	
	if($("#li" + pmlName).length==1){
		//如果这个tab已经存在，则设置成选中的css
		$("#li" + pmlName).addClass("active");
		$(".page-content").hide();
		$("#pc" + pmlName).show();	
		return;
	}

	if($(".nav-tabs > li").length ==6){
		$(".nav-tabs > li:eq(1)").remove();
		console.log(".nav-tabs > li:eq(1)>a:::" + $(".nav-tabs > li:eq(1)>a").attr("pmlName")  );
		$("#pc" + $(".nav-tabs > li:eq(1)>a").attr("pmlName")).remove();
	}
	
	$("<li  class='active' id='li" 
	+	pmlName	+ "'><a data-toggle='tab' pmlName='" 
	+   pmlName	+ "' href='#'><span class='menu-text'> "
	+  title +		
	" </span><i class='icon-remove'></i></a></li> ").appendTo(".nav-tabs");
	
	$(".page-content").hide();
	
	loading();
	$(" <div  class='page-content' id='pc" + pmlName +  "'> </div>").appendTo(".main-content").load(thisPml
	 ,function(){
		
		closeWin();
	}		
	);
	
	
}



function changeDefaultApp(appId){
	var url = globalURL + "/servicecontroller?dataBus=setUserContext&contextKey=default_app_uid&contextValue=" +appId;
	$.get(url);
}


//override dialog's title function to allow for HTML titles
$.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
	_title: function(title) {
		var $title = this.options.title || '&nbsp;'
		if( ("title_html" in this.options) && this.options.title_html == true )
			title.html($title);
		else title.text($title);
	}
}));



function widgetBoxes(selector) {

	$("#" + selector).delegate('.widget-toolbar > [data-action]' , 'click', function(ev) {
		ev.preventDefault();

		var $this = $(this);
		var $action = $this.data('action');
		var $box = $("#" +  selector);

		if($box.hasClass('ui-sortable-helper')) return;

		if($action == 'collapse') {
			var $body = $box.find('.widget-body');
			var $icon = $this.find('[class*=icon-]').eq(0);
			var $match = $icon.attr('class').match(/icon\-(.*)\-(up|down)/);
			var $icon_down = 'icon-'+$match[1]+'-down';
			var $icon_up = 'icon-'+$match[1]+'-up';
			
			var $body_inner = $body.find('.widget-body-inner')
			if($body_inner.length == 0) {
				$body = $body.wrapInner('<div class="widget-body-inner"></div>').find(':first-child').eq(0);
			} else $body = $body_inner.eq(0);


			var expandSpeed   = 300;
			var collapseSpeed = 200;

			if($box.hasClass('collapsed')) {
				if($icon) $icon.addClass($icon_up).removeClass($icon_down);
				$box.removeClass('collapsed');
				$body.slideUp(0 , function(){$body.slideDown(expandSpeed)});
			}
			else {
				if($icon) $icon.addClass($icon_down).removeClass($icon_up);
				$body.slideUp(collapseSpeed, function(){$box.addClass('collapsed')});
			}
		}
		else if($action == 'close') {
			var closeSpeed = parseInt($this.data('close-speed')) || 300;
			$box.hide(closeSpeed , function(){$box.remove()});
		}
		
	});
}


//custom autocomplete (category selection)
$.widget( "custom.eeplatcombox", $.ui.autocomplete, {
	
	_create: function() {
		this._super();
		var that = this;
		this.element.parent().delegate('.popupimg' , 'click', function(ev) {
			ev.preventDefault();
			that._search();
		});
		
		this.element.parent().delegate('.popupimg2' , 'click', function(ev) {
			ev.preventDefault();
			var popconfig = $(this).attr("popconfig");
			var popArray = popconfig.split(",");
			invokeDomId = that.element.prevAll(".resultlistpopup").attr("id");
			autoPopupPml(popArray[0],popArray[1],popArray[2],popArray[3],popArray[4],popArray[5])
		});
		
		this.element.tooltip({
			tooltipClass: "ui-state-highlight"
		});
		

		
		this.menu.element.off("menufocus");
		this.menu.element.off("menuselect");
		
		this._on( this.menu.element, {
			
			menufocus: function( event, ui ) {
				// support: Firefox
				// Prevent accidental activation of menu items in Firefox (#7024 #9118)
				if ( this.isNewMenu ) {
					this.isNewMenu = false;
					if ( event.originalEvent && /^mouse/.test( event.originalEvent.type ) ) {
						this.menu.blur();

						this.document.one( "mousemove", function() {
							$( event.target ).trigger( event.originalEvent );
						});

						return;
					}
				}

				var item = ui.item.data( "ui-autocomplete-item" );
				if ( false !== this._trigger( "focus", event, { item: item } ) ) {
					// use value to match what will end up in the input, if it was a key event
					if ( event.originalEvent && /^key/.test( event.originalEvent.type ) ) {
						this._value( item.label );
						this.element.prevAll(".resultlistpopup").val(item.value);
						///执行onchange
						if(this.element.attr('changejs')){
							eval(this.element.attr('changejs'));
						}
						//// 配合TESaveMulti控制器
						$(this.element).parent().parent().parent().attr("edit","true");	
					}
				} else {
					// Normally the input is populated with the item's value as the
					// menu is navigated, causing screen readers to notice a change and
					// announce the item. Since the focus event was canceled, this doesn't
					// happen, so we update the live region so that screen readers can
					// still notice the change and announce it.
					this.liveRegion.text( item.label );
				}
			},
			menuselect: function( event, ui ) {
				var item = ui.item.data( "ui-autocomplete-item" ),
					previous = this.previous;
	
				// only trigger when focus was lost (click on menu)
				if ( this.element[0] !== this.document[0].activeElement ) {
					this.element.focus();
					this.previous = previous;
					// #6109 - IE triggers two focus events and the second
					// is asynchronous, so we need to reset the previous
					// term synchronously and asynchronously :-(
					this._delay(function() {
						this.previous = previous;
						this.selectedItem = item;
					});
				}
	
				if ( false !== this._trigger( "select", event, { item: item } ) ) {
					this._value( item.label );
					this.element.prevAll(".resultlistpopup").val(item.value);
					///trigger
					///执行onchange
					if(this.element.attr('changejs')){
						eval(this.element.attr('changejs'));
					}
					//// 配合TESaveMulti控制器
					$(this.element).parent().parent().parent().attr("edit","true");	
				}
				// reset the term after the select event
				// this allows custom select handling to work properly
				this.term = this._value();
	
				this.close( event );
				this.selectedItem = item;
			}
		});
	},
	
	_initSource: function() {
		
		
		var serviceName = this.element.attr("serviceName");
		var that = this;
		var aFormName = that.element.attr("formName");
		
		this.source = function( request, response ) {
			

			var url = globalURL + "servicecontroller?contextServiceName=" + serviceName +"&callType=ssp";

			var paras = "";
			if(aFormName!=null && aFormName!=""){
				paras = $("#"+aFormName).serialize();	
			}

				try{
					var rs = that.element.parent().parent().parent().parent().parent().find('.resultlistpopup');
					if(rs.length==1){
						rs = that.element.parent().parent().parent().parent().parent().parent().find('.resultlistpopup');
					}
					var  not2This = true;
					 if(rs.length > 1){
						 rs.each(function(i){
							    if(not2This){
								 	if($(rs.get(i)).attr("id") ==   that.element.prevAll(".resultlistpopup").attr("id")){
								 		not2This = false;
								 	}
								 	var o = $(rs.get(i));
								 	if( o.attr('name')!=null){
								 		paras = paras + "&" + o.attr('name')+"="+o.val();
								 	}
						 		}
						 	}
						 );		
						 
					 } 
				}	 
				catch(e){
				  alert(e);	
				}	 


			paras = urlCodeDeal(paras);
			

			////如果sc_page_size设为1000及以上就不分页了。
		
			url = url + "&sc_page_no="
					+ 1+"&sc_page_size=" + 200 +  paras;
		
			
				that.xhr = $.ajax({
					url: url,
					data: request,
					dataType: "json",
					success: function( data ) {
						
					   if(data.success=='false' && ret.msg.indexOf("err001")!=-1){
							   window.location = globalURL + "web/default/logoff.jsp";
					    }
						try{
							if(data.items.length==0){
								that.element.prevAll(".resultlistpopup").val('');
								that.element
								.val( "" )
								.attr( "title", EELang.noData )
								.tooltip( "open" );
								
								that._delay(function() {
									that.element.tooltip( "close" ).attr( "title", "" );
								}, 600 );
								return;
							}
						}catch(e){
							
						}
						var  emptyItem = {"label":"------","value":""};
						data.items.unshift( emptyItem );
						response(  data.items );
					},
					error: function() {
						response( [] );
					}
				});
			};

	}
});


function autoPopupPml(linkPaneName,pmlWidth,pmlHeight,title,formName,target){
	

		var p = {};
		p.pml = linkPaneName;
		if(pmlWidth!=null && pmlWidth!=''){
			p.pmlWidth = pmlWidth;
		}
		if(pmlHeight!=null && pmlHeight!=''){
			p.pmlHeight = pmlHeight;
		}
		
		if(title!=null && title!=''){
			p.title = title;
		}
		
		if(formName!=null && formName!=''){
			p.formName = formName;
		}
		
		if(target!=null && target!=''){
			p.target = target;
		}
		
		loadPml(p);
 }

function generateUUID(){
    var d = new Date().getTime();
    var uuid = 'xxxxxxxxxxxx4xxxyxxxxxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
        var r = (d + Math.random()*16)%16 | 0;
        d = Math.floor(d/16);
        return (c=='x' ? r : (r&0x7|0x8)).toString(16);
    });
    return uuid;
}




function  gridCheck(isCheck,o,selector,e){

	if(!isCheck){
		$(o).parent().parent().removeClass("selected");
		if($(selector +  ' .selected').size()==0){
			$(selector + ' .list_check:checked:first').parent().parent().addClass("selected");				
		}
		if( $(o).attr("eeplatselected")){
			console.log("unselected!");
			 $(o).addClass("removevaluee");				
		}else{
			 $(o).removeClass("addvaluee");				
		}				
		e.stopPropagation();
	}
	else{
		if( $(o).attr("eeplatselected") == null ){
			console.log("selected!");
			 $(o).addClass("addvaluee");				
		}else{
			$(o).removeClass("removevaluee");
		}
	}

}


var O2String = function (O) {
	
	if(JSON){
		return JSON.stringify(O);
	}
	
    var S = [];
    var J = "";
    if (Object.prototype.toString.apply(O) === '[object Array]') {
        for (var i = 0; i < O.length; i++)
            S.push(O2String(O[i]));
        J = '[' + S.join(',') + ']';
    }
    else if (Object.prototype.toString.apply(O) === '[object Date]') {
        J = "new Date(" + O.getTime() + ")";
    }
    else if (Object.prototype.toString.apply(O) === '[object RegExp]' || Object.prototype.toString.apply(O) === '[object Function]') {
        J = O.toString();
    }
    else if (Object.prototype.toString.apply(O) === '[object Object]') {
        for (var i in O) {
            O[i] = typeof (O[i]) == 'string' ? '"' + O[i] + '"' : (typeof (O[i]) === 'object' ? O2String(O[i]) : O[i]);
            S.push(i + ':' + O[i]);
        }
        J = '{' + S.join(',') + '}';
    }
    return J;
};

function toggleMore(obj){

    var a = $(obj).parent().parent().nextAll(":not(.buttonMore)");
	a.toggle(a.css('display') == 'none');
	var html = $(obj).text()==EELang.more ? "<b>" + EELang.less +"</b>" : "<b>" +  EELang.more  + "</b>";
	$(obj).html(html);
}
