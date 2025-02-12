package repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Cita;
import modelo.Sucursal;

public class RepositorioCita {

	//Dada una fecha y una sucursal, elimina del arrayList de horas “Horario” (que pertenece a la sucursal) las horas ocupadas
	public static void eliminarHorasOcupadas(String fecha, Sucursal sucursal) throws SQLException {
		String query = "SELECT Hora FROM Cita WHERE fecha = ? AND CodSucursal = ? AND Hora IS NOT NULL";
		try (PreparedStatement preparedStatement = Conector.conexion.prepareStatement(query)){
			preparedStatement.setString(1, fecha);
			preparedStatement.setInt(2, sucursal.getCodigo());
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				for (int i = 0; i < sucursal.getHorarios().size(); i++) {
					if (resultSet.getString("Hora").equalsIgnoreCase(sucursal.getHorarios().get(i))) {
						sucursal.getHorarios().remove(i);
					}
				}
			}
		}
	}
	
	//Dado una cita la inserta en la base de datos
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