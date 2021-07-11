package com.productstock.service.hype.controller;

import com.productstock.service.hype.controller.response.ProductStockResponse;
import com.productstock.service.hype.service.ProductStockService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productstock")
public class ProductStockController {

  private final ProductStockService productStockService;

  public ProductStockController(ProductStockService productStockService) {
    this.productStockService = productStockService;
  }

  @GetMapping("/{id}")
  public ProductStockResponse getProduct(@PathVariable("id") String productId) {
    return productStockService.getProductStock(productId);
  }

}
