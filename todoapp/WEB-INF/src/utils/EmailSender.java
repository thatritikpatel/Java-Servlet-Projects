package utils;

import javax.mail.Session;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.Message;
import javax.mail.Message.RecipientType;

import java.util.Properties;

import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;

public class EmailSender{
	
	static String activationMail = "<div style='font-family:verdana;width:80%;border:1px solid #8c8c8c;color:#2b2b2b;margin:20px auto;box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0.19)'>"+
	"<h1 style='text-align:center'>Welcome! ppp</h1>"+
	"<h3 style='text-align:center;background-color:yellow;color:#ff0033'>Happy To See You On Our Portal</h3>"+
	"<h2 style='text-align:center'>Please Click over the <a href='ppp'>link</a> to activate your account.</h2></div>";

	public static boolean sendEmail(String toEmail,String userName,String activationCode){
		boolean flag = false;
		
		Properties props = new Properties();

		props.put("mail.transport.protocol","smtp");
		props.put("mail.smtp.auth","true");
		props.put("mail.smtp.starttls.enable","true");
		props.put("mail.smtp.host","smtp.gmail.com");
		props.put("mail.smtp.port","587");

		Session session = Session.getInstance(props,new EmailAuthenticator());

		MimeMessage mmsg = new MimeMessage(session);

		try{
			mmsg.setFrom(new InternetAddress("ritikkamailbox@gmail.com"));
			mmsg.setRecipients(Message.RecipientType.TO,toEmail);
			mmsg.setSubject("TodoApp Apka Swagat karta hai.... -^-");
			
			String[] arr = activationMail.split("ppp");				

			String link = "http://localhost:8080/_todoapp_v1/activate_account.do?email="+toEmail+"&activation_code="+activationCode;

			String theMessage = arr[0]+userName+arr[1]+link+arr[2];

			mmsg.setContent(theMessage,"text/html");

			Transport.send(mmsg);

			flag = true;
		}catch(MessagingException e){
			e.printStackTrace();
		}

		return flag;
	}
}

class EmailAuthenticator extends Authenticator{
	public PasswordAuthentication getPasswordAuthentication(){
		return new PasswordAuthentication("ritikkamailbox@gmail.com","GateAir1");		
	}
}