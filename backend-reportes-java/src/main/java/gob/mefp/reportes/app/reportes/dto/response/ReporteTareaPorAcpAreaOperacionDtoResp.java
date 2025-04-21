package gob.mefp.reportes.app.reportes.dto.response;

import lombok.Data;

@Data
public class ReporteTareaPorAcpAreaOperacionDtoResp {
    private String[] acp ;
    private String[] area;
    private String[] codigoOperacion;
    private Integer[] cantidadTareas;
}
