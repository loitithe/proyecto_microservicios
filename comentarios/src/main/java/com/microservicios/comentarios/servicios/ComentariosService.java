package com.microservicios.comentarios.servicios;

import com.microservicios.comentarios.repositorios.IComentariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    private String obtenerIdHotel(String nombreHotel) {
        String url = "http://localhost:8702/hoteles/id?nombre=" + nombreHotel;
        return restTemplate.postForObject(url, null, String.class);
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
