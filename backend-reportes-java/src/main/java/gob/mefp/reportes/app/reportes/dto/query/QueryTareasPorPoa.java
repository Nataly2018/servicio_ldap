/**
 * Project: nova
 * Package: bo.gob.ypfb.nova
 * El archivo "QueryTareasPorPoa.java" fue creado para el proyecto nova
 * por Y.P.F.B. Corporación, todos los Derechos reservados.
 *
 * @author: Roger Diego Flores Condori
 * @email : cons.rflores@ypfb.gob.bo
 * @date: 11/9/2023 08:21
 * @copyright: YPFB
 * @version: 1.0
 **/
package gob.mefp.reportes.app.reportes.dto.query;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

public class QueryTareasPorPoa {
    private Integer codigo;
    private Integer codigoArea;
    private String codigoOperacion;
    private Long idTarea;
    private String codigoTarea;
    private String descripcionOperacion;
    private Integer ordenOperacion;
    private String descripcionTarea;
    private Integer ordenTarea;

    @Basic
    @Column(name = "codigo")
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    @Basic
    @Column(name = "codigo_area")
    public Integer getCodigoArea() {
        return codigoArea;
    }

    public void setCodigoArea(Integer codigoArea) {
        this.codigoArea = codigoArea;
    }

    @Basic
    @Column(name = "codigo_operacion")
    public String getCodigoOperacion() {
        return codigoOperacion;
    }

    public void setCodigoOperacion(String codigoOperacion) {
        this.codigoOperacion = codigoOperacion;
    }

    @Basic
    @Column(name = "id_tarea")
    public Long getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Long idTarea) {
        this.idTarea = idTarea;
    }

    @Basic
    @Column(name = "codigo_tarea")
    public String getCodigoTarea() {
        return codigoTarea;
    }

    public void setCodigoTarea(String codigoTarea) {
        this.codigoTarea = codigoTarea;
    }

    @Basic
    @Column(name = "descripcion_operacion")
    public String getDescripcionOperacion() {
        return descripcionOperacion;
    }

    public void setDescripcionOperacion(String descripcionOperacion) {
        this.descripcionOperacion = descripcionOperacion;
    }

    @Basic
    @Column(name = "orden_operacion")
    public Integer getOrdenOperacion() {
        return ordenOperacion;
    }

    public void setOrdenOperacion(Integer ordenOperacion) {
        this.ordenOperacion = ordenOperacion;
    }

    @Basic
    @Column(name = "descripcion_tarea")
    public String getDescripcionTarea() {
        return descripcionTarea;
    }

    public void setDescripcionTarea(String descripcionTarea) {
        this.descripcionTarea = descripcionTarea;
    }

    @Basic
    @Column(name = "orden_tarea")
    public Integer getOrdenTarea() {
        return ordenTarea;
    }

    public void setOrdenTarea(Integer ordenTarea) {
        this.ordenTarea = ordenTarea;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueryTareasPorPoa that = (QueryTareasPorPoa) o;
        return Objects.equals(codigo, that.codigo) && Objects.equals(codigoArea, that.codigoArea) && Objects.equals(codigoOperacion, that.codigoOperacion) && Objects.equals(idTarea, that.idTarea) && Objects.equals(codigoTarea, that.codigoTarea) && Objects.equals(descripcionOperacion, that.descripcionOperacion) && Objects.equals(ordenOperacion, that.ordenOperacion) && Objects.equals(descripcionTarea, that.descripcionTarea) && Objects.equals(ordenTarea, that.ordenTarea);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, codigoArea, codigoOperacion, idTarea, codigoTarea, descripcionOperacion, ordenOperacion, descripcionTarea, ordenTarea);
    }
}
