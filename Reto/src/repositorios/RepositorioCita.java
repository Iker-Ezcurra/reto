package repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Sucursal;

public class RepositorioCita {

	public static void horasOcupadasPorSucursal(String fecha, Sucursal sucursal) throws SQLException {
		String query = "SELECT C.Hora FROM Cita C JOIN TieneLugar TL ON C.codigo=TL.codigoCita JOIN Sucursal S ON S.codigo=TL.codigoSucursal WHERE C.fecha=? AND S.codigo=?";
		try (PreparedStatement preparedStatement = Conector.conexion.prepareStatement(query)){
			preparedStatement.setString(1, fecha);
			preparedStatement.setInt(2, sucursal.getCodigo());
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				for (int i=0; i<sucursal.getHorarios().size(); i++) {
					if (resultSet.getString("Hora").equalsIgnoreCase(sucursal.getHorarios().get(i))) {
						sucursal.getHorarios().remove(i);
					}
				}
			}
		}
	}
}
