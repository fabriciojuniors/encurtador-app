package com.encurtador.repositories;

import com.encurtador.models.Encurtador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EncurtadorRepository extends JpaRepository<Encurtador, Long> {

    Optional<Encurtador> findByEncodedURL(String encodedURL);

}
