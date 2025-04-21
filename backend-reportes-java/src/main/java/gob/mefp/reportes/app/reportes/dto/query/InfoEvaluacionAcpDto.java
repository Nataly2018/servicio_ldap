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

import gob.ypfb.nova.config.ConstanteSeguimiento;
import gob.ypfb.nova.utils.calculo.Operaciones;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;


@Data
public class InfoEvaluacionAcpDto {
    private ConstanteSeguimiento.EnumTipoRegistroEvaluacion enumTipoRegistro;
    List<EvaluacionAcpDto> lstDatosEvaluacion;
}
