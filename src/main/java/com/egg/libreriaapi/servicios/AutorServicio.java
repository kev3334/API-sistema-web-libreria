package com.egg.libreriaapi.servicios;

import com.egg.libreriaapi.entidades.Autor;
import com.egg.libreriaapi.excepciones.MiExcepcion;
import com.egg.libreriaapi.modelos.AutorBuscarDTO;
import com.egg.libreriaapi.modelos.AutorModificarDTO;
import com.egg.libreriaapi.repositorios.AutorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AutorServicio {

    @Autowired
    private AutorRepositorio autorRepositorio;

    @Transactional
    public void crearAutor(String nombre){
        Autor autor = new Autor();
        autor.setAutorActivo(true);
        autor.setNombre(nombre);

        autorRepositorio.save(autor);
    }

    @Transactional(readOnly = true)
    public List<Autor> listarAutor(){
        return autorRepositorio.findAll();
    }

    @Transactional
    public void modificarAutor(String id, String nombre) throws MiExcepcion {
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if(respuesta.isPresent()){
            Autor autorModificado = new Autor();
            autorModificado = respuesta.get();
            autorModificado.setNombre(nombre);
            autorRepositorio.save(autorModificado);
        }else{
            throw new MiExcepcion("No se pudo modificar el autor");
        }
    }

    @Transactional
    public void modificarAutor(AutorModificarDTO autorDTO) throws MiExcepcion{
        Optional<Autor> respuesta = autorRepositorio.findById(autorDTO.getId());
        if(respuesta.isPresent()){
            Autor autorModificado = new Autor();
            autorModificado = respuesta.get();
            autorModificado.setNombre(autorDTO.getNombre());
            autorRepositorio.save(autorModificado);
        }else{
            throw new MiExcepcion("No se pudo modificar el autor");
        }
    }

    @Transactional
    public void eliminarAutor(String id) throws MiExcepcion {
        Optional<Autor> respuesta = autorRepositorio.findById(id);

        if(respuesta.isPresent()){
            Autor autorEliminar = respuesta.get();
            autorEliminar.setAutorActivo(false);
            autorRepositorio.save(autorEliminar);
        }else{
            throw new MiExcepcion("No se pudo eliminar el Autor");
        }

    }


    @Transactional(readOnly = true)
    public Autor getOne(String idAutor) {
        return autorRepositorio.getReferenceById(idAutor);
//        Optional<Autor> respuesta = autorRepositorio.findById(idAutor);
//        if(respuesta.isPresent()){
//            Autor autor = new Autor();
//            autor = respuesta.get();
//            return autor;
//        }else{
//            return null;
//        }
    }



}
