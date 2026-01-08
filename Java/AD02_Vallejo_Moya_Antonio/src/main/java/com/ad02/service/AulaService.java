package com.ad02.service;

import com.ad02.entity.Aula;
import com.ad02.entity.Centro;
import com.ad02.repository.AulaRepository;
import com.ad02.repository.CentroRepository;
import com.ad02.dto.AulaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AulaService {
    private final AulaRepository aulaRepository;
    private final CentroRepository centroRepository;
    private final ObjectMapper objectMapper;

    public List<AulaDTO> listarAulas() {
        return aulaRepository.findAll().stream().map(aula -> {
            AulaDTO dto = new AulaDTO();
            dto.setId(aula.getId());
            dto.setNumeroAula(aula.getNumeroAula());
            dto.setEsAulaOrdenadores(aula.getEsAulaOrdenadores());
            dto.setComentarios(aula.getComentarios());
            dto.setCentro(aula.getCentro() != null ? aula.getCentro().getNombre() : null);
            dto.setFechaAlta(aula.getFechaAlta());
            dto.setFechaModificacion(aula.getFechaModificacion());
            return dto;
        }).collect(Collectors.toList());
    }

    public AulaDTO obtenerAula(Long id) {
        Aula aula = aulaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aula no encontrada"));

        AulaDTO dto = new AulaDTO();
        dto.setId(aula.getId());
        dto.setNumeroAula(aula.getNumeroAula());
        dto.setEsAulaOrdenadores(aula.getEsAulaOrdenadores());
        dto.setComentarios(aula.getComentarios());
        dto.setFechaAlta(aula.getFechaAlta());
        dto.setFechaModificacion(aula.getFechaModificacion());

        if (aula.getCentro() != null) {
            dto.setCentroId(aula.getCentro().getId());
            dto.setCentro(aula.getCentro().getNombre());
        }

        return dto;
    }

    /**
     * Crea una instancia de la entidad de Aula, rellena la información con un DTO que se mapea de la petición HTTP,
     * selecciona el centro (controla si este existe o no) y registra mediante funcionalidad de Hibernate repository
     * el aula nueva.
     * @param dto Clase que transporta los datos.
     * @return La entidad de Aula.
     */
    public AulaDTO crearAula(AulaDTO dto) {
        Aula aula = new Aula();

        aula.setNumeroAula(dto.getNumeroAula());
        aula.setEsAulaOrdenadores(dto.getEsAulaOrdenadores());
        aula.setComentarios(dto.getComentarios());

        if (dto.getCentro() != null) {
            Centro centro = centroRepository.findAll()
                    .stream()
                    .filter(c -> c.getNombre().equalsIgnoreCase(dto.getCentro()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("El centro seleccionado no existe"));
            aula.setCentro(centro);
        }

        Aula aulaGuardada = aulaRepository.save(aula);

        AulaDTO response = new AulaDTO();
        response.setId(aulaGuardada.getId());
        response.setNumeroAula(aulaGuardada.getNumeroAula());
        response.setEsAulaOrdenadores(aulaGuardada.getEsAulaOrdenadores());
        response.setComentarios(aulaGuardada.getComentarios());
        response.setCentro(aulaGuardada.getCentro() != null
                ? aulaGuardada.getCentro().getNombre()
                : null);
        response.setFechaAlta(aulaGuardada.getFechaAlta());
        response.setFechaModificacion(aulaGuardada.getFechaModificacion());

        return response;
    }
    
    /**
     * Elimina un aula específica.
     * @param id El ID del aula a eliminar.
     */
    public void eliminarAula(Long id) {
        Aula aula = aulaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aula no encontrada"));

        if (aula.getCentro() != null) {
            aula.setCentro(null);
        }

        aulaRepository.delete(aula);
    }

    /**
     * Modifica parcialmente un aula existente según los campos proporcionados en la request.
     * <p>
     * Este método permite actualizar únicamente los campos presentes en el Map de updates,
     * evitando sobrescribir información no enviada. Además, permite asociar el aula a un
     * centro existente mediante el nombre del centro en el DTO.
     * </p>
     *
     * @param id      El ID del aula que se desea modificar.
     * @param updates Un mapa con los campos a actualizar y sus nuevos valores.
     * @return AulaDTO La representación del aula actualizada, incluyendo el nombre del centro asociado
     *                 si se ha modificado.
     * @throws RuntimeException Si el aula no existe o el centro indicado no se encuentra.
     */
    public AulaDTO modificarAula(Long id, Map<String, Object> updates) {
        Aula aula = aulaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aula no encontrada"));

        AulaDTO dtoParcial = objectMapper.convertValue(updates, AulaDTO.class);

        if (dtoParcial.getNumeroAula() != null) aula.setNumeroAula(dtoParcial.getNumeroAula());
        if (dtoParcial.getEsAulaOrdenadores() != null) aula.setEsAulaOrdenadores(dtoParcial.getEsAulaOrdenadores());
        if (dtoParcial.getComentarios() != null) aula.setComentarios(dtoParcial.getComentarios());

        if (dtoParcial.getCentro() != null) {
            Centro centro = centroRepository.findAll().stream()
                    .filter(c -> c.getNombre().equalsIgnoreCase(dtoParcial.getCentro()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException(
                            "Centro con nombre " + dtoParcial.getCentro() + " no encontrado"));
            aula.setCentro(centro);
        }

        aula = aulaRepository.save(aula);

        AulaDTO resultado = new AulaDTO();
        resultado.setId(aula.getId());
        resultado.setNumeroAula(aula.getNumeroAula());
        resultado.setEsAulaOrdenadores(aula.getEsAulaOrdenadores());
        resultado.setComentarios(aula.getComentarios());
        if (aula.getCentro() != null) {
            resultado.setCentro(aula.getCentro().getNombre());
        }

        return resultado;
    }

    public AulaDTO toDTO(Aula aula) {
        AulaDTO dto = new AulaDTO();
        dto.setId(aula.getId());
        dto.setNumeroAula(aula.getNumeroAula());
        dto.setEsAulaOrdenadores(aula.getEsAulaOrdenadores());
        dto.setComentarios(aula.getComentarios());
        if (aula.getCentro() != null) {
            dto.setCentro(aula.getCentro().getNombre());
        }
        return dto;
    }

}
