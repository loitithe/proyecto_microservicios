package com.microservicios.comentarios.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrearComentarioDTO {

    private String nombreUsuario;
   private String nombreHotel;
   private int reservaId;
   private double puntuacion;
   private String comentario;
}
