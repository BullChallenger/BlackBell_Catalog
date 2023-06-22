package com.example.blackbell_catalog.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.Date;

public class CatalogVO {

    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ResponseVO {
        private String productId;

        private String productName;

        private Integer unitPrice;

        private Integer stock;

        private Date createdAt;
    }
}
