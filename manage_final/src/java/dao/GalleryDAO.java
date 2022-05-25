package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Gallery;
/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 * 
 * @author Ramesh Fadatare
 *
 */
public class GalleryDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/dbtravel?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";

	private static final String insert_gallery = "INSERT INTO gallery" + "  (name, image, country) VALUES "
			+ " (?, ?, ?);";

	private static final String select_gallery_byid = "select id,name,image,country from gallery where id =?";
	private static final String select_allgallery = "select * from gallery";
	private static final String delete_gallery = "delete from gallery where id = ?;";
	private static final String update_gallery = "update gallery set name = ?,image= ?, country =? where id = ?;";

	public GalleryDAO() {
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

	public void insertGallery(Gallery gallery) throws SQLException {
		System.out.println(insert_gallery);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(insert_gallery)) {
			preparedStatement.setString(1, gallery.getName());
			preparedStatement.setString(2, gallery.getImage());
			preparedStatement.setString(3, gallery.getCountry());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Gallery selectGallery(int id) {
		Gallery gallery = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(select_gallery_byid);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("name");
				String image = rs.getString("image");
				String country = rs.getString("country");
				gallery = new Gallery(id, name, image, country);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return gallery;
	}

	public List<Gallery> selectAllGallery() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Gallery> gallery = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(select_allgallery);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String image = rs.getString("image");
				String country = rs.getString("country");
				gallery.add(new Gallery(id, name, image, country));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return gallery;
	}

	public boolean deleteGallery(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(delete_gallery);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateGallery(Gallery gallery) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(update_gallery);) {
			statement.setString(1, gallery.getName());
			statement.setString(2, gallery.getImage());
			statement.setString(3, gallery.getCountry());
			statement.setInt(4, gallery.getId());

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
