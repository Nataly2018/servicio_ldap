package gob.mefp.reportes.app.reportes.dto.query;

import lombok.Data;

@Data
public class QueryTareasPoraAcpPoaArea {
    private String codigoAcp;
    private String area;
    private Integer cantidadTareas;
}
