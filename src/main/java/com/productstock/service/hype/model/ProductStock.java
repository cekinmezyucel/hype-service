package com.productstock.service.hype.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash("ProductStock")
public class ProductStock implements Serializable {

  @Id
  private String productId;

  private int stockCount;

  private int reserveCount;

  private Set<String> reservationTokens;

  private int soldCount;

  public Set<String> getReservationTokens() {
    if (this.reservationTokens == null) {
      this.reservationTokens = new HashSet<>();
    }
    return this.reservationTokens;
  }
}
