
package tratamientoimam;

import java.util.ArrayList;


public class Config {
    private String loQueSebusca, centroDCostos, uniInfo, inicioFila;
    private int celLoQueSeBusca, celCentroCostos, celUniInfo, celInicioFila, numeroDeColumnas;
    private ArrayList<String> titulos;
    
    public Config(String loQueSebusca, String centroDCostos, String uniInfo, String inicioFila, int celLoQueSeBusca, int celCentroCostos, int celUniInfo, int celInicioFila, int numeroDeColumnas){
        this.loQueSebusca= loQueSebusca;
        this.centroDCostos=centroDCostos;
        this.uniInfo= uniInfo;
        this.inicioFila=inicioFila;
        this.celLoQueSeBusca=celLoQueSeBusca;
        this.celCentroCostos=celCentroCostos;
        this.celUniInfo=celUniInfo;
        this.celInicioFila=celInicioFila;
        this.numeroDeColumnas=numeroDeColumnas;
        titulos = new ArrayList<>();
    }
    
    public Config(){
        titulos = new ArrayList<>();
    }
    public void limpiarTitulos(String titulos){
        char[] title= titulos.toCharArray();
        String aux="";
        for (int i = 0; i < title.length; i++) {
            if (','==title[i]) {
                this.titulos.add(aux);
                aux="";
            }else{
                aux=aux+title[i];
            }
        }
    }
    
    
    public String getLoQueSebusca() {
        return loQueSebusca;
    }

    public String getCentroDCostos() {
        return centroDCostos;
    }

    public String getUniInfo() {
        return uniInfo;
    }

    public String getInicioFila() {
        return inicioFila;
    }

    public int getCelLoQueSeBusca() {
        return celLoQueSeBusca;
    }

    public int getCelCentroCostos() {
        return celCentroCostos;
    }

    public int getCelUniInfo() {
        return celUniInfo;
    }

    public int getCelInicioFila() {
        return celInicioFila;
    }

    public int getNumeroDeColumnas() {
        return numeroDeColumnas;
    }

    public void setLoQueSebusca(String loQueSebusca) {
        this.loQueSebusca = loQueSebusca;
    }

    public void setCentroDCostos(String centroDCostos) {
        this.centroDCostos = centroDCostos;
    }

    public void setUniInfo(String uniInfo) {
        this.uniInfo = uniInfo;
    }

    public void setInicioFila(String inicioFila) {
        this.inicioFila = inicioFila;
    }

    public void setCelLoQueSeBusca(int celLoQueSeBusca) {
        this.celLoQueSeBusca = celLoQueSeBusca;
    }

    public void setCelCentroCostos(int celCentroCostos) {
        this.celCentroCostos = celCentroCostos;
    }

    public void setCelUniInfo(int celUniInfo) {
        this.celUniInfo = celUniInfo;
    }

    public void setCelInicioFila(int celInicioFila) {
        this.celInicioFila = celInicioFila;
    }

    public void setNumeroDeColumnas(int numeroDeColumnas) {
        this.numeroDeColumnas = numeroDeColumnas;
    }

    public ArrayList<String> getTitulos() {
        return titulos;
    }
    
    
    
    
    
}
