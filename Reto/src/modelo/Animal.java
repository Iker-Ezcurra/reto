package modelo;

import java.util.Objects;

public class Animal {
	
	
	//atributos
	private String codigoChip;
	private String nombre;
	private String sexo;
	private int edad;
	
	//constructores
	public Animal(String CodigoChip, String nombre, String sexo, int edad) {
		this.codigoChip = CodigoChip;
		this.nombre = nombre;
		this.sexo = sexo;
		this.edad = edad;
	}
	
	public Animal () {}

	//getters & setters
	public String getCodigoChip() {
		return codigoChip;
	}

	public void setCodigoChip(String CodigoChip) {
		this.codigoChip = CodigoChip;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	//toString
	@Override
	public String toString() {
		return nombre+": Codigo del chip: "+codigoChip+", edad: "+edad+", sexo: "+sexo;
	}

	//hashCode
	@Override
	public int hashCode() {
		return Objects.hash(codigoChip);
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
		Animal other = (Animal) obj;
		return Objects.equals(codigoChip, other.codigoChip);
	}
		
}