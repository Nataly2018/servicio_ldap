package gob.mefp.reportes.utils.validaciones;

import com.ctc.wstx.shaded.msv_core.datatype.xsd.regex.RegExp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpresionesRegulares {

    public static String caracteres="Letras , Caracteres, Numeros, [],#¡!&<>,.%:;¨´°+*=/_-()$\\";

    public static boolean validarLongitudMinimaYMaxima(String pValor, Integer pCantidadMinima, Integer pCantidadMaxima) {
        boolean formatoValido = false;
        //String regex = "^[A-Za-zÀ-ȕ0-9\\n ,.%:;¨´°/_\\-()$\\\"]{"+pCantidadMinima+","+pCantidadMaxima+"}$";
        //boolean esCorreo = pValor.matches(regex);
        //if (esCorreo)
          //  formatoValido = true;
        return pValor.length()>= pCantidadMinima && pValor.length()<=pCantidadMaxima;

    }
    public static boolean validarCorreo(String pValor) {
        boolean formatoValido = false;
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        boolean esCorreo = pValor.matches(regex);
        if (esCorreo)
            formatoValido = true;
        return formatoValido;
    }
    public static  boolean validarCaracteresPermitidos(String pValor) {
        boolean formatoValido = false;
        //String regex = "^[A-Za-zÀ-ȕ0-9\n ,.%:;¨´°/_\-()$\"]+$";
        Pattern regex = Pattern.compile("^[A-Za-zÀ-ȕ0-9\\n \\[\\]#¡!&<>,.%:;¨´°'+*=/_\\-()$\\\"]+$");
        Matcher mat = regex.matcher(pValor);
        if (mat.matches())
            formatoValido = true;
        return formatoValido;
    }

}
