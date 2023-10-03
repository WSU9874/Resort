package kr.ac.kopo.ctc.kopo20.resort.dto;

import lombok.Data;

@Data
public class NoticeDTO {
	
	private Long noticeId;

	private String title;

	private String writer;

	private String date;

	private String content;

	private Long viewCount;

}
