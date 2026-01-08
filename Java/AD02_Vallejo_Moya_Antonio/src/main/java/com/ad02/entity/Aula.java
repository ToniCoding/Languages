package com.ad02.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

/**
 * Aula
 *
 * <p>Entidad gestionada por Hibernate que representa en base de datos a un Aula y su correspondiente información.</p>
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroAula;
    private Boolean esAulaOrdenadores;

    @Column(columnDefinition = "TEXT")
    private String comentarios;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDate fechaAlta;

    @LastModifiedDate
    private LocalDate fechaModificacion;

    @ManyToOne
    @JoinColumn(name = "centro_id")
    private Centro centro;
}
