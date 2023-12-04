package net.croz.owasp.goodexample.listener;

import net.croz.owasp.goodexample.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    public final AuthService authService;

    @Autowired
    public AuthenticationFailureListener(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
        authService.loginAttemptFailed(event.getAuthentication().getName());
    }

}
