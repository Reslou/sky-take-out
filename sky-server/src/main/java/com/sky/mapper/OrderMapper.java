package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 订单持久层
 */
@Mapper
public interface OrderMapper {
    /**
     * 插入订单表
     *
     * @param orders 订单
     */
    void insert(Orders orders);

    /**
     * 用订单号查询订单表
     *
     * @param orderNumber 订单号
     * @return 订单 by number
     */
    @Select("select * from orders where number = #{orderNumber}")
    Orders getByNumber(String orderNumber);

    /**
     * 更新订单表
     *
     * @param orders 订单
     */
    void update(Orders orders);

    /**
     * 分页查询订单表
     *
     * @param ordersPageQueryDTO 分页查询订单DTO
     * @return the page 订单
     */
    Page<Orders> pageQuery(OrdersPageQueryDTO ordersPageQueryDTO);

    /**
     * 用订单ID查询订单表
     *
     * @param id 订单ID
     * @return 订单 by id
     */
    @Select("select * from orders where id = #{id}")
    Orders getById(Long id);


    /**
     * 根据状态统计订单数量
     *
     * @param status 订单状态
     */
    @Select("select count(id) from orders where status = #{status}")
    Integer countStatus(Integer status);
}
