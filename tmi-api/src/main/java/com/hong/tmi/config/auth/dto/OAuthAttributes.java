package com.hong.tmi.config.auth.dto;

import com.hong.tmi.domain.Member;
import com.hong.tmi.domain.Member.MemberRole;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

/**
 * OAuth2User의 속성을 담는 DTO
 */
@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }


    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        return ofGoogle(userNameAttributeName,attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName).build();
    }

    public Member toEntity(){
        return Member.builder()
                .memberName(name)
                .email(email)
                .picture(picture)
                .memberRole(MemberRole.GUEST)
                .build();
    }
}
