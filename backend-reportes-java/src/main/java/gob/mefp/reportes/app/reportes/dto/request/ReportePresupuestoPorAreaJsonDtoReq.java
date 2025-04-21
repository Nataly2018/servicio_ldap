package gob.mefp.reportes.app.reportes.dto.request;

import lombok.Data;

import java.util.List;
@Data
public class ReportePresupuestoPorAreaJsonDtoReq {
    private Long idVersion;
    private String idPoaArea;
    private String idTipoGasto;
    private String idTipoTarea;
}
