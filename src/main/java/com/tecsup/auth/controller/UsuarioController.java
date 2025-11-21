package com.tecsup.auth.controller;

import com.tecsup.auth.dto.LoginRequestDTO;
import com.tecsup.auth.dto.UsuarioDTO;
import com.tecsup.auth.entity.Usuario;
import com.tecsup.auth.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // LOGIN: Refactorizado sin .orElse
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {
        Optional<Usuario> usuarioOpt = usuarioService.autenticar(loginRequest.getLogin(), loginRequest.getPassword());
        
        if (usuarioOpt.isPresent()) {
            return ResponseEntity.ok().body(usuarioOpt.get());
        } else {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }
    }

    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.listar();
    }

    // GET ID: Refactorizado sin .orElse
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtener(@PathVariable Integer id) {
        Optional<Usuario> usuarioOpt = usuarioService.obtenerPorId(id);
        
        if (usuarioOpt.isPresent()) {
            return ResponseEntity.ok(usuarioOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Usuario crear(@RequestBody UsuarioDTO usuarioDto) {
        return usuarioService.guardar(usuarioDto);
    }

    // UPDATE: Validamos si el servicio devolvió null
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable Integer id, @RequestBody UsuarioDTO usuarioDto) {
        Usuario actualizado = usuarioService.actualizar(id, usuarioDto);
        
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        boolean eliminado = usuarioService.eliminar(id);
        
        if (eliminado) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}