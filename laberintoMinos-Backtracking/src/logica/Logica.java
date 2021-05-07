package logica;

import java.util.ArrayList;
import java.util.Arrays;
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
     * 3: Teseo
     * 4: Jovenes
     */
    
    private int numPasos = 0,numPasosCambio = 13, numJovenes = 0, numJovenesEncontrados = 0, xAnterior = 0, yAnterior = 0;
    private boolean minoVivo = true, juego = true;
    private ArrayList<String> camino = new ArrayList<String>();
    private int[] posicionesJovenes = new int[6];
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
    private Vista vista = new Vista(tablero);
    
    public Logica() {
        
        generarJovenes();
        vista.getMapa().repintarMapa(tablero);
        while(juego){
            if(numJovenesEncontrados>0){
                juego = false;
            }
            camino.clear();
            recorrer(1,0,tablero);
            
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
    
    public void recorrer(int x, int y, int[][] tableroAct) {
        
        //Mostrar pasos realizados(guardados)
        //System.out.println(camino);
        
        pausar();
        vista.getMapa().repintarMapa(tableroAct);
        
        /**
         * MOVIMIENTO ARRIBA
         * x>0: Verifica los bordes del laberinto, ya que evita que Teseo se
         *      mueva a una posición invalda.
         * 
         * tableroAct[x-1][y] != 1: Verifica si la casilla que se encuentra
         * arriba no es un muro.
         * 
         * camino.contains(...): Verifica si ese paso ya se realizó.
         * 
         * numJovenesEncontrados == 0: Verifica que hasta el momento no se haya
         * encontrado ningún joven, ya que de ser así, no será necesario seguir
         * avanzando y Teseo empecerá a devolverse al punto de partida.
         */
        if (x>0 && tableroAct[x - 1][y] != 1 && !camino.contains("" + x + "" + y + "" + (x - 1) + "" + y)
                && numJovenesEncontrados == 0) {
            
            //Se verifica si el minotauro se encuentra arriba
            if (tableroAct[x - 1][y] == 2) {
                notificar(0);
                //Se mata al minotauro
                minoVivo = false;
                tableroAct[x - 1][y] = 3;
            }
            
            // Se verifica si hay un joven
            if(tableroAct[x - 1][y] == 4){
                
                //Se verifica si el minotaruo está vivo y de no ser así, se
                //recoge al niño y se notifica
                if(!minoVivo){
                    notificar(3);
                    numJovenesEncontrados++;
                    tableroAct[x - 1][y] = 0;
                }else{
                    tableroAct[x - 1][y] = 4;
                }
            }
            
            /**
             * Si la casilla que se encuentra arriba esta vacía, se ubica a
             * Teseo en esa posición.
             */
            if(tableroAct[x - 1][y] == 0)
                tableroAct[x - 1][y] = 3;
            
            /**
             * Se borra borra posición anterior de Teseo.
             */
            if(tableroAct[x][y] != 4)
                tableroAct[x][y] = 0;
            
                
            contarPasos();
            
            /**
             * Se agrega paso al arreglo de pasos realizados por teseo.
             */
            camino.add("" + x + "" + y + "" + (x - 1) + "" + y);
            
            /**
             * Se guarda paso actual.
             */
            xAnterior = x;
            yAnterior = y;
            
            /**
             * Se vuelve a llamar al método recorrer, pero se le pasa por
             * parametroes, la nueva posición de Teseo y el nuevo tablero.
             */
            recorrer((x - 1), y, tableroAct);
            
            /**
             * Se borran los cambios producidos en el tablero, antes de realizar
             * el llamado recursivo.
             */
            if(tableroAct[x - 1][y] == 4){
                tableroAct[x][y] = 3;
//                tableroAct[x - 1][y] = 4;
            }else if(tableroAct[x][y] == 4){
                tableroAct[x - 1][y] = 0;
            }else{
                tableroAct[x][y] = 3;
                tableroAct[x - 1][y] = 0;
            }
            
            contarPasos();
        }
        vista.getMapa().repintarMapa(tableroAct);
        pausar();
        
        /**
         * MOVIMIENTO DERECHA
         * 
         * tableroAct[x][y + 1] != 1: Verifica si la casilla que se encuentra
         * a la derecha no es un muro.
         * 
         * camino.contains(...): Verifica si ese paso ya se realizó.
         * 
         * numJovenesEncontrados == 0: Verifica que hasta el momento no se haya
         * encontrado ningún joven, ya que de ser así, no será necesario seguir
         * avanzando y Teseo empecerá a devolverse al punto de partida.
         */
        if (tableroAct[x][y + 1] != 1 && !camino.contains("" + x + "" + y + "" + x + "" + (y + 1))
                && numJovenesEncontrados == 0) {
            if (tableroAct[x][y + 1] == 2) {
                notificar(0);
                minoVivo = false;
                tableroAct[x][y + 1] = 3;
            }
            
            if(tableroAct[x][y + 1] == 4){
                if(!minoVivo){
                    notificar(3);
                    numJovenesEncontrados++;
                    tableroAct[x][y + 1] = 0;
                }else{
                    tableroAct[x][y + 1] = 4;
                }
            }
            if(tableroAct[x][y + 1] == 0)
                tableroAct[x][y + 1] = 3;
            
            if(tableroAct[x][y] != 4)
                tableroAct[x][y] = 0;
            
            contarPasos();
            camino.add("" + x + "" + y + "" + x + "" + (y + 1));
            xAnterior = x;
            yAnterior = y;
            recorrer(x, (y + 1), tableroAct);
            
            if(tableroAct[x][y + 1] == 4){
                tableroAct[x][y] = 3;
                //tableroAct[x][y + 1] = 4;
            }else if(tableroAct[x][y] == 4){
                tableroAct[x][y + 1] = 0;
            }else{
                tableroAct[x][y] = 3;
                tableroAct[x][y + 1] = 0;
            }
            
            contarPasos();
        }
        vista.getMapa().repintarMapa(tableroAct);
        pausar();
        
        /**
         * MOVIMIENTO ABAJO
         * 
         * tableroAct[x + 1][y] != 1: Verifica si la casilla que se encuentra
         * abajo no es un muro.
         * 
         * camino.contains(...): Verifica si ese paso ya se realizó
         * 
         * numJovenesEncontrados == 0: Verifica que hasta el momento no se haya
         * encontrado ningún joven, ya que de ser así, no será necesario seguir
         * avanzando y Teseo empecerá a devolverse al punto de partida.
         */
        if (tableroAct[x + 1][y] != 1 && !camino.contains("" + x + "" + y + "" + (x + 1) + "" + y)
                && numJovenesEncontrados == 0) {
            if (tableroAct[x + 1][y] == 2) {
                notificar(0);
                minoVivo = false;
                tableroAct[x + 1][y] = 3;
            }
            
            if(tableroAct[x + 1][y] == 4){
                if(!minoVivo){
                    notificar(3);
                    numJovenesEncontrados++;
                    tableroAct[x + 1][y] = 0;
                }else{
                    tableroAct[x + 1][y] = 4;
                }
            }
            
            if(tableroAct[x + 1][y] == 0)
                tableroAct[x + 1][y] = 3;
            
            
            if(tableroAct[x][y] != 4)
                tableroAct[x][y] = 0;
            
            contarPasos();
            camino.add("" + x + "" + y + "" + (x + 1) + "" + y);
            xAnterior = x;
            yAnterior = y;
            recorrer((x + 1), y, tableroAct);
            if(tableroAct[x + 1][y] == 4){
                tableroAct[x][y] = 3;
                //tableroAct[x + 1][y] = 4;
            }else if(tableroAct[x][y] == 4){
                tableroAct[x + 1][y] = 0;
            }else{
                tableroAct[x][y] = 3;
                tableroAct[x + 1][y] = 0;
            }
            
            contarPasos();
        }
        vista.getMapa().repintarMapa(tableroAct);
        pausar();
        
        /**
         * MOVIMIENTO IZQUIERDA
         * 
         * tableroAct[x][y - 1] != 1: Verifica si la casilla que se encuentra
         * a la izquierda no es un muro.
         * 
         * camino.contains(...): Verifica si ese paso ya se realizó
         * 
         * numJovenesEncontrados == 0: Verifica que hasta el momento no se haya
         * encontrado ningún joven, ya que de ser así, no será necesario seguir
         * avanzando y Teseo empecerá a devolverse al punto de partida.
         */
        if (y > 0 && tableroAct[x][y - 1] != 1 && !camino.contains("" + x + "" + y + "" + x + "" + (y - 1))
                && numJovenesEncontrados == 0) {
            if (tableroAct[x][y - 1] == 2) {
                notificar(0);
                minoVivo = false;
                tableroAct[x][y - 1] = 3;
            }
            
            if(tableroAct[x][y - 1] == 4){
                if(!minoVivo){
                    notificar(3);
                    numJovenesEncontrados++;
                    tableroAct[x][y - 1] = 0;
                }else{
                    tableroAct[x][y - 1] = 4;
                }
            }
            
            if(tableroAct[x][y - 1] == 0)
                tableroAct[x][y - 1] = 3;
            
            if(tableroAct[x][y] != 4)
                tableroAct[x][y] = 0;

            contarPasos();
            camino.add("" + x + "" + y + "" + x + "" + (y - 1));
            xAnterior = x;
            yAnterior = y;
            recorrer(x, (y - 1), tableroAct);
            if(tableroAct[x][y - 1] == 4){
               tableroAct[x][y] = 3;
                //tableroAct[x][y - 1] = 4; 
            }else if(tableroAct[x][y] == 4){
                tableroAct[x][y - 1] = 0;
                
            }else{
                tableroAct[x][y] = 3;
                tableroAct[x][y - 1] = 0;
            }
            
            contarPasos();
        }
        
        
        /**
         * Se borran los jovenes rescatados que siguen a Teseo para
         * después actualizar su posición.
         */
        if(numJovenesEncontrados > 0){
            for(int i = 0; i<numJovenesEncontrados; i++)
                tablero[posicionesJovenes[i*2]][posicionesJovenes[(i*2)+1]] = 0;
        }
        
        /**
         * Se verifica si existen jovenes en la posición actual,
         * al momento de retroceder en el algoritmo y los rescata.
         */
        if(tablero[x][y] == 4 && !minoVivo){
            notificar(3);
            numJovenesEncontrados++;
            tableroAct[x][y] = 0;
        }
        
        /**
         * Se pintan los jovenes rescatados en el mapa, se hace uso del
         * arreglo posicionesJovenes que guarda últimos pasos de Teseo y se
         * ubican allí los jovenes.
         */
        if(numJovenesEncontrados > 0){
            // Mostrar pasos de Teseo guardados
            //System.out.println(Arrays.toString(posicionesJovenes));
            
            /**
             * Actualización de las posiciones de los jovenes que siguen a Teseo,
             * caso 2 jovenes.
            **/
            if(numJovenesEncontrados == 2){   
                posicionesJovenes[2] = posicionesJovenes[0];
                posicionesJovenes[3] = posicionesJovenes[1];
                tablero[posicionesJovenes[2]][posicionesJovenes[3]] = 4;
            }
            
            /**
             * Actualización de las posiciones de los jovenes que siguen a Teseo,
             * caso 3 jovenes.
            **/
            if(numJovenesEncontrados == 3){
                posicionesJovenes[4] = posicionesJovenes[2];
                posicionesJovenes[5] = posicionesJovenes[3];
                posicionesJovenes[2] = posicionesJovenes[0];
                posicionesJovenes[3] = posicionesJovenes[1];
                tablero[posicionesJovenes[2]][posicionesJovenes[3]] = 4;
                tablero[posicionesJovenes[4]][posicionesJovenes[5]] = 4;
            }
            
            /**
             * Actualización de la última posición de Teseo, que corresponde a 
             * la posición del joven rescatado.
             */
            posicionesJovenes[0] = xAnterior;
            posicionesJovenes[1] = yAnterior;
            tablero[xAnterior][yAnterior] = 4;
        }
        vista.getMapa().repintarMapa(tableroAct);
        pausar();
        
        /**
         * Se guarda el último paso de Teseo
         */
        xAnterior = x;
        yAnterior = y;
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
            Thread.sleep(20);
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
                JOptionPane.showMessageDialog(null, "Juego terminado.\nJovenes rescatados: "+numJovenesEncontrados);
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
