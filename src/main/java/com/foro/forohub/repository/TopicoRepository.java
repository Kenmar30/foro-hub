package com.foro.forohub.repository;

import com.foro.forohub.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    // Filtro por nombre del curso (ignora mayúsculas/minúsculas)
    Page<Topico> findByCursoNombreIgnoreCase(String nombre, Pageable pageable);

    // Filtro por año de creación usando JPQL
    @Query("SELECT t FROM Topico t WHERE YEAR(t.fechaCreacion) = :anio")
    Page<Topico> findByAnioCreacion(@Param("anio") int anio, Pageable pageable);
}


