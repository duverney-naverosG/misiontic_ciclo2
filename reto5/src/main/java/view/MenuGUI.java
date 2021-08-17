package view;

import java.sql.SQLException;
import java.util.ArrayList;
import controller.ControladorRequerimientosReto4;
import model.vo.MaterialNacional;
import model.vo.ProyectoRankeadoCompras;
import model.vo.CargoAsignacion;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import java.awt.Image;


//GUI
public class MenuGUI extends JFrame {

    public static final ControladorRequerimientosReto4 controlador = new ControladorRequerimientosReto4();

    //Atributos de la interfaz
    private JButton btnRequerimiento1;
    private JButton btnRequerimiento2;
    private JButton btnRequerimiento3;
    private JLabel jletiqueta;

    public void iniciarGUI(){

        
        setTitle("Menú Inicial Requerimientos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        ImageIcon icono4 = new ImageIcon("imag/reto5.png"); 
        jletiqueta = new JLabel(icono4);

        ImageIcon icono1 = new ImageIcon("imag/materialesConstruccion.png"); 
        Image img1 = icono1.getImage();
        icono1 = new ImageIcon( img1.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH) );
        btnRequerimiento1 = new JButton(icono1); 
        btnRequerimiento1.setBackground(Color.ORANGE);
        btnRequerimiento1.setForeground(Color.BLACK);
        btnRequerimiento1.setText("Materiales Más Comprados");
        btnRequerimiento1.addActionListener(controlador);
        btnRequerimiento1.setActionCommand("requerimiento1"); 
        
        ImageIcon icono2 = new ImageIcon("imag/rankingProyectos.jpg");
        Image img2 = icono2.getImage();
        icono2 = new ImageIcon( img2.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH) );
        btnRequerimiento2 = new JButton(icono2);
        btnRequerimiento2.setForeground(Color.BLACK);
        btnRequerimiento2.setText("Ranking Proyectos Granito");
        btnRequerimiento2.addActionListener(controlador);
        btnRequerimiento2.setActionCommand("requerimiento2");

        ImageIcon icono = new ImageIcon("imag/cargosLiderados.png");
        Image img = icono.getImage();
        icono = new ImageIcon( img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH) );
        btnRequerimiento3 = new JButton(icono);
        btnRequerimiento3.setBackground(Color.ORANGE);
        btnRequerimiento3.setForeground(Color.BLACK);
        btnRequerimiento3.setText("Cargos Liderando Menos");
        btnRequerimiento3.addActionListener(controlador);
        btnRequerimiento3.setActionCommand("requerimiento3");

        //Añadir los componentes a contenedores o contenendores intermedios
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,1));
        panel.add(jletiqueta);
        panel.add(btnRequerimiento1);
        panel.add(btnRequerimiento2);
        panel.add(btnRequerimiento3);

        getContentPane().add(panel);      

        //Establecer últimas propiedades del frame
        setSize(400,400);
        setLocationRelativeTo(null);
        setVisible(true);

    } 

    public static void requerimiento3(){

        System.out.println("-----Materiales Producción Nacional Más Comprados-------");       

        try{

            ArrayList<MaterialNacional> rankingMaterialesNacionales = controlador.consultarMaterialesNacionalesComprados();

            //Encabezado del resultado
            System.out.println("Nombre_Material Importado No_Compras");
            
            for (MaterialNacional materialNacional : rankingMaterialesNacionales) {
                System.out.printf("%s %s %d %n",
                    materialNacional.getNombreMaterial(),
                    materialNacional.getImportado(),
                    materialNacional.getNoCompras()
                );                
            }

        }catch(SQLException e){
            System.err.println("Ha ocurrido un error!"+e.getMessage());
        }

    } 
    
    public static void requerimiento4(){

        System.out.println("-----Proyectos Mayor Compra de Granito-------");       

        try{

            ArrayList<ProyectoRankeadoCompras> rankingProyectosGranito = controlador.consultarProyectosComprasGranito();

            //Encabezado del resultado
            System.out.println("ID_Proyecto Clasificacion Area_Max No_Compras_Granito");
            
            for (ProyectoRankeadoCompras proyecto : rankingProyectosGranito) {
                System.out.printf("%d %s %d %d %n",
                    proyecto.getIdProyecto(),
                    proyecto.getClasificacion(),
                    proyecto.getAreaMaxima(),
                    proyecto.getNoComprasGranito()
                );
            }

        }catch(SQLException e){
            System.err.println("Ha ocurrido un error!"+e.getMessage());
        }

    }

    public static void requerimiento5(){

        System.out.println("-----Cargos Menos Asignados-------");       

        try{

            ArrayList<CargoAsignacion> cargosMenosAsignados = controlador.consultarCargosMenosAsignados();

            //Encabezado del resultado
            System.out.println("Cargo Número_Proyectos");
            
            for (CargoAsignacion cargo : cargosMenosAsignados) {
                System.out.printf("%s %d %n",
                    cargo.getCargo(),
                    cargo.getNoProyectos()
                );
            }

        }catch(SQLException e){
            System.err.println("Ha ocurrido un error!"+e.getMessage());
        }

    }
}
