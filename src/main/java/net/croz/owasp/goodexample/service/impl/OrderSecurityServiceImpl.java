package net.croz.owasp.goodexample.service.impl;

import net.croz.owasp.goodexample.entity.AuthUser;
import net.croz.owasp.goodexample.entity.UserType;
import net.croz.owasp.goodexample.service.OrderSecurityService;
import org.springframework.stereotype.Service;

@Service
public class OrderSecurityServiceImpl implements OrderSecurityService {

    @Override
    public boolean canFindAll(AuthUser authUser) {
        return authUser.getUserType() == UserType.ADMIN;
    }

}
