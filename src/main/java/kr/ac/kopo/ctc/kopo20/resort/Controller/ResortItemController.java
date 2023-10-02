package kr.ac.kopo.ctc.kopo20.resort.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.ac.kopo.ctc.kopo20.resort.domain.Notice;
import kr.ac.kopo.ctc.kopo20.resort.domain.Reservation;
import kr.ac.kopo.ctc.kopo20.resort.dto.NoticeDTO;
import kr.ac.kopo.ctc.kopo20.resort.dto.ReserveDTO;
import kr.ac.kopo.ctc.kopo20.resort.service.NoticeServiceImpl;
import kr.ac.kopo.ctc.kopo20.resort.service.ReservationServiceImpl;
import kr.ac.kopo.ctc.kopo20.resort.service.Serv;

@Controller
public class ResortItemController {

	@Autowired
	Serv.ReservationListService reSer = new ReservationServiceImpl();

	@Autowired
	Serv.NoticeService noSer = new NoticeServiceImpl();

	@GetMapping("/index")
	public String index( Model model) {
		model.addAttribute("var", 1);
//		System.out.println(user.isAccountNonExpired());
		return "index";
	}

	@GetMapping("/about")
	public String about(Model model) {
		model.addAttribute("var", 2);
		return "about";
	}

	@GetMapping("/service")
	public String service(Model model) {
		model.addAttribute("var", 3);
		return "service";
	}

	@GetMapping("/room")
	public String room(Model model) {
		model.addAttribute("var", 4);
		return "room";
	}

	@GetMapping("/booking")
	public String bookForm(Model model) {
		model.addAttribute("var", 5);
		model.addAttribute("var2", 567);
		return "booking";
	}

	@PostMapping("/booking")
	public String book(ReserveDTO dto) {
		Reservation re = new Reservation();
		re.setRoomId(dto.getRoomId());
		re.setName(dto.getName());
		re.setEmail(dto.getEmail());
		re.setCheckIn(dto.getCheckIn());
		re.setCheckOut(dto.getCheckOut());
		re.setAdultCount(dto.getAdultCount());
		re.setPhoneNum(dto.getPhoneNum());
		re.setRequest(dto.getRequest());
		System.out.println("getPhoneNum = " + re.getPhoneNum());

		reSer.create(re);
		return "booking";
	}
	
	@GetMapping("/currentReserve")
	public String currentReserve(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		UserDetails userDetails = (UserDetails)principal; 
		String username = ((User) principal).getUsername(); 
		String password = ((User) principal).getPassword();
		System.out.println(username);
		System.out.println(password);
		return "currentReserve";
	}

	@GetMapping("/team")
	public String team(Model model) {
		model.addAttribute("var", 6);
		model.addAttribute("var2", 567);
		return "team";
	}

	@GetMapping("/testimonial")
	public String testimonial(Model model) {
		model.addAttribute("var", 7);
		model.addAttribute("var2", 567);
		return "testimonial";
	}

	@GetMapping("/contact")
	public String contact(Model model) {
		model.addAttribute("var", 8);
		return "contact";
	}
	
	@GetMapping("/map")
	public String map() {
		return "map";
	}

	@GetMapping("/nearby")
	public String nearby(Model model) {
		model.addAttribute("var", 8);
		return "nearby";
	}

	@GetMapping("/notice")
	public String notice(Model model) {
		List<Notice> no = noSer.readAllNotice();
		model.addAttribute("var", 8);
		model.addAttribute("notice", no);

		return "notice";
	}

	@GetMapping("/noticeNew")
	public String noticeNew(Model model) {

		return "noticeNew";
	}

	@PostMapping("/noticeNew")
	public String book(NoticeDTO dto) {
		Notice no = new Notice();
		no.setTitle(dto.getTitle());
		no.setContent(dto.getContent());

		noSer.create(no);

		return "redirect:notice";
	}

	@GetMapping("/dashboard")
	public String dashboard(@AuthenticationPrincipal UserDetails user, Model model) {
		model.addAttribute("loginId", user.getUsername());
		model.addAttribute("loginRoles", user.getAuthorities());

		return "dashboard";
	}

	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	@GetMapping("/join")
	public String join() {
		return "join";
	}
	
	@GetMapping("/setting/admin")
	public String admin() {
		return "admin_setting";
	}
	
	@GetMapping("/setting/user")
	public String user() {
		return "user_setting";
	}


}
