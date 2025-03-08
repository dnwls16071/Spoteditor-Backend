package com.spoteditor.backend.placelog.controller;

import com.spoteditor.backend.config.swagger.PlaceLogBookmarkApiDocument;
import com.spoteditor.backend.placelog.controller.dto.PlaceLogBookmarkResponse;
import com.spoteditor.backend.placelog.service.PlaceLogBookmarkService;
import com.spoteditor.backend.placelog.service.PlaceLogService;
import com.spoteditor.backend.user.common.dto.UserIdDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PlaceLogBookmarkController implements PlaceLogBookmarkApiDocument {

    private final PlaceLogBookmarkService placeLogBookmarkService;
    private final PlaceLogService placeLogService;

    @Override
    @PostMapping("/placelogs/{placeLogId}/bookmark")
    public ResponseEntity<Void> addBookmark(
            @AuthenticationPrincipal UserIdDto userIdDto,
            @PathVariable Long placeLogId
    ) {
        placeLogBookmarkService.addBookmark(userIdDto.getId(), placeLogId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @Override
    @DeleteMapping("/placelogs/{placeLogId}/bookmark")
    public ResponseEntity<Void> removeBookmark(
            @AuthenticationPrincipal UserIdDto userIdDto,
            @PathVariable Long placeLogId
    ) {
        placeLogBookmarkService.removeBookmark(userIdDto.getId(), placeLogId);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping("/placelogs/{placeLogId}/bookmark")
    public ResponseEntity<List<PlaceLogBookmarkResponse>> getBookmarkStatus(
            @AuthenticationPrincipal UserIdDto userIdDto,
            @PathVariable Long placeLogId
    ) {
        List<PlaceLogBookmarkResponse> placeBookmarkStatus = placeLogService.getPlaceBookmarkStatus(userIdDto.getId(), placeLogId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(placeBookmarkStatus);
    }
}
