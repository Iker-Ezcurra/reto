package repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Sucursal;

public class RepositorioSucursal {
	
	//Dado el código de una sucursal, instancia una con los datos de ese código en la base de datos
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
	
	public static int sucursales() throws SQLException{
		String consulta = "SELECT * FROM Sucursal";
		int i = 1;
		try (PreparedStatement preparedStatement = Conector.conexion.prepareStatement(consulta)){
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Sucursal sucursal = new Sucursal(i, resultSet.getString("Localidad"));
				System.out.println(i + ". " + sucursal.getLocalidad());
				i++;
			}
		}
		return i;
	}

}