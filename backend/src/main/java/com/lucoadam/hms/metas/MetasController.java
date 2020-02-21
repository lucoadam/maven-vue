package com.lucoadam.hms.metas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/public/metas")
public class MetasController {
    @Autowired
    MetaService metaService;

    @GetMapping("")
    public Iterable<Meta> index() {
        return metaService.findAll();
    }
}
