package com.microservicios.DTO;


import com.microservicios.entidades.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UsuarioDTO implements Serializable {

    private int usuario_id;
    private String nombre;
    private String correo_electronico;
    private String direccion;
    private String contrasena;

  //  public UsuarioDTO(Usuario usuario) {
//        this.usuario_id = usuario.getUsuario_id();
//        this.nombre = usuario.getNombre();
//        this.correo_electronico = usuario.getCorreo_electronico();
//        this.direccion = usuario.getDireccion();
//        this.contrasena = usuario.getContrasena();
//    }
}
