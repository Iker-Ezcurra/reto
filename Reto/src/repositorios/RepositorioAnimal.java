package repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import modelo.Animal;

public class RepositorioAnimal {
	
	//comprueba que exista el animal con este codigo del chip
	public static boolean comprobar(Animal animal) {
		boolean encontrado = false;
		String queryCheck = "SELECT COUNT(*) FROM Animales WHERE CodigoChip = ?";
		try (PreparedStatement checkStmt = Conector.conexion.prepareStatement(queryCheck)) {
			checkStmt.setString(1, animal.getCodigoChip());
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
	
	//metodo que comprueba la existencia del animal en la base de datos y lo inserta en caso de no existir
	//devuelve booleano que indica si se ha insertado o no
	public static boolean registrar(Animal animal) throws SQLException {
		boolean hecho = false;
		//llamada al metodo comprobar 
		if (RepositorioAnimal.comprobar(animal)) {
			System.out.println("Este animal ya está registrado");
		} else {
			//llama al metodo insertar
			RepositorioAnimal.insertar(animal);
		    hecho = true;
		}
		return hecho;
	}
	
	//método para hacer insert de un animal en la base de datos
	public static void insertar(Animal animal) throws SQLException {
		String query = "INSERT INTO Animales VALUES (?, ?, ?, ?)";
		try (PreparedStatement preparedStatement = Conector.conexion.prepareStatement(query)) {
			preparedStatement.setString(1, animal.getCodigoChip());
			preparedStatement.setString(2, animal.getNombre());
			preparedStatement.setString(3, animal.getSexo());
			preparedStatement.setInt(4, animal.getEdad());
			preparedStatement.executeUpdate();
		}
	}
	
	public static Animal construirAnimal(String cc) throws SQLException {
		String consulta = "SELECT Nombre, Sexo, Edad FROM Animales WHERE CodigoChip=?";
		Animal animal = new Animal();
		try (PreparedStatement preparedStatement = Conector.conexion.prepareStatement(consulta)){
			preparedStatement.setString(1, cc);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				animal.setNombre(resultSet.getString("Nombre"));
				animal.setSexo(resultSet.getString("Sexo"));
				animal.setEdad(resultSet.getInt("Edad"));
				animal.setCodigoChip(cc);
			}
		}
		return animal;
	}
	
}