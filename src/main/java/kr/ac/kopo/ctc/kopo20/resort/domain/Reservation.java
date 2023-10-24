package kr.ac.kopo.ctc.kopo20.resort.domain;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "reservation")
@Data
@Component
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private Long roomId;
	@Column(length = 20, nullable = false)
	private String name;
	@Column(length = 20, nullable = false)
	private String email;
	@Column(length = 20, nullable = false)
	private Long adultCount;
	@Column(length = 20, nullable = false)
	private Long childCount;
	@Column(length = 20, nullable = false)
	private String phoneNum;
	@Column
	private String request;
	@Column(nullable = false)
	private String reservationDate;

}