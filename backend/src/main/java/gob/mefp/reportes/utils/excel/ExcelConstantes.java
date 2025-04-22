/**
 * Project: reportes
 * Package: bo.gob.mefp.reportes
 * @author: Nataly Wendy Mamani Mamani
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
