package gob.mefp.reportes.app.reportes.service;

import gob.ypfb.nova.app.parametricas.dto.response.UnidadEjecutoraDtoResp;
import gob.ypfb.nova.app.reportes.dto.query.*;
import gob.ypfb.nova.app.reportes.dto.response.*;
import gob.ypfb.nova.app.reportes.query.IConsultaGeneralPoaQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ReporteGeneralTareasPoaService implements IReporteGeneralTareasPoaService {
@Autowired
    IConsultaGeneralPoaQuery vIConsultaGeneralPoaQuery;
public ReporteTareaPorAcpAreaOperacionDtoResp listarTareasPorAcpPoaAreaOperacion(Long pIdVersionPoa, String pIdAcp, String pIdPoaArea){
    ReporteTareaPorAcpAreaOperacionDtoResp vResultado = new ReporteTareaPorAcpAreaOperacionDtoResp();
    List<QueryTareasPorAcpPoaAreaOperacion> vLista = vIConsultaGeneralPoaQuery.listarTareasPorAcpPoaAreaOperacion(pIdVersionPoa, pIdPoaArea, pIdAcp);
    if(vLista.size()>0){
        Object[] vAcp=vLista.stream().map(x->x.getCodigoAcp()).toArray();
        Object[] vArea=vLista.stream().map(x->x.getArea()).toArray();
        Object[] vCodigoOperacion=vLista.stream().map(x->x.getCodigoOperacion()).toArray();
        Object[] vCantidadTareas=vLista.stream().map(x->x.getCantidadTareas()).toArray();
        String[] aux1=new String[vAcp.length];
        String[] aux2=new String[vAcp.length];
        String[] aux3=new String[vAcp.length];
        Integer[] aux4=new Integer[vAcp.length];
        if(vAcp.length>0) {
            for (int i = 0; i < vAcp.length; i++) {
                aux1[i] = (String) vAcp[i];
                aux2[i] = (String) vArea[i];
                aux3[i] = (String) vCodigoOperacion[i];
                aux4[i] = (Integer) vCantidadTareas[i];
            }
        }
        vResultado.setAcp(aux1);
        vResultado.setArea(aux2);
        vResultado.setCodigoOperacion(aux3);
        vResultado.setCantidadTareas(aux4);
    }
    return vResultado;
}
    public ReporteTareaPorAcpAreaDtoResp listarTareasPorAcpPoaArea(Long pIdVersionPoa, String pIdAcp, String pIdPoaArea){
        ReporteTareaPorAcpAreaDtoResp vResultado = new ReporteTareaPorAcpAreaDtoResp();
        List<QueryTareasPoraAcpPoaArea> vLista = vIConsultaGeneralPoaQuery.listarTareasPorAcpPoaArea(pIdVersionPoa, pIdPoaArea, pIdAcp);
        if(vLista.size()>0){
            Object[] vAcp=vLista.stream().map(x->x.getCodigoAcp()).toArray();
            Object[] vArea=vLista.stream().map(x->x.getArea()).toArray();
            Object[] vCantidadTareas=vLista.stream().map(x->x.getCantidadTareas()).toArray();
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
            vResultado.setCantidadTareas(aux4);
        }
        return vResultado;
    }
    public ReporteTareaPorAcpDtoResp listarTareasPorAcp(Long pIdVersionPoa, String pIdAcp){
        ReporteTareaPorAcpDtoResp vResultado = new ReporteTareaPorAcpDtoResp();
        List<QueryTareasPorAcp> vLista = vIConsultaGeneralPoaQuery.listarTareasPorAcp(pIdVersionPoa, pIdAcp);
        if(vLista.size()>0){
            Object[] vAcp=vLista.stream().map(x->x.getCodigoAcp()).toArray();
            Object[] vCantidadTareas=vLista.stream().map(x->x.getCantidadTareas()).toArray();
            String[] aux1=new String[vAcp.length];
            String[] aux2=new String[vAcp.length];
            Integer[] aux4=new Integer[vAcp.length];
            if(vAcp.length>0) {
                for (int i = 0; i < vAcp.length; i++) {
                    aux1[i] = (String) vAcp[i];
                    aux4[i] = (Integer) vCantidadTareas[i];
                }
            }
            vResultado.setAcp(aux1);
            vResultado.setCantidadTareas(aux4);
        }
        return vResultado;
    }
    public ReporteTareaPorAreaDtoResp listarTareasPorAreas(Long pIdVersionPoa, String pIdPoaArea){
        ReporteTareaPorAreaDtoResp vResultado = new ReporteTareaPorAreaDtoResp();
        List<QueryTareasPorArea> vLista = vIConsultaGeneralPoaQuery.listarTareasPorArea(pIdVersionPoa, pIdPoaArea);
        if(vLista.size()>0){
            Object[] vArea=vLista.stream().map(x->x.getArea()).toArray();
            Object[] vCantidadTareas=vLista.stream().map(x->x.getCantidadTareas()).toArray();
            String[] aux1=new String[vArea.length];
            Integer[] aux4=new Integer[vArea.length];
            if(vArea.length>0) {
                for (int i = 0; i < vArea.length; i++) {
                    aux1[i] = (String) vArea[i];
                    aux4[i] = (Integer) vCantidadTareas[i];
                }
            }
            vResultado.setArea(aux1);
            vResultado.setCantidadTareas(aux4);
        }
        return vResultado;
    }
    @Override
    public List<ReporteCantidadTareasPorTipoDto> listarTareasPorTipo(Long pIdVersionPoa, String pIdPoaArea){
        List<ReporteCantidadTareasPorTipoDto> vResultado = null;
        List<QueryTareasPorAreaYTipo> vLista = vIConsultaGeneralPoaQuery.listarCantidadTareasPorArea(pIdVersionPoa, pIdPoaArea).stream().collect(Collectors.toList());
        if(vLista.size()>0){
            vResultado =
                    vLista.stream()
                            .map(p -> new ReporteCantidadTareasPorTipoDto() {{
                                        setArea(p.getArea());
                                        setActividad(p.getActividad());
                                        setProyecto(p.getProyecto());
                                    }}
                            )
                            .collect(Collectors.toList());


        }
        return vResultado;
    }
}
