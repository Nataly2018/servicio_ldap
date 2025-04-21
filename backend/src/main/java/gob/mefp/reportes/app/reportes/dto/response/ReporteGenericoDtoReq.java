package gob.mefp.reportes.app.reportes.dto.response;

import com.nimbusds.jose.shaded.gson.JsonArray;
import com.nimbusds.jose.shaded.gson.JsonObject;
import gob.mefp.reportes.app.reportes.dto.query.QueryObtenerDatosCabeceraGenericoDto;
import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

@Data
public class ReporteGenericoDtoReq {

    private QueryObtenerDatosCabeceraGenericoDto cabecera;
    private Object datos;

    public QueryObtenerDatosCabeceraGenericoDto getCabecera() {
        return cabecera;
    }

    public Object getDatos() {
        return datos;
    }

    public void setCabecera(QueryObtenerDatosCabeceraGenericoDto cabecera) {
        this.cabecera = cabecera;
    }

    public void setDatos(Object datos) {
        this.datos = datos;
    }
}
