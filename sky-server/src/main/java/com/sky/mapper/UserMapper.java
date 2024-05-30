package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 微信用户数据访问层
 */
@Mapper
public interface UserMapper {
    /**
     * 以openid查询微信用户表
     *
     * @param openid 微信用户唯一标识
     * @return user 微信用户
     */
    @Select("select * from user where openid = #{openid}")
    User selectByOpenid(String openid);

    /**
     * 将user保存到微信用户表
     *
     * @param user 微信用户
     */
    void insert(User user);

    /**
     * 用用户ID查询用户表
     *
     * @param userId 用户ID
     * @return 用户
     */
    @Select("select * from user where id = #{userId}")
    User getById(Long userId);
}
