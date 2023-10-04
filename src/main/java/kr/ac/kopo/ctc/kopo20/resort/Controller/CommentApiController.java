//package kr.ac.kopo.ctc.kopo20.resort.Controller;
//
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import kr.ac.kopo.ctc.kopo20.resort.domain.CommentRequest;
//import kr.ac.kopo.ctc.kopo20.resort.domain.CommentResponse;
//import kr.ac.kopo.ctc.kopo20.resort.service.NoticeCommentServiceImpl;
//import lombok.RequiredArgsConstructor;
//
//@RestController
//@RequiredArgsConstructor
//public class CommentApiController {
//
//	private final NoticeCommentServiceImpl commentService;
//
//	// 신규 댓글 생성
//	@PostMapping("/posts/{noticeId}/comments")
//	public CommentResponse saveComment(@PathVariable final Long noticeId, @RequestBody final CommentRequest params) {
////		Long id = commentService.saveComment(params);
//		Long id = (long) 1;
//		return commentService.findCommentById(id);
//	}
//
//}