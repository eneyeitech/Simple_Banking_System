package banking.business;

public class Card {
    private String cardNumber;
    private String cardPin;

    public Card() {
    }

    public Card(String cardNumber, String cardPin) {
        this.cardNumber = cardNumber;
        this.cardPin = cardPin;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardPin() {
        return cardPin;
    }

    public void setCardPin(String cardPin) {
        this.cardPin = cardPin;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardNumber='" + cardNumber + '\'' +
                ", cardPin='" + cardPin + '\'' +
                '}';
    }
}
