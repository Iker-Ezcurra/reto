package repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Cliente;
import modelo.Servicio;
import modelo.Sucursal;

public class RepositorioServicio {

	//mostrar lista de servicios por sucursal
	public static void serviciosPorSucursal(Sucursal sucursal) throws SQLException {
		Servicio servicio = new Servicio();
		//consulta
		String consulta = "SELECT SE.Descripcion, SE.Coste FROM sucursal SU JOIN dispone D ON SU.Codigo=D.CodigoSucursal JOIN servicio SE ON SE.Codigo=D.CodigoServicio where ? = SU.Codigo";
		try (PreparedStatement preparedStatement = Conector.conexion.prepareStatement(consulta)) {
			preparedStatement.setInt(1, sucursal.getCodigo()); 
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				servicio.setDescripcion(resultSet.getString("Descripcion"));
				servicio.setCoste(resultSet.getInt("Coste"));
				System.out.println(servicio.toString());
	        }
		}
	}
	
	//mostrar lista de servicios segun el animal y la sucursal
	public static void serviciosPorSurcursalYAnimal(Sucursal sucursal, String tipoAnimal) throws SQLException {
		String consulta = "SELECT SE.Descripcion, SE.Coste FROM Sucursal SU JOIN Dispone D ON Su.Codigo = D.CodigoSucursal JOIN Servicio SE ON D.CodigoServicio = SE.Codigo WHERE SU.Codigo = ? AND SE.TipoAnimal = ? OR SE.TipoAnimal = ? GROUP BY SE.Descripcion, SE.Coste";
		try (PreparedStatement preparedStatement = Conector.conexion.prepareStatement(consulta)) {
			preparedStatement.setInt(1, sucursal.getCodigo());
			preparedStatement.setString(2, tipoAnimal);
			preparedStatement.setString(3, "Todos");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Servicio servicio = new Servicio(resultSet.getString("Descripcion"), resultSet.getInt("Coste"));
				System.out.println(servicio.toString());
	        }
		}
	}
	
	public static Servicio construir(Servicio servicio) throws  SQLException {
		String consulta = "SELECT Descripcion, Coste FROM Servicio where Codigo = ?";
		try (PreparedStatement preparedStatement = Conector.conexion.prepareStatement(consulta)){
			preparedStatement.setInt(1, servicio.getCodigo());
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				servicio.setDescripcion(resultSet.getString("Descripcion"));
				servicio.setCoste(resultSet.getInt("Coste"));
			}
		}
		return servicio;
	}
	
}