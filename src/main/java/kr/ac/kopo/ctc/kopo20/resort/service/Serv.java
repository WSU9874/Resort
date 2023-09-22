package kr.ac.kopo.ctc.kopo20.resort.service;

import java.util.List;
import java.util.Optional;

import kr.ac.kopo.ctc.kopo20.resort.domain.Notice;
import kr.ac.kopo.ctc.kopo20.resort.domain.Reservation;

public interface Serv {

	interface ReservationListService {
		// Create
		void create(Reservation reservation);

		// Read
		List<Reservation> readAllReservation();

		Reservation readOneReservation(long id);

		// Update
		void updateOneReservation(Reservation reservation);

		// Delete
		void deleteOneReservation(long id);
	}

	interface NoticeService {
		// Create
		void create(Notice notice);

		// Read
		List<Notice> readAllNotice();

		Optional<Notice> readOneNotice(long id);

		// Update
		void updateOneNotice(Notice notice);

		// Delete
		void deleteOneNotice(long id);
		
		public void viewCount(Notice notice);
	}
}
