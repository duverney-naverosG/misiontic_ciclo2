package model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.vo.CargoAsignacion;
import util.JDBCUtilities;

public class CargoAsignacionDao {
    public ArrayList<CargoAsignacion> cargosMenosAsignados() throws SQLException {

        ArrayList<CargoAsignacion> respuesta = new ArrayList<CargoAsignacion>();

        //Declarar la conexión
        Connection conexion = null;
        try{

            conexion = JDBCUtilities.getConnection();

            String consulta = "SELECT l.Cargo, "+
                                        "COUNT (p.ID_Proyecto) AS Numero_Proyectos "+
                                "FROM Proyecto p "+
                                "JOIN Lider l ON "+
                                "p.ID_Lider = l.ID_Lider "+
                                "GROUP BY l.Cargo "+
                                "ORDER BY Numero_Proyectos "+
                                "LIMIT 2 ";

            //Construir objeto que realizará la consulta
            PreparedStatement statement = conexion.prepareStatement(consulta);

            //Realizar propiamente la consulta
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){                
                CargoAsignacion cargo = new CargoAsignacion();
                cargo.setCargo(resultSet.getString("Cargo"));
                cargo.setNoProyectos(resultSet.getInt("Numero_Proyectos"));            
                
                respuesta.add(cargo);
            }

            resultSet.close();
            statement.close();


        }catch(SQLException e){
            System.err.println("Error consultando cargos menos asignado!! " + e);
            
        }finally{
            if(conexion != null){
                conexion.close();
            }
        }
        return respuesta;
    }
}
