package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;

import java.util.List;

/**
 * 菜品业务逻辑层
 */
public interface DishService {
    /**
     * 新增菜品和对应的口味
     *
     * @param dishDTO the dish dto
     */
    public void saveWithFlavor(DishDTO dishDTO);

    /**
     * 菜品分页查询
     *
     * @param dishPageQueryDTO the dish page query dto
     * @return the page result
     */
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 批量删除菜品
     *
     * @param ids the ids
     */
    void deleteBatch(List<Long> ids);
}
