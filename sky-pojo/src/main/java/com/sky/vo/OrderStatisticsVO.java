package com.sky.vo;

import lombok.Data;
import java.io.Serializable;

/**
 * 统计订单状态VO
 */
@Data
public class OrderStatisticsVO implements Serializable {
    //待接单数量
    private Integer toBeConfirmed;

    //待派送数量
    private Integer confirmed;

    //派送中数量
    private Integer deliveryInProgress;
}
