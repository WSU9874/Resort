package kr.ac.kopo.ctc.kopo20.resort.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import kr.ac.kopo.ctc.kopo20.resort.domain.Member;
import kr.ac.kopo.ctc.kopo20.resort.domain.Notice;
import kr.ac.kopo.ctc.kopo20.resort.dto.MemberDTO;
import kr.ac.kopo.ctc.kopo20.resort.repository.MemberRepository;
import kr.ac.kopo.ctc.kopo20.resort.service.MemberService;

@Controller
public class MyPageController {
	@Autowired
	MemberService mser = new MemberService();

	@Autowired
	private MemberRepository repository;

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
		String userid = principal.getName();
		Member member = mser.findOneUserId(userid).orElse(new Member());
		model.addAttribute("user", member);
		return "user_setting";
	}

	@GetMapping("/infoModify")
	public String infoModify(Principal principal, Model model) {
		String userid = principal.getName();
		Member member = mser.findOneUserId(userid).orElse(new Member());
		model.addAttribute("user", member);
		return "infoModify";
	}

//	@PostMapping("/infoModify/{id}")
//	public String infotmationModify(@PathVariable("id") long id, Model model) {
//		model.addAttribute("member", mser.findOneId(id).orElse(new Member()));
//		return "modify";
//	}

	@PostMapping("/infoUpdate/{id}")
	public String informationUpdate(@PathVariable("id") long id, Member member, @Valid MemberDTO dto,
			BindingResult bindingResult, Model model) {
		Member memberTemp = mser.findOneId(id).orElse(new Member());
//		memberTemp.setPw(member.getPw());
		memberTemp.setEmail(member.getEmail());
		memberTemp.setPhone(member.getPhone());
		memberTemp.setAddress(member.getAddress());
		memberTemp.setNickname(member.getNickname());

		mser.updateInfo(memberTemp);

		return "redirect:/setting/user";
	}

	@ResponseBody // 값 변환을 위해 꼭 필요함
	@GetMapping("/nickCheck") // 아이디 중복확인을 위한 값으로 따로 매핑
	@Transactional
	public boolean overlappedNick(@RequestParam("nickname") String nickname) throws Exception {
		boolean result = repository.existsByNickname(nickname); // 중복확인한 값을 int로 받음
		System.out.println(result);
		return result;
	}

	@ResponseBody // 값 변환을 위해 꼭 필요함
	@GetMapping("/emailCheck") // 아이디 중복확인을 위한 값으로 따로 매핑
	public boolean overlappedEmail(@RequestParam("email") String email) throws Exception {
		boolean result = repository.existsByEmail(email); // 중복확인한 값을 int로 받음
		return result;
	}

	@ResponseBody // 값 변환을 위해 꼭 필요함
	@GetMapping("/phoneCheck") // 아이디 중복확인을 위한 값으로 따로 매핑
	@Transactional
	public boolean overlappedPhone(@RequestParam("phone") String phone) throws Exception {
		String num = phone.replaceAll("-", "");
		boolean result = repository.existsByPhone(num); // 중복확인한 값을 int로 받음
		System.out.println(result);
		return result;
	}
}
