/**
 * Project: reportes
 * Package: bo.gob.mefp.reportes
 * @author: Nataly Wendy Mamani Mamani
 * @version: 1.0
 **/
package gob.mefp.reportes.config;

public class ConstanteReporteGeneralExcel {
    public static final String PLANTILLA_XLS_REPORTE_POA_UNIDAD_PATH = "excel/reporte_poa_unidad_plantilla.xlsx";

    public enum EnumReporteHojasExcel {
        PRESUPUESTO_GASTO_CORRIENTE("PTO_GC","Presupuesto"),
        PRESUPUESTO_INVERSION("PTO_INV","Presupuesto"),
        CONSULTOR_GASTO_CORRIENTE("CONSULT_GC","Consultor Gasto corriente"),
        CONSULTOR_INVERSION("CONSULT_INV","Consultor Inversion"),
        TAREAS("TAREAS","Tareas"),
        HOJAS_OPERACIONES("OP_TR","OP_TR"),
        RESUM_PTO_GC("RESUM_PTO_GC","Presupuesto"),
        RESUM_PTO_INV("RESUM_PTO_INV","Presupuesto"),
        MEM_PTO_GC("MEM_PTO_GC","Memoria de calculo"),
        MEM_PTO_INV("MEM_PTO_INV","Memoria de calculo"),
        PASAJES_VIATICOS("PASAJES_VIATICOS","Pasajes y Viaticos"),
        OPERACION("OPERACION","Operaciones");

        public final String nombre;
        public final String nombreCompleto;
        EnumReporteHojasExcel(String nombre,String nombreCompleto) {

            this.nombre = nombre;
            this.nombreCompleto= nombreCompleto;
        }
    }

    public enum EnumCriterio {
        APROBADOR_ACP_NOMBRE_GCOM("APROBADOR_ACP_NOMBRE_GCOM"),
        APROBADOR_ACP_CARGO_GCOM("APROBADOR_ACP_CARGO_GCOM"),
        APROBADOR_ACP_NOMBRE_GRGD("APROBADOR_ACP_NOMBRE_GRGD"),
        APROBADOR_ACP_CARGO_GRGD("APROBADOR_ACP_CARGO_GRGD");
        public final String codigo;
        EnumCriterio(String value) {
            this.codigo = value;
        }
    }

    public static final String TEXTO_SUMA_POR_TAREA = "La Meta Mensual de la  tarea %s es de tipo PORCENTUAL " +
            "por lo que debe sumar un 100%% , actualmente suma %s";
    public static final String TEXTO_SUMA_PONDERACION_TAREA_POR_OPERACION = "El total de la ponderacion porcentual de las tareas" +
            " de la operacion %s debe sumar un 100%% , actualmente suma %s";
    public static final String TEXTO_SUMA_PONDERACION_OPERACION = "El total de la ponderacion porcentual de las operaciones" +
            " debe sumar un 100%% , actualmente suma %s";
    public static final String TEXTO_ERROR_OPERACIONES_CON_TAREAS = "La operacion %s " +
            " no tiene tareas ";
    public static final String TEXTO_CONSULTORIA_SIN_COMPLETAR = "La  tarea %s tiene %s presupuesto(s) de consultoria " +
            " cuya informacion (objetivo, perfil etc.) falta commpletar";

    public static final String TEXTO_VALIDAR_TAREA_MESES_CON_VALOR = "La  tarea %s no tiene ningun valor en sus meses de programación";


}
