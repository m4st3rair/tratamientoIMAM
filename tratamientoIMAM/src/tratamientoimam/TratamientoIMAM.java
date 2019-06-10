package tratamientoimam;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TratamientoIMAM {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        GUI gui= new GUI();
        gui.setVisible(true);
        
        gui.cargarArchivoConfig();
        
        
        
        
    }
    
}
