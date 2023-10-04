package kr.ac.kopo.ctc.kopo20.resort.repository;

import java.util.List;
import java.util.Optional;

import kr.ac.kopo.ctc.kopo20.resort.domain.CommentRequest;
import kr.ac.kopo.ctc.kopo20.resort.domain.CommentResponse;
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
		void deleteById(Notice notice, Long id);
	}

	interface NoticeCommentRepository {

		/**
		 * 댓글 저장
		 * 
		 * @param params - 댓글 정보
		 */
		void save(CommentRequest params);

		/**
		 * 댓글 상세정보 조회
		 * 
		 * @param id - PK
		 * @return 댓글 상세정보
		 */
		CommentResponse findById(Long id);

		/**
		 * 댓글 수정
		 * 
		 * @param params - 댓글 정보
		 */
		void update(CommentRequest params);

		/**
		 * 댓글 삭제
		 * 
		 * @param id - PK
		 */
		void deleteById(Long id);

		/**
		 * 댓글 리스트 조회
		 * 
		 * @param postId - 게시글 번호 (FK)
		 * @return 댓글 리스트
		 */
		List<CommentResponse> findAll(Long noticeId);
		
//		@Query("SELECT c FROM Comment c WHERE c.deleteYn = false AND c.postId = :postId ORDER BY c.id DESC")
//	    List<CommentResponse> findAll(@Param("postId") Long noticeId);

		/**
		 * 댓글 수 카운팅
		 * 
		 * @param postId - 게시글 번호 (FK)
		 * @return 댓글 수
		 */
		int count(Long noticeId);

	}

}
