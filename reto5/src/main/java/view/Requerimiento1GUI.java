package view;

import java.util.Scanner;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.IllegalFormatConversionException;
import controller.ControladorRequerimientosReto4;
import model.vo.MaterialNacional;
import model.vo.ProyectoRankeadoCompras;
import model.vo.CargoAsignacion;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.Image;


//GUI
public class Requerimiento1GUI extends JFrame {

    private JTable jtTablaRequerimiento1; 
    private JLabel jletiqueta; 


    public Requerimiento1GUI(ArrayList<MaterialNacional> materiales, ControladorRequerimientosReto4 controlador){
    
        setTitle("-----Materiales Producción Nacional Más Comprados-------");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        String[] encabezados = {"Nombre_Material","Importado","No_Compras"};        
        jtTablaRequerimiento1 = new JTable( this.formatoMaterialesNacionales(materiales, encabezados.length), encabezados );
        ImageIcon icono1 = new ImageIcon("imag/materialesConstruccion.png"); 
        Image img1 = icono1.getImage();
        icono1 = new ImageIcon( img1.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH) );
        jletiqueta = new JLabel(icono1);
        JScrollPane sp = new JScrollPane(jtTablaRequerimiento1);
        JPanel panel = new JPanel();
        sp.setPreferredSize(new Dimension(200, 150)  );
        panel.add(sp);  
        panel.add(jletiqueta);
        getContentPane().add(panel);

        //Propiedades de la ventana
        setSize(400,400);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public String[][] formatoMaterialesNacionales(ArrayList<MaterialNacional> materiales, int numeroColumnas){
        
        String[][] registros = new String[materiales.size()][numeroColumnas];

        for (int i = 0; i < registros.length; i++) {
            //Desempaquetar cada material en una fila           
            registros[i][0] = materiales.get(i).getNombreMaterial();//Cargar el nombre del material
            registros[i][1] = materiales.get(i).getImportado();
            registros[i][2] = String.valueOf(materiales.get(i).getNoCompras());           
        }

        //Retornar registros en formato de arreglo convencional
        return registros;

    }
    
}

