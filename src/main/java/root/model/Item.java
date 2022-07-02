package root.model;

public class Item {
    private String title;
    private String body;

    public Item(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public Item() {
    }

    public String build() {
        return String.format("<b>%s</b>\n<code>%s</code>",title,body);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
