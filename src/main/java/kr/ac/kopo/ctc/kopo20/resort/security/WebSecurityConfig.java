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
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().disable()
				.authorizeHttpRequests(request -> request.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
						.requestMatchers(new AntPathRequestMatcher("/status")).permitAll()
						.requestMatchers(new AntPathRequestMatcher("/bootstrap/**")).permitAll()
						.requestMatchers(new AntPathRequestMatcher("/summernote/**")).permitAll()
						.requestMatchers(new AntPathRequestMatcher("/css/**")).permitAll()
						.requestMatchers(new AntPathRequestMatcher("/js/**")).permitAll()
						.requestMatchers(new AntPathRequestMatcher("/join")).permitAll()
						.requestMatchers(new AntPathRequestMatcher("/auth/join")).permitAll()
						.requestMatchers(new AntPathRequestMatcher("/index")).permitAll()
						.requestMatchers(new AntPathRequestMatcher("/service")).permitAll()
						.requestMatchers(new AntPathRequestMatcher("/room")).permitAll()
						.requestMatchers(new AntPathRequestMatcher("/team")).permitAll()
						.requestMatchers(new AntPathRequestMatcher("/testimonial")).permitAll()
						.requestMatchers(new AntPathRequestMatcher("/contact")).permitAll()
						.requestMatchers(new AntPathRequestMatcher("/nearby")).permitAll()
						.requestMatchers(new AntPathRequestMatcher("/notice")).permitAll()
						.requestMatchers(new AntPathRequestMatcher("/setting/user")).hasRole("USER")		
                        .anyRequest().authenticated())
				.formLogin(login -> login.loginPage("/login") // [A] 커스텀 로그인 페이지 지정
						.loginProcessingUrl("/login-process") // [B] submit 받을 url
						.usernameParameter("userid") // [C] submit할 아이디
						.passwordParameter("pw") // [D] submit할 비밀번호
//						.defaultSuccessUrl("/dashboard", true).permitAll()
						.defaultSuccessUrl("/index", true).permitAll())

//	                .formLogin(login -> login	// form 방식 로그인 사용
//                    .defaultSuccessUrl("/dashboard", true)	// 성공 시 dashboard로
//                    .permitAll()	// 대시보드 이동이 막히면 안되므로 얘는 허용
//            )
				.logout(withDefaults());

		return http.build();
	}
}
//// 패스워드 인코더로 사용할 빈 등록
//@Bean
//public BCryptPasswordEncoder bCryptPasswordEncoder() {
//	return new BCryptPasswordEncoder();
//}

// 스프링 시큐리티 기능 비활성화

// 특정 HTTP 요청에 대한 웹 기반 보안 구성
//@Bean
//public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//	return http
//			.authorizeHttpRequests()			
//					.requestMatchers("/login","/signup","/user").permitAll()				
//					.anyRequest().authenticated()
//					.and()
//					.formLogin()
//					.loginPage("/login")
//					.defaultSuccessUrl("/index")
//					.and()
//					.logout()
//					.logoutSuccessUrl("/login")
//					.invalidateHttpSession(true)
//					.and()
//					.csrf().disable()
//					.build();
//					
//
//	 
//}
