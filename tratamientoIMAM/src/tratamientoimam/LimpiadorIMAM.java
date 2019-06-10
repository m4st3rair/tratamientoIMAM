
package tratamientoimam;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;


import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Antonio
 */
public class LimpiadorIMAM {
    private String rutaArchivo;
    private ArrayList<objetoControl> imam;
    private objetoControl filaImam;
    private Config conf;
    private JTextArea jlist;

    
    public LimpiadorIMAM(String rutaArchivo, Config conf, JTextArea lista){
        this.rutaArchivo=rutaArchivo;
        this.imam = new ArrayList<>();
        this.filaImam=null;
        this.conf=conf;
        this.jlist= lista;
    }
    
    
    
    public void LeeArchivo(){
        try {
            String rutaArchivoExcel = rutaArchivo;
            FileInputStream inputStream = new FileInputStream(new File(rutaArchivoExcel));
            Workbook workbook = new HSSFWorkbook(inputStream);
            Sheet firstSheet = workbook.getSheetAt(0);
            Iterator iterator = firstSheet.iterator();            
            DataFormatter formatter = new DataFormatter();
            boolean banderaInicio= true;
            //true  estamos iniciando
            //false ya habiamos iniciado
            System.err.println(firstSheet.getLastRowNum());
            String unidadInfo="", centroCostos="";
            ArrayList<String> fila = null;
            
            while (iterator.hasNext()) {
                Row nextRow = (Row) iterator.next();
                Iterator cellIterator = nextRow.cellIterator();
                
                if (formatter.formatCellValue(nextRow.getCell(this.conf.getCelUniInfo())).equals(this.conf.getUniInfo())) {                    
                    unidadInfo=formatter.formatCellValue(nextRow.getCell(this.conf.getCelUniInfo()+1));
                    if(banderaInicio){
                        banderaInicio=false;
                    }else{
                        imam.add(filaImam);
                    }
                }
                if (formatter.formatCellValue(nextRow.getCell(this.conf.getCelCentroCostos())).equals(this.conf.getCentroDCostos())) {
                    centroCostos=formatter.formatCellValue(nextRow.getCell(this.conf.getCelCentroCostos()+1));
                }
                
                if(!centroCostos.equals("") && !unidadInfo.equals("")){
                    filaImam= new objetoControl( centroCostos, unidadInfo);
                    centroCostos="";
                    unidadInfo="";
                }

                if (formatter.formatCellValue(nextRow.getCell(0)).equals(this.conf.getInicioFila()) && formatter.formatCellValue(nextRow.getCell(this.conf.getCelLoQueSeBusca())).equals(this.conf.getLoQueSebusca())) {
                    
                    fila=new ArrayList<>();
                    for (int i = 0; i < this.conf.getNumeroDeColumnas(); i++) {
                        
                        fila.add(formatter.formatCellValue(nextRow.getCell(i)));
                    }
                    if (filaImam!=null) {
                        filaImam.nuevaLine(fila);
                    }
                }
            }
            this.jlist.append("\n Archivo leido y tratado");
        } catch (IOException e) {
            System.err.println(e.getCause());
        }
    }
    public void guardarArchivoExcel() throws FileNotFoundException, IOException{
        this.jlist.append("\n Guardando Cambios en Archivo");
        
        File directorio = new File("c:\\IMAMprograma\\ArchivosCreados");
        
        if (!directorio.exists()) {
            directorio.mkdirs();
            this.jlist.append("\n Creando Directorio de Archivos...");
        }
        String respuesta="";
        while("".equals(respuesta)){
            respuesta = JOptionPane.showInputDialog("Ingrese el nombre del Documento de Excel");
        }
        respuesta="\\"+respuesta+".xlsx";
        String nombreHoja = "IMAM";
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet hoja=  wb.createSheet(nombreHoja);
        //Hiteracion para el numero de filas
        ArrayList<String> titulos=this.conf.getTitulos();
        titulos.add(0, "Centro de costos");
        titulos.add(0, "Unidad de Informacion");
        
        int numeroFilas=0;
        XSSFRow filaT= hoja.createRow(numeroFilas);
        
        for (int i = 0; i < titulos.size(); i++) {
           XSSFCell celd= filaT.createCell(i);
           celd.setCellValue(titulos.get(i));
        }
        numeroFilas++;
        for (int i = 0; i < imam.size(); i++) {
            for (int j = 0; j < imam.get(i).getFilas().size(); j++) {
                XSSFRow fila = hoja.createRow(numeroFilas);
                numeroFilas++;       
                XSSFCell celda= fila.createCell(1);
                celda.setCellValue( imam.get(i).getCentroDCostos());
                celda=fila.createCell(0);
                celda.setCellValue(imam.get(i).getUnidadDInformacion());
                for (int k = 0; k < imam.get(i).getFilas().get(j).size(); k++) {
                    celda=fila.createCell(k+2);
                    celda.setCellValue(imam.get(i).getFilas().get(j).get(k));       
                }       
            }
        }
        try (FileOutputStream archivoSalida = new FileOutputStream(directorio + respuesta)) {
            wb.write(archivoSalida);
            archivoSalida.flush();
        }
        this.jlist.append("Archivo creado");
        Desktop.getDesktop().open(directorio);
        titulos.remove(0);
        titulos.remove(0);   
    }   
}