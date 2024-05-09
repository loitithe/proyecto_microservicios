package com.microservicios.reservas.repositories;

import com.microservicios.reservas.models.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHabitacionRepository extends JpaRepository<Habitacion,Integer> {
    Habitacion findById(int id);
}
