package com.exedosoft.plat;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.DOServiceRedirect;

public class DOSendEMail extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4182943988305938731L;

	@Override
	public String excute() throws ExedoException {
		// TODO Auto-generated method stub

		if (this.service == null) {
			return NO_FORWARD;
		}

		if (this.actionForm == null) {
			return NO_FORWARD;
		}
		
		String emailTo = this.getEMailTo();
		if(emailTo==null){
			return NO_FORWARD;
		}
		
		/**
		 * 首先实现的是更新操作
		 */
		if(this.service.getMainSql()!=null){
			if(this.service.isUpdate()){
				long currentDate = System.currentTimeMillis();
				this.actionForm.putValue("sendEMailDate", currentDate);
				this.service.invokeUpdate();
			}
		}

		List<DOServiceRedirect> list = this.service.retrieveServiceRedirect();
		if (list != null && list.size() > 0) {

			for (DOServiceRedirect dsr : list) {
				if (dsr.getPaneModel() != null) {

					String title = dsr.getPaneModel().getTitle();
					String context = dsr.getPaneModel().getHtmlCode();
					try {
						sendEmail(emailTo,title,context);
					} catch (AddressException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}

		}

		return DEFAULT_FORWARD;
	}

	private String getEMailTo() {

		if (this.actionForm.getValue("email") != null) {
			return this.actionForm.getValue("email");
		}
		if (this.actionForm.getValue("user_email") != null) {
			return this.actionForm.getValue("user_email");
		}

		if (this.actionForm.getValue("useremail") != null) {
			return this.actionForm.getValue("useremail");
		}

		if (this.actionForm.getValue("emaildress") != null) {
			return this.actionForm.getValue("emaildress");
		}
		return null;
	}

	// //////////////////////////////////////////////////////////////
	// 发送邮件
	public static void sendEmail(String smtpHost, String from, String password,
			String to, String title, String text) throws AddressException,
			MessagingException, IOException {

		final Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");

		Session myMailSession = Session.getInstance(props);
		myMailSession.setDebug(true); // 打开DEBUG模式
		Message msg = new MimeMessage(myMailSession);
		msg.setFrom(new InternetAddress(from));
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		msg.setContent("I have a email!", "text/html");
		msg.setSentDate(new java.util.Date());
		msg.setSubject(title);
		// msg.setText(text);

		msg.setDataHandler(new DataHandler(new ByteArrayDataSource(text,
				"text/html ")));
		System.out.println("1.Please wait for sending to...");

		// 发送邮件
		Transport myTransport = myMailSession.getTransport("smtp");
		myTransport.connect(smtpHost, from, password);
		myTransport.sendMessage(msg,
				msg.getRecipients(Message.RecipientType.TO));
		myTransport.close();
		// javax.mail.Transport.send(msg); // 这行不能使用。
		System.out.println("2.Your message had send!");

	}

	// //////////////////////////////////////////////////////////////
	// 发送邮件
	public static void sendEmail(String to, String title, String text)
			throws AddressException, MessagingException, IOException {

		// **************************************************8
		// 测试用
		// to = "yaoyx@bst.org.cn";
		// *****************************************************8

		String smtpHost = "smtp.ym.163.com";
		String from = "support@eeplat.com";
		String password = "qweasd90";
		sendEmail(smtpHost, from, password, to, title, text);

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			sendEmail("toweikexin@126.com", "test", "test2222");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
