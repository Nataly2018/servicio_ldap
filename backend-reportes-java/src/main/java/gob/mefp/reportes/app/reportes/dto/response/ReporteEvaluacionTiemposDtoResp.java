/**
 * Project: nova
 * Package: bo.gob.ypfb.nova
 * El archivo "ReporteEvaluacionTiemposDtoResp.java" fue creado para el proyecto nova
 * por Y.P.F.B. Corporación, todos los Derechos reservados.
 *
 * @author: Roger Diego Flores Condori
 * @email : cons.rflores@ypfb.gob.bo
 * @date: 10/4/2024 16:13
 * @copyright: YPFB
 * @version: 1.0
 **/
package gob.mefp.reportes.app.reportes.dto.response;

import gob.ypfb.nova.app.parametricas.dto.response.MesesDtoResp;
import lombok.Data;

import java.util.List;

@Data
public class ReporteEvaluacionTiemposDtoResp {
    List<MesesDtoResp> listaMeses;
    List<Integer> listaGestiones;
    Integer mesDefecto=0;
    Integer gestionDefecto=0;
}
