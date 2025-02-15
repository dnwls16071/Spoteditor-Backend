package com.spoteditor.backend.user.controller.dto;

import com.spoteditor.backend.user.service.dto.UserUpdateCommand;

public record UserUpdateRequest (
        String name,
        String imageUrl,
        String description,
        String instagramId
) {
    public UserUpdateCommand from(){
        return new UserUpdateCommand(
                name,
                imageUrl,
                description,
                instagramId
        );
    }
}
