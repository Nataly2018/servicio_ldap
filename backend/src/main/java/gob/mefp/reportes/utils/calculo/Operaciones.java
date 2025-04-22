/**
 * Project: reportes
 * Package: bo.gob.mefp.reportes
 * @author: Nataly Wendy Mamani Mamani
 * @version: 1.0
 **/

package gob.mefp.reportes.utils.calculo;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Operaciones {
    public static final BigDecimal PORCENTAJE_100 = new BigDecimal(100);

    public enum EnumTipoNum {
        PORCENTUAL("PORCENTUAL"),
        CONVERTIR_PORCENTUAL("CONVERTIR_PORCENTUAL"),
        NUMERO("NUMERO");
        public final String nombre;
        EnumTipoNum(String nombre) {
            this.nombre = nombre;
        }
    }

//    public static String decimalToString(BigDecimal valor){
//        return decimalToString(valor,EnumTipoNum.NUMERO);
//    }
//    public static String decimalToString(BigDecimal valor,EnumTipoNum tipo){
//        if(valor==null){
//            return "";
//        }
//        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
//        String numeroFormatoString = decimalFormat.format(valor);
//        if(tipo.equals(EnumTipoNum.PORCENTUAL)){
//            numeroFormatoString =  numeroFormatoString+"%";
//        }
//        return  numeroFormatoString;
//    }
//
//    public static String stringToComasComoSeparadorDeMiles(String valorNumeroString){
//        return valorNumeroString.replace(".", "#").replace(",", ".").replace("#", ",");
//    }

    public static BigDecimal sumar(BigDecimal monto1,BigDecimal monto2){
        MathContext mc = new MathContext(15, RoundingMode.HALF_UP) ;
        BigDecimal total = new BigDecimal(0);
        total = total.add(monto1,mc);
        total = total.add(monto2,mc);
        return total;
    }

    public  static BigDecimal promedio(List<BigDecimal> montos){
        if(montos.isEmpty()){
            return BigDecimal.ZERO;
        }

        BigDecimal suma = sumarLista(montos);
        int cantidad = montos.size();

        return dividir(suma,new BigDecimal(cantidad));
    }


    public static BigDecimal restar(BigDecimal monto1,BigDecimal monto2){
        MathContext mc = new MathContext(15, RoundingMode.HALF_UP) ;
        BigDecimal total = new BigDecimal(0);
        total = monto1.subtract(monto2);
        return total;
    }
    public static BigDecimal multiplicar(BigDecimal monto1,BigDecimal monto2){
        if(monto1==null){
            monto1=BigDecimal.ZERO;
        }
        BigDecimal total;
        MathContext mc = new MathContext(15, RoundingMode.HALF_UP) ;
        total = monto1.multiply(monto2,mc);
        return total;
    }
    public static BigDecimal multiplicar(BigDecimal monto1,Integer monto2){
        BigDecimal monto2b = BigDecimal.valueOf(monto2);
        if(monto1==null){
            monto1=BigDecimal.ZERO;
        }
        BigDecimal total;
        MathContext mc = new MathContext(15, RoundingMode.HALF_UP) ;
        total = monto1.multiply(monto2b,mc);
        return total;
    }
    public static BigDecimal dividir(BigDecimal monto1,BigDecimal monto2){
        //System.out.println("Division = " + monto1.toPlainString() + "/" + monto2.toPlainString() );

        if(monto2.compareTo(BigDecimal.ZERO) == 0) return  BigDecimal.ZERO;
        if(monto1.compareTo(BigDecimal.ZERO) == 0) return  BigDecimal.ZERO;
        MathContext mc = new MathContext(15, RoundingMode.HALF_UP) ;
        BigDecimal total;
        total = monto1.divide(monto2,mc);
        return total;
    }
    public static BigDecimal sumarLista(List<BigDecimal> montos){
        BigDecimal total = new BigDecimal(0);
        for(BigDecimal vMonto :montos){
            if(vMonto!=null) {
                total = sumar(total, vMonto);
            }
        }
        return total;
    }

    public static BigDecimal redondear(BigDecimal valor,Integer cantidadDecimales){
        valor = valor.setScale(cantidadDecimales, RoundingMode.HALF_UP);
        return valor;
    }
    public static BigDecimal redondearValorAbsoluto(BigDecimal valor){
        valor = valor.setScale(0, RoundingMode.DOWN);
        return valor;
    }
    public static BigDecimal redondearDecimalDosDecimalesValorAbsoluto(BigDecimal valor){
        BigDecimal numero = decimalAPorcentualSinDecimalesValorAbsoluto(valor);
        return numero.movePointLeft(2);
    }
    public static BigDecimal decimalAPorcentualSinDecimalesValorAbsoluto(
            BigDecimal decimal

    ){
        BigDecimal resultado = Operaciones.multiplicar(decimal,PORCENTAJE_100);
        resultado = Operaciones.redondearValorAbsoluto(resultado);
        return resultado;
    }
    public static BigDecimal obtenerPorcentual (
            BigDecimal totalValor,
            BigDecimal porcentaje

    ){
        BigDecimal resultado = Operaciones.dividir(porcentaje,PORCENTAJE_100);
        resultado = Operaciones.multiplicar(totalValor,resultado);
        return resultado;
    }

    public static boolean mayorQue(BigDecimal primer , BigDecimal segundo){
        return primer.compareTo(segundo)==1;
    }
    public static boolean mayorIgual(BigDecimal primer , BigDecimal segundo){
        return primer.compareTo(segundo)==1 || primer.compareTo(segundo)==0 ;
    }
    public static boolean diferenteQue(BigDecimal primer , BigDecimal segundo){
        return primer.compareTo(segundo) != 0;
    }
    public static boolean igualQue(BigDecimal primer , BigDecimal segundo){
        return primer.compareTo(segundo) == 0;
    }
    public static String getFechaFormato(){
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return  dateFormat.format(date);
    }
    public static String margenTexto(String texto){
        if(!texto.endsWith(".")){
            texto = texto+".";
        }
        return "\n"+texto+"\n";
    }
    public static Boolean tieneValor(String texto){
        if(texto == null || texto.trim().equals("")){
            return false;
        }
        return true;
    }

    public static String limpiarSaltosLinea(String texto){
        String[] lineas = texto.split("\\r?\\n");

        // Utiliza un StringBuilder para recombinar las líneas no vacías
        StringBuilder resultado = new StringBuilder();

        for (String linea : lineas) {
            if (!linea.trim().isEmpty()) { // Verifica si la línea no está vacía
                resultado.append(linea).append("\n");
            }
        }

        // Elimina el último salto de línea agregado
        if (resultado.length() > 0) {
            resultado.setLength(resultado.length() - 1);
        }
        return resultado.toString();
    }
}
