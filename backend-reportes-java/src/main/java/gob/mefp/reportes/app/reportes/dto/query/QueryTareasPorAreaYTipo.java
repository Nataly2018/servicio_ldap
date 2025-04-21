package gob.mefp.reportes.app.reportes.dto.query;

import lombok.Data;

@Data
public class QueryTareasPorAreaYTipo {
    private String area;
    private int proyecto;
    private int actividad;
}
