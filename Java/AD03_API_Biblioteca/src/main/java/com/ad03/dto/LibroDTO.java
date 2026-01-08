package com.ad03.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LibroDTO {

    private Long id;

    @NotBlank(message = "El título es obligatorio")
    @Size(min = 3, message = "El título debe tener al menos 3 caracteres")
    private String titulo;

    @NotBlank(message = "El ISBN es obligatorio")
    @Size(min = 10, max = 17, message = "El ISBN debe tener entre 10 y 17 caracteres")
    private String isbn;

    @NotNull(message = "La fecha de publicación es obligatoria")
    private LocalDate fechaPublicacion;

    private LocalDate fechaCreacion;

    @Builder.Default
    private Set<Long> autoresIds = new HashSet<>();

    @Builder.Default
    private Set<AutorDTO> autoresInfo = new HashSet<>();
    
    public LibroDTO(Long id, String titulo, String isbn, LocalDate fechaPublicacion, LocalDate fechaCreacion) {
        this.id = id;
        this.titulo = titulo;
        this.isbn = isbn;
        this.fechaPublicacion = fechaPublicacion;
        this.fechaCreacion = fechaCreacion;
        this.autoresIds = new HashSet<>();
        this.autoresInfo = new HashSet<>();
    }

    public void addAutorId(Long autorId) {
        this.autoresIds.add(autorId);
    }

    public void addAutorInfo(AutorDTO autorInfo) {
        this.autoresInfo.add(autorInfo);
    }
}
