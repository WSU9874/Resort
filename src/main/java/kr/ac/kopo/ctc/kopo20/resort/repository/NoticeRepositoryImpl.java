package kr.ac.kopo.ctc.kopo20.resort.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import kr.ac.kopo.ctc.kopo20.resort.domain.Notice;

@Repository
public class NoticeRepositoryImpl implements Repo.NoticeRepository {

	// jpa는 엔티티메니저로 작동함 도메인과 연결해줌
	private final EntityManager em;

	@Autowired
	public NoticeRepositoryImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public Notice save(Notice notice) {
		// TODO Auto-generated method stub
		em.persist(notice);
		return notice;
	}

	@Override
	public Optional<Notice> findById(Long id) {
		// TODO Auto-generated method stub
		Notice notice = em.find(Notice.class, id);
		return Optional.ofNullable(notice);
	}

	@Override
	public List<Notice> findAll() {
		List<Notice> notice = em.createQuery("SELECT n FROM Notice n ORDER BY n.noticeId DESC", Notice.class)
				.getResultList();
		return notice;
	}

//	@Override
//	public Notice deleteById(Long id) {
//		List<Notice> notice = em.createQuery("delete n from notice n where n.id = :id", Notice.class).getResultList(); // JPQL
//		return null;
//	}

	@Override
	public void deleteById(Notice notice, Long id) {
		notice = em.find(Notice.class, id);
		em.remove(notice);

	}

	@Override
	public Optional<Notice> findByWriter(String writer) {
		List<Notice> result = em.createQuery("select m from Member m where m.writer = :writer", Notice.class)
				.setParameter("writer", writer).getResultList();
		return result.stream().findAny();
	}

}
