package google.enums;

public enum NavigateMenuHrefPart {

    INBOX("inbox"),
    STARRED("starred");

    private String hrefPart;

    NavigateMenuHrefPart(String hrefPart) {
        this.hrefPart = hrefPart;
    }

    public String getHrefPart() {
        return hrefPart;
    }
}