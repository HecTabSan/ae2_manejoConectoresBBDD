package vista;

import java.util.Scanner;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.negocio.GestorCoche;
import modelo.negocio.GestorPasajero;
import modelo.persistencia.DAObbdd;

public class Vista {
	private static Scanner sc;

	public static void main(String[] args) {

		boolean end = false;
		sc = new Scanner(System.in);
		
		// Preparación de la base de datos 
		DAObbdd dao = new DAObbdd();
		
		if(!dao.connect()) {
			dao.createDatabase();
			dao.checkTable();
		} else {
			System.out.println("Conectado a la base de datos");
			dao.checkTable();
		}

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
				borrar(gestorCoche);
				break;
			case 3:
				consultar(gestorCoche);
				break;
			case 4:
				modificar(coche, gestorCoche);
				break;
			case 5:
				listar(gestorCoche);
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
						borrar(gestorPasajero);
						break;
					case 3:
						consultar(gestorPasajero);
						break;
					case 4:
						listar(gestorPasajero);
						break;
					case 5:
						aniadir(pasajero, gestorPasajero, coche, gestorCoche);
						break;
					case 6:
						borrar(pasajero, gestorPasajero, coche, gestorCoche);
						break;
					case 7:
						listar(gestorPasajero, coche, gestorCoche);
						break;
					case 8:
						end2 = true;
						break;
					}
				} while (!end2);
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

	/**
	 * Método que añade un coche a la bd
	 * @param coche objeto cuyos atributos se corresponden con las columnas de la tabla coches de la bd
	 * @param gestorCoche objeto de la clase GestoCoche que establece las reglas de negocio
	 */
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
	
	/**
	 * Método que borra un coche de la bd. Requiere de un ID tipo entero para la consulta
	 * @param coche objeto cuyos atributos se corresponden con las columnas de la tabla coches de la bd
	 * @param gestorCoche objeto de la clase GestoCoche que establece las reglas de negocio	 
	 */	 
	 
	private static void borrar(GestorCoche gestorCoche) {
		System.out.println("Introduce el ID del coche");
		while (!sc.hasNextInt()) {
			System.out.println("El ID debe ser un valor numérico");
			sc.nextLine();
			System.out.println("Introduce el ID del coche");
		}
		int id = sc.nextInt();
		boolean baja = gestorCoche.borrar(id);
		if (baja) {
			System.out.println("El coche se borró correctamente");
		} else {
			System.out.println("No se ha podido borrar el coche");
		}		
	}
	
	/**
	 * Método que lee un dato de la bd y lo imprime en la consola. Requiere de un ID tipo entero para la consulta	 
	 * @param gestorCoche objeto de la clase GestoCoche que establece las reglas de negocio	  
	 */	
	private static void consultar(GestorCoche gestorCoche) {
		System.out.println("Introduce el ID del coche");
		while (!sc.hasNextInt()) {
			System.out.println("El ID debe ser un valor numérico");
			sc.nextLine();
			System.out.println("Introduce el ID del coche");
		}
		int id1 = sc.nextInt();
		System.out.println("El coche es: " + gestorCoche.consultar(id1));
	}
	
	/**
	 * Método que modifica un dato de la bd. Requiere de un ID tipo entero para seleccionar la consulta.
	 * @param coche objeto cuyos atributos se corresponden con las columnas de la tabla coches de la bd
	 * @param gestorCoche objeto de la clase GestoCoche que establece las reglas de negocio	  
	 */	
	private static void modificar(Coche coche, GestorCoche gestorCoche) {
		System.out.println("Introduce el ID del coche");
		while (!sc.hasNextInt()) {
			System.out.println("El ID debe ser un valor numérico");
			sc.nextLine();
			System.out.println("Introduce el ID del coche");
		}
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
	
	/**
	 * Método que lista todos los objetos coche de la bd.	 
	 * @param gestorCoche objeto de la clase GestoCoche que establece las reglas de negocio	  
	 */	
	private static void listar(GestorCoche gestorCoche) {
		System.out.println("La lista de coches es: ");
		System.out.println(gestorCoche.listar());
	}
	
	/**
	 * Método que añade un nuevo pasajero a la tabla pasajeros.
	 * @param pasajero objeto cuyos atributos se corresponden con las columnas de la tabla pasajeros de la bd
	 * @param gestorPasajero objeto de la clase GestorPasajero que establece las reglas de negocio	  
	 */	
	private static void aniadir(Pasajero pasajero, GestorPasajero gestorPasajero) {
		System.out.println("Introduce el nombre del pasajero: ");
		String nombre = sc.next();
		pasajero.setNombre(nombre);

		System.out.println("Introduce la edad del pasajero: ");
		while (!sc.hasNextInt()) {
			System.out.println("La edad debe ser un valor numérico");
			sc.nextLine();
			System.out.println("Introduce la edad del pasajero");
		}
		int edad = sc.nextInt();
		pasajero.setEdad((edad));

		System.out.println("Introduce el peso del pasajero: ");
		while (!sc.hasNextDouble()) {
			System.out.println("El peso debe ser un valor numérico");
			sc.nextLine();
			System.out.println("Introduce el peso del pasajero");
		}
		Double peso = sc.nextDouble();
		pasajero.setPeso(peso);

		boolean alta = gestorPasajero.aniadir(pasajero);
		if (alta) {
			System.out.println("El pasajero se añadió correctamente");
		} else {
			System.out.println("No se ha podido añadir el pasajero");
		}
	}
	
	/**
	 * Método que borra un pasajero de la tabla pasajeros. Requiere un ID tipo entero para la consulta
	 * @param gestorPasajero objeto de la clase GestorPasajero que establece las reglas de negocio	  
	 */	
	private static void borrar(GestorPasajero gestorPasajero) {
		System.out.println("Introduce el ID del pasajero");
		while (!sc.hasNextInt()) {
			System.out.println("El ID debe ser un valor numérico");
			sc.nextLine();
			System.out.println("Introduce el ID del pasajero");
		}
		int id = sc.nextInt();
		boolean baja = gestorPasajero.borrar(id);
		if (baja) {
			System.out.println("El pasajero se borró correctamente");
		} else {
			System.out.println("No se ha podido borrar el pasajero");
		}
	}
	
	/**
	 * Método que consulta pasajero de la tabla pasajeros. El ID consultado debe ser tipo entero
	 * @param gestorPasajero objeto de la clase GestorPasajero que establece las reglas de negocio	  
	 */	
	private static void consultar(GestorPasajero gestorPasajero) {		
		GestorCoche gestorCoche = new GestorCoche();
		listar(gestorCoche);
		System.out.println("Introduce el ID del coche");
		while (!sc.hasNextInt()) {
			System.out.println("El ID debe ser un valor numérico");
			sc.nextLine();
			System.out.println("Introduce el ID del coche");
		}
		int id = sc.nextInt();
		System.out.println("El pasajero es: " + gestorPasajero.consultar(id));
	}
	
	/**
	 * Método que lista todos los pasajeros de la tabla pasajeros.
	 * @param gestorPasajero objeto de la clase GestorPasajero que establece las reglas de negocio	  
	 */	
	private static void listar(GestorPasajero gestorPasajero) {
		System.out.println("La lista de pasajeros es: ");
		System.out.println(gestorPasajero.listar());
	}

	/**
	 * Método que asigna un pasajero a un coche, modificando así la FK de la tabla pasajeros
	 * @param pasajero objeto cuyos atributos se corresponden con las columnas de la tabla pasajeros de la bd
	 * @param gestorPasajero objeto de la clase GestorPasajero que establece las reglas de negocio
	 * @param coche objeto cuyos atributos se corresponden con las columnas de la tabla coches de la bd
	 * @param gestorCoche objeto de la clase gestorCoche que establece las reglas de negocio
	 */
	private static void aniadir(Pasajero pasajero, GestorPasajero gestorPasajero, Coche coche,
			GestorCoche gestorCoche) {
		listar(coche, gestorCoche);
		System.out.println("Introduce el ID del coche: ");
		while (!sc.hasNextInt()) {
			System.out.println("El ID debe ser un valor numérico");
			sc.nextLine();
			System.out.println("Introduce el ID del coche");
		}
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

	/**
	 * Método que borra un pasajero asignado a un coche, modificando así la FK de la tabla pasajeros (la pasa a null)
	 * @param pasajero objeto cuyos atributos se corresponden con las columnas de la tabla pasajeros de la bd
	 * @param gestorPasajero objeto de la clase GestorPasajero que establece las reglas de negocio
	 * @param coche objeto cuyos atributos se corresponden con las columnas de la tabla coches de la bd
	 * @param gestorCoche objeto de la clase gestorCoche que establece las reglas de negocio
	 */
	private static void borrar(Pasajero pasajero, GestorPasajero gestorPasajero, Coche coche, GestorCoche gestorCoche) {

		listar(pasajero, gestorPasajero, coche, gestorCoche);

		System.out.println("Introduce el ID del pasajero");
		while (!sc.hasNextInt()) {
			System.out.println("El ID debe ser un valor numérico");
			sc.nextLine();
			System.out.println("Introduce el ID del pasajero");
		}
		int idPasajero = sc.nextInt();

		boolean baja = gestorPasajero.desasignar(idPasajero);

		if (baja) {
			System.out.println("El pasajero fue borrado correctamente");
		} else {
			System.out.println("El pasajero no pudo ser borrado");
		}
	}

	/**
	 * Método que lista los pasajeros de un coche	 
	 * @param gestorPasajero objeto de la clase GestorPasajero que establece las reglas de negocio
	 * @param coche objeto cuyos atributos se corresponden con las columnas de la tabla coches de la bd
	 * @param gestorCoche objeto de la clase gestorCoche que establece las reglas de negocio
	 */
	private static void listar(GestorPasajero gestorPasajero, Coche coche, GestorCoche gestorCoche) {
		listar(coche, gestorCoche);
		System.out.println("Introduce el ID del coche: ");
		int idCoche = sc.nextInt();

		System.out.println(gestorPasajero.listar(idCoche));
	}

}
