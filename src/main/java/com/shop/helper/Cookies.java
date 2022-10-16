package com.shop.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shop.utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Repository
public class Cookies {
    @Autowired
    private HttpServletRequest request;

    public <T> List<T> gets(String name, Class<T> clazz) throws IOException {
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

    public <T> T get(String name, Class<T> clazz) throws IOException {
        Cookie[] cookies = this.request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equalsIgnoreCase(name)) {
                    return Convert.StringToEntity(cookie.getValue(), clazz);
                }
            }
        }
        return null;
    }

    public <T> Cookie add(String name, T t) throws JsonProcessingException {
        Cookie cookie = new Cookie(name, Convert.EntityToString(t));
        cookie.setMaxAge(7 * 24 * 60 * 60);
        cookie.setPath("/");
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        return cookie;
    }

    public <T> Cookie add(String name, List<T> t) throws JsonProcessingException {
        Cookie cookie = new Cookie(name, Convert.ArrayToString(t));
        cookie.setMaxAge(7 * 24 * 60 * 60);
        cookie.setPath("/");
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        return cookie;
    }
}