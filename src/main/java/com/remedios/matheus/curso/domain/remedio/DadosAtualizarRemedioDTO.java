package com.remedios.matheus.curso.domain.remedio;

public record DadosAtualizarRemedioDTO(
        Long id,
        String nome,
        Via via,
        Laboratorio laboratorio
) {
}
