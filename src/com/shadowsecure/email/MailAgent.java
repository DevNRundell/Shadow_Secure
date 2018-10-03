package com.shadowsecure.email;

public abstract class MailAgent {
	
	String to;
	String cc;
	String bcc;
	String from;
	String subject;
	String content;
	String password;
	String smtpHost;
	
	public MailAgent() {
		
		this.to = "";
		this.cc = "";
		this.bcc = "";
		this.from = "";
		this.subject = "";
		this.content = "";
		this.smtpHost = "";
	}
	
	public MailAgent(String to,
            String cc,
            String bcc,
            String from,
            String subject,
            String content,
            String smtpHost) {
		
		this.to = to;
		this.cc = cc;
		this.bcc = bcc;
		this.from = from;
		this.subject = subject;
		this.content = content;
		this.smtpHost = smtpHost;
		
	}

	public void setToEmail(String to) {
		this.to = to;
	}
	
	public void setFromEmail(String from) {
		this.from = from;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public void setMessage(String content) {
		this.content = content;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}
	
	public void setCc(String cc) {
		this.cc = cc;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
	}
}
