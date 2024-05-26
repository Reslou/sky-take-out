package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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

    /**
     * 批量保存套餐和菜品的关联关系
     *
     * @param setmealDishes 套餐菜品关系
     */
    void insertBatch(List<SetmealDish> setmealDishes);

    /**
     * 根据套餐id删除套餐和菜品的关联关系
     *
     * @param setmealId 套餐id
     */
    @Delete("delete from setmeal_dish where setmeal_id = #{setmealId}")
    void deleteBySetmealId(Long setmealId);

    /**
     * 根据套餐id查询套餐和菜品的关联关系
     *
     * @param setmealId
     * @return
     */
    @Select("select * from setmeal_dish where setmeal_id = #{setmealId}")
    List<SetmealDish> getBySetmealId(Long setmealId);
}
