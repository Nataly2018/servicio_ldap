/**
 * Project: nova
 * Package: bo.gob.ypfb.nova
 * El archivo "AccionesCortoPlazoService.java" fue creado para el proyecto nova
 * por Y.P.F.B. Corporación, todos los Derechos reservados.
 *
 * @author: Roger Diego Flores Condori
 * @email : cons.rflores@ypfb.gob.bo
 * @date: 5/24/2023 2:00 p. m.
 * @copyright: YPFB
 * @version: 1.0
 **/
package gob.mefp.reportes.app.reportes.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import gob.ypfb.nova.app.configuracion.service.IBitacoraService;
import gob.ypfb.nova.app.formulacion.domain.Poa;
import gob.ypfb.nova.app.formulacion.dto.objeto.AreasResponsablesExcelDto;
import gob.ypfb.nova.app.formulacion.service.reporte.FabricDatosPoa;
import gob.ypfb.nova.app.parametricas.domain.UnidadEjecutora;
import gob.ypfb.nova.app.parametricas.repository.IAreaRepository;
import gob.ypfb.nova.app.formulacion.repository.IPoaRepository;
import gob.ypfb.nova.app.formulacion.repository.IPresupuestoRepository;
import gob.ypfb.nova.app.parametricas.repository.IAccionCortoPlazoRepository;
import gob.ypfb.nova.app.parametricas.repository.IUnidadEjecutoraRepository;
import gob.ypfb.nova.app.parametricas.service.IDominioService;
import gob.ypfb.nova.app.reportes.dto.excel.ConsultaConsultoriaConTotalesExcel;
import gob.ypfb.nova.app.reportes.dto.excel.ConsultaOperacionesConTotalesExcel;
import gob.ypfb.nova.app.reportes.dto.excel.ConsultaPresupuestoConTotalesExcel;
import gob.ypfb.nova.app.reportes.dto.excel.ConsultaTareasConTotalesExcel;
import gob.ypfb.nova.app.reportes.dto.query.*;
import gob.ypfb.nova.app.reportes.dto.request.*;
import gob.ypfb.nova.app.reportes.dto.request.ParametrosGenericos.*;
import gob.ypfb.nova.app.reportes.dto.response.*;
import gob.ypfb.nova.app.reportes.query.IConsultasQuery;
import gob.ypfb.nova.config.ConstantesReporte;
import gob.ypfb.nova.exception.ErrorValidacionException;
import gob.ypfb.nova.exception.IntegridadYpfbException;
import gob.ypfb.nova.exception.InvalidException;
import gob.ypfb.nova.security.jwt.JwtServiceContext;
import gob.ypfb.nova.utils.calculo.Conversion;
import gob.ypfb.nova.utils.excel.DocumentoExcelYpfbUtil;
import gob.ypfb.nova.utils.excel.ExcelConstantes;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static gob.ypfb.nova.config.ConstantesPresupuesto.NOMBRE_EXCEL_REVISION_GAFC_PRESUPUESTO;
import static gob.ypfb.nova.config.ConstantesReporte.*;
import gob.ypfb.nova.app.configuracion.dto.QueryUsuarioFormulacion;
import gob.ypfb.nova.app.configuracion.service.IConfiguracionService;
import gob.ypfb.nova.app.administracion.dto.query.QueryPoaDto;
@Service
@Slf4j
public class ConsultaPametricaGeneralService implements IConsultaPametricaGeneralService {
    @Autowired
    IAccionCortoPlazoRepository vIAccionCortoPlazoRepository;
    @Autowired
    IPoaRepository vIPoaRepository;
    @Autowired
    IAreaRepository vIAreaRepository;
    @Autowired
    IConsultasQuery vIConsultasQuery;
    @Autowired
    IDominioService vIDominioService;
    private DocumentoExcelYpfbUtil vExcelArchivo;
    @Autowired
    private JwtServiceContext jwtAuthenticationFilter;
    @Autowired
    IPresupuestoRepository vIPresupuestoRepository;
    @Autowired
    IBitacoraService vIBitacoraService;

    @Autowired
    IUnidadEjecutoraRepository vIUnidadEjecutoraRepository;
    @Autowired
    IConfiguracionService vIConfiguracionService;

    @Override
    public List<ConsultaParametricaDtoResp> listaPoa() {
        List<Poa> listaAcp =
                vIPoaRepository.listadoPoa();
        return listaAcp.stream().map(p -> {
            ConsultaParametricaDtoResp consultaParametricaDtoResp = new ConsultaParametricaDtoResp();
            consultaParametricaDtoResp.setId(p.getId());
            consultaParametricaDtoResp.setSigla(p.getGestion().toString());
            consultaParametricaDtoResp.setDescripcion(p.getDescripcion());
            return consultaParametricaDtoResp;

        }).collect(Collectors.toList());
    }

    @Override
    public List<ConsultaParametricaDtoResp> listaVersionPoa(ConsultaVersionesPoaDtoReq pConsultaVersionesPoaDtoReq) {
        List<QueryVersionPoa> listaArea =
                vIConsultasQuery.listaVersionesPoaPorPoa(pConsultaVersionesPoaDtoReq.getIdPoa());
        return listaArea.stream().map(p -> {
            ConsultaParametricaDtoResp consultaParametricaDtoResp = new ConsultaParametricaDtoResp();
            consultaParametricaDtoResp.setId(p.getId());
            consultaParametricaDtoResp.setSigla(p.getCodigoVersion());
            consultaParametricaDtoResp.setDescripcion("Version " + p.getCodigoVersion() + " - " + p.getDescripcion());
            return consultaParametricaDtoResp;

        }).collect(Collectors.toList());
    }


    @Override
    public List<ConsultaParametricaDtoResp> listaAccionesCortoPlazoPorPoa(ConsultaAccionCortoPlazoDtoReq pConsultaAccionCortoPlazoDtoReq) {
        List<QueryAccionCortoPlazoPorPoa> listaAcp =
                vIConsultasQuery.listaAccionCortoPlazoPorVersion(pConsultaAccionCortoPlazoDtoReq.getIdVersionPoa());
        return listaAcp.stream().map(p -> {
            ConsultaParametricaDtoResp consultaParametricaDtoResp = new ConsultaParametricaDtoResp();
            consultaParametricaDtoResp.setId(p.getId());
            consultaParametricaDtoResp.setSigla(p.getCodigo().toString());
            consultaParametricaDtoResp.setDescripcion(p.getCodigo() + " - " + p.getDescripcion());
            return consultaParametricaDtoResp;

        }).collect(Collectors.toList());
    }

    @Override
    public List<ConsultaParametricaDtoResp> listaUnidadesPorPoa(ConsultaUnidadesDtoReq pConsultaUnidadesDtoReq) {

        List<QueryAreaPorPoa> listaArea =
                vIConsultasQuery.listaUnidadesPorPoa(pConsultaUnidadesDtoReq.getIdVersionPoa());
        List<ConsultaParametricaDtoResp> resultado =  listaArea.stream().map(p -> {
            ConsultaParametricaDtoResp consultaParametricaDtoResp = new ConsultaParametricaDtoResp();
            consultaParametricaDtoResp.setId(p.getIdPoaArea());
            consultaParametricaDtoResp.setSigla(p.getSigla() + " (" + p.getCodigoArea() + ")");
            consultaParametricaDtoResp.setDescripcion(p.getSigla() + " - " + p.getDescripcion());
            consultaParametricaDtoResp.setCodigoArea(p.getCodigoArea());
            return consultaParametricaDtoResp;

        }).collect(Collectors.toList());



        return resultado;
    }

    @Override
    public List<ConsultaParametricaDtoResp> listaOperacionesPorPoa(ConsultaOperacionesDtoReq pConsultaUnidadesDtoReq) {

        List<QueryOperacionesPorPoa> listaOperaciones =
                vIConsultasQuery.listaOperacionesPorPoa(pConsultaUnidadesDtoReq.getIdVersionPoa());
        return listaOperaciones.stream().map(p -> {
            ConsultaParametricaDtoResp consultaParametricaDtoResp = new ConsultaParametricaDtoResp();
            consultaParametricaDtoResp.setId(p.getIdOperacion());
            consultaParametricaDtoResp.setSigla(p.getCodigoOperacion());
            consultaParametricaDtoResp.setDescripcion(p.getCodigoOperacion() + " " + p.getDescripcion());
            consultaParametricaDtoResp.setCodigoArea(p.getCodigoArea());
            consultaParametricaDtoResp.setCodigoOperacion(p.getCodigoOperacion());
            return consultaParametricaDtoResp;

        }).collect(Collectors.toList());
    }

    @Override
    public List<ConsultaParametricaDtoResp> listaTareasPorPoa(ConsultaTareasDtoReq pConsultaTareasDtoReq) {

        List<QueryTareasPorPoa> listaOperaciones =
                vIConsultasQuery.listaTareasPorPoa(pConsultaTareasDtoReq.getIdVersionPoa());
        return listaOperaciones.stream().map(p -> {
            ConsultaParametricaDtoResp consultaParametricaDtoResp = new ConsultaParametricaDtoResp();
            consultaParametricaDtoResp.setId(p.getIdTarea());
            consultaParametricaDtoResp.setSigla(p.getCodigoTarea());
            consultaParametricaDtoResp.setDescripcion(p.getCodigoTarea() + " " + p.getDescripcionTarea());
            consultaParametricaDtoResp.setCodigoArea(p.getCodigoArea());
            consultaParametricaDtoResp.setCodigoOperacion(p.getCodigoOperacion());
            return consultaParametricaDtoResp;

        }).collect(Collectors.toList());
    }

    @Override
    public List<ConsultaParametricaDtoResp> listaTipoTareasPorPoa(ConsultaTipoTareasDtoReq pConsultaTipoTareasDtoReq) {

        List<QueryTipoTareasPorPoa> listaOperaciones =
                vIConsultasQuery.listaTipoTareasPorVersionPoa(pConsultaTipoTareasDtoReq.getIdVersionPoa());
        return listaOperaciones.stream().map(p -> {
            ConsultaParametricaDtoResp consultaParametricaDtoResp = new ConsultaParametricaDtoResp();
            consultaParametricaDtoResp.setId(p.getId());
            consultaParametricaDtoResp.setSigla(p.getDescripcion());
            consultaParametricaDtoResp.setDescripcion(p.getDescripcion());
            return consultaParametricaDtoResp;

        }).collect(Collectors.toList());
    }

    @Override
    public List<ConsultaParametricaDtoResp> listaResponsablesTareasPorPoa(ConsultaResponsableTareasDtoReq pConsultaResponsableTareasDtoReq) {

        List<QueryAreasResponsablesPorPoa> listaOperaciones =
                vIConsultasQuery.listaAreasResponsablesPorPoa(pConsultaResponsableTareasDtoReq.getIdVersionPoa());
        return listaOperaciones.stream().map(p -> {
            ConsultaParametricaDtoResp consultaParametricaDtoResp = new ConsultaParametricaDtoResp();
            consultaParametricaDtoResp.setId(p.getId());
            consultaParametricaDtoResp.setSigla(p.getSiglaGthc());
            consultaParametricaDtoResp.setDescripcion(p.getSiglaGthc() + " " + p.getDescripcion());
            return consultaParametricaDtoResp;

        }).collect(Collectors.toList());
    }

    @Override
    public List<ConsultaParametricaDtoResp> listaCategoriaProgamPorPoa(ConsultaCategProgramDtoReq pConsultaResponsableTareasDtoReq) {

        List<QueryCategoriaProgramaticaPorPoa> listaGeneral =
                vIConsultasQuery.listaCategoriaProgramaticaPorPoa(pConsultaResponsableTareasDtoReq.getIdVersionPoa());
        return listaGeneral.stream().map(p -> {
            ConsultaParametricaDtoResp consultaParametricaDtoResp = new ConsultaParametricaDtoResp();
            consultaParametricaDtoResp.setId(p.getId());
            consultaParametricaDtoResp.setSigla(p.getPrograma() + " " + p.getProyecto() + " " + p.getActividad());
            consultaParametricaDtoResp.setDescripcion(p.getPrograma() + " " + p.getProyecto() + " " + p.getActividad());
            return consultaParametricaDtoResp;

        }).collect(Collectors.toList());
    }

    @Override
    public List<ConsultaParametricaDtoResp> listaDireccionAdminPorPoa(ConsultaDirecAdminDtoReq pConsultaResponsableTareasDtoReq) {

        List<QueryDireccionAdministrativaPorPoa> listaGeneral =
                vIConsultasQuery.listaDireccionAdminPorPoa(pConsultaResponsableTareasDtoReq.getIdVersionPoa());
        return listaGeneral.stream().map(p -> {
            ConsultaParametricaDtoResp consultaParametricaDtoResp = new ConsultaParametricaDtoResp();
            consultaParametricaDtoResp.setId(p.getId());
            consultaParametricaDtoResp.setSigla(p.getCodigo());
            consultaParametricaDtoResp.setDescripcion(p.getNombre());
            return consultaParametricaDtoResp;

        }).collect(Collectors.toList());
    }

    @Override
    public List<ConsultaParametricaDtoResp> listaPartidaPresupuestariaPorPoa(ConsultaPartidaPresupDtoReq pConsultaResponsableTareasDtoReq) {

        List<QueryPartidaPresupuestariaPorPoa> listaGeneral =
                vIConsultasQuery.listaPartidadPresupuestariaPorPoa(pConsultaResponsableTareasDtoReq.getIdVersionPoa());
        return listaGeneral.stream().map(p -> {
            ConsultaParametricaDtoResp consultaParametricaDtoResp = new ConsultaParametricaDtoResp();
            consultaParametricaDtoResp.setId(p.getId());
            consultaParametricaDtoResp.setSigla(p.getCodigo());
            consultaParametricaDtoResp.setDescripcion(p.getCodigo() + " " + p.getDescripcion());
            return consultaParametricaDtoResp;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ConsultaParametricaDtoResp> listaUnidadEjecutoraPorPoa(ConsultaUnidadEjecturaDtoReq pConsultaResponsableTareasDtoReq) {

        List<QueryUnidadEjecutoraPorPoa> listaGeneral =
                vIConsultasQuery.listaUnidadEjcutoraPorPoa(pConsultaResponsableTareasDtoReq.getIdVersionPoa());
        return listaGeneral.stream().map(p -> {
            ConsultaParametricaDtoResp consultaParametricaDtoResp = new ConsultaParametricaDtoResp();
            consultaParametricaDtoResp.setId(p.getId());
            consultaParametricaDtoResp.setSigla(p.getCodigo());
            consultaParametricaDtoResp.setDescripcion(p.getCodigo() + " " + p.getNombre());
            return consultaParametricaDtoResp;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ConsultaParametricaDtoResp> listaUnidadEjecutoraPorArea(Long idArea) {
        QueryPoaDto poaSel = vIConfiguracionService.poaSeleccionado();
        List<UnidadEjecutora> listaGeneral =
        vIUnidadEjecutoraRepository.porIdArea(idArea,poaSel.getGestion());
        return listaGeneral.stream().map(p -> {
            ConsultaParametricaDtoResp consultaParametricaDtoResp = new ConsultaParametricaDtoResp();
            consultaParametricaDtoResp.setId(p.getId());
            consultaParametricaDtoResp.setSigla(p.getCodigo());
            consultaParametricaDtoResp.setDescripcion(p.getCodigo() + " " + p.getNombre());
            return consultaParametricaDtoResp;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ConsultaParametricaDtoResp> listaTipoPresupuestoPorPoa(
            ConsultaTipoPresupuestoDtoReq consultaDtoReq
    ) {
        List<QueryTipoPresupuestoPorPoa> listaGeneral =
                vIConsultasQuery.listaTipoPresupuestoPorVersionPoa(consultaDtoReq.getIdVersionPoa());
        return listaGeneral.stream().map(p -> {
            ConsultaParametricaDtoResp consultaParametricaDtoResp = new ConsultaParametricaDtoResp();
            consultaParametricaDtoResp.setId(p.getId());
            consultaParametricaDtoResp.setSigla(p.getDescripcion());
            consultaParametricaDtoResp.setDescripcion(p.getDescripcion());
            return consultaParametricaDtoResp;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ConsultaParametricaDtoResp> listaFuenteOrgFiunaciadorPorPoa(ConsultaOrgFinanDtoReq pConsultaResponsableTareasDtoReq) {
        List<QueryFuenteOrgFinanPorPoa> listaGeneral =
                vIConsultasQuery.listaFuenteOrgFinanPorPoa(pConsultaResponsableTareasDtoReq.getIdVersionPoa());
        return listaGeneral.stream().map(p -> {
            ConsultaParametricaDtoResp consultaParametricaDtoResp = new ConsultaParametricaDtoResp();
            consultaParametricaDtoResp.setId(p.getId());
            consultaParametricaDtoResp.setSigla(p.getCodigo());
            consultaParametricaDtoResp.setDescripcion(p.getCodigo());
            return consultaParametricaDtoResp;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ConsultaParametricaDtoResp> listaResponsablesAreasValidadorasPorPoa(
            ConsultaAreasValidadorasConsultoriaDtoReq pConsultaAreasValidadorasConsultoriaDtoReq) {

        List<QueryAreasResponsablesPorPoa> listaOperaciones =
                vIConsultasQuery.listaAreasValidadoreasConsultoriaPorPoa(pConsultaAreasValidadorasConsultoriaDtoReq.getIdVersionPoa());
        return listaOperaciones.stream().map(p -> {
            ConsultaParametricaDtoResp consultaParametricaDtoResp = new ConsultaParametricaDtoResp();
            consultaParametricaDtoResp.setId(p.getId());
            consultaParametricaDtoResp.setSigla(p.getSiglaGthc());
            consultaParametricaDtoResp.setDescripcion(p.getSiglaGthc() + " " + p.getDescripcion());
            return consultaParametricaDtoResp;

        }).collect(Collectors.toList());
    }

    private String standarizarTexto (String texto){
        texto = texto.replace("CRITERIOS DE BUSQUEDA:\n","");
        texto = texto.replace("\n"," ; ");
        return texto;
    }

    @Override
    public ConsultaOperacionesDtoResp consultaOperacion(
            ConsultaTrazaGeneralOperacionesReq pConsultaTrazaGeneralOperacionesReq) {



        ConsultaOperacionesDtoResp resultado =
                vIConsultasQuery.consultaGeneralOperacion(pConsultaTrazaGeneralOperacionesReq);

        if(pConsultaTrazaGeneralOperacionesReq.getPagina().equals(1)) {
            String mensaje = "Se realizó una consulta de la información de OPERACIONES DEL POA con los siguientes criterios de búsqueda: ";
            String criterios = generarCabeceraOperacionesParaExcel(pConsultaTrazaGeneralOperacionesReq);
            vIBitacoraService.guardarBitacoraApiLogger(mensaje + standarizarTexto(criterios));
        }
        return resultado;

    }

    @Override
    public ConsultaTareasDtoResp consultaTareas(
            ConsultaTrazaGeneralTareasReq pConsultaTrazaGeneralOperacionesReq) {



        ConsultaTareasDtoResp resultado =
                vIConsultasQuery.consultaGeneralTarea(pConsultaTrazaGeneralOperacionesReq);
        if(pConsultaTrazaGeneralOperacionesReq.getPagina().equals(1)) {
            String mensaje = "Se realizó una consulta de la información de TAREAS DEL POA con los siguientes criterios de búsqueda: ";
            String criterios = generarCabeceraTareasParaExcel(pConsultaTrazaGeneralOperacionesReq);
            vIBitacoraService.guardarBitacoraApiLogger(mensaje + standarizarTexto(criterios));
        }
        return resultado;

    }

    @Override
    public ConsultaPresupuestoDtoResp consultaPresupuesto(
            ConsultaTrazaGeneralPresupuestoReq consultaTrazaGeneralPresupuestoReq) {



        ConsultaPresupuestoDtoResp resultado =
                vIConsultasQuery.consultaGeneralPresupuesto(consultaTrazaGeneralPresupuestoReq);
        if(consultaTrazaGeneralPresupuestoReq.getPagina().equals(1)) {
            String mensaje = "Se realizó una consulta de la información de PRESUPUESTO DEL POA con los siguientes criterios de búsqueda: ";
            String criterios = generarCabeceraPresupuestoParaExcel(consultaTrazaGeneralPresupuestoReq);
            vIBitacoraService.guardarBitacoraApiLogger(mensaje + standarizarTexto(criterios));
        }
        return resultado;

    }

    @Override
    public ConsultaConsultoriaDtoResp consultaConsultoria(
            ConsultaTrazaGeneralConsultoriaReq consultaTrazaGeneralConsultoriaReq) {



        ConsultaConsultoriaDtoResp resultado =
                vIConsultasQuery.consultaGeneralConsultoria(consultaTrazaGeneralConsultoriaReq);
        if(consultaTrazaGeneralConsultoriaReq.getPagina().equals(1)) {
            String mensaje = "Se realizó una consulta de la información de CONSULTORIA DEL POA con los siguientes criterios de búsqueda: ";
            String criterios = generarCabeceraConsultoriaParaExcel(consultaTrazaGeneralConsultoriaReq);
            vIBitacoraService.guardarBitacoraApiLogger(mensaje + standarizarTexto(criterios));
        }
        return resultado;

    }

    @Override
    @Transactional(readOnly = true)
    public void exportarConsultaDatosOperacionesEnExcel(ConsultaTrazaGeneralOperacionesReq pConsultaTrazaGeneralOperacionesReq, HttpServletResponse pHttpServletResponse) {
        vExcelArchivo = new DocumentoExcelYpfbUtil();
        vExcelArchivo.obtenerArchivoXls(PLANTILLA_CONSULTA_XLS_OPERACIONES_PATH);
        List<ConsultaOperacionesConTotalesExcel> resultados =
                generarDatosOperacionesParaExcel(pConsultaTrazaGeneralOperacionesReq);


        XSSFSheet vHojaExcelTipoGasto = vExcelArchivo.obtenerHojaXls(ConstantesReporte.EnumHojasConsultasExcel.OPERACIONES.nombre);
        vExcelArchivo.escribirListaEnExcel(vHojaExcelTipoGasto, 7, 1,
                resultados, ConsultaOperacionesConTotalesExcel.class, ExcelConstantes.EnumTipoPrimeraColumna.CONTADOR, true);
        String cabecera = generarCabeceraOperacionesParaExcel(pConsultaTrazaGeneralOperacionesReq);
        RichTextString richText = vExcelArchivo.getArchivExcel().getCreationHelper().createRichTextString(cabecera);

        vExcelArchivo.escribirCeldaTextoIndependienteEnExcel(vHojaExcelTipoGasto,
                2, 1,
                richText);

        String nombreArchivoExcelExportado = generarNombreArchivoExcel(
                NOMBRE_EXCEL_REVISION_GAFC_PRESUPUESTO, resultados.get(0).getPoaGestion(), "OPERACIONES");

        vExcelArchivo.escribirExcelRequest(pHttpServletResponse, nombreArchivoExcelExportado);

        String mensaje = "Se realizó una exportacion en formato excel de la información de OPERACIONES DEL POA con los siguientes criterios de búsqueda: ";
        String criterios = generarCabeceraOperacionesParaExcel(pConsultaTrazaGeneralOperacionesReq);
        vIBitacoraService.guardarBitacoraApiLogger(mensaje + standarizarTexto(criterios));
    }
    @Override
    @Transactional(readOnly = true)
    public void exportarConsultaDatosTareasEnExcel(ConsultaTrazaGeneralTareasReq pConsultaTrazaGeneralTareasReq, HttpServletResponse pHttpServletResponse) {
        vExcelArchivo = new DocumentoExcelYpfbUtil();
        vExcelArchivo.obtenerArchivoXls(PLANTILLA_CONSULTA_XLS_TAREAS_PATH);
        List<ConsultaTareasConTotalesExcel> resultados =
                generarDatosTareasParaExcel(pConsultaTrazaGeneralTareasReq);


        XSSFSheet vHojaExcelTipoGasto = vExcelArchivo.obtenerHojaXls(ConstantesReporte.EnumHojasConsultasExcel.TAREAS.nombre);
        vExcelArchivo.escribirListaEnExcel(vHojaExcelTipoGasto, 7, 1,
                resultados, ConsultaTareasConTotalesExcel.class, ExcelConstantes.EnumTipoPrimeraColumna.CONTADOR, true);
        String cabecera = generarCabeceraTareasParaExcel(pConsultaTrazaGeneralTareasReq);
        RichTextString richText = vExcelArchivo.getArchivExcel().getCreationHelper().createRichTextString(cabecera);

        vExcelArchivo.escribirCeldaTextoIndependienteEnExcel(vHojaExcelTipoGasto,
                2, 1,
                richText);

        String nombreArchivoExcelExportado = generarNombreArchivoExcel(
                NOMBRE_EXCEL_REVISION_GAFC_PRESUPUESTO, resultados.get(0).getPoaGestion(), "TAREAS");

        vExcelArchivo.escribirExcelRequest(pHttpServletResponse, nombreArchivoExcelExportado);

        String mensaje = "Se realizó una exportacion en formato excel de la información de TAREAS DEL POA con los siguientes criterios de búsqueda: ";
        String criterios = generarCabeceraTareasParaExcel(pConsultaTrazaGeneralTareasReq);
        vIBitacoraService.guardarBitacoraApiLogger(mensaje + standarizarTexto(criterios));
    }

    @Override
    @Transactional(readOnly = true)
    public void exportarConsultaDatosPresupuestoEnExcel(
            ConsultaTrazaGeneralPresupuestoReq pConsultaTrazaGeneralPresupuestoReq, HttpServletResponse pHttpServletResponse) {
        vExcelArchivo = new DocumentoExcelYpfbUtil();
        vExcelArchivo.obtenerArchivoXls(PLANTILLA_CONSULTA_XLS_PRESUPUESTO_PATH);
        XSSFSheet vHojaExcel = vExcelArchivo.obtenerHojaXls("PRESUPUESTO");

        Poa poaInstance = vIPoaRepository.getById(pConsultaTrazaGeneralPresupuestoReq.getIdPoa());

        vExcelArchivo.escribirCeldaTextoIndependienteEnExcel(vHojaExcel,
                6, 29,
                "TOTAL "+poaInstance.getGestion());
        vExcelArchivo.escribirCeldaTextoIndependienteEnExcel(vHojaExcel,
                4, 24,
                poaInstance.getGestion());
        vExcelArchivo.escribirCeldaTextoIndependienteEnExcel(vHojaExcel,
                6, 30,
                poaInstance.getGestion()+1);
        vExcelArchivo.escribirCeldaTextoIndependienteEnExcel(vHojaExcel,
                6, 31,
                "TOTAL " +poaInstance.getGestion()+"-"+(poaInstance.getGestion()+1));

        List<ConsultaPresupuestoConTotalesExcel> resultados =
                generarDatosPresupuestoParaExcel(pConsultaTrazaGeneralPresupuestoReq);

        XSSFSheet vHojaExcelTipoGasto = vExcelArchivo.obtenerHojaXls(EnumHojasConsultasExcel.PRESUPUESTO.nombre);
        vExcelArchivo.escribirListaEnExcel(vHojaExcelTipoGasto, 7, 1,
                resultados, ConsultaPresupuestoConTotalesExcel.class, ExcelConstantes.EnumTipoPrimeraColumna.CONTADOR, true);
        String cabecera = generarCabeceraPresupuestoParaExcel(pConsultaTrazaGeneralPresupuestoReq);
        RichTextString richText = vExcelArchivo.getArchivExcel().getCreationHelper().createRichTextString(cabecera);

        vExcelArchivo.escribirCeldaTextoIndependienteEnExcel(vHojaExcelTipoGasto,
                2, 1,
                richText);

        String nombreArchivoExcelExportado = generarNombreArchivoExcel(
                NOMBRE_EXCEL_REVISION_GAFC_PRESUPUESTO, resultados.get(0).getPoaGestion(), "PRESUPUESTO");

        vExcelArchivo.escribirExcelRequest(pHttpServletResponse, nombreArchivoExcelExportado);

        String mensaje = "Se realizó una exportacion en formato excel de la información de PRESUPUESTO DEL POA con los siguientes criterios de búsqueda: ";
        String criterios = generarCabeceraPresupuestoParaExcel(pConsultaTrazaGeneralPresupuestoReq);
        vIBitacoraService.guardarBitacoraApiLogger(mensaje + standarizarTexto(criterios));
    }


    @Override
    @Transactional(readOnly = true)
    public void exportarConsultaDatosConsultoriaEnExcel(
            ConsultaTrazaGeneralConsultoriaReq pConsultaTrazaGeneralConsultoriaReq, HttpServletResponse pHttpServletResponse) {
        vExcelArchivo = new DocumentoExcelYpfbUtil();
        vExcelArchivo.obtenerArchivoXls(PLANTILLA_CONSULTA_XLS_CONSULTORIA_PATH);
        List<ConsultaConsultoriaConTotalesExcel> resultados =
                generarDatosConsultoriaParaExcel(pConsultaTrazaGeneralConsultoriaReq);


        XSSFSheet vHojaExcelTipoGasto = vExcelArchivo.obtenerHojaXls(EnumHojasConsultasExcel.CONSULTORIA.nombre);
        vExcelArchivo.escribirListaEnExcel(vHojaExcelTipoGasto, 7, 1,
                resultados, ConsultaConsultoriaConTotalesExcel.class, ExcelConstantes.EnumTipoPrimeraColumna.CONTADOR, true);
        String cabecera = generarCabeceraConsultoriaParaExcel(pConsultaTrazaGeneralConsultoriaReq);
        RichTextString richText = vExcelArchivo.getArchivExcel().getCreationHelper().createRichTextString(cabecera);

        vExcelArchivo.escribirCeldaTextoIndependienteEnExcel(vHojaExcelTipoGasto,
                2, 1,
                richText);

        String nombreArchivoExcelExportado = generarNombreArchivoExcel(
                NOMBRE_EXCEL_REVISION_GAFC_PRESUPUESTO, resultados.get(0).getPoaGestion(), "CONSULTORIA");

        vExcelArchivo.escribirExcelRequest(pHttpServletResponse, nombreArchivoExcelExportado);

        String mensaje = "Se realizó una exportacion en formato excel de la información de CONSULTORIA DEL POA con los siguientes criterios de búsqueda: ";
        String criterios = generarCabeceraConsultoriaParaExcel(pConsultaTrazaGeneralConsultoriaReq);
        vIBitacoraService.guardarBitacoraApiLogger(mensaje + standarizarTexto(criterios));
    }

    private List<ConsultaConsultoriaConTotalesExcel> generarDatosConsultoriaParaExcel(
            ConsultaTrazaGeneralConsultoriaReq pConsultaTrazaGeneralConsultoriaReq) {
        //llamar la consulta de detalle sin paginacion
        //ConsultaTrazaGeneralPresupuestoReq consultaTrazaGeneralPresupuestoReq
        pConsultaTrazaGeneralConsultoriaReq.setResultadosPorPagina(-1);

        ConsultaConsultoriaDtoResp resultado =
                vIConsultasQuery.consultaGeneralConsultoria(pConsultaTrazaGeneralConsultoriaReq);
        ModelMapper vMapper = new ModelMapper();
        List<ConsultaPresupuestoConTotales> detalle = resultado.getDetalle();

        List<ConsultaConsultoriaConTotalesExcel> listaDetalle = detalle.stream()
                .map(e -> {
                    return vMapper.map(e, ConsultaConsultoriaConTotalesExcel.class);
                }).collect(Collectors.toList());
        Conversion conversion = new Conversion();
        StringBuilder areas = new StringBuilder();

        listaDetalle.forEach(p->
                {
                    p.setPresDireccionAdmin(p.getPresCodigoDireccionAdmin());
                    p.setPresCodigoFuenteFinanciamiento(p.getPresCodigoFuenteFinanciamiento()+"-"+p.getPresCodigoOrganismoFinanciador());

                    List<AreasResponsablesExcelDto> lista = new ArrayList<>();
                    areas.setLength(0);
                    try {
                        if (p.getTarAreaResponsable() != null) {
                            ObjectMapper objectMapper = new ObjectMapper();
                            lista =
                                    objectMapper.readValue(p.getTarAreaResponsable(),
                                            new TypeReference<List<AreasResponsablesExcelDto>>() {
                                            });

                            lista.stream().map(m->m.getSigla()+"(" +conversion.decimalToString(m.getPorcentaje())+"%) ; ")
                                    .collect(Collectors.toList()).forEach(b->
                                            {
                                                areas.append(b);
                                            }
                                    );

                        }
                    } catch (Exception exp) {
                        throw new IntegridadYpfbException("No se pudo leer el dato de gestiones futuras en presupuesto:" + exp);
                    }

                    p.setTarAreaResponsable(areas.toString());

                    if(p.getConsAreasValidadorasConsultoria()!=null && !p.getConsAreasValidadorasConsultoria().equals("")) {
                        List<AreasResponsablesExcelDto> listaValidadoras = new ArrayList<>();
                        areas.setLength(0);
                        try {

                                ObjectMapper objectMapper = new ObjectMapper();
                                lista =
                                        objectMapper.readValue(p.getConsAreasValidadorasConsultoria(),
                                                new TypeReference<List<AreasResponsablesExcelDto>>() {
                                                });

                                lista.stream().map(m -> m.getSigla() + " ; ")
                                        .collect(Collectors.toList()).forEach(b ->
                                                {
                                                    areas.append(b);
                                                }
                                        );


                        } catch (Exception exp) {
                            throw new IntegridadYpfbException("No se pudo leer el dato de gestiones futuras en presupuesto:" + exp);
                        }
                        p.setConsAreasValidadorasConsultoria(areas.toString());
                    }



                }
        );


        return listaDetalle;
    }


    private String generarCabeceraConsultoriaParaExcel(ConsultaTrazaGeneralConsultoriaReq pConsultaTrazaGeneralConsultoriaReq) {
        StringBuilder cabecera = new StringBuilder();
        cabecera.append("CRITERIOS DE BUSQUEDA:\n");
        cabecera.append("Descripcion Poa: ").append(pConsultaTrazaGeneralConsultoriaReq.getDescripcionPoa()).append("\n");
        if (pConsultaTrazaGeneralConsultoriaReq.getOperIdAccionCortoPlazo() != null) {
            cabecera.append("Accion a Corto Plazo: ").append(pConsultaTrazaGeneralConsultoriaReq.getOperAccionCortoPlazo()).append("\n");
        }
        if (!pConsultaTrazaGeneralConsultoriaReq.getAreas().isEmpty()) {
            cabecera.append("Area(s): ");
            for (ConsultaArea area : pConsultaTrazaGeneralConsultoriaReq.getAreas()) {
                cabecera.append(area.getAreaSigla()).append(",");
            }
            cabecera.append("\n");
        }
        if (!pConsultaTrazaGeneralConsultoriaReq.getOperaciones().isEmpty()) {
            cabecera.append("Operaciones(s): ");
            for (ConsultaOperacion operacion : pConsultaTrazaGeneralConsultoriaReq.getOperaciones()) {
                cabecera.append(operacion.getOperCodigoOperacion()).append(",");
            }
            cabecera.append("\n");
        }
        if (pConsultaTrazaGeneralConsultoriaReq.getTarIdTipoTarea() != null) {
            cabecera.append("Tipo de Tarea: ").append(pConsultaTrazaGeneralConsultoriaReq.getTarTipoTarea()).append("\n");
        }
        if (!pConsultaTrazaGeneralConsultoriaReq.getTareas().isEmpty()) {
            cabecera.append("Tareas(s): ");
            for (ConsultaTarea tarea : pConsultaTrazaGeneralConsultoriaReq.getTareas()) {
                cabecera.append(tarea.getTarCodigoTarea()).append(",");
            }
            cabecera.append("\n");
        }
        if (!pConsultaTrazaGeneralConsultoriaReq.getAreasResponsables().isEmpty()) {
            cabecera.append("Areas responsables de las Tarea(s): ");
            for (ConsultaAreaResponsable area : pConsultaTrazaGeneralConsultoriaReq.getAreasResponsables()) {
                cabecera.append(area.getAreaSigla()).append(",");
            }
            cabecera.append("\n");
        }

        if (pConsultaTrazaGeneralConsultoriaReq.getPresIdTipo() != null) {
            cabecera.append("Tipo de Presupuesto: ").append(pConsultaTrazaGeneralConsultoriaReq.getPresTipoPresupuesto()).append("\n");
        }
        if (!pConsultaTrazaGeneralConsultoriaReq.getCategorias().isEmpty()) {
            cabecera.append("Categoria(s) Programatica(s): ");
            for (ConsultaCategoriaProgramatica categoriaProgramatica : pConsultaTrazaGeneralConsultoriaReq.getCategorias()) {
                cabecera.append(categoriaProgramatica.getPresCategoriaProgramatica()).append(",");
            }
            cabecera.append("\n");
        }
        if (!pConsultaTrazaGeneralConsultoriaReq.getPartidas().isEmpty()) {
            cabecera.append("Partida(s) Presupuestaria(s): ");
            for (ConsultaPartidaPresupuestaria consultaPartidaPresupuestaria : pConsultaTrazaGeneralConsultoriaReq.getPartidas()) {
                cabecera.append(consultaPartidaPresupuestaria.getPresCodigoPartidaPresupuestaria()).append(",");
            }
            cabecera.append("\n");
        }
        if (pConsultaTrazaGeneralConsultoriaReq.getPresIdFuenteOrganismoFinanciador() != null) {
            cabecera.append("Fuente Organismo Financiador: ").append(pConsultaTrazaGeneralConsultoriaReq.getPresCodigoFuenteOrganismoFinanciador()).append("\n");
        }
        if (!pConsultaTrazaGeneralConsultoriaReq.getUnidadEjecutora().isEmpty()) {
            cabecera.append("Unidad(es) Ejecutora(s): ");
            for (ConsultaUnidadEjecutora unidadEjecutora : pConsultaTrazaGeneralConsultoriaReq.getUnidadEjecutora()) {
                cabecera.append(unidadEjecutora.getPresCodigoUnidadEjecutora()).append(",");
            }
            cabecera.append("\n");
        }
        if (pConsultaTrazaGeneralConsultoriaReq.getDireccionAdminId() != null) {
            cabecera.append("Direccion Administrativa: ").append(pConsultaTrazaGeneralConsultoriaReq.getDireccionAdmin()).append("\n");
        }
        if (pConsultaTrazaGeneralConsultoriaReq.getPresDetalleGasto() != null) {
            cabecera.append("Detalle de Gasto: ").append(pConsultaTrazaGeneralConsultoriaReq.getPresDetalleGasto()).append("\n");
        }

        if (!pConsultaTrazaGeneralConsultoriaReq.getAreasResponsableConsultoria().isEmpty()) {
            cabecera.append("Areas validadoras de Consultoria: ");
            for (
                    ConsultaArea consultaArea : pConsultaTrazaGeneralConsultoriaReq.getAreasResponsableConsultoria()) {
                cabecera.append(consultaArea.getAreaSigla()).append(",");
            }
            cabecera.append("\n");
        }


        return cabecera.toString();
    }



    private List<ConsultaPresupuestoConTotalesExcel> generarDatosPresupuestoParaExcel(
            ConsultaTrazaGeneralPresupuestoReq pConsultaTrazaGeneralPresupuestoReq) {
        //llamar la consulta de detalle sin paginacion
        //ConsultaTrazaGeneralPresupuestoReq consultaTrazaGeneralPresupuestoReq
        pConsultaTrazaGeneralPresupuestoReq.setResultadosPorPagina(-1);

        ConsultaPresupuestoDtoResp resultado =
                vIConsultasQuery.consultaGeneralPresupuesto(pConsultaTrazaGeneralPresupuestoReq);
        ModelMapper vMapper = new ModelMapper();
        List<ConsultaPresupuestoConTotales> detalle = resultado.getDetalle();

        List<ConsultaPresupuestoConTotalesExcel> listaDetalle = detalle.stream()
                .map(e -> {
                    return vMapper.map(e, ConsultaPresupuestoConTotalesExcel.class);
                }).collect(Collectors.toList());
        Conversion conversion = new Conversion();
        StringBuilder areas = new StringBuilder();

        listaDetalle.forEach(p->
                {
                    p.setPresDireccionAdmin(p.getPresCodigoDireccionAdmin());

                    p.setPresCodigoFuenteFinanciamiento(p.getPresCodigoFuenteFinanciamiento()+"-"+p.getPresCodigoOrganismoFinanciador());

                    List<AreasResponsablesExcelDto> lista = new ArrayList<>();
                    areas.setLength(0);
                    try {
                        if (p.getTarAreaResponsable() != null) {
                            ObjectMapper objectMapper = new ObjectMapper();
                            lista =
                                    objectMapper.readValue(p.getTarAreaResponsable(),
                                            new TypeReference<List<AreasResponsablesExcelDto>>() {
                                            });

                            lista.stream().map(m->m.getSigla()+"(" +conversion.decimalToString(m.getPorcentaje())+"%) ; ")
                                    .collect(Collectors.toList()).forEach(b->
                                            {
                                                areas.append(b);
                                            }
                                    );

                        }
                    } catch (Exception exp) {
                        throw new IntegridadYpfbException("No se pudo leer el dato de gestiones futuras en presupuesto:" + exp);
                    }

                    p.setTarAreaResponsable(areas.toString());

                }
        );


        return listaDetalle;
    }


    private String generarCabeceraPresupuestoParaExcel(ConsultaTrazaGeneralPresupuestoReq pConsultaTrazaGeneralPresupuestoReq) {
        StringBuilder cabecera = new StringBuilder();
        cabecera.append("CRITERIOS DE BUSQUEDA:\n");
        cabecera.append("Descripcion Poa: ").append(pConsultaTrazaGeneralPresupuestoReq.getDescripcionPoa()).append("\n");
        if (pConsultaTrazaGeneralPresupuestoReq.getOperIdAccionCortoPlazo() != null) {
            cabecera.append("Accion a Corto Plazo: ").append(pConsultaTrazaGeneralPresupuestoReq.getOperAccionCortoPlazo()).append("\n");
        }
        if (!pConsultaTrazaGeneralPresupuestoReq.getAreas().isEmpty()) {
            cabecera.append("Area(s): ");
            for (ConsultaArea area : pConsultaTrazaGeneralPresupuestoReq.getAreas()) {
                cabecera.append(area.getAreaSigla()).append(",");
            }
            cabecera.append("\n");
        }
        if (!pConsultaTrazaGeneralPresupuestoReq.getOperaciones().isEmpty()) {
            cabecera.append("Operaciones(s): ");
            for (ConsultaOperacion operacion : pConsultaTrazaGeneralPresupuestoReq.getOperaciones()) {
                cabecera.append(operacion.getOperCodigoOperacion()).append(",");
            }
            cabecera.append("\n");
        }
        if (pConsultaTrazaGeneralPresupuestoReq.getTarIdTipoTarea() != null) {
            cabecera.append("Tipo de Tarea: ").append(pConsultaTrazaGeneralPresupuestoReq.getTarTipoTarea()).append("\n");
        }
        if (!pConsultaTrazaGeneralPresupuestoReq.getTareas().isEmpty()) {
            cabecera.append("Tareas(s): ");
            for (ConsultaTarea tarea : pConsultaTrazaGeneralPresupuestoReq.getTareas()) {
                cabecera.append(tarea.getTarCodigoTarea()).append(",");
            }
            cabecera.append("\n");
        }
        if (!pConsultaTrazaGeneralPresupuestoReq.getAreasResponsables().isEmpty()) {
            cabecera.append("Areas responsables de las Tarea(s): ");
            for (ConsultaAreaResponsable area : pConsultaTrazaGeneralPresupuestoReq.getAreasResponsables()) {
                cabecera.append(area.getAreaSigla()).append(",");
            }
            cabecera.append("\n");
        }

        if (pConsultaTrazaGeneralPresupuestoReq.getPresIdTipo() != null) {
            cabecera.append("Tipo de Presupuesto: ").append(pConsultaTrazaGeneralPresupuestoReq.getPresTipoPresupuesto()).append("\n");
        }
        if (!pConsultaTrazaGeneralPresupuestoReq.getCategorias().isEmpty()) {
            cabecera.append("Categoria(s) Programatica(s): ");
            for (ConsultaCategoriaProgramatica categoriaProgramatica : pConsultaTrazaGeneralPresupuestoReq.getCategorias()) {
                cabecera.append(categoriaProgramatica.getPresCategoriaProgramatica()).append(",");
            }
            cabecera.append("\n");
        }
        if (!pConsultaTrazaGeneralPresupuestoReq.getPartidas().isEmpty()) {
            cabecera.append("Partida(s) Presupuestaria(s): ");
            for (ConsultaPartidaPresupuestaria consultaPartidaPresupuestaria : pConsultaTrazaGeneralPresupuestoReq.getPartidas()) {
                cabecera.append(consultaPartidaPresupuestaria.getPresCodigoPartidaPresupuestaria()).append(",");
            }
            cabecera.append("\n");
        }
        if (pConsultaTrazaGeneralPresupuestoReq.getPresIdFuenteOrganismoFinanciador() != null) {
            cabecera.append("Fuente Organismo Financiador: ").append(pConsultaTrazaGeneralPresupuestoReq.getPresCodigoFuenteOrganismoFinanciador()).append("\n");
        }
        if (!pConsultaTrazaGeneralPresupuestoReq.getUnidadEjecutora().isEmpty()) {
            cabecera.append("Unidad(es) Ejecutora(s): ");
            for (ConsultaUnidadEjecutora unidadEjecutora : pConsultaTrazaGeneralPresupuestoReq.getUnidadEjecutora()) {
                cabecera.append(unidadEjecutora.getPresCodigoUnidadEjecutora()).append(",");
            }
            cabecera.append("\n");
        }
        if (pConsultaTrazaGeneralPresupuestoReq.getDireccionAdminId() != null) {
            cabecera.append("Direccion Administrativa: ").append(pConsultaTrazaGeneralPresupuestoReq.getDireccionAdmin()).append("\n");
        }
        if (pConsultaTrazaGeneralPresupuestoReq.getPresDetalleGasto() != null) {
            cabecera.append("Detalle de Gasto: ").append(pConsultaTrazaGeneralPresupuestoReq.getPresDetalleGasto()).append("\n");
        }
        return cabecera.toString();
    }

    private List<ConsultaTareasConTotalesExcel> generarDatosTareasParaExcel(ConsultaTrazaGeneralTareasReq pConsultaTrazaGeneralOperacionesReq) {
        //llamar la consulta de detalle sin paginacion
        pConsultaTrazaGeneralOperacionesReq.setResultadosPorPagina(-1);

        ConsultaTareasDtoResp resultado =
                vIConsultasQuery.consultaGeneralTarea(pConsultaTrazaGeneralOperacionesReq);
        ModelMapper vMapper = new ModelMapper();
        List<ConsultaTareasConTotales> detalle = resultado.getDetalle();

        List<ConsultaTareasConTotalesExcel> listaDetalle = detalle.stream()
                .map(e -> {
                    return vMapper.map(e, ConsultaTareasConTotalesExcel.class);
                }).collect(Collectors.toList());
        Conversion conversion = new Conversion();
        StringBuilder areas = new StringBuilder();

        listaDetalle.forEach(p->
                {
                    p.setTarRequiereOtraAreaDescripcion("NO");
                    if(Boolean.TRUE.equals(p.getTarRequiereOtraArea())){
                        p.setTarRequiereOtraAreaDescripcion("SI");
                    }
                    List<AreasResponsablesExcelDto> lista = new ArrayList<>();
                    areas.setLength(0);
                    try {
                        if (p.getTarAreaResponsable() != null) {
                            ObjectMapper objectMapper = new ObjectMapper();
                            lista =
                                    objectMapper.readValue(p.getTarAreaResponsable(),
                                            new TypeReference<List<AreasResponsablesExcelDto>>() {
                                            });

                            lista.stream().map(m->m.getSigla()+"(" +conversion.decimalToString(m.getPorcentaje())+"%) ; ")
                                    .collect(Collectors.toList()).forEach(b->
                                            {
                                                    areas.append(b);
                                            }
                                    );

                        }
                    } catch (Exception exp) {
                        throw new IntegridadYpfbException("No se pudo leer el dato de gestiones futuras en presupuesto:" + exp);
                    }

                    p.setTarAreaResponsable(areas.toString());

                }
        );


        return listaDetalle;
    }

    private String generarCabeceraTareasParaExcel(ConsultaTrazaGeneralTareasReq pConsultaTrazaGeneralOperacionesReq) {
        StringBuilder cabecera = new StringBuilder();
        cabecera.append("CRITERIOS DE BUSQUEDA:\n");
        cabecera.append("Descripcion Poa: ").append(pConsultaTrazaGeneralOperacionesReq.getDescripcionPoa()).append("\n");
        if (pConsultaTrazaGeneralOperacionesReq.getOperIdAccionCortoPlazo() != null) {
            cabecera.append("Accion a Corto Plazo: ").append(pConsultaTrazaGeneralOperacionesReq.getOperAccionCortoPlazo()).append("\n");
        }
        if (!pConsultaTrazaGeneralOperacionesReq.getAreas().isEmpty()) {
            cabecera.append("Area(s): ");
            for (ConsultaArea area : pConsultaTrazaGeneralOperacionesReq.getAreas()) {
                cabecera.append(area.getAreaSigla()).append(",");
            }
            cabecera.append("\n");
        }
        if (!pConsultaTrazaGeneralOperacionesReq.getOperaciones().isEmpty()) {
            cabecera.append("Operaciones(s): ");
            for (ConsultaOperacion operacion : pConsultaTrazaGeneralOperacionesReq.getOperaciones()) {
                cabecera.append(operacion.getOperCodigoOperacion()).append(",");
            }
            cabecera.append("\n");
        }
        if (pConsultaTrazaGeneralOperacionesReq.getTarIdTipoTarea() != null) {
            cabecera.append("Tipo de Tarea: ").append(pConsultaTrazaGeneralOperacionesReq.getTarTipoTarea()).append("\n");
        }
        if (!pConsultaTrazaGeneralOperacionesReq.getTareas().isEmpty()) {
            cabecera.append("Tareas(s): ");
            for (ConsultaTarea tarea : pConsultaTrazaGeneralOperacionesReq.getTareas()) {
                cabecera.append(tarea.getTarCodigoTarea()).append(",");
            }
            cabecera.append("\n");
        }
        if (!pConsultaTrazaGeneralOperacionesReq.getAreasResponsables().isEmpty()) {
            cabecera.append("Areas responsables de las Tarea(s): ");
            for (ConsultaAreaResponsable area : pConsultaTrazaGeneralOperacionesReq.getAreasResponsables()) {
                cabecera.append(area.getAreaSigla()).append(",");
            }
            cabecera.append("\n");
        }

        return cabecera.toString();
    }

    //enumerador del tipo de cosnulta
    @Override
    public ConsultaParametrosDefectoDtoResp obtenerValoresDefecto(String modulo){

        ConsultaParametrosDefectoDtoResp vConsultaParametrosDefectoDtoResp = new ConsultaParametrosDefectoDtoResp();

        QueryVersionPoa vUltimoQueryVersionPoa =
        vIConsultasQuery.listaVersionesPoaUltimoMigrado().stream().findFirst().get();

        vConsultaParametrosDefectoDtoResp.setIdPoa(vUltimoQueryVersionPoa.getIdPoa());
        vConsultaParametrosDefectoDtoResp.setIdVersionPoa(vUltimoQueryVersionPoa.getId());


        //escoger poa por defecto
        //escoger version por defecto
        //obtener permiso del usuario si es ROL AREA
        List<EnumTipoConsultas> consultasOper = Arrays.stream(EnumTipoConsultas.values())
                .filter(tipoConsulta -> tipoConsulta.modulo.equals(modulo) && tipoConsulta.tipo.equals("AREA"))
                .collect(Collectors.toList());

        List<QueryResponsableConsulta> listaAreas =
        vIConsultasQuery.listaRolConsultaRolAreas(jwtAuthenticationFilter.getJwtTokenContextAplicacion().getUsername(),
                consultasOper.get(0).rol);

        consultasOper = Arrays.stream(EnumTipoConsultas.values())
                .filter(tipoConsulta -> tipoConsulta.modulo.equals(modulo) && tipoConsulta.tipo.equals("EMPRESA"))
                .collect(Collectors.toList());

        List<QueryResponsableConsulta> listaEmpresa =
                vIConsultasQuery.listaRolConsultaRolGeneral(jwtAuthenticationFilter.getJwtTokenContextAplicacion().getUsername(),
                        consultasOper.get(0).rol);

        consultasOper = Arrays.stream(EnumTipoConsultas.values())
                .filter(tipoConsulta -> tipoConsulta.modulo.equals("*") && tipoConsulta.tipo.equals("EMPRESA"))
                .collect(Collectors.toList());
        List<QueryResponsableConsulta> superUsuarioConsulta =
                vIConsultasQuery.listaRolConsultaRolGeneral(jwtAuthenticationFilter.getJwtTokenContextAplicacion().getUsername(),
                        consultasOper.get(0).rol);

        if(!superUsuarioConsulta.isEmpty()){
            List<Long> lista = new ArrayList<>();
            lista.add(-1L);
            vConsultaParametrosDefectoDtoResp.setAreas(lista);
        }
        //si es rol de area entonces escoger solo las areas en que esta asignado
        else if(listaEmpresa.isEmpty())
        {
           if(!listaAreas.isEmpty()){
               vConsultaParametrosDefectoDtoResp.setAreas(listaAreas.stream().map(p->p.getIdArea()).collect(Collectors.toList()));
           }else{
               throw new ErrorValidacionException("El usuario no tiene permisos para ejecutar esta accion");
           }
        }
        else{
            List<Long> lista = new ArrayList<>();
            lista.add(-1L);
            vConsultaParametrosDefectoDtoResp.setAreas(lista);
        }
        return vConsultaParametrosDefectoDtoResp;
        //devolver id's  id_area[]. id_version , id_poa
    }


    private String generarCabeceraOperacionesParaExcel(ConsultaTrazaGeneralOperacionesReq pConsultaTrazaGeneralOperacionesReq) {
        StringBuilder cabecera = new StringBuilder();
        cabecera.append("CRITERIOS DE BUSQUEDA:\n");
        cabecera.append("Descripcion Poa: ").append(pConsultaTrazaGeneralOperacionesReq.getDescripcionPoa()).append("\n");
        if (pConsultaTrazaGeneralOperacionesReq.getOperIdAccionCortoPlazo() != null) {
            cabecera.append("Accion a Corto Plazo: ").append(pConsultaTrazaGeneralOperacionesReq.getOperAccionCortoPlazo()).append("\n");
        }
        if (!pConsultaTrazaGeneralOperacionesReq.getAreas().isEmpty()) {
            cabecera.append("Area(s): ");
            for (ConsultaArea area : pConsultaTrazaGeneralOperacionesReq.getAreas()) {
                cabecera.append(area.getAreaSigla()).append(",");
            }
            cabecera.append("\n");
        }
        if (!pConsultaTrazaGeneralOperacionesReq.getOperaciones().isEmpty()) {
            cabecera.append("Operaciones(s): ");
            for (ConsultaOperacion operacion : pConsultaTrazaGeneralOperacionesReq.getOperaciones()) {
                cabecera.append(operacion.getOperCodigoOperacion()).append(",");
            }
            cabecera.append("\n");
        }

        return cabecera.toString();
    }

    private List<ConsultaOperacionesConTotalesExcel> generarDatosOperacionesParaExcel(ConsultaTrazaGeneralOperacionesReq pConsultaTrazaGeneralOperacionesReq){
        //llamar la consulta de detalle sin paginacion
        pConsultaTrazaGeneralOperacionesReq.setResultadosPorPagina(-1);

        ConsultaOperacionesDtoResp resultado =
                vIConsultasQuery.consultaGeneralOperacion(pConsultaTrazaGeneralOperacionesReq);
        ModelMapper vMapper = new ModelMapper();
        List<ConsultaOperacionesConTotales> detalle =  resultado.getDetalle();

        List<ConsultaOperacionesConTotalesExcel> listaDetalle = detalle.stream()
                .map(e -> {
                    return vMapper.map(e, ConsultaOperacionesConTotalesExcel.class);
                }).collect(Collectors.toList());

        return listaDetalle;
    }


    protected String generarNombreArchivoExcel(String pPrefijoNombre, Integer pGestion, String informacion) {
        return String.format(pPrefijoNombre, pGestion, informacion);

    }


    @Override
    public void realizarVolcadoVersionDetalle(CrearVersionDetalleReq pCrearVersionDetalleReq){
        vIPresupuestoRepository.crearVersionDetalle(pCrearVersionDetalleReq.getIdPoaArea(),pCrearVersionDetalleReq.getVersion(),pCrearVersionDetalleReq.getCodigoVersion());
    }
    @Override
    public void realizarVolcadoVersionTotales(CrearVersionDetalleReq pCrearVersionDetalleReq){
        vIConsultasQuery.crearVersionTotales(pCrearVersionDetalleReq.getIdPoaArea(),pCrearVersionDetalleReq.getVersion(),pCrearVersionDetalleReq.getCodigoVersion());
    }


}
