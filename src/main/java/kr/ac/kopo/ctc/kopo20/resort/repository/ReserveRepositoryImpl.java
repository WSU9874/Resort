package kr.ac.kopo.ctc.kopo20.resort.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
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
		// TODO Auto-generated method stub
		em.persist(reservation);
		return reservation;
	}

	@Override
	public Optional<Reservation> findById(Long id) {
		// TODO Auto-generated method stub
		Reservation reservation = em.find(Reservation.class, id);
		return Optional.ofNullable(reservation);
	}

	@Override
	public List<Reservation> findAll() {
		List<Reservation> result = em.createQuery("select r from Reservation r", Reservation.class).getResultList(); //JPQL
		return result;
	}

	@Override
	public Reservation deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
