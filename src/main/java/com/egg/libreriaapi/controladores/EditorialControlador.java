package com.egg.libreriaapi.controladores;

import com.egg.libreriaapi.entidades.Editorial;
import com.egg.libreriaapi.excepciones.MiExcepcion;
import com.egg.libreriaapi.modelos.EditorialCrearDTO;
import com.egg.libreriaapi.servicios.EditorialServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/editorial")
public class EditorialControlador {

    @Autowired
    private EditorialServicio editorialServicio;

    @PostMapping("/crear")
    public ResponseEntity<Object> guardarEditorial(@RequestBody EditorialCrearDTO editorialCrearDTO){
        try {
            editorialServicio.crearEditorial(editorialCrearDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Editorial>> listarEditoriales(){
        try {
            List<Editorial> listaEditorial = editorialServicio.listarEditorial();
            return new ResponseEntity<>(listaEditorial, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/modificar")
    public ResponseEntity<Object> modificarEditorial(String id, String nombre){
        try {
            editorialServicio.modificarEditorial(id, nombre);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (MiExcepcion me) {
            return new ResponseEntity<>(me.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Object> eliminarEditorial(@PathVariable String id){
        try {
            editorialServicio.eliminarEditorial(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
