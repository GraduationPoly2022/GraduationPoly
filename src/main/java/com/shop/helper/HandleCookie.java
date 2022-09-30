package com.shop.helper;

import com.shop.dto.MailDto;
import com.shop.utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class HandleCookie {
    @Autowired
    private HttpServletResponse response;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpSession session;

    public <T> List<T> get(String name, Class<T> clazz) {
        Cookie[] cookies = this.request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equalsIgnoreCase(name)) {
                    return Convert.StringToArray(cookie.getValue(), clazz);
                }
            }
        }
        return null;
    }

    public <T> Cookie add(String name, List<T> t) {
        Cookie cookie = new Cookie(name, Convert.ArrayToString(t));
        cookie.setMaxAge(7*24*60*60);// cookie hết hạn sau 7 ngày
        cookie.setPath("/");
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        this.response.addCookie(cookie);
        return cookie;
    }


//    public Cookie delete(String name, String value, Long exprired){
//        Cookie ckremove = new Cookie(name, value);
//        ckremove.setMaxAge(0);
//        this.response.addCookie(ckremove);
//        return ckremove;
//    }
}
