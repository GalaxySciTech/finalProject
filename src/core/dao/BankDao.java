//package finalProject.core.dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import finalProject.core.model.Bank;
//import finalProject.core.utlities.ConnectionFactory;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//
//public class BankDao {
//	private static ConnectionFactory connFactory = ConnectionFactory.getConnectionFactory();
//
//	public int save(Bank bank)
//	{
//		try {
//			Connection conn = connFactory.getConnection();
//				String sql ="INSERT INTO book (bookName, author, publisher,catogory,price,isbn,description)VALUES (?,?,?,?,?,?,?)";
//				PreparedStatement pStmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
//				pStmt.setString(1, bank.getBookName());
//				pStmt.setString(2, bank.getAuthor());
//				pStmt.setString(3, bank.getPublisher());
//				pStmt.setString(4,bank.getCatogory());
//				pStmt.setFloat (5, bank.getPrice());
//				pStmt.setString(6,bank.getIsbn());
//				pStmt.setString(7, bank.getDescription());
//
//				int generatedId = 0;
//				try {
//					conn.setAutoCommit(false);
//					pStmt.executeUpdate();
//					ResultSet resultSet = pStmt.getGeneratedKeys();
//
//					if (resultSet.next()) {
//						generatedId = resultSet.getInt(1);
//						System.out.println("generatedId : " + generatedId);
//						conn.commit();
//					} else {
//						conn.rollback();
//					}
//				} catch (SQLException e) {
//					e.printStackTrace();
//					try {
//						conn.rollback();
//					} catch (SQLException e1) {
//						e1.printStackTrace();
//					}
//				} finally {
//					try {
//						conn.close();
//					} catch (SQLException e) {
//						e.printStackTrace();
//					}
//				}
//				System.out.println("New Book Saved : " + generatedId);
//				return generatedId;
//
//	}catch (Exception e) {
//		e.printStackTrace();
//		return 0;
//	}
//	}
//
//	public List<Bank> find()
//	{
//		List<Bank> banks =new ArrayList<>();
//		try {
//			Connection conn = connFactory.getConnection();
//			String sql ="SELECT * FROM bank ORDER BY id ASC";
//			PreparedStatement pStmt = conn.prepareStatement(sql);
//			try {
//				conn.setAutoCommit(false);
//				ResultSet resultSet = pStmt.executeQuery();
//				while (resultSet.next()) {
//					Bank bank=new Bank();
//
//					banks.add(bank);
//				}
//			}catch (Exception e) {
//				e.printStackTrace();
//				return null;
//			}
//		return banks;
//		}
//		catch (Exception e) {
//			return null;
//		}
//	}
//
//	public	int update(Bank book)
//	{
//		int rowsUpdated = 0;
//		Connection conn = connFactory.getConnection();
//		try {
//			String sql ="UPDATE book SET bookName=?, author=?, publisher=?,catogory=?,price=?,isbn=?,description=? WHERE Id=?";
//			PreparedStatement pStmt = conn.prepareStatement(sql);
//			pStmt.setString(1, book.getBookName());
//			pStmt.setString(2, book.getAuthor());
//			pStmt.setString(3, book.getPublisher());
//			pStmt.setString(4,book.getCatogory());
//			pStmt.setFloat (5, book.getPrice());
//			pStmt.setString(6,book.getIsbn());
//			pStmt.setString(7, book.getDescription());
//			pStmt.setInt(8, book.getBookId());
//			conn.setAutoCommit(false);
//			rowsUpdated = pStmt.executeUpdate();
//			if (rowsUpdated==1) {
//				conn.commit();
//			} else {
//				conn.rollback();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		System.out.println("update book : "+rowsUpdated);
//		return rowsUpdated;
//	}
//
//	public int delete(int id)
//	{
//		int rowsUpdated = 0;
//		Connection conn = connFactory.getConnection();
//		try {
//			String sql ="Delete from book  WHERE id=?";
//			PreparedStatement pStmt = conn.prepareStatement(sql);
//			pStmt.setInt(1,id);
//			conn.setAutoCommit(false);
//			rowsUpdated = pStmt.executeUpdate();
//			if (rowsUpdated==1) {
//				conn.commit();
//			} else {
//				conn.rollback();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return rowsUpdated;
//		} finally {
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return id;
//	}
//}
