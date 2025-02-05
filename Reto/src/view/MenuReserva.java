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
import modelo.Peludo;
import modelo.Reptil;
import modelo.Servicio;
import modelo.Sucursal;
import repositorios.RepositorioCita;
import repositorios.RepositorioServicio;
import repositorios.RepositorioSucursal;

public class MenuReserva {
	
	public static Cita mostrar(Animal animal, ArrayList<Cita> listaCitas) throws SQLException {
		Scanner teclado = new Scanner(System.in);
		ArrayList<Servicio> listaServicios = new ArrayList<>();
		int costeTotal = 0;
		String fecha;
		String fechaFin = null;
		String hora = null;
		int aux;
		
		Cita cita = new Cita();

		System.out.println("--- ¿A que sucursal desea asistir? ---");
		System.out.println();
		System.out.println("Escoge una de las opciones: ");
		System.out.println("1. Irun");
		System.out.println("2. Donostia");
		System.out.println("3. Renteria");
		System.out.println("4. Bilbao");
		int sucursalCodigo = teclado.nextInt();
		Sucursal sucursal = RepositorioSucursal.contruirSucursal(sucursalCodigo);
		System.out.println();
		System.out.println("Elija entre los servicios disponibles para su animal:");
		if(animal instanceof Reptil) {
			listaServicios = RepositorioServicio.serviciosPorSurcursalYAnimal(sucursal, "Reptil", listaServicios);
		} else if (animal instanceof Ave) {
			listaServicios = RepositorioServicio.serviciosPorSurcursalYAnimal(sucursal, "Ave", listaServicios);
		} else if (animal instanceof Peludo) {
			listaServicios = RepositorioServicio.serviciosPorSurcursalYAnimal(sucursal, "Peludos", listaServicios);
		} else if (animal instanceof Animal){
			listaServicios = RepositorioServicio.serviciosPorSurcursalYAnimal(sucursal, "Todos", listaServicios);
		}
		int opcion = teclado.nextInt();
		int codServicio = listaServicios.get(opcion - 1).getCodigo();
		if(codServicio == 1) {
			DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate dateInicio;
			LocalDate dateFin;
			long diferenciaDias;
			do {
				System.out.println("¿Desde que dia quieres el servicio?(yyyy-mm-dd)");
				fecha = teclado.next();
				dateInicio = LocalDate.parse(fecha, formatter);
				System.out.println("¿Hasta que dia quieres el servicio?(yyyy-mm-dd)");
				fechaFin = teclado.next();
				dateFin = LocalDate.parse(fechaFin, formatter);
				diferenciaDias = ChronoUnit.DAYS.between(dateInicio, dateFin);
				if (diferenciaDias < 1) {
					System.out.println("Las fechas introducidas no son válidas, intentalo de nuevo");
				}
			} while(diferenciaDias < 1);
			costeTotal = listaServicios.get(opcion - 1).getCoste() * (int) diferenciaDias;
		} else {
			System.out.println("¿Que dia quieres el servicio?(yyyy-mm-dd)");
			fecha = teclado.next();
			RepositorioCita.horasOcupadasPorSucursal(fecha, sucursal);
			for (int i = 0; i < listaCitas.size(); i++) {
				if ((fecha.equals(listaCitas.get(i).getFecha())) && (sucursalCodigo == listaCitas.get(i).getCodSucursal())) {
					for (int j = 0; j < sucursal.getHorarios().size(); j++) {
						if (listaCitas.get(i).getHora().equals(sucursal.getHorarios().get(j))) {
							sucursal.getHorarios().remove(j);
						}
					}
				}
			}
			System.out.println("Elige entre las horas disponibles:");
			System.out.println(sucursal.toString());
			aux = (teclado.nextInt() - 1);
			hora = sucursal.getHorarios().get(aux);
			sucursal.getHorarios().remove(aux);
			costeTotal = listaServicios.get(opcion - 1).getCoste();
		}
		cita.setCodAnimal(animal.getCodigoChip());
		cita.setCosteTotal(costeTotal);
		cita.setFecha(fecha);
		cita.setFechaFin(fechaFin);
		cita.setHora(hora);
		cita.setCodSucursal(sucursalCodigo);
		cita.setCodServicio(codServicio);
		System.out.println("Cita realizada con exito");
		return cita;
	}
	
}