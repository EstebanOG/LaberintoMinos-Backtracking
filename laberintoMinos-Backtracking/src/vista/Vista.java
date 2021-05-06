package vista;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Usuario
 */
public class Vista extends JFrame{
    public Mapa mapa;
    private JPanel pPrincipal;
   // private Logica logica;
    private boolean buscarSolucion = true;
    private int tablero[][];
    public Vista(int tablero[][]){

        this.tablero = tablero;
        
        // Propiedades JFrame
        setTitle("Laberinto de Minos");
        setSize (900, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        initComponents();

    }
    
    private void initComponents() {
        //Panel principal
        pPrincipal = new JPanel();
        pPrincipal.setBounds(0, 0, 900, 700);
        pPrincipal.setLayout(null);
        this.getContentPane().add(pPrincipal);
        
        //Panel mapa
        mapa = new Mapa(tablero);
        //mapa.repintarMapa(tablero);
        pPrincipal.add(mapa);
        mapa.setVisible(false);
        mapa.setVisible(true);
        
        pPrincipal.repaint();
    }
    
    public Mapa getMapa() {
        return mapa;
    }

    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }
    
}
