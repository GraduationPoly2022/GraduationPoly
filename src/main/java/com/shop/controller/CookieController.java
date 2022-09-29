package com.shop.controller;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
public class CookieController {
    //Tạo Cookie
    @GetMapping("/create-cookie")
    public ResponseEntity setCookie(){
        ResponseCookie resCookie = ResponseCookie.from("user-id","c2FtLnNtaXRoQGV4YW1wbGUuY29t")
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(7*24*60*60) //Cookie hết hạn sau 7 ngày
                .domain("localhost")
                .build();
        return  ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,resCookie.toString()).build();
    }

    //Xóa Cookie
    @GetMapping("/delete-cookie")
    public ResponseEntity deleteCookie(){
        //Tạo cookie
        ResponseCookie resCookie = ResponseCookie.from("user-id", null)
                .build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,resCookie.toString()).build();
    }

    //Đọc Cookie
    @GetMapping("/read-cookie")
    public String readCookie(@CookieValue(name = "user-id", defaultValue = "default-user-id") String cookieName){
        return String.format("Giá trị của Cookie với tên user-id là: %s", cookieName);
    }
}
