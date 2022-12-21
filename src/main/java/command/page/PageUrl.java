package command.page;

public class PageUrl {
    private final String urlPath;
    private final Boolean isRedirection;
    private final String message;

    public PageUrl(String urlPath, Boolean isRedirection) {
        this.urlPath = urlPath;
        this.isRedirection = isRedirection;
        this.message = null;
    }
    public PageUrl(String urlPath, Boolean isRedirection, String message) {
        this.urlPath = urlPath;
        this.isRedirection = isRedirection;
        this.message = message;
    }

    public Boolean isRedirection(){
        return isRedirection;
    }
    public String getUrlPath() {
        return urlPath;
    }

    public String getMessage() {
        return message;
    }
}
