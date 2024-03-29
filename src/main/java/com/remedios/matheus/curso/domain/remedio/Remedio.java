package com.remedios.matheus.curso.domain.remedio;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Remedio {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String nome;
    @Column @Enumerated(EnumType.STRING)
    private Via via;
    @Column
    private String lote;
    @Column
    private int quantidade;
    @Column
    private LocalDate validade;
    @Column @Enumerated(EnumType.STRING)
    private Laboratorio laboratorio;
    @Column
    private boolean ativo;

    public Remedio(DadosCadastroRemedioDTO dados) {
        this.nome = dados.nome();
        this.via = dados.via();
        this.lote = dados.lote();
        this.quantidade = dados.quantidade();
        this.validade = dados.validade();
        this.laboratorio = dados.laboratorio();
        this.ativo = true;
    }
}
