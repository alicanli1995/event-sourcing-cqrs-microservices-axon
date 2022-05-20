package com.oauth.server.oauthserver.services;

import com.oauth.server.oauthserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(value = "userService")
@Log4j2
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username);
        if(user.isEmpty())
            throw new UsernameNotFoundException("This user is not in database... ");
        var userDetails = user.get().getAccount();
        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(userDetails.getPassword())
                .authorities(userDetails.getRoles())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
