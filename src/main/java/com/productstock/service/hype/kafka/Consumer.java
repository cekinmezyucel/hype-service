package com.productstock.service.hype.kafka;

import static org.springframework.kafka.support.KafkaHeaders.RECEIVED_MESSAGE_KEY;

import com.productstock.service.hype.service.ProductStockService;
import com.productstock.service.hype.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

  private final Logger logger = LoggerFactory.getLogger(Consumer.class);

  private final ProductStockService productStockService;

  public Consumer(ProductStockService productStockService) {
    this.productStockService = productStockService;
  }

  @KafkaListener(topics = "hype", groupId = "group_id")
  public void consume(@Payload String message, @Header(RECEIVED_MESSAGE_KEY) String productId) {
    logger.info(String.format("#### -> Consumed message -> %s", message));
    logger.info(String.format("#### -> Consumed key -> %s", productId));

    var action = StringUtils.getAction(message);
    var value = StringUtils.getValue(message);
    switch (action) {
      case CREATE:
        productStockService.upsertStock(productId, Integer.parseInt(value));
        break;
      case RESERVE:
        productStockService.reserve(productId, value);
        break;
      case UNRESERVE:
        productStockService.unReserve(productId, value);
        break;
      case SOLD:
        productStockService.sold(productId, value);
        break;
      default:
        //error
    }
  }

}
