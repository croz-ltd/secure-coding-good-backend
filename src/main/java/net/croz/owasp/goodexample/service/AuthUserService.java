package net.croz.owasp.goodexample.service;

import net.croz.owasp.goodexample.entity.AuthUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthUserService extends UserDetailsService {

    AuthUser loadUserByUsername(String username);

    AuthUser getUserByType(AuthUser authUser);

    void updateAuthPassword(AuthUser authUser);

}
