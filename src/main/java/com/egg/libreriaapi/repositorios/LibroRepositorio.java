package com.egg.libreriaapi.repositorios;

import com.egg.libreriaapi.entidades.Libro;
import com.egg.libreriaapi.modelos.LibroListarActivosDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, Long> {

    //Directamente, recupero la info que se precisa en la BBDD creando una isntancia de LibroListarActivosDTO
    @Query("SELECT new com.egg.libreriaapi.modelos.LibroListarActivosDTO(l.titulo, l.ejemplares) "
    + "FROM Libro l WHERE l.libroActivo = true")
    List<LibroListarActivosDTO> encontrarLibrosActivos();
}
