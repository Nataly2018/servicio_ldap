/**
 * Project: nova
 * Package: bo.gob.ypfb.nova
 * El archivo "ConsultaOperacionesDtoResp.java" fue creado para el proyecto nova
 * por Y.P.F.B. Corporación, todos los Derechos reservados.
 *
 * @author: Roger Diego Flores Condori
 * @email : cons.rflores@ypfb.gob.bo
 * @date: 19/9/2023 11:29
 * @copyright: YPFB
 * @version: 1.0
 **/
package gob.mefp.reportes.app.reportes.dto.response;

import gob.ypfb.nova.app.reportes.dto.query.ConsultaPresupuestoConTotales;
import gob.ypfb.nova.app.reportes.dto.query.ConsultaTareasConTotales;
import gob.ypfb.nova.app.reportes.dto.request.ParametrosGenericos.ConsultaPresupuestoTotales;
import gob.ypfb.nova.app.reportes.dto.request.ParametrosGenericos.ConsultaTareaTotales;
import gob.ypfb.nova.app.reportes.dto.request.ParametrosGenericos.ConsultaTrazaGeneralPresupuestoReq;
import gob.ypfb.nova.app.reportes.dto.request.ParametrosGenericos.ConsultaTrazaGeneralTareasReq;
import lombok.Data;

import java.util.List;

@Data
public class ConsultaPresupuestoDtoResp {
    List<ConsultaPresupuestoConTotales> detalle;
    ConsultaPresupuestoTotales total;
    ConsultaTrazaGeneralPresupuestoReq request;
}
