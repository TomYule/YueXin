package com.yuexin.service;

import com.yuexin.common.service.BaseService;
import com.yuexin.domain.User;

import java.util.Optional;

/**
 * @author kricss
 */
public interface UserService extends BaseService<User>{

    Optional<User> findOneWithAuthoritiesByEmailIgnoreCase(String email);

    Optional<User> findOneWithAuthoritiesByLogin(String longin);

}
