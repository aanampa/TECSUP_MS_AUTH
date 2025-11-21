package com.tecsup.auth.dto;

import lombok.Data;

@Data
public class UsuarioDTO {
    private String login;
    private String password;
    private String nombreUsuario;
    private String estado;
}