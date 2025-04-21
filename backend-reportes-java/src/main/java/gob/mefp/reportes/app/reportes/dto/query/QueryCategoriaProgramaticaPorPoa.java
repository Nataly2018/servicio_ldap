/**
 * Project: nova
 * Package: bo.gob.ypfb.nova
 * El archivo "QueryCategoriaProgramaticaPorPoa.java" fue creado para el proyecto nova
 * por Y.P.F.B. Corporación, todos los Derechos reservados.
 *
 * @author: Roger Diego Flores Condori
 * @email : cons.rflores@ypfb.gob.bo
 * @date: 11/9/2023 12:50
 * @copyright: YPFB
 * @version: 1.0
 **/
package gob.mefp.reportes.app.reportes.dto.query;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

public class QueryCategoriaProgramaticaPorPoa {
    private Long id;
    private String programa;
    private String proyecto;
    private String actividad;

    @Basic
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "programa")
    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    @Basic
    @Column(name = "proyecto")
    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    @Basic
    @Column(name = "actividad")
    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueryCategoriaProgramaticaPorPoa that = (QueryCategoriaProgramaticaPorPoa) o;
        return Objects.equals(id, that.id) && Objects.equals(programa, that.programa) && Objects.equals(proyecto, that.proyecto) && Objects.equals(actividad, that.actividad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, programa, proyecto, actividad);
    }
}
