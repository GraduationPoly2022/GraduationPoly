package com.shop.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
public class OrderController {
    @GetMapping("")
    List<String> getAll(){
        return List.of("apc", "cba", "acb");
    }
}
