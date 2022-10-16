package com.shop.services.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shop.helper.Cookies;
import com.shop.services.ICookieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.io.IOException;
import java.util.List;

@Service
public class CookieServiceImpl implements ICookieService {

    @Autowired
    private Cookies cookies;


    @Override
    public <T> Cookie createOrderOnCookies(String name, T entity) throws JsonProcessingException {
        return this.cookies.add(name, entity);
    }

    @Override
    public <T> List<T> findAll(String name, Class<T> clazz) throws IOException {
        return this.cookies.gets(name, clazz);
    }

    @Override
    public <T> T find(String name, Class<T> clazz) throws IOException {
        return this.cookies.get(name, clazz);
    }
}
