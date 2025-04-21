/**
 * Project: nova
 * Package: bo.gob.ypfb.nova
 * El archivo "BandejaMaeController.java" fue creado para el proyecto nova
 * por Y.P.F.B. Corporación, todos los Derechos reservados.
 *
 * @author: Roger Diego Flores Condori
 * @email : cons.rflores@ypfb.gob.bo
 * @date: 7/13/2023 9:53 a. m.
 * @copyright: YPFB
 * @version: 1.0
 **/
package gob.mefp.reportes.app.reportes.controller;


import gob.ypfb.nova.app.formulacion.dto.request.ReasignacionDtoReq;
import gob.ypfb.nova.app.reportes.dto.request.*;
import gob.ypfb.nova.app.reportes.dto.request.ParametrosGenericos.*;
import gob.ypfb.nova.app.reportes.dto.response.*;
import gob.ypfb.nova.app.reportes.service.IConsultaPametricaGeneralService;
import gob.ypfb.nova.commons.dto.RespuestaGeneral;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("nova/api/v1/consulta")
@Slf4j
public class ConsultaParametricaController {

    @Autowired
    IConsultaPametricaGeneralService vIConsultaPametricaGeneralService;

    @Operation(
            summary = "042-01 Obtener Listado de Accion a corto plazo",
            description = "042-01 Obtener Listado de Accion a corto plazo")
    @GetMapping(value = "/poa/version/{idPoa}/accion_corto_plazo")
    public ResponseEntity<?> listaAccionCortoPlazo(@PathVariable("idPoa") Long idPoa) {
        ConsultaAccionCortoPlazoDtoReq consultaAccionCortoPlazoDtoReq = new ConsultaAccionCortoPlazoDtoReq();
        consultaAccionCortoPlazoDtoReq.setIdVersionPoa(idPoa);
        List<ConsultaParametricaDtoResp> listar =
                vIConsultaPametricaGeneralService.listaAccionesCortoPlazoPorPoa(consultaAccionCortoPlazoDtoReq);
        return RespuestaGeneral.ok(listar);
    }
    @Operation(
            summary = "042-02 Obtener Listado de Poa",
            description = "042-02 Obtener Listado de Poa")
    @GetMapping(value = "/poa")
    public ResponseEntity<?> listaPoa() {
        List<ConsultaParametricaDtoResp> listar =
                vIConsultaPametricaGeneralService.listaPoa();
        return RespuestaGeneral.ok(listar);
    }

    @Operation(
            summary = "042-03 Obtener Listado de Area",
            description = "042-03 Obtener Listado de Area")
    @GetMapping(value = "/poa/version/{idVersionPoa}/area")
    public ResponseEntity<?> listaUnidades(@PathVariable("idVersionPoa") Long idVersionPoa) {
        ConsultaUnidadesDtoReq consultaUnidadesDtoReq = new ConsultaUnidadesDtoReq();
        consultaUnidadesDtoReq.setIdVersionPoa(idVersionPoa);
        List<ConsultaParametricaDtoResp> listar =
                vIConsultaPametricaGeneralService.listaUnidadesPorPoa(consultaUnidadesDtoReq);
        return RespuestaGeneral.ok(listar);
    }

    @Operation(
            summary = "042-04 Obtener Listado de Operaciones",
            description = "042-04 Obtener Listado de Operaciones")
    @GetMapping(value = "/poa/version/{idVersionPoa}/operacion")
    public ResponseEntity<?> listaOperaciones(@PathVariable("idVersionPoa") Long idVersionPoa) {
        ConsultaOperacionesDtoReq consultaOperacionesDtoReq = new ConsultaOperacionesDtoReq();
        consultaOperacionesDtoReq.setIdVersionPoa(idVersionPoa);
        List<ConsultaParametricaDtoResp> listar =
                vIConsultaPametricaGeneralService.listaOperacionesPorPoa(consultaOperacionesDtoReq);
        return RespuestaGeneral.ok(listar);
    }

    @Operation(
            summary = "042-05 Obtener Listado de Tareas",
            description = "042-05 Obtener Listado de Tareas")
    @GetMapping(value = "/poa/version/{idVersionPoa}/tarea")
    public ResponseEntity<?> listaTareas(@PathVariable("idVersionPoa") Long idVersionPoa) {
        ConsultaTareasDtoReq consultaTareasDtoReq = new ConsultaTareasDtoReq();
        consultaTareasDtoReq.setIdVersionPoa(idVersionPoa);
        List<ConsultaParametricaDtoResp> listar =
                vIConsultaPametricaGeneralService.listaTareasPorPoa(consultaTareasDtoReq);
        return RespuestaGeneral.ok(listar);
    }

    @Operation(
            summary = "042-06 Obtener Listado de Tipo Tareas",
            description = "042-06 Obtener Listado de Tipo Tareas")
    @GetMapping(value = "/poa/version/{idVersionPoa}/tipo_tarea")
    public ResponseEntity<?> listaTipoTareas(@PathVariable("idVersionPoa") Long idVersionPoa) {
        ConsultaTipoTareasDtoReq consultaTipoTareasDtoReq = new
                ConsultaTipoTareasDtoReq();
        consultaTipoTareasDtoReq.setIdVersionPoa(idVersionPoa);
        List<ConsultaParametricaDtoResp> listar =
                vIConsultaPametricaGeneralService.listaTipoTareasPorPoa(consultaTipoTareasDtoReq);
        return RespuestaGeneral.ok(listar);
    }

    @Operation(
            summary = "042-07 Obtener Listado de Areas Responsables  de Tareas",
            description = "042-07 Obtener Listado de Areas Responsables  de Tareas")
    @GetMapping(value = "/poa/version/{idVersionPoa}/area_responsable")
    public ResponseEntity<?> listaAreasResponsablesTareas(@PathVariable("idVersionPoa") Long idVersionPoa) {
        ConsultaResponsableTareasDtoReq consultaTipoTareasDtoReq = new
                ConsultaResponsableTareasDtoReq();
        consultaTipoTareasDtoReq.setIdVersionPoa(idVersionPoa);
        List<ConsultaParametricaDtoResp> listar =
                vIConsultaPametricaGeneralService.listaResponsablesTareasPorPoa(consultaTipoTareasDtoReq);
        return RespuestaGeneral.ok(listar);
    }

    @Operation(
            summary = "042-08 Obtener Listado de Categoria Programatica",
            description = "042-08 Obtener Listado de Categoria Programatica")
    @GetMapping(value = "/poa/version/{idVersionPoa}/categoria_programatica")
    public ResponseEntity<?> listaCategoriaProgram(@PathVariable("idVersionPoa") Long idVersionPoa) {
        ConsultaCategProgramDtoReq consultaDtoReq = new
                ConsultaCategProgramDtoReq();
        consultaDtoReq.setIdVersionPoa(idVersionPoa);
        List<ConsultaParametricaDtoResp> listar =
                vIConsultaPametricaGeneralService.listaCategoriaProgamPorPoa(consultaDtoReq);
        return RespuestaGeneral.ok(listar);
    }
    @Operation(
            summary = "042-09 Obtener Listado de Direccion Administrativa",
            description = "042-09 Obtener Listado de Direccion Administrativa")
    @GetMapping(value = "/poa/version/{idVersionPoa}/direccion_admin")
    public ResponseEntity<?> listaDireccionAdministrativa(@PathVariable("idVersionPoa") Long idVersionPoa) {
        ConsultaDirecAdminDtoReq consultaDtoReq = new
                ConsultaDirecAdminDtoReq();
        consultaDtoReq.setIdVersionPoa(idVersionPoa);
        List<ConsultaParametricaDtoResp> listar =
                vIConsultaPametricaGeneralService.listaDireccionAdminPorPoa(consultaDtoReq);
        return RespuestaGeneral.ok(listar);
    }
    @Operation(
            summary = "042-10 Obtener Listado de Fuente Organismo Financiador",
            description = "042-10 Obtener Listado de Fuente Organismo Financiador")
    @GetMapping(value = "/poa/version/{idVersionPoa}/fuente_org_financiador")
    public ResponseEntity<?> listaFuenteOrgFiunaciadorPorPoa(@PathVariable("idVersionPoa") Long idVersionPoa) {
        ConsultaOrgFinanDtoReq consultaDtoReq = new
                ConsultaOrgFinanDtoReq();
        consultaDtoReq.setIdVersionPoa(idVersionPoa);
        List<ConsultaParametricaDtoResp> listar =
                vIConsultaPametricaGeneralService.listaFuenteOrgFiunaciadorPorPoa(consultaDtoReq);
        return RespuestaGeneral.ok(listar);
    }
    @Operation(
            summary = "042-11 Obtener Listado de Partida Presupuestaria",
            description = "042-11 Obtener Listado de Partida Presupuestaria")
    @GetMapping(value = "/poa/version/{idVersionPoa}/partida_presupuestaria")
    public ResponseEntity<?> listaPartidaPresupuestariaPorPoa(@PathVariable("idVersionPoa") Long idVersionPoa) {
        ConsultaPartidaPresupDtoReq consultaDtoReq = new
                ConsultaPartidaPresupDtoReq();
        consultaDtoReq.setIdVersionPoa(idVersionPoa);
        List<ConsultaParametricaDtoResp> listar =
                vIConsultaPametricaGeneralService.listaPartidaPresupuestariaPorPoa(consultaDtoReq);
        return RespuestaGeneral.ok(listar);
    }
    @Operation(
            summary = "042-12 Obtener Listado de Unidad Ejecutora",
            description = "042-12 Obtener Listado de Unidad Ejecutora")
    @GetMapping(value = "/poa/version/{idVersionPoa}/unidad_ejecutora")
    public ResponseEntity<?> listaUnidadEjecutoraPorPoa(@PathVariable("idVersionPoa") Long idVersionPoa) {
        ConsultaUnidadEjecturaDtoReq consultaDtoReq = new
                ConsultaUnidadEjecturaDtoReq();
        consultaDtoReq.setIdVersionPoa(idVersionPoa);
        List<ConsultaParametricaDtoResp> listar =
                vIConsultaPametricaGeneralService.listaUnidadEjecutoraPorPoa(consultaDtoReq);
        return RespuestaGeneral.ok(listar);
    }
    @Operation(
            summary = "042-13 Obtener Listado de Tipo Presupuesto",
            description = "042-13 Obtener Listado de Tipo Presupuesto")
    @GetMapping(value = "/poa/version/{idVersionPoa}/tipo_presupuesto")
    public ResponseEntity<?> listaTipoPresupuestoPorPoa(@PathVariable("idVersionPoa") Long idVersionPoa) {
        ConsultaTipoPresupuestoDtoReq consultaDtoReq = new
                ConsultaTipoPresupuestoDtoReq();
        consultaDtoReq.setIdVersionPoa(idVersionPoa);
        List<ConsultaParametricaDtoResp> listar =
                vIConsultaPametricaGeneralService.listaTipoPresupuestoPorPoa(consultaDtoReq);
        return RespuestaGeneral.ok(listar);
    }

    @Operation(
            summary = "042-14 Obtener Listado de Version Poa",
            description = "042-14 Obtener Listado de Version Poa")
    @GetMapping(value = "/poa/{idPoa}/version")
    public ResponseEntity<?> listaVersionPoaPorPoa(@PathVariable("idPoa") Long idPoa) {
        ConsultaVersionesPoaDtoReq  consultaDtoReq = new
        ConsultaVersionesPoaDtoReq();
        consultaDtoReq.setIdPoa(idPoa);
        List<ConsultaParametricaDtoResp> listar =
                vIConsultaPametricaGeneralService.listaVersionPoa(consultaDtoReq);
        return RespuestaGeneral.ok(listar);
    }

    @Operation(
            summary = "042-07 Obtener Listado de Areas Responsables  de Tareas",
            description = "042-07 Obtener Listado de Areas Responsables  de Tareas")
    @GetMapping(value = "/poa/version/{idVersionPoa}/area_validadora_consultoria")
    public ResponseEntity<?> listaAreasValidadorasConsultoria(@PathVariable("idVersionPoa") Long idVersionPoa) {
        ConsultaAreasValidadorasConsultoriaDtoReq consultaTipoTareasDtoReq = new
                ConsultaAreasValidadorasConsultoriaDtoReq();
        consultaTipoTareasDtoReq.setIdVersionPoa(idVersionPoa);
        List<ConsultaParametricaDtoResp> listar =
                vIConsultaPametricaGeneralService.listaResponsablesAreasValidadorasPorPoa(consultaTipoTareasDtoReq);
        return RespuestaGeneral.ok(listar);
    }

    @Operation(
            summary = "042-08 consulta Operacion",
            description = " Operacion ")
    @PostMapping(value = "/poa/operacion")
    public ResponseEntity<?> consultaOperacion(@RequestBody ConsultaTrazaGeneralOperacionesReq pConsultaTrazaGeneralOperacionesReq) {

        ConsultaOperacionesDtoResp resultado =
        vIConsultaPametricaGeneralService.consultaOperacion(pConsultaTrazaGeneralOperacionesReq);
        return RespuestaGeneral.ok(resultado);
    }

    @Operation(
            summary = "042-08 consulta Tarea",
            description = " Tareas ")
    @PostMapping(value = "/poa/tarea")
    public ResponseEntity<?> consultaTarea(@RequestBody ConsultaTrazaGeneralTareasReq pConsultaTrazaGeneralTareasReq) {

        ConsultaTareasDtoResp resultado =
                vIConsultaPametricaGeneralService.consultaTareas(pConsultaTrazaGeneralTareasReq);
        return RespuestaGeneral.ok(resultado);
    }

    @Operation(
            summary = "042-08 consulta Preesupuesto",
            description = " Presupuesto ")
    @PostMapping(value = "/poa/presupuesto")
    public ResponseEntity<?> consultaPresupuesto(@RequestBody ConsultaTrazaGeneralPresupuestoReq pConsultaTrazaGeneralPresupuestoReq) {

        ConsultaPresupuestoDtoResp resultado =
                vIConsultaPametricaGeneralService.consultaPresupuesto(pConsultaTrazaGeneralPresupuestoReq);
        return RespuestaGeneral.ok(resultado);
    }
    @Operation(
            summary = "042-08 consulta Preesupuesto",
            description = " Presupuesto ")
    @PostMapping(value = "/poa/consultoria")
    public ResponseEntity<?> consultaPresupuesto(@RequestBody ConsultaTrazaGeneralConsultoriaReq pConsultaTrazaGeneralConsultoriaReq) {

        ConsultaConsultoriaDtoResp resultado =
                vIConsultaPametricaGeneralService.consultaConsultoria(pConsultaTrazaGeneralConsultoriaReq);
        return RespuestaGeneral.ok(resultado);
    }

    @Operation(
            summary = "036-21 exportacion de excel de operacion ",
            description = "036-21 exportacion de excel de presupuesto")
    @PostMapping(value = "/poa/operacion/exportar/excel")
    public void excelOperacion(@RequestBody ConsultaTrazaGeneralOperacionesReq pConsultaTrazaGeneralOperacionesReq,
                      HttpServletResponse response ) {
        vIConsultaPametricaGeneralService.exportarConsultaDatosOperacionesEnExcel(pConsultaTrazaGeneralOperacionesReq,response);
    }
    @Operation(
            summary = "036-21 exportacion de excel de operacion ",
            description = "036-21 exportacion de excel de presupuesto")
    @PostMapping(value = "/poa/tarea/exportar/excel")
    public void excelTarea(@RequestBody ConsultaTrazaGeneralTareasReq pConsultaTrazaGeneralTareasReq,
                               HttpServletResponse response ) {
        vIConsultaPametricaGeneralService.exportarConsultaDatosTareasEnExcel(pConsultaTrazaGeneralTareasReq,response);
    }
    @Operation(
            summary = "036-21 exportacion de excel de operacion ",
            description = "036-21 exportacion de excel de presupuesto")
    @PostMapping(value = "/poa/presupuesto/exportar/excel")
    public void excelPresupuesto(@RequestBody ConsultaTrazaGeneralPresupuestoReq pConsultaTrazaGeneralPresupuestoReq,
                           HttpServletResponse response ) {
        vIConsultaPametricaGeneralService.exportarConsultaDatosPresupuestoEnExcel(pConsultaTrazaGeneralPresupuestoReq,response);
    }

    @Operation(
            summary = "036-21 exportacion de excel de operacion ",
            description = "036-21 exportacion de excel de presupuesto")
    @PostMapping(value = "/poa/consultoria/exportar/excel")
    public void excelConsultoria(@RequestBody ConsultaTrazaGeneralConsultoriaReq pConsultaTrazaGeneralConsultoriaReq,
                                 HttpServletResponse response ) {
        vIConsultaPametricaGeneralService.exportarConsultaDatosConsultoriaEnExcel(pConsultaTrazaGeneralConsultoriaReq,response);
    }
    //listaResponsablesAreasValidadorasPorPoa
    @Operation(
            summary = "042-07 Obtener Listado de Areas Responsables  de Tareas",
            description = "042-07 Obtener Listado de Areas Responsables  de Tareas")
    @GetMapping(value = "/modulo/{modulo}/valores_por_defecto")
    public ResponseEntity<?> listaPaarametrosDefecto(@PathVariable("modulo") String modulo) {
        ConsultaParametrosDefectoDtoResp listar =
                vIConsultaPametricaGeneralService.obtenerValoresDefecto(modulo);
        return RespuestaGeneral.ok(listar);
    }

    @Operation(
            summary = "036-21 exportacion de excel de operacion ",
            description = "036-21 exportacion de excel de presupuesto")
    @PostMapping(value = "/poa/version/detalle")
    public void versionDetalle(@RequestBody CrearVersionDetalleReq pCrearVersionDetalleReq) {
        vIConsultaPametricaGeneralService.realizarVolcadoVersionDetalle(pCrearVersionDetalleReq);
    }

    @Operation(
            summary = "036-21 exportacion de excel de operacion ",
            description = "036-21 exportacion de excel de presupuesto")
    @PostMapping(value = "/poa/version/total")
    public void versionTotales(@RequestBody CrearVersionDetalleReq pCrearVersionDetalleReq) {
        vIConsultaPametricaGeneralService.realizarVolcadoVersionTotales(pCrearVersionDetalleReq);
    }


    @Operation(
            summary = "042-12 Obtener Listado de Unidad Ejecutora",
            description = "042-12 Obtener Listado de Unidad Ejecutora")
    @GetMapping(value = "/poa/area/{idArea}/unidad_ejecutora")
    public ResponseEntity<?> listaUnidadEjecutoraPorArea(@PathVariable("idArea") Long idArea) {

        List<ConsultaParametricaDtoResp> listar =
                vIConsultaPametricaGeneralService.listaUnidadEjecutoraPorArea(idArea);
        return RespuestaGeneral.ok(listar);
    }

}
