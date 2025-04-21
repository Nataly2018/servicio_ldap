package gob.mefp.reportes.app.reportes.controller;

import gob.ypfb.nova.app.reportes.dto.request.ReporteGeneralPoaDtoReq;
import gob.ypfb.nova.app.reportes.dto.response.*;
import gob.ypfb.nova.app.reportes.service.IReporteGeneralOperacionesPoaService;
import gob.ypfb.nova.commons.dto.RespuestaGeneral;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("nova/api/v1/reportes_poa")
@CrossOrigin(origins = "*",
        methods = {RequestMethod.GET, RequestMethod.POST,
                RequestMethod.PUT, RequestMethod.DELETE})
@Slf4j
public class ReporteGeneralOperacionesPoaController {
    @Autowired
    IReporteGeneralOperacionesPoaService vIReporteGeneralOperacionesPoaService;

    @Operation(
            summary = "Obtener reporte general de poa por parámetros",
            description = "Obtener reporte general de operaciones por acp, area")
    @GetMapping(value = "/operaciones_por_apc_area/version/{idVersion}/acps/{idAcps}/areas/{idPoaArea}")
    public ResponseEntity<?> listarOperacionesPorAcpPoaArea(@PathVariable("idVersion") Long idVersion,
                                                            @PathVariable("idAcps") String idAcps,
                                                            @PathVariable("idPoaArea") String idPoaArea) {
        ReporteOperacionesPorAcpAreaDtoResp vObj =
                vIReporteGeneralOperacionesPoaService.listarOperacionesPorAcpPoaArea(idVersion, idAcps, idPoaArea);
        return RespuestaGeneral.ok(vObj);
    }
    @Operation(
            summary = "Obtener reporte general de poa por parámetros",
            description = "Obtener reporte general de operaciones por acp")
    @GetMapping(value = "/operaciones_por_acp/version/{idVersion}/acps/{idAcps}")
    public ResponseEntity<?> listarOperacionesPorAcp(@PathVariable("idVersion") Long idVersion,
                                                     @PathVariable("idAcps") String idAcps) {
        ReporteOperacionesPorAcpDtoResp vObj =
                vIReporteGeneralOperacionesPoaService.listarOperacionesPorAcp(idVersion,idAcps);
        return RespuestaGeneral.ok(vObj);
    }
    @Operation(
            summary = "Obtener reporte general de poa por parámetros",
            description = "Obtener reporte general de operaciones por area")
    @GetMapping(value = "/operaciones_por_area/version/{idVersion}/areas/{idPoaArea}")
    public ResponseEntity<?> listarOperacionesPorAreas(@PathVariable("idVersion") Long idVersion,
                                                       @PathVariable("idPoaArea") String idPoaArea) {
        ReporteOperacionesPorAreaDtoResp vObj =
                vIReporteGeneralOperacionesPoaService.listarOperacionesPorAreas(idVersion, idPoaArea);
        return RespuestaGeneral.ok(vObj);
    }
    @Operation(
            summary = "Obtener reporte general de poa por parámetros",
            description = "Obtener reporte general de operaciones por acp")
    @GetMapping(value = "/cantidad_operaciones_por_acp/version/{version}/acps/{pIdAcps}")
    public ResponseEntity<?> listarOperacionesPorAcpJson(@PathVariable("version") Long pIdVersion, @PathVariable("pIdAcps") String pIdAcps) {
        ReporteOperacionesPorAcpJsonDtoResp vObj =
                vIReporteGeneralOperacionesPoaService.listarOperacionesPorAcpJson(pIdVersion, pIdAcps);
        return RespuestaGeneral.ok(vObj);
    }
    @Operation(
            summary = "Obtener reporte general de poa por parámetros",
            description = "Obtener reporte general de operaciones por area")
    @GetMapping(value = "/cantidad_operaciones_por_area/version/{version}/areas/{pIdAreas}")
    public ResponseEntity<?> listarOperacionesPorAreaJson(@PathVariable("version") Long pIdVersion, @PathVariable("pIdAreas") String pIdAreas) {
        ReporteOperacionesPorAreaJsonDtoResp vObj =
                vIReporteGeneralOperacionesPoaService.listarOperacionesPorAreaJson(pIdVersion, pIdAreas);
        return RespuestaGeneral.ok(vObj);
    }
}
