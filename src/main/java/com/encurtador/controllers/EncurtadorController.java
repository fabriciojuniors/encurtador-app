package com.encurtador.controllers;

import com.encurtador.models.Encurtador;
import com.encurtador.models.dto.EncurtadorDto;
import com.encurtador.services.EncurtadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
public class EncurtadorController {

    @Autowired
    EncurtadorService service;

    @PostMapping
    public Encurtador save(@RequestBody @Valid EncurtadorDto dto){
        return service.save(dto);
    }

    @GetMapping(path = "/{url}")
    public Encurtador findByURL(@PathVariable("url") String url){
        return service.findByEncodedURL(url);
    }

}
