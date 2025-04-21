package gob.mefp.reportes.app.reportes.dto.response;

import lombok.Data;

@Data
public class QueryReportePorParametrosDto {
     private Long idVersion;
     private String accionCortoPlazo ;
     private String area;
     private String tipoTarea;
     private Double gastoCorriente;
     private Double inversion;
    private Double total;
}
