package google.enums;

public enum RegExp {

    CURRENT_MAIL("\\((.*)\\)"),
    MESSAGE_BODY("(?m)^[ \t]*\r?\n");

    private String regularExpression;

    RegExp(String regularExpression) {
        this.regularExpression = regularExpression;
    }

    public String getRegExp() {
        return regularExpression;
    }
}