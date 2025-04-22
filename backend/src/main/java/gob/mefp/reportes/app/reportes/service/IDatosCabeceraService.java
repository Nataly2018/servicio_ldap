package gob.mefp.reportes.app.reportes.service;

import gob.mefp.reportes.app.administracion.dto.query.QueryObtenerDatosCabeceraDto;

public interface IDatosCabeceraService {
    QueryObtenerDatosCabeceraDto obtenerDatosCabeceraDto(String pIdVersion, String pCodigoReporte, String pNombreSistema, String pTituloReporte, String pSubTituloReporte, String pDescripcion);
}
