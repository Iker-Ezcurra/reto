package modelo;

import java.util.ArrayList;
import java.util.Arrays;

public class Sucursal {

	//atributos
	private int codigo;
	private String direccion;
	private String localidad;
	private ArrayList<String> Horarios = new ArrayList<>(Arrays.asList("09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30"));
	
	//constructor
	public Sucursal(int codigo, String direccion, String localidad) {
		super();
		this.codigo = codigo;
		this.direccion = direccion;
		this.localidad = localidad;
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
	
	
}