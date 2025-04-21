/**
 * Project: nova
 * Package: bo.gob.ypfb.nova
 * El archivo "ExcelConstantes.java" fue creado para el proyecto nova
 * por Y.P.F.B. Corporación, todos los Derechos reservados.
 *
 * @author: Roger Diego Flores Condori
 * @email : cons.rflores@ypfb.gob.bo
 * @date: 7/11/2023 9:40 a. m.
 * @copyright: YPFB
 * @version: 1.0
 **/
package gob.mefp.reportes.utils.excel;

public class ExcelConstantes {
    public enum EnumTipoPrimeraColumna {
        CONTADOR("CONTADOR"),
        SIN_COLUMNA_AUTOMATICA("SIN_COLUMNA_AUTOMATICA"),
        TOTAL("TOTAL")
        ;
        public final String nombre;
        EnumTipoPrimeraColumna(String nombre) {
            this.nombre = nombre;
        }



    }
    public enum TipoArchivoExportacion {
        EXCEL("EXCEL"),
        PDF("PDF")
        ;
        private final String text;
        TipoArchivoExportacion(final String text) {
            this.text = text;
        }
        @Override
        public String toString() {
            return text;
        }
    }

    public enum TipoVersionDatos {
        UNIDAD("UNIDAD"),
        AJUSTADO_GAFC("AJUSTADO_GAFC")
        ;
        private final String text;
        TipoVersionDatos(final String text) {
            this.text = text;
        }
        @Override
        public String toString() {
            return text;
        }
    }
}
