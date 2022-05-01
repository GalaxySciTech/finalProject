package core.controller;

import core.dao.BankDao;
import core.model.Bank;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BankController implements Initializable {

    @FXML
    public Label update;

    BankDao bankDao;

    public void getBank(ActionEvent event) {
        Bank query = new Bank();
        List<Bank> list = bankDao.find(query);

    }

    public void updateBank(ActionEvent event) {

    }

    public void deleteBank(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bankDao = new BankDao();
    }
}
