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
	@Column
	private String name;
	@Column
	private String email;
	@Column
	private String checkIn;
	@Column
	private String checkOut;
	@Column
	private Long adultCount;
	@Column
	private Long childCount;
	@Column
	private String phoneNum;
	@Column
	private String request;
	@Column
	private String reservationDate;

}


