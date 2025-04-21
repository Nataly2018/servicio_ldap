package gob.mefp.reportes.app.reportes.service;

import gob.ypfb.nova.app.formulacion.dto.request.ReporteGeneralDtoReq;
import gob.ypfb.nova.app.reportes.dto.request.ReporteGeneralPorParametrosDtoReq;
import gob.ypfb.nova.app.reportes.dto.request.ReportePresupuestoPorAcpJsonDtoReq;
import gob.ypfb.nova.app.reportes.dto.request.ReportePresupuestoPorAreaDtoReq;
import gob.ypfb.nova.app.reportes.dto.request.ReportePresupuestoPorAreaJsonDtoReq;
import gob.ypfb.nova.app.reportes.dto.response.QueryReportePorParametrosListDto;
import gob.ypfb.nova.app.reportes.dto.response.ReportePresupuestoPorAcpDtoResp;
import gob.ypfb.nova.app.reportes.dto.response.ReportePresupuestoPorAreaDtoResp;

public interface IReporteGeneralPresupuestoService {
    QueryReportePorParametrosListDto obtenerPresupuestoGeneralPorParametros(ReporteGeneralDtoReq pReporteGeneralDtoReq);
    QueryReportePorParametrosListDto obtenerPresupuestoPorAcp(ReporteGeneralPorParametrosDtoReq vReporteGeneralDtoReq);
    QueryReportePorParametrosListDto obtenerPresupuestoPorArea(ReportePresupuestoPorAreaDtoReq vReportePresupuestoPorAreaDtoReq);
    ReportePresupuestoPorAreaDtoResp obtenerPresupuestoPorAreaJson(ReportePresupuestoPorAreaJsonDtoReq vReportePresupuestoPorAreaJsonDtoReq);
    ReportePresupuestoPorAcpDtoResp obtenerPresupuestoPorAcpJson(ReportePresupuestoPorAcpJsonDtoReq vReportePresupuestoPorAcpJsonDtoReq);
}
