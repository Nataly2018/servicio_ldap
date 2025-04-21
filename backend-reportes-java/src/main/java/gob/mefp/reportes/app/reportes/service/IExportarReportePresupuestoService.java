package gob.mefp.reportes.app.reportes.service;

import gob.ypfb.nova.utils.excel.ExcelConstantes;

import javax.servlet.http.HttpServletResponse;

public interface IExportarReportePresupuestoService {

   void exportarReportePresupuestoPorArea(HttpServletResponse pHttpServletResponse
            , ExcelConstantes.TipoArchivoExportacion pTipoArchivoExportacion, Long pIdVersion, String pIdPoaAreas, String pIdTipoGasto, String pIdTipoTarea);
   void exportarReportePresupuestoPorAcp(HttpServletResponse pHttpServletResponse
           , ExcelConstantes.TipoArchivoExportacion pTipoArchivoExportacion, Long pIdVersion, String pIdAcps, String pIdTipoGasto, String pIdTipoTarea);
   void exportarReporteCantidadOperacionesPorAcp(HttpServletResponse pHttpServletResponse
           , ExcelConstantes.TipoArchivoExportacion pTipoArchivoExportacion, Long pIdVersionPoa, String pIdAcp);
   void exportarReporteCantidadOperacionesPorArea(HttpServletResponse pHttpServletResponse
           , ExcelConstantes.TipoArchivoExportacion pTipoArchivoExportacion, Long pIdVersionPoa, String pIdAcp);
}
