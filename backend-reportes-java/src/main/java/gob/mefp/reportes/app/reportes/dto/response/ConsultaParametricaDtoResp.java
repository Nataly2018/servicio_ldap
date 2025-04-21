/**
 * Project: nova
 * Package: bo.gob.ypfb.nova
 * El archivo "CortoPlazoResp.java" fue creado para el proyecto nova
 * por Y.P.F.B. Corporación, todos los Derechos reservados.
 *
 * @author: Roger Diego Flores Condori
 * @email : cons.rflores@ypfb.gob.bo
 * @date: 5/24/2023 2:47 p. m.
 * @copyright: YPFB
 * @version: 1.0
 **/
package gob.mefp.reportes.app.reportes.dto.response;

import lombok.Data;

@Data
public class ConsultaParametricaDtoResp {
    private Long id;
    private Integer codigoArea;
    private String sigla;
    private String descripcion;
    private String codigoOperacion;

}
