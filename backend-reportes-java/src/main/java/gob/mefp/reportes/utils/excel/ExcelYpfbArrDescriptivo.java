/**
 * Project: nova
 * Package: bo.gob.ypfb.nova
 * El archivo "ExcelYpfbArrDescriptivo.java" fue creado para el proyecto nova
 * por Y.P.F.B. Corporación, todos los Derechos reservados.
 *
 * @author: Roger Diego Flores Condori
 * @email : cons.rflores@ypfb.gob.bo
 * @date: 6/23/2023 4:17 p. m.
 * @copyright: YPFB
 * @version: 1.0
 **/
package gob.mefp.reportes.utils.excel;

import lombok.Data;

import java.lang.reflect.Field;


@Data
public class ExcelYpfbArrDescriptivo {
    private Field vField;
    private Integer posicionColumna;
    private String tituloColumna;
    private Integer cantidadDecimalesColumna;
    private Boolean columnaOculta;
    private Boolean soloLectura;
    private Class claseListado;
    private Boolean  esTitulo;
    private Boolean  tipoEstricto;
    private String formatoRegex;
    private Boolean esFormula;
    private String  ubicacionFormula;
    private Boolean valorONada;
}
