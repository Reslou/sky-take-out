package com.sky.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 查询历史订单DTO
 */
@Data
public class OrdersHistoryDTO implements Serializable {

    private int page;

    private int pageSize;

    private Integer status;

}
