package com.remedios.matheus.curso.domain.remedio.dto;

import com.remedios.matheus.curso.domain.remedio.entity.Remedio;
import com.remedios.matheus.curso.domain.remedio.enums.Laboratorio;
import com.remedios.matheus.curso.domain.remedio.enums.Via;

import java.time.LocalDate;

public record DadosListagemRemedioDTO(
        Long id,
        String nome,
        Via via,
        String lote,
        Laboratorio laboratorio,
        LocalDate validade
) {
    public DadosListagemRemedioDTO(Remedio remedio) {
        this(
            remedio.getId(),
            remedio.getNome(),
            remedio.getVia(),
            remedio.getLote(),
            remedio.getLaboratorio(),
            remedio.getValidade()
        );
    }
}
