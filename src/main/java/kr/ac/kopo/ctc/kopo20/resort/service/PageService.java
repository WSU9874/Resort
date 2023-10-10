package kr.ac.kopo.ctc.kopo20.resort.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kr.ac.kopo.ctc.kopo20.resort.domain.Notice;
import kr.ac.kopo.ctc.kopo20.resort.repository.PageRepository;

@Service
public class PageService {
	@Autowired
	private PageRepository pageRepository;

	public Page<Notice> getList(int page) {
		List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("noticeId"));
        Pageable pageable = PageRequest.of(page, 10,Sort.by(sorts));
        return this.pageRepository.findAll(pageable);
    }
}
