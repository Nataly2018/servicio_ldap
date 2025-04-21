package gob.mefp.reportes.app.reportes.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.gson.JsonArray;
import com.nimbusds.jose.shaded.gson.JsonObject;
import com.nimbusds.jose.shaded.gson.JsonParser;
import gob.mefp.reportes.app.reportes.dto.ReporteGenericoResultanteDto;
import gob.mefp.reportes.app.reportes.dto.query.QueryObtenerDatosCabeceraDto;
import gob.mefp.reportes.app.reportes.dto.query.QueryObtenerDatosCabeceraGenericoDto;
import gob.mefp.reportes.app.reportes.dto.response.ListaDto;
import gob.mefp.reportes.app.reportes.dto.response.ReporteGenericoDtoReq;
import gob.mefp.reportes.app.reportes.dto.response.ReporteGenericoJsonDtoReq;
import gob.mefp.reportes.app.reportes.dto.response.ReporteGenericoJsonDtoResp;
import gob.mefp.reportes.exception.IntegridadMefpException;
import gob.mefp.reportes.utils.excel.ExcelConstantes;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.query.JsonQueryExecuterFactory;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
//@Slf4j
public class ReporteAuthService implements IReporteAuthService  {

    @Autowired
    IReportesGenericoService vIReportesGenericoService;

    //@Override
    public void exportarReporte(HttpServletResponse pHttpServletResponse
            , ExcelConstantes.TipoArchivoExportacion pTipoArchivoExportacion
            , ReporteGenericoJsonDtoReq pReporteGenericoJsonDtoReq) {
        try {
            String cadenaJsonDeOrden = this.prepararDatosReporte(pReporteGenericoJsonDtoReq);
            //String cadenaJsonDeOrden = pReporteGenericoJsonDtoReq.getDatos();

            String ruta = "reportes/rpt001_parametricas.jasper";
            String directorio = new ClassPathResource("reportes/rpt001_parametricas.jasper").getURI().getPath();

            if (directorio != null) {
                directorio = directorio.replace("/rpt001_parametricas.jasper", "");
            } else {
                directorio = "reportes";
            }

            InputStream fis = new ClassPathResource(ruta).getInputStream();
            Map<String, Object> params = new HashMap<>();
            params.put("path", directorio+"/");
            params.put(JsonQueryExecuterFactory.JSON_INPUT_STREAM, new ByteArrayInputStream(cadenaJsonDeOrden.getBytes("UTF-8")));
            params.put(JsonQueryExecuterFactory.JSON_LOCALE, new Locale("en", "US", "POSIX"));//. "en_US_POSIX"

            JasperPrint jasperPrint = JasperFillManager.fillReport(fis, params);
            String nombreArchivo = "reporte_generico";
            if(pTipoArchivoExportacion.equals(ExcelConstantes.TipoArchivoExportacion.PDF)) {
                vIReportesGenericoService.generarPdf(jasperPrint, pHttpServletResponse, nombreArchivo);
            }

        } catch (IOException | JRException e) {
            e.printStackTrace();
        }
    }

    private String prepararDatosReporte(ReporteGenericoJsonDtoReq pReporteGenericoJsonDtoReq) throws JsonProcessingException {

        // Convertir la cadena de entrada en el campo datos del objeto de entrada
        ObjectMapper objectMapper = new ObjectMapper();

        // Mapear la cabecera
        JsonObject nuevaCabecera = new JsonObject();
        mapearCabecera(pReporteGenericoJsonDtoReq, nuevaCabecera);

        // Convertir el campo "datos" de String JSON a un JsonArray
        String datosString = pReporteGenericoJsonDtoReq.getDatos(); // Obtener datos como String
        JsonArray datosArray = JsonParser.parseString(datosString).getAsJsonArray();

        // Construir el JSON de salida con "data"
        JsonObject resultado = new JsonObject();
        JsonObject data = new JsonObject();
        data.add("cabecera", nuevaCabecera);
        data.add("datos", datosArray);
        resultado.add("data", data);

        // Convertir a JSON String y mostrar el resultado
        String jsonOutput = objectMapper.writeValueAsString(objectMapper.readTree(resultado.toString()));
        System.out.println(jsonOutput);

        return jsonOutput;
    }

    private static void mapearCabecera(ReporteGenericoJsonDtoReq pReporteGenericoJsonDtoReq, JsonObject nuevaCabecera) {
        QueryObtenerDatosCabeceraGenericoDto vQueryObtenerDatosCabeceraGenericoDto;
        vQueryObtenerDatosCabeceraGenericoDto = pReporteGenericoJsonDtoReq.getCabecera();
        nuevaCabecera.addProperty("nombreUnidad", vQueryObtenerDatosCabeceraGenericoDto.getNombreUnidad());
        nuevaCabecera.addProperty("nombreSistema", vQueryObtenerDatosCabeceraGenericoDto.getNombreSistema());
        nuevaCabecera.addProperty("codigoReporte", vQueryObtenerDatosCabeceraGenericoDto.getCodigoReporte());
        nuevaCabecera.addProperty("tituloReporte", vQueryObtenerDatosCabeceraGenericoDto.getTituloReporte());
        nuevaCabecera.addProperty("usuario", vQueryObtenerDatosCabeceraGenericoDto.getUsuario());
        nuevaCabecera.addProperty("fechaImpresion", vQueryObtenerDatosCabeceraGenericoDto.getFechaImpresion());
    }

    private String prepararDatosReporteReqTipado(ReporteGenericoJsonDtoReq pReporteGenericoJsonDtoReq){

        ReporteGenericoDtoReq vReporte = new ReporteGenericoDtoReq();
        //vReporte.setCabecera(pReporteGenericoJsonDtoReq.getCabecera());
        vReporte.setDatos(pReporteGenericoJsonDtoReq.getDatos());
        ReporteGenericoResultanteDto vRGenericoResultanteDto = new ReporteGenericoResultanteDto<ReporteGenericoJsonDtoReq>();
        vRGenericoResultanteDto.setData(vReporte);
        ObjectMapper objectMapper = new ObjectMapper();
        String cadenaJsonDeOrden = "";
        try {
            cadenaJsonDeOrden = objectMapper.writeValueAsString(vRGenericoResultanteDto);
            return cadenaJsonDeOrden;
        } catch (Exception exp) {
            throw new IntegridadMefpException("Error interno de casteo Json para el Reporte");
        }
    }
}
