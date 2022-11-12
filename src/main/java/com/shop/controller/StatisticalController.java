package com.shop.controller;

import com.shop.dto.ResponseMessage;
import com.shop.dto.StatisticalRevenue;
import com.shop.dto.Statistical_Top10Dto;
import com.shop.enumEntity.StatusMessage;
import com.shop.services.IOrderService;
import com.shop.services.IStatisticalService;
import com.shop.utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/statistical")
public class StatisticalController {

    @Autowired
    private IOrderService orderService;
    @Autowired
    private IStatisticalService iStatisticalService;

    //Statistics Revenue Admin
    @GetMapping("/revenue-admin")
    public ResponseEntity<ResponseMessage> revenueStatisticsByYear(@RequestParam("year") Integer year) {
        List<Object[]> list = this.orderService.revenueStatisticsByYear(year);
        return getStatisticalRevenue(list);
    }

    //Statistics Revenue Shipper
    @GetMapping("/revenue-shipper")
    public ResponseEntity<ResponseMessage> statisticsShipperOrder(
            @RequestParam("userId") Long userId,
            @RequestParam("year") Integer year) {
        List<Object[]> list = this.orderService.statisticsShipperOrder(userId, year);
        return getStatisticalRevenue(list);
    }

    //General Method Statistical Revenue
    private ResponseEntity<ResponseMessage> getStatisticalRevenue(List<Object[]> list) {
        ResponseEntity<ResponseMessage> message;
        List<StatisticalRevenue> listStatistical = new ArrayList<>();
        for (Object[] objects : list) {
            listStatistical.add(Convert.objectToClass(objects, StatisticalRevenue.class));
        }
        message = ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseMessage(StatusMessage.OK, "Revenue statistics by year", listStatistical));
        return message;
    }

    @GetMapping("/top-by-month-and-year")
    //Statistics of 10 best-selling products by month and year
    public ResponseEntity<ResponseMessage> thongKe_Top10(@RequestParam("month") Integer month, @RequestParam("year") Integer year) {
        List<Object[]> list = this.iStatisticalService.thongKeTop10(month, year);
        return getStatisticalRevenueTop10(list);
    }

    @GetMapping("/top-10-by-year/{year}")
    //Statistics of 10 best-selling products by year
    public ResponseEntity<ResponseMessage> thongKe_Top10ByYear(@PathVariable("year") Integer year) {
        List<Object[]> list = this.iStatisticalService.thongKeTop10ByYear(year);
        return getStatisticalRevenueTop10(list);
    }

    public ResponseEntity<ResponseMessage> getStatisticalRevenueTop10(List<Object[]> list) {
        List<Statistical_Top10Dto> list_top10 = new ArrayList<>();
        for (Object[] objects : list) {
            list_top10.add(Convert.objectToClass(objects, Statistical_Top10Dto.class));
        }
        return ResponseEntity.ok(new ResponseMessage(StatusMessage.OK, "Get Data", list_top10));
    }
}
