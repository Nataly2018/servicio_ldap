/**
 * Project: reportes
 * Package: bo.gob.mefp.reportes
 * @author: Nataly Wendy Mamani Mamani
 * @version: 1.0
 **/
package gob.mefp.reportes.utils.validaciones;

import gob.mefp.reportes.exception.ErrorValidacionException;

import java.math.BigDecimal;

public class ValidacionesNumericas {
    public static void ValorPorcentual(BigDecimal valor, String descripcionCampo){
        if(valor.compareTo(new BigDecimal("100"))==1){
            throw new ErrorValidacionException("El campo "+ descripcionCampo+" no puede ser un numero mayor a 100");
        }
        if(valor.compareTo(BigDecimal.ZERO)==-1){
            throw new ErrorValidacionException("El campo "+ descripcionCampo+" no puede ser un numero negativo");
        }
    }
    public static void ValorNumericoPositivo(BigDecimal valor, String descripcionCampo){
        if(valor.compareTo(BigDecimal.ZERO)==-1){
            throw new ErrorValidacionException("El campo '"+ descripcionCampo+"' no puede ser un numero negativo");
        }
    }
}
