/**
 * Project: nova
 * Package: bo.gob.ypfb.nova
 * El archivo "QueryVersionPoa.java" fue creado para el proyecto nova
 * por Y.P.F.B. Corporación, todos los Derechos reservados.
 *
 * @author: Roger Diego Flores Condori
 * @email : cons.rflores@ypfb.gob.bo
 * @date: 15/9/2023 08:31
 * @copyright: YPFB
 * @version: 1.0
 **/
package gob.mefp.reportes.app.reportes.dto.query;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Objects;


public class QueryVersionPoa {
    private Long id;
    private Long idPoa;
    private Integer version;
    private String codigoVersion;
    private String descripcion;
    private Timestamp fechaInicial;
    private Timestamp fechaFinal;

    @Basic
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    @Column(name = "version")
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Basic
    @Column(name = "codigo_version")
    public String getCodigoVersion() {
        return codigoVersion;
    }

    public void setCodigoVersion(String codigoVersion) {
        this.codigoVersion = codigoVersion;
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
    @Column(name = "fecha_inicial")
    public Timestamp getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Timestamp fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    @Basic
    @Column(name = "fecha_final")
    public Timestamp getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Timestamp fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueryVersionPoa that = (QueryVersionPoa) o;
        return Objects.equals(id, that.id) && Objects.equals(idPoa, that.idPoa) && Objects.equals(version, that.version) && Objects.equals(codigoVersion, that.codigoVersion) && Objects.equals(descripcion, that.descripcion) && Objects.equals(fechaInicial, that.fechaInicial) && Objects.equals(fechaFinal, that.fechaFinal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idPoa, version, codigoVersion, descripcion, fechaInicial, fechaFinal);
    }
}
