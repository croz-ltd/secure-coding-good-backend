package net.croz.owasp.goodexample.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.croz.owasp.goodexample.service.command.LoginCommand;
import net.croz.owasp.goodexample.service.command.ResetPasswordCommand;

public interface AuthService {

    void resetPassword(ResetPasswordCommand resetPasswordCommand);

    void login(LoginCommand loginCommand, HttpServletRequest request, HttpServletResponse response);

    void loginAttemptFailed(String username);

}
