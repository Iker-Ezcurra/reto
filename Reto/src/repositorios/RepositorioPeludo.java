package repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Peludo;

public class RepositorioPeludo {
	
	public static void insertar(Peludo peludo) throws SQLException {
		String query = "INSERT INTO Peludo VALUES (?, ?)";
		try (PreparedStatement preparedStatement = Conector.conexion.prepareStatement(query)) {
			preparedStatement.setString(1, peludo.getCodigoChip());
			preparedStatement.setString(2, peludo.getRaza());
			preparedStatement.executeUpdate();
		}
	}
	
	public static boolean comprobar(Peludo peludo) {
		boolean encontrado = false;
		String queryCheck = "SELECT COUNT(*) FROM Peludo WHERE CodigoChip = ?";
		try (PreparedStatement checkStmt = Conector.conexion.prepareStatement(queryCheck)) {
			checkStmt.setString(1, peludo.getCodigoChip());
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