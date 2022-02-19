package banking.database;

import banking.business.Account;
import banking.business.Card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Store {
    private static Store instance;

    private Map<String, Account> accountMap;

    private Store() {
        accountMap = new HashMap<>();
    }

    public static Store getInstance() {
        if (instance == null) {
            instance = new Store();
        }
        return instance;
    }


    public Account addAccount(Account account) {
        return accountMap.put(account.getCard().getCardNumber(), account);
    }

    public Account findAccount(String cardNumber) {
        return accountMap.get(cardNumber);
    }

    public boolean cardExist(String cardNumber) {
        return accountMap.containsKey(cardNumber);
    }

    public List<Account> all() {
        List<Account> cards = new ArrayList<>();
        for (Map.Entry<String, Account> entry : accountMap.entrySet()) {
            cards.add(entry.getValue());
        }
        return cards;
    }

    public Map<String, Account> getAccountMap() {
        return accountMap;
    }

    public void setAccountMap(Map<String, Account> accountMap) {
        this.accountMap = accountMap;
    }
}
