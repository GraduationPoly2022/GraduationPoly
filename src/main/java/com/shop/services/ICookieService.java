package com.shop.services;

import com.fasterxml.jackson.core.JsonProcessingException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface ICookieService {
    <T> Cookie createOrderOnCookies(String name, T entity, String path, HttpServletResponse response) throws JsonProcessingException;

    <T> List<T> findAll(String name, Class<T> clazz, HttpServletRequest request) throws IOException;

    <T> T find(String name, Class<T> clazz, HttpServletRequest request) throws IOException;

    void deleteForCookie(String name, String path);
}
