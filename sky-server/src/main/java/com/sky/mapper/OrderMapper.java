package com.sky.mapper;

import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

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
}
