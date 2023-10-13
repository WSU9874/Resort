package kr.ac.kopo.ctc.kopo20.resort.service;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.ctc.kopo20.resort.domain.Member;
import kr.ac.kopo.ctc.kopo20.resort.repository.MemberRepository;

@Service
public class MemberService {
	@Autowired
	private MemberRepository repository;

	public Optional<Member> findOneUserId(String userId) {
		return repository.findByUserid(userId);
	}
	
	public Optional<Member> findOneId(Long id) {
		return repository.findById(id);
	}

	public boolean existsByMemberId(String userid) throws Exception {
		boolean  result = repository.existsByUserid(userid);
		return result;
	}
	
	public Member updateInfo(Member member) {
		
		return repository.save(member);
	}
	
	public void deleteUser(Long id) {
		repository.deleteById(id);
		
	}

}
