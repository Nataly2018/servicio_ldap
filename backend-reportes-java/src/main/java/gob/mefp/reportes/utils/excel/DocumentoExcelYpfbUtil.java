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
import org.apache.poi.ss.util.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.poi.openxml4j.util.ZipSecureFile.setMinInflateRatio;

public class DocumentoExcelYpfbUtil {

    private XSSFWorkbook archivExcel;
    private List<String> listaErrores;
    private Map<String, List<Integer>> dimensiones;
    private Map<String, List<? extends XSSFTable>> tablas;
    private CellStyle stylePlomoLeft;
    private CellStyle stylePlomoRight;
//    private ExcelConstantes.EnumTipoPrimeraColumna tipoCelda;

    private String textoTotal ;
    private short altoCelda = 750;
    public DocumentoExcelYpfbUtil(){
        listaErrores = new ArrayList<>();
        dimensiones = new HashMap<>();
        tablas = new HashMap<>();
        textoTotal = ExcelConstantes.EnumTipoPrimeraColumna.TOTAL.nombre;
    }

    public short getAltoCelda() {
        return altoCelda;
    }
    public short resetAltoCelda() {
        return altoCelda= 750;
    }
    public void setAltoCelda(short altoCelda) {
        this.altoCelda = altoCelda;
    }

    public void setTextoTotal (String texto){
        this.textoTotal = texto;
    }
    public void resetTextoTotal (){
        this.textoTotal = ExcelConstantes.EnumTipoPrimeraColumna.TOTAL.nombre;
    }

    public void obtenerArchivoXls(String pathExcel) {
        try {
            setMinInflateRatio(0);
            ClassPathResource classPathResource = new ClassPathResource(pathExcel);
            InputStream in = classPathResource.getInputStream();
//            if (classPathResource.exists()) {
//                System.out.println("El recurso existe en el classpath.");
//            } else {
//                System.out.println("El recurso no existe en el classpath.");
//            }
            archivExcel = new XSSFWorkbook(in);

            stylePlomoLeft = archivExcel.createCellStyle();
            stylePlomoLeft.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            stylePlomoLeft.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            stylePlomoLeft.setAlignment(HorizontalAlignment.LEFT);
            stylePlomoLeft.setWrapText(true);
            stylePlomoLeft.setVerticalAlignment(VerticalAlignment.CENTER);
            Font fontArial9 = archivExcel.createFont();
            fontArial9.setFontName("Arial");
            fontArial9.setFontHeightInPoints((short) 9);
            stylePlomoLeft.setFont(fontArial9);
            stylePlomoRight = archivExcel.createCellStyle();
            stylePlomoRight.cloneStyleFrom(stylePlomoLeft);
            stylePlomoRight.setAlignment(HorizontalAlignment.RIGHT);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public XSSFSheet obtenerHojaXls(String nombreSeccion) {
        XSSFSheet sheet = archivExcel.getSheet(nombreSeccion);
        if(sheet == null ){
            throw new ErrorValidacionException(String.format("No fue posible abrir la hoja excel: %s, verifique que sea el archivo correcto", nombreSeccion));
        }
        List<Integer> anchos = new ArrayList<>();
        if(sheet.getRow(0)==null){
            return sheet;
        }
        for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++) {
            anchos.add(sheet.getColumnWidth(i));
        }
        dimensiones.put(sheet.getSheetName(),anchos);
        return sheet;
    }
    public void ajustarColumnasExcel(XSSFSheet vXSSFSheet){
        CellStyle style = archivExcel.createCellStyle();
        if(!dimensiones.containsKey(vXSSFSheet.getSheetName())){
            return;
        }
        List<Integer> anchos = dimensiones.get(vXSSFSheet.getSheetName());
        style.setWrapText(true);
        for (int i = 0; i < anchos.size(); i++) {
            vXSSFSheet.setColumnWidth(i, anchos.get(i));
            vXSSFSheet.setDefaultColumnStyle(i, style);
        }
    }

//    public void obtenerInformacionTablas(XSSFSheet vXSSFSheet){
//        List<? extends XSSFTable> tables = vXSSFSheet.getTables();
//        if(tables !=null && !tables.isEmpty() ) {
//            tablas.put(vXSSFSheet.getSheetName(), tables);
//        }
//
//    }

//    public void redimensionarTabla(XSSFSheet vXSSFSheet,Integer cantidadFilas){
//        if(tablas.containsKey(vXSSFSheet.getSheetName())){
//            XSSFTable table = (XSSFTable) tablas.get(vXSSFSheet.getSheetName()).get(0);
////            CellReference startCellReference = table.getStartCellReference();
////            CellReference endCellReference = table.getEndCellReference();
////            CellReference newEndCellReference = new CellReference(startCellReference.getRow() + cantidadFilas - 1, endCellReference.getCol());
////            AreaReference vAreaReference = new AreaReference(startCellReference, newEndCellReference, SpreadsheetVersion.EXCEL97);
////
////            table.setCellReferences(vAreaReference);
//
//            table.getCTTable().setRef(
//
//            );
//        }
//    }

//    public void aplicarEstiloTabla(XSSFSheet vXSSFSheet,Cell cell ){
//        if(!tablas.containsKey(vXSSFSheet.getSheetName())){
//            return;
//        }
//        XSSFTable table = null;
//        for (XSSFTable t : tablas.get(vXSSFSheet.getSheetName())) {
//            int startRow = t.getStartRowIndex();
//            int endRow = t.getEndRowIndex();
//            int startColumn = t.getStartColIndex();
//            int endColumn = t.getEndColIndex();
//            if (cell.getRowIndex() >= startRow && cell.getRowIndex() <= endRow && cell.getColumnIndex() >= startColumn && cell.getColumnIndex() <= endColumn) {
//                table = t;
//                break;
//            }
//        }
//        if (table != null) {
//            CellStyle cellStyle = cell.getCellStyle();
//            CTTable  vCTTable  = table.getCTTable();
//            vCTTable.getTableColumns();
//            CTTableStyleInfo vCTTableStyleInfo = vCTTable.getTableStyleInfo();
//            cell.getCellStyle().setDataFormat(cellStyle.getDataFormat());
//            cell.getCellStyle().setFillForegroundColor(vCTTableStyleInfo..getFillForegroundColor());
//            // Otros atributos que deseas mantener, como la alineación, color de fondo, etc.
//        }
//    }
    public void escribirExcelRequest(HttpServletResponse response, String nombreExcelDescarga) {
        XSSFFormulaEvaluator.evaluateAllFormulaCells(archivExcel);
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
            throw new  IntegridadYpfbException("Error al exportar el archivo Excel");
        }
    }
    public void obtenerArchivoFromMultipart(MultipartFile file) {
        InputStream inputStream = null;
        try {
            setMinInflateRatio(0);
            inputStream = file.getInputStream();
            archivExcel = new XSSFWorkbook(inputStream);
        } catch (Exception e) {
            throw new IntegridadYpfbException(String.format("No fue posible abrir el archivo excel, revise que sea un documento de extensión 'xlsx' valido"),e);
        }

    }

    public <T extends Object> void escribirListaEnExcel(
            XSSFSheet vHoja,
            Integer pFilaInicio, Integer columnainicio,
            List<T> listaEscribir,
            Class<T> clase
            ) {
        escribirListaEnExcel(vHoja,pFilaInicio,columnainicio,
                listaEscribir,clase, ExcelConstantes.EnumTipoPrimeraColumna.CONTADOR,false);
    }

    public <T extends Object> void escribirListaEnExcel(
            XSSFSheet vHoja,
            Integer pFilaInicio, Integer columnainicio,
            List<T> listaEscribir,
            Class<T> clase,
            ExcelConstantes.EnumTipoPrimeraColumna tipoPrimeraColumna,
            Boolean vFilaExistente) {
        columnainicio--;
        List<ExcelYpfbArrDescriptivo> vListaCamposDatos = extraerInformacionNotaciones(clase);
        int contadorFila = 0;
        pFilaInicio--;

        if (tipoPrimeraColumna.equals(ExcelConstantes.EnumTipoPrimeraColumna.SIN_COLUMNA_AUTOMATICA)) {
            columnainicio--;
        }
//        if (tipoPrimeraColumna.equals(ExcelConstantes.EnumTipoPrimeraColumna.TOTAL)) {
//            styleCeldaActual= stylePlomo;
//        }
//        if(vHoja.getSheetName().equals("PRESUPUESTO")){
//            System.out.println("uno");
//        }

        try {
            for (T vObjeto : listaEscribir) {
                contadorFila++;
                XSSFRow vFilaExcel;
                if (Boolean.TRUE.equals(vFilaExistente)) {
                    vFilaExcel = vHoja.getRow(pFilaInicio);
                    if(vFilaExcel==null){
                        vFilaExcel = vHoja.createRow(pFilaInicio);
                    }
                } else {
                    vFilaExcel = vHoja.createRow(pFilaInicio);
                }

                if (tipoPrimeraColumna.equals(ExcelConstantes.EnumTipoPrimeraColumna.CONTADOR)) {
                    escribirCeldaEnExcel(vFilaExcel, columnainicio, contadorFila);
                }
                if (tipoPrimeraColumna.equals(ExcelConstantes.EnumTipoPrimeraColumna.TOTAL)) {
                    //XSSFCell celda =escribirCeldaEnExcel(vFilaExcel, columnainicio, ExcelConstantes.EnumTipoPrimeraColumna.TOTAL.nombre);
                    XSSFCell celda =escribirCeldaEnExcel(vFilaExcel, columnainicio, this.textoTotal);
                    celda.setCellStyle(stylePlomoLeft);
                }
                pFilaInicio++;
                for (ExcelYpfbArrDescriptivo vExcelYpfbArrDescriptivo : vListaCamposDatos) {
                    Object campo = vExcelYpfbArrDescriptivo.getVField().get(vObjeto);
                    //si es una lista
                    //llamar a un metodo que convierta un listado a columnas

                    if(Boolean.TRUE.equals(vExcelYpfbArrDescriptivo.getValorONada()) && campo==null){
                        continue;
                    }

                    if(campo instanceof  BigDecimal && vExcelYpfbArrDescriptivo.getCantidadDecimalesColumna()>0){
                        BigDecimal nuevo =  (BigDecimal)campo;
                        campo = nuevo.setScale(vExcelYpfbArrDescriptivo.getCantidadDecimalesColumna(), RoundingMode.HALF_UP);
                    }
                    XSSFCell celda =escribirCeldaEnExcel(vFilaExcel,
                            vExcelYpfbArrDescriptivo.getPosicionColumna() + columnainicio
                            , campo);
                    if (tipoPrimeraColumna.equals(ExcelConstantes.EnumTipoPrimeraColumna.TOTAL)) {
                        if(celda.getCellStyle().getAlignment().equals(HorizontalAlignment.LEFT)) {
                            celda.setCellStyle(stylePlomoLeft);
                        }
                        else{
                            celda.setCellStyle(stylePlomoRight);
                        }
                    }

                }
                if(altoCelda>(short)0){
                    vFilaExcel.setHeight(altoCelda);
                }

            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        for (ExcelYpfbArrDescriptivo vExcelYpfbArrDescriptivo : vListaCamposDatos) {
            //vHoja.autoSizeColumn(vExcelYpfbArrDescriptivo.getPosicionColumna() + columnainicio);
            if (Boolean.TRUE.equals(vExcelYpfbArrDescriptivo.getColumnaOculta())) {
                vHoja.setColumnHidden(vExcelYpfbArrDescriptivo.getPosicionColumna() + columnainicio, vExcelYpfbArrDescriptivo.getColumnaOculta());
            }
        }
        ajustarColumnasExcel(vHoja);
    }

    public <T extends Object> List<T> leerCuerpoExcel(
            XSSFSheet vHoja,
            Integer pFilaInicio, Integer columnainicio,
            Class<T> clase) {
        T obj = null;
        List<T> listaResultados = new ArrayList<>();
        List<ExcelYpfbArrDescriptivo> vListaCamposDatos = extraerInformacionNotaciones(clase);
        Integer cantidadFilasExcel = vHoja.getLastRowNum() + 1;


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
                            listaErroresFila.add("En la Hoja "+vHoja.getSheetName()+" la celda '"+celda.getAddress().formatAsString() + "' "+tipoCasteoCampo);
                        }
                    }
                    catch (Exception exp){
                        listaErroresFila.add("En la Hoja "+vHoja.getSheetName()+" la celda '"+celda.getAddress().formatAsString() + "' "+tipoCasteoCampo);
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
                errorGeneral+=" en la celda: " + celda.getAddress().formatAsString()+" de la hoja "+vHoja.getSheetName();
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




    public void ocultarColumnaEspecifica(XSSFSheet vHoja, Integer columna) {
        columna--;
        vHoja.setColumnHidden(columna, true);
    }
    public <T extends Object> void escribirCeldaTextoIndependienteEnExcel(
            XSSFSheet vHoja,
            Integer pFila,
            Integer pColumna, Object campo) {

        pFila--;
        pColumna--;
        XSSFRow vFilaExcel = vHoja.getRow(pFila);
        if (vFilaExcel == null) {
            vFilaExcel = vHoja.createRow(pFila);
        }
        escribirCeldaEnExcel(vFilaExcel, pColumna, campo);
    }

    public XSSFCell crearCelda(XSSFRow vFilaExcel,Integer pColumna,CellType tipo){
        XSSFCell celda = vFilaExcel.getCell(pColumna);
        if(celda==null){
            celda = vFilaExcel.createCell(pColumna, tipo);
        }
//        if(styleCeldaActual!=null) {
//            if (stylePlomo == null) {
//                CellStyle style = celda.getCellStyle();
//                style.setFillForegroundColor(IndexedColors.BLUE.getIndex());
//                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//                stylePlomo = archivExcel.createCellStyle();
//                stylePlomo.cloneStyleFrom(style);
//            }
//            celda.setCellStyle(stylePlomo);
//        }

        return celda;
    }
    public XSSFCell crearCeldaPuro(XSSFRow vFilaExcel,Integer pColumna){
        XSSFCell celda = vFilaExcel.getCell(pColumna);
        if(celda==null){
            celda = vFilaExcel.createCell(pColumna);
        }
//        if(styleCeldaActual!=null) {
//            if (stylePlomo == null) {
//                CellStyle style = celda.getCellStyle();
//                style.setFillForegroundColor(IndexedColors.BLUE.getIndex());
//                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//                stylePlomo = archivExcel.createCellStyle();
//                stylePlomo.cloneStyleFrom(style);
//            }
//            celda.setCellStyle(stylePlomo);
//        }

        return celda;
    }

    public <T extends Object> XSSFCell escribirCeldaEnExcel(
            XSSFRow vFilaExcel,
            Integer pColumna,
            Object campo
            ) {


        XSSFCell celda=null;
        if (campo instanceof Long) {
            celda = crearCelda(vFilaExcel,pColumna, CellType.NUMERIC);
            celda.setCellValue(((Long) campo).toString());
        } else if (campo instanceof Integer) {
            celda = crearCelda(vFilaExcel,pColumna, CellType.NUMERIC);
            celda.setCellValue(((Integer) campo));
        } else if (campo instanceof BigDecimal) {



            String strNumero = campo.toString();
            strNumero = strNumero.replace(".","");
            Boolean esMayorAUno = (((BigDecimal) campo).compareTo(BigDecimal.ONE) > 0 );
            if ((strNumero.length() > 15) && esMayorAUno){
                celda = crearCelda(vFilaExcel,pColumna, CellType.STRING);
                strNumero = campo.toString();
                strNumero = strNumero.replace(".",",");
                celda.setCellValue(strNumero);
            } else {
                String string = ((BigDecimal) campo).stripTrailingZeros().toPlainString();
                int index = string.indexOf(".");
                //Integer cantidadDecimales = index < 0 ? 0 : string.length() - index - 1;
                //if(cantidadDecimales<cantidadDecimalesColumna){
                  //  cantidadDecimales=cantidadDecimalesColumna;
                //}
                //styleCelda = styleNumericos.get(cantidadDecimales);
                celda = crearCelda(vFilaExcel,pColumna, CellType.NUMERIC);
                celda.setCellValue(((BigDecimal) campo).doubleValue());
            }
        }else if (campo instanceof  RichTextString){
                celda = crearCeldaPuro(vFilaExcel,pColumna);
                celda.setCellValue((RichTextString)campo);
        }
        else {
            celda = crearCelda(vFilaExcel,pColumna, CellType.STRING);
            celda.setCellValue((String) campo);
           }

        return celda;
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
    public static String convertirCoordenadaExcel(int fila, int columna) {
        CellReference cellReference = new CellReference(fila - 1, columna - 1);
        return cellReference.formatAsString();
    }


    public XSSFWorkbook getArchivExcel() {
        return archivExcel;
    }

    public void setArchivExcel(XSSFWorkbook archivExcel) {
        this.archivExcel = archivExcel;
    }

    public List<String> getListaErrores() {
        return listaErrores;
    }

    public void setListaErrores(List<String> listaErrores) {
        this.listaErrores = listaErrores;
    }
}

