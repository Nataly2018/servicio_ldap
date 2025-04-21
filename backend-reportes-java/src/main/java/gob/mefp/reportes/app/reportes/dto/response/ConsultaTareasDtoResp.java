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

import gob.ypfb.nova.app.reportes.dto.query.ConsultaOperacionesConTotales;
import gob.ypfb.nova.app.reportes.dto.query.ConsultaTareasConTotales;
import gob.ypfb.nova.app.reportes.dto.request.ParametrosGenericos.ConsultaOperacionTotales;
import gob.ypfb.nova.app.reportes.dto.request.ParametrosGenericos.ConsultaTareaTotales;
import gob.ypfb.nova.app.reportes.dto.request.ParametrosGenericos.ConsultaTrazaGeneralOperacionesReq;
import gob.ypfb.nova.app.reportes.dto.request.ParametrosGenericos.ConsultaTrazaGeneralTareasReq;
import lombok.Data;

import java.util.List;

@Data
public class ConsultaTareasDtoResp {
    List<ConsultaTareasConTotales> detalle;
    ConsultaTareaTotales total;
    ConsultaTrazaGeneralTareasReq request;
}
