package com.spoteditor.backend.follow.controller;

import com.spoteditor.backend.config.page.CustomPageRequest;
import com.spoteditor.backend.config.page.CustomPageResponse;
import com.spoteditor.backend.config.swagger.FollowApiDocument;
import com.spoteditor.backend.follow.controller.dto.FollowRequest;
import com.spoteditor.backend.follow.controller.dto.FollowResponse;
import com.spoteditor.backend.follow.repository.FollowRepository;
import com.spoteditor.backend.follow.service.FollowService;
import com.spoteditor.backend.user.common.dto.UserIdDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "follow", description = "팔로우 API")
public class FollowApiController implements FollowApiDocument {

	private final FollowService followService;
	private final FollowRepository followRepository;

	@PostMapping("/follow")
	public ResponseEntity<Void> follow(@AuthenticationPrincipal UserIdDto dto,
									   @RequestBody FollowRequest request) {

		followService.saveFollow(dto.getId(), request);

		return ResponseEntity
				.status(HttpStatus.CREATED)
				.build();
	}

	@DeleteMapping("/unfollow")
	public ResponseEntity<Void> unfollow(@AuthenticationPrincipal UserIdDto dto,
										 @RequestBody FollowRequest request) {

		followService.removeFollow(dto.getId(), request);
		return ResponseEntity
				.status(HttpStatus.NO_CONTENT)
				.build();
	}

	@GetMapping("/following")
	public ResponseEntity<CustomPageResponse<FollowResponse>> followingList(@AuthenticationPrincipal UserIdDto dto,
																			CustomPageRequest request) {

		CustomPageResponse<FollowResponse> data = followRepository.findAllFollowing(dto.getId(), request);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(data);
	}

	@GetMapping("/follower")
	public ResponseEntity<CustomPageResponse<FollowResponse>> followerList(@AuthenticationPrincipal UserIdDto dto,
																		   CustomPageRequest request) {

		CustomPageResponse<FollowResponse> data = followRepository.findAllFollower(dto.getId(), request);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(data);
	}
}
