package com.microservicios.reservas.repositorios;

import com.microservicios.reservas.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReservaRepository  extends JpaRepository<Reserva,Integer> {
}
