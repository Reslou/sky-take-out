package com.sky.service;

import com.sky.dto.OrdersPaymentDTO;
import com.sky.dto.OrdersSubmitDTO;
import com.sky.vo.OrderPaymentVO;
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

    /**
     * 订单支付
     * @param ordersPaymentDTO 订单支付DTO
     * @return 订单支付VO
     */
    OrderPaymentVO payment(OrdersPaymentDTO ordersPaymentDTO) throws Exception;

    /**
     * 支付成功，修改订单状态
     * @param outTradeNo 商户退款单号
     */
    void paySuccess(String outTradeNo);
}
