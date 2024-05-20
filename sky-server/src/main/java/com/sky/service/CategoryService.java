package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;

import java.util.List;

/**
 * The interface Category service.
 */
public interface CategoryService {

    /**
     * 新增分类
     *
     * @param categoryDTO the category dto
     */
    void save(CategoryDTO categoryDTO);

    /**
     * 分页查询
     *
     * @param categoryPageQueryDTO the category page query dto
     * @return page result
     */
    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 根据id删除分类
     *
     * @param id the id
     */
    void deleteById(Long id);

    /**
     * 修改分类
     *
     * @param categoryDTO the category dto
     */
    void update(CategoryDTO categoryDTO);

    /**
     * 启用、禁用分类
     *
     * @param status the status
     * @param id     the id
     */
    void startOrStop(Integer status, Long id);

    /**
     * 根据类型查询分类
     *
     * @param type the type
     * @return list
     */
    List<Category> list(Integer type);
}
