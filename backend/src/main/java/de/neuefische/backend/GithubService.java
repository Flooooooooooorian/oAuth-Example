package de.neuefische.backend;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GithubService {

    private String clientId = "01de35f3dd7e75b77b93";
    private String clientSecret = "f2f1b411bb737c0af60d899826b3851a013a8389";

    public String loginWithGithub(String code) {
        WebClient webClient = WebClient.create();

        GithubAccessTokenDto accessTokenDto = webClient.post()
                .uri("https://github.com/login/oauth/access_token?code=" + code + "&client_id=" + clientId + "&client_secret=" + clientSecret)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(GithubAccessTokenDto.class)
                .block()
                .getBody();

        return accessTokenDto.accessToken();
    }
}
