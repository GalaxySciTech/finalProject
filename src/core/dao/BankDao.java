package core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import core.model.Bank;
import core.utlities.ConnectionFactory;

public class BankDao {
    private static ConnectionFactory connFactory = ConnectionFactory.getConnectionFactory();

    public List<Bank> find(Bank queryBank) {
        List<Bank> banks = new ArrayList<>();
        try {
            Connection conn = connFactory.getConnection();
            String sql = "SELECT * FROM bank  WHERE user_id=? ORDER BY id ASC ";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, queryBank.getUserId());
            try {
                conn.setAutoCommit(false);
                ResultSet resultSet = pStmt.executeQuery();
                while (resultSet.next()) {
                    Bank bank = new Bank();
                    bank.setId(resultSet.getInt("id"));
                    bank.setBalance(resultSet.getBigDecimal("balance"));
                    bank.setUserId(resultSet.getInt("user_id"));
                    banks.add(bank);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            return banks;
        } catch (Exception e) {
            return null;
        }
    }


}
