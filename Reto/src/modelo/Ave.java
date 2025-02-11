package modelo;

import java.util.Objects;

public class Ave extends Animal {

	//atributos
	private String especie;
	
	//constructores
	public Ave(String CodigoChip, String nombre, String sexo, int edad, String especie) {
		super(CodigoChip, nombre, sexo, edad);
		this.especie = especie;
	}
	
	public Ave() {}

	//getters & setters
	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	//toString
	@Override
	public String toString() {
		return super.toString()+ ", especie: " + especie;
	}

	//hashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(especie);
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
		Ave other = (Ave) obj;
		return Objects.equals(especie, other.especie);
	}
	
	
}