/**
 * Project: nova
 * Package: bo.gob.ypfb.nova
 * El archivo "IConsultasQuery.java" fue creado para el proyecto nova
 * por Y.P.F.B. Corporación, todos los Derechos reservados.
 *
 * @author: Roger Diego Flores Condori
 * @email : cons.rflores@ypfb.gob.bo
 * @date: 8/9/2023 13:48
 * @copyright: YPFB
 * @version: 1.0
 **/

package gob.mefp.reportes.app.reportes.query;

import gob.ypfb.nova.app.reportes.dto.query.*;
import gob.ypfb.nova.app.reportes.dto.request.ParametrosGenericos.ConsultaTrazaGeneralConsultoriaReq;
import gob.ypfb.nova.app.reportes.dto.request.ParametrosGenericos.ConsultaTrazaGeneralOperacionesReq;
import gob.ypfb.nova.app.reportes.dto.request.ParametrosGenericos.ConsultaTrazaGeneralPresupuestoReq;
import gob.ypfb.nova.app.reportes.dto.request.ParametrosGenericos.ConsultaTrazaGeneralTareasReq;
import gob.ypfb.nova.app.reportes.dto.response.ConsultaConsultoriaDtoResp;
import gob.ypfb.nova.app.reportes.dto.response.ConsultaOperacionesDtoResp;
import gob.ypfb.nova.app.reportes.dto.response.ConsultaPresupuestoDtoResp;
import gob.ypfb.nova.app.reportes.dto.response.ConsultaTareasDtoResp;

import java.util.List;

public interface IConsultasQuery {

    List<QueryAreaPorPoa> listaUnidadesPorPoa(Long idPoa);

    List<QueryOperacionesPorPoa> listaOperacionesPorPoa(Long idPoa);

    List<QueryTareasPorPoa> listaTareasPorPoa(Long idPoa);

    //QueryTipoTareasPorPoa
    List<QueryTipoTareasPorPoa> listaTipoTareasPorVersionPoa(Long idPoa);

    List<QueryTipoPresupuestoPorPoa> listaTipoPresupuestoPorVersionPoa(Long idVersionPoa);

    //QueryAreasResponsablesPorPoa
    List<QueryAreasResponsablesPorPoa> listaAreasResponsablesPorPoa(Long idPoa);

    List<QueryCategoriaProgramaticaPorPoa> listaCategoriaProgramaticaPorPoa(Long idPoa);

    List<QueryDireccionAdministrativaPorPoa> listaDireccionAdminPorPoa(Long idPoa);

    List<QueryPartidaPresupuestariaPorPoa> listaPartidadPresupuestariaPorPoa(Long idPoa);

    List<QueryUnidadEjecutoraPorPoa> listaUnidadEjcutoraPorPoa(Long idPoa);

    List<QueryFuenteOrgFinanPorPoa> listaFuenteOrgFinanPorPoa(Long idPoa);

    List<QueryVersionPoa> listaVersionesPoaPorPoa(Long idPoa);

    List<QueryVersionPoa> listaVersionesPoaUltimoMigrado();

    List<QueryResponsableConsulta> listaRolConsulta(String nombreUsuario, String codigo);

    List<QueryAccionCortoPlazoPorPoa> listaAccionCortoPlazoPorVersion(Long idVersionPoa);

    List<QueryAreasResponsablesPorPoa> listaAreasValidadoreasConsultoriaPorPoa(Long idVersionPoa);

    ConsultaOperacionesDtoResp consultaGeneralOperacion(ConsultaTrazaGeneralOperacionesReq consultaTrazaGeneralOperacionesReq);

    ConsultaTareasDtoResp consultaGeneralTarea(ConsultaTrazaGeneralTareasReq consultaTrazaGeneralTareasReq);

    ConsultaPresupuestoDtoResp consultaGeneralPresupuesto(ConsultaTrazaGeneralPresupuestoReq consultaTrazaGeneralPresupuestoReq);

    ConsultaConsultoriaDtoResp consultaGeneralConsultoria(
            ConsultaTrazaGeneralConsultoriaReq consultaTrazaGeneralConsultoriaReq);

    List<QueryVersionTotalesPoa> crearVersionTotales(Long idPoaArea, Integer pVersion, String pCodigoVersion);

    List<QueryResponsableConsulta> listaRolConsultaRolAreas(String nombreUsuario, String codigo);

    List<QueryResponsableConsulta> listaRolConsultaRolGeneral(String nombreUsuario, String codigo);


}
