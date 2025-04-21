package gob.mefp.reportes.app.reportes.dto.response;

import lombok.Data;

@Data
public class ListaOperacionesPorAcpDto {
    private String acp;
    private String tituloOperaciones;
    private Integer cantidad;
}
