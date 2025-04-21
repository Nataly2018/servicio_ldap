/**
 * Project: nova
 * Package: bo.gob.ypfb.nova
 * El archivo "ExcelDatosFormatoAdicional.java" fue creado para el proyecto nova
 * por Y.P.F.B. Corporación, todos los Derechos reservados.
 *
 * @author: Roger Diego Flores Condori
 * @email : cons.rflores@ypfb.gob.bo
 * @date: 6/23/2023 4:22 p. m.
 * @copyright: YPFB
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
