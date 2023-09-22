package kr.ac.kopo.ctc.kopo20.resort.dto;

import lombok.Data;

@Data
public class NoticeCommentDTO {
	private Long commentId;

	private String title;

	private String writer;

	private String date;

	private String content;

	private String viewCount;

}
