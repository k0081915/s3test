package com.s3test.service;

import io.awspring.cloud.s3.S3Exception;
import io.awspring.cloud.s3.S3Template;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3ImageService {

	private final S3Template s3Template;

	@Value("${spring.cloud.aws.s3.bucket}")
	private String bucketName;

	public String upload(MultipartFile file) {
		if (file.isEmpty()) {
			throw new RuntimeException("파일이 비어있습니다.");
		}

		// 파일 이름 중복 방지를 위한 UUID 생성
		String originalFileName = file.getOriginalFilename();
		String uuid = UUID.randomUUID().toString();
		String key = uuid + "_" + originalFileName;

		try (InputStream inputStream = file.getInputStream()) {
			// S3에 파일 업로드 (Spring Cloud AWS 3.0 문법)
			s3Template.upload(bucketName, key, inputStream);

			// 업로드된 파일의 S3 URL 반환
			return s3Template.download(bucketName, key).getURL().toString();
		} catch (IOException e) {
			throw new RuntimeException("파일 업로드 중 오류가 발생했습니다.", e);
		} catch (S3Exception e) {
			throw new RuntimeException("S3 서비스 오류: " + e.getMessage());
		}
	}
}
