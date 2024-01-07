package com.remedios.matheus.curso.controllers;

import aj.org.objectweb.asm.Type;
import com.remedios.matheus.curso.domain.remedio.dto.DadosAtualizarRemedioDTO;
import com.remedios.matheus.curso.domain.remedio.dto.DadosCadastroRemedioDTO;
import com.remedios.matheus.curso.domain.remedio.dto.DadosListagemRemedioDTO;
import com.remedios.matheus.curso.domain.remedio.entity.Remedio;
import com.remedios.matheus.curso.domain.remedio.repository.RemedioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/remedios")
public class RemedioController {
    @Autowired
    private RemedioRepository remedioRepository;

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public Long save(@RequestBody @Valid DadosCadastroRemedioDTO dados) {
        Remedio saved = remedioRepository.save(new Remedio(dados));
        return saved.getId();
    }

    @GetMapping
    public List<DadosListagemRemedioDTO> find() {
        return remedioRepository
                .findAllByAtivoTrue()
                .stream()
                .map(DadosListagemRemedioDTO::new)
                .toList();
    }

    @PutMapping("{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateById(@PathVariable @Valid Long id, @RequestBody DadosAtualizarRemedioDTO remedio) {
        Remedio remedioAtt = remedioRepository.getReferenceById(id);
        remedioAtt.atualizarInformacoes(remedio);
    }

    @DeleteMapping("{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        remedioRepository
                .findById(id)
                .map(dado -> {
                    remedioRepository.deleteById(dado.getId());
                    return Type.VOID;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum registro foi encontrado."));
    }

    @DeleteMapping("inativar/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@PathVariable Long id) {
        Remedio remedio = remedioRepository.getReferenceById(id);
        remedio.setAtivo(false);
    }

    @PutMapping("reativar/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void reativar(@PathVariable Long id) {
        Remedio remedio = remedioRepository.getReferenceById(id);
        remedio.setAtivo(true);
    }
}
