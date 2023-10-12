package kr.ac.kopo.ctc.kopo20.resort.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.ac.kopo.ctc.kopo20.resort.domain.Member;
import kr.ac.kopo.ctc.kopo20.resort.service.MemberService;

@Controller
public class MyPageController {
	@Autowired
	MemberService mser = new MemberService();
	
	@GetMapping("/dashboard")
	public String dashboard(@AuthenticationPrincipal UserDetails user, Model model) {
		model.addAttribute("loginId", user.getUsername());
		model.addAttribute("loginRoles", user.getAuthorities());

		return "dashboard";
	}

	@GetMapping("/setting/admin")
	public String admin() {
		return "admin_setting";
	}

	@GetMapping("/setting/user")
	public String user(Principal principal, Model model) {
		String userid=principal.getName();
        Member member=mser.findOne(userid).orElse(new Member());
        model.addAttribute("user", member);
		return "user_setting";
	}
	
	@GetMapping("/infoModify")
	public String infoModify() {
		return "infoModify";
	}
}
