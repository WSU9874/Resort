package kr.ac.kopo.ctc.kopo20.resort.domain;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import groovy.transform.builder.Builder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 20, nullable = false, unique = true)
	private String userid;
	@Column(nullable = false, unique = true)
	private String pw;
	@Column
	private String roles;
	@Column
	private String email;
	@Column
	private String phone;
	@Column(length = 20)
	private String address;
	@Column(length = 20, nullable = false, unique = true)
	private String nickname;

	public static Member createUser(String userId, String pw, PasswordEncoder passwordEncoder, String email,
			String phone, String address, String nickname) {
		return new Member(null, userId, passwordEncoder.encode(pw), "USER", email, phone, address, nickname);
	}

}
