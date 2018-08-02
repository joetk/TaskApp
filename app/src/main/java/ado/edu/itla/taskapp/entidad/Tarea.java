package ado.edu.itla.taskapp.entidad;

import java.io.Serializable;
import java.util.Date;

public class Tarea implements Serializable{

    public enum TareaEstado {

        PENDIENTE,
        EN_PROCESO,
        TERMINADO
    }

    private Integer id;
    private String nombre;
    private String descripcion;
    private Date fecha;
    private Date fechaTerminado;
    private TareaEstado tareaEstado;
    private int usuarioCreadorId;
    private int usuarioAsignadoId;
    private int categoriaId;

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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
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

    public int getUsuarioCreadorId() {
        return usuarioCreadorId;
    }

    public void setUsuarioCreadorId(int usuarioCreadorId) {
        this.usuarioCreadorId = usuarioCreadorId;
    }

    public int getUsuarioAsignado() {
        return usuarioAsignadoId;
    }

    public void setUsuarioAsignadoId(int usuarioAsignadoId) {
        this.usuarioAsignadoId = usuarioAsignadoId;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Tarea{");
        sb.append("id=").append(id);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", descripcion='").append(descripcion).append('\'');
        sb.append(", fecha=").append(fecha);
        sb.append(", fechaTerminado=").append(fechaTerminado);
        sb.append(", tareaEstado=").append(tareaEstado);
        sb.append(", usuarioCreadorId=").append(usuarioCreadorId);
        sb.append(", usuarioAsignadoId=").append(usuarioAsignadoId);
        sb.append(", categoriaId=").append(categoriaId);
        sb.append('}');
        return sb.toString();
    }
}
