package gob.mefp.reportes.config;

public class ConstantesExpresionRegular {
    public enum EnumLongitud {
        MAXIMO_DETALLE_GASTO(500),
        MINIMO_DETALLE_GASTO(5),
        MAXIMO_UNIDAD_MEDIDA(500),
        MINIMO_UNIDAD_MEDIDA(2),
        MAXIMO_OBJETIVO(1000),
        MINIMO_OBJETIVO(5),
        MAXIMO_PERFIL(1000),
        MINIMO_PERFIL(5),
        MAXIMO_RESULTADOS(1000),
        MINIMO_RESULTADOS(5),
        MAXIMO_ACTIVIDADES(1000),
        MINIMO_ACTIVIDADES(5);
        public final Integer valor;
        EnumLongitud(Integer valor) {
            this.valor = valor;
        }
    }
}
