package com.example.blackbell_catalog.service;

import com.example.blackbell_catalog.dto.CatalogDTO.*;

public interface CatalogService {
    Iterable<GetResponseDTO> getAllCatalogs();
}
