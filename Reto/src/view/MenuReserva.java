package view;

import java.sql.SQLException;
import java.util.Scanner;

import modelo.Animal;
import modelo.Ave;
import modelo.Cita;
import modelo.Peludo;
import modelo.Reptil;
import repositorios.RepositorioServicio;

public class MenuReserva {
	public static Cita mostrar(Animal animal) throws SQLException {
		Scanner teclado = new Scanner(System.in);
		int costeTotal = 0;
		String fecha;
		String fechaFin;
		String hora;
		Cita cita = new Cita();
		int aux;
		System.out.println("--- ¿A que sucursal desea asistir? ---");
		System.out.println();
		System.out.println("Escoge una de las opciones: ");
		System.out.println("1. Irun");
		System.out.println("2. Donostia");
		System.out.println("3. Renteria");
		System.out.println("4. Bilbao");
		int sucursal = teclado.nextInt();
		System.out.println();
		System.out.println("Elija entre los servicios disponibles para su animal:");
		if(animal instanceof Reptil) {
			aux = RepositorioServicio.serviciosPorSurcursalYAnimal(sucursal, "Reptil");
		} else if (animal instanceof Ave) {
			aux = RepositorioServicio.serviciosPorSurcursalYAnimal(sucursal, "Ave");
		} else if (animal instanceof Peludo) {
			aux = RepositorioServicio.serviciosPorSurcursalYAnimal(sucursal, "Peludos");
		} else if (animal instanceof Animal){
			aux = RepositorioServicio.serviciosPorSurcursalYAnimal(sucursal, "Todos");
		}
		int servicio = teclado.nextInt();
		if(servicio == 1) {
			System.out.println("¿Cuantos dias de hotel quiere reservar?");
			int dias = teclado.nextInt();
			costeTotal = RepositorioServicio.precio(servicio) * dias;
		} else if (servicio == 2) {
			costeTotal =  RepositorioServicio.precio(servicio);
		} else if (servicio == 3) {
			RepositorioServicio.precio(servicio);
		}
		return cita;
	}
}
