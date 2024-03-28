package com.astra.kuzushiji.utils;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;

import java.util.Optional;

@UtilityClass
public class UserUtils {
    public static Optional<String> getCurrentUserEmail(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if(authentication.getPrincipal() instanceof DefaultOidcUser principal){
            return Optional.ofNullable(principal.getAttributes().get("email"))
                    .filter(String.class::isInstance)
                    .map(String.class::cast);
        } else {
            return Optional.empty();
        }

    }

}
