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


public class ConsultaPresupuestoTotales {
    private Integer cantidad;
    private BigDecimal presTotalGestion;
    private BigDecimal presTotalGastoGestionesFuturas;
    private BigDecimal presTotalGestiones;

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Basic
    @Column(name = "pres_total_gestion")
    public BigDecimal getPresTotalGestion() {
        return presTotalGestion;
    }

    public void setPresTotalGestion(BigDecimal presTotalGestion) {
        this.presTotalGestion = presTotalGestion;
    }
    @Basic
    @Column(name = "pres_total_gasto_gestiones_futuras")
    public BigDecimal getPresTotalGastoGestionesFuturas() {
        return presTotalGastoGestionesFuturas;
    }

    public void setPresTotalGastoGestionesFuturas(BigDecimal presTotalGastoGestionesFuturas) {
        this.presTotalGastoGestionesFuturas = presTotalGastoGestionesFuturas;
    }
    @Basic
    @Column(name = "pres_total_gestiones")
    public BigDecimal getPresTotalGestiones() {
        return presTotalGestiones;
    }

    public void setPresTotalGestiones(BigDecimal presTotalGestiones) {
        this.presTotalGestiones = presTotalGestiones;
    }
}
