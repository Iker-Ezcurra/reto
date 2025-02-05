package repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Ave;

public class RepositorioAve {
	
	//comprueba que exista el ave con este codigoChip en la base de datos
	public static boolean comprobar(String codigoAve) {
		boolean encontrado = false;
		
		String queryCheck = "SELECT COUNT(*) FROM Ave WHERE CodigoChip = ?";
		
		try (PreparedStatement checkStmt = Conector.conexion.prepareStatement(queryCheck)) {
			checkStmt.setString(1, codigoAve);
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
	
	//método para hacer insert de un ave en la base de datos
	public static void insertar(Ave ave) throws SQLException {
		String query = "INSERT INTO Ave VALUES (?, ?)";
		
		try (PreparedStatement preparedStatement = Conector.conexion.prepareStatement(query)) {
			preparedStatement.setString(1, ave.getCodigoChip());
			preparedStatement.setString(2, ave.getEspecie());
			preparedStatement.executeUpdate();
		}
	}
	
	//método para hacer insert de un ave en la base de datos
	public static Ave construirAve(String codChip) throws SQLException {
		String consulta = "SELECT A.Nombre, A.Sexo, A.Edad, AV.Especie FROM Animales A JOIN Ave AV ON A.CodigoChip=AV.CodigoChip WHERE A.CodigoChip=?";
		
		Ave ave = new Ave();
		
		try (PreparedStatement preparedStatement = Conector.conexion.prepareStatement(consulta)){
			preparedStatement.setString(1, codChip);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				ave.setNombre(resultSet.getString("Nombre"));
				ave.setSexo(resultSet.getString("Sexo"));
				ave.setEdad(resultSet.getInt("Edad"));
				ave.setEspecie(resultSet.getString("Especie")); 
				ave.setCodigoChip(codChip);
			}
		}
		
		return ave;
	}
	
}