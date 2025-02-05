package repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Animal;
import modelo.Cita;
import modelo.Sucursal;

public class RepositorioCita {

	public static void horasOcupadasPorSucursal(String fecha, Sucursal sucursal) throws SQLException {
		String query = "SELECT C.Hora FROM Cita C WHERE C.fecha = ? AND CodSucursal = ?";
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
	
	public static void insertar(Cita cita) throws SQLException {
		String query = "INSERT INTO Cita (CodAnimal, CodigoServicio, CodSucursal, CosteTotal, Fecha, FechaFin, Hora) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement preparedStatement = Conector.conexion.prepareStatement(query)) {
			preparedStatement.setString(1, cita.getCodAnimal());
			preparedStatement.setInt(2, cita.getCodServicio());
			preparedStatement.setInt(3, cita.getCodSucursal());
			preparedStatement.setInt(4, cita.getCosteTotal());
			preparedStatement.setString(5, cita.getFecha());
			preparedStatement.setString(6, cita.getFechaFin());
			preparedStatement.setString(7, cita.getHora());
			preparedStatement.executeUpdate();
		}
	}
	
}