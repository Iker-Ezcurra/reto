package repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Cliente;
import view.InstanciarPorTeclado;

public class RepositorioCliente {
	
	//comprueba que exista un cliente con este usuario
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
  	
  	//Comprueba que exista un cliente con este usuario y contraseña para iniciar sesion
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
	
  	//metodo que comprueba la existencia del cliente en la base de datos y lo inserta en caso de no existir
  	//devuelve booleano que indica si se ha insertado o no
	public static boolean registrar() throws SQLException {
		boolean hecho = false;
		Cliente cliente = InstanciarPorTeclado.Cliente();
		//llamada al metodo comprobar 
		if(RepositorioCliente.comprobarCliente(cliente)) {
			System.out.println("Este usuario esta ya en uso");
		}else {
			//llama al metodo insertar
			RepositorioCliente.insertar(cliente);
		    hecho = true;
		}
		return hecho;
	}
	
	//método para insertar un cliente en la tabla
	public static void insertar(Cliente cliente) throws SQLException {
		String query = "INSERT INTO Cliente VALUES (?, ?, ?, ?, ?, ?)";
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
	
	//metodo para iniciar sesion
	public static Cliente inicioSesion() throws SQLException {
		Cliente cliente = InstanciarPorTeclado.ClienteInicioSesion();
		//llamada al metodo comprobar 
		if(RepositorioCliente.comprobarClienteUsuarioConstraseina(cliente)) {
			cliente=RepositorioCliente.construir(cliente);
		}
		return cliente;
	}
	
	public static Cliente construir(Cliente cliente) throws  SQLException {
		String consulta = "SELECT DNI, Nombre, Direccion, NumeroTelefono FROM Cliente where Usuario=? AND Contraseina = ?";
		try (PreparedStatement preparedStatement = Conector.conexion.prepareStatement(consulta)){
			preparedStatement.setString(1, cliente.getUsuario());
			preparedStatement.setString(2, cliente.getContraseina());
			ResultSet resultSet = preparedStatement.executeQuery();
			//creo que hay un error a partir de aqui
			cliente.setDNI(resultSet.getString("DNI"));
			cliente.setNombre(resultSet.getString("Nombre"));
			cliente.setDireccion(resultSet.getString("Direccion"));
			cliente.setNumTel(resultSet.getInt("NumeroTelefono"));	
		}
		return cliente;
	}
	
}