package Main;

import java.io.Console;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * TBD
 *
 * @author Alexander Lundqvist & Ramin Shojaei
 */
public class Sender {
    private static String username;
    private static String password;
    


    /**
     * Main method with unit testing for the class.
     * @param args takes no input arguments
     */    
    
    //Insert your username and password
    public static void main(String[] args) {
        Console console = System.console();
        console.readLine("Type your Username:  "); 
        new String(console.readPassword("Your Password:  "));
        
        //Properties field is created
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.kth.se");
        properties.put("mail.smtp.port", "587");

//Authenticator represents an object that knows how to obtain authentication for a network connection
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
        //We create this authenticator to pass a username and password to its constructor.
            protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password );
                    }
                });
        
            try {
		// MimeMessage implements javax.mail.internet.MimePart
                //It defines content of what we want to transfer
                //To create a msg it's required to pass session object in Mime Message constructor
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(username + "@kth.se"));
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(username + "@kth.se"));
                msg.setText("Hello, what's up?");
	
                //Uses the SMTP Protocol to send msg
		Transport.send(msg);
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
    }
    
    
    
}
