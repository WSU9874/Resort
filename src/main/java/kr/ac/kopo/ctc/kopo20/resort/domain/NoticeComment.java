package kr.ac.kopo.ctc.kopo20.resort.domain;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "noticeComment")
@Data
@Component
public class NoticeComment {
	@ManyToOne(optional = false)
	@JoinColumn(name = "noticeId")
	private Notice notice;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long commentId;
	@Column
	private String title;
	@Column
	private String writer;
	@Column
	private String date;
	@Column
	private String content;
	@Column
	private String viewCount;

}
