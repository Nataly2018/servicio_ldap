package gob.mefp.reportes.app.reportes.controller;

import gob.ypfb.nova.app.formulacion.dto.request.ReporteGeneralDtoReq;
import gob.ypfb.nova.app.reportes.dto.request.ReporteGeneralPorParametrosDtoReq;
import gob.ypfb.nova.app.reportes.dto.request.ReportePresupuestoPorAcpJsonDtoReq;
import gob.ypfb.nova.app.reportes.dto.request.ReportePresupuestoPorAreaDtoReq;
import gob.ypfb.nova.app.reportes.dto.request.ReportePresupuestoPorAreaJsonDtoReq;
import gob.ypfb.nova.app.reportes.dto.response.QueryReportePorParametrosListDto;
import gob.ypfb.nova.app.reportes.dto.response.ReportePresupuestoPorAcpDtoResp;
import gob.ypfb.nova.app.reportes.dto.response.ReportePresupuestoPorAreaDtoResp;
import gob.ypfb.nova.app.reportes.service.IReporteGeneralPresupuestoService;
import gob.ypfb.nova.commons.dto.RespuestaGeneral;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("nova/api/v1/reportes")
@CrossOrigin(origins = "*",
        methods = {RequestMethod.GET, RequestMethod.POST,
                RequestMethod.PUT, RequestMethod.DELETE})
@Slf4j
public class ReporteGeneralPresupuestoController {
    @Autowired
    private IReporteGeneralPresupuestoService vIReporteGeneralService;
    @Operation(
            summary = "Obtener reporte general de presupuesto por parametros",
            description = "Obtener reporte general de presupuesto por parametros")
    @PostMapping(value = "/reporte_presupuesto_por_area")
    public ResponseEntity<?> obtenerPresupuestoPorArea(@RequestBody ReportePresupuestoPorAreaDtoReq vReportePresupuestoPorAreaDtoReq) {
        QueryReportePorParametrosListDto vObj =
                vIReporteGeneralService.obtenerPresupuestoPorArea(
                        vReportePresupuestoPorAreaDtoReq);
        return RespuestaGeneral.ok(vObj);
    }
    @Operation(
            summary = "Obtener reporte general de presupuesto por parametros",
            description = "Obtener reporte general de presupuesto por parametros")
    @PostMapping(value = "/reporte_presupuesto_por_acp")
    public ResponseEntity<?> obtenerPresupuestoPorAcp(@RequestBody ReporteGeneralPorParametrosDtoReq vReporteGeneralDtoReq) {
        QueryReportePorParametrosListDto vObj =
                vIReporteGeneralService.obtenerPresupuestoPorAcp(
                        vReporteGeneralDtoReq);
        return RespuestaGeneral.ok(vObj);
    }
    @Operation(
            summary = "Obtener reporte general de presupuesto por parametros",
            description = "Obtener reporte general de presupuesto por parametros")
    @PostMapping(value = "/reporte_general")
    public ResponseEntity<?> obtenerPresupuestoGeneralPorParametros(
            @RequestBody ReporteGeneralDtoReq vReporteGeneralDtoReq
    ) {
        QueryReportePorParametrosListDto vObj =
                vIReporteGeneralService.obtenerPresupuestoGeneralPorParametros(
                        vReporteGeneralDtoReq);
        return RespuestaGeneral.ok(vObj);
    }
    @Operation(
            summary = "Obtener reporte general de presupuesto por parametros",
            description = "Obtener reporte general de presupuesto por parametros")
    @GetMapping(value = "/presupuesto_por_area/version/{idVersion}/poa_areas/{idPoaArea}/tipo_gasto/{idTipoGasto}/tipo_tarea/{idTipoTarea}")
    public ResponseEntity<?> obtenerPresupuestoPorAreaJson(
            @PathVariable("idVersion") Long idVersion,
            @PathVariable("idPoaArea") String idPoaArea,
            @PathVariable("idTipoGasto") String idTipoGasto,
            @PathVariable("idTipoTarea") String idTipoTarea
    ) {
        ReportePresupuestoPorAreaJsonDtoReq vReportePresupuestoPorAreaJsonDtoReq = new ReportePresupuestoPorAreaJsonDtoReq();
        vReportePresupuestoPorAreaJsonDtoReq.setIdVersion(idVersion);
        vReportePresupuestoPorAreaJsonDtoReq.setIdPoaArea(idPoaArea);
        vReportePresupuestoPorAreaJsonDtoReq.setIdTipoGasto(idTipoGasto);
        vReportePresupuestoPorAreaJsonDtoReq.setIdTipoTarea(idTipoTarea);
        ReportePresupuestoPorAreaDtoResp vReportePresupuestoPorAreaDtoResp =
                vIReporteGeneralService.obtenerPresupuestoPorAreaJson(vReportePresupuestoPorAreaJsonDtoReq);
        return RespuestaGeneral.ok(vReportePresupuestoPorAreaDtoResp);
    }
    @Operation(
            summary = "Obtener reporte general de presupuesto por parametros",
            description = "Obtener reporte general de presupuesto por parametros")
    @GetMapping(value = "/presupuesto_por_acp/version/{idVersion}/acps/{idAcps}/tipo_gasto/{idTipoGasto}/tipo_tarea/{idTipoTarea}")
    public ResponseEntity<?> obtenerPresupuestoPorAcpJson(
            @PathVariable("idVersion") Long idVersion,
            @PathVariable("idAcps") String idAcps,
            @PathVariable("idTipoGasto") String idTipoGasto,
            @PathVariable("idTipoTarea") String idTipoTarea
    ) {
        ReportePresupuestoPorAcpJsonDtoReq vReportePresupuestoPorAcpJsonDtoReq = new ReportePresupuestoPorAcpJsonDtoReq();
        vReportePresupuestoPorAcpJsonDtoReq.setIdVersion(idVersion);
        vReportePresupuestoPorAcpJsonDtoReq.setIdAcps(idAcps);
        vReportePresupuestoPorAcpJsonDtoReq.setIdTipoGasto(idTipoGasto);
        vReportePresupuestoPorAcpJsonDtoReq.setIdTipoTarea(idTipoTarea);
        ReportePresupuestoPorAcpDtoResp vReportePresupuestoPorAcpDtoResp =
                vIReporteGeneralService.obtenerPresupuestoPorAcpJson(vReportePresupuestoPorAcpJsonDtoReq);
        return RespuestaGeneral.ok(vReportePresupuestoPorAcpDtoResp);
    }

}
