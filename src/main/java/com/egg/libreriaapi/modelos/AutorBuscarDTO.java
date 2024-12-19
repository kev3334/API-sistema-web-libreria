package com.egg.libreriaapi.modelos;

import lombok.Data;

@Data
public class AutorBuscarDTO {
    private String id;
    private String nombre;
    private boolean isActive;

    public AutorBuscarDTO(String id, String nombre, boolean isActive) {
        this.id = id;
        this.nombre = nombre;
        this.isActive = isActive;
    }
}
