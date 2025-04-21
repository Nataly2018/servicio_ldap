package gob.mefp.reportes.app.reportes.service;

import gob.ypfb.nova.app.reportes.dto.query.QueryOperacionesPorAcp;
import gob.ypfb.nova.app.reportes.dto.query.QueryOperacionesPorArea;
import gob.ypfb.nova.app.reportes.dto.response.*;

import java.util.List;

public interface IReporteGeneralOperacionesPoaService {
    ReporteOperacionesPorAcpAreaDtoResp listarOperacionesPorAcpPoaArea(Long pIdVersionPoa, String pIdAcp, String pIdPoaArea);
    ReporteOperacionesPorAcpDtoResp listarOperacionesPorAcp(Long pIdVersionPoa, String pIdAcp);
    ReporteOperacionesPorAreaDtoResp listarOperacionesPorAreas(Long pIdVersionPoa, String pIdPoaArea);
    ReporteOperacionesPorAcpJsonDtoResp listarOperacionesPorAcpJson(Long pIdVersionPoa, String pIdAcp);
    ReporteOperacionesPorAreaJsonDtoResp listarOperacionesPorAreaJson(Long pIdVersionPoa, String pIdPoaAreas);
}
