package repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Cliente;
import modelo.Servicio;
import modelo.Sucursal;

public class RepositorioServicio {

	//mostrar lista de servicios por sucursal
	public static void serviciosPorSucursal(Sucursal sucursal) throws SQLException {
		Servicio servicio = new Servicio();
		//consulta
		String consulta = "SELECT SE.Descripcion, SE.Coste FROM servicio SE JOIN dispone D ON SE.Codigo=D.CodigoServicio where ? = D.CodigoSucursal";
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
	public static ArrayList<Servicio> serviciosPorSurcursalYAnimal(Sucursal sucursal, String tipoAnimal, ArrayList<Servicio> listaServicios) throws SQLException {
		String consulta = "SELECT SE.Codigo, SE.Descripcion, SE.Coste FROM servicio SE JOIN dispone D ON SE.Codigo = D.CodigoServicio WHERE ? = D.CodigoSucursal AND SE.TipoAnimal = ? OR SE.TipoAnimal = ? GROUP BY CodigoServicio";
		try (PreparedStatement preparedStatement = Conector.conexion.prepareStatement(consulta)) {
			preparedStatement.setInt(1, sucursal.getCodigo());
			preparedStatement.setString(2, tipoAnimal);
			preparedStatement.setString(3, "Todos");
			ResultSet resultSet = preparedStatement.executeQuery();
			int i=1;
			while (resultSet.next()) {
				Servicio servicio = new Servicio(resultSet.getInt("Codigo"), resultSet.getString("Descripcion"), resultSet.getInt("Coste"));
				System.out.println(i+". "+servicio.toString());
				listaServicios.add(servicio);
				i++;
	        }
		}
		return listaServicios;
	}
	
	public static Servicio construir(int codServicio) throws  SQLException {
		String consulta = "SELECT Descripcion, Coste FROM Servicio where Codigo = ?";
		
		Servicio servicio = new Servicio();
		
		try (PreparedStatement preparedStatement = Conector.conexion.prepareStatement(consulta)){
			preparedStatement.setInt(1, codServicio);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				servicio.setDescripcion(resultSet.getString("Descripcion"));
				servicio.setCoste(resultSet.getInt("Coste"));
			}
		}
		
		return servicio;
	}
	
}