package com.spoteditor.backend.image.controller.dto;

public record PreSignedUrlResponse(
		String preSignedUrl,
		String uuid
) {

	public static PreSignedUrlResponse from(String preSignedUrl, String uuid) {
		return new PreSignedUrlResponse(preSignedUrl, uuid);
	}
}
