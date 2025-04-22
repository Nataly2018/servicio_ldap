/**
 * Project: reportes
 * Package: bo.gob.mefp.reportes
 * @author: Nataly Wendy Mamani Mamani
 * @version: 1.0
 **/

package gob.mefp.reportes.utils.calculo;

import java.time.Duration;
import java.time.Instant;

public class UtilTiempos {
    Instant inicio;
    Instant ultimohito;
    String alias;
    public UtilTiempos(String alias){
        this.alias=alias;
    }
    public void inicio(){
        inicio = Instant.now();
        ultimohito= inicio;
    }
    public void fin(String alias){
        this.alias=alias;
        Instant fin = Instant.now();
        Duration tiempoTranscurrido = Duration.between(inicio, fin);
        System.out.println("Tiempo General : "+alias +" milisengundos "+tiempoTranscurrido.toMillis()+ "segundos: "+tiempoTranscurrido.getSeconds());
        tiempoTranscurrido = Duration.between(ultimohito, fin);
        System.out.println("Tiempo especifico : "+alias +" milisengundos "+tiempoTranscurrido.toMillis()+ "segundos: "+tiempoTranscurrido.getSeconds());
        System.out.println("-----------------------------------------------------------------");
        ultimohito = fin;
    }
}
