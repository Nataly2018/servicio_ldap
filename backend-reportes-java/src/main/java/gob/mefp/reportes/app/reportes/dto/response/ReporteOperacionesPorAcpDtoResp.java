package gob.mefp.reportes.app.reportes.dto.response;

import lombok.Data;

@Data
public class ReporteOperacionesPorAcpDtoResp {
    private String[] acp ;
    private Integer[] cantidadOperaciones;
}
