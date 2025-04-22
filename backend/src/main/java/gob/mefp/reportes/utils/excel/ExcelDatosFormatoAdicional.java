/**
 * Project: reportes
 * Package: bo.gob.mefp.reportes
 * @author: Nataly Wendy Mamani Mamani
 * @version: 1.0
 **/

package gob.mefp.reportes.utils.excel;
import lombok.Data;
@Data
public class ExcelDatosFormatoAdicional {
    Integer filaInicio;
    String columnaAgrupadora;
    Integer cantidadFilasCabecera;
    Integer cantidadFilaPie;
    String columnaMarcaFormatoAdicional;
    String columnaInicial;
    String columnaFinal;

    boolean[] coloresCabera;
    boolean[] coloresPie;
    String[] columnasSumatoria;
}
