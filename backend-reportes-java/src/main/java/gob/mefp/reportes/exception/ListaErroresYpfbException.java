/**
 * Project: nova
 * Package: bo.gob.ypfb.nova
 * El archivo "ListaErroresYpfbException.java" fue creado para el proyecto nova
 * por Y.P.F.B. Corporación, todos los Derechos reservados.
 *
 * @author: Roger Diego Flores Condori
 * @email : cons.rflores@ypfb.gob.bo
 * @date: 6/30/2023 1:12 p. m.
 * @copyright: YPFB
 * @version: 1.0
 **/
package gob.mefp.reportes.exception;
import java.util.List;
public class ListaErroresYpfbException extends  RuntimeException{
    public List<String> getListaErrores() {
        return listaErrores;
    }

    public void setListaErrores(List<String> listaErrores) {
        this.listaErrores = listaErrores;
    }

    private List<String> listaErrores;
    public ListaErroresYpfbException(List<String> listaErrores) {
        super("Lista de Errores");
        setListaErrores(listaErrores);
    }
}
