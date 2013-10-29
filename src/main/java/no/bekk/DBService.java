package no.bekk;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DBService implements Serializable {


    private static DBService dbService;

    private List<Comment> comments;
    private boolean visible;

    private DBService() {
        comments = new ArrayList<Comment>();
        comments.add(new Comment("Peter", "peter@hemmen.no", "Tjobing! Jeg heter Peter"));
        comments.add(new Comment("Ingrid", "ingrid@hjulstad.no", "Hei hei, jeg heter Ingrid"));

        visible = true;
    }

    public List<? extends Comment> getComments() {
        return comments;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public static DBService getInstance() {
        if (dbService == null) {
            dbService = new DBService();
        }
        return dbService;
    }

    public void toggleVisibility() {
        visible = !visible;
    }

    public boolean isVisible() {
        return visible;
    }
}
