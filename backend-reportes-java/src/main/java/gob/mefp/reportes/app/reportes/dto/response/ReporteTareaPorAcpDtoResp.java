package gob.mefp.reportes.app.reportes.dto.response;

import lombok.Data;

@Data
public class ReporteTareaPorAcpDtoResp {
    private String[] acp ;
    private Integer[] cantidadTareas;
}
