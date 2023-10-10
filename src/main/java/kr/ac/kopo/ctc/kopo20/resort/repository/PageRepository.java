package kr.ac.kopo.ctc.kopo20.resort.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.ctc.kopo20.resort.domain.Notice;

@Repository
public interface  PageRepository extends JpaRepository<Notice, Integer>{
	Page<Notice> findAll(Pageable pageable);

}
