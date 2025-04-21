package gob.mefp.reportes.app.reportes.dto.response;

import lombok.Data;

@Data
public class ReporteCantidadTareasPorTipoDto {
    private String area;
    private int proyecto;
    private int actividad;
}
