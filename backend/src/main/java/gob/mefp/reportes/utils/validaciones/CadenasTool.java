/**
 * Project: reportes
 * Package: bo.gob.mefp.reportes
 * @author: Nataly Wendy Mamani Mamani
 * @version: 1.0
 **/

package gob.mefp.reportes.utils.validaciones;

import java.math.BigDecimal;


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
}
