package com.ad02.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Centro
 *
 * <p>Entidad gestionada por Hibernate que representa en base de datos a un Centro y su correspondiente información.</p>
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Centro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String localidad;

    @OneToMany(mappedBy = "centro")
    private List<Aula> aulas;
}
