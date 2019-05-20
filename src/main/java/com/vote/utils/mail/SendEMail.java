package com.vote.utils.mail;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
* @author LTT-AH-ZY
* @version 创建时间：2019年4月25日 下午8:30:03
* @version 1.0
* @类说明
*/
public class SendEMail {
	/**
	 * 
	 * @param subject 邮件主题
	 * @param content 邮件内容
	 * @param Users 收件人邮箱（多人）
	 */
	public static Map<String,Object> sendFileMail(String subject,String content,String[] Users) {
		//自定义变量工具类
		InitVariable initvariable = new InitVariable();
		//JavaMailSenderImpl实现类
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
		//设置自己登录email的服务商提供的host地址
		senderImpl.setHost(initvariable.Mail_HOST);
		//设置登录邮箱账户
		senderImpl.setUsername(initvariable.Mail_UserName);
		//设置登录邮箱授权码（不是登录密码）
		senderImpl.setPassword(initvariable.Mail_PassWord);
		
		//返回结果集
		Map<String,Object> result = new HashMap<String,Object>();
		try {
			// 建立HTML邮件消息
			MimeMessage mailMessage = senderImpl.createMimeMessage();
			// true表示开始附件模式.如果邮件不需要附件设置成false即可
			MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true, "utf-8");
			// 设置收信人的email地址
			messageHelper.setTo(Users);
			// 设置寄信人的email地址{与上面登陆的邮件一致}
			messageHelper.setFrom(initvariable.Mail_UserName);
			// 设置邮件发送内容的主题
			messageHelper.setSubject(subject);
			// true 表示启动HTML格式的邮件
			messageHelper.setText("<html><title>这是一封邮件</title><body>"+ content + "</body></html>", true);
			// 如不需要附件,这里可以省略
			// 读取附件
			FileSystemResource file = new FileSystemResource(new File("F:/Onlinelogs/error.log"));
			//发送邮件
			senderImpl.send(mailMessage);
			result.put("code", 200);
			result.put("msg", "邮件发送成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("code", 500);
			result.put("msg", "邮件发送失败！");
		}
		return result;
	}
}
