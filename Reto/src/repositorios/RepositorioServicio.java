package repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RepositorioServicio {

	//mostrar lista de servicios por sucursal
	public static void servicios(int sucursal) throws SQLException {
		//consulta
		String consulta = "SELECT SE.Descripcion FROM sucursal SU JOIN dispone D ON SU.Codigo=D.CodigoSucursal JOIN servicio SE ON SE.Codigo=D.CodigoServicio where ? = SU.Codigo";
		try (PreparedStatement preparedStatement = Conector.conexion.prepareStatement(consulta)) {
			preparedStatement.setInt(1, sucursal); 
			ResultSet resultSet = preparedStatement.executeQuery();
			//variable con valor de las columnas que cumplen la consulta
			int columnCount = resultSet.getMetaData().getColumnCount();
			//imprimir filas de la tabla
			while (resultSet.next()) {
				for (int i = 1; i <= columnCount; i++) {
					System.out.print(resultSet.getString(i) + "\t");
	            }
	            System.out.println();
	        }
		} 
	}
	
}
