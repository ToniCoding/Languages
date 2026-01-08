package com.ad02.controller;

import com.ad02.entity.Centro;
import com.ad02.dto.CentroDTO;
import com.ad02.service.CentroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CentroController - El controlador REST para el endpoint de centros.
 *
 * <p>Mapea los métodos HTTP y llama a los métodos del servicio Centro correspondiente.</p>
 * */
@RestController
@RequestMapping("/api/centro")
@RequiredArgsConstructor
public class CentroController {

    private final CentroService centroService;

    /**
     * Lista todos los centros creados.
     *
     * <p>Endpoint REST: GET /api/centro/</p>
     *
     * @return Lista de los centros registrados.
     */
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<CentroDTO>> listarCentros() {
        return ResponseEntity.ok(
                centroService.listarCentros().stream()
                        .map(centroService::toDTO)
                        .toList()
        );
    }

    /**
     * Obtiene información de un centro específico.
     *
     * <p>Endpoint REST: GET /api/centro/{id}</p>
     *
     * @return Lista de los centros registrados.
     */
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CentroDTO> obtenerCentro(@PathVariable Long id) {
        Centro centro = centroService.obtenerCentro(id);
        if (centro == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(centroService.toDTO(centro));
    }

    /**
     * Registra un nuevo centro.
     *
     * <p>Endpoint REST: POST /api/aula/</p>
     * <p>Request body: JSON de información del centro</p>
     *
     * @return 200 OK en caso de que el centro se actualice correctamente.
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CentroDTO> crearCentro(@RequestBody Centro centro) {
        Centro creado = centroService.crearCentro(centro);
        return ResponseEntity.ok(centroService.toDTO(creado));
    }

    /**
     * Registra un nuevo centro.
     *
     * <p>Endpoint REST: PUT /api/aula/</p>
     * <p>Request body: JSON de información a actualizar del centro</p>
     *
     * @return 200 OK en caso de que la información del centro se actualice correctamente.
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Centro> actualizarCentro(
            @PathVariable Long id,
            @RequestBody Centro centro
    ) {
        return ResponseEntity.ok(centroService.actualizarCentro(id, centro));
    }

    /**
     * Elimina un centro existente.
     *
     * <p>Endpoint REST: DELETE /api/centro/{id}</p>
     *
     * @return 200 OK en caso de que la información del centro se actualice correctamente.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminarCentro(@PathVariable Long id) {
        centroService.eliminarCentro(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Agrega un aula específica a un centro específico.
     *
     * <p>Endpoint REST: POST /api/centro/{idCentro}/aulas/{idAula}</p>
     *
     * @param idCentro El ID del centro al que agregar el aula.
     * @param idAula El ID del aula que se va a agregar al centro.
     * @return 200 OK en caso de que el aula se agregue correctamente al centro.
     */
    @PostMapping("/{idCentro}/aulas/{idAula}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CentroDTO> agregarAulaACentro(
            @PathVariable Long idCentro,
            @PathVariable Long idAula
    ) {
        CentroDTO centroActualizado = centroService.agregarAula(idCentro, idAula);
        return ResponseEntity.ok(centroActualizado);
    }

    /**
     * Elimina un aula específica a un centro específico.
     *
     * <p>Endpoint REST: DELETE /api/centro/{idCentro}/aulas/{idAula}</p>
     *
     * @param idCentro El ID del centro al que eliminar el aula.
     * @param idAula El ID del aula que se va a eliminar del centro.
     * @return 200 OK en caso de que el aula se elimine correctamente al centro.
     */
    @DeleteMapping("/{idCentro}/aulas/{idAula}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminarAula(
            @PathVariable Long idCentro,
            @PathVariable Long idAula
    ) {
        try {
            centroService.quitarAula(idCentro, idAula);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
