package com.microservicios.comentarios.controladores;

import com.microservicios.comentarios.servicios.ComentariosService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comentarios")
public class ComentariosController {
    private final ComentariosService comentariosService;

    public ComentariosController(ComentariosService comentariosService) {
        this.comentariosService = comentariosService;
    }


    //Crear comentario (crearComentario):

    //Eliminar todos los comentarios (eliminarComentarios):
    //Elimina un comentario de un usuario (eliminarComentarioDeUsuario):
    //Listará todos los comentarios de las reservas de un determinado hotel (listarComentariosHotel):
    //Listará todos los comentarios de las reservas de un determinado usuario (listarComentariosUsuario):
    //Mostrar comentario de un usuario en una reserva (mostrarComentarioUsuarioReserva):
    //Puntuación media de un hotel (puntuacionMediaHotel):
//Puntuación media de un usuario (puntuacionesMediasUsuario):

}
