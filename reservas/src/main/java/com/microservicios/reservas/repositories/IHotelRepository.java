package com.microservicios.reservas.repositorios;

import com.microservicios.reservas.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IHotelRepository extends JpaRepository<Hotel,Integer> {

    Hotel findByNombre(String nombre);
}
