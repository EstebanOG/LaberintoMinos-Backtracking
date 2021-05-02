package logica;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import vista.Vista;

/**
 *
 * @author Usuario
 */
public class Logica{
    /**
     * 0: Camino
     * 1: Bloque
     * 2: Minotauro
     * 3: Perseo
     * 4: Jovenes
     */
    private Vista vista = new Vista();
    private int numPasos = 0,numPasosCambio = 13, numJovenes = 0, numJovenesEncontrados = 0;
    private boolean minoVivo = true, juego = true;
    private ArrayList<String> camino = new ArrayList<String>();
    private int tablero[][] = {{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                               {3,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1},
                               {1,0,1,1,1,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1},
                               {1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1},
                               {1,0,1,0,1,0,1,1,1,1,1,1,1,1,1,1,1,0,1,0,1},
                               {1,0,1,0,1,0,0,0,0,1,0,0,0,0,0,0,1,0,1,0,1},
                               {1,0,1,0,1,0,1,1,1,1,0,1,1,1,1,0,1,0,1,0,1},
                               {1,0,1,0,1,0,1,0,0,0,0,0,0,0,1,0,1,0,1,0,1},
                               {1,0,1,0,1,0,1,0,2,0,0,0,0,0,1,0,1,1,1,0,1},
                               {1,0,1,0,1,0,1,0,0,0,0,0,0,0,1,0,1,0,1,0,1},
                               {1,0,1,1,1,0,1,1,1,1,1,1,1,1,1,0,1,0,1,0,1},
                               {1,0,1,0,1,0,0,0,0,0,0,0,1,0,0,0,1,0,1,0,1},
                               {1,0,1,0,1,1,1,1,1,1,1,1,1,1,1,0,1,0,1,0,1},
                               {1,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,1},
                               {1,0,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,0,1,0,1},
                               {1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1},
                               {1,0,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1},
                               {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                               {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                                };
    public Logica() {
        
        generarJovenes();
        vista.getMapa().repintarMapa(tablero);
        while(juego){
            if(numJovenesEncontrados>0){
                juego = false;
            }
            camino.clear();
            recorrer(1,0,tablero,"");
            
        }
        notificar(5);
        
    }

    public int[][] getTablero() {
        return tablero;
    }

    public void setTablero(int[][] tablero) {
        this.tablero = tablero;
    }
    
    /**
     * Generar los jovenes que se encuentran al interior del laberinto.
     */
    private void generarJovenes(){
        //Generar número de jovenes, el cual está entre 1 y 3
        numJovenes = (int) Math.floor(Math.random()*3+1);
        int contador = 0;
        
        //Se buscan casillas alteatorias disponibles para ubicar a los jovenes.
        while(contador<numJovenes){
            int x = (int) Math.floor(Math.random()*18);
            int y = (int) Math.floor(Math.random()*20);
            if(tablero[x][y] ==  0 ){
                tablero[x][y] = 4;
                contador++;
            }
        }
        
        
    }
    
    public void recorrer(int x, int y, int[][] tableroAct, String pasoAnterior) {
        //Movimiento derecha
        pausar();
        vista.getMapa().repintarMapa(tableroAct);
        
        if (x>0 && juego && tableroAct[x - 1][y] != 1 && !camino.contains("" + x + "" + y + "" + (x - 1) + "" + y)) {
            if (tableroAct[x - 1][y] == 2) {
                notificar(0);
                minoVivo = false;
                tableroAct[x - 1][y] = 3;
            }
            if(tableroAct[x - 1][y] == 4){
                if(!minoVivo){
                    notificar(3);
                    numJovenesEncontrados++;
                }
            }
            
            tableroAct[x][y] = 0;
            
            contarPasos();
            tablero = tableroAct;
            camino.add("" + x + "" + y + "" + (x - 1) + "" + y);
            recorrer((x - 1), y, tableroAct, "arriba");
            tableroAct[x][y] = 3;
            tableroAct[x - 1][y] = 0;
            contarPasos();
        }
        vista.getMapa().repintarMapa(tableroAct);
        pausar();
        
        if ( juego &&  tableroAct[x][y + 1] != 1 && !camino.contains("" + x + "" + y + "" + x + "" + (y + 1))) {
            if (tableroAct[x][y + 1] == 2) {
                notificar(0);
                minoVivo = false;
            }
            
            if(tableroAct[x][y + 1] == 4){
                if(!minoVivo){
                    notificar(3);
                    numJovenesEncontrados++;
                }
            }
            
            
            tableroAct[x][y] = 0;
            tableroAct[x][y + 1] = 3;
            contarPasos();
            tablero = tableroAct;
            camino.add("" + x + "" + y + "" + x + "" + (y + 1));
            recorrer(x, (y + 1), tableroAct, "derecha");
            tableroAct[x][y] = 3;
            tableroAct[x][y + 1] = 0;
            contarPasos();
        }
        vista.getMapa().repintarMapa(tableroAct);
        pausar();
        
        if ( juego &&  tableroAct[x + 1][y] != 1 && !camino.contains("" + x + "" + y + "" + (x + 1) + "" + y)) {
            if (tableroAct[x + 1][y] == 2) {
                notificar(0);
                minoVivo = false;
            }
            
            if(tableroAct[x + 1][y] == 4){
                if(!minoVivo){
                    notificar(3);
                    numJovenesEncontrados++;
                }
            }
            tableroAct[x][y] = 0;
            tableroAct[x + 1][y] = 3;
            contarPasos();
            tablero = tableroAct;
            camino.add("" + x + "" + y + "" + (x + 1) + "" + y);
            recorrer((x + 1), y, tableroAct, "abajo");
            tableroAct[x][y] = 3;
            tableroAct[x + 1][y] = 0;
            contarPasos();
        }
        vista.getMapa().repintarMapa(tableroAct);
        pausar();
        
        if (y > 0 && juego &&  tableroAct[x][y - 1] != 1 && !camino.contains("" + x + "" + y + "" + x + "" + (y - 1))) {
            if (tableroAct[x][y - 1] == 2) {
                notificar(0);
                minoVivo = false;
            }
            
            if(tableroAct[x][y - 1] == 4){
                if(!minoVivo){
                    notificar(3);
                    numJovenesEncontrados++;
                }
            }
            tableroAct[x][y] = 0;
            tableroAct[x][y - 1] = 3;
            contarPasos();
            tablero = tableroAct;
            camino.add("" + x + "" + y + "" + x + "" + (y - 1));
            recorrer(x, (y - 1), tableroAct, "izquierda");
            tableroAct[x][y] = 3;
            tableroAct[x][y - 1] = 0;
            contarPasos();
        }
        
        vista.getMapa().repintarMapa(tableroAct);
        pausar();
    }
    
    
    /**
     * Cambiar la posición del minotauro.
     */
    private void cambiarMinotauro(){
        boolean cambiarMino = true;
        
        int posXMino = 0,
            posYMino = 0;
       
        //Buscar Minotaruo
        for(int i = 0; i < tablero.length; i++){
            for(int j = 0; j < tablero[0].length; j++){
                if(tablero[i][j]==2){
                    posXMino = i;
                    posYMino = j;
                }
            }
        }
        
        //Se repite hasta cambiar el minotauto.
        while(cambiarMino){
            //Se genera una casilla aleatoria.
            int x = (int) Math.floor(Math.random()*18);
            int y = (int) Math.floor(Math.random()*20);
            
            //Se verfica si la casilla está disponible, se encuentra teseo
            //o alguno de los jovenes.
            if(tablero[x][y] ==  0 ){
                tablero[posXMino][posYMino] = 0;
                tablero[x][y] = 2;
                cambiarMino = false;
            }
            if(tablero[x][y] == 3){
                tablero[posXMino][posYMino] = 0;
                tablero[x][y] = 2;
                notificar(1);
                juego = false;
                cambiarMino = false;
            }
            
            if(tablero[x][y]==4){
                numJovenes--;
                tablero[posXMino][posYMino] = 0;
                tablero[x][y] = 2;
                if(numJovenes == 0){
                  juego =false;
                  notificar(4);  
                }else{
                    notificar(2); 
                }
                cambiarMino = false;
            }
        }
    }
    
    private void pausar(){
        try {
            Thread.sleep(10);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private void notificar(int op){
        switch(op){
            case 0:
                JOptionPane.showMessageDialog(null, "Minotauro eliminado.");
                break;
            case 1:
                JOptionPane.showMessageDialog(null, "Teseo eliminado. El juego a terminado.");
                System.exit(0);
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "Joven eliminado.");
                break;
            case 3:
                JOptionPane.showMessageDialog(null, "Joven encontrado.");
                break;
            case 4:
                JOptionPane.showMessageDialog(null, "Los jovenes han sido eliminados. El juego ha termiando.");
                System.exit(0);
                break;
            case 5:
                JOptionPane.showMessageDialog(null, "Juego terminado.");
                break;
            default:
                break;
        }
        
    }
    
    private void contarPasos(){
        numPasos++;
        System.out.println("Pasos:"+numPasos);
        numPasosCambio--;
        if(minoVivo && numPasosCambio == 0){
            cambiarMinotauro();
            numPasosCambio = 13;
        }
    }
}
