package com.sky.service;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;

import java.util.List;

/**
 * 购物车服务层接口
 */
public interface ShoppingCartService {
    /**
     * 添加购物车
     *
     * @param shoppingCartDTO 购物车DTO
     */
    void addShoppingCart(ShoppingCartDTO shoppingCartDTO);

    /**
     * 查询购物车
     *
     * @return 购物车list
     */
    List<ShoppingCart> list();

    /**
     * 清空购物车
     */
    void clean();

    /**
     * 删除购物车中一个商品
     *
     * @param shoppingCartDTO 购物车DTO
     */
    void subShoppingCart(ShoppingCartDTO shoppingCartDTO);
}
