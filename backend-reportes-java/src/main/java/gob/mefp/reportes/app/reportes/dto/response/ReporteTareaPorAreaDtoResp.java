package gob.mefp.reportes.app.reportes.dto.response;

import lombok.Data;

@Data
public class ReporteTareaPorAreaDtoResp {
    private String[] area ;
    private Integer[] cantidadTareas;
}
