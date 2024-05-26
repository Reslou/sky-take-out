package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;

import java.util.List;

/**
 * 套餐服务接口
 */
public interface SetmealService {
    /**
     * 新增套餐，同时需要保存套餐和菜品的关联关系
     *
     * @param setmealDTO 套餐dto
     */
    void saveWithDish(SetmealDTO setmealDTO);

    /**
     * 条件查询
     *
     * @param setmeal 套餐
     * @return list套餐
     */
    List<Setmeal> list(Setmeal setmeal);

    /**
     * 根据id查询菜品选项
     *
     * @param id 套餐id
     * @return 菜品列表VO
     */
    List<DishItemVO> getDishItemById(Long id);

    /**
     * 分页查询
     *
     * @param setmealPageQueryDTO 套餐分页查询DTO
     * @return 分页查询结果
     */
    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * 批量删除套餐
     *
     * @param ids 套餐ID
     */
    void deleteBatch(List<Long> ids);

    /**
     * 根据id查询套餐和关联的菜品数据
     *
     * @param id
     * @return
     */
    SetmealVO getByIdWithDish(Long id);

    /**
     * 修改套餐
     *
     * @param setmealDTO
     */
    void update(SetmealDTO setmealDTO);

    /**
     * 套餐起售、停售
     *
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);
}
