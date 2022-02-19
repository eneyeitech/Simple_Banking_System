package banking.business;


import java.math.BigDecimal;

public class Account {
    //Banking Identification Number
    private final String BIN = "400000";
    private final String CHECK_DIGIT = "9";

    private String customer_account_number;
    private long balance = 0;
    private Card card;

    public Account() {
    }

    public Account(Card card) {
        this.card = card;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getBIN() {
        return BIN;
    }

    public String getCHECK_DIGIT() {
        return CHECK_DIGIT;
    }

    public String getCustomer_account_number() {
        return customer_account_number;
    }

    public void setCustomer_account_number(String customer_account_number) {
        this.customer_account_number = customer_account_number;
    }

    @Override
    public String toString() {
        return "Account{" +
                "BIN='" + BIN + '\'' +
                ", CHECK_DIGIT='" + CHECK_DIGIT + '\'' +
                ", customer_account_number='" + customer_account_number + '\'' +
                ", balance=" + balance +
                ", card=" + card +
                '}';
    }
}
