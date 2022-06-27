package com.encurtador.services;

import com.encurtador.models.Encurtador;
import com.encurtador.models.dto.EncurtadorDto;
import com.encurtador.repositories.EncurtadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class EncurtadorService {

    @Autowired
    EncurtadorRepository repository;

    public Encurtador save(EncurtadorDto dto) throws IOException {
        Encurtador encurtador = Encurtador.Builder.fromDto(dto);
        return repository.save(encurtador);
    }

    public Encurtador findByEncodedURL(String encodedURL){
        Encurtador encurtador = repository.findByEncodedURL(encodedURL).orElseThrow(() -> new RuntimeException("URL não encontrada"));

        encurtador.incrementViews();
        repository.save(encurtador);

        return encurtador;
    }

    public List<Encurtador> findByIds(List<Long> ids){
        return repository.findAllById(ids);
    }

}
