package com.spoteditor.backend.security.oauth.dto;

import com.spoteditor.backend.config.oauth.dto.OauthAttributes;
import com.spoteditor.backend.config.oauth.dto.OauthAttributesUserMapper;
import com.spoteditor.backend.user.entity.User;
import com.spoteditor.backend.user.entity.OauthProvider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OauthAttributesUserMapperTest {

    private final OauthAttributesUserMapper mapper = new OauthAttributesUserMapper();

    @Test
    @DisplayName("toEntity 메서드로 유저 생성")
    void testToEntity() {
        // given
        OauthAttributes attributes = OauthAttributes.builder()
                .oauthUserId("1234567890")
                .name("Test User")
                .imageUrl("http://example.com/image.jpg")
                .email("testuser@example.com")
                .provider(OauthProvider.KAKAO)
                .build();

        // when
        User user = mapper.toEntity(attributes);

        // then
        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getOauthUserId()).isEqualTo("1234567890");
        Assertions.assertThat(user.getName()).isEqualTo("Test User");
        Assertions.assertThat(user.getImageUrl()).isEqualTo("http://example.com/image.jpg");
        Assertions.assertThat(user.getEmail()).isEqualTo("testuser@example.com");
        Assertions.assertThat(user.getProvider()).isEqualTo(OauthProvider.KAKAO);
    }
}