package gob.mefp.reportes.app.reportes.dto.response;

import gob.ypfb.nova.app.administracion.dto.query.QueryObtenerDatosCabeceraDto;
import lombok.Data;

import java.util.List;
@Data
public class ReporteOperacionesPorAcpJsonDtoResp {
    private QueryObtenerDatosCabeceraDto cabecera;
    private List<ListaOperacionesPorAcpDto> datos;
}
