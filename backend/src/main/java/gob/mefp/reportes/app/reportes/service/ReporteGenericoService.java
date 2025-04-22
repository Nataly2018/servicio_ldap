package gob.mefp.reportes.app.reportes.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import gob.mefp.reportes.app.reportes.dto.ReporteGenericoResultanteDto;
import gob.mefp.reportes.app.reportes.dto.query.QueryObtenerDatosCabeceraDto;
import gob.mefp.reportes.app.reportes.dto.query.QueryObtenerDatosCabeceraGenericoDto;
import gob.mefp.reportes.app.reportes.dto.response.*;
import gob.mefp.reportes.exception.IntegridadMefpException;
import gob.mefp.reportes.utils.excel.ExcelConstantes;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.query.JsonQueryExecuterFactory;
import net.sf.jasperreports.renderers.SimpleDataRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

@Service
@Slf4j
public class ReporteGenericoService implements IReporteGenericoService {

    //@Autowired
    //private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    IReportesGenericoService vIReportesGenericoService;

    @Override
    public void exportarReporteGenerico(HttpServletResponse pHttpServletResponse
            , ExcelConstantes.TipoArchivoExportacion pTipoArchivoExportacion
            , ReporteGenericoJsonDtoResp pReporteGenericoJsonDtoResp) {

        try {
            String cadenaJsonDeOrden = this.prepararDatosReporte(pReporteGenericoJsonDtoResp);

            String ruta = "reportes/main.jasper";
            String directorio = new ClassPathResource("reportes/main.jasper").getURI().getPath();
            if (directorio != null) {
                directorio = directorio.replace("/main.jasper", "");
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

    @Override
    public void exportarReporteGenerico_1(HttpServletResponse pHttpServletResponse
            , ExcelConstantes.TipoArchivoExportacion pTipoArchivoExportacion) {
        try {
            String cadenaJsonDeOrden = this.prepararDatosReporte_1("1");
            String ruta = "reportes/main.jasper";
            String directorio = new ClassPathResource("reportes/main.jasper").getURI().getPath();

            if (directorio != null) {
                directorio = directorio.replace("/main.jasper", "");
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

    public ReporteGenericoJsonDtoResp prepararDatosReporteJson(String pIdAcp){
        //ReporteJsonOperaciones vReporteJsonOperaciones = operacionesJson(idPoaArea,tipoVersionDatos);

        ReporteGenericoJsonDtoResp vReporte = new ReporteGenericoJsonDtoResp();
        //Agregar la cabecera
        QueryObtenerDatosCabeceraGenericoDto cabecera = new QueryObtenerDatosCabeceraGenericoDto();
        cabecera.setNombreUnidad("UNIDAD UTI");
        cabecera.setCodigoReporte("UNIDAD UTI");
        cabecera.setTituloReporte("UNIDAD UTI");
        cabecera.setUsuario("UNIDAD UTI");
        cabecera.setFechaImpresion("UNIDAD UTI");
        vReporte.setCabecera(cabecera);
        //Agregar los datos
        List<ListaDto> datos = new ArrayList<>();
        ListaDto vListaDto=new ListaDto();
        vListaDto.setNombre("Juan");
        vListaDto.setEdad("35");
        vListaDto.setFechaNacimiento("10/03/2025");
        datos.add(vListaDto);

        vReporte.setDatos(datos);
        try {
            return vReporte;
        } catch (Exception exp) {
            throw new IntegridadMefpException("Error interno de casteo Json para el Reporte");
        }
    }

    private String prepararDatosReporte(ReporteGenericoJsonDtoResp pReporteGenericoJsonDtoResp){
        //ReporteJsonOperaciones vReporteJsonOperaciones = operacionesJson(idPoaArea,tipoVersionDatos);

        ReporteGenericoResultanteDto vRGenericoResultanteDto = new ReporteGenericoResultanteDto<ReporteGenericoJsonDtoResp>();
        vRGenericoResultanteDto.setData(pReporteGenericoJsonDtoResp);
        ObjectMapper objectMapper = new ObjectMapper();
        String cadenaJsonDeOrden = "";
        try {
            cadenaJsonDeOrden = objectMapper.writeValueAsString(vRGenericoResultanteDto);
            return cadenaJsonDeOrden;
        } catch (Exception exp) {
            throw new IntegridadMefpException("Error interno de casteo Json para el Reporte");
        }
    }

    private String prepararDatosReporte_1(String pIdAcp){
        //ReporteJsonOperaciones vReporteJsonOperaciones = operacionesJson(idPoaArea,tipoVersionDatos);

        ReporteGenericoJsonDtoResp vReporte = new ReporteGenericoJsonDtoResp();
        //Agregar la cabecera
        QueryObtenerDatosCabeceraGenericoDto cabecera = new QueryObtenerDatosCabeceraGenericoDto();
        cabecera.setNombreUnidad("UNIDAD UTI");
        cabecera.setCodigoReporte("UNIDAD UTI");
        cabecera.setTituloReporte("UNIDAD UTI");
        cabecera.setUsuario("UNIDAD UTI");
        cabecera.setFechaImpresion("UNIDAD UTI");
        vReporte.setCabecera(cabecera);
        //Agregar los datos
        List<ListaDto> datos = new ArrayList<>();
        ListaDto vListaDto=new ListaDto();
        vListaDto.setNombre("Juan");
        vListaDto.setEdad("35");
        vListaDto.setFechaNacimiento("10/03/2025");
        datos.add(vListaDto);
        vReporte.setDatos(datos);

        ReporteGenericoResultanteDto vRGenericoResultanteDto = new ReporteGenericoResultanteDto<ReporteGenericoJsonDtoResp>();
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
    private QueryObtenerDatosCabeceraDto obtenerDatosCabeceraDto(Long pIdVersion)
    {
        QueryObtenerDatosCabeceraDto vQueryObtenerDatosCabeceraDto=new QueryObtenerDatosCabeceraDto();

        return vQueryObtenerDatosCabeceraDto;
    }
}
