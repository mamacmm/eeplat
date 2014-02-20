package com.zhiliaoshu.mobile.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.SessionContext;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.util.DOGlobals;

public class DataAction extends DOAbstractAction {

	@Override
	public String excute() throws ExedoException {
		BOInstance resultInstance = new BOInstance();

		SessionContext sessionContext = DOGlobals.getInstance()
				.getSessoinContext();
		BOInstance form = sessionContext.getFormInstance();
		String classId = form.getValue("classId");
		String babyId = form.getValue("babyId");
		String userId = form.getValue("userId");
		int scope_day = (int) form.getIntValue("scope");//时间范围

		DOService doService;
		List<BOInstance> list;
		BOInstance userInfo;

		List<String> lessonList = new ArrayList<String>();
		List<String> menuList = new ArrayList<String>();
		List<String> msgList = new ArrayList<String>();
		List<String> reportList = new ArrayList<String>();
		List<String> commentList = new ArrayList<String>();

		// 加载一定时间范围的数据
		Calendar beginDate = Calendar.getInstance();
		beginDate.add(Calendar.DATE, -scope_day);
		String beginStr = getDateStr(beginDate);
		//加载课程，以开始时间为准
		doService = DOService.getService("kids_dailylesson_getbybegindate");
		for (Object boInstance : doService.invokeSelect(beginStr, classId)) {
			lessonList.add(((BOInstance) boInstance).toJSONObject().toString());
			System.out.println("*****lessonList:"
					+ ((BOInstance) boInstance).getMap());
		}
		//加载餐饮，以开始时间为准
		doService = DOService.getService("kids_dailymenu_getbybegindate");
		for (Object boInstance : doService.invokeSelect(beginStr,classId)) {
			menuList.add(((BOInstance) boInstance).toJSONObject().toString());
			System.out.println("*****menuList:"
					+ ((BOInstance) boInstance).getMap());  
		}
		//加载表现，以开始时间为准
		doService = DOService.getService("kids_dailyreport_getbybegindate");
		for (Object boInstance : doService.invokeSelect(beginStr,babyId)) {
			reportList.add(((BOInstance) boInstance).toJSONObject().toString());
			System.out.println("*****reportList:"
					+ ((BOInstance) boInstance).getMap());
		}
		//加载消息，以开始时间为准
		doService = DOService.getService("kids_message_getbybeginDate");
		for (Object boInstance : doService.invokeSelect(userId,beginStr)) {
			msgList.add(((BOInstance) boInstance).toJSONObject().toString());
			
			System.out.println("*****msgList:"
					+ ((BOInstance) boInstance).getMap());
		}
		//加载回复，以开始时间为准
		doService = DOService.getService("kids_comment_getbybeginDate");
		for (Object boInstance : doService.invokeSelect(userId,beginStr)) {
			commentList.add(((BOInstance) boInstance).toJSONObject().toString());
			System.out.println("*****commentList:"
					+ ((BOInstance) boInstance).getMap());
		}

		// 把查询到的数据放进返回数据中
		resultInstance.putValue("lesson", lessonList);
		resultInstance.putValue("menu", menuList);
		resultInstance.putValue("message", msgList);
		resultInstance.putValue("report", reportList);
		resultInstance.putValue("comment", commentList);

		List bis = new ArrayList();
		bis.add(resultInstance);
		this.setInstances(bis);
		// this.setInstance(instance);
		return DEFAULT_FORWARD;
	}

	/**
	 * 获取日期字符串
	 * 
	 * @param calendar
	 * @return 格式：2010-09-10
	 */
	public String getDateStr(Calendar calendar) {
		StringBuffer dateSb = new StringBuffer();
		dateSb.append(calendar.get(Calendar.YEAR)).append("-")
				.append(addZero(calendar.get(Calendar.MONTH) + 1)).append("-")
				.append(addZero(calendar.get(Calendar.DAY_OF_MONTH)));
		return dateSb.toString();
	}

	/**
	 * @param i
	 * @return 在大于0小于10的数前面加0
	 */
	public String addZero(int i) {
		if (-1 < i && i < 10) {
			return "0" + i;
		}
		return i + "";

	}

}
