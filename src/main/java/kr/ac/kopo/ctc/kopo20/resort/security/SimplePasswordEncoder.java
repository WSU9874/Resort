package kr.ac.kopo.ctc.kopo20.resort.security;

import org.springframework.security.crypto.password.PasswordEncoder;

public class SimplePasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) { //해당 암호화 방시으로 암호화한 문자열을 리턴
		// TODO Auto-generated method stub			//회원가입시 DC에 넣기전에 이것을 호출해서 암호화해야함
		return rawPassword.toString();
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) { //rawPassword 로그인 시 사용자가 입력한 비밀번호
		// TODO Auto-generated method stub										//usernameParameter("pw"로 설정해 둔 값이 rawPassword로 들어감
																				//encodedPassword DB에서 조회한 이미 암호화된 비밀번호
		return encodedPassword.equals(encode(rawPassword));						//따라서 rawPw를 단방향 암호화 후 encodedPw와 동일한지 확인해보면 원래
																				//암화 자체가 어떤 문자열인지는 누구도 알수 없지만 DB에 있는 거와 동일한지는 알수 있다.
	}

}
