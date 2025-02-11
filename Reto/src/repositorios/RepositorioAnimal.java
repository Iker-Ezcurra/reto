package repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Animal;
import modelo.Cliente;

public class RepositorioAnimal {
	
	//Dado el código de un animal, comprueba si está o no en la base de datos y devuelve un booleano que lo expresa
	public static boolean comprobar(String codigoAnimal) {
		boolean encontrado = false;
		String queryCheck = "SELECT COUNT(*) FROM Animales WHERE CodigoChip = ?";
		try (PreparedStatement checkStmt = Conector.conexion.prepareStatement(queryCheck)) {
			checkStmt.setString(1, codigoAnimal);
			ResultSet resultSet = checkStmt.executeQuery();
			resultSet.next();
			int count = resultSet.getInt(1);
			if (count != 0) {
				encontrado = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return encontrado;
	}
	
	//Dado el código de un animal, devuelve el tipo de animal que es haciendo una consulta con estructura case when y devuelve un String de los siguientes que expresa el tipo de animal que es: Peludo, Ave, Reptil u Otro
	public static String tipoAnimal(String codigoAnimal) throws SQLException {
		String tipoAnimal= "Otro";
		String queryCheck = "SELECT " +
	               "CASE " +
	               "WHEN p.CodigoChip IS NOT NULL THEN 'Peludos' " +
	               "WHEN a.CodigoChip IS NOT NULL THEN 'Ave' " +
	               "WHEN r.CodigoChip IS NOT NULL THEN 'Reptil' " +
	               "ELSE 'Desconocido' " +
	               "END AS TipoAnimal " +
	               "FROM Animales an " +
	               "LEFT JOIN Peludo p ON an.CodigoChip = p.CodigoChip " +
	               "LEFT JOIN Ave a ON an.CodigoChip = a.CodigoChip " +
	               "LEFT JOIN Reptil r ON an.CodigoChip = r.CodigoChip " +
	               "WHERE an.CodigoChip = ?";
		try (PreparedStatement checkStmt = Conector.conexion.prepareStatement(queryCheck)){
			checkStmt.setString(1, codigoAnimal);
			ResultSet resultSet = checkStmt.executeQuery();
			if (resultSet.next()) {
				tipoAnimal = resultSet.getString("TipoAnimal");
			}
		}
		return tipoAnimal;
	}
	
	//Dado un animal y un cliente inserta el animal en la base de datos. El cliente se le da para poder relacionarlo con su DNI
	public static void insertar(Animal animal, Cliente cliente) throws SQLException {
		String query = "INSERT INTO Animales VALUES (?, ?, ?, ?, ?)";
		try (PreparedStatement preparedStatement = Conector.conexion.prepareStatement(query)) {
			preparedStatement.setString(1, animal.getCodigoChip());
			preparedStatement.setString(2, animal.getNombre());
			preparedStatement.setString(3, animal.getSexo());
			preparedStatement.setInt(4, animal.getEdad());
			preparedStatement.setString(5, cliente.getDNI());
			preparedStatement.executeUpdate();
		}
	}
	
	//Dado el código de un animal, instancia uno con los datos del animal con ese código en la base de datos
	public static Animal construirAnimal(String codChip) throws SQLException {
		String consulta = "SELECT Nombre, Sexo, Edad FROM Animales WHERE CodigoChip=?";
		Animal animal = new Animal();
		try (PreparedStatement preparedStatement = Conector.conexion.prepareStatement(consulta)){
			preparedStatement.setString(1, codChip);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				animal.setNombre(resultSet.getString("Nombre"));
				animal.setSexo(resultSet.getString("Sexo"));
				animal.setEdad(resultSet.getInt("Edad"));
				animal.setCodigoChip(codChip);
			}
		}
		return animal;
	}
	
}