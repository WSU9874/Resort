package kr.ac.kopo.ctc.kopo20.resort.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	/**
	 * @param registry
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("http://localhost:8082","http://www.polysort.site", "http://polysort.site") // 허용할 도메인
				.allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 HTTP 메서드
				.allowCredentials(true) // 인증 정보 허용
				.maxAge(3600); // preflight 요청 결과를 캐시할 시간(초)
	}
}
