/**
 * Project: nova
 * Package: bo.gob.ypfb.nova
 * El archivo "FabricConsultaQuery.java" fue creado para el proyecto nova
 * por Y.P.F.B. Corporación, todos los Derechos reservados.
 *
 * @author: Roger Diego Flores Condori
 * @email : cons.rflores@ypfb.gob.bo
 * @date: 19/9/2023 09:35
 * @copyright: YPFB
 * @version: 1.0
 **/
package gob.mefp.reportes.app.reportes.query;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static gob.ypfb.nova.config.ConstantesReporte.*;

public class FabricConsulta {
    public Builder builder() {
        return new Builder(this);
    }
    private Map<String, Object> parametros;
    private String sqlFilasConsulta;
    private String sqlTotalesConsulta;
    private String sqlTotalesCabeceraConsulta;
    private String sqlPaginacionConsulta;
    private String sqlGroupBy;
    private String sqlOrderBy;

    private void inicializar(Long idVersion){
        parametros= new HashMap<>();
        sqlFilasConsulta = SELECT_CAMPOS_TRAZA;
        sqlFilasConsulta += FROM_CAMPOS_TRAZA;

        sqlTotalesCabeceraConsulta = " select count(*) as cantidad, " +
                "sum(pres_total_gestion) as pres_total_gestion," +
                "sum(pres_total_gasto_gestiones_futuras) as pres_total_gasto_gestiones_futuras," +
                "sum(pres_total_gestiones) as pres_total_gestiones ";
        sqlFilasConsulta += "  WHERE 1=1 " ;
        sqlTotalesConsulta = "  WHERE 1=1 ";
        sqlPaginacionConsulta = "";
        sqlGroupBy = "order by oper_codigo_accion_corto_plazo ,area_codigo ,oper_orden_operacion ," +
                "tar_orden_tarea,pres_codigo_partida_presupuestaria::integer ";
    }

    private void inicializarOperaciones(Long idVersion){
        parametros= new HashMap<>();
        sqlFilasConsulta = OPERACIONES_SELECT_CAMPOS_TRAZA;
        sqlFilasConsulta += FROM_CAMPOS_TRAZA;
        sqlFilasConsulta += "  WHERE 1=1 " ;
        sqlTotalesConsulta = "  WHERE 1=1 ";
        sqlPaginacionConsulta = "";
        sqlGroupBy="";
        //sqlGroupBy = OPERACIONES_GROUP_CAMPOS_TRAZA;
        sqlOrderBy = OPERACIONES_ORDER_CAMPOS_TRAZA;
        sqlTotalesCabeceraConsulta = " select count(*) as cantidad, " +
                "sum(gasto_corriente_operacion) as gasto_corriente_operacion," +
                "sum(inversion_operacion) as inversion_operacion," +
                "sum(gasto_corriente_operacion+inversion_operacion) as pres_total_gestion from ( ";
        parametros.put("idPoaVersion",idVersion);

    }

    private void inicializarTareas(Long idVersion){
        parametros= new HashMap<>();
        sqlFilasConsulta = TAREAS_SELECT_CAMPOS_TRAZA;
        sqlFilasConsulta += FROM_CAMPOS_TRAZA;
        sqlFilasConsulta += "  WHERE 1=1 " ;
        sqlTotalesConsulta = "  WHERE 1=1 ";
        sqlPaginacionConsulta = "";
        sqlGroupBy="";
        //sqlGroupBy = OPERACIONES_GROUP_CAMPOS_TRAZA;
        sqlOrderBy = TAREAS_ORDER_CAMPOS_TRAZA;
        sqlTotalesCabeceraConsulta = " select count(*) as cantidad, " +
                "sum(gasto_corriente_tarea) as gasto_corriente_tarea," +
                "sum(inversion_tarea) as inversion_tarea," +
                "sum(gasto_corriente_tarea+inversion_tarea) as pres_total_gestion from ( ";
        parametros.put("idPoaVersion",idVersion);

    }
    private void inicializarPresupuesto(Long idVersion){
        parametros= new HashMap<>();
            sqlFilasConsulta = PRESUPUESTO_SELECT_CAMPOS_TRAZA;
        sqlFilasConsulta += FROM_CAMPOS_TRAZA;
        sqlFilasConsulta += "  WHERE 1=1 and pres_id_presupuesto is not null   " ;
        sqlTotalesConsulta = "  WHERE 1=1 and pres_id_presupuesto is not null   ";
        sqlPaginacionConsulta = "";
        sqlGroupBy="";
        //sqlGroupBy = OPERACIONES_GROUP_CAMPOS_TRAZA;
        sqlOrderBy = PRESUPUESTO_ORDER_CAMPOS_TRAZA;
        sqlTotalesCabeceraConsulta = " select count(*) as cantidad, " +
                "sum(pres_total_gestion) as pres_total_gestion," +
                "sum(pres_total_gasto_gestiones_futuras) as pres_total_gasto_gestiones_futuras," +
                "sum(pres_total_gestiones) as pres_total_gestiones from ( ";
        parametros.put("idPoaVersion",idVersion);

    }
    private void inicializarConsultoria(Long idVersion){
        parametros= new HashMap<>();
        sqlFilasConsulta = CONSULTORIA_SELECT_CAMPOS_TRAZA;
        sqlFilasConsulta += FROM_CAMPOS_TRAZA;
        sqlFilasConsulta += "  WHERE 1=1 and pres_id_presupuesto is not null  and cons_objetivo_consultoria is not null  " ;
        sqlTotalesConsulta = "  WHERE 1=1 and pres_id_presupuesto is not null   and cons_objetivo_consultoria is not null ";
        sqlPaginacionConsulta = "";
        sqlGroupBy="";
        //sqlGroupBy = OPERACIONES_GROUP_CAMPOS_TRAZA;
        sqlOrderBy = PRESUPUESTO_ORDER_CAMPOS_TRAZA;
        sqlTotalesCabeceraConsulta = " select count(*) as cantidad, " +
                "sum(pres_total_gestion) as pres_total_gestion," +
                "sum(pres_total_gasto_gestiones_futuras) as pres_total_gasto_gestiones_futuras," +
                "sum(pres_total_gestiones) as pres_total_gestiones from ( ";
        parametros.put("idPoaVersion",idVersion);

    }

    private void adicionarParametroIgualdad(String nombreCampo, Object valor){
        if(valor!=null) {
            String subSql = " AND " + nombreCampo + " = :" + nombreCampo + " ";
            parametros.put(nombreCampo, valor);
            sqlFilasConsulta += subSql;
            sqlTotalesConsulta += subSql;
        }
    }
    private void adicionarParametroLike(String nombreCampo, String valor){
        if(valor!=null) {
            String subSql = " AND lower(translate(" + nombreCampo + ", 'áéíóúÁÉÍÓÚüÜ', 'aeiouAEIOUuU')) LIKE" +
                    " lower(translate(:" + nombreCampo+", 'áéíóúÁÉÍÓÚüÜ', 'aeiouAEIOUuU')) " ;
            parametros.put(nombreCampo,"%"+ valor+"%");
            sqlFilasConsulta += subSql;
            sqlTotalesConsulta += subSql;
        }
    }

    private void adicionarCabeceraTotalizador(String cabeceraTotal){
        sqlTotalesCabeceraConsulta+=","+cabeceraTotal;
    }

    private void adicionarParametroIn(String nombreCampo, List<Long> listaIds){
        if (listaIds != null && !listaIds.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Long listaId : listaIds) {
                sb.append(listaId).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            String subSql = " AND "+nombreCampo+" IN (" + sb.toString() + ")";
            sqlFilasConsulta += subSql;
            sqlTotalesConsulta += subSql;
        }
    }
    private void adicionarParametroStringIn(String nombreCampo, List<String> listaIds){
        if (listaIds != null && !listaIds.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (String listaId : listaIds) {
                sb.append(listaId).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            String subSql = " AND "+nombreCampo+" IN ('" + sb.toString() + "')";
            sqlFilasConsulta += subSql;
            sqlTotalesConsulta += subSql;
        }
    }
    private void adicionarParametroInEnJsonB(String nombreCampo,String nombreCampoJsonB, List<Long> listaIds){
        if (listaIds != null && !listaIds.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Long listaId : listaIds) {
                sb.append("'").append(listaId).append("',");
            }
            sb.deleteCharAt(sb.length() - 1);
            String subSql = " AND EXISTS (\n" +
                    "    SELECT 1\n" +
                    "    FROM jsonb_array_elements("+nombreCampo+") AS a\n" +
                    "    WHERE a->>'"+nombreCampoJsonB+"' IN (" + sb.toString() + ") ) ";
            sqlFilasConsulta += subSql;
            sqlTotalesConsulta += subSql;
        }
    }

    private void adicionarPaginacion(Integer resultadosPorPagina,Integer pagina){
            if(resultadosPorPagina <=0){
                return;
            }
        int offset = (pagina - 1) * resultadosPorPagina;
        parametros.put("limit",resultadosPorPagina);
        parametros.put("offset", offset);
        sqlPaginacionConsulta = " LIMIT :limit OFFSET :offset ";
    }



    public String obtenerConsultaSql(){
         return sqlFilasConsulta + " " + sqlGroupBy + " " + sqlOrderBy + " "
                  + sqlPaginacionConsulta;
    }
    public String obtenerConsultaTotalesSql(){
        return sqlTotalesCabeceraConsulta + " "+sqlFilasConsulta + " "+ sqlGroupBy + " " + sqlOrderBy + " "
                + ") a";
    }
    public Map<String, Object> obtenerMapParametros(){
        return parametros;
    }




    private Map<String, Object> getParametros() {
        return parametros;
    }

    private void setParametros(Map<String, Object> parametros) {
        this.parametros = parametros;
    }

    private String getSqlFilasConsulta() {
        return sqlFilasConsulta;
    }

    private void setSqlFilasConsulta(String sqlFilasConsulta) {
        this.sqlFilasConsulta = sqlFilasConsulta;
    }

    public class Builder {
        private final FabricConsulta fabricConsulta;

        public Builder(FabricConsulta fabricConsulta) {
            this.fabricConsulta = fabricConsulta;
        }

        public Builder inicializarOperaciones(Long idVersion) {
            this.fabricConsulta.inicializarOperaciones(idVersion);
            return this;
        }
        public Builder inicializarTareas(Long idVersion) {
            this.fabricConsulta.inicializarTareas(idVersion);
            return this;
        }
        public Builder inicializarPresupuesto(Long idVersion) {
            this.fabricConsulta.inicializarPresupuesto(idVersion);
            return this;
        }
        public Builder inicializarConsultoria(Long idVersion) {
            this.fabricConsulta.inicializarConsultoria(idVersion);
            return this;
        }

        //inicializarPresupuesto
        public Builder adicionarParametroIgual(String nombreCampo, Object valor) {
            this.fabricConsulta.adicionarParametroIgualdad(nombreCampo,valor);
            return this;
        }
        //adicionarParametroLike
        public Builder adicionarParametroLike(String nombreCampo, String valor) {
            this.fabricConsulta.adicionarParametroLike(nombreCampo,valor);
            return this;
        }
        public Builder adicionarParametroIn(String nombreCampo,  List<Long> valor) {
            this.fabricConsulta.adicionarParametroIn(nombreCampo,valor);
            return this;
        }
        public Builder adicionarParametroStringIn(String nombreCampo,  List<String> valor) {
            this.fabricConsulta.adicionarParametroStringIn(nombreCampo,valor);
            return this;
        }


        public Builder adicionarParametroInEnJsonB(String nombreCampo,String nombreCampoJsonB, List<Long> listaIds) {
            this.fabricConsulta.adicionarParametroInEnJsonB(nombreCampo,nombreCampoJsonB, listaIds);
            return this;
        }

        public Builder adicionarCabeceraTotalizador(String nombreCampo) {
            this.fabricConsulta.adicionarCabeceraTotalizador(nombreCampo);
            return this;
        }
        public Builder adicionarPaginacion(Integer resultadosPorPagina,Integer pagina) {
            this.fabricConsulta.adicionarPaginacion(resultadosPorPagina,pagina);
            return this;
        }

        //adicionarPaginacion(String nombreCampo, Integer limit,Integer offset)
    }
}
