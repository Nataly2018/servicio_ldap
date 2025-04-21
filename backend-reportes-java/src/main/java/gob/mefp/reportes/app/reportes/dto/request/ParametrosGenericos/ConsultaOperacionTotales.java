/**
 * Project: nova
 * Package: bo.gob.ypfb.nova
 * El archivo "VPoaTrazaGeneral.java" fue creado para el proyecto nova
 * por Y.P.F.B. Corporación, todos los Derechos reservados.
 *
 * @author: Roger Diego Flores Condori
 * @email : cons.rflores@ypfb.gob.bo
 * @date: 19/9/2023 08:51
 * @copyright: YPFB
 * @version: 1.0
 **/
package gob.mefp.reportes.app.reportes.dto.request.ParametrosGenericos;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.math.BigDecimal;


public class ConsultaOperacionTotales {
    private Integer cantidad;
    private BigDecimal gastoCorrienteOperacion;
    private BigDecimal inversionOperacion;
    private BigDecimal presTotalGestion;

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Basic
    @Column(name = "gasto_corriente_operacion")
    public BigDecimal getGastoCorrienteOperacion() {
        return gastoCorrienteOperacion;
    }

    public void setGastoCorrienteOperacion(BigDecimal gastoCorrienteOperacion) {
        this.gastoCorrienteOperacion = gastoCorrienteOperacion;
    }
    @Basic
    @Column(name = "inversion_operacion")
    public BigDecimal getInversionOperacion() {
        return inversionOperacion;
    }

    public void setInversionOperacion(BigDecimal inversionOperacion) {
        this.inversionOperacion = inversionOperacion;
    }
    @Basic
    @Column(name = "pres_total_gestion")
    public BigDecimal getPresTotalGestion() {
        return presTotalGestion;
    }

    public void setPresTotalGestion(BigDecimal presTotalGestion) {
        this.presTotalGestion = presTotalGestion;
    }
}
