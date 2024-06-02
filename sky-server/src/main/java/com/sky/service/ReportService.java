package com.sky.service;

import com.sky.vo.TurnoverReportVO;

import java.time.LocalDate;

/**
 * 统计服务层
 */
public interface ReportService {
    /**
     * 营业额统计
     *
     * @param begin the begin 开始时间
     * @param end   the end 结束时间
     * @return the result 统计订单VO
     */
    TurnoverReportVO getTurnoverStatistics(LocalDate begin, LocalDate end);
}
