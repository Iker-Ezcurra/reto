package repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Sucursal;

public class RepositorioSucursal {
	
	public static Sucursal contruirSucursal(int codSucursal) throws SQLException {
		String consulta = "SELECT Direccion, localidad FROM Sucursal WHERE Codigo=?";
		Sucursal sucursal = new Sucursal(codSucursal);
		try (PreparedStatement preparedStatement = Conector.conexion.prepareStatement(consulta)){
			preparedStatement.setInt(1, codSucursal);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				sucursal.setDireccion(resultSet.getString("Direccion"));
				sucursal.setLocalidad(resultSet.getString("Localidad"));
			}		
		}
		return sucursal;
	} 

}