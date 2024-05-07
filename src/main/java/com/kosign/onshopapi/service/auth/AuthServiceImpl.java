package com.kosign.onshopapi.service.auth;

import com.kosign.onshopapi.common.api.StatusCode;
import com.kosign.onshopapi.domain.user.User;
import com.kosign.onshopapi.domain.user.UserRepository;
import com.kosign.onshopapi.enums.AuthProvider;
import com.kosign.onshopapi.enums.StatusActive;
import com.kosign.onshopapi.exception.BusinessException;
import com.kosign.onshopapi.payload.auth.AuthRequest;
import com.kosign.onshopapi.utils.PasswordUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void signup(AuthRequest payload) throws Throwable{

        var userExist = userRepository.findByUserName(payload.username()).orElse(new User());
        if (StringUtils.isNotEmpty(userExist.getUsername())) {
            throw new BusinessException(StatusCode.USER_NOT_FOUND);
        }

        String rawPwd;
        try {
            rawPwd = passwordEncoder.encode(PasswordUtils.decrypt(payload.password()));
        } catch (Exception e) {
            throw new BusinessException(StatusCode.PASSWORD_MUST_BE_ENCRYPTED);
        }

        var user = User.builder()
                .username(payload.username())
                .password(rawPwd)
                .address(payload.address())
                .email(payload.email())
                .phoneNumber(payload.phoneNumber())
                .role(payload.role())
                .status(StatusActive.ACTIVATE.getValue())
                .build();

        userRepository.save(user);
    }
}
