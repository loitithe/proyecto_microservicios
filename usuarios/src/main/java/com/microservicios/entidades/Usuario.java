package com.microservicios.entidades;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NonNull
    @Column(length = 10)
    private String nombre;

    @NonNull
    @Column(length = 80)
    private String correo_electronico;

    @NonNull
    @Column(length = 80)
    private String direccion;

    @NonNull
    @Column(length = 100)
    private String contrasena;

    public Usuario(int id, String nombre, String correo_electronico, String direccion, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.correo_electronico = correo_electronico;
        this.direccion = direccion;
        this.contrasena = contrasena;
    }



}
