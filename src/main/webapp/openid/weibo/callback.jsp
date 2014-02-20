<%@ page contentType="text/html;charset=utf-8" %>
<%@ page language="java" import="weibo4j.http.*" %>
<%@ page language="java" import="weibo4j.*" %>
<%@ page language="java" import="weibo4j.org.json.*" %>
<%@ page language="java" import="weibo4j.model.User" %>
<%@ page language="java" import="java.util.Map" %>
<%@ page language="java" import="java.util.HashMap" %>
<%@ page language="java" import="java.util.List" %>
<%@ page language="java" import="com.exedosoft.plat.login.LoginMain" %>
<%@ page language="java" import="com.exedosoft.plat.bo.BOInstance" %>
<%@ page language="java" import="com.exedosoft.plat.bo.DOService" %>

<%
	Oauth oauth = new Oauth();

	String  code = request.getParameter("code");
		if(code!=null)
		{
			AccessToken accessToken=oauth.getAccessTokenByCode(code);
				if(accessToken!=null)
				{
					Account am = new Account();
					am.client.setToken(accessToken.getAccessToken());
					JSONObject uid = am.getUid();
					Users um = new Users();
					um.client.setToken(accessToken.getAccessToken());
					User user = um.showUserById(am.getUid().getString("uid"));
					//PublicService ps = new PublicService();
					//ps.client.setToken(accessToken.getAccessToken());
					//ps.cityList("11");
					
					Map paras = new HashMap();
					paras.put("province", user.getLocation());
					paras.put("weibo_uid", user.getId());
					paras.put("name", user.getName());
					
					DOService findUserByWeiboid = DOService.getService("club_user_findbyweiboid");
					BOInstance boUser = findUserByWeiboid.getInstance(user.getId());
					System.out.println("boUser::::::" + boUser);
					if(boUser==null){
						DOService storeUser = DOService.getService("club_user_insert");
						boUser = storeUser.invokeUpdate(paras);
					}
					LoginMain.makeLogin(boUser, request);
					response.sendRedirect(request.getContextPath() + "/clubnew_portal.pml?isApp=true");

					out.println("<br/>Just for a test,您使用新浪微博成功登录！::" +user );				
				}else
					{
					out.println("access token request error");
					}
		
		}
		else
			{
			out.println("request token session error");
			}

	
%>   