package com.shop.controller;

import com.shop.entity.Producer;
import com.shop.services.IProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/producer")
@CrossOrigin("*")
public class ProducerController {
    @Autowired
    private IProducerService producerService;

    @PostMapping("/")
    public ResponseEntity<?> createProducer(@RequestBody Producer producer) {
        Producer pro = this.producerService.createProducer(producer);
        if (pro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Can not saved producer!!");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(pro);
        }

    }
}
