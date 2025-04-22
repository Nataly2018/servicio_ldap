/**
 * Project: reportes
 * Package: bo.gob.mefp.reportes
 * @author: Nataly Wendy Mamani Mamani
 * @version: 1.0
 **/
package gob.mefp.reportes.app.reportes.dto;

import lombok.Data;

@Data
public class ReporteGenericoResultanteDto<T extends Object> {
        private  T data;

        public void setData(T vLista) {
                data = vLista;
        }
}
