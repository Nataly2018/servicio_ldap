package gob.mefp.reportes.app.reportes.dto.request;

import lombok.Data;

@Data
public class ReportePresupuestoPorAcpJsonDtoReq {
    private Long idVersion;
    private String idAcps;
    private String idTipoGasto;
    private String idTipoTarea;
}
