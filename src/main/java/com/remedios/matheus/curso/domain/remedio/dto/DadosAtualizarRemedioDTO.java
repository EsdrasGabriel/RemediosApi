package com.remedios.matheus.curso.domain.remedio.dto;

import com.remedios.matheus.curso.domain.remedio.enums.Laboratorio;
import com.remedios.matheus.curso.domain.remedio.enums.Via;

public record DadosAtualizarRemedioDTO(
        Long id,
        String nome,
        Via via,
        Laboratorio laboratorio
) {
}
