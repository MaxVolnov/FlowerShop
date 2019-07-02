package com.daoimpl;

import com.dao.DBConnector;
import com.dao.UserDAO;
import com.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    DBConnector connector;

    Connection conn;
    @PostConstruct
    public void init(){
        if (connector != null) {
            conn = connector.getInstance().getConnection();
        }
    }
    public User userInfo(String id) {
        User user = new User();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from users WHERE user_id = " + id);
            if (rs.next()) {
                user.id = rs.getInt("USER_ID");
                user.login = rs.getString("LOGIN");
                user.password = rs.getString("PASSWORD");
                user.name = rs.getString("NAME");
                user.e_mail = rs.getString("E_MAIL");
                user.balance = rs.getInt("BALANCE");
                user.discount = rs.getInt("DISCOUNT");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
