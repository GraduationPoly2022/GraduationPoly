package com.shop.services.Impl;

import com.shop.entity.Category;
import com.shop.repository.CategoryRepository;
import com.shop.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {
        return this.categoryRepository.save(category);
    }
}
