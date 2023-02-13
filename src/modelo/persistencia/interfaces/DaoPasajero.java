package modelo.persistencia.interfaces;

import java.util.List;
import modelo.entidad.Pasajero;

public interface DaoPasajero {

	/**
	 * Método que da de alta un pasajero en la BBDD. En la bbdd se genera 
	 * un ID numérico de forma automática e incremental.
	 * 
	 * @param coche es el coche que se da de alta
	 * @return true si se da de alta. False si existe error en BBDD
	 */
	boolean create(Pasajero pasajero);

	/**
	 * Método que da de alta un pasajero nuevo en un determinado coche previa 
	 * petición de su id.
	 * @param idPasajero
	 * @param idCoche
	 * @return
	 */
	boolean create(int idPasajero, int idCoche);

	/**
	 * Método que devuelve los atributos de un pasajero buscado por su ID 
	 * en la bbdd
	 * 
	 * @param id es el ID del coche del que se quieren obtener los datos
	 * @return los atributos de clase (ID, matrícula, marca, modelo y color)
	 */
	Pasajero read(int id);

	/**
	 * Método que devuelve una lista de todos los pasajeros de la bbdd
	 * 
	 * @return la lista de todos los pasajeros de la bbdd
	 */
	List<Pasajero> list();

	/**
	 * Método que devuelve una lista con todos los pasajeros pertenecientes a un 
	 * id de coche
	 * @param idCoche es el ID del coche del que se quieren listar los pasajeros	 
	 * @return
	 */
	List<Pasajero> list(int idCoche);
	
	/**
	 * Método que modifica un coche en la BBDD.
	 * 
	 * @param coche es el coche que se modifica
	 * @return true si modifica. False si existe error en BBDD
	 */
	boolean update(Pasajero pasajero);
	

	/**
	 * Método que da de baja un pasajero en la BBDD. 
	 * @param id es el ID del coche que se da de baja
	 * @return true si se da de baja. False si existe error en BBDD
	 */
	boolean delete(int id);

	/**
	 * Método que sirve para borrar un pasajero previa petición de un id de coche
	 * @param idCoche
	 * @param idPasajero
	 * @return
	 */
	boolean unAssign(int idPasajero);

}
