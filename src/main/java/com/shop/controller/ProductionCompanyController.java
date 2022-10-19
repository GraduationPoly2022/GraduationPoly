package com.shop.controller;

import com.shop.dto.ResponseMessage;
import com.shop.entity.ProductionCompany;
import com.shop.enumEntity.StatusMessage;
import com.shop.services.IProductionCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.shop.utils.Convert.CapitalOfFirst;

@RestController
@RequestMapping("api/production-company")
public class ProductionCompanyController {

    @Autowired
    private IProductionCompanyService productionCompanyService;

    @PostMapping("/")
    public ResponseEntity<ResponseMessage> handleCreate(@RequestBody ProductionCompany production) {
        return getMessageResponseEntity(production, "Create");
    }

    @PutMapping("/")
    public ResponseEntity<ResponseMessage> handleUpdate(@RequestBody ProductionCompany production) {
        return getMessageResponseEntity(production, "Update");
    }

    @GetMapping("/")
    public ResponseEntity<ResponseMessage> findAll() {
        List<ProductionCompany> productionCompanies = this.productionCompanyService.findAll();
        return ResponseEntity.ok(
                new ResponseMessage(StatusMessage.OK, "Get Data production company successful", productionCompanies)
        );
    }

    private ResponseEntity<ResponseMessage> getMessageResponseEntity(@RequestBody ProductionCompany production, String name) {
        ResponseEntity<ResponseMessage> message = null;
        production.setName(CapitalOfFirst(production.getName()));
        ProductionCompany productionSave = this.productionCompanyService.createProductionCompany(production);
        try {
            if (productionSave != null) {
                message = ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseMessage(StatusMessage.OK, name + " production company successful", productionSave)
                );
            }
        } catch (Exception e) {
            message = ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseMessage(StatusMessage.FAILED,
                            name + " production company unsuccessful " + e.getMessage(), null)
            );
        }
        return message;
    }
}
