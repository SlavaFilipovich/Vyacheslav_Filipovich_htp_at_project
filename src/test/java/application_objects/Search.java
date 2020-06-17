package application_objects;

public class Search {
    private String user;
    private Boolean strict;

    public Search(String user, Boolean strict) {
        this.user = user;
        this.strict = strict;
    }

    @Override
    public String toString() {
        return "Search {\n" +
                "user='" + user + '\'' +
                ", \nstrict=" + strict +
                '}';
    }
}
