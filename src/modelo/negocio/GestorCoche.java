package modelo.negocio;

import java.util.List;

import modelo.entidad.Coche;
import modelo.persistencia.DaoCocheMySql;
import modelo.persistencia.interfaces.DaoCoche;

public class GestorCoche {
	private DaoCoche daoCoche = new DaoCocheMySql();
	
	/**
	 * Método que permite añadir un coche a una bbdd. Se verifica que los datos introducidos cumplen con 
	 * las restricciones establecidas en la bbdd.
	 * @param coche el objeto de la clase Coche que se añadirá a la bbdd
	 * @return true en caso de que haya podido añadir el coche a la bbdd
	 */
	public boolean aniadir(Coche coche) {
		boolean isAlta = false;
		boolean isVerificado = verificarCoche(coche);
		if (isVerificado) {
			isAlta = daoCoche.create(coche);
		}
		return isAlta;			
	}
	
	/**
	 * Método que permite borrar un coche por ID
	 * @param id el ID del objeto coche que se quiere borrar
	 * @return devuelve true si el coche es borrado
	 */
	public boolean borrar(int id) {
		boolean isBaja = false;
		boolean isVerificado = verificarId(id);
		if (isVerificado) {
			isBaja = daoCoche.delete(id);
		}
		return isBaja;		
	}
	
	/**
	 * Método que permite modificar los datos de un coche. Se verifica que los datos introducidos cumplen con 
	 * las restricciones establecidas en la bbdd.
	 * @param coche es la instancia coche que se quiere modificiar
	 * @return devuelve true si se actualizan correctamente los datos del coche
	 */
	public boolean modificar(Coche coche) {
		boolean isModificado = false;
		boolean isVerificado = verificarCoche(coche);
		if (isVerificado) {
			isModificado = daoCoche.update(coche);
		}
		return isModificado;
	}
	
	/**
	 * Método que permite leer los datos de un coche. Se verifica que los datos introducidos cumplen con 
	 * las restricciones establecidas en la bbdd.
	 * @param id es el ID de la instacia coche que se quiere consultar
	 * @return devuelve los datos de la instancia consultada
	 */
	public Coche consultar(int id) {
		Coche coche = new Coche();
		boolean isVerificado = verificarId(id);
		if (isVerificado) {
			coche = daoCoche.read(id);
		}
		return coche;
		
	}
	
	/**
	 * Método que lista todos los coches dados de alta en la tabla pasajeros
	 * @return devuelve una lista por consola
	 */
	public List<Coche> listar() {
		List<Coche> listaCoches = daoCoche.list();
		return listaCoches;
	}
	
	/**
	 * Método que verifica que los datos introducidos cumplen con los parámetros establecidos en la tabla coches
	 * @param coche la instancia coche de la que se quieren comprobar los datos
	 * @return devuelve true si la verificación es correcta
	 */
	public boolean verificarCoche(Coche coche) {
		String patronMatricula = "^[0-9]{4}[a-zA-Z]{3}$";
		String patronVarchar = "^\\w{1,20}$";
		boolean isVerificado = false;
		if (!coche.getMatricula().matches(patronMatricula)) {
			System.out.println("La matrícula deben ser 4 números y 3 letras sin espacios en blanco");
		} else if (!coche.getMarca().matches(patronVarchar)) {
			System.out.println("La marca debe contener entre 1 y 20 letras y/o números");
		} else if (!coche.getModelo().matches(patronVarchar)) {
			System.out.println("El modelo debe contener entre 1 y 20 letras y/o números");
		} else if (!coche.getColor().matches(patronVarchar)) {
			System.out.println("El color debe contener entre 1 y 20 letras y/o números");
		} else if (coche.getId() < 1 && coche.getId() > 9999) {
			System.out.println("El ID debe estar entre 1 y 9999");
		} else {
			isVerificado = true;
		}
		return isVerificado;
	}
	
	/**
	 * Método que verifica que el id introducido cumple con los parámetros establecidos en la tabla coches
	 * @param id el id del coche 
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
