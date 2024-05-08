
package com.microservicios.reservas.servicios;

import com.microservicios.reservas.dto.HabitacionDTO;
import com.microservicios.reservas.model.Habitacion;
import com.microservicios.reservas.repositorios.IHabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HabitacionService {
    @Autowired
    private IHabitacionRepository habitacionRepository;

    public HabitacionService(IHabitacionRepository habitacionRepository) {
        this.habitacionRepository = habitacionRepository;
    }


    public String crearHabitacion(HabitacionDTO habitacionDTO) {
        Habitacion habitacion = new Habitacion();
        habitacion.setHotel(habitacionDTO.getHotel());
        habitacion.setTipo(habitacionDTO.getTipo());
        habitacion.setNumero_habitacion(habitacionDTO.getNumero_habitacion());
        habitacion.setDisponible(habitacionDTO.isDisponible());
        habitacion.setPrecio(habitacionDTO.getPrecio());
        this.habitacionRepository.save(habitacion);
        return "Habitacion creada";

    }

    public String actualizarHabitacion(HabitacionDTO habitacionDTO) {
        Habitacion habitacion = habitacionRepository.findById(habitacionDTO.getId());
        if (habitacion != null) {
            habitacion.setHotel(habitacionDTO.getHotel());
            habitacion.setTipo(habitacionDTO.getTipo());
            habitacion.setDisponible(habitacionDTO.isDisponible());
            habitacion.setPrecio(habitacionDTO.getPrecio());
            habitacion.setNumero_habitacion(habitacionDTO.getNumero_habitacion());
            habitacionRepository.save(habitacion);
            return "Habitacion actualizada";
        } else return "Habitacion no encontrada";
    }

    public String eliminarHabitacion(int id) {
        this.habitacionRepository.delete(this.habitacionRepository.findById(id));
        return "habitacion eliminada";
    }

    public HabitacionDTO obtenerHabitacionPorId(int id) {
        return new HabitacionDTO(this.habitacionRepository.findById(id));
    }

    public Habitacion findById(int habitacionId) {
        return habitacionRepository.findById(habitacionId);
    }
}
