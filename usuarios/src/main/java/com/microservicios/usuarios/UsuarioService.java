package com.microservicios.usuarios;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    private final List<UsuarioDTO> usuarios = new ArrayList<>();
    private final IUserRepository userRepository;

    public UsuarioService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String saveUser(Usuario usuario) {
        userRepository.save(usuario);
        return "Usuario creado correctamente";
    }

    public Usuario deleteUser(Usuario usuario) {
        userRepository.delete(usuario);
        System.out.println("Usuario eliminado correctamente");
        return usuario;
    }

    public Usuario findById(int id) {
        return userRepository.findById(Integer.parseInt(""+id)).orElse(null);
    }
}
