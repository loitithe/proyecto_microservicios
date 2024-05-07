package com.microservicios.comentarios.servicios;

import com.microservicios.comentarios.repositorios.IComentariosRepository;

public class ComentariosService {
    private final IComentariosRepository iComentariosRepository;

    public ComentariosService(IComentariosRepository iComentariosRepository) {
        this.iComentariosRepository = iComentariosRepository;
    }


}
