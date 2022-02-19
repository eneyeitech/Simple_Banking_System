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
                    accountService.save(account);
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
                System.out.println("You have successfully logged out!\n");
                choice();
                break;
            case "0":
            default:
                break;
        }
    }

    private void validateCard() {
        System.out.println("Enter your card number:");
        String cn = scanner.nextLine();
        System.out.println("Enter your PIN:");
        String p = scanner.nextLine();
        Account account = accountService.find(cn);
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
}
