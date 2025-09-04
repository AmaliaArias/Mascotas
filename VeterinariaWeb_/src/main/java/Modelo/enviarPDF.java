package Modelo;

import java.io.File;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;


public class enviarPDF {

	public static void enviarCertificado(String destinatario, File pdfCertificado) {

		System.out.println("entro");
		
		final String remitente = "amaliarosaonatearias10.3@gmail.com";
		final String claveApp = "lboi wjbt bhma hpxz"; // No uses tu contraseña normal

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		Session sesion = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(remitente, claveApp);
			}
		});

		try {
			MimeMessage mensaje = new MimeMessage(sesion);
			mensaje.setFrom(new InternetAddress(remitente));
			mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
			mensaje.setSubject("Tu certificado PDF");
			mensaje.setText("nose");

			// Parte del texto
			MimeBodyPart cuerpoTexto = new MimeBodyPart();
			cuerpoTexto.setText("Hola, adjunto encontrarás tu certificado en PDF.");

			// Parte del archivo adjunto
			MimeBodyPart adjunto = new MimeBodyPart();
			adjunto.attachFile(pdfCertificado);

			// Combinar ambas partes
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(cuerpoTexto);
			multipart.addBodyPart(adjunto);

			
			System.out.println("entro");
			Transport.send(mensaje);
			System.out.println("Correo enviado con éxito.");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
