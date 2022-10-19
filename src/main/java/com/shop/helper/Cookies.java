package com.shop.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shop.utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.WebAsyncUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class Cookies {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    public <T> List<T> gets(String name, Class<T> clazz, HttpServletRequest request) throws IOException {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equalsIgnoreCase(name)) {
                    System.out.println(cookie.getValue());
                    return Convert.StringToArray(cookie.getValue(), clazz);
                }
            }
        }
        return null;
    }

    public <T> T get(String name, Class<T> clazz, HttpServletRequest request) throws IOException {
        Cookie cookies = WebUtils.getCookie(request, name);
        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equalsIgnoreCase(name)) {
            return Convert.StringToEntity(cookies.getValue(), clazz);
//                }
//            }
        }
        return null;
    }

    public <T> Cookie add(String name, T t, String path, HttpServletResponse response) throws JsonProcessingException {
        Cookie cookie = new Cookie(name, Convert.EntityToString(t));
        cookie.setMaxAge(7 * 24 * 60 * 60);
        cookie.setPath("/");
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        WebAsyncUtils.createAsyncWebRequest(request, response);
        return cookie;
    }

    public <T> Cookie add(String name, List<T> t, String path, HttpServletResponse response) throws JsonProcessingException {
        Cookie cookie = new Cookie(name, Convert.ArrayToString(t));
        cookie.setMaxAge(7 * 24 * 60 * 60);
        cookie.setPath("/");
//        cookie.setSecure(true);
//        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return cookie;
    }

    public void deleteForCookie(String name, String path) {
        Cookie cookie = new Cookie(name, null);
        cookie.setPath("/");
//        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
    }
}