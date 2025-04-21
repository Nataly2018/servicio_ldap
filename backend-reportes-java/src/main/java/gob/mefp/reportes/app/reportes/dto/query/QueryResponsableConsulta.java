/**
 * Project: nova
 * Package: bo.gob.ypfb.nova
 * El archivo "QueryResponsableConsulta.java" fue creado para el proyecto nova
 * por Y.P.F.B. Corporación, todos los Derechos reservados.
 *
 * @author: Roger Diego Flores Condori
 * @email : cons.rflores@ypfb.gob.bo
 * @date: 27/9/2023 14:05
 * @copyright: YPFB
 * @version: 1.0
 **/
package gob.mefp.reportes.app.reportes.dto.query;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;


public class QueryResponsableConsulta {
    private Long idArea;
    private String nombreUsuario;
    private Long idPoaArea;

    @Basic
    @Column(name = "id_area")
    public Long getIdArea() {
        return idArea;
    }

    public void setIdArea(Long idArea) {
        this.idArea = idArea;
    }

    @Basic
    @Column(name = "nombre_usuario")
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    @Basic
    @Column(name = "id_poa_area")
    public Long getIdPoaArea() {
        return idPoaArea;
    }

    public void setIdPoaArea(Long idPoaArea) {
        this.idPoaArea = idPoaArea;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueryResponsableConsulta that = (QueryResponsableConsulta) o;
        return Objects.equals(idArea, that.idArea) && Objects.equals(nombreUsuario, that.nombreUsuario) && Objects.equals(idPoaArea, that.idPoaArea);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idArea, nombreUsuario, idPoaArea);
    }
}
