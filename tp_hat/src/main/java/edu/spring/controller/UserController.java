package edu.spring.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.spring.domain.User;
import edu.spring.service.UserService;

@Controller
@RequestMapping(value = "user")
public class UserController {
	
	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired UserService userService;
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public void login(String url, Model model) {
		logger.info("login(url = {} 호출)", url);
		if(url != null) {
			model.addAttribute("targetUrl", url);
		}
	}
	
	@RequestMapping(value = "login-post", method = RequestMethod.POST)
	public void loginPost (User user, Model model) {
		logger.info("loginPost({}) 호출", user);
		
		// preHandle 위치
		User result = userService.loginCheck(user);
		model.addAttribute("loginResult", result);
		// postHandle 위치
	}
	
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String register(User user, Model model, RedirectAttributes rttr) {
		logger.info("register({}) 호출");
		
		int result = 0;
		try {
			result = userService.insert(user);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		rttr.addFlashAttribute("msg", "가입시 사용가능한 이메일로 인증해 주세요");
		logger.info("user = ({})", result);
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "emailConfirm", method = RequestMethod.GET)
	public String emailConfirm(User user, Model model, RedirectAttributes rttr) {
		logger.info("emailConfirm({})호출", user);
		
		/*User certiCheck = new User();
		if(certiCheck.getCertification() == "n") {
			rttr.addFlashAttribute("msg", "이메일 인증이 확인되지 않았습니다. 이메일 인증을 다시 해주세요");
			return "redirect:/";
		}*/
		
		// model.addAttribute("certiCheck", certiCheck);
		String userId = user.getUserId();
		
		logger.info("userId = ({})", userId);
		userService.certiUpdate(userId);
		
		model.addAttribute("user_Id", user.getUserId());
		return "/user/emailConfirm";
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		logger.info("logout() 호출");
		logger.info("loginId: {}", session.getAttribute("loginId"));
		
		session.invalidate();
		
		return "redirect:/"; // 메인 페이지로 이동
	}
	

} // end class UserController