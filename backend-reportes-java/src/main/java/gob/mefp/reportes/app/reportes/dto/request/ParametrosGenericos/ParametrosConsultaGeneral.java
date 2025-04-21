/**
 * Project: nova
 * Package: bo.gob.ypfb.nova
 * El archivo "ParametrosConsultaGeneral.java" fue creado para el proyecto nova
 * por Y.P.F.B. Corporación, todos los Derechos reservados.
 *
 * @author: Roger Diego Flores Condori
 * @email : cons.rflores@ypfb.gob.bo
 * @date: 19/9/2023 10:43
 * @copyright: YPFB
 * @version: 1.0
 **/
package gob.mefp.reportes.app.reportes.dto.request.ParametrosGenericos;

import lombok.Data;

@Data
public  abstract class ParametrosConsultaGeneral {
    private Long idVersionPoa;
    private Long idPoa;
    String descripcionPoa;
    String descripcionVersionPoa;
    Integer resultadosPorPagina;
    Integer pagina;
}
