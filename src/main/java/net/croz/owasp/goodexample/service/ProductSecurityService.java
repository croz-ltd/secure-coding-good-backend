package net.croz.owasp.goodexample.service;

import net.croz.owasp.goodexample.entity.AuthUser;
import net.croz.owasp.goodexample.entity.UserType;

public interface ProductSecurityService {

    boolean canCreateProduct(AuthUser authUser);

    boolean canFindOne(AuthUser authUser);

    boolean canFindAll(AuthUser authUser);

    boolean canPlaceOrder(AuthUser authUser);

    boolean canCreateComment(AuthUser authUser);

    boolean canGetFile(AuthUser authUser);

}
