package modelo;

import java.util.Objects;

public class Peludo extends Animal {

	//atributos
	private String raza;
	
	//constructores
	public Peludo(String CodigoChip, String nombre, String sexo, int edad, String raza) {
		super(CodigoChip, nombre, sexo, edad);
		this.raza = raza;
	}
	
	public Peludo() {}

	//getters & setters
	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	//toString
	@Override
	public String toString() {
		return super.toString()+", raza: " + raza ;
	}

	//hashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(raza);
		return result;
	}

	//equals
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Peludo other = (Peludo) obj;
		return Objects.equals(raza, other.raza);
	}
	
}