package gob.mefp.reportes.app.reportes.service;

import gob.ypfb.nova.app.administracion.dto.query.QueryObtenerDatosCabeceraDto;
import gob.ypfb.nova.security.jwt.JwtServiceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
@Service
@Slf4j
public class DatosCabeceraService implements IDatosCabeceraService{
    @Autowired
    private JwtServiceContext jwtAuthenticationFilter;
    @Override
    public QueryObtenerDatosCabeceraDto obtenerDatosCabeceraDto(String pIdVersion, String pCodigoReporte, String pNombreSistema, String pTituloReporte, String pSubTituloReporte, String pDescripcion)
    {
        QueryObtenerDatosCabeceraDto vQueryObtenerDatosCabeceraDto=new QueryObtenerDatosCabeceraDto();
        vQueryObtenerDatosCabeceraDto.setCodigoReporte(pCodigoReporte);
        vQueryObtenerDatosCabeceraDto.setSistema(pNombreSistema);
        vQueryObtenerDatosCabeceraDto.setTitulo(pTituloReporte);
        vQueryObtenerDatosCabeceraDto.setSubTitulo(pSubTituloReporte);
        vQueryObtenerDatosCabeceraDto.setVersionPoa(pIdVersion);
        vQueryObtenerDatosCabeceraDto.setNombreUsuario(jwtAuthenticationFilter.getJwtTokenContextAplicacion().getUsername().toString());
        vQueryObtenerDatosCabeceraDto.setDescripcion(pDescripcion);
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        vQueryObtenerDatosCabeceraDto.setFechaImpresion(dateFormat.format(date));
        return vQueryObtenerDatosCabeceraDto;
    }
}
