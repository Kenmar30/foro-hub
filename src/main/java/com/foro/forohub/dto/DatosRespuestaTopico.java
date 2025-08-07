package com.foro.forohub.dto;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        String fechaCreacion,
        String nombreAutor,
        String nombreCurso
) {}

