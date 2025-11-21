package com.tecsup.auth.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(name = "nombre_usuario")
    private String nombreUsuario;

    // Mantenemos la corrección del tipo de dato y eliminamos asignaciones manuales externas
      private String estado;

    // Quitamos insertable=false para que JPA pueda guardar el valor generado por Java
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    // Este método se ejecuta automáticamente ANTES de insertar en la BD
    @PrePersist
    public void prePersist() {
        this.fechaCreacion = LocalDateTime.now(); // Fecha actual
        this.estado = "A";                        // Estado siempre Activo
    }
}