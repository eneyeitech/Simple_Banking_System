package banking.persistence;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLITE3DAOFactory extends DAOFactory{
        public static final String DRIVER = "org.h2.Driver";
        //public static final String DBURL = "jdbc:sqlite:C:/db/card.s3db";
        public static final String DBURL = "jdbc:sqlite:./card.s3db";

        // method to create H2 connections
        public static Connection createConnection() throws SQLException{
            // Use DRIVER and DBURL to create a connection
            // Recommend connection pool implementation/usage
            SQLiteDataSource dataSource = new SQLiteDataSource();
            dataSource.setUrl(DBURL);
            return dataSource.getConnection();
        }

        @Override
        public AccountDAO getAccountDAO() {
            // H2CompanyDAO implements CompanyDAO
            return new SQLITE3AccountDAO();
        }

    }
