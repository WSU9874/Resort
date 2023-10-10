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

//	@Autowired
//	public MemberService(MemberRepository repository) {
//		this.repository = repository;
//	}

	public Optional<Member> findOne(String userId) {
		return repository.findByUserid(userId);
	}

	public boolean existsByMemberId(String userid) throws Exception {
		boolean  result = repository.existsByUserid(userid);
		return result;
	}

}
