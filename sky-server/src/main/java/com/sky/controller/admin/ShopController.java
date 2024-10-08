package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * 店铺控制层
 */
@RestController("adminShopController")
@RequestMapping("/admin/shop")
@Slf4j
@Api(tags = "店铺相关接口")
public class ShopController {
    private final String KEY = "SHOP_STATUS";
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 设置营业状态
     *
     * @param status 营业状态
     * @return the result
     */
    @PutMapping("/{status}")
    @ApiOperation("设置营业状态")
    public Result update(@PathVariable Integer status) {
        log.info("设置营业状态：{}", status == 1 ? "营业中" : "打烊中");
        redisTemplate.opsForValue().set(KEY, status);
        return Result.success();
    }

    /**
     * 获取营业状态
     *
     * @return 营业状态
     */
    @GetMapping("/status")
    @ApiOperation("获取营业状态")
    public Result<Integer> get() {
        Integer status = (Integer) redisTemplate.opsForValue().get(KEY);
        log.info("获取营业状态：{}", status == 1 ? "营业中" : "打烊中");
        return Result.success(status);
    }
}
