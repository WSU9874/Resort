package kr.ac.kopo.ctc.kopo20.resort.dto;

import lombok.Data;

@Data
public class MemberDTO {
	
	private String userid;
	private String pw;
	private String roles;
	private String email;
	private String phone;
	private String address;
	private String nickName;

}
