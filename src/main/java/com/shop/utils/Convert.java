package com.shop.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Convert {

    public static String CapitalAllFirstLetter(String name) {
        if (name != null) {
            String[] split = name.split(" ");
            StringBuilder sb = new StringBuilder();
            for (String s : split) {
                sb.append(Character.toUpperCase(s.charAt(0))).append(s.substring(1).toLowerCase()).append(" ");
            }
            return sb.toString().trim();
        }
        return null;
    }

    public static String CapitalFirstLetter(String name) {
        if (!Objects.equals(name, "")) {
            String[] split = name.split(" ");
            StringBuilder sb = new StringBuilder();
            System.out.println(split.length);
            if (split.length > 1) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 1; i < split.length; i++) {
                    stringBuilder.append(split[i].substring(0).toLowerCase()).append(" ");
                }
                sb.append(Character.toUpperCase(split[0].charAt(0))).append(split[0].substring(1).toLowerCase()).append(" ").append(stringBuilder);
            } else {
                sb.append(Character.toUpperCase(split[0].charAt(0))).append(split[0].substring(1).toLowerCase());
            }
            return sb.toString().trim();
        }
        return null;
    }

    public static <E> E objectToClass(@NotNull Object[] obj, @NotNull Class<E> clazz) {
        Map<String, Object> stringObjectMap = new HashMap<>();
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            stringObjectMap.put(fields[i].getName(), obj[i]);
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.convertValue(stringObjectMap, clazz);
    }

    public static <T> String ArrayToString(List<T> tList) {
        StringBuilder sb = new StringBuilder();
        try {
            for (T c : tList) {
                Field[] fields = c.getClass().getDeclaredFields();
                Method[] method = c.getClass().getMethods();
                List<Object> values = new ArrayList<>();
                for (Method method1 : method) {
                    method1.setAccessible(true);
                    if (method1.getName().startsWith("get")) {
                        if (!method1.invoke(c).toString().startsWith("class")) {
                            values.add(method1.invoke(c));
                        }
                    }
                }
                for (int i = 0; i < fields.length; i++) {
                    sb.append(fields[i].getName()).append("=").append(values.get(i)).append(",");
                }
                sb = new StringBuilder(sb.substring(0, sb.lastIndexOf(",")));
                sb.append(";");
            }
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return sb.substring(0, sb.lastIndexOf(";")).trim();
    }

    public static <T> List<T> StringToArray(String str, Class<T> clazz) {
        Map<String, Object> map = new HashMap<>();
        List<T> list = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String[] cuts = str.split(";");
        for (String cut : cuts) {
            String[] split = cut.split(",");
            for (int i = 0; i < split.length; i++) {
                String[] cutVals = split[i].trim().split("=");
                map.put(cutVals[0], cutVals[1]);
                if (i % 2 != 0) {
                    T cur = mapper.convertValue(map, clazz);
                    list.add(cur);
                    map.clear();
                }
            }
        }
        return list;
    }
}
