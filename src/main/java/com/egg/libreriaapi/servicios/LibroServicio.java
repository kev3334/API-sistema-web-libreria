package com.egg.libreriaapi.servicios;

import com.egg.libreriaapi.entidades.Autor;
import com.egg.libreriaapi.entidades.Editorial;
import com.egg.libreriaapi.entidades.Libro;
import com.egg.libreriaapi.excepciones.MiExcepcion;
import com.egg.libreriaapi.modelos.LibroCreateDTO;
import com.egg.libreriaapi.modelos.LibroListarActivosDTO;
import com.egg.libreriaapi.repositorios.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LibroServicio {
    @Autowired
    private AutorServicio autorServicio;
    @Autowired
    private EditorialServicio editorialServicio;
    @Autowired
    private LibroRepositorio libroRepositorio;

    @Transactional
    public void crearLibro(LibroCreateDTO libroDTO){
        Libro libroNuevo = new Libro();
        libroNuevo.setIsbn(libroDTO.getIsbn());
        libroNuevo.setTitulo(libroDTO.getTitulo());
        libroNuevo.setEjemplares(libroDTO.getEjemplares());
        libroNuevo.setLibroActivo(libroDTO.isLibroActivo());
        Autor autor = autorServicio.getOne(libroDTO.getIdAutor());
        if(autor != null){
            libroNuevo.setAutor(autor);
        }
        Editorial editorial = editorialServicio.getOne(libroDTO.getIdEditorial());
        if(editorial != null){
            libroNuevo.setEditorial(editorial);
        }
        libroRepositorio.save(libroNuevo);
    }

    @Transactional(readOnly = true)
    public List<LibroListarActivosDTO> listarLibros(){
        return libroRepositorio.encontrarLibrosActivos();
    }

    @Transactional
    public void eliminarLibro(Long isbn) throws MiExcepcion {
        Optional<Libro> respuesta = libroRepositorio.findById(isbn);

        if(respuesta.isPresent()){
            Libro libroEliminar = respuesta.get();
            libroRepositorio.delete(libroEliminar);
        }else{
            throw new MiExcepcion("No se pudo eliminar el Libro");
        }
    }

    @Transactional(readOnly = true)
    public Libro getOne(Long isbn){
        return libroRepositorio.getReferenceById(isbn);
    }


}
