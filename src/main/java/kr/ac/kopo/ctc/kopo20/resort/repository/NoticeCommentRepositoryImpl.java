package kr.ac.kopo.ctc.kopo20.resort.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import kr.ac.kopo.ctc.kopo20.resort.domain.CommentRequest;
import kr.ac.kopo.ctc.kopo20.resort.domain.CommentResponse;
import kr.ac.kopo.ctc.kopo20.resort.domain.Notice;
import kr.ac.kopo.ctc.kopo20.resort.domain.NoticeComment;


@Repository
@Transactional
public class NoticeCommentRepositoryImpl implements Repo.NoticeCommentRepository {

	private final EntityManager em;
	
	@Autowired
	public NoticeCommentRepositoryImpl(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public void save(NoticeComment params) {
		em.persist(params);
		
	}

	@Override
	public NoticeComment findById(Long commentId) {
		NoticeComment noticeComment = em.find(NoticeComment.class, commentId);
		return noticeComment;
	}

	@Override
	public void update(NoticeComment params) {
	    // 네이티브 SQL 쿼리 실행
	    String sqlQuery = "INSERT INTO tb_comment (commentId, noticeId, content, writer, deleteYn, createdDate, updatedDate) " +
	                      "VALUES (:id, :postId, :content, :writer, 0, CURRENT_TIMESTAMP, NULL)";
	    Query query = em.createNativeQuery(sqlQuery);
	    query.setParameter("id", params.getCommentId());
	    query.setParameter("postId", params.getNoticeId());
	    query.setParameter("content", params.getContent());
	    query.setParameter("writer", params.getWriter());

	    // 트랜잭션 시작
	    em.getTransaction().begin();

	    // 쿼리 실행
	    query.executeUpdate();

	    // 트랜잭션 커밋
	    em.getTransaction().commit();
	}


	@Override
	public void deleteById(Long commentId) {
		em.remove(commentId);
	}

//	@Override
//	public List<CommentResponse> findAll(Long postId) {
//		List<Notice> notice = em.createQuery("SELECT n FROM notice_comment n where n.noticeId DESC", Notice.class)
//				.getResultList();
//		return null;
//	}

	@Override
	public int count(Long noticeId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<NoticeComment> findAll(Long noticeId) {
		List<NoticeComment> comments = em.createQuery("SELECT c FROM NoticeComment c WHERE c.notice.noticeId = :noticeId AND c.deleteYn = false ORDER BY c.createdDate DESC", NoticeComment.class)
                .setParameter("noticeId", noticeId)
                .getResultList();
	    
	 // 쿼리 결과를 출력
        for (NoticeComment comment : comments) {
            System.out.println("Comment ID: " + comment.getCommentId());
            System.out.println("Content: " + comment.getContent());
            System.out.println("Writer: " + comment.getWriter());
            System.out.println("Created Date: " + comment.getCreatedDate());
            System.out.println("----------------------");
        }

        return comments;
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