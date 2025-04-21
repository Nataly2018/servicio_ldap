package gob.mefp.reportes.app.reportes.dto.query;

import lombok.Data;

@Data
public class QueryOperacionesPorAcp {
    private String codigoAcp;
    private Integer cantidadOperaciones;
}
