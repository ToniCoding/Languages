package com.ad03.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "autores")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, message = "El nombre debe tener al menos 3 caracteres")
    @Column(nullable = false)
    private String nombre;

    @ManyToMany(mappedBy = "autores", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude // Evitar referencias circulares.
    private Set<Libro> libros = new HashSet<>();
}