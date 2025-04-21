package gob.mefp.reportes.app.reportes.service;

import gob.ypfb.nova.app.administracion.dto.query.QueryObtenerDatosCabeceraDto;
import gob.ypfb.nova.app.formulacion.dto.request.ReporteGeneralDtoReq;
import gob.ypfb.nova.app.parametricas.dto.response.PartidaPresupuestariaDtoResp;
import gob.ypfb.nova.app.reportes.dto.request.ReporteGeneralPorParametrosDtoReq;
import gob.ypfb.nova.app.reportes.dto.request.ReportePresupuestoPorAcpJsonDtoReq;
import gob.ypfb.nova.app.reportes.dto.request.ReportePresupuestoPorAreaDtoReq;
import gob.ypfb.nova.app.reportes.dto.request.ReportePresupuestoPorAreaJsonDtoReq;
import gob.ypfb.nova.app.reportes.dto.response.*;
import gob.ypfb.nova.config.ConstantesReportes;
import gob.ypfb.nova.security.jwt.JwtServiceContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ReporteGeneralPresupuestoPresupuestoService implements IReporteGeneralPresupuestoService {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    private JwtServiceContext jwtAuthenticationFilter;
    @Autowired
    private IDatosCabeceraService vIDatosCabeceraService;
    @Override
    public ReportePresupuestoPorAcpDtoResp obtenerPresupuestoPorAcpJson(ReportePresupuestoPorAcpJsonDtoReq vReportePresupuestoPorAcpJsonDtoReq) {
        ReportePresupuestoPorAcpDtoResp vResultado = new ReportePresupuestoPorAcpDtoResp();
        List<QueryReportePorParametrosDto> lista = null;
        Long vIdVersion = vReportePresupuestoPorAcpJsonDtoReq.getIdVersion();
        String vIdAcps = vReportePresupuestoPorAcpJsonDtoReq.getIdAcps();
        String vIdTipoGasto = vReportePresupuestoPorAcpJsonDtoReq.getIdTipoGasto();
        String IdTipoTarea = vReportePresupuestoPorAcpJsonDtoReq.getIdTipoTarea();
        String vQuery = "select res.\"accionCortoPlazo\"\n" +
                ", sum(res.\"gastoCorriente\") as gastoCorriente\n" +
                ", sum(res.inversion) as inversion \n" +
                "from  (select * from nova.fn_obtener_reporte_presupuesto_por_acp(" +
                ":i_id_version," +
                ":id_accion_corto_plazo," +
                ":id_tipo_gasto," +
                ":id_tipo_tarea"+
                "))as res\n" +
                "group by 1;"
                ;
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("i_id_version", vIdVersion);
        parameter.put("id_accion_corto_plazo", vIdAcps);
        parameter.put("id_tipo_gasto",vIdTipoGasto);
        parameter.put("id_tipo_tarea",IdTipoTarea);
        lista = jdbcTemplate.query(vQuery, parameter,
                new BeanPropertyRowMapper<>(QueryReportePorParametrosDto.class));
        List<ListaPresupuestoPorAcpDto> listaFormateada = null;

        if(lista.size()>0)
        {

            listaFormateada =

                    lista.stream()
                            .map(p -> new ListaPresupuestoPorAcpDto() {{
                                        setAcp(p.getAccionCortoPlazo());
                                        setTituloGastoCorriente("Gasto Corriente");
                                        setGastoCorriente(p.getGastoCorriente());
                                        setTituloInversion("Inversión");
                                        setInversion(p.getInversion());
                                    }}
                            )
                            .collect(Collectors.toList());

        String vAcps = listaFormateada.stream().map(p->p.getAcp()).collect(Collectors.toList()).toString();
        QueryObtenerDatosCabeceraDto vQueryObtenerDatosCabeceraDto = vIDatosCabeceraService.obtenerDatosCabeceraDto(
                "Versión 1.0.0"
                , ConstantesReportes.EnumCodigoReporte.RPT_NOVA_001.texto
                , "NOVA"
                , ConstantesReportes.EnumTituloReporte.PRESUPUESTO_POR_ACP.texto
                , ConstantesReportes.EnumSubTituloReporte.POA_2024.texto
                , "Reporte de presupuesto de las acciones a corto plazo: "+vAcps);
        vResultado.setCabecera(vQueryObtenerDatosCabeceraDto);
        vResultado.setDatos(listaFormateada);
        }
        return vResultado;
    }
    public ReportePresupuestoPorAreaDtoResp obtenerPresupuestoPorAreaJson(ReportePresupuestoPorAreaJsonDtoReq vReportePresupuestoPorAreaJsonDtoReq) {
        ReportePresupuestoPorAreaDtoResp vResultado = new ReportePresupuestoPorAreaDtoResp();
        List<QueryReportePorParametrosDto> lista = null;
        Long vIdVersion = vReportePresupuestoPorAreaJsonDtoReq.getIdVersion();
        String vIdPoaArea = vReportePresupuestoPorAreaJsonDtoReq.getIdPoaArea();
        String vIdTipoGasto = vReportePresupuestoPorAreaJsonDtoReq.getIdTipoGasto();
        String IdTipoTarea = vReportePresupuestoPorAreaJsonDtoReq.getIdTipoTarea();
        String vQuery = "select res.area\n" +
                ", sum(res.\"gastoCorriente\") as gastoCorriente\n" +
                ", sum(res.inversion) as inversion \n" +
                "from  (select * from nova.fn_obtener_reporte_presupuesto_por_area(" +
                ":i_id_version," +
                ":id_poa_area," +
                ":id_tipo_gasto," +
                ":id_tipo_tarea"+
                "))as res\n" +
                "group by 1;"
                ;
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("i_id_version", vIdVersion);
        parameter.put("id_poa_area", vIdPoaArea);
        parameter.put("id_tipo_gasto",vIdTipoGasto);
        parameter.put("id_tipo_tarea",IdTipoTarea);
        lista = jdbcTemplate.query(vQuery, parameter,
                new BeanPropertyRowMapper<>(QueryReportePorParametrosDto.class));
        List<ListaPresupuestoPorAreaDto> listaFormateada = null;

        if(lista.size()>0)
        {

            listaFormateada =

                    lista.stream()
                            .map(p -> new ListaPresupuestoPorAreaDto() {{
                                setArea(p.getArea());
                                setTituloGastoCorriente("Gasto Corriente");
                                setGastoCorriente(p.getGastoCorriente());
                                setTituloInversion("Inversión");
                                setInversion(p.getInversion());
                                }}
                            )
                            .collect(Collectors.toList());

        String vAreas = listaFormateada.stream().map(p->p.getArea()).collect(Collectors.toList()).toString();
        QueryObtenerDatosCabeceraDto vQueryObtenerDatosCabeceraDto = vIDatosCabeceraService.obtenerDatosCabeceraDto(
                  "Versión 1.0.0"
                , ConstantesReportes.EnumCodigoReporte.RPT_NOVA_002.texto
                , "NOVA"
                , ConstantesReportes.EnumTituloReporte.PRESUPUESTO_POR_AREA.texto
                , ConstantesReportes.EnumSubTituloReporte.POA_2024.texto
                , "Reporte de presupuesto de las áreas organizacionales: "+vAreas);
        vResultado.setCabecera(vQueryObtenerDatosCabeceraDto);
        vResultado.setDatos(listaFormateada);
        }
        return vResultado;
    }
    public QueryReportePorParametrosListDto obtenerPresupuestoPorArea(ReportePresupuestoPorAreaDtoReq vReportePresupuestoPorAreaDtoReq) {
        QueryReportePorParametrosListDto vResultado = new QueryReportePorParametrosListDto();
        List<QueryReportePorParametrosDto> lista = null;
        Long vIdVersion = vReportePresupuestoPorAreaDtoReq.getIdVersion();
        String vIdPoaArea = StringUtils.join(vReportePresupuestoPorAreaDtoReq.getIdPoaArea(), ',');
        String vIdTipoGasto = StringUtils.join(vReportePresupuestoPorAreaDtoReq.getIdTipoGasto(), ',');
        String IdTipoTarea = StringUtils.join(vReportePresupuestoPorAreaDtoReq.getIdTipoTarea(), ',');
        String vQuery = "select * from  nova.fn_obtener_reporte_presupuesto_por_area(" +
                ":i_id_version," +
                ":id_poa_area," +
                ":id_tipo_gasto," +
                ":id_tipo_tarea);"
                ;
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("i_id_version", vIdVersion);
        parameter.put("id_poa_area", vIdPoaArea);
        parameter.put("id_tipo_gasto",vIdTipoGasto);
        parameter.put("id_tipo_tarea",IdTipoTarea);
        lista = jdbcTemplate.query(vQuery, parameter,
                new BeanPropertyRowMapper<>(QueryReportePorParametrosDto.class));
        if(lista.size()>0){
            Object[] area=lista.stream().map(x->x.getArea()).toArray();
            Object[] tipoTarea=lista.stream().map(x->x.getTipoTarea()).toArray();
            Object[] gastoCorriente=lista.stream().map(x->x.getGastoCorriente()).toArray();
            Object[] inversion=lista.stream().map(x->x.getInversion()).toArray();
            String[] aux1=new String[area.length];
            String[] aux2=new String[area.length];
            Double[] aux3=new Double[area.length];
            Double[] aux4=new Double[area.length];
            Double[] aux5=new Double[area.length];
            if(area.length>0) {
                for (int i = 0; i < area.length; i++) {
                    aux2[i] = (String) area[i];
                    aux1[i] = (String) tipoTarea[i];
                    aux3[i] = (Double) gastoCorriente[i];
                    aux4[i] = (Double) inversion[i];
                    aux5[i] = aux3[i]+aux4[i];
                }


            }
            vResultado.setTipoTarea(aux1);
            vResultado.setArea(aux2);
            vResultado.setInversion(aux4);
            vResultado.setGastoCorriente(aux3);
            vResultado.setTotal(aux5);
        }
        return vResultado;
    }

    public QueryReportePorParametrosListDto obtenerPresupuestoPorAcp(ReporteGeneralPorParametrosDtoReq vReporteGeneralDtoReq) {
        QueryReportePorParametrosListDto vResultado = new QueryReportePorParametrosListDto();
        List<QueryReportePorParametrosDto> lista = null;
        Long vIdVersion = vReporteGeneralDtoReq.getIdVersion();
        String vIdAccionCortoPlazo = StringUtils.join(vReporteGeneralDtoReq.getIdAccionCortoPlazo(), ',');
        String vIdPoaArea = StringUtils.join(vReporteGeneralDtoReq.getIdPoaArea(), ',');
        String vIdTipoGasto = StringUtils.join(vReporteGeneralDtoReq.getIdTipoGasto(), ',');
        String IdTipoTarea = StringUtils.join(vReporteGeneralDtoReq.getIdTipoTarea(), ',');
        String vQuery = "select * from  nova.fn_obtener_reporte_de_presupuesto_por_acp(" +
                ":i_id_version," +
                ":id_accion_corto_plazo," +
                ":id_poa_area," +
                ":id_tipo_gasto," +
                ":id_tipo_tarea);"
                ;
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("i_id_version", vIdVersion);
        parameter.put("id_accion_corto_plazo", vIdAccionCortoPlazo);
        parameter.put("id_poa_area", vIdPoaArea);
        parameter.put("id_tipo_gasto",vIdTipoGasto);
        parameter.put("id_tipo_tarea",IdTipoTarea);
        lista = jdbcTemplate.query(vQuery, parameter,
                new BeanPropertyRowMapper<>(QueryReportePorParametrosDto.class));
        if(lista.size()>0){
            Object[] acp = lista.stream().map(x->x.getAccionCortoPlazo()).toArray();
            Object[] area=lista.stream().map(x->x.getArea()).toArray();
            Object[] tipoTarea=lista.stream().map(x->x.getTipoTarea()).toArray();
            Object[] gastoCorriente=lista.stream().map(x->x.getGastoCorriente()).toArray();
            Object[] inversion=lista.stream().map(x->x.getInversion()).toArray();
            Object[] total=lista.stream().map(x->x.getTotal()).toArray();
            String[] aux1=new String[acp.length];
            String[] aux2=new String[acp.length];
            String[] aux3=new String[acp.length];
            Double[] aux4=new Double[acp.length];
            Double[] aux5=new Double[acp.length];
            Double[] aux6=new Double[acp.length];
            if(acp.length>0) {
                for (int i = 0; i < acp.length; i++) {
                    aux1[i] = (String) acp[i];
                    aux2[i] = (String) area[i];
                    aux3[i] = (String) tipoTarea[i];
                    aux4[i] = (Double) gastoCorriente[i];
                    aux5[i] = (Double) inversion[i];
                    aux6[i] = (Double) total[i];
                }
            }
            vResultado.setAcp(aux1);
            vResultado.setTipoTarea(aux3);
            vResultado.setArea(aux2);
            vResultado.setInversion(aux5);
            vResultado.setGastoCorriente(aux4);
            vResultado.setTotal(aux6);
        }
        return vResultado;
    }

    public QueryReportePorParametrosListDto obtenerPresupuestoGeneralPorParametros(ReporteGeneralDtoReq vReporteGeneralDtoReq) {
        QueryReportePorParametrosListDto vResultado = new QueryReportePorParametrosListDto();
        List<QueryReportePorParametrosDto> lista = null;
        Long IdAccionCortoPlazo = vReporteGeneralDtoReq.getIdAccionCortoPlazo();
        Long IdPoaArea = vReporteGeneralDtoReq.getIdPoaArea();
        Long IdTipoGasto = vReporteGeneralDtoReq.getIdTipoGasto();
        Long IdTipoTarea = vReporteGeneralDtoReq.getIdTipoTarea();
        String GrupoPartida = vReporteGeneralDtoReq.getGrupoPartida();
        Long IdPartidaPresupuestaria = vReporteGeneralDtoReq.getIdPartidaPresupuestaria();
        String vQuery = "select * from  nova.fn_obtener_reporte_por_parametros(" +
                ":id_accion_corto_plazo," +
                ":id_poa_area," +
                ":id_tipo_gasto," +
                ":id_tipo_tarea," +
                ":grupo_partida," +
                ":id_partida_presupuestaria);"
                ;
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("id_accion_corto_plazo", null);
        parameter.put("id_poa_area", null);
        parameter.put("id_tipo_gasto",null);
        parameter.put("id_tipo_tarea",null);
        parameter.put("grupo_partida",null);
        parameter.put("id_partida_presupuestaria",null);
        lista = jdbcTemplate.query(vQuery, parameter,
                new BeanPropertyRowMapper<>(QueryReportePorParametrosDto.class));
        if(lista.size()>0){
            Object[] acp = lista.stream().map(x->x.getAccionCortoPlazo()).toArray();
            Object[] area=lista.stream().map(x->x.getArea()).toArray();
            Object[] tipoTarea=lista.stream().map(x->x.getTipoTarea()).toArray();
            Object[] gastoCorriente=lista.stream().map(x->x.getGastoCorriente()).toArray();
            Object[] inversion=lista.stream().map(x->x.getInversion()).toArray();
            Object[] total=lista.stream().map(x->x.getTotal()).toArray();
            String[] aux1=new String[acp.length];
            String[] aux2=new String[acp.length];
            String[] aux3=new String[acp.length];
            Double[] aux4=new Double[acp.length];
            Double[] aux5=new Double[acp.length];
            Double[] aux6=new Double[acp.length];
            if(acp.length>0) {
                for (int i = 0; i < acp.length; i++) {
                    aux1[i] = (String) acp[i];
                    aux2[i] = (String) area[i];
                    aux3[i] = (String) tipoTarea[i];
                    aux4[i] = (Double) gastoCorriente[i];
                    aux5[i] = (Double) inversion[i];
                    aux6[i] = (Double) total[i];
                }
            }
            vResultado.setAcp(aux1);
            vResultado.setTipoTarea(aux2);
            vResultado.setArea(aux3);
            vResultado.setInversion(aux5);
            vResultado.setGastoCorriente(aux4);
            vResultado.setTotal(aux6);
        }
        return vResultado;
    }
}
