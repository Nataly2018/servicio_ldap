package gob.mefp.reportes.app.reportes.dto.response;

import lombok.Data;

@Data
public class ReporteTareaPorAcpAreaDtoResp {
    private String[] acp ;
    private String[] area;
    private Integer[] cantidadTareas;
}
