package com.egg.libreriaapi.controladores;

import com.egg.libreriaapi.modelos.LibroCreateDTO;
import com.egg.libreriaapi.modelos.LibroListarActivosDTO;
import com.egg.libreriaapi.servicios.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libro")
public class LibroControlador {

    @Autowired
    private LibroServicio libroServicio;

    @PostMapping("/crear")
    public ResponseEntity<Object> crearLibro(@RequestBody LibroCreateDTO libroDTO){
        try {
            libroServicio.crearLibro(libroDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"Algun dato no es correcto o es nulo, revisar.\"}");
        }
    }

    @GetMapping("/listarActivos")
    public ResponseEntity<List<LibroListarActivosDTO>> listarLibro(){
        try {
            List<LibroListarActivosDTO> libros = libroServicio.listarLibros();
            return new ResponseEntity<>(libros, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/eliminar/{isbn}")
    public ResponseEntity<Object> eliminarLibro(@PathVariable Long isbn){
        try {
            libroServicio.eliminarLibro(isbn);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
