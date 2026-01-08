package com.ad03.controller;

import com.ad03.dto.LibroDTO;
import com.ad03.dto.LibroYNumEjemplaresDTO;
import com.ad03.service.LibroService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/libros")
@RequiredArgsConstructor
public class LibroController {

    private final LibroService libroService;

    @GetMapping
    public ResponseEntity<Page<LibroDTO>> listarLibros(
            Pageable pageable,
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {

        if (titulo != null) {
            return ResponseEntity.ok(libroService.buscarPorTituloPaginado(titulo, pageable));
        }

        if (fecha != null) {
            return ResponseEntity.ok(libroService.buscarPorFechaPublicacionPaginado(fecha, pageable));
        }

        return ResponseEntity.ok(libroService.obtenerTodosLibrosPaginados(pageable));
    }

    @GetMapping("/resumen")
    public ResponseEntity<List<LibroYNumEjemplaresDTO>> listarLibrosResumen() {
        return ResponseEntity.ok(libroService.obtenerLibrosConNumeroEjemplares());
    }

    @PostMapping
    public ResponseEntity<LibroDTO> crearLibro(@RequestBody LibroDTO libroDTO) {
        LibroDTO libroCreado = libroService.crearLibro(libroDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(libroCreado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibroDTO> obtenerLibroPorId(@PathVariable Long id) {
        return ResponseEntity.ok(libroService.obtenerLibroPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
        libroService.eliminarLibro(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{libroId}/autor/{autorId}")
    public ResponseEntity<LibroDTO> agregarAutorALibro(
            @PathVariable Long libroId,
            @PathVariable Long autorId) {
        LibroDTO libroActualizado = libroService.agregarAutorALibro(libroId, autorId);
        return ResponseEntity.ok(libroActualizado);
    }

    @DeleteMapping("/{libroId}/autor/{autorId}")
    public ResponseEntity<LibroDTO> quitarAutorDeLibro(
            @PathVariable Long libroId,
            @PathVariable Long autorId) {
        LibroDTO libroActualizado = libroService.quitarAutorDeLibro(libroId, autorId);
        return ResponseEntity.ok(libroActualizado);
    }

    @PostMapping("/{libroId}/ejemplares")
    public ResponseEntity<LibroDTO> agregarEjemplarALibro(
            @PathVariable Long libroId,
            @RequestParam(required = false) String descripcion) {
        LibroDTO libroActualizado = libroService.agregarEjemplarALibro(libroId, descripcion);
        return ResponseEntity.ok(libroActualizado);
    }

    @DeleteMapping("/{libroId}/ejemplares/{ejemplarId}")
    public ResponseEntity<LibroDTO> eliminarEjemplarDeLibro(
            @PathVariable Long libroId,
            @PathVariable Long ejemplarId) {
        LibroDTO libroActualizado = libroService.eliminarEjemplarDeLibro(libroId, ejemplarId);
        return ResponseEntity.ok(libroActualizado);
    }

    @GetMapping("/buscar/titulo-con-ejemplares")
    public ResponseEntity<List<LibroYNumEjemplaresDTO>> buscarPorTituloConEjemplares(@RequestParam String titulo) {
        return ResponseEntity.ok(libroService.buscarPorTituloConEjemplares(titulo));
    }

    @GetMapping("/estadisticas/antiguos")
    public ResponseEntity<Long> contarLibrosAntesDeano(@RequestParam Integer ano) {
        return ResponseEntity.ok(libroService.contarLibrosAntesDeano(ano));
    }

    @GetMapping("/buscar/ano")
    public ResponseEntity<List<LibroYNumEjemplaresDTO>> buscarPorano(@RequestParam Integer ano) {
        return ResponseEntity.ok(libroService.buscarPoranoPublicacion(ano));
    }
}