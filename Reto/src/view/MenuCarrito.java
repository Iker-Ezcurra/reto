package view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.Animal;
import modelo.Ave;
import modelo.Cita;
import modelo.Peludo;
import modelo.Reptil;
import repositorios.RepositorioAnimal;
import repositorios.RepositorioAve;
import repositorios.RepositorioCita;
import repositorios.RepositorioPeludo;
import repositorios.RepositorioReptil;

public class MenuCarrito {

	public static void mostrar(ArrayList<Cita> listaCitas, ArrayList<Animal> listaAnimales) throws SQLException {
		Scanner sc = new Scanner(System.in);
		int opcion = 0;
		int h = 0;
		boolean fin = false;
		boolean finalizado = false;
		
		Cita cita;
		Peludo peludo = new Peludo();
		Ave ave = new Ave();
		Reptil reptil = new Reptil();
		Animal animal = new Animal();
		
		do {
			System.out.println("--- Carrito ---");
			System.out.println();
			
			if (listaCitas.size() == 0) {
				System.out.println("Todavia no hay ninguna reserva");
				System.out.println();
				System.out.println("1. Atras");
				if (sc.nextInt() == 1) {
					finalizado = true;
				} else {
					System.out.println("Opción inválida");
				}
			} else {
				for (int i = 0; i<listaCitas.size(); i++) {
					System.out.println((i+1) +". "+ listaCitas.get(i).toString() +" "+listaAnimales.get(i).toString()+"\n");
					h = h + listaCitas.get(i).getCosteTotal();
				}
				System.out.println();
				System.out.println("Coste total: " + h + "€");
				System.out.println();
				System.out.println("1. Cancelar cita");
				System.out.println("2. Confirmar reservas");
				System.out.println("3. Atras");
				opcion = sc.nextInt();
				switch (opcion){
					case 1:
						do {
							System.out.println("Que cita quieres cancelar");
							int borrar = sc.nextInt();
							System.out.println("¿Estas seguro? (Y/N)");
							char eleccion = sc.next().charAt(0);
							if (eleccion == 'Y' || eleccion == 'y') {
								listaCitas.remove(borrar-1);
								listaAnimales.remove(borrar-1);
								System.out.println("La cita se ha eliminado con éxito");
								fin = true;
							} else if (eleccion == 'N' || eleccion == 'n') {
								System.out.println("Se ha cancelado la operación de borrar cita");
								fin = true;
							} else {
								System.out.println("Opción inválida");
							}
						} while (!fin);
						break;
					case 2: 
						do {
							System.out.println("¿Confirmar reservas? (Y/N)");
							char eleccion = sc.next().charAt(0);
							if (eleccion == 'Y' || eleccion == 'y') {
								for (int i = 0; i < listaCitas.size(); i++) {
									cita = listaCitas.get(i);
									RepositorioCita.insertar(cita);
									animal = listaAnimales.get(i);
									if(!(RepositorioAnimal.comprobar(animal))) {
										RepositorioAnimal.insertar(animal);
										if (animal instanceof Peludo) {
											peludo = (Peludo) animal;
											RepositorioPeludo.insertar(peludo);
										} else if (animal instanceof Ave) {
											ave = (Ave) animal;
											RepositorioAve.insertar(ave);
										} else if (animal instanceof Reptil) {
											reptil = (Reptil) animal;
											RepositorioReptil.insertar(reptil);
										}
									}
									listaCitas.remove(i);
									listaAnimales.remove(i);
								}
								System.out.println("Reservas confirmadas");
								fin = true;
							} else if (eleccion == 'N' || eleccion == 'n') {
								System.out.println("Se ha cancelado la operación de confirmar citas");
								fin = true;
							} else {
								System.out.println("Opción inválida");
							}
						} while (!fin);
						break;
					case 3:
						finalizado = true;
						break;
					default:
						System.out.println("Opción inválida");
				}
			}
		} while(!finalizado);
	}
}