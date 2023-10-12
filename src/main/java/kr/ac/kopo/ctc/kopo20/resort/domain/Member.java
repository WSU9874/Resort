package kr.ac.kopo.ctc.kopo20.resort.domain;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import groovy.transform.builder.Builder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@Getter
@Component
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, unique = true)
	private String userid;
	@Column
	private String pw;
	@Column
	private String roles;
	@Column(length = 20, nullable = false, unique = true)
	private String email;
	@Column(length = 11, nullable = false, unique = true)
	private String phone;
	@Column(length = 15)
	private String address;
	@Column(length = 11, nullable = false, unique = true)
	private String nickname;

	public static Member createUser(String userId, String pw, PasswordEncoder passwordEncoder, String email,
			String phone, String address, String nickname) {
		return new Member(null, userId, passwordEncoder.encode(pw), "USER", email, phone, address, nickname);
	}

}
