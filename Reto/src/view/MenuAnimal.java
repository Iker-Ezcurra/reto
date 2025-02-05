package view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.Animal;
import modelo.Ave;
import modelo.Peludo;
import modelo.Reptil;
import repositorios.RepositorioAnimal;
import repositorios.RepositorioAve;
import repositorios.RepositorioPeludo;
import repositorios.RepositorioReptil;

public class MenuAnimal {
	
	public static ArrayList<Animal> mostrar(ArrayList<Animal> listaAnimales) throws SQLException {
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
				System.out.println("Introduce el codigo de su chip");
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
					} else if (tipo.equalsIgnoreCase("Peludo")) {
						System.out.println("Animal encontrado");
						listaAnimales.add(RepositorioPeludo.construirPeludo(codChip));
						fin = true;
					} else {
						System.out.println("Animal encontrado");
						listaAnimales.add(RepositorioAnimal.construirAnimal(codChip));
						fin = true;
					}
					
				} else {
					fin = buscarAnimalEnElArray(listaAnimales, codChip);
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
			} else {
				System.out.println("Opcion incorrecta");
			}
		}
		return listaAnimales;
	}

	private static boolean buscarAnimalEnElArray(ArrayList<Animal> listaAnimales, String codChip) {
		boolean encontrado = false;
		for (int i = 0; i < listaAnimales.size(); i++) {
			if ((codChip.equals(listaAnimales.get(i).getCodigoChip()))) {
				if(listaAnimales.get(i) instanceof Reptil) {
					Reptil reptil = (Reptil) listaAnimales.get(i);
					listaAnimales.add(reptil);
					encontrado = true;
				} else if (listaAnimales.get(i) instanceof Ave) {
					Ave ave = (Ave) listaAnimales.get(i);
					listaAnimales.add(ave);
					encontrado = true;
				} else if (listaAnimales.get(i) instanceof Peludo) {
					Peludo peludo = (Peludo) listaAnimales.get(i);
					listaAnimales.add(peludo);
					encontrado = true;
				} else if (listaAnimales.get(i) instanceof Animal){
					Animal animal = listaAnimales.get(i);
					listaAnimales.add(animal);
					encontrado = true;
				}
			}
		}
		return encontrado;
	}
	
}