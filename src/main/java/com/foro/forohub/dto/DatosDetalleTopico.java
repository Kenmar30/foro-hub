package com.foro.forohub.dto;

import com.foro.forohub.model.Topico;

public record DatosDetalleTopico(
        Long id,
        String titulo,
        String mensaje,
        String fechaCreacion,
        String status,
        String autor,
        String curso
) {
    public static DatosDetalleTopico desde(Topico topico) {
        return new DatosDetalleTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion().toString(),
                topico.getStatus().toString(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre()
        );
    }
}

