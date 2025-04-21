package gob.mefp.reportes.app.reportes.dto.response;

import com.nimbusds.jose.shaded.gson.JsonObject;
import gob.mefp.reportes.app.reportes.dto.query.QueryObtenerDatosCabeceraGenericoDto;
import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

@Data
public class ReporteGenericoJsonDtoReq {

    private QueryObtenerDatosCabeceraGenericoDto cabecera;
    private String datos;

    public QueryObtenerDatosCabeceraGenericoDto getCabecera() {
        return cabecera;
    }

    public void setCabecera(QueryObtenerDatosCabeceraGenericoDto cabecera) {
        this.cabecera = cabecera;
    }

    public String getDatos() {
        return datos;
    }
    public void setDatos(String datos) {
        this.datos = datos;
    }
}
