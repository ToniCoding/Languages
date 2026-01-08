package com.ad03.repository;

import com.ad03.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByNombre(String nombre);

    List<Autor> findByNombreContainingIgnoreCase(String nombre);

    List<Autor> findByNombreStartingWithIgnoreCase(String prefijo);

    boolean existsByNombre(String nombre);

    @Query("SELECT a FROM Autor a WHERE SIZE(a.libros) > :minLibros")
    List<Autor> findAutoresConMasDeLibros(@Param("minLibros") Long minLibros);

    @Query("SELECT a FROM Autor a JOIN a.libros l WHERE l.id = :libroId")
    List<Autor> findByLibroId(@Param("libroId") Long libroId);
}