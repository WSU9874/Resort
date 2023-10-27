package kr.ac.kopo.ctc.kopo20.resort.security;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import jakarta.servlet.DispatcherType;

@Configuration
public class WebSecurityConfig {
	@Autowired
    private AuthenticationSuccessHandler customAuthenticationSuccessHandler;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.httpBasic(HttpBasicConfigurer::disable)
		.csrf(AbstractHttpConfigurer::disable)
		.cors(withDefaults())
		.authorizeHttpRequests(request -> request.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
				.requestMatchers(new AntPathRequestMatcher("/reserveClick")).authenticated()
				.requestMatchers(new AntPathRequestMatcher("/reservationStatus")).authenticated()
				.requestMatchers(new AntPathRequestMatcher("/setting/user")).hasRole("USER").anyRequest().permitAll())
				.formLogin(login -> login.loginPage("/login") // [A] 커스텀 로그인 페이지 지정
						.loginProcessingUrl("/login-process") // [B] submit 받을 url
						.usernameParameter("userid") // [C] submit할 아이디
						.passwordParameter("pw") // [D] submit할 비밀번호
						.defaultSuccessUrl("/index", true).permitAll()
						.defaultSuccessUrl("/")
						.successHandler(customAuthenticationSuccessHandler))				
				.logout(withDefaults());
		http.headers().frameOptions().sameOrigin();

		return http.build();
	}    
}

