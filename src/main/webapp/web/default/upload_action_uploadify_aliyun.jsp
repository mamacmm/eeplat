<%@page import="java.sql.Struct"%><%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.exedosoft.plat.util.DOGlobals"%>
<%@ page import="com.exedosoft.plat.util.Escape"%>
<%@ page import="com.exedosoft.plat.util.StringUtil"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.File"%>
<%@ page import="javax.imageio.ImageIO"%>
<%@ page import="java.awt.image.BufferedImage"%>
<%@ page import="org.apache.commons.fileupload.*"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="com.exedosoft.plat.SessionContext"%>
<%@ page import="com.exedosoft.plat.storage.oss.OSSUpload"%>

<%
	SessionContext context = (SessionContext) session
	.getAttribute("userInfo");
	if (null == session.getAttribute("userInfo")
	|| context.getUser() == null) {
	response.sendRedirect(request.getContextPath()
		+ "/web/default/logoff.jsp");
	}
	System.out.println("sessionid::" + session.getId());
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");
	DiskFileUpload fu = new DiskFileUpload();
	fu.setHeaderEncoding("UTF-8");
	// 设置允许用户上传文件大小,单位:字节
	fu.setSizeMax(1000 * 1024 * 1024);
	// 设置最多只允许在内存中存储的数据,单位:字节
	fu.setSizeThreshold(1024 * 100);
	// 设置一旦文件大小超过getSizeThreshold()的值时数据存放在硬盘的目录
	///如果没有上传路径则创建
	String colName = "";// requestM.getParameter("colName");
	String blobColName = "";// requestM.getParameter("blobColName");
	String fileName = "";// requestM.getFilesystemName("fileupload");
	String myFile="";
	try {
		
		File file = new File(DOGlobals.UPLOAD_TEMP);
		file.mkdirs();
		fu.setRepositoryPath(DOGlobals.UPLOAD_TEMP);
		//开始读取上传信息
		
		String uuid = request.getParameter("uuid");
		
		if(uuid!=null && uuid.indexOf(";jsessionid=")!=-1){
			uuid = uuid.substring(0,uuid.indexOf(";jsessionid="));
		}

		List fileItems = fu.parseRequest(request);
		// 依次处理每个上传的文件
		Iterator iter = fileItems.iterator();

		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();
			
			
			////文件随机数
			if(item.isFormField()){
				if(item.getFieldName()!=null && item.getFieldName().equals("hiddenfileuuid")){
					myFile=item.getString();
					System.out.println("===========hiddenfileuuid---------"+myFile);
				}
			}
			if (!item.isFormField()) {
				String name = item.getName();
				if (name.indexOf("\\") != -1) {
					name = name.substring(name.lastIndexOf("\\") + 1);
 
				}
				///不能删除jsp文件
				if(name.toLowerCase().indexOf(".jsp")!=-1){
					return;
				}
				
	
				String key = name + ";" + uuid;
				OSSUpload.upload(key, item);
				System.out.println(fileName+"item:::::::"+item.getFieldName());
				BufferedImage bis = ImageIO.read(item.getInputStream());
				DOGlobals.getInstance().getSessoinContext().getGlobal().putValue("a"+uuid+"_w",  bis.getWidth() );
				DOGlobals.getInstance().getSessoinContext().getGlobal().putValue("a"+uuid+"_h",  bis.getHeight() );
				bis = null;
				////上传之后本地删除
				item.delete();
			}
			
			if (item.getFieldName().equals("colName")) {
				colName = item.getString();
			}
			if (item.getFieldName().equals("blobColName")) {
				blobColName = item.getString();
			}

		}
	} catch (Exception e) {
		e.printStackTrace();

	}
	
	System.out.println("Upload Action Comm FileName::" + fileName);
	
%>