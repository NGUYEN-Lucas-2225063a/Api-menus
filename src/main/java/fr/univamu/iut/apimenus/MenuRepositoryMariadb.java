package fr.univamu.iut.apimenus;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;
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
    public Menu getMenu(int menuId) {
        Menu selectedMenu = null;

        String query = "SELECT * FROM Menus WHERE menu_id=?";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, menuId);

            ResultSet result = ps.executeQuery();

            if (result.next()) {
                String name = result.getString("name");
                String creatorName = result.getString("creator_name");
                String creationDate = result.getString("creation_date");

                List<Plat> Plat = getPlatsByMenu(menuId);

                selectedMenu = new Menu(menuId, name, Plat, creatorName, creationDate);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return selectedMenu;
    }

    @Override
    public ArrayList<Menu> getAllMenus() {
        ArrayList<Menu> menus = new ArrayList<>();

        String query = "SELECT * FROM Menus";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                int menu_id = result.getInt("menu_id");
                String name = result.getString("name");
                String creatorName = result.getString("creator_name");
                String creationDate = result.getString("creation_date");

                List<Plat> Plat = getPlatsByMenu(menu_id);

                Menu currentmenu = new Menu(menu_id, name, Plat, creatorName, creationDate);
                menus.add(currentmenu);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return menus;
    }

    @Override
    public void addMenu(Menu menu) {
        String query = "INSERT INTO Menus (name, creator_name, creation_date) VALUES (?, ?, ?)";
        int rowsAffected = 0;

        try (PreparedStatement ps = dbConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, menu.getName());
            ps.setString(2, menu.getCreatorName());
            ps.setString(3, menu.getCreationDate());
            ps.executeUpdate();

            ResultSet result = ps.getGeneratedKeys();
            if (result.next()) {
                int id = result.getInt(1);
                for (Plat plat : menu.getPlats()) {
                    plat.setId(id);
                    addPlat(plat);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void addPlat(Plat plat) {
        String query = "INSERT INTO Plat (id, nom, description, prix, createur_nom, date_creation) VALUES (?, ?, ?, ?, ?, ?)";
        int rowsAffected = 0;

        try (PreparedStatement ps = dbConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, plat.getId());
            ps.setString(1, plat.getNom());
            ps.setString(2, plat.getDescription());
            ps.setDouble(3, plat.getPrix());
            ps.setString(4, plat.getCreateurNom());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateMenu(Menu menu) {
    String query = "UPDATE Menus SET name=? WHERE menu_id=?";

    if (menu.getPlats().isEmpty()) {
        return;
        }

        try{
            dbConnection.setAutoCommit(false);
            try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
                ps.setString(1, menu.getName());
                ps.setInt(2, menu.getMenuId());
                ps.executeUpdate();

                for (Plat plat : menu.getPlats()) {
                    updatePlat(plat);
                }

                dbConnection.commit();
            } catch (SQLException e) {
                dbConnection.rollback();
                throw new RuntimeException(e);
            } finally {
                dbConnection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateMenu(Menu menu,String creationDate) {
        String query = "UPDATE Menus SET name=?, creation_date=? WHERE menu_id=?";

        if (menu.getPlats().isEmpty()) {
            return;
        }

        try {
            dbConnection.setAutoCommit(false);
            try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
                ps.setString(1, menu.getName());
                ps.setString(2, creationDate);
                ps.setInt(3, menu.getMenuId());
                ps.executeUpdate();

                for (Plat plat : menu.getPlats()) {
                    updatePlat(plat);
                }

                dbConnection.commit();
            } catch (SQLException e) {
                dbConnection.rollback();
                throw new RuntimeException(e);
            } finally {
                dbConnection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updatePlat(Plat plat) {
        String query = "UPDATE Plat SET nom=?, description=?, prix=?, createur_nom=? WHERE id=?";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, plat.getNom());
            ps.setString(2, plat.getDescription());
            ps.setDouble(3, plat.getPrix());
            ps.setString(4, plat.getCreateurNom());
            ps.setInt(5, plat.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public boolean deleteMenu(int menuId) {
        String query = "DELETE FROM Menus WHERE menu_id=?";
        int rowsAffected = 0;

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, menuId);
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rowsAffected > 0;
    }

    @Override
    public void deletePlat(int id) {
        String query = "DELETE FROM Plat WHERE id=?";
        int rowsAffected = 0;

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id);
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Plat> getAllPlats() {
        ArrayList<Plat> Plat = new ArrayList<>();
        String query = "SELECT * FROM Plat";

        try(PreparedStatement ps = dbConnection.prepareStatement(query)){
            ResultSet result = ps.executeQuery();

            while(result.next()){
                int id = result.getInt("id");
                String nom = result.getString("nom");
                String description = result.getString("description");
                double prix = result.getDouble("prix");
                String createurNom = result.getString("createur_nom");

                Plat plat = new Plat(id, nom, description, prix, createurNom);
                plat.setId(id);
                Plat.add(plat);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Plat;
    }

    @Override
    public Plat getPlat(int id) {
        Plat selectedPlat = null;

        String query = "SELECT * FROM Plat WHERE id=?";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();

            if (result.next()) {
                String nom = result.getString("nom");
                String description = result.getString("description");
                double prix = result.getDouble("prix");
                String createurNom = result.getString("createur_nom");

                selectedPlat = new Plat(id, nom, description, prix, createurNom);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return selectedPlat;
    }

    @Override
    public ArrayList<Plat> getPlatsByMenu(int id) {
        ArrayList<Plat> listPlat = new ArrayList<>();

        String query = "SELECT * FROM Plat WHERE id=?";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                String nom = result.getString("nom");
                String description = result.getString("description");
                double prix = result.getDouble("prix");
                String createurNom = result.getString("createur_nom");

                Plat plat = new Plat(id, nom, description, prix, createurNom);
                listPlat.add(plat);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listPlat;
    }

}
