/**
 * Project: nova
 * Package: bo.gob.ypfb.nova
 * El archivo "QueryAreasResponsablesPorPoa.java" fue creado para el proyecto nova
 * por Y.P.F.B. Corporación, todos los Derechos reservados.
 *
 * @author: Roger Diego Flores Condori
 * @email : cons.rflores@ypfb.gob.bo
 * @date: 11/9/2023 09:27
 * @copyright: YPFB
 * @version: 1.0
 **/
package gob.mefp.reportes.app.reportes.dto.query;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;


public class QueryAreasResponsablesPorPoa {
    private Long id;
    private String sigla;
    private String siglaGthc;
    private String descripcion;

    @Basic
    @Column(name = "descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Basic
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    @Column(name = "sigla_gthc")
    public String getSiglaGthc() {
        return siglaGthc;
    }

    public void setSiglaGthc(String siglaGthc) {
        this.siglaGthc = siglaGthc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueryAreasResponsablesPorPoa that = (QueryAreasResponsablesPorPoa) o;
        return Objects.equals(id, that.id) && Objects.equals(sigla, that.sigla) && Objects.equals(siglaGthc, that.siglaGthc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sigla, siglaGthc);
    }
}
