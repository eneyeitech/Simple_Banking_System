package banking.helpers;

import java.util.Random;

public class CustomerAccountNumberGenerator {

    private int leftLimit = 48;
    private int rightLimit = 57;
    private int targetStringLength = 9;
    private Random random = new Random(1000000000);

    public String generate9Digits() {
        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
