package com.sky.task;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;

//@Component
@Slf4j
public class MyTask {
    //@Scheduled(cron = "0/5 * * * * ?")
    public void task(){
        log.info("定时任务开始执行:{}",new Date());
    }
}
