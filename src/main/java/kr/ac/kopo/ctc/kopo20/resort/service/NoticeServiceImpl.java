package kr.ac.kopo.ctc.kopo20.resort.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.kopo.ctc.kopo20.resort.domain.Notice;
import kr.ac.kopo.ctc.kopo20.resort.repository.Repo;

@Service
@Transactional
public class NoticeServiceImpl implements Serv.NoticeService {

	@Autowired
	private Repo.NoticeRepository noticeRepository;

	@Override
	public void create(Notice notice) {

		noticeRepository.save(notice);
	}

	@Override
	public List<Notice> readAllNotice() {

		return noticeRepository.findAll();
	}

	@Override
	public Optional<Notice> readOneNotice(long id) {

		return null;
	}

	@Override
	public void updateOneNotice(Notice notice) {


	}

	@Override
	public void deleteOneNotice(long id) {

		noticeRepository.deleteById(id);
	}
	@Override
	public void viewCount(Notice notice) {
		notice.setViewCount(notice.getViewCount() + 1);
		noticeRepository.save(notice);
	}

}
