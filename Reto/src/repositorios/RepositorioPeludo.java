package repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Peludo;

public class RepositorioPeludo {
	
	public static boolean comprobar(String codigoPeludo) {
		boolean encontrado = false;
		
		String queryCheck = "SELECT COUNT(*) FROM Peludo WHERE CodigoChip = ?";
		
		try (PreparedStatement checkStmt = Conector.conexion.prepareStatement(queryCheck)) {
			checkStmt.setString(1, codigoPeludo);
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
	
	public static void insertar(Peludo peludo) throws SQLException {
		String query = "INSERT INTO Peludo VALUES (?, ?)";
		
		try (PreparedStatement preparedStatement = Conector.conexion.prepareStatement(query)) {
			preparedStatement.setString(1, peludo.getCodigoChip());
			preparedStatement.setString(2, peludo.getRaza());
			preparedStatement.executeUpdate();
		}
	}
	
	public static Peludo construirPeludo(String codChip) throws SQLException {
		String consulta = "SELECT A.Nombre, A.Sexo, A.Edad, P.Raza FROM Animales A JOIN Peludo P ON A.CodigoChip=P.CodigoChip WHERE A.CodigoChip=?";
		
		Peludo peludo = new Peludo();
		
		try (PreparedStatement preparedStatement = Conector.conexion.prepareStatement(consulta)){
			preparedStatement.setString(1, codChip);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				peludo.setNombre(resultSet.getString("Nombre"));
				peludo.setSexo(resultSet.getString("Sexo"));
				peludo.setEdad(resultSet.getInt("Edad"));
				peludo.setRaza(resultSet.getString("Raza")); 
				peludo.setCodigoChip(codChip);
			}
		}
		
		return peludo;
	}
	
}