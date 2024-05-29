package com.sky.service;

import com.sky.dto.OrdersSubmitDTO;
import com.sky.vo.OrderSubmitVO;

/**
 * 订单业务逻辑层
 */
public interface OrderService {
    /**
     * 提交订单
     *
     * @param ordersSubmitDTO 提交订单DTO
     * @return 提交订单VO
     */
    OrderSubmitVO submit(OrdersSubmitDTO ordersSubmitDTO);
}
