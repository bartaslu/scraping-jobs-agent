package jobscraperagent.jobagent;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService {
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String emailAddress;
    
    public EmailNotificationService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendTestEmail(){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(emailAddress);
        message.setTo(emailAddress);

        message.setSubject("Job Agent - Email Test");
        message.setText("""
                Your Job Agent email configuration is working!

                Your application can now send email notifications.
                """
        );

        mailSender.send(message);
    }

    public void sendJobDigest(String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(emailAddress);
        message.setTo(emailAddress);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}
