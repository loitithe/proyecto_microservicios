package com.microservicios.usuarios;


import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
public class UsuarioDTO{

    private int usuario_id;
    private String nombre;
    private String correo_electronico;
    private String direccion;
    private String contrasena;

    public UsuarioDTO(Usuario usuario1) {

    }
}
