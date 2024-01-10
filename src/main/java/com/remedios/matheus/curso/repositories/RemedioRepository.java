package com.remedios.matheus.curso.repositories;

import com.remedios.matheus.curso.domain.remedio.Remedio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RemedioRepository extends JpaRepository<Remedio, Long> {
    List<Remedio> findAllByAtivoTrue();
}
