package com.s3test.controller;

import com.s3test.service.S3ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class FileUploadController {

	private final S3ImageService s3ImageService;

	@PostMapping("/api/upload")
	public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("file") MultipartFile file) {
		// 서비스에서 파일 처리
		String url = s3ImageService.upload(file);

		// 결과(이미지 주소)를 JSON으로 돌려줌
		Map<String, String> response = new HashMap<>();
		response.put("imageUrl", url);

		return ResponseEntity.ok(response);
	}
}
