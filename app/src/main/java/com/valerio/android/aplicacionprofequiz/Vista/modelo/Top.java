package com.valerio.android.aplicacionprofequiz.Vista.modelo;

import java.io.Serializable;

public class Top  implements Serializable {
    //Esta clase representa la estructura de datos generada por el JSON
    private String cod_prof;
    private String nombre_completo;
    private String correo_prof;
    private String fot_prof;
    private String carrera_prof;
    private String  calificacion;

    public String getCod_prof() {
        return cod_prof;
    }

    public void setCod_prof(String cod_prof) {
        this.cod_prof = cod_prof;
    }

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }

    public String getCorreo_prof() {
        return correo_prof;
    }

    public void setCorreo_prof(String correo_prof) {
        this.correo_prof = correo_prof;
    }

    public String getFot_prof() {
        return fot_prof;
    }

    public void setFot_prof(String fot_prof) {
        this.fot_prof = fot_prof;
    }

    public String getCarrera_prof() {
        return carrera_prof;
    }

    public void setCarrera_prof(String carrera_prof) {
        this.carrera_prof = carrera_prof;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }
}
