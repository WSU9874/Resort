package kr.ac.kopo.ctc.kopo20.resort.domain;

import java.util.Collection;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "notice")
@Data
@Component
public class Notice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long noticeId;
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
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "notice", fetch = FetchType.LAZY)
	private Collection<NoticeComment> noticeComment;

}
