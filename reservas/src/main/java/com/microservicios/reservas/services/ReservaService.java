package com.microservicios.reservas.servicios;

import com.microservicios.reservas.dto.CrearReservaDTO;
import com.microservicios.reservas.dto.ReservaDTO;
import com.microservicios.reservas.model.Reserva;
import com.microservicios.reservas.repositorios.IReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservaService {

    @Autowired
    private final IReservaRepository reservaRepository;
    @Autowired
    private final HabitacionService habitacionService;

    @Autowired
    public ReservaService(IReservaRepository reservaRepository, HabitacionService habitacionService) {
        this.reservaRepository = reservaRepository;
        this.habitacionService = habitacionService;
    }

    public boolean comprobarContrasena(String nombre,String contrasena){
        return  true;
    }

    public String crearReserva(CrearReservaDTO reservaDTO) {
        try {
            // Verificar si la habitación existe
            Reserva reserva = new Reserva();
            // Ejemplo de fecha en formato String

            // Definir el formato esperado de la fecha
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            // Convertir el String a LocalDate
            String fechaInicio = reservaDTO.getFecha_inicio();
            String fechaFin = reservaDTO.getFecha_fin();
            LocalDate fecha = LocalDate.parse(fechaInicio, formatter);
            reserva.setFecha_inicio(fecha);
            fecha = LocalDate.parse(fechaFin, formatter);
            reserva.setFecha_fin(fecha);
            reserva.setHabitacion(habitacionService.findById(reservaDTO.getHabitacion_id()));
            reservaRepository.save(reserva);
            return "Reserva creada correctamente";
        } catch (Exception e) {
            return "Error al crear la reserva: " + e.getMessage();
        }
    }

    public String cambiarEstadoReserva(ReservaDTO cambioEstadoReservaDTO) {
        try {
            Reserva reserva = reservaRepository.findById(cambioEstadoReservaDTO.getReservaId()).orElse(null);
            if (reserva != null) {
                reserva.setEstado(cambioEstadoReservaDTO.getEstado());
                reservaRepository.save(reserva);
                return "Estado de la reserva cambiado" + cambioEstadoReservaDTO.getEstado();
            } else return "La reserva con ID " + cambioEstadoReservaDTO.getReservaId() + "no se encontro";
        } catch (Exception e) {
            return "Error al cambiar el estado de la reserva" + e.getMessage();
        }
    }

    public List<ReservaDTO> listarReservasUsuario(String usuario, String contraseña) {
        List<Reserva> reservas = reservaRepository.findByUsuario(usuario);
        List<ReservaDTO> reservasDTO = new ArrayList<>();
        for (Reserva reserva : reservas) {
            reservasDTO.add(new ReservaDTO(reserva));
        }
        return reservasDTO;
    }

    public List<ReservaDTO> listarReservasSegunEstado(String estado) {
        List<Reserva> reservas = reservaRepository.findByEstado(estado);
        List<ReservaDTO> reservasDTO = new ArrayList<>();
        for (Reserva reserva : reservas) {
            reservasDTO.add(new ReservaDTO(reserva));
        }
        return reservasDTO;
    }

    public boolean checkReserva(int idUsuario, int idHotel, int idReserva) {
        Reserva reserva = reservaRepository.findByIdAndUsuarioAndHabitacion_Hotel_Id(idReserva, idUsuario, idHotel);
        if (reserva != null) return true;
        else return false;
    }
}
