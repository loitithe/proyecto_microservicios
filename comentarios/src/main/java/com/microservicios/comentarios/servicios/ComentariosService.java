package com.microservicios.comentarios.servicios;

import com.microservicios.comentarios.dto.CrearComentarioDTO;
import com.microservicios.comentarios.entidades.Comentario;
import com.microservicios.comentarios.repositorios.IComentariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ComentariosService {
    @Autowired
    private final IComentariosRepository iComentariosRepository;
    @Autowired
    private RestTemplate restTemplate;


    public ComentariosService(IComentariosRepository iComentariosRepository) {
        this.iComentariosRepository = iComentariosRepository;
    }

    public int obtenerIdUsuario(String nombre) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8702/usuarios/info/nombre/?nombre=" + nombre;
        try {
            //  GET al UsuarioService: respuesta como Integer
            ResponseEntity<Integer> response = restTemplate.exchange(url, HttpMethod.GET, null, Integer.class);
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

    public boolean existeComentario(int reserva_id, int usuario_id, int hotel_id) {
        String url = String.format("http://localhost:8701/reservas/check?idUsuario=%d&idReserva=%d&idHotel=%d");
        return restTemplate.postForObject(url, null, Boolean.class);
    }

    private int obtenerIdHotel(String nombreHotel) {
        String url = "http://localhost:8702/hotel/id?nombre=" + nombreHotel;
        return restTemplate.postForObject(url, null, Integer.class);
    }

    public CrearComentarioDTO crearComentario(CrearComentarioDTO comentarioDTO) {
        int idHotel = obtenerIdHotel(comentarioDTO.getNombreHotel());
        int idUsuario = obtenerIdUsuario(comentarioDTO.getNombreUsuario());
        if (existeComentario(comentarioDTO.getReservaId(), idUsuario, idHotel)){
            Comentario comentario = new Comentario();
            comentario.setComentario(comentario.getComentario());
            comentario.setHotelId(comentario.getHotelId());
            comentario.setUsuarioId(comentario.getUsuarioId());
            comentario.set
            iComentariosRepository.save(comentario);
        }
    }

//    private String obtenerIdUsuario(String nombreUsuario) {
//        String url = "http://ruta-del-microservicio-usuario/obtenerIdUsuario?nombreUsuario=" + nombreUsuario;
//        return restTemplate.getForObject(url, String.class);
//    }
//
//    private boolean checkReserva(String idUsuario, String idHotel, String idReserva) {
//        String url = "http://ruta-del-microservicio-reserva/checkReserva?idUsuario=" + idUsuario +
//                "&idHotel=" + idHotel + "&idReserva=" + idReserva;
//        return restTemplate.getForObject(url, Boolean.class);
//    }
}
