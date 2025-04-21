package gob.mefp.reportes.app.reportes.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class ReporteGeneralPorParametrosDtoReq {
    private Long idVersion;
    private List<Long> idAccionCortoPlazo;
    private List<Long> idPoaArea;
    private List<Long> idTipoGasto;
    private List<Long> idTipoTarea;
}
