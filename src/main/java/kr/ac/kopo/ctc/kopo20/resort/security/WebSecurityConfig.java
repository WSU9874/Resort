package kr.ac.kopo.ctc.kopo20.resort.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;


@Configuration
public class WebSecurityConfig {

	    @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http.csrf().disable().cors().disable()
	                .authorizeHttpRequests(request -> request
	                	.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
	                        .anyRequest().authenticated()	// 어떠한 요청이라도 인증필요
	                )
	                .formLogin(login -> login	// form 방식 로그인 사용
	                        .defaultSuccessUrl("/index", true)	// 성공 시 dashboard로
	                        .permitAll()	// 대시보드 이동이 막히면 안되므로 얘는 허용
	                )
	                .logout(withDefaults());	// 로그아웃은 기본설정으로 (/logout으로 인증해제)

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

//@Bean
//public DaoAuthenticationProvider daoAuthenticationProvider() throws Exception {
//	DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//
//	daoAuthenticationProvider.setUserDetailsService(userService);
//	daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
//
//	return daoAuthenticationProvider;
//}
//


