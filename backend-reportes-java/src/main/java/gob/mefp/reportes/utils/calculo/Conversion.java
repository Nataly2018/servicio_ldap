/**
 * Project: nova
 * Package: bo.gob.ypfb.nova
 * El archivo "Operaciones.java" fue creado para el proyecto nova
 * por Y.P.F.B. Corporación, todos los Derechos reservados.
 *
 * @author: Roger Diego Flores Condori
 * @email : cons.rflores@ypfb.gob.bo
 * @date: 6/6/2023 12:06 p. m.
 * @copyright: YPFB
 * @version: 1.0
 **/
package gob.mefp.reportes.utils.calculo;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Conversion {

    Boolean comasComoSeparadorMiles=false;
    DecimalFormatSymbols symbols;
    public Conversion(){
        setComasComoSeparadorMiles(comasComoSeparadorMiles);
    }
    public Boolean getComasComoSeparadorMiles() {
        return comasComoSeparadorMiles;
    }

    public void setComasComoSeparadorMiles(Boolean comasComoSeparadorMiles) {
        this.comasComoSeparadorMiles = comasComoSeparadorMiles;
        symbols = new DecimalFormatSymbols();

        if (comasComoSeparadorMiles) {
            symbols.setGroupingSeparator(',');
            symbols.setDecimalSeparator('.');
        } else {
            symbols.setGroupingSeparator('.');
            symbols.setDecimalSeparator(',');
        }
    }

    public String booleanToString(Boolean valor){
        if(valor) {
            return "SI";
        }
        return "NO";
    }

    public  String decimalToString(BigDecimal valor){
        return decimalToString(valor, Operaciones.EnumTipoNum.NUMERO);
    }
    public  String decimalToPorcentualString(BigDecimal valor){
        return decimalToString(valor, Operaciones.EnumTipoNum.CONVERTIR_PORCENTUAL);
    }
    public  String decimalToString(BigDecimal valor, Operaciones.EnumTipoNum tipo){
        if(valor==null){
            return "";
        }
        if(tipo.equals(Operaciones.EnumTipoNum.CONVERTIR_PORCENTUAL)){
            valor = Operaciones.multiplicar(valor,new BigDecimal("100"));
            tipo = Operaciones.EnumTipoNum.PORCENTUAL;
        }
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00",symbols);
        String numeroFormatoString = decimalFormat.format(valor);
        if(tipo.equals(Operaciones.EnumTipoNum.PORCENTUAL)){
            numeroFormatoString =  numeroFormatoString+"%";
        }

       /* if(comasComoSeparadorMiles){
            numeroFormatoString = stringToComasComoSeparadorDeMiles(numeroFormatoString);
        }*/
        return  numeroFormatoString;
    }



    public  String decimal3DecimToString(BigDecimal valor, Operaciones.EnumTipoNum tipo){
        if(valor==null){
            return "";
        }
        if(tipo.equals(Operaciones.EnumTipoNum.CONVERTIR_PORCENTUAL)){
            valor = Operaciones.multiplicar(valor,new BigDecimal("100"));
            tipo = Operaciones.EnumTipoNum.PORCENTUAL;
        }
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00#",symbols);
        String numeroFormatoString = decimalFormat.format(valor);
        if(tipo.equals(Operaciones.EnumTipoNum.PORCENTUAL)){
            numeroFormatoString =  numeroFormatoString+"%";
        }


        return  numeroFormatoString;
    }
    public  String decimalToStringEvaluacion(BigDecimal valor){
        if(valor==null){
            return "";
        }
        if(Operaciones.mayorQue(BigDecimal.ZERO,valor)){
            return "";
        }
        if(Operaciones.mayorQue(valor,new BigDecimal("1"))){
            return ">100%";
        }
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00",symbols);
        String numeroFormatoString = decimalFormat.format(Operaciones.multiplicar(valor,new BigDecimal(100)));

            numeroFormatoString =  numeroFormatoString+"%";


        return  numeroFormatoString;
    }
    public  String decimalToPorcentual100Decimal(BigDecimal valor){
        if(valor==null){
            return "";
        }
        if(Operaciones.mayorQue(BigDecimal.ZERO,valor)){
            return "";
        }
        valor = Operaciones.redondear(Operaciones.multiplicar(valor,new BigDecimal(100)),0);
        DecimalFormat decimalFormat = new DecimalFormat("#,##0",symbols);
        String numeroFormatoString = decimalFormat.format(valor);

        numeroFormatoString =  numeroFormatoString+"%";

        return  numeroFormatoString;
    }
    public  String decimalToEnteroPorcentual(BigDecimal valor){
        if(valor==null){
            return "";
        }
        if(Operaciones.mayorQue(BigDecimal.ZERO,valor)){
            return "";
        }
        DecimalFormat decimalFormat = new DecimalFormat("#");
        String numeroFormatoString = decimalFormat.format(Operaciones.multiplicar(valor,new BigDecimal(100)));
        return numeroFormatoString+"%";


    }

    public  String stringToComasComoSeparadorDeMiles(String valorNumeroString){
        return valorNumeroString.replace(".", "#").replace(",", ".").replace("#", ",");
    }


}
