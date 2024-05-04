package com.microservicios.repositorios;

import com.microservicios.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<Usuario, Integer> {

    Usuario findbyNombre(String nombre);

}
