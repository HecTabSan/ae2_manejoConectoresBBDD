package vista;

import java.util.Scanner;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.negocio.GestorCoche;
import modelo.negocio.GestorPasajero;

public class Vista {
	private static Scanner sc;

	public static void main(String[] args) {

		boolean end = false;
		sc = new Scanner(System.in);

		do {
			GestorCoche gestorCoche = new GestorCoche();
			Coche coche = new Coche();
			menuPrincipal();
			int opcion = sc.nextInt();
			switch (opcion) {
			case 1:
				aniadir(coche, gestorCoche);
				break;
			case 2:
				borrar(coche, gestorCoche);
				break;
			case 3:
				consultar(coche, gestorCoche);
				break;
			case 4:
				modificar(coche, gestorCoche);
				break;
			case 5:
				listar(coche, gestorCoche);
				break;
			case 6:
				boolean end2 = false;
				do {
					GestorPasajero gestorPasajero = new GestorPasajero();				
					Pasajero pasajero = new Pasajero();
					menuSecundario();
					int caso = sc.nextInt();
					switch (caso) {
						case 1:
							aniadir(pasajero, gestorPasajero);
							break;
						case 2:
							borrar(pasajero, gestorPasajero);
							break;
						case 3:
							consultar(pasajero, gestorPasajero);
							break;
						case 4:
							listar(pasajero, gestorPasajero);
							break;
						case 5:
							aniadir(pasajero, gestorPasajero, coche, gestorCoche);
							break;
						case 6:
							borrar(pasajero, gestorPasajero, coche, gestorCoche);
							break;
						case 7:
							listar(pasajero, gestorPasajero, coche, gestorCoche);
							break;
						case 8:
							end2 = true; 
							break;
					}
				}while (!end2);
				break;
			case 7:
				end = true;
				sc.close();
				break;
			}
		} while (!end);

		System.out.println("Fin del programa");
	}

	private static void menuPrincipal() {
		System.out.println("Elije una opción: ");
		System.out.println("1 - Añadir nuevo coche");
		System.out.println("2 - Borrar coche por ID");
		System.out.println("3 - Consultar coche por ID");
		System.out.println("4 - Modificar coche por ID");
		System.out.println("5 - Listar todos los coches");
		System.out.println("6 - Gestión de pasajeros");
		System.out.println("7 - Terminar programa");
	}

	private static void menuSecundario() {
		System.out.println("Elije una opción: ");
		System.out.println("1 - Añadir nuevo pasajero");
		System.out.println("2 - Borrar pasajero por ID");
		System.out.println("3 - Consultar pasajero por ID");
		System.out.println("4 - Listar todos los pasajeros");
		System.out.println("5 - Añadir pasajero a coche");
		System.out.println("6 - Eliminar pasajero de un coche");
		System.out.println("7 - Listar pasajeros de un coche");
		System.out.println("8 - Salir submenú");
	}

	private static void aniadir(Coche coche, GestorCoche gestorCoche) {
		System.out.println("Introduce la matrícula del coche: ");
		String matricula = sc.next();
		coche.setMatricula(matricula);

		System.out.println("Introduce la marca del coche: ");
		String marca = sc.next();
		coche.setMarca(marca);

		System.out.println("Introduce el modelo del coche: ");
		String modelo = sc.next();
		coche.setModelo(modelo);

		System.out.println("Introduce el color del coche: ");
		String color = sc.next();
		coche.setColor(color);

		boolean alta = gestorCoche.aniadir(coche);
		if (alta) {
			System.out.println("El coche se añadió correctamente");
		} else {
			System.out.println("No se ha podido añadir el coche");
		}
	}

	private static void borrar(Coche coche, GestorCoche gestorCoche) {
		System.out.println("Introduce el ID del coche");
		int id = sc.nextInt();
		boolean baja = gestorCoche.borrar(id);
		if (baja) {
			System.out.println("El coche se borró correctamente");
		} else {
			System.out.println("No se ha podido borrar el coche");
		}
	}

	private static void consultar(Coche coche, GestorCoche gestorCoche) {
		System.out.println("Introduce el ID del coche");
		int id1 = sc.nextInt();
		System.out.println("El coche es: " + gestorCoche.consultar(id1));
	}

	private static void modificar(Coche coche, GestorCoche gestorCoche) {
		System.out.println("Introduce el ID del coche");
		int id2 = sc.nextInt();
		coche.setId(id2);

		System.out.println("Introduce la matrícula del coche: ");
		String matricula1 = sc.next();
		coche.setMatricula(matricula1);

		System.out.println("Introduce la marca del coche: ");
		String marca1 = sc.next();
		coche.setMarca(marca1);

		System.out.println("Introduce el modelo del coche: ");
		String modelo1 = sc.next();
		coche.setModelo(modelo1);

		System.out.println("Introduce el color del coche: ");
		String color1 = sc.next();
		coche.setColor(color1);

		boolean modificacion = gestorCoche.modificar(coche);
		if (modificacion) {
			System.out.println("El coche se modificó correctamente");
		} else {
			System.out.println("No se ha podido modificar el coche");
		}
	}

	private static void listar(Coche coche, GestorCoche gestorCoche) {
		System.out.println("La lista de coches es: ");
		System.out.println(gestorCoche.listar());
	}

	private static void aniadir(Pasajero pasajero, GestorPasajero gestorPasajero) {
		System.out.println("Introduce el nombre del pasajero: ");
		String nombre = sc.next();
		pasajero.setNombre(nombre);

		System.out.println("Introduce la edad del pasajero: ");
		String edad = sc.next();
		pasajero.setEdad(Integer.parseInt(edad));

		System.out.println("Introduce el peso del pasajero: ");
		String peso = sc.next();
		pasajero.setPeso(Double.parseDouble(peso));

		boolean alta = gestorPasajero.aniadir(pasajero);
		if (alta) {
			System.out.println("El pasajero se añadió correctamente");
		} else {
			System.out.println("No se ha podido añadir el pasajero");
		}
	}

	private static void borrar(Pasajero pasajero, GestorPasajero gestorPasajero) {
		System.out.println("Introduce el ID del pasajero");
		int id = sc.nextInt();
		boolean baja = gestorPasajero.borrar(id);
		if (baja) {
			System.out.println("El pasajero se borró correctamente");
		} else {
			System.out.println("No se ha podido borrar el pasajero");
		}
	}

	private static void consultar(Pasajero pasajero, GestorPasajero gestorPasajero) {
		Coche coche = new Coche();
		GestorCoche gestorCoche = new GestorCoche();
		listar(coche, gestorCoche);
		System.out.println("Introduce el ID del coche");
		int id = sc.nextInt();
		System.out.println("El pasajero es: " + gestorPasajero.consultar(id));
	}

	private static void listar(Pasajero pasajero, GestorPasajero gestorPasajero) {
		System.out.println("La lista de pasajeros es: ");
		System.out.println(gestorPasajero.listar());
	}

	private static void aniadir(Pasajero pasajero, GestorPasajero gestorPasajero, Coche coche,
			GestorCoche gestorCoche) {
		listar(coche, gestorCoche);
		System.out.println("Introduce el ID del coche: ");
		int idCoche = sc.nextInt();
		listar(pasajero, gestorPasajero);
		System.out.println("Introduce el ID del pasajero: ");
		int idPasajero = sc.nextInt();

		boolean asignacion = gestorPasajero.aniadir(idPasajero, idCoche);

		if (asignacion) {
			System.out.println("El pasajero fue asignado correctamente");
		} else {
			System.out.println("El pasajero no pudo ser asignado");
		}
	}

	private static void borrar(Pasajero pasajero, GestorPasajero gestorPasajero, Coche coche, GestorCoche gestorCoche) {

		listar(pasajero, gestorPasajero, coche, gestorCoche);

		System.out.println("Introduce el ID del pasajero");
		int idPasajero = sc.nextInt();

		boolean baja = gestorPasajero.desasignar(idPasajero);

		if (baja) {
			System.out.println("El pasajero fue borrado correctamente");
		} else {
			System.out.println("El pasajero no pudo ser borrado");
		}
	}

	private static void listar(Pasajero pasajero, GestorPasajero gestorPasajero, Coche coche, GestorCoche gestorCoche) {
		listar(coche, gestorCoche);
		System.out.println("Introduce el ID del coche: ");
		int idCoche = sc.nextInt();

		System.out.println(gestorPasajero.listar(idCoche));
	}

}
