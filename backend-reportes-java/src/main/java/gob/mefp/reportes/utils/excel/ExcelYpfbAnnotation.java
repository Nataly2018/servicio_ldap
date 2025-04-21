/**
 * Project: nova
 * Package: bo.gob.ypfb.nova
 * El archivo "ExcelYpfbAnnotation.java" fue creado para el proyecto nova
 * por Y.P.F.B. Corporación, todos los Derechos reservados.
 *
 * @author: Roger Diego Flores Condori
 * @email : cons.rflores@ypfb.gob.bo
 * @date: 6/23/2023 4:19 p. m.
 * @copyright: YPFB
 * @version: 1.0
 **/

package gob.mefp.reportes.utils.excel;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelYpfbAnnotation {
    public int posicionColumna() default 0;
    public String tituloColumna() default "";
    public int cantidadDecimalesColumna() default 0;
    public boolean columnaOculta() default false;
    public boolean sololectura() default false;
    public boolean esTitulo() default false;
    public Class claseListado() default Object.class;
    public boolean tipoEstricto() default false;
    public String formatoRegex() default "";
    public boolean esFormula() default false;
    public String  ubicacionFormula() default "";
    public boolean valorONada() default false;
}
