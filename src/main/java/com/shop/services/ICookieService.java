package com.shop.services;

import com.fasterxml.jackson.core.JsonProcessingException;

import javax.servlet.http.Cookie;
import java.io.IOException;
import java.util.List;

public interface ICookieService {
    <T> Cookie createOrderOnCookies(String name, T entity) throws JsonProcessingException;

    <T> List<T> findAll(String name, Class<T> clazz) throws IOException;

    <T> T find(String name, Class<T> clazz) throws IOException;
}
