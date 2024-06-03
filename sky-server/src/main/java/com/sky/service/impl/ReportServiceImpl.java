package com.sky.service.impl;

import com.sky.dto.GoodsSalesDTO;
import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import com.sky.mapper.UserMapper;
import com.sky.service.ReportService;
import com.sky.service.WorkspaceService;
import com.sky.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 统计服务层
 */
@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private WorkspaceService workspaceService;

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
            Map map = new HashMap();
            map.put("begin", beginTime);
            map.put("end", endTime);
            Double turnover = orderMapper.sumByMap(map);
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

    /**
     * 统计指定时间区间的订单数据
     *
     * @param begin the begin 开始时间
     * @param end   the end 结束时间
     * @return OrderReportVO 订单统计数据
     */
    public OrderReportVO getOrderStatistics(LocalDate begin, LocalDate end) {
        //计算统计时间
        List<LocalDate> DateList = getLocalDates(begin, end);

        //统计订单,有效订单
        List<Integer> orderCountList = new ArrayList<>();
        List<Integer> validOrderCountList = new ArrayList<>();
        for (LocalDate date : DateList) {
            LocalDateTime beginTime = LocalDateTime.of(date, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(date, LocalTime.MAX);
            Map map = new HashMap();
            map.put("begin", beginTime);
            map.put("end", endTime);
            Integer orderCount = orderMapper.countByMap(map);
            orderCountList.add(orderCount);

            map.put("status", Orders.COMPLETED);
            Integer validOrderCount = orderMapper.countByMap(map);
            validOrderCountList.add(validOrderCount);
        }

        //计算订单总数量
        Integer totalOrderCount = orderCountList.stream().reduce(Integer::sum).get();

        //计算有效订单总数量
        Integer validOrderCount = validOrderCountList.stream().reduce(Integer::sum).get();

        //计算订单完成率
        Double orderCompletionRate = 0.0;
        if (totalOrderCount != 0) {
            orderCompletionRate = validOrderCount.doubleValue() / totalOrderCount;
        }

        //封装订单统计VO并返回
        return OrderReportVO
                .builder()
                .dateList(StringUtils.join(DateList, ","))
                .orderCountList(StringUtils.join(orderCountList, ","))
                .validOrderCountList(StringUtils.join(validOrderCountList, ","))
                .totalOrderCount(totalOrderCount)
                .validOrderCount(validOrderCount)
                .orderCompletionRate(orderCompletionRate)
                .build();
    }

    /**
     * 统计指定时间区间的商品销量前十
     *
     * @param begin the begin 开始时间
     * @param end   the end 结束时间
     * @return SalesTop10ReportVO 销量统计数据
     */
    public SalesTop10ReportVO getTop10(LocalDate begin, LocalDate end) {
        LocalDateTime beginTime = LocalDateTime.of(begin, LocalTime.MIN);
        LocalDateTime endTime = LocalDateTime.of(end, LocalTime.MAX);
        List<GoodsSalesDTO> goodsSalesList = orderMapper.getSalesTop10(beginTime, endTime);
        List<String> nameList = goodsSalesList.stream().map(GoodsSalesDTO::getName).collect(Collectors.toList());
        List<Integer> numberList = goodsSalesList.stream().map(GoodsSalesDTO::getNumber).collect(Collectors.toList());
        return SalesTop10ReportVO
                .builder()
                .nameList(StringUtils.join(nameList, ","))
                .numberList(StringUtils.join(numberList, ","))
                .build();
    }

    /**
     * 导出运营数据
     *
     * @param response 浏览器对象
     */
    public void exportBusinessData(HttpServletResponse response) {
        //查询前30天的运营数据
        LocalDate beginDate = LocalDate.now().minusDays(30);
        LocalDate endDate = LocalDate.now().minusDays(1);
        LocalDateTime begin = LocalDateTime.of(beginDate, LocalTime.MIN);
        LocalDateTime end = LocalDateTime.of(endDate, LocalTime.MAX);
        BusinessDataVO businessData = workspaceService.getBusinessData(begin, end);

        //通过POI将数据写入到Excel文件中
        InputStream in = this.getClass().getClassLoader()
                .getResourceAsStream("template/运营数据报表模板.xlsx");
        try {
            //填充时间
            XSSFWorkbook excel = new XSSFWorkbook(in);
            XSSFSheet sheet = excel.getSheet("Sheet1");
            XSSFRow row = sheet.getRow(1);
            row.getCell(1).setCellValue("时间：" + beginDate + "至" + endDate);//2,2

            //填充概览数据
            row = sheet.getRow(3);
            row.getCell(2).setCellValue(businessData.getTurnover());//4,3
            row.getCell(4).setCellValue(businessData.getOrderCompletionRate());//4,5
            row.getCell(6).setCellValue(businessData.getNewUsers());//4,7
            row = sheet.getRow(4);
            row.getCell(2).setCellValue(businessData.getValidOrderCount());//5,3
            row.getCell(4).setCellValue(businessData.getUnitPrice());//5,5

            for (int i = 0; i < 30; i++) {
                //查询某一天的运营数据
                LocalDate days = beginDate.plusDays(i);
                begin = LocalDateTime.of(days, LocalTime.MIN);
                end = LocalDateTime.of(days, LocalTime.MAX);
                businessData = workspaceService.getBusinessData(begin, end);

                //填充明细数据
                row = sheet.getRow(i + 7);
                row.getCell(1).setCellValue(days.toString());//8,2
                row.getCell(2).setCellValue(businessData.getTurnover());//8,3
                row.getCell(3).setCellValue(businessData.getValidOrderCount());//8,4
                row.getCell(4).setCellValue(businessData.getOrderCompletionRate());//8,5
                row.getCell(5).setCellValue(businessData.getUnitPrice());//8,6
                row.getCell(6).setCellValue(businessData.getNewUsers());//8,7
            }

            //通过输出流将Excel下载到浏览器
            ServletOutputStream out = response.getOutputStream();
            excel.write(out);

            //关闭资源
            in.close();
            out.close();
            excel.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
