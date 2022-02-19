package banking.persistence;

import banking.business.Account;

import java.util.List;

public interface IAccountStore {

    public Account addAccount(Account a);
    public Account findAccount(String c);
    public boolean cardExist(String c);
    public List<Account> allAccounts();
}
