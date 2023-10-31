package com.onlinestore.security;

import com.onlinestore.api.user.User;
import com.onlinestore.api.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User securityUser = userRepository.findByUsernameAndIsDeletedFalse(username)
                .orElseThrow(() -> {
                    log.error("Username has not been found..!");
                    return new UsernameNotFoundException("Username has not been found..!");
                });
        log.info("Security user = {}", securityUser.getUsername());
        log.info("Security user = {}", securityUser.getEmail());
        log.info("Security user = {}", securityUser.getPassword());

        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setUser(securityUser);
        log.info("Authorities = {} ", customUserDetails.getAuthorities());
        return customUserDetails;
    }
}
