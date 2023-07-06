package com.example.blackbell_catalog.messageQ;

import com.example.blackbell_catalog.constant.ResultType;
import com.example.blackbell_catalog.entity.CatalogEntity;
import com.example.blackbell_catalog.exception.BaseException;
import com.example.blackbell_catalog.repository.CatalogRepository;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class KafkaConsumer {

    private final ObjectMapper objectMapper;

    private final CatalogRepository catalogRepository;

    @KafkaListener(topics = "example-catalog-topic")
    public void updateQuantity(String kafkaMessage) {
        log.info("Kafka Message: ==> " + kafkaMessage);

        Map<Object, Object> map = new HashMap<>();
        try {
            map = objectMapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String productId = String.valueOf(map.get("productId"));
        CatalogEntity theCatalog = catalogRepository.findByProductId(productId).orElseThrow(() ->
                new BaseException(ResultType.SYSTEM_ERROR)
        );

        theCatalog.setStock(theCatalog.getStock() - ((Integer) map.get("quantity")));
        catalogRepository.save(theCatalog);
    }
}
