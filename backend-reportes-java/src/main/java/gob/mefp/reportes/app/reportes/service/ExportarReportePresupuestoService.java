package gob.mefp.reportes.app.reportes.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import gob.ypfb.nova.app.formulacion.dto.response.ReporteJsonOperaciones;
import gob.ypfb.nova.app.formulacion.service.reporte.IReportesGenericoService;
import gob.ypfb.nova.app.formulacion.service.reporte.RGenericoResultanteDto;
import gob.ypfb.nova.app.parametricas.service.IContextoService;
import gob.ypfb.nova.app.reportes.dto.query.QueryOperacionesPorAcp;
import gob.ypfb.nova.app.reportes.dto.request.ReportePresupuestoPorAcpJsonDtoReq;
import gob.ypfb.nova.app.reportes.dto.request.ReportePresupuestoPorAreaJsonDtoReq;
import gob.ypfb.nova.app.reportes.dto.response.ReporteOperacionesPorAcpJsonDtoResp;
import gob.ypfb.nova.app.reportes.dto.response.ReporteOperacionesPorAreaJsonDtoResp;
import gob.ypfb.nova.app.reportes.dto.response.ReportePresupuestoPorAcpDtoResp;
import gob.ypfb.nova.app.reportes.dto.response.ReportePresupuestoPorAreaDtoResp;
import gob.ypfb.nova.exception.IntegridadYpfbException;
import gob.ypfb.nova.security.jwt.JwtServiceContext;
import gob.ypfb.nova.utils.excel.ExcelConstantes;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.query.JsonQueryExecuterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;
@Service
@Slf4j
public class ExportarReportePresupuestoService implements IExportarReportePresupuestoService{
    @Autowired
    private JwtServiceContext jwtAuthenticationFilter;
    @Autowired
    IContextoService vIContextoService;
    @Autowired
    IReportesGenericoService vIReportesGenericoService;
    @Autowired
    IReporteGeneralPresupuestoService vIReporteGeneralPresupuestoService;
    @Autowired
    IReporteGeneralOperacionesPoaService vIReporteGeneralOperacionesPoaService;
    @Override
    public void exportarReporteCantidadOperacionesPorArea(HttpServletResponse pHttpServletResponse
            , ExcelConstantes.TipoArchivoExportacion pTipoArchivoExportacion, Long pIdVersionPoa, String pIdAcp) {

        try {
            String cadenaJsonDeOrden = prepararDatosReporteOperacionesPorAreaJson(pIdVersionPoa, pIdAcp);

            String ruta = "reportes/reportePresupuestoPorAcp.jasper";
            String directorio = new ClassPathResource("reportes/reportePresupuestoPorAcp.jasper").getURI().getPath();
            if (directorio != null) {
                directorio = directorio.replace("/reportePresupuestoPorAcp.jasper", "");
            } else {
                directorio = "reportes";
            }

            InputStream fis = new ClassPathResource(ruta).getInputStream();
            Map<String, Object> params = new HashMap<>();
            params.put("path", directorio+"/");

            params.put(JsonQueryExecuterFactory.JSON_INPUT_STREAM, new ByteArrayInputStream(cadenaJsonDeOrden.getBytes("UTF-8")));
            params.put(JsonQueryExecuterFactory.JSON_LOCALE, new Locale("en", "US", "POSIX"));//. "en_US_POSIX"

            JasperPrint jasperPrint = JasperFillManager.fillReport(fis, params);
            String nombreArchivo = "presupuesto_por_acp";
            if(pTipoArchivoExportacion.equals(ExcelConstantes.TipoArchivoExportacion.PDF)) {
                vIReportesGenericoService.generarPdf(jasperPrint, pHttpServletResponse, nombreArchivo);
            }

        } catch (IOException | JRException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void exportarReporteCantidadOperacionesPorAcp(HttpServletResponse pHttpServletResponse
            , ExcelConstantes.TipoArchivoExportacion pTipoArchivoExportacion, Long pIdVersionPoa, String pIdAcp) {

        try {
            String cadenaJsonDeOrden = prepararDatosReporteOperacionesPorAcpJson(pIdVersionPoa, pIdAcp);

            String ruta = "reportes/reportePresupuestoPorAcp.jasper";
            String directorio = new ClassPathResource("reportes/reportePresupuestoPorAcp.jasper").getURI().getPath();
            if (directorio != null) {
                directorio = directorio.replace("/reportePresupuestoPorAcp.jasper", "");
            } else {
                directorio = "reportes";
            }

            InputStream fis = new ClassPathResource(ruta).getInputStream();
            Map<String, Object> params = new HashMap<>();
            params.put("path", directorio+"/");

            params.put(JsonQueryExecuterFactory.JSON_INPUT_STREAM, new ByteArrayInputStream(cadenaJsonDeOrden.getBytes("UTF-8")));
            params.put(JsonQueryExecuterFactory.JSON_LOCALE, new Locale("en", "US", "POSIX"));//. "en_US_POSIX"

            JasperPrint jasperPrint = JasperFillManager.fillReport(fis, params);
            String nombreArchivo = "presupuesto_por_acp";
            if(pTipoArchivoExportacion.equals(ExcelConstantes.TipoArchivoExportacion.PDF)) {
                vIReportesGenericoService.generarPdf(jasperPrint, pHttpServletResponse, nombreArchivo);
            }

        } catch (IOException | JRException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void exportarReportePresupuestoPorAcp(HttpServletResponse pHttpServletResponse
            , ExcelConstantes.TipoArchivoExportacion pTipoArchivoExportacion, Long pIdVersion, String pIdAcps, String pIdTipoGasto, String pIdTipoTarea) {

        try {
            String cadenaJsonDeOrden = prepararDatosReportePresupuestoPorAcpJson(pIdVersion, pIdAcps, pIdTipoGasto, pIdTipoTarea);

            String ruta = "reportes/reportePresupuestoPorAcp.jasper";
            String directorio = new ClassPathResource("reportes/reportePresupuestoPorAcp.jasper").getURI().getPath();
            if (directorio != null) {
                directorio = directorio.replace("/reportePresupuestoPorAcp.jasper", "");
            } else {
                directorio = "reportes";
            }

            InputStream fis = new ClassPathResource(ruta).getInputStream();
            Map<String, Object> params = new HashMap<>();
            params.put("path", directorio+"/");

            params.put(JsonQueryExecuterFactory.JSON_INPUT_STREAM, new ByteArrayInputStream(cadenaJsonDeOrden.getBytes("UTF-8")));
            params.put(JsonQueryExecuterFactory.JSON_LOCALE, new Locale("en", "US", "POSIX"));//. "en_US_POSIX"

            JasperPrint jasperPrint = JasperFillManager.fillReport(fis, params);
            String nombreArchivo = "presupuesto_por_acp";
            if(pTipoArchivoExportacion.equals(ExcelConstantes.TipoArchivoExportacion.PDF)) {
                vIReportesGenericoService.generarPdf(jasperPrint, pHttpServletResponse, nombreArchivo);
            }

        } catch (IOException | JRException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void exportarReportePresupuestoPorArea(HttpServletResponse pHttpServletResponse
            , ExcelConstantes.TipoArchivoExportacion pTipoArchivoExportacion, Long pIdVersion, String pIdPoaAreas, String pIdTipoGasto, String pIdTipoTarea) {

        try {
            String cadenaJsonDeOrden = prepararDatosReportePresupuestoPorAreaJson(pIdVersion, pIdPoaAreas, pIdTipoGasto, pIdTipoTarea);

            String ruta = "reportes/reportePresupuestoPorArea.jasper";
            String directorio = new ClassPathResource("reportes/reportePresupuestoPorArea.jasper").getURI().getPath();
            if (directorio != null) {
                directorio = directorio.replace("/reportePresupuestoPorArea.jasper", "");
            } else {
                directorio = "reportes";
            }
            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String fechaHoy = dateFormat.format(date);
            InputStream fis = new ClassPathResource(ruta).getInputStream();
            Map<String, Object> params = new HashMap<>();
            //params.put("usuario", jwtAuthenticationFilter.getJwtTokenContextAplicacion().getUsername());
            params.put("path", directorio+"/");
            //params.put("fechaHoy", fechaHoy);
            //params.put("version", "");

            params.put(JsonQueryExecuterFactory.JSON_INPUT_STREAM, new ByteArrayInputStream(cadenaJsonDeOrden.getBytes("UTF-8")));
            params.put(JsonQueryExecuterFactory.JSON_LOCALE, new Locale("en", "US", "POSIX"));//. "en_US_POSIX"

            JasperPrint jasperPrint = JasperFillManager.fillReport(fis, params);
            String nombreArchivo = "presupuesto_por_area";
            if(pTipoArchivoExportacion.equals(ExcelConstantes.TipoArchivoExportacion.PDF)) {
                vIReportesGenericoService.generarPdf(jasperPrint, pHttpServletResponse, nombreArchivo);
            }

        } catch (IOException | JRException e) {
            e.printStackTrace();
        }
    }

    private String prepararDatosReporteOperacionesPorAcpJson(Long pIdVersionPoa, String pIdAcp){
        //ReporteJsonOperaciones vReporteJsonOperaciones = operacionesJson(idPoaArea,tipoVersionDatos);
        ReporteOperacionesPorAcpJsonDtoResp vLista = vIReporteGeneralOperacionesPoaService.listarOperacionesPorAcpJson(pIdVersionPoa,pIdAcp);
        RGenericoResultanteDto vRGenericoResultanteDto = new RGenericoResultanteDto<ReporteOperacionesPorAcpJsonDtoResp>();
        vRGenericoResultanteDto.setData(vLista);
        ObjectMapper objectMapper = new ObjectMapper();
        String cadenaJsonDeOrden = "";
        try {
            cadenaJsonDeOrden = objectMapper.writeValueAsString(vRGenericoResultanteDto);
            return cadenaJsonDeOrden;
        } catch (Exception exp) {
            throw new IntegridadYpfbException("Error interno de casteo Json para el Reporte");
        }
    }
    private String prepararDatosReporteOperacionesPorAreaJson(Long pIdVersionPoa, String pIdAcp){
        //ReporteJsonOperaciones vReporteJsonOperaciones = operacionesJson(idPoaArea,tipoVersionDatos);
        ReporteOperacionesPorAreaJsonDtoResp vLista = vIReporteGeneralOperacionesPoaService.listarOperacionesPorAreaJson(pIdVersionPoa,pIdAcp);
        RGenericoResultanteDto vRGenericoResultanteDto = new RGenericoResultanteDto<ReporteOperacionesPorAreaJsonDtoResp>();
        vRGenericoResultanteDto.setData(vLista);
        ObjectMapper objectMapper = new ObjectMapper();
        String cadenaJsonDeOrden = "";
        try {
            cadenaJsonDeOrden = objectMapper.writeValueAsString(vRGenericoResultanteDto);
            return cadenaJsonDeOrden;
        } catch (Exception exp) {
            throw new IntegridadYpfbException("Error interno de casteo Json para el Reporte");
        }
    }
    private String prepararDatosReportePresupuestoPorAreaJson(Long pIdVersion, String pIdPoaAreas, String pIdTipoGasto, String pIdTipoTarea) {
        //temporal
        //ReporteJsonOperaciones vReporteJsonOperaciones = operacionesJson(idPoaArea,tipoVersionDatos);
        ReportePresupuestoPorAreaJsonDtoReq vReportePresupuestoPorAreaJsonDtoReq = new ReportePresupuestoPorAreaJsonDtoReq();
        vReportePresupuestoPorAreaJsonDtoReq.setIdPoaArea(pIdPoaAreas);
        vReportePresupuestoPorAreaJsonDtoReq.setIdVersion(pIdVersion);
        vReportePresupuestoPorAreaJsonDtoReq.setIdTipoGasto(pIdTipoGasto);
        vReportePresupuestoPorAreaJsonDtoReq.setIdTipoTarea(pIdTipoTarea);
        ReportePresupuestoPorAreaDtoResp vReportePresupuestoPorAreaDtoResp = vIReporteGeneralPresupuestoService.obtenerPresupuestoPorAreaJson(vReportePresupuestoPorAreaJsonDtoReq);
        RGenericoResultanteDto vRGenericoResultanteDto = new RGenericoResultanteDto<ReportePresupuestoPorAcpDtoResp>();
        vRGenericoResultanteDto.setData(vReportePresupuestoPorAreaDtoResp);
        ObjectMapper objectMapper = new ObjectMapper();
        String cadenaJsonDeOrden = "";
        try {
            cadenaJsonDeOrden = objectMapper.writeValueAsString(vRGenericoResultanteDto);
            return cadenaJsonDeOrden;
        } catch (Exception exp) {
            throw new IntegridadYpfbException("Error interno de casteo Json para el Reporte");
        }
    }
    private String prepararDatosReportePresupuestoPorAcpJson(Long pIdVersion, String pIdAcps, String pIdTipoGasto, String pIdTipoTarea) {
        //temporal
        //ReporteJsonOperaciones vReporteJsonOperaciones = operacionesJson(idPoaArea,tipoVersionDatos);
        ReportePresupuestoPorAcpJsonDtoReq vReportePresupuestoPorAcpJsonDtoReq = new ReportePresupuestoPorAcpJsonDtoReq();
        vReportePresupuestoPorAcpJsonDtoReq.setIdAcps(pIdAcps);
        vReportePresupuestoPorAcpJsonDtoReq.setIdVersion(pIdVersion);
        vReportePresupuestoPorAcpJsonDtoReq.setIdTipoGasto(pIdTipoGasto);
        vReportePresupuestoPorAcpJsonDtoReq.setIdTipoTarea(pIdTipoTarea);
        ReportePresupuestoPorAcpDtoResp vReportePresupuestoPorAcpDtoResp = vIReporteGeneralPresupuestoService.obtenerPresupuestoPorAcpJson(vReportePresupuestoPorAcpJsonDtoReq);
        RGenericoResultanteDto vRGenericoResultanteDto = new RGenericoResultanteDto<ReportePresupuestoPorAcpDtoResp>();
        vRGenericoResultanteDto.setData(vReportePresupuestoPorAcpDtoResp);
        ObjectMapper objectMapper = new ObjectMapper();
        String cadenaJsonDeOrden = "";
        try {
            cadenaJsonDeOrden = objectMapper.writeValueAsString(vRGenericoResultanteDto);
            return cadenaJsonDeOrden;
        } catch (Exception exp) {
            throw new IntegridadYpfbException("Error interno de casteo Json para el Reporte");
        }
    }
}
