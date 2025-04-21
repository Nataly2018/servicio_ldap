/**
 * Project: nova
 * Package: bo.gob.ypfb.nova
 * El archivo "QueryAreaPorPoa.java" fue creado para el proyecto nova
 * por Y.P.F.B. Corporación, todos los Derechos reservados.
 *
 * @author: Roger Diego Flores Condori
 * @email : cons.rflores@ypfb.gob.bo
 * @date: 8/9/2023 13:44
 * @copyright: YPFB
 * @version: 1.0
 **/
package gob.mefp.reportes.app.reportes.dto.query;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;


public class QueryAreaPorPoa {
    private Long idArea;
    private Long idPoa;
    private Long idPoaArea;
    private Integer codigoArea;
    private String sigla;
    private String descripcion;

    @Basic
    @Column(name = "id_area")
    public Long getIdArea() {
        return idArea;
    }

    public void setIdArea(Long idArea) {
        this.idArea = idArea;
    }

    @Basic
    @Column(name = "id_poa")
    public Long getIdPoa() {
        return idPoa;
    }

    public void setIdPoa(Long idPoa) {
        this.idPoa = idPoa;
    }

    @Basic
    @Column(name = "id_poa_area")
    public Long getIdPoaArea() {
        return idPoaArea;
    }

    public void setIdPoaArea(Long idPoaArea) {
        this.idPoaArea = idPoaArea;
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
    @Column(name = "sigla")
    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    @Basic
    @Column(name = "descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueryAreaPorPoa that = (QueryAreaPorPoa) o;
        return Objects.equals(idArea, that.idArea) && Objects.equals(idPoa, that.idPoa) && Objects.equals(idPoaArea, that.idPoaArea) && Objects.equals(codigoArea, that.codigoArea) && Objects.equals(sigla, that.sigla) && Objects.equals(descripcion, that.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idArea, idPoa, idPoaArea, codigoArea, sigla, descripcion);
    }
}
