package modelo;

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
	
	public Cita(int codigo, int costeTotal, String fecha, String fechaFin, int codSucursal) {
		this.codigo = codigo;
		this.costeTotal = costeTotal;
		this.fecha = fecha;
		this.fechaFin = fechaFin;
		this.codSucursal = codSucursal;
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

	@Override
	public String toString() {
		return "Cita [costeTotal=" + costeTotal + ", fecha=" + fecha + ", fechaFin=" + fechaFin
				+ ", hora=" + hora + "]";
	}
	
}