package com.sky.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 购物车DTO
 */
@Data
public class ShoppingCartDTO implements Serializable {
    //菜品ID
    private Long dishId;
    //套餐ID
    private Long setmealId;
    //菜品口味
    private String dishFlavor;

}
