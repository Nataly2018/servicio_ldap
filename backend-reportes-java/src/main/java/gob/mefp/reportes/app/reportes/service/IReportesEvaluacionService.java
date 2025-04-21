/**
 * Project: nova
 * Package: bo.gob.ypfb.nova
 * El archivo "IReportesEvaluacionService.java" fue creado para el proyecto nova
 * por Y.P.F.B. Corporación, todos los Derechos reservados.
 *
 * @author: Roger Diego Flores Condori
 * @email : cons.rflores@ypfb.gob.bo
 * @date: 10/4/2024 16:10
 * @copyright: YPFB
 * @version: 1.0
 **/

package gob.mefp.reportes.app.reportes.service;

import gob.ypfb.nova.app.reportes.dto.query.EvaluacionAcpDto;
import gob.ypfb.nova.app.reportes.dto.query.EvaluacionAcpPonderacionDto;
import gob.ypfb.nova.app.reportes.dto.query.EvaluacionAcpPresupuestoDto;
import gob.ypfb.nova.app.seguimiento.dto.query.QueryCatalogoAcp;
import gob.ypfb.nova.app.seguimiento.dto.query.QueryIndicadoresEvaluacion;
import gob.ypfb.nova.app.seguimiento.dto.reporte.EvaluacionForm05MainJsonDto;
import gob.ypfb.nova.app.seguimiento.dto.request.*;
import gob.ypfb.nova.app.seguimiento.dto.excel.EvaluacionAcpGeneralJsonDto;
import gob.ypfb.nova.app.seguimiento.dto.response.EvaluacionEvaluarParametrosReporteDtoResp;
import gob.ypfb.nova.app.reportes.dto.response.ReporteEvaluacionTiemposDtoResp;
import gob.ypfb.nova.utils.excel.ExcelConstantes;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IReportesEvaluacionService {
    ReporteEvaluacionTiemposDtoResp parametrosTiempo();

    EvaluacionEvaluarParametrosReporteDtoResp evaluarPeriodoReporte(EvaluacionEvaluarParametrosReporteDtoReq parametros);

    List<EvaluacionAcpDto> cargarPorcentajesAcp(Integer gestion, Integer mes);

    List<EvaluacionAcpDto> cargarPresupuestoAcp(Integer gestion,Integer mes);

    void guardarPonderaciones(Integer gestion ,Integer mes , List<EvaluacionPonderacionDtoReq> datos);

    void guardarPrespuesto(Integer gestion,Integer mes, List<EvaluacionPrespuestoDtoReq> datos);

    //generar datos
    @Transactional(readOnly = true)
    void reporteExcelAcpPorOperaciones(Integer mes, Integer gestion, HttpServletResponse pHttpServletResponse);

    @Transactional(readOnly = true)
    void reporteExcelAcpPorMacroAreas(Integer mes, Integer gestion, HttpServletResponse pHttpServletResponse);

    @Transactional(readOnly = true)
    void reporteExcelAcpPorTipoAreas(Integer mes, Integer gestion, HttpServletResponse pHttpServletResponse);

    List<QueryCatalogoAcp> obtenerListaAcp(Integer gestion);

    @Transactional
    void eliminarProgramacionIndicadores(EvaluacionElimIndicadorDtoReq datos);

    @Transactional
    void guardarProgramacionIndicadores(EvaluacionProgramacionDtoReq datos);


    @Transactional
    void guardarEjecucionIndicadores(EvaluacionEjecucionMainDtoReq datos);

    List<QueryIndicadoresEvaluacion> obtenerIndicadoresEvaluacion(Long idAcp,  Integer mes);

    List<QueryIndicadoresEvaluacion> obtenerIndicadoresEvaluacionGestion(Integer mes, Integer gestion);

    List<QueryIndicadoresEvaluacion> obtenerIndicadoresEvaluacionManual(Long idAcp, Integer mes);
    /* @Override
         @Transactional(readOnly = true)
         public void reporteExcelAcpPorOperacionesPdf(Integer mes, Integer gestion, HttpServletResponse pHttpServletResponse){
             vFabricReporteEvaluacionPoa.builder()
                     .gestion(gestion)
                     .mes(mes)
                     .inicializarAcp()
                     .contruirgenerarReporte01H01AcpOper()
                     .contruirgenerarReporte01H02AcpPonderacion()
                     .contruirgenerarReporte01H03AcpEjecucionPres();
             construirHojaReporte01H01Json();

         }*/
     @Transactional(readOnly = true)
     EvaluacionAcpGeneralJsonDto generaJsonReporteojaReporte01H01(Integer mes, Integer gestion);

    void generaReporteEvaluacionPdf(Integer mes, Integer gestion,
                                    HttpServletResponse pHttpServletResponse,
                                    ExcelConstantes.TipoArchivoExportacion pTipoArchivoExportacion);


    @Transactional(readOnly = true)
    void reporteFormulario05(Integer mes, Integer gestion, HttpServletResponse pHttpServletResponse, ExcelConstantes.TipoArchivoExportacion tipo);

    @Transactional(readOnly = true)
    EvaluacionForm05MainJsonDto reporteFormulario05Json(Integer mes, Integer gestion);
}
