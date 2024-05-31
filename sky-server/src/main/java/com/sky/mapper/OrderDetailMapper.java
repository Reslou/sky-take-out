package com.sky.mapper;

import com.sky.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 订单明细持久层
 */
@Mapper
public interface OrderDetailMapper {
    /**
     * 批量插入订单明细表
     *
     * @param orderDetailList 订单明细集合
     */
    void insertBatch(List<OrderDetail> orderDetailList);

    @Select("select * from order_detail where order_id = #{userId}")
    List<OrderDetail> getByOrderId(Long userId);
}
