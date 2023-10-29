package kr.ac.kopo.ctc.kopo20.resort.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.kopo.ctc.kopo20.resort.domain.Reservation;
import kr.ac.kopo.ctc.kopo20.resort.repository.Repo;
import kr.ac.kopo.ctc.kopo20.resort.service.Serv.ReservationListService;

@Service
@Transactional
public class ReservationServiceImpl implements Serv.ReservationListService {

	@Autowired
	private Repo.ReserveRepository reserveRepository;

	// Create
	@Override
	public void create(Reservation reservation) {
		reserveRepository.save(reservation);
	}
	// Read
	@Override
	public Reservation readOneReservation(Long id) {
		Reservation re = reserveRepository.findById(id).orElse(new Reservation());
		return re;
	}
	
	@Override
	public List<Reservation> readAllReservation() {
		return reserveRepository.findAll();
	}

	// Update
	@Override
	public void updateOneReservation(Reservation reservation) {
		reserveRepository.save(reservation);
	}

	// Delete
	@Override
	public void deleteOneReservation(Reservation re, Long id) {
		reserveRepository.deleteById(re, id);
	}
	
	@Override
	public List<Reservation> getReservationsFor30Days() {
		List<Reservation> result = reserveRepository.findAll();
		return result;
	}
	@Override
	public List<Reservation> getReservationById(String name) {
		return reserveRepository.findAllById(name);
	}

}
