package kr.ac.kopo.ctc.kopo20.resort.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
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
	public String loginPage(HttpServletRequest request, Model model) {
		String uri = request.getHeader("Referer");
	    if (uri != null && !uri.contains("/login")) {
	        request.getSession().setAttribute("prevPage", uri);
	    }
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
