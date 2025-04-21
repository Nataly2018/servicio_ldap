package gob.mefp.reportes.app.reportes.service;

import gob.mefp.reportes.app.reportes.dto.response.ReporteGenericoJsonDtoReq;
import gob.mefp.reportes.utils.excel.ExcelConstantes;
import javax.servlet.http.HttpServletResponse;


public interface IReporteAuthService {
    void exportarReporte(HttpServletResponse pHttpServletResponse
            , ExcelConstantes.TipoArchivoExportacion pTipoArchivoExportacion
            , ReporteGenericoJsonDtoReq pReporteGenericoJsonDtoReq);
    //String prepararDatosReporte(ReporteGenericoJsonDtoReq pReporteGenericoJsonDtoReq);
}
