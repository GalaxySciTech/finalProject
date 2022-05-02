package core.controller;

import core.dao.BankDao;
import core.model.Bank;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class BankController implements Initializable {

    @FXML
    public Label update;
    @FXML
    public TableView<Bank> table;
    @FXML
    public TableColumn<Bank, Integer> bankId;
    @FXML
    public TableColumn<Bank, Integer> bankUserId;
    @FXML
    public TableColumn<Bank, String> bankBalance;
    @FXML
    public Button addBtn;
    @FXML
    public Button updateBtn;
    @FXML
    public Button deleteBtn;
    @FXML
    public TextField updateId;
    @FXML
    public TextField updateBalance;
    @FXML
    public TextField updateUserId;

    BankDao bankDao;


    public boolean checkAccountIsWrong() {
        if (LoginController.u.getIsAdmin() != 1) {
            update.setText("This operation can only be performed by the admin");
            return true;
        }
        return false;
    }


    public void getBank() {
        Bank query = new Bank();
        if (LoginController.u.getIsAdmin() != 1)
            query.setUserId(LoginController.u.getId());
        ObservableList<Bank> list = FXCollections.observableArrayList(bankDao.find(query));
        if (list.isEmpty()) return;
        bankId.setCellValueFactory(new PropertyValueFactory<>("id"));
        bankUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        bankBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        table.setItems(list);
    }

    public void addBank(ActionEvent event) {
        if (checkAccountIsWrong()) return;
        Bank add = new Bank();
        add.setId(Integer.valueOf(updateId.getText()));
        add.setBalance(new BigDecimal(updateBalance.getText()));
        add.setUserId(Integer.valueOf(updateUserId.getText()));
        bankDao.add(add);
        getBank();
    }

    public void updateBank(ActionEvent event) {
        if (checkAccountIsWrong()) return;
        Bank update = new Bank();
        update.setId(Integer.valueOf(updateId.getText()));
        update.setBalance(new BigDecimal(updateBalance.getText()));
        update.setUserId(Integer.valueOf(updateUserId.getText()));
        bankDao.update(update);
        getBank();
    }

    public void deleteBank(ActionEvent event) {
        if (checkAccountIsWrong()) return;
        bankDao.delete(Integer.valueOf(updateId.getText()));
        getBank();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bankDao = new BankDao();
        getBank();
    }
}
