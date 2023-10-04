package kr.ac.kopo.ctc.kopo20.resort.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentRequest {
	
	private Long commentId;
	private Long noticeId;
	private String content;
	private String writer;
}
