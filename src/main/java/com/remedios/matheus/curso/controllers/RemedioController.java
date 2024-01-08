package com.remedios.matheus.curso.controllers;

import aj.org.objectweb.asm.Type;
import com.remedios.matheus.curso.domain.remedio.dto.DadosAtualizarRemedioDTO;
import com.remedios.matheus.curso.domain.remedio.dto.DadosCadastroRemedioDTO;
import com.remedios.matheus.curso.domain.remedio.dto.DadosListagemRemedioDTO;
import com.remedios.matheus.curso.domain.remedio.entity.Remedio;
import com.remedios.matheus.curso.domain.remedio.repository.RemedioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/remedios")
public class RemedioController {
    @Autowired
    private RemedioRepository remedioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosListagemRemedioDTO> save(@RequestBody @Valid DadosCadastroRemedioDTO dados, UriComponentsBuilder uriBuilder) {
        Remedio saved = remedioRepository.save(new Remedio(dados));
        URI uri = uriBuilder.path("/remedios/{id}").buildAndExpand(saved.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosListagemRemedioDTO(saved));
    }

    @GetMapping
    public List<DadosListagemRemedioDTO> find() {
        return remedioRepository
                .findAllByAtivoTrue()
                .stream()
                .map(DadosListagemRemedioDTO::new)
                .toList();
    }

    @GetMapping("{id}")
    public DadosListagemRemedioDTO findById(@PathVariable Long id) {
        return remedioRepository
                .findById(id)
                .map(dado -> new DadosListagemRemedioDTO(
                        dado.getId(),
                        dado.getNome(),
                        dado.getVia(),
                        dado.getLote(),
                        dado.getLaboratorio(),
                        dado.getValidade()
                        )
                ).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Nenhum registro encontrado"));

    }

    @PutMapping("{id}")
    @Transactional
    @ResponseStatus(NO_CONTENT)
    public void updateById(@PathVariable Long id, @RequestBody DadosAtualizarRemedioDTO remedio) {
        Remedio remedioAtt = remedioRepository.getReferenceById(id);
        Optional.ofNullable(remedio.nome()).ifPresent(remedioAtt::setNome);
        Optional.ofNullable(remedio.via()).ifPresent(remedioAtt::setVia);
        Optional.ofNullable(remedio.laboratorio()).ifPresent(remedioAtt::setLaboratorio);
        remedioRepository.save(remedioAtt);
    }

    @DeleteMapping("{id}")
    @Transactional
    @ResponseStatus(NO_CONTENT)
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
    @ResponseStatus(NO_CONTENT)
    public void inativar(@PathVariable Long id) {
        Remedio remedio = remedioRepository.getReferenceById(id);
        remedio.setAtivo(false);
    }

    @PutMapping("reativar/{id}")
    @Transactional
    @ResponseStatus(NO_CONTENT)
    public void reativar(@PathVariable Long id) {
        Remedio remedio = remedioRepository.getReferenceById(id);
        remedio.setAtivo(true);
    }
}
