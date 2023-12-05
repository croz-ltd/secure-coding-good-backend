package net.croz.owasp.goodexample.service;

import net.croz.owasp.goodexample.entity.AuthUser;

public interface OrderSecurityService {

    boolean canFindAll(AuthUser authUser);

}
