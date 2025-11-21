package com.tecsup.auth.service;

import com.tecsup.auth.dto.UsuarioDTO;
import com.tecsup.auth.entity.Usuario;
import com.tecsup.auth.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; // Importante
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Inyectamos el encriptador

    // AUTENTICAR: Modificado para comparar hashes
    public Optional<Usuario> autenticar(String login, String password) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByLogin(login);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            // Comparamos el password plano con el hash de la BD
            if (passwordEncoder.matches(password, usuario.getPassword())) {
                return Optional.of(usuario);
            }
        }
        return Optional.empty();
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> obtenerPorId(Integer id) {
        return usuarioRepository.findById(id);
    }

    // GUARDAR: Modificado para encriptar
    public Usuario guardar(UsuarioDTO dto) {
        if (usuarioRepository.existsByLogin(dto.getLogin())) {
            return null;
        }

        Usuario usuario = new Usuario();
        usuario.setLogin(dto.getLogin());
        
        // ENCRIPTAMOS EL PASSWORD AQUÍ
        String passwordEncriptado = passwordEncoder.encode(dto.getPassword());
        usuario.setPassword(passwordEncriptado);
        
        usuario.setNombreUsuario(dto.getNombreUsuario());
        
        return usuarioRepository.save(usuario);
    }

    // ACTUALIZAR: Modificado para encriptar si cambia el password
    public Usuario actualizar(Integer id, UsuarioDTO dto) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            usuario.setLogin(dto.getLogin());
            
            // Volvemos a encriptar al actualizar
            String passwordEncriptado = passwordEncoder.encode(dto.getPassword());
            usuario.setPassword(passwordEncriptado);
            
            usuario.setNombreUsuario(dto.getNombreUsuario());
            // usuario.setEstado(dto.getEstado()); // Opcional
            return usuarioRepository.save(usuario);
        }
        
        return null;
    }

    public boolean eliminar(Integer id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}