package com.kosign.onshopapi.service.auth;

import com.kosign.onshopapi.domain.user.UserRepository;
import com.kosign.onshopapi.payload.auth.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component("userAuthService")
@RequiredArgsConstructor
public class UserAuthServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       var user = userRepository.findByUserName(username).orElseThrow(
               ()-> new UsernameNotFoundException("User not found"));
        return new SecurityUser(user);
    }
}
