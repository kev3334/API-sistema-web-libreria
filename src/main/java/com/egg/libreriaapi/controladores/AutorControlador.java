package com.egg.libreriaapi.controladores;

import com.egg.libreriaapi.entidades.Autor;
import com.egg.libreriaapi.entidades.Editorial;
import com.egg.libreriaapi.excepciones.MiExcepcion;
import com.egg.libreriaapi.modelos.AutorBuscarDTO;
import com.egg.libreriaapi.modelos.AutorModificarDTO;
import com.egg.libreriaapi.servicios.AutorServicio;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autor")
public class AutorControlador {

    @Autowired
    private AutorServicio autorServicio;

    @PostMapping("/crear")
    public ResponseEntity<Object> guardarAutor(@RequestBody String nombre){
        try {
            autorServicio.crearAutor(nombre);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Autor>> listarAutores(){
        try {
            List<Autor> listaAutor = autorServicio.listarAutor();
            return new ResponseEntity<>( listaAutor , HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PatchMapping("/modificar")
    public ResponseEntity<Object> modificarAutor(@RequestBody AutorModificarDTO autorModificarDTO){
        try {
            autorServicio.modificarAutor(autorModificarDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (MiExcepcion me) {
            return new ResponseEntity<>(me.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PatchMapping("/modificar")
//    public ResponseEntity<Object> modificarAutor(String id, String nombre){
//        try {
//            autorServicio.modificarAutor(id, nombre);
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (MiExcepcion me) {
//            return new ResponseEntity<>(me.getMessage(), HttpStatus.BAD_REQUEST);
//        } catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Object> eliminarAutor(@PathVariable String id){
        try {
            autorServicio.eliminarAutor(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (MiExcepcion me){
            return new ResponseEntity<>(me.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listarAutor/{id}")
    public ResponseEntity<AutorBuscarDTO> buscarAutor(@PathVariable("id") String idAutor){
        try {
            Autor autor = autorServicio.getOne(idAutor);
            AutorBuscarDTO autorBuscarDTO = new AutorBuscarDTO(autor.getId(), autor.getNombre(), autor.isAutorActivo());
            return new ResponseEntity<>(autorBuscarDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
