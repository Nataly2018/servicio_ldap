package gob.mefp.reportes.app.reportes.dto.response;

import lombok.Data;

@Data
public class ListaDto {
    private String nombre;
    private String edad;
    private String fechaNacimiento;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
