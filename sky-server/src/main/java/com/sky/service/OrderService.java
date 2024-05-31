package com.sky.service;

import com.sky.dto.OrdersHistoryDTO;
import com.sky.dto.OrdersPaymentDTO;
import com.sky.dto.OrdersSubmitDTO;
import com.sky.result.PageResult;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;

/**
 * 订单业务逻辑层
 */
public interface OrderService {
    /**
     * 提交订单
     *
     * @param ordersSubmitDTO 提交订单DTO
     * @return 提交订单VO order submit vo
     */
    OrderSubmitVO submit(OrdersSubmitDTO ordersSubmitDTO);

    /**
     * 订单支付
     *
     * @param ordersPaymentDTO 订单支付DTO
     * @return 订单支付VO order payment vo
     * @throws Exception the exception
     */
    OrderPaymentVO payment(OrdersPaymentDTO ordersPaymentDTO) throws Exception;

    /**
     * 支付成功，修改订单状态
     *
     * @param outTradeNo 商户退款单号
     */
    void paySuccess(String outTradeNo);

    /**
     * 查询历史订单
     *
     * @param ordersHistoryDTO 查询历史订单DTO
     * @return the result 分页查询结果
     */
    PageResult historyOrders(OrdersHistoryDTO ordersHistoryDTO);

    /**
     * 查询订单详情
     *
     * @param id 订单ID
     * @return 订单VO
     */
    OrderVO orderDetail(Long id);

    /**
     * 取消订单
     *
     * @param id 订单ID
     */
    void cancel(Long id);

    /**
     * 再来一单
     *
     * @param id 订单ID
     */
    void repetition(Long id);
}
