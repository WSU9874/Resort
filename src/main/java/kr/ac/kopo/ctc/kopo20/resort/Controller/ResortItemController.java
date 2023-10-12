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

	@PostMapping("/booking")
	public String book(ReserveDTO dto) {
		LocalDate now = LocalDate.now();
		Reservation re = new Reservation();
		re.setRoomId(dto.getRoomId());
		re.setName(dto.getName());
		re.setEmail(dto.getEmail());
		re.setChildCount(dto.getChildCount());
		re.setAdultCount(dto.getAdultCount());
		re.setPhoneNum(dto.getPhoneNum());
		re.setRequest(dto.getRequest());

		re.setReservationDate(now.toString());

		reSer.create(re);
		return "booking";
	}

	@GetMapping("/currentReserve")
	public String currentReserve(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = (UserDetails) principal;
		String username = ((User) principal).getUsername();
		String password = ((User) principal).getPassword();
		System.out.println(username);
		System.out.println(password);
		return "currentReserve";
	}

	@GetMapping("/reservationStatus")
	public String requestReservationList(Model model) {
		LocalDate today = LocalDate.now();
		List<Reservation> listofReservation = reSer.readAllReservation();
		List<RoomStatus> roomStatusList = new ArrayList<RoomStatus>();
		for (int i = 0; i < 31; i++) {
			RoomStatus roomStatus = new RoomStatus("0", "0", "0");
			roomStatus.setReservationDate(today.plusDays(i).toString());

			roomStatusList.add(roomStatus);
		}
		for (RoomStatus roomStatus : roomStatusList) {
			for (Reservation reservation : listofReservation) {
				if (roomStatus.getReservationDate().equals(reservation.getReservationDate())) {
					if (reservation.getRoomId() == 1) {
						roomStatus.setRoom1Status(reservation.getName());
					} else if (reservation.getRoomId() == 2) {
						roomStatus.setRoom2Status(reservation.getName());
					} else if (reservation.getRoomId() == 3) {
						roomStatus.setRoom3Status(reservation.getName());
					}
				}
			}
		}
		for (RoomStatus reservation : roomStatusList) {
			System.out.println(reservation);
		}
		model.addAttribute("reservations", roomStatusList);

		return "reservationStatus";
	}

	@GetMapping("/reserveClick")
	public String click(Model model, @RequestParam("reservationDate") String reservationDate,@RequestParam("roomId") Long roomId, Reservation reservation, ReserveDTO dto, Principal principal) {

		model.addAttribute("reservationDate", reservationDate);
		model.addAttribute("roomId", roomId);

		String userid=principal.getName();
        Member member=mser.findOne(userid).orElse(new Member());
        model.addAttribute("user", member);

		return "reserveClick";
	}
	
	@PostMapping("/reserveClick")
	public String click(ReserveDTO dto, Model model) {
		Reservation re = new Reservation();

		re.setRoomId(dto.getRoomId());
		re.setName(dto.getName());
		re.setEmail(dto.getEmail());
		re.setChildCount(dto.getChildCount());
		re.setAdultCount(dto.getAdultCount());
		re.setPhoneNum(dto.getPhoneNum());
		re.setRequest(dto.getRequest());
		re.setReservationDate(dto.getReservationDate());

		reSer.create(re);
		return "reserveClick";
	}

	@GetMapping("/notice")
	public String notice(Model model, @RequestParam(value="page", defaultValue="0") int page) {
		List<Notice> no = noSer.readAllNotice();
		Page<Notice> paging = this.pSer.getList(page);
        model.addAttribute("paging", paging);
		model.addAttribute("notice", no);

		return "notice";
	}

	@GetMapping("/noticeNew")
	public String noticeNew(Model model) {
		return "noticeNew";
	}

	@PostMapping("/noticeNew")
	public String noticeNew(NoticeDTO dto, Model model) {
		LocalDate now = LocalDate.now();

		Notice no = new Notice();
		no.setTitle(dto.getTitle());
		no.setContent(dto.getContent());
		no.setDate(now);
		no.setWriter("admin");
		no.setViewCount((long) 0);

		noSer.create(no);

		return "redirect:notice";
	}

	@GetMapping("/noticeOne")
	public String findById(Model model, @RequestParam("noticeId") Long noticeId, Notice notice, NoticeDTO dto) {
		notice = noSer.readOneNotice(noticeId).orElse(new Notice());
		model.addAttribute("notice", notice);
		model.addAttribute("noticeId", noticeId);
		System.out.println(noticeId);
		noSer.viewCount(notice);
		return "noticeOne";
	}

	@GetMapping("/deleteDB")
	public String delete(Notice notice, Long noticeId) throws IOException {

		noSer.deleteOneNotice(notice, noticeId);

		return "redirect:notice";
	}

	@PostMapping("/modify/{noticeId}")
	public String boardmodify(@PathVariable("noticeId") long noticeId, Model model) {
		model.addAttribute("notice", noSer.readOneNotice(noticeId).orElse(new Notice()));
		return "modify";
	}

	@PostMapping("/update/{noticeId}")
	public String boardUpdate(@PathVariable("noticeId") long noticeId, Notice notice) {
		Notice noticeTemp = noSer.readOneNotice(noticeId).orElse(new Notice());
		noticeTemp.setTitle(notice.getTitle());
		noticeTemp.setContent(notice.getContent());

		noSer.updateOneNotice(noticeTemp);

		return "redirect:/noticeOne?noticeId={noticeId}";
	}

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
