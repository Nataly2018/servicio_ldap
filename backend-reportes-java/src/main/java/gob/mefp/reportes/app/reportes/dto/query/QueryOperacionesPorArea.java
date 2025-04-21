package gob.mefp.reportes.app.reportes.dto.query;

import lombok.Data;

@Data
public class QueryOperacionesPorArea {
    private String area;
    private Integer cantidadOperaciones;
}
