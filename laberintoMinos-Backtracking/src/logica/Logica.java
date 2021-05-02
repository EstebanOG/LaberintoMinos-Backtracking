package logica;

import java.util.ArrayList;
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
    private boolean minoVivo = true;
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
        
        //generarJovenes();
        vista.getMapa().repintarMapa(tablero);
        while(minoVivo){
            camino.clear();
            recorrer(1,0,tablero,"");
        }
        
        
    }

    public int[][] getTablero() {
        return tablero;
    }

    public void setTablero(int[][] tablero) {
        this.tablero = tablero;
    }
    
    private void generarJovenes(){
        int numJovenes = (int) Math.floor(Math.random()*3+1);
        int contador = 0;
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
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            System.out.println(e);
        }
        vista.getMapa().repintarMapa(tableroAct);

        if (minoVivo && tableroAct[x - 1][y] != 1 && !camino.contains("" + x + "" + y + "" + (x - 1) + "" + y)) {
            if (tableroAct[x - 1][y] == 2) {
                minoVivo = false;
            }
            tableroAct[x][y] = 0;
            tableroAct[x - 1][y] = 3;
            tablero = tableroAct;
            System.out.println("Arriba");
            camino.add("" + x + "" + y + "" + (x - 1) + "" + y);
            recorrer((x - 1), y, tableroAct, "arriba");
            tableroAct[x][y] = 3;
            tableroAct[x - 1][y] = 0;
        }
        vista.getMapa().repintarMapa(tableroAct);
        try {
            
            Thread.sleep(100);
        } catch (Exception e) {
            System.out.println(e);
        }
        if ( minoVivo &&  tableroAct[x][y + 1] != 1 && !camino.contains("" + x + "" + y + "" + x + "" + (y + 1))) {
            if (tableroAct[x][y + 1] == 2) {
                minoVivo = false;
            }
            tableroAct[x][y] = 0;
            tableroAct[x][y + 1] = 3;
            tablero = tableroAct;
            System.out.println("Derecha");
            camino.add("" + x + "" + y + "" + x + "" + (y + 1));
            recorrer(x, (y + 1), tableroAct, "derecha");
            tableroAct[x][y] = 3;
            tableroAct[x][y + 1] = 0;
        }
        vista.getMapa().repintarMapa(tableroAct);
        try {
            //Ponemos a "Dormir" el programa durante los ms que queremos
            Thread.sleep(100);
        } catch (Exception e) {
            System.out.println(e);
        }
        if ( minoVivo &&  tableroAct[x + 1][y] != 1 && !camino.contains("" + x + "" + y + "" + (x + 1) + "" + y)) {
            if (tableroAct[x + 1][y] == 2) {
                minoVivo = false;
            }
            tableroAct[x][y] = 0;
            tableroAct[x + 1][y] = 3;
            tablero = tableroAct;
            System.out.println("Abajo");
            camino.add("" + x + "" + y + "" + (x + 1) + "" + y);
            recorrer((x + 1), y, tableroAct, "abajo");
            tableroAct[x][y] = 3;
            tableroAct[x + 1][y] = 0;
        }
        vista.getMapa().repintarMapa(tableroAct);
        try {
            //Ponemos a "Dormir" el programa durante los ms que queremos
            Thread.sleep(100);
        } catch (Exception e) {
            System.out.println(e);
        }
        if (y > 0 && minoVivo &&  tableroAct[x][y - 1] != 1 && !camino.contains("" + x + "" + y + "" + x + "" + (y - 1))) {
            if (tableroAct[x][y - 1] == 2) {
                minoVivo = false;
            }
            tableroAct[x][y] = 0;
            tableroAct[x][y - 1] = 3;
            tablero = tableroAct;
            System.out.println("Izquierda");
            camino.add("" + x + "" + y + "" + x + "" + (y - 1));
            recorrer(x, (y - 1), tableroAct, "izquierda");
            tableroAct[x][y] = 3;
            tableroAct[x][y - 1] = 0;
        }
        
        vista.getMapa().repintarMapa(tableroAct);
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private void mostrarLaberinto(){
        for(int i = 0; i < tablero.length; i++){
            for(int j = 0; j < tablero[0].length; j++){
                System.out.print(tablero[i][j]+"-");
            }
            System.out.println("");
        }
    }
    
}
