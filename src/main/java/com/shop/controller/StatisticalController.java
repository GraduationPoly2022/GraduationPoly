package com.shop.controller;

import com.shop.dto.ResponseMessage;
import com.shop.dto.StatisticalRevenue;
import com.shop.enumEntity.StatusMessage;
import com.shop.services.IOrderService;
import com.shop.utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/statistical")
public class StatisticalController {

    @Autowired
    private IOrderService orderService;

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
}
