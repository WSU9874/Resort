package kr.ac.kopo.ctc.kopo20.resort.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import kr.ac.kopo.ctc.kopo20.resort.domain.CommentRequest;
import kr.ac.kopo.ctc.kopo20.resort.domain.CommentResponse;
import kr.ac.kopo.ctc.kopo20.resort.domain.Notice;
import kr.ac.kopo.ctc.kopo20.resort.domain.NoticeComment;

@Repository
public class NoticeCommentRepositoryImpl implements Repo.NoticeCommentRepository {

	private final EntityManager em;
	
	@Autowired
	public NoticeCommentRepositoryImpl(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public void save(CommentRequest params) {
		em.persist(params);
		
	}

	@Override
	public CommentResponse findById(Long id) {
		CommentResponse commentResponse = em.find(CommentResponse.class, id);
		return commentResponse;
	}

	@Override
	public void update(CommentRequest params) {
		em.persist(params);		
	}

	@Override
	public void deleteById(Long id) {
		em.remove(id);
	}

//	@Override
//	public List<CommentResponse> findAll(Long postId) {
//		List<Notice> notice = em.createQuery("SELECT n FROM notice_comment n where n.noticeId DESC", Notice.class)
//				.getResultList();
//		return null;
//	}

	@Override
	public int count(Long postId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
    public List<CommentResponse> findAll(Long noticeId) {
        return em.createQuery("SELECT c FROM notice_comment c WHERE c.noticeId = :noticeId AND c.deleteYn = false ORDER BY c.createdDate DESC", CommentResponse.class)
                .setParameter("noticeId", noticeId)
                .getResultList();
    }

	

}

////jpa는 엔티티메니저로 작동함 도메인과 연결해줌
//	private final EntityManager em;
//
//	@Autowired
//	public NoticeCommentRepositoryImpl(EntityManager em) {
//		this.em = em;
//	}
//
//	@Override
//	public NoticeComment save(NoticeComment noticeComment) {
//		em.persist(noticeComment);
//		return noticeComment;
//	}
//
//	@Override
//	public Optional<NoticeComment> findById(Long id) {
//		NoticeComment noticeComment = em.find(NoticeComment.class, id);
//		return Optional.ofNullable(noticeComment);
//	}
//
//	@Override
//	public List<NoticeComment> findAllByBoardItem(Notice notice) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void deleteById(NoticeComment noticeComment, Long id) {
//		noticeComment = em.find(NoticeComment.class, id);
//		em.remove(noticeComment);
//	}