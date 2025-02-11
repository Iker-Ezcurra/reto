package modelo;

import java.util.Objects;

public class Reptil extends Animal{

	//atributos
	private String dieta;
	
	//constructores
	public Reptil(String CodigoChip, String nombre, String sexo, int edad,  String dieta) {
		super(CodigoChip, nombre, sexo, edad);
		this.dieta = dieta;
	}
	
	public Reptil() {}

	//getters & setters
	public String getDieta() {
		return dieta;
	}

	public void setDieta(String dieta) {
		this.dieta = dieta;
	}

	//toString
	@Override
	public String toString() {
		return super.toString()+", dieta: " + dieta ;
	}

	//hashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(dieta);
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
		Reptil other = (Reptil) obj;
		return Objects.equals(dieta, other.dieta);
	}

}