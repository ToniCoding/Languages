package com.ad03.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LibroYNumEjemplaresDTO {

    private Long id;
    private String titulo;
    private String isbn;
    private LocalDate fechaPublicacion;
    private Integer numeroEjemplares;

    public LibroYNumEjemplaresDTO(Long id, String titulo, String isbn, LocalDate fechaPublicacion, Long numeroEjemplares) {
        this.id = id;
        this.titulo = titulo;
        this.isbn = isbn;
        this.fechaPublicacion = fechaPublicacion;
        this.numeroEjemplares = numeroEjemplares != null ? numeroEjemplares.intValue() : 0;
    }
}