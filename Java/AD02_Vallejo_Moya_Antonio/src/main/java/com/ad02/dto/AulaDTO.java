package com.ad02.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AulaDTO {
    private Long id;
    private String numeroAula;
    private Boolean esAulaOrdenadores;
    private String comentarios;
    private Long centroId;
    private String centro;
    private LocalDate fechaAlta;
    private LocalDate fechaModificacion;
}
