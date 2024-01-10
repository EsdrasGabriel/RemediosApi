package com.remedios.matheus.curso.repositories;

import com.remedios.matheus.curso.domain.usuario.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, String> {
    UserDetails findByLogin(String login);
}
