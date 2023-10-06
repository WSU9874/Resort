package kr.ac.kopo.ctc.kopo20.resort.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ReserveDTO {
	private Long id;
	
	private Long roomId;
	
	private String name;

	private String email;

	private String checkIn;

	private String checkOut;

	private Long adultCount;

	private Long childCount;

	private String phoneNum;
	
	private String request;
}
