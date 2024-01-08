package com.remedios.matheus.curso.domain.remedio.dto;

import com.remedios.matheus.curso.domain.remedio.enums.Laboratorio;
import com.remedios.matheus.curso.domain.remedio.enums.Via;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosCadastroRemedioDTO(
        @NotBlank(message = "Campo nome é obrigatório")
        String nome,
        @NotNull(message = "Campo via é obrigatório")
        @Enumerated()
        Via via,
        @NotBlank(message = "Campo lote é obrigatório")
        String lote,
        @NotNull(message = "Campo quantidade é obrigatório")
        Integer quantidade,
        @NotNull(message = "Campo validade é obrigatório")
        @Future
        LocalDate validade,
        @NotNull(message = "Campo laboratório é obrigatório")
        @Enumerated
        Laboratorio laboratorio
) {

}
