package com.prixbanque.catalog_ms.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class CatalogService {

    @GetMapping("/ping")
    public String ping() {
        return "Catalog microservice is alive!";
    }
}
