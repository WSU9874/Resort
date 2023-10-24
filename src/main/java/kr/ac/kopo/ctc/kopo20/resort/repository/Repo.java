package kr.ac.kopo.ctc.kopo20.resort.repository;

import java.util.List;
import java.util.Optional;

import kr.ac.kopo.ctc.kopo20.resort.domain.Notice;
import kr.ac.kopo.ctc.kopo20.resort.domain.NoticeComment;
import kr.ac.kopo.ctc.kopo20.resort.domain.Reservation;

public interface Repo {
	interface ReserveRepository {
		// create, update
		Reservation save(Reservation reservation);

		// read
		Optional<Reservation> findById(Long id);

		List<Reservation> findAllById(String name);
		
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
		void deleteById(Notice notice, Long id);
	}

	interface NoticeCommentRepository {

		//댓글 저장, 수정

		void save(NoticeComment params);

		//댓글 상세정보 조회

		NoticeComment findById(Long commentId);

		//댓글 삭제

		void deleteById(Long commentId);

		//댓글 리스트 조회

		List<NoticeComment> findAll(Long noticeId);
		



//		 댓글 수 카운팅

		int count(Long noticeId);

	}

}
