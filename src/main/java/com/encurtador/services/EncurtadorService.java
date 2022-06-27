package com.encurtador.services;

import com.encurtador.models.Encurtador;
import com.encurtador.models.dto.EncurtadorDto;
import com.encurtador.repositories.EncurtadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EncurtadorService {

    @Autowired
    EncurtadorRepository repository;

    public Encurtador save(EncurtadorDto dto){
        Encurtador encurtador = Encurtador.Builder.fromDto(dto);
        return repository.save(encurtador);
    }

    public Encurtador findByEncodedURL(String encodedURL){
        return repository.findByEncodedURL(encodedURL).orElseThrow(() -> new RuntimeException("URL não encontrada"));
    }

}
