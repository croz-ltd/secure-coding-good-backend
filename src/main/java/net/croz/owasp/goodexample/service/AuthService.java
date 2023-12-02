package net.croz.owasp.goodexample.service;

import net.croz.owasp.goodexample.entity.AuthUser;
import net.croz.owasp.goodexample.service.command.ResetPasswordCommand;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {

    void resetPassword(ResetPasswordCommand resetPasswordCommand);

    AuthUser getUserByType(AuthUser authUser);
}
