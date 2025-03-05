package com.spoteditor.backend.placelog.controller.dto;

import com.spoteditor.backend.placelog.entity.PlaceLogStatus;
import com.spoteditor.backend.tag.dto.TagDto;
import jakarta.annotation.Nullable;

import java.util.List;

public record PlaceLogUpdateRequest (
        @Nullable String name,
        @Nullable String description,
        @Nullable String originalFile,
        @Nullable String uuid,
        @Nullable PlaceLogStatus status,
        @Nullable List<TagDto> deleteTags,
        @Nullable List<TagDto> addTags,
        @Nullable List<Long> deletePlaceIds,
        @Nullable List<PlaceLogPlaceRegisterRequest> addPlaces,
        @Nullable List<PlaceLogPlaceUpdateRequest> updatePlaces
) {
}
