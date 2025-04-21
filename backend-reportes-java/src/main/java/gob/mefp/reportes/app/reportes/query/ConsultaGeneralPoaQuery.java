package gob.mefp.reportes.app.reportes.query;

import gob.ypfb.nova.app.reportes.dto.query.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class ConsultaGeneralPoaQuery  implements  IConsultaGeneralPoaQuery{
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Override
    public List<QueryTareasPorAcpPoaAreaOperacion> listarTareasPorAcpPoaAreaOperacion(Long pIdVersionPoa, String pIdPoaArea, String pIdAcp) {
        List<QueryTareasPorAcpPoaAreaOperacion> vResultado = null;

        String vQuery = "select t.oper_codigo_accion_corto_plazo as codigoAcp,\n" +
                "       t.area_sigla as area,\n" +
                "\t   t.oper_codigo_operacion as codigoOperacion,\n" +
                "\t   count(distinct t.tar_id_tarea) as cantidadTareas\n" +
                "\t   \n" +
                "from   nova.trs_poa_versionado as t\n" +
                "where \n" +
                "\t\tcase when :pIdAcp <> 'null' then (t.oper_id_accion_corto_plazo  in(select UNNEST(REGEXP_SPLIT_TO_ARRAY(:pIdAcp,','))::bigint)) else t.oper_id_accion_corto_plazo=t.oper_id_accion_corto_plazo end\n" +
                "\t\tand (case when :pIdPoaArea <> 'null' then (t.oper_id_poa_area  in((select UNNEST(REGEXP_SPLIT_TO_ARRAY(:pIdPoaArea,','))::bigint))) else t.oper_id_poa_area = t.oper_id_poa_area end )\n" +
                "\t\tand t.\"version\" =:pIdVersionPoa\n" +
                "group by 1,2,3" ;
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("pIdAcp", pIdAcp);
        parameter.put("pIdPoaArea", pIdPoaArea);
        parameter.put("pIdVersionPoa", pIdVersionPoa);
        vResultado = jdbcTemplate.query(vQuery, parameter, new BeanPropertyRowMapper<>(QueryTareasPorAcpPoaAreaOperacion.class));
        return vResultado;
    }
    @Override
    public List<QueryTareasPoraAcpPoaArea> listarTareasPorAcpPoaArea(Long pIdVersionPoa, String pIdPoaArea, String pIdAcp) {
        List<QueryTareasPoraAcpPoaArea> vResultado = null;

        String vQuery = "select t.oper_codigo_accion_corto_plazo as codigoAcp,\n" +
                "       t.area_sigla as area,\n" +
                "\t   count(distinct t.tar_id_tarea) as cantidadTareas \n" +
                "\t   \n" +
                "from   nova.trs_poa_versionado as t\n" +
                "where \n" +
                "\t\tcase when :pIdAcp is not null then (t.oper_id_accion_corto_plazo  in(select UNNEST(REGEXP_SPLIT_TO_ARRAY(:pIdAcp,','))::bigint)) else t.oper_id_accion_corto_plazo=t.oper_id_accion_corto_plazo end\n" +
                "\t\tand (case when :pIdPoaArea <> 'null' then (t.oper_id_poa_area  in((select UNNEST(REGEXP_SPLIT_TO_ARRAY(:pIdPoaArea,','))::bigint))) else t.oper_id_poa_area = t.oper_id_poa_area end )\n" +
                "\t\tand t.\"version\" =:pIdVersionPoa\n" +
                "group by 1,2" ;
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("pIdAcp", pIdAcp);
        parameter.put("pIdPoaArea", pIdPoaArea);
        parameter.put("pIdVersionPoa", pIdVersionPoa);
        vResultado = jdbcTemplate.query(vQuery, parameter, new BeanPropertyRowMapper<>(QueryTareasPoraAcpPoaArea.class));
        return vResultado;
    }
    @Override
    public List<QueryTareasPorAcp> listarTareasPorAcp(Long pIdVersionPoa, String pIdAcp) {
        List<QueryTareasPorAcp> vResultado = null;
        String vQuery;

            vQuery = "select t.oper_codigo_accion_corto_plazo as codigoAcp,\n" +
                    "\t   count(distinct t.tar_id_tarea) as cantidadTareas\n" +
                    "\t   \n" +
                    "from   nova.trs_poa_versionado as t\n" +
                    "where \n" +
                    "\t\tcase when :pIdAcp <> 'null' then (t.oper_id_accion_corto_plazo  in(select UNNEST(REGEXP_SPLIT_TO_ARRAY(:pIdAcp,','))::bigint)) else t.oper_id_accion_corto_plazo=t.oper_id_accion_corto_plazo end\n" +
                    "\t\tand t.\"version\" =:pIdVersionPoa\n" +
                    "group by 1";

        Map<String, Object> parameter = new HashMap<>();
        parameter.put("pIdAcp", pIdAcp);
        parameter.put("pIdVersionPoa", pIdVersionPoa);
        vResultado = jdbcTemplate.query(vQuery, parameter, new BeanPropertyRowMapper<>(QueryTareasPorAcp.class));
        return vResultado;
    }
    @Override
    public List<QueryTareasPorArea> listarTareasPorArea(Long pIdVersionPoa, String pIdPoaArea) {
        List<QueryTareasPorArea> vResultado = null;
        String vQuery;


            vQuery = "select t.area_sigla as area, \n" +
                    "                  count(distinct t.tar_id_tarea) as cantidadTareas  \n" +
                    "                   \n" +
                    "                from   nova.trs_poa_versionado as t \n" +
                    "                where  \n" +
                    "                (case when :pIdPoaArea <> 'null' then (t.oper_id_poa_area  in((select UNNEST(REGEXP_SPLIT_TO_ARRAY(:pIdPoaArea,','))::bigint))) else t.oper_id_poa_area = t.oper_id_poa_area end ) \n" +
                    "                and t.version =:pIdVersionPoa\n" +
                    "                group by 1\n";

        Map<String, Object> parameter = new HashMap<>();
        parameter.put("pIdPoaArea", pIdPoaArea);
        parameter.put("pIdVersionPoa", pIdVersionPoa);
        vResultado = jdbcTemplate.query(vQuery, parameter, new BeanPropertyRowMapper<>(QueryTareasPorArea.class));
        return vResultado;
    }
    @Override
    public List<QueryTareasPorAreaYTipo> listarCantidadTareasPorArea(Long pIdVersionPoa, String pIdPoaArea) {
        List<QueryTareasPorAreaYTipo> vResultado = null;
        String vQuery;

            vQuery = "select res.area as area, \n" +
                    "max(case when res.tar_tipo_tarea='ACTIVIDAD' then res.cantidadTareas else 0 end) as  actividad,\n" +
                    "max(case when res.tar_tipo_tarea='PROYECTO' then res.cantidadTareas else 0 end) as proyecto\n" +
                    "from(\n" +
                    "select t.area_sigla as area, \n" +
                    "t.tar_tipo_tarea,\n" +
                    "count(distinct t.tar_id_tarea) as cantidadTareas   \n" +
                    "from   nova.trs_poa_versionado as t  \n" +
                    "where   \n" +
                    "t.version =:pIdVersionPoa and" +
                    " (case when :pIdPoaArea <> 'null' then ( t.oper_id_poa_area  in((select UNNEST(REGEXP_SPLIT_TO_ARRAY(:pIdPoaArea,','))::bigint))) else t.oper_id_poa_area = t.oper_id_poa_area end )\n" +
                    "group by 1,2) as res\n" +
                    "group by 1;";

        Map<String, Object> parameter = new HashMap<>();
        parameter.put("pIdPoaArea", pIdPoaArea);
        parameter.put("pIdVersionPoa", pIdVersionPoa);
        vResultado = jdbcTemplate.query(vQuery, parameter, new BeanPropertyRowMapper<>(QueryTareasPorAreaYTipo.class));
        return vResultado;
    }
    @Override
    public List<QueryOperacionesPorAcpPoaArea> listarOperacionesPorAcpPoaArea(Long pIdVersionPoa, String pIdPoaArea, String pIdAcp) {
        List<QueryOperacionesPorAcpPoaArea> vResultado = null;

        String vQuery = "select t.oper_codigo_accion_corto_plazo as codigoAcp,\n" +
                "       t.area_sigla as area,\n" +
                "\t   count(distinct t.oper_id_operacion) as cantidadOperaciones\n" +
                "\t   \n" +
                "from   nova.trs_poa_versionado as t\n" +
                "where \n" +
                "\t\tcase when :pIdAcp <> 'null' then (t.oper_id_accion_corto_plazo  in(select UNNEST(REGEXP_SPLIT_TO_ARRAY(:pIdAcp,','))::bigint)) else t.oper_id_accion_corto_plazo=t.oper_id_accion_corto_plazo end\n" +
                "\t\tand (case when :pIdPoaArea <> 'null' then (t.oper_id_poa_area  in((select UNNEST(REGEXP_SPLIT_TO_ARRAY(:pIdPoaArea,','))::bigint))) else t.oper_id_poa_area = t.oper_id_poa_area end )\n" +
                "\t\tand t.\"version\" =:pIdVersionPoa\n" +
                "group by 1,2" ;
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("pIdAcp", pIdAcp);
        parameter.put("pIdPoaArea", pIdPoaArea);
        parameter.put("pIdVersionPoa", pIdVersionPoa);
        vResultado = jdbcTemplate.query(vQuery, parameter, new BeanPropertyRowMapper<>(QueryOperacionesPorAcpPoaArea.class));
        return vResultado;
    }
    @Override
    public List<QueryOperacionesPorAcp> listarOperacionesPorAcp(Long pIdVersionPoa, String pIdAcp) {
        List<QueryOperacionesPorAcp> vResultado = null;

        String vQuery = "select t.oper_codigo_accion_corto_plazo as codigoAcp,\n" +
                "\t   count(distinct t.oper_id_operacion) as cantidadOperaciones\n" +
                "\t   \n" +
                "from   nova.trs_poa_versionado as t\n" +
                "where \n" +
                "\t\tcase when :pIdAcp <> 'null' then (t.oper_id_accion_corto_plazo  in(select UNNEST(REGEXP_SPLIT_TO_ARRAY(:pIdAcp,','))::bigint)) else t.oper_id_accion_corto_plazo=t.oper_id_accion_corto_plazo end\n" +
                "\t\tand t.\"version\" =:pIdVersionPoa\n" +
                "group by 1" ;
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("pIdAcp", pIdAcp);
        parameter.put("pIdVersionPoa", pIdVersionPoa);
        vResultado = jdbcTemplate.query(vQuery, parameter, new BeanPropertyRowMapper<>(QueryOperacionesPorAcp.class));
        return vResultado;
    }
    @Override
    public List<QueryOperacionesPorArea> listarOperacionesPorArea(Long pIdVersionPoa, String pIdPoaArea) {
        List<QueryOperacionesPorArea> vResultado = null;

        String vQuery = "select t.area_sigla as area,\n" +
                "\t   count(distinct t.oper_id_operacion) as cantidadOperaciones \n" +
                "\t   \n" +
                "from   nova.trs_poa_versionado as t\n" +
                "where \n" +
                "\t\t(case when :pIdPoaArea <> 'null' then (t.oper_id_poa_area  in((select UNNEST(REGEXP_SPLIT_TO_ARRAY(:pIdPoaArea,','))::bigint))) else t.oper_id_poa_area = t.oper_id_poa_area end )\n" +
                "\t\tand t.\"version\" =:pIdVersionPoa\n" +
                "group by 1" ;
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("pIdPoaArea", pIdPoaArea);
        parameter.put("pIdVersionPoa", pIdVersionPoa);
        vResultado = jdbcTemplate.query(vQuery, parameter, new BeanPropertyRowMapper<>(QueryOperacionesPorArea.class));
        return vResultado;
    }
    @Override
    public List<QueryPresupuestoPorTipoTareaArea> obtenerPresupuestoPorTipoTarea(Long pIdVersionPoa, String pIdPoaArea) {
        List<QueryPresupuestoPorTipoTareaArea> vResultado = null;

        String vQuery = "select res.area as area,  \n" +
                "max(case when res.tar_tipo_tarea='ACTIVIDAD' then res.presupuesto else 0 end) as  presupuesto_actividad, \n" +
                "max(case when res.tar_tipo_tarea='PROYECTO' then res.presupuesto else 0 end) as presupuesto_proyecto \n" +
                "from( \n" +
                "select t.area_sigla as area,  \n" +
                "t.tar_tipo_tarea, \n" +
                "sum( case when  t.pres_salario_mensual_consultor>0 then t.pres_salario_mensual_consultor*t.pres_cantidad_requerida*t.pres_duracion_meses_consultoria else t.pres_cantidad_requerida*t.pres_precio_unitario end) as presupuesto    \n" +
                "from   nova.trs_poa_versionado as t   \n" +
                "where    \n" +
                "t.version =:pIdVersionPoa and \n" +
                " (case when :pIdPoaArea <>'null' then ( t.oper_id_poa_area  in((select UNNEST(REGEXP_SPLIT_TO_ARRAY(:pIdPoaArea,','))::bigint))) else t.oper_id_poa_area = t.oper_id_poa_area end ) \n" +
                "group by 1,2) as res \n" +
                "group by 1" ;
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("pIdPoaArea", pIdPoaArea);
        parameter.put("pIdVersionPoa", pIdVersionPoa);
        vResultado = jdbcTemplate.query(vQuery, parameter, new BeanPropertyRowMapper<>(QueryPresupuestoPorTipoTareaArea.class));
        return vResultado;
    }

}
