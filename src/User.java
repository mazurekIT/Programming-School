package src;

import org.apache.commons.lang3.ArrayUtils;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private int id;
    private String username;
    private String email;
    private String password;

    //KONSTRUKTORY
    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.setPassword(password);
    }


    //ZAPYTANIA
    public static final String QUERY_INSERT = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
    public static final String QUERY_SELECT_ID = "SELECT * FROM users where id=?";
    public static final String QUERY_SELECT_ALL = "SELECT * FROM users";
    public static final String QUERY_UPDATE = "UPDATE users SET username=?, email=?, password=? where id = ?";
    public static final String QUERY_DELETE = "DELETE FROM users WHERE id=?";


    //METODY
    public void saveToDB(Connection conn) throws SQLException {
        if (this.id == 0) {
            String[] generatedColums = {"ID"};
            PreparedStatement preparedStatement = conn.prepareStatement(QUERY_INSERT, generatedColums);
            preparedStatement.setString(1, this.username);
            preparedStatement.setString(2, this.email);
            preparedStatement.setString(3, this.password);
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            while (generatedKeys.next()) {
                this.id = generatedKeys.getInt(1);
            }
        } else {
            PreparedStatement preparedStatement = conn.prepareStatement(QUERY_UPDATE);
            preparedStatement.setString(1, this.username);
            preparedStatement.setString(2, this.email);
            preparedStatement.setString(3, this.password);
            preparedStatement.setInt(4, this.id);
            preparedStatement.executeUpdate();


        }
    }

    public static User loadUserById(Connection connection, int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SELECT_ID);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            User loadedUser = new User();
            loadedUser.id = resultSet.getInt("id");
            loadedUser.username = resultSet.getString("username");
            loadedUser.email = resultSet.getString("email");
            loadedUser.password = resultSet.getString("password");
            return loadedUser;
        }
        return null;
    }

    public static User[] loadAllUsers(Connection conn) throws SQLException {
        User[] users = new User[0];
        PreparedStatement preparedStatement = conn.prepareStatement(QUERY_SELECT_ALL);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            User loadedUser = new User();
            loadedUser.id = resultSet.getInt("id");
            loadedUser.username = resultSet.getString("username");
            loadedUser.email = resultSet.getString("email");
            loadedUser.password = resultSet.getString("password");
            users = ArrayUtils.add(users, loadedUser);
        }
        return users;
    }

    public void deleteUser(Connection conn) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement(QUERY_DELETE);
        preparedStatement.setInt(1, this.id);
        preparedStatement.executeUpdate();
        this.id = 0;

    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


    //GETTERY i SETTERY
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        ;
    }
}
