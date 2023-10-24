package kr.ac.kopo.ctc.kopo20.resort.Controller;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.kopo.ctc.kopo20.resort.domain.Member;
import kr.ac.kopo.ctc.kopo20.resort.domain.Notice;
import kr.ac.kopo.ctc.kopo20.resort.domain.Reservation;
import kr.ac.kopo.ctc.kopo20.resort.domain.RoomStatus;
import kr.ac.kopo.ctc.kopo20.resort.dto.NoticeDTO;
import kr.ac.kopo.ctc.kopo20.resort.dto.ReserveDTO;
import kr.ac.kopo.ctc.kopo20.resort.service.MemberService;
import kr.ac.kopo.ctc.kopo20.resort.service.NoticeCommentServiceImpl;
import kr.ac.kopo.ctc.kopo20.resort.service.NoticeServiceImpl;
import kr.ac.kopo.ctc.kopo20.resort.service.PageService;
import kr.ac.kopo.ctc.kopo20.resort.service.ReservationServiceImpl;
import kr.ac.kopo.ctc.kopo20.resort.service.Serv;

@Controller
public class ResortItemController {

	@Autowired
	Serv.ReservationListService reSer = new ReservationServiceImpl();

	@Autowired
	Serv.NoticeService noSer = new NoticeServiceImpl();

	@Autowired
	Serv.NoticeCommentService noCSer = new NoticeCommentServiceImpl();
	
	@Autowired
	PageService pSer = new PageService();

	@Autowired
	MemberService mser = new MemberService();

	//Read
	
	@GetMapping("/index")
	public String index(Model model) {

		return "index";
	}

	@GetMapping("/about")
	public String about(Model model) {

		return "about";
	}

	@GetMapping("/service")
	public String service(Model model) {

		return "service";
	}

	@GetMapping("/room")
	public String room(Model model) {

		return "room";
	}

	@GetMapping("/booking")
	public String bookForm(Model model) {

		return "booking";
	}
	
	@GetMapping("/team")
	public String team(Model model) {

		return "team";
	}

	@GetMapping("/testimonial")
	public String testimonial(Model model) {

		return "testimonial";
	}

	@GetMapping("/contact")
	public String contact(Model model) {

		return "contact";
	}

	@GetMapping("/map")
	public String map() {
		return "map";
	}

	@GetMapping("/nearby")
	public String nearby(Model model) {

		return "nearby";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

	@GetMapping("/join")
	public String join() {
		return "join";
	}
	
	@GetMapping("/trans")
	public String trans() {
		return "trans";
	}

}
