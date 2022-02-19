package banking.persistence;

import banking.business.Account;
import banking.business.Card;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLITE3AccountDAO implements AccountDAO{
    @Override
    public Account addAccount(Account a) {
        String insertPaymentSQL = "INSERT INTO \"CARD\" " +
                "(NUMBER , PIN) VALUES (?, ?)";

        try (Connection con = SQLITE3DAOFactory.createConnection()) {

            try (PreparedStatement insertPayment = con.prepareStatement(insertPaymentSQL);) {

                // Insert a payment
                insertPayment.setString(1, a.getCard().getCardNumber());
                insertPayment.setString(2, a.getCard().getCardPin());
                insertPayment.executeUpdate();
                System.out.println(a.getCard() + " saved");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    @Override
    public Account findAccount(String c) {
        Account account = null;
        String updatePaymentSQL = "SELECT * FROM \"CARD\" " +
                "WHERE NUMBER = ?";

        try (Connection con = SQLITE3DAOFactory.createConnection()) {

            try (PreparedStatement insertPayment = con.prepareStatement(updatePaymentSQL);) {
                // Insert a payment
                insertPayment.setString(1, c);

                ResultSet rs = insertPayment.executeQuery();
                while (rs.next()) {
                    account = new Account();
                    Card card = new Card();
                    card.setCardNumber(rs.getString(2));
                    card.setCardPin(rs.getString(3));
                    account.setBalance(rs.getLong(4));
                    account.setCard(card);
                    break;
                }
                System.out.println(" query successful");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public boolean cardExist(String c) {
        return false;
    }

    @Override
    public List<Account> allAccounts() {
        return null;
    }

    @Override
    public void createTable() {
        try (Statement statement = SQLITE3DAOFactory.createConnection().createStatement();){
            statement.execute("create table if not exists card(" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " NUMBER TEXT," +
                    " PIN TEXT," +
                    " BALANCE INTEGER DEFAULT 0" +
                    ")");
            System.out.println("CARD table created.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropTable() {
        try (Statement statement = SQLITE3DAOFactory.createConnection().createStatement();){
            statement.execute("drop table CARD if exists");
            System.out.println("CARD table dropped.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Account updateBalance(Account a) {
        String updatePaymentSQL = "UPDATE \"card\" " +
                "SET balance = ? WHERE number = ?";

        try (Connection con = SQLITE3DAOFactory.createConnection()) {

            try (PreparedStatement insertPayment = con.prepareStatement(updatePaymentSQL);) {

                // Insert a payment
                insertPayment.setLong(1, a.getBalance());
                insertPayment.setString(2, a.getCard().getCardNumber());
                insertPayment.executeUpdate();
                System.out.println(a.getCard().getCardNumber() + " updated");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    @Override
    public Account deleteAccount(Account a) {
        String updatePaymentSQL = "DELETE FROM \"card\" " +
                "WHERE number = ?";

        try (Connection con = SQLITE3DAOFactory.createConnection()) {

            try (PreparedStatement insertPayment = con.prepareStatement(updatePaymentSQL);) {

                // Delete a card
                insertPayment.setString(1, a.getCard().getCardNumber());
                insertPayment.executeUpdate();
                System.out.println(a.getCard().getCardNumber() + " deleted");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }
}
