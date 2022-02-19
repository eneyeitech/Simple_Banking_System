package banking;

import banking.business.AccountService;
import banking.persistence.AccountDAO;
import banking.persistence.DAOFactory;
import banking.presentation.DisplayAndControl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String search = "-fileName";
        String dbName = "";
        List<String> arguments = new ArrayList<>(Arrays.asList(args));
        if (arguments.contains(search)) {
            dbName = args[arguments.indexOf(search) + 1];
        } else {
            dbName = "test";
        }

        System.out.println(dbName);

        AccountService accountService = new AccountService();
        accountService.createTable();

        DisplayAndControl displayAndControl = new DisplayAndControl(accountService);
        displayAndControl.run();


    }


}