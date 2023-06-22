package com.example.blackbell_catalog.controller;

import com.example.blackbell_catalog.constant.ResultType;
import com.example.blackbell_catalog.dto.CatalogDTO.*;
import com.example.blackbell_catalog.dto.ResponseDTO;
import com.example.blackbell_catalog.service.CatalogService;
import com.example.blackbell_catalog.vo.CatalogVO.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/catalog-service")
public class CatalogController {

    private final Environment env;
    private final ModelMapper modelMapper;

    private final CatalogService catalogService;

    @GetMapping(value = "/health_check")
    public String status() {
        return String.format("It's Working in User Service on PORT %s", env.getProperty("local.server.port"));
    }

    @GetMapping(value = "/catalogs")
    public ResponseDTO<List<ResponseVO>> readAll() {
        Iterable<GetResponseDTO> catalogList = catalogService.getAllCatalogs();

        List<ResponseVO> responseVOList = new ArrayList<>();
        catalogList.forEach(catalog -> {
            responseVOList.add(modelMapper.map(catalog, ResponseVO.class));
        });

        return ResponseDTO.ok(ResultType.SUCCESS, responseVOList);
    }

}
