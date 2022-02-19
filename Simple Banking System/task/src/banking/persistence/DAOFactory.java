package banking.persistence;

public abstract class DAOFactory {

    // List of DAO types supported by the factory
    public static final int SQLITE3 = 1;

    // There will be a method for each DAO that can be
    // created. The concrete factories will have to
    // implement these methods.
    public abstract AccountDAO getAccountDAO();

    public static DAOFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case SQLITE3:
                return new SQLITE3DAOFactory();
            default:
                return null;
        }
    }
}
