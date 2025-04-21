/**
 * Project: nova
 * Package: bo.gob.ypfb.nova
 * El archivo "QueryOperacionesPorPoa.java" fue creado para el proyecto nova
 * por Y.P.F.B. Corporación, todos los Derechos reservados.
 *
 * @author: Roger Diego Flores Condori
 * @email : cons.rflores@ypfb.gob.bo
 * @date: 8/9/2023 14:53
 * @copyright: YPFB
 * @version: 1.0
 **/
package gob.mefp.reportes.app.reportes.dto.query;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

public class QueryOperacionesPorPoa {
    private Long idOperacion;
    private Integer codigoAccionCortoPlazo;
    private Integer codigoArea;
    private String codigoOperacion;
    private String descripcion;
    private Integer orden;

    @Basic
    @Column(name = "id_operacion")
    public Long getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(Long idOperacion) {
        this.idOperacion = idOperacion;
    }

    @Basic
    @Column(name = "codigo_accion_corto_plazo")
    public Integer getCodigoAccionCortoPlazo() {
        return codigoAccionCortoPlazo;
    }

    public void setCodigoAccionCortoPlazo(Integer codigoAccionCortoPlazo) {
        this.codigoAccionCortoPlazo = codigoAccionCortoPlazo;
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
    @Column(name = "descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Basic
    @Column(name = "orden")
    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueryOperacionesPorPoa that = (QueryOperacionesPorPoa) o;
        return Objects.equals(idOperacion, that.idOperacion) && Objects.equals(codigoAccionCortoPlazo, that.codigoAccionCortoPlazo) && Objects.equals(codigoArea, that.codigoArea) && Objects.equals(codigoOperacion, that.codigoOperacion) && Objects.equals(descripcion, that.descripcion) && Objects.equals(orden, that.orden);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOperacion, codigoAccionCortoPlazo, codigoArea, codigoOperacion, descripcion, orden);
    }
}
