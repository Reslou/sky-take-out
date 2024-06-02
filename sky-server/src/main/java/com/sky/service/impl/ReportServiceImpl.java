package com.sky.service.impl;

import com.sky.mapper.OrderMapper;
import com.sky.service.ReportService;
import com.sky.vo.TurnoverReportVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 统计服务层
 */
@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private OrderMapper orderMapper;

    /**
     * 营业额统计
     *
     * @param begin the begin 开始时间
     * @param end   the end 结束时间
     * @return the result 统计订单VO
     */
    public TurnoverReportVO getTurnoverStatistics(LocalDate begin, LocalDate end) {
        //计算统计时间
        List<LocalDate> dateList = new ArrayList<>();
        dateList.add(begin);
        while (!begin.isEqual(end)) {
            begin = begin.plusDays(1);
            dateList.add(begin);
        }

        //计算营业额
        List<Double> turnoverList = new ArrayList<>();
        for (LocalDate localDate : dateList) {
            LocalDateTime beginTime = LocalDateTime.of(localDate, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(localDate, LocalTime.MAX);
            Double turnover = orderMapper.sumByTime(beginTime, endTime);
            turnover = turnover == null ? 0.0 : turnover;
            turnoverList.add(turnover);
        }

        //封装并返回统计订单VO
        return TurnoverReportVO
                .builder()
                .dateList(StringUtils.join(dateList, ","))
                .turnoverList(StringUtils.join(turnoverList, ","))
                .build();
    }
}
