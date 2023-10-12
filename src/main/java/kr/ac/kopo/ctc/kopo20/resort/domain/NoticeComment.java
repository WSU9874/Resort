package kr.ac.kopo.ctc.kopo20.resort.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Table(name = "noticeComment")
@Data
@Component
public class NoticeComment{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long commentId;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "noticeId")
	private Notice notice;
	
	public Long getNoticeId() {
        if (notice != null) {
            return notice.getNoticeId();
        }
        return null;
    }
	
	public void setNoticeId(Long noticeId) {
        if (this.notice == null) {
            this.notice = new Notice();
        }
        this.notice.setNoticeId(noticeId);
    }
	
	@Column(name = "content", length = 1000)
	private String content;

	@Column(name = "writer", length = 20)
	private String writer;
	
    @Column(name = "delete_yn")
    private boolean deleteYn;
	
    @Column(name = "created_date", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
	
}   