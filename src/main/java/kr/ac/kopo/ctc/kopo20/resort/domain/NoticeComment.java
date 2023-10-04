package kr.ac.kopo.ctc.kopo20.resort.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
	
	@Column(name = "content", nullable = false, length = 1000)
	private String content;

	@Column(name = "writer", nullable = false, length = 20)
	private String writer;
	
    @Column(name = "delete_yn", nullable = false)
    private boolean deleteYn;
	
    @Column(name = "created_date", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
	
}   

    // Getters and setters

    // Constructors

    // toString, equals, hashCode (if necessary)

