package modelo.negocio;

import java.util.List;

import modelo.entidad.Pasajero;
import modelo.persistencia.DaoPasajeroMySql;
import modelo.persistencia.interfaces.DaoPasajero;

public class GestorPasajero {
	
	private DaoPasajero daoPasajero = new DaoPasajeroMySql();
	
	/**
	 * Método que permite añadir un pasajero a una bbdd. Se verifica que los datos introducidos cumplen con 
	 * las restricciones establecidas en la bbdd.
	 * @param pasajero el objeto de la clase Pasajero que se añadirá a la bbdd
	 * @return true en caso de que haya podido añadir el pasajero a la bbdd
	 */
	public boolean aniadir(Pasajero pasajero) {
		boolean isAlta = false;
		boolean isVerificado = verificarPasajero(pasajero);
		if (isVerificado) {
			isAlta = daoPasajero.create(pasajero);
		}
		return isAlta;			
	}
	
	/**
	 * Método que permite añadir datos a la FK id_coche de la tabla pasajeros. Se verifica que los datos introducidos cumplen con 
	 * las restricciones establecidas en la bbdd.
	 * @param idPasajero es el id perteneciente a la PK de la tabla pasajeros
	 * @param idCoche es el id_coche (FK) de la tabla pasajeros. Se corresponderá con el ID de un coche existente
	 * @return
	 */
	public boolean aniadir(int idPasajero, int idCoche) {
		boolean isAlta = false;
		boolean isVerificadoPasajero = verificarId(idPasajero);
		boolean isVerificadoCoche = verificarId(idCoche);
		
		if (isVerificadoPasajero && isVerificadoCoche) {
			isAlta = daoPasajero.create(idPasajero, idCoche);
		}
		return isAlta;			
	}
	
	/**
	 * Método que permite borrar un pasajero por ID
	 * @param id el ID del objeto pasajero que se quiere borrar
	 * @return devuelve true si el pasajero es borrado
	 */
	public boolean borrar(int id) {
		boolean isBaja = false;
		boolean isVerificado = verificarId(id);
		if (isVerificado) {
			isBaja = daoPasajero.delete(id);
		}
		return isBaja;		
	}
	
	/**
	 * Método que permite eliminar de la tabla pasajeros el valor asignado en su FK id_coche
	 * @param idPasajero es el ID del pasajero del que se quiere borrar la FK
	 * @return devuelve true si se borra el pasajero
	 */
	public boolean desasignar(int idPasajero) {
		boolean isBaja = false;
		boolean isVerificadoPasajero = verificarId(idPasajero);
		if (isVerificadoPasajero) {
			isBaja = daoPasajero.unAssign(idPasajero);
		}
		return isBaja;		
	}
	
	/**
	 * Método que permite modificar los datos de un pasajero, excepto la FK id_coche. Se verifica que los datos introducidos cumplen con 
	 * las restricciones establecidas en la bbdd.
	 * @param pasajero es la instancia pasajero que se quiere modificiar
	 * @return devuelve true si se actualizan correctamente los datos del pasajero
	 */
	public boolean modificar(Pasajero pasajero) {
		boolean isModificado = false;
		boolean isVerificado = verificarPasajero(pasajero);
		if (isVerificado) {
			isModificado = daoPasajero.update(pasajero);
		}
		return isModificado;
	}
	
	/**
	 * Método que permite leer los datos de un pasajero. Se verifica que los datos introducidos cumplen con 
	 * las restricciones establecidas en la bbdd.
	 * @param id es el ID de la instacia pasajero que se quiere consultar
	 * @return devuelve los datos de la instancia consultada
	 */
	public Pasajero consultar(int id) {
		Pasajero pasajero = new Pasajero();
		boolean isVerificado = verificarId(id);
		if (isVerificado) {
			pasajero = daoPasajero.read(id);
		}
		return pasajero;
		
	}
	
	/**
	 * Método que lista todos los pasajeros dados de alta en la tabla pasajeros
	 * @return devuelve una lista por consola
	 */
	public List<Pasajero> listar() {
		List<Pasajero> listaPasajeros = daoPasajero.list();
		return listaPasajeros;
	}
	
	/**
	 * Método que lista todos los pasajeros asociados a un coche
	 * @param idCoche el ID del coche del que se quiere sacar la lista
	 * @return devuelve una lista por consola
	 */
	public List<Pasajero> listar(int idCoche) {
		List<Pasajero> listaPasajeros = daoPasajero.list(idCoche);
		return listaPasajeros;
	}
	
	/**
	 * Método que verifica que los datos introducidos cumplen con los parámetros establecidos en la tabla pasajeros
	 * @param pasajero la instancia pasajero de la que se quieren comprobar los datos
	 * @return devuelve true si la verificación es correcta
	 */
	public boolean verificarPasajero(Pasajero pasajero) {
		
		String patronVarchar = "^\\w{1,20}$";
		boolean isVerificado = false;
		if (!pasajero.getNombre().matches(patronVarchar)) {
			System.out.println("El nombre debe ser una cadena de texto");
		} else if (!(pasajero.getEdad() < 0) && pasajero.getEdad() > 999) {
			System.out.println("La edad debe ser un número entero positivo de máximo 3 cifras");
		} else if (!(pasajero.getPeso() < 0) && pasajero.getPeso() > 999 ) {
			System.out.println("El peso debe ser una cantidad positiva con un máximo de 5 cifras incluyendo decimales");		
		} else if (pasajero.getId() < 1 && pasajero.getId() > 9999) {
			System.out.println("El ID debe estar entre 1 y 9999");
		} else {
			isVerificado = true;
		}
		return isVerificado;
	}
	
	/**
	 * Método que verifica que el id introducido cumple con los parámetros establecidos en la tabla pasajeros
	 * @param id el id del pasajero 
	 * @return devuelve true si la verificación es correcta
	 */
	public boolean verificarId(int id) {
		boolean isVerificado = false;
		if (id < 1 && id > 9999) {
			System.out.println("El ID debe estar entre 1 y 9999");
		} else {
			isVerificado = true;
		}
		return isVerificado;
	}
	
	
	
	
	
	

}
