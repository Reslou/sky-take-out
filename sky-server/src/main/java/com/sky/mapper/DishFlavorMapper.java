package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 菜品口味数据访问层
 */
@Mapper
public interface DishFlavorMapper {


    /**
     * 新增菜品口味
     *
     * @param flavors the flavors
     */
    void insert(List<DishFlavor> flavors);

    /**
     * 根据菜品id删除菜品口味
     *
     * @param dishId the dish id
     */
    @Delete("delete from dish_flavor where dish_id = #{dishId}")
    void deleteByDishId(Long dishId);


    /**
     * 根据菜品id批量删除菜品口味
     *
     * @param dishIds the dish ids
     */
    void deleteByDishIds(List<Long> dishIds);

    /**
     * 根据菜品id查询
     *
     * @param dishId 菜品id
     * @return 菜品口味集合
     */
    @Select("select * from dish_flavor where dish_id = #{dishId}")
    List<DishFlavor> getByDishId(Long dishId);
}
