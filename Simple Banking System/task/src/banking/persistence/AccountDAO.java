package banking.persistence;

import banking.business.Account;

import java.util.List;

public interface AccountDAO {
    public Account addAccount(Account a);
    public Account findAccount(String c);
    public boolean cardExist(String c);
    public List<Account> allAccounts();
    public void createTable();
    public void dropTable();
}
