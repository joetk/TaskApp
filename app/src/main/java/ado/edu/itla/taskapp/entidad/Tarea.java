package ado.edu.itla.taskapp.entidad;

import java.util.Date;

public class Tarea {

    public enum TareaEstado {

        PENDIENTE,
        EN_PROCESO,
        TERMINADO
    }

    private Integer id;
    private String nombre;
    private String descripcion;
    private String fecha;
    private Date fechaTerminado;
    private TareaEstado tareaEstado;
    private Usuario usuarioCreador;
    private Usuario usuarioAsignado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Date getFechaTerminado() {
        return fechaTerminado;
    }

    public void setFechaTerminado(Date fechaTerminado) {
        this.fechaTerminado = fechaTerminado;
    }

    public TareaEstado getTareaEstado() {
        return tareaEstado;
    }

    public void setTareaEstado(TareaEstado tareaEstado) {
        this.tareaEstado = tareaEstado;
    }

    public Usuario getUsuarioCreador() {
        return usuarioCreador;
    }

    public void setUsuarioCreador(Usuario usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
    }

    public Usuario getUsuarioAsignado() {
        return usuarioAsignado;
    }

    public void setUsuarioAsignado(Usuario usuarioAsignado) {
        this.usuarioAsignado = usuarioAsignado;
    }
}
