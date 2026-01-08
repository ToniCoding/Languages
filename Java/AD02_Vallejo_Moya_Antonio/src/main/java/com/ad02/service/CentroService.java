package com.ad02.service;

import com.ad02.dto.CentroDTO;
import com.ad02.entity.Centro;
import com.ad02.entity.Aula;
import com.ad02.repository.AulaRepository;
import com.ad02.repository.CentroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CentroService {
    private final AulaRepository aulaRepository;
    private final CentroRepository centroRepository;

    public List<Centro> listarCentros() {
        return centroRepository.findAll();
    }

    public Centro obtenerCentro(Long id) {
        return centroRepository.findById(id).orElse(null);
    }

    public Centro crearCentro(Centro dto) {
        Centro centro = new Centro();
        centro.setNombre(dto.getNombre());
        centro.setLocalidad(dto.getLocalidad());

        centro = centroRepository.save(centro);

        if (dto.getAulas() != null) {
            for (Aula aula : dto.getAulas()) {
                aula.setCentro(centro);
                aulaRepository.save(aula);
            }
        }

        return centro;
    }

    public Centro actualizarCentro(Long id, Centro dto) {
        Centro centro = centroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Centro no encontrado"));

        if (dto.getNombre() != null) {
            centro.setNombre(dto.getNombre());
        }
        if (dto.getLocalidad() != null) {
            centro.setLocalidad(dto.getLocalidad());
        }

        // Sustituye las aulas actuales del centro por las del DTO.
        // Desasocia las existentes, actualiza o crea las nuevas según tengan ID o no,
        // y luego las asocia al centro.
        if (dto.getAulas() != null) {
            if (centro.getAulas() != null && !centro.getAulas().isEmpty()) {
                List<Aula> actuales = new ArrayList<>(centro.getAulas());
                for (Aula a : actuales) {
                    a.setCentro(null);
                    aulaRepository.save(a);
                }
                centro.getAulas().clear();
            }

            for (Aula aDto : dto.getAulas()) {
                Aula aulaEntity;
                if (aDto.getId() != null) {
                    aulaEntity = aulaRepository.findById(aDto.getId())
                            .orElseThrow(() -> new RuntimeException("Aula con id " + aDto.getId() + " no encontrada"));
                    if (aDto.getNumeroAula() != null) aulaEntity.setNumeroAula(aDto.getNumeroAula());
                    if (aDto.getEsAulaOrdenadores() != null) aulaEntity.setEsAulaOrdenadores(aDto.getEsAulaOrdenadores());
                    if (aDto.getComentarios() != null) aulaEntity.setComentarios(aDto.getComentarios());
                } else {
                    aulaEntity = new Aula();
                    aulaEntity.setNumeroAula(aDto.getNumeroAula());
                    aulaEntity.setEsAulaOrdenadores(aDto.getEsAulaOrdenadores());
                    aulaEntity.setComentarios(aDto.getComentarios());
                }

                aulaEntity.setCentro(centro);
                aulaEntity = aulaRepository.save(aulaEntity);

                if (centro.getAulas() == null) centro.setAulas(new ArrayList<>());
                centro.getAulas().add(aulaEntity);
            }
        }

        return centroRepository.save(centro);
    }


    public CentroDTO agregarAula(long idCentro, long idAula) {
        Centro centro = centroRepository.findById(idCentro)
                .orElseThrow(() -> new RuntimeException("Centro no encontrado"));

        Aula aula = aulaRepository.findById(idAula)
                .orElseThrow(() -> new RuntimeException("Aula no encontrada"));

        aula.setCentro(centro);
        aulaRepository.save(aula);

        return toDTO(centro);
    }

    public Centro quitarAula(long idCentro, long idAula) {
        Centro centro = centroRepository.findById(idCentro)
                .orElseThrow(() -> new RuntimeException("Centro no encontrado"));

        Aula aula = aulaRepository.findById(idAula)
                .orElseThrow(() -> new RuntimeException("Aula no encontrada"));

        if (aula.getCentro() != null && aula.getCentro().getId().equals(idCentro)) {
            aula.setCentro(null);
            aulaRepository.save(aula);
        }

        return centro;
    }

    public void eliminarCentro(long idCentro) {
        Centro centro = centroRepository.findById(idCentro)
                .orElseThrow(() -> new RuntimeException("Centro no encontrado"));

        if (centro.getAulas() != null) {
            for (Aula aula : centro.getAulas()) {
                aula.setCentro(null);
                aulaRepository.save(aula);
            }
        }

        centroRepository.delete(centro);
    }

    public CentroDTO toDTO(Centro centro) {
        CentroDTO dto = new CentroDTO();
        dto.setId(centro.getId());
        dto.setNombre(centro.getNombre());
        dto.setLocalidad(centro.getLocalidad());

        if (centro.getAulas() != null) {
            dto.setAulasIds(
                    centro.getAulas().stream()
                            .map(Aula::getId)
                            .toList()
            );
        }

        return dto;
    }

}
