package com.shop.services;

import com.shop.entity.Category;

import java.util.List;

public interface ICategoryService {
    Category createCategory(Category category);

    List<Category> findAll();

    Category findBycatId(Long catId);
}
