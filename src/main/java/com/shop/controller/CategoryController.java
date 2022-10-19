package com.shop.controller;

import com.shop.dto.ResponseMessage;
import com.shop.entity.Category;
import com.shop.enumEntity.StatusMessage;
import com.shop.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.shop.utils.Convert.CapitalOfFirst;

@RestController
@RequestMapping("api/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<ResponseMessage> handleCreate(@RequestBody Category category) {
        return getMessageResponseEntity(category, "Create");
    }

    @PutMapping("/")
    public ResponseEntity<ResponseMessage> handleUpdate(@RequestBody Category category) {
        return getMessageResponseEntity(category, "Update");
    }

    @GetMapping("/")
    public ResponseEntity<ResponseMessage> findAll() {
        List<Category> categories = this.categoryService.findAll();
        return ResponseEntity.ok(
                new ResponseMessage(StatusMessage.OK, "Get Data category successful", categories)
        );
    }

    private ResponseEntity<ResponseMessage> getMessageResponseEntity(@RequestBody Category category, String name) {
        ResponseEntity<ResponseMessage> message = null;
        category.setName(CapitalOfFirst(category.getName()));
        Category categorySave = this.categoryService.createCategory(category);
        try {
            if (categorySave != null) {
                message = ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseMessage(StatusMessage.OK, name + " category successful", categorySave)
                );
            }
        } catch (Exception e) {
            message = ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseMessage(StatusMessage.FAILED,
                            name + " category unsuccessful " + e.getMessage(), null)
            );
        }
        return message;
    }
}
