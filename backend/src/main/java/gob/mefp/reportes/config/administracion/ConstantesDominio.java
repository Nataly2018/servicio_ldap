/**
 * Project: reportes
 * Package: bo.gob.mefp.reportes
 * @author: Nataly Wendy Mamani Mamani
 * @version: 1.0
 **/
package gob.mefp.reportes.config.administracion;

public class ConstantesDominio {

    public enum EnumGrupos {
        TIPO_VALOR_TAREA ("TIPO_VALOR_TAREA"),
        ROL              ("ROL"),
        TIPO_INDICADOR   ("TIPO_INDICADOR"),
        CORREO_TEST      ("CORREO_TEST"),
        TIPO_PRESUPUESTO ("TIPO_PRESUPUESTO"),
        TIPO_VALOR       ("TIPO_VALOR"),
        ROL_GENERAL      ("ROL_GENERAL"),
        ROL_AREA      ("ROL_AREA"),
        TIPO_TAREA       ("TIPO_TAREA");
        public final String grupo;
        EnumGrupos(String grupo) {
            this.grupo = grupo;
        }
    }

    public enum EnumTipoUsuarios {
        ROL ("ROL"),
        ROL_GENERAL("ROL_GENERAL"),
        ROL_AREA      ("ROL_AREA");
        public final String grupo;
        EnumTipoUsuarios(String grupo) {
            this.grupo = grupo;
        }
    }


}
