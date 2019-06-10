
package tratamientoimam;

import java.util.ArrayList;


public class objetoControl {
    private ArrayList<ArrayList<String>> filas;
    private String centroDCostos, unidadDInformacion;

    
    public objetoControl(String centroDCostos, String unidadDInformacion){
        this.centroDCostos=centroDCostos;
        this.unidadDInformacion=unidadDInformacion;
        //System.out.println("Centro de costos: "+this.centroDCostos);
        //System.out.println("Unidad de Informacion: "+this.unidadDInformacion);
        this.filas = new ArrayList<>();

        
    }

    public void nuevaLine(ArrayList<String> fila){

        this.filas.add(fila);
        //System.out.println(fila);

        
        
    }

    public ArrayList<ArrayList<String>> getFilas() {
        return filas;
    }

    public String getCentroDCostos() {
        return centroDCostos;
    }

    public String getUnidadDInformacion() {
        return unidadDInformacion;
    }
    
    

    
}


