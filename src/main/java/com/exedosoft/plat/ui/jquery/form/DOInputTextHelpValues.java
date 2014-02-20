package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;

//
//表单中只有一个文本框时，回车页面刷新错误
//
//在一个也没中如果一个form标签中只有文本框<input type="text" />,当在输入完数据后点击回车，会发现页面进行了刷新，代码如下：
//Html代码  收藏代码
//
//    <body>      
//            <form>  
//                <input type="hidden" id="contextPath" name="contextPath" value="<%=request.getContextPath()%>" />  
//                <textarea rows="2" cols="2" name="test"></textarea>  
//                <input type="text" name="noticeNo" id="noticeNo"/>  
//            </form>         
//    </body>  
//
//有如下解决方法：
//
//1.在文本域元素中加入onkeydown或者onkeypress事件，判断如果用户点击了回车就阻止默认的行为。
//Java代码  收藏代码
//
//    <body>          
//            <form>  
//                <input type="textsdfsd" name="noticeNo"  onkeypress="if(event.keyCode==13||event.which==13){return false;}" />      
//            </form>  
//    </body>  
//
// 
//
//2.在form中在加入一个隐藏的文本域
//
//<input type="text" name="test" style="display:none"/>
//Html代码  收藏代码
//
//    <body>      
//            <form>  
//                <input type="hidden" id="contextPath" name="contextPath" value="<%=request.getContextPath()%>" />  
//                <textarea rows="2" cols="2" name="test"></textarea>  
//                <input type="text" name="noticeNo" id="noticeNo"/>  
//                <input type="text" name="test" style="display:none"/>  
//            </form>         
//    </body>  
//
// 说明：大家可以发现，里面是没有提交按钮的即
//
//<input type="sumit" />,要是里面有提交按钮的话，第二种方法时不使用的，只能使用第一种方法，因为通过查看你会发现，当你点击一个表单时，或者表单的任何元素会发现，提交按钮时激活状态，所以单点击回车时就执行了提交操作。


public class DOInputTextHelpValues extends DOInputText {
	
	public DOInputTextHelpValues(){
		super();
	}

	@Override
	public String getHtmlCode(DOIModel aModel) {
		
		
		if(isUsingTemplate){
			return super.getHtmlCode(aModel);
		}


		DOFormModel property = (DOFormModel) aModel;
		StringBuffer buffer = getInputTextStr(property);
		DOTextAreaHelpValues.appendHelps(property, buffer);

		return buffer.toString();
	}

	
	
	public static void main(String[] args){
		
		DOFormModel fm = DOFormModel.getFormModelByID("636f5ca21e864c18835150a787d8c1bc");
		System.out.println("Is not null:::" + fm.isNotNull());

	}

}
