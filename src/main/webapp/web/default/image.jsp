<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="image/jpeg" import="java.awt.*,java.awt.image.*,java.util.*,javax.imageio.*" %>

<%@ page import="java.util.List"%>

<%@ page import="nl.captcha.Captcha"%>
<%@ page import="nl.captcha.Captcha.Builder"%>
<%@ page import="nl.captcha.backgrounds.BackgroundProducer"%>
<%@ page import="nl.captcha.backgrounds.FlatColorBackgroundProducer"%>
<%@ page import="nl.captcha.backgrounds.GradiatedBackgroundProducer"%>
<%@ page import="nl.captcha.backgrounds.SquigglesBackgroundProducer"%>
<%@ page import="nl.captcha.backgrounds.TransparentBackgroundProducer"%>
<%@ page import="nl.captcha.gimpy.BlockGimpyRenderer"%>
<%@ page import="nl.captcha.gimpy.DropShadowGimpyRenderer"%>
<%@ page import="nl.captcha.gimpy.FishEyeGimpyRenderer"%>
<%@ page import="nl.captcha.gimpy.RippleGimpyRenderer"%>
<%@ page import="nl.captcha.gimpy.ShearGimpyRenderer"%>
<%@ page import="nl.captcha.servlet.CaptchaServletUtil"%>
<%@ page import="nl.captcha.servlet.SimpleCaptchaServlet"%>
<%@ page import="nl.captcha.text.producer.ChineseTextProducer"%>
<%@ page import="nl.captcha.text.producer.DefaultTextProducer"%>
<%@ page import="nl.captcha.text.renderer.ColoredEdgesWordRenderer"%>
<%@ page import="nl.captcha.text.renderer.DefaultWordRenderer"%>
<%@ page import="nl.captcha.text.renderer.WordRenderer"%>
<%



Builder builder=new Captcha.Builder(80, 28);
//增加边框
builder   .addBorder();
//是否增加干扰线条
builder.addNoise();
//----------------自定义字体大小-----------
//自定义设置字体颜色和大小 最简单的效果 多种字体随机显示
List<Font> fontList = new ArrayList<Font>();
fontList.add(new Font("Arial", Font.HANGING_BASELINE, 20));//可以设置斜体之类的
fontList.add(new Font("Courier", Font.BOLD, 20));	


//--------------添加背景-------------
//设置背景渐进效果 以及颜色 form为开始颜色，to为结束颜色
//GradiatedBackgroundProducer gbp=new GradiatedBackgroundProducer();
//gbp.setFromColor(Color.yellow);
//gbp.setToColor(Color.red);
//无渐进效果，只是填充背景颜色
FlatColorBackgroundProducer  gbp=new FlatColorBackgroundProducer(Color.white);
//加入网纹--一般不会用
//SquigglesBackgroundProducer  sbp=new SquigglesBackgroundProducer();
//

//		加入多种颜色后会随机显示 字体空心
		List<Color> colorList=new ArrayList<Color>();
		colorList.add(Color.BLACK);
		colorList.add(Color.blue);
		colorList.add(Color.red);

		ColoredEdgesWordRenderer cwr= new ColoredEdgesWordRenderer(colorList,fontList);
builder.addText(cwr);

//
//		WordRenderer wr=dwr;
//		//增加文本，默认为5个随机字符.
//		if(_text==null){
//			   builder.addText();
//		}else{
//			   String[]ts=_text.split(",");
//			   for(int i=0;i<ts.length;i++){
//				   String[] ts1=ts[i].split(":");
//				   if("chinese".equals(ts1[0])){
//					   builder.addText(new ChineseTextProducer(Integer.parseInt(ts1[1])),wr);
//				   }else if("number".equals(ts1[0])){
//					   //这里没有0和1是为了避免歧义 和字母I和O
//					   char[] numberChar = new char[] { '2', '3', '4', '5', '6', '7', '8' };
//					   builder.addText(new DefaultTextProducer(Integer.parseInt(ts1[1]),numberChar),wr);
//				   }else if("word".equals(ts1[0])){
//					   //原理同上
//					   char[] numberChar = new char[] {'a', 'b', 'c', 'd',
//					            'e', 'f', 'g', 'h', 'k', 'm', 'n', 'p', 'r', 'w', 'x', 'y' };
//					   builder.addText(new DefaultTextProducer(Integer.parseInt(ts1[1]),numberChar),wr);
//				   }else{
//					   builder.addText(new DefaultTextProducer(Integer.parseInt(ts1[1])),wr);
//				   }
//			   }
//			   
//		}

builder.addBackground(gbp);
//---------装饰字体---------------
   // 字体边框齿轮效果 默认是3
builder.gimp(new BlockGimpyRenderer(1));
//波纹渲染 相当于加粗
//builder.gimp(new RippleGimpyRenderer());
//修剪--一般不会用
//builder.gimp(new ShearGimpyRenderer(Color.red));
//加网--第一个参数是横线颜色，第二个参数是竖线颜色
//builder.gimp(new FishEyeGimpyRenderer(Color.red,Color.yellow));
//加入阴影效果 默认3，75 
//builder.gimp(new DropShadowGimpyRenderer());
//创建对象
Captcha captcha =  builder .build();

// 将认证码存入SESSION
System.out.println("Answer::" + captcha.getAnswer());
session.setAttribute("rand",captcha.getAnswer());
// 输出图象到页面
CaptchaServletUtil.writeImage(response, captcha.getImage()); 
out.clear();
out = pageContext.pushBody();
%>
