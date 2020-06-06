package com.desarrolloweb.spring.app.Services;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service("AuditorAwareImpl")
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {

        return Optional
        .ofNullable(SecurityContextHolder.getContext())
        .map(SecurityContext::getAuthentication)
        .filter(Authentication::isAuthenticated)
        .map(Authentication::getPrincipal)
        .map(UserDetails.class::cast)
        .map(UserDetails::getUsername);
        
    }    
}


