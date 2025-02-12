package com.spoteditor.backend.user.controller;

import com.spoteditor.backend.config.security.annotation.AdminOnly;
import com.spoteditor.backend.user.common.dto.UserIdDto;
import com.spoteditor.backend.user.controller.dto.UserResponse;
import com.spoteditor.backend.user.controller.dto.UserUpdateRequest;
import com.spoteditor.backend.user.controller.dto.UserUpdateResponse;
import com.spoteditor.backend.user.service.UserService;
import com.spoteditor.backend.user.service.dto.UserResult;
import com.spoteditor.backend.user.service.dto.UserUpdateCommand;
import com.spoteditor.backend.user.service.dto.UserUpdateResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<UserResponse> getUser(
            @AuthenticationPrincipal UserIdDto userIdDto
    ) {
        UserResult userResult = userService.getUser(userIdDto.getId());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(UserResponse.from(userResult));
    }

    @PatchMapping("/users")
    public ResponseEntity<UserUpdateResponse> updateUser(
            @AuthenticationPrincipal UserIdDto userIdDto,
            @RequestBody UserUpdateRequest userUpdateRequest
    ) {
        UserUpdateCommand command = userUpdateRequest.from();
        UserUpdateResult result = userService.updateUser(userIdDto.getId(), command);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(UserUpdateResponse.from(result));
    }

    @DeleteMapping("/users")
    public ResponseEntity<Void> deleteUser(
            @AuthenticationPrincipal UserIdDto userIdDto
    ) {
        userService.deleteUser(userIdDto.getId());

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
