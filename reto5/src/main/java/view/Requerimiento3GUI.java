package view;

import java.util.ArrayList;
import controller.ControladorRequerimientosReto4;
import model.vo.CargoAsignacion;

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
public class Requerimiento3GUI extends JFrame {

    private JTable jtTablaRequerimiento3;    
    private JLabel jletiqueta;

    public Requerimiento3GUI(ArrayList<CargoAsignacion> cargosAsignados, ControladorRequerimientosReto4 controlador){        
        
        setLayout(new GridLayout());
        
        setTitle("-----Cargos Menos Asignados-------");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        String[] encabezados = {"Cargo","No_Proyectos"};        
        jtTablaRequerimiento3 = new JTable(this.formatoRegistro(cargosAsignados, encabezados.length),encabezados );
        ImageIcon icono1 = new ImageIcon("imag/cargosLiderados.png"); 
        Image img1 = icono1.getImage();
        icono1 = new ImageIcon( img1.getScaledInstance(300, 200, java.awt.Image.SCALE_SMOOTH) );
        jletiqueta = new JLabel(icono1);
        JScrollPane sp = new JScrollPane(jtTablaRequerimiento3);
        JPanel panel = new JPanel( new GridLayout(2,1) );
        panel.add(sp);  
        panel.add(jletiqueta);
        panel.setBorder(new TitledBorder("Cargos Menos Asignados"));      
        getContentPane().add(panel);//Incorporar tabla

        //Propiedades de la ventana
        setSize(600,400);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public String[][] formatoRegistro(ArrayList<CargoAsignacion> cargosAsignados, int numeroColumnas){
        String[][] registros = new String[cargosAsignados.size()][numeroColumnas];

        for (int i = 0; i < registros.length; i++) {                     
            registros[i][0] = cargosAsignados.get(i).getCargo();            
            registros[i][1] = String.valueOf(cargosAsignados.get(i).getNoProyectos());
        }
        return registros;

    } 
}