package kr.ac.kopo.ctc.kopo20.resort.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.ctc.kopo20.resort.domain.CommentRequest;
import kr.ac.kopo.ctc.kopo20.resort.domain.CommentResponse;
import kr.ac.kopo.ctc.kopo20.resort.repository.Repo;

@Service
public class NoticeCommentServiceImpl implements Serv.NoticeCommentService {

	@Autowired
	private Repo.NoticeCommentRepository cRepo;

	@Override
	public Long saveComment(CommentRequest params) {
		cRepo.save(params);
		return params.getCommentId();

	}

	@Override
	public CommentResponse findCommentById(Long commentId) {
		return cRepo.findById(commentId);
	}

	@Override
	public List<CommentResponse> findAllComment(Long noticeId) {
		return cRepo.findAll(noticeId);

	}

	@Override
	public Long updateComment(CommentRequest params) {
		cRepo.update(params);
		return params.getCommentId();
	}

	@Override
	public Long deleteComment(Long commentId) {
		cRepo.deleteById(commentId);
		return commentId;
	}

}
