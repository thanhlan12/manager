package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Pplace;
/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 * 
 * @author Ramesh Fadatare
 *
 */
public class PplaceDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/dbtravel?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";

	private static final String insert_pplace = "INSERT INTO pplace" + "  (name, image, status) VALUES "
			+ " (?, ?, ?);";

	private static final String select_pplace_byid = "select id,name,image,status from pplace where id =?";
	private static final String select_allpplace = "select * from pplace";
	private static final String delete_pplace = "delete from pplace where id = ?;";
	private static final String update_pplace = "update pplace set name = ?,image= ?, status =? where id = ?;";

	public PplaceDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public void insertPplace(Pplace pplace) throws SQLException {
		System.out.println(insert_pplace);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(insert_pplace)) {
			preparedStatement.setString(1, pplace.getName());
			preparedStatement.setString(2, pplace.getImage());
			preparedStatement.setString(3, pplace.getStatus());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Pplace selectPplace(int id) {
		Pplace pplace = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(select_pplace_byid);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("name");
				String image = rs.getString("image");
				String status = rs.getString("status");
				pplace = new Pplace(id, name, image, status);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return pplace;
	}

	public List<Pplace> selectAllPplace() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Pplace> pplace = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(select_allpplace);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String image = rs.getString("image");
				String status = rs.getString("status");
				pplace.add(new Pplace(id, name, image, status));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return pplace;
	}

	public boolean deletePplace(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(delete_pplace);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updatePplace(Pplace pplace) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(update_pplace);) {
			statement.setString(1, pplace.getName());
			statement.setString(2, pplace.getImage());
			statement.setString(3, pplace.getStatus());
			statement.setInt(4, pplace.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
