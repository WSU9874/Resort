package kr.ac.kopo.ctc.kopo20.resort.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer.AuthorizedUrl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jakarta.servlet.DispatcherType;

@Configuration
public class WebSecurityConfig {

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(request -> request.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
				.requestMatchers(new AntPathRequestMatcher("/reserveClick")).authenticated()
				.requestMatchers(new AntPathRequestMatcher("/reservationStatus")).authenticated()
				.requestMatchers(new AntPathRequestMatcher("/setting/user")).hasRole("USER").anyRequest().permitAll())
				.formLogin(login -> login.loginPage("/login") // [A] 커스텀 로그인 페이지 지정
						.loginProcessingUrl("/login-process") // [B] submit 받을 url
						.usernameParameter("userid") // [C] submit할 아이디
						.passwordParameter("pw") // [D] submit할 비밀번호
						.defaultSuccessUrl("/index", true).permitAll())
				.logout(withDefaults()).csrf(csrf -> {
					try {
						csrf.disable().cors(cors -> cors.disable());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});

		return http.build();
	}
}
//.requestMatchers(new AntPathRequestMatcher("/status")).permitAll()
//.requestMatchers(new AntPathRequestMatcher("/bootstrap/**")).permitAll()
//.requestMatchers(new AntPathRequestMatcher("/summernote/**")).permitAll()
//.requestMatchers(new AntPathRequestMatcher("/css/**")).permitAll()
//.requestMatchers(new AntPathRequestMatcher("/js/**")).permitAll()
//.requestMatchers(new AntPathRequestMatcher("/join")).permitAll()
//.requestMatchers(new AntPathRequestMatcher("/auth/idCheck")).permitAll()/////
//.requestMatchers(new AntPathRequestMatcher("/auth/nickCheck")).permitAll()/////
//.requestMatchers(new AntPathRequestMatcher("/auth/join")).permitAll()
//.requestMatchers(new AntPathRequestMatcher("/index")).permitAll()
//.requestMatchers(new AntPathRequestMatcher("/service")).permitAll()
//.requestMatchers(new AntPathRequestMatcher("/room")).permitAll()
//.requestMatchers(new AntPathRequestMatcher("/team")).permitAll()
//.requestMatchers(new AntPathRequestMatcher("/testimonial")).permitAll()
//.requestMatchers(new AntPathRequestMatcher("/contact")).permitAll()
//.requestMatchers(new AntPathRequestMatcher("/nearby")).permitAll()

//.formLogin(login -> login	// form 방식 로그인 사용
//.defaultSuccessUrl("/dashboard", true)	// 성공 시 dashboard로
//.permitAll()	// 대시보드 이동이 막히면 안되므로 얘는 허용
//)