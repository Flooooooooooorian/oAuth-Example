package de.neuefische.backend;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GithubAccessTokenDto(
        @JsonProperty("access_token")
        String accessToken
) {

}
