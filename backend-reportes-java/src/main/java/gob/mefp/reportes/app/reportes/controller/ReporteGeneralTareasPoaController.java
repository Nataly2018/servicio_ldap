package gob.mefp.reportes.app.reportes.controller;

import gob.ypfb.nova.app.reportes.dto.request.ReporteGeneralPoaDtoReq;
import gob.ypfb.nova.app.reportes.dto.response.*;
import gob.ypfb.nova.app.reportes.service.IReporteGeneralTareasPoaService;
import gob.ypfb.nova.commons.dto.RespuestaGeneral;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("nova/api/v1/reportes_poa")
@CrossOrigin(origins = "*",
        methods = {RequestMethod.GET, RequestMethod.POST,
                RequestMethod.PUT, RequestMethod.DELETE})
@Slf4j
public class ReporteGeneralTareasPoaController {
    @Autowired
    IReporteGeneralTareasPoaService vIReporteGeneralPoaService;
    @Operation(
            summary = "Obtener reporte general de poa por parámetros",
            description = "Obtener reporte general de tareas por acp, area")
    @GetMapping(value = "/tareas_por_apc_area/version/{idVersion}/acps/{idAcps}/areas/{idPoaArea}")
    public ResponseEntity<?> listarTareasPorAcpPoaArea(@PathVariable("idVersion") Long idVersion,
                                                       @PathVariable("idAcps") String idAcps,
                                                       @PathVariable("idPoaArea") String idPoaArea) {
        ReporteTareaPorAcpAreaDtoResp vObj =
                vIReporteGeneralPoaService.listarTareasPorAcpPoaArea(idVersion, idAcps, idPoaArea);
        return RespuestaGeneral.ok(vObj);
    }
    @Operation(
            summary = "Obtener reporte general de poa por parámetros",
            description = "Obtener reporte general de tareas por acp")
    @GetMapping(value = "/tareas_por_acp/version/{idVersion}/acps/{idAcps}")
    public ResponseEntity<?> listarTareasPorAcp(@PathVariable("idVersion") Long idVersion,
                                                @PathVariable("idAcps") String idAcps) {
        ReporteTareaPorAcpDtoResp vObj =
                vIReporteGeneralPoaService.listarTareasPorAcp(idVersion, idAcps);
        return RespuestaGeneral.ok(vObj);
    }
    @Operation(
            summary = "Obtener reporte general de poa por parámetros",
            description = "Obtener reporte general de tareas por area")
    @GetMapping(value = "/tareas_por_area/version/{idVersion}/areas/{idPoaArea}")
    public ResponseEntity<?> listarTareasPorArea(@PathVariable("idVersion") Long idVersion,
                                                 @PathVariable("idPoaArea") String idPoaArea) {
        ReporteTareaPorAreaDtoResp vObj =
                vIReporteGeneralPoaService.listarTareasPorAreas(idVersion, idPoaArea);
        return RespuestaGeneral.ok(vObj);
    }
    @Operation(
            summary = "Obtener reporte general de poa por parámetros",
            description = "Obtener reporte general de tareas por area")
    @GetMapping(value = "/tipo_tareas_por_area/version/{idVersion}/poa_areas/{idPoaArea}")
    public ResponseEntity<?> listarTareasPorTipo(@PathVariable("idVersion") Long idVersion,
                                                 @PathVariable("idPoaArea") String idPoaArea) {
        List<ReporteCantidadTareasPorTipoDto> vObj =
                vIReporteGeneralPoaService.listarTareasPorTipo(idVersion, idPoaArea);
        return RespuestaGeneral.ok(vObj);
    }
    @Operation(
            summary = "Obtener reporte general de poa por parámetros",
            description = "Obtener reporte general de tareas por area")
    @GetMapping(value = "/tipo_tareas_poa/version/{idVersion}")
    public ResponseEntity<?> listarTareasPorTipo(@PathVariable("idVersion") Long idVersion){
        List<ReporteCantidadTareasPorTipoDto> vObj =
                vIReporteGeneralPoaService.listarTareasPorTipo(idVersion, null);
        return RespuestaGeneral.ok(vObj);
    }
}
