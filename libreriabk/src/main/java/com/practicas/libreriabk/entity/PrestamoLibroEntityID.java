package com.practicas.libreriabk.entity;

import java.io.Serializable;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

public class PrestamoLibroEntityID implements Serializable {

    @Getter @Setter private int idPrestamo;
    @Getter @Setter private int idLibro;
    
    public PrestamoLibroEntityID(int idPre, int idLi) {
    	this.idPrestamo = idPre;
    	this.idLibro = idLi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PrestamoLibroEntityID)) return false;
        PrestamoLibroEntityID that = (PrestamoLibroEntityID) o;
        return idPrestamo == that.idPrestamo &&
        		idLibro == that.idLibro;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPrestamo, idLibro);
    }
}

