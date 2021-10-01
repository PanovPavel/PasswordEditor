package org.example;

import bi.ConnectionBD;
import dao.UsersDAO;
import enity.Users;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller extends ConnectionBD implements UsersDAO {
    @FXML
    TextArea websiteTextArea;
    @FXML
    TextArea passwordTextArea;
    @FXML
    TextArea loginTextArea;
    @FXML
    TextArea sendLogin;
    @FXML
    TextArea sendPassword;
    @FXML
    TextArea sendWebsity;

    Connection connectionsBD;
    public void initialize() {
        //connectionsBD = getConnection();
    }

    @Override
    public void add(Users users) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO USERS (login, password, website) VALUES (?, ?, ?);";
        try {
            preparedStatement = connectionsBD.prepareStatement(sql);
            preparedStatement.setString(1, users.getLogin());
            preparedStatement.setString(2, users.getPassword());
            preparedStatement.setString(3, users.getWebsite());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if(preparedStatement != null)preparedStatement.close();
            if(connectionsBD != null) connectionsBD.close();
        }
    }

    @Override
    public List<Users> getAllUsers() throws SQLException {
        List<Users> UserList = new ArrayList<>();
        String sql = "Select login, password, website FROM users";
        Statement statement = null;
        try {
            statement = connectionsBD.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Users users = new Users();
                users.setLogin(resultSet.getString("login"));
                users.setPassword(resultSet.getString("password"));
                users.setWebsite(resultSet.getString("website"));
                UserList.add(users);
            }
            for(int i = 0; i < UserList.size(); i++){
                System.out.println(UserList.get(i).getLogin() + " " + UserList.get(i).getPassword() + " " + UserList.get(i).getWebsite());
                if(UserList.get(i).getWebsite().equals(websiteTextArea.getText())){
                    passwordTextArea.setText(UserList.get(i).getPassword());
                    loginTextArea.setText(UserList.get(i).getLogin());
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            statement.close();
            connectionsBD.close();
        }

        return null;
    }


    public void swichButton(ActionEvent actionEvent) {
        connectionsBD = getConnection();
        new Thread (()-> {
            try {
                getAllUsers();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }).start();

    }

    public void sendButton(ActionEvent actionEvent) {
        connectionsBD = getConnection();
        new Thread (()-> {
        if(sendPassword.getText() != null && sendLogin.getText() != null && sendWebsity.getText() != null) {
            Users obUser = new Users();
            obUser.setPassword(sendPassword.getText());
            obUser.setLogin(sendLogin.getText());
            obUser.setWebsite(sendWebsity.getText());
            try {
                add(obUser);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        }).start();
    }
}
