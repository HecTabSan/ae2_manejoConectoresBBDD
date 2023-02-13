package modelo.persistencia;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAObbdd {
	private Connection con;

    public void createDatabase(){
        String bbdd = "jdbc:mysql://localhost:3306";
        String user = "root";
        String pass = "";
        try {
            String sql = "CREATE DATABASE edix_ae2";
            con = DriverManager.getConnection(bbdd,user,pass);
            Statement st = con.createStatement();
            st.executeUpdate(sql);
            disconnect();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean connect(){
        String bbdd = "jdbc:mysql://localhost:3306/edix_ae2";
        String user = "root";
        String pass = "";
        try {
            con = DriverManager.getConnection(bbdd,user,pass);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public void disconnect(){
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTables(String tabla) {
        String pasajeros = """
                            CREATE TABLE pasajeros (
                                id INTEGER(4) PRIMARY KEY AUTO_INCREMENT,
                                nombre VARCHAR(20) NOT NULL,
                                edad INTEGER(3) NOT NULL,
                                peso DOUBLE NOT NULL,
                                id_coche INTEGER(4),
                                FOREIGN KEY (id_coche) REFERENCES coches(id)
                            );
                            """;
        String coches = """
                        CREATE TABLE coches(
                            id INTEGER(4) PRIMARY KEY AUTO_INCREMENT,
                            matricula VARCHAR(7) NOT NULL,
                            marca VARCHAR(20) NOT NULL,
                            modelo VARCHAR(20) NOT NULL,
                            color VARCHAR(20) NOT NULL
                        );
                        """;
        try{
            connect();
            Statement st = con.createStatement();
            if(tabla.equals("coches")) {
                st.executeUpdate(coches);
           }else if(tabla.equals("pasajeros")) {
                st.executeUpdate(pasajeros);
            }

        }catch (SQLException e) {
            System.out.println("checkTables()" + e.getMessage());
        }
        disconnect();
    }

    public void checkTable() {
        try {
            connect();
            String[] tables = {"coches","pasajeros"};
            DatabaseMetaData metaData = con.getMetaData();
            for (String table : tables) {
                ResultSet rs = metaData.getTables(null, null, table, null);
                if (!rs.next()) {
                    createTables(table);
                }
            }
        }catch (SQLException e) {
            System.out.println("checkTables() =" + e.getMessage());
        }
        disconnect();
    }
}
