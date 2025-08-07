package com.foro.forohub.repository;

import com.foro.forohub.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    Optional<Curso> findByNombreIgnoreCase(String nombre);
}


