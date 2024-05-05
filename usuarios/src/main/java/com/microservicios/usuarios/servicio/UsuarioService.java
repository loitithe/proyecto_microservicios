package com.microservicios.usuarios.servicio;


import com.microservicios.usuarios.repositorio.IUserRepository;
import com.microservicios.usuarios.model.Usuario;
import com.microservicios.usuarios.model.UsuarioDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    private final IUserRepository userRepository;

    public UsuarioService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String saveUser(UsuarioDTO usuarioDTO) {
        // Convertir UsuarioDTO a Usuario antes de guardar
        Usuario usuario = new Usuario(usuarioDTO.getNombre(), usuarioDTO.getCorreo_electronico(), usuarioDTO.getDireccion(), usuarioDTO.getContrasena());
        userRepository.save(usuario);
        return "Usuario creado correctamente";
    }

    public String deleteUser(int id) {
        Usuario usuario = userRepository.findById(id).orElse(null);
        if (usuario != null) {
            userRepository.delete(usuario);
            System.out.println();
            return "Usuario eliminado correctamente";
        }
        return "El usuario no se encontro";
    }

    public UsuarioDTO findById(int id) {
        Usuario usuario = userRepository.findById(id).orElse(null);
        if (usuario != null) {
            return new UsuarioDTO(usuario);
        }
        return null;
    }

    public List<UsuarioDTO> findByNombre(String nombre) {
        List<Usuario> usuarios = userRepository.findAllByNombre(nombre);
        List<UsuarioDTO> usuariosDTO = new ArrayList<>();
        if (usuarios != null) {
            for (Usuario user : usuarios) {
                usuariosDTO.add(new UsuarioDTO(user));
            }
            return usuariosDTO;
        }
        return null;
    }

    public List<UsuarioDTO> findAllUsers() {
        List<Usuario> usuarios = userRepository.findAll();
        List<UsuarioDTO> usuariosDTO = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            // Convertir cada Usuario a UsuarioDTO y agregarlo a la lista
            usuariosDTO.add(new UsuarioDTO(usuario));
        }
        return usuariosDTO;
    }

    public Boolean findByNombreAndContrasena(String nombre, String contrasena) {
        Usuario usuario = userRepository.findByNombreAndContrasena(nombre, contrasena);
        if (usuario != null) {
            // Convertir Usuario a UsuarioDTO antes de retornar
            return true;
        }
        return false;
    }

    public UsuarioDTO getByNombreAndContrasena(String nombre, String contrasena) {
        Usuario usuario = userRepository.findByNombreAndContrasena(nombre, contrasena);
        return new UsuarioDTO(usuario);
    }

    public String actualizarUsuario(UsuarioDTO usuarioDTO) {
        Usuario user = userRepository.findById(usuarioDTO.getUsuario_id()).orElse(null);
        if (user != null) {
            user.setNombre(usuarioDTO.getNombre());
            user.setCorreo_electronico(usuarioDTO.getCorreo_electronico());
            user.setDireccion(usuarioDTO.getDireccion());
            user.setContrasena(usuarioDTO.getContrasena());
            userRepository.save(user);
            return "Usuario actualizado correctamente";
        } else return "Error: El usuario no existe";
    }

    public Boolean checkIfExists(int id) {
        Usuario usuario = userRepository.findById(id).orElse(null);
        if (usuario != null) {
            return true;
        } else
            return false;
    }
}
