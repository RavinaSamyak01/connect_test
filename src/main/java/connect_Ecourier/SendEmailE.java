package connect_Ecourier;

import java.io.File;
import java.util.Properties;
import java.util.Date;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;

public class SendEmailE  {

	public static boolean sendMail(String toAddresses, String subject, String msg, String fileAttachments)
			throws Exception {
	
		
		String hostName = "10.100.112.1"; // for receive
		Session session = null;
		Transport transport = null;
		boolean success = false;

		String fromAddress = "bhushan.pathak@samyak.com";
		String bccAddresses = "bhushan.pathak@samyak.com";
		// pdoshi@samyak.com, asharma@samyak.com
		// "pdoshi@samyak.com,kunjan.modi@samyak.com,pgandhi@samyak.com" ;
		// String msg = " " ;
		// ravina.prajapati@samyak.com

		// connect to SMTP server
		Properties props = System.getProperties();

		// Setup mail server
		props.put("mail.smtp.host", hostName); // for send

		// Get session
		session = Session.getInstance(props, null);

		transport = session.getTransport("smtp");
		transport.connect(hostName, "bhushan.pathak@samyak.com", "bhushan.pathak");
		
		
		

		// Define message object
		MimeMessage message = new MimeMessage(session);

		message.setFrom(new InternetAddress(fromAddress)); // set from address

		String toAddress[] = toAddresses.split(","); // spliting toAddress for multiple toAddresses

		for (int i = 0; i < toAddress.length; i++) {
			// set toAddress
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress[i]));
		}

		if ((bccAddresses != null) && (!bccAddresses.equals(""))) {
			String bccAddress[] = bccAddresses.split(","); // spliting bccAddress for multiple bccAddresses
			for (int i = 0; i < bccAddress.length; i++) {
				// set bccAddress
				message.addRecipient(Message.RecipientType.BCC, new InternetAddress(bccAddress[i]));
			}
		}

		message.setSubject(subject); // set subject for the email
		message.setSentDate(new Date()); // set the sent date for the email

		// create the message part - Plain text
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		// fill message with what is sent
		messageBodyPart.setText(msg);

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		// Part two is attachment
		if ((fileAttachments != null) && (!fileAttachments.equals(""))) {
			String fileNames[] = fileAttachments.split(",");

			for (int i = 0; i < fileNames.length; i++) {
				File AttachementFileName = new File(fileNames[i]);

				if (AttachementFileName != null && !fileNames[i].equals("")) {
					if (AttachementFileName.exists()) {
						MimeBodyPart mbp2 = new MimeBodyPart();
						FileDataSource fds = new FileDataSource(fileNames[i]);
						// attach the file to the message
						mbp2.setDataHandler(new DataHandler(fds));

						String justFileName;

						justFileName = fileNames[i].substring(fileNames[i].lastIndexOf("\\") + 1,
								fileNames[i].length());

						mbp2.setFileName(justFileName);

						// create the next message part
						multipart.addBodyPart(mbp2);
					} else {
						System.out.println("5 - Attachment File not found. Send failed for recepient :- " + toAddresses
								+ "   Subject:" + subject);
					}
				}
			}
		}

		// Put parts in message
		message.setContent(multipart);

		// retry_send:

		// Send the message
		try {
			message.saveChanges(); // implicit with send()
			transport.sendMessage(message, message.getAllRecipients());
			success = true;
		} catch (SendFailedException sfe) {
			System.err.println("6 - Send Failed " + sfe.getMessage());
			System.out.println("6 - Send Failed for " + toAddresses + ". " + sfe.getMessage());
		} catch (MessagingException ex) {
			System.err.println("7 - Cannot send email. " + ex.getMessage());
			System.out.println("7 - Could not send email to " + toAddresses + ". " + ex.getMessage());
		} catch (Exception e) {
			System.err.println("8 - Error " + e.getMessage());
			System.out.println("8 - Error " + e.getMessage());
		}

		return success;

	}
}