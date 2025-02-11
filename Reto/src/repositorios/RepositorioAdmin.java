package repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RepositorioAdmin {

	//Instancia todas las citas y las escribe en pantalla
	public static void consultarCitas() throws SQLException {
		String consulta = "SELECT * FROM Cita";
		Statement statement = Conector.conexion.createStatement();
		ResultSet resultSet = statement.executeQuery(consulta);
		while (resultSet.next()) {
			System.out.println("Codigo: " + resultSet.getInt("Codigo") + " CodigoAnimal: " + resultSet.getString("CodAnimal") + " CodigoServicio: " + resultSet.getInt("CodigoServicio") + " CodigoSucursal: " + resultSet.getInt("CodSucursal") + " Precio: " + resultSet.getInt("CosteTotal") + " Fecha: " + resultSet.getString("Fecha") + " FechaFin: " + resultSet.getString("FechaFin") + " Hora: " + resultSet.getString("Hora"));
		}
	}

	//Dado el código de un servicio, se instancian todas las citas que tengan ese servicio y se escriben en pantalla
	public static void consultarCitasServicio(int codServ) {
		String consulta = "SELECT * FROM Cita WHERE CodigoServicio = ?";
		try (PreparedStatement checkStmt = Conector.conexion.prepareStatement(consulta)) {
			checkStmt.setInt(1, codServ);
			ResultSet resultSet = checkStmt.executeQuery();
			while (resultSet.next()) {
				System.out.println("Codigo: " + resultSet.getInt("Codigo") + " CodigoAnimal: " + resultSet.getString("CodAnimal") + " CodigoServicio: " + resultSet.getInt("CodigoServicio") + " CodigoSucursal: " + resultSet.getInt("CodSucursal") + " Precio: " + resultSet.getInt("CosteTotal") + " Fecha: " + resultSet.getString("Fecha") + " FechaFin: " + resultSet.getString("FechaFin") + " Hora: " + resultSet.getString("Hora"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//Dado el código de una sucursal, se instancian todas las citas que sean en esa sucursal y se escriben en pantalla
	public static void consultarCitasOficina(int codOf) {
		String consulta = "SELECT * FROM Cita WHERE CodSucursal = ?";
		try (PreparedStatement checkStmt = Conector.conexion.prepareStatement(consulta)) {
			checkStmt.setInt(1, codOf);
			ResultSet resultSet = checkStmt.executeQuery();
			while (resultSet.next()) {
				System.out.println("Codigo: " + resultSet.getInt("Codigo") + " CodigoAnimal: " + resultSet.getString("CodAnimal") + " CodigoServicio: " + resultSet.getInt("CodigoServicio") + " CodigoSucursal: " + resultSet.getInt("CodSucursal") + " Precio: " + resultSet.getInt("CosteTotal") + " Fecha: " + resultSet.getString("Fecha") + " FechaFin: " + resultSet.getString("FechaFin") + " Hora: " + resultSet.getString("Hora"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//Escribe en pantalla la cantidad total de citas
	public static void consultarCantidadCitas() throws SQLException {
		String consulta = "SELECT COUNT(Codigo) FROM Cita";
		Statement statement = Conector.conexion.createStatement();
		ResultSet resultSet = statement.executeQuery(consulta);
		resultSet.next();
		System.out.println("La cantidad de citas es de: " + resultSet.getInt(1));
	}

	//Escribe en pantalla la cantidad de total de clientes
	public static void consultarCantidadClientes() throws SQLException {
		String consulta = "SELECT COUNT(DISTINCT C.DNI) FROM cliente C JOIN animales A ON C.DNI=A.DNICliente JOIN cita CI ON A.CodigoChip=CI.CodAnimal";
		Statement statement = Conector.conexion.createStatement();
		ResultSet resultSet = statement.executeQuery(consulta);
		resultSet.next();
		System.out.println("La cantidad de clientes es de: " + resultSet.getInt(1));
	}

	//Escribe en pantalla los ingresos totales
	public static void consultarIngresos() throws SQLException {
		String consulta = "SELECT SUM(CosteTotal) FROM cita";
		Statement statement = Conector.conexion.createStatement();
		ResultSet resultSet = statement.executeQuery(consulta);
		resultSet.next();
		System.out.println("La cantidad de ingresos generados en total es: "+resultSet.getInt(1));
	}

	//Dado el código de un servicio se escribe en pantalla los ingresos de las citas que tengan ese servicio
	public static void consultarIngresosServicio(int codServicio) throws SQLException {
		String consulta = "SELECT SUM(CosteTotal) FROM cita WHERE CodigoServicio = ?";
		try (PreparedStatement statement = Conector.conexion.prepareStatement(consulta)){
			statement.setInt(1, codServicio);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			System.out.println("La cantidad de ingresos generados en total por ese servicio es: "+resultSet.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//Dado el código de una sucursal se escribe en pantalla los ingresos de las citas que tengan esa sucursal
	public static void consultarIngresosOficina(int codOficina) throws SQLException {
		String consulta = "SELECT SUM(CosteTotal) FROM cita WHERE CodSucursal = ?";
		try (PreparedStatement statement = Conector.conexion.prepareStatement(consulta)){
			statement.setInt(1, codOficina);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			System.out.println("La cantidad de ingresos generados en total por ese servicio es: "+resultSet.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
