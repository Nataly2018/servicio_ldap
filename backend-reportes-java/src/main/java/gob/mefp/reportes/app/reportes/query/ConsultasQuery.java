package gob.mefp.reportes.app.reportes.query;

import gob.ypfb.nova.app.parametricas.domain.FteFinOrgFin;
import gob.ypfb.nova.app.parametricas.repository.IFteFinOrgFinRepository;
import gob.ypfb.nova.app.parametricas.repository.IUnidadEjecutoraRepository;
import gob.ypfb.nova.app.reportes.dto.request.ParametrosGenericos.*;

import gob.ypfb.nova.app.reportes.dto.query.*;
import gob.ypfb.nova.app.reportes.dto.response.ConsultaConsultoriaDtoResp;
import gob.ypfb.nova.app.reportes.dto.response.ConsultaOperacionesDtoResp;
import gob.ypfb.nova.app.reportes.dto.response.ConsultaPresupuestoDtoResp;
import gob.ypfb.nova.app.reportes.dto.response.ConsultaTareasDtoResp;
import gob.ypfb.nova.config.ConstantesPoa;
import gob.ypfb.nova.config.ConstantesReporte;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class ConsultasQuery implements IConsultasQuery {
    @Autowired
    IFteFinOrgFinRepository vIFteFinOrgFinRepository;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private IUnidadEjecutoraRepository vIUnidadEjecutoraRepository;

    @Override
    public List<QueryAreaPorPoa> listaUnidadesPorPoa(Long idVersionPoa) {
        List<QueryAreaPorPoa> vResultado = null;

        String vQuery = "select \n" +
                "pa.id as id_area,\n" +
                "tpa.id_poa ,\n" +
                "tpa.id  as id_poa_area,\n" +
                "tpa.codigo_area ,\n" +
                "pa.sigla ,\n" +
                "pa.descripcion \n" +
                "from nova.trs_poa_area tpa \n" +
                "inner join nova.par_area pa  \n" +
                "on tpa.id_area = pa.id \n" +
                "where \n" +
                "exists (\n" +
                "select 1 from nova.fn_parametricas_activas_version(:idVersionPoa,:nombreTablaTpa)as codigo \n" +
                "where codigo = tpa.id\n" +
                ")\n" +
                "and \n" +
                "exists (\n" +
                "select 1 from nova.fn_parametricas_activas_version(:idVersionPoa,:nombreTablaPa)as codigo \n" +
                "where codigo = pa.id\n" +
                ")\n" +
                "order by pa.sigla " ;
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("idVersionPoa", idVersionPoa);
        parameter.put("nombreTablaTpa", ConstantesReporte.EnumTablasParametricasVersionado.TRS_POA_AREA.nombre);
        parameter.put("nombreTablaPa", ConstantesReporte.EnumTablasParametricasVersionado.PAR_AREA.nombre);
        vResultado = jdbcTemplate.query(vQuery, parameter, new BeanPropertyRowMapper<>(QueryAreaPorPoa.class));
        return vResultado;
    }

    @Override
    public List<QueryOperacionesPorPoa> listaOperacionesPorPoa(Long idVersionPoa) {
        List<QueryOperacionesPorPoa> vResultado = null;

        String vQuery = "select \n" +
                "distinct \n" +
                "vptg.area_codigo as codigo_area,\n" +
                "vptg.oper_codigo_accion_corto_plazo as codigo_accion_corto_plazo,\n" +
                "vptg.oper_id_operacion as id_operacion ,\n" +
                "vptg.oper_codigo_operacion as codigo_operacion,\n" +
                "vptg.oper_descripcion_operacion as descripcion ,\n" +
                "vptg.oper_orden_operacion as orden \n" +
                "from \n" +
                "nova.fn_traza_activas_version(:idVersionPoa) vptg  \n" +
                "order by vptg.oper_codigo_accion_corto_plazo ,vptg.area_codigo ,vptg.oper_orden_operacion  " ;
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("idVersionPoa", idVersionPoa);
        vResultado = jdbcTemplate.query(vQuery, parameter, new BeanPropertyRowMapper<>(QueryOperacionesPorPoa.class));
        return vResultado;
    }

    @Override
    public List<QueryTareasPorPoa> listaTareasPorPoa(Long idVersionPoa) {
        List<QueryTareasPorPoa> vResultado = null;

        String vQuery = "select \n" +
                "distinct \n" +
                "vptg.oper_codigo_accion_corto_plazo as codigo,\n" +
                "vptg.area_codigo as codigo_area,\n" +
                "vptg.oper_codigo_operacion as codigo_operacion,\n" +
                "vptg.tar_id_tarea as id_tarea,\n" +
                "vptg.tar_codigo_tarea as codigo_tarea,\n" +
                "vptg.oper_descripcion_operacion as descripcion_operacion ,\n" +
                "vptg.oper_orden_operacion as orden_operacion ,\n" +
                "vptg.tar_descripcion_tarea as descripcion_tarea,\n" +
                "vptg.tar_orden_tarea as orden_tarea \n" +
                "from \n" +
                "nova.fn_traza_activas_version(:idVersionPoa) vptg   \n" +
                "order by vptg.oper_codigo_accion_corto_plazo ,vptg.area_codigo ,vptg.oper_orden_operacion ,vptg.tar_orden_tarea   " ;
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("idVersionPoa", idVersionPoa);
        vResultado = jdbcTemplate.query(vQuery, parameter, new BeanPropertyRowMapper<>(QueryTareasPorPoa.class));
        return vResultado;
    }
    //QueryTipoTareasPorPoa
    @Override
    public List<QueryTipoTareasPorPoa> listaTipoTareasPorVersionPoa(Long idVersionPoa) {
        List<QueryTipoTareasPorPoa> vResultado = null;

        String vQuery = "select \n" +
                "pd.id ,\n" +
                "pd.valor as descripcion \n" +
                "from \n" +
                "nova.par_dominio pd\n" +
                "where pd.grupo_dominio =:grupo\n" +
                "and exists (\n" +
                "select 1 from nova.fn_parametricas_activas_version(:idVersionPoa,:nombreTabla)as codigo \n" +
                "where codigo = pd.id\n" +
                ") order by id   " ;
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("idVersionPoa", idVersionPoa);
        parameter.put("grupo", ConstantesPoa.EnumGrupoDominio.TIPO_TAREA.grupo);
        parameter.put("nombreTabla", ConstantesReporte.EnumTablasParametricasVersionado.PAR_DOMINIO.nombre);
        vResultado = jdbcTemplate.query(vQuery, parameter, new BeanPropertyRowMapper<>(QueryTipoTareasPorPoa.class));
        return vResultado;
    }

    @Override
    public List<QueryTipoPresupuestoPorPoa> listaTipoPresupuestoPorVersionPoa(Long idVersionPoa) {
        List<QueryTipoPresupuestoPorPoa> vResultado = null;

        String vQuery = "select \n" +
                "pd.id ,\n" +
                "pd.valor as descripcion \n" +
                "from \n" +
                "nova.par_dominio pd\n" +
                "where pd.grupo_dominio =:grupo\n" +
                "and exists (\n" +
                "select 1 from nova.fn_parametricas_activas_version(:idVersionPoa,:nombreTabla)as codigo \n" +
                "where codigo = pd.id\n" +
                ") order by id   " ;
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("idVersionPoa", idVersionPoa);
        parameter.put("grupo", ConstantesPoa.EnumGrupoDominio.TIPO_PRESUPUESTO.grupo);
        parameter.put("nombreTabla", ConstantesReporte.EnumTablasParametricasVersionado.PAR_DOMINIO.nombre);
        vResultado = jdbcTemplate.query(vQuery, parameter, new BeanPropertyRowMapper<>(QueryTipoPresupuestoPorPoa.class));
        return vResultado;
    }


    //QueryAreasResponsablesPorPoa
    @Override
    public List<QueryAreasResponsablesPorPoa> listaAreasResponsablesPorPoa(Long idVersionPoa) {
        List<QueryAreasResponsablesPorPoa> vResultado = null;

        String vQuery = " select\n" +
                "    distinct\n" +
                "    (elem->>'id_area')::bigint AS id,\n" +
                "    elem->>'sigla' AS sigla,\n" +
                "    elem->>'sigla_gthc' AS sigla_gthc,\n" +
                "    elem->>'descripcion' as descripcion\n" +
                "FROM\n" +
                "    nova.fn_traza_activas_version(:idVersionPoa) vptg,\n" +
                "    jsonb_array_elements(vptg.tar_area_responsable) AS elem \n" +
                "order by sigla " ;
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("idVersionPoa", idVersionPoa);
        vResultado = jdbcTemplate.query(vQuery, parameter, new BeanPropertyRowMapper<>(QueryAreasResponsablesPorPoa.class));
        return vResultado;
    }

    @Override
    public List<QueryCategoriaProgramaticaPorPoa> listaCategoriaProgramaticaPorPoa(Long idVersionPoa) {
        List<QueryCategoriaProgramaticaPorPoa> vResultado = null;

        String vQuery = "   select \n" +
                "pcp.id ,\n" +
                "pcp.programa,\n" +
                "pcp.proyecto ,\n" +
                "pcp.actividad\n" +
                "from nova.par_categoria_programatica pcp \n" +
                "where exists (\n" +
                "select 1 from nova.fn_parametricas_activas_version(:idVersionPoa,:nombreTabla)as codigo \n" +
                "where codigo = pcp.id\n" +
                ") order by id " ;
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("idVersionPoa", idVersionPoa);
        parameter.put("nombreTabla", ConstantesReporte.EnumTablasParametricasVersionado.PAR_CATEGORIA_PROGRAMATICA.nombre);
        vResultado = jdbcTemplate.query(vQuery, parameter, new BeanPropertyRowMapper<>(QueryCategoriaProgramaticaPorPoa.class));
        return vResultado;
    }
    @Override
    public List<QueryDireccionAdministrativaPorPoa> listaDireccionAdminPorPoa(Long idVersionPoa) {
        List<QueryDireccionAdministrativaPorPoa> vResultado = null;

        String vQuery = "  select \n" +
                " pda.id ,\n" +
                " pda.codigo ,\n" +
                " pda.nombre \n" +
                " from \n" +
                " nova.par_direccion_administrativa pda \n" +
                " where exists (\n" +
                "select 1 from nova.fn_parametricas_activas_version(:idVersionPoa,:nombreTabla)as codigo \n" +
                "where codigo = pda.id\n" +
                ") order by id " ;
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("idVersionPoa", idVersionPoa);
        parameter.put("nombreTabla", ConstantesReporte.EnumTablasParametricasVersionado.PAR_DIRECCION_ADMINISTRATIVA.nombre);
        vResultado = jdbcTemplate.query(vQuery, parameter, new BeanPropertyRowMapper<>(QueryDireccionAdministrativaPorPoa.class));
        return vResultado;
    }
    @Override
    public List<QueryPartidaPresupuestariaPorPoa> listaPartidadPresupuestariaPorPoa(Long idVersionPoa) {
        List<QueryPartidaPresupuestariaPorPoa> vResultado = null;

        String vQuery = "  select \n" +
                " ppp.id ,\n" +
                " ppp.codigo ,\n" +
                " ppp.descripcion \n" +
                " from nova.par_partida_presupuestaria ppp \n" +
                " where \n" +
                " exists (\n" +
                "select 1 from nova.fn_parametricas_activas_version(:idVersionPoa,:nombreTabla) as codigo \n" +
                "where codigo = ppp.id\n" +
                ")\n" +
                "  order by codigo::integer  " ;
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("idVersionPoa", idVersionPoa);
        parameter.put("nombreTabla", ConstantesReporte.EnumTablasParametricasVersionado.PAR_PARTIDA_PRESUPUESTARIA.nombre);
        vResultado = jdbcTemplate.query(vQuery, parameter, new BeanPropertyRowMapper<>(QueryPartidaPresupuestariaPorPoa.class));
        return vResultado;
    }
    @Override
    public List<QueryUnidadEjecutoraPorPoa> listaUnidadEjcutoraPorPoa(Long idVersionPoa) {
        List<QueryUnidadEjecutoraPorPoa> vResultado = null;

        String vQuery = "  select\n" +
                "  distinct \n" +
                "  CAST(pue.codigo AS BIGINT) as id ,\n" +
                " pue.codigo ,\n" +
                " pue.nombre \n" +
                " from nova.par_unidad_ejecutora pue  \n" +
                " where \n" +
                " exists (\n" +
                "select 1 from nova.fn_parametricas_activas_version(:idVersionPoa,:nombreTabla)as codigo \n" +
                "where codigo = pue.id\n" +
                ")\n" +
                " order by CAST(pue.codigo AS BIGINT)  " ;
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("idVersionPoa", idVersionPoa);
        parameter.put("nombreTabla", ConstantesReporte.EnumTablasParametricasVersionado.PAR_UNIDAD_EJECUTORA.nombre);
        vResultado = jdbcTemplate.query(vQuery, parameter, new BeanPropertyRowMapper<>(QueryUnidadEjecutoraPorPoa.class));
        return vResultado;
    }


    @Override
    public List<QueryFuenteOrgFinanPorPoa> listaFuenteOrgFinanPorPoa(Long idVersionPoa) {
        List<QueryFuenteOrgFinanPorPoa> vResultado = null;

        String vQuery = "   select fff.id as \"id\",\n" +
                "       ff.id as \"idFuenteFinanciamiento\",\n" +
                "       o.id as \"idOrganismoFinanciador\",\n" +
                "       ff.codigo||'-'||o.codigo as \"codigo\"\n" +
                "from nova.par_fte_fin_org_fin fff \n" +
                "inner join nova.par_fuente_financiamiento ff\n" +
                "on fff.id_fuente_financiamiento = ff.id \n" +
                "inner join nova.par_organismo_financiador o\n" +
                "on fff.id_org_financiador = o.id \n" +
                "where \n" +
                "exists (\n" +
                "select 1 from nova.fn_parametricas_activas_version(:idVersionPoa,:nombreTablaFFF)as codigo \n" +
                "where codigo = fff.id\n" +
                ")\n" +
                "and \n" +
                "exists (\n" +
                "select 1 from nova.fn_parametricas_activas_version(:idVersionPoa,:nombreTablaFF)as codigo \n" +
                "where codigo = ff.id\n" +
                ")\n" +
                "and \n" +
                "exists (\n" +
                "select 1 from nova.fn_parametricas_activas_version(:idVersionPoa,:nombreTablaO)as codigo \n" +
                "where codigo = o.id\n" +
                ")\n" +
                "order by fff.id " ;
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("idVersionPoa", idVersionPoa);
        parameter.put("nombreTablaFFF", ConstantesReporte.EnumTablasParametricasVersionado.PAR_FTE_FIN_ORG_FIN.nombre);
        parameter.put("nombreTablaFF", ConstantesReporte.EnumTablasParametricasVersionado.PAR_FUENTE_FINANCIAMIENTO.nombre);
        parameter.put("nombreTablaO", ConstantesReporte.EnumTablasParametricasVersionado.PAR_ORGANISMO_FINANCIADOR.nombre);
        vResultado = jdbcTemplate.query(vQuery, parameter, new BeanPropertyRowMapper<>(QueryFuenteOrgFinanPorPoa.class));
        return vResultado;
    }

    @Override
    public List<QueryVersionPoa> listaVersionesPoaPorPoa(Long idPoa) {
        List<QueryVersionPoa> vResultado = null;

        String vQuery = " SELECT id, id_poa, \"version\", codigo_version, descripcion, fecha_inicial, fecha_final\n" +
                "FROM nova.trs_poa_version\n" +
                "where id_poa = :idPoa and eliminado = false " +
                " order by id, \"version\" " ;
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("idPoa", idPoa);
        vResultado = jdbcTemplate.query(vQuery, parameter, new BeanPropertyRowMapper<>(QueryVersionPoa.class));
        return vResultado;
    }
    @Override
    public List<QueryVersionPoa> listaVersionesPoaUltimoMigrado() {
        List<QueryVersionPoa> vResultado = null;

        String vQuery = "  SELECT id, id_poa, \"version\", codigo_version, descripcion, fecha_inicial, fecha_final\n" +
                "FROM nova.trs_poa_version\n" +
                "where  eliminado = false and fecha_final is not null\n" +
                " order by fecha_final desc limit 1 " ;
        Map<String, Object> parameter = new HashMap<>();

        vResultado = jdbcTemplate.query(vQuery, parameter, new BeanPropertyRowMapper<>(QueryVersionPoa.class));
        return vResultado;
    }


    @Override
    public List<QueryResponsableConsulta> listaRolConsulta(String nombreUsuario, String codigo) {
        List<QueryResponsableConsulta> vResultado = null;

        String vQuery = " select\n" +
                "\ttpa.id_area ,\n" +
                "\ttu.nombre_usuario ,\t\n" +
                "\ttr.id_poa_area \n" +
                "from\n" +
                "\tnova.trs_responsable tr \n" +
                "inner join \n" +
                "   nova.trs_poa_area tpa on tr.id_poa_area = tpa.id \n" +
                "inner join \n" +
                "   nova.trs_usuario tu on tu.id = tr.id_usuario and tu.nombre_usuario =:nombreUsuario \n" +
                "inner join \n" +
                "   nova.par_dominio pd on pd.grupo_dominio ='ROL_GENERAL' and pd.codigo =:codigo \n" +
                "   and tr.id_rol = pd.id     \n" +
                "; " ;
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("nombreUsuario", nombreUsuario);
        parameter.put("codigo", codigo);
        vResultado = jdbcTemplate.query(vQuery, parameter, new BeanPropertyRowMapper<>(QueryResponsableConsulta.class));
        return vResultado;
    }

    @Override
    public List<QueryAccionCortoPlazoPorPoa> listaAccionCortoPlazoPorVersion(Long idVersionPoa) {
        List<QueryAccionCortoPlazoPorPoa> vResultado = null;

        String vQuery = " select pacp.id ,\n" +
                "pacp.codigo ,\n" +
                "pacp.descripcion \n" +
                "from nova.par_accion_corto_plazo pacp \n" +
                "where exists (\n" +
                "select 1 from nova.fn_parametricas_activas_version(:idVersionPoa,:nombreTabla)as codigo \n" +
                "where codigo = pacp.id\n" +
                ") order by pacp.id " ;
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("idVersionPoa", idVersionPoa);
        parameter.put("nombreTabla", ConstantesReporte.EnumTablasParametricasVersionado.PAR_ACCION_CORTO_PLAZO.nombre);
        vResultado = jdbcTemplate.query(vQuery, parameter, new BeanPropertyRowMapper<>(QueryAccionCortoPlazoPorPoa.class));
        return vResultado;
    }

    @Override
    public List<QueryAreasResponsablesPorPoa> listaAreasValidadoreasConsultoriaPorPoa(Long idVersionPoa) {
        List<QueryAreasResponsablesPorPoa> vResultado = null;

        String vQuery = " select\n" +
                "    distinct\n" +
                "    (elem->>'id_area')::bigint AS id,\n" +
                "    elem->>'sigla' AS sigla,\n" +
                "    elem->>'sigla_gthc' AS sigla_gthc,\n" +
                "    elem->>'descripcion' as descripcion\n" +
                "FROM\n" +
                "    nova.fn_traza_activas_version(:idVersionPoa) vptg,\n" +
                "    jsonb_array_elements(vptg.cons_areas_validadoras_consultoria) AS elem \n" +
                "order by sigla_gthc " ;
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("idVersionPoa", idVersionPoa);
        vResultado = jdbcTemplate.query(vQuery, parameter, new BeanPropertyRowMapper<>(QueryAreasResponsablesPorPoa.class));
        return vResultado;
    }





    @Override
    public ConsultaOperacionesDtoResp consultaGeneralOperacion(ConsultaTrazaGeneralOperacionesReq consultaTrazaGeneralOperacionesReq){
        FabricConsulta fabricConsulta = new FabricConsulta();
        fabricConsulta.builder()
                .inicializarOperaciones(consultaTrazaGeneralOperacionesReq.getIdVersionPoa())
                .adicionarParametroIgual("oper_id_accion_corto_plazo",consultaTrazaGeneralOperacionesReq.getOperIdAccionCortoPlazo())
                .adicionarParametroIn("area_id_area",consultaTrazaGeneralOperacionesReq.getAreas().stream().map(p->p.getAreaIdArea()).collect(Collectors.toList()))
                .adicionarParametroIn("oper_id_operacion",consultaTrazaGeneralOperacionesReq.getOperaciones().stream().map(p->p.getOperIdOperacion()).collect(Collectors.toList()))
                .adicionarPaginacion(consultaTrazaGeneralOperacionesReq.getResultadosPorPagina(),consultaTrazaGeneralOperacionesReq.getPagina())
        ;
     //   System.out.println("la conusulta es :"+fabricConsulta.obtenerConsultaSql());
     //   System.out.println("la conusulta totale es :"+fabricConsulta.obtenerConsultaTotalesSql());
        List<ConsultaOperacionesConTotales> detalle =
                jdbcTemplate.query(fabricConsulta.obtenerConsultaSql(), fabricConsulta.obtenerMapParametros(), new BeanPropertyRowMapper<>(ConsultaOperacionesConTotales.class));

        ConsultaOperacionTotales total = new ConsultaOperacionTotales();
        if(consultaTrazaGeneralOperacionesReq.getPagina()==1){
            total =
                    jdbcTemplate.query(fabricConsulta.obtenerConsultaTotalesSql(),
                            fabricConsulta.obtenerMapParametros(),
                            new BeanPropertyRowMapper<>(ConsultaOperacionTotales.class))
                            .get(0);

        }

        ConsultaOperacionesDtoResp vConsultaOperacionesDtoResp = new ConsultaOperacionesDtoResp();
        vConsultaOperacionesDtoResp.setDetalle(detalle);
        vConsultaOperacionesDtoResp.setTotal(total);
        vConsultaOperacionesDtoResp.setRequest(consultaTrazaGeneralOperacionesReq);
        return vConsultaOperacionesDtoResp;
    }


    @Override
    public ConsultaTareasDtoResp consultaGeneralTarea(ConsultaTrazaGeneralTareasReq consultaTrazaGeneralTareasReq){
        FabricConsulta fabricConsulta = new FabricConsulta();
        fabricConsulta.builder()
                .inicializarTareas(consultaTrazaGeneralTareasReq.getIdVersionPoa())
                .adicionarParametroIgual("oper_id_accion_corto_plazo",consultaTrazaGeneralTareasReq.getOperIdAccionCortoPlazo())
                .adicionarParametroIgual("tar_id_tipo_tarea",consultaTrazaGeneralTareasReq.getTarIdTipoTarea())
                .adicionarParametroIn("area_id_area",consultaTrazaGeneralTareasReq.getAreas().stream().map(p->p.getAreaIdArea()).collect(Collectors.toList()))
                .adicionarParametroIn("oper_id_operacion",consultaTrazaGeneralTareasReq.getOperaciones().stream().map(p->p.getOperIdOperacion()).collect(Collectors.toList()))
                .adicionarParametroIn("tar_id_tarea",consultaTrazaGeneralTareasReq.getTareas().stream().map(p->p.getTarIdTarea()).collect(Collectors.toList()))
                .adicionarParametroInEnJsonB ("tar_area_responsable","id_area",consultaTrazaGeneralTareasReq.getAreasResponsables().stream().map(p->p.getAreaIdArea()).collect(Collectors.toList()))
                .adicionarPaginacion(consultaTrazaGeneralTareasReq.getResultadosPorPagina(),consultaTrazaGeneralTareasReq.getPagina())
        ;
     //   System.out.println("la conusulta es :"+fabricConsulta.obtenerConsultaSql());
     //   System.out.println("la conusulta totale es :"+fabricConsulta.obtenerConsultaTotalesSql());
        List<ConsultaTareasConTotales> detalle =
                jdbcTemplate.query(fabricConsulta.obtenerConsultaSql(), fabricConsulta.obtenerMapParametros(), new BeanPropertyRowMapper<>(ConsultaTareasConTotales.class));

        ConsultaTareaTotales total = new ConsultaTareaTotales();
        if(consultaTrazaGeneralTareasReq.getPagina()==1){
            total =
                    jdbcTemplate.query(fabricConsulta.obtenerConsultaTotalesSql(),
                                    fabricConsulta.obtenerMapParametros(),
                                    new BeanPropertyRowMapper<>(ConsultaTareaTotales.class))
                            .get(0);

        }

        ConsultaTareasDtoResp vConsultaTareasDtoResp = new ConsultaTareasDtoResp();
        vConsultaTareasDtoResp.setDetalle(detalle);
        vConsultaTareasDtoResp.setTotal(total);
        vConsultaTareasDtoResp.setRequest(consultaTrazaGeneralTareasReq);
        return vConsultaTareasDtoResp;
    }


    @Override
    public ConsultaPresupuestoDtoResp consultaGeneralPresupuesto(ConsultaTrazaGeneralPresupuestoReq consultaTrazaGeneralPresupuestoReq){
        FabricConsulta fabricConsulta = new FabricConsulta();
        Long idFuentes = null;
        Long idOrganismos = null;

        if(consultaTrazaGeneralPresupuestoReq.getPresIdFuenteOrganismoFinanciador()!=null) {
            FteFinOrgFin registro =
            vIFteFinOrgFinRepository.getById(consultaTrazaGeneralPresupuestoReq.getPresIdFuenteOrganismoFinanciador());
            idFuentes = registro.getId();
            idOrganismos = registro.getId();
        }

        fabricConsulta.builder()
                .inicializarPresupuesto(consultaTrazaGeneralPresupuestoReq.getIdVersionPoa())
                .adicionarParametroIgual("oper_id_accion_corto_plazo",consultaTrazaGeneralPresupuestoReq.getOperIdAccionCortoPlazo())
                .adicionarParametroIgual("pres_id_direccion_admin",consultaTrazaGeneralPresupuestoReq.getDireccionAdminId())
                .adicionarParametroIgual("tar_id_tipo_tarea",consultaTrazaGeneralPresupuestoReq.getTarIdTipoTarea())
                .adicionarParametroLike("pres_detalle_gasto",consultaTrazaGeneralPresupuestoReq.getPresDetalleGasto())
                .adicionarParametroIn("area_id_area",consultaTrazaGeneralPresupuestoReq.getAreas().stream().map(p->p.getAreaIdArea()).collect(Collectors.toList()))
                .adicionarParametroIn("oper_id_operacion",consultaTrazaGeneralPresupuestoReq.getOperaciones().stream().map(p->p.getOperIdOperacion()).collect(Collectors.toList()))
                .adicionarParametroIn("tar_id_tarea",consultaTrazaGeneralPresupuestoReq.getTareas().stream().map(p->p.getTarIdTarea()).collect(Collectors.toList()))
                .adicionarParametroInEnJsonB ("tar_area_responsable","id_area",consultaTrazaGeneralPresupuestoReq.getAreasResponsables().stream().map(p->p.getAreaIdArea()).collect(Collectors.toList()))
                .adicionarParametroIgual("pres_id_tipo",consultaTrazaGeneralPresupuestoReq.getPresIdTipo())
                .adicionarParametroIn("pres_id_cat_programatica",consultaTrazaGeneralPresupuestoReq.getCategorias().stream().map(p->p.getPresIdCatProgramatica()).collect(Collectors.toList()))
                .adicionarParametroIn("pres_id_partida_presupuestaria",consultaTrazaGeneralPresupuestoReq.getPartidas().stream().map(p->p.getPresIdPartidaPresupuestaria()).collect(Collectors.toList()))
                .adicionarParametroIgual("pres_id_fuente",idFuentes)
                .adicionarParametroIgual("pres_id_organismo_financiador",idOrganismos)
                .adicionarParametroStringIn("pres_codigo_unidad_ejecutora",consultaTrazaGeneralPresupuestoReq.getUnidadEjecutora().stream().map(p->p.getPresCodigoUnidadEjecutora()).collect(Collectors.toList()))
                .adicionarPaginacion(consultaTrazaGeneralPresupuestoReq.getResultadosPorPagina(),consultaTrazaGeneralPresupuestoReq.getPagina())
        ;

        List<ConsultaPresupuestoConTotales> detalle =
                jdbcTemplate.query(fabricConsulta.obtenerConsultaSql(), fabricConsulta.obtenerMapParametros(), new BeanPropertyRowMapper<>(ConsultaPresupuestoConTotales.class));

        ConsultaPresupuestoTotales total = new ConsultaPresupuestoTotales();
        if(consultaTrazaGeneralPresupuestoReq.getPagina()==1){
            total =
                    jdbcTemplate.query(fabricConsulta.obtenerConsultaTotalesSql(),
                                    fabricConsulta.obtenerMapParametros(),
                                    new BeanPropertyRowMapper<>(ConsultaPresupuestoTotales.class))
                            .get(0);

        }

        ConsultaPresupuestoDtoResp vConsultaTareasDtoResp = new ConsultaPresupuestoDtoResp();
        vConsultaTareasDtoResp.setDetalle(detalle);
        vConsultaTareasDtoResp.setTotal(total);
        vConsultaTareasDtoResp.setRequest(consultaTrazaGeneralPresupuestoReq);
        return vConsultaTareasDtoResp;
    }

    @Override
    public ConsultaConsultoriaDtoResp consultaGeneralConsultoria(
            ConsultaTrazaGeneralConsultoriaReq consultaTrazaGeneralConsultoriaReq){
        FabricConsulta fabricConsulta = new FabricConsulta();
        Long idFuentes = null;
        Long idOrganismos = null;

        if(consultaTrazaGeneralConsultoriaReq.getPresIdFuenteOrganismoFinanciador()!=null) {
            FteFinOrgFin registro =
                    vIFteFinOrgFinRepository.getById(consultaTrazaGeneralConsultoriaReq.getPresIdFuenteOrganismoFinanciador());
            idFuentes = registro.getId();
            idOrganismos = registro.getId();
        }

        fabricConsulta.builder()
                .inicializarConsultoria(consultaTrazaGeneralConsultoriaReq.getIdVersionPoa())
                .adicionarParametroIgual("oper_id_accion_corto_plazo",consultaTrazaGeneralConsultoriaReq.getOperIdAccionCortoPlazo())
                .adicionarParametroIgual("pres_id_direccion_admin",consultaTrazaGeneralConsultoriaReq.getDireccionAdminId())
                .adicionarParametroIgual("tar_id_tipo_tarea",consultaTrazaGeneralConsultoriaReq.getTarIdTipoTarea())
                .adicionarParametroLike("pres_detalle_gasto",consultaTrazaGeneralConsultoriaReq.getPresDetalleGasto())
                .adicionarParametroIn("area_id_area",consultaTrazaGeneralConsultoriaReq.getAreas().stream().map(p->p.getAreaIdArea()).collect(Collectors.toList()))
                .adicionarParametroIn("oper_id_operacion",consultaTrazaGeneralConsultoriaReq.getOperaciones().stream().map(p->p.getOperIdOperacion()).collect(Collectors.toList()))
                .adicionarParametroIn("tar_id_tarea",consultaTrazaGeneralConsultoriaReq.getTareas().stream().map(p->p.getTarIdTarea()).collect(Collectors.toList()))
                .adicionarParametroInEnJsonB ("tar_area_responsable","id_area",consultaTrazaGeneralConsultoriaReq.getAreasResponsables().stream().map(p->p.getAreaIdArea()).collect(Collectors.toList()))
                .adicionarParametroInEnJsonB ("cons_areas_validadoras_consultoria","id_area",consultaTrazaGeneralConsultoriaReq.getAreasResponsableConsultoria().stream().map(p->p.getAreaIdArea()).collect(Collectors.toList()))
                .adicionarParametroIgual("pres_id_tipo",consultaTrazaGeneralConsultoriaReq.getPresIdTipo())
                .adicionarParametroIn("pres_id_cat_programatica",consultaTrazaGeneralConsultoriaReq.getCategorias().stream().map(p->p.getPresIdCatProgramatica()).collect(Collectors.toList()))
                .adicionarParametroIn("pres_id_partida_presupuestaria",consultaTrazaGeneralConsultoriaReq.getPartidas().stream().map(p->p.getPresIdPartidaPresupuestaria()).collect(Collectors.toList()))
                .adicionarParametroIgual("pres_id_fuente",idFuentes)
                .adicionarParametroIgual("pres_id_organismo_financiador",idOrganismos)
                .adicionarParametroStringIn("pres_codigo_unidad_ejecutora",consultaTrazaGeneralConsultoriaReq.getUnidadEjecutora().stream().map(p->p.getPresCodigoUnidadEjecutora()).collect(Collectors.toList()))
                .adicionarPaginacion(consultaTrazaGeneralConsultoriaReq.getResultadosPorPagina(),consultaTrazaGeneralConsultoriaReq.getPagina())
        ;
     //   System.out.println("la conusulta es :"+fabricConsulta.obtenerConsultaSql());
     //   System.out.println("la conusulta totale es :"+fabricConsulta.obtenerConsultaTotalesSql());
        List<ConsultaPresupuestoConTotales> detalle =
                jdbcTemplate.query(fabricConsulta.obtenerConsultaSql(), fabricConsulta.obtenerMapParametros(), new BeanPropertyRowMapper<>(ConsultaPresupuestoConTotales.class));

        ConsultaPresupuestoTotales total = new ConsultaPresupuestoTotales();
        if(consultaTrazaGeneralConsultoriaReq.getPagina()==1){
            total =
                    jdbcTemplate.query(fabricConsulta.obtenerConsultaTotalesSql(),
                                    fabricConsulta.obtenerMapParametros(),
                                    new BeanPropertyRowMapper<>(ConsultaPresupuestoTotales.class))
                            .get(0);

        }

        ConsultaConsultoriaDtoResp vConsultaTareasDtoResp = new ConsultaConsultoriaDtoResp();
        vConsultaTareasDtoResp.setDetalle(detalle);
        vConsultaTareasDtoResp.setTotal(total);
        vConsultaTareasDtoResp.setRequest(consultaTrazaGeneralConsultoriaReq);
        return vConsultaTareasDtoResp;
    }

    @Override
    public List<QueryVersionTotalesPoa> crearVersionTotales(Long idPoaArea, Integer pVersion, String pCodigoVersion) {
        List<QueryVersionTotalesPoa> vResultado = null;

        String vQuery = "   SELECT * from  nova.fn_registrar_programacion_poa_por_id_poa(:idPoa,:pVersion,:pCodigoVersion); " ;
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("idPoa", idPoaArea);
        parameter.put("pVersion", pVersion);
        parameter.put("pCodigoVersion", pCodigoVersion);
        vResultado = jdbcTemplate.query(vQuery, parameter, new BeanPropertyRowMapper<>(QueryVersionTotalesPoa.class));
        return vResultado;
    }



    @Override
    public List<QueryResponsableConsulta> listaRolConsultaRolAreas(String nombreUsuario, String codigo) {
        List<QueryResponsableConsulta> vResultado = null;

        String vQuery = " select tu.nombre_usuario ,tu.nombre_completo,tura.id_area ,pd.codigo \n" +
                "from nova.trs_usuario tu \n" +
                "inner join nova.trs_usuario_rol tur \n" +
                "on tu.id = tur.id_usuario and tur.eliminado = false and tu.nombre_usuario=:nombreUsuario \n" +
                "inner join nova.par_dominio pd on tur.id_rol = pd.id and pd.codigo=:codigo " +
                "and pd.grupo_dominio ='ROL_AREA'\n" +
                "inner join nova.trs_usuario_rol_area tura " +
                "on tura.id_usuario_rol = tur.id and tura.eliminado = false     \n" +
                "; " ;
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("nombreUsuario", nombreUsuario);
        parameter.put("codigo", codigo);
        vResultado = jdbcTemplate.query(vQuery, parameter, new BeanPropertyRowMapper<>(QueryResponsableConsulta.class));
        return vResultado;
    }
    @Override
    public List<QueryResponsableConsulta> listaRolConsultaRolGeneral(String nombreUsuario, String codigo) {
        List<QueryResponsableConsulta> vResultado = null;

        String vQuery = " select tu.nombre_usuario ,tu.nombre_completo,pd.codigo \n" +
                "from nova.trs_usuario tu \n" +
                "inner join nova.trs_usuario_rol tur \n" +
                "on tu.id = tur.id_usuario and tur.eliminado = false and tu.nombre_usuario=:nombreUsuario \n" +
                "inner join nova.par_dominio pd on tur.id_rol = pd.id and pd.codigo=:codigo " +
                " and pd.grupo_dominio ='ROL_GENERAL'     \n" +
                "; " ;
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("nombreUsuario", nombreUsuario);
        parameter.put("codigo", codigo);
        vResultado = jdbcTemplate.query(vQuery, parameter, new BeanPropertyRowMapper<>(QueryResponsableConsulta.class));
        return vResultado;
    }

}
