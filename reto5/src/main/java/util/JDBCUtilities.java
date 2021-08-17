package util;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtilities {
    private static final String UBICACION_BD = "C://Users//DUVERNEY//Documents//mision tic//ciclo 2//programacion//unidad 4//ProyectosConstruccion.db";    

    public static Connection getConnection() throws SQLException {        
        String url = "jdbc:sqlite:" + UBICACION_BD;
        return DriverManager.getConnection(url);
    }

    public static boolean estaVacia(){
        File archivo = new File(JDBCUtilities.UBICACION_BD);

        System.out.println("Líneas identificadas: "+archivo.length());

        return archivo.length() == 0;
    }
}
