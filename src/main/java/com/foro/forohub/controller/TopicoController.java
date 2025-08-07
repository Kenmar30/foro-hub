package com.foro.forohub.controller;

import com.foro.forohub.dto.DatosActualizarTopico;
import com.foro.forohub.dto.DatosDetalleTopico;
import com.foro.forohub.dto.DatosRegistroTopico;
import com.foro.forohub.dto.DatosRespuestaTopico;
import com.foro.forohub.model.Topico;
import com.foro.forohub.model.Usuario;
import com.foro.forohub.model.Curso;
import com.foro.forohub.repository.TopicoRepository;
import com.foro.forohub.repository.UsuarioRepository;
import com.foro.forohub.repository.CursoRepository;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    // GET - Listar todos los tópicos con paginación y orden por fecha ASC
    @GetMapping
    public Page<DatosRespuestaTopico> listar(
            @RequestParam(required = false) String curso,
            @RequestParam(required = false) Integer anio,
            @PageableDefault(size = 10, sort = "fechaCreacion") Pageable paginacion
    ) {
        Page<Topico> pagina;

        if (curso != null) {
            pagina = repository.findByCursoNombreIgnoreCase(curso, paginacion);
        } else if (anio != null) {
            pagina = repository.findByAnioCreacion(anio, paginacion);
        } else {
            pagina = repository.findAll(paginacion);
        }

        return pagina.map(topico ->
                new DatosRespuestaTopico(
                        topico.getId(),
                        topico.getTitulo(),
                        topico.getMensaje(),
                        topico.getFechaCreacion().toString(),
                        topico.getAutor().getNombre(),
                        topico.getCurso() != null ? topico.getCurso().getNombre() : null
                )
        );
    }


    // GET - Filtrar por nombre del curso
    @GetMapping("/filtrar")
    public ResponseEntity<Page<DatosRespuestaTopico>> listarPorCurso(
            @RequestParam String nombreCurso,
            @PageableDefault(size = 10, sort = "fechaCreacion") Pageable paginacion) {

        return ResponseEntity.ok(repository.findByCursoNombreIgnoreCase(nombreCurso, paginacion).map(topico ->
                new DatosRespuestaTopico(
                        topico.getId(),
                        topico.getTitulo(),
                        topico.getMensaje(),
                        topico.getFechaCreacion().toString(),
                        topico.getAutor().getNombre(),
                        topico.getCurso().getNombre()
                )
        ));
    }

    // GET - Filtrar por año de creación
    @GetMapping("/anio")
    public ResponseEntity<Page<DatosRespuestaTopico>> listarPorAnio(
            @RequestParam int anio,
            @PageableDefault(size = 10, sort = "fechaCreacion") Pageable paginacion) {

        return ResponseEntity.ok(repository.findByAnioCreacion(anio, paginacion).map(topico ->
                new DatosRespuestaTopico(
                        topico.getId(),
                        topico.getTitulo(),
                        topico.getMensaje(),
                        topico.getFechaCreacion().toString(),
                        topico.getAutor().getNombre(),
                        topico.getCurso().getNombre()
                )
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleTopico> obtener(@PathVariable Long id) {
        return repository.findById(id)
                .map(topico -> ResponseEntity.ok(DatosDetalleTopico.desde(topico)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }






    // POST - Crear un nuevo tópico
    @PostMapping
    @Transactional
    public ResponseEntity<?> crear(@RequestBody DatosRegistroTopico datos) {

        var cursoOptional = cursoRepository.findByNombreIgnoreCase(datos.nombreCurso());
        if (cursoOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Error: El curso '" + datos.nombreCurso() + "' no existe.");
        }

        var autorOptional = usuarioRepository.findById(datos.autorId());
        if (autorOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Error: No existe un usuario con ID " + datos.autorId());
        }

        var curso = cursoOptional.get();
        var autor = autorOptional.get();

        Topico topico = new Topico(datos.titulo(), datos.mensaje(), autor, curso);
        repository.save(topico);

        DatosRespuestaTopico dto = new DatosRespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion().toString(),
                autor.getNombre(),
                curso.getNombre()
        );

        URI url = URI.create("/topicos/" + topico.getId());
        return ResponseEntity.created(url).body(dto);

    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @RequestBody @Valid DatosActualizarTopico datos) {
        var topicoOptional = repository.findById(id);

        if (topicoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var topico = topicoOptional.get();

        topico.setTitulo(datos.titulo());
        topico.setMensaje(datos.mensaje());



        DatosDetalleTopico dto = new DatosDetalleTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion().toString(),
                topico.getStatus().toString(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre()
        );

        return ResponseEntity.ok(dto);
    }




    // DELETE - Eliminar un tópico
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        var topicoOptional = repository.findById(id);

        if (topicoOptional.isEmpty()) {
            return ResponseEntity.notFound().build(); // 404 si no existe
        }

        repository.deleteById(id);
        return ResponseEntity.noContent().build(); // 204 si fue eliminado
    }



}


