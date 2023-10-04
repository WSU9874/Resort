package kr.ac.kopo.ctc.kopo20.resort.domain;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "notice")
@Data
@Component
public class Notice{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long noticeId;
	@Column(nullable = false)
	private String title;
	@Column
	private String writer;
	@Column
	private LocalDate date;
	@Column(nullable = false, length = 10000)
	private String content;
	@Column
	private Long viewCount;
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "notice", fetch = FetchType.LAZY)
//	@OrderBy("noticeId")
//	private Set<NoticeComment> noticeComment = new LinkedHashSet<>();

}
