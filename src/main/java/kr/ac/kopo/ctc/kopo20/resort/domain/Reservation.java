package kr.ac.kopo.ctc.kopo20.resort.domain;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "reservation")
@Component
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private Long id;
	@Column
	@Getter
	@Setter
	private Long roomId;
	@Column(length = 20, nullable = false)
	@Getter
	@Setter
	private String name;
	@Column(length = 20, nullable = false)
	@Getter
	@Setter
	private String email;
	@Column(length = 20, nullable = false)
	@Getter
	@Setter
	private Long adultCount;
	@Column(length = 20, nullable = false)
	@Getter
	@Setter
	private Long childCount;
	@Column(length = 20, nullable = false)
	@Getter
	@Setter
	private String phoneNum;
	@Getter
	@Column
	@Setter
	private String request;
	@Column
	@Getter
	@Setter
	private String reservationDate;

}