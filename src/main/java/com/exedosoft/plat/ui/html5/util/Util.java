package com.exedosoft.plat.ui.html5.util;

import java.util.List;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;

public class Util {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/**
	 * 通过身份性别获取头像
	 * @param args
	 */
	public static String getTx(String gender,String type) {
		String strImgTx = "teacher_man.png";
		String strTeacher = "teacher";
		String strFamily = "family";
		String strMan = "man";
		String strWoman = "woman";
		String strImgType = ".png";
		
		if(type.equals("teacher")){
			strImgTx = strTeacher;
			if(gender.equals("man")){
				strImgTx += "_" + strMan + strImgType;
			}else{
				strImgTx += "_" + strWoman + strImgType;
			}
		}else if(type.equals("family")){
			strImgTx = strFamily;
			if(gender.equals("man")){
				strImgTx += "_" + strMan + strImgType;
			}else{
				strImgTx += "_" + strWoman + strImgType;
			}
		}
		return strImgTx;
	}
	/**
	 * 通过身份获取角色
	 * @param args
	 */
	public static String getRole(String persontype,String teachertype) {
		String strRet = "家长";
		if(persontype.equals("teacher")){
			if(teachertype.equals("3")){
				strRet = "园长";
			}else{
				strRet = "老师";
			}
		}else if(persontype.equals("family")){

		}
		return strRet;
	}
}
