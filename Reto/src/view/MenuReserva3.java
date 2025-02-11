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
		String fecha=null;
		String fechaFin = null;
		String hora = null;
		String volver = null;
		int aux;
		boolean valido = false;
		int sucursalCodigo = 0;
		int opcion = 0;
		Cita cita = new Cita();

		LocalDate fechaActual = LocalDate.now();
		
		while (!valido) {
			System.out.println("\n--- ¿A que sucursal desea asistir? ---");
			System.out.println();
			System.out.println("Escoge una de las opciones: ");
			System.out.println("1. Irun");
			System.out.println("2. Donostia");
			System.out.println("3. Renteria");
			System.out.println("4. Bilbao");
			sucursalCodigo = teclado.nextInt();
			if (sucursalCodigo < 0 || sucursalCodigo > 4) {
				System.out.println("Opción inválida");
			} else if (sucursalCodigo == 0){
				System.out.println("¿Esta seguro que quiere volver al paso anterior? Y/N");
				volver = teclado.next();
				if (volver.equalsIgnoreCase("Y")) {
					listaAnimales.remove(listaAnimales.size()-1);
					MenuAnimal.mostrar(listaAnimales, cliente);
				}
			} else{
				valido = true;
			}
		}
		
		valido = false;
		Sucursal sucursal = RepositorioSucursal.contruirSucursal(sucursalCodigo);
		System.out.println();
		while (!valido) {
			listaServicios.clear();
			System.out.println("Elija entre los servicios disponibles para su animal:");
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
			for (int i = 0; i < listaServicios.size(); i++) {
				System.out.println((i+1) + ". " + listaServicios.get(i).toString());
			}
			opcion = teclado.nextInt();
			if (opcion < 0 || opcion > listaServicios.size()) {
				System.out.println("Opción inválida");
			} else if (opcion == 0) {
				System.out.println("¿Esta seguro que quiere volver al paso anterior? Y/N");
				volver = teclado.next();
				if (volver.equalsIgnoreCase("Y")) {
					MenuReserva.mostrar(animal, listaAnimales, listaCitas, cliente);
				}
			} else {
				valido = true;
			}
		}
		LocalDate dateInicio;
		int codServicio = listaServicios.get(opcion - 1).getCodigo();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		if(codServicio == 1) {
			boolean fechaCorrecta = false;
			LocalDate dateFin;
			long diferenciaDias=0;
			do {
				do {
					System.out.println("¿Desde que dia quieres el servicio?(yyyy-mm-dd)");
					fecha = teclado.next();
					dateInicio = LocalDate.parse(fecha, formatter);
					if (ChronoUnit.DAYS.between(dateInicio, fechaActual) > 1) {
						System.out.println("Esa fecha ya no esta disponible");
					} else {
						System.out.println("¿Hasta que dia quieres el servicio?(yyyy-mm-dd)");
						fechaFin = teclado.next();
						dateFin = LocalDate.parse(fechaFin, formatter);
						diferenciaDias = ChronoUnit.DAYS.between(dateInicio, dateFin);
						if (diferenciaDias < 1) {
							System.out.println("Las fechas introducidas no son válidas, intentalo de nuevo");
						} 
					}
					
				} while(diferenciaDias < 1);
				costeTotal = listaServicios.get(opcion - 1).getCoste() * (int) diferenciaDias;
				fechaCorrecta=true;
			} while (!fechaCorrecta);
		} else {
			boolean fechaCorrecta = false;
			do {
				System.out.println("¿Que dia quieres el servicio?(yyyy-mm-dd)");
				fecha = teclado.next();
				dateInicio = LocalDate.parse(fecha, formatter);
				if (ChronoUnit.DAYS.between(dateInicio, fechaActual) > 1) {
					System.out.println("Esa fecha ya no esta disponible");
				} else {
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
					fechaCorrecta=true;
				}
			} while(!fechaCorrecta);
		}
		cita.setCodAnimal(animal.getCodigoChip());
		cita.setCosteTotal(costeTotal);
		cita.setFecha(fecha);
		cita.setFechaFin(fechaFin);
		cita.setHora(hora);
		cita.setCodSucursal(sucursalCodigo);
		cita.setCodServicio(codServicio);
		System.out.println("Cita realizada con exito, confirmala en el carrito");
		return cita;
	}
	
}