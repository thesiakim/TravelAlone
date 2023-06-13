package com.travelAlone.s20230404.service.si;

import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class MailSendService {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private JavaMailSenderImpl mailSender;
	private int authCode;
	
	//인증번호 생성
	public void makeRandomNumber() {
		//난수 범위 : 111111 ~ 999999 
		Random r = new Random();
		authCode = r.nextInt(888888) + 111111;
		
		log.info("인증번호 생성 : " + authCode);
	}
	
	
	//이메일 양식
	public String joinEmail(String email) {
		makeRandomNumber();
		String setFrom = "s20230404@gmail.com"; 
		String toMail = email;
		String title = "[나 혼자 여행] 회원 가입 인증 이메일 입니다.";   //메일 제목 
		String content = 
				"나 혼자 여행에 방문해주셔서 감사합니다." + 	
                "<br><br>" + 
			    "인증 번호는 " + authCode + "입니다." + 
			    "<br>" + 
			    "해당 인증번호를 인증번호 확인란에 기입해주세요.";        //이메일 내용 삽입
		
		session.setAttribute("authCode", Integer.toString(authCode));
		mailSend(setFrom, toMail, title, content);
		return Integer.toString(authCode);
	}
	
	
	//이메일 전송
	public void mailSend(String setFrom, String toMail, String title, String content) { 
		MimeMessage message = mailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
			helper.setFrom(setFrom);
			helper.setTo(toMail);
			helper.setSubject(title);
	
			helper.setText(content,true);
			mailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	

}
