package com.sky.service.impl;

import com.sky.mapper.OrderMapper;
import com.sky.mapper.UserMapper;
import com.sky.service.ReportService;
import com.sky.vo.TurnoverReportVO;
import com.sky.vo.UserReportVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 统计服务层
 */
@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * 统计指定时间区间的营业额数据
     *
     * @param begin the begin 开始时间
     * @param end   the end 结束时间
     * @return TurnoverReportVO 营业额统计数据
     */
    public TurnoverReportVO getTurnoverStatistics(LocalDate begin, LocalDate end) {
        //计算统计时间
        List<LocalDate> dateList = getLocalDates(begin, end);

        //统计营业额
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

    /**
     * 统计指定时间区间的用户数据
     *
     * @param begin the begin 开始时间
     * @param end   the end 结束时间
     * @return UserReportVO 用户统计数据
     */
    public UserReportVO getUserStatistics(LocalDate begin, LocalDate end) {
        //计算统计时间
        List<LocalDate> localDateList = getLocalDates(begin, end);

        //统计新增用户,总用户
        List<Integer> totalUserList = new ArrayList<>();
        List<Integer> newUserList = new ArrayList<>();
        for (LocalDate date : localDateList) {
            Map<String, LocalDateTime> map = new HashMap<>();
            LocalDateTime beginTime = LocalDateTime.of(date, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(date, LocalTime.MAX);
            map.put("end", endTime);
            Integer totalUser = userMapper.countByMap(map);
            totalUserList.add(totalUser);
            map.put("begin", beginTime);
            Integer newUser = userMapper.countByMap(map);
            newUserList.add(newUser);
        }

        //封装用户统计VO并返回
        return UserReportVO
                .builder()
                .dateList(StringUtils.join(localDateList, ","))
                .totalUserList(StringUtils.join(totalUserList, ","))
                .newUserList(StringUtils.join(newUserList, ","))
                .build();
    }

    //计算统计时间
    private static List<LocalDate> getLocalDates(LocalDate begin, LocalDate end) {
        List<LocalDate> dateList = new ArrayList<>();
        dateList.add(begin);
        while (!begin.isEqual(end)) {
            begin = begin.plusDays(1);
            dateList.add(begin);
        }
        return dateList;
    }
}
