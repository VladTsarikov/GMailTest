package google.enums;

public enum MessageOptionsActNumber {

    DELETE(10),
    SPAM(9);

    private int actNumber;

    MessageOptionsActNumber(int actNumber) {
        this.actNumber = actNumber;
    }

    public int getAttributeName() {
        return actNumber;
    }
}