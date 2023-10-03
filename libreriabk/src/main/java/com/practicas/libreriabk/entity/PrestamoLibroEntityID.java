package com.practicas.libreriabk.entity;

import java.io.Serializable;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

public class PrestamoLibroEntityID implements Serializable {

    @Getter @Setter private int id_prestamo;
    @Getter @Setter private int id_libro;
    
    public PrestamoLibroEntityID(int idPre, int idLi) {
    	this.id_prestamo = idPre;
    	this.id_libro = idLi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PrestamoLibroEntityID)) return false;
        PrestamoLibroEntityID that = (PrestamoLibroEntityID) o;
        return id_prestamo == that.id_prestamo &&
                id_libro == that.id_libro;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_prestamo, id_libro);
    }
}

