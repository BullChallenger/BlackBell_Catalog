package com.example.blackbell_catalog.service;

import com.example.blackbell_catalog.dto.CatalogDTO.*;
import com.example.blackbell_catalog.entity.CatalogEntity;
import com.example.blackbell_catalog.repository.CatalogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {

    private final ModelMapper modelMapper;

    private final CatalogRepository catalogRepository;

    @Override
    public Iterable<GetResponseDTO> getAllCatalogs() {
        List<CatalogEntity> catalogList = catalogRepository.findAll();
        List<GetResponseDTO> responseDTOList = new ArrayList<>();

        catalogList.stream().forEach(catalog -> {
            responseDTOList.add(modelMapper.map(catalog, GetResponseDTO.class));
        });

        return responseDTOList;
    }
}
