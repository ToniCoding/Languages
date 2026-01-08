package com.ad03.service;

import com.ad03.dto.LibroDTO;
import com.ad03.dto.LibroYNumEjemplaresDTO;
import com.ad03.entity.Autor;
import com.ad03.entity.Libro;
import com.ad03.entity.Ejemplar;
import com.ad03.repository.AutorRepository;
import com.ad03.repository.LibroRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class LibroService {

    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    // ========== CONSULTAS DERIVADAS ==========

    public List<Libro> buscarPorTitulo(String texto) {
        log.info("Buscando libros con título que contenga: '{}'", texto);
        return libroRepository.findByTituloContainingIgnoreCase(texto);
    }

    public List<Libro> buscarPorFechaPublicacion(LocalDate fecha) {
        log.info("Buscando libros publicados en: {}", fecha);
        return libroRepository.findByFechaPublicacion(fecha);
    }

    public List<Libro> buscarPublicadosAntesDe(LocalDate fecha) {
        return libroRepository.findByFechaPublicacionBefore(fecha);
    }

    public List<Libro> buscarPublicadosDespuesDe(LocalDate fecha) {
        return libroRepository.findByFechaPublicacionAfter(fecha);
    }

    public List<Libro> buscarPublicadosEntre(LocalDate inicio, LocalDate fin) {
        return libroRepository.findByFechaPublicacionBetween(inicio, fin);
    }

    // ========== CONSULTAS PERSONALIZADAS ==========

    public List<LibroYNumEjemplaresDTO> obtenerLibrosConNumeroEjemplares() {
        log.info("Obteniendo todos los libros con número de ejemplares");
        return libroRepository.findAllWithEjemplarCount();
    }

    public LibroYNumEjemplaresDTO obtenerLibroConEjemplares(Long libroId) {
        log.info("Obteniendo libro {} con número de ejemplares", libroId);
        return libroRepository.findByIdWithEjemplarCount(libroId)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado con ID: " + libroId));
    }

    public List<LibroYNumEjemplaresDTO> buscarPorTituloConEjemplares(String titulo) {
        log.info("Buscando libros por título '{}' con número de ejemplares", titulo);
        return libroRepository.findByTituloContainingWithEjemplarCount(titulo);
    }

    public Long contarLibrosAntesDeano(Integer ano) {
        log.info("Contando libros publicados antes del año {}", ano);
        return libroRepository.countLibrosAntesDeano(ano);
    }

    public List<LibroYNumEjemplaresDTO> buscarPoranoPublicacion(Integer ano) {
        log.info("Buscando libros publicados en el año {}", ano);
        return libroRepository.findByanoPublicacionWithEjemplarCount(ano);
    }

    public List<LibroYNumEjemplaresDTO> buscarPorFechaPublicacionConEjemplares(LocalDate fecha) {
        return libroRepository.findByFechaPublicacionWithEjemplarCount(fecha);
    }

    public List<LibroYNumEjemplaresDTO> obtenerLibrosConMinEjemplaresDisponibles(Long minimo) {
        return libroRepository.findLibrosConMinEjemplaresDisponibles(minimo);
    }

    public List<String> obtenerEstadisticasPorAutor() {
        List<Object[]> resultados = libroRepository.countLibrosPorAutor();
        return resultados.stream()
                .map(arr -> String.format("Autor: %s - Libros: %d", arr[0], arr[1]))
                .toList();
    }

    @Transactional
    public LibroDTO crearLibro(LibroDTO libroDTO) {
        if (libroRepository.existsByIsbn(libroDTO.getIsbn())) {
            throw new RuntimeException("El ISBN ya existe");
        }

        Libro libro = new Libro();
        libro.setTitulo(libroDTO.getTitulo());
        libro.setIsbn(libroDTO.getIsbn());
        libro.setFechaPublicacion(libroDTO.getFechaPublicacion());

        Set<Autor> autores = new HashSet<>();
        for (Long autorId : libroDTO.getAutoresIds()) {
            Autor autor = autorRepository.findById(autorId)
                    .orElseThrow(() -> new RuntimeException("Autor no encontrado: " + autorId));
            autores.add(autor);
        }
        libro.setAutores(autores);

        Libro libroGuardado = libroRepository.save(libro);
        return convertirADTO(libroGuardado);
    }

    private LibroDTO convertirADTO(Libro libro) {
        LibroDTO dto = new LibroDTO();
        dto.setId(libro.getId());
        dto.setTitulo(libro.getTitulo());
        dto.setIsbn(libro.getIsbn());
        dto.setFechaPublicacion(libro.getFechaPublicacion());

        Set<Long> autoresIds = new HashSet<>();
        libro.getAutores().forEach(autor -> autoresIds.add(autor.getId()));
        dto.setAutoresIds(autoresIds);

        return dto;
    }

    public Page<LibroDTO> obtenerTodosLibrosPaginados(Pageable pageable) {
        Page<Libro> librosPage = libroRepository.findAll(pageable);
        return librosPage.map(this::convertirADTO);
    }

    public Page<LibroDTO> buscarPorTituloPaginado(String titulo, Pageable pageable) {
        Page<Libro> librosPage = libroRepository.findByTituloContainingIgnoreCase(titulo, pageable);
        return librosPage.map(this::convertirADTO);
    }

    public Page<LibroDTO> buscarPorFechaPublicacionPaginado(LocalDate fecha, Pageable pageable) {
        Page<Libro> librosPage = libroRepository.findByFechaPublicacion(fecha, pageable);
        return librosPage.map(this::convertirADTO);
    }

    public LibroDTO agregarAutorALibro(Long libroId, Long autorId) {
        Libro libro = libroRepository.findById(libroId)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        Autor autor = autorRepository.findById(autorId)
                .orElseThrow(() -> new RuntimeException("Autor no encontrado"));

        libro.getAutores().add(autor);
        Libro libroActualizado = libroRepository.save(libro);
        return convertirADTO(libroActualizado);
    }

    public LibroDTO quitarAutorDeLibro(Long libroId, Long autorId) {
        Libro libro = libroRepository.findById(libroId)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        libro.getAutores().removeIf(autor -> autor.getId().equals(autorId));
        Libro libroActualizado = libroRepository.save(libro);
        return convertirADTO(libroActualizado);
    }

    public LibroDTO agregarEjemplarALibro(Long libroId, String descripcion) {
        Libro libro = libroRepository.findById(libroId)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        Ejemplar ejemplar = new Ejemplar();
        ejemplar.setDescripcion(descripcion);
        ejemplar.setDisponible(true);
        ejemplar.setLibro(libro);

        libro.getEjemplares().add(ejemplar);
        Libro libroActualizado = libroRepository.save(libro);
        return convertirADTO(libroActualizado);
    }

    public LibroDTO eliminarEjemplarDeLibro(Long libroId, Long ejemplarId) {
        Libro libro = libroRepository.findById(libroId)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        libro.getEjemplares().removeIf(ejemplar -> ejemplar.getId().equals(ejemplarId));
        Libro libroActualizado = libroRepository.save(libro);
        return convertirADTO(libroActualizado);
    }

    @Transactional
    public void eliminarLibro(Long id) {
        if (!libroRepository.existsById(id)) {
            throw new RuntimeException("Libro no encontrado con ID: " + id);
        }
        libroRepository.deleteById(id);
        log.info("Libro eliminado con ID: {}", id);
    }

    public LibroDTO obtenerLibroPorId(Long id) {
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado con ID: " + id));
        return convertirADTO(libro);
    }
}
