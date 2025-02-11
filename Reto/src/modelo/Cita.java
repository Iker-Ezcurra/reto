package modelo;

import java.sql.SQLException;
import java.util.Objects;

import repositorios.RepositorioServicio;

public class Cita {

	//atributos
	private int codigo;
	private String codAnimal;
	private int codServicio;
	private int codSucursal;
	private int costeTotal;
	private String fecha;
	private String fechaFin;
	private String hora;
	
	//constructores
	public Cita(int codigo, String codAnimal, int codServicio, int codSucursal, int costeTotal, String fecha, String fechaFin, String hora) {
		this.codigo = codigo;
		this.codAnimal = codAnimal;
		this.codServicio = codServicio;
		this.codSucursal = codSucursal;
		this.costeTotal = costeTotal;
		this.fecha = fecha;
		this.fechaFin = fechaFin;
		this.hora = hora;
	}
	
	public Cita () {}

	//getters & setters
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}
	
	public int getCodSucursal() {
		return codSucursal;
	}
	
	public void setCodSucursal(int codSucursal) {
		this.codSucursal = codSucursal;
	}

	public String getCodAnimal() {
		return codAnimal;
	}

	public void setCodAnimal(String codAnimal) {
		this.codAnimal = codAnimal;
	}

	public int getCodServicio() {
		return codServicio;
	}

	public void setCodServicio(int codServicio) {
		this.codServicio = codServicio;
	}

	public int getCosteTotal() {
		return costeTotal;
	}

	public void setCosteTotal(int costeTotal) {
		this.costeTotal = costeTotal;
	}

	//toString
	@Override
	public String toString() {
		String descripcion="";
		try {
			descripcion = RepositorioServicio.servicioDes(codServicio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String string="";
		if (this.fechaFin == null) {
			string = descripcion + ": Precio: " + costeTotal + ", fecha: " + fecha + ", hora:" + hora;
		} else if (this.hora == null) {
			string=  descripcion +": Precio:" + costeTotal + ", fecha:" + fecha + ", fecha de finalizacion:" + fechaFin;
		}
		return string;
	}

	//hashCode
	@Override
	public int hashCode() {
		return Objects.hash(codAnimal, codServicio, codSucursal, codigo, costeTotal, fecha, fechaFin, hora);
	}

	//equals
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cita other = (Cita) obj;
		return Objects.equals(codAnimal, other.codAnimal) && codServicio == other.codServicio
				&& codSucursal == other.codSucursal && codigo == other.codigo && costeTotal == other.costeTotal
				&& Objects.equals(fecha, other.fecha) && Objects.equals(fechaFin, other.fechaFin)
				&& Objects.equals(hora, other.hora);
	}
	
	
	
}