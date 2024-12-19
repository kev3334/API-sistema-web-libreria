package com.egg.libreriaapi.servicios;

import com.egg.libreriaapi.entidades.Editorial;
import com.egg.libreriaapi.excepciones.MiExcepcion;
import com.egg.libreriaapi.modelos.EditorialCrearDTO;
import com.egg.libreriaapi.repositorios.EditorialRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EditorialServicio {

    @Autowired
    private EditorialRepositorio editorialRepositorio;

    @Transactional
    public void crearEditorial(String nombre){

        Editorial editorial = new Editorial();

        editorial.setEditorialActivo(true);
        editorial.setNombre(nombre);

        editorialRepositorio.save(editorial);

    }

    @Transactional
    public void crearEditorial(EditorialCrearDTO editorialDTO){
        Editorial editorial = new Editorial();

        editorial.setNombre(editorialDTO.getNombre());
        editorial.setEditorialActivo(true);

        editorialRepositorio.save(editorial);
    }

    @Transactional(readOnly = true)
    public List<Editorial> listarEditorial(){

        return editorialRepositorio.findAll();
    }

    public void modificarEditorial(String id, String nombre) throws MiExcepcion{
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Editorial editorialModificada = respuesta.get();
            editorialModificada.setNombre(nombre);
            editorialRepositorio.save(editorialModificada);
        }else{
            throw new MiExcepcion("No se pudo actualizar la Editorial");
        }
    }


    @Transactional
    public void eliminarEditorial(String id) throws MiExcepcion {
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);

        if(respuesta.isPresent()){
            Editorial editorialEliminar = respuesta.get();
            editorialEliminar.setEditorialActivo(false);
            editorialRepositorio.save(editorialEliminar);
        }else{
            throw new MiExcepcion("No se pudo eliminar la Editorial");
        }

    }

    public Editorial getOne(String idEditorial) {
        return editorialRepositorio.getReferenceById(idEditorial);
    }
}
