package com.shop.helper;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HandleCookie {
    @Autowired
    private HttpServletResponse response;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpSession session;

    public String get(String name) {
        Cookie[] cookies = this.request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equalsIgnoreCase(name)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public Cookie add(String name, String value, Long exprired) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(exprired.intValue());
        cookie.setPath("/cookie");
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        this.response.addCookie(cookie);

        return cookie;
    }
}
