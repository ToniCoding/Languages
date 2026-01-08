package com.ad02.controller;

import com.ad02.dto.AulaDTO;
import com.ad02.service.AulaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * AulaController - Controlador REST para manejar las operaciones de aulas.
 *
 * <p>Proporciona endpoints para listar, obtener, crear, modificar y eliminar aulas.
 * Permite modificaciones parciales sin sobrescribir campos no enviados.</p>
 */
@RestController
@RequestMapping("/api/aula")
@RequiredArgsConstructor
public class AulaController {

    private final AulaService aulaService;

    /**
     * Lista todas las aulas registradas.
     *
     * Endpoint REST: GET /api/aula
     *
     * @return Lista de aulas.
     */
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<AulaDTO>> listar() {
        return ResponseEntity.ok(aulaService.listarAulas());
    }

    /**
     * Obtiene información de un aula concreta por ID.
     *
     * Endpoint REST: GET /api/aula/{id}
     *
     * @param id ID del aula.
     * @return Información del aula.
     */
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<AulaDTO> obtener(@PathVariable Long id) {
        try {
            AulaDTO aula = aulaService.obtenerAula(id);
            return ResponseEntity.ok(aula);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Crea un aula nueva.
     *
     * Endpoint REST: POST /api/aula
     *
     * @param aulaDTO DTO con los datos del aula a crear.
     * @return Aula creada.
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AulaDTO> insertar(@RequestBody AulaDTO aulaDTO) {
        return ResponseEntity.ok(aulaService.crearAula(aulaDTO));
    }

    /**
     * Modifica parcialmente un aula existente.
     *
     * Endpoint REST: PUT /api/aula/{id}
     * <p>Se actualizan únicamente los campos presentes en la request.</p>
     *
     * @param id      ID del aula a modificar.
     * @param updates Mapa con los campos a actualizar.
     * @return Aula actualizada.
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AulaDTO> actualizarAula(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates
    ) {
        try {
            return ResponseEntity.ok(aulaService.modificarAula(id, updates));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    /**
     * Elimina un aula por ID.
     *
     * Endpoint REST: DELETE /api/aula/{id}
     *
     * @param id ID del aula a eliminar.
     * @return 204 No Content si la eliminación fue exitosa.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminarAula(@PathVariable Long id) {
        try {
            aulaService.eliminarAula(id);
        } catch (RuntimeException runtimeException) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
