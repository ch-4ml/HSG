package com.hsg.intro.member.controller;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.hsg.intro.member.model.service.MemberServiceImpl;
import com.hsg.intro.member.model.vo.Member;

@Controller
@SessionAttributes("loginUser")
public class MemberController {
	@Autowired
	private MemberServiceImpl ms;
	@RequestMapping(value = "login.me", method = RequestMethod.POST) // DI 의존성 주입
	// 로그인
	public ModelAndView loginCheck(Member m, ModelAndView mv, SessionStatus status){
		
		System.out.println("controller member : " + m);
		
		try {
			String result = ms.loginMember(m);
			
			mv.addObject("result",result);
			System.out.println("Controller - try. result : " + mv.getModel());
		} catch (LoginException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");
		} 
		finally {
			mv.setViewName("jsonView");
		}
		
		return mv;
	}
	// 로그인 - 세션 저장
	@RequestMapping(value = "loginSessionStore.me", method = RequestMethod.GET) // DI 의존성 주입
	public ModelAndView loginSessionStore(Member m, ModelAndView mv, HttpServletRequest request){
		
		System.out.println("controller member : " + m);
		HttpSession session = request.getSession();
		String redirectUrl = (String) session.getAttribute("prevPage");
		try {
			Member loginUser = ms.selectMember(m);
			
			mv.addObject("loginUser",loginUser);
			mv.setViewName("redirect:" + redirectUrl);
		} catch (LoginException e) {
			mv.addObject("message",e.getMessage());
			System.out.println("SessionStore error : " + e);
		} 
		
		return mv;
	}
	
	
	// 로그아웃
	@RequestMapping(value="logout.me", method=RequestMethod.GET)
	public String logout(/*HttpSession session*/ SessionStatus status){

		status.setComplete();
		
		return "redirect:/";
	}
	@RequestMapping(value="memberJoinView.me")
	public String showMemberJoinView(){
		
		return "member/memberJoin";
	}
	@RequestMapping(value="signIn.me", method=RequestMethod.GET)
	public String signIn(HttpServletRequest request) {
		String referer = request.getHeader("Referer");
		request.getSession().setAttribute("prevPage", referer);
		return "member/login";
	}
	//회원가입
	/*@RequestMapping("insert.me")
	public String insertMember(Member m, Model model
			, @RequestParam(name="photo", required=false) MultipartFile photo
			, HttpServletRequest request){
		
		String root = request.getSession().getServletContext().getRealPath("resources");
		String filePath = root + "\\uploadFiles";
		
		System.out.println(filePath);
		
		try {
			System.out.println("photo : " + photo);
			photo.transferTo(new File(filePath + "\\" + photo.getOriginalFilename()));
		} catch (IllegalStateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		m.setUserPwd(passwordEncoder.encode(m.getUserPwd()));
		
		if(m.getGender().equals("1") || m.getGender().equals("3") ){
			m.setGender("M");
		} else {
			m.setGender("F");
		}
		
		System.out.println("controller : " + m);
		
		try {
			ms.insertMember(m);
			return "main/main";
		} catch (RuntimeException e) {
			model.addAttribute("message","濡쒓렇?씤?떎?뙣");
			return "common/errorPage";
		}
	}*/
	
	// 중복검사
	/*@RequestMapping("duplicationCheck.me")
	public ModelAndView duplicationCheck(ModelAndView mv, String userId){
		Member m = new Member();
		m.setUserId(userId);
		
		mv.addObject(m);
		
		mv.setViewName("jsonView");
		
		return mv;
	}*/
	
}
