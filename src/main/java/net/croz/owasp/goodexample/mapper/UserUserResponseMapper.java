package net.croz.owasp.goodexample.mapper;

import net.croz.owasp.goodexample.controller.response.UserResponse;
import net.croz.owasp.goodexample.entity.AuthUser;
import org.springframework.stereotype.Component;

@Component
public class UserUserResponseMapper implements CreateMapper<AuthUser, UserResponse> {

    @Override
    public UserResponse map(AuthUser authUser) {
        final UserResponse userResponse = new UserResponse();

        userResponse.setId(authUser.getId());
        userResponse.setUsername(authUser.getUsername());

        return userResponse;
    }

}
