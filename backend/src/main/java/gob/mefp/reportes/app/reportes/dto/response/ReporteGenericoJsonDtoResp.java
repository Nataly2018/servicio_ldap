package gob.mefp.reportes.app.reportes.dto.response;


import gob.mefp.reportes.app.reportes.dto.query.QueryObtenerDatosCabeceraGenericoDto;
import lombok.Data;

import java.util.List;

@Data
public class ReporteGenericoJsonDtoResp {
    private QueryObtenerDatosCabeceraGenericoDto cabecera;
    private List<ListaDto> datos;

    public void setCabecera(QueryObtenerDatosCabeceraGenericoDto cabecera) {
        this.cabecera = cabecera;
    }

    public void setDatos(List<ListaDto> datos) {
        this.datos = datos;
    }
}
