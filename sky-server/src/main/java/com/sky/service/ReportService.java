package com.sky.service;

import com.sky.vo.OrderReportVO;
import com.sky.vo.SalesTop10ReportVO;
import com.sky.vo.TurnoverReportVO;
import com.sky.vo.UserReportVO;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

/**
 * 统计服务层
 */
public interface ReportService {
    /**
     * 统计指定时间区间的营业额数据
     *
     * @param begin the begin 开始时间
     * @param end   the end 结束时间
     * @return turnover statistics 营业额统计数据
     */
    TurnoverReportVO getTurnoverStatistics(LocalDate begin, LocalDate end);

    /**
     * 统计指定时间区间的用户数据
     *
     * @param begin the begin 开始时间
     * @param end   the end 结束时间
     * @return user statistics 用户统计数据
     */
    UserReportVO getUserStatistics(LocalDate begin, LocalDate end);

    /**
     * 统计指定时间区间的订单数据
     *
     * @param begin the begin 开始时间
     * @param end   the end 结束时间
     * @return OrderReportVO 订单统计数据
     */
    OrderReportVO getOrderStatistics(LocalDate begin, LocalDate end);

    /**
     * 统计指定时间区间的商品销量前十
     *
     * @param begin the begin 开始时间
     * @param end   the end 结束时间
     * @return SalesTop10ReportVO 销量统计数据
     */
    SalesTop10ReportVO getTop10(LocalDate begin, LocalDate end);

    /**
     * 导出运营数据
     *
     * @param response 浏览器对象
     */
    void exportBusinessData(HttpServletResponse response);
}
