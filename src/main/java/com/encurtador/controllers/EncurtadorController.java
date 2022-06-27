package com.encurtador.controllers;

import com.encurtador.models.Encurtador;
import com.encurtador.models.dto.EncurtadorDto;
import com.encurtador.services.EncurtadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/")
public class EncurtadorController {

    @Autowired
    EncurtadorService service;

    @PostMapping
    public Encurtador save(@RequestBody @Valid EncurtadorDto dto) throws IOException {
        return service.save(dto);
    }

    @PostMapping(path = "/filter")
    public Encurtador findByURL(@RequestBody EncurtadorDto dto){
        return service.findByEncodedURL(dto.getUrl());
    }

    @GetMapping
    public String teste(){
        return "Ola";
    }

}
