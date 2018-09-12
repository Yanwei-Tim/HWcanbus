package com.tool.mail;


public class SendMailUtils {
	public final static String MAIL_SRV = "smtp.126.com";
	public final static String MAIL_ADDR = "normanwill@126.com";
	public final static String MAIL_AUTH_CODE = "yanxuan2018";
	public final static String MAIL_TARGET = MAIL_ADDR;
	 
	MailSenderInfo mailSenderInfo;
	
	public void sendText() {
		mailSenderInfo = new MailSenderInfo();
		mailSenderInfo.setContent("content : jgaskdhuiweghdirvkjbasflkasdlbasdfui");
		mailSenderInfo.setMailServerHost(MAIL_SRV);
		mailSenderInfo.setUsername(MAIL_ADDR);
		mailSenderInfo.setPassword(MAIL_AUTH_CODE);
		mailSenderInfo.setFromAddress(MAIL_ADDR);
		mailSenderInfo.setToAddress(MAIL_TARGET); 
		mailSenderInfo.setValidate(true); 
		new SimpleMailServer().sendTextMail(mailSenderInfo);
	}
}
