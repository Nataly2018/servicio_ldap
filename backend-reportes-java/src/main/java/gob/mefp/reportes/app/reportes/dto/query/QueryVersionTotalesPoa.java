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

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.util.Objects;

@Data
public class QueryVersionTotalesPoa {
    private Integer codigoRespuesta;
    private String mensaje;
}
