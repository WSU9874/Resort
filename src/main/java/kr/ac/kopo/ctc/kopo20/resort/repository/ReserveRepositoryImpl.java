package kr.ac.kopo.ctc.kopo20.resort.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import kr.ac.kopo.ctc.kopo20.resort.domain.Notice;
import kr.ac.kopo.ctc.kopo20.resort.domain.Reservation;

@Repository
public class ReserveRepositoryImpl implements Repo.ReserveRepository {

	// jpa는 엔티티메니저로 작동함 도메인과 연결해줌
	private final EntityManager em;

	@Autowired
	public ReserveRepositoryImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public Reservation save(Reservation reservation) {
		em.persist(reservation);
		return reservation;
	}

	@Override
	public Optional<Reservation> findById(Long id) {
		Reservation reservation = em.find(Reservation.class, id);
		return Optional.ofNullable(reservation);
	}

	@Override
	public List<Reservation> findAllById(String name) {
		List<Reservation> result = em.createQuery("select r from Reservation r where r.name = :name", Reservation.class)
				.setParameter("name", name)
				.getResultList(); //JPQL
		return result;
	}
	
	@Override
	public List<Reservation> findAll() {
		List<Reservation> result = em.createQuery("select r from Reservation r", Reservation.class)
				.getResultList(); //JPQL
		return result;
	}

	@Override
	public void deleteById(Reservation re, Long id) {
		re = em.find(Reservation.class, id);
		em.remove(re);
	}

}
