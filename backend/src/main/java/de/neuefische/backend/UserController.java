package de.neuefische.backend;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/oauth")
public class UserController {

    private final GithubService githubService;
    private final UserService userService;

    public UserController(GithubService githubService, UserService userService) {
        this.githubService = githubService;
        this.userService = userService;
    }

    @GetMapping("me")
    public String helloMe() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @PostMapping("github")
    public String loginWithGithub(@RequestBody String code) {
        String accessToken = githubService.loginWithGithub(code);

        return userService.loginWithGithub("TestMe", accessToken).getUsername();
    }
}
