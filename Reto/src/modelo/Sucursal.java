package modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Sucursal {

	//atributos
	private int codigo;
	private String direccion;
	private String localidad;
	private ArrayList<String> Horarios = new ArrayList<>(Arrays.asList("09:00:00", "09:30:00", "10:00:00", "10:30:00", "11:00:00", "11:30:00", "12:00:00", "12:30:00", "16:00:00", "16:30:00", "17:00:00", "17:30:00", "18:00:00", "18:30:00", "19:00:00", "19:30:00"));
	
	//constructores
	public Sucursal(int codigo, String direccion, String localidad) {
		super();
		this.codigo = codigo;
		this.direccion = direccion;
		this.localidad = localidad;
	}
	
	public Sucursal (int codigo, String localidad) {
		this.codigo = codigo;
		this.localidad = localidad;
	}
	
	public Sucursal (int codigo) {
		this.codigo = codigo;
	}
	
	public Sucursal() {}

	//getters & setters
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public ArrayList<String> getHorarios() {
		return Horarios;
	}

	public void setHorarios(ArrayList<String> horarios) {
		Horarios = horarios;
	}

	//toString
	@Override
	public String toString() {
		String string = "";
		for(int i=0; i<Horarios.size(); i++) {
			if (i % 3 == 0) {
				string = string + "\n" + (i + 1) + ". " + Horarios.get(i) + "\t";
			} else {
				string = string + (i + 1) + ". " + Horarios.get(i) + "\t";
			}
			
		}
		return string;
	}

	//hashCode
	@Override
	public int hashCode() {
		return Objects.hash(Horarios, codigo, direccion, localidad);
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
		Sucursal other = (Sucursal) obj;
		return Objects.equals(Horarios, other.Horarios) && codigo == other.codigo
				&& Objects.equals(direccion, other.direccion) && Objects.equals(localidad, other.localidad);
	}
	
}