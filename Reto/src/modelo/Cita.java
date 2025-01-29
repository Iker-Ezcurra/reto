package modelo;

public class Cita {

	//atributos
	private int codigo;
	private int costeTotal;
	private String fecha;
	private String fechaFin;
	private String hora;
	
	//constructores
	public Cita(int codigo, int costeTotal, String fecha, String fechaFin) {
		this.codigo = codigo;
		this.costeTotal = costeTotal;
		this.fecha = fecha;
		this.fechaFin = fechaFin;
	}

	public Cita(int codigo, String hora, String fecha, int coste) {
		this.codigo = codigo;
		this.costeTotal = coste;
		this.fecha = fecha;
		this.hora = hora;
	}

	//getters & setters
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCoste() {
		return costeTotal;
	}

	public void setCoste(int coste) {
		this.costeTotal = coste;
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
	
}