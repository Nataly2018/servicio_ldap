/**
 * Project: nova
 * Package: bo.gob.ypfb.nova
 * El archivo "DocumentoExcelYpfbUtil.java" fue creado para el proyecto nova
 * por Y.P.F.B. Corporación, todos los Derechos reservados.
 *
 * @author: Roger Diego Flores Condori
 * @email : cons.rflores@ypfb.gob.bo
 * @date: 6/23/2023 4:14 p. m.
 * @copyright: YPFB
 * @version: 1.0
 **/
package gob.mefp.reportes.utils.excel;

import gob.ypfb.nova.exception.ErrorValidacionException;
import gob.ypfb.nova.exception.IntegridadYpfbException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocumentoExcelYpfbUtil2 {
    private CellStyle styleTextoEdicion;
    private CellStyle styleTextoEdicionTexto;
    private CellStyle styleTextoLectura;
    private CellStyle styleTitulo;
    private CellStyle styleTituloAzul;
    private CellStyle styleTituloVerde;
    private CellStyle styleTituloAmarillo;
    DataFormat dataFormatTexto;

    public static final String CARACTER_FILA_ADICIONAL = "?F?";
    public static final String FORMULA_SUMA= "SUM(%s:%s)";

    private XSSFWorkbook archivExcel;

    private Map<Integer, CellStyle> styleNumericos;

    public String getNombreSeccionExcel() {
        return nombreSeccionExcel;
    }

    public void setNombreSeccionExcel(String nombreSeccionExcel) {
        this.nombreSeccionExcel = nombreSeccionExcel;
    }

    private String nombreSeccionExcel;

    public List<String> getListaErrores() {
        return listaErrores;
    }

    public void setListaErrores(List<String> listaErrores) {
        this.listaErrores = listaErrores;
    }

    private List<String> listaErrores;

    private Map<String, List<Integer>> dimensiones;

    public DocumentoExcelYpfbUtil2() {
        listaErrores = new ArrayList<>();
        dimensiones = new HashMap<>();
    }

    public void ObtenerArchivoFromMultipart(MultipartFile file) {
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            archivExcel = new XSSFWorkbook(inputStream);
        } catch (Exception e) {
            throw new IntegridadYpfbException(String.format("No fue posible abrir el archivo excel, revise que sea un documento de extensión 'xlsx' valido"),e);
        }

    }

    public void ObtenerArchivoXls(String pathExcel) {
        try {

            ClassPathResource classPathResource = new ClassPathResource(pathExcel);
            InputStream in = classPathResource.getInputStream();
            archivExcel = new XSSFWorkbook(in);
            styleTextoEdicion = archivExcel.createCellStyle();
            styleTextoEdicion.setBorderBottom(BorderStyle.THIN);
            styleTextoEdicion.setBorderTop(BorderStyle.THIN);
            styleTextoEdicion.setBorderRight(BorderStyle.THIN);
            styleTextoEdicion.setBorderLeft(BorderStyle.THIN);

            //Palette palette = archivExcel.getCreationHelper().getCustomPalette();
            styleTextoEdicionTexto = archivExcel.createCellStyle();
            styleTextoEdicionTexto.cloneStyleFrom(styleTextoEdicion);
            DataFormat fmt = archivExcel.createDataFormat();
            styleTextoEdicionTexto.setDataFormat(fmt.getFormat("@"));

            styleTextoLectura = archivExcel.createCellStyle();
            styleTextoLectura.cloneStyleFrom(styleTextoEdicion);
            styleTextoLectura.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            styleTextoLectura.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());


            Font font = archivExcel.createFont();
            font.setColor(IndexedColors.WHITE.getIndex());


            styleTituloAzul = archivExcel.createCellStyle();
            styleTituloAzul.cloneStyleFrom(styleTextoEdicion);
            styleTituloAzul.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            styleTituloAzul.setFillForegroundColor(IndexedColors.ROYAL_BLUE.getIndex());
            styleTituloAzul.setFont(font);

            styleTituloVerde = archivExcel.createCellStyle();
            styleTituloVerde.cloneStyleFrom(styleTextoEdicion);
            styleTituloVerde.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            styleTituloVerde.setFillForegroundColor(IndexedColors.GREEN.getIndex());
            styleTituloVerde.setFont(font);

            Font font2 = archivExcel.createFont();
            font2.setColor(IndexedColors.LIGHT_ORANGE.getIndex());
            styleTituloAmarillo = archivExcel.createCellStyle();
            styleTituloAmarillo.setBorderBottom(BorderStyle.THIN);
            styleTituloAmarillo.setBorderTop(BorderStyle.THIN);
            styleTituloAmarillo.setBorderRight(BorderStyle.THIN);
            styleTituloAmarillo.setBorderLeft(BorderStyle.THIN);
            styleTituloAmarillo.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            styleTituloAmarillo.setFillForegroundColor(IndexedColors.LEMON_CHIFFON.getIndex());
            styleTituloAmarillo.setFont(font2);

            styleTitulo = styleTituloAzul;
            styleNumericos = new HashMap<Integer, CellStyle>();
            CellStyle styleCeldaNumericoSimple = archivExcel.createCellStyle();
            styleCeldaNumericoSimple.cloneStyleFrom(styleTextoEdicion);
            styleNumericos.put(0, styleCeldaNumericoSimple);

            for (int contador = 1; contador <= 20; contador++) {
                CellStyle styleCeldaNumerico = archivExcel.createCellStyle();
                styleCeldaNumerico.cloneStyleFrom(styleTextoEdicion);
                String decimal = new String(new char[contador]).replace("\0", "0");
                styleCeldaNumerico.setDataFormat(archivExcel.createDataFormat().getFormat("####0." + decimal));
                styleNumericos.put(contador, styleCeldaNumerico);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void obtenerEstiloPatron(XSSFSheet vXSSFSheet,String cellReference){
       CellReference cellRef = new CellReference(cellReference);
        XSSFRow row = vXSSFSheet.getRow(cellRef.getRow()); // Obtiene la fila correspondiente
        XSSFCell cell = row.getCell(cellRef.getCol()); // Obtiene la celda correspondiente
        this.styleTextoEdicion= cell.getCellStyle();
    }

    public void EstablecerTituloAzul() {
        styleTitulo = styleTituloAzul;
    }

    public void EstablecerTituloVerde() {
        styleTitulo = styleTituloVerde;
    }

    public XSSFSheet ObtenerHojaXls(String nombreSeccion) {
        XSSFSheet sheet = archivExcel.getSheet(nombreSeccion);
        if(sheet == null ){
            throw new ErrorValidacionException(String.format("No fue posible abrir la hoja excel: %s, verifique que sea el archivo correcto", nombreSeccion));
        }
        nombreSeccionExcel = nombreSeccion;
        return sheet;
    }
    public void evaluarFormulasExcel(){
        XSSFFormulaEvaluator.evaluateAllFormulaCells(archivExcel);
    }

    public void grabarAnchosOriginales(XSSFSheet vXSSFSheet){
        List<Integer> anchos = new ArrayList<>();
        for (int i = 0; i < vXSSFSheet.getRow(0).getLastCellNum(); i++) {
            anchos.add(vXSSFSheet.getColumnWidth(i));
        }
        dimensiones.put(vXSSFSheet.getSheetName(),anchos);
    }
    public void ajustarColumnasExcel(XSSFSheet vXSSFSheet){
        CellStyle style = archivExcel.createCellStyle();
        List<Integer> anchos = dimensiones.get(vXSSFSheet.getSheetName());
        style.setWrapText(true);
        for (int i = 0; i < anchos.size(); i++) {
            vXSSFSheet.setColumnWidth(i, anchos.get(i));
            vXSSFSheet.setDefaultColumnStyle(i, style);
        }
    }
    public void escribirExcelRequest(HttpServletResponse response, String nombreExcelDescarga) {
        try {
            response.setContentType("application/octet-stream");
            response.setHeader("x-suggested-filename",
                    java.net.URLEncoder.encode(nombreExcelDescarga, "UTF-8"));
            response.setHeader("Content-disposition",
                    "attachment;filename=" + java.net.URLEncoder.encode(nombreExcelDescarga, "UTF-8"));
            response.flushBuffer();
            ServletOutputStream out = response.getOutputStream();
            archivExcel.write(out);
            out.flush();
            out.close();
        } catch (IOException e) {
            //e.printStackTrace();
            //excepcion
        }
    }

    public <T extends Object> void EscribirCeldaTextoEnExcel(
            XSSFRow vFilaExcel,
            Integer pColumna, Object campo, Boolean pSoloLectura) {
        EscribirCeldaEnExcel(vFilaExcel, pColumna, campo, 0, pSoloLectura);
    }

    public <T extends Object> void EscribirCeldaTituloEnExcel(
            XSSFRow vFilaExcel,
            Integer pColumna, Object campo, Boolean pSoloLectura) {
        EscribirCeldaEnExcel(vFilaExcel, pColumna, campo, 0, pSoloLectura, true);//chekar
    }

    public <T extends Object> void EscribirCeldaDecimalEnExcel(
            XSSFRow vFilaExcel,
            Integer pColumna, Object campo, Integer cantidadDecimalesColumna) {
        EscribirCeldaEnExcel(vFilaExcel, pColumna, campo, cantidadDecimalesColumna, false);
    }

    public <T extends Object> void EscribirCeldaDecimalIndependienteEnExcel(
            XSSFSheet vHoja,
            Integer pFila,
            Integer pColumna, Object campo, Integer cantidadDecimalesColumna) {
        pFila--;
        pColumna--;
        XSSFRow vFilaExcel = vHoja.getRow(pFila);
        if (vFilaExcel == null) {
            vFilaExcel = vHoja.createRow(pFila);
        }
        EscribirCeldaDecimalEnExcel(vFilaExcel, pColumna, campo, cantidadDecimalesColumna);
    }

    public <T extends Object> void EscribirCeldaTextoIndependienteEnExcel(
            XSSFSheet vHoja,
            Integer pFila,
            Integer pColumna, Object campo, Boolean pSoloLectura) {
        pFila--;
        pColumna--;
        XSSFRow vFilaExcel = vHoja.getRow(pFila);
        if (vFilaExcel == null) {
            vFilaExcel = vHoja.createRow(pFila);
        }
        EscribirCeldaTextoEnExcel(vFilaExcel, pColumna, campo, pSoloLectura);
    }

    public String ObtenerCeldaTextoIndependienteEnExcel(
            XSSFSheet vHoja,
            Integer pFila,
            Integer pColumna) {
        pFila--;
        pColumna--;
        XSSFRow vFilaExcel = vHoja.getRow(pFila);
        if (vFilaExcel == null) {
            return null;
        }
        return vFilaExcel.getCell(pColumna, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
    }

    public <T extends Object> void EscribirCeldaEnExcel(
            XSSFRow vFilaExcel,
            Integer pColumna, Object campo, Integer cantidadDecimalesColumna, Boolean pSoloLectura) {
        EscribirCeldaEnExcel(
                vFilaExcel,
                pColumna, campo, cantidadDecimalesColumna, pSoloLectura, false);

    }

    public <T extends Object> void EscribirCeldaEnExcel(
            XSSFRow vFilaExcel,
            Integer pColumna, Object campo, Integer cantidadDecimalesColumna, Boolean pSoloLectura, Boolean pEsTitulo) {

        CellStyle styleCelda = styleTextoEdicion;
        if (pSoloLectura) {
            styleCelda = styleTextoLectura;
        }
        if (pEsTitulo) {
            styleCelda = styleTitulo;
        }
        if (campo instanceof Long) {
            XSSFCell celda = vFilaExcel.createCell(pColumna, CellType.STRING);
            celda.setCellStyle(styleCelda);
            celda.setCellValue(((Long) campo).toString());
        } else if (campo instanceof Integer) {
            XSSFCell celda = vFilaExcel.createCell(pColumna, CellType.STRING);
            celda.setCellStyle(styleCelda);
            celda.setCellValue(((Integer) campo));
        } else if (campo instanceof BigDecimal) {
            String strNumero = campo.toString();
            strNumero = strNumero.replace(".","");
            Boolean esMayorAUno = (((BigDecimal) campo).compareTo(BigDecimal.ONE) > 0 );
            if ((strNumero.length() > 15) && esMayorAUno){
                XSSFCell celda = vFilaExcel.createCell(pColumna, CellType.STRING);
                strNumero = campo.toString();
                strNumero = strNumero.replace(".",",");
                celda.setCellValue(strNumero);
            } else {
                String string = ((BigDecimal) campo).stripTrailingZeros().toPlainString();
                int index = string.indexOf(".");
                Integer cantidadDecimales = index < 0 ? 0 : string.length() - index - 1;
                if(cantidadDecimales<cantidadDecimalesColumna){
                    cantidadDecimales=cantidadDecimalesColumna;
                }
                styleCelda = styleNumericos.get(cantidadDecimales);
                XSSFCell celda = vFilaExcel.createCell(pColumna, CellType.NUMERIC);
                celda.setCellStyle(styleCelda);
                celda.setCellValue(((BigDecimal) campo).doubleValue());
            }
        } else {
            XSSFCell celda = vFilaExcel.createCell(pColumna, CellType.STRING);
            //celda.getCellStyle()
            //XSSFCell celda = vFilaExcel.getCell(pColumna);
            celda.setCellStyle(styleCelda);

            if(!pSoloLectura && !pEsTitulo ){
                celda.setCellStyle(styleTextoEdicionTexto);
            }
            celda.setCellValue((String) campo);
            //celda.getRow().setHeight((short) -1);
        }
    }

    public <T extends Object> List<ExcelYpfbArrDescriptivo> extraerInformacionNotaciones(Class<T> clase) {
        Field[] fields = clase.getDeclaredFields();
        ExcelYpfbAnnotation vExcelYpfbAnnotation;
        List<ExcelYpfbArrDescriptivo> vListaCamposDatos = new ArrayList<>();
        int posicionColumna = 1;
        for (Field f : fields) {
            f.setAccessible(true);
            if (f.isAnnotationPresent(ExcelYpfbAnnotation.class)) {
                vExcelYpfbAnnotation = f.getAnnotation(ExcelYpfbAnnotation.class);
                posicionColumna = vExcelYpfbAnnotation.posicionColumna();
                //XSSFCell celda = row.createCell(posicionColumna, CellType.STRING);
                //celda.setCellValue(vExcelYpfbAnnotation.tituloColumna());
                //celda.setCellStyle(styleCabecera);
                ExcelYpfbArrDescriptivo vExcelYpfbArrDescriptivo = new ExcelYpfbArrDescriptivo();
                vExcelYpfbArrDescriptivo.setPosicionColumna(posicionColumna);
                vExcelYpfbArrDescriptivo.setTituloColumna(vExcelYpfbAnnotation.tituloColumna());
                vExcelYpfbArrDescriptivo.setVField(f);
                vExcelYpfbArrDescriptivo.setCantidadDecimalesColumna(vExcelYpfbAnnotation.cantidadDecimalesColumna());
                vExcelYpfbArrDescriptivo.setColumnaOculta(vExcelYpfbAnnotation.columnaOculta());
                vExcelYpfbArrDescriptivo.setSoloLectura(vExcelYpfbAnnotation.sololectura());
                vExcelYpfbArrDescriptivo.setClaseListado(vExcelYpfbAnnotation.claseListado());
                vExcelYpfbArrDescriptivo.setEsTitulo(vExcelYpfbAnnotation.esTitulo());
                vExcelYpfbArrDescriptivo.setTipoEstricto(vExcelYpfbAnnotation.tipoEstricto());
                vExcelYpfbArrDescriptivo.setFormatoRegex(vExcelYpfbAnnotation.formatoRegex());
                vExcelYpfbArrDescriptivo.setEsFormula(vExcelYpfbAnnotation.esFormula());
                vExcelYpfbArrDescriptivo.setUbicacionFormula(vExcelYpfbAnnotation.ubicacionFormula());
                vExcelYpfbArrDescriptivo.setValorONada(vExcelYpfbAnnotation.valorONada());
                vListaCamposDatos.add(vExcelYpfbArrDescriptivo);
            }
        }
        return vListaCamposDatos;
    }

    public <T extends Object> void EscribirListaEnExcel(
            XSSFSheet vHoja, Integer pFilaInicio, Integer columnainicio, List<T> listaEscribir, Class<T> clase) {
        EscribirListaEnExcel(vHoja, pFilaInicio, columnainicio, listaEscribir, clase, true, false);
    }

    public <T extends Object> void EscribirListaAnidadaEnExcel(
            XSSFSheet vHoja, Integer pFilaInicio, Integer columnainicio, List<T> listaEscribir, Class<T> clase) {
        EscribirListaEnExcel(vHoja, pFilaInicio, columnainicio, listaEscribir, clase, false, true);
    }

    public <T extends Object> void EscribirListaEnExcel(
            XSSFSheet vHoja, Integer pFilaInicio, Integer columnainicio, List<T> listaEscribir, Class<T> clase,
            Boolean vColumnaContador, Boolean vFilaExistente) {
        columnainicio--;
        List<ExcelYpfbArrDescriptivo> vListaCamposDatos = extraerInformacionNotaciones(clase);
        int contadorFila = 0;
        pFilaInicio--;
        if (!vColumnaContador) {
            columnainicio--;
        }
        if(vHoja.getSheetName().equals("PRESUPUESTO")){
            //System.out.println("aqui");
            CellReference cellReference = new CellReference("X14");
            XSSFRow row = vHoja.getRow(cellReference.getRow());
            XSSFCell cell = row.getCell(cellReference.getCol());
        }
        try {
            for (T vObjeto : listaEscribir) {
                contadorFila++;
                XSSFRow vFilaExcel;
                if (vFilaExistente) {
                    vFilaExcel = vHoja.getRow(pFilaInicio);
                } else {
                    vFilaExcel = vHoja.createRow(pFilaInicio);
                }

                if (vColumnaContador) {
                    EscribirCeldaTextoEnExcel(vFilaExcel, columnainicio, contadorFila, true);
                }
                pFilaInicio++;
                for (ExcelYpfbArrDescriptivo vExcelYpfbArrDescriptivo : vListaCamposDatos) {
                    Object campo = vExcelYpfbArrDescriptivo.getVField().get(vObjeto);
                    //si es una lista
                    //llamar a un metodo que convierta un listado a columnas

                    if(vExcelYpfbArrDescriptivo.getValorONada() && campo==null){
                        continue;
                    }

                    if (!vExcelYpfbArrDescriptivo.getClaseListado().equals(Object.class)) {
                        EscribirListaComoColumnasEnExcel(
                                vHoja, vFilaExcel, columnainicio + vListaCamposDatos.size(), (List) campo, vExcelYpfbArrDescriptivo.getClaseListado());
                    } else if (vExcelYpfbArrDescriptivo.getEsTitulo()) {
                        EscribirCeldaTituloEnExcel(
                                vFilaExcel, vExcelYpfbArrDescriptivo.getPosicionColumna() + columnainicio,
                                campo, vExcelYpfbArrDescriptivo.getSoloLectura());
                        if (campo == null || ((String) campo).isEmpty()) {
                            vHoja.setColumnHidden(vExcelYpfbArrDescriptivo.getPosicionColumna() + columnainicio, true);
                        }
                    }
                    else if (vExcelYpfbArrDescriptivo.getEsFormula()) {
                        insertarFormulaCeldaPatron(vHoja,vFilaExcel,
                                vExcelYpfbArrDescriptivo.getPosicionColumna() + columnainicio,vExcelYpfbArrDescriptivo.getUbicacionFormula());
                    }

                    else if (vExcelYpfbArrDescriptivo.getCantidadDecimalesColumna() == 0) {
                        EscribirCeldaTextoEnExcel(
                                vFilaExcel, vExcelYpfbArrDescriptivo.getPosicionColumna() + columnainicio,
                                campo, vExcelYpfbArrDescriptivo.getSoloLectura());
                    } else {
                        EscribirCeldaDecimalEnExcel(
                                vFilaExcel, vExcelYpfbArrDescriptivo.getPosicionColumna() + columnainicio,
                                campo, vExcelYpfbArrDescriptivo.getCantidadDecimalesColumna());
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        for (ExcelYpfbArrDescriptivo vExcelYpfbArrDescriptivo : vListaCamposDatos) {
            vHoja.autoSizeColumn(vExcelYpfbArrDescriptivo.getPosicionColumna() + columnainicio);
            if (vExcelYpfbArrDescriptivo.getColumnaOculta()) {
                vHoja.setColumnHidden(vExcelYpfbArrDescriptivo.getPosicionColumna() + columnainicio, vExcelYpfbArrDescriptivo.getColumnaOculta());
            }
        }
    }

    /*
     * metodo que convierta un listado a columnas
     * recibir listado, fila , columna
     * foreach del listado , mandar cada objeto para escribir en la fila en la columna siguiente
     * extraer informacion de los campos
     * mandar a escribir un objeto en una fila
     * */
    public <T extends Object> void EscribirListaComoColumnasEnExcel(
            XSSFSheet vHoja, XSSFRow vFilaExcel, Integer columnainicio, List<T> listaEscribir, Class<T> clase) {
        List<ExcelYpfbArrDescriptivo> vListaCamposDatos = extraerInformacionNotaciones(clase);

        Integer vColumnaEscritura = columnainicio;
        vColumnaEscritura--;
        try {
            for (T vObjeto : listaEscribir) {
                for (ExcelYpfbArrDescriptivo vExcelYpfbArrDescriptivo : vListaCamposDatos) {
                    Object campo = vExcelYpfbArrDescriptivo.getVField().get(vObjeto);

                    if (vExcelYpfbArrDescriptivo.getCantidadDecimalesColumna() == 0) {
                        EscribirCeldaTextoEnExcel(
                                vFilaExcel, vExcelYpfbArrDescriptivo.getPosicionColumna() + vColumnaEscritura,
                                campo, vExcelYpfbArrDescriptivo.getSoloLectura());
                    } else {
                        EscribirCeldaDecimalEnExcel(
                                vFilaExcel, vExcelYpfbArrDescriptivo.getPosicionColumna() + vColumnaEscritura,
                                campo, vExcelYpfbArrDescriptivo.getCantidadDecimalesColumna());
                    }

                    vHoja.autoSizeColumn(vExcelYpfbArrDescriptivo.getPosicionColumna() + vColumnaEscritura);
                    vHoja.setColumnHidden(vExcelYpfbArrDescriptivo.getPosicionColumna() + vColumnaEscritura, vExcelYpfbArrDescriptivo.getColumnaOculta());
                }
                vColumnaEscritura += vListaCamposDatos.size();

            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }

    public void OcultarColumnaEspecifica(XSSFSheet vHoja, Integer columna) {
        columna--;
        vHoja.setColumnHidden(columna, true);
    }

    public static Workbook ObtenerXlsFromArchivo(InputStream pInputArchivo) {
        Workbook archivExcel = null;
        try {
            archivExcel = new XSSFWorkbook(pInputArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return archivExcel;
    }

    //XSSFSheet vHoja,    XSSFRow vFilaExcel, Integer columnainicio, List<T> listaEscribir, Class<T> clase
    public <T extends Object> List<T> leerCuerpoExcel(
            XSSFSheet vHoja, Integer pFilaInicio, Integer columnainicio, Class<T> clase) {
        return
                leerCuerpoExcel(
                        vHoja, pFilaInicio, columnainicio, clase, 0);
    }

    public <T extends Object> List<T> leerCuerpoExcel(
            XSSFSheet vHoja, Integer pFilaInicio, Integer columnainicio, Class<T> clase, Integer cantidadFilasExcel) {
        T obj = null;
        List<T> listaResultados = new ArrayList<>();
        List<ExcelYpfbArrDescriptivo> vListaCamposDatos = extraerInformacionNotaciones(clase);

        if (cantidadFilasExcel == 0) {
            cantidadFilasExcel = vHoja.getLastRowNum() + 1;
        }else{
            cantidadFilasExcel=cantidadFilasExcel+pFilaInicio-1;
        }

        //saltando cabeceras
        pFilaInicio--;
        columnainicio--;
        XSSFRow vFilaExcel;
        XSSFCell celda = null;
        String tipoCasteoCampo = "";
        Boolean esFilaConValores = false;
        try {
            for (int i = pFilaInicio; i < cantidadFilasExcel; i++) {
                vFilaExcel = vHoja.getRow(i);
                celda = vFilaExcel.getCell(0);
//                if(celda!=null) {
//                    String valorColumnaMarca = celda.getStringCellValue();
//                    if (valorColumnaMarca.equals(CARACTER_FILA_ADICIONAL)) {
//                        continue;
//                    }
//                }
                obj = clase.getDeclaredConstructor().newInstance();
                esFilaConValores = false;
                List<String> listaErroresFila = new ArrayList<>();
                for (ExcelYpfbArrDescriptivo vExcelYpfbArrDescriptivo : vListaCamposDatos) {
                    celda = vFilaExcel.getCell(columnainicio + vExcelYpfbArrDescriptivo.getPosicionColumna(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                    CellType tipoCelda = celda.getCellType();
                    String formato = celda.getCellStyle().getDataFormatString();
                    //System.out.println(xx);

                    //celda.setCellType(CellType.STRING);
                    Object campo = vExcelYpfbArrDescriptivo.getVField().getType();
                    //DataFormatter formatter = new DataFormatter();
                    //String valorCampo2 = formatter.formatCellValue(celda);
                    //String valorCampo = celda.getStringCellValue();
                    String valorCampo = obtenerValor(celda,campo);
                    if (valorCampo != null && !valorCampo.isEmpty()) {
                        esFilaConValores = true;
                    }
                    try {

                        if (campo == Long.class) {
                            tipoCasteoCampo = " debe ser un numero";
                            vExcelYpfbArrDescriptivo.getVField().set(obj, Long.valueOf(valorCampo));
                        } else if (campo == Integer.class) {
                            tipoCasteoCampo = " debe ser un numero Entero";

                            vExcelYpfbArrDescriptivo.getVField().set(obj, Integer.valueOf(valorCampo));
                        } else if (campo == BigDecimal.class) {
                            tipoCasteoCampo = " debe ser un numero Decimal";
                            valorCampo = valorCampo.trim();
                            valorCampo = valorCampo.replace(",",".");
                            //if(valorCampo.isEmpty()){
                            //    valorCampo="0";
                            //}
                            //BigDecimal uno = BigDecimal.valueOf(Double.valueOf(valorCampo));
                            BigDecimal casteandoNumeroDecimal = new BigDecimal(valorCampo);
//                            if(casteandoNumeroDecimal.compareTo(new BigDecimal("99999999.9"))==1){
//                                tipoCasteoCampo = " debe ser un numero Decimal menor a 100 millones";
//                                throw new ErrorValidacionException("error de numeric");
//                            }

                            vExcelYpfbArrDescriptivo.getVField().set(obj, casteandoNumeroDecimal);
                        } else if (campo == String.class && vExcelYpfbArrDescriptivo.getTipoEstricto()){
                            tipoCasteoCampo = " debe ser un texto";
                            if(!formato.equals("@")){
                                throw new ErrorValidacionException("error de numeric, se esperaba un texto");
                            }
                            vExcelYpfbArrDescriptivo.getVField().set(obj, valorCampo);

                        } else if (campo == String.class && !vExcelYpfbArrDescriptivo.getFormatoRegex().equals("")){
                            tipoCasteoCampo = " no cumple el formato solicitado ";
                            Pattern patron = Pattern.compile(vExcelYpfbArrDescriptivo.getFormatoRegex());
                            Matcher vMatch = patron.matcher(valorCampo);
                            if(!vMatch.matches()){
                                throw new ErrorValidacionException("error de formato");
                            }
                            vExcelYpfbArrDescriptivo.getVField().set(obj, valorCampo);
                        }
                        else{
                            vExcelYpfbArrDescriptivo.getVField().set(obj, valorCampo);
                        }
                    }
                    catch(NumberFormatException exp){
                        if(vExcelYpfbArrDescriptivo.getValorONada() && valorCampo.equals("")){
                            vExcelYpfbArrDescriptivo.getVField().set(obj,null);
                        }else{
                            listaErroresFila.add("En la Hoja "+nombreSeccionExcel+" la celda '"+celda.getAddress().formatAsString() + "' "+tipoCasteoCampo);
                        }
                    }
                    catch (Exception exp){
                        listaErroresFila.add("En la Hoja "+nombreSeccionExcel+" la celda '"+celda.getAddress().formatAsString() + "' "+tipoCasteoCampo);
                    }
                }
                if (esFilaConValores) {

                    listaErrores.addAll(listaErroresFila);
                    listaResultados.add(obj);
                }
            }
            return listaResultados;
        } catch (Exception e) {
            String errorGeneral = "Excepcion en la lectura Excel";
            if (celda != null) {
                errorGeneral+=" en la celda: " + celda.getAddress().formatAsString();
            }
            throw new IntegridadYpfbException(errorGeneral,e);
        }
    }





    public String obtenerValor(Cell pCelda,Object tipoEsperado) {
        String valor = "";
        switch (pCelda.getCellType()) {
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(pCelda)) {
                    Format formatter = new SimpleDateFormat("dd/MM/yyyy");
                    valor = formatter.format(pCelda.getDateCellValue());
                } else {

                    Double valorDouble = pCelda.getNumericCellValue();
                    valor = NumberToTextConverter.toText(valorDouble);
                    if(tipoEsperado.equals(String.class)){
                        valor = valor.replace(".",",");
                    }
                }
                break;
            case STRING:
                valor = pCelda.getRichStringCellValue().getString().trim();
                break;
            case FORMULA:
                DataFormatter dataFormatter = new DataFormatter();
                valor = dataFormatter.formatCellValue(pCelda);
                break;
            case BLANK:
                valor = "";
                break;
            case BOOLEAN:
                valor = Boolean.toString(pCelda.getBooleanCellValue());
                break;
            case ERROR:
                break;
            default:
                throw new IllegalArgumentException("Tipo de Celada Invalida " + pCelda.getCellType());
        }
        return valor;
    }




    public void aplicarFormatoAdicional(ExcelDatosFormatoAdicional pExcelDatosFormatoAdicional,XSSFSheet vHoja){
        Integer pFilaInicio = pExcelDatosFormatoAdicional.getFilaInicio()-1;
        XSSFRow vFilaExcel;
        XSSFCell celda = null;
        Boolean blnFinalizado = false;
        Integer contador = pFilaInicio;
        String valorAgrupador = "";
        Boolean blnPrimerRegistro=true;

        Integer filaSumaInicial=0;
        Integer filaSumaFinal=0;

        while (Boolean.FALSE.equals(blnFinalizado)){
            vFilaExcel = vHoja.getRow(contador);
            if(vFilaExcel == null){
                blnFinalizado=true;
                continue;
            }
            celda= vFilaExcel.getCell(CellReference.convertColStringToIndex(pExcelDatosFormatoAdicional.getColumnaAgrupadora()));
            String valorCampo = celda.getStringCellValue();
            if(Boolean.FALSE.equals(valorAgrupador.equals(valorCampo))){
                valorAgrupador= valorCampo;
                if(!blnPrimerRegistro) {
                    for (int pie = pExcelDatosFormatoAdicional.getCantidadFilaPie(); pie >=1 ; pie--) {
                        insertarUnaFila(vHoja, contador,
                                pExcelDatosFormatoAdicional.getColumnaInicial(),
                                pExcelDatosFormatoAdicional.getColumnaFinal(),
                                pExcelDatosFormatoAdicional.getColoresPie()[pie-1]
                        );
                    }
                    contador=contador+pExcelDatosFormatoAdicional.getCantidadFilaPie();
                    insertarTextoCeldaBasico(vHoja,contador-pExcelDatosFormatoAdicional.getCantidadFilaPie(),
                            CellReference.convertColStringToIndex(pExcelDatosFormatoAdicional.getColumnaAgrupadora())
                            ,"Totales");
                    filaSumaFinal = contador-pExcelDatosFormatoAdicional.getCantidadFilaPie()-1;
                    adicionarSumatorias(vHoja
                            ,contador-pExcelDatosFormatoAdicional.getCantidadFilaPie()
                            ,filaSumaInicial,filaSumaFinal,pExcelDatosFormatoAdicional.getColumnasSumatoria());
                }

                for(int cabecera=pExcelDatosFormatoAdicional.getCantidadFilasCabecera();cabecera>=1;cabecera--) {
                    insertarUnaFila(vHoja, contador,
                            pExcelDatosFormatoAdicional.getColumnaInicial(),
                            pExcelDatosFormatoAdicional.getColumnaFinal(),
                            pExcelDatosFormatoAdicional.getColoresPie()[cabecera-1]);
                }

                blnPrimerRegistro= false;
                contador=contador+pExcelDatosFormatoAdicional.getCantidadFilasCabecera();
                if(pExcelDatosFormatoAdicional.getCantidadFilasCabecera()>0) {
                    insertarTextoCeldaBasico(vHoja, contador - 1,
                            CellReference.convertColStringToIndex(pExcelDatosFormatoAdicional.getColumnaAgrupadora())
                            , "Grupo: " + valorAgrupador);
                }
                filaSumaInicial = contador;
            }
            contador++;
        }
        if(Boolean.FALSE.equals(blnPrimerRegistro)){
            vHoja.createRow(contador);
            insertarUnaFila(vHoja, contador,
                    pExcelDatosFormatoAdicional.getColumnaInicial(),
                    pExcelDatosFormatoAdicional.getColumnaFinal(),
                    pExcelDatosFormatoAdicional.getColoresPie()[0]
            );
            insertarTextoCeldaBasico(vHoja,contador,
                    CellReference.convertColStringToIndex(pExcelDatosFormatoAdicional.getColumnaAgrupadora())
                    ,"Totales");
            filaSumaFinal = contador-1;
            adicionarSumatorias(vHoja
                    ,contador
                    ,filaSumaInicial,filaSumaFinal,pExcelDatosFormatoAdicional.getColumnasSumatoria());
        }

    }
    private void adicionarSumatorias(XSSFSheet vHoja,
                                     Integer fila,Integer filaInicial,Integer filaFinal,
                                     String[] columnasSumatoria){

        for(String columna : columnasSumatoria){
            insertarFormulaCeldaBasico(vHoja,fila,columna,filaInicial,filaFinal);
        }
    }

    private void insertarUnaFila(XSSFSheet vHoja,Integer filaInicio
            ,String inicioColumna, String ultimaColumna,boolean conColor){
        int ultimaFila = vHoja.getLastRowNum();
        if (ultimaFila < filaInicio) {
            vHoja.createRow(filaInicio);
        }
        vHoja.shiftRows(filaInicio, ultimaFila, 1, true, true);
        vHoja.createRow(filaInicio);
        XSSFRow vFilaExcel = vHoja.getRow(filaInicio);
        XSSFCell celda = vFilaExcel.createCell(CellReference.convertColStringToIndex("A"), CellType.STRING);
        celda.setCellValue(CARACTER_FILA_ADICIONAL);
        for(int indice = CellReference.convertColStringToIndex(inicioColumna);
            indice<=CellReference.convertColStringToIndex(ultimaColumna);indice++ ){
            XSSFCell celdaRecorrida = vFilaExcel.createCell(indice,CellType.STRING);
            if(conColor) {
                celdaRecorrida.setCellStyle(styleTituloAmarillo);
            }
        }
    }
    private void insertarTextoCeldaBasico(XSSFSheet vHoja,Integer fila,Integer pColumna,String texto){
        XSSFRow vFilaExcel = vHoja.getRow(fila);
        XSSFCell celda = vFilaExcel.createCell(pColumna, CellType.STRING);
        celda.setCellStyle(styleTituloAmarillo);
        celda.setCellValue(texto);
    }
    private void insertarFormulaCeldaBasico(XSSFSheet vHoja,Integer fila,String pColumna,Integer filaInicial,Integer filaFinal){
        XSSFRow vFilaExcel = vHoja.getRow(fila);
        XSSFCell celda = vFilaExcel.createCell(CellReference.convertColStringToIndex(pColumna), CellType.FORMULA);
        filaInicial++;
        filaFinal++;
        String formula = String.format(FORMULA_SUMA, pColumna.concat(filaInicial.toString()),pColumna.concat(filaFinal.toString()));
        celda.setCellStyle(styleTituloAmarillo);
        celda.setCellFormula(formula);
    }
    private void insertarFormulaCeldaPatron(XSSFSheet vHoja,XSSFRow vFilaExcel,Integer pColumna, String colocacionPatron){
        CellReference cellReference = new CellReference(colocacionPatron);
        XSSFRow row = vHoja.getRow(cellReference.getRow());
        XSSFCell cell = row.getCell(cellReference.getCol());
        String formula = cell.getCellFormula();

        XSSFCell celda = vFilaExcel.createCell(pColumna, CellType.FORMULA);
        //celda.setCellStyle(styleTituloAmarillo);
        celda.setCellFormula(formula);
    }



    public String sumarColumna(String columna, Integer recorrer){
        Integer indexColumna = CellReference.convertColStringToIndex(columna);
        indexColumna= indexColumna+recorrer;
        return CellReference.convertNumToColString(indexColumna);
    }
    public String[] columnasRango(String columna, String columnaFinal){

        Integer indexColumna = CellReference.convertColStringToIndex(columna);
        Integer indexColumnafinal = CellReference.convertColStringToIndex(columnaFinal);
        String[] arrColumnas= new String[indexColumnafinal-indexColumna+1];
        int contadorColumna = 0;
        for(int contador= indexColumna; contador<=indexColumnafinal;contador++){
            arrColumnas[contadorColumna++]= CellReference.convertNumToColString(contador);
        }
        return arrColumnas;
    }

    public void combinaYBordea(int fila1,int fila2, int col1, int col2,XSSFSheet vHojaExcel){

        CellRangeAddress region = new CellRangeAddress(fila1, fila2, col1, col2);
        RegionUtil.setBorderTop(BorderStyle.THIN, region, vHojaExcel);
        RegionUtil.setBorderBottom(BorderStyle.THIN, region, vHojaExcel);
        RegionUtil.setBorderLeft(BorderStyle.THIN, region, vHojaExcel);
        RegionUtil.setBorderRight(BorderStyle.THIN, region, vHojaExcel);
        vHojaExcel.addMergedRegion(new CellRangeAddress(fila1, fila2, col1, col2));
    }

    private static <T> T obtenerValorAtributo(Object instancia, String nombreAtributo) throws NoSuchFieldException, IllegalAccessException {
        Class<?> clase = instancia.getClass();
        Field campo = clase.getDeclaredField(nombreAtributo);
        campo.setAccessible(true); // Permite acceder a campos privados
        Object valor = campo.get(instancia);
        return (T) valor;
    }
    public static String convertirCoordenadaExcel(int fila, int columna) {
        CellReference cellReference = new CellReference(fila - 1, columna - 1);
        return cellReference.formatAsString();
    }

}

