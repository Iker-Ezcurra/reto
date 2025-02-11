package repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Cliente;

public class RepositorioCliente {
	
	//Dado un cliente, comprueba si está o no en la base de datos y devuelve un booleano que lo expresa
  	public static boolean comprobarCliente(Cliente cliente) {
  		boolean encontrado = false;
  		//preparamos la consulta
  		String queryCheck = "SELECT COUNT(*) FROM Cliente WHERE Usuario = ?";
  		try (PreparedStatement checkStmt = Conector.conexion.prepareStatement(queryCheck)) {
  			checkStmt.setString(1, cliente.getUsuario());
  		    ResultSet resultSet = checkStmt.executeQuery();
  		    resultSet.next();
  		    int count = resultSet.getInt(1);
  		    if(count != 0) {
  		    	encontrado = true;
  		    }
  		} catch (SQLException e) {
  		    e.printStackTrace();
  		}
  		return encontrado;
  	}
  	
  	//Dado un cliente, comprueba si existe o no en la base de datos un cliente con ese usuario y contraseña, esto se hace para el inicio de sesión
  	public static boolean comprobarClienteUsuarioConstraseina(Cliente cliente) {
  		boolean encontrado = false;
  		String queryCheck = "SELECT COUNT(*) FROM Cliente WHERE Usuario = ? AND Contraseina = ?";
  			try (PreparedStatement checkStmt = Conector.conexion.prepareStatement(queryCheck)) {
  				checkStmt.setString(1, cliente.getUsuario());
  		        checkStmt.setString(2, cliente.getContraseina());
  		        ResultSet resultSet = checkStmt.executeQuery();
  		        resultSet.next();
  		        int count = resultSet.getInt(1);
  		        if(count != 0) {
  		        	encontrado = true;
  		        }
  		    } catch (SQLException e) {
  		    	e.printStackTrace();
  		    }
  		return encontrado;
  	}
	
	//Dado un cliente lo inserta en la base de datos
	public static void insertar(Cliente cliente) throws SQLException {
		String query = "INSERT INTO Cliente (Usuario, Contraseina, DNI, Nombre, Direccion, NumeroTelefono) VALUES (?, ?, ?, ?, ?, ?)";
		try (PreparedStatement preparedStatement = Conector.conexion.prepareStatement(query)) {
		    preparedStatement.setString(1, cliente.getUsuario());
		    preparedStatement.setString(2, cliente.getContraseina());
		    preparedStatement.setString(3, cliente.getDNI());
		    preparedStatement.setString(4, cliente.getNombre());
		    preparedStatement.setString(5, cliente.getDireccion());
		    preparedStatement.setInt(6, cliente.getNumTel());
		    preparedStatement.executeUpdate();
		}
	}
	
	//Dado el usuario de un cliente instancia uno con los datos que tenga ese usuario en la base de datos
	public static Cliente construir(String usuario) throws  SQLException {
		Cliente cliente = new Cliente();
		
		String consulta = "SELECT DNI, Usuario, Contraseina, Nombre, Direccion, NumeroTelefono, Administrador FROM Cliente where Usuario = ?";
		
		try (PreparedStatement preparedStatement = Conector.conexion.prepareStatement(consulta)){
			preparedStatement.setString(1, usuario);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				cliente.setUsuario(usuario);
				cliente.setContraseina(resultSet.getString("Contraseina"));
				cliente.setDNI(resultSet.getString("DNI"));
				cliente.setNombre(resultSet.getString("Nombre"));
				cliente.setDireccion(resultSet.getString("Direccion"));
				cliente.setNumTel(resultSet.getInt("NumeroTelefono"));
				cliente.setAdmin(resultSet.getBoolean("Administrador"));
			}
		}
		
		return cliente;
	}
}