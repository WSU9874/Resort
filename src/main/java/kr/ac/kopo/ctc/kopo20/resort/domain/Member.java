package kr.ac.kopo.ctc.kopo20.resort.domain;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@Getter
@Component
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String userid;
	@Column
	private String pw;
	@Column
	private String roles;
	
	protected Member() {}
	
	private Member(Long id, String userid, String pw, String roleUser) {
        this.id = id;
        this.userid = userid;
        this.pw = pw;
        this.roles = roleUser;
    }
	
	public static Member createUser(String userId, String pw, PasswordEncoder passwordEncoder) {
		return new Member(null, userId, passwordEncoder.encode(pw), "USER");
	}
}
