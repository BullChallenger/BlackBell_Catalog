package com.example.blackbell_catalog.dto;

import java.io.Serializable;

public class CatalogDTO {
    public static class GetResponseDTO implements Serializable {
        private String productId;

        private String productName;

        private Integer stock;

        private Integer unitPrice;

        private Integer totalPrice;

        private String orderId;

        private String accountId;
    }
}
