package Funciones;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
	static String remitente="proyectodefinitivoprogram@gmail.com";
	static String claveemail= "fiktyurwaxtknfcb";
	
	
	
	public static void sendMail(String destinatario, String asunto,String cuerpo) throws MessagingException{
		Properties props = System.getProperties();
	    props.put("mail.smtp.auth", "true");   
	    props.put("mail.smtp.starttls.enable", "true"); 
	    props.put("mail.smtp.host", "smtp.gmail.com");  
	    props.put("mail.smtp.user", remitente);
	    props.put("mail.smtp.clave", claveemail);    
	    props.put("mail.smtp.port", "587"); 

	    Session session = Session.getDefaultInstance(props);
	    //session.setDebug(true);
	    MimeMessage message = new MimeMessage(session);

	    try {
	        message.setFrom(new InternetAddress(remitente));
	        message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));    
	        message.setSubject(asunto);
	        message.setText(cuerpo);
	        Transport transport = session.getTransport("smtp");
	        transport.connect("smtp.gmail.com", remitente, claveemail);
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();
	    }
	    catch (MessagingException me) {
	        me.printStackTrace();   
	    }
	  }
		
		
		 public  static void signUpMail(String correo) throws MessagingException {
		    	sendMail(correo,"Registro","Bienvenido al  grupo \nEstamos muy contentos de que hayas decidido registarte con nosotros.\nUn saludo");
		    }
		
		
		 public static void orderMail(List<String> lista, String nombre,String factura) throws MessagingException{
			   for(String correo : lista) {
			   sendMail(correo,"Gracias","Muchas gracias "+nombre+" por confiar en nosotros, \n"+ factura+"Esperamos volver a vernos pronto\n Un saludo"); }	
	}
		
}
