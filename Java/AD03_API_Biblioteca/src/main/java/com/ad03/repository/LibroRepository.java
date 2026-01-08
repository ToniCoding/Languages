package com.ad03.repository;

import com.ad03.dto.LibroYNumEjemplaresDTO;
import com.ad03.entity.Libro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    List<Libro> findByTituloContainingIgnoreCase(String titulo);

    Page<Libro> findByTituloContainingIgnoreCase(String titulo, Pageable pageable);

    List<Libro> findByFechaPublicacion(LocalDate fecha);

    Page<Libro> findByFechaPublicacion(LocalDate fecha, Pageable pageable);

    List<Libro> findByFechaPublicacionBefore(LocalDate fecha);

    List<Libro> findByFechaPublicacionAfter(LocalDate fecha);

    List<Libro> findByFechaPublicacionBetween(LocalDate fechaInicio, LocalDate fechaFin);

    Optional<Libro> findByIsbn(String isbn);

    boolean existsByIsbn(String isbn);

    List<Libro> findByAutoresNombreContainingIgnoreCase(String nombreAutor);

    @Query("SELECT new com.ad03.dto.LibroYNumEjemplaresDTO(l.id, l.titulo, l.isbn, l.fechaPublicacion, COUNT(e.id)) FROM Libro l LEFT JOIN l.ejemplares e GROUP BY l.id, l.titulo, l.isbn, l.fechaPublicacion")
    List<LibroYNumEjemplaresDTO> findAllWithEjemplarCount();

    @Query("SELECT new com.ad03.dto.LibroYNumEjemplaresDTO(l.id, l.titulo, l.isbn, l.fechaPublicacion, COUNT(e.id)) FROM Libro l LEFT JOIN l.ejemplares e WHERE l.id = :libroId GROUP BY l.id, l.titulo, l.isbn, l.fechaPublicacion")
    Optional<LibroYNumEjemplaresDTO> findByIdWithEjemplarCount(@Param("libroId") Long libroId);

    @Query("SELECT new com.ad03.dto.LibroYNumEjemplaresDTO(l.id, l.titulo, l.isbn, l.fechaPublicacion, COUNT(e.id)) FROM Libro l LEFT JOIN l.ejemplares e WHERE LOWER(l.titulo) LIKE LOWER(CONCAT('%', :titulo, '%')) GROUP BY l.id, l.titulo, l.isbn, l.fechaPublicacion")
    List<LibroYNumEjemplaresDTO> findByTituloContainingWithEjemplarCount(@Param("titulo") String titulo);

    @Query("SELECT COUNT(l) FROM Libro l WHERE YEAR(l.fechaPublicacion) < :ano")
    Long countLibrosAntesDeano(@Param("ano") Integer ano);

    @Query("SELECT new com.ad03.dto.LibroYNumEjemplaresDTO(l.id, l.titulo, l.isbn, l.fechaPublicacion, COUNT(e.id)) FROM Libro l LEFT JOIN l.ejemplares e WHERE YEAR(l.fechaPublicacion) = :ano GROUP BY l.id, l.titulo, l.isbn, l.fechaPublicacion")
    List<LibroYNumEjemplaresDTO> findByanoPublicacionWithEjemplarCount(@Param("ano") Integer ano);

    @Query("SELECT new com.ad03.dto.LibroYNumEjemplaresDTO(l.id, l.titulo, l.isbn, l.fechaPublicacion, COUNT(e.id)) FROM Libro l LEFT JOIN l.ejemplares e WHERE l.fechaPublicacion = :fecha GROUP BY l.id, l.titulo, l.isbn, l.fechaPublicacion")
    List<LibroYNumEjemplaresDTO> findByFechaPublicacionWithEjemplarCount(@Param("fecha") LocalDate fecha);

    @Query("SELECT new com.ad03.dto.LibroYNumEjemplaresDTO(l.id, l.titulo, l.isbn, l.fechaPublicacion, COUNT(e.id)) FROM Libro l LEFT JOIN l.ejemplares e WHERE e.disponible = true GROUP BY l.id, l.titulo, l.isbn, l.fechaPublicacion HAVING COUNT(e.id) >= :minEjemplares")
    List<LibroYNumEjemplaresDTO> findLibrosConMinEjemplaresDisponibles(@Param("minEjemplares") Long minEjemplares);

    @Query("SELECT a.nombre, COUNT(l) FROM Libro l JOIN l.autores a GROUP BY a.id, a.nombre")
    List<Object[]> countLibrosPorAutor();
}