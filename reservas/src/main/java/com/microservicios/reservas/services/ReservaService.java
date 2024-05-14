package com.microservicios.reservas.services;

import com.microservicios.reservas.dto.CrearReservaDTO;
import com.microservicios.reservas.dto.ReservaCambiarEstadoDTO;
import com.microservicios.reservas.dto.ReservaDTO;
import com.microservicios.reservas.models.Hotel;
import com.microservicios.reservas.models.Reserva;
import com.microservicios.reservas.repositories.IHotelRepository;
import com.microservicios.reservas.repositories.IReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private IReservaRepository reservaRepository;


    @Autowired
    private HabitacionService habitacionService;
    @Autowired
    private IHotelRepository iHotelRepository;


    public boolean comprobarContrasena(String nombre, String contrasena) {
        RestTemplate restTemplate = new RestTemplate();
        String urlValidarContrasena = "http://localhost:8702/usuarios/validar";
        // Crear un objeto CrearReservaDTO con los datos proporcionados
        CrearReservaDTO crearReservaDTO = new CrearReservaDTO();
        crearReservaDTO.setNombre(nombre);
        crearReservaDTO.setContrasena(contrasena);

        // Configurar los encabezados de la solicitud
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CrearReservaDTO> request = new HttpEntity<>(crearReservaDTO, headers);

        // Realizar la solicitud POST al servicio de validación de usuarios
        ResponseEntity<Boolean> response = restTemplate.postForEntity(urlValidarContrasena, request, Boolean.class);

        // Verificar la respuesta y devolver el resultado
        if (response.getBody() != null) {
            return response.getBody();
        } else {
            return false;
        }
    }


    public String crearReserva(CrearReservaDTO reservaDTO) {
        // Verificar la contraseña del usuario
        if (comprobarContrasena(reservaDTO.getNombre(), reservaDTO.getContrasena())) {
            try {
                int usuarioId = obtenerIdUsuario(reservaDTO.getNombre());
                Reserva reserva = new Reserva();
                reserva.setUsuario(usuarioId); // Asignar el ID del usuario
                reserva.setFecha_inicio(reservaDTO.getFecha_inicio());
                reserva.setFecha_fin(reservaDTO.getFecha_fin());
                reserva.setHabitacion(habitacionService.findById(reservaDTO.getHabitacion_id()));
                reserva.setEstado(reservaDTO.getEstado());
                reservaRepository.save(reserva);
                return "Reserva creada correctamente";
            } catch (Exception e) {
                return "Error al crear la reserva: " + e.getMessage();
            }
        } else {
            return "Contraseña incorrecta";
        }
    }

    public int obtenerIdUsuario(String nombre) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8702/usuarios/info/nombre/?nombre=" + nombre;

        try {
            // Realizar la solicitud GET al servicio de usuarios y obtener la respuesta como Integer
            ResponseEntity<Integer> response = restTemplate.exchange(url, HttpMethod.GET, null, Integer.class);

            // Verificar si la respuesta es exitosa y no es nula
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                // Obtener el ID del usuario de la respuesta
                int usuarioId = response.getBody();
                System.out.println("ID del usuario obtenido correctamente: " + usuarioId);
                return usuarioId;
            } else {
                System.err.println("Error: Respuesta no exitosa o cuerpo de respuesta nulo");
                return 0; // Otra opción es lanzar una excepción si lo prefieres
            }
        } catch (Exception e) {
            System.err.println("Error al realizar la solicitud al servicio de usuarios: " + e.getMessage());
            return 0; // Otra opción es lanzar una excepción si lo prefieres
        }
    }


    public String cambiarEstadoReserva(ReservaCambiarEstadoDTO cambioEstadoReservaDTO) {
        if (comprobarContrasena(cambioEstadoReservaDTO.getNombre(), cambioEstadoReservaDTO.getContrasena())) {
            try {
                Reserva reserva = reservaRepository.findById(cambioEstadoReservaDTO.getReserva_id());
                if (reserva != null) {
                    reserva.setEstado(cambioEstadoReservaDTO.getEstado());
                    reservaRepository.save(reserva);
                    return "Estado de la reserva cambiado" + cambioEstadoReservaDTO.getEstado();
                } else return "La reserva con ID " + cambioEstadoReservaDTO.getReserva_id() + "no se encontro";
            } catch (Exception e) {
                return "Error al cambiar el estado de la reserva" + e.getMessage();
            }
        }else return "";
    }

        public List<ReservaDTO> listarReserva ( int usuario, String contraseña){
            List<Reserva> reservas = reservaRepository.findByUsuario(usuario);
            List<ReservaDTO> reservasDTO = new ArrayList<>();
            for (Reserva reserva : reservas) {
                reservasDTO.add(new ReservaDTO(reserva));
            }
            return reservasDTO;
        }

        public List<ReservaDTO> listarReservasSegunEstado (String estado){
            List<Reserva> reservas = reservaRepository.findByEstado(estado);
            List<ReservaDTO> reservasDTO = new ArrayList<>();
            for (Reserva reserva : reservas) {
                reservasDTO.add(new ReservaDTO(reserva));
            }
            return reservasDTO;
        }

        public boolean checkReserva ( int idUsuario, int idHotel, int idReserva){
            Reserva reserva = reservaRepository.findById(idReserva);
            Hotel hotel = iHotelRepository.findById(idHotel).orElse(null);
            if (reserva != null && hotel != null) return true;
            else return false;
        }
    }
