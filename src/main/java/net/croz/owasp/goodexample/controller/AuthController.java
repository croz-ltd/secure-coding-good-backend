package net.croz.owasp.goodexample.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.croz.owasp.goodexample.controller.response.UserResponse;
import net.croz.owasp.goodexample.entity.AuthUser;
import net.croz.owasp.goodexample.mapper.CreateMapper;
import net.croz.owasp.goodexample.service.AuthService;
import net.croz.owasp.goodexample.service.command.LoginCommand;
import net.croz.owasp.goodexample.service.command.ResetPasswordCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    private final AuthenticationManager authenticationManager;

    private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

    private final CreateMapper<AuthUser, UserResponse> authUserUserResponseCreateMapper;

    @Autowired
    public AuthController(AuthService authService, AuthenticationManager authenticationManager,
        CreateMapper<AuthUser, UserResponse> authUserUserResponseCreateMapper) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.authUserUserResponseCreateMapper = authUserUserResponseCreateMapper;
    }

    @GetMapping("/current-user")
    public UserResponse getCurrentUser(@AuthenticationPrincipal AuthUser authUser) {
        return authUserUserResponseCreateMapper.map(authUser);
    }

    @PostMapping("/login")
    public void authenticateUser(
        @RequestBody LoginCommand loginCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) {
        final UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken.unauthenticated(
            loginCommand.getUsername(), loginCommand.getPassword());
        final Authentication authentication = authenticationManager.authenticate(token);
        final SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
        securityContextRepository.saveContext(context, request, response);
    }

    @PostMapping("/password-reset")
    public void resetPassword(@RequestBody ResetPasswordCommand resetPasswordCommand) {
        authService.resetPassword(resetPasswordCommand);
    }

    @GetMapping("/csrf")
    public CsrfToken getCsrfToken(CsrfToken csrfToken) {
        return csrfToken;
    }

}
