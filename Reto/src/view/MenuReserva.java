package view;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.Animal;
import modelo.Ave;
import modelo.Cita;
import modelo.Cliente;
import modelo.Peludo;
import modelo.Reptil;
import modelo.Servicio;
import modelo.Sucursal;
import repositorios.RepositorioAnimal;
import repositorios.RepositorioAve;
import repositorios.RepositorioCita;
import repositorios.RepositorioPeludo;
import repositorios.RepositorioReptil;
import repositorios.RepositorioServicio;
import repositorios.RepositorioSucursal;

public class MenuReserva {
	
	//Dado un animal, un ArrayList de animales, un ArrayList de citas y un cliente, se le piden todos los datos de la cita y se instancia
	public static Cita mostrar(Animal animal, ArrayList<Animal> listaAnimales, ArrayList<Cita> listaCitas, Cliente cliente) throws SQLException {
		Scanner teclado = new Scanner(System.in);
		ArrayList<Servicio> listaServicios = new ArrayList<>();
		int costeTotal = 0;
		String fechaFin = null;
		String hora = null;
	
		Sucursal sucursal = elegirSucursal();
		
		Servicio servicio = elegirServicio(establecerServicios(listaServicios, animal, sucursal));
		
		String fecha = elegirFecha();
		
		if (servicio.getCodigo() == 1) {
			fechaFin = elegirFechaFin(fecha);
			costeTotal = (int) (servicio.getCoste() * contarDiasEntreFechas(fecha, fechaFin));
			
		} else {
			hora = elegirHora(fecha, sucursal, listaCitas);
			costeTotal = servicio.getCoste();
		}
		
		Cita cita = new Cita();
		cita.setCodAnimal(animal.getCodigoChip());
		cita.setCosteTotal(costeTotal);
		cita.setFecha(fecha);
		cita.setFechaFin(fechaFin);
		cita.setHora(hora);
		cita.setCodSucursal(sucursal.getCodigo());
		cita.setCodServicio(servicio.getCodigo());
		
		System.out.println("Cita realizada con éxito, confirmala en el carrito");
		
		return cita;
	}

	private static Sucursal elegirSucursal() throws SQLException {
		Scanner teclado = new Scanner(System.in);
		boolean valido = false;
		int sucursalCodigo = 0;
		
		while (!valido) {
			System.out.println("\n--- ¿A que sucursal desea asistir? ---");
			System.out.println();
			System.out.println("Escoge una de las opciones: ");
			System.out.println("1. Irun");
			System.out.println("2. Donostia");
			System.out.println("3. Renteria");
			System.out.println("4. Bilbao");
			System.out.println();
			System.out.println("0. Volver atrás");
			
			sucursalCodigo = teclado.nextInt();
			
			if (sucursalCodigo < 0 || sucursalCodigo > 4) {
				System.out.println("Opción inválida");
			} else {
				valido = true;
			}
		}
		
		Sucursal sucursal = RepositorioSucursal.contruirSucursal(sucursalCodigo);
		
		return sucursal;
	}
	
	private static ArrayList<Servicio> establecerServicios(ArrayList<Servicio> listaServicios, Animal animal, Sucursal sucursal) throws SQLException{
		listaServicios.clear();
		if (RepositorioAnimal.comprobar(animal.getCodigoChip())) {
			listaServicios = RepositorioServicio.serviciosPorSurcursalYAnimal(sucursal, RepositorioAnimal.tipoAnimal(animal.getCodigoChip()), listaServicios);
		} else {
			if (animal instanceof Peludo) {
				listaServicios = RepositorioServicio.serviciosPorSurcursalYAnimal(sucursal, "Peludo", listaServicios);
			} else if (animal instanceof Ave) {
				listaServicios = RepositorioServicio.serviciosPorSurcursalYAnimal(sucursal, "Ave", listaServicios);
			} else if (animal instanceof Reptil) {
				listaServicios = RepositorioServicio.serviciosPorSurcursalYAnimal(sucursal, "Reptil", listaServicios);
			} else {
				listaServicios = RepositorioServicio.serviciosPorSurcursalYAnimal(sucursal, "Otro", listaServicios);
			}
		}
		return listaServicios;
	}
	
	private static Servicio elegirServicio(ArrayList<Servicio> listaServicios) throws SQLException {
		Scanner teclado = new Scanner(System.in);
		boolean valido = false;
		int opcion = 0;
		
		while (!valido) {
			System.out.println("Elija entre los servicios disponibles para su animal:");
			for (int i = 0; i < listaServicios.size(); i++) {
				System.out.println((i+1) + ". " + listaServicios.get(i).toString());
			}
			opcion = teclado.nextInt();
			if (opcion < 0 || opcion > listaServicios.size()) {
				System.out.println("Opción inválida");
			} else {
				valido = true;
			}
		}

		Servicio servicio = listaServicios.get(opcion - 1);

		return servicio;
	}
	
	private static String elegirFecha() {
		Scanner teclado = new Scanner(System.in);
		String fecha = "";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fechaActual = LocalDate.now();
		boolean fin = false;
		
		while (!fin) {
			System.out.println("¿Que dia quiere el servicio? (yyyy-mm-dd)");
			
			fecha = teclado.next();
			LocalDate dateInicio = LocalDate.parse(fecha, formatter);
			
			if (ChronoUnit.DAYS.between(dateInicio, fechaActual) > 1) {
				System.out.println("Esa fecha ya no esta disponible, por favor introduce otra fecha");
			} else {
				fin = true;
			}
		}
		
		return fecha;
	}
	
	private static String elegirFechaFin(String fecha) {
		Scanner teclado = new Scanner(System.in);
		String fechaFin = null;
		long diferenciaDias = 0;
	
		do {
			System.out.println("¿Hasta que dia quieres el servicio?(yyyy-mm-dd)");
			
			fechaFin = teclado.next();
			
			diferenciaDias = contarDiasEntreFechas(fecha, fechaFin);
			
			if (diferenciaDias < 1) {
				System.out.println("Las fechas introducidas no son válidas, intentalo de nuevo");
			}
			
		} while(diferenciaDias < 1);
		
		return fechaFin;
	}
	
	private static long contarDiasEntreFechas(String fecha, String fechaFin) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dateInicio = LocalDate.parse(fecha, formatter);
		LocalDate dateFin = LocalDate.parse(fechaFin, formatter);
		
		long diasDiferencia = ChronoUnit.DAYS.between(dateInicio, dateFin);
		
		return diasDiferencia;
	}
	
	private static String elegirHora(String fecha, Sucursal sucursal, ArrayList<Cita> listaCitas) throws SQLException {
		Scanner teclado = new Scanner(System.in);
		
		RepositorioCita.eliminarHorasOcupadas(fecha, sucursal);
		
		for (int i = 0; i < listaCitas.size(); i++) {
			if ((fecha.equals(listaCitas.get(i).getFecha())) && (sucursal.getCodigo() == listaCitas.get(i).getCodSucursal())) {
				for (int j = 0; j < sucursal.getHorarios().size(); j++) {
					if (listaCitas.get(i).getHora().equals(sucursal.getHorarios().get(j))) {
						sucursal.getHorarios().remove(j);
					}
				}
			}
		}
		
		System.out.println("Elige entre las horas disponibles:");
		System.out.println(sucursal.toString());
		
		int aux = (teclado.nextInt() - 1);
		
		String hora = sucursal.getHorarios().get(aux);
		
		return hora;
	}
	
}