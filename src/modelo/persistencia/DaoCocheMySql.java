package modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Coche;
import modelo.persistencia.interfaces.DaoCoche;

public class DaoCocheMySql implements DaoCoche {

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
	public boolean create(Coche coche) {
		
		boolean created = false;
		if (openConnection()) {
			
			String query = "insert into coches (MATRICULA, MARCA, MODELO, COLOR) values(?,?,?,?)";
			try {
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1, coche.getMatricula());
				ps.setString(2, coche.getMarca());
				ps.setString(3, coche.getModelo());
				ps.setString(4, coche.getColor());

				int numeroFilasAfectadas = ps.executeUpdate();
				if (numeroFilasAfectadas > 0) {
					created = true;
				}
			} catch (SQLException e) {
				System.out.println("Error al insertar: " + coche);				
				e.printStackTrace();
			} finally {
				closeConnection();
			}
		}
		return created;
	}
	
	@Override
	public Coche read(int id) {
		Coche coche = null;
		if (openConnection()) {

			String query = "select * from coches where id = ?";
			try {
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setInt(1, id);

				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					coche = new Coche();
					coche.setId(rs.getInt(1));
					coche.setMatricula(rs.getString(2));
					coche.setMarca(rs.getString(3));
					coche.setModelo(rs.getString(4));
					coche.setColor(rs.getString(5));
				}
			} catch (SQLException e) {
				System.out.println("Error al obtener el coche con id " + id);
				e.printStackTrace();
			} finally {
				closeConnection();
			}
		}
		return coche;
	}

	@Override
	public List<Coche> list() {
		List<Coche> listaCoches = new ArrayList<>();
		if (openConnection()) {

			String query = "select * from coches";
			try {
				PreparedStatement ps = connection.prepareStatement(query);

				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					Coche coche = new Coche();
					coche.setId(rs.getInt(1));
					coche.setMatricula(rs.getString(2));
					coche.setMarca(rs.getString(3));
					coche.setModelo(rs.getString(4));
					coche.setColor(rs.getString(5));

					listaCoches.add(coche);
				}
			} catch (SQLException e) {
				System.out.println("Error al obtener la lista de coches");
				e.printStackTrace();
			} finally {
				closeConnection();
			}
		}
		return listaCoches;
	}

	@Override
	public boolean update(Coche coche) {
		boolean updated = false;
		if (openConnection()) {

			String query = "update coches set MATRICULA=?, MARCA=?, MODELO=?, COLOR=? WHERE ID=?";
			try {
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1, coche.getMatricula());
				ps.setString(2, coche.getMarca());
				ps.setString(3, coche.getModelo());
				ps.setString(4, coche.getColor());
				ps.setInt(5, coche.getId());

				int numeroFilasAfectadas = ps.executeUpdate();
				if (numeroFilasAfectadas > 0) {
					updated = true;
				}
			} catch (SQLException e) {
				System.out.println("Error al modificar coche: " + coche);
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
			String query = "delete from coches where id=?";
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

}
