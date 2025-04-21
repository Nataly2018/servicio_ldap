package gob.mefp.reportes.app.reportes.service;

import gob.ypfb.nova.app.reportes.dto.query.QueryTareasPorAcpPoaAreaOperacion;
import gob.ypfb.nova.app.reportes.dto.response.*;

import java.util.List;

public interface IReporteGeneralTareasPoaService {
    ReporteTareaPorAcpAreaOperacionDtoResp listarTareasPorAcpPoaAreaOperacion(Long pIdVersionPoa, String pIdPoa, String pIdAcp);
    ReporteTareaPorAcpAreaDtoResp listarTareasPorAcpPoaArea(Long pIdVersionPoa, String pIdAcp, String pIdPoaArea);
    ReporteTareaPorAcpDtoResp listarTareasPorAcp(Long pIdVersionPoa, String pIdAcp);
    ReporteTareaPorAreaDtoResp listarTareasPorAreas(Long pIdVersionPoa, String pIdAreas);
    List<ReporteCantidadTareasPorTipoDto> listarTareasPorTipo(Long pIdVersionPoa, String pIdPoaArea);
}
