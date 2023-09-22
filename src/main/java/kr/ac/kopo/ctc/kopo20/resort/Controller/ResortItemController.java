package kr.ac.kopo.ctc.kopo20.resort.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String index(Model model) {
		model.addAttribute("var", 1);
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

	@GetMapping("/nearby")
	public String nearby(Model model) {
		model.addAttribute("var", 8);
		return "nearby";
	}

	@GetMapping("/notice")
	public String notice(Model model) {
		List<Notice> no = noSer.readAllNotice();
		model.addAttribute("var", 8);
		model.addAttribute("notice",no);

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
		no.setWriter(dto.getWriter());

		System.out.println("getPhoneNum = " + no.getTitle());

		noSer.create(no);
		return "booking";
	}
	/*
	 * @GetMapping("/login") public String loginPage() { return "login"; }
	 */
	
}
