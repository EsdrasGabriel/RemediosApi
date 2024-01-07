package com.remedios.matheus.curso.domain.remedio.dto;

import com.remedios.matheus.curso.domain.remedio.enums.Laboratorio;
import com.remedios.matheus.curso.domain.remedio.enums.Via;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarRemedioDTO(
        @NotNull
        Long id,
        String nome,
        Via via,
        Laboratorio laboratorio
) {
}
