package net.croz.owasp.goodexample.service.impl;

import net.croz.owasp.goodexample.entity.AuthUser;
import net.croz.owasp.goodexample.entity.UserType;
import net.croz.owasp.goodexample.repository.AuthUserRepository;
import net.croz.owasp.goodexample.repository.UserBuyerRepository;
import net.croz.owasp.goodexample.repository.UserSellerRepository;
import net.croz.owasp.goodexample.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthUserServiceImpl implements AuthUserService {

    private final AuthUserRepository authUserRepository;

    private final UserSellerRepository userSellerRepository;

    private final UserBuyerRepository userBuyerRepository;

    @Autowired
    public AuthUserServiceImpl(AuthUserRepository authUserRepository, UserSellerRepository userSellerRepository,
        UserBuyerRepository userBuyerRepository) {
        this.authUserRepository = authUserRepository;
        this.userSellerRepository = userSellerRepository;
        this.userBuyerRepository = userBuyerRepository;
    }

    @Override
    public AuthUser getUserByType(AuthUser authUser) {
        if (authUser.getUserType() == UserType.BUYER) {
            return userBuyerRepository.findById(authUser.getId()).get();
        }

        return userSellerRepository.findById(authUser.getId()).get();
    }

    @Override
    public void updateAuthPassword(AuthUser authUser) {
        authUserRepository.updateAuthPassword(authUser);
    }

    @Override
    public AuthUser loadUserByUsername(String username) throws UsernameNotFoundException {
        return authUserRepository.loadUserByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException(""));
    }

}
