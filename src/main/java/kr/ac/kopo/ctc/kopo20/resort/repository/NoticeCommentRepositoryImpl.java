package kr.ac.kopo.ctc.kopo20.resort.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
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
	public void deleteById(Long commentId) {
		NoticeComment params = em.find(NoticeComment.class, commentId);
		em.remove(params);
	}

	@Override
	public int count(Long noticeId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<NoticeComment> findAll(Long noticeId) {
		List<NoticeComment> comments = em.createQuery(
				"SELECT c FROM NoticeComment c WHERE c.notice.noticeId = :noticeId AND c.deleteYn = false ORDER BY c.createdDate DESC",
				NoticeComment.class).setParameter("noticeId", noticeId).getResultList();
		return comments;
	}
}
