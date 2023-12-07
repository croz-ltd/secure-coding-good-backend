package net.croz.owasp.goodexample.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.croz.owasp.goodexample.entity.AuthUser;
import net.croz.owasp.goodexample.exception.AuthUnAuthorizedException;
import net.croz.owasp.goodexample.exception.LoginBlockedException;
import net.croz.owasp.goodexample.repository.AuthUserRepository;
import net.croz.owasp.goodexample.service.AuthService;
import net.croz.owasp.goodexample.service.AuthUserService;
import net.croz.owasp.goodexample.service.command.LoginCommand;
import net.croz.owasp.goodexample.service.command.ResetPasswordCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthUserService authUserService;

    private final AuthUserRepository authUserRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

    @Autowired
    public AuthServiceImpl(AuthUserService authUserService,
        AuthUserRepository authUserRepository, PasswordEncoder passwordEncoder,
        AuthenticationManager authenticationManager
    ) {
        this.authUserService = authUserService;
        this.authUserRepository = authUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void resetPassword(ResetPasswordCommand resetPasswordCommand) {
        final AuthUser existingUser = authUserService.loadUserByUsername(resetPasswordCommand.getUsername());

        final boolean questionOne =
            Objects.equals(existingUser.getSecurityQuestionOne(), resetPasswordCommand.getQuestionOneAnswer());
        final boolean questionTwo =
            Objects.equals(existingUser.getSecurityQuestionTwo(), resetPasswordCommand.getQuestionTwoAnswer());
        final boolean questionThree =
            Objects.equals(existingUser.getSecurityQuestionThree(), resetPasswordCommand.getQuestionThreeAnswer());
        final boolean allQuestionsCorrect = questionOne && questionTwo && questionThree;

        if (!allQuestionsCorrect) {
            throw new AuthUnAuthorizedException();
        }

        final String newPasswordEncoded = passwordEncoder.encode(resetPasswordCommand.getPassword());
        existingUser.setPassword(newPasswordEncoded);
        authUserService.updateAuthPassword(existingUser);
    }

    @Override
    public void login(LoginCommand loginCommand, HttpServletRequest request, HttpServletResponse response) {
        final AuthUser authUser = authUserService.loadUserByUsername(loginCommand.getUsername());

        // OWASP[41]
        if (authUser.getLockedUntil() != null && authUser.getLockedUntil().isAfter(LocalDateTime.now())) {
            throw new LoginBlockedException();
        }
        final UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken.unauthenticated(
            loginCommand.getUsername(), loginCommand.getPassword());
        final Authentication authentication = authenticationManager.authenticate(token);
        final SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
        securityContextRepository.saveContext(context, request, response);
    }

    // OWASP[41]
    public void loginAttemptFailed(String username) {
        final AuthUser authUser = authUserService.loadUserByUsername(username);
        if (!Objects.equals(authUser.getFailedAttempts(), 5)) {
            authUser.setFailedAttempts(authUser.getFailedAttempts() == null ? 1 : authUser.getFailedAttempts() + 1);
            authUser.setLastAttempt(LocalDateTime.now());
            authUserRepository.updateLoginAttempts(authUser);
        } else {
            authUser.setFailedAttempts(0);
            authUser.setLockedUntil(LocalDateTime.now().plusMinutes(5));
            authUserRepository.updateLoginAttempts(authUser);
        }
    }

}
