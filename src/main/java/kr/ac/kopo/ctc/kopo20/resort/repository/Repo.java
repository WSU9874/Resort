package kr.ac.kopo.ctc.kopo20.resort.repository;

import java.util.List;
import java.util.Optional;

import kr.ac.kopo.ctc.kopo20.resort.domain.Notice;
import kr.ac.kopo.ctc.kopo20.resort.domain.Reservation;

public interface Repo {
	interface ReserveRepository {
		// create, update
		Reservation save(Reservation reservation);

		// read
		Optional<Reservation> findById(Long id);

		List<Reservation> findAll();

		// delete
		Reservation deleteById(Long id);
	}

	interface NoticeRepository {
		// create, update
		Notice save(Notice Notice);

		// read
		Optional<Notice> findById(Long id);
		
		Optional<Notice> findByWriter(String writer);

		List<Notice> findAll();

		// delete
		Notice deleteById(Long id);
	}

}
