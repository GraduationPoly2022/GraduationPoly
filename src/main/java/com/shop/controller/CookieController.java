package com.shop.controller;

import com.shop.dto.OrderDetailDto;
import com.shop.dto.OrderDto;
import com.shop.dto.ProductDto;
import com.shop.dto.ResponseMessage;
import com.shop.entity.Category;
import com.shop.entity.OrderDetail;
import com.shop.enumEntity.OrderStatus;
import com.shop.enumEntity.StatusMessage;
import com.shop.helper.Cookies;
import com.shop.services.ICategoryService;
import com.shop.services.IOrderDetailService;
import com.shop.services.IOrderService;
import com.shop.services.IProductService;
import com.shop.utils.Convert;
import com.shop.utils.GoogleTranslate;
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
    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IOrderDetailService orderDetailService;

    @Autowired
    private IOrderService orderService;

    @GetMapping("/find-cate-all")
    public ResponseEntity<ResponseMessage> findAll(@RequestParam("lang") String lang) throws IOException {
        List<Category> categories = new ArrayList<>();
        for (Category category : this.categoryService.findAll()) {
            Category cate = new Category();
            cate.setCatId(category.getCatId());
            switch (lang) {
                case "vi", "zh" -> cate.setName(GoogleTranslate.translate("en", lang, category.getName()));
                default -> cate.setName(category.getName());
            }
            categories.add(cate);
        }
        return ResponseEntity.ok(
                new ResponseMessage(StatusMessage.OK, "Get Data category successful", categories)
        );
    }

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

    @GetMapping("/find-product-all")
    public ResponseEntity<ResponseMessage> findAllProduct(@RequestParam("userId") Long userId) {
        List<ProductDto> productDtoList;
        if (userId != 0) {
            productDtoList = this.iProductService.findAllProducts(userId);
        } else {
            productDtoList = this.iProductService.findAllProducts();
        }
        return ResponseEntity.ok(new ResponseMessage(StatusMessage.OK, "Get Data", productDtoList));
    }

    @GetMapping("/find-product-by-cateID/{cateId}")
    public ResponseEntity<ResponseMessage> findByCategory(@PathVariable("cateId") Long cateId,
                                                          @RequestParam("userId") Long userId) {
        List<ProductDto> productDtoList;
        if (userId != 0) {
            productDtoList = this.iProductService.findByCategory(cateId, userId);
        } else {
            productDtoList = this.iProductService.findByCategory(cateId);
        }
        return ResponseEntity.ok(new ResponseMessage(StatusMessage.OK, "Get Data", productDtoList));

    }

    //Add Quantity
    @PatchMapping("/update-qty-order")
    public ResponseEntity<ResponseMessage> updateQuantity(@RequestBody OrderDto orderDto) {
        ResponseEntity<ResponseMessage> message = null;
        OrderDetail orderDetail = this.orderDetailService.checkOrders(orderDto.getProduct().getProdId(), orderDto.getOdId(), OrderStatus.CART);
        if (orderDetail != null) {
            if (orderDto.getQty() == 0) {
                message = this.delete(orderDetail);
            } else {
                orderDetail.setQty(orderDto.getQty());
                OrderDetail updateOdd = this.orderDetailService.saveOrUpdate(orderDetail);
                if (updateOdd != null) {
                    message = ResponseEntity.status(HttpStatus.OK)
                            .body(new ResponseMessage(StatusMessage.OK, "Update successfully", updateOdd));
                }
            }
        } else {
            message = ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage(StatusMessage.NOT_FOUND, "Not found order", null));
        }
        return message;
    }

    //Delete Order
    @DeleteMapping("/delete-product-order")
    public ResponseEntity<ResponseMessage> deleteOrder(
            @RequestParam("odId") Long odId,
            @RequestParam("prodId") Long prodId) {
        OrderDetail orderDetails = this.orderDetailService.checkOrders(prodId, odId, OrderStatus.CART);
        return this.delete(orderDetails);
    }

    @GetMapping("/find-product-id/{prodId}/{userId}")
    public ResponseEntity<ResponseMessage> findProductByProdId(@PathVariable("prodId") Long prodId,
                                                               @RequestParam("lang") String lang,
                                                               @PathVariable Long userId) throws IOException {
        ResponseEntity<ResponseMessage> message = null;
        ProductDto productDtoList = this.iProductService.findAcSpLtByProduct(prodId, lang, userId);
        if (productDtoList != null) {
            message = ResponseEntity.ok(new ResponseMessage(StatusMessage.OK, "Get Data", productDtoList));
        }
        return message;
    }

    private ResponseEntity<ResponseMessage> delete(OrderDetail orderDetails) {
        ResponseEntity<ResponseMessage> message = null;
        try {
            if (orderDetails != null) {
                List<OrderDetail> odDetails = this.orderDetailService.checkOrderDetails(orderDetails.getOdde().getOdId(), OrderStatus.CART);
                if (odDetails.size() <= 1) {
                    this.orderDetailService.deleteOrders(orderDetails);
                    this.orderService.delete(orderDetails.getOdde().getOdId());
                } else {
                    this.orderDetailService.deleteOrders(orderDetails);
                }
                message = ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseMessage(StatusMessage.OK, "Deleted product id is ", orderDetails.getProdOdde().getProdId()));
            }
        } catch (Exception e) {
            message = ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage(StatusMessage.ERROR, e.getMessage(), null));
        }
        return message;
    }
}


