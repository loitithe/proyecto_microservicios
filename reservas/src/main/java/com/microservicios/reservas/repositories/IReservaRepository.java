package com.microservicios.reservas.repositories;

import com.microservicios.reservas.models.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReservaRepository  extends JpaRepository<Reserva,Integer> {
    List<Reserva> findByUsuario(String usuario);

    List<Reserva> findByEstado(String estado);

    Reserva findByIdAndUsuarioAndHabitacion_Hotel_Id(int idReserva, int idUsuario, int idHotel);
}
