package com.foro.forohub.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private StatusTopico status = StatusTopico.NO_RESPONDIDO;

    @ManyToOne
    private Usuario autor;

    @ManyToOne
    private Curso curso;

    // Constructores
    public Topico() {}

    public Topico(String titulo, String mensaje, Usuario autor, Curso curso) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.autor = autor;
        this.curso = curso;
        this.fechaCreacion = LocalDateTime.now();
        this.status = StatusTopico.NO_RESPONDIDO;
    }


    // Getters
    public Long getId() { return id; }

    public String getTitulo() { return titulo; }

    public String getMensaje() { return mensaje; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }

    public StatusTopico getStatus() { return status; }

    public Usuario getAutor() { return autor; }

    public Curso getCurso() { return curso; }

    // Setters
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public void setStatus(StatusTopico status) { this.status = status; }
}
