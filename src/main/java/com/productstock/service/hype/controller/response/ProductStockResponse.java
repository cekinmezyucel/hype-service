package com.productstock.service.hype.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.productstock.service.hype.model.ProductStock;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductStockResponse implements Serializable {

  public ProductStockResponse(ProductStock productStock) {
    this.productId = productStock.getProductId();
    this.stockCount = productStock.getStockCount();
    this.reservationCount = productStock.getReserveCount();
    this.soldCount = productStock.getSoldCount();
  }

  @JsonProperty("ID")
  private String productId;

  @JsonProperty("IN_STOCK")
  private Integer stockCount;

  @JsonProperty("RESERVED")
  private Integer reservationCount;

  @JsonProperty("SOLD")
  private Integer soldCount;

}
