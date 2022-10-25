/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.gob.minam.sistema.utils;

import java.io.InputStream;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.log4j.Logger;

/**
 *
 * @author analista5
 */
public class UtilCorreo {
    
    private static Logger logger = Logger.getLogger(UtilCorreo.class);

    public void enviar(String destino, String asunto, String cuerpo) {
        try
	    {                
            Properties properties = new Properties(); 
            String propFileName = "pe/gob/minam/chm/configuracion/correo.properties";
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            properties.load(inputStream);
                
            Properties props = new Properties();
            props.setProperty("mail.smtp.submitter",properties.getProperty("mail.email.usuario"));
            props.setProperty("mail.transport.protocol", properties.getProperty("mail.transport.protocol")); 
            props.setProperty("mail.smtp.host",properties.getProperty("mail.smtp.host"));
            props.setProperty("mail.smtp.starttls.enable", properties.getProperty("mail.smtp.starttls.enable"));
            props.setProperty("mail.smtp.port", properties.getProperty("mail.smtp.port"));  
            props.setProperty("mail.smtp.auth", properties.getProperty("mail.smtp.auth"));
            props.setProperty("mail.smtp.socketFactory.port", properties.getProperty("mail.smtp.socketFactory.port"));  
            props.setProperty("mail.smtp.socketFactory.fallback", properties.getProperty("mail.smtp.socketFactory.fallback"));
            
            // Preparamos la sesion
            Session session = Session.getDefaultInstance(props);

            // Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(properties.getProperty("mail.email.usuario")));
            message.addRecipients(Message.RecipientType.TO,destino);
            
            message.setSubject(asunto);

            MimeMultipart multiParte = new MimeMultipart();
            
            BodyPart texto = new MimeBodyPart();
            texto.setContent(cuerpo,"text/html");
            multiParte.addBodyPart(texto);            
            message.setContent(multiParte);

            String usuario = properties.getProperty("mail.email.usuario").trim();                
            String password = properties.getProperty("mail.email.password").trim();
            // Lo enviamos.
            Transport t = session.getTransport("smtp");               
            t.connect(usuario,password);
            t.sendMessage(message, message.getAllRecipients());

            // Cierre.
            t.close();            
            logger.info("El Correo se envio satisfactoriamente.");
        } catch (Exception e){            
            logger.info("Error al enviar correo: " + e.getMessage());
            logger.error("Detalle:",e);
            e.printStackTrace();
        }
    }
    
}
