package com.productstock.service.hype.service;

import static com.productstock.service.hype.exception.ExceptionMap.OBJECT_NOT_FOUND;

import com.productstock.service.hype.controller.response.ProductStockResponse;
import com.productstock.service.hype.exception.model.BusinessException;
import com.productstock.service.hype.model.ProductStock;
import com.productstock.service.hype.repository.ProductStockRepository;
import java.util.HashSet;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProductStockService {

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  private final ProductStockRepository productStockRepository;

  public ProductStockService(ProductStockRepository productStockRepository) {
    this.productStockRepository = productStockRepository;
  }

  public ProductStockResponse getProductStock(String productId) {
    return productStockRepository.findById(productId).map(ProductStockResponse::new)
        .orElseThrow(() -> new BusinessException(OBJECT_NOT_FOUND, productId));
  }

  public ProductStock upsertStock(String productId, int stockCount) {
    Optional<ProductStock> productStock = productStockRepository.findById(productId);
    if (productStock.isPresent()) {
      var existingStock = productStock.get();
      existingStock.setStockCount(stockCount);
      log.info("Update!!");
      return productStockRepository.save(existingStock);
    } else {
      var newProductStock = new ProductStock();
      newProductStock.setProductId(productId);
      newProductStock.setStockCount(stockCount);
      newProductStock.setReservationTokens(new HashSet<>());
      log.info("Save!!");
      return productStockRepository.save(newProductStock);
    }
  }

  public ProductStock reserve(String productId, String value) {
    Optional<ProductStock> productStock = productStockRepository.findById(productId);
    if (productStock.isPresent() && productStock.get().getStockCount() > 0) {
      var existingStock = productStock.get();
      existingStock.setReserveCount(existingStock.getReserveCount() + 1);
      existingStock.setStockCount(existingStock.getStockCount() - 1);
      existingStock.getReservationTokens().add(value);
      log.info("Reserved!!");
      return productStockRepository.save(existingStock);
    }
    return null;
  }

  public ProductStock unReserve(String productId, String value) {
    Optional<ProductStock> productStock = productStockRepository.findById(productId);
    if (productStock.isPresent() && productStock.get().getReservationTokens().contains(value)) {
      var existingStock = productStock.get();
      existingStock.setReserveCount(existingStock.getReserveCount() - 1);
      existingStock.setStockCount(existingStock.getStockCount() + 1);
      existingStock.getReservationTokens().remove(value);
      log.info("UnReserved!!");
      return productStockRepository.save(existingStock);
    }
    return null;
  }

  public ProductStock sold(String productId, String value) {
    Optional<ProductStock> productStock = productStockRepository.findById(productId);
    if (productStock.isPresent() && productStock.get().getReservationTokens().contains(value)) {
      var existingStock = productStock.get();
      existingStock.setReserveCount(existingStock.getReserveCount() - 1);
      existingStock.setStockCount(existingStock.getStockCount());
      existingStock.setSoldCount(existingStock.getSoldCount() + 1);
      existingStock.getReservationTokens().remove(value);
      log.info("Sold!!");
      return productStockRepository.save(existingStock);
    }
    return null;
  }
}
