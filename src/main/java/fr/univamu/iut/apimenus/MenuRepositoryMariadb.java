package fr.univamu.iut.apimenus;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MenuRepositoryMariadb implements MenuRepositoryInterface, Closeable {

    protected Connection dbConnection;

    public MenuRepositoryMariadb(String url, String user, String pwd) throws SQLException, ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        dbConnection = DriverManager.getConnection(url, user, pwd);
    }

    @Override
    public void close() {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Menu getMenu(int id) {
        Menu selectedMenu = null;

        String query = "SELECT * FROM Menus WHERE id=?";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();

            if (result.next()) {
                String name = result.getString("name");
                String creatorName = result.getString("creator_name");
                Date creationDate = result.getDate("creation_date");

                selectedMenu = new Menu(id, name, creatorName, creationDate);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return selectedMenu;
    }

    @Override
    public List<Menu> getAllMenus() {
        List<Menu> menus = new ArrayList<>();

        String query = "SELECT * FROM Menus";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String creatorName = result.getString("creator_name");
                Date creationDate = result.getDate("creation_date");

                Menu menu = new Menu(id, name, creatorName, creationDate);
                menus.add(menu);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return menus;
    }

    @Override
    public boolean updateMenu(int id, String name) {
        return false;
    }

    @Override
    public boolean updateMenu(int id, String name, String creatorName) {
        String query = "UPDATE Menus SET name=? WHERE id=?";
        int rowsAffected = 0;

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setInt(2, id);
            ps.setString(3, creatorName);

            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rowsAffected > 0;
    }

    @Override
    public Menu addMenu(Menu menu) {
        String query = "INSERT INTO Menus (name, creator_name, creation_date) VALUES (?, ?, ?, ?, ?)";
        int id = 0;

        try (PreparedStatement ps = dbConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, menu.getName());
            ps.setString(2, menu.getCreatorName());
            ps.setDate(3, new java.sql.Date(menu.getCreationDate().getTime()));

            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        menu.setId(id);
        return menu;
    }

    @Override
    public boolean deleteMenu(int id) {
        String query = "DELETE FROM Menus WHERE id=?";
        int rowsAffected = 0;

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id);
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rowsAffected > 0;
    }


}
