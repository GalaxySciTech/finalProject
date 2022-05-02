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
            PreparedStatement pStmt;
            if (queryBank.getUserId() == null) {
                String sql = "SELECT * FROM rld_bank  ORDER BY id ASC ";
                pStmt = conn.prepareStatement(sql);

            } else {
                String sql = "SELECT * FROM rld_bank  WHERE user_id=? ORDER BY id ASC ";
                pStmt = conn.prepareStatement(sql);
                pStmt.setInt(1, queryBank.getUserId());
            }

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

    public int add(Bank addBank) {
        try {
            Connection conn = connFactory.getConnection();
            String sql = "insert into rld_bank (user_id,balance)values(?,?)";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, addBank.getUserId());
            pStmt.setBigDecimal(2, addBank.getBalance());
            try {
                System.out.println("Add a record");
                return pStmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        } catch (Exception e) {
            return 0;
        }
    }

    public int update(Bank updateBank) {
        try {
            Connection conn = connFactory.getConnection();
            String sql = "update rld_bank set balance= ? ,user_id=? where id=?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setBigDecimal(1, updateBank.getBalance());
            pStmt.setInt(2, updateBank.getUserId());
            pStmt.setInt(3, updateBank.getId());
            try {
                System.out.println("Update a record");
                return pStmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        } catch (Exception e) {
            return 0;
        }
    }

    public int delete(Integer bankId) {
        try {
            Connection conn = connFactory.getConnection();
            String sql = "delete from rld_bank where id=?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, bankId);
            try {
                System.out.println("Delete a record");
                return pStmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        } catch (Exception e) {
            return 0;
        }
    }


}
