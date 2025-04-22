package gob.mefp.reportes.app.reportes.service;

import gob.mefp.reportes.app.reportes.dto.response.*;
import gob.mefp.reportes.utils.excel.ExcelConstantes;

import javax.servlet.http.HttpServletResponse;


public interface IReporteGenericoService {
    void exportarReporteGenerico(HttpServletResponse pHttpServletResponse
            , ExcelConstantes.TipoArchivoExportacion pTipoArchivoExportacion
            , ReporteGenericoJsonDtoResp pReporteGenericoJsonDtoResp);
    void exportarReporteGenerico_1(HttpServletResponse pHttpServletResponse
            , ExcelConstantes.TipoArchivoExportacion pTipoArchivoExportacion);
    ReporteGenericoJsonDtoResp prepararDatosReporteJson(String pIdAcp);
}
