package kr.ac.kopo.ctc.kopo20.resort.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.ctc.kopo20.resort.domain.Member;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

	Optional<Member> findByUserid(String userId);
	
	Optional<Member> findById(Long id);
	
	boolean existsByUserid(String userid);
	
	boolean existsByNickname(String nickname);
	
	Member save(Member member);
	
	void deleteById(Long id);


}
