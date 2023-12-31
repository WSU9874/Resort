package kr.ac.kopo.ctc.kopo20.resort.Controller;

import java.io.IOException;
import java.security.Principal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.kopo.ctc.kopo20.resort.domain.Member;
import kr.ac.kopo.ctc.kopo20.resort.domain.Notice;
import kr.ac.kopo.ctc.kopo20.resort.domain.Reservation;
import kr.ac.kopo.ctc.kopo20.resort.domain.RoomStatus;
import kr.ac.kopo.ctc.kopo20.resort.dto.ReserveDTO;
import kr.ac.kopo.ctc.kopo20.resort.service.MemberService;
import kr.ac.kopo.ctc.kopo20.resort.service.ReservationServiceImpl;
import kr.ac.kopo.ctc.kopo20.resort.service.Serv;

@Controller
public class ReservationController {
	@Autowired
	Serv.ReservationListService reSer = new ReservationServiceImpl();
	@Autowired
	MemberService mser = new MemberService();
	
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
		return "currentReserve";
	}

	@GetMapping("/reservationStatus")
	public String requestReservationList(Model model) {
		LocalDate today = LocalDate.now();
		
		List<Reservation> listofReservation = reSer.readAllReservation();
		List<RoomStatus> roomStatusList = new ArrayList<RoomStatus>();
		for (int i = 0; i < 31; i++) {			
			RoomStatus roomStatus = new RoomStatus((long) 0,"0", "0", "0");
			LocalDate currentDate = today.plusDays(i);
			DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
            String dayOfWeekString = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.US);
            
			roomStatus.setReservationDate(today.plusDays(i).toString());
			roomStatus.setWeek(dayOfWeekString);
			roomStatusList.add(roomStatus);
			
		}
		for (RoomStatus roomStatus : roomStatusList) {
			
			for (Reservation reservation : listofReservation) {
				
				if (roomStatus.getReservationDate().equals(reservation.getReservationDate())) {
					if (reservation.getRoomId() == 1) {
						roomStatus.setId(reservation.getId());
						roomStatus.setRoom1Status(reservation.getName());
					} else if (reservation.getRoomId() == 2) {
						roomStatus.setId(reservation.getId());
						roomStatus.setRoom2Status(reservation.getName());
					} else if (reservation.getRoomId() == 3) {
						roomStatus.setId(reservation.getId());
						roomStatus.setRoom3Status(reservation.getName());
					}
				}
			}
		}
		model.addAttribute("reservations", roomStatusList);

		return "reservationStatus";
	}

	@GetMapping("/reserveClick")
	public String click(Model model, @RequestParam("reservationDate") String reservationDate,
			@RequestParam("roomId") Long roomId, Reservation reservation, ReserveDTO dto, 
			Principal principal) {

		model.addAttribute("reservationDate", reservationDate);
		model.addAttribute("roomId", roomId);

		String userid=principal.getName();
        Member member=mser.findOneUserId(userid).orElse(new Member());
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
	
	@GetMapping("/booked")
	public String booked(Model model, @RequestParam("name") String name) {
		List<Reservation> reservation = reSer.getReservationById(name);
		model.addAttribute("list",reservation);
		return "booked";
	}
	
	@GetMapping("/bookUpdate")
	public String bookUpdate(Model model, @RequestParam("reservationDate") String reservationDate,
			@RequestParam("roomId") Long roomId,@RequestParam("id") Long id, Reservation reservation, ReserveDTO dto, Principal principal) {
		model.addAttribute("reservationDate", reservationDate);
		model.addAttribute("roomId", roomId);
		model.addAttribute("id", id);
		
		Reservation re = reSer.readOneReservation(id);
System.out.print(re);
        model.addAttribute("user", re);

		return "bookUpdate";
	}
	
	@PostMapping("/bookUpdate")
	public String update(ReserveDTO dto, Model model) {
		Reservation re = reSer.readOneReservation(dto.getId());
		
		re.setRoomId(dto.getRoomId());
		re.setName(dto.getName());
		re.setEmail(dto.getEmail());
		re.setChildCount(dto.getChildCount());
		re.setAdultCount(dto.getAdultCount());
		re.setPhoneNum(dto.getPhoneNum());
		re.setRequest(dto.getRequest());
		re.setReservationDate(dto.getReservationDate());

		reSer.updateOneReservation(re);
		return "bookUpdate";
	}
	@PostMapping("/deleteBook")
	public String delete(@RequestParam("id") Long id) throws IOException {
		Reservation re = reSer.readOneReservation(id);
		reSer.deleteOneReservation(re, id);

		return "redirect:reservationStatus";
	}
}
