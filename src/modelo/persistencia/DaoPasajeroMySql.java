package modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Pasajero;
import modelo.persistencia.interfaces.DaoPasajero;

public class DaoPasajeroMySql implements DaoPasajero {

	private Connection connection;
	
	public boolean openConnection() {
		String url = "jdbc:mysql://localhost:3306/edix_ae2";
		String user = "root";
		String password = "";
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean create(Pasajero pasajero) {
		boolean created = false;
		if (openConnection()) {

			String query = "insert into pasajeros (NOMBRE, EDAD, PESO) values(?,?,?)";
			try {
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1, pasajero.getNombre());
				ps.setInt(2, pasajero.getEdad());
				ps.setDouble(3, pasajero.getPeso());

				int numeroFilasAfectadas = ps.executeUpdate();
				if (numeroFilasAfectadas > 0) {
					created = true;
				}
			} catch (SQLException e) {
				System.out.println("Error al insertar: " + pasajero);
				e.printStackTrace();
			} finally {
				closeConnection();
			}
		}
		return created;
	}

	@Override
	public boolean create(int idPasajero, int idCoche) {
		boolean created = false;
		if (openConnection()) {

			String query = "UPDATE pasajeros SET id_coche=? where id=?";
			try {
				PreparedStatement ps = connection.prepareStatement(query);				
				ps.setInt(1, idCoche);
				ps.setInt(2, idPasajero);				

				int numeroFilasAfectadas = ps.executeUpdate();
				if (numeroFilasAfectadas > 0) {
					created = true;
				}
			} catch (SQLException e) {
				System.out.println("Error al insertar al pasajero");
				e.printStackTrace();
			} finally {
				closeConnection();
			}
		}
		return created;
		
	}

	@Override
	public Pasajero read(int id) {
		Pasajero pasajero = null;
		if (openConnection()) {

			String query = "SELECT * from pasajeros WHERE id_coche = ?";
			try {
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setInt(1, id);

				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					pasajero = new Pasajero();
					pasajero.setId(rs.getInt(1));
					pasajero.setNombre(rs.getString(2));
					pasajero.setEdad(rs.getInt(3));
					pasajero.setPeso(rs.getDouble(4));					
				}
			} catch (SQLException e) {
				System.out.println("Error al obtener el pasajero con id " + id);
				e.printStackTrace();
			} finally {
				closeConnection();
			}
		}
		return pasajero;
	}

	@Override
	public List<Pasajero> list() {
		List<Pasajero> listaPasajeros = new ArrayList<>();
		if (openConnection()) {

			String query = "select * from pasajeros";
			try {
				PreparedStatement ps = connection.prepareStatement(query);

				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					Pasajero pasajero = new Pasajero();
					pasajero.setId(rs.getInt(1));
					pasajero.setNombre(rs.getString(2));
					pasajero.setEdad(rs.getInt(3));
					pasajero.setPeso(rs.getDouble(4));

					listaPasajeros.add(pasajero);
				}
			} catch (SQLException e) {
				System.out.println("Error al obtener la lista de pasajeros");
				e.printStackTrace();
			} finally {
				closeConnection();
			}
		}
		return listaPasajeros;
	}
	
	@Override
	public List<Pasajero> list(int idCoche) {
		List<Pasajero> listaPasajeros = new ArrayList<>();
		if (openConnection()) {

			String query = "select id, nombre, edad, peso from pasajeros WHERE id_coche = ?";
			try {
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setInt(1, idCoche);
				
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					Pasajero pasajero = new Pasajero();
					pasajero.setId(rs.getInt(1));
					pasajero.setNombre(rs.getString(2));
					pasajero.setEdad(rs.getInt(3));
					pasajero.setPeso(rs.getDouble(4));				

					listaPasajeros.add(pasajero);
				}
			} catch (SQLException e) {
				System.out.println("Error al obtener la lista de pasajeros");
				e.printStackTrace();
			} finally {
				closeConnection();
			}
		}
		return listaPasajeros;
	}
	
	@Override
	public boolean update(Pasajero pasajero) {
		boolean updated = false;
		if (openConnection()) {

			String query = "update coches set NOMBRE=?, MARCA=?, EDAD=?, PESO=? WHERE ID=?";
			try {
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1, pasajero.getNombre());
				ps.setInt(2, pasajero.getEdad());
				ps.setDouble(3, pasajero.getPeso());
				ps.setInt(5, pasajero.getId());

				int numeroFilasAfectadas = ps.executeUpdate();
				if (numeroFilasAfectadas > 0) {
					updated = true;
				}
			} catch (SQLException e) {
				System.out.println("Error al modificar pasajero: " + pasajero);
				e.printStackTrace();
			} finally {
				closeConnection();
			}
		}
		return updated;
	}


	
	@Override
	public boolean delete(int id) {
		boolean deleted = false;
		if (openConnection()) {
			String query = "delete from pasajeros where id=?";
			try {
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setInt(1, id);

				int numeroFilasAfectadas = ps.executeUpdate();
				if (numeroFilasAfectadas > 0) {
					deleted = true;
				}
			} catch (SQLException e) {
				System.out.println("Error al borrar: " + id);
				e.printStackTrace();
			} finally {
				closeConnection();
			}
		}
		return deleted;
	}

	@Override
	public boolean unAssign(int idPasajero) {
		boolean deleted = false;
		if (openConnection()) {
			String query = "UPDATE pasajeros SET id_coche=NULL where id=?";
			try {
				PreparedStatement ps = connection.prepareStatement(query);				
				ps.setInt(1, idPasajero);

				int numeroFilasAfectadas = ps.executeUpdate();
				if (numeroFilasAfectadas > 0) {
					deleted = true;
				}
			} catch (SQLException e) {
				System.out.println("Error al borrar: " + idPasajero);
				e.printStackTrace();
			} finally {
				closeConnection();
			}
		}
		return deleted;
	}

	

	
}
