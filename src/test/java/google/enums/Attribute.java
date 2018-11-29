package google.enums;

public enum Attribute {

    EMAIL("email"),
    ARIA_LABEL("aria-label");

    private String attribute;

    Attribute(String attribute) {
        this.attribute = attribute;
    }

    public String getAttributeName() {
        return attribute;
    }
}