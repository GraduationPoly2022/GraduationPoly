package com.shop.controller;

import com.shop.dto.ResponseMessage;
import com.shop.dto.Statistical_Top10Dto;
import com.shop.enumEntity.StatusMessage;
import com.shop.services.IStatisticalService;
import com.shop.utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/statistical")
public class StatisticalController {
    @Autowired
    private IStatisticalService iStatisticalService;

    @GetMapping("/top-by-month-and-year")
    //Statistics of 10 best-selling products by month and year
    public ResponseEntity<ResponseMessage> thongKe_Top10(@RequestParam("month") Integer month, @RequestParam("year") Integer year) {
        List<Object[]> list = this.iStatisticalService.thongKeTop10(month, year);
        return getStatisticalRevenue(list);
    }

    @GetMapping("/top-10-by-year/{year}")
    //Statistics of 10 best-selling products by year
    public ResponseEntity<ResponseMessage> thongKe_Top10ByYear(@PathVariable("year") Integer year) {
        List<Object[]> list = this.iStatisticalService.thongKeTop10ByYear(year);
        return getStatisticalRevenue(list);
    }

    public ResponseEntity<ResponseMessage> getStatisticalRevenue(List<Object[]> list) {
        List<Statistical_Top10Dto> list_top10 = new ArrayList<>();
        for (Object[] objects : list) {
            list_top10.add(Convert.objectToClass(objects, Statistical_Top10Dto.class));
        }
        return ResponseEntity.ok(new ResponseMessage(StatusMessage.OK, "Get Data", list_top10));
    }

}
