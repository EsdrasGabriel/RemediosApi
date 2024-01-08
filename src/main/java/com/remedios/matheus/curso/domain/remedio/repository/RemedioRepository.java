package com.remedios.matheus.curso.domain.remedio.repository;

import com.remedios.matheus.curso.domain.remedio.dto.DadosListagemRemedioDTO;
import com.remedios.matheus.curso.domain.remedio.entity.Remedio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RemedioRepository extends JpaRepository<Remedio, Long> {
    List<Remedio> findAllByAtivoTrue();
}
