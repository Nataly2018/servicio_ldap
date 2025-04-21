package gob.mefp.reportes.app.reportes.dto.response;

import lombok.Data;

@Data
public class QueryReportePorParametrosListDto {
    private String[] acp ;
    private String[] area;
    private String[] tipoTarea;
    private Double[] gastoCorriente;
    private Double[] inversion;
    private Double[] total;
}
