package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 菜品套餐数据访问层
 */
@Mapper
public interface SetmealDishMapper {

    /**
     * 根据菜品id查询
     *
     * @param ids the ids
     * @return the list
     */
    public List<Long> selectByDishId(List<Long> ids);
}
