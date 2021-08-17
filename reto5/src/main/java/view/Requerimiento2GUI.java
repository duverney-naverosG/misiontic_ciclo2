package view;

import java.util.ArrayList;
import controller.ControladorRequerimientosReto4;
import model.vo.ProyectoRankeadoCompras;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import java.awt.GridLayout;
import java.awt.Image;


//GUI
public class Requerimiento2GUI extends JFrame {

    private JTable jtTablaRequerimiento1;    
    private JLabel jletiqueta;
    public Requerimiento2GUI(ArrayList<ProyectoRankeadoCompras> proyectos, ControladorRequerimientosReto4 controlador){        
        
        setLayout(new GridLayout());
        
        setTitle("-----Proyectos Mayor Compra Granito-------");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        String[] encabezados = {"ID_Proyecto","Clasificación","Area_Max","No_Compras_Granito"};        
        jtTablaRequerimiento1 = new JTable(this.formatoRegistro(proyectos, encabezados.length), encabezados );
        ImageIcon icono1 = new ImageIcon("imag/rankingProyectos.jpg"); 
        Image img1 = icono1.getImage();
        icono1 = new ImageIcon( img1.getScaledInstance(400, 200, java.awt.Image.SCALE_SMOOTH) );
        jletiqueta = new JLabel(icono1);

        JScrollPane sp = new JScrollPane(jtTablaRequerimiento1);
        JPanel panel = new JPanel(new GridLayout(2,1));
        panel.add(sp);  
        panel.add(jletiqueta);
        panel.setBorder(new TitledBorder("Compras Granito"));      
        getContentPane().add(panel);//Incorporar tabla

        //Propiedades de la ventana
        setSize(600,400);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public String[][] formatoRegistro(ArrayList<ProyectoRankeadoCompras> proyectos, int numeroColumnas){
        String[][] registros = new String[proyectos.size()][numeroColumnas];

        for (int i = 0; i < registros.length; i++) {           
            registros[i][0] = String.valueOf(proyectos.get(i).getIdProyecto());
            registros[i][1] = proyectos.get(i).getClasificacion();
            registros[i][2] = String.valueOf(proyectos.get(i).getAreaMaxima());           
            registros[i][3] = String.valueOf(proyectos.get(i).getNoComprasGranito());           
        }
        return registros;

    }
   
}

