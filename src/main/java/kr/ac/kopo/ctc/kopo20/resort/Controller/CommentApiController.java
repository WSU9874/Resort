package kr.ac.kopo.ctc.kopo20.resort.Controller;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.kopo.ctc.kopo20.resort.domain.NoticeComment;
import kr.ac.kopo.ctc.kopo20.resort.service.NoticeCommentServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CommentApiController {

	private final NoticeCommentServiceImpl commentService;

	// 신규 댓글 생성
	@PostMapping("/posts/{noticeId}/comments")
	public NoticeComment saveComment(@PathVariable final Long noticeId, @RequestBody final NoticeComment params) {
		Date now = new Date();
		params.setCreatedDate(now);
		Long id = commentService.saveComment(params);
		System.out.println(now);
		System.out.println(id);
		return commentService.findCommentById(id);
//		return null;
	}
	
    // 댓글 리스트 조회
    @GetMapping("/posts/{noticeId}/comments")
    public List<NoticeComment> findAllComment(@PathVariable final Long noticeId) {
  
        return commentService.findAllComment(noticeId);
    }
    
    // 댓글 상세정보 조회
    @GetMapping("/posts/{noticeId}/comments/{commentId}")
    public NoticeComment findCommentById(@PathVariable final Long noticeId, @PathVariable final Long commentId) {
        return commentService.findCommentById(commentId);
    }


    // 기존 댓글 수정
    @PatchMapping("/posts/{noticeId}/comments/{commentId}")
    public NoticeComment updateComment(@PathVariable final Long noticeId, @PathVariable final Long commentId, @RequestBody final NoticeComment params) {
    	
    	commentService.updateComment(params);
        return commentService.findCommentById(commentId);
    }
    
 // 댓글 삭제
    @DeleteMapping("/posts/{noticeId}/comments/{commentId}")
    public Long deleteComment(@PathVariable final Long noticeId, @PathVariable final Long commentId) {
        return commentService.deleteComment(commentId);
    }

}