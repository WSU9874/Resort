package kr.ac.kopo.ctc.kopo20.resort.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class RoomStatus {

	private String reservationDate;
	private String week;
	private String room1Status;
	private String room2Status;
	private String room3Status;
	

	public RoomStatus(String room1Status, String room2Status, String room3Status) {
		super();
		this.room1Status = room1Status;
		this.room2Status = room2Status;
		this.room3Status = room3Status;
	}
	
	
	

}