package com.shop.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Convert {

    public static String CapitalAll(String name) {
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

    public static String CapitalOfFirst(String name) {
        if (!Objects.equals(name, "")) {
            String[] split = name.split(" ");
            StringBuilder sb = new StringBuilder();
            System.out.println(split.length);
            if (split.length > 1) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 1; i < split.length; i++) {
                    stringBuilder.append(split[i].toLowerCase()).append(" ");
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


    public static <T> String ArrayToString(List<T> tList) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String str = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(tList);
        byte[] bytes = str.getBytes();
        byte[] buffer = Base64.getEncoder().encode(bytes);
        return new String(buffer, StandardCharsets.UTF_8);
    }

    public static <T> String EntityToString(T tList) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String str = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(tList);
        byte[] bytes = str.getBytes();
        byte[] buffer = Base64.getEncoder().encode(bytes);
        return new String(buffer, StandardCharsets.UTF_8);
    }

    public static <T> List<T> StringToArray(String encode, Class<T> clazz) throws IOException {
        byte[] decode = Base64.getDecoder().decode(encode);
        String str = new String(decode);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(str, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
    }

    public static <T> T StringToEntity(String encode, Class<T> clazz) throws IOException {
        byte[] decode = Base64.getDecoder().decode(encode);
        String str = new String(decode);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(str, clazz);
    }
}
