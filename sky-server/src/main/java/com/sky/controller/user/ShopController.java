package com.sky.controller.user;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 店铺控制层
 */
@RestController("userShopController")
@RequestMapping("/user/shop")
@Slf4j
@Api(tags = "店铺相关接口")
public class ShopController {
    private final String KEY = "SHOP_STATUS";
    @Autowired
    private RedisTemplate redisTemplate;

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
