package com.exedosoft.plat.ui.customize.tools;

import java.io.File;
import java.net.URL;
import java.util.List;

import com.exedosoft.plat.bo.DODataSource;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.jquery.form.DOStaticList;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.StringUtil;

/**
 * 静态列表应该单独作为一个表
 * @author IBM
 *
 */
public class DOStaticListInputConfigFromValue extends DOStaticList {

	public DOStaticListInputConfigFromValue() {
		super();
	}

	public String getHtmlCode(DOIModel iModel) {
		

		
		URL url = DODataSource.class.getResource("/globals.xml");
		String s = url.getPath();
		String s2 = s;
		s = s.substring(0, s2.toLowerCase().indexOf("web-inf"));
		// /处理空格
		s.replace("20%", " ");
		
		s = s + "web/template";
	
		File templatePath = new File(s);
		
		StringBuffer filestr = new StringBuffer();
		
		////首先进行扫描 
		////扫描不到直接去globals.xml 中查看定义,weblogic 部署方式有可能无法拿到。
		
		if(DOGlobals.getValue("web.plugins")!=null){
			filestr = filestr.append(DOGlobals.getValue("web.plugins"));
		}
		
		else if(templatePath.exists()){
			File[]  files = templatePath.listFiles();
			for(int i = 0; i < files.length ; i++){
				
				File aFile = files[i];
				if(aFile.isDirectory()){
					System.out.println("aFile.getName():::::::::" + aFile.getName());
					filestr.append(aFile.getName());
					if( i  < (files.length-1)){
						filestr.append(";");
					}
				}
			}
		}
		DOFormModel property = (DOFormModel) iModel;

		List list = StringUtil.getStaticList(filestr.toString());
		
		

		return formSelectStr(property, list);
	}
	
	public static void main(String[] args){
		
		
		
		
		
	}
}

	
