package banking.persistence;

import banking.business.Account;

import java.util.List;

public interface AccountDAO {
    public Account addAccount(Account a);
    public Account findAccount(String c);
    public Account updateBalance(Account a);
    public Account deleteAccount(Account a);
    public boolean cardExist(String c);
    public List<Account> allAccounts();
    public void createTable();
    public void dropTable();
}
