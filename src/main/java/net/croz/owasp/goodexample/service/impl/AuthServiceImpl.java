package net.croz.owasp.goodexample.service.impl;

import net.croz.owasp.goodexample.entity.AuthUser;
import net.croz.owasp.goodexample.exception.AuthInvalidUsernameException;
import net.croz.owasp.goodexample.exception.AuthUnAuthorizedException;
import net.croz.owasp.goodexample.repository.AuthUserRepository;
import net.croz.owasp.goodexample.service.AuthService;
import net.croz.owasp.goodexample.service.command.ResetPasswordCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;

     @Autowired
    public AuthServiceImpl(AuthUserRepository authUserRepository, PasswordEncoder passwordEncoder) {
        this.authUserRepository = authUserRepository;
         this.passwordEncoder = passwordEncoder;
     }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return authUserRepository.loadUserByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException(""));
    }

    @Override
    public void resetPassword(ResetPasswordCommand resetPasswordCommand) {
        final AuthUser existingUser = authUserRepository.loadUserByUsername(resetPasswordCommand.getUsername())
            .orElseThrow(() -> new AuthInvalidUsernameException(resetPasswordCommand.getUsername()));

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
        authUserRepository.updateAuthPassword(existingUser);
    }

}
