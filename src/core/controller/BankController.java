package core.controller;

import core.dao.BankDao;
import core.model.Bank;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BankController implements Initializable {

    @FXML
    public Label update;
    @FXML
    public TableView table;
    @FXML
    public TableColumn bankId;
    @FXML
    public TableColumn bankUserId;
    @FXML
    public TableColumn bankBalance;
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
        query.setUserId(LoginController.u.getId());
        List<Bank> list = bankDao.find(query);
        if (list.isEmpty()) return;
        Bank bank = list.get(0);
        bankId.setText(bank.getId().toString());
        bankUserId.setText(bank.getUserId().toString());
        bankBalance.setText(bank.getBalance().toPlainString());
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
