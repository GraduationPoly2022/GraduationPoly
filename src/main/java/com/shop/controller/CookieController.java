package com.shop.controller;
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

    //Đọc HTTP Cookie
    @GetMapping("/")
    public String readCookie(@CookieValue(value = "username", defaultValue = "Lam") String username){
        return"Chào! Tên đăng nhập của tôi là"+username;
    }

    //Đặt Cookie HTTP
    @GetMapping("/change-username")
    public String setCookie(HttpServletResponse response){
        //tạo một cookie
        Cookie cookie = new Cookie("username", "Lam");
        cookie.setMaxAge(7 * 24 * 60 * 60); //Hết hạn sau 7 ngày
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/"); //Cookie toàn cầu có thể truy cập ở mọi nơi

        //thêm cookie vào phản hồi
        response.addCookie(cookie);
        return "Tên người dùng đã được thay đổi";
    }

    //Xóa Cookies
    @GetMapping("/delete-username")
    public String deleteCookie(HttpServletResponse response) {

        //tạo một cookie
        Cookie cookie = new Cookie("username", null);
        cookie.setMaxAge(0);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        //thêm cookie vào phản hồi
        response.addCookie(cookie);

        return "Tên người dùng đã bị xóa!";
    }

    //Đọc tất cả Cookie

    @GetMapping("/all-cookies")
    public String readAllCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            return Arrays.stream(cookies)
                    .map(c -> c.getName()+"="+ c.getValue()).collect(Collectors.joining(","));
        }
        return "Không có Cookies";
    }
}
