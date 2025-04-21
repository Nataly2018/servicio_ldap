/**
 * Project: nova
 * Package: bo.gob.ypfb.nova
 * El archivo "CadenasTool.java" fue creado para el proyecto nova
 * por Y.P.F.B. Corporación, todos los Derechos reservados.
 *
 * @author: Roger Diego Flores Condori
 * @email : cons.rflores@ypfb.gob.bo
 * @date: 7/5/2023 11:55 a. m.
 * @copyright: YPFB
 * @version: 1.0
 **/
package gob.mefp.reportes.utils.validaciones;

import gob.ypfb.nova.exception.ErrorValidacionException;

import java.math.BigDecimal;

import static gob.ypfb.nova.config.ConstantesPresupuesto.*;

public class CadenasTool {
    public static String cadenaVacia( String valorCampo){
        if(valorCampo == null){
            return "";
        }
        return valorCampo;
    }
    public static Integer numeroVacio( Integer valorCampo){
        if(valorCampo == null){
            return 0;
        }
        return valorCampo;
    }
    public static BigDecimal decimalVacio( BigDecimal valorCampo){
        if(valorCampo == null){
            return BigDecimal.ZERO;
        }
        return valorCampo;
    }

    public static void validarCaracteresTexto(String valor,String nombreCampo){
        if (!ExpresionesRegulares.validarCaracteresPermitidos(valor)) {
            throw new ErrorValidacionException(String.format(ERROR_CARACTERES, nombreCampo) + "Los caracteres admitidos son "+ ExpresionesRegulares.caracteres);
        }
    }
    public static void validarLongitudTexto(String valor,String nombreCampo,Integer valorMinimo,Integer valorMaximo){
        if (!ExpresionesRegulares.validarLongitudMinimaYMaxima(valor
                , valorMinimo
                , valorMaximo)) {
            throw new ErrorValidacionException(String.format(ERROR_LONGITUD, nombreCampo,
                    valorMinimo,
                    valorMaximo));
        }
    }
    public static void validarValorObligatorio(String valor,String nombreCampo){
        if (valor ==null || valor.isEmpty()) {
            throw new ErrorValidacionException(String.format(ERROR_OBLIGATORIO, nombreCampo));
        }
    }
    public static void validarTextoNoAplica(String valor,String nombreCampo,String soloAplica){
        if ((valor != null) && !valor.isEmpty()) {
            throw new ErrorValidacionException(String.format(ERROR_NO_APLICA, nombreCampo,soloAplica));
        }

    }
}
