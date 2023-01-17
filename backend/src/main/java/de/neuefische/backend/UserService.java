package de.neuefische.backend;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MongoUser user = userRepo.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(username)
                );
        return new User(user.username(), user.password(), List.of());
    }

    public UserDetails loginWithGithub(String username, String accessToken) {
        User user = new User(username, accessToken, List.of());

        MongoUser mongoUser = new MongoUser(null, username, accessToken);
        userRepo.save(mongoUser);

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, accessToken, List.of()));
        return user;
    }
}
