package kr.ac.kopo.ctc.kopo20.resort.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MemberDTO {
	
	@NotBlank(message = "아이디는 필수 입력값입니다.")
	@Pattern(regexp = "^[a-z0-9]{4,20}$", message = "아이디는 영어 소문자와 숫자만 사용하여 4~20자리여야 합니다.")
	private String userid;
	
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$", message = "비밀번호는 8~16자리수여야 합니다. 영문 대소문자, 숫자, 특수문자를 1개 이상 포함해야 합니다.")
	private String pw;
	
	private String roles;
	
	@NotBlank(message = "이메일은 필수 입력값입니다.")
	@Email
	private String email;
	
	@NotBlank(message = "휴대폰번호는 필수 입력값입니다.")
	private String phone;
	
	@NotBlank(message = "주소는 필수 입력값입니다.")
	private String address;
	
	@NotBlank(message = "닉네임은 필수 입력값입니다.")
	@Pattern(regexp = "^[가-힣a-zA-Z0-9]{2,10}$" , message = "닉네임은 특수문자를 포함하지 않은 2~10자리여야 합니다.")
	private String nickname;

}
