package com.sky.service;

import com.sky.dto.ShoppingCartDTO;

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
}
