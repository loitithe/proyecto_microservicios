package com.microservicios.reservas.repositorios;

import com.microservicios.reservas.model.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHabitacionRepository extends JpaRepository<Habitacion,Integer> {
    Habitacion findById(int id);
}
