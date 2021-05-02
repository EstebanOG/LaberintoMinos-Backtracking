package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author Usuario
 */
public class Mapa extends JPanel {

    private JButton matrizCasillas[][];
    private Border bordeDefault;
     

    public Mapa(int[][] tablero) {
        //Atributos de JPanel
        this.setBounds(135, 65, 630, 570);
        this.setBackground(Color.blue);
        bordeDefault = new LineBorder(Color.black);

        //Matriz de botones
        matrizCasillas = new JButton[19][21];
        for (int i = 0; i < matrizCasillas.length; i++) {
            for (int j = 0; j < matrizCasillas[i].length; j++) {
                matrizCasillas[i][j] = new JButton();

                matrizCasillas[i][j].setPreferredSize(new Dimension(30, 30));
                matrizCasillas[i][j].setFont(new Font("Arial", Font.BOLD, 30));
                if (tablero[i][j] == 1) {
                    matrizCasillas[i][j].setBorder(bordeDefault);
                    matrizCasillas[i][j].setBackground(new Color(153, 204, 255));
                } else {
                    matrizCasillas[i][j].setBorder(null);
                }
                matrizCasillas[i][j].setEnabled(false);
                this.add(matrizCasillas[i][j]);
            }
            setLayout(new GridLayout(19, 21));
        }
    }
    
    /**
     * Función para repintar el laberinto en la interfaz gráfica.
     */
    public void repintarMapa(int[][] tableroActualizado){
        for (int i = 0; i < matrizCasillas.length; i++) {
            for (int j = 0; j < matrizCasillas[i].length; j++) {
                
                if(tableroActualizado[i][j] == 2){
                    matrizCasillas[i][j].setText("M");
                   
                }
                
                if(tableroActualizado[i][j] == 3){
                    matrizCasillas[i][j].setText("T");
                }
                
                if(tableroActualizado[i][j] == 4){
                    matrizCasillas[i][j].setText("J");
                }
                
                if(tableroActualizado[i][j] == 0){
                    matrizCasillas[i][j].setText("");
                }
                
            }
        }
    }
    
    
}
