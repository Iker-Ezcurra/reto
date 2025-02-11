package view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.Animal;
import modelo.Ave;
import modelo.Cliente;
import modelo.Peludo;
import modelo.Reptil;
import repositorios.RepositorioAnimal;
import repositorios.RepositorioAve;
import repositorios.RepositorioPeludo;
import repositorios.RepositorioReptil;

public class MenuAnimal {
	
	//Dado un ArrayList de animales, se instancia un animal, ya sea de cero o cogiendo los datos de la base de datos, lo que el cliente decida, y se añade al arrayList
	public static ArrayList<Animal> mostrar(ArrayList<Animal> listaAnimales, Cliente cliente) throws SQLException {
		Scanner sc = new Scanner(System.in);
		Animal animal = new Animal();
		boolean fin = false;
		while (!fin) {
			System.out.println("\n--- Reservando cita---");
			System.out.println();
			System.out.println("¿Su animal ya está registrado?");
			System.out.println("1. Si");
			System.out.println("2. No");
			int opcion = sc.nextInt();
			if (opcion == 1) {
				System.out.println("Introduce el codigo de su chip (000000000XXXX): ");
				String codChip = sc.next();
				
				if (RepositorioAnimal.comprobar(codChip)) {
					String tipo=RepositorioAnimal.tipoAnimal(codChip);
					
					if (tipo.equalsIgnoreCase("Reptil")) {
						System.out.println("Animal encontrado");
						listaAnimales.add( RepositorioReptil.construirReptil(codChip));
						fin = true;
					} else if (tipo.equalsIgnoreCase("Ave")) {
						System.out.println("Animal encontrado");
						listaAnimales.add( RepositorioAve.construirAve(codChip));
						fin = true;
					} else if (tipo.equalsIgnoreCase("Peludos")) {
						System.out.println("Animal encontrado");
						listaAnimales.add(RepositorioPeludo.construirPeludo(codChip));
						fin = true;
					} else {
						System.out.println("Animal encontrado");
						listaAnimales.add(RepositorioAnimal.construirAnimal(codChip));
						fin = true;
					}
					
				} else {
					fin = buscarAnimalArrayList(listaAnimales, codChip);
					if (fin) {
						listaAnimales = agregarAnimalArrayList(listaAnimales, copiarAnimalDelArrayList(listaAnimales, codChip));
					}
				}
				
				if (fin == false) {
					System.out.println("Este chip no pertenece a ningun animal");
				}
				
			} else if (opcion == 2){
				System.out.println("Registra a tu animal");
				animal = InstanciarPorTeclado.Animal();
				if (RepositorioAnimal.comprobar(animal.getCodigoChip())) {
					System.out.println("Este animal ya estaba registrado previamente");
				} else {
					listaAnimales.add(animal);
					System.out.println("Animal registrado correctamente");
					fin = true;
				}
			} else if (opcion == 0){
				String volver;
				System.out.println("¿Esta seguro que quiere volver al paso anterior? Y/N");
				volver = sc.next();
				if (volver.equalsIgnoreCase("Y")) {
					MenuPrincipal.mostrar(cliente);
				}
			} else {
				System.out.println("Opcion incorrecta");
			}
		}
		return listaAnimales;
	}

	//Dado un ArrayList de animales y un animal, agrega este al ArrayList
	private static ArrayList<Animal> agregarAnimalArrayList(ArrayList<Animal> listaAnimales, Animal animal) {
		if (animal != null) {
			listaAnimales.add(animal);
		}
		return listaAnimales;
	}
	
	//Dado un ArrayList de animales y un código chip devuelve una instancia de animal que o bien tendrá los datos pertenecientes a ese código chip o bien será null si no se encuentra un animal con ese código en el arrayList
	private static Animal copiarAnimalDelArrayList(ArrayList<Animal> listaAnimales, String codChip) {
		int aux = listaAnimales.size();
		Animal animal = null;
		for (int i = 0; i < aux; i++) {
			if (listaAnimales.get(i).getCodigoChip().equals(codChip)) {
				if(listaAnimales.get(i) instanceof Reptil) {
					animal = (Reptil) listaAnimales.get(i);
				} else if (listaAnimales.get(i) instanceof Ave) {
					animal = (Ave) listaAnimales.get(i);
				} else if (listaAnimales.get(i) instanceof Peludo) {
					animal = (Peludo) listaAnimales.get(i);
				} else {
					animal = listaAnimales.get(i);
				}
			}
		}
		return animal;
	}

	//Dado un ArrayList de animales y un código chip devuelve un booleano que expresa si hay un animal con ese código en el ArrayList
	private static boolean buscarAnimalArrayList(ArrayList<Animal> listaAnimales, String codChip) {
		boolean encontrado = false;
		int aux = listaAnimales.size();
		for (int i = 0; i < aux; i++) {
			if ((codChip.equals(listaAnimales.get(i).getCodigoChip()))) {
				encontrado = true;
			}
		}
		return encontrado;
	}
	
}