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

public class LoginAction extends DOAbstractAction {

	@Override
	public String excute() throws ExedoException {
		BOInstance resultInstance = new BOInstance();

		SessionContext sessionContext = DOGlobals.getInstance()
				.getSessoinContext();
		BOInstance form = sessionContext.getFormInstance();
		String phoneStr = form.getValue("a");  
		if (phoneStr == null)
			phoneStr = form.getValue("linkphone");
		boolean isTeacher = Boolean.valueOf(form.getValue("isTeacher"));
		boolean isFirstLogin = Boolean.valueOf(form.getValue("isFirstLogin"));

		DOService doService;
		List<BOInstance> list;
		BOInstance userInfo;
		if (isTeacher) {
			doService = DOService.getService("kids_teacher_loginbyphone");
			list = doService.invokeSelect(phoneStr);
		} else {
			doService = DOService.getService("kids_family_loginbyphone");
			list = doService.invokeSelect(phoneStr);
		}

		if (list.size() > 0) {
			userInfo = list.get(0);
			System.out.println(userInfo.getMap());

			resultInstance.putValue("userinfo", userInfo.getMap());
			// resultInstance.putValue("userinfo", new
			// JSONObject(userInfo.getMap()).toString());
		} else {
			// 登录失败
			return "";
		}

		String userId = userInfo.getValue("objuid");
		String classId = userInfo.getValue("classuid");
		String babyId = userInfo.getValue("babyuid");

		if (!isFirstLogin) { // 如果不是第一次登录则去加载未推送的数据
			List<String> lessonList = new ArrayList<String>();
			List<String> menuList = new ArrayList<String>();
			List<String> msgList = new ArrayList<String>();
			List<String> reportList = new ArrayList<String>();
			List<String> commentList = new ArrayList<String>();

			doService = DOService.getService("kids_weeklesson_getbyunpush");
			for (Object boInstance : doService.invokeSelect(userId)) {
				lessonList.add(((BOInstance) boInstance).toJSONObject().toString());
				System.out.println("*****getunpushlessonList:"
						+ ((BOInstance) boInstance).getMap());
			}
			doService = DOService.getService("kids_weekmenu_getbyunpush");
			for (Object boInstance : doService.invokeSelect(userId)) {
				menuList.add(((BOInstance) boInstance).toJSONObject().toString());
				System.out.println("*****getunpushmenuList:"
						+ ((BOInstance) boInstance).getMap());
			}
			doService = DOService.getService("kids_dailyreport_getbyunpush");
			for (Object boInstance : doService.invokeSelect(userId)) {
				reportList.add(((BOInstance) boInstance).toJSONObject().toString());
				System.out.println("*****getunpushreportList:"
						+ ((BOInstance) boInstance).getMap());
			}
			doService = DOService.getService("kids_message_getbyunpush");
			for (Object boInstance : doService.invokeSelect(userId)) {
				msgList.add(((BOInstance) boInstance).toJSONObject().toString());
				System.out.println("*****getunpushmsgList:"
						+ ((BOInstance) boInstance).getMap());
			}
			doService = DOService.getService("kids_comment_getbyunpush");
			for (Object boInstance : doService.invokeSelect(userId)) {
				commentList.add(((BOInstance) boInstance).toJSONObject().toString());
				System.out.println("*****getunpushcommentList:"
						+ ((BOInstance) boInstance).getMap());
			}

			// 把查询到的数据放进返回数据中
			resultInstance.putValue("lesson", lessonList);
			resultInstance.putValue("menu", menuList);
			resultInstance.putValue("message", msgList);
			resultInstance.putValue("report", reportList);
			resultInstance.putValue("comment", commentList);

		}

		// 把所有未推送数据更改为已推送
		doService = DOService
				.getService("kids_infolog_updateallpushbypersonid");
		doService.invokeUpdate(userId);

		// 获取未读信息的数目
		doService = DOService.getService("kids_infolog_getunreadbyid");
		List<BOInstance> unreadList = doService.invokeSelect(userId);

		Map<String, String> unreadMap = new HashMap<String, String>();
		for (BOInstance boInstance : unreadList) {
			unreadMap.put(boInstance.getMap().get("infotype").toString(),
					boInstance.getMap().get("num").toString());
		}
		resultInstance.putValue("unread", unreadMap);
		System.out.println("访问了自定义服务");

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
