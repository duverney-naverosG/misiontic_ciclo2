package model.dao;

import util.JDBCUtilities;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import model.vo.MaterialNacional;

import java.sql.ResultSet;

public class MaterialNacionalDao {
    public ArrayList<MaterialNacional> topMaterialesNacionales() throws SQLException {

        ArrayList<MaterialNacional> respuesta = new ArrayList<MaterialNacional>();

        Connection conexion = null;

        try{

            conexion = JDBCUtilities.getConnection();

            String consulta = "SELECT  MaterialConstruccion.Nombre_Material, "+
                                "MaterialConstruccion.Importado, "+
                                    "COUNT(*) as No_Compras "+
                                "FROM MaterialConstruccion "+
                                "INNER JOIN Compra c "+
                                "ON MaterialConstruccion.ID_MaterialConstruccion = c.ID_MaterialConstruccion "+
                                "WHERE MaterialConstruccion.Importado = 'No' "+
                                "GROUP BY MaterialConstruccion.ID_MaterialConstruccion "+
                                "ORDER BY No_Compras DESC,"+ 
                                    "MaterialConstruccion.Nombre_Material ASC";

            PreparedStatement statement = conexion.prepareStatement(consulta);

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){                
                MaterialNacional material = new MaterialNacional();
                material.setNombreMaterial(resultSet.getString("Nombre_Material"));
                material.setImportado(resultSet.getString(2));
                material.setNoCompras(resultSet.getInt("No_Compras"));
                
                respuesta.add(material);
            }

            resultSet.close();
            statement.close();


        }catch(SQLException e){
            System.err.println("Error consultando ranking de materiales nacionales!! " + e);
            
        }finally{
            if(conexion != null){
                conexion.close();
            }
        }

        return respuesta;
    } 
}
