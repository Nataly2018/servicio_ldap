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

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper=true)
public class ConsultaTrazaGeneralConsultoriaReq extends ParametrosConsultaGeneral {
    private Long operIdAccionCortoPlazo;
    private String operAccionCortoPlazo;
    private List<ConsultaArea> areas = new ArrayList<>();
    private List<ConsultaOperacion> operaciones = new ArrayList<>();
    private Long tarIdTipoTarea;
    private String tarTipoTarea;
    private List<ConsultaTarea> tareas = new ArrayList<>();
    private List<ConsultaAreaResponsable> areasResponsables = new ArrayList<>();
    private Long presIdTipo;
    private String presTipoPresupuesto;
    private List<ConsultaCategoriaProgramatica> categorias = new ArrayList<>();
    private List<ConsultaPartidaPresupuestaria> partidas = new ArrayList<>();
    private Long presIdFuenteOrganismoFinanciador;
    private String presCodigoFuenteOrganismoFinanciador;
    private String presFuenteOrganismoFinanciador;
    private List<ConsultaUnidadEjecutora> unidadEjecutora = new ArrayList<>();
    private Long direccionAdminId;
    private String direccionAdminCodigo;
    private String direccionAdmin;
    private String presDetalleGasto;
    private List<ConsultaArea> areasResponsableConsultoria = new ArrayList<>();
}
