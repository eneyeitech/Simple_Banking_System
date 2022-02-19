package banking.business;

import banking.database.Store;
import banking.helpers.CardPinGenerator;
import banking.helpers.CustomerAccountNumberGenerator;
import banking.helpers.LuhnAlgorithmHelper;
import banking.persistence.AccountDAO;
import banking.persistence.AccountRepository;
import banking.persistence.DAOFactory;

import java.util.List;

public class AccountService {
    private AccountRepository accountRepository;
    private CustomerAccountNumberGenerator customerAccountNumberGenerator;
    private CardPinGenerator cardPinGenerator;
    private LuhnAlgorithmHelper luhnAlgorithmHelper;
    private AccountDAO accountDAO;
    private DAOFactory sqlite3Factory;

    public AccountService() {
        accountRepository = new AccountRepository();
        customerAccountNumberGenerator = new CustomerAccountNumberGenerator();
        cardPinGenerator = new CardPinGenerator();
        luhnAlgorithmHelper = new LuhnAlgorithmHelper();
        sqlite3Factory = DAOFactory.getDAOFactory(DAOFactory.SQLITE3);
        accountDAO = sqlite3Factory.getAccountDAO();
    }

    public String createPin() {
        return cardPinGenerator.generate4Digits();
    }

    public String createCustomerAccountNumber() {
        String customerAccountNumber = customerAccountNumberGenerator.generate9Digits();
        return  customerAccountNumber;
    }

    public String createCardNumber(Account account, String customerAccountNumber) {
        String cardNumber = account.getBIN() + customerAccountNumber;
        cardNumber += checkDigit(cardNumber);

        return cardNumber;
    }

    public int checkDigit(String cardNo) {

        int sum = luhnAlgorithmHelper.luhnSum(cardNo);
        if(sum % 10 == 0){
            return 0;
        }
        int rem = sum % 10;

        return 10 - rem;
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

    public Account saveToDB(Account account) {
        return accountDAO.addAccount(account);
    }

    public Account find(String c) {
        return accountRepository.findAccount(c);
    }

    public Account findInDB(String c) {
        return accountDAO.findAccount(c);
    }

    public boolean exist(String c) {
        return accountRepository.cardExist(c);
    }

    public List<Account> all() {
        return accountRepository.allAccounts();
    }

    public void createTable(){
        accountDAO.createTable();
    }

    public void dropTable() {
        accountDAO.dropTable();
    }
}
