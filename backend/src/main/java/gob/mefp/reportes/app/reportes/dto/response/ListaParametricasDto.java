package gob.mefp.reportes.app.reportes.dto.response;

import lombok.Data;

@Data
public class ListaParametricasDto {
    public String getGrupoParametrica() {
        return grupoParametrica;
    }

    public String getParametrica() {
        return parametrica;
    }

    private String grupoParametrica ;

    public void setParametrica(String parametrica) {
        this.parametrica = parametrica;
    }

    public void setGrupoParametrica(String grupoParametrica) {
        this.grupoParametrica = grupoParametrica;
    }

    private String parametrica ;
}
