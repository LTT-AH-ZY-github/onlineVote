package TestClass;

import java.io.File;

import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
* @author LTT-AH-ZY
* @version 创建时间：2019年4月25日 下午7:27:55
* @version 1.0
* @类说明
*/
public class Sendmail {
	public static void sendFileMail(String subject, String content) {
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
		// 设置自己登陆email的服务商提供的host
		senderImpl.setHost("smtp.163.com");
		// 设置自己登陆邮箱账号
		senderImpl.setUsername("17356535195@163.com");
		// 邮箱密码
		senderImpl.setPassword("ltt940720");
		// 多个邮箱用户
		String users[] = {"1176270587@qq.com","1785390696@qq.com"};
		try {
			// 建立HTML邮件消息
			   MimeMessage mailMessage = senderImpl.createMimeMessage();
			   // true表示开始附件模式.如果邮件不需要附件设置成false即可
			   MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true, "utf-8");
			   // 设置收信人的email地址
			   messageHelper.setTo(users);
			   // 设置寄信人的email地址{与上面登陆的邮件一致}
			   messageHelper.setFrom("17356535195@163.com");
			   // 设置邮件发送内容的主题
			   messageHelper.setSubject(subject);
			   // true 表示启动HTML格式的邮件
			   messageHelper.setText("<html><title>这是一封邮件</title><body>"
			     + content + "</body></html>", true);
			   // 如不需要附件,这里可以省略---------------------------------------START
			   // 读取附件一
			   FileSystemResource file1 = new FileSystemResource(new File(
			     "F:/Onlinelogs/error.log"));
			   // 读取附件二
			  /* FileSystemResource file2 = new FileSystemResource(new File(
			     "e:/测试.txt"));*/
			   // 添加附件一
			   messageHelper.addAttachment("error.log", file1);
			   // 添加附件二
			   // 附件名有中文可能出现乱码
//			   messageHelper.addAttachment(MimeUtility.encodeWord("测试.txt"), file2);
			   // 如不需要附件,这里可以省略------------------------------------------END
			   // 发送邮件
			   senderImpl.send(mailMessage);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// 注意测试需要修改您自己的邮件服务商host,登陆邮件用户,邮件密码,附件,收信人地址
		  sendFileMail("测试邮件", "<H1>测试邮件标题</H1>");
	}
}
