package com.microservicios.comentarios.repositorios;

import com.microservicios.comentarios.entidades.Comentario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IComentariosRepository extends MongoRepository<Comentario,String> {
    Comentario findByNombreHotelAndIdReservaAndIdUsuario(String nombreHotel,String idReserva,String idUsuario);
}
