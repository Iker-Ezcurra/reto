package modelo;

import java.util.Objects;

public class Servicio {
	//atributos
	private int codigo;
	private String descripcion;
	private int coste;

	//constructores
	public Servicio(int codigo, String descripcion, int coste) {
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.coste=coste;
	}
	
	public Servicio( int coste, String descripcion) {
		this.descripcion = descripcion;
		this.coste=coste;
	}
	
	public Servicio() {}
	
	//getters & setters
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public int getCoste() {
		return coste;
	}
	
	public void setCoste(int coste) {
		this.coste=coste;
	}

	//toString
	@Override
	public String toString() {
		return descripcion + "\t" + "\t" + coste + "€ " ;
	}

	//hashCode
	@Override
	public int hashCode() {
		return Objects.hash(codigo, coste, descripcion);
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
		Servicio other = (Servicio) obj;
		return codigo == other.codigo && coste == other.coste && Objects.equals(descripcion, other.descripcion);
	}
	
	

}