package com.sky.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 取消订单DTO
 */
@Data
public class OrdersCancelDTO implements Serializable {

    private Long id;
    //订单取消原因
    private String cancelReason;

}
