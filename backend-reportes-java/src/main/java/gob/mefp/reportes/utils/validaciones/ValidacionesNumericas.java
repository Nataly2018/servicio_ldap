/**
 * Project: nova
 * Package: bo.gob.ypfb.nova
 * El archivo "ValidacionesNumericas.java" fue creado para el proyecto nova
 * por Y.P.F.B. Corporación, todos los Derechos reservados.
 *
 * @author: Roger Diego Flores Condori
 * @email : cons.rflores@ypfb.gob.bo
 * @date: 5/25/2023 4:38 p. m.
 * @copyright: YPFB
 * @version: 1.0
 **/
package gob.mefp.reportes.utils.validaciones;

import gob.ypfb.nova.exception.ErrorValidacionException;

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
