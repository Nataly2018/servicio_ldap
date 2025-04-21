package gob.mefp.reportes.app.reportes.dto.response;

import lombok.Data;

@Data
public class ListaPresupuestoPorAcpDto {
    private String acp;
    private String tituloGastoCorriente;
    private Double gastoCorriente;
    private String tituloInversion;
    private Double inversion;
}
