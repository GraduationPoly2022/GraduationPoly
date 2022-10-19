package com.shop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shop.dto.*;
import com.shop.enumEntity.StatusMessage;
import com.shop.helper.Cookies;
import com.shop.services.IProductService;
import com.shop.utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cookies")
public class CookieController {

    @Autowired
    private Cookies cookieService;

    @Autowired
    private IProductService iProductService;


    @GetMapping("/create-cookie-order")
    public ResponseEntity<ResponseMessage> AddCookieOrder(@RequestParam("order") String order,
                                                          HttpServletResponse response, HttpServletRequest request) throws IOException {
        ResponseEntity<ResponseMessage> message;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Set-Cookie", "platform=mobile; Max-Age=604800; Path=/; Secure; HttpOnly");
        String path = "http://localhost:8080/";
        Cookie cookie = new Cookie("abctest", "nesllfsf");
        cookie.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(cookie);
        OrderDto requestBody = Convert.StringToEntity(order, OrderDto.class);
        try {
            OrderDto orderDto = this.cookieService.get("order", OrderDto.class, request);
            Cookie cookies;
            if (orderDto == null) {
                List<OrderDetailDto> listOrderDTail = new ArrayList<>();
                OrderDetailDto orderDetailDto = new OrderDetailDto();
                OrderDto odd = new OrderDto();
                orderDetailDto.setQty(requestBody.getQty());
                orderDetailDto.setPrice(requestBody.getProduct().getPriceProd());
                orderDetailDto.setProdOdde(requestBody.getProduct());
                listOrderDTail.add(orderDetailDto);

                odd.setOrderDate(requestBody.getOrderDate());
                odd.setStatus(requestBody.getStatus());
                odd.setLsOrderDetails(listOrderDTail);
                cookies = this.cookieService.add("order", odd, path, response);
            } else {
                boolean productDto = orderDto.getLsOrderDetails().stream()
                        .map(OrderDetailDto::getProdOdde)
                        .anyMatch(prodOdde -> Objects.equals(prodOdde.getProdId(), requestBody.getProduct().getProdId()));
                if (productDto) {
                    orderDto.setLsOrderDetails(orderDto.getLsOrderDetails().stream()
                            .filter(item -> Objects.equals(item.getProdOdde().getProdId(), requestBody.getProduct().getProdId()))
                            .peek(item -> item.setQty(item.getQty() + requestBody.getQty()))
                            .collect(Collectors.toList())
                    );
                } else {
                    OrderDetailDto orderDetailDto = new OrderDetailDto();
                    orderDetailDto.setQty(requestBody.getQty());
                    orderDetailDto.setPrice(requestBody.getProduct().getPriceProd());
                    orderDetailDto.setProdOdde(requestBody.getProduct());
                    List<OrderDetailDto> listOrderDTail = new ArrayList<>();
                    listOrderDTail.add(orderDetailDto);
                    listOrderDTail.addAll(orderDto.getLsOrderDetails());
                    orderDto.setLsOrderDetails(listOrderDTail);
                }
                cookies = this.cookieService.add("order", orderDto, path, response);
            }
            message = ResponseEntity.status(HttpStatus.OK).headers(headers)
                    .body(new ResponseMessage(StatusMessage.OK, "Oke", cookies));
            response.addCookie(cookies);
        } catch (Exception e) {
            message = ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage(StatusMessage.OK, "Error " + e.getMessage(), null));
        }
        return message;
    }

    @GetMapping("/all-cookie")
    public ResponseEntity<ResponseMessage> getOrderCookie(HttpServletRequest request) throws IOException {
        OrderDto orderDto = this.cookieService.get("order", OrderDto.class, request);
        return ResponseEntity.ok(new ResponseMessage(StatusMessage.OK, "Get Cookie Order thanh cong", orderDto));
    }

    @GetMapping("/")
    public ResponseEntity<ResponseMessage> findAllProduct() {
        List<ProductDto> productDtoList = this.iProductService.findAllProducts();
        return ResponseEntity.ok(new ResponseMessage(StatusMessage.OK, "Get Data", productDtoList));
    }

    @DeleteMapping("/")
    public void deleteCookie() {
        String path = "http://localhost:4200";
        this.cookieService.deleteForCookie("order", path);
    }

    @GetMapping("/abc")
    public ResponseEntity<?> test(@RequestParam("name") String name, @RequestParam("to") String to, HttpServletResponse response) throws JsonProcessingException {
        MailDto mailDto = new MailDto(name, to);
        String path = "http://localhost:8080";
        this.cookieService.add("testting", mailDto, path, response);
        return null;
    }
}


