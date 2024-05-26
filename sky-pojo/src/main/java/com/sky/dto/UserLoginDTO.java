package com.sky.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * C端微信用户登录数据传输对象
 */
@Data
public class UserLoginDTO implements Serializable {

    private String code;

}
