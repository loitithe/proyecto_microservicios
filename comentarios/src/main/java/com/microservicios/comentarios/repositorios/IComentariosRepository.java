package com.microservicios.comentarios.repositorios;

import com.microservicios.comentarios.entidades.Comentario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IComentariosRepository extends MongoRepository<Comentario,String> {

}
