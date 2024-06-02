package com.sky.service;

import com.sky.dto.*;
import com.sky.result.PageResult;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderStatisticsVO;
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
     * @return order submit vo 提交订单VO
     */
    OrderSubmitVO submit(OrdersSubmitDTO ordersSubmitDTO);

    /**
     * 订单支付
     *
     * @param ordersPaymentDTO 订单支付DTO
     * @return order payment vo 订单支付VO
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
     * @return 订单VO order detail
     */
    OrderVO getOrderDetail(Long id);

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

    /**
     * 有条件的搜索订单
     *
     * @param ordersPageQueryDTO 分页查询订单DTO
     * @return the result 分页查询结果
     */
    PageResult conditionSearch(OrdersPageQueryDTO ordersPageQueryDTO);

    /**
     * 各个状态的订单数量统计
     *
     * @return order statistics vo 统计订单状态VO
     */
    OrderStatisticsVO statistics();

    /**
     * 接单
     *
     * @param ordersConfirmDTO 接单DTO
     */
    void confirm(OrdersConfirmDTO ordersConfirmDTO);

    /**
     * 拒单
     *
     * @param ordersRejectionDTO 拒单DTO
     * @throws Exception the exception
     */
    void rejection(OrdersRejectionDTO ordersRejectionDTO) throws Exception;

    /**
     * 商家取消订单
     *
     * @param ordersCancelDTO 取消订单DTO
     * @throws Exception the exception
     */
    void cancel(OrdersCancelDTO ordersCancelDTO) throws Exception;

    /**
     * 派送订单
     *
     * @param id 订单ID
     */
    void delivery(Long id);

    /**
     * 完成订单
     *
     * @param id 订单ID
     */
    void complete(Long id);

    /**
     * 用户催单
     *
     * @param id 订单ID
     */
    void reminder(Long id);
}
