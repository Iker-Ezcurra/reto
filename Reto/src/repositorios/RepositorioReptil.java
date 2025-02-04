package repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Reptil;

public class RepositorioReptil {
	
	public static void insertar(Reptil reptil) throws SQLException {
		String query = "INSERT INTO Reptil VALUES (?, ?)";
		try (PreparedStatement preparedStatement = Conector.conexion.prepareStatement(query)) {
			preparedStatement.setString(1, reptil.getCodigoChip());
			preparedStatement.setString(2, reptil.getDieta());
			preparedStatement.executeUpdate();
		}
	}
	
	public static boolean comprobar(Reptil reptil) {
		boolean encontrado = false;
		String queryCheck = "SELECT COUNT(*) FROM Reptil WHERE CodigoChip = ?";
		try (PreparedStatement checkStmt = Conector.conexion.prepareStatement(queryCheck)) {
			checkStmt.setString(1, reptil.getCodigoChip());
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

}