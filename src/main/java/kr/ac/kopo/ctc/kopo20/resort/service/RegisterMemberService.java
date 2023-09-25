package kr.ac.kopo.ctc.kopo20.resort.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.ac.kopo.ctc.kopo20.resort.domain.Member;
import kr.ac.kopo.ctc.kopo20.resort.repository.MemberRepository;

@Service
public class RegisterMemberService {
	private final PasswordEncoder passwordEncoder;
	private final MemberRepository memberRepository;
	
	@Autowired
	public RegisterMemberService(PasswordEncoder passwordEncoder, MemberRepository memberRepository) {
		this.passwordEncoder = passwordEncoder;
		this.memberRepository = memberRepository;
	}
	
	public Long join(String userid, String pw) {
		Member member = Member.createUser(userid, pw, passwordEncoder);
		validateDuplicateMember(member);
		memberRepository.save(member);
		
		return member.getId();
		
	}

	private void validateDuplicateMember(Member member) {
		// TODO Auto-generated method stub
		
	}

}
