package com.microservicios.servicios;


import com.microservicios.entidades.Usuario;
import com.microservicios.repositorios.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final List<Usuario>usuarios = new ArrayList<>();


    @Autowired
    private IUserRepository userRepository;

    public UsuarioService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String saveUser(Usuario usuario){
        userRepository.save(usuario);
        return "Usuario creado correctamente";
    }

}
