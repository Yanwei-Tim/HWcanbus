package com.tool.mail;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.sun.mail.util.MailSSLSocketFactory;

public class MailSenderInfo {
	/** 发送邮件的服务器ip */
	private String mailServerHost;
	/** 发送邮件的服务器端口 */
	private String mailServerPort = "25";
	/** 邮件发送者的地址 */
	private String fromAddress;
	/** 邮件接收者的地址 */
	private String toAddress;
	/** 登录邮件发送服务器的用户名 */
	private String username;
	/** 登录邮件发送服务器的密码 --授权密码 */
	private String password;
	/** 是否需要身份验证 */
	private boolean isValidate = false;
	/** 邮件主题 */
	private String subject;
	/** 邮件文本内容 */
	private String content;
	/** 邮件附件的文件 */
	private List<File> fileList;

	/**
	 * 获得邮件会话属性
	 * 
	 * @throws GeneralSecurityException
	 *             证书安全异常
	 */
	public Properties getProperties() throws GeneralSecurityException {
		Properties p = new Properties();
		p.put("mail.smtp.host", this.mailServerHost);
		p.put("mail.smtp.port", this.mailServerPort);
		p.put("mail.smtp.auth", isValidate ? "true" : "false");
		// 进行证书验证
		MailSSLSocketFactory mssf = new MailSSLSocketFactory();
		mssf.setTrustAllHosts(true);
		p.put("mail.smtp.ssl.enable", true);
		p.put("mail.smtp.ssl.socketFactory", mssf);
		return p;
	}

	public String getMailServerHost() {
		return mailServerHost;
	}

	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}

	public String getMailServerPort() {
		return mailServerPort;
	}

	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isValidate() {
		return isValidate;
	}

	public void setValidate(boolean isValidate) {
		this.isValidate = isValidate;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<File> getFileList() {
		return fileList;
	}

	public void setFileList(List<File> fileList) {
		this.fileList = fileList;
	}

}