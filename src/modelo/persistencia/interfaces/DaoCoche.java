package modelo.persistencia.interfaces;

import java.util.List;

import modelo.entidad.Coche;

public interface DaoCoche {

	/**
	 * Método que da de alta un coche en la BBDD. Genera ID numérico de forma
	 * automática e incremental.
	 * 
	 * @param coche es el coche que se da de alta
	 * @return true si se da de alta. False si existe error en BBDD
	 */
	boolean create(Coche coche);

	/**
	 * Método que devuelve los atributos de un objeto coche buscado por su ID en la
	 * bbdd
	 * 
	 * @param id es el ID del coche del que se quieren obtener los datos
	 * @return los atributos de clase (ID, matrícula, marca, modelo y color)
	 */
	Coche read(int id);

	/**
	 * Método que devuelve una lista de todos los coches de la bbdd
	 * 
	 * @return la lista de todos los coches de la bbdd
	 */
	List<Coche> list();

	/**
	 * Método que modifica un coche en la BBDD.
	 * 
	 * @param coche es el coche que se modifica
	 * @return true si modifica. False si existe error en BBDD
	 */
	boolean update(Coche coche);

	/**
	 * Método que da de baja un coche en la BBDD. 
	 * @param id es el ID del coche que se da de baja
	 * @return true si se da de baja. False si existe error en BBDD
	 */
	boolean delete(int id);

}
