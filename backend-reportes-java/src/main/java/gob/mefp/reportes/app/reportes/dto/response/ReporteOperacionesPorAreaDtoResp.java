package gob.mefp.reportes.app.reportes.dto.response;

import lombok.Data;

@Data
public class ReporteOperacionesPorAreaDtoResp {
    private String[] area ;
    private Integer[] cantidadOperaciones;
}
