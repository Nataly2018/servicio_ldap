package gob.mefp.reportes.app.reportes.service;

import gob.ypfb.nova.app.administracion.dto.query.QueryObtenerDatosCabeceraDto;
import gob.ypfb.nova.app.reportes.dto.query.*;
import gob.ypfb.nova.app.reportes.dto.request.ReportePresupuestoPorAcpJsonDtoReq;
import gob.ypfb.nova.app.reportes.dto.response.*;
import gob.ypfb.nova.app.reportes.query.IConsultaGeneralPoaQuery;
import gob.ypfb.nova.exception.ErrorValidacionException;
import gob.ypfb.nova.security.jwt.JwtServiceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ReporteGeneralOperacionesPoaService implements IReporteGeneralOperacionesPoaService{
    @Autowired
    IConsultaGeneralPoaQuery vIConsultaGeneralPoaQuery;
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    private JwtServiceContext jwtAuthenticationFilter;

    @Override
    public ReporteOperacionesPorAcpAreaDtoResp listarOperacionesPorAcpPoaArea(Long pIdVersionPoa, String pIdAcp, String pIdPoaArea){
        ReporteOperacionesPorAcpAreaDtoResp vResultado = new ReporteOperacionesPorAcpAreaDtoResp();
        List<QueryOperacionesPorAcpPoaArea> vLista = vIConsultaGeneralPoaQuery.listarOperacionesPorAcpPoaArea(pIdVersionPoa, pIdPoaArea, pIdAcp);
        if(vLista.size()>0){
            Object[] vAcp=vLista.stream().map(x->x.getCodigoAcp()).toArray();
            Object[] vArea=vLista.stream().map(x->x.getArea()).toArray();
            Object[] vCantidadTareas=vLista.stream().map(x->x.getCantidadOperaciones()).toArray();
            String[] aux1=new String[vAcp.length];
            String[] aux2=new String[vAcp.length];
            Integer[] aux4=new Integer[vAcp.length];
            if(vAcp.length>0) {
                for (int i = 0; i < vAcp.length; i++) {
                    aux1[i] = (String) vAcp[i];
                    aux2[i] = (String) vArea[i];
                    aux4[i] = (Integer) vCantidadTareas[i];
                }
            }
            vResultado.setAcp(aux1);
            vResultado.setArea(aux2);
            vResultado.setCantidadOperaciones(aux4);
        }
        return vResultado;
    }
    @Override
    public ReporteOperacionesPorAcpDtoResp listarOperacionesPorAcp(Long pIdVersionPoa, String pIdAcp){
        ReporteOperacionesPorAcpDtoResp vResultado = new ReporteOperacionesPorAcpDtoResp();
        List<QueryOperacionesPorAcp> vLista = vIConsultaGeneralPoaQuery.listarOperacionesPorAcp(pIdVersionPoa, pIdAcp);
        if(vLista.size()>0){
            Object[] vAcp=vLista.stream().map(x->x.getCodigoAcp()).toArray();
            Object[] vCantidadOperaciones=vLista.stream().map(x->x.getCantidadOperaciones()).toArray();
            String[] aux1=new String[vAcp.length];
            String[] aux2=new String[vAcp.length];
            Integer[] aux4=new Integer[vAcp.length];
            if(vAcp.length>0) {
                for (int i = 0; i < vAcp.length; i++) {
                    aux1[i] = (String) vAcp[i];
                    aux4[i] = (Integer) vCantidadOperaciones[i];
                }
            }
            vResultado.setAcp(aux1);
            vResultado.setCantidadOperaciones(aux4);
        }
        return vResultado;
    }
    @Override
    public ReporteOperacionesPorAreaDtoResp listarOperacionesPorAreas(Long pIdVersionPoa, String pIdPoaArea){
        ReporteOperacionesPorAreaDtoResp vResultado = new ReporteOperacionesPorAreaDtoResp();
        List<QueryOperacionesPorArea> vLista = vIConsultaGeneralPoaQuery.listarOperacionesPorArea(pIdVersionPoa, pIdPoaArea);
        if(vLista.size()>0){
            Object[] vArea=vLista.stream().map(x->x.getArea()).toArray();
            Object[] vCantidadTareas=vLista.stream().map(x->x.getCantidadOperaciones()).toArray();
            String[] aux1=new String[vArea.length];
            Integer[] aux4=new Integer[vArea.length];
            if(vArea.length>0) {
                for (int i = 0; i < vArea.length; i++) {
                    aux1[i] = (String) vArea[i];
                    aux4[i] = (Integer) vCantidadTareas[i];
                }
            }
            vResultado.setArea(aux1);
            vResultado.setCantidadOperaciones(aux4);
        }
        return vResultado;
    }
    @Override
    public ReporteOperacionesPorAcpJsonDtoResp listarOperacionesPorAcpJson(Long pIdVersionPoa, String pIdAcp) {
        ReporteOperacionesPorAcpJsonDtoResp vResultado = new ReporteOperacionesPorAcpJsonDtoResp();
        List<QueryOperacionesPorAcp> vLista = vIConsultaGeneralPoaQuery.listarOperacionesPorAcp(pIdVersionPoa, pIdAcp);
        if(vLista.size()==0)
        {
           throw new ErrorValidacionException("No se encontraron resultados");
        }

         List<ListaOperacionesPorAcpDto>   listaFormateada =

                    vLista.stream()
                            .map(p -> new ListaOperacionesPorAcpDto() {{
                                        setAcp(p.getCodigoAcp());
                                        setTituloOperaciones("Cantidad de operaciones por ACP");
                                        setCantidad(p.getCantidadOperaciones());
                                    }}
                            )
                            .collect(Collectors.toList());

        QueryObtenerDatosCabeceraDto vQueryObtenerDatosCabeceraDto = this.obtenerDatosCabeceraDto(pIdVersionPoa);
        vResultado.setCabecera(vQueryObtenerDatosCabeceraDto);
        vResultado.setDatos(listaFormateada);
        return vResultado;
    }
    @Override
    public ReporteOperacionesPorAreaJsonDtoResp listarOperacionesPorAreaJson(Long pIdVersionPoa, String pIdPoaAreas) {
        ReporteOperacionesPorAreaJsonDtoResp vResultado = new ReporteOperacionesPorAreaJsonDtoResp();
        List<QueryOperacionesPorArea> vLista = vIConsultaGeneralPoaQuery.listarOperacionesPorArea(pIdVersionPoa, pIdPoaAreas);
        if(vLista.size()==0)
        {
            throw new ErrorValidacionException("No se encontraron resultados");
        }

        List<ListaOperacionesPorAreaDto>   listaFormateada =

                vLista.stream()
                        .map(p -> new ListaOperacionesPorAreaDto() {{
                                    setArea(p.getArea());
                                    setTituloOperaciones("Cantidad de operaciones por Área");
                                    setCantidad(p.getCantidadOperaciones());
                                }}
                        )
                        .collect(Collectors.toList());

        QueryObtenerDatosCabeceraDto vQueryObtenerDatosCabeceraDto = this.obtenerDatosCabeceraDto(pIdVersionPoa);
        vResultado.setCabecera(vQueryObtenerDatosCabeceraDto);
        vResultado.setDatos(listaFormateada);
        return vResultado;
    }
    private QueryObtenerDatosCabeceraDto obtenerDatosCabeceraDto(Long pIdVersion)
    {
        QueryObtenerDatosCabeceraDto vQueryObtenerDatosCabeceraDto=new QueryObtenerDatosCabeceraDto();
        vQueryObtenerDatosCabeceraDto.setCodigoReporte("RPT-NOVA-001");
        vQueryObtenerDatosCabeceraDto.setSistema("SIPOA");
        vQueryObtenerDatosCabeceraDto.setTitulo("CANTIDAD DE OPERACIONES POR ACCION A CORTO PLAZO");
        vQueryObtenerDatosCabeceraDto.setSubTitulo("PLAN OPERATIVO ANUAL 2024");
        vQueryObtenerDatosCabeceraDto.setVersionPoa("Version 1.0.0");
        vQueryObtenerDatosCabeceraDto.setNombreUsuario(jwtAuthenticationFilter.getJwtTokenContextAplicacion().getUsername().toString());
        vQueryObtenerDatosCabeceraDto.setDescripcion("En el siguiente reporte se visualizan los totales presupuestados para gasto corriente e inversión, de las areas GTIC y PRS");
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        vQueryObtenerDatosCabeceraDto.setFechaImpresion(dateFormat.format(date));
        return vQueryObtenerDatosCabeceraDto;
    }
}
