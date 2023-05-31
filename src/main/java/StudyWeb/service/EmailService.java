package StudyWeb.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmailService{
    private final JavaMailSender emailSender;


    public String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();
        for (int i = 0; i < 8; i++) { // 인증코드 8자리
            int index = rnd.nextInt(3); // 0~2 까지 랜덤

            switch (index) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    //  a~z  (ex. 1+97=98 => (char)98 = 'b')
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    //  A~Z
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    // 0~9
                    break;
            }
        }
        return key.toString();
    }

    private MimeMessage createMessage(String to, String key)throws Exception{
        log.info("Send To = {}",to);
        log.info("Key Number = {}", key);
        MimeMessage  message = emailSender.createMimeMessage();

        message.addRecipients(Message.RecipientType.TO, to);//보내는 대상
        message.setSubject("Studyweb 회원가입 이메일 인증");//제목

        String msgg="";
        msgg+= "<div style='margin:100px;'>";
        msgg+= "<h1> 안녕하세요 Studyweb입니다. </h1>";
        msgg+= "<br>";
        msgg+= "<p>아래 코드를 회원가입 창으로 돌아가 입력해주세요<p>";
        msgg+= "<br>";
        msgg+= "<p>감사합니다!<p>";
        msgg+= "<br>";
        msgg+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg+= "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
        msgg+= "<div style='font-size:130%'>";
        msgg+= "CODE : <strong>";
        msgg+= key+"</strong><div><br/> ";
        msgg+= "</div>";
        message.setText(msgg, "utf-8", "html");//내용
        message.setFrom(new InternetAddress("studyweb@gmail.com","Kim"));//보내는 사람

        return message;
    }

    /*
    * modal로 비밀번호 변경용 이메일 창 띄우기 => 이메일 입력 => /password_url_email POST
    * => 해당 메일 전송 => 메일 링크 접속 => /change_password GET & PathVariable로 userEmail 전송
    * => 비밀번호 변경 진행
    * */
    private MimeMessage createPasswordChangeMail(String to)throws Exception{
        String api = "https://studyweb.site/api/change_password/";
        log.info("Password Change Mail Send To = {}",to);
        MimeMessage message = emailSender.createMimeMessage();

        message.addRecipients(Message.RecipientType.TO, to);//보내는 대상
        message.setSubject("[StudyWeb] 비밀번호 변경 링크입니다.");//제목

        String msgg="";
        msgg+= "<div style='margin:100px;'>";
        msgg+= "<h1> 비밀번호 변경 링크입니다.</h1>";
        msgg += "<div>";
        msgg += api + to;
        msgg += "/<div>";

        message.setText(msgg, "utf-8", "html");//내용
        message.setFrom(new InternetAddress("studyweb@gmail.com","Kim"));//보내는 사람

        return message;
    }

    @Async("mailExecutor")
    public void sendValidationMail(String to,String authKey) throws Exception {
        MimeMessage message = createMessage(to,authKey);
        try {
            emailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
            log.error("Mail error :  ",e);
            throw new IllegalArgumentException();
        }
    }

    @Async("mailExecutor")
    public void sendPasswordChangeMail(String to) throws Exception {
        MimeMessage message = createPasswordChangeMail(to);
        try {
            emailSender.send(message);
        }catch (MailException e) {
            e.printStackTrace();
            log.error("Mail error :  ",e);
            throw new IllegalArgumentException();
        }
    }
}