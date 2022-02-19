package banking.presentation;

import banking.business.Account;
import banking.business.AccountService;

import java.util.List;
import java.util.Scanner;

public class DisplayAndControl {
    private final Scanner scanner;
    private final AccountService accountService;


    public DisplayAndControl() {
        scanner =  new Scanner(System.in);
        accountService = new AccountService();
    }

    public DisplayAndControl(AccountService accountService) {
        scanner =  new Scanner(System.in);
        this.accountService = accountService;
    }

    public void run() {
        choice();
    }

    private void choice(){
        String input = "";
        do {
            Menu.startMenu();
            input = scanner.nextLine();
            selection(input);
        } while (!input.equals("0"));
    }

    private void choice2(Account account){
        String input = "";
        do {
            Menu.loginMenu();
            input = scanner.nextLine();
            selection2(input, account);
        } while (!input.equals("0"));
    }

    private void selection(String inp) {
        switch (inp) {
            case "1":
                Account account = accountService.createAccount();
                if (account!=null) {
                    //accountService.save(account);
                    accountService.saveToDB(account);
                    System.out.println("Your card has been created");
                    System.out.printf("Your card number:\n%s\nYour card PIN:\n%s\n\n", account.getCard().getCardNumber(), account.getCard().getCardPin());

                }
                break;
            case "2":
                validateCard();
                break;
            case "3":
                List<Account> accounts = accountService.all();
                if (accounts.size() == 0) {
                    System.out.println("No accounts created\n");
                    return;
                }
                System.out.println("Created Accounts\n");
                for(Account a: accounts) {
                    System.out.println(a);
                }
                break;
            case "0":
                System.out.println("Bye!");
                System.exit(0);
            default:
                break;
        }
    }

    private void selection2(String inp, Account account) {
        switch (inp) {
            case "1":
                System.out.printf("Balance: %s\n", account.getBalance());
                break;
            case "2":
                addIncome(account);
                break;
            case "3":
                doTransfer(account);
                break;
            case "4":
                closeAccount(account);
                choice();
                break;
            case "5":
                System.out.println("You have successfully logged out!\n");
                choice();
                break;
            case "0":
                System.exit(0);
            default:
                break;
        }
    }

    private void validateCard() {
        System.out.println("Enter your card number:");
        String cn = scanner.nextLine();
        System.out.println("Enter your PIN:");
        String p = scanner.nextLine();
        //Account account = accountService.find(cn);
        Account account = accountService.findInDB(cn);
        System.out.println(account);
        if (account == null) {
            System.out.println("Wrong card number or PIN!\n");
            return;
        }

        if (!account.getCard().getCardPin().equalsIgnoreCase(p)){
            System.out.println("Wrong card number or PIN!\n");
            return;
        }

        System.out.println("You have successfully logged in!\n");
        choice2(account);
    }

    private void addIncome(Account account) {
        System.out.println("Enter income\n");
        long amt = scanner.nextLong();
        if (amt > 0) {
            account.setBalance(account.getBalance() + amt);
            accountService.updateBalanceInDB(account);
        }
        System.out.println("Income was added!\n");
    }

    private void doTransfer(Account account) {
        System.out.println("Transfer");
        System.out.println("Enter card number:");
        String cn = scanner.nextLine();

        if(account.getCard().getCardNumber().equalsIgnoreCase(cn)) {
            System.out.println("You can't transfer money to the same account\n");
            return;
        }

        if (!accountService.isValidCard(cn)) {
             System.out.println("Probably you made a mistake in the card number. Please try again!\n");
            return;
        }

        Account transferTo = accountService.findInDB(cn);
        System.out.println(transferTo);
        if (transferTo == null) {
            System.out.println("Such a card does not exist.\n");
            return;
        }

        System.out.println("Enter how much money you want to transfer:");
        long amt = scanner.nextLong();
        if (amt <= account.getBalance()) {
            account.setBalance(account.getBalance() - amt);
            accountService.updateBalanceInDB(account);

            transferTo.setBalance(transferTo.getBalance() + amt);
            accountService.updateBalanceInDB(transferTo);
        } else {
            System.out.println("Not enough money!");
            return;
        }
        System.out.println("Success!");
    }

    private void closeAccount(Account account) {
        accountService.deleteFromDB(account);
        System.out.println("The account has been closed!");

    }
}
