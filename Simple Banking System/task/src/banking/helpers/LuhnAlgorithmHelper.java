package banking.helpers;

public class LuhnAlgorithmHelper {

    public  boolean checkLuhn(String cardNo) {
        int nDigits = cardNo.length();

        int sum = 0;
        for (int i = 0; i < nDigits; i++)
        {
            char tmp = cardNo.charAt(i);
            // #1: fixed the '0' problem
            int num = tmp - '0';
            int product;
            if (i % 2 != 0){
                product = num * 1;
            }
            else{
                product = num * 2;
            }
            if (product > 9)
                product -= 9;
            sum+= product;
        }

        return (sum % 10 == 0);
    }

    public  int luhnSum(String cardNo) {
        int nDigits = cardNo.length();

        int sum = 0;
        for (int i = 0; i < nDigits; i++)
        {
            char tmp = cardNo.charAt(i);
            // #1: fixed the '0' problem
            int num = tmp - '0';
            int product;
            if (i % 2 != 0){
                product = num * 1;
            }
            else{
                product = num * 2;
            }
            if (product > 9)
                product -= 9;
            sum+= product;
        }
        return sum;
    }
}
