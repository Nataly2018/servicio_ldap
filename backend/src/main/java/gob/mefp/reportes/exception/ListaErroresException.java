package gob.mefp.reportes.exception;
import java.util.List;
public class ListaErroresException extends  RuntimeException{
    public List<String> getListaErrores() {
        return listaErrores;
    }

    public void setListaErrores(List<String> listaErrores) {
        this.listaErrores = listaErrores;
    }

    private List<String> listaErrores;
    public ListaErroresException(List<String> listaErrores) {
        super("Lista de Errores");
        setListaErrores(listaErrores);
    }
}
