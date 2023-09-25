package kr.ac.kopo.ctc.kopo20.resort.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import kr.ac.kopo.ctc.kopo20.resort.domain.Member;
import kr.ac.kopo.ctc.kopo20.resort.service.MemberService;

@Component
public class MyUserDetailsService implements UserDetailsService{
	private final MemberService userService;
	
	@Autowired
	public MyUserDetailsService(MemberService userService) {
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String insertedUserId) throws UsernameNotFoundException {
		Optional<Member> findOne = userService.findOne(insertedUserId);
		Member user = findOne.orElseThrow(() -> new UsernameNotFoundException("없는 회원입니다"));
		
		return User.builder()
				.username(user.getUserid())
				.password(user.getPw())
				.roles(user.getRoles())
				.build();
	}

}
