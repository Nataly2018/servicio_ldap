/**
 * Project: nova
 * Package: bo.gob.ypfb.nova
 * El archivo "QueryFuenteOrgFinanPorPoa.java" fue creado para el proyecto nova
 * por Y.P.F.B. Corporación, todos los Derechos reservados.
 *
 * @author: Roger Diego Flores Condori
 * @email : cons.rflores@ypfb.gob.bo
 * @date: 11/9/2023 13:28
 * @copyright: YPFB
 * @version: 1.0
 **/
package gob.mefp.reportes.app.reportes.dto.query;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.util.Objects;


public class QueryFuenteOrgFinanPartidasTramite {
    private Long id;
    private Long idFuenteFinanciamiento;
    private Long idOrganismoFinanciador;
    private String codigo;

    @Basic
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "idFuenteFinanciamiento")
    public Long getIdFuenteFinanciamiento() {
        return idFuenteFinanciamiento;
    }

    public void setIdFuenteFinanciamiento(Long idFuenteFinanciamiento) {
        this.idFuenteFinanciamiento = idFuenteFinanciamiento;
    }

    @Basic
    @Column(name = "idOrganismoFinanciador")
    public Long getIdOrganismoFinanciador() {
        return idOrganismoFinanciador;
    }

    public void setIdOrganismoFinanciador(Long idOrganismoFinanciador) {
        this.idOrganismoFinanciador = idOrganismoFinanciador;
    }

    @Basic
    @Column(name = "codigo")
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueryFuenteOrgFinanPartidasTramite that = (QueryFuenteOrgFinanPartidasTramite) o;
        return Objects.equals(id, that.id) && Objects.equals(idFuenteFinanciamiento, that.idFuenteFinanciamiento) && Objects.equals(idOrganismoFinanciador, that.idOrganismoFinanciador) && Objects.equals(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idFuenteFinanciamiento, idOrganismoFinanciador, codigo);
    }
}
