/**
 * Project: nova
 * Package: bo.gob.ypfb.nova
 * El archivo "ConsultaOperacionesConTotales.java" fue creado para el proyecto nova
 * por Y.P.F.B. Corporación, todos los Derechos reservados.
 *
 * @author: Roger Diego Flores Condori
 * @email : cons.rflores@ypfb.gob.bo
 * @date: 20/9/2023 11:51
 * @copyright: YPFB
 * @version: 1.0
 **/
package gob.mefp.reportes.app.reportes.dto.query;

import gob.ypfb.nova.utils.calculo.Operaciones;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class EvaluacionAcpPresupuestoDto {
    private Long id;
    private Long acpId;
    private Integer acpCodigo;
    private String acpDescripcion;
    private BigDecimal presGcProg;
    private BigDecimal presInvProg;
    private BigDecimal presGcEjec;
    private BigDecimal presInvEjec;
    private BigDecimal presTotalProg;
    private BigDecimal presTotalEjec;
    private BigDecimal porcentajeEjecucion;

    public void calcularTotales(){
        presTotalProg = Operaciones.sumar(presGcProg,presInvProg);
        presTotalEjec = Operaciones.sumar(presGcEjec,presInvEjec);
        porcentajeEjecucion = Operaciones.dividir(presTotalEjec,presTotalProg);
    }
}
