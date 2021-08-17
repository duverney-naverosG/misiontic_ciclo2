package model.dao;

import util.JDBCUtilities;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import model.vo.ProyectoRankeadoCompras;

import java.sql.ResultSet;

public class ProyectoRankeadoComprasDao {
    public ArrayList<ProyectoRankeadoCompras> topProyectosComprasGranito() throws SQLException {

        ArrayList<ProyectoRankeadoCompras> respuesta = new ArrayList<ProyectoRankeadoCompras>();

        Connection conexion = null;

        try{

            conexion = JDBCUtilities.getConnection();

            String consulta = "SELECT  Proyecto.ID_Proyecto, "+ "Proyecto.Clasificacion, " +"Tipo.Area_Max, "+
                                    "COUNT(MaterialConstruccion.Nombre_Material) as No_Compras_Granito "+ 
                                "FROM Proyecto "+
                                "JOIN Compra c ON "+
                                "Proyecto.ID_Proyecto = c.ID_Proyecto "+
                                "JOIN MaterialConstruccion ON "+
                                "MaterialConstruccion.ID_MaterialConstruccion = c.ID_MaterialConstruccion "+
                                "JOIN Tipo ON "+
                                "Proyecto.ID_Tipo = Tipo.ID_Tipo "+
                                "WHERE MaterialConstruccion.Nombre_Material LIKE 'Granito' "+
                                "GROUP BY Proyecto.ID_Proyecto " +
                                "ORDER BY No_Compras_Granito DESC " +
                                "LIMIT 5 ";

            PreparedStatement statement = conexion.prepareStatement(consulta);

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){                
                ProyectoRankeadoCompras proyecto = new ProyectoRankeadoCompras();
                proyecto.setIdProyecto(resultSet.getInt("ID_Proyecto"));
                proyecto.setClasificacion(resultSet.getString("Clasificacion"));
                proyecto.setAreaMaxima(resultSet.getInt("Area_Max"));
                proyecto.setNoComprasGranito(resultSet.getInt("No_Compras_Granito"));                
                respuesta.add(proyecto);
            }

            resultSet.close();
            statement.close();


        }catch(SQLException e){
            System.err.println("Error consultando ranking de proyectos que compran más granito!! " + e);
            
        }finally{
            if(conexion != null){
                conexion.close();
            }
        }

        return respuesta;
    }   
}
