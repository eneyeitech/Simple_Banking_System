package banking.helpers;

import java.util.Random;

public class CardPinGenerator {

    private int leftLimit = 48;
    private int rightLimit = 57;
    private int targetStringLength = 4;
    private Random random = new Random(1000000);

    public String generate4Digits() {
        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
