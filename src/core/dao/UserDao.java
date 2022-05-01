package core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import core.model.User;
import core.utlities.ConnectionFactory;

public class UserDao {

    private static ConnectionFactory connFactory = ConnectionFactory.getConnectionFactory();

    public User find(User user) {
        try {
            Connection conn = connFactory.getConnection();
            String sql = "SELECT * FROM rld_users WHERE username=? AND password=? AND is_admin=?";
            PreparedStatement pStmt = conn.prepareStatement(sql);

            pStmt.setNString(1, user.getUserName());
            pStmt.setNString(2, user.getPassword());
            pStmt.setInt(3, user.getIsAdmin());
            try {
                conn.setAutoCommit(false);
                ResultSet resultSet = pStmt.executeQuery();
                if (!resultSet.next()) {
                    System.out.println("User Not Found");
                    return null;
                }
                System.out.println("User Found");
                User u = new User();
                u.setUserName(resultSet.getString("username"));

                u.setPassword(resultSet.getString("password"));

                u.setIsAdmin(resultSet.getInt("is_admin"));

                return u;
            } catch (Exception e) {
                System.out.println("User Not Found");
                e.printStackTrace();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
