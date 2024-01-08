package com.remedios.matheus.curso.infra.exception;

import lombok.Data;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Data
@Getter
public class FieldsWithError {
    private List<String> errors;
    public FieldsWithError(List<String> errors) {
        this.errors = errors;
    }
}
