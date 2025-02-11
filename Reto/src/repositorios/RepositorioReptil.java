package repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Reptil;

public class RepositorioReptil {
	
	//Dado un reptil inserta el animal en la base de datos, en la tabla de reptiles
	public static void insertar(Reptil reptil) throws SQLException {
		String query = "INSERT INTO Reptil VALUES (?, ?)";
		try (PreparedStatement preparedStatement = Conector.conexion.prepareStatement(query)) {
			preparedStatement.setString(1, reptil.getCodigoChip());
			preparedStatement.setString(2, reptil.getDieta());
			preparedStatement.executeUpdate();
		}
	}
	
	//Dado el código de un animal, comprueba si está o no en la base de datos, concretamente en la tabla de reptiles, y devuelve un booleano que lo expresa
	public static boolean comprobar(String codigoReptil) {
		boolean encontrado = false;
		String queryCheck = "SELECT COUNT(*) FROM Reptil WHERE CodigoChip = ?";
		try (PreparedStatement checkStmt = Conector.conexion.prepareStatement(queryCheck)) {
			checkStmt.setString(1, codigoReptil);
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
	
	//Dado el código de un animal, instancia un reptil con los datos que tenga ese código en la base de datos
	public static Reptil construirReptil(String codChip) throws SQLException {
		String consulta = "SELECT A.Nombre, A.Sexo, A.Edad, R.Dieta FROM Animales A JOIN Reptil R ON A.CodigoChip=R.CodigoChip WHERE A.CodigoChip=?";
		Reptil reptil = new Reptil();
		try (PreparedStatement preparedStatement = Conector.conexion.prepareStatement(consulta)){
			preparedStatement.setString(1, codChip);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				reptil.setNombre(resultSet.getString("Nombre"));
				reptil.setSexo(resultSet.getString("Sexo"));
				reptil.setEdad(resultSet.getInt("Edad"));
				reptil.setDieta(resultSet.getString("Dieta"));
				reptil.setCodigoChip(codChip);
			}
		}
		return reptil;
	}

}