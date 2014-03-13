package kalenderSystem;

import java.util.ArrayList;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.smtp.SMTPTransport;
import java.security.Security;
import java.util.Date;
import java.util.Properties;



public class Mail {
	Database db = new Database();
	
	public static void main(String[] args){
		Mail se = new Mail();
		se.getEmails(2);
		
		try {
			for(int i=0;i<1;i++){
			Send("gruppe41.pu", "gruppe41fp", "christdj@stud.ntnu.no", "Fuck yeeah", "joloswag");
			}
		}catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}		
	}
	
	public ArrayList<String> getEmails(int id){
		ArrayList<String> emails = new ArrayList<String>();
		emails = db.getEposter();
		return emails;
	}
	
	public void sendEmail(int id){
		ArrayList<String> emails = getEmails(id);
		try{
			for(int i = 0; i < emails.size(); i++){
			//for (emails : email)
				Send("pu.gruppe42", "gruppe42fp", emails.get(i), "Emne", "text");
			}
		}catch(AddressException e){
			e.printStackTrace();
		}catch(MessagingException e){
			e.printStackTrace();
		}
	}

	private Mail() {
	}
	public static void Send(final String username, final String password, String recipientEmail, String title, String message) throws AddressException, MessagingException {
		Mail.Send(username, password, recipientEmail, "", title, message);
	}
	public static void Send(final String username, final String password, String recipientEmail, String ccEmail, String title, String message) throws AddressException, MessagingException {
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		Properties props = System.getProperties();
		props.setProperty("mail.smtps.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.setProperty("mail.smtps.auth", "true");
		props.put("mail.smtps.quitwait", "false");
		Session session = Session.getInstance(props, null);
		final MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(username + "@gmail.com"));
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail, false));
		if (ccEmail.length() > 0) {
			msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccEmail, false));
		}
		msg.setSubject(title);
		msg.setText(message, "utf-8");
		msg.setSentDate(new Date());
		SMTPTransport t = (SMTPTransport)session.getTransport("smtps");
		t.connect("smtp.gmail.com", username, password);
		t.sendMessage(msg, msg.getAllRecipients());      
	}
}
