/**
 * Project: nova
 * Package: bo.gob.ypfb.nova
 * El archivo "IConsultaPametricaGeneralService.java" fue creado para el proyecto nova
 * por Y.P.F.B. Corporación, todos los Derechos reservados.
 *
 * @author: Roger Diego Flores Condori
 * @email : cons.rflores@ypfb.gob.bo
 * @date: 6/9/2023 11:04
 * @copyright: YPFB
 * @version: 1.0
 **/

package gob.mefp.reportes.app.reportes.service;

import gob.ypfb.nova.app.reportes.dto.request.*;
import gob.ypfb.nova.app.reportes.dto.request.ParametrosGenericos.*;
import gob.ypfb.nova.app.reportes.dto.response.*;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IConsultaPametricaGeneralService {
    List<ConsultaParametricaDtoResp> listaPoa();

    List<ConsultaParametricaDtoResp> listaVersionPoa(ConsultaVersionesPoaDtoReq pConsultaVersionesPoaDtoReq);

    List<ConsultaParametricaDtoResp> listaAccionesCortoPlazoPorPoa(ConsultaAccionCortoPlazoDtoReq pConsultaAccionCortoPlazoDtoReq);

    List<ConsultaParametricaDtoResp> listaUnidadesPorPoa(ConsultaUnidadesDtoReq pConsultaUnidadesDtoReq);

    List<ConsultaParametricaDtoResp> listaOperacionesPorPoa(ConsultaOperacionesDtoReq pConsultaUnidadesDtoReq);

    List<ConsultaParametricaDtoResp> listaTareasPorPoa(ConsultaTareasDtoReq pConsultaTareasDtoReq);

    List<ConsultaParametricaDtoResp> listaTipoTareasPorPoa(ConsultaTipoTareasDtoReq pConsultaTipoTareasDtoReq);

    List<ConsultaParametricaDtoResp> listaResponsablesTareasPorPoa(ConsultaResponsableTareasDtoReq pConsultaResponsableTareasDtoReq);

    List<ConsultaParametricaDtoResp> listaCategoriaProgamPorPoa(ConsultaCategProgramDtoReq pConsultaResponsableTareasDtoReq);

    List<ConsultaParametricaDtoResp> listaDireccionAdminPorPoa(ConsultaDirecAdminDtoReq pConsultaResponsableTareasDtoReq);

    List<ConsultaParametricaDtoResp> listaPartidaPresupuestariaPorPoa(ConsultaPartidaPresupDtoReq pConsultaResponsableTareasDtoReq);

    List<ConsultaParametricaDtoResp> listaUnidadEjecutoraPorPoa(ConsultaUnidadEjecturaDtoReq pConsultaResponsableTareasDtoReq);

    List<ConsultaParametricaDtoResp> listaUnidadEjecutoraPorArea(Long idArea);

    List<ConsultaParametricaDtoResp> listaTipoPresupuestoPorPoa(
            ConsultaTipoPresupuestoDtoReq consultaDtoReq);

    List<ConsultaParametricaDtoResp> listaFuenteOrgFiunaciadorPorPoa(ConsultaOrgFinanDtoReq pConsultaResponsableTareasDtoReq);

    List<ConsultaParametricaDtoResp> listaResponsablesAreasValidadorasPorPoa(
            ConsultaAreasValidadorasConsultoriaDtoReq pConsultaAreasValidadorasConsultoriaDtoReq);

    ConsultaOperacionesDtoResp consultaOperacion(
            ConsultaTrazaGeneralOperacionesReq pConsultaTrazaGeneralOperacionesReq);

    ConsultaTareasDtoResp consultaTareas(
            ConsultaTrazaGeneralTareasReq pConsultaTrazaGeneralOperacionesReq);

    ConsultaPresupuestoDtoResp consultaPresupuesto(
            ConsultaTrazaGeneralPresupuestoReq consultaTrazaGeneralPresupuestoReq);

    ConsultaConsultoriaDtoResp consultaConsultoria(
            ConsultaTrazaGeneralConsultoriaReq consultaTrazaGeneralConsultoriaReq);

    @Transactional(readOnly = true)
    void exportarConsultaDatosOperacionesEnExcel(ConsultaTrazaGeneralOperacionesReq pConsultaTrazaGeneralOperacionesReq, HttpServletResponse pHttpServletResponse);

    @Transactional(readOnly = true)
    void exportarConsultaDatosTareasEnExcel(ConsultaTrazaGeneralTareasReq pConsultaTrazaGeneralTareasReq, HttpServletResponse pHttpServletResponse);

    @Transactional(readOnly = true)
    void exportarConsultaDatosPresupuestoEnExcel(
            ConsultaTrazaGeneralPresupuestoReq pConsultaTrazaGeneralPresupuestoReq, HttpServletResponse pHttpServletResponse);

    @Transactional(readOnly = true)
    void exportarConsultaDatosConsultoriaEnExcel(
            ConsultaTrazaGeneralConsultoriaReq pConsultaTrazaGeneralConsultoriaReq, HttpServletResponse pHttpServletResponse);

    //enumerador del tipo de cosnulta
    ConsultaParametrosDefectoDtoResp obtenerValoresDefecto(String modulo);

    void realizarVolcadoVersionDetalle(CrearVersionDetalleReq pCrearVersionDetalleReq);

    void realizarVolcadoVersionTotales(CrearVersionDetalleReq pCrearVersionDetalleReq);
}
