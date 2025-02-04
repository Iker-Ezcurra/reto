package repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Ave;

public class RepositorioAve {
	
	public static void insertar(Ave ave) throws SQLException {
		String query = "INSERT INTO Ave VALUES (?, ?)";
		try (PreparedStatement preparedStatement = Conector.conexion.prepareStatement(query)) {
			preparedStatement.setString(1, ave.getCodigoChip());
			preparedStatement.setString(2, ave.getEspecie());
			preparedStatement.executeUpdate();
		}
	}
	
	public static boolean comprobar(Ave ave) {
		boolean encontrado = false;
		String queryCheck = "SELECT COUNT(*) FROM Ave WHERE CodigoChip = ?";
		try (PreparedStatement checkStmt = Conector.conexion.prepareStatement(queryCheck)) {
			checkStmt.setString(1, ave.getCodigoChip());
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