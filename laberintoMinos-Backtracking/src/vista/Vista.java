/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Usuario
 */
public class Vista extends JFrame{
    private Mapa mapa;
    private JPanel pPrincipal;
    /**
     * 1: Bloque
     * 2: Camino
     */
    private int tablero[][] = {{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                               {0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1},
                               {1,0,1,1,1,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1},
                               {1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1},
                               {1,0,1,0,1,0,1,1,1,1,1,1,1,1,1,1,1,0,1,0,1},
                               {1,0,1,0,1,0,0,0,0,1,0,0,0,0,0,0,1,0,1,0,1},
                               {1,0,1,0,1,0,1,1,1,1,0,1,1,1,1,0,1,0,1,0,1},
                               {1,0,1,0,1,0,1,0,0,0,0,0,0,0,1,0,1,0,1,0,1},
                               {1,0,1,0,1,0,1,0,0,0,0,0,0,0,1,0,1,1,1,0,1},
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
    public Vista(){
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
        pPrincipal.setBackground(Color.red);
        this.getContentPane().add(pPrincipal);
        
        //Panel mapa
        mapa = new Mapa(tablero);
        pPrincipal.add(mapa);
        mapa.setVisible(false);
        mapa.setVisible(true);
        
        pPrincipal.repaint();
    }
}
