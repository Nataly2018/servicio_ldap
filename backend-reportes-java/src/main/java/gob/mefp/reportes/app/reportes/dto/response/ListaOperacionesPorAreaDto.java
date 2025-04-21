package gob.mefp.reportes.app.reportes.dto.response;

import lombok.Data;

@Data
public class ListaOperacionesPorAreaDto {
    private String area;
    private String tituloOperaciones;
    private Integer cantidad;
}
