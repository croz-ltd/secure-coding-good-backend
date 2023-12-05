package net.croz.owasp.goodexample.service.impl;

import net.croz.owasp.goodexample.entity.AuthUser;
import net.croz.owasp.goodexample.entity.UserType;
import net.croz.owasp.goodexample.service.ProductSecurityService;
import org.springframework.stereotype.Service;

@Service
public class ProductSecurityServiceImpl implements ProductSecurityService {

    @Override
    public boolean canCreateProduct(AuthUser authUser) {
        return authUser.getUserType() == UserType.SELLER;
    }

    @Override
    public boolean canFindOne(AuthUser authUser) {
        return authUser.getUserType() == UserType.SELLER || authUser.getUserType() == UserType.BUYER;
    }

    @Override
    public boolean canFindAll(AuthUser authUser) {
        return authUser.getUserType() == UserType.SELLER || authUser.getUserType() == UserType.BUYER;
    }

    @Override
    public boolean canPlaceOrder(AuthUser authUser) {
        return authUser.getUserType() == UserType.BUYER;
    }

    @Override
    public boolean canCreateComment(AuthUser authUser) {
        return authUser.getUserType() == UserType.BUYER;
    }

    @Override
    public boolean canGetFile(AuthUser authUser) {
        return authUser.getUserType() == UserType.SELLER || authUser.getUserType() == UserType.BUYER;
    }

}
