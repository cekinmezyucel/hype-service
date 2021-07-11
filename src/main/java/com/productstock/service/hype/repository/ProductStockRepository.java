package com.productstock.service.hype.repository;

import com.productstock.service.hype.model.ProductStock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductStockRepository extends CrudRepository<ProductStock, String> {

}
