package repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RepositorioServicio {

	//mostrar lista de servicios por sucursal
	public static void serviciosPorSucursal(int sucursal) throws SQLException {
		//consulta
		String consulta = "SELECT SE.Descripcion, SE.Coste FROM sucursal SU JOIN dispone D ON SU.Codigo=D.CodigoSucursal JOIN servicio SE ON SE.Codigo=D.CodigoServicio where ? = SU.Codigo";
		try (PreparedStatement preparedStatement = Conector.conexion.prepareStatement(consulta)) {
			preparedStatement.setInt(1, sucursal); 
			ResultSet resultSet = preparedStatement.executeQuery();
			//variable con valor de las columnas que cumplen la consulta
			int columnCount = resultSet.getMetaData().getColumnCount();
			//imprimir filas de la tabla
			while (resultSet.next()) {
				for (int i = 1; i <= columnCount; i++) {
					if (i == 1) {
						System.out.print(resultSet.getString(i) + "\t"+"\t");
					} else {
						System.out.print(resultSet.getString(i) + "€");
					}
	            }
	            System.out.println();
	        }
		}
	}
	
	//mostrar lista de servicios segun el animal y la sucursal
	public static int serviciosPorSurcursalYAnimal(int sucursal, String tipoAnimal) throws SQLException {
		int j = 0;
		String consulta = "SELECT SE.Descripcion, SE.Coste FROM Sucursal SU JOIN Dispone D ON Su.Codigo = D.CodigoSucursal JOIN Servicio SE ON D.CodigoServicio = SE.Codigo WHERE SU.Codigo = ? AND SE.TipoAnimal = ? OR SE.TipoAnimal = Todos GROUP BY SE.Descripcion, SE.Coste";
		try (PreparedStatement preparedStatement = Conector.conexion.prepareStatement(consulta)) {
			preparedStatement.setInt(1, sucursal);
			preparedStatement.setString(2, tipoAnimal);
			ResultSet resultSet = preparedStatement.executeQuery();
			//variable con valor de las columnas que cumplen la consulta
			int columnCount = resultSet.getMetaData().getColumnCount();
			//imprimir filas de la tabla
			while (resultSet.next()) {
				for (int i = 1; i <= columnCount; i++) {
					if (i == 1) {
						System.out.print(resultSet.getString(i) + "\t" + "\t");
						j++;
					} else {
						System.out.print(resultSet.getString(i) + "€");
					}
	            }
	            System.out.println();
	        }
		}
		return j;
	}
	
	//devuelve el precio de un servicio
	public static int precio(int servicio) throws SQLException {
		//consulta
		int coste = 0;
		String consulta = "SELECT SE.Coste FROM servicio where ? = SE.Codigo";
		try (PreparedStatement preparedStatement = Conector.conexion.prepareStatement(consulta)) {
			preparedStatement.setInt(1, servicio); 
			ResultSet resultSet = preparedStatement.executeQuery();
			//variable con valor de las columnas que cumplen la consulta
			int columnCount = resultSet.getMetaData().getColumnCount();
			//imprimir filas de la tabla
			if (resultSet.next()) {
				coste = resultSet.getInt("Coste");
			}
		}
		return coste;
	}
}