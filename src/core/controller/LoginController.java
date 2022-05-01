package core.controller;


import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import core.dao.UserDao;
import core.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    @FXML
    private Label update;
    @FXML
    private TextField userName;
    @FXML
    private TextField password;
    @FXML
    private TextField isAdmin;
    @FXML
    public Button login;

    private UserDao userDao;

    public void login(ActionEvent event) throws IOException {
        User user = new User();
        user.setUserName(userName.getText());
        user.setPassword(password.getText());
        user.setIsAdmin(Boolean.parseBoolean(isAdmin.getText()) ? 1 : 0);
        User u = userDao.find(user);
        if (u == null) {
            update.setText("Invalid UserName or password");
            return;
        }
        AnchorPane root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view/bank.fxml")));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userDao = new UserDao();
    }

}
