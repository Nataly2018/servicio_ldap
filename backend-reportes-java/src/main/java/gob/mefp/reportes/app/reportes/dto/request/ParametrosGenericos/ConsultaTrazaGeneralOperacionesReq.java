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

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@EqualsAndHashCode(callSuper=true)
public class ConsultaTrazaGeneralOperacionesReq extends ParametrosConsultaGeneral {
    private Long operIdAccionCortoPlazo;
    private String operAccionCortoPlazo;
    private List<ConsultaArea> areas = new ArrayList<>();
    private List<ConsultaOperacion> operaciones = new ArrayList<>();

}
