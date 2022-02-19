package banking.business;

import banking.database.Store;
import banking.helpers.CardPinGenerator;
import banking.helpers.CustomerAccountNumberGenerator;
import banking.persistence.AccountRepository;

import java.util.List;

public class AccountService {
    private AccountRepository accountRepository;
    private CustomerAccountNumberGenerator customerAccountNumberGenerator;
    private CardPinGenerator cardPinGenerator;

    public AccountService() {
        accountRepository = new AccountRepository();
        customerAccountNumberGenerator = new CustomerAccountNumberGenerator();
        cardPinGenerator = new CardPinGenerator();
    }

    public String createPin() {
        return cardPinGenerator.generate4Digits();
    }

    public String createCustomerAccountNumber() {
        String customerAccountNumber = customerAccountNumberGenerator.generate9Digits();
        return  customerAccountNumber;
    }

    public String createCardNumber(Account account, String customerAccountNumber) {
        String cardNumber = account.getBIN() + customerAccountNumber + account.getCHECK_DIGIT();
        return cardNumber;
    }

    public Account createAccount() {
        Account account = new Account();
        Card card = new Card();
        String customerAccountNumber = createCustomerAccountNumber();
        String cardPin = createPin();
        String cardNumber = createCardNumber(account, customerAccountNumber);

        card.setCardNumber(cardNumber);
        card.setCardPin(cardPin);

        account.setCustomer_account_number(customerAccountNumber);
        account.setCard(card);

        return account;
    }

    public Account save(Account account) {
        return accountRepository.addAccount(account);
    }

    public Account find(String c) {
        return accountRepository.findAccount(c);
    }

    public boolean exist(String c) {
        return accountRepository.cardExist(c);
    }

    public List<Account> all() {
        return accountRepository.allAccounts();
    }

}
