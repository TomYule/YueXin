package com.yuexin.service;

import com.yuexin.common.service.BaseServiceImpl;
import com.yuexin.domain.User;
import org.nutz.dao.Cnd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    public static String USERS_BY_LOGIN_CACHE = "usersByLogin";

    public static String USERS_BY_EMAIL_CACHE = "usersByEmail";

    @Override
    public Optional<User> findOneWithAuthoritiesByEmailIgnoreCase(String email) {
        return Optional.ofNullable(this.dao().fetchLinks(this.fetch(Cnd.where("binary email","=",email)),"authorities"));
    }

    @Override
    public Optional<User> findOneWithAuthoritiesByLogin(String login) {
        return Optional.ofNullable(this.dao().fetchLinks(this.fetch(Cnd.where("login","=",login)),"authorities"));

    }
}
