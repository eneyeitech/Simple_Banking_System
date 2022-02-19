package banking.persistence;

import banking.business.Account;
import banking.database.Store;

import java.util.List;

public class AccountRepository implements IAccountStore{
    private Store store = Store.getInstance();

    @Override
    public Account addAccount(Account a) {
        return store.addAccount(a);
    }

    @Override
    public Account findAccount(String c) {
        return store.findAccount(c);
    }

    @Override
    public boolean cardExist(String c) {
        return store.cardExist(c);
    }

    @Override
    public List<Account> allAccounts() {
        return store.all();
    }
}
