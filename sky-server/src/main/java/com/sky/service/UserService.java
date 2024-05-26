package com.sky.service;

import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;

/**
 * 微信用户业务逻辑层
 */
public interface UserService {

    /**
     * 微信用户登录
     *
     * @param userLoginDTO C端微信用户登录数据传输对象
     * @return user 微信用户
     */
    User login(UserLoginDTO userLoginDTO);
}
