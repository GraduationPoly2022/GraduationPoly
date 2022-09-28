package com.shop.services.Impl;

import com.shop.entity.Producer;
import com.shop.repository.ProducerRepository;
import com.shop.services.IProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProducerImpl implements IProducerService {
    @Autowired
    private ProducerRepository producerRepository;

    @Override
    public Producer createProducer(Producer producer) {
        return this.producerRepository.save(producer);
    }
}
