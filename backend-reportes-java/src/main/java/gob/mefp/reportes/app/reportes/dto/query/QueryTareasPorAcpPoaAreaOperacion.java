package gob.mefp.reportes.app.reportes.dto.query;

import lombok.Data;

@Data
public class QueryTareasPorAcpPoaAreaOperacion {
    private String codigoAcp;
    private String area;
    private String codigoOperacion;
    private Integer cantidadTareas;
}
