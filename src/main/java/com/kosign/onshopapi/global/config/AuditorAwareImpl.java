package com.kosign.onshopapi.global.config;

import com.kosign.onshopapi.component.security.APIKeyUser;
import com.kosign.onshopapi.helper.AuthHelper;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<Long> {
    @Override
    public Optional<Long> getCurrentAuditor() {
        var auth = SecurityContextHolder.getContext().getAuthentication();

        if(
                auth == null
                || !auth.isAuthenticated()
                || auth instanceof APIKeyUser
                || auth instanceof AnonymousAuthenticationToken // not required login
        ){
            return Optional.empty();
        }

        return Optional.ofNullable(AuthHelper.getUserId());
    }
}
