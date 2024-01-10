package com.remedios.matheus.curso.domain.usuario;

public record RegisterDTO(
        String login,
        String password,
        UserRole role
) {
}
