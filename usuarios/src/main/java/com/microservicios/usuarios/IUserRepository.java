package com.microservicios.usuarios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByNombre(String nombre);

}
