package com.microservicios.reservas.repositories;

import com.microservicios.reservas.models.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReservaRepository  extends JpaRepository<Reserva,Integer> {
    List<Reserva> findByUsuario(int usuario);

    List<Reserva> findByEstado(String estado);

    Reserva findById(int reserva_id);
}
