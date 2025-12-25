package com.s3test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") // 모든 API 경로에 대해
				.allowedOrigins("https://d1mwcbuf9zywax.cloudfront.net") // 이 주소만 허락한다
				.allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 HTTP 메서드
				.allowedHeaders("*") // 모든 헤더 허용
				.allowCredentials(true) // 쿠키/인증 정보 포함 허용
				.maxAge(3600); // 설정 캐시 시간 (1시간)
	}
}
